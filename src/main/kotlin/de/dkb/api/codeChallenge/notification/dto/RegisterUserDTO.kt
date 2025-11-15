package de.dkb.api.codeChallenge.notification.dto

import java.util.UUID

data class RegisterUserDto(
    val userId: UUID,
    val categoryId: UUID? = null,
    val categoryName: String? = null
)