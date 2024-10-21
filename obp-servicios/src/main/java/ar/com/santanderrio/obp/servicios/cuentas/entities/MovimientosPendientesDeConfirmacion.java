/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.entities;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

/**
 * The Class MovimientosPendientesDeConfirmacion.
 */
public class MovimientosPendientesDeConfirmacion extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The movimientos pendientes de confirmacion. */
	private List<MovimientoDeCuenta> movimientosPendientesDeConfirmacion;

	/** The cuenta. */
	private String cuenta;

	/** The tipo cuenta. */
	private String tipoCuenta;

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

	/**
	 * Gets the movimientos pendientes de confirmacion.
	 *
	 * @return the movimientos pendientes de confirmacion
	 */
	public List<MovimientoDeCuenta> getMovimientosPendientesDeConfirmacion() {
		if (movimientosPendientesDeConfirmacion == null) {
			movimientosPendientesDeConfirmacion = new ArrayList<MovimientoDeCuenta>();
		}
		return movimientosPendientesDeConfirmacion;
	}

	/**
	 * Setter para movimientos pendientes de confirmacion.
	 *
	 * @param movimientosPendientesDeConfirmacion
	 *            el nuevo movimientos pendientes de confirmacion
	 */
	public void setMovimientosPendientesDeConfirmacion(List<MovimientoDeCuenta> movimientosPendientesDeConfirmacion) {
		this.movimientosPendientesDeConfirmacion = movimientosPendientesDeConfirmacion;
	}

	/**
	 * Gets the cantidad de movimientos pendientes de confirmacion.
	 *
	 * @return the cantidad de movimientos pendientes de confirmacion
	 */
	public int getCantidadDeMovimientosPendientesDeConfirmacion() {
		return getMovimientosPendientesDeConfirmacion().size();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cuenta);
		hcb.append(tipoCuenta);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj.getClass().equals(this.getClass()))) {
			return false;
		}
		MovimientosPendientesDeConfirmacion other = (MovimientosPendientesDeConfirmacion) obj;
		return new EqualsBuilder().append(cuenta, other.cuenta).append(tipoCuenta, other.tipoCuenta).isEquals();
	}

}
