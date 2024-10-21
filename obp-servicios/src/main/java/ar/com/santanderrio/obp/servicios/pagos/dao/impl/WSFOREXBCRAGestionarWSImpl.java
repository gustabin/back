package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.prestamos.WSFOREXBCRASoap;

/**
 * Obtener el servicio para consultar WSFOREXBCRA.
 *
 */
@Component("WSFOREXBCRAGestor")
public class WSFOREXBCRAGestionarWSImpl extends GestionarWSAbstract<WSFOREXBCRASoap> {

	/** The Constant WS_CODE. */
	private static final String WS_CODE = "PRESTAMOSUELDOS.CONTROL.FOREX";

	/**
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return WS_CODE;
	}

}
