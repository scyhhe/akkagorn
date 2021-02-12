package akkagorn.events

import akkagorn.server.model._

final case class SubscriberRegistered(subscriber: Subscriber, topic: Topic)