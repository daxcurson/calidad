package calidad.service;

import java.util.List;

import calidad.model.Group;

public interface PermissionService 
{
	public List<Group> listAllGroups();
	public String grantOrRevokePermission(Group g,String permission);
	public Group findGroupById(int id);
}
