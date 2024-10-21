/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The Class Orden.
 */
public class Orden {

	/** The nro orden. */
	private String nroOrden;

	/** The cod sitema. */
	private String codSitema;

	/** The tipo orden. */
	private String tipoOrden;

	/** The especie. */
	private String especie;

	/** The moneda liq. */
	private String monedaLiq;

	/** The fecha orden. */
	private Date fechaOrden;

	/** The fecha liq. */
	private Date fechaLiq;

	/** The cant titulo. */
	private BigDecimal cantTitulo;

	/** The precio uni. */
	private BigDecimal precioUni;

	/** The capital. */
	private BigDecimal capital;

	/** The detalle. */
	private String detalle;

	/** The estado. */
	private String estado;

	/** The hora emision. */
	private String horaEmision;

	/** The descripcion especie. */
	private String descripcionEspecie;

	/** The nro atit. */
	private BigDecimal nroAtit;

	/** The respaldable. */
	private String respaldable;

	/** The plazo. */
	private String plazo;

	/** The tna. */
	private String tna;

	/** The cod accion vto. */
	private String codAccionVto;

	/** The accion vto. */
	private String accionVto;

	/** The int deveng espec. */
	private String intDevengEspec;

	/** The derechos. */
	private BigDecimal derechos;

	/** The impuesto. */
	private BigDecimal impuesto;

	/** The mercado origen. */
	private String mercadoOrigen;

	/** The mercado operacion. */
	private String mercadoOperacion;

	/** The sucursal cta plazo. */
	private String sucursalCtaPlazo;

	/** The num cta plazo. */
	private String numCtaPlazo;

	/** The secuencia. */
	private String secuencia;

	/** The nro fondo. */
	private BigDecimal nroFondo;

	/**
	 * Gets the nro orden.
	 *
	 * @return the nro orden
	 */
	public String getNroOrden() {
		return nroOrden;
	}

	/**
	 * Sets the nro orden.
	 *
	 * @param nroOrden
	 *            the new nro orden
	 */
	public void setNroOrden(String nroOrden) {
		this.nroOrden = nroOrden;
	}

	/**
	 * Gets the cod sitema.
	 *
	 * @return the cod sitema
	 */
	public String getCodSitema() {
		return codSitema;
	}

	/**
	 * Sets the cod sitema.
	 *
	 * @param codSitema
	 *            the new cod sitema
	 */
	public void setCodSitema(String codSitema) {
		this.codSitema = codSitema;
	}

	/**
	 * Gets the tipo orden.
	 *
	 * @return the tipo orden
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}

	/**
	 * Sets the tipo orden.
	 *
	 * @param tipoOrden
	 *            the new tipo orden
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	/**
	 * Gets the especie.
	 *
	 * @return the especie
	 */
	public String getEspecie() {
		return especie;
	}

	/**
	 * Sets the especie.
	 *
	 * @param especie
	 *            the new especie
	 */
	public void setEspecie(String especie) {
		this.especie = especie;
	}

	/**
	 * Gets the moneda liq.
	 *
	 * @return the moneda liq
	 */
	public String getMonedaLiq() {
		return monedaLiq;
	}

	/**
	 * Sets the moneda liq.
	 *
	 * @param monedaLiq
	 *            the new moneda liq
	 */
	public void setMonedaLiq(String monedaLiq) {
		this.monedaLiq = monedaLiq;
	}

	/**
	 * Gets the fecha orden.
	 *
	 * @return the fecha orden
	 */
	public Date getFechaOrden() {
		return fechaOrden;
	}

	/**
	 * Sets the fecha orden.
	 *
	 * @param fechaOrden
	 *            the new fecha orden
	 */
	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	/**
	 * Gets the fecha liq.
	 *
	 * @return the fecha liq
	 */
	public Date getFechaLiq() {
		return fechaLiq;
	}

	/**
	 * Sets the fecha liq.
	 *
	 * @param fechaLiq
	 *            the new fecha liq
	 */
	public void setFechaLiq(Date fechaLiq) {
		this.fechaLiq = fechaLiq;
	}

	/**
	 * Gets the cant titulo.
	 *
	 * @return the cant titulo
	 */
	public BigDecimal getCantTitulo() {
		return cantTitulo;
	}

	/**
	 * Sets the cant titulo.
	 *
	 * @param cantTitulo
	 *            the new cant titulo
	 */
	public void setCantTitulo(BigDecimal cantTitulo) {
		this.cantTitulo = cantTitulo;
	}

	/**
	 * Gets the precio uni.
	 *
	 * @return the precio uni
	 */
	public BigDecimal getPrecioUni() {
		return precioUni;
	}

	/**
	 * Sets the precio uni.
	 *
	 * @param precioUni
	 *            the new precio uni
	 */
	public void setPrecioUni(BigDecimal precioUni) {
		this.precioUni = precioUni;
	}

	/**
	 * Gets the capital.
	 *
	 * @return the capital
	 */
	public BigDecimal getCapital() {
		return capital;
	}

	/**
	 * Sets the capital.
	 *
	 * @param capital
	 *            the new capital
	 */
	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	/**
	 * Gets the detalle.
	 *
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * Sets the detalle.
	 *
	 * @param detalle
	 *            the new detalle
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
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
	 * Gets the hora emision.
	 *
	 * @return the hora emision
	 */
	public String getHoraEmision() {
		return horaEmision;
	}

	/**
	 * Sets the hora emision.
	 *
	 * @param horaEmision
	 *            the new hora emision
	 */
	public void setHoraEmision(String horaEmision) {
		this.horaEmision = horaEmision;
	}

	/**
	 * Gets the descripcion especie.
	 *
	 * @return the descripcion especie
	 */
	public String getDescripcionEspecie() {
		return descripcionEspecie;
	}

	/**
	 * Sets the descripcion especie.
	 *
	 * @param descripcionEspecie
	 *            the new descripcion especie
	 */
	public void setDescripcionEspecie(String descripcionEspecie) {
		this.descripcionEspecie = descripcionEspecie;
	}

	/**
	 * Gets the nro atit.
	 *
	 * @return the nro atit
	 */
	public BigDecimal getNroAtit() {
		return nroAtit;
	}

	/**
	 * Sets the nro atit.
	 *
	 * @param nroAtit
	 *            the new nro atit
	 */
	public void setNroAtit(BigDecimal nroAtit) {
		this.nroAtit = nroAtit;
	}

	/**
	 * Gets the respaldable.
	 *
	 * @return the respaldable
	 */
	public String getRespaldable() {
		return respaldable;
	}

	/**
	 * Sets the respaldable.
	 *
	 * @param respaldable
	 *            the new respaldable
	 */
	public void setRespaldable(String respaldable) {
		this.respaldable = respaldable;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public String getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the new plazo
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the tna.
	 *
	 * @return the tna
	 */
	public String getTna() {
		return tna;
	}

	/**
	 * Sets the tna.
	 *
	 * @param tna
	 *            the new tna
	 */
	public void setTna(String tna) {
		this.tna = tna;
	}

	/**
	 * Gets the cod accion vto.
	 *
	 * @return the cod accion vto
	 */
	public String getCodAccionVto() {
		return codAccionVto;
	}

	/**
	 * Sets the cod accion vto.
	 *
	 * @param codAccionVto
	 *            the new cod accion vto
	 */
	public void setCodAccionVto(String codAccionVto) {
		this.codAccionVto = codAccionVto;
	}

	/**
	 * Gets the accion vto.
	 *
	 * @return the accion vto
	 */
	public String getAccionVto() {
		return accionVto;
	}

	/**
	 * Sets the accion vto.
	 *
	 * @param accionVto
	 *            the new accion vto
	 */
	public void setAccionVto(String accionVto) {
		this.accionVto = accionVto;
	}

	/**
	 * Gets the int deveng espec.
	 *
	 * @return the int deveng espec
	 */
	public String getIntDevengEspec() {
		return intDevengEspec;
	}

	/**
	 * Sets the int deveng espec.
	 *
	 * @param intDevengEspec
	 *            the new int deveng espec
	 */
	public void setIntDevengEspec(String intDevengEspec) {
		this.intDevengEspec = intDevengEspec;
	}

	/**
	 * Gets the derechos.
	 *
	 * @return the derechos
	 */
	public BigDecimal getDerechos() {
		return derechos;
	}

	/**
	 * Sets the derechos.
	 *
	 * @param derechos
	 *            the new derechos
	 */
	public void setDerechos(BigDecimal derechos) {
		this.derechos = derechos;
	}

	/**
	 * Gets the impuesto.
	 *
	 * @return the impuesto
	 */
	public BigDecimal getImpuesto() {
		return impuesto;
	}

	/**
	 * Sets the impuesto.
	 *
	 * @param impuesto
	 *            the new impuesto
	 */
	public void setImpuesto(BigDecimal impuesto) {
		this.impuesto = impuesto;
	}

	/**
	 * Gets the mercado origen.
	 *
	 * @return the mercado origen
	 */
	public String getMercadoOrigen() {
		return mercadoOrigen;
	}

	/**
	 * Sets the mercado origen.
	 *
	 * @param mercadoOrigen
	 *            the new mercado origen
	 */
	public void setMercadoOrigen(String mercadoOrigen) {
		this.mercadoOrigen = mercadoOrigen;
	}

	/**
	 * Gets the mercado operacion.
	 *
	 * @return the mercado operacion
	 */
	public String getMercadoOperacion() {
		return mercadoOperacion;
	}

	/**
	 * Sets the mercado operacion.
	 *
	 * @param mercadoOperacion
	 *            the new mercado operacion
	 */
	public void setMercadoOperacion(String mercadoOperacion) {
		this.mercadoOperacion = mercadoOperacion;
	}

	/**
	 * Gets the sucursal cta plazo.
	 *
	 * @return the sucursal cta plazo
	 */
	public String getSucursalCtaPlazo() {
		return sucursalCtaPlazo;
	}

	/**
	 * Sets the sucursal cta plazo.
	 *
	 * @param sucursalCtaPlazo
	 *            the new sucursal cta plazo
	 */
	public void setSucursalCtaPlazo(String sucursalCtaPlazo) {
		this.sucursalCtaPlazo = sucursalCtaPlazo;
	}

	/**
	 * Gets the num cta plazo.
	 *
	 * @return the num cta plazo
	 */
	public String getNumCtaPlazo() {
		return numCtaPlazo;
	}

	/**
	 * Sets the num cta plazo.
	 *
	 * @param numCtaPlazo
	 *            the new num cta plazo
	 */
	public void setNumCtaPlazo(String numCtaPlazo) {
		this.numCtaPlazo = numCtaPlazo;
	}

	/**
	 * Gets the secuencia.
	 *
	 * @return the secuencia
	 */
	public String getSecuencia() {
		return secuencia;
	}

	/**
	 * Sets the secuencia.
	 *
	 * @param secuencia
	 *            the new secuencia
	 */
	public void setSecuencia(String secuencia) {
		this.secuencia = secuencia;
	}

	/**
	 * Gets the nro fondo.
	 *
	 * @return the nro fondo
	 */
	public BigDecimal getNroFondo() {
		return nroFondo;
	}

	/**
	 * Sets the nro fondo.
	 *
	 * @param nroFondo
	 *            the new nro fondo
	 */
	public void setNroFondo(BigDecimal nroFondo) {
		this.nroFondo = nroFondo;
	}

}