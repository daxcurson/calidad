package calidad.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import calidad.documentation.Descripcion;
import calidad.service.MedicionService;
import calidad.service.MetricasService;
import calidad.service.ObjetivosService;
import calidad.service.ProyectoService;

@Controller
@RequestMapping("/tablero")
public class TableroController extends AppController
{
	private ProyectoService proyectoService;
	private ObjetivosService objetivoService;
	private MetricasService metricasService;
	private MedicionService medicionService;
	private static Logger log=LogManager.getLogger(TableroController.class);
	@RequestMapping("/index/{proyectoId}")
	@Descripcion(value="Mostrar lista de objetivos del proyecto",permission="ROLE_TABLERO_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_TABLERO_MOSTRAR_MENU')")
	public ModelAndView mostrarTablero(@PathVariable("proyectoId") int proyectoId)
	{
		ModelAndView modelo=new ModelAndView("tablero_index");
		// Leemos los objetivos que hay en este proyecto.
		modelo.addObject("objetivos",objetivoService.listarObjetivos(proyectoId));
		modelo.addObject("proyecto",proyectoService.getProyectoById(proyectoId));
		return modelo;
	}
}
