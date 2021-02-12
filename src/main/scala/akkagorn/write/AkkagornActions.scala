package akkagorn.write

import akka.actor.typed.ActorRef
import akkagorn.model.AkkagornFailure._
import akkagorn.write.AkkagornCommand._

trait AkkagornActions[F[_]] {
    def registerSubscriber(cmd: ActorRef[Either[RegisterSubscriberFailure, Unit]] => RegisterSubscriber): F[Either[RegisterSubscriberFailure, Unit]]
    def publishMessage(cmd: ActorRef[Either[PublishMessageFailure, Unit]] => PublishMessage): F[Either[PublishMessageFailure, Unit]]
}