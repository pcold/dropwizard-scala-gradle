package org.pcold.hello.service

import org.junit.runner.RunWith
import org.pcold.hello.core.Saying
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class HelloServiceTest extends FlatSpec with Matchers {

  behavior of "the HelloService"
  
  it should "say hello"  in {
    val helloClient = HelloService.helloClient
    val hello = helloClient.sayHello("foo")
    hello should be (Saying(1, "Hello, foo!"))
  }
}



