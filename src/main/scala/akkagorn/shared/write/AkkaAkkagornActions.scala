package akkagorn.shared.write

import akka.actor.typed.ActorSystem
import scala.concurrent.Future

class AkkaAkkagornActions(behavior: AkkagornBehaviorRoot)(implicit
    system: ActorSystem[_]
) extends AkkagornActions[Future] {}
