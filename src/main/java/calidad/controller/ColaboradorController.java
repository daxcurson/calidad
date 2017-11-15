package calidad.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import calidad.documentation.DescripcionClase;
import calidad.service.AuditorService;
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
}
