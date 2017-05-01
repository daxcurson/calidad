package calidad.service;

import java.util.List;

import calidad.model.Objetivo;

public interface ObjetivosService 
{
	List<Objetivo> listarObjetivos(int proyectoId);
	void agregar(Objetivo objetivo, int proyectoId);
	Objetivo getObjetivoById(Integer objetivoId);
	void grabar(Objetivo objetivo);
}
