package com.acheron.notify.repository;

import com.acheron.notify.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Dibya Prakash Ojha
 * @date : 28-Sep-22
 * @project : notify-app-spring
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByOwnerUserId(String ownerUserId);

    @Query(value = "select * from user" , nativeQuery = true)
    List<User> getAllUsers();
}
