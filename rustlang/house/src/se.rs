use tantivy::collector::*;
use tantivy::query::QueryParser;
use tantivy::schema::{Field, Schema, SchemaBuilder, STORED, TEXT};
use tantivy::{Index, ReloadPolicy};
use tempdir::TempDir;

/// 索引文档
pub fn index() -> tantivy::Result<()> {
    let mut schema_builder = SchemaBuilder::default();
    let title: Field = schema_builder.add_text_field("title", TEXT | STORED);
    let body: Field = schema_builder.add_text_field("body", TEXT);
    let schema: Schema = schema_builder.build();

    let index_path = TempDir::new("/Volumes/sm/test/tantivy");
    let index = Index::create_in_dir(&index_path, schema.clone())?;
    let mut index_writer = index.writer(100_000_000);

    index_writer.add_document(doc!(
    title=> "The Old Man and The Sea",
    body=>"He was an old man who fished alone in a skiff in \
    the Gulf Stream and he had gone eighty-four days \
    now whithout taking a fish. "
    ));

    index_writer.commit();

    let reader = index
        .reader_builder()
        .reload_policy(ReloadPolicy::OnCommit)
        .try_into()?;
    let searcher = reader.searcher();

    let query_parser = QueryParser::for_index(&index, vec![title, body]);
    let query = query_parser.parse_query("sea whale")?;
    let top_docs = searcher.search(&query, &TopDocs::with_limit(10))?;
    for (_score, doc_address) in top_docs {
        let retrieved_doc = searcher.doc(doc_address)?;
        println!("{}", schema.to_json(&retrieved_doc));
    }
    Ok(())
}
