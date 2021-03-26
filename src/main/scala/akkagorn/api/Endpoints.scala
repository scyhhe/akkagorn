package akkagorn.api

import sttp.model._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.generic.auto._

import akkagorn.model.FeedCategory
import akkagorn.api.Codecs._
import akkagorn.model.FeedId
import akka.http.scaladsl.marshalling.sse.EventStreamMarshalling
import sttp.tapir.server.akkahttp.serverSentEventsBody

object Codecs {
  implicit val feedIdCodec = Codec.string.map(FeedId)(_.value)
  implicit val feedCategoryCodec = Codec.string.map(FeedCategory)(_.value)
}

object ManagementEndpoints {

  private val baseEndpoint =
    endpoint.in("api").errorOut(statusCode.and(jsonBody[ApiError]))

  val createFeedCategory =
    baseEndpoint.post
      .in("feeds")
      .in(jsonBody[CreateFeedCategoryRequest])
      .out(statusCode(StatusCode.Created))

  val createFeed =
    baseEndpoint.post
      .in("feeds" / path[FeedCategory])
      .in(jsonBody[CreateFeedRequest])
      .out(statusCode(StatusCode.Created))
}

object ActivityFeedEndpoints extends EventStreamMarshalling {

  private val baseEndpoint =
    endpoint.errorOut(statusCode.and(jsonBody[ApiError]))

  // This is using SSE, a separate paginated endpoint will also be added
  val streamActivities =
    baseEndpoint.get
      .in("feeds" / path[FeedCategory] / path[FeedId])
      .out(serverSentEventsBody)
}
