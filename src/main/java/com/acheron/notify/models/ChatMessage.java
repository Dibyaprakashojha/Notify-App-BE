package com.acheron.notify.models;

import lombok.*;

/**
 * @author Dibya Prakash Ojha
 * @project : notify-app-spring
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;

}
