package langs.kotlin

/**
 * Person
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2018/12/28 14:51.
 */
class Person
constructor(name: String) {

    private var name: String;

    init {
        this.name = name;
    }

    fun hello(greeting: Greeting): String {
        return greeting.greeting(name);
    }

    override fun toString(): String {
        return "Person(name='$name')"
    }
}