package ar.com.santanderrio.obp.base.security.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.context.ThreadScope;
import ar.com.santanderrio.obp.base.security.aop.annotation.TargetSystem;
import ar.com.santanderrio.obp.base.security.datasource.SystemRequested;

// TODO: Auto-generated Javadoc
/**
 * The Class TargetSystemAspect.
 */
@Aspect
@Component
public class TargetSystemAspect {

	/** The system requested. */
	@Autowired
	private SystemRequested systemRequested;

	/** The systemrequested scope. */
	@Autowired
	@Qualifier("systemrequestedScope")
	private ThreadScope systemrequestedScope;

	/**
	 * Before.
	 *
	 * @param joinPoint
	 *            the join point
	 * @param targetSystem
	 *            the target system
	 */
	@Before(value = "@within(targetSystem) || @annotation(targetSystem)")
	public void before(JoinPoint joinPoint, TargetSystem targetSystem) {
		TargetSystem realTargetSystem = targetSystem;
		if (realTargetSystem == null) {
			Class<?> clazz = joinPoint.getTarget().getClass();
			realTargetSystem = clazz.getAnnotation(TargetSystem.class);
		}
		if (realTargetSystem.value() != null) {
			systemRequested.setSystem(realTargetSystem.value().getNombreTarget());
		}
	}

	/**
	 * After.
	 *
	 * @param joinPoint
	 *            the join point
	 * @param targetSystem
	 *            the target system
	 */
	@After(value = "@within(targetSystem) || @annotation(targetSystem)")
	public void after(JoinPoint joinPoint, TargetSystem targetSystem) {
		systemRequested.setSystem(null);
		systemrequestedScope.cleanUpThreadScopedBeans();
	}

}
