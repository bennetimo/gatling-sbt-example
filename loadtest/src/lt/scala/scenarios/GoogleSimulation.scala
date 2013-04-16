package scenarios

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._

class GoogleSimulation() extends Simulation {

	val httpConf = httpConfig
			.baseURL("http://www.google.co.uk")
			.acceptHeader("*/*")
			.acceptEncodingHeader("gzip, deflate")
			.acceptLanguageHeader("en-us")
			.connection("keep-alive")
			.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_8_2) AppleWebKit/536.26.14 (KHTML, like Gecko) Version/6.0.1 Safari/536.26.14")

	val headers = Map(
			"Accept" -> """text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"""
	)

	val scn = scenario("Get Google UK")
		.exec(http("google")
					.get("/")
					.headers(headers)
          .check(status.is(200))
			)
		.pause(2)
    .exec(http("google")
          .get("/")
          .headers(headers)
          .check(status.is(200))
  )
    .pause(2)

	setUp(scn.users(10).protocolConfig(httpConf))
}