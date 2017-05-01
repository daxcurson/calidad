package calidad.service;

import java.util.List;
import calidad.model.Pregunta;

public interface PreguntaService 
{
	List<Pregunta> listarPreguntas(int objetivoId);
	void agregar(Pregunta pregunta, int objetivoId);
	Pregunta getPreguntaById(Integer preguntaId);
	void grabar(Pregunta pregunta);
}
