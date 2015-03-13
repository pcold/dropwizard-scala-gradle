package org.pcold.hello.resources

import java.util.concurrent.atomic.AtomicLong
import javax.validation.Valid
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

import com.codahale.metrics.annotation.Timed
import org.pcold.hello.core.Saying

@Path("/hello")
@Produces(Array(MediaType.APPLICATION_JSON))
class HelloWorldResource(template: String, defaultName: String, counter: AtomicLong = new AtomicLong) {
  @GET
  @Timed
  @Valid
  def sayHello(@QueryParam("name") name: Option[String]): Saying = {
    val value: String = this.template.format(name.getOrElse(this.defaultName))
    Saying(this.counter.incrementAndGet(), value)
  }
}
