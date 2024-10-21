/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ConsultaComprobanteInEntity.
 * 
 * @author dante.omar.olmedo
 */
public class ConsultaComprobanteInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** The tipo cuenta. */
	private String tipoCuenta;

	/** The sucursal cuenta. */
	private String sucursalCuenta;

	/** The nro cuenta. */
	private String nroCuenta;

	/** The nro orden firmante. */
	private String nroOrdenFirmante;

	/** The nro tarjeta banelco. */
	private String nroTarjetaBanelco;

	/** The fecha desde. */
	private String fechaDesde;

	/** The fecha hasta. */
	private String fechaHasta;

	/** The empresa. */
	private String empresa;

	/** The tipo monto. */
	private String tipoMonto;

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
	 * Gets the tipo cuenta.
	 *
	 * @return the tipo cuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the new tipo cuenta
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the sucursal cuenta.
	 *
	 * @return the sucursal cuenta
	 */
	public String getSucursalCuenta() {
		return sucursalCuenta;
	}

	/**
	 * Sets the sucursal cuenta.
	 *
	 * @param sucursalCuenta
	 *            the new sucursal cuenta
	 */
	public void setSucursalCuenta(String sucursalCuenta) {
		this.sucursalCuenta = sucursalCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nro cuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the new nro cuenta
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the nro orden firmante.
	 *
	 * @return the nro orden firmante
	 */
	public String getNroOrdenFirmante() {
		return nroOrdenFirmante;
	}

	/**
	 * Sets the nro orden firmante.
	 *
	 * @param nroOrdenFirmante
	 *            the new nro orden firmante
	 */
	public void setNroOrdenFirmante(String nroOrdenFirmante) {
		this.nroOrdenFirmante = nroOrdenFirmante;
	}

	/**
	 * Gets the nro tarjeta banelco.
	 *
	 * @return the nro tarjeta banelco
	 */
	public String getNroTarjetaBanelco() {
		return nroTarjetaBanelco;
	}

	/**
	 * Sets the nro tarjeta banelco.
	 *
	 * @param nroTarjetaBanelco
	 *            the new nro tarjeta banelco
	 */
	public void setNroTarjetaBanelco(String nroTarjetaBanelco) {
		this.nroTarjetaBanelco = nroTarjetaBanelco;
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

	/**
	 * Gets the empresa.
	 *
	 * @return the empresa
	 */
	public String getEmpresa() {
		return empresa;
	}

	/**
	 * Sets the empresa.
	 *
	 * @param empresa
	 *            the empresa to set
	 */
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	/**
	 * Gets the tipo monto.
	 *
	 * @return the tipoMonto
	 */
	public String getTipoMonto() {
		return tipoMonto;
	}

	/**
	 * Sets the tipo monto.
	 *
	 * @param tipoMonto
	 *            the tipoMonto to set
	 */
	public void setTipoMonto(String tipoMonto) {
		this.tipoMonto = tipoMonto;
	}

}
