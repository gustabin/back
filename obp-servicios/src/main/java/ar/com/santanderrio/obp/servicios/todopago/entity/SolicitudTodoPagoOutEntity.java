/*
 * 
 */
package ar.com.santanderrio.obp.servicios.todopago.entity;

/**
 * The Class SolicitudTodoPagoOutEntity.
 */
public class SolicitudTodoPagoOutEntity {

	/** The resultado. */
	private String resultado;

	/** The error tecnico. */
	private String errorTecnico;

	/** The error amigable. */
	private String errorAmigable;

	/**
	 * Gets the resultado.
	 *
	 * @return the resultado
	 */
	public String getResultado() {
		return resultado;
	}

	/**
	 * Sets the resultado.
	 *
	 * @param resultado
	 *            the new resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	/**
	 * Gets the error tecnico.
	 *
	 * @return the error tecnico
	 */
	public String getErrorTecnico() {
		return errorTecnico;
	}

	/**
	 * Sets the error tecnico.
	 *
	 * @param errorTecnico
	 *            the new error tecnico
	 */
	public void setErrorTecnico(String errorTecnico) {
		this.errorTecnico = errorTecnico;
	}

	/**
	 * Gets the error amigable.
	 *
	 * @return the error amigable
	 */
	public String getErrorAmigable() {
		return errorAmigable;
	}

	/**
	 * Sets the error amigable.
	 *
	 * @param errorAmigable
	 *            the new error amigable
	 */
	public void setErrorAmigable(String errorAmigable) {
		this.errorAmigable = errorAmigable;
	}

}
