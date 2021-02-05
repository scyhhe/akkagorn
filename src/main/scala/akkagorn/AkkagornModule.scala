package akkagorn

import akkagorn.api.Endpoints

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import sttp.tapir._
import sttp.tapir.server.akkahttp._

import scala.io.StdIn
import scala.concurrent.Future
import com.typesafe.config.ConfigFactory
import akka.actor.typed.ActorRef

object AkkagornModule extends App {

  private val config = ConfigFactory.load()
  private implicit val system = ActorSystem(Behaviors.empty, "akkagorn-system")
  private implicit val executionContext = system.executionContext

  def startServer(): Unit = {
    // needed for the future flatMap/onComplete in the end

    val getHello =
      AkkaHttpServerInterpreter.toRoute(Endpoints.helloEndpoint)(_ =>
        Future(Right("<h1>Say hello to akka-http</h1>"))
      )

    val routeAggregate = getHello

    val bindingFuture =
      Http().newServerAt("localhost", 8080).bind(routeAggregate)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }

  startServer()
}
