package akkagorn.common

// import sttp.tapir.generic.auto._
// import sttp.tapir.json.circe._
// import io.circe.generic.auto._

import akkagorn.shared.model.{Slug, FeedId}
import sttp.tapir.Codec

object Codecs {
  implicit val feedIdCodec = Codec.string.map(FeedId)(_.value)
  implicit val slugCodec = Codec.string.map(Slug)(_.value)
}
