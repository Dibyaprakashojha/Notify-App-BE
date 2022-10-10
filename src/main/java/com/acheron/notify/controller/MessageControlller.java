package com.acheron.notify.controller;

import com.acheron.notify.models.Message;
import com.acheron.notify.service.IMessageService;
import com.acheron.notify.service.MessageService;
import lombok.NonNull;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.Subscription;
import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dibya Prakash Ojha
 * @project : notify-app-spring
 */
//@ClientEndpoint
@RestController
@RequestMapping("/app")
@CrossOrigin("*")
public class MessageControlller {

    @Autowired
    private IMessageService service;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private MessageService messageService;

    public @NonNull String getPublicKey() {
       return messageService.getPublicKey();
    }

    /**
     * this method is called when the user clicks on the subscribe button
     *
     * @param subscription
     */
    @PostMapping("/subscribe")
    public void subscribe(@RequestBody Subscription subscription) {
        System.out.println("Subscription: " + subscription);
        logger.info(subscription.toString());
        messageService.subscribe(subscription);
    }

    /**
     * this method is called when the user sends any message to the server
     *
     * @param subscription
     * @param messageJson
     * @return
     */
    @PostMapping("/triggerMessage")
    public void sendNotification(@Payload Subscription subscription, @Payload String messageJson) {
        System.out.println("Notification sent: " + messageJson);
        messageService.sendNotification(subscription, messageJson);
    }

    public void unsubscribe(String endpoint) {
        messageService.unsubscribe(endpoint);
    }

    @PostMapping("/addMessages")
    public void addMessages(@RequestBody Message msgObject) {
        Message message = new Message();
        message.setMessageTo(msgObject.getMessageTo());
        message.setMessageFrom(msgObject.getMessageFrom());
        message.setMessageText(msgObject.getMessageText());
        logger.info("Added message: " + message);
        service.addMessage(message);
    }
}
