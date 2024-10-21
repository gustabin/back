package ar.com.santanderrio.obp.base.performance.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.AspectException;

// TODO: Auto-generated Javadoc
/**
 * The Class PerformanceAuditAspect.
 */
@SuppressWarnings("PMD.AvoidCatchingThrowable")
@Aspect
@Component
public class PerformanceAuditAspect {

	/** The Constant PMD_AVOID_CATCHING_THROWABLE. */
	static final String PMD_AVOID_CATCHING_THROWABLE = "PMD.AvoidCatchingThrowable";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceAuditAspect.class);

	/**
	 * Around.
	 *
	 * @param point
	 *            the point
	 * @return the object
	 * @throws AspectException
	 *             the aspect exception
	 */
	@Around(value = "@within(ar.com.santanderrio.obp.base.performance.aop.annotation.PerformanceAudit) || @annotation(ar.com.santanderrio.obp.base.performance.aop.annotation.PerformanceAudit)")
	public Object around(ProceedingJoinPoint point) throws AspectException {
		long start = System.nanoTime();
		Object result;
		try {
			result = point.proceed();
		} catch (Throwable e) {
			throw new AspectException(e);
		}
		long time = System.nanoTime() - start;
		LOGGER.info("PERFORMANCE TEST: " + time);
		return result;
	}
}
