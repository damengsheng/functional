package rok;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import java.util.Set;

/**
 * RokAbstructProcessor
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/08/21 18:13.
 */
abstract
public class RokAbstructProcessor extends AbstractProcessor {

    protected JavacTrees                trees;
    protected TreeMaker                 treeMaker;
    protected Name.Table                nameTable;
    protected Context                   context;
    protected Messager                  messager;
    protected Filer                     filer;
    protected Types                     types;
    protected Elements                  elements;
    protected ClassLoader               classLoader;
    protected Set<Symbol.PackageSymbol> specifiedPackages;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {

        JavacProcessingEnvironment javacProcessingEnv = (JavacProcessingEnvironment) processingEnv;

        super.init(javacProcessingEnv);

        this.trees             = JavacTrees.instance(javacProcessingEnv);
        this.context           = javacProcessingEnv.getContext();
        this.treeMaker         = TreeMaker.instance(context);
        this.nameTable         = Names.instance(context).table;
        this.messager          = javacProcessingEnv.getMessager();
        this.filer             = javacProcessingEnv.getFiler();
        this.types             = javacProcessingEnv.getTypeUtils();
        this.elements          = javacProcessingEnv.getElementUtils();
        this.classLoader       = javacProcessingEnv.getProcessorClassLoader();
        this.specifiedPackages = javacProcessingEnv.getSpecifiedPackages();
    }


}
