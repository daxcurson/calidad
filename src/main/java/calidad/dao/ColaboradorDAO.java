package calidad.dao;

import java.util.List;

import calidad.model.Colaborador;

public interface ColaboradorDAO extends GenericDAO<Colaborador>
{
	List<Colaborador> listarColaboradores();
}
