package calidad.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;

import calidad.model.Persona;

public interface UserDetails extends Serializable 
{
    Collection<GrantedAuthority> getAuthorities();
    String getPassword();
    String getUsername();
    Persona getPersona();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}