package pe.edu.cibertec.examenT1_fronted.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pe.edu.cibertec.examenT1_fronted.model.VehiculoModel;

@Controller
@RequestMapping("/busqueda")
public class VehiculoController {

    @GetMapping("/inicio")
    public String inicio(Model model) {
        VehiculoModel vehiculoModel = new VehiculoModel("00", "", "", "", "", "", "");
        model.addAttribute("vehiculoModel", vehiculoModel);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("placa") String placa, Model model) {

        //validar campo
        if (placa == null || placa.trim().length() == 0 || placa.trim().length() >= 8) {
            VehiculoModel vehiculoModel = new VehiculoModel("01",
                    "Error: Debe completar correctamente la placa",
                    "", "", "", "", "");
            model.addAttribute("vehiculoModel", vehiculoModel);
            return "inicio";
        }


        return "inicio";
}
}
