package akkagorn.management.api

import sttp.model._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.generic.auto._

import akkagorn.shared.model.Slug
import akkagorn.management.api.model._
import akkagorn.common.Codecs._

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
      .in("feeds" / path[Slug])
      .in(jsonBody[CreateFeedRequest])
      .out(statusCode(StatusCode.Created))
}
