package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface UserRepository extends JpaRepository<User, Long> {

    //User methods
    @Query("SELECT r FROM Report r WHERE r.user = :user")
    List<Report> findUserOrders(User user);

    //Admin methods
    @Query("SELECT u FROM User u WHERE u.previousRidePaid = false")
    List<User> findUserDebts();
}
