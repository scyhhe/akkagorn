package akkagorn.api

import java.util.UUID
import akkagorn.model._

final case class CreateFeedRequest(id: FeedId, category: FeedCategory)
