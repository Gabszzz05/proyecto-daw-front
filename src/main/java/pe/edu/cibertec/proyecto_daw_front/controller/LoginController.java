package pe.edu.cibertec.proyecto_daw_front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.proyecto_daw_front.client.AuthClient;
import pe.edu.cibertec.proyecto_daw_front.dto.LoginRequestDTO;
import pe.edu.cibertec.proyecto_daw_front.dto.LoginResponseDTO;
import pe.edu.cibertec.proyecto_daw_front.viewModel.LoginModel;

import java.util.ArrayList;

//https://app-proyecto-backend.azurewebsites.net/

@Controller
@RequestMapping("/login")
public class LoginController {
    //FeignClient
    @Autowired
    AuthClient authClient;

    @Autowired
    RestTemplate restTemplateConfig;

    //Vista inicial
    @GetMapping("/inicio")
    public String inicioPage(Model model) {
        //Instanciamos el viewmodel
        LoginModel loginModel = new LoginModel("00", "", "");
        //                  VARIABLE            VALOR
        model.addAttribute("loginModel", loginModel);
        //Retornamos la vista
        return "inicioPage";
    }

    //Autenticar
    @PostMapping("/auth")
    public String autenticarFeign(@RequestParam("codeAlumno") String codeAlumno,
                                  @RequestParam("passAlumno") String passAlumno,
                                  Model model) {
        //Msj
        System.out.println("Consumiendo con FeignClient");
        //Validamos los campos
        if(codeAlumno.isEmpty() || passAlumno == null && passAlumno.isEmpty() || passAlumno == null) {
            LoginModel loginModel = new LoginModel("01", "ERROR: Completar todos los campos", "");
            return "inicioPage";
        }
        //
        try{
            //Preparar request
            LoginRequestDTO loginRequestDTO = new LoginRequestDTO(codeAlumno, passAlumno);
            //Consumir servicio con FeignClient
            ResponseEntity<LoginResponseDTO> responseEntity = authClient.login(loginRequestDTO);
            //Validar respuesta del servicio
            if(responseEntity.getStatusCode().is2xxSuccessful()){
                //Recuperar response
                LoginResponseDTO loginResponseDTO = responseEntity.getBody();
                //
                if(loginResponseDTO.code().equals("00")){

                    ArrayList<String> grupo5 = restTemplateConfig.getForObject("/auth/integrantes", ArrayList.class);

                    System.out.println(grupo5.toString());
                    model.addAttribute("grupo5", grupo5);

                    return "principal";

                }else {
                    LoginModel loginModel = new LoginModel("01", "ERROR: Autenticacion fallida","");
                    model.addAttribute("loginModel", loginModel);
                    return "inicioPage";
                }
            }else {
                LoginModel loginModel = new LoginModel("02", "ERROR: Ocurrio un problema HTTP","");
                model.addAttribute("loginModel", loginModel);
                return "inicioPage";
            }
        }catch (Exception e) {
            LoginModel loginModel = new LoginModel("99", "ERROR: Ocurrio un problema", "");
            model.addAttribute("loginModel", loginModel);
            System.out.println(e.getMessage());
            return "inicioPage";
        }
    }

}
