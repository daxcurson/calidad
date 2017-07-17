package calidad.dao;

import java.util.List;

import calidad.model.Medicion;

public interface MedicionDAO extends GenericDAO<Medicion>
{
	List<Medicion> listarMedicionesProyecto(int proyecto_id);
	List<Medicion> listarMedicionesMetrica(int metrica_id);
}
