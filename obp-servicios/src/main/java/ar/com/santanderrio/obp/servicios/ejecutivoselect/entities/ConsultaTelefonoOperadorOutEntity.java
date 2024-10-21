package ar.com.santanderrio.obp.servicios.ejecutivoselect.entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class ConsultaTelefonoOperadorOutEntity {

	/** The telefeno operador. */
	@JsonProperty("Telefono")
	private String telefenoOperador;
	
	/** The resultado. */
	@JsonProperty("Resultado")
	private String resultado;


	/**
	 * Instantiates a new consulta telefono operador out DTO.
	 */
	public ConsultaTelefonoOperadorOutEntity() {
		super();
	}


	/**
	 * Gets the telefeno operador.
	 *
	 * @return the telefeno operador
	 */
	public String getTelefenoOperador() {
		return telefenoOperador;
	}


	/**
	 * Sets the telefeno operador.
	 *
	 * @param telefenoOperador the new telefeno operador
	 */
	public void setTelefenoOperador(String telefenoOperador) {
		this.telefenoOperador = telefenoOperador;
	}


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
	 * @param resultado the new resultado
	 */
	public void setResultado(String resultado) {
		this.resultado = resultado;
	};
	
	
}
