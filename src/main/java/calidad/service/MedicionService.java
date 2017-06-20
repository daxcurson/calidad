package calidad.service;

import java.util.List;

import calidad.model.Medicion;

public interface MedicionService 
{
	List<Medicion> listarMediciones(int proyecto_id);
	void agregar(Medicion medicion, int metrica_id);
	Medicion getMedicionById(Integer medicionId);
	void grabar(Medicion medicion);
}
