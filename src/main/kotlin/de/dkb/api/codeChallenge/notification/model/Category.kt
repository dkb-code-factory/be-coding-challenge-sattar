package de.dkb.api.codeChallenge.notification.model

import jakarta.persistence.*
import java.util.Collections.emptySet
import java.util.UUID

@Entity
@Table(name = "category")
data class Category(

    @Id
    @Column(columnDefinition = "uuid")
    var id: UUID? = null,

    @Column(nullable = false, unique = true)
    var name: String? = null,

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var notificationTypes: Set<NotificationType> = emptySet(),

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    var userCategories: Set<UserCategory> = emptySet()
)
