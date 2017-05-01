package calidad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import calidad.dao.ObjetivosDAO;
import calidad.dao.ProyectosDAO;
import calidad.model.Objetivo;
import calidad.service.ObjetivosService;

@Service
public class ObjetivosServiceImpl implements ObjetivosService
{
	@Autowired
	private ObjetivosDAO objetivosDAO;
	@Autowired
	private ProyectosDAO proyectosDAO;
	@Override
	public List<Objetivo> listarObjetivos(int proyectoId) 
	{
		return objetivosDAO.listarObjetivosProyecto(proyectoId);
	}

	@Override
	@Transactional
	public void agregar(Objetivo objetivo, int proyectoId) 
	{
		objetivo.setProyecto(proyectosDAO.getById(proyectoId));
		objetivosDAO.add(objetivo);
	}

	@Override
	public Objetivo getObjetivoById(Integer objetivoId) 
	{
		return objetivosDAO.getById(objetivoId);
	}

	@Override
	@Transactional
	public void grabar(Objetivo objetivo) 
	{
		objetivosDAO.update(objetivo);
	}

}
