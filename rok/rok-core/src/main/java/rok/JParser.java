package rok;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import java.util.Set;

/**
 * JParser
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/08/20 10:12.
 */
public class JParser extends AbstractProcessor {


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {


        return false;
    }
}
