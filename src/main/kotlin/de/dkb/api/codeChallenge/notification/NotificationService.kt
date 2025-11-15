package de.dkb.api.codeChallenge.notification

import de.dkb.api.codeChallenge.notification.dto.NotificationDto
import de.dkb.api.codeChallenge.notification.dto.RegisterUserDto
import de.dkb.api.codeChallenge.notification.model.Category
import de.dkb.api.codeChallenge.notification.model.NotificationType
import de.dkb.api.codeChallenge.notification.model.UserCategory
import de.dkb.api.codeChallenge.notification.model.UserCategoryId
import de.dkb.api.codeChallenge.notification.repository.CategoryRepository
import de.dkb.api.codeChallenge.notification.repository.NotificationTypeRepository
import de.dkb.api.codeChallenge.notification.repository.UserCategoryRepository
import org.springframework.stereotype.Service

@Service
class NotificationService(
    private val userCategoryRepo: UserCategoryRepository,
    private val notificationTypeRepo: NotificationTypeRepository,
    private val categoryRepository: CategoryRepository
) {


    fun registerUser(dto: RegisterUserDto) {
        val category = if (dto.categoryId != null) {
            categoryRepository.findById(dto.categoryId)
                .orElseThrow { IllegalArgumentException("Category not found by ID") }
        } else if (dto.categoryName != null && dto.categoryName!= "") {
            val categoryName = dto.categoryName
            categoryRepository.findByName(categoryName) ?: throw IllegalArgumentException("Category not found by name")
        } else {
            throw IllegalArgumentException("Either categoryId or categoryName must be provided")
        }
        val categoryId = category.id ?: throw IllegalStateException("Category ID is null")
        val alreadySubscribed = userCategoryRepo.existsByIdUserIdAndIdCategoryId(dto.userId, categoryId)

        if (alreadySubscribed) {
            println("User ${dto.userId} is already subscribed to category ${dto.categoryId}")
            return
        }

        val userCategory = UserCategory(
            id = UserCategoryId(
                userId = dto.userId,
                categoryId = categoryId
            ),
            category = category
        )

        userCategoryRepo.save(userCategory)

        println("User ${dto.userId} successfully subscribed to category ${dto.categoryId}")
    }

    fun sendNotification(notificationDto: NotificationDto) {
        val notificationType: NotificationType = notificationTypeRepo.findById(notificationDto.notificationId)
            .orElseThrow { IllegalArgumentException("Unknown notification type") }
        val category: Category = notificationType.category ?: throw IllegalStateException("Notification type category is missing")
        val categoryId = category.id ?: throw IllegalStateException("Category ID is null")
        val isSubscribed = userCategoryRepo.existsByIdUserIdAndIdCategoryId(
            notificationDto.userId,
            categoryId
        )
        if (isSubscribed) {
            println(
                "Sending notification type '${notificationType.type}' " +
                        "to user ${notificationDto.userId}: ${notificationDto.message}"
            )
        } else {
            println(
                "User ${notificationDto.userId} is NOT subscribed to category ${category.name} " +
                        "→ NOT sending notification of type '${notificationType.type}'"
            )
        }
    }
}