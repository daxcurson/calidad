package calidad.controller;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import calidad.documentation.Descripcion;
import calidad.documentation.DescripcionClase;
import calidad.service.ObjetivosService;
import calidad.service.ProyectoService;

@Controller
@RequestMapping("/tablero")
@DescripcionClase("Tablero de control")
public class TableroController extends AppController
{
	@Autowired
	private ProyectoService proyectoService;
	@Autowired
	private ObjetivosService objetivoService;
	//private static Logger log=LogManager.getLogger(TableroController.class);
	@RequestMapping("/mostrar/{proyectoId}")
	@Descripcion(value="Mostrar lista de objetivos del proyecto",permission="ROLE_TABLERO_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_TABLERO_MOSTRAR_MENU')")
	public ModelAndView mostrarTablero(@PathVariable("proyectoId") int proyectoId)
	{
		ModelAndView modelo=new ModelAndView("tablero_index");
		// Leemos los objetivos que hay en este proyecto.
		modelo.addObject("objetivos",objetivoService.listarObjetivos(proyectoId));
		// Para cada metrica, quiero saber el valor de la ultima medicion.
		// Hay 2 alternativas: o la busco, o construyo algo para que la clase la busque sola.
		// Eso podria ser con campos virtuales.
		modelo.addObject("proyecto",proyectoService.getProyectoById(proyectoId));
		return modelo;
	}
}
