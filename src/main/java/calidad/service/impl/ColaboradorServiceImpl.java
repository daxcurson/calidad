package calidad.service.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import calidad.dao.ColaboradorDAO;
import calidad.dao.UserDAO;
import calidad.exceptions.ColaboradorExistenteException;
import calidad.model.Colaborador;
import calidad.model.User;
import calidad.service.ColaboradorService;

@Service
public class ColaboradorServiceImpl implements ColaboradorService
{
	@Autowired
	private ColaboradorDAO colaboradorDAO;
	@Autowired
	private UserDAO userDAO;

	@Override
	public List<Colaborador> listarColaboradores() 
	{
		return colaboradorDAO.listarColaboradores();
	}

	@Override
	public void agregar(Colaborador colaborador) throws ColaboradorExistenteException 
	{
		try
		{
			colaborador.setUsuario_sistema(true);
			User user=colaborador.getUser();
			user.setEnabled(1);
			// Hay que encriptar el password antes de grabarlo!!!
	        BCryptPasswordEncoder pwe=new BCryptPasswordEncoder();
	        user.setPassword(pwe.encode(user.getPassword()));
	        user.setConfirm_password(user.getPassword());

			userDAO.save(colaborador.getUser());
			colaboradorDAO.add(colaborador);
		}
		catch(ConstraintViolationException e)
		{
			throw new ColaboradorExistenteException();
		}
	}

	@Override
	public Colaborador getColaboradorById(Integer colaboradorId) 
	{
		return colaboradorDAO.getById(colaboradorId);
	}

	@Override
	public void grabar(Colaborador colaborador) 
	{
		colaboradorDAO.update(colaborador);
	}	
}
