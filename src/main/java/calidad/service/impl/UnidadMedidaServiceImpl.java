package calidad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calidad.dao.UnidadMedidaDAO;
import calidad.model.UnidadMedida;
import calidad.service.UnidadMedidaService;

@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService
{
	@Autowired
	private UnidadMedidaDAO unidadMedidaDAO;
	@Override
	public void agregar(UnidadMedida unidadMedida) 
	{
		unidadMedidaDAO.add(unidadMedida);
	}

	@Override
	public UnidadMedida getUnidadMedidaById(Integer unidadId) 
	{
		return unidadMedidaDAO.getById(unidadId);
	}

	@Override
	public void grabar(UnidadMedida unidadMedida) 
	{
		unidadMedidaDAO.update(unidadMedida);
	}

	@Override
	public List<UnidadMedida> listarUnidadesMedida() 
	{
		return unidadMedidaDAO.listar();
	}

}
