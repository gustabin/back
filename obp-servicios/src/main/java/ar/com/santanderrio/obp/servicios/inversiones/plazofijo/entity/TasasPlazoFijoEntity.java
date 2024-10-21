/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * Tasas de un plazo fijo en particular.
 *
 * @author juan.pablo.picate
 */
public class TasasPlazoFijoEntity {

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
				.toString();
	}

}
