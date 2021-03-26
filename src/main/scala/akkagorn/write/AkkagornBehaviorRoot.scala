package akkagorn.write

import akka.actor.typed.scaladsl.{ActorContext, AbstractBehavior}
import akka.actor.typed.Behavior

import cats.syntax.all._
import akkagorn.write.AkkagornCommand._
import akka.actor.typed.scaladsl.Behaviors
import akkagorn.model.failure.AkkagornFailure
import akkagorn.model.FeedId

object AkkagornBehaviorRoot {
  def apply(): Behavior[AkkagornCommand] =
    Behaviors.setup(ctx => new AkkagornBehaviorRoot(ctx))
}

class AkkagornBehaviorRoot(context: ActorContext[AkkagornCommand])
    extends AbstractBehavior[AkkagornCommand](context) {

  private val testUUID = new java.util.UUID(0, 0)
  override def onMessage(msg: AkkagornCommand): Behavior[AkkagornCommand] =
    msg match {
      case CreateTopic(name, replyTo) =>
        // "persist" topic
        replyTo ! FeedId(testUUID.toString()).asRight
        Behaviors.same
      case RegisterSubscriber(replyTo) => Behaviors.same

      // whatever
      case PublishMessage(replyTo) =>
        replyTo ! AkkagornFailure.PublishMessageFailure.asLeft[Unit]
        Behaviors.same
    }
}
