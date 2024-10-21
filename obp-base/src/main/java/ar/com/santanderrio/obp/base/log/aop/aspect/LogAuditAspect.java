package ar.com.santanderrio.obp.base.log.aop.aspect;

import java.io.IOException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class LogAuditAspect.
 */
@Aspect
@Component
public class LogAuditAspect {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAuditAspect.class);

	/**
	 * Log audit.
	 */
	@Pointcut("@within(ar.com.santanderrio.obp.base.log.aop.annotation.LogAudit) || @annotation(ar.com.santanderrio.obp.base.log.aop.annotation.LogAudit)")
	public void logAudit() {
		// se utiliza para ejecutar el aspecto
	}

	/**
	 * Before.
	 *
	 * @param joinPoint
	 *            the join point
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Before(value = "logAudit()")
	public void before(JoinPoint joinPoint) throws IOException {
		Object[] args = joinPoint.getArgs();
		int i = 0;
		for (Object object : args) {
			String xml = object.toString();
			LOGGER.info(joinPoint.getTarget().getClass() + " - " + joinPoint.getSignature().toShortString() + " - Arg["
					+ i + "]: " + xml);
			i++;
		}
	}

	/**
	 * After.
	 *
	 * @param joinPoint
	 *            the join point
	 * @param returnValue
	 *            the return value
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@AfterReturning(value = "logAudit()", returning = "returnValue")
	public void after(JoinPoint joinPoint, Object returnValue) throws IOException {
		String xml = returnValue.toString();
		LOGGER.info(joinPoint.getTarget().getClass() + " - " + joinPoint.getSignature().toShortString() + " - Return: "
				+ xml);
	}

}
