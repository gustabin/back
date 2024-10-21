/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.infomercado.dto;

import java.math.BigDecimal;

/**
 * The Class EspecieDTO.
 */
public class EspecieDTO {

	/** The tipo. */
	private TipoInstrumentoInfoMercadoEnum tipo;
	
	/** The codigo especie. */
	private String codigoEspecie;
	
	/** The descripcion. */
	private String descripcion;
	
	/** The codigo amigable. */
	private String codigoAmigable;
	
	/** The moneda especie. */
	private String monedaEspecie;
	
	/** The moneda negociacion. */
	private String monedaNegociacion;
	
	/** The plazo. */
	private Integer plazo;
	
	/** The ultima cotizacion. */
	private BigDecimal ultimaCotizacion;
	
	/** The ultima cotizacion fecha. */
	private String ultimaCotizacionFecha;
	
	/** The ultima cotizacion hora. */
	private String ultimaCotizacionHora;
	
	/** The variacion valor. */
	private BigDecimal variacionValor;
	
	/** The importe operado. */
	private BigDecimal importeOperado;
	
	/** The precio maximo. */
	private BigDecimal precioMaximo;
	
	/** The precio minimo. */
	private BigDecimal precioMinimo;
	
	/** The precio cierre. */
	private BigDecimal precioCierre;
	
	/** The valor nominal. */
	private BigDecimal valorNominal;
	
	/** The negociable. */
	private Boolean negociable;
	
	/** The tipo especie. */
	private String tipoEspecie;

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public TipoInstrumentoInfoMercadoEnum getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(TipoInstrumentoInfoMercadoEnum tipo) {
		this.tipo = tipo;
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
	 * Gets the codigo amigable.
	 *
	 * @return the codigoAmigable
	 */
	public String getCodigoAmigable() {
		return codigoAmigable;
	}

	/**
	 * Sets the codigo amigable.
	 *
	 * @param codigoAmigable
	 *            the codigoAmigable to set
	 */
	public void setCodigoAmigable(String codigoAmigable) {
		this.codigoAmigable = codigoAmigable;
	}

	/**
	 * Gets the moneda especie.
	 *
	 * @return the monedaEspecie
	 */
	public String getMonedaEspecie() {
		return monedaEspecie;
	}

	/**
	 * Sets the moneda especie.
	 *
	 * @param monedaEspecie
	 *            the monedaEspecie to set
	 */
	public void setMonedaEspecie(String monedaEspecie) {
		this.monedaEspecie = monedaEspecie;
	}

	/**
	 * Gets the moneda negociacion.
	 *
	 * @return the monedaNegociacion
	 */
	public String getMonedaNegociacion() {
		return monedaNegociacion;
	}

	/**
	 * Sets the moneda negociacion.
	 *
	 * @param monedaNegociacion
	 *            the monedaNegociacion to set
	 */
	public void setMonedaNegociacion(String monedaNegociacion) {
		this.monedaNegociacion = monedaNegociacion;
	}

	/**
	 * Gets the plazo.
	 *
	 * @return the plazo
	 */
	public Integer getPlazo() {
		return plazo;
	}

	/**
	 * Sets the plazo.
	 *
	 * @param plazo
	 *            the plazo to set
	 */
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
	}

	/**
	 * Gets the ultima cotizacion.
	 *
	 * @return the ultimaCotizacion
	 */
	public BigDecimal getUltimaCotizacion() {
		return ultimaCotizacion;
	}

	/**
	 * Sets the ultima cotizacion.
	 *
	 * @param ultimaCotizacion
	 *            the ultimaCotizacion to set
	 */
	public void setUltimaCotizacion(BigDecimal ultimaCotizacion) {
		this.ultimaCotizacion = ultimaCotizacion;
	}

	/**
	 * Gets the ultima cotizacion fecha.
	 *
	 * @return the ultimaCotizacionFecha
	 */
	public String getUltimaCotizacionFecha() {
		return ultimaCotizacionFecha;
	}

	/**
	 * Sets the ultima cotizacion fecha.
	 *
	 * @param ultimaCotizacionFecha
	 *            the ultimaCotizacionFecha to set
	 */
	public void setUltimaCotizacionFecha(String ultimaCotizacionFecha) {
		this.ultimaCotizacionFecha = ultimaCotizacionFecha;
	}

	/**
	 * Gets the ultima cotizacion hora.
	 *
	 * @return the ultimaCotizacionHora
	 */
	public String getUltimaCotizacionHora() {
		return ultimaCotizacionHora;
	}

	/**
	 * Sets the ultima cotizacion hora.
	 *
	 * @param ultimaCotizacionHora
	 *            the ultimaCotizacionHora to set
	 */
	public void setUltimaCotizacionHora(String ultimaCotizacionHora) {
		this.ultimaCotizacionHora = ultimaCotizacionHora;
	}

	/**
	 * Gets the variacion valor.
	 *
	 * @return the variacionValor
	 */
	public BigDecimal getVariacionValor() {
		return variacionValor;
	}

	/**
	 * Sets the variacion valor.
	 *
	 * @param variacionValor
	 *            the variacionValor to set
	 */
	public void setVariacionValor(BigDecimal variacionValor) {
		this.variacionValor = variacionValor;
	}

	/**
	 * Gets the importe operado.
	 *
	 * @return the importeOperado
	 */
	public BigDecimal getImporteOperado() {
		return importeOperado;
	}

	/**
	 * Sets the importe operado.
	 *
	 * @param importeOperado
	 *            the importeOperado to set
	 */
	public void setImporteOperado(BigDecimal importeOperado) {
		this.importeOperado = importeOperado;
	}

	/**
	 * Gets the precio maximo.
	 *
	 * @return the precioMaximo
	 */
	public BigDecimal getPrecioMaximo() {
		return precioMaximo;
	}

	/**
	 * Sets the precio maximo.
	 *
	 * @param precioMaximo
	 *            the precioMaximo to set
	 */
	public void setPrecioMaximo(BigDecimal precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	/**
	 * Gets the precio minimo.
	 *
	 * @return the precioMinimo
	 */
	public BigDecimal getPrecioMinimo() {
		return precioMinimo;
	}

	/**
	 * Sets the precio minimo.
	 *
	 * @param precioMinimo
	 *            the precioMinimo to set
	 */
	public void setPrecioMinimo(BigDecimal precioMinimo) {
		this.precioMinimo = precioMinimo;
	}

	/**
	 * Gets the precio cierre.
	 *
	 * @return the precioCierre
	 */
	public BigDecimal getPrecioCierre() {
		return precioCierre;
	}

	/**
	 * Sets the precio cierre.
	 *
	 * @param precioCierre
	 *            the precioCierre to set
	 */
	public void setPrecioCierre(BigDecimal precioCierre) {
		this.precioCierre = precioCierre;
	}

	/**
	 * Gets the valor nominal.
	 *
	 * @return the valorNominal
	 */
	public BigDecimal getValorNominal() {
		return valorNominal;
	}

	/**
	 * Sets the valor nominal.
	 *
	 * @param valorNominal
	 *            the valorNominal to set
	 */
	public void setValorNominal(BigDecimal valorNominal) {
		this.valorNominal = valorNominal;
	}

	/**
	 * Gets the codigo especie.
	 *
	 * @return the codigoEspecie
	 */
	public String getCodigoEspecie() {
		return codigoEspecie;
	}

	/**
	 * Sets the codigo especie.
	 *
	 * @param codigoEspecie
	 *            the codigoEspecie to set
	 */
	public void setCodigoEspecie(String codigoEspecie) {
		this.codigoEspecie = codigoEspecie;
	}

	/**
	 * Gets the negociable.
	 *
	 * @return the negociable
	 */
	public Boolean getNegociable() {
		return negociable;
	}

	/**
	 * Sets the negociable.
	 *
	 * @param negociable
	 *            the negociable to set
	 */
	public void setNegociable(Boolean negociable) {
		this.negociable = negociable;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipoEspecie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the tipoEspecie to set
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

}
