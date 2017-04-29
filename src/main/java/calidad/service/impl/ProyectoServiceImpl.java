package calidad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calidad.dao.ProyectosDAO;
import calidad.model.Proyecto;
import calidad.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService
{
	@Autowired
	private ProyectosDAO proyectoDAO;
	@Override
	public List<Proyecto> listarProyectos() 
	{
		return proyectoDAO.listarProyectos();
	}
	@Override
	public void agregar(Proyecto proyecto) 
	{
		proyectoDAO.add(proyecto);
	}
	@Override
	public Proyecto getProyectoById(Integer proyectoId) 
	{
		return proyectoDAO.getById(proyectoId);
	}
	@Override
	public void grabar(Proyecto proyecto) 
	{
		proyectoDAO.update(proyecto);
	}

}
