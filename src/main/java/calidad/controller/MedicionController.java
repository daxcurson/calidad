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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import calidad.documentation.Descripcion;
import calidad.documentation.DescripcionClase;
import calidad.model.Medicion;
import calidad.service.MedicionService;

@Controller
@RequestMapping("/medicion")
@DescripcionClase("Mediciones")
@SessionAttributes("medicion")
public class MedicionController extends AppController 
{
	@Autowired
	private MedicionService medicionService;
	private static Logger log=LogManager.getLogger(MedicionController.class);

	@RequestMapping("/listar_mediciones/{proyecto_id}")
	@Descripcion(value="Mostrar lista de mediciones tomadas del proyecto",permission="ROLE_MEDICIONES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEDICIONES_MOSTRAR_MENU')")
	public ModelAndView listarMediciones(@PathVariable("proyecto_id") int proyecto_id)
	{
		ModelAndView modelo=new ModelAndView("medicion_index");
		// Leemos los auditores que hay.
		modelo.addObject("mediciones",medicionService.listarMediciones(proyecto_id));
		return modelo;
	}
	private ModelAndView cargarFormMedicion(String vista,Medicion medicion)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("medicion",medicion);
		return modelo;
	}
	/**
	 * Agrega una medicion de una metrica
	 * @param metrica_id
	 * @param model
	 * @return
	 */
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEDICIONES_AGREGAR')")
	@RequestMapping(value="/add/{metrica_id}",method=RequestMethod.GET)
	public ModelAndView mostrarFormMedicion(@PathVariable("metrica_id") int metrica_id,
			Model model)
	{
		
		ModelAndView modelo=this.cargarFormMedicion("medicion_add",new Medicion());
		return modelo;
	}
	@Descripcion(value="Agregar Medicion",permission="ROLE_MEDICIONES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEDICIONES_AGREGAR')")
	@RequestMapping(value = "/add/{metrica_id}", method = RequestMethod.POST)
	public ModelAndView agregarMedicion(
			@PathVariable("metrica_id") int metrica_id,
			@Valid @ModelAttribute("medicion") Medicion medicion,
	BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormMedicion("medicion_add",medicion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/menu");
			try
			{
				medicionService.agregar(medicion,metrica_id);
				redirectAttributes.addFlashAttribute("message","Medici&oacute;n agregada exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar la medicion");
				modelo=this.cargarFormMedicion("medicion_add",medicion);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEDICIONES_EDITAR')")
	@RequestMapping(value="/edit/{medicionId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("medicionId") Integer medicionId,
			Model model)
	{
		Medicion p=this.medicionService.getMedicionById(medicionId);
		ModelAndView modelo=this.cargarFormMedicion("medicion_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Medicion",permission="ROLE_MEDICIONES_EDITAR")
	@RequestMapping(value="/edit/{medicionId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_MEDICIONES_EDITAR')")
	public ModelAndView editarMedicion(@PathVariable("medicionId") Integer medicionId,
			@Valid @ModelAttribute("medicion") Medicion medicion,
			BindingResult result,ModelMap model,final RedirectAttributes redirectAttributes)
	{
		if(result.hasErrors())
		{
			List<ObjectError> lista_errores=result.getAllErrors();
			Iterator<ObjectError> i=lista_errores.iterator();
			while(i.hasNext())
			{
				log.trace("Error: "+i.next().toString());
			}
			ModelAndView modelo=this.cargarFormMedicion("medicion_edit",medicion);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/mediciones/listar_mediciones/"+medicion.getProyecto().getId());
			try
			{
				log.trace("Voy a grabar");
				medicionService.grabar(medicion);
				log.trace("Listo, grabe");
				redirectAttributes.addFlashAttribute("message","Medici&oacute;n editada exitosamente");
			}
			catch(Exception e)
			{
				log.trace("Error al grabar: "+e.getMessage());
				model.addAttribute("message","Error al editar Medicion");
				modelo=this.cargarFormMedicion("medicion_edit",medicion);
			}
			return modelo;
		}
	}

}
