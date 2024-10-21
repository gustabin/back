/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clave.online.view;

/**
 * The Class PreguntaView.
 */
public class PreguntaView extends MetodoAutenticacionView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id pregunta. */
	private String idPregunta;

	/** The pregunta. */
	private String pregunta;

	/** The indice. */
	private Integer indice;

	/**
	 * Gets the id pregunta.
	 *
	 * @return the idPregunta
	 */
	public String getIdPregunta() {
		return idPregunta;
	}

	/**
	 * Sets the id pregunta.
	 *
	 * @param idPregunta
	 *            the idPregunta to set
	 */
	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}

	/**
	 * Gets the pregunta.
	 *
	 * @return the pregunta
	 */
	public String getPregunta() {
		return pregunta;
	}

	/**
	 * Sets the pregunta.
	 *
	 * @param pregunta
	 *            the pregunta to set
	 */
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	/**
	 * Gets the indice.
	 *
	 * @return the indice
	 */
	public Integer getIndice() {
		return indice;
	}

	/**
	 * Sets the indice.
	 *
	 * @param indice
	 *            the indice to set
	 */
	public void setIndice(Integer indice) {
		this.indice = indice;
	}

}
