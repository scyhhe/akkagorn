package akkagorn.shared.model.failure

object Failure {
  sealed trait CreateFeedCategoryFailure
  sealed trait CreateFeedFailure

  object CreateFeedCategoryFailure {
    case object SomeFailure extends CreateFeedCategoryFailure
  }

  object CreateFeedFailure {
    case object SomeFailure extends CreateFeedFailure
  }

}
