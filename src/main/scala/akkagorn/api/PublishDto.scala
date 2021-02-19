package akkagorn.api

import akkagorn.model._

final case class PublishRequest(topic: Topic, message: Message)
