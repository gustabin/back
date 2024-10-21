/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.token;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// TODO: Auto-generated Javadoc
/**
 * Indicar el texto que debe preceder al atributo. Example:
 * 
 * <pre>
 * <code>
 * &#64;StringTokenFormat("Tipo-Documento=")
 * private String tipoDoc; //DNI
 * </code>
 * </pre>
 * 
 * Output: "Tipo-Document=DNI\\r\\n"
 * 
 * @author sergio.e.goldentair
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface StringTokenFormat {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	String value() default "";
}
