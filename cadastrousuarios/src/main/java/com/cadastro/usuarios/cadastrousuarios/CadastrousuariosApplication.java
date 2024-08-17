package com.cadastro.usuarios.cadastrousuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
public class CadastrousuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastrousuariosApplication.class, args);
	}

}
