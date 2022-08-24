package com.practice.scooterrentalspringapplication.repository;

import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //User methods
    @Query("SELECT r FROM Report r WHERE r.user = :user")
    List<Report> findUserOrders(User user);

    @Query("SELECT r FROM Report r WHERE r.user = :user AND r.paid = false")
    Report findUnpaidOrder(User user);

    //Admin methods

    @Override
    @Query("SELECT u FROM User u WHERE u.deleted = false")
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE u.previousRidePaid = false")
    List<User> findUserDebts();
}
