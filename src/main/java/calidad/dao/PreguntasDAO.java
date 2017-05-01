package calidad.dao;

import java.util.List;

import calidad.model.Pregunta;

public interface PreguntasDAO extends GenericDAO<Pregunta>
{
	List<Pregunta> listarPreguntasObjetivo(int objetivoId);
}
