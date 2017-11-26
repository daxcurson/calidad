package calidad.service;

import java.util.List;

import calidad.model.Medicion;
import calidad.model.MedicionJson;
import calidad.model.Persona;

public interface MedicionService 
{
	List<Medicion> listarMedicionesProyecto(int proyecto_id);
	List<Medicion> listarMedicionesMetrica(int metrica_id);
	List<MedicionJson> listarMedicionesMetricaJson(int metrica_id);
	void agregar(Medicion medicion, int metrica_id, Persona p);
	Medicion getMedicionById(Integer medicionId);
	void grabar(Medicion medicion);
}
