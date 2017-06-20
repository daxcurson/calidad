package calidad.service.impl;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calidad.dao.MedicionDAO;
import calidad.dao.MetricasDAO;
import calidad.model.Medicion;
import calidad.model.Metrica;
import calidad.model.Proyecto;
import calidad.service.MedicionService;

@Service
public class MedicionServiceImpl implements MedicionService 
{
	@Autowired
	private MedicionDAO medicionDAO;
	@Autowired MetricasDAO metricasDAO;
	@Override
	public List<Medicion> listarMediciones(int proyecto_id) 
	{
		return medicionDAO.listarMediciones(proyecto_id);
	}

	@Override
	public void agregar(Medicion medicion,int metrica_id) 
	{
		// Tengo que buscar la metrica y asociarla
		Metrica m=metricasDAO.getById(metrica_id);
		medicion.setMetrica(m);
		Proyecto p=m.getPregunta().getObjetivo().getProyecto();
		medicion.setProyecto(p);
		medicion.setFecha(Calendar.getInstance().getTime());
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
