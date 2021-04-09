package akkagorn.shared.write

import akka.actor.typed.scaladsl.{ActorContext, AbstractBehavior}
import akka.actor.typed.Behavior

import akkagorn.shared.write.AkkagornCommand
import akka.actor.typed.scaladsl.Behaviors

object AkkagornBehaviorRoot {
  def apply(): Behavior[AkkagornCommand] =
    Behaviors.setup(ctx => new AkkagornBehaviorRoot(ctx))
}

class AkkagornBehaviorRoot(context: ActorContext[AkkagornCommand])
    extends AbstractBehavior[AkkagornCommand](context) {

  override def onMessage(msg: AkkagornCommand): Behavior[AkkagornCommand] = ???
}
