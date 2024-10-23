package pe.edu.cibertec.proyecto_daw_front.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.cibertec.proyecto_daw_front.dto.LoginRequestDTO;
import pe.edu.cibertec.proyecto_daw_front.dto.LoginResponseDTO;

import java.util.ArrayList;

//Asociamos la clase config al Feign
@FeignClient(name = "autenticacion", url = "https://app-proyecto-backend.azurewebsites.net")
public interface AuthClient {
    //Firma del login
    @PostMapping("/auth/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO);

    @GetMapping("/auth/integrantes")
    ArrayList<String> integrantes();

}
// url => http://localhost:8181/auth