package akkagorn.api

import akkagorn.server.model.Subscriber
import akkagorn.server.model.Topic

final case class RegisterDto(subscriber: Subscriber, topic: Topic)