package com.henrique3g.controle_veiculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(enableDefaultTransactions = false)
public class ControleDeVeiculosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeVeiculosApplication.class, args);
	}

}
