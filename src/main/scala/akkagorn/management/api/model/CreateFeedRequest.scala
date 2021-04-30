package akkagorn.management.api.model

import akkagorn.shared.model.{FeedId, FeedCategory}

// TODO do we need a slug here to allow a "custom name", or do we just keep the ID?
final case class CreateFeedRequest(id: FeedId, category: FeedCategory)
