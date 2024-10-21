package ar.com.santanderrio.obp.base.log.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class LogAuditAspect.
 */
@Aspect
@Component
public class ManagerAuditAspect extends BaseAuditAspect {
	/**
	 * Log audit.
	 */
	@Pointcut("execution(public * ar.com.santanderrio.obp.*.*.*.*Manager+.* (..))")
	public void logAudit() {
		// No tiene implementacion ya que se utiliza pointcut para el logue por
		// aspectos.
	}

}
