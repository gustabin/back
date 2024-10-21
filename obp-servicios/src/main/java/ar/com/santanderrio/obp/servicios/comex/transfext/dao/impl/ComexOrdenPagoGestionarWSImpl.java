package ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.comex.canalesorpago.ICanalesOrPagoService;

@Component("comexOrdenPagoGestor")
public class ComexOrdenPagoGestionarWSImpl extends GestionarWSAbstract<ICanalesOrPagoService> {

	/** The Constant TRANSFEXT_ORPAGO_CODE. */
	private static final String TRANSFEXT_ORDENPAGO_CODE = "TRANSFEXT.ORDENPAGO";

	@Override
	public String getCodigoWS() {
		return TRANSFEXT_ORDENPAGO_CODE;
	}

}
