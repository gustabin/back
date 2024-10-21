/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.comex.consultas.tempuri.IConsultasService;

/**
 * Obtener el servicio para consultar el ws de Consultas para Comex.
 *
 */
@Component("comexConsultasGestor")
public class ComexConsultasGestionarWSImpl extends GestionarWSAbstract<IConsultasService> {

	/** The Constant TRANSFEXT_CONSULTAS_CODE. */
	private static final String TRANSFEXT_CONSULTAS_CODE = "TRANSFEXT.CONSULTAS";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return TRANSFEXT_CONSULTAS_CODE;
	}

}
