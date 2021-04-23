package akkagorn.management.api.model

import akkagorn.shared.model.{TenantId, Slug}

final case class CreateFeedCategoryRequest(tenantId: TenantId, name: Slug)
