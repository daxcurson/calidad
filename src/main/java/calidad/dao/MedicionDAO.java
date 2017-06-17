package calidad.dao;

import java.util.List;

import calidad.model.Medicion;

public interface MedicionDAO extends GenericDAO<Medicion>
{
	List<Medicion> listarMediciones(int proyecto_id);
}
