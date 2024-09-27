package pe.edu.cibertec.examenT1_fronted.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.examenT1_fronted.dto.VehiculoRequestDTO;
import pe.edu.cibertec.examenT1_fronted.dto.VehiculoResponseDTO;
import pe.edu.cibertec.examenT1_fronted.model.VehiculoModel;

@Controller
@RequestMapping("/busqueda")
public class VehiculoController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        VehiculoModel vehiculoModel = new VehiculoModel("00", "",
                "", "", "", "", "");
        model.addAttribute("vehiculoModel", vehiculoModel);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("placa") String placa, Model model) {

        //validar
        if (placa == null || placa.trim().length() == 0 || placa.trim().length() >= 8) {
            VehiculoModel vehiculoModel = new VehiculoModel("01",
                    "Error: Debe completar correctamente la placa",
                    "", "", "", "", "");
            model.addAttribute("vehiculoModel", vehiculoModel);
            return "inicio";
        }


        try{
        String url = "http://localhost:8081/inicio/buscar";
        VehiculoRequestDTO vehiculoRequestDTO = new VehiculoRequestDTO(placa);
        VehiculoResponseDTO vehiculoResponseDTO =restTemplate.postForObject(url, vehiculoRequestDTO, VehiculoResponseDTO.class);

        if (vehiculoResponseDTO.codigo().equals("00")){
            VehiculoModel vehiculoModel = new VehiculoModel("00","",vehiculoResponseDTO.marca(),
                    vehiculoResponseDTO.modelo(),vehiculoResponseDTO.numeroAsientos(),vehiculoResponseDTO.precio(),
                    vehiculoResponseDTO.color());
            model.addAttribute("vehiculoModel", vehiculoModel);
            return "principal";
        }else {
            VehiculoModel vehiculoModel = new VehiculoModel("01","Error: No se encontraron resultados",
                    "","","","  ","");
          model.addAttribute("vehiculoModel", vehiculoModel);
          return "inicio";
        }

        }catch (Exception e){
            VehiculoModel vehiculoModel = new VehiculoModel("01","Error: No se encontraron resultados",
                    "","","","","");
            model.addAttribute("vehiculoModel", vehiculoModel);
            return "inicio";
        }
}

}
