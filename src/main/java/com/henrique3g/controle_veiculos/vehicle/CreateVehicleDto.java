package com.henrique3g.controle_veiculos.vehicle;

import java.time.Year;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.henrique3g.controle_veiculos.user.User;

public class CreateVehicleDto {
    @NotNull
    private Long userId;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private Year year;

    public Vehicle toVehicle(Double price, User user) {
        return new Vehicle(getBrand(), getModel(), getYear(), price, user);
    }

    public Long getUserId() {
        return userId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}
