package org.pcold.hello

import com.massrelevance.dropwizard.ScalaApplication
import com.massrelevance.dropwizard.bundles.ScalaBundle
import io.dropwizard.setup.{Bootstrap, Environment}
import org.pcold.hello.health.TemplateHealthCheck
import org.pcold.hello.resources.HelloWorldResource

/**
 * @author pcold
 * @since Mar-2015
 */
object HelloWorldApplication extends ScalaApplication[HelloWorldConfiguration] {
  override def initialize(bootstrap: Bootstrap[HelloWorldConfiguration]): Unit = {
    bootstrap.addBundle(new ScalaBundle)
  }

  override def run(conf: HelloWorldConfiguration, env: Environment): Unit = {
    env.jersey.register(new HelloWorldResource(conf.template, conf.defaultName))
    env.healthChecks.register("template", TemplateHealthCheck(conf.template))
  }
}
