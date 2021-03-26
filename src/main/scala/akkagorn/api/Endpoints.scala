package akkagorn.api

import sttp.model._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.syntax._
import io.circe.generic.auto._

import akkagorn.model.FeedCategory
import akkagorn.api.Codecs._

object Codecs {
  implicit val feedCategoryCodec =
    Codec.stringCodec[FeedCategory](FeedCategory(_))
}

object Endpoints {

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
