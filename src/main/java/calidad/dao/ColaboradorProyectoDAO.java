package calidad.dao;

import java.util.List;

import calidad.model.ColaboradorProyecto;

public interface ColaboradorProyectoDAO extends GenericDAO<ColaboradorProyecto>
{
	List<ColaboradorProyecto> getColaboradoresProyecto(int proyecto_id);
	void quitarColaboradorProyecto(ColaboradorProyecto p);
	ColaboradorProyecto getColaboradorEnProyecto(int colaborador_id,int proyecto_id);
}
