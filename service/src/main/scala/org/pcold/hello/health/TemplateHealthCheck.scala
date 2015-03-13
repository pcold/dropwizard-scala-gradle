package org.pcold.hello.health

import com.codahale.metrics.health.HealthCheck
import com.codahale.metrics.health.HealthCheck.Result

case class TemplateHealthCheck(template: String) extends HealthCheck {
    override def check(): Result = {
      val saying = this.template.format("TEST")
      if (!(saying contains "TEST"))
        Result.unhealthy("template doesn't include a name")
      else
        Result.healthy()
    }
}

