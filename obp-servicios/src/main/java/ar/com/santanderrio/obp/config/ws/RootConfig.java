/*
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ar.com.santanderrio.obp.config.executor.ExecutorConfig;
import ar.com.santanderrio.obp.config.mail.MailConfig;

/**
 * Clase inicial para la configuracion de la app.
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
@Import({ ContextConfig.class, CxfSpringConfig.class, MethodSecurityConfig.class, SecurityConfig.class,
		CacheConfig.class, ExecutorConfig.class, MailConfig.class, SucursalesConfig.class, FondosConfig.class, PrestamosConfig.class })
public class RootConfig {

}