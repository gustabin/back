/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import java.util.List;

/**
 * The Class PlazoFijoEntity.
 */
public class PlazoFijoEntity {

	/** The codigo plazo. */
	private String codigoPlazo;

	/** The descripcion. */
	private String descripcion;

	/** The nombre legal. */
	private String nombreLegal;

	/** The habilitado. */
	private String habilitado;

	/** The es interesante. */
	private String esInteresante;

	/** The es precancelable. */
	private String esPrecancelable;

	/** The es ajustable. */
	private String esAjustable;

	/** The es tasa variable. */
	private String esTasaVariable;

	/** The mostrar frec intereses. */
	private String mostrarFrecIntereses;

	/** The ayuda. */
	private String ayuda;

	/** The min dias liq int. */
	private String minDiasLiqInt;

	/** The moneda. */
	private String moneda;

	/** The prioridad. */
	private String prioridad;

	/** The codigo B priv. */
	private String codigoBPriv;

	/** The acciones al vencimiento. */
	private List<AccionAlVencimientoOutEntity> accionesAlVencimiento;

	/** The importe Minino. */
	private String importeMinimo;

	/** The plazo Taza. */
	private String plazoTaza;

	/**
	 * Gets the codigo plazo.
	 *
	 * @return the codigo plazo
	 */
	public String getCodigoPlazo() {
		return codigoPlazo;
	}

	/**
	 * Sets the codigo plazo.
	 *
	 * @param codigoPlazo
	 *            the new codigo plazo
	 */
	public void setCodigoPlazo(String codigoPlazo) {
		this.codigoPlazo = codigoPlazo;
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
	 *            the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the nombre legal.
	 *
	 * @return the nombre legal
	 */
	public String getNombreLegal() {
		return nombreLegal;
	}

	/**
	 * Sets the nombre legal.
	 *
	 * @param nombreLegal
	 *            the new nombre legal
	 */
	public void setNombreLegal(String nombreLegal) {
		this.nombreLegal = nombreLegal;
	}

	/**
	 * Gets the habilitado.
	 *
	 * @return the habilitado
	 */
	public String getHabilitado() {
		return habilitado;
	}

	/**
	 * Sets the habilitado.
	 *
	 * @param habilitado
	 *            the new habilitado
	 */
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	/**
	 * Gets the es interesante.
	 *
	 * @return the es interesante
	 */
	public String getEsInteresante() {
		return esInteresante;
	}

	/**
	 * Sets the es interesante.
	 *
	 * @param esInteresante
	 *            the new es interesante
	 */
	public void setEsInteresante(String esInteresante) {
		this.esInteresante = esInteresante;
	}

	/**
	 * Gets the es precancelable.
	 *
	 * @return the es precancelable
	 */
	public String getEsPrecancelable() {
		return esPrecancelable;
	}

	/**
	 * Sets the es precancelable.
	 *
	 * @param esPrecancelable
	 *            the new es precancelable
	 */
	public void setEsPrecancelable(String esPrecancelable) {
		this.esPrecancelable = esPrecancelable;
	}

	/**
	 * Gets the es ajustable.
	 *
	 * @return the es ajustable
	 */
	public String getEsAjustable() {
		return esAjustable;
	}

	/**
	 * Sets the es ajustable.
	 *
	 * @param esAjustable
	 *            the new es ajustable
	 */
	public void setEsAjustable(String esAjustable) {
		this.esAjustable = esAjustable;
	}

	/**
	 * Gets the es tasa variable.
	 *
	 * @return the es tasa variable
	 */
	public String getEsTasaVariable() {
		return esTasaVariable;
	}

	/**
	 * Sets the es tasa variable.
	 *
	 * @param esTasaVariable
	 *            the new es tasa variable
	 */
	public void setEsTasaVariable(String esTasaVariable) {
		this.esTasaVariable = esTasaVariable;
	}

	/**
	 * Gets the mostrar frec intereses.
	 *
	 * @return the mostrar frec intereses
	 */
	public String getMostrarFrecIntereses() {
		return mostrarFrecIntereses;
	}

	/**
	 * Sets the mostrar frec intereses.
	 *
	 * @param mostrarFrecIntereses
	 *            the new mostrar frec intereses
	 */
	public void setMostrarFrecIntereses(String mostrarFrecIntereses) {
		this.mostrarFrecIntereses = mostrarFrecIntereses;
	}

	/**
	 * Gets the ayuda.
	 *
	 * @return the ayuda
	 */
	public String getAyuda() {
		return ayuda;
	}

	/**
	 * Sets the ayuda.
	 *
	 * @param ayuda
	 *            the new ayuda
	 */
	public void setAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	/**
	 * Gets the min dias liq int.
	 *
	 * @return the min dias liq int
	 */
	public String getMinDiasLiqInt() {
		return minDiasLiqInt;
	}

	/**
	 * Sets the min dias liq int.
	 *
	 * @param minDiasLiqInt
	 *            the new min dias liq int
	 */
	public void setMinDiasLiqInt(String minDiasLiqInt) {
		this.minDiasLiqInt = minDiasLiqInt;
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
	 *            the new moneda
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public String getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the prioridad.
	 *
	 * @param prioridad
	 *            the new prioridad
	 */
	public void setPrioridad(String prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * Gets the codigo B priv.
	 *
	 * @return the codigo B priv
	 */
	public String getCodigoBPriv() {
		return codigoBPriv;
	}

	/**
	 * Sets the codigo B priv.
	 *
	 * @param codigoBPriv
	 *            the new codigo B priv
	 */
	public void setCodigoBPriv(String codigoBPriv) {
		this.codigoBPriv = codigoBPriv;
	}

	/**
	 * Gets the acciones al vencimiento.
	 *
	 * @return the acciones al vencimiento
	 */
	public List<AccionAlVencimientoOutEntity> getAccionesAlVencimiento() {
		return accionesAlVencimiento;
	}

	/**
	 * Sets the acciones al vencimiento.
	 *
	 * @param accionesAlVencimiento
	 *            the new acciones al vencimiento
	 */
	public void setAccionesAlVencimiento(List<AccionAlVencimientoOutEntity> accionesAlVencimiento) {
		this.accionesAlVencimiento = accionesAlVencimiento;
	}

	/**
	 * Gets the importe minimo.
	 *
	 * @return the importe minimo
	 */
	public String getImporteMinimo() {
		return importeMinimo;
	}

	/**
	 * Sets the importe minimo.
	 *
	 * @param importeMinimo
	 *            the new importe minimo
	 */
	public void setImporteMinimo(String importeMinimo) {
		this.importeMinimo = importeMinimo;
	}

	/**
	 * Gets the plazo taza.
	 *
	 * @return the plazo taza
	 */
	public String getPlazoTaza() {
		return plazoTaza;
	}

	/**
	 * Sets the plazo taza.
	 *
	 * @param plazoTaza
	 *            the new plazo taza
	 */
	public void setPlazoTaza(String plazoTaza) {
		this.plazoTaza = plazoTaza;
	}

}
