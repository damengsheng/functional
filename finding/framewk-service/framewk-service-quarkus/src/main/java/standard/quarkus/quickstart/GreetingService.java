package standard.quarkus.quickstart;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

/**
 * GreetingService
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/05 14:36.
 */
@ApplicationScoped
public class GreetingService {

    public String greeting(String name) {
        return Optional.ofNullable(name)
                .map(_name -> "Hello ".concat(_name).concat(" !"))
                .orElse("");
    }
}
