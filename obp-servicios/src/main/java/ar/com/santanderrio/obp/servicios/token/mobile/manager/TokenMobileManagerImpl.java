/*
 * 
 */
package ar.com.santanderrio.obp.servicios.token.mobile.manager;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.token.mobile.bo.TokenMobileBO;
import ar.com.santanderrio.obp.servicios.token.mobile.entities.TokenMobile;

/**
 * The Class TokenMobileManagerImpl.
 */
@Component
public class TokenMobileManagerImpl implements TokenMobileManager {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenMobileManagerImpl.class);

	/** The token mobile BO. */
	@Autowired
	private TokenMobileBO tokenMobileBO;

	/** The segundos entre consultas. */
	@Value("${TOKEN.MOBILE.CANTIDAD.SEGUNDOS.REINTENTOS}")
	private String segundosEntreConsultas;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.servicios.token.mobile.manager.TokenMobileManager
	 * #consultarTokenMobile(java.lang.String)
	 */
	@Override
	public Respuesta<TokenMobile> consultarTokenMobile(String nup) {

		Respuesta<TokenMobile> respuesta = this.tokenMobileBO.consultarTokenMobile(nup);

		if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {
			return respuesta;
		}
		esperarLaSiguienteInvocacion();

		return this.tokenMobileBO.consultarTokenMobile(nup);

	}

	/**
	 * Esperar la siguiente invocacion.
	 */
	private void esperarLaSiguienteInvocacion() {
		Integer segundosEntreReintentos = NumberUtils.INTEGER_ONE;
		if (NumberUtils.isNumber(this.segundosEntreConsultas)) {
			segundosEntreReintentos = Integer.parseInt(this.segundosEntreConsultas);
		}

		try {
			Thread.sleep(segundosEntreReintentos * 1000);
		} catch (InterruptedException e) {

			LOGGER.error("error" + e);
		}
	}

}
