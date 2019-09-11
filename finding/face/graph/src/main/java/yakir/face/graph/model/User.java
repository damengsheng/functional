package yakir.face.graph.model;

import yakir.face.graph.anno.Struct;

import java.time.LocalDateTime;

/**
 * User
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/09 16:11.
 */
@Struct
public class User {

    private Long          id;
    private String        name;
    private LocalDateTime birthday;
    private Language      language;
}
