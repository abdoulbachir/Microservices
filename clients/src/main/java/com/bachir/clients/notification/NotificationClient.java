package com.bachir.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "notification",
        url = "${clients.notification.url}"
)
public interface NotificationClient {
    @PostMapping(path = "")
    void sendNotification(NotificationRequest notificationRequest);

}
