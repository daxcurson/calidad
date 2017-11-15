package calidad.service.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import calidad.dao.ProyectosDAO;
import calidad.model.Colaborador;
import calidad.model.ColaboradorProyecto;
import calidad.model.Proyecto;
import calidad.service.ProyectoService;

@Service
public class ProyectoServiceImpl implements ProyectoService
{
	private static Logger log=LogManager.getLogger(ProyectoServiceImpl.class);
	@Autowired
	private ProyectosDAO proyectoDAO;
	@Override
	public List<Proyecto> listarProyectos() 
	{
		return proyectoDAO.listarProyectos();
	}
	@Override
	@Transactional
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
	@Transactional
	public void grabar(Proyecto proyecto) 
	{
		log.trace("Voy a grabar, llamando al DAO");
		proyectoDAO.update(proyecto);
	}
	@Override
	public List<ColaboradorProyecto> getColaboradores(int proyecto_id) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void agregarColaboradorProyecto(Proyecto proyecto, Colaborador colaborador) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void quitarColaborador(int id, Integer colaborador_proyecto_id) 
	{
		// TODO Auto-generated method stub
		
	}

}
