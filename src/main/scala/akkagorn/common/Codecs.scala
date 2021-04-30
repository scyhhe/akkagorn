package akkagorn.common

import akkagorn.shared.model.{Slug, FeedId}
import sttp.tapir.Codec
import akkagorn.shared.model.TenantId

object Codecs {
  implicit val feedIdCodec = Codec.string.map(FeedId)(_.value)
  implicit val tenantIdCodec = Codec.uuid.map(TenantId)(_.value)
  implicit val slugCodec = Codec.string.map(Slug)(_.value)
}
