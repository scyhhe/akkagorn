package akkagorn.events

import akkagorn.model._

final case class SubscriberRegistered(queue: Queue, topic: Topic)
