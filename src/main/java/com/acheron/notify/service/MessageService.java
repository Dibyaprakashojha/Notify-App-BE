package com.acheron.notify.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Value;
import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

/**
 * @author Dibya Prakash Ojha
 * @date : 30-Sep-22
 * @project : notify-app-spring
 */
@Service
public class MessageService {

    private String publicKey;

    private String privateKey;

    private PushService pushService;
    private List<Subscription> subscriptions = new ArrayList<>();

    public MessageService(@Value("${vapid.public.key}") String publicKey, @Value("${vapid.private.key}") String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    @PostConstruct
    private void init() throws GeneralSecurityException {
        Security.addProvider(new BouncyCastleProvider());
        pushService = new PushService(publicKey, privateKey);
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void subscribe(Subscription subscription) {
        System.out.println("Subscribed to " + subscription.endpoint);
        this.subscriptions.add(subscription);
    }

    public void unsubscribe(String endpoint) {
        System.out.println("Unsubscribed from " + endpoint);
        subscriptions = subscriptions.stream().filter(s -> !endpoint.equals(s.endpoint))
                .collect(Collectors.toList());
    }

    public void sendNotification(Subscription subscription, String messageJson) {
        try {
            pushService.send(new Notification(subscription, messageJson));
        } catch (GeneralSecurityException | IOException | JoseException | ExecutionException
                | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
