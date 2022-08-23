package com.practice.scooterrentalspringapplication.dto;

import com.practice.scooterrentalspringapplication.model.Scooter;
import com.practice.scooterrentalspringapplication.model.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.sql.Time;
import java.util.Objects;

public class UserReportDto {
    private Long reportId;
    private Scooter scooter;
    private Time minutesRidden;
    private Long price;
    private Boolean paid;

    //Constructor
    public UserReportDto() {
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

    //Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserReportDto that = (UserReportDto) o;

        if (!Objects.equals(reportId, that.reportId)) return false;
        return Objects.equals(scooter, that.scooter);
    }

    @Override
    public int hashCode() {
        int result = reportId != null ? reportId.hashCode() : 0;
        result = 31 * result + (scooter != null ? scooter.hashCode() : 0);
        return result;
    }
}
