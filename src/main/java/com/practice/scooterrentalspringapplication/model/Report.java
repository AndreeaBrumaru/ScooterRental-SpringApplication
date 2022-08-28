package com.practice.scooterrentalspringapplication.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;
    @OneToOne
    @JoinColumn(name = "scooterId", referencedColumnName = "scooterId")
    private Scooter scooter;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    private Long minutesRidden;
    private Long price;
    private Boolean paid;
    private LocalDate date;
    private String Notes;

    //Constructor
    public Report() {
    }

    public Report(Scooter scooter, User user, Long minutesRidden, Long price, Boolean paid, LocalDate date, String notes) {
        this.scooter = scooter;
        this.user = user;
        this.minutesRidden = minutesRidden;
        this.price = price;
        this.paid = paid;
        this.date = date;
        Notes = notes;
    }

    //Getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Scooter getScooter() {
        return scooter;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getMinutesRidden() {
        return minutesRidden;
    }

    public void setMinutesRidden(Long minutesRidden) {
        this.minutesRidden = minutesRidden;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    //Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        return Objects.equals(reportId, report.reportId);
    }

    @Override
    public int hashCode() {
        return reportId != null ? reportId.hashCode() : 0;
    }
}
