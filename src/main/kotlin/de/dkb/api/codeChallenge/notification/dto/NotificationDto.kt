package de.dkb.api.codeChallenge.notification.dto

import de.dkb.api.codeChallenge.notification.model.NotificationType
import java.util.UUID

data class NotificationDto(
    val userId: UUID,
    val notificationId: UUID,
    val message: String,
)