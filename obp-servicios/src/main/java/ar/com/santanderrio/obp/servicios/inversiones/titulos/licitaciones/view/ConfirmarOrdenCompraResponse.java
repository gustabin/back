/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import ar.com.santanderrio.base.web.view.View;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DatosConsultaComisionResponse;

/**
 * The Class ConfirmarOrdenCompraResponse.
 */
public class ConfirmarOrdenCompraResponse extends View {
	
	/** The serial VersionUID. */
	private static final long serialVersionUID = -6917323317979757846L;

	/** The importe debito credito. */
	private Double importeDebitoCredito;

	/** The cantidad. */
	private Double cantidad;

	/** The cotizacion. */
	private Double cotizacion;

	/** The cotizacion limite. */
	private Double cotizacionLimite;

	/** The fecha liquidacion. */
	private String fechaLiquidacion;

	/** The fecha debito cuenta. */
	private String fechaDebitoCuenta;

	/** The importe poder compra. */
	private Double importePoderCompra;

	/** The derechos. */
	private Double derechos;

	/** The iva. */
	private Double iva;

	/** The comision. */
	private Double comision;

	/** The impuestos. */
	private Double impuestos;

	/** The legales. */
	private String legales;

	/** The mensaje feed back. */
	private String mensajeFeedBack;

	/** The comprobante. */
	private String comprobante;

	/** The numero orden. */
	private String numeroOrden;

	/** The id cumplimiento. */
	private Long idCumplimiento;
	
	/** The cabecera. */
	private String cabecera;

	/** The pie. */
	private String pie;
	
	private DatosConsultaComisionResponse datosComisiones;

	/**
	 * Gets the importe debito credito.
	 *
	 * @return the importe debito credito
	 */
	public Double getImporteDebitoCredito() {
		return importeDebitoCredito;
	}

	/**
	 * Sets the importe debito credito.
	 *
	 * @param importeDebitoCredito
	 *            the new importe debito credito
	 */
	public void setImporteDebitoCredito(Double importeDebitoCredito) {
		this.importeDebitoCredito = importeDebitoCredito;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public Double getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the new cantidad
	 */
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public Double getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the new cotizacion
	 */
	public void setCotizacion(Double cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the cotizacion limite.
	 *
	 * @return the cotizacion limite
	 */
	public Double getCotizacionLimite() {
		return cotizacionLimite;
	}

	/**
	 * Sets the cotizacion limite.
	 *
	 * @param cotizacionLimite
	 *            the new cotizacion limite
	 */
	public void setCotizacionLimite(Double cotizacionLimite) {
		this.cotizacionLimite = cotizacionLimite;
	}

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fecha liquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the new fecha liquidacion
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the fecha debito cuenta.
	 *
	 * @return the fecha debito cuenta
	 */
	public String getFechaDebitoCuenta() {
		return fechaDebitoCuenta;
	}

	/**
	 * Sets the fecha debito cuenta.
	 *
	 * @param fechaDebitoCuenta
	 *            the new fecha debito cuenta
	 */
	public void setFechaDebitoCuenta(String fechaDebitoCuenta) {
		this.fechaDebitoCuenta = fechaDebitoCuenta;
	}

	/**
	 * Gets the importe poder compra.
	 *
	 * @return the importe poder compra
	 */
	public Double getImportePoderCompra() {
		return importePoderCompra;
	}

	/**
	 * Sets the importe poder compra.
	 *
	 * @param importePoderCompra
	 *            the new importe poder compra
	 */
	public void setImportePoderCompra(Double importePoderCompra) {
		this.importePoderCompra = importePoderCompra;
	}

	/**
	 * Gets the derechos.
	 *
	 * @return the derechos
	 */
	public Double getDerechos() {
		return derechos;
	}

	/**
	 * Sets the derechos.
	 *
	 * @param derechos
	 *            the new derechos
	 */
	public void setDerechos(Double derechos) {
		this.derechos = derechos;
	}

	/**
	 * Gets the iva.
	 *
	 * @return the iva
	 */
	public Double getIva() {
		return iva;
	}

	/**
	 * Sets the iva.
	 *
	 * @param iva
	 *            the new iva
	 */
	public void setIva(Double iva) {
		this.iva = iva;
	}

	/**
	 * Gets the comision.
	 *
	 * @return the comision
	 */
	public Double getComision() {
		return comision;
	}

	/**
	 * Sets the comision.
	 *
	 * @param comision
	 *            the new comision
	 */
	public void setComision(Double comision) {
		this.comision = comision;
	}

	/**
	 * Gets the impuestos.
	 *
	 * @return the impuestos
	 */
	public Double getImpuestos() {
		return impuestos;
	}

	/**
	 * Sets the impuestos.
	 *
	 * @param impuestos
	 *            the new impuestos
	 */
	public void setImpuestos(Double impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * Gets the legales.
	 *
	 * @return the legales
	 */
	public String getLegales() {
		return legales;
	}

	/**
	 * Sets the legales.
	 *
	 * @param legales
	 *            the new legales
	 */
	public void setLegales(String legales) {
		this.legales = legales;
	}

	/**
	 * Gets the mensaje feed back.
	 *
	 * @return the mensaje feed back
	 */
	public String getMensajeFeedBack() {
		return mensajeFeedBack;
	}

	/**
	 * Sets the mensaje feed back.
	 *
	 * @param mensajeFeedBack
	 *            the new mensaje feed back
	 */
	public void setMensajeFeedBack(String mensajeFeedBack) {
		this.mensajeFeedBack = mensajeFeedBack;
	}

	/**
	 * Gets the cabecera.
	 *
	 * @return the cabecera
	 */
	public String getCabecera() {
		return cabecera;
	}

	/**
	 * Sets the cabecera.
	 *
	 * @param cabecera
	 *            the new cabecera
	 */
	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	/**
	 * Gets the pie.
	 *
	 * @return the pie
	 */
	public String getPie() {
		return pie;
	}

	/**
	 * Sets the pie.
	 *
	 * @param pie
	 *            the new pie
	 */
	public void setPie(String pie) {
		this.pie = pie;
	}

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the new comprobante
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the numero orden.
	 *
	 * @return the numero orden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * Sets the numero orden.
	 *
	 * @param numeroOrden
	 *            the new numero orden
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * Gets the id cumplimiento.
	 *
	 * @return the id cumplimiento
	 */
	public Long getIdCumplimiento() {
		return idCumplimiento;
	}

	/**
	 * Sets the id cumplimiento.
	 *
	 * @param idCumplimiento
	 *            the new id cumplimiento
	 */
	public void setIdCumplimiento(Long idCumplimiento) {
		this.idCumplimiento = idCumplimiento;
	}

	/**
	 * Gets the id cumplimiento.
	 *
	 * @return the id cumplimiento
	 */
	public DatosConsultaComisionResponse getDatosComisiones() {
		return datosComisiones;
	}

	/**
	 * Sets the id cumplimiento.
	 *
	 * @param idCumplimiento
	 *            the new id cumplimiento
	 */
	public void setDatosComisiones(DatosConsultaComisionResponse datosComisiones) {
		this.datosComisiones = datosComisiones;
	}

	
}
