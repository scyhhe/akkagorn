package akkagorn.api

import sttp.model._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.syntax._
import io.circe.generic.auto._
import akka.http.scaladsl.model.HttpEntity
import java.nio.charset.Charset
import io.circe
import io.circe.Encoder

object Endpoints {
  private val baseEndpoint =
    endpoint.in("api").errorOut(statusCode.and(jsonBody[ApiError]))

  //POST /api/topics
  //POST /api/topics/:tid/subscribe
  //GET  /api/topics
  //GET /api/topics/:tid - topic with subscribers

  val createTopic =
    baseEndpoint.post
      .in("topics")
      .in(jsonBody[CreateTopicRequest])
      .out(statusCode(StatusCode.Created) and jsonBody[CreateTopicResponse])
}
