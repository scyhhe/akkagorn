package akkagorn.api

import sttp.tapir._
import sttp.tapir.generic.auto._
import sttp.tapir.json.circe._
import io.circe.syntax._
import io.circe.generic.auto._
import akka.http.scaladsl.model.HttpEntity
import java.nio.charset.Charset

object Endpoints {
  private val baseEndpoint =
    endpoint.errorOut(statusCode.and(jsonBody[ApiError]))

  val helloEndpoint =
    baseEndpoint.get.in("hello").out(htmlBodyUtf8)

  val register = 
    baseEndpoint.put.in("register").out(htmlBodyUtf8)
}
