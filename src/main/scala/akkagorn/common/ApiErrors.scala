package akkagorn.common

import akkagorn.management.api.ApiError
import sttp.model.StatusCode

object ApiErrors {
  def resourceNotFound(msg: String): (StatusCode, ApiError) =
    ApiError.tupled(StatusCode.NotFound, msg)

  def badRequest(msg: String = "Bad Request"): (StatusCode, ApiError) =
    ApiError.tupled(StatusCode.BadRequest, msg)

  def conflict(
      msg: String = "The resource already exists"
  ): (StatusCode, ApiError) =
    ApiError.tupled(StatusCode.Conflict, msg)

  def forbidden: (StatusCode, ApiError) =
    ApiError.tupled(
      StatusCode.Forbidden,
      "You are not allowed to do this action"
    )

  def unauthorized: (StatusCode, ApiError) =
    ApiError.tupled(
      StatusCode.Unauthorized,
      "invalid bearer token"
    )

  def internalError: (StatusCode, ApiError) =
    ApiError.tupled(
      StatusCode.InternalServerError,
      "internal error"
    )
}
