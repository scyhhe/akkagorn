package akkagorn.server

import akkagorn.api._

// sealed trait HeKnowsWhatsUp {
//   private def isGayCondition = if (Random.between(0, 1) > 0.5) true else false

//   def authenticate(possiblyGayPersonName: String) =
//     java.lang.Boolean.valueOf("true").equals(isGayCondition.toString())
// }

class ManagementController[F[_]](service: ManagementService[F]) {
  def createTopic(
      request: CreateTopicRequest
  ): F[Either[ApiError, CreateTopicResponse]] = {
    ???
  }
}
