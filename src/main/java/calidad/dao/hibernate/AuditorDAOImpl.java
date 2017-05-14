package calidad.dao.hibernate;

import java.util.List;

import calidad.dao.AuditorDAO;
import calidad.model.Auditor;

public class AuditorDAOImpl extends GenericDAOImpl<Auditor> implements AuditorDAO 
{

	@Override
	public Auditor getById(int id) 
	{
		return (Auditor) this.sessionFactory.getCurrentSession().createQuery("from Auditor where id="+id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Auditor> listarAuditores() 
	{
		return (List<Auditor>) this.sessionFactory.getCurrentSession().createQuery("from Auditor").getResultList();
	}
}
