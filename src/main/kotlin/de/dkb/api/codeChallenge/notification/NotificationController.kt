package de.dkb.api.codeChallenge.notification

import de.dkb.api.codeChallenge.notification.dto.NotificationDto
import de.dkb.api.codeChallenge.notification.dto.RegisterUserDto
import org.springframework.web.bind.annotation.*

@RestController
class NotificationController(private val notificationService: NotificationService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody user: RegisterUserDto) =
        notificationService.registerUser(user)

    @PostMapping("/notify")
    fun sendNotification(@RequestBody notificationDto: NotificationDto) =
        notificationService.sendNotification(notificationDto)
}