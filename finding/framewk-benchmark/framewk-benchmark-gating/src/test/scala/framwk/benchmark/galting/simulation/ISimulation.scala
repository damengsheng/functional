package framwk.benchmark.galting.simulation

import io.gatling.core.scenario.Simulation

/**
 * ISimulation
 *
 * @author yakir on 2019/11/20 19:22.
 */
trait ISimulation extends Simulation {

  val userAgentHeader: String = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.108 Safari/537.36"

}
