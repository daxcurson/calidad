package calidad.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import calidad.dao.UnidadMedidaDAO;
import calidad.model.UnidadMedida;

@Repository
public class UnidadMedidaDAOImpl extends GenericDAOImpl<UnidadMedida> implements UnidadMedidaDAO
{
	@SuppressWarnings("unchecked")
	@Override
	public List<UnidadMedida> listar() 
	{
		return this.sessionFactory.getCurrentSession().createQuery("from UnidadMedida").getResultList();
	}
}
