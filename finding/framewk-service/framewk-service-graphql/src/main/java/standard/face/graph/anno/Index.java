package standard.face.graph.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Index
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/11 11:31.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Repeatable(Struct.class)
public @interface Index {

    String[] indices() default {"id"};
}
