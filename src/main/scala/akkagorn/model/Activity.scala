package akkagorn.model

object Activity {
  final case class Actor(name: String) // who performed it
  final case class Verb(name: String) // what did he perform
  final case class Object(name: String) // what did he perform it on
  final case class Recipient(name: String) // who should be notified about it
}

final case class Activity(
    actor: Activity.Actor,
    action: Activity.Verb,
    obj: Activity.Object,
    recipient: Activity.Recipient
)
