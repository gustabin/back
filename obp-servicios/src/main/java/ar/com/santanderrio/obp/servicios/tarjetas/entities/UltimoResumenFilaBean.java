/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.entities;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The Class UltimoResumenFilaBean.
 */
public class UltimoResumenFilaBean {

	/** The fecha. */
	private String fecha;

	/** The descripcion. */
	private String descripcion;

	/** The comprobante. */
	private String comprobante;

	/** The pesos. */
	private String pesos;

	/** The dolares. */
	private String dolares;

	/** The total. */
	private String total;

	/** The total pesos. */
	private String totalPesos;

	/** The total dolares. */
	private String totalDolares;

	/** The legal. */
	private String legal;

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	public String getComprobante() {
		return comprobante;
	}

	/**
	 * Sets the comprobante.
	 *
	 * @param comprobante
	 *            the comprobante to set
	 */
	public void setComprobante(String comprobante) {
		this.comprobante = comprobante;
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
	 * Gets the dolares.
	 *
	 * @return the dolares
	 */
	public String getDolares() {
		return dolares;
	}

	/**
	 * Sets the dolares.
	 *
	 * @param dolares
	 *            the dolares to set
	 */
	public void setDolares(String dolares) {
		this.dolares = dolares;
	}

	/**
	 * Gets the fecha.
	 *
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Sets the fecha.
	 *
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * Gets the pesos.
	 *
	 * @return the pesos
	 */
	public String getPesos() {
		return pesos;
	}

	/**
	 * Sets the pesos.
	 *
	 * @param pesos
	 *            the pesos to set
	 */
	public void setPesos(String pesos) {
		this.pesos = pesos;
	}

	/**
	 * Gets the legal.
	 *
	 * @return the legal
	 */
	public String getLegal() {
		return legal;
	}

	/**
	 * Sets the legal.
	 *
	 * @param legal
	 *            the legal to set
	 */
	public void setLegal(String legal) {
		this.legal = legal;
	}

	/**
	 * Gets the total.
	 *
	 * @return the total
	 */
	public String getTotal() {
		return total;
	}

	/**
	 * Sets the total.
	 *
	 * @param total
	 *            the total to set
	 */
	public void setTotal(String total) {
		this.total = total;
	}

	/**
	 * Gets the total pesos.
	 *
	 * @return the totalPesos
	 */
	public String getTotalPesos() {
		return totalPesos;
	}

	/**
	 * Sets the total pesos.
	 *
	 * @param totalPesos
	 *            the totalPesos to set
	 */
	public void setTotalPesos(String totalPesos) {
		this.totalPesos = totalPesos;
	}

	/**
	 * Gets the total dolares.
	 *
	 * @return the totalDolares
	 */
	public String getTotalDolares() {
		return totalDolares;
	}

	/**
	 * Sets the total dolares.
	 *
	 * @param totalDolares
	 *            the totalDolares to set
	 */
	public void setTotalDolares(String totalDolares) {
		this.totalDolares = totalDolares;
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("fecha", fecha).append("descripcion", descripcion)
				.append("comprobante", comprobante).append("pesos", pesos).append("dolares", dolares)
				.append("total", total).append("totalPesos", totalPesos).append("totalDolares", totalDolares)
				.append("legal", legal).toString();
	}

}
