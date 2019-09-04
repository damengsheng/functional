package yakir.modular.service;

/**
 * HelloWorld
 *
 * @author yakir <a href="https://yakirchen.github.io">yakirchen.github.io</a> on 02/12/2019 18:58.
 */
public class SayHello implements Greeting {

    @Override
    public String hello(String name) {

        return "Hello ".concat(name).concat(" !");

    }

    @Override
    public void moduleInfo() {

        var module     = SayHello.class.getModule();
        var name       = module.getName();
        var isNamed    = module.isNamed();
        var descriptor = module.getDescriptor();

        var out = String.format("Name: %s \n%s名模块 \nDescriptor: %s", name, isNamed ? "具" : "无", descriptor);

        System.out.println(out);
    }
}
