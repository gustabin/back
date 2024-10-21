/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ComprobanteVisaAmexIATXInEntity.
 */
public class ComprobanteVisaAmexIATXInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The sucursal cuenta tarjeta. */
	private String sucursalCuentaTarjeta;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The cod titularidad. */
	private String codTitularidad;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the sucursal cuenta tarjeta.
	 *
	 * @return the sucursal cuenta tarjeta
	 */
	public String getSucursalCuentaTarjeta() {
		return sucursalCuentaTarjeta;
	}

	/**
	 * Sets the sucursal cuenta tarjeta.
	 *
	 * @param sucursalCuentaTarjeta
	 *            the new sucursal cuenta tarjeta
	 */
	public void setSucursalCuentaTarjeta(String sucursalCuentaTarjeta) {
		this.sucursalCuentaTarjeta = sucursalCuentaTarjeta;
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the cod titularidad.
	 *
	 * @return the cod titularidad
	 */
	public String getCodTitularidad() {
		return codTitularidad;
	}

	/**
	 * Sets the cod titularidad.
	 *
	 * @param codTitularidad
	 *            the new cod titularidad
	 */
	public void setCodTitularidad(String codTitularidad) {
		this.codTitularidad = codTitularidad;
	}

	/**
	 * Gets the fecha desde.
	 *
	 * @return the fecha desde
	 */
	public String getFechaDesde() {
		return fechaDesde;
	}

	/**
	 * Sets the fecha desde.
	 *
	 * @param fechaDesde
	 *            the new fecha desde
	 */
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	/**
	 * Gets the fecha hasta.
	 *
	 * @return the fecha hasta
	 */
	public String getFechaHasta() {
		return fechaHasta;
	}

	/**
	 * Sets the fecha hasta.
	 *
	 * @param fechaHasta
	 *            the new fecha hasta
	 */
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

}
