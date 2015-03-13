package org.pcold.hello

import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

/**
 * @author pcold
 * @since Mar-2015
 */
class HelloWorldConfiguration extends Configuration {
  @NotEmpty
  val template: String = ""

  @NotEmpty
  val defaultName: String = ""
}
