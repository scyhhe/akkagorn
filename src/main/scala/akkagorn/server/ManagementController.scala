package akkagorn.server

import akkagorn.api._
import cats.data.EitherT
import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import sttp.model.StatusCode

class ManagementController(service: ManagementService)(implicit
    ec: ExecutionContext
) {
  def createTopic(
      request: CreateTopicRequest
  ): Future[Either[(StatusCode, ApiError), CreateTopicResponse]] = {
    // authorize

    EitherT(service.createTopic(request.name))
      .bimap(
        _ =>
          (
            StatusCode.BadRequest,
            ApiError(s"Could not create Topic=${request.name}")
          ),
        topicId => CreateTopicResponse(topicId, request.name)
      )
      .value
  }
}
