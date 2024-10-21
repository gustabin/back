/**
 * 
 */
package ar.com.santanderrio.obp.servicios.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManager;

/**
 * Agregar logica para poder filtrar url con el metodo tienePermisoIsban.
 * 
 * El objetivo es a los permisos que tenga el usuario poder agregarle el control
 * segun el estado que tenga el permiso que se este solicitando en la base.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component(value = "customExpressionHandler")
public class CustomWebSecurityExpressionHandler extends DefaultWebSecurityExpressionHandler {
	/** Validar el usuario. */
	private final AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();

	/** The modulo permiso manager. */
	@Autowired
	/** Gestionar los permisos sobre el modulo. */
	private ModuloPermisoManager moduloPermisoManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.access.expression.
	 * DefaultWebSecurityExpressionHandler#createSecurityExpressionRoot(org.
	 * springframework.security.core.Authentication,
	 * org.springframework.security.web.FilterInvocation)
	 */
	@Override
	protected WebSecurityExpressionRoot createSecurityExpressionRoot(Authentication authentication,
			FilterInvocation fi) {
		WebSecurityExpressionRoot expressionRoot = new CustomWebSecurityExpressionRoot(authentication, fi,
				moduloPermisoManager);
		expressionRoot.setTrustResolver(trustResolver);
		expressionRoot.setRoleHierarchy(getRoleHierarchy());
		expressionRoot.setPermissionEvaluator(getPermissionEvaluator());
		// expressionRoot.setDefaultRolePrefix("ir");
		return expressionRoot;
	}
}