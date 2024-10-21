/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.conssolupgrade.ConsultaEstadoSolicitudImpl;

/**
 * The Class ConsSolUpgradeGestionarWSImpl.
 */
@Component("gestionConsSolUpgrade")
public class ConsSolUpgradeGestionarWSImpl extends GestionarWSAbstract<ConsultaEstadoSolicitudImpl> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "CONSSOLUPGRADE";
	}

}
