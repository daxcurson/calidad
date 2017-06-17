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
	public List<Medicion> listarMediciones(int proyecto_id) 
	{
		return this.sessionFactory.getCurrentSession().createQuery("from Mediciones where proyecto.id="+proyecto_id).getResultList();
	}

}
