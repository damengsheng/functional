package framewk.org.mybatis;

import org.mybatis.generator.api.dom.java.CompilationUnit;

import java.util.List;

/**
 * CompilationUnitGenerator
 *
 * @author yakir on 2019/11/26 12:51.
 */
public interface CompilationUnitGenerator {

    List<CompilationUnit> generate();
}
