package akkagorn.shared.model

import java.time.LocalDateTime

object Activity {
  final case class Actor(value: String) // who performed it
  final case class Verb(value: String) // what did he perform
  final case class Object(value: String) // what did he perform it on
  final case class Recipient(value: String) // who should be notified about it
}

final case class Activity(
    actor: Activity.Actor,
    action: Activity.Verb,
    obj: Activity.Object,
    recipient: Activity.Recipient,
    time: Option[LocalDateTime],
    to: List[FeedId],
    customFields: Map[String, String]
)
