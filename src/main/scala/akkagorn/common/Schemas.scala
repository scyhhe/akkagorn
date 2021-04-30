package akkagorn.common

import akkagorn.shared.model.FeedId
import sttp.tapir.Schema
import akkagorn.shared.model.Activity

// TODO do we still need this?
object Schemas {

  implicit val feedIdSchema: Schema[FeedId] =
    Schema.schemaForString.map(id => Some(FeedId(id)))(_.value)

  implicit val activityActorSchema: Schema[Activity.Actor] =
    Schema.schemaForString.map(s => Some(Activity.Actor(s)))(_.value)

  implicit val activityVerbSchema: Schema[Activity.Verb] =
    Schema.schemaForString.map(s => Some(Activity.Verb(s)))(_.value)

  implicit val activityObjectSchema: Schema[Activity.Object] =
    Schema.schemaForString.map(s => Some(Activity.Object(s)))(_.value)

  implicit val activityRecipientSchema: Schema[Activity.Recipient] =
    Schema.schemaForString.map(s => Some(Activity.Recipient(s)))(_.value)

  implicit val mapSchema = Schema.schemaForMap[String]
  implicit val activitySchema = Schema.derived[Activity]
}
