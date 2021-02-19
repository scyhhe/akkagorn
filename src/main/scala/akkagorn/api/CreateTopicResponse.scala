package akkagorn.api

import java.util.UUID
import akkagorn.model.TopicIdentifier

final case class CreateTopicResponse(id: UUID, identifier: TopicIdentifier)
