package calidad.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import calidad.dao.ObjetivosDAO;
import calidad.model.Objetivo;

@Repository
public class ObjetivosDAOImpl extends GenericDAOImpl<Objetivo> implements ObjetivosDAO
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Objetivo> listarObjetivosProyecto(int proyecto_id)
	{
		return this.sessionFactory.getCurrentSession().createQuery("from Objetivo where proyecto_id="+proyecto_id).getResultList();
	}
}
