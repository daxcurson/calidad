package calidad.dao;

import java.util.List;

import calidad.model.UnidadMedida;

public interface UnidadMedidaDAO extends GenericDAO<UnidadMedida>
{
	List<UnidadMedida> listar();
}
