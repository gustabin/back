package ar.com.santanderrio.obp.servicios.ejecutivoselect.view;

/**
 * The Class OperadorEjecutivoEnviarMailInView.
 */
public class OperadorEjecutivoEnviarMailInView {

	/** The motivo seleccionado. */
	private String motivoSeleccionado;

	/** The mensaje texto. */
	private String mensajeTexto;

	/**
	 * Instantiates a new operador ejecutivo enviar mail in view.
	 */
	public OperadorEjecutivoEnviarMailInView() {
		super();
	}

	/**
	 * Gets the motivo seleccionado.
	 *
	 * @return the motivo seleccionado
	 */
	public String getMotivoSeleccionado() {
		return motivoSeleccionado;
	}

	/**
	 * Sets the motivo seleccionado.
	 *
	 * @param motivoSeleccionado
	 *            the new motivo seleccionado
	 */
	public void setMotivoSeleccionado(String motivoSeleccionado) {
		this.motivoSeleccionado = motivoSeleccionado;
	}

	/**
	 * Gets the mensaje texto.
	 *
	 * @return the mensaje texto
	 */
	public String getMensajeTexto() {
		return mensajeTexto;
	}

	/**
	 * Sets the mensaje texto.
	 *
	 * @param mensajeTexto
	 *            the new mensaje texto
	 */
	public void setMensajeTexto(String mensajeTexto) {
		this.mensajeTexto = mensajeTexto;
	}

}
