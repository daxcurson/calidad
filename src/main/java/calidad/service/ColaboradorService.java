package calidad.service;

import java.util.List;

import calidad.exceptions.ColaboradorExistenteException;
import calidad.model.Colaborador;

public interface ColaboradorService 
{
	List<Colaborador> listarColaboradores();
	void agregar(Colaborador colaborador) throws ColaboradorExistenteException;
	Colaborador getColaboradorById(Integer colaboradorId);
	void grabar(Colaborador colaborador);
}
