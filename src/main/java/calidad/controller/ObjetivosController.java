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
import calidad.model.Objetivo;
import calidad.service.ObjetivosService;
import calidad.service.ProyectoService;

@Controller
@RequestMapping("objetivos")
@DescripcionClase("Objetivos")
@SessionAttributes("objetivo")
public class ObjetivosController extends AppController
{
	private static Logger log=LogManager.getLogger(ObjetivosController.class);

	@Autowired
	private ObjetivosService objetivoService;
	@Autowired
	private ProyectoService proyectoService;
	@RequestMapping("/listar/{proyectoId}")
	@Descripcion(value="Mostrar lista de objetivos del proyecto",permission="ROLE_OBJETIVOS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_OBJETIVOS_MOSTRAR_MENU')")
	public ModelAndView listarObjetivos(@PathVariable("proyectoId") int proyectoId)
	{
		ModelAndView modelo=new ModelAndView("objetivo_index");
		// Leemos los objetivos que hay en este proyecto.
		modelo.addObject("objetivos",objetivoService.listarObjetivos(proyectoId));
		modelo.addObject("proyecto",proyectoService.getProyectoById(proyectoId));
		return modelo;
	}
	private ModelAndView cargarFormObjetivo(String vista,Objetivo objetivo)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("objetivo",objetivo);
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_OBJETIVOS_AGREGAR')")
	@RequestMapping(value="/add/{proyectoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormProyecto(@PathVariable("proyectoId") int proyectoId,
			Model model)
	{
		ModelAndView modelo=this.cargarFormObjetivo("objetivo_add",new Objetivo());
		return modelo;
	}
	@Descripcion(value="Agregar Objetivo",permission="ROLE_OBJETIVOS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_OBJETIVOS_AGREGAR')")
	@RequestMapping(value = "/add/{proyectoId}", method = RequestMethod.POST)
	public ModelAndView agregarProyecto(@PathVariable("proyectoId") int proyectoId,
			@Valid @ModelAttribute("objetivo")
	Objetivo objetivo,
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
			ModelAndView modelo=this.cargarFormObjetivo("objetivo_add",objetivo);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/objetivos/listar/"+proyectoId);
			try
			{
				objetivoService.agregar(objetivo,proyectoId);
				model.addAttribute("message","Objetivo agregado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar el objetivo");
				modelo=this.cargarFormObjetivo("objetivo_add",objetivo);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_OBJETIVOS_EDITAR')")
	@RequestMapping(value="/edit/{objetivoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("objetivoId") Integer objetivoId,
			Model model)
	{
		Objetivo p=this.objetivoService.getObjetivoById(objetivoId);
		ModelAndView modelo=this.cargarFormObjetivo("objetivo_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Objetivo",permission="ROLE_OBJETIVOS_EDITAR")
	@RequestMapping(value="/edit/{objetivoId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_OBJETIVOS_EDITAR')")
	public ModelAndView editarObjetivo(@PathVariable("objetivoId") Integer objetivoId,
			@Valid @ModelAttribute("objetivo") Objetivo objetivo,
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
			ModelAndView modelo=this.cargarFormObjetivo("objetivo_edit",objetivo);
			return modelo;
		}
		else
		{
			log.trace("El id del objetivo es "+objetivo.getId());
			ModelAndView modelo=new ModelAndView("redirect:/objetivos/index/"+objetivo.getProyecto().getId());
			try
			{
				objetivoService.grabar(objetivo);
				model.addAttribute("message","Objetivo editado exitosamente");
			}
			catch(Exception e)
			{
				log.trace("Error al grabar: "+e.getMessage());
				model.addAttribute("message","Error al editar Objetivo");
				modelo=this.cargarFormObjetivo("objetivo_edit",objetivo);
			}
			return modelo;
		}
	}
}
