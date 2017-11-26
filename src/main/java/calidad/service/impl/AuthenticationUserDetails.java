package calidad.service.impl;

import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;

import calidad.model.Group;
import calidad.model.Permission;
import calidad.model.PermissionGranted;
import calidad.model.Persona;
import calidad.model.User;
import calidad.service.UserDetails;


public class AuthenticationUserDetails implements org.springframework.security.core.userdetails.UserDetails,UserDetails 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private final String login;
    private final String passwordHash;
    private final boolean enabled;
    private Persona persona;
    private HashSet<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
    private static Logger log=LogManager.getLogger(AuthenticationUserDetails.class);
    private User user;

    public AuthenticationUserDetails(User user) 
    {
    	log.trace("Estoy en AuthenticationUserDetails (constructor)");
        this.login = user.getUsername();
        this.passwordHash = user.getPassword();
        this.enabled = (user.getEnabled()==1 ? true:false);
        // Convierto los permisos leidos de la base, para el grupo al que pertenece el usuario, en Authorities.
        Group g=user.getGroup();
        this.setPersona(user.getPersona());
        for(Permission p:g.getPermissions())
        {
        	PermissionGranted pg=new PermissionGranted(p.getAuthority());
        	log.trace("Obtuve el permiso "+pg.getAuthority());
        	this.grantedAuthorities.add(pg);
        }
        this.setUser(user);
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() 
    {
    	log.trace("Estoy en AuthenticationUserDetails.getAuthorities");
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
    	log.trace("Estoy en AuthenticationUserDetails.getPassword");
        return passwordHash;
    }

    @Override
    public String getUsername() {
    	log.trace("Estoy en AuthenticationUserDetails.getUsername");
        return login;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
    	log.trace("Estoy en isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
    	log.trace("Estoy en isAccountNonLocked");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
    	log.trace("Estoy en isCredentialsNonExpired");
        return true;
    }

    public String getLogin() {
    	log.trace("Estoy en getLogin");
        return login;
    }

	public int getId() {
		log.trace("Estoy en getId");
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Persona getPersona() 
	{
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
}