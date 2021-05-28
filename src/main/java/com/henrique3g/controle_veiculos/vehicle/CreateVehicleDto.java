package com.henrique3g.controle_veiculos.vehicle;

import java.time.Year;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.henrique3g.controle_veiculos.user.User;
import org.hibernate.validator.constraints.br.CPF;

public class CreateVehicleDto {
    @CPF
    private String userCpf;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    private Year year;

    public Vehicle toVehicle(Double price, User user) {
        return new Vehicle(getBrand(), getModel(), getYear(), price, user);
    }

    public String getUserCpf() {
        return userCpf;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public Year getYear() {
        return year;
    }
}
