/**
 * 
 */
package ar.com.santanderrio.obp.base.jexl;

import org.apache.commons.jexl3.JexlBuilder;
import org.apache.commons.jexl3.JexlEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// TODO: Auto-generated Javadoc
/**
 * The Class JexlConfig.
 *
 * @author B039636
 */
@Configuration
public class JexlConfig {

	/** The Constant MAXCACHESIZE. */
	private static final int MAXCACHESIZE = 512;

	/**
	 * Gets the jexl engine.
	 *
	 * @return the jexl engine
	 */
	@Bean()
	@Scope("singleton")
	public JexlEngine getJexlEngine() {
		return new JexlBuilder().cache(MAXCACHESIZE).strict(true).silent(false).create();
	}

}
