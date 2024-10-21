package ar.com.santanderrio.obp.base.security.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import ar.com.santanderrio.obp.base.security.credential.DataBase;

// TODO: Auto-generated Javadoc
/**
 * The Interface TargetSystem.
 * 
 * @see ar.com.santanderrio.obp.base.security.aop.aspect.TargetSystemAspect
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
public abstract @interface TargetSystem {

	/**
	 * Value.
	 *
	 * @return the string
	 */
	DataBase value();
}
