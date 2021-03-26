package akkagorn.server

import akkagorn.model._
import akka.actor.typed.ActorRef
import akkagorn.write.AkkagornCommand
import akka.util.Timeout
import scala.concurrent.duration._
import akka.actor.typed.ActorSystem

class ManagementService(actor: ActorRef[AkkagornCommand])(implicit
    system: ActorSystem[AkkagornCommand]
) {
  private implicit val timeout: Timeout = Timeout(30.seconds)

  val userCategory = FeedCategory("user")
  val micheleUserFeed = Feed(userCategory, FeedId("Michele"))

  // Sebastian shared a job
  val jobSharedActivity =
    Activity(
      Activity.Actor("seb"),
      Activity.Verb("shares"),
      Activity.Object("job"),
      Activity.Recipient(micheleUserFeed.id.value)
    )

  // tenant
  //  -> category
  //    -> feeds

  // s"api/feeds/${userGroup.name}/${micheleUserFeed.id}"

  // def createTopic(
  //     name: FeedCategory
  // ): Future[Either[CreateTopicFailure, FeedId]] = {
  //   actor.ask[Either[CreateTopicFailure, FeedId]](replyTo =>
  //     CreateTopic(name, replyTo)
  //   )
  // }

}
