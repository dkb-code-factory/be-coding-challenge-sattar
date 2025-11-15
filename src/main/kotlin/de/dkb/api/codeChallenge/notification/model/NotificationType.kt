package de.dkb.api.codeChallenge.notification.model

import jakarta.persistence.*
import java.util.UUID;

@Entity
@Table(name = "notification_type")
data class NotificationType(

    @Id
    @Column(columnDefinition = "uuid")
    var id: UUID? = null,

    @Column(nullable = false, unique = true)
    var type: String? = null,

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category? = null
)
