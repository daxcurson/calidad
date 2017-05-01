package calidad.controller;

import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import calidad.documentation.Descripcion;
import calidad.documentation.DescripcionClase;
import calidad.model.Metrica;
import calidad.service.MetricasService;
import calidad.service.PreguntaService;

@Controller
@RequestMapping("metricas")
@DescripcionClase("M&eacute;tricas")
@SessionAttributes("metrica")
public class MetricasController extends AppController
{
	private static Logger log=LogManager.getLogger(MetricasController.class);

	@Autowired
	private MetricasService metricaService;
	@Autowired
	private PreguntaService preguntasService;
	@RequestMapping("/listar/{preguntaId}")
	@Descripcion(value="Mostrar lista de m&eacute;tricas para la pregunta",permission="ROLE_METRICAS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_METRICAS_MOSTRAR_MENU')")
	public ModelAndView listarMetricas(@PathVariable("preguntaId") int preguntaId)
	{
		ModelAndView modelo=new ModelAndView("metrica_index");
		// Leemos los objetivos que hay en este proyecto.
		modelo.addObject("metricas",metricaService.listarMetricas(preguntaId));
		modelo.addObject("pregunta",preguntasService.getPreguntaById(preguntaId));
		return modelo;
	}
	private ModelAndView cargarFormMetrica(String vista,Metrica metrica)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("metrica",metrica);
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_METRICAS_AGREGAR')")
	@RequestMapping(value="/add/{proyectoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormProyecto(@PathVariable("proyectoId") int proyectoId,
			Model model)
	{
		ModelAndView modelo=this.cargarFormMetrica("metrica_add",new Metrica());
		return modelo;
	}
	@Descripcion(value="Agregar Metrica",permission="ROLE_METRICAS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_METRICAS_AGREGAR')")
	@RequestMapping(value = "/add/{preguntaId}", method = RequestMethod.POST)
	public ModelAndView agregarMetrica(@PathVariable("preguntaId") int preguntaId,
			@Valid @ModelAttribute("metrica")
	Metrica metrica,
	BindingResult result,ModelMap model)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormMetrica("metrica_add",metrica);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/metricas/listar/"+preguntaId);
			try
			{
				metricaService.agregar(metrica,preguntaId);
				model.addAttribute("message","Metrica agregada exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar la metrica");
				modelo=this.cargarFormMetrica("metrica_add",metrica);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_METRICAS_EDITAR')")
	@RequestMapping(value="/edit/{metricaId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("metricaId") Integer metricaId,
			Model model)
	{
		Metrica p=this.metricaService.getMetricaById(metricaId);
		ModelAndView modelo=this.cargarFormMetrica("metrica_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Metrica",permission="ROLE_METRICAS_EDITAR")
	@RequestMapping(value="/edit/{metricaId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_METRICAS_EDITAR')")
	public ModelAndView editarMetrica(@PathVariable("metricaId") Integer metricaId,
			@Valid @ModelAttribute("metrica") Metrica metrica,
			BindingResult result,ModelMap model)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormMetrica("metrica_edit",metrica);
			return modelo;
		}
		else
		{
			log.trace("El id de la metrica es "+metrica.getId());
			ModelAndView modelo=new ModelAndView("redirect:/metricas/index/"+metrica.getPregunta());
			try
			{
				metricaService.grabar(metrica);
				model.addAttribute("message","Metrica editada exitosamente");
			}
			catch(Exception e)
			{
				log.trace("Error al grabar: "+e.getMessage());
				model.addAttribute("message","Error al editar Metrica");
				modelo=this.cargarFormMetrica("metrica_edit",metrica);
			}
			return modelo;
		}
	}
}
