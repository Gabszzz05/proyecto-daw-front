package pe.edu.cibertec.proyecto_daw_front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//Habilitamos el Feign Client y ya se podra usar
@EnableFeignClients
public class ProyectoDawFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoDawFrontApplication.class, args);
	}

}
