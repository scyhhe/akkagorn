package akkagorn.shared.model

import io.circe._
import io.circe.generic.auto._
import shapeless.Unwrapped
import java.util.UUID
import java.time.LocalDateTime

object Encoding {
  implicit def decodeAnyVal[T, U](implicit
      ev: T <:< AnyVal,
      unwrapped: Unwrapped.Aux[T, U],
      decoder: Decoder[U]
  ): Decoder[T] = Decoder.instance[T] { cursor =>
    decoder(cursor).map(value => unwrapped.wrap(value))
  }

  implicit val decodeTenantIdDecoder: Decoder[TenantId] =
    decodeAnyVal[TenantId, UUID]
  implicit val decodeSlugDecoder: Decoder[Slug] = decodeAnyVal[Slug, String]
  implicit val feedIdDecoder: Decoder[FeedId] = decodeAnyVal[FeedId, String]
  implicit val feedCategoryIdDecoder: Decoder[FeedCategoryId] =
    decodeAnyVal[FeedCategoryId, UUID]

  implicit val dateDecoder: Decoder[LocalDateTime] = Decoder.decodeLocalDateTime
  implicit val dateEncoder: Encoder[LocalDateTime] =
    Encoder.encodeLocalDateTime

  // Use auto / semi-auto derivation for this in the future
  implicit val activityEncoder: Encoder[Activity] = Encoder.instance[Activity] {
    activity =>
      Json.obj(
        "actor" -> Json.fromString(activity.actor.value),
        "verb" -> Json.fromString(activity.action.value),
        "object" -> Json.fromString(activity.obj.value),
        "recipient" -> Json.fromString(activity.recipient.value)
      )
  }

  implicit val activityDecoder: Decoder[Activity] = Decoder.instance[Activity] {
    cursor =>
      for {
        actor <- cursor.get[Activity.Actor]("actor")
        verb <- cursor.get[Activity.Verb]("verb")
        obj <- cursor.get[Activity.Object]("object")
        recipient <- cursor.get[Activity.Recipient]("recipient")
      } yield Activity(
        actor,
        verb,
        obj,
        recipient,
        None,
        List.empty[FeedId],
        Map.empty
      )
  }
}
