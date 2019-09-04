package yakir.quarkus.quickstart;

import javax.enterprise.context.ApplicationScoped;

/**
 * GreetingService
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/05 14:36.
 */
@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return "Hello ".concat(name).concat(" !");
    }
}
