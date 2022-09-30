package com.acheron.notify.controller;

import com.acheron.notify.service.MessageService;
import lombok.NonNull;
import nl.martijndwars.webpush.Subscription;
import org.springframework.beans.factory.annotation.Autowired;

import javax.websocket.ClientEndpoint;

/**
 * @author Dibya Prakash Ojha
 * @date : 30-Sep-22
 * @project : notify-app-spring
 */
@ClientEndpoint
public class MessageControlller {

    @Autowired
    private MessageService messageService;

    public @NonNull String getPublicKey() {
        return messageService.getPublicKey();
    }

    public void subscribe(Subscription subscription) {
        messageService.subscribe(subscription);
    }

    public void unsubscribe(String endpoint) {
        messageService.unsubscribe(endpoint);
    }
}
