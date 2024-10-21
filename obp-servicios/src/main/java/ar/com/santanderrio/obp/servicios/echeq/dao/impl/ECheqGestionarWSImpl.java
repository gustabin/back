package ar.com.santanderrio.obp.servicios.echeq.dao.impl;

import javax.xml.ws.BindingProvider;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.echeq.EcheqService;

/**
 * Obtener el servicio para consultar el ws Echeq.
 *
 */
@Component("echeqGestor")
public class ECheqGestionarWSImpl extends GestionarWSAbstract<EcheqService> {

	/** The Constant ECHEQ_BINDING_ID. */
	private static final String ECHEQ_BINDING_ID = "http://www.w3.org/2003/05/soap/bindings/HTTP/";

	/** The Constant ECHEQ_CODE. */
	private static final String ECHEQ_CODE = "ECHEQ";

	/**
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return ECHEQ_CODE;
	}

	/**
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getBindingId() {
		return ECHEQ_BINDING_ID;
	}

	/**
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#obtenerPort()
	 */
	@Override
	public EcheqService obtenerPort() throws DAOException {
		EcheqService port = super.obtenerPort();
		BindingProvider bindingProvider = (BindingProvider) port;
		bindingProvider.getRequestContext().put("set-jaxb-validation-event-handler", Boolean.FALSE);
		return port;
	}

}
