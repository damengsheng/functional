package search.lucene;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * LuceneIndexer
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 11/15/2018 16:42.
 */
public class LuceneIndexer {

    private static final Logger logger = LogManager.getLogger(LuceneIndexer.class);

    public void index(String indexPath, String filePath) throws IOException {

        Path              path     = Paths.get(indexPath);
        Directory         dir      = SimpleFSDirectory.open(path);
        StandardAnalyzer  analyzer = new StandardAnalyzer();
        IndexWriterConfig iwcfg    = new IndexWriterConfig(analyzer);
        IndexWriter       iw       = new IndexWriter(dir, iwcfg);
        File[]            files    = new File(filePath).listFiles();

        for (File file : files == null ? new File[0] : files) {
            Document doc = new Document();
            try {
                doc.add(new TextField("contents", new FileReader(file)));
                doc.add(new TextField("fileName", file.getName(), Field.Store.YES));
                doc.add(new TextField("fullPath", file.getCanonicalPath(), Field.Store.YES));
                iw.addDocument(doc);
            } catch (IOException e) {
                logger.error("error ", e);
            }
        }
        logger.info("索引文档数 {}", iw.numRamDocs());
        iw.close();
    }

    public void doSearch(String indexDir, String queryStr) throws IOException, ParseException, InvalidTokenOffsetsException {

        // 查询字段
        String          fieldName = "contents";
        Directory       dir       = FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader    = DirectoryReader.open(dir);
        IndexSearcher   searcher  = new IndexSearcher(reader);
        Analyzer        analyzer  = new SmartChineseAnalyzer();
        QueryParser     parser    = new QueryParser(fieldName, analyzer);
        Query           query     = parser.parse(queryStr);

        long    startTime = System.currentTimeMillis();
        TopDocs docs      = searcher.search(query, 10);

        logger.info("查找 {} 所用时间：{}", queryStr, (System.currentTimeMillis() - startTime));
        logger.info("查询到 {} 条记录", docs.totalHits);

        //加入高亮显示的
        SimpleHTMLFormatter sHTMLFormatter = new SimpleHTMLFormatter("<b><font color=red>", "</font></b>");
        //计算查询结果最高的得分
        QueryScorer scorer = new QueryScorer(query);
        //根据得分算出一个片段
        Fragmenter  fragmenter  = new SimpleSpanFragmenter(scorer);
        Highlighter highlighter = new Highlighter(sHTMLFormatter, scorer);
        //设置显示高亮的片段
        highlighter.setTextFragmenter(fragmenter);

        //遍历查询结果
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc      = searcher.doc(scoreDoc.doc);
            String   tcontent = doc.get(fieldName);
            if (tcontent != null) {
                TokenStream tokenStream = analyzer.tokenStream(fieldName, new StringReader(tcontent));
                String      summary     = highlighter.getBestFragment(tokenStream, tcontent);
                logger.info(summary);
            }
        }
        reader.close();
    }

    public static void main(String[] args) {
        LuceneIndexer luceneIndexer = new LuceneIndexer();
        try {
            luceneIndexer.index("/Volumes/sm/repos/lucene", "/Volumes/sm/logs/idea");
        } catch (IOException e) {
            logger.error("error ", e);
        }

        String indexDir = "/Volumes/sm/repos/lucene";
        String q        = "gc"; // 查询字段值
        try {
            luceneIndexer.doSearch(indexDir, q);
        } catch (Exception e) {
            logger.error("error ", e);
        }
    }
}
