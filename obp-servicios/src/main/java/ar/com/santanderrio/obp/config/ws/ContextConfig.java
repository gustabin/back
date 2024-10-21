/**
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Temporal hasta lograr separar los paquetes y poder mediante wildcard
 * scanearlos por separado.
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
@ComponentScan(basePackages = { "ar.com.santanderrio.obp.base", "ar.com.santanderrio.obp.servicios" })
public class ContextConfig {

}
