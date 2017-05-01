package calidad.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import calidad.dao.PreguntasDAO;
import calidad.model.Pregunta;

@Repository
public class PreguntasDAOImpl extends GenericDAOImpl<Pregunta> implements PreguntasDAO
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Pregunta> listarPreguntasObjetivo(int objetivoId) 
	{
		return this.sessionFactory.getCurrentSession().createQuery("from Pregunta where objetivo_id="+objetivoId).getResultList();
	}

}
