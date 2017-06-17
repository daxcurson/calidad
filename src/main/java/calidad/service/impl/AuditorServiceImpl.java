package calidad.service.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import calidad.dao.AuditorDAO;
import calidad.dao.UserDAO;
import calidad.exceptions.AuditorExistenteException;
import calidad.model.Auditor;
import calidad.model.User;
import calidad.service.AuditorService;

@Service
public class AuditorServiceImpl implements AuditorService 
{
	@Autowired
	private AuditorDAO auditorDAO;
	@Autowired
	private UserDAO userDAO;
	@Override
	public List<Auditor> listarAuditores() 
	{
		return auditorDAO.listarAuditores();
	}

	@Override
	public void agregar(Auditor auditor) throws AuditorExistenteException 
	{
		try
		{
			auditor.setUsuario_sistema(true);
			User user=auditor.getUser();
			user.setEnabled(1);
			// Hay que encriptar el password antes de grabarlo!!!
	        BCryptPasswordEncoder pwe=new BCryptPasswordEncoder();
	        user.setPassword(pwe.encode(user.getPassword()));
	        user.setConfirm_password(user.getPassword());

			userDAO.save(auditor.getUser());
			auditorDAO.add(auditor);
		}
		catch(ConstraintViolationException e)
		{
			throw new AuditorExistenteException();
		}
	}

	@Override
	public Auditor getAuditorById(Integer auditorId) 
	{
		return auditorDAO.getById(auditorId);
	}

	@Override
	public void grabar(Auditor auditor) 
	{
		auditorDAO.update(auditor);
	}

}
