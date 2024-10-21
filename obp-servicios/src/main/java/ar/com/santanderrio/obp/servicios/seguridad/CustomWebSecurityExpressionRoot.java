/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguridad;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManager;

/**
 * Agregar el metodo tienePermisoIsban para validar si sumado a los permisos
 * obtenidos de los productos del cliente el modulo solicitado esta habilitado.
 * 
 * @author sergio.e.goldentair
 *
 */
public class CustomWebSecurityExpressionRoot extends WebSecurityExpressionRoot {

	/** The modulo permiso manager. */
	private ModuloPermisoManager moduloPermisoManager;

	/**
	 * Instantiates a new custom web security expression root.
	 *
	 * @param a
	 *            the a
	 * @param fi
	 *            the fi
	 * @param moduloPermisoManager
	 *            the modulo permiso manager
	 */
	public CustomWebSecurityExpressionRoot(Authentication a, FilterInvocation fi,
			ModuloPermisoManager moduloPermisoManager) {
		super(a, fi);
		this.moduloPermisoManager = moduloPermisoManager;
	}

	/**
	 * Valida si los roles que tiene el usuario junto a los permisos sobre los
	 * modulos permiten que continue con la operacion. Si no tiene permiso de
	 * visibilidad no se puede acceder a la funcionalidad. Si tiene permiso de
	 * visibilidad y es topbar o home y segun los productos tiene ese grant se
	 * permite acceder a la funcionalidad en caso contrario se rechaza.
	 *
	 * @param role
	 *            the role
	 * @return true, if successful
	 */
	public boolean tienePermisoIsban(String role) {
		AccionController accion = AccionController.obtenerEnumPorDescripcion(role);
		ModuloPermiso moduloPermiso = moduloPermisoManager.obtenerModuloPermiso(accion);
		Collection<? extends GrantedAuthority> userAuthorities = authentication.getAuthorities();
		if (moduloPermiso.tienePermisoDeVisibilidad()) {
			if (moduloPermiso.isMenu()) {
				for (GrantedAuthority grantedAuthority : userAuthorities) {
					if (grantedAuthority.getAuthority().equals(role)) {
						return true;
					}
				}
			} else {
				return true;
			}
		}
		return false;
	}

}
