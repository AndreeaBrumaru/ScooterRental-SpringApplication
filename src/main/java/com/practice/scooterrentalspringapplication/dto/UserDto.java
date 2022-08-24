package com.practice.scooterrentalspringapplication.dto;

import com.practice.scooterrentalspringapplication.model.Report;
import com.practice.scooterrentalspringapplication.model.Scooter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

public class UserDto {
    private Long userId;
    @NotBlank(message = "Username is mandatory")
    @Size(max = 25, message = "Username must have 25 characters maximum.")
    private String username;
    @NotBlank(message = "CNP is mandatory.")
    @Size(min = 13, max = 13, message = "CNP must be 13 characters long.")
    private String CNP;
    private Scooter scooter;

    //Constructor
    public UserDto() {
    }

    //Getters and setters
    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

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
