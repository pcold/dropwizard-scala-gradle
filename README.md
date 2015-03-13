# dropwizard-scala-gradle
hello world dropwizard app with scala plugin and gradle build.

# gradle
i couldn't find an example of a dropwizard multi project build using gradle, so here's one 
with projects as recommended in the dropwizard documentation. the exception is that they indicate 
that the client project should be used for interactions with external api's.  the client in this 
project is a jar that can be used by other apps to interact with this one.  that just seems to make 
more sense to me.  i tried to keep the dependencies on the client jar minimal by using explicit dependencies 
instead of just grabbing the whole dropwizard-client.

# shadow jar
com.github.jengelman.gradle.plugins:shadow used to replicate the maven shade build used in the dropwizard
example
  
# dropwizard-scala
i also couldn't seem to find a basic 'hello world' using the dropwizard-scala plugin.

# scalatest and scoverage
test cases using scalatest with scoverage for coverage checks.
