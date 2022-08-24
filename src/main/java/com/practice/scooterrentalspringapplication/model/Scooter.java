package com.practice.scooterrentalspringapplication.model;

import com.practice.scooterrentalspringapplication.model.enums.Condition;
import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "scooters")
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long scooterId;
    private Boolean inUse;
    private double battery;
    private Long timeRidden;
    @Enumerated(EnumType.ORDINAL)
    private Condition condition;

    //Constructors
    public Scooter(Boolean inUse, double battery, Long timeRidden, Condition condition) {
        this.inUse = inUse;
        this.battery = battery;
        this.timeRidden = timeRidden;
        this.condition = condition;
    }

    public Scooter() {
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

    public double getBattery() {
        return battery;
    }

    public void setBattery(double battery) {
        this.battery = battery;
    }

    public Long getTimeRidden() {
        return timeRidden;
    }

    public void setTimeRidden(Long timeRidden) {
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

        Scooter scooter = (Scooter) o;

        if (!Objects.equals(scooterId, scooter.scooterId)) return false;
        return condition == scooter.condition;
    }

    @Override
    public int hashCode() {
        int result = scooterId != null ? scooterId.hashCode() : 0;
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        return result;
    }

    //toString
    @Override
    public String toString() {
        return "Scooter{" +
                "scooterId=" + scooterId +
                ", inUse=" + inUse +
                ", battery=" + battery +
                ", timeRidden=" + timeRidden +
                ", condition=" + condition +
                '}';
    }
}
