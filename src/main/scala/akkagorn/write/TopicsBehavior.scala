package akkagorn.write

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.ActorContext

class TopicsBehavior(context: ActorContext[AkkagornCommand])
    extends AkkagornBehaviorRoot(context) {
  override def onMessage(msg: AkkagornCommand): Behavior[AkkagornCommand] = ???
}
