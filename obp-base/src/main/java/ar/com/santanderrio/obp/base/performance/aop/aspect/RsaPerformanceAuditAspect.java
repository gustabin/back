package ar.com.santanderrio.obp.base.performance.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.exceptions.AspectException;

// TODO: Auto-generated Javadoc
/**
 * The Class RsaPerformanceAuditAspect.
 */
@SuppressWarnings("PMD.AvoidCatchingThrowable")
@Aspect
@Component
public class RsaPerformanceAuditAspect {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(RsaPerformanceAuditAspect.class);

	/** The Constant RSA_TIEMPO_NORMAL. */
	private static final int RSA_TIEMPO_NORMAL = 1000;

	/** The Constant RSA_TIEMPO_LARGO. */
	private static final int RSA_TIEMPO_LARGO = 2000;

	/** The Constant RSA_TIEMPO_MUY_LARGO. */
	private static final int RSA_TIEMPO_MUY_LARGO = 3000;

	/**
	 * Metodo rsa.
	 */
	@Pointcut("execution(* ar.com.santanderrio.obp.base.rsa.dao..*.*(..))")
	public void metodoRsa() {
		// Es solo para realizar el pointcut del aspecto
	}

	/**
	 * Medir tiempo rsa.
	 *
	 * @param point
	 *            the point
	 * @return the object
	 * @throws AspectException
	 *             the aspect exception
	 */
	@Around(value = "metodoRsa()")
	public Object medirTiempoRsa(ProceedingJoinPoint point) throws AspectException {
		long start = System.nanoTime();
		Object result;
		try {
			result = point.proceed();
		} catch (Throwable e) {
			throw new AspectException(e);
		}
		long time = System.nanoTime() - start;

		if (time >= RSA_TIEMPO_MUY_LARGO) {
			LOGGER.warn("**WARNING 3 SEG POOL = " + time);
		} else if (time >= RSA_TIEMPO_LARGO) {
			LOGGER.warn("**WARNING 2 a 3 SEG POOL = " + time);
		} else if (time >= RSA_TIEMPO_NORMAL) {
			LOGGER.warn("**WARNING 1 a 2 SEG POOL = " + time);
		}

		return result;
	}
}
