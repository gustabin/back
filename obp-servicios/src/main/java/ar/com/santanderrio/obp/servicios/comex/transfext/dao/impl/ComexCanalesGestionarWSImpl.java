/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.comex.canalestrf.comex.ICanalesTrfService;

/**
 * Obtener el servicio para consultar el ws de Canales para Comex.
 *
 */
@Component("comexCanalesGestor")
public class ComexCanalesGestionarWSImpl extends GestionarWSAbstract<ICanalesTrfService> {

	/** The Constant TRANSFEXT_CANALES_CODE. */
	private static final String TRANSFEXT_CANALES_CODE = "TRANSFEXT.CANALES";

	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ComexCanalesGestionarWSImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return TRANSFEXT_CANALES_CODE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#
	 * asignarInterceptors(org.apache.cxf.endpoint.Client)
	 */
	@Override
	protected void asignarInterceptors(Client cliente) {
		int limite = 100 * 1024;
		cliente.getOutInterceptors().clear();
		LOGGER.debug("Interceptor de {} de Out en fase {}", getCodigoWS(), Phase.PRE_STREAM);
		LoggingInInterceptor loggingInInterceptor = new LoggingInInterceptor(Phase.PRE_STREAM, limite);
		loggingInInterceptor.setPrettyLogging(true);
		cliente.getOutInterceptors().add(loggingInInterceptor);
		cliente.getInInterceptors().clear();
		LOGGER.debug("Interceptor de {} de In en fase {}", getCodigoWS(), Phase.RECEIVE);
		LoggingOutInterceptor loggingOutInterceptor = new LoggingOutInterceptor(Phase.RECEIVE);
		loggingOutInterceptor.setLimit(limite);
		loggingOutInterceptor.setPrettyLogging(true);
		cliente.getInInterceptors().add(loggingOutInterceptor);
	}

}
