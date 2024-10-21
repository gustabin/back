/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.visa.planv.PlanVService;

/**
 * Obtener el servicio para consultar el ws de PLANV.
 *
 * @author sergio.e.goldentair
 *
 */
@Component("planVGestor")
public class ConsultaFinanciacionGestionarWSImpl extends GestionarWSAbstract<PlanVService> {

	/** The Constant CONSULTA_FINANCIACION_CODE. */
	private static final String CONSULTA_FINANCIACION_CODE = "PLANV";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return CONSULTA_FINANCIACION_CODE;
	}

}
