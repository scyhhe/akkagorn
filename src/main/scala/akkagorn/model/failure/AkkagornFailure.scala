package akkagorn.model.failure

object AkkagornFailure {
  sealed trait RegisterSubscriberFailure
  case object RegisterSubscriberFailure extends RegisterSubscriberFailure

  sealed trait PublishMessageFailure
  case object PublishMessageFailure extends PublishMessageFailure

  sealed trait CreateTopicFailure
  case object CreateTopicFailure extends CreateTopicFailure
}
