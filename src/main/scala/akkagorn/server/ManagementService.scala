package akkagorn.server

import akkagorn.model._
import cats.Monad
import scala.util.Random
import akka.actor.typed.ActorRef
import akkagorn.write.AkkagornCommand
import akkagorn.write.AkkagornCommand._
import akkagorn.model.AkkagornFailure._
import akka.actor.typed.scaladsl.AskPattern._
import akka.util.Timeout
import scala.concurrent.duration._
import akka.actor.typed.Scheduler
import akka.actor.typed.ActorSystem
import scala.concurrent.Future

class ManagementService(actor: ActorRef[AkkagornCommand])(implicit
    system: ActorSystem[AkkagornCommand]
) {
  private implicit val timeout: Timeout = Timeout(30.seconds)

  def getTopic(topicId: TopicId): Future[Option[Topic]] = ???

  // def publishMessage(
  //     topic: Topic,
  //     message: Message
  // ): F[Either[PublishMessageFailure, Unit]] = {
  //   val cmd = (actorRef: ActorRef[
  //     Either[AkkagornFailure.PublishMessageFailure, Unit]
  //   ]) => AkkagornCommand.PublishMessage(actorRef)
  //   behaviorActions.publishMessage(cmd)
  // }

  def createTopic(
      name: TopicName
  ): Future[Either[CreateTopicFailure, TopicId]] = {
    actor.ask[Either[CreateTopicFailure, TopicId]](replyTo =>
      CreateTopic(name, replyTo)
    )
  }

}
