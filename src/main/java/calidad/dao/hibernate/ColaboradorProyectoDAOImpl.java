package calidad.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import calidad.dao.ColaboradorProyectoDAO;
import calidad.model.ColaboradorProyecto;

@Repository
public class ColaboradorProyectoDAOImpl extends GenericDAOImpl<ColaboradorProyecto> implements ColaboradorProyectoDAO
{
	@SuppressWarnings("unchecked")
	@Override
	public List<ColaboradorProyecto> getColaboradoresProyecto(int proyecto_id) 
	{
		return (List<ColaboradorProyecto>)sessionFactory.getCurrentSession().createQuery("from ColaboradorProyecto where proyecto.id="+proyecto_id).getResultList();
	}

	@Override
	@Transactional
	public void quitarColaboradorProyecto(ColaboradorProyecto p) 
	{
		sessionFactory.getCurrentSession().delete(p);
	}

	@Override
	public ColaboradorProyecto getColaboradorEnProyecto(int colaborador_id, int proyecto_id) 
	{
		return (ColaboradorProyecto)sessionFactory.getCurrentSession().createQuery("from ColaboradorProyecto where colaborador.id="+colaborador_id+" and proyecto.id="+proyecto_id).getSingleResult();
	}
}
