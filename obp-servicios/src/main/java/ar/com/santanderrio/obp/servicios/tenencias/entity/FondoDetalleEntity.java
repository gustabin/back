/**
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

import ar.com.santanderrio.obp.servicios.tenencias.view.ResumenCuentaInversionesView;

/**
 * The Class FondoDetalleEntity.
 */
public class FondoDetalleEntity {

	/** The cod fondo. */
	private String codFondo;

	/** The descripcion. */
	private String descripcion;

	/** The divisa. */
	private String divisa;

	/** The saldo. */
	private String saldo;

	/** The detalles. */
	private List<ResumenCuentaInversionesView> detalles;

	/**
	 * Gets the cod fondo.
	 *
	 * @return the codFondo
	 */
	public String getCodFondo() {
		return codFondo;
	}

	/**
	 * Sets the cod fondo.
	 *
	 * @param codFondo
	 *            the codFondo to set
	 */
	public void setCodFondo(String codFondo) {
		this.codFondo = codFondo;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the divisa.
	 *
	 * @return the divisa
	 */
	public String getDivisa() {
		return divisa;
	}

	/**
	 * Sets the divisa.
	 *
	 * @param divisa
	 *            the divisa to set
	 */
	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}

	/**
	 * Gets the saldo.
	 *
	 * @return the saldo
	 */
	public String getSaldo() {
		return saldo;
	}

	/**
	 * Sets the saldo.
	 *
	 * @param saldo
	 *            the saldo to set
	 */
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	/**
	 * Gets the detalles.
	 *
	 * @return the detalles
	 */
	public List<ResumenCuentaInversionesView> getDetalles() {
		return detalles;
	}

	/**
	 * Sets the detalles.
	 *
	 * @param detalles
	 *            the detalles to set
	 */
	public void setDetalles(List<ResumenCuentaInversionesView> detalles) {
		this.detalles = detalles;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FondoDetalleEntity [codFondo=" + codFondo + ", descripcion=" + descripcion + ", divisa=" + divisa
				+ ", saldo=" + saldo + ", detalles=" + detalles + "]";
	}

}