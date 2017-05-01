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
import calidad.model.Pregunta;
import calidad.service.ObjetivosService;
import calidad.service.PreguntaService;

@Controller
@RequestMapping("preguntas")
@DescripcionClase("Preguntas")
@SessionAttributes("pregunta")
public class PreguntasController extends AppController
{
	private static Logger log=LogManager.getLogger(PreguntasController.class);

	@Autowired
	private PreguntaService preguntaService;
	@Autowired
	private ObjetivosService objetivoService;
	@RequestMapping("/listar/{objetivoId}")
	@Descripcion(value="Mostrar lista de preguntas del objetivo",permission="ROLE_PREGUNTAS_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PREGUNTAS_MOSTRAR_MENU')")
	public ModelAndView listarPreguntas(@PathVariable("objetivoId") int objetivoId)
	{
		ModelAndView modelo=new ModelAndView("pregunta_index");
		// Leemos los objetivos que hay en este proyecto.
		modelo.addObject("preguntas",preguntaService.listarPreguntas(objetivoId));
		modelo.addObject("objetivo",objetivoService.getObjetivoById(objetivoId));
		return modelo;
	}
	private ModelAndView cargarFormPregunta(String vista,Pregunta pregunta)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("pregunta",pregunta);
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PREGUNTAS_AGREGAR')")
	@RequestMapping(value="/add/{objetivoId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormPregunta(@PathVariable("objetivoId") int objetivoId,
			Model model)
	{
		ModelAndView modelo=this.cargarFormPregunta("pregunta_add",new Pregunta());
		return modelo;
	}
	@Descripcion(value="Agregar Pregunta",permission="ROLE_PREGUNTAS_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PREGUNTAS_AGREGAR')")
	@RequestMapping(value = "/add/{objetivoId}", method = RequestMethod.POST)
	public ModelAndView agregarProyecto(@PathVariable("objetivoId") int objetivoId,
			@Valid @ModelAttribute("pregunta")
	Pregunta pregunta,
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
			ModelAndView modelo=this.cargarFormPregunta("pregunta_add",pregunta);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/preguntas/listar/"+objetivoId);
			try
			{
				preguntaService.agregar(pregunta,objetivoId);
				model.addAttribute("message","Pregunta agregada exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar la pregunta");
				modelo=this.cargarFormPregunta("pregunta_add",pregunta);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PREGUNTAS_EDITAR')")
	@RequestMapping(value="/edit/{preguntaId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("preguntaId") Integer preguntaId,
			Model model)
	{
		Pregunta p=this.preguntaService.getPreguntaById(preguntaId);
		ModelAndView modelo=this.cargarFormPregunta("pregunta_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Pregunta",permission="ROLE_PREGUNTAS_EDITAR")
	@RequestMapping(value="/edit/{preguntaId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_PREGUNTAS_EDITAR')")
	public ModelAndView editarPregunta(@PathVariable("preguntaId") Integer preguntaId,
			@Valid @ModelAttribute("pregunta") Pregunta pregunta,
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
			ModelAndView modelo=this.cargarFormPregunta("pregunta_edit",pregunta);
			return modelo;
		}
		else
		{
			log.trace("El id de la pregunta es "+pregunta.getId());
			ModelAndView modelo=new ModelAndView("redirect:/preguntas/listar/"+pregunta.getObjetivo().getId());
			try
			{
				preguntaService.grabar(pregunta);
				model.addAttribute("message","Pregunta editado exitosamente");
			}
			catch(Exception e)
			{
				log.trace("Error al grabar: "+e.getMessage());
				model.addAttribute("message","Error al editar Pregunta");
				modelo=this.cargarFormPregunta("pregunta_Edit",pregunta);
			}
			return modelo;
		}
	}
}
