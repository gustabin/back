package ar.com.santanderrio.obp.base.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import ar.com.santanderrio.obp.base.iatx.helpers.IatxInicial;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurity;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.credential.impl.CredentialSecurityFactoryDAOImpl;
import ar.com.santanderrio.obp.base.security.datasource.DataSourceJNDIFactory;
import ar.com.santanderrio.obp.base.security.datasource.DataSourcesFactory;
import ar.com.santanderrio.obp.base.security.datasource.SystemDataSources;
import ar.com.santanderrio.obp.base.security.datasource.SystemRoutingDataSource;
import ar.com.santanderrio.obp.base.security.datasource.impl.DataSourceJNDIFactoryImpl;
import ar.com.santanderrio.obp.base.security.datasource.impl.DataSourceJNDIFactoryTestImpl;
import ar.com.santanderrio.obp.base.security.datasource.impl.DataSourceJNDIFactoryTomcatImpl;
import ar.com.santanderrio.obp.base.security.datasource.impl.DataSourcesFactoryImpl;

// TODO: Auto-generated Javadoc
/**
 * Clase de configuracion por annotations.
 *
 * @author Gabriel_Vigano
 */

@Configuration
public class BackEndSecurityConfig {

    /** The seguridadds. */
    @Value("${DB.SEGURIDAD.DS}")
    private String seguridadds;

    /**
     * Data source.
     *
     * @param dataSourceJNDIFactory
     *            the data source jndi factory
     * @return the data source
     */
    @Bean(name = "seguridad")
    public DataSource dataSource(DataSourceJNDIFactory dataSourceJNDIFactory) {
        return dataSourceJNDIFactory.getDataSource(seguridadds);
    }

    /**
     * Data source jndi factory impl.
     *
     * @return the data source jndi factory impl
     */
    @Bean
    @Profile({ Profiles.PRODUCTION, Profiles.HOMO, Profiles.DESA, Profiles.MOCK })
    public DataSourceJNDIFactoryImpl dataSourceJNDIFactoryImpl() {
        return new DataSourceJNDIFactoryImpl();
    }

    /**
     * Data source jndi factory test impl.
     *
     * @return the data source jndi factory test impl
     */
    @Bean
    @Profile({ Profiles.TEST, Profiles.DIAGNOSTIC })
    public DataSourceJNDIFactoryTestImpl dataSourceJNDIFactoryTestImpl() {
        return new DataSourceJNDIFactoryTestImpl();
    }

    /**
     * Data source jndi factory test impl.
     *
     * @return the data source jndi factory test impl
     */
    @Bean
    @Profile({ Profiles.TOMCAT })
    public DataSourceJNDIFactory dataSourceJNDIFactoryTomcatImpl() {
        return new DataSourceJNDIFactoryTomcatImpl();
    }

    /**
     * Property map.
     *
     * @return the property map
     */
    @Bean
    public PropertyMap propertyMap() {
        return new PropertyMap();
    }

    /**
     * Gets the credential security factory.
     *
     * @param dataSource
     *            the data source
     * @param propertyMap
     *            the property map
     * @return the credential security factory
     */
    @Bean
    public CredentialSecurityFactory getCredentialSecurityFactory(@Qualifier("seguridad") DataSource dataSource,
            PropertyMap propertyMap) {
        return new CredentialSecurityFactoryDAOImpl(dataSource);
    }

    /**
     * Gets the credential security.
     *
     * @param credentialSecurityFactory
     *            the credential security factory
     * @return the credential security
     */
    @Bean
    public CredentialSecurity getCredentialSecurity(CredentialSecurityFactory credentialSecurityFactory) {
        return credentialSecurityFactory.create();
    }

    /**
     * Data sources factory impl.
     *
     * @param credentialSecurity
     *            the credential security
     * @param dataSourceJNDIFactory
     *            the data source jndi factory
     * @return the data sources factory
     */
    @Bean(name = "dataSourcesFactory")
    public DataSourcesFactory dataSourcesFactoryImpl(CredentialSecurity credentialSecurity,
            DataSourceJNDIFactory dataSourceJNDIFactory) {
        return new DataSourcesFactoryImpl(credentialSecurity, dataSourceJNDIFactory);
    }

    /**
     * Data sources factory.
     *
     * @param dataSourcesFactory
     *            the data sources factory
     * @return the system data sources
     */
    @Bean(name = "dataSources")
    public SystemDataSources dataSourcesFactory(DataSourcesFactory dataSourcesFactory) {
        return dataSourcesFactory.create();
    }

    /**
     * System routing data source.
     *
     * @param dataSources
     *            the data sources
     * @param appContext
     *            the app context
     * @return the system routing data source
     */
    @Bean(name = "dataSource")
    public SystemRoutingDataSource systemRoutingDataSource(@Qualifier("dataSources") SystemDataSources dataSources,
            ApplicationContext appContext) {
        SystemRoutingDataSource systemRoutingDataSource = new SystemRoutingDataSource();
        systemRoutingDataSource.setTargetDataSources(dataSources);
        systemRoutingDataSource.setApplicationContext(appContext);
        return systemRoutingDataSource;
    }

    /**
     * Levantar la credencial RACF de sistema para la aplicacion.
     *
     * @param dataSource the data source
     * @param propertyMap the property map
     * @param credentialId the credential id
     * @return the iatx inicial
     */
    @Bean(name = "iatxInicial")
    public IatxInicial getIatxInicial(@Qualifier("seguridad") DataSource dataSource, PropertyMap propertyMap,
            @Value("${DB.RACFINICIAL.ID}") String credentialId) {
        return new IatxInicial(getCredentialSecurityFactory(dataSource, propertyMap), credentialId);
    }
}
