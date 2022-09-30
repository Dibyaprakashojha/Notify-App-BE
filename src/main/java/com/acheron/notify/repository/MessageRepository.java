package com.acheron.notify.repository;

import com.acheron.notify.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dibya Prakash Ojha
 * @date : 28-Sep-22
 * @project : notify-app-spring
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
