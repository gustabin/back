/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.beanio.annotation.Field;

/**
 * The Class ConsultaPrecancelacionImpuestosVencPlazoFijoEntity.
 *
 * @author juan.pablo.picate
 */
public class ConsultaPrecancelacionImpuestosVencPlazoFijoEntity {

	/** The venc imp cod. */
	@Field
	private String vencImpCod;

	/** The venc imp descripcion. */
	@Field
	private String vencImpDescripcion;

	/** 13 enteros 2 decimales. */
	@Field
	private String montoImpuesto;

	/** The momento cobro. */
	@Field
	private String momentoCobro;

	/**
	 * Gets the venc imp cod.
	 *
	 * @return the vencImpCod
	 */
	public String getVencImpCod() {
		return vencImpCod;
	}

	/**
	 * Sets the venc imp cod.
	 *
	 * @param vencImpCod
	 *            the vencImpCod to set
	 */
	public void setVencImpCod(String vencImpCod) {
		this.vencImpCod = vencImpCod;
	}

	/**
	 * Gets the venc imp descripcion.
	 *
	 * @return the vencImpDescripcion
	 */
	public String getVencImpDescripcion() {
		return vencImpDescripcion;
	}

	/**
	 * Sets the venc imp descripcion.
	 *
	 * @param vencImpDescripcion
	 *            the vencImpDescripcion to set
	 */
	public void setVencImpDescripcion(String vencImpDescripcion) {
		this.vencImpDescripcion = vencImpDescripcion;
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
		return new ToStringBuilder(this).append("vencImpCod", vencImpCod)
				.append("vencImpDescripcion", vencImpDescripcion).append("montoImpuesto", montoImpuesto)
				.append("momentoCobro", momentoCobro).toString();
	}

}
