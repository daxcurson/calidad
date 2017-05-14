package calidad.service.impl;

import java.util.List;

import calidad.dao.AuditorDAO;
import calidad.model.Auditor;
import calidad.service.AuditorService;

public class AuditorServiceImpl implements AuditorService 
{
	private AuditorDAO auditorDAO;
	@Override
	public List<Auditor> listarAuditores() 
	{
		return auditorDAO.listarAuditores();
	}

	@Override
	public void agregar(Auditor auditor) 
	{
		auditorDAO.add(auditor);
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
