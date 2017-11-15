package calidad.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import calidad.dao.ColaboradorDAO;
import calidad.model.Colaborador;

@Repository
public class ColaboradorDAOImpl extends GenericDAOImpl<Colaborador> implements ColaboradorDAO
{
	@Override
	public Colaborador getById(int id)
	{
		return (Colaborador) this.sessionFactory.getCurrentSession().createQuery("from Colaborador where id="+id).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Colaborador> listarColaboradores()
	{
		return (List<Colaborador>)this.sessionFactory.getCurrentSession().createQuery("from Colaborador").getResultList();
	}
}
