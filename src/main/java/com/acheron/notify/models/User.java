package com.acheron.notify.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * @author Dibya Prakash Ojha
 * @project : notify-app-spring
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    private Integer userId;
    private String userName;
    private String ownerUserId;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
