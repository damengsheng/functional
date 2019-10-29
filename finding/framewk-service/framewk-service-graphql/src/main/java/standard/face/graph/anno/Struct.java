package standard.face.graph.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Data
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/09 16:09.
 */
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Struct {

    Index[] value() default {};

}
