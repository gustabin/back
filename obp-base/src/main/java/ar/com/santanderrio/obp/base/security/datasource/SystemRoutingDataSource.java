package ar.com.santanderrio.obp.base.security.datasource;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemRoutingDataSource.
 */
public class SystemRoutingDataSource extends AbstractRoutingDataSource {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemRoutingDataSource.class);

	/** The application context. */
	private ApplicationContext applicationContext;

	/**
	 * Refresh.
	 *
	 * @param targetDatasources
	 *            the target datasources
	 */
	public void refresh(Map<Object, Object> targetDatasources) {
		this.setTargetDataSources(targetDatasources);
		this.afterPropertiesSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#
	 * determineCurrentLookupKey()
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		Object response;
		try {
			SystemRequested sr = applicationContext.getBean(SystemRequested.class);
			response = sr.getSystem();
			LOGGER.info("determineCurrentLookupKey - " + response);

		}
		/*
		 * TODO: Revisar tratamiento de excepciones
		 */
		catch (BeansException e) {
			LOGGER.error("Error por BeansException", e);
			response = null;
		} catch (RuntimeException e) {
			LOGGER.error("Error por RuntimeException", e);
			response = null;
		} catch (Exception e) {
			LOGGER.error("Error por Exception", e);
			response = null;
		}
		return response;
	}

	/**
	 * Gets the application context.
	 *
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Setter para application context.
	 *
	 * @param applicationContext
	 *            the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}