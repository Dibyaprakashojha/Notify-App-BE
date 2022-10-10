package com.acheron.notify.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Dibya Prakash Ojha
 * @date : 28-Sep-22
 * @project : notify-app-spring
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

//    @OneToOne
//    @JoinColumn(name = "message_from")
    private String messageFrom;

//    @OneToOne
//    @JoinColumn(name = "message_to")
    private String messageTo;

    private String messageText;

    private LocalDateTime createdAt;
    private boolean isDeleted;
    private boolean isSeen;
}
