/**
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.ExpressionBasedAnnotationAttributeFactory;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import ar.com.santanderrio.obp.servicios.seguridad.CustomPermissionEvaluator;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPrePostAnnotationSecurityMetadataSource;

/**
 * Permite poder agregar logica a los metodos CustomPreAuthorize. Ej tomado de
 * http://www.baeldung.com/spring-security-create-new-custom-security-expression
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

	/**
	 * Inyectar el Permission Evaluator.
	 *
	 * @return the method security expression handler
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.method.configuration.
	 * GlobalMethodSecurityConfiguration#createExpressionHandler()
	 */
	@Override
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(new CustomPermissionEvaluator());
		return expressionHandler;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.method.configuration.
	 * GlobalMethodSecurityConfiguration#customMethodSecurityMetadataSource()
	 */
	@Override
	protected MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
		ExpressionBasedAnnotationAttributeFactory attributeFactory = new ExpressionBasedAnnotationAttributeFactory(
				getExpressionHandler());

		return new CustomPrePostAnnotationSecurityMetadataSource(attributeFactory);
	}
}
