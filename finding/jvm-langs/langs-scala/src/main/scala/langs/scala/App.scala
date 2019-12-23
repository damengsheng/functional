package langs.scala

/**
 * App
 *
 * @author yakir on 2019/11/18 21:33.
 */
object App {

  def foo(x: Array[String]) = x.foldLeft("")((a, b) => a + b)

  def main(args: Array[String]) {
    println("Hello World!")
    println("concat arguments = " + foo(args))
  }

}
