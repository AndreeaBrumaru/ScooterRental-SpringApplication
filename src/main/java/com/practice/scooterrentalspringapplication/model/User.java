package com.practice.scooterrentalspringapplication.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String CNP;
    @OneToOne
    @JoinColumn(name="scooterId", referencedColumnName = "scooterId")
    private Scooter scooter;
    private Boolean previousRidePaid;
    private Boolean deleted;

    //I'd put password as well, but since this application isn't actually going to be used, and also I don't have a way
    //to encrypt passwords, I thought it would be useless to include it.

    //Constructors
    public User(Long userId, String username, String CNP, Scooter scooter, Boolean previousRidePaid, Boolean deleted) {
        this.userId = userId;
        this.username = username;
        this.CNP = CNP;
        this.scooter = scooter;
        this.previousRidePaid = previousRidePaid;
        this.deleted = deleted;
    }

    public User(String username, String CNP, Scooter scooter, Boolean previousRidePaid, Boolean deleted) {
        this.username = username;
        this.CNP = CNP;
        this.scooter = scooter;
        this.previousRidePaid = previousRidePaid;
        this.deleted = deleted;
    }

    public User(String username, String CNP) {
        this.username = username;
        this.CNP = CNP;
    }

    public User() {
    }

    //Getters and setters
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
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

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    public Boolean getPreviousRidePaid() {
        return previousRidePaid;
    }

    public void setPreviousRidePaid(Boolean previousRidePaid) {
        this.previousRidePaid = previousRidePaid;
    }

    //Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(userId, user.userId)) return false;
        if (!Objects.equals(username, user.username)) return false;
        return Objects.equals(CNP, user.CNP);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (CNP != null ? CNP.hashCode() : 0);
        return result;
    }

    //toStirng
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", CNP='" + CNP + '\'' +
                '}';
    }
}
