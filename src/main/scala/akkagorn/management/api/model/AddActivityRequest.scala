package akkagorn.management.api.model

import akkagorn.shared.model.{Feed, Activity}

final case class AddActivityRequest(feed: Feed, activity: Activity)
