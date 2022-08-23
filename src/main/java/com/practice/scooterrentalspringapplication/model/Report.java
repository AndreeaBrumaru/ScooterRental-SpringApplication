package com.practice.scooterrentalspringapplication.model;

import javax.persistence.*;
import java.sql.Time;
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
    private Time minutesRidden;
    private Long price;
    private Boolean paid;
    private Long batteryLeft;
    private String Notes;

    //Constructor
    public Report() {
    }

    //Getters and setters
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

    public Time getMinutesRidden() {
        return minutesRidden;
    }

    public void setMinutesRidden(Time minutesRidden) {
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

    public Long getBatteryLeft() {
        return batteryLeft;
    }

    public void setBatteryLeft(Long batteryLeft) {
        this.batteryLeft = batteryLeft;
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
