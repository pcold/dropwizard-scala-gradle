package org.pcold.hello.core

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FlatSpec, Matchers}

@RunWith(classOf[JUnitRunner])
class SayingTest extends FlatSpec with Matchers {

  behavior of "a Saying"

  it should "have an id and some content" in {
    val saying = Saying(1, "content")
    saying.id should be (1)
    saying.content should be ("content")
  }
}
