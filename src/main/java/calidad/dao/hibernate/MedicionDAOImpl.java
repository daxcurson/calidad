package calidad.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import calidad.dao.MedicionDAO;
import calidad.model.Medicion;

@Repository
public class MedicionDAOImpl extends GenericDAOImpl<Medicion> implements MedicionDAO
{

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicion> listarMedicionesProyecto(int proyecto_id) 
	{
		return this.sessionFactory.getCurrentSession().createQuery("from Medicion where proyecto.id="+proyecto_id).getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Medicion> listarMedicionesMetrica(int metrica_id)
	{
		return this.sessionFactory.getCurrentSession().createQuery("from Medicion where metrica_id="+metrica_id).getResultList();
	}
}
