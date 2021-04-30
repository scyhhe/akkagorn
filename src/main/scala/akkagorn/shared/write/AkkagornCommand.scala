package akkagorn.shared.write

import akkagorn.shared.model._
import akka.actor.typed.ActorRef
import akkagorn.shared.model.failure.Failure._

sealed trait AkkagornCommand

object AkkagornCommand {
  sealed trait ManagementCommand extends AkkagornCommand
  sealed trait ActivityFeedCommand extends AkkagornCommand
}

object ManagementCommand {
  final case class CreateFeedCategory(
      tenantId: TenantId,
      identifier: Slug,
      replyTo: ActorRef[Either[CreateFeedCategoryFailure, Unit]]
  ) extends AkkagornCommand.ManagementCommand
  final case class CreateFeed(
      tenantId: TenantId,
      category: FeedCategoryId,
      id: FeedId,
      replyTo: ActorRef[Either[CreateFeedFailure, Unit]]
  ) extends AkkagornCommand.ManagementCommand
}
