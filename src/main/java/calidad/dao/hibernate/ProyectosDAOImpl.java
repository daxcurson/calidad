package calidad.dao.hibernate;

import java.util.List;

import calidad.dao.ProyectosDAO;
import calidad.model.Proyecto;

public class ProyectosDAOImpl extends GenericDAOImpl<Proyecto> implements ProyectosDAO
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Proyecto> listarProyectos() 
	{
		return (List<Proyecto>)this.sessionFactory.getCurrentSession().createQuery("from Proyecto").getResultList();
	}

}
