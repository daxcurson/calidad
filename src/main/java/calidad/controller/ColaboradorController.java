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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import calidad.documentation.Descripcion;
import calidad.documentation.DescripcionClase;
import calidad.model.Colaborador;
import calidad.model.Group;
import calidad.model.propertyeditor.GroupEditor;
import calidad.service.ColaboradorService;
import calidad.service.GroupService;

@Controller
@RequestMapping("colaboradores")
@SessionAttributes("colaborador")
@DescripcionClase("Colaboradores")
public class ColaboradorController extends AppController
{
	private static Logger log=LogManager.getLogger(AuditorController.class);

	@Autowired
	private ColaboradorService colaboradorService;
	@Autowired
	private GroupService groupService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(Group.class, new GroupEditor(groupService));
	}
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de colaboradores y menu",permission="ROLE_COLABORADORES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_COLABORADORES_MOSTRAR_MENU')")
	public ModelAndView index()
	{
		ModelAndView modelo=new ModelAndView("colaborador_index");
		// Leemos los auditores que hay.
		modelo.addObject("colaboradores",colaboradorService.listarColaboradores());
		return modelo;
	}
	private ModelAndView cargarFormColaborador(String vista,Colaborador colaborador)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("colaborador",colaborador);
		modelo.addObject("groups",groupService.listGroups());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_COLABORADORES_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormColaborador(
			Model model)
	{
		ModelAndView modelo=this.cargarFormColaborador("colaborador_add",new Colaborador());
		return modelo;
	}
	@Descripcion(value="Agregar Colaborador",permission="ROLE_COLABORADORES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_COLABORADORES_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarColaborador(
			@Valid @ModelAttribute("colaborador")
	Colaborador colaborador,
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
			ModelAndView modelo=this.cargarFormColaborador("colaborador_add",colaborador);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/colaboradores/index");
			try
			{
				colaboradorService.agregar(colaborador);
				model.addAttribute("message","Colaborador agregado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar el colaborador");
				modelo=this.cargarFormColaborador("colaborador_add",colaborador);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_COLABORADORES_EDITAR')")
	@RequestMapping(value="/edit/{colaboradorId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("colaboradorId") Integer colaboradorId,
			Model model)
	{
		Colaborador p=this.colaboradorService.getColaboradorById(colaboradorId);
		ModelAndView modelo=this.cargarFormColaborador("colaborador_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Colaborador",permission="ROLE_COLABORADORES_EDITAR")
	@RequestMapping(value="/edit/{colaboradorId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_COLABORADORES_EDITAR')")
	public ModelAndView editarColaborador(@PathVariable("colaboradorId") Integer colaboradorId,
			@Valid @ModelAttribute("colaborador") Colaborador colaborador,
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
			ModelAndView modelo=this.cargarFormColaborador("colaborador_edit",colaborador);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/colaboradores/index");
			try
			{
				log.trace("Voy a grabar");
				log.trace("Nombre del colaborador: "+colaborador.getNombre());
				colaboradorService.grabar(colaborador);
				log.trace("Listo, grabe");
				model.addAttribute("message","Colaborador editado exitosamente");
			}
			catch(Exception e)
			{
				log.trace("Error al grabar: "+e.getMessage());
				model.addAttribute("message","Error al editar Colaborador");
				modelo=this.cargarFormColaborador("colaborador_edit",colaborador);
			}
			return modelo;
		}
	}
}
