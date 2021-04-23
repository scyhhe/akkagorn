package akkagorn.shared.write

import akka.actor.typed.ActorSystem
import scala.concurrent.Future

class AkkaAkkagornActions(behavior: AkkagornBehaviorRoot[AkkagornCommand])(
    implicit system: ActorSystem[_]
) extends AkkagornActions[Future] {}
