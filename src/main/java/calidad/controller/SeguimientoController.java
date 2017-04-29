package calidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import calidad.documentation.DescripcionClase;

@Controller
@RequestMapping("panel")
@DescripcionClase("Panel de Seguimiento")
@SessionAttributes("panel")
public class SeguimientoController 
{

}
