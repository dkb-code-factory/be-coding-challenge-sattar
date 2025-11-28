package de.dkb.api.codeChallenge.notification.model


import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.UUID

@Embeddable
data class UserCategoryId(
    @Column(name = "user_id", nullable = false)
    var userId: UUID? = null,

    @Column(name = "category_id", columnDefinition = "uuid", nullable = false)
    var categoryId: UUID? = null
) : Serializable