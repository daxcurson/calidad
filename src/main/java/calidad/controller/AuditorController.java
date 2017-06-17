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
import calidad.model.Auditor;
import calidad.model.Group;
import calidad.model.propertyeditor.GroupEditor;
import calidad.service.AuditorService;
import calidad.service.GroupService;

@Controller
@RequestMapping("auditores")
@SessionAttributes("auditor")
@DescripcionClase("Auditores")
public class AuditorController extends AppController
{
	private static Logger log=LogManager.getLogger(AuditorController.class);

	@Autowired
	private AuditorService auditorService;
	@Autowired
	private GroupService groupService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
		binder.registerCustomEditor(Group.class, new GroupEditor(groupService));
	}
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de auditores y menu",permission="ROLE_AUDITORES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AUDITORES_MOSTRAR_MENU')")
	public ModelAndView index()
	{
		ModelAndView modelo=new ModelAndView("auditor_index");
		// Leemos los auditores que hay.
		modelo.addObject("auditores",auditorService.listarAuditores());
		return modelo;
	}
	private ModelAndView cargarFormAuditor(String vista,Auditor auditor)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("auditor",auditor);
		modelo.addObject("groups",groupService.listGroups());
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AUDITORES_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormAuditor(
			Model model)
	{
		ModelAndView modelo=this.cargarFormAuditor("auditor_add",new Auditor());
		return modelo;
	}
	@Descripcion(value="Agregar Auditor",permission="ROLE_AUDITORES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AUDITORES_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarAuditor(
			@Valid @ModelAttribute("auditor")
	Auditor auditor,
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
			ModelAndView modelo=this.cargarFormAuditor("auditor_add",auditor);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/auditores/index");
			try
			{
				auditorService.agregar(auditor);
				model.addAttribute("message","Auditor agregado exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar el auditor");
				modelo=this.cargarFormAuditor("auditor_add",auditor);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AUDITORES_EDITAR')")
	@RequestMapping(value="/edit/{auditorId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("auditorId") Integer auditorId,
			Model model)
	{
		Auditor p=this.auditorService.getAuditorById(auditorId);
		ModelAndView modelo=this.cargarFormAuditor("auditor_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Auditor",permission="ROLE_AUDITORES_EDITAR")
	@RequestMapping(value="/edit/{auditorId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_AUDITORES_EDITAR')")
	public ModelAndView editarAuditor(@PathVariable("auditorId") Integer auditorId,
			@Valid @ModelAttribute("auditor") Auditor auditor,
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
			ModelAndView modelo=this.cargarFormAuditor("auditor_edit",auditor);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/auditores/index");
			try
			{
				log.trace("Voy a grabar");
				log.trace("Nombre del auditor: "+auditor.getNombre());
				auditorService.grabar(auditor);
				log.trace("Listo, grabe");
				model.addAttribute("message","Auditor editado exitosamente");
			}
			catch(Exception e)
			{
				log.trace("Error al grabar: "+e.getMessage());
				model.addAttribute("message","Error al editar Auditor");
				modelo=this.cargarFormAuditor("auditor_edit",auditor);
			}
			return modelo;
		}
	}
}
