package akkagorn.api

import akkagorn.model._

final case class CreateFeedRequest(id: FeedId, category: FeedCategory)
