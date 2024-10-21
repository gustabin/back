/**
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dao.impl;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.generated.webservices.billetera.admcbu.AdministrarCBUBancosPortType;

/**
 * The Class AdministrarCbuGestionarWSImpl.
 *
 */
@Component("gestionAdministrarCBU")
public class AdministrarCbuGestionarWSImpl extends AbstractBilleteraGestionarWS<AdministrarCBUBancosPortType> {

	/**
	 * Gets the codigo WS.
	 *
	 * @return the codigo WS
	 * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "BILLETERA.ADMINISTRARCBU";
	}
}
