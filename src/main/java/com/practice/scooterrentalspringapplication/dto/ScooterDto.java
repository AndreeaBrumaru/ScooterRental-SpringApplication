package com.practice.scooterrentalspringapplication.dto;

import com.practice.scooterrentalspringapplication.model.enums.Condition;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.Objects;

public class ScooterDto {
    private Long scooterId;
    private Boolean inUse;
    @NotBlank(message = "Battery percentage is mandatory.")
    @Range(min = 0, max = 100, message = "Battery must be between 0 and 100 percent. " +
            "\nIf you managed otherwise you're either a god or the scooter is malfunctioning.")
    private Long battery;
    private Time timeRidden;
    @NotBlank(message = "Condition is mandatory.")
    private Condition condition;

    //Constructors
    public ScooterDto() {
    }

    //Getters and setters
    public Long getScooterId() {
        return scooterId;
    }

    public void setScooterId(Long scooterId) {
        this.scooterId = scooterId;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    public Long getBattery() {
        return battery;
    }

    public void setBattery(Long battery) {
        this.battery = battery;
    }

    public Time getTimeRidden() {
        return timeRidden;
    }

    public void setTimeRidden(Time timeRidden) {
        this.timeRidden = timeRidden;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    //Equals and hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScooterDto that = (ScooterDto) o;

        if (!Objects.equals(scooterId, that.scooterId)) return false;
        return condition == that.condition;
    }

    @Override
    public int hashCode() {
        int result = scooterId != null ? scooterId.hashCode() : 0;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }
}
