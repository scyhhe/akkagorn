package akkagorn.model

object AkkagornFailure {
    sealed trait RegisterSubscriberFailure
    object RegisterSubscriberFailure {}

    sealed trait PublishMessageFailure
    object PublishMessageFailure {}
}