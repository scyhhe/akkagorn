package akkagorn.activityfeed

import akkagorn.activityfeed.api.Endpoints
import scala.concurrent.Future
import scala.concurrent.duration._
import sttp.model.sse.ServerSentEvent
import com.typesafe.config.Config
import sttp.tapir.server.akkahttp._
import akka.http.scaladsl.server.Route
import akka.stream.scaladsl.Source

class ActivityFeedModule(config: Config) {

  val sseTest = Source
    .tick(
      2.seconds,
      2.seconds,
      ServerSentEvent(Some("data"), None, None, None)
    )
    .keepAlive(
      5.seconds,
      () => ServerSentEvent(Some("even more data?"), None, None, None)
    )

  val streamActivities: Route = AkkaHttpServerInterpreter.toRoute(
    Endpoints.streamActivities
  )(_ => Future.successful(Right(sseTest)))
}
