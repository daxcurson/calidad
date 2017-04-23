package calidad.service;

import calidad.exceptions.GrupoExistenteException;
import calidad.exceptions.UsuarioExistenteException;
import calidad.model.User;

public interface InstalacionService 
{
	public void grabarUsuarioAdministrador(User user) throws UsuarioExistenteException, GrupoExistenteException;
}
