package calidad.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import calidad.dao.ColaboradorDAO;
import calidad.exceptions.ColaboradorExistenteException;
import calidad.model.Colaborador;
import calidad.service.ColaboradorService;

@Service
public class ColaboradorServiceImpl implements ColaboradorService
{
	@Autowired
	private ColaboradorDAO colaboradorDAO;

	@Override
	public List<Colaborador> listarColaboradores() 
	{
		return null;
	}

	@Override
	public void agregar(Colaborador colaborador) throws ColaboradorExistenteException 
	{
	}

	@Override
	public Colaborador getColaboradorById(Integer colaboradorId) 
	{
		return null;
	}

	@Override
	public void grabar(Colaborador colaborador) 
	{
	}	
}
