package ar.com.santanderrio.obp.base.log.spring;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ar.com.santanderrio.obp.base.context.ThreadScope;

// TODO: Auto-generated Javadoc
/**
 * The Class ScopeConfig.
 */
@Configuration
public class ScopeConfig {

	/**
	 * Asyncthread scope.
	 *
	 * @return the thread scope
	 */
	@Bean(name = "asyncthreadScope")
	public ThreadScope asyncthreadScope() {
		return new ThreadScope();
	}

	/**
	 * Systemrequested scope.
	 *
	 * @return the thread scope
	 */
	@Bean(name = "systemrequestedScope")
	public ThreadScope systemrequestedScope() {
		return new ThreadScope();
	}

	/**
	 * Custom scope configurer.
	 *
	 * @param systemrequestedScope
	 *            the systemrequested scope
	 * @param asyncthreadScope
	 *            the asyncthread scope
	 * @return the custom scope configurer
	 */
	@Bean
	public CustomScopeConfigurer customScopeConfigurer(
			@Qualifier("systemrequestedScope") ThreadScope systemrequestedScope,
			@Qualifier("asyncthreadScope") ThreadScope asyncthreadScope) {
		CustomScopeConfigurer scopeConfigurer = new CustomScopeConfigurer();
		HashMap<String, Object> scopes = new HashMap<String, Object>();
		scopes.put("asyncthread", asyncthreadScope);
		scopes.put("systemrequested", systemrequestedScope);
		scopeConfigurer.setScopes(scopes);
		return scopeConfigurer;
	}
}
