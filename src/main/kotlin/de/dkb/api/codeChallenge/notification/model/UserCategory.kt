package de.dkb.api.codeChallenge.notification.model

import jakarta.persistence.*

@Entity
@Table(name = "user_category")
data class UserCategory(

    @EmbeddedId
    var id: UserCategoryId? = null,

    @ManyToOne
    @MapsId("categoryId")
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category? = null
)
