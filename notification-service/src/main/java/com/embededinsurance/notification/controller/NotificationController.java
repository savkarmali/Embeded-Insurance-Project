package com.embededinsurance.notification.controller;

import com.embededinsurance.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notify")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    public  String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        notificationService.sendEmail(to, subject, body);
        return "Email sent...!!!";
    }
}
