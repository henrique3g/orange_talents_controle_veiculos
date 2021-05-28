package com.henrique3g.controle_veiculos.vehicle;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "Fipe", url = "https://parallelum.com.br/fipe/api/v1/carros/")
public interface VehicleApiClient {
    @RequestMapping(method = RequestMethod.GET, value = "/marcas")
    public List<ResponseItem> getAllBrands();

    @RequestMapping(method = RequestMethod.GET, value = "/marcas/{brandId}/modelos")
    public ResponseModels getAllModelsByBrand(@PathVariable int brandId);

    @RequestMapping(method = RequestMethod.GET, value = "/marcas/{brandId}/modelos/{modelId}/anos")
    public List<Year> getAllYearsOfAModel(@PathVariable int brandId, @PathVariable int modelId);

    @RequestMapping(method = RequestMethod.GET,
            value = "/marcas/{brandId}/modelos/{modelId}/anos/{yearId}")
    public ResponseVehicle getVehicle(@PathVariable int brandId, @PathVariable int modelId,
            @PathVariable String yearId);

    public static class ResponseModels {
        public List<ResponseItem> modelos;
    }

    public static class ResponseItem {
        @JsonAlias("codigo")
        private int code;

        @JsonAlias("nome")
        private String name;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Year {
        @JsonAlias("codigo")
        private String code;

        @JsonAlias("nome")
        private String name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ResponseVehicle {
        @JsonAlias("Valor")
        private double price;

        public double getPrice() {
            return price;
        }

        public void setPrice(String price) {
            price = price.replace(".", "");
            price = price.replace(",", ".");
            price = price.replace("R$", "");
            this.price = Double.parseDouble(price);
        }
    }
}
