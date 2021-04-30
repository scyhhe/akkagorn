package akkagorn.activityfeed

import akkagorn.activityfeed.api.Endpoints
import akkagorn.shared.model.Activity
import akkagorn.shared.model.Activity._
import scala.concurrent.Future
import scala.concurrent.duration._
import com.typesafe.config.Config
import sttp.model.sse.ServerSentEvent
import sttp.tapir.server.akkahttp._
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.Source

import io.circe.syntax._
import akkagorn.shared.model.Encoding.activityEncoder

class ActivityFeedModule(config: Config) {
  val activityTest = Activity(
    Actor("Migi"),
    Verb("slapped"),
    Object("Seb"),
    Recipient("Me"),
    None,
    List.empty,
    Map.empty[String, String]
  )

  val sseTest = Source
    .tick(
      1.seconds,
      5.seconds,
      ServerSentEvent(Some(activityTest.asJson.toString()), None, None, None)
    )
    .keepAlive(
      5.seconds,
      () => ServerSentEvent(Some("even more data?"), None, None, None)
    )

  val streamActivities: Route = AkkaHttpServerInterpreter.toRoute(
    Endpoints.streamActivities
  )(_ => Future.successful(Right(sseTest)))

  val pushActivity: Route = AkkaHttpServerInterpreter.toRoute(
    Endpoints.pushActivity
  )(_ => Future.successful(Right(activityTest)))
}
