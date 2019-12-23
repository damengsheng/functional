package langs.kotlin

/**
 * MainKt
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2018/12/28 14:50.
 */

fun main(args: Array<String>) {

    val yakir = Person("yakir");
    val yakirChen = Person("yakirChen");

    var greeting: String = yakir.hello(Greetings.HelloGreeting());
    println(greeting);
    greeting = yakirChen.hello(Greetings.HiGreeting())
    println(greeting);

}