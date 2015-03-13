package org.pcold.hello.resources

import org.junit.runner.RunWith
import org.pcold.hello.core.Saying
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

/**
 * @author pcold
 * @since Mar-2015
 */
@RunWith(classOf[JUnitRunner])
class HelloWorldResourceTest extends FlatSpec with Matchers {

  behavior of "the HelloWorldResource"

  it should "say hello with default name" in {
    val resource = new HelloWorldResource("hello, %s", "default")
    val hello = resource.sayHello(None)
    hello should be (Saying(1, "hello, default"))
  }

  it should "say hello to specified name" in {
    val resource = new HelloWorldResource("hello, %s", "default")
    val hello = resource.sayHello(Option("tester"))
    hello should be (Saying(1, "hello, tester"))
  }

}
