/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.entity;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class RescateFondoRsa.
 */
public class RescateFondoRsa extends RsaDTO {

	/**
	 * Instantiates a new rescate fondo rsa.
	 */
	public RescateFondoRsa() {
		super(OperacionesRSAEnum.RESCATE_FONDO);
	}

}
