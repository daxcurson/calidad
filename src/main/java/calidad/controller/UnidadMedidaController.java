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
import org.springframework.web.servlet.ModelAndView;

import calidad.documentation.Descripcion;
import calidad.documentation.DescripcionClase;
import calidad.model.UnidadMedida;
import calidad.service.UnidadMedidaService;

@Controller
@RequestMapping("/unidades")
@DescripcionClase("Unidades de medida")
public class UnidadMedidaController 
{
	@Autowired
	private UnidadMedidaService unidadMedidaService;
	
	private static Logger log=LogManager.getLogger(UnidadMedidaController.class);
	
	@RequestMapping({"/","/index"})
	@Descripcion(value="Mostrar lista de unidades de medida",permission="ROLE_UNIDADES_MOSTRAR_MENU")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_UNIDADES_MOSTRAR_MENU')")
	public ModelAndView listarUnidadesMedida()
	{
		ModelAndView modelo=new ModelAndView("unidad_medida_index");
		modelo.addObject("unidades",unidadMedidaService.listarUnidadesMedida());
		return modelo;
	}
	private ModelAndView cargarFormUnidadMedida(String vista,UnidadMedida unidadMedida)
	{
		ModelAndView modelo=new ModelAndView(vista);
		modelo.addObject("unidad_medida",unidadMedida);
		return modelo;
	}
	
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_UNIDADES_AGREGAR')")
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public ModelAndView mostrarFormProyecto(Model model)
	{
		ModelAndView modelo=this.cargarFormUnidadMedida("unidad_medida_add",new UnidadMedida());
		return modelo;
	}
	@Descripcion(value="Agregar Unidad de medida",permission="ROLE_UNIDADES_AGREGAR")
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_UNIDADES_AGREGAR')")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView agregarUnidadMedida(@Valid @ModelAttribute("unidad_medida")
	UnidadMedida unidadMedida,
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
			ModelAndView modelo=this.cargarFormUnidadMedida("unidad_medida_add",unidadMedida);
			return modelo;
		}
		else
		{
			ModelAndView modelo=new ModelAndView("redirect:/unidades/index");
			try
			{
				unidadMedidaService.agregar(unidadMedida);
				model.addAttribute("message","Unidad de medida agregada exitosamente");
			}
			catch(Exception e)
			{
				model.addAttribute("message","Errores al grabar la unidad de medida");
				modelo=this.cargarFormUnidadMedida("unidad_medida_add",unidadMedida);
			}
			return modelo;
		}
	}
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_UNIDADES_EDITAR')")
	@RequestMapping(value="/edit/{unidadId}",method=RequestMethod.GET)
	public ModelAndView mostrarFormEditar(@PathVariable("unidadId") Integer unidadId,
			Model model)
	{
		UnidadMedida p=this.unidadMedidaService.getUnidadMedidaById(unidadId);
		ModelAndView modelo=this.cargarFormUnidadMedida("unidad_medida_edit",p);
		return modelo;
	}
	@Descripcion(value="Editar Unidad de Medida",permission="ROLE_UNIDADES_EDITAR")
	@RequestMapping(value="/edit/{unidadId}",method=RequestMethod.POST)
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_UNIDADES_EDITAR')")
	public ModelAndView editarUnidadMedida(@PathVariable("unidadId") Integer unidadId,
			@Valid @ModelAttribute("unidad_medida") UnidadMedida unidadMedida,
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
			ModelAndView modelo=this.cargarFormUnidadMedida("unidad_medida_edit",unidadMedida);
			return modelo;
		}
		else
		{
			log.trace("El id de la unidad es "+unidadMedida.getId());
			ModelAndView modelo=new ModelAndView("redirect:/unidades/index");
			try
			{
				unidadMedidaService.grabar(unidadMedida);
				model.addAttribute("message","Unidad de Medida editada exitosamente");
			}
			catch(Exception e)
			{
				log.trace("Error al grabar: "+e.getMessage());
				model.addAttribute("message","Error al editar Unidad de Medida");
				modelo=this.cargarFormUnidadMedida("unidad_medida_edit",unidadMedida);
			}
			return modelo;
		}
	}
}
