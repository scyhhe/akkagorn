package akkagorn.write

import akka.actor.typed.ActorRef
import akkagorn.model.AkkagornFailure._


sealed trait AkkagornCommand

object AkkagornCommand {
    final case class RegisterSubscriber(replyTo: ActorRef[Either[RegisterSubscriberFailure, Unit]])
    final case class PublishMessage(replyTo: ActorRef[Either[PublishMessageFailure, Unit]])
}