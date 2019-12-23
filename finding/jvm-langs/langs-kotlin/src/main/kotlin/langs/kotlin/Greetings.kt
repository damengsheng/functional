package langs.kotlin

/**
 * Greetings
 *
 * @author yakir <a href="yakirchen.github.io">yakirchen.github.io</a> on 2018/12/28 13:59.
 */
class Greetings {

    class HelloGreeting : Greeting {
        override fun greeting(str: String): String {
            return "Hello " + str + " !";
        }
    }

    class HiGreeting : Greeting {
        override fun greeting(str: String): String {
            return "Hi " + str + " !";
        }
    }
}
