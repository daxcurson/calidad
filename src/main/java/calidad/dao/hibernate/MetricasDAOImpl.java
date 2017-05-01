package calidad.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import calidad.dao.MetricasDAO;
import calidad.model.Metrica;

@Repository
public class MetricasDAOImpl extends GenericDAOImpl<Metrica> implements MetricasDAO
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Metrica> listarMetricasPregunta(int pregunta_id) 
	{
		return this.sessionFactory.getCurrentSession().createQuery("from Metrica where pregunta_id="+pregunta_id).getResultList();
	}
}
