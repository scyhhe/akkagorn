package akkagorn.write

import akka.actor.typed.ActorRef
import akkagorn.model.failure.AkkagornFailure._
import akkagorn.model._

sealed trait AkkagornCommand

sealed trait ManagementCommand extends AkkagornCommand

object AkkagornCommand {

  // Management
  final case class CreateTopic(
      name: FeedCategory,
      replyTo: ActorRef[Either[CreateTopicFailure, FeedId]]
  ) extends ManagementCommand

  // Whatever
  final case class RegisterSubscriber(
      replyTo: ActorRef[Either[RegisterSubscriberFailure, Unit]]
  ) extends AkkagornCommand
  final case class PublishMessage(
      replyTo: ActorRef[Either[PublishMessageFailure, Unit]]
  ) extends AkkagornCommand

}
