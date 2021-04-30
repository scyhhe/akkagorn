package akkagorn

import akka.actor.typed.ActorSystem
import akka.http.scaladsl._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import akkagorn.shared.write.AkkagornBehaviorRoot
import akkagorn.shared.write.AkkagornCommand
import akkagorn.management.ManagementModule
import akkagorn.activityfeed.ActivityFeedModule

import scala.io.StdIn

import com.typesafe.config.{Config, ConfigFactory}

object AkkagornModule extends App {

  private val config: Config = ConfigFactory.load()

  implicit val system: ActorSystem[AkkagornCommand] =
    ActorSystem(AkkagornBehaviorRoot(), "akkagorn-system")

  implicit val executionContext = system.executionContext

  private val managementModule = new ManagementModule(config)
  private val activityFeedModule = new ActivityFeedModule(config)

  def startServer(): Unit = {

    val routeAggregate: Route =
      concat(
        managementModule.createFeedCategoryRoute,
        managementModule.createFeedRoute,
        activityFeedModule.streamActivities,
        activityFeedModule.pushActivity
      )

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
