/**
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.generated.webservices.billetera.admclave.AdministrarClaveBancosPortType;

/**
 * The Class AdministrarClaveGestionarWSImpl.
 *
 */
@Component("gestionAdministrarClave")
public class AdministrarClaveGestionarWSImpl extends AbstractBilleteraGestionarWS<AdministrarClaveBancosPortType> {

	/**
	 * Gets the codigo WS.
	 *
	 * @return the codigo WS
	 * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "BILLETERA.ADMINISTRARCLAVE";
	}
}
