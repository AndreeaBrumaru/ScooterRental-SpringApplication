package com.practice.scooterrentalspringapplication.dto;

import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;
import java.time.LocalDate;
import java.util.Objects;

public class ReportDto {
    private Long reportId;
    private Scooter scooter;
    private User user;
    private Long minutesRidden;
    private Long price;
    private Boolean paid;
    private LocalDate date;
    private String Notes;

    //Constructor

    public ReportDto(Scooter scooter, User user, Long minutesRidden, Long price, Boolean paid, LocalDate date, String notes) {
        this.scooter = scooter;
        this.user = user;
        this.minutesRidden = minutesRidden;
        this.price = price;
        this.paid = paid;
        this.date = date;
        Notes = notes;
    }

    public ReportDto() {
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

        ReportDto reportDto = (ReportDto) o;

        if (!Objects.equals(reportId, reportDto.reportId)) return false;
        if (!Objects.equals(scooter, reportDto.scooter)) return false;
        return Objects.equals(user, reportDto.user);
    }

    @Override
    public int hashCode() {
        int result = reportId != null ? reportId.hashCode() : 0;
        result = 31 * result + (scooter != null ? scooter.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
