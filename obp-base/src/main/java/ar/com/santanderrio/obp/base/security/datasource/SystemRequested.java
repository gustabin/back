package ar.com.santanderrio.obp.base.security.datasource;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemRequested.
 */
@Component
@Scope(value = "systemrequested", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SystemRequested {

	/** The system. */
	private String system;

	/**
	 * Gets the system.
	 *
	 * @return the system
	 */
	public String getSystem() {
		return system;
	}

	/**
	 * Setter para system.
	 *
	 * @param system
	 *            the system to set
	 */
	public void setSystem(String system) {
		this.system = system;
	}

}
