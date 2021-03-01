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
import akkagorn.write.AkkagornBehaviorRoot
import akkagorn.write.AkkagornCommand
import akkagorn.server._
import akkagorn.api.CreateTopicRequest
import akkagorn.model.TopicName
object AkkagornModule extends App {

  private val config = ConfigFactory.load()
  implicit val system: ActorSystem[AkkagornCommand] =
    ActorSystem(AkkagornBehaviorRoot(), "akkagorn-system")
  implicit val executionContext = system.executionContext

  val managementService: ManagementService = new ManagementService(system.ref)
  private val managementController: ManagementController =
    new ManagementController(
      managementService
    )

  def startServer(): Unit = {

    val createTopicRoute =
      AkkaHttpServerInterpreter.toRoute(Endpoints.createTopic)(_ =>
        managementController.createTopic(
          CreateTopicRequest(TopicName("TEST TOPIC"))
        )
      )

    val routeAggregate = createTopicRoute

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
