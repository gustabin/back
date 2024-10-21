/*
 * 
 */
package ar.com.santanderrio.obp.servicios.chequera.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class PedidoChequeraOutEntity.
 */
@Record
public class PedidoChequeraOutEntity {
	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** *. */
	@Field
	private String fechaEntrega;

	/** *. */
	@Field
	private String sucursalEntrega;

	/** *. */
	@Field
	private String domicilioSucursal;

	/** *. */
	@Field
	private String localidadSucursal;

	/** The numero comprobante. */
	private String numeroComprobante;

	/** The fecha transaccion. */
	private String fechaTransaccion;

	/** The tipo chequera. */
	private String tipoChequera;

	/** The cantidad chequera. */
	private String cantidadChequera;

	/** The cantidad cheques. */
	private String cantidadCheques;

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the fecha entrega.
	 *
	 * @return the fechaEntrega
	 */
	public String getFechaEntrega() {
		return fechaEntrega;
	}

	/**
	 * Sets the fecha entrega.
	 *
	 * @param fechaEntrega
	 *            the fechaEntrega to set
	 */
	public void setFechaEntrega(String fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Gets the sucursal entrega.
	 *
	 * @return the sucursalEntrega
	 */
	public String getSucursalEntrega() {
		return sucursalEntrega;
	}

	/**
	 * Sets the sucursal entrega.
	 *
	 * @param sucursalEntrega
	 *            the sucursalEntrega to set
	 */
	public void setSucursalEntrega(String sucursalEntrega) {
		this.sucursalEntrega = sucursalEntrega;
	}

	/**
	 * Gets the domicilio sucursal.
	 *
	 * @return the domicilioSucursal
	 */
	public String getDomicilioSucursal() {
		return domicilioSucursal;
	}

	/**
	 * Sets the domicilio sucursal.
	 *
	 * @param domicilioSucursal
	 *            the domicilioSucursal to set
	 */
	public void setDomicilioSucursal(String domicilioSucursal) {
		this.domicilioSucursal = domicilioSucursal;
	}

	/**
	 * Gets the localidad sucursal.
	 *
	 * @return the localidadSucursal
	 */
	public String getLocalidadSucursal() {
		return localidadSucursal;
	}

	/**
	 * Sets the localidad sucursal.
	 *
	 * @param localidadSucursal
	 *            the localidadSucursal to set
	 */
	public void setLocalidadSucursal(String localidadSucursal) {
		this.localidadSucursal = localidadSucursal;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numeroComprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the numeroComprobante to set
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the fecha transaccion.
	 *
	 * @return the fechaTransaccion
	 */
	public String getFechaTransaccion() {
		return fechaTransaccion;
	}

	/**
	 * Sets the fecha transaccion.
	 *
	 * @param fechaTransaccion
	 *            the fechaTransaccion to set
	 */
	public void setFechaTransaccion(String fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	/**
	 * Gets the tipo chequera.
	 *
	 * @return the tipoChequera
	 */
	public String getTipoChequera() {
		return tipoChequera;
	}

	/**
	 * Sets the tipo chequera.
	 *
	 * @param tipoChequera
	 *            the tipoChequera to set
	 */
	public void setTipoChequera(String tipoChequera) {
		this.tipoChequera = tipoChequera;
	}

	/**
	 * Gets the cantidad chequera.
	 *
	 * @return the cantidadChequera
	 */
	public String getCantidadChequera() {
		return cantidadChequera;
	}

	/**
	 * Sets the cantidad chequera.
	 *
	 * @param cantidadChequera
	 *            the cantidadChequera to set
	 */
	public void setCantidadChequera(String cantidadChequera) {
		this.cantidadChequera = cantidadChequera;
	}

	/**
	 * Gets the cantidad cheques.
	 *
	 * @return the cantidadCheques
	 */
	public String getCantidadCheques() {
		return cantidadCheques;
	}

	/**
	 * Sets the cantidad cheques.
	 *
	 * @param cantidadCheques
	 *            the cantidadCheques to set
	 */
	public void setCantidadCheques(String cantidadCheques) {
		this.cantidadCheques = cantidadCheques;
	}
}
