package akkagorn.shared.model

import io.circe._
import shapeless.Unwrapped
import java.util.UUID

object Encoding {
  implicit def decodeAnyVal[T, U](implicit
      ev: T <:< AnyVal,
      unwrapped: Unwrapped.Aux[T, U],
      decoder: Decoder[U]
  ): Decoder[T] = Decoder.instance[T] { cursor =>
    decoder(cursor).map(value => unwrapped.wrap(value))
  }

  implicit val decodeTenantId: Decoder[TenantId] = decodeAnyVal[TenantId, UUID]
  implicit val decodeSlug: Decoder[Slug] = decodeAnyVal[Slug, String]
  implicit val feedId: Decoder[FeedId] = decodeAnyVal[FeedId, String]
  implicit val feedCategoryId: Decoder[FeedCategoryId] =
    decodeAnyVal[FeedCategoryId, UUID]
}
