package com.henrique3g.controle_veiculos.vehicle;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;
import com.henrique3g.controle_veiculos.user.User;

public class UserVehiclesView {
    private Long id;
    private String name;
    private String email;
    private String cpf;
    private LocalDate birthdate;
    private List<VehicleView> vehicles;

    public UserVehiclesView(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cpf = user.getCpf();
        this.birthdate = user.getBirthdate();
        this.setVehicles(user.getVehicles());
    }

    protected class VehicleView {
        private Long id;
        private String brand;
        private String model;
        private Year year;
        private Double price;
        private boolean isRotationDay;
        private DayOfWeek rotationDay;

        VehicleView(Vehicle vehicle) {
            id = vehicle.getId();
            brand = vehicle.getBrand();
            model = vehicle.getModel();
            year = vehicle.getYear();
            price = vehicle.getPrice();
            isRotationDay = vehicle.isRotationDay();
            rotationDay = vehicle.getRotationDay();
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

        public boolean getIsRotationDay() {
            return isRotationDay;
        }

        public DayOfWeek getRotationDay() {
            return rotationDay;
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public List<VehicleView> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles.stream().map(VehicleView::new).collect(Collectors.toList());
    }
}
