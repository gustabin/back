/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosConsultaSuscripcionSaldoPDCRequest.
 */
public class DatosConsultaSuscripcionSaldoPDCRequest extends EntityBaseTitulos{

	/** The fecha liquidacion. */
	@JsonProperty("FechaLiquidacion")
	private String fechaLiquidacion;
	
	/** The cuenta titulos. */
	@JsonProperty("CuentaTitulos")
	private String cuentaTitulos;
	
	/** The codigo moneda. */
	@JsonProperty("CodigoMoneda")
	private String codigoMoneda;
	
	/** The tipo cta oper. */
	@JsonProperty("TipoCtaOper")
	private String tipoCtaOper;
	
	/** The suc cta oper. */
	@JsonProperty("SucCtaOper")
	private String sucCtaOper;
	
	/** The numero cuenta oper. */
	@JsonProperty("NumeroCuentaOper")
	private String numeroCuentaOper;
	
	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;
	
	/** The producto inversion. */
	@JsonProperty("ProductoInversion")
	private String productoInversion;

	/**
	 * Gets the fecha liquidacion.
	 *
	 * @return the fechaLiquidacion
	 */
	public String getFechaLiquidacion() {
		return fechaLiquidacion;
	}

	/**
	 * Sets the fecha liquidacion.
	 *
	 * @param fechaLiquidacion
	 *            the fechaLiquidacion to set
	 */
	public void setFechaLiquidacion(String fechaLiquidacion) {
		this.fechaLiquidacion = fechaLiquidacion;
	}

	/**
	 * Gets the cuenta titulos.
	 *
	 * @return the cuentaTitulos
	 */
	public String getCuentaTitulos() {
		return cuentaTitulos;
	}

	/**
	 * Sets the cuenta titulos.
	 *
	 * @param cuentaTitulos
	 *            the cuentaTitulos to set
	 */
	public void setCuentaTitulos(String cuentaTitulos) {
		this.cuentaTitulos = cuentaTitulos;
	}

	/**
	 * Gets the codigo moneda.
	 *
	 * @return the codigoMoneda
	 */
	public String getCodigoMoneda() {
		return codigoMoneda;
	}

	/**
	 * Sets the codigo moneda.
	 *
	 * @param codigoMoneda
	 *            the codigoMoneda to set
	 */
	public void setCodigoMoneda(String codigoMoneda) {
		this.codigoMoneda = codigoMoneda;
	}

	/**
	 * Gets the tipo cta oper.
	 *
	 * @return the tipoCtaOper
	 */
	public String getTipoCtaOper() {
		return tipoCtaOper;
	}

	/**
	 * Sets the tipo cta oper.
	 *
	 * @param tipoCtaOper
	 *            the tipoCtaOper to set
	 */
	public void setTipoCtaOper(String tipoCtaOper) {
		this.tipoCtaOper = tipoCtaOper;
	}

	/**
	 * Gets the suc cta oper.
	 *
	 * @return the sucCtaOper
	 */
	public String getSucCtaOper() {
		return sucCtaOper;
	}

	/**
	 * Sets the suc cta oper.
	 *
	 * @param sucCtaOper
	 *            the sucCtaOper to set
	 */
	public void setSucCtaOper(String sucCtaOper) {
		this.sucCtaOper = sucCtaOper;
	}

	/**
	 * Gets the numero cuenta oper.
	 *
	 * @return the numeroCuentaOper
	 */
	public String getNumeroCuentaOper() {
		return numeroCuentaOper;
	}

	/**
	 * Sets the numero cuenta oper.
	 *
	 * @param numeroCuentaOper
	 *            the numeroCuentaOper to set
	 */
	public void setNumeroCuentaOper(String numeroCuentaOper) {
		this.numeroCuentaOper = numeroCuentaOper;
	}

	/**
	 * Gets the segmento.
	 *
	 * @return the segmento
	 */
	public String getSegmento() {
		return segmento;
	}

	/**
	 * Sets the segmento.
	 *
	 * @param segmento
	 *            the segmento to set
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	/**
	 * Gets the producto inversion.
	 *
	 * @return the productoInversion
	 */
	public String getProductoInversion() {
		return productoInversion;
	}

	/**
	 * Sets the producto inversion.
	 *
	 * @param productoInversion
	 *            the productoInversion to set
	 */
	public void setProductoInversion(String productoInversion) {
		this.productoInversion = productoInversion;
	}

}
