package com.practice.scooterrentalspringapplication.dto;

import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Objects;
import java.util.Set;

public class UserDto {
    private Long userId;
    private String username;
    private Scooter scooter;
    private Set<Report> orderHistory;

    //Constructor
    public UserDto() {
    }

    //Getters and setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    public Set<Report> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(Set<Report> orderHistory) {
        this.orderHistory = orderHistory;
    }

    //Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (!Objects.equals(userId, userDto.userId)) return false;
        return Objects.equals(username, userDto.username);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
