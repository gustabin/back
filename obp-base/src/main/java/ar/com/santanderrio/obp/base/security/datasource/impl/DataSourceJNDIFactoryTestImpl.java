/**
 * 
 */
package ar.com.santanderrio.obp.base.security.datasource.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.datasource.DataSourceJNDIFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSourceJNDIFactoryTestImpl.
 *
 * @author Jonatan_Bocian
 */
@Profile(Profiles.TEST)
public class DataSourceJNDIFactoryTestImpl implements DataSourceJNDIFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceJNDIFactoryTestImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.base.security.datasource.DataSourceJNDIFactory#
	 * getDataSource(java.lang.String)
	 */
	@Override
	public DataSource getDataSource(String jndi) {
		if (jndi == null) {
			return null;
		}
		DataSource dataSource = null;
		try {
			Context initContext = new InitialContext();
			Context webContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) webContext.lookup(jndi);
		} catch (NamingException e) {
			LOGGER.error("Error al invocar datasource por jndi {}", jndi, e);
		}
		return dataSource;
	}
}
