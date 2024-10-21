package ar.com.santanderrio.obp.base.security.datasource.impl;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurity;
import ar.com.santanderrio.obp.base.security.datasource.DataSourceJNDIFactory;
import ar.com.santanderrio.obp.base.security.datasource.DataSourceWrapper;
import ar.com.santanderrio.obp.base.security.datasource.DataSourcesFactory;
import ar.com.santanderrio.obp.base.security.datasource.SystemDataSources;

// TODO: Auto-generated Javadoc
/**
 * The Class DataSourcesFactoryImpl.
 */
public class DataSourcesFactoryImpl implements DataSourcesFactory {

	/** The credential security. */
	private CredentialSecurity credentialSecurity;

	/** The data source jndi factory. */
	private DataSourceJNDIFactory dataSourceJNDIFactory;

	/**
	 * Instantiates a new data sources factory impl.
	 *
	 * @param credentialSecurity
	 *            the credential security
	 * @param dataSourceJNDIFactory
	 *            the data source jndi factory
	 */
	public DataSourcesFactoryImpl(CredentialSecurity credentialSecurity, DataSourceJNDIFactory dataSourceJNDIFactory) {
		this.credentialSecurity = credentialSecurity;
		this.dataSourceJNDIFactory = dataSourceJNDIFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.security.datasource.DataSourcesFactory#
	 * create()
	 */
	/*
	 * 
	 * @see
	 * ar.com.santanderrio.base.security.datasource.DataSourcesFactory#create()
	 */
	public SystemDataSources create() {
		Map<String, Credential> map = credentialSecurity.getCredentialMap();

		SystemDataSources datasources = new SystemDataSources();

		Set<Entry<String, Credential>> entries = map.entrySet();
		for (Entry<String, Credential> entry : entries) {
			String dataSourceName = entry.getKey();
			Credential credential = entry.getValue();
			DataSource datasource = generateDatasource(entry.getKey(), credential);
			if (datasource != null) {
				datasources.put(dataSourceName, datasource);
			}
		}
		return datasources;
	}

	/**
	 * Generate datasource.
	 *
	 * @param sistema
	 *            the sistema
	 * @param credential
	 *            the credential
	 * @return the data source
	 */
	public DataSource generateDatasource(String sistema, Credential credential) {
		DataSourceWrapper dataSourceWrapper = null;
		DataSource dataSource = dataSourceJNDIFactory.getDataSource(credential.getJndi());
		if (dataSource != null) {
			dataSourceWrapper = new DataSourceWrapper(dataSource);
			dataSourceWrapper.setUser(credential.getUsuario());
			dataSourceWrapper.setPassword(credential.getPassword());
			dataSourceWrapper.setSistema(sistema);
		}
		return dataSourceWrapper;
	}

}
