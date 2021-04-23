package akkagorn.management.api

import sttp.model.StatusCode

final case class ApiError(
    status: StatusCode,
    message: String
)

object ApiError {
  def tupled(
      status: StatusCode,
      message: String
  ): (StatusCode, ApiError) =
    (status, ApiError(status, message))
}
