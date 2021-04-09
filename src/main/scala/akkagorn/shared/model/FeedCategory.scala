package akkagorn.shared.model

final case class FeedCategory(
    id: FeedCategoryId,
    tenantId: TenantId,
    slug: Slug
)
