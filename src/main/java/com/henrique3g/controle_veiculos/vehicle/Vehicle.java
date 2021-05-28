package com.henrique3g.controle_veiculos.vehicle;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.henrique3g.controle_veiculos.user.User;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private Long id;

    private String brand;

    private String model;

    private Year year;

    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Deprecated
    public Vehicle() {}

    public Vehicle(String brand, String model, Year year, Double price, User user) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.user = user;
    }

    @JsonGetter("rotation_day")
    public DayOfWeek getRotationDay() {
        return verifyRotationRule(year);
    }

    @JsonGetter("is_rotation_day")
    public boolean isRotationDay() {
        return LocalDate.now().getDayOfWeek().compareTo(getRotationDay()) == 0;
    }

    private DayOfWeek verifyRotationRule(Year vehicleYear) {
        String vehicleYearString = vehicleYear.toString();
        char lastNumberOfYear = vehicleYearString.charAt(vehicleYearString.length() - 1);
        switch (lastNumberOfYear) {
            case '0':
            case '1':
                return DayOfWeek.MONDAY;
            case '2':
            case '3':
                return DayOfWeek.TUESDAY;
            case '4':
            case '5':
                return DayOfWeek.WEDNESDAY;
            case '6':
            case '7':
                return DayOfWeek.THURSDAY;
            case '8':
            case '9':
            default:
                return DayOfWeek.FRIDAY;
        }
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public Year getYear() {
        return year;
    }

    public Double getPrice() {
        return price;
    }

    public User getUser() {
        return user;
    }
}
