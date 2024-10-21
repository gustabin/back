/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguridad;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import ar.com.santanderrio.obp.base.context.ContextHolder;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManager;

/**
 * Agregar en los metodos seguridad (uso de SPEL). El parametro Authentication
 * authentication de ambos metodos los inyecta el framework, mientras los demas
 * dependera del metodo sobrecargado que se invoque.
 * 
 * @author sergio.e.goldentair
 *
 */
public class CustomPermissionEvaluator implements PermissionEvaluator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.PermissionEvaluator#hasPermission(org
	 * .springframework.security.core.Authentication, java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if ((authentication == null) || !(permission instanceof String)) {
			return false;
		}
		return tienePrivilegio(authentication, permission.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.access.PermissionEvaluator#hasPermission(org
	 * .springframework.security.core.Authentication, java.io.Serializable,
	 * java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return hasPermission(authentication, null, permission);
	}

	/**
	 * Tiene privilegio.
	 *
	 * @param auth
	 *            the auth
	 * @param permission
	 *            the permission
	 * @return true, if successful
	 */
	private boolean tienePrivilegio(Authentication auth, String permission) {
		ModuloPermisoManager moduloPermisoManager = ContextHolder.getContext().getBean(ModuloPermisoManager.class);
		String[] permisos = permission.split(",");
		for (String permiso : permisos) { 
			AccionController accion = AccionController.obtenerEnumPorDescripcion(permiso);
			ModuloPermiso moduloPermiso = moduloPermisoManager.obtenerModuloPermiso(accion);
			Collection<? extends GrantedAuthority> userAuthorities = auth.getAuthorities();
			for (GrantedAuthority grantedAuthority : userAuthorities) {
				if (grantedAuthority.getAuthority().equals(permiso) && moduloPermiso.tienePermisoDeVisibilidad()) {
					return true; 
				}
			}
		}
		return false;
	}
}
