/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * The Class ImposicionImpuestosPlazoFijoEntity.
 *
 * @author juan.pablo.picate
 */
public class ImposicionImpuestosPlazoFijoEntity {

	/** The descripcion impuesto. */
	@Field
	private String descripcionImpuesto;

	/** The tipo impuesto. */
	@Field
	private String tipoImpuesto;

	/** 12 enteros ,2 decimales. */
	@Field
	private String montoMonedaLocal;

	/** 12 enteros, 2 decimales. */
	@Field
	private String montoMonedaExtranjera;

	/** The momento cobro. */
	@Field
	private String momentoCobro;

	/**
	 * Gets the descripcion impuesto.
	 *
	 * @return the descripcionImpuesto
	 */
	public String getDescripcionImpuesto() {
		return descripcionImpuesto;
	}

	/**
	 * Sets the descripcion impuesto.
	 *
	 * @param descripcionImpuesto
	 *            the descripcionImpuesto to set
	 */
	public void setDescripcionImpuesto(String descripcionImpuesto) {
		this.descripcionImpuesto = descripcionImpuesto;
	}

	/**
	 * Gets the tipo impuesto.
	 *
	 * @return the tipoImpuesto
	 */
	public String getTipoImpuesto() {
		return tipoImpuesto;
	}

	/**
	 * Sets the tipo impuesto.
	 *
	 * @param tipoImpuesto
	 *            the tipoImpuesto to set
	 */
	public void setTipoImpuesto(String tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
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
		return new ToStringBuilder(this).append("descripcionImpuesto", descripcionImpuesto)
				.append("tipoImpuesto", tipoImpuesto).append("montoMonedaLocal", montoMonedaLocal)
				.append("montoMonedaExtranjera", montoMonedaExtranjera).append("momentoCobro", momentoCobro).toString();
	}

}
