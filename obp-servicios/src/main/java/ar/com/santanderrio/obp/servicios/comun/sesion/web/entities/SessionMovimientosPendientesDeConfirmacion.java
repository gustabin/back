/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoDeCuenta;

/**
 * The Class SessionMovimientosPendientesDeConfirmacion.
 *
 * @author B039636
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMovimientosPendientesDeConfirmacion {

	/** The movimiento de cuenta. */
	private List<MovimientoDeCuenta> movimientoDeCuenta;

	/** The cuenta. */
	private String cuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/**
	 * Gets the movimiento de cuenta.
	 *
	 * @return the movimientoDeCuenta
	 */
	public List<MovimientoDeCuenta> getMovimientoDeCuenta() {
		return movimientoDeCuenta;
	}

	/**
	 * Setter para movimiento de cuenta.
	 *
	 * @param movimientoDeCuenta
	 *            the movimientoDeCuenta to set
	 */
	public void setMovimientoDeCuenta(List<MovimientoDeCuenta> movimientoDeCuenta) {
		this.movimientoDeCuenta = movimientoDeCuenta;
	}

	/**
	 * Gets the cantidad de movimientos pendientes de confirmacion.
	 *
	 * @return the cantidad de movimientos pendientes de confirmacion
	 */
	public int getCantidadDeMovimientosPendientesDeConfirmacion() {
		return movimientoDeCuenta == null ? 0 : movimientoDeCuenta.size();
	}

	/**
	 * Gets the cuenta.
	 *
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * Setter para cuenta.
	 *
	 * @param cuenta
	 *            el nuevo cuenta
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Setter para tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            el nuevo tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

}
