package de.dkb.api.codeChallenge.notification.repository

import de.dkb.api.codeChallenge.notification.model.Category
import de.dkb.api.codeChallenge.notification.model.UserCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface UserCategoryRepository : JpaRepository<UserCategory, UUID> {
    fun existsByIdUserIdAndIdCategoryId(userId: UUID, categoryId: UUID): Boolean
}