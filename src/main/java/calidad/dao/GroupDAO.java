package calidad.dao;

import java.util.List;
import calidad.model.*;

public interface GroupDAO 
{
	List<Group> listAllGroups();
	Group findGroupById(long id);
	void save(Group g);
}
