package de.dkb.api.codeChallenge.notification.repository

import de.dkb.api.codeChallenge.notification.model.NotificationType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface NotificationTypeRepository : JpaRepository<NotificationType, UUID> {
    fun findByType(type: String): NotificationType?
}