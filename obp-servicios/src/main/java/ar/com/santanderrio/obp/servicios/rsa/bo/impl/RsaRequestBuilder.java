/*
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.bo.impl;

import ar.com.santanderrio.obp.generated.webservices.rsa.EventData;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Interface RsaRequestBuilder.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 10/11/2016
 */
// TODO cambiar para que arme todo el request y no solo el EventData
public interface RsaRequestBuilder {

	/**
	 * Builds the.
	 *
	 * @param operacionDeRiesgo
	 *            the operacion de riesgo
	 * @return the event data
	 */
	EventData build(RsaDTO operacionDeRiesgo);

}
