/**
 * 
 */
package ar.com.santanderrio.obp.base.security.datasource;

import javax.sql.DataSource;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating DataSourceJNDI objects.
 *
 * @author Jonatan_Bocian
 */
public interface DataSourceJNDIFactory {

	/**
	 * Gets the data source.
	 *
	 * @param jndi
	 *            the jndi
	 * @return the data source
	 */
	// Obtiene un datasource a partir de un nombre
	DataSource getDataSource(String jndi);

}
