package standard.core.foo;

public class Foobar {

    private String foo;
    private String bar;

    public String getFoo() {
        return foo;
    }

    public Foobar setFoo(String foo) {
        this.foo = foo;
        return this;
    }

    public String getBar() {
        return bar;
    }

    public Foobar setBar(String bar) {
        this.bar = bar;
        return this;
    }
}