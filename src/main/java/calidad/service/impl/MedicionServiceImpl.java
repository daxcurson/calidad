package calidad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calidad.dao.MedicionDAO;
import calidad.model.Medicion;
import calidad.service.MedicionService;

@Service
public class MedicionServiceImpl implements MedicionService 
{
	@Autowired
	private MedicionDAO medicionDAO;
	@Override
	public List<Medicion> listarMediciones(int proyecto_id) 
	{
		return medicionDAO.listarMediciones(proyecto_id);
	}

	@Override
	public void agregar(Medicion medicion) 
	{
		medicionDAO.add(medicion);
	}

	@Override
	public Medicion getMedicionById(Integer medicionId) 
	{
		return medicionDAO.getById(medicionId);
	}

	@Override
	public void grabar(Medicion medicion) 
	{
		medicionDAO.update(medicion);
	}

}
