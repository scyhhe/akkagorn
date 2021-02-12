package akkagorn.write

import akka.actor.typed.ActorSystem
import akka.actor.typed.ActorRef
import scala.concurrent.Future
import akkagorn.write.AkkagornCommand._
import akkagorn.model.AkkagornFailure._

class AkkaAkkagornActions(behavior: AkkagornBehavior)(implicit system: ActorSystem[_]) extends AkkagornActions[Future] {
    def registerSubscriber(cmd: ActorRef[Either[RegisterSubscriberFailure, Unit]] => RegisterSubscriber): Future[Either[RegisterSubscriberFailure, Unit]] = ???
    def publishMessage(cmd: ActorRef[Either[PublishMessageFailure, Unit]] => PublishMessage): Future[Either[PublishMessageFailure, Unit]] = ???
}