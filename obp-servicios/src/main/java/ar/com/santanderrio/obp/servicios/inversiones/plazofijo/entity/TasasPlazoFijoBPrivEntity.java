package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

public class TasasPlazoFijoBPrivEntity {

	/** The moneda. */
	@Field
	private String moneda;

	/** The plazo. */
	@Field
	private String plazo;

	/** The importe 12 enteros, 2 decimales. */
	@Field
	private String importe;

	/** The tasa nominal baja 3 enteros, 5 decimales. */
	@Field
	private String tasaNominalBaja;

	/** The tasaNominalCanal 3 enteros, 5 decimales. */
	@Field
	private String tasaNominalCanal;

	/** Codigo de plazo fijo. */
	@Field
	private String tipoPF;

	/** The descripcion subproducto. */
	@Field
	private String descripcionSubproducto;

	/** The porcentajePenalizacion 3 enteros, 5 decimales. */
	@Field
	private String porcentajePenalizacion;

	/** The importe minimo 12 enteros, 2 decimales . */
	@Field
	private String importeMinimo;
	
	/** The Tasa minima variable 4 enteros, 5 decimales. */
	@Field
	private String tasaMinimaVariable;
	
	/** The Tasa maxima variable 4 enteros, 5 decimales. */
	@Field
	private String tasaMaximaVariable;
	
	/** The Tasa inc Spread 4 enteros, 5 decimales. */
	@Field
	private String tasaIncSpread;

	/** The Plazo minimo precancelacion 5 enteros. */
	@Field
	private String plazoMinimoPrecancelacion;
	
	/** The Spread neg 4 enteros, 5 decimales. */
	@Field
	private String spreadNeg;
	
	/** The Tasa minima variable garantizada 4 enteros, 5 decimales. */
	@Field
	private String tasaMinimaVariableGarantizada;
	
	/** The Tasa dia liq variable */
	@Field
	private String tasaDiaLiqVariable;
	
	/** The Num dias liquidacion desde 2 enteros. */
	@Field
	private String numDiasLiquidacionDesde;
	
	/** The Num dias liquidacion hasta 2 enteros. */
	@Field
	private String numDiasLiquidacionHasta;
	
	/** The Frecuencia de revision de tasas 2 enteros. */
	@Field
	private String frecuenciaRevisionTasas;
	
	/** The Tipo frecuencia de revision. */
	@Field
	private String tipoFrecuenciaRevision;
	
	/** The Num dias liquidacion 2 enteros. */
	@Field
	private String numDiasLiquidacion;

	
	
	/**
	 * Constructor vacio
	 */
	public TasasPlazoFijoBPrivEntity() {
		super();
	}

	/**
	 * @param moneda
	 * @param plazo
	 * @param importe
	 * @param tasaNominalBaja
	 * @param tasaNominalCanal
	 * @param tipoPF
	 * @param descripcionSubproducto
	 * @param porcentajePenalizacion
	 * @param importeMinimo
	 * @param tasaMinimaVariable
	 * @param tasaMaximaVariable
	 * @param tasaIncSpread
	 * @param plazoMinimoPrecancelacion
	 * @param spreadNeg
	 * @param tasaMinimaVariableGarantizada
	 * @param tasaDiaLiqVariable
	 * @param numDiasLiquidacionDesde
	 * @param numDiasLiquidacionHasta
	 * @param frecuenciaRevisionTasas
	 * @param tipoFrecuenciaRevision
	 * @param numDiasLiquidacion
	 */
	public TasasPlazoFijoBPrivEntity(String moneda, String plazo, String importe, String tasaNominalBaja,
			String tasaNominalCanal, String tipoPF, String descripcionSubproducto, String porcentajePenalizacion,
			String importeMinimo) {
		super();
		this.moneda = moneda;
		this.plazo = plazo;
		this.importe = importe;
		this.tasaNominalBaja = tasaNominalBaja;
		this.tasaNominalCanal = tasaNominalCanal;
		this.tipoPF = tipoPF;
		this.descripcionSubproducto = descripcionSubproducto;
		this.porcentajePenalizacion = porcentajePenalizacion;
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the moneda.
	 *
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * Sets the moneda.
	 *
	 * @param moneda
	 *            the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
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
	 *            the plazo to set
	 */
	public void setPlazo(String plazo) {
		this.plazo = plazo;
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
	 * Gets the tasa nominal baja.
	 *
	 * @return the tasaNominalBaja
	 */
	public String getTasaNominalBaja() {
		return tasaNominalBaja;
	}

	/**
	 * Sets the tasa nominal baja.
	 *
	 * @param tasaNominalBaja
	 *            the tasaNominalBaja to set
	 */
	public void setTasaNominalBaja(String tasaNominalBaja) {
		this.tasaNominalBaja = tasaNominalBaja;
	}

	/**
	 * Gets the tasa nominal canal.
	 *
	 * @return the tasaNominalCanal
	 */
	public String getTasaNominalCanal() {
		return tasaNominalCanal;
	}

	/**
	 * Sets the tasa nominal canal.
	 *
	 * @param tasaNominalCanal
	 *            the tasaNominalCanal to set
	 */
	public void setTasaNominalCanal(String tasaNominalCanal) {
		this.tasaNominalCanal = tasaNominalCanal;
	}

	/**
	 * Gets the tipo PF.
	 *
	 * @return the tipoPF
	 */
	public String getTipoPF() {
		return tipoPF;
	}

	/**
	 * Sets the tipo PF.
	 *
	 * @param tipoPF
	 *            the tipoPF to set
	 */
	public void setTipoPF(String tipoPF) {
		this.tipoPF = tipoPF;
	}

	/**
	 * Gets the descripcion subproducto.
	 *
	 * @return the descripcionSubproducto
	 */
	public String getDescripcionSubproducto() {
		return descripcionSubproducto;
	}

	/**
	 * Sets the descripcion subproducto.
	 *
	 * @param descripcionSubproducto
	 *            the descripcionSubproducto to set
	 */
	public void setDescripcionSubproducto(String descripcionSubproducto) {
		this.descripcionSubproducto = descripcionSubproducto;
	}

	/**
	 * Gets the porcentaje penalizacion.
	 *
	 * @return the porcentajePenalizacion
	 */
	public String getPorcentajePenalizacion() {
		return porcentajePenalizacion;
	}

	/**
	 * Sets the porcentaje penalizacion.
	 *
	 * @param porcentajePenalizacion
	 *            the porcentajePenalizacion to set
	 */
	public void setPorcentajePenalizacion(String porcentajePenalizacion) {
		this.porcentajePenalizacion = porcentajePenalizacion;
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importeMinimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the importeMinimo to set
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}
	
	

	public String getTasaMinimaVariable() {
		return tasaMinimaVariable;
	}

	public void setTasaMinimaVariable(String tasaMinimaVariable) {
		this.tasaMinimaVariable = tasaMinimaVariable;
	}

	public String getTasaMaximaVariable() {
		return tasaMaximaVariable;
	}

	public void setTasaMaximaVariable(String tasaMaximaVariable) {
		this.tasaMaximaVariable = tasaMaximaVariable;
	}

	public String getTasaIncSpread() {
		return tasaIncSpread;
	}

	public void setTasaIncSpread(String tasaIncSpread) {
		this.tasaIncSpread = tasaIncSpread;
	}

	public String getPlazoMinimoPrecancelacion() {
		return plazoMinimoPrecancelacion;
	}

	public void setPlazoMinimoPrecancelacion(String plazoMinimoPrecancelacion) {
		this.plazoMinimoPrecancelacion = plazoMinimoPrecancelacion;
	}

	public String getSpreadNeg() {
		return spreadNeg;
	}

	public void setSpreadNeg(String spreadNeg) {
		this.spreadNeg = spreadNeg;
	}

	public String getTasaMinimaVariableGarantizada() {
		return tasaMinimaVariableGarantizada;
	}

	public void setTasaMinimaVariableGarantizada(String tasaMinimaVariableGarantizada) {
		this.tasaMinimaVariableGarantizada = tasaMinimaVariableGarantizada;
	}

	public String getTasaDiaLiqVariable() {
		return tasaDiaLiqVariable;
	}

	public void setTasaDiaLiqVariable(String tasaDiaLiqVariable) {
		this.tasaDiaLiqVariable = tasaDiaLiqVariable;
	}

	public String getNumDiasLiquidacionDesde() {
		return numDiasLiquidacionDesde;
	}

	public void setNumDiasLiquidacionDesde(String numDiasLiquidacionDesde) {
		this.numDiasLiquidacionDesde = numDiasLiquidacionDesde;
	}

	public String getNumDiasLiquidacionHasta() {
		return numDiasLiquidacionHasta;
	}

	public void setNumDiasLiquidacionHasta(String numDiasLiquidacionHasta) {
		this.numDiasLiquidacionHasta = numDiasLiquidacionHasta;
	}

	public String getFrecuenciaRevisionTasas() {
		return frecuenciaRevisionTasas;
	}

	public void setFrecuenciaRevisionTasas(String frecuenciaRevisionTasas) {
		this.frecuenciaRevisionTasas = frecuenciaRevisionTasas;
	}

	public String getTipoFrecuenciaRevision() {
		return tipoFrecuenciaRevision;
	}

	public void setTipoFrecuenciaRevision(String tipoFrecuenciaRevision) {
		this.tipoFrecuenciaRevision = tipoFrecuenciaRevision;
	}

	public String getNumDiasLiquidacion() {
		return numDiasLiquidacion;
	}

	public void setNumDiasLiquidacion(String numDiasLiquidacion) {
		this.numDiasLiquidacion = numDiasLiquidacion;
	}	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("moneda", moneda).append("plazo", plazo).append("importe", importe)
				.append("tasaNominalBaja", tasaNominalBaja).append("tasaNominalCanal", tasaNominalCanal)
				.append("tipoPF", tipoPF).append("descripcionSubproducto", descripcionSubproducto)
				.append("porcentajePenalizacion", porcentajePenalizacion).append("importeMinimo", importeMinimo)				
				.append("tasaMinimaVariable", tasaMinimaVariable).append("tasaMaximaVariable", tasaMaximaVariable)
				.append("tasaIncSpread", tasaIncSpread).append("plazoMinimoPrecancelacion", plazoMinimoPrecancelacion)
				.append("spreadNeg", spreadNeg).append("tasaMinimaVariableGarantizada", tasaMinimaVariableGarantizada)
				.append("tasaDiaLiqVariable", tasaDiaLiqVariable).append("numDiasLiquidacionDesde", numDiasLiquidacionDesde)
				.append("numDiasLiquidacionHasta", numDiasLiquidacionHasta).append("frecuenciaRevisionTasas", frecuenciaRevisionTasas)
				.append("tipoFrecuenciaRevision", tipoFrecuenciaRevision).append("numDiasLiquidacion", numDiasLiquidacion)
				.toString();
	}

}

