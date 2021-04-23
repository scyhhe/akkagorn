package akkagorn.shared.write

import cats.syntax.all._

import akka.actor.typed.scaladsl.{ActorContext, AbstractBehavior}
import akka.actor.typed.Behavior

import akkagorn.shared.write.AkkagornCommand
import akkagorn.shared.write.ManagementCommand._
import akka.actor.typed.scaladsl.Behaviors

object AkkagornBehaviorRoot {
  def apply(): Behavior[AkkagornCommand] =
    Behaviors.setup(ctx => new AkkagornBehaviorRoot(ctx))
}

class AkkagornBehaviorRoot[AkkagornCommand](
    context: ActorContext[AkkagornCommand]
) extends AbstractBehavior[AkkagornCommand](context) {

  override def onMessage(msg: AkkagornCommand): Behavior[AkkagornCommand] =
    msg match {
      case CreateFeed(categoryId, feedId, replyTo) =>
        replyTo ! ().asRight
        Behaviors.same

      case CreateFeedCategory(identifier, tenantId, replyTo) =>
        replyTo ! ().asRight
        // replyTo ! Failure.CreateFeedCategoryFailure.SomeFailure.asLeft
        Behaviors.same
    }
}
