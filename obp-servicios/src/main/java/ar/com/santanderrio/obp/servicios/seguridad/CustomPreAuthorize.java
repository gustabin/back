/*
 * 
 */
package ar.com.santanderrio.obp.servicios.seguridad;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;

/**
 * The Interface CustomPreAuthorize.
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CustomPreAuthorize {

	/**
	 * Value.
	 *
	 * @return the accion controller
	 */
	AccionController[] value();
}
