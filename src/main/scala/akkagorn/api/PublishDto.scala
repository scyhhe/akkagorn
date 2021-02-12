package akkagorn.api

import akkagorn.server.model._

final case class PublishDto(subscriber: Subscriber, topic: Topic, message: Message)