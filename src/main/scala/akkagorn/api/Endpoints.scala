package akkagorn.api

import sttp.model._
import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.syntax._
import io.circe.generic.auto._
import akka.http.scaladsl.model.HttpEntity
import java.nio.charset.Charset

object Endpoints {
  private val baseEndpoint =
    endpoint.in("api").errorOut(statusCode.and(jsonBody[ApiError]))

  val helloEndpoint =
    baseEndpoint.get.in("hello").out(htmlBodyUtf8)

  //POST /api/topics
  //POST /api/topics/:tid/subscribe
  //GET  /api/topics

  val createTopic =
    baseEndpoint.post
      .in("topics")
      .in(jsonBody[CreateTopicRequest])
      .out(statusCode(StatusCode.Created) and jsonBody[CreateTopicResponse])
}
