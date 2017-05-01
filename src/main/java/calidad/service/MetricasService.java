package calidad.service;

import java.util.List;

import calidad.model.Metrica;

public interface MetricasService
{
	List<Metrica> listarMetricas(int preguntaId);
	void agregar(Metrica metrica, int preguntaId);
	Metrica getMetricaById(Integer metricaId);
	void grabar(Metrica metrica);
}
