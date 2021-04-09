package akkagorn.management

import akkagorn.shared.model._
import akkagorn.shared.write.AkkagornCommand

import akka.http.scaladsl.server.Route
import sttp.tapir.server.akkahttp._

import akkagorn.management.api.model._
import akkagorn.management.{ManagementService, ManagementController}
import akkagorn.management.api.Endpoints

import com.typesafe.config.Config
import akka.actor.typed.ActorSystem
import scala.concurrent.ExecutionContext

import java.util.UUID

class ManagementModule(config: Config)(implicit
    system: ActorSystem[AkkagornCommand],
    ec: ExecutionContext
) {
  val managementService = new ManagementService(system.ref)
  val managementController = new ManagementController(managementService)

  val createFeedCategoryRoute: Route =
    AkkaHttpServerInterpreter.toRoute(Endpoints.createFeedCategory)(_ =>
      managementController.createFeedCategory(
        CreateFeedCategoryRequest(
          FeedCategory(
            FeedCategoryId(UUID.randomUUID()),
            TenantId(UUID.randomUUID()),
            Slug("test-category")
          )
        )
      )
    )

  val createFeedRoute: Route =
    AkkaHttpServerInterpreter.toRoute(Endpoints.createFeed)(_ =>
      managementController.createFeed(
        CreateFeedRequest(
          FeedId("test"),
          FeedCategory(
            FeedCategoryId(UUID.randomUUID()),
            TenantId(UUID.randomUUID()),
            Slug("test-category")
          )
        )
      )
    )

}
