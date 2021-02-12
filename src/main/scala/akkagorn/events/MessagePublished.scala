package akkagorn.events

import akkagorn.server.model._

final case class MessagePublished(subscriber: Subscriber, topic: Topic, message: Message)