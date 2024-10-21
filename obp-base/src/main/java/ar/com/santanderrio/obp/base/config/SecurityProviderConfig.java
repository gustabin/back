/**
 * 
 */
package ar.com.santanderrio.obp.base.config;

import java.security.Provider;
import java.security.Security;

import javax.annotation.PostConstruct;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * 
 * Agregar el provider de Bouncycastle requerido para firmar el token con el que
 * se consulta a Visa.
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
public class SecurityProviderConfig {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityProviderConfig.class);

	/**
	 * Carga el provider en la jvm.
	 */
	@PostConstruct
	public void init() {
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
			Security.addProvider(new BouncyCastleProvider());
			LOGGER.info("Programaticamente agrego Provider de {}", BouncyCastleProvider.PROVIDER_NAME);
		}
		getProviders();
	}

	/**
	 * Mandar al log los providers que estan disponibles.
	 *
	 * @return the providers
	 */
	private void getProviders() {
		LOGGER.info("Listado de {} Providers cargados.", Security.getProviders().length);
		for (Provider provider : Security.getProviders()) {
			LOGGER.info("Provider {}.", provider.getName());
		}
		LOGGER.info("Fin de Providers cargados.");
	}
}
