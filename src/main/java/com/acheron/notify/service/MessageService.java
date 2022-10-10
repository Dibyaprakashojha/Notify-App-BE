package com.acheron.notify.service;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * @author Dibya Prakash Ojha
 * @date : 30-Sep-22
 * @project : notify-app-spring
 */
@Service
public class MessageService {


    private final Map<Subscription, String> subscriptions = new ConcurrentHashMap<>();

    //    private final String PUBLIC_KEY = env.getProperty("vapid.public.key");
    //
    //    private final String PRIVATE_KEY = env.getProperty("vapid.private.key");
    @Autowired
    Environment env;
    private PushService pushService;

    @PostConstruct
    private void init() throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        pushService = new PushService(env.getProperty("vapid.public.key"), env.getProperty("vapid.private.key"));
    }

    public String getPublicKey() {
        return env.getProperty("vapid.public.key");
    }

    public void subscribe(Subscription subscription) {
        System.out.println("Subscribed to " + subscription.endpoint);
        this.subscriptions.put(subscription, subscription.endpoint);
    }

    public void unsubscribe(String endpoint) {
        System.out.println("Unsubscribed from " + endpoint);
        //        subscriptions.remove(endpoint);
        //        subscriptions = subscriptions.stream().filter(s -> !endpoint.equals(s.endpoint))
        //                .collect(Collectors.toList());
    }

    public void sendNotification(Subscription subscription, String messageJson) {
        try {
            pushService.send(new Notification(subscription, messageJson));
        } catch (GeneralSecurityException | IOException | JoseException | ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
