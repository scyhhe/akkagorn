package akkagorn.events

import akkagorn.model._

final case class MessagePublished(queue: Queue, topic: Topic, message: Message)
