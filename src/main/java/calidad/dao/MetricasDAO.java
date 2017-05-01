package calidad.dao;

import java.util.List;

import calidad.model.Metrica;

public interface MetricasDAO extends GenericDAO<Metrica>
{
	List<Metrica> listarMetricasPregunta(int pregunta_id);
}
