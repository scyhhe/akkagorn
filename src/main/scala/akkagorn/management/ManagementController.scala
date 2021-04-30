package akkagorn.management

import cats.data.EitherT

import akkagorn.shared.model.TenantId
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
      tenantId: TenantId,
      request: CreateFeedCategoryRequest
  ): Future[Either[(StatusCode, ApiError), Unit]] = {
    EitherT(service.createFeedCategory(tenantId, request.name))
      .leftMap(_ => ApiErrors.badRequest(msg = "This ain't right, dawg"))
      .map(_ => println(s"Created FeedCategory from request=$request"))
      .value
  }

  def createFeed(
      request: CreateFeedRequest
  ): Future[Either[(StatusCode, ApiError), Unit]] = {
    EitherT
      .pure[Future, (StatusCode, ApiError)](
        println(s"Created FeedCategory from request=$request")
      )
      .value
  }
}
