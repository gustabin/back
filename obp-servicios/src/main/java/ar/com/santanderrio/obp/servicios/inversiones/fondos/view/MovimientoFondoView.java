/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.view;

import java.math.BigDecimal;

/**
 * The Class MovimientoFondoView.
 */
public class MovimientoFondoView {

	/** The importe. */
	private BigDecimal importe;

	/** The concepto. */
	private String concepto;

	/** The fecha. */
	private String fecha;

	/** The nro Certificado. */
	private String nroCertificado;

	/** The cantidadCuotapartes. */
	private BigDecimal cantidadCuotapartes;

	/** The valorCuotapartes. */
	private BigDecimal valorCuotapartes;

	/**
	 * Gets the nro certificado.
	 *
	 * @return the nroCertificado
	 */
	public String getNroCertificado() {
		return nroCertificado;
	}

	/**
	 * Sets the nro certificado.
	 *
	 * @param nroCertificado
	 *            the nroCertificado to set
	 */
	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public BigDecimal getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	/**
	 * Gets the concepto.
	 *
	 * @return the concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Sets the concepto.
	 *
	 * @param concepto
	 *            the concepto to set
	 */
	public void setConcepto(String concepto) {
		this.concepto = concepto;
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
	 * Gets the cantidad cuotapartes.
	 *
	 * @return the cantidadCuotapartes
	 */
	public BigDecimal getCantidadCuotapartes() {
		return cantidadCuotapartes;
	}

	/**
	 * Sets the cantidad cuotapartes.
	 *
	 * @param cantidadCuotapartes
	 *            the cantidadCuotapartes to set
	 */
	public void setCantidadCuotapartes(BigDecimal cantidadCuotapartes) {
		this.cantidadCuotapartes = cantidadCuotapartes;
	}

	/**
	 * Gets the valor cuotapartes.
	 *
	 * @return the valorCuotapartes
	 */
	public BigDecimal getValorCuotapartes() {
		return valorCuotapartes;
	}

	/**
	 * Sets the valor cuotapartes.
	 *
	 * @param valorCuotapartes
	 *            the valorCuotapartes to set
	 */
	public void setValorCuotapartes(BigDecimal valorCuotapartes) {
		this.valorCuotapartes = valorCuotapartes;
	}

}
