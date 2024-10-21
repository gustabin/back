/*
 * 
 */
package ar.com.santanderrio.obp.servicios.crm.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.crm.AlertasCrmSoap;

/**
 * Obtener el servicio para consultar el ws de Alertas CRM.
 * 
 * @author B025331
 *
 */
@Component("alertasCRM")
public class AlertasCRMGestionarWSImpl extends GestionarWSAbstract<AlertasCrmSoap> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "CRM";
	}

}
