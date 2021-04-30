package akkagorn.management

import akka.util.Timeout
import akka.actor.typed.scaladsl.AskPattern._
import akka.actor.typed.{ActorRef, ActorSystem}
import scala.concurrent.duration._
import scala.concurrent.Future
import akkagorn.shared.model.failure.Failure._
import akkagorn.shared.model._
import akkagorn.shared.write.ManagementCommand
import akkagorn.shared.write.AkkagornCommand

class ManagementService(actor: ActorRef[AkkagornCommand])(implicit
    system: ActorSystem[AkkagornCommand]
) {
  private implicit val timeout: Timeout = Timeout(30.seconds)

  def createFeedCategory(
      tenantId: TenantId,
      name: Slug
  ): Future[Either[CreateFeedCategoryFailure, Unit]] = {
    val cmd =
      (actorRef: ActorRef[
        Either[CreateFeedCategoryFailure, Unit]
      ]) => ManagementCommand.CreateFeedCategory(tenantId, name, actorRef)
    actor.ask(cmd)
  }

  def createFeed(
      tenantId: TenantId,
      feedId: FeedId,
      feedCategoryId: FeedCategoryId
  ): Future[Either[CreateFeedFailure, Unit]] = {
    val cmd =
      (actorRef: ActorRef[
        Either[CreateFeedFailure, Unit]
      ]) =>
        ManagementCommand.CreateFeed(tenantId, feedCategoryId, feedId, actorRef)
    actor.ask(cmd)
  }

  // val userCategory = FeedCategory("user")
  // val micheleUserFeed = Feed(userCategory, FeedId("Michele"))

  // Sebastian shared a job
  // val jobSharedActivity =
  //   Activity(
  //     Activity.Actor("seb"),
  //     Activity.Verb("shares"),
  //     Activity.Object("job"),
  //     Activity.Recipient(micheleUserFeed.id.value)
  //   )

  // tenant
  //  -> category
  //    -> feeds

  // s"api/feeds/${userGroup.name}/${micheleUserFeed.id}"
}
