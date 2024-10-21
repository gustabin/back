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
 * The Class DataSourceJNDIFactoryTomcatImpl.
 */
@Profile({ Profiles.TOMCAT })
public class DataSourceJNDIFactoryTomcatImpl implements DataSourceJNDIFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceJNDIFactoryImpl.class);

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
			java.util.Hashtable env = new java.util.Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, jndi);
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/" + jndi);
			ctx.close();
		} catch (NamingException e) {
			LOGGER.error("Error al invocar datasource por jndi {}.", jndi, e);
		}
		return dataSource;
	}

}
