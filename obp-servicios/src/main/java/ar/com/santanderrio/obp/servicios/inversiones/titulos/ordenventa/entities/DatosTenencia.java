/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.entities;

import java.math.BigDecimal;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * The Class DatosTenencia.
 */
public class DatosTenencia {

	/** The fecha custodia. */
	@JsonProperty("FechaCustodia")
	private String fechaCustodia;
	
	/** The cta titulo. */
	@JsonProperty("CtaTitulo")
	private Integer ctaTitulo;
	
	/** The descripcion especie. */
	@JsonProperty("DescripcionEspecie")
	private String descripcionEspecie;
	
	/** The codigo amigable. */
	@JsonProperty("CodigoAmigable")
	private String codigoAmigable;
	
	/** The codigo especie rossi. */
	@JsonProperty("CodigoEspecieRossi")
	private String codigoEspecieRossi;
	
	/** The codigo especie caja valores. */
	@JsonProperty("CodigoEspecieCajaValores")
	private String codigoEspecieCajaValores;
	
	/** The tipo especie. */
	@JsonProperty("TipoEspecie")
	private String tipoEspecie;
	
	/** The instrumento. */
	@JsonProperty("Instrumento")
	private String instrumento;
	
	/** The codigo moneda emision. */
	@JsonProperty("CodigoMonedaEmision")
	private String codigoMonedaEmision;
	
	/** The estado cuenta titulo. */
	@JsonProperty("EstadoCuentaTitulo")
	private String estadoCuentaTitulo;
	
	/** The plazo. */
	@JsonProperty("Plazo")
	private Integer plazo;
	
	/** The fecha liquidacion. */
	@JsonProperty("FechaLiquidacion")
	private String fechaLiquidacion;
	
	/** The motivo bloqueo. */
	@JsonProperty("MotivoBloqueo")
	private String motivoBloqueo;
	
	/** The especie codigo. */
	@JsonProperty("EspecieCodigo")
	private String especieCodigo;
	
	/** The codigo estado. */
	@JsonProperty("CodigoEstado")
	private String codigoEstado;
	
	/** The estado tenencia. */
	@JsonProperty("EstadoTenencia")
	private String estadoTenencia;
	
	/** The descr estado. */
	@JsonProperty("DescrEstado")
	private String descrEstado;
	
	/** The tenencia nominal negociable. */
	@JsonProperty("TenenciaNominalNegociable")
	private BigDecimal tenenciaNominalNegociable;
	
	/** The tenencia total. */
	@JsonProperty("TenenciaTotal")
	private BigDecimal tenenciaTotal;
	
	/** The segmento. */
	@JsonProperty("Segmento")
	private String segmento;

	
	/**
	 * Gets the fecha custodia.
	 *
	 * @return the fecha custodia
	 */
	public String getFechaCustodia() {
		return fechaCustodia;
	}

	/**
	 * Sets the fecha custodia.
	 *
	 * @param fechaCustodia
	 *            the new fecha custodia
	 */
	public void setFechaCustodia(String fechaCustodia) {
		this.fechaCustodia = fechaCustodia;
	}

	/**
	 * Gets the cta titulo.
	 *
	 * @return the cta titulo
	 */
	public Integer getCtaTitulo() {
		return ctaTitulo;
	}

	/**
	 * Sets the cta titulo.
	 *
	 * @param ctaTitulo
	 *            the new cta titulo
	 */
	public void setCtaTitulo(Integer ctaTitulo) {
		this.ctaTitulo = ctaTitulo;
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
	 * Gets the codigo amigable.
	 *
	 * @return the codigo amigable
	 */
	public String getCodigoAmigable() {
		return codigoAmigable;
	}

	/**
	 * Sets the codigo amigable.
	 *
	 * @param codigoAmigable
	 *            the new codigo amigable
	 */
	public void setCodigoAmigable(String codigoAmigable) {
		this.codigoAmigable = codigoAmigable;
	}

	/**
	 * Gets the codigo especie rossi.
	 *
	 * @return the codigo especie rossi
	 */
	public String getCodigoEspecieRossi() {
		return codigoEspecieRossi;
	}

	/**
	 * Sets the codigo especie rossi.
	 *
	 * @param codigoEspecieRossi
	 *            the new codigo especie rossi
	 */
	public void setCodigoEspecieRossi(String codigoEspecieRossi) {
		this.codigoEspecieRossi = codigoEspecieRossi;
	}

	/**
	 * Gets the codigo especie caja valores.
	 *
	 * @return the codigo especie caja valores
	 */
	public String getCodigoEspecieCajaValores() {
		return codigoEspecieCajaValores;
	}

	/**
	 * Sets the codigo especie caja valores.
	 *
	 * @param codigoEspecieCajaValores
	 *            the new codigo especie caja valores
	 */
	public void setCodigoEspecieCajaValores(String codigoEspecieCajaValores) {
		this.codigoEspecieCajaValores = codigoEspecieCajaValores;
	}

	/**
	 * Gets the tipo especie.
	 *
	 * @return the tipo especie
	 */
	public String getTipoEspecie() {
		return tipoEspecie;
	}

	/**
	 * Sets the tipo especie.
	 *
	 * @param tipoEspecie
	 *            the new tipo especie
	 */
	public void setTipoEspecie(String tipoEspecie) {
		this.tipoEspecie = tipoEspecie;
	}

	/**
	 * Gets the instrumento.
	 *
	 * @return the instrumento
	 */
	public String getInstrumento() {
		return instrumento;
	}

	/**
	 * Sets the instrumento.
	 *
	 * @param instrumento
	 *            the new instrumento
	 */
	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	/**
	 * Gets the codigo moneda emision.
	 *
	 * @return the codigo moneda emision
	 */
	public String getCodigoMonedaEmision() {
		return codigoMonedaEmision;
	}

	/**
	 * Sets the codigo moneda emision.
	 *
	 * @param codigoMonedaEmision
	 *            the new codigo moneda emision
	 */
	public void setCodigoMonedaEmision(String codigoMonedaEmision) {
		this.codigoMonedaEmision = codigoMonedaEmision;
	}

	/**
	 * Gets the estado cuenta titulo.
	 *
	 * @return the estado cuenta titulo
	 */
	public String getEstadoCuentaTitulo() {
		return estadoCuentaTitulo;
	}

	/**
	 * Sets the estado cuenta titulo.
	 *
	 * @param estadoCuentaTitulo
	 *            the new estado cuenta titulo
	 */
	public void setEstadoCuentaTitulo(String estadoCuentaTitulo) {
		this.estadoCuentaTitulo = estadoCuentaTitulo;
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
	 *            the new plazo
	 */
	public void setPlazo(Integer plazo) {
		this.plazo = plazo;
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
	 * Gets the motivo bloqueo.
	 *
	 * @return the motivo bloqueo
	 */
	public String getMotivoBloqueo() {
		return motivoBloqueo;
	}

	/**
	 * Sets the motivo bloqueo.
	 *
	 * @param motivoBloqueo
	 *            the new motivo bloqueo
	 */
	public void setMotivoBloqueo(String motivoBloqueo) {
		this.motivoBloqueo = motivoBloqueo;
	}

	/**
	 * Gets the especie codigo.
	 *
	 * @return the especie codigo
	 */
	public String getEspecieCodigo() {
		return especieCodigo;
	}

	/**
	 * Sets the especie codigo.
	 *
	 * @param especieCodigo
	 *            the new especie codigo
	 */
	public void setEspecieCodigo(String especieCodigo) {
		this.especieCodigo = especieCodigo;
	}

	/**
	 * Gets the codigo estado.
	 *
	 * @return the codigo estado
	 */
	public String getCodigoEstado() {
		return codigoEstado;
	}

	/**
	 * Sets the codigo estado.
	 *
	 * @param codigoEstado
	 *            the new codigo estado
	 */
	public void setCodigoEstado(String codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	/**
	 * Gets the estado tenencia.
	 *
	 * @return the estado tenencia
	 */
	public String getEstadoTenencia() {
		return estadoTenencia;
	}

	/**
	 * Sets the estado tenencia.
	 *
	 * @param estadoTenencia
	 *            the new estado tenencia
	 */
	public void setEstadoTenencia(String estadoTenencia) {
		this.estadoTenencia = estadoTenencia;
	}

	/**
	 * Gets the descr estado.
	 *
	 * @return the descr estado
	 */
	public String getDescrEstado() {
		return descrEstado;
	}

	/**
	 * Sets the descr estado.
	 *
	 * @param descrEstado
	 *            the new descr estado
	 */
	public void setDescrEstado(String descrEstado) {
		this.descrEstado = descrEstado;
	}

	/**
	 * Gets the tenencia nominal negociable.
	 *
	 * @return the tenencia nominal negociable
	 */
	public BigDecimal getTenenciaNominalNegociable() {
		return tenenciaNominalNegociable;
	}

	/**
	 * Sets the tenencia nominal negociable.
	 *
	 * @param tenenciaNominalNegociable
	 *            the new tenencia nominal negociable
	 */
	public void setTenenciaNominalNegociable(BigDecimal tenenciaNominalNegociable) {
		this.tenenciaNominalNegociable = tenenciaNominalNegociable;
	}

	/**
	 * Gets the tenencia total.
	 *
	 * @return the tenencia total
	 */
	public BigDecimal getTenenciaTotal() {
		return tenenciaTotal;
	}

	/**
	 * Sets the tenencia total.
	 *
	 * @param tenenciaTotal
	 *            the new tenencia total
	 */
	public void setTenenciaTotal(BigDecimal tenenciaTotal) {
		this.tenenciaTotal = tenenciaTotal;
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
	 *            the new segmento
	 */
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	
}