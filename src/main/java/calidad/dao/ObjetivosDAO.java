package calidad.dao;

import java.util.List;

import calidad.model.Objetivo;

public interface ObjetivosDAO extends GenericDAO<Objetivo>
{
	List<Objetivo> listarObjetivosProyecto(int proyecto_id);
}
