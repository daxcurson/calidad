package calidad.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import calidad.documentation.DescripcionClase;

@Controller
@RequestMapping("proyectos")
@DescripcionClase("Proyectos")
@SessionAttributes("proyecto")
public class ProyectosController extends AppController
{

}
