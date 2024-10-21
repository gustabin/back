/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * The Class PrecancelacionImpuestosPlazoFijoEntity.
 *
 * @author juan.pablo.picate
 */
public class PrecancelacionImpuestosPlazoFijoEntity {

	/** The descrip impuesto. */
	@Field
	private String descripImpuesto;

	/** The cont juris. */
	@Field
	private String contJuris;

	/** 13 enteros , 2 decimales. */
	@Field
	private String montoMonedaLocal;

	/** 13 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera;

	/** The momento cobro. */
	@Field
	private String momentoCobro;

	/**
	 * Gets the descrip impuesto.
	 *
	 * @return the descripImpuesto
	 */
	public String getDescripImpuesto() {
		return descripImpuesto;
	}

	/**
	 * Sets the descrip impuesto.
	 *
	 * @param descripImpuesto
	 *            the descripImpuesto to set
	 */
	public void setDescripImpuesto(String descripImpuesto) {
		this.descripImpuesto = descripImpuesto;
	}

	/**
	 * Gets the cont juris.
	 *
	 * @return the contJuris
	 */
	public String getContJuris() {
		return contJuris;
	}

	/**
	 * Sets the cont juris.
	 *
	 * @param contJuris
	 *            the contJuris to set
	 */
	public void setContJuris(String contJuris) {
		this.contJuris = contJuris;
	}

	/**
	 * Gets the monto moneda local.
	 *
	 * @return the montoMonedaLocal
	 */
	public String getMontoMonedaLocal() {
		return montoMonedaLocal;
	}

	/**
	 * Sets the monto moneda local.
	 *
	 * @param montoMonedaLocal
	 *            the montoMonedaLocal to set
	 */
	public void setMontoMonedaLocal(String montoMonedaLocal) {
		this.montoMonedaLocal = montoMonedaLocal;
	}

	/**
	 * Gets the monto moneda extranjera.
	 *
	 * @return the montoMonedaExtranjera
	 */
	public String getMontoMonedaExtranjera() {
		return montoMonedaExtranjera;
	}

	/**
	 * Sets the monto moneda extranjera.
	 *
	 * @param montoMonedaExtranjera
	 *            the montoMonedaExtranjera to set
	 */
	public void setMontoMonedaExtranjera(String montoMonedaExtranjera) {
		this.montoMonedaExtranjera = montoMonedaExtranjera;
	}

	/**
	 * Gets the momento cobro.
	 *
	 * @return the momentoCobro
	 */
	public String getMomentoCobro() {
		return momentoCobro;
	}

	/**
	 * Sets the momento cobro.
	 *
	 * @param momentoCobro
	 *            the momentoCobro to set
	 */
	public void setMomentoCobro(String momentoCobro) {
		this.momentoCobro = momentoCobro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).toString();
	}
}
