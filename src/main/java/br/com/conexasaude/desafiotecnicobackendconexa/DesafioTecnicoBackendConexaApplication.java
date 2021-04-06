package br.com.conexasaude.desafiotecnicobackendconexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DesafioTecnicoBackendConexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioTecnicoBackendConexaApplication.class, args);
		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}

}
