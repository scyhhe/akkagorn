package akkagorn.management.api.model

import akkagorn.shared.model.{FeedId, FeedCategory}

final case class CreateFeedRequest(id: FeedId, category: FeedCategory)
