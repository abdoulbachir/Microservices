package com.bachir.notification.rabbitmq;

import com.bachir.clients.notification.NotificationRequest;
import com.bachir.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationRequest notificationRequest){
        log.info(String.format("Consumed %s from queue", notificationRequest));
        notificationService.send(notificationRequest);
    }
}
