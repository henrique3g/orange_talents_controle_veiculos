package com.henrique3g.controle_veiculos;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String name;
    @Email
    private String email;
    @CPF
    private String cpf;
    @DateTimeFormat(iso = ISO.DATE)
    private LocalDate birthdate;

    public User() {}

    public User(String name, String email, String cpf, LocalDate birthdate) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.birthdate = birthdate;
    }
}
