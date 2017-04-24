package calidad.dao;

import java.util.List;

import calidad.model.Proyecto;

public interface ProyectosDAO extends GenericDAO<Proyecto>
{
	List<Proyecto> listarProyectos();
}
