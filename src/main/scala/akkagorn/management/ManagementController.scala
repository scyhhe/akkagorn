package akkagorn.management

import akkagorn.management.api.model._
import akkagorn.management.api.ApiError
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import sttp.model.StatusCode

class ManagementController(service: ManagementService)(implicit
    ec: ExecutionContext
) {
  def createFeedCategory(
      request: CreateFeedCategoryRequest
  ): Future[Either[(StatusCode, ApiError), Unit]] = ???

  def createFeed(
      request: CreateFeedRequest
  ): Future[Either[(StatusCode, ApiError), Unit]] = ???
}
