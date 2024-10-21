/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class DatosConfirmacionFinanciacionTarjetaDTO.
 */
public class DatosConfirmacionFinanciacionTarjetaDTO extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nro cuenta producto seleccionado. */
	private String nroCuentaProductoSeleccionado;

	/** The tarjeta. */
	private Cuenta tarjeta;

	/** The monto financiacion. */
	private BigDecimal montoFinanciacion;

	/** The cuotas. */
	private String cuotas;

	/**
	 * Gets the tarjeta.
	 *
	 * @return the tarjeta
	 */
	public Cuenta getTarjeta() {
		return tarjeta;
	}

	/**
	 * Sets the tarjeta.
	 *
	 * @param tarjeta
	 *            the new tarjeta
	 */
	public void setTarjeta(Cuenta tarjeta) {
		this.tarjeta = tarjeta;
	}

	/**
	 * Gets the monto financiacion.
	 *
	 * @return the monto financiacion
	 */
	public BigDecimal getMontoFinanciacion() {
		return montoFinanciacion;
	}

	/**
	 * Sets the monto financiacion.
	 *
	 * @param montoFinanciacion
	 *            the new monto financiacion
	 */
	public void setMontoFinanciacion(BigDecimal montoFinanciacion) {
		this.montoFinanciacion = montoFinanciacion;
	}

	/**
	 * Gets the cuotas.
	 *
	 * @return the cuotas
	 */
	public String getCuotas() {
		return cuotas;
	}

	/**
	 * Sets the cuotas.
	 *
	 * @param cuotas
	 *            the new cuotas
	 */
	public void setCuotas(String cuotas) {
		this.cuotas = cuotas;
	}

	/**
	 * Gets the nro cuenta producto seleccionado.
	 *
	 * @return the nroCuentaProductoSeleccionado
	 */
	public String getNroCuentaProductoSeleccionado() {
		return nroCuentaProductoSeleccionado;
	}

	/**
	 * Sets the nro cuenta producto seleccionado.
	 *
	 * @param nroCuentaProductoSeleccionado
	 *            the nroCuentaProductoSeleccionado to set
	 */
	public void setNroCuentaProductoSeleccionado(String nroCuentaProductoSeleccionado) {
		this.nroCuentaProductoSeleccionado = nroCuentaProductoSeleccionado;
	}

}
