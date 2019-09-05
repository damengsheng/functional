package yakir.quarkus.quickstart;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * GreetingResource
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2019/09/05 22:01.
 */
@Path("/hello")
public class GreetingResource {

    @Inject
    private GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello\n";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String hello(@PathParam("name") String name) {
        return greetingService.greeting(name);
    }
}