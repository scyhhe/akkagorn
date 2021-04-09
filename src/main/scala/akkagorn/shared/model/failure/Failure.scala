package akkagorn.shared.model.failure

sealed trait Failure

object Failure {
  sealed trait FeedCategoryFailure extends Failure
  case object CreateFeedCategory extends FeedCategoryFailure
}
