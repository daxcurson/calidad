package calidad.service;

import java.util.List;

import calidad.exceptions.AuditorExistenteException;
import calidad.model.Auditor;

public interface AuditorService 
{
	List<Auditor> listarAuditores();
	void agregar(Auditor auditor) throws AuditorExistenteException;
	Auditor getAuditorById(Integer auditorId);
	void grabar(Auditor auditor);
}
