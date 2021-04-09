package akkagorn.management.api.model

import akkagorn.shared.model.{FeedId, FeedCategory}

final case class CreateFeed(id: FeedId, name: FeedCategory)
