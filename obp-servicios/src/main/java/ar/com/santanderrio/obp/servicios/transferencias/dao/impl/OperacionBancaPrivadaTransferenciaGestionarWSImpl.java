/**
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.bpriv.IBancaPrivadaSucursalSvc;

/**
 * The Class OperacionBancaPrivadaTransferenciaGestionarWSImpl.
 */
@Component("gestionOperacionBPTransferencia")
public class OperacionBancaPrivadaTransferenciaGestionarWSImpl extends GestionarWSAbstract<IBancaPrivadaSucursalSvc>{

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "BPRIVWS";
	}
	
}
