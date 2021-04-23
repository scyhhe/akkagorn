package akkagorn.management

import cats.data.EitherT

import akkagorn.management.api.model._
import akkagorn.management.api.ApiError
import akkagorn.common.ApiErrors
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import sttp.model.StatusCode

class ManagementController(service: ManagementService)(implicit
    ec: ExecutionContext
) {
  def createFeedCategory(
      request: CreateFeedCategoryRequest
  ): Future[Either[(StatusCode, ApiError), Unit]] = {
    // auth
    EitherT(service.createFeedCategory(request.tenantId, request.name))
      .leftMap(_ => ApiErrors.badRequest(msg = "This ain't right, dawg"))
      .map(_ => println(s"Created FeedCategory from request=$request"))
      .value
  }

  def createFeed(
      request: CreateFeedRequest
  ): Future[Either[(StatusCode, ApiError), Unit]] = ???
}
