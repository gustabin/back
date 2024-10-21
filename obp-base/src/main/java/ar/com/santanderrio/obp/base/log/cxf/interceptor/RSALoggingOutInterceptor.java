package ar.com.santanderrio.obp.base.log.cxf.interceptor;

import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class RSALoggingOutInterceptor.
 */
@Component
public class RSALoggingOutInterceptor extends RSALoggingInterceptor {

	/**
	 * Instantiates a new RSA logging out interceptor.
	 */
	public RSALoggingOutInterceptor() {
		super(Phase.PRE_STREAM);
	}

}
