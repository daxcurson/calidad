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

import calidad.documentation.DescripcionClase;
import calidad.model.Proyecto;
import calidad.service.ProyectoService;
import calidad.documentation.Descripcion;

@Controller
@RequestMapping("proyectos")
@DescripcionClase("Proyectos")
@SessionAttributes("proyecto")
public class ProyectosController extends AppController
{
	private static Logger log=LogManager.getLogger(ProyectosController.class);

	@Autowired
	private ProyectoService proyectoService;
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de capacitadores y menu",permission="ROLE_PROYECTOS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_MOSTRAR_MENU')")
	public ModelAndView index()
	{
		ModelAndView modelo=new ModelAndView("proyecto_index");
		// Leemos los capacitadores que hay.
		modelo.addObject("proyectos",proyectoService.listarProyectos());
		return modelo;
	}
	private ModelAndView cargarFormProyecto(String vista,Proyecto proyecto)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("proyecto",proyecto);
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormProyecto(
			Model model)
	{
		ModelAndView modelo=this.cargarFormProyecto("proyecto_add",new Proyecto());
		return modelo;
	}
	@Descripcion(value="Agregar Proyecto",permission="ROLE_PROYECTOS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarProyecto(
			@Valid @ModelAttribute("proyecto")
	Proyecto proyecto,
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
			ModelAndView modelo=this.cargarFormProyecto("proyecto_add",proyecto);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/proyectos/index");
			try
			{
				proyectoService.agregar(proyecto);
				model.addAttribute("message","Proyecto agregado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar el proyecto");
				modelo=this.cargarFormProyecto("proyecto_add",proyecto);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_EDITAR')")
	@RequestMapping(value="/edit/{proyectoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("proyectoId") Integer proyectoId,
			Model model)
	{
		Proyecto p=this.proyectoService.getProyectoById(proyectoId);
		ModelAndView modelo=this.cargarFormProyecto("proyecto_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Proyecto",permission="ROLE_PROYECTOS_EDITAR")
	@RequestMapping(value="/edit/{proyectoId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PROYECTOS_EDITAR')")
	public ModelAndView editarProyecto(@PathVariable("proyectoId") Integer proyectoId,
			@Valid @ModelAttribute("proyecto") Proyecto proyecto,
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
			ModelAndView modelo=this.cargarFormProyecto("proyecto_edit",proyecto);
			return modelo;
		}
		else
		{
			log.trace("El id del proyecto es "+proyecto.getId());
			ModelAndView modelo=new ModelAndView("redirect:/proyectos/index");
			try
			{
				log.trace("Voy a grabar");
				proyectoService.grabar(proyecto);
				log.trace("Listo, grabe");
				model.addAttribute("message","Proyecto editado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Error al editar Proyecto");
				modelo=this.cargarFormProyecto("proyecto_edit",proyecto);
			}
			return modelo;
		}
	}
}
