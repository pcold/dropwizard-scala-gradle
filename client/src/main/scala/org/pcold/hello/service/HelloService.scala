package org.pcold.hello.service

import javax.ws.rs.core.MediaType

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.sun.jersey.api.client.Client
import com.sun.jersey.client.apache4.config.DefaultApacheHttpClient4Config
import com.sun.jersey.client.apache4.{ApacheHttpClient4, ApacheHttpClient4Handler}
import org.apache.http.config.SocketConfig
import org.apache.http.impl.client.{BasicCookieStore, HttpClientBuilder}
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.pcold.hello.core.Saying

trait HelloResourceComponent {
  val helloResource: HelloResource

  trait HelloResource {
    def sayHello(name: String): Saying
  }

  class SimpleHelloResource extends HelloResource {
    override def sayHello(name: String): Saying = Saying(1, "hello, " + name)
  }

  class HttpHelloResource extends HelloResource {

    val client = getClient

    override def sayHello(name: String): Saying = {
      client.resource("http://localhost:8080/hello")
        .queryParam("name", name)
        .accept(MediaType.APPLICATION_JSON)
        .get(classOf[Saying])
    }

    private def getClient: Client = {
      val connectionManager = new PoolingHttpClientConnectionManager
      connectionManager.setMaxTotal(200)
      connectionManager.setDefaultMaxPerRoute(10)
      val socketConfig = SocketConfig.custom()
        .setSoTimeout(1000)
        .build()
      val httpClient = HttpClientBuilder.create()
        .setConnectionManager(connectionManager)
        .setDefaultSocketConfig(socketConfig)
        .build()

      val clientHandler = new ApacheHttpClient4Handler(httpClient, new BasicCookieStore, false)
      val config = new DefaultApacheHttpClient4Config()
      val mapper = new ObjectMapper
      mapper.registerModule(new DefaultScalaModule)
      val provider = new JacksonJsonProvider(mapper)
      config.getSingletons.add(provider)
      new ApacheHttpClient4(clientHandler, config)
    }
  }
}

trait HelloClientComponent {this: HelloResourceComponent =>
  val helloClient: HelloClient
  class HelloClient {
    def sayHello(name: String): Saying = {
      helloResource.sayHello(name)
    }
  }
}

object HelloService extends HelloClientComponent with HelloResourceComponent {
  override val helloClient = new HelloClient
  override val helloResource = new HttpHelloResource
}
