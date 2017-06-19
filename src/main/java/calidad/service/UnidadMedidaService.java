package calidad.service;

import java.util.List;

import calidad.model.UnidadMedida;

public interface UnidadMedidaService 
{
	void agregar(UnidadMedida unidadMedida);
	UnidadMedida getUnidadMedidaById(Integer unidadId);
	void grabar(UnidadMedida unidadMedida);
	List<UnidadMedida> listarUnidadesMedida();
}
