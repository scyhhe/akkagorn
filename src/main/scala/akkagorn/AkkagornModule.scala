package akkagorn

import akkagorn.api.ManagementEndpoints

import akka.actor.typed.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.server.Directives._
import sttp.tapir.server.akkahttp._

import scala.io.StdIn
import scala.concurrent.Future
import com.typesafe.config.ConfigFactory
import akkagorn.write.AkkagornBehaviorRoot
import akkagorn.write.AkkagornCommand
import akkagorn.server._
import akkagorn.api._
import akkagorn.model._
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.Source
import sttp.model.sse.ServerSentEvent

import scala.concurrent.duration._
import java.time.LocalTime

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

    val createFeedCategoryRoute: Route =
      AkkaHttpServerInterpreter.toRoute(ManagementEndpoints.createFeedCategory)(
        _ =>
          managementController.createFeedCategory(
            CreateFeedCategoryRequest(FeedCategory("TEST TOPIC"))
          )
      )

    val createFeedRoute: Route =
      AkkaHttpServerInterpreter.toRoute(ManagementEndpoints.createFeed)(_ =>
        managementController.createFeed(
          CreateFeedRequest(FeedId("test"), FeedCategory("TEST TOPIC"))
        )
      )

    val sseTest = Source
      .tick(
        2.seconds,
        2.seconds,
        ServerSentEvent(Some("data"), None, None, None)
      )
      // .map(_ => LocalTime.now())
      // .map(time => ServerSentEvent(Some("more data"), None, None, None))
      .keepAlive(
        1.second,
        () => ServerSentEvent(Some("even more data?"), None, None, None)
      )

    val streamActivities: Route = AkkaHttpServerInterpreter.toRoute(
      ActivityFeedEndpoints.streamActivities
    )(_ =>
      Future.successful(
        // - keeps connection open, but no data received
        Right(sseTest)

        // - data is delivered, but connection stops
        // Right(Source.single(ServerSentEvent(Some("data"), None, None, None)))
      )
    )

    val routeAggregate: Route =
      concat(createFeedCategoryRoute, createFeedRoute, streamActivities)

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
