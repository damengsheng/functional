package framwk.benchmark.galting.simulation

import java.util.concurrent.TimeUnit

import io.gatling.core.Predef._
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.concurrent.duration.FiniteDuration;

/**
 * GraphqlSimulation
 *
 * @author yakir on 2019/11/20 19:24.
 */
class GraphqlSimulation extends ISimulation {

  var host: String = "master.shushi.vip"
  var port: String = "8092"

  val potocolBuilder: HttpProtocolBuilder = http.userAgentHeader(userAgentHeader)

  val requestBuilder: HttpRequestBuilder = http("modelLoadRequest")
    .post("http://".concat(host).concat(":").concat(port).concat("/pamirs/pro.shushi.pamirs.base"))
    .body(StringBody("{\"operationName\":null,\"variables\":{\"mode\":\"product\",\"context\":{\"host\":\"master.shushi.vip\",\"activeIds\":\"\"}},\"query\":\"{\\n  modelQuery {\\n    load(condition: {rsql: \\\"id>0\\\", size: 10}) {\\n      content {\\n        id\\n        name\\n        model\\n        state\\n        info\\n        isTransient\\n        displayName\\n        masterFieldStr\\n        masterFields {\\n          id\\n          name\\n          model\\n          __typename\\n        }\\n        progressFieldStr\\n        modelFields {\\n          id\\n          name\\n          displayName\\n          ttype\\n          model\\n          modelId\\n          relation\\n          relationField\\n          related\\n          selection\\n          store\\n          domain\\n          isUnique\\n          required\\n          readonly\\n          invisible\\n          size\\n          __typename\\n        }\\n        views {\\n          id\\n          name\\n          type\\n          arch\\n          sequence\\n          model {\\n            id\\n            model\\n            name\\n            displayName\\n            __typename\\n          }\\n          __typename\\n        }\\n        viewActions {\\n          id\\n          name\\n          actionType\\n          target\\n          domain\\n          viewId\\n          viewMode\\n          viewType\\n          viewCategory\\n          context\\n          contextType\\n          bindingType\\n          model {\\n            writeUid\\n            isEnhance\\n            exportVersion\\n            signFields\\n            model\\n            state\\n            id\\n            info\\n            isQueryExported\\n            isMutationExported\\n            name\\n            exportGroup\\n            isTransient\\n            __typename\\n          }\\n          resModel {\\n            writeUid\\n            isEnhance\\n            exportVersion\\n            signFields\\n            model\\n            state\\n            id\\n            info\\n            isQueryExported\\n            isMutationExported\\n            name\\n            exportGroup\\n            isTransient\\n            __typename\\n          }\\n          __typename\\n        }\\n        serverActions {\\n          usageDesc\\n          actionType\\n          name\\n          displayName\\n          contextType\\n          bindingType\\n          model {\\n            isEnhance\\n            exportVersion\\n            signFields\\n            model\\n            state\\n            id\\n            createUid\\n            info\\n            isQueryExported\\n            isMutationExported\\n            name\\n            exportGroup\\n            isTransient\\n            __typename\\n          }\\n          id\\n          fun {\\n            codes\\n            imports\\n            argNames\\n            type\\n            argTypes\\n            name\\n            namespace\\n            context\\n            id\\n            returnType\\n            funName\\n            __typename\\n          }\\n          __typename\\n        }\\n        urlActions {\\n          id\\n          name\\n          url\\n          target\\n          usageDesc\\n          actionType\\n          contextType\\n          bindingType\\n          groups {\\n            writeUid\\n            name\\n            comment\\n            id\\n            __typename\\n          }\\n          model {\\n            writeUid\\n            isEnhance\\n            exportVersion\\n            signFields\\n            model\\n            state\\n            id\\n            info\\n            isQueryExported\\n            isMutationExported\\n            name\\n            exportGroup\\n            isTransient\\n            __typename\\n          }\\n          __typename\\n        }\\n        __typename\\n      }\\n      __typename\\n    }\\n    __typename\\n  }\\n}\\n\"}"))
    .header("pamirs-host", host)
    .header("Cookie", "JSESSIONID=8F777757C507B2E86517BE32D585AD7A; pamirs_uc_session_id=8F777757C507B2E86517BE32D585AD7A")
    .asJson

  val modelLoad = scenario("modelLoad")
    .repeat(1) {
      exec(requestBuilder)
    }
    .inject(
      //      constantConcurrentUsers(50) during FiniteDuration.apply(10, TimeUnit.MINUTES)

      rampUsersPerSec(10) to (20) during FiniteDuration.apply(10, TimeUnit.MINUTES)
    )

  setUp(modelLoad)
    .protocols(potocolBuilder)
}
