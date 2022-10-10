package com.acheron.notify.service;

import com.acheron.notify.models.Message;
import com.acheron.notify.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dibya Prakash Ojha
 * @date : 29-Sep-22
 * @project : notify-app-spring
 */
@Service
public class MessageServiceImpl implements IMessageService{

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }
}
