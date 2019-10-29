package standard.face.graph.model;

import yakir.face.graph.anno.Index;
import yakir.face.graph.anno.Struct;

import java.io.Serializable;

/**
 * Language
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/10 21:31.
 */
@Index(indices = {"code"})
@Index(indices = {"isoCode", "code"})
public class Language implements Serializable {

    private static final long serialVersionUID = 7236724131493479035L;

    private long   id;
    private String code;
    private String isoCode;
}
