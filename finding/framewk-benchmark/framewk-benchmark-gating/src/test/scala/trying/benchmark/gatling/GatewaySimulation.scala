package trying.benchmark.gatling

import java.util.concurrent.TimeUnit

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._

import scala.concurrent.duration.FiniteDuration
import scala.language.postfixOps

class GatewaySimulation extends Simulation {

  val httpProtocol = http
    //    .baseUrl("http://127.0.0.1:8082")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_1) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/12.0.1 Safari/605.1.15")

  val scnGateway = scenario("scnGateway")
    .repeat(1) {
      exec(http("Gateway Rsa")
        .post("http://localhost:8766/api/v6/rsa")
        .body(StringBody("{\"gs_rsa\": \"GQ/8NLq6VIaFoREr9jujgopy/HbaNQOIUH2/d7BAL0soYUeYh+63Y4hlnm0xqclEXaHMExlp1P+1DNwGeAuzZcsAFNMpyNH0Nl35yjb52MHB4oMxykHauTDXw8wqCTUFigIAtFnBc0LjRbHiSDNKH27AHog6uoSqBMLtho3D38c=\"}"))
        .asJson
      )
      //.pause(FiniteDuration.apply(300, TimeUnit.MILLISECONDS))
    }
    .inject(
      constantConcurrentUsers(200) during FiniteDuration.apply(1, TimeUnit.MINUTES)
    )

  setUp(scnGateway)
    .protocols(httpProtocol)
  //    .maxDuration(Duration.apply(5, TimeUnit.MINUTES))
}