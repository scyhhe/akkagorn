package akkagorn.shared.model

import io.circe._
// import io.circe.generic.semiauto._
import shapeless.Unwrapped

object Encoding {
  implicit def encodeAnyVal[W, U](implicit
      ev: W <:< AnyVal,
      unwrapped: Unwrapped.Aux[W, U],
      encoderUnwrapped: Encoder[U]
  ): Encoder[W] = {
    Encoder.instance[W](v => encoderUnwrapped(unwrapped.unwrap(v)))
  }

}
