/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao.entity;

import org.beanio.annotation.Field;

/**
 * The Class ComprobanteVisaIATXEntity.
 */
public class ComprobanteVisaIATXEntity {

	/** The fecha pago debito. */
	@Field
	private String fechaPagoDebito;

	/** The tipo moneda. */
	@Field
	private String tipoMoneda;

	/** The importe pagado debitado. */
	@Field
	private String importePagadoDebitado;

	/** The nro comprobante. */
	@Field
	private String nroComprobante;

	/** The origen mov. */
	@Field
	private String origenMov;

	/** The estado. */
	@Field
	private String estado;

	/** The fecha estado. */
	@Field
	private String fechaEstado;

	/** The medio de pago. */
	@Field
	private String medioDePago;

	// /** The tipo cuenta dolar. */
	// @Field
	// private String tipoCuentaDolar;
	//
	// /** The sucursal dolar. */
	// @Field
	// private String sucursalDolar;
	//
	// /** The nro cuenta dolar. */
	// @Field
	// private String nroCuentaDolar;
	//
	/** The importe dolares. */
	@Field
	private String importeDolares;

	/**
	 * Gets the fecha pago debito.
	 *
	 * @return the fecha pago debito
	 */
	public String getFechaPagoDebito() {
		return fechaPagoDebito;
	}

	/**
	 * Sets the fecha pago debito.
	 *
	 * @param fechaPagoDebito
	 *            the new fecha pago debito
	 */
	public void setFechaPagoDebito(String fechaPagoDebito) {
		this.fechaPagoDebito = fechaPagoDebito;
	}

	/**
	 * Gets the tipo moneda.
	 *
	 * @return the tipo moneda
	 */
	public String getTipoMoneda() {
		return tipoMoneda;
	}

	/**
	 * Sets the tipo moneda.
	 *
	 * @param tipoMoneda
	 *            the new tipo moneda
	 */
	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	/**
	 * Gets the importe pagado debitado.
	 *
	 * @return the importe pagado debitado
	 */
	public String getImportePagadoDebitado() {
		return importePagadoDebitado;
	}

	/**
	 * Sets the importe pagado debitado.
	 *
	 * @param importePagadoDebitado
	 *            the new importe pagado debitado
	 */
	public void setImportePagadoDebitado(String importePagadoDebitado) {
		this.importePagadoDebitado = importePagadoDebitado;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/**
	 * Gets the origen mov.
	 *
	 * @return the origen mov
	 */
	public String getOrigenMov() {
		return origenMov;
	}

	/**
	 * Sets the origen mov.
	 *
	 * @param origenMov
	 *            the new origen mov
	 */
	public void setOrigenMov(String origenMov) {
		this.origenMov = origenMov;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado
	 *            the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the fecha estado.
	 *
	 * @return the fecha estado
	 */
	public String getFechaEstado() {
		return fechaEstado;
	}

	/**
	 * Sets the fecha estado.
	 *
	 * @param fechaEstado
	 *            the new fecha estado
	 */
	public void setFechaEstado(String fechaEstado) {
		this.fechaEstado = fechaEstado;
	}

	/**
	 * Gets the medio de pago.
	 *
	 * @return the medio de pago
	 */
	public String getMedioDePago() {
		return medioDePago;
	}

	/**
	 * Sets the medio de pago.
	 *
	 * @param medioDePago
	 *            the new medio de pago
	 */
	public void setMedioDePago(String medioDePago) {
		this.medioDePago = medioDePago;
	}

	// /**
	// * Gets the tipo cuenta dolar.
	// *
	// * @return the tipo cuenta dolar
	// */
	// public String getTipoCuentaDolar() {
	// return tipoCuentaDolar;
	// }
	//
	// /**
	// * Sets the tipo cuenta dolar.
	// *
	// * @param tipoCuentaDolar
	// * the new tipo cuenta dolar
	// */
	// public void setTipoCuentaDolar(String tipoCuentaDolar) {
	// this.tipoCuentaDolar = tipoCuentaDolar;
	// }
	//
	// /**
	// * Gets the sucursal dolar.
	// *
	// * @return the sucursal dolar
	// */
	// public String getSucursalDolar() {
	// return sucursalDolar;
	// }
	//
	// /**
	// * Sets the sucursal dolar.
	// *
	// * @param sucursalDolar
	// * the new sucursal dolar
	// */
	// public void setSucursalDolar(String sucursalDolar) {
	// this.sucursalDolar = sucursalDolar;
	// }
	//
	// /**
	// * Gets the nro cuenta dolar.
	// *
	// * @return the nro cuenta dolar
	// */
	// public String getNroCuentaDolar() {
	// return nroCuentaDolar;
	// }
	//
	// /**
	// * Sets the nro cuenta dolar.
	// *
	// * @param nroCuentaDolar
	// * the new nro cuenta dolar
	// */
	// public void setNroCuentaDolar(String nroCuentaDolar) {
	// this.nroCuentaDolar = nroCuentaDolar;
	// }
	//
	/**
	 * Gets the importe dolares.
	 *
	 * @return the importe dolares
	 */
	public String getImporteDolares() {
		return importeDolares;
	}

	/**
	 * Sets the importe dolares.
	 *
	 * @param importeDolares
	 *            the new importe dolares
	 */
	public void setImporteDolares(String importeDolares) {
		this.importeDolares = importeDolares;
	}

}
