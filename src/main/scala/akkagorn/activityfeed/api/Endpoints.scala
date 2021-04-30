package akkagorn.activityfeed.api

import sttp.tapir._
import sttp.tapir.json.circe._
import io.circe.generic.auto._
import sttp.tapir.generic.auto._

import akkagorn.shared.model._
import akkagorn.shared.model.Encoding._
import akkagorn.management.api.ApiError
import akkagorn.common.Codecs._
import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling
import sttp.tapir.server.akkahttp.serverSentEventsBody

object Endpoints extends EventStreamMarshalling {

  private val baseEndpoint =
    endpoint.errorOut(statusCode.and(jsonBody[ApiError]))

  // This is using SSE, a separate paginated endpoint will also be added
  val streamActivities =
    baseEndpoint.get
      .in("feeds" / path[Slug] / path[FeedId])
      .out(serverSentEventsBody)

  val pushActivity = baseEndpoint.post
    .in("feeds" / path[Slug] / path[FeedId])
    .out(jsonBody[Activity])
}
