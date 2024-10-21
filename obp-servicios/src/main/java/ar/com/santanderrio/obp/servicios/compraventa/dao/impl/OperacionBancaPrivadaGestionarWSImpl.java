package ar.com.santanderrio.obp.servicios.compraventa.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.bpriv.IBancaPrivadaSucursalSvc;

/**
 * The Class OperacionBancaPrivadaGestionarWSImpl.
 */
@Component("gestionOperacionBP")
public class OperacionBancaPrivadaGestionarWSImpl extends GestionarWSAbstract<IBancaPrivadaSucursalSvc> {

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
