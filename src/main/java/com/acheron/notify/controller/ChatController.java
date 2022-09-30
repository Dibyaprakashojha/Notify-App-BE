package com.acheron.notify.controller;

import com.acheron.notify.models.Message;
import com.acheron.notify.service.MessageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

/**
 * @author Dibya Prakash Ojha
 * @project : notify-app-spring
 */
@Controller
public class ChatController {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    private MessageServiceImpl messageService;

    /**
     *
     * @param message
     * @return message which will be sent to the server
     */
    @MessageMapping("/resume")
    @SendTo("/current/initial")
    public String processMessageFromClient(String message) {
        Message messageDetails = new Message();
        logger.info(message);
//        messageDetails.setMessageFrom(message.getMessageFrom());
//        messageDetails.setMessageTo(message.getMessageTo());
//        messageDetails.setMessageText(message.getMessageText());
//        messageService.addMessage(messageDetails);
        return message;

    }

}
