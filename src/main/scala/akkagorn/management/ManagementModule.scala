package akkagorn.management

import akkagorn.shared.write.AkkagornCommand

import akka.http.scaladsl.server.Route
import sttp.tapir.server.akkahttp._

import akkagorn.management.{ManagementService, ManagementController}
import akkagorn.management.api.Endpoints

import com.typesafe.config.Config
import akka.actor.typed.ActorSystem
import scala.concurrent.ExecutionContext

class ManagementModule(config: Config)(implicit
    system: ActorSystem[AkkagornCommand],
    ec: ExecutionContext
) {
  val managementService = new ManagementService(system.ref)
  val managementController = new ManagementController(managementService)

  val createFeedCategoryRoute: Route =
    AkkaHttpServerInterpreter.toRoute(
      Endpoints.createFeedCategory
        .serverLogic { case (tenantId, req) =>
          managementController.createFeedCategory(tenantId, req)
        }
    )

  val createFeedRoute: Route =
    AkkaHttpServerInterpreter.toRoute(
      Endpoints.createFeed.serverLogic { case (tenantId, slug, req) =>
        managementController.createFeed(req)
      }
    )

}
