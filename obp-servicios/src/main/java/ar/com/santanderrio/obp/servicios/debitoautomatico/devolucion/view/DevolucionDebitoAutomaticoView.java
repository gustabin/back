package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class DevolucionDebitoAutomaticoView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DevolucionDebitoAutomaticoView {

	/** The fecha debito. */
	private String fechaDebito;

	/** The id cliente. */
	private String idCliente;
	
	/** The fecha vencimiento. */
	private String fechaVencimiento;
	
	/** The mensaje feedback ok. */
	private String mensajeFeedbackOk;
	
	/** The descarga PDF. */
	private Boolean descargaPDF = Boolean.FALSE;
	
	
	/**
	 * Gets the fecha debito.
	 *
	 * @return the fecha debito
	 */
	public String getFechaDebito() {
		return fechaDebito;
	}

	/**
	 * Sets the fecha debito.
	 *
	 * @param fechaDebito the new fecha debito
	 */
	public void setFechaDebito(String fechaDebito) {
		this.fechaDebito = fechaDebito;
	}

	/**
	 * Gets the id cliente.
	 *
	 * @return the id cliente
	 */
	public String getIdCliente() {
		return idCliente;
	}

	/**
	 * Sets the id cliente.
	 *
	 * @param idCliente the new id cliente
	 */
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * Gets the fecha vencimiento.
	 *
	 * @return the fecha vencimiento
	 */
	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	/**
	 * Sets the fecha vencimiento.
	 *
	 * @param fechaVencimiento the new fecha vencimiento
	 */
	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	/**
	 * Gets the mensaje feedback ok.
	 *
	 * @return the mensaje feedback ok
	 */
	public String getMensajeFeedbackOk() {
		return mensajeFeedbackOk;
	}

	/**
	 * Sets the mensaje feedback ok.
	 *
	 * @param mensajeFeedbackOk the new mensaje feedback ok
	 */
	public void setMensajeFeedbackOk(String mensajeFeedbackOk) {
		this.mensajeFeedbackOk = mensajeFeedbackOk;
	}

	/**
	 * Gets the descarga PDF.
	 *
	 * @return the descarga PDF
	 */
	public Boolean getDescargaPDF() {
		return descargaPDF;
	}

	/**
	 * Sets the descarga PDF.
	 *
	 * @param descargaPDF the new descarga PDF
	 */
	public void setDescargaPDF(Boolean descargaPDF) {
		this.descargaPDF = descargaPDF;
	}
	
}
