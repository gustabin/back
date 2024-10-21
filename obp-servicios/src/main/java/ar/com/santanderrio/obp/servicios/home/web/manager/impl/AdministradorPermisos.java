/**
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.manager.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;

/**
 * El manejo dinamico del contexto de seguridad esta dado por si el usuario no
 * tiene algun permiso segun sus productos pero en algun momento guardo o conoce
 * la url.<br>
 * Si se Ignora esa posibilidad se podria retirar esta logica ya que los
 * GrantedAuthority default es suficiente.
 * 
 * The Class AdministradorPermisos.
 *
 * @author sergio.e.goldentair
 */
@Component("gestorGrants")
public class AdministradorPermisos {

	/**
	 * Agregar grants al usuario registrado.
	 *
	 * @param irInicioCuentas
	 *            the ir inicio cuentas
	 */
	@SuppressWarnings("unchecked")
	public void addNewGrant(AccionController irInicioCuentas) {
		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(irInicioCuentas.getDescripcion());
		if (!oldAuthorities.contains(authority)) {
			Set<SimpleGrantedAuthority> updatedAuthorities = new HashSet<SimpleGrantedAuthority>(oldAuthorities);
			updatedAuthorities.add(authority);
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
					SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
					SecurityContextHolder.getContext().getAuthentication().getCredentials(), updatedAuthorities));
		}
	}

	/**
	 * Quitar el grant al usuario de modo que si guardo la url no pueda acceder.
	 *
	 * @param irInicioCuentas
	 *            the ir inicio cuentas
	 */
	@SuppressWarnings("unchecked")
	public void removeGrant(AccionController irInicioCuentas) {
		Collection<SimpleGrantedAuthority> oldAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(irInicioCuentas.getDescripcion());
		Set<SimpleGrantedAuthority> updatedAuthorities = new HashSet<SimpleGrantedAuthority>(oldAuthorities);
		if (updatedAuthorities.remove(authority)) {
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
					SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
					SecurityContextHolder.getContext().getAuthentication().getCredentials(), updatedAuthorities));
		}
	}
}
