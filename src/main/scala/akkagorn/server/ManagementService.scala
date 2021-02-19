package akkagorn.server

import akkagorn.model.Topic
import cats.Monad
import scala.util.Random

class ManagementService[F[_]: Monad] {
  def create(topic: Topic): F[Either[String, Topic]] = ???
}
