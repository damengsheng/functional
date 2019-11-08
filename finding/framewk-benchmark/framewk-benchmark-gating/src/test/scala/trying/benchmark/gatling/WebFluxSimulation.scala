package trying.benchmark.gatling

import java.util.concurrent.TimeUnit

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.FiniteDuration
import scala.language.postfixOps

class WebFluxSimulation extends Simulation {

  val httpProtocol = http
    //    .baseUrl("http://127.0.0.1:8082")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.1 Safari/605.1.15")
  //
  //  val scnDubbo = scenario("Dubbo")
  //    .repeat(1) {
  //      exec(http("Dubbo Hello")
  //        .get("http://127.0.0.1:8082/hello/hello/yakir"))
  //    }
  //    .inject(constantUsersPerSec(1000) during FiniteDuration.apply(1, TimeUnit.MINUTES))
  //
  //  //  val scnGrpc = scenario("Grpc")
  //    .forever(exec(http("Grpc Hello")
  //      .get("http://127.0.0.1:8088/grpc/hello/yakir")))
  //    .inject(atOnceUsers(1000))
  //
  //  val scnOpenFeign = scenario("OpenFeign")
  //    .repeat(1) {
  //      exec(http("OpenFeign Rest Hello")
  //        .get("http://127.0.0.1:8085/rest/hello/yakir"))
  //    }
  //    .inject(constantUsersPerSec(600) during FiniteDuration.apply(1, TimeUnit.MINUTES))

  val scnCloudProvider = scenario("webflux stream")
    .repeat(1) {
      exec(
        http("webflux stream")
          .get("http://127.0.0.1:8088/stream")
          .check(status.is(200))
      )
    }
    .inject(constantUsersPerSec(800) during FiniteDuration.apply(2, TimeUnit.MINUTES))


  //  val scnGateway = scenario("Gateway")
  //    .forever(exec(http("Gateway Rsa")
  //      .post("http://localhost:8766/api/v6/rsa")
  //      .body(StringBody("{\n\t\"gs_rsa\": \"GQ/8NLq6VIaFoREr9jujgopy/HbaNQOIUH2/d7BAL0soYUeYh+63Y4hlnm0xqclEXaHMExlp1P+1DNwGeAuzZcsAFNMpyNH0Nl35yjb52MHB4oMxykHauTDXw8wqCTUFigIAtFnBc0LjRbHiSDNKH27AHog6uoSqBMLtho3D38c=\"\n}"))
  //      .asJson))
  //    .inject(atOnceUsers(100))

  //  val scnGateway = scenario("scnZuul")
  //    .repeat(1) {
  //      exec(http("Zuul Rsa")
  //        .post("http://localhost:8766/api/v6/rsa")
  //        .body(StringBody("{\"gs_rsa\": \"GQ/8NLq6VIaFoREr9jujgopy/HbaNQOIUH2/d7BAL0soYUeYh+63Y4hlnm0xqclEXaHMExlp1P+1DNwGeAuzZcsAFNMpyNH0Nl35yjb52MHB4oMxykHauTDXw8wqCTUFigIAtFnBc0LjRbHiSDNKH27AHog6uoSqBMLtho3D38c=\"}"))
  //        .asJson)
  //        //.pause(FiniteDuration.apply(300, TimeUnit.MILLISECONDS))
  //    }
  //    .inject(
  //      constantUsersPerSec(200) during FiniteDuration.apply(1, TimeUnit.MINUTES)
  //    )
  //
  //  val scnZuul = scenario("scnZuul")
  //    .repeat(1) {
  //      exec(http("Zuul Rsa")
  //        .post("http://localhost:8765/api/v6/rsa")
  //        .body(StringBody("{\"gs_rsa\": \"GQ/8NLq6VIaFoREr9jujgopy/HbaNQOIUH2/d7BAL0soYUeYh+63Y4hlnm0xqclEXaHMExlp1P+1DNwGeAuzZcsAFNMpyNH0Nl35yjb52MHB4oMxykHauTDXw8wqCTUFigIAtFnBc0LjRbHiSDNKH27AHog6uoSqBMLtho3D38c=\"}"))
  //        .asJson)
  //      //.pause(FiniteDuration.apply(300, TimeUnit.MILLISECONDS))
  //    }
  //    .inject(
  //      constantUsersPerSec(180) during FiniteDuration.apply(20, TimeUnit.SECONDS)
  //    )


  //  val scnZuulSleep = scenario("ZuulSleep")
  //    .repeat(1) {
  //      exec(http("Zuul Sleep")
  //        .get("http://localhost:8765/api/v6/sleep")
  //      )
  //    }.pause(Duration.apply(2, TimeUnit.SECONDS))
  //    .inject(
  //      constantUsersPerSec(800) during FiniteDuration.apply(1, TimeUnit.MINUTES) randomized
  //    )
  //    .inject(constantUsersPerSec(100) during (60))

  //  val scnZuul = scenario("Zuul")
  //    .forever(exec(http("Zuul Rsa")
  //      .post("http://localhost:8765/api/v6/rsa")
  //      .body(StringBody("{\n\t\"gs_rsa\": \"GQ/8NLq6VIaFoREr9jujgopy/HbaNQOIUH2/d7BAL0soYUeYh+63Y4hlnm0xqclEXaHMExlp1P+1DNwGeAuzZcsAFNMpyNH0Nl35yjb52MHB4oMxykHauTDXw8wqCTUFigIAtFnBc0LjRbHiSDNKH27AHog6uoSqBMLtho3D38c=\"\n}"))
  //      .asJson))
  //    .inject(atOnceUsers(2000))

  //  val scnZuul_1 = scenario("Zuul_1")
  //    .forever(exec(http("Zuul_1 Rsa")
  //      .post("http://localhost:8764/api/v6/rsa")
  //      .body(StringBody("{\n\t\"gs_rsa\": \"GQ/8NLq6VIaFoREr9jujgopy/HbaNQOIUH2/d7BAL0soYUeYh+63Y4hlnm0xqclEXaHMExlp1P+1DNwGeAuzZcsAFNMpyNH0Nl35yjb52MHB4oMxykHauTDXw8wqCTUFigIAtFnBc0LjRbHiSDNKH27AHog6uoSqBMLtho3D38c=\"\n}"))
  //      .asJson))
  //    .inject(atOnceUsers(100))

  //    val scnCloudProvider = scenario("CloudProvider")
  //      .forever(exec(http("CloudProvider Rsa")
  //        .post("http://localhost:8084/api/v6/rsa")
  //        .body(StringBody("{\n\t\"gs_rsa\": \"GQ/8NLq6VIaFoREr9jujgopy/HbaNQOIUH2/d7BAL0soYUeYh+63Y4hlnm0xqclEXaHMExlp1P+1DNwGeAuzZcsAFNMpyNH0Nl35yjb52MHB4oMxykHauTDXw8wqCTUFigIAtFnBc0LjRbHiSDNKH27AHog6uoSqBMLtho3D38c=\"\n}"))
  //        .asJson))
  //      .inject(atOnceUsers(100))

  //  setUp(scnDubbo, scnOpenFeign, scnGrpc)
  //  setUp(scnGateway, scnZuul, scnZuul_1, scnCloudProvider)
  //  setUp(scnZuul)
  setUp(scnCloudProvider)
    .protocols(httpProtocol)
  //    .maxDuration(Duration.apply(5, TimeUnit.MINUTES))
}