package ar.com.santanderrio.obp.base.log.cxf.interceptor;

import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class RSALoggingInInterceptor.
 */
@Component
public class RSALoggingInInterceptor extends RSALoggingInterceptor {

	/**
	 * Instantiates a new RSA logging in interceptor.
	 */
	public RSALoggingInInterceptor() {
		super(Phase.RECEIVE);
	}

}
