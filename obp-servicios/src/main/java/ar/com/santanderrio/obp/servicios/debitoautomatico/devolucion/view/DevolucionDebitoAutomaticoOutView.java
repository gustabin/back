package ar.com.santanderrio.obp.servicios.debitoautomatico.devolucion.view;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * The Class DevolucionDebitoAutomaticoOutView.
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class DevolucionDebitoAutomaticoOutView {

	/** The titulo stack. */
	private String tituloStack;
	
	/** The mensaje informativo. */
	private String mensajeInformativo;
	
	/** The mensaje feedback ok. */
	private String mensajeFeedbackOk;

	/** The fecha vencimiento. */
	private String fechaVencimiento;
	
	/** The fecha devolucion. */
	private String fechaDevolucion;
	
	/** The id cliente. */
	private String idCliente;
	
	/**
	 * Gets the titulo stack.
	 *
	 * @return the titulo stack
	 */
	public String getTituloStack() {
		return tituloStack;
	}

	/**
	 * Sets the titulo stack.
	 *
	 * @param tituloStack the new titulo stack
	 */
	public void setTituloStack(String tituloStack) {
		this.tituloStack = tituloStack;
	}

	/**
	 * Gets the mensaje informativo.
	 *
	 * @return the mensaje informativo
	 */
	public String getMensajeInformativo() {
		return mensajeInformativo;
	}

	/**
	 * Sets the mensaje informativo.
	 *
	 * @param mensajeInformativo the new mensaje informativo
	 */
	public void setMensajeInformativo(String mensajeInformativo) {
		this.mensajeInformativo = mensajeInformativo;
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
	 * Gets the fecha devolucion.
	 *
	 * @return the fecha devolucion
	 */
	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	/**
	 * Sets the fecha devolucion.
	 *
	 * @param fechaDevolucion the new fecha devolucion
	 */
	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
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
	
}
