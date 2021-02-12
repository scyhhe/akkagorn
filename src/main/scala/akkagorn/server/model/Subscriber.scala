package akkagorn.server.model

import akkagorn.model.Queue
import java.util.UUID

final case class Subscriber(queue: Queue, id: UUID)
