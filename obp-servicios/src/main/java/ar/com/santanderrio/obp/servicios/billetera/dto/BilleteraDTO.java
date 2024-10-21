/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dto;

import java.util.List;

/**
 * The Class BilleteraDTO.
 */
public class BilleteraDTO {

	/** The cuenta acreditacion. */
	private Integer cuentaAcreditacion;

	/** The cuenta acreditacion error. */
	private String cuentaAcreditacionError;

	/** The cuenta acreditacion ok. */
	private String cuentaAcreditacionOk;

	/** The fecha. */
	private String fecha;

	/** The hora. */
	private String hora;

	/** The marcar adhesion. */
	private boolean marcarAdhesion;

	/** The medios de pago error. */
	private List<String> mediosDePagoError;

	/** The medios de pago. */
	private List<MedioDePagoBilleteraDTO> mediosDePagoOk;

	/** The nroComprobante. */
	private String nroComprobante;

	/**
	 * Gets the cuenta acreditacion.
	 *
	 * @return the cuentaAcreditacion
	 */
	public Integer getCuentaAcreditacion() {
		return cuentaAcreditacion;
	}

	/**
	 * Gets the cuenta acreditacion error.
	 *
	 * @return the cuentaAcreditacionError
	 */
	public String getCuentaAcreditacionError() {
		return cuentaAcreditacionError;
	}

	/**
	 * Gets the cuenta acreditacion ok.
	 *
	 * @return the cuentaAcreditacionOk
	 */
	public String getCuentaAcreditacionOk() {
		return cuentaAcreditacionOk;
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
	 * Gets the hora.
	 *
	 * @return the hora
	 */
	public String getHora() {
		return hora;
	}

	/**
	 * Gets the marcar adhesion.
	 *
	 * @return the marcarAdhesion
	 */
	public boolean getMarcarAdhesion() {
		return marcarAdhesion;
	}

	/**
	 * Gets the medios de pago error.
	 *
	 * @return the mediosDePagoError
	 */
	public List<String> getMediosDePagoError() {
		return mediosDePagoError;
	}

	/**
	 * Gets the medios de pago ok.
	 *
	 * @return the medios de pago ok
	 */
	public List<MedioDePagoBilleteraDTO> getMediosDePagoOk() {
		return mediosDePagoOk;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nroComprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the cuenta acreditacion.
	 *
	 * @param cuentaAcreditacion
	 *            the cuentaAcreditacion to set
	 */
	public void setCuentaAcreditacion(Integer cuentaAcreditacion) {
		this.cuentaAcreditacion = cuentaAcreditacion;
	}

	/**
	 * Sets the cuenta acreditacion error.
	 *
	 * @param cuentaAcreditacionError
	 *            the cuentaAcreditacionError to set
	 */
	public void setCuentaAcreditacionError(String cuentaAcreditacionError) {
		this.cuentaAcreditacionError = cuentaAcreditacionError;
	}

	/**
	 * Sets the cuenta acreditacion ok.
	 *
	 * @param cuentaAcreditacionOk
	 *            the cuentaAcreditacionOk to set
	 */
	public void setCuentaAcreditacionOk(String cuentaAcreditacionOk) {
		this.cuentaAcreditacionOk = cuentaAcreditacionOk;
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
	 * Sets the hora.
	 *
	 * @param hora
	 *            the hora to set
	 */
	public void setHora(String hora) {
		this.hora = hora;
	}

	/**
	 * Sets the marcar adhesion.
	 *
	 * @param marcarAdhesion
	 *            the new marcar adhesion
	 */
	public void setMarcarAdhesion(boolean marcarAdhesion) {
		this.marcarAdhesion = marcarAdhesion;
	}

	/**
	 * Sets the medios de pago error.
	 *
	 * @param mediosDePagoError
	 *            the mediosDePagoError to set
	 */
	public void setMediosDePagoError(List<String> mediosDePagoError) {
		this.mediosDePagoError = mediosDePagoError;
	}

	/**
	 * Sets the medios de pago ok.
	 *
	 * @param mediosDePagoOk
	 *            the new mediosDePagoOk
	 */
	public void setMediosDePagoOk(List<MedioDePagoBilleteraDTO> mediosDePagoOk) {
		this.mediosDePagoOk = mediosDePagoOk;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the nroComprobante to set
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

}
