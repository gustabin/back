/*
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevopago.entities;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;

/**
 * The Class NuevoPagoAutomatico.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 10/11/2016
 */
public class NuevoPagoAutomatico extends RsaDTO {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new nuevo pago automatico.
	 */
	public NuevoPagoAutomatico() {
		super(OperacionesRSAEnum.NUEVO_PAGO_AUTOMATICO);
	}

}
