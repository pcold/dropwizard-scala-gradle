package org.pcold.hello.health

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author pcold
 * @since Mar-2015
 */
@RunWith(classOf[JUnitRunner])
class TemplateHealthCheckTest extends FlatSpec with Matchers {

  behavior of "a TemplateHealthCheck"

  it should "be unhealthy without parameterized template" in {
    val check = TemplateHealthCheck("hi!")
    val result = check.check()
    result.isHealthy should be (false)
  }

  it should "be healthy with parameterized template" in {
    val check = TemplateHealthCheck("hi, %s!")
    val result = check.check()
    result.isHealthy should be (true)
  }

}
