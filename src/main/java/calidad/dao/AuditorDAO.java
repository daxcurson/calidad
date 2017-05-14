package calidad.dao;

import java.util.List;

import calidad.model.Auditor;

public interface AuditorDAO extends GenericDAO<Auditor> 
{
	List<Auditor> listarAuditores();
}
