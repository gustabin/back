/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosValoresPendientes;

/**
 * The Class SessionMovimientosValores.
 *
 * @author B039636
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMovimientosValores {

	/** The movimientos valores pendientes. */
	private MovimientosValoresPendientes movimientosValoresPendientes;

	/**
	 * Gets the movimientos valores pendientes.
	 *
	 * @return the movimientosValoresPendientes
	 */
	public MovimientosValoresPendientes getMovimientosValoresPendientes() {
		return movimientosValoresPendientes;
	}

	/**
	 * Setter para movimientos valores pendientes.
	 *
	 * @param movimientosValoresPendientes
	 *            the movimientosValoresPendientes to set
	 */
	public void setMovimientosValoresPendientes(MovimientosValoresPendientes movimientosValoresPendientes) {
		this.movimientosValoresPendientes = movimientosValoresPendientes;
	}

}
