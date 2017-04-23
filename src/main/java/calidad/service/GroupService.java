package calidad.service;

import java.util.List;

import calidad.exceptions.GrupoExistenteException;
import calidad.model.Group;

public interface GroupService 
{
	public Group getById(long id);
	public List<Group> listGroups();
	public void save(Group group) throws GrupoExistenteException;
}
