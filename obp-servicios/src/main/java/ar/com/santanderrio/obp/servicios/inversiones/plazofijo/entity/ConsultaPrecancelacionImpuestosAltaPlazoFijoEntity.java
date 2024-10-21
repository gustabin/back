/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * The Class ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity.
 *
 * @author juan.pablo.picate
 */
public class ConsultaPrecancelacionImpuestosAltaPlazoFijoEntity {

	/** The alta imp cod. */
	@Field
	private String altaImpCod;

	/** The alta imp descripcion. */
	@Field
	private String altaImpDescripcion;

	/** 13 enteros 2 decimales. */
	@Field
	private String montoImpuesto;

	/** The momento cobro. */
	@Field
	private String momentoCobro;

	/**
	 * Gets the alta imp cod.
	 *
	 * @return the altaImpCod
	 */
	public String getAltaImpCod() {
		return altaImpCod;
	}

	/**
	 * Sets the alta imp cod.
	 *
	 * @param altaImpCod
	 *            the altaImpCod to set
	 */
	public void setAltaImpCod(String altaImpCod) {
		this.altaImpCod = altaImpCod;
	}

	/**
	 * Gets the alta imp descripcion.
	 *
	 * @return the altaImpDescripcion
	 */
	public String getAltaImpDescripcion() {
		return altaImpDescripcion;
	}

	/**
	 * Sets the alta imp descripcion.
	 *
	 * @param altaImpDescripcion
	 *            the altaImpDescripcion to set
	 */
	public void setAltaImpDescripcion(String altaImpDescripcion) {
		this.altaImpDescripcion = altaImpDescripcion;
	}

	/**
	 * Gets the monto impuesto.
	 *
	 * @return the montoImpuesto
	 */
	public String getMontoImpuesto() {
		return montoImpuesto;
	}

	/**
	 * Sets the monto impuesto.
	 *
	 * @param montoImpuesto
	 *            the montoImpuesto to set
	 */
	public void setMontoImpuesto(String montoImpuesto) {
		this.montoImpuesto = montoImpuesto;
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
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("altaImpCod", altaImpCod)
				.append("altaImpDescripcion", altaImpDescripcion).append("montoImpuesto", montoImpuesto)
				.append("momentoCobro", momentoCobro).toString();
	}
}
