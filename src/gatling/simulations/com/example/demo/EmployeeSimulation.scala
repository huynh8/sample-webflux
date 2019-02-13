package com.example.demo

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class EmployeeSimulation extends Simulation {

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080")

  val scn: ScenarioBuilder = scenario("com.example.demo.EmployeeSimulation")
    .exec(http("request-1")
        .get("/employees")
        .check(status.is(200))
    )

  val users: Integer = Integer.getInteger("users", 1)

  setUp(scn.inject(atOnceUsers(users)).protocols(httpProtocol))
}
