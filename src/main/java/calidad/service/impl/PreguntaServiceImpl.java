package calidad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import calidad.dao.ObjetivosDAO;
import calidad.dao.PreguntasDAO;
import calidad.model.Pregunta;
import calidad.service.PreguntaService;

@Service
public class PreguntaServiceImpl implements PreguntaService 
{
	@Autowired
	private PreguntasDAO preguntaDAO;
	@Autowired
	private ObjetivosDAO objetivoDAO;
	@Override
	public List<Pregunta> listarPreguntas(int objetivoId) 
	{
		return preguntaDAO.listarPreguntasObjetivo(objetivoId);
	}

	@Override
	@Transactional
	public void agregar(Pregunta pregunta, int objetivoId) 
	{
		pregunta.setObjetivo(objetivoDAO.getById(objetivoId));
		preguntaDAO.add(pregunta);
	}

	@Override
	public Pregunta getPreguntaById(Integer preguntaId) 
	{
		return preguntaDAO.getById(preguntaId);
	}

	@Override
	@Transactional
	public void grabar(Pregunta pregunta) 
	{
		preguntaDAO.update(pregunta);
	}
}
