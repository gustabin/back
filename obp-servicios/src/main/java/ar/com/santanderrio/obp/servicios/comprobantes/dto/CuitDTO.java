/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dto;

/**
 * The Class CuitDTO.
 *
 * @author sabrina.cis
 */
public class CuitDTO {

	/** The tipo. */
	private CuitEnum tipo;

	/** The numero. */
	private String numero;

	/**
	 * Instantiates a new cuit DTO.
	 *
	 * @param tipo
	 *            the tipo
	 * @param numero
	 *            the numero
	 */
	public CuitDTO(CuitEnum tipo, String numero) {
		super();
		this.tipo = tipo;
		this.numero = numero;
	}

	/**
	 * Instantiates a new cuit DTO.
	 */
	public CuitDTO() {
		super();
	}

	/** The digito verificador. */
	private String digitoVerificador;

	/**
	 * Instantiates a new cuit.
	 *
	 * @param tipo
	 *            the tipo
	 * @param numero
	 *            the numero
	 * @param digitoVerificador
	 *            the digito verificador
	 */
	public CuitDTO(CuitEnum tipo, String numero, String digitoVerificador) {
		super();
		this.tipo = tipo;
		this.numero = numero;
		this.digitoVerificador = digitoVerificador;
	}

	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public CuitEnum getTipo() {
		return tipo;
	}

	/**
	 * Sets the tipo.
	 *
	 * @param tipo
	 *            the new tipo
	 */
	public void setTipo(CuitEnum tipo) {
		this.tipo = tipo;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Sets the numero.
	 *
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Gets the digito verificador.
	 *
	 * @return the digitoVerificador
	 */
	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	/**
	 * Sets the digito verificador.
	 *
	 * @param digitoVerificador
	 *            the digitoVerificador to set
	 */
	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
	}

}
