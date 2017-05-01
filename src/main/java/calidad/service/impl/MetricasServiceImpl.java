package calidad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import calidad.dao.MetricasDAO;
import calidad.dao.PreguntasDAO;
import calidad.model.Metrica;
import calidad.service.MetricasService;

@Service
public class MetricasServiceImpl implements MetricasService
{
	@Autowired
	private MetricasDAO metricasDAO;
	@Autowired
	private PreguntasDAO preguntasDAO;
	@Override
	public List<Metrica> listarMetricas(int preguntaId) 
	{
		return metricasDAO.listarMetricasPregunta(preguntaId);
	}

	@Override
	@Transactional
	public void agregar(Metrica metrica, int preguntaId) 
	{
		metrica.setPregunta(preguntasDAO.getById(preguntaId));
		metricasDAO.add(metrica);
	}

	@Override
	public Metrica getMetricaById(Integer metricaId) 
	{
		return metricasDAO.getById(metricaId);
	}

	@Override
	@Transactional
	public void grabar(Metrica metrica) 
	{
		metricasDAO.update(metrica);
	}

}
