package calidad.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calidad.dao.MedicionDAO;
import calidad.dao.MetricasDAO;
import calidad.model.Medicion;
import calidad.model.MedicionJson;
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
	public List<Medicion> listarMedicionesProyecto(int proyecto_id) 
	{
		return medicionDAO.listarMedicionesProyecto(proyecto_id);
	}
	@Override
	public List<Medicion> listarMedicionesMetrica(int metrica_id)
	{
		return medicionDAO.listarMedicionesMetrica(metrica_id);
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
	@Override
	public List<MedicionJson> listarMedicionesMetricaJson(int metrica_id) 
	{
		List<Medicion> mediciones=medicionDAO.listarMedicionesMetrica(metrica_id);
		// Convierto al formato que necesito
		List<MedicionJson> lista=new LinkedList<MedicionJson>();
		Iterator<Medicion> i=mediciones.iterator();
		while(i.hasNext())
		{
			Medicion m=i.next();
			MedicionJson e=new MedicionJson();
			SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
			e.setKey("Metrica"+metrica_id+"_"+formatoFecha.format(m.getFecha()));
			e.setValue(Double.toString(m.getValor_medido()));
			e.setUnit(m.getMetrica().getUnidad_medida().getDescripcion());
			e.setFecha(formatoFecha.format(m.getFecha()));
			lista.add(e);
		}
		return lista;
	}

}
