package com.henrique3g.controle_veiculos.vehicle;

import java.time.Year;
import java.util.Optional;
import com.henrique3g.controle_veiculos.Result;
import com.henrique3g.controle_veiculos.vehicle.VehicleApiClient.ResponseVehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetVehiclePrice {
    @Autowired
    private VehicleApiClient vehicleClient;

    public Result<Double> execute(String brand, String model, Year year) {
        Optional<Integer> brandId = getBrandId(brand);
        if (!brandId.isPresent()) {
            return Result.fail("Brand not found");
        }
        Optional<Integer> modelId = getModelId(brandId.get(), model);
        if (!modelId.isPresent()) {
            return Result.fail("Model not found");
        }
        Optional<String> yearId = getYearId(brandId.get(), modelId.get(), year);
        if (!yearId.isPresent()) {
            return Result.fail("Not found model with the year");
        }
        ResponseVehicle vehicle =
                vehicleClient.getVehicle(brandId.get(), modelId.get(), yearId.get());
        return Result.ok(vehicle.getPrice());
    }

    private Optional<String> getYearId(Integer brandId, Integer modelId, Year year) {
        return vehicleClient.getAllYearsOfAModel(brandId, modelId).stream()
                .filter((item) -> item.getCode().contains(year.toString())).findFirst()
                .map((item) -> item.getCode());
    }

    private Optional<Integer> getModelId(int brandId, String model) {
        return vehicleClient.getAllModelsByBrand(brandId).modelos.stream()
                .filter((item) -> item.getName().equalsIgnoreCase(model)).findFirst()
                .map((item) -> item.getCode());
    }

    private Optional<Integer> getBrandId(String brand) {
        return vehicleClient.getAllBrands().stream()
                .filter((item) -> item.getName().equalsIgnoreCase(brand)).findFirst()
                .map((item) -> item.getCode());
    }
}
