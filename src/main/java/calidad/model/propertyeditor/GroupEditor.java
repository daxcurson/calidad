package calidad.model.propertyeditor;

import java.beans.PropertyEditorSupport;

import calidad.model.Group;
import calidad.service.GroupService;

public class GroupEditor extends PropertyEditorSupport {

	private final GroupService groupService;
	public GroupEditor(GroupService groupService) 
	{
		this.groupService=groupService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException 
	{
		Group g=groupService.getById(Long.parseLong(text));
        setValue(g);
	}
}
