/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.entity;

import java.util.List;

/**
 * The Class ResumenCuentaInversionesEntity.
 */
/**
 * @author A308895
 *
 */

public class ResumenCuentaInversionesEntity {

	/** The espe tipo. */
	private String espeTipo;

	/** The espe cod. */
	private String espeCod;

	/** The cuenta. */
	private String cuenta;

	/** The fecha. */
	private String fecha;

	/** The concepto. */
	private String concepto;

	/** The comprobante. */
	private String comprobante;

	/** The cantidad. */
	private String cantidad;

	/** The cotizacion. */
	private String cotizacion;

	/** The importe. */
	private String importe;

	/** The suc cta ori. */
	private String sucCtaOri;

	/** The tip cta ori. */
	private String tipCtaOri;

	/** The nro cta ori. */
	private String nroCtaOri;

	/** The divisa. */
	private String divisa;
	
	/** The fecha_liq */
	private String fecha_liq;
	
	/** The gastoEntrada */
	private String gastoEntrada;
	
	/** The gastoSalida */
	private String gastoSalida;
	
	/** The importe bruto */
	private String importeBruto;
	
	/** The impuestos */
	private String impuestos;
	
	/** The nro orden */
	private String nroOrden;
	
	/** The nro boleto */
	private String nroBoleto;
	
	/** The moneda Transaccion*/
	private String monedaTransaccion;
	
	/** The renta*/
	private String renta;
	
	/** The amortizacion*/
	private String amortizacion;

	/**
	 * @return the renta
	 */
	public String getRenta() {
		return renta;
	}

	/**
	 * @param renta the renta to set
	 */
	public void setRenta(String renta) {
		this.renta = renta;
	}

	/**
	 * @return the amortizacion
	 */
	public String getAmortizacion() {
		return amortizacion;
	}

	/**
	 * @param amortizacion the amortizacion to set
	 */
	public void setAmortizacion(String amortizacion) {
		this.amortizacion = amortizacion;
	}

	/**
	 * @return the monedaTransaccion
	 */
	public String getMonedaTransaccion() {
		return monedaTransaccion;
	}

	/**
	 * @param monedaTransaccion the monedaTransaccion to set
	 */
	public void setMonedaTransaccion(String monedaTransaccion) {
		this.monedaTransaccion = monedaTransaccion;
	}

	/**
	 * @return the importeBruto
	 */
	public String getImporteBruto() {
		return importeBruto;
	}

	/**
	 * @param importeBruto the importeBruto to set
	 */
	public void setImporteBruto(String importeBruto) {
		this.importeBruto = importeBruto;
	}

	/**
	 * @return the impuestos
	 */
	public String getImpuestos() {
		return impuestos;
	}

	/**
	 * @param impuestos the impuestos to set
	 */
	public void setImpuestos(String impuestos) {
		this.impuestos = impuestos;
	}

	/**
	 * @return the nroOrden
	 */
	public String getNroOrden() {
		return nroOrden;
	}

	/**
	 * @param nroOrden the nroOrden to set
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}

	/**
	 * @return the nroBoleto
	 */
	public String getNroBoleto() {
		return nroBoleto;
	}

	/**
	 * @param nroBoleto the nroBoleto to set
	 */
	public void setNroBoleto(String nroBoleto) {
		this.nroBoleto = nroBoleto;
	}

	/**
	 * @return the fecha_liq
	 */
	public String getFecha_liq() {
		return fecha_liq;
	}

	/**
	 * @param fecha_liq the fecha_liq to set
	 */
	public void setFecha_liq(String fecha_liq) {
		this.fecha_liq = fecha_liq;
	}

	/**
	 * @return the gastoEntrada
	 */
	public String getGastoEntrada() {
		return gastoEntrada;
	}

	/**
	 * @param gastoEntrada the gastoEntrada to set
	 */
	public void setGastoEntrada(String gastoEntrada) {
		this.gastoEntrada = gastoEntrada;
	}

	/**
	 * @return the gastoSalida
	 */
	public String getGastoSalida() {
		return gastoSalida;
	}

	/**
	 * @param gastoSalida the gastoSalida to set
	 */
	public void setGastoSalida(String gastoSalida) {
		this.gastoSalida = gastoSalida;
	}

	/**
	 * Gets the espe tipo.
	 *
	 * @return the espeTipo
	 */
	public String getEspeTipo() {
		return espeTipo;
	}

	/**
	 * Sets the espe tipo.
	 *
	 * @param espeTipo
	 *            the espeTipo to set
	 */
	public void setEspeTipo(String espeTipo) {
		this.espeTipo = espeTipo;
	}

	/**
	 * Gets the espe cod.
	 *
	 * @return the espeCod
	 */
	public String getEspeCod() {
		return espeCod;
	}

	/**
	 * Sets the espe cod.
	 *
	 * @param espeCod
	 *            the espeCod to set
	 */
	public void setEspeCod(String espeCod) {
		this.espeCod = espeCod;
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
	 * Sets the cuenta.
	 *
	 * @param cuenta
	 *            the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
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
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacion to set
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the suc cta ori.
	 *
	 * @return the sucCtaOri
	 */
	public String getSucCtaOri() {
		return sucCtaOri;
	}

	/**
	 * Sets the suc cta ori.
	 *
	 * @param sucCtaOri
	 *            the sucCtaOri to set
	 */
	public void setSucCtaOri(String sucCtaOri) {
		this.sucCtaOri = sucCtaOri;
	}

	/**
	 * Gets the tip cta ori.
	 *
	 * @return the tipCtaOri
	 */
	public String getTipCtaOri() {
		return tipCtaOri;
	}

	/**
	 * Sets the tip cta ori.
	 *
	 * @param tipCtaOri
	 *            the tipCtaOri to set
	 */
	public void setTipCtaOri(String tipCtaOri) {
		this.tipCtaOri = tipCtaOri;
	}

	/**
	 * Gets the nro cta ori.
	 *
	 * @return the nroCtaOri
	 */
	public String getNroCtaOri() {
		return nroCtaOri;
	}

	/**
	 * Sets the nro cta ori.
	 *
	 * @param nroCtaOri
	 *            the nroCtaOri to set
	 */
	public void setNroCtaOri(String nroCtaOri) {
		this.nroCtaOri = nroCtaOri;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResumenCuentaInversionesEntity [espeTipo=" + espeTipo + ", espeCod=" + espeCod + ", cuenta=" + cuenta
				+ ", fecha=" + fecha + ", concepto=" + concepto + ", comprobante=" + comprobante + ", cantidad="
				+ cantidad + ", cotizacion=" + cotizacion + ", importe=" + importe + ", sucCtaOri=" + sucCtaOri
				+ ", tipCtaOri=" + tipCtaOri + ", nroCtaOri=" + nroCtaOri + ", divisa=" + divisa + "]";
	}

}
