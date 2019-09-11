package yakir.face.graph;

import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * GqlApp
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/09 15:53.
 */
@SpringBootApplication
public class GqlApp {

    public static void main(String[] args) {

        new SpringApplicationBuilder(GqlApp.class)
                .web(WebApplicationType.REACTIVE)
                .bannerMode(Banner.Mode.OFF)
                .run(args);

    }
}
