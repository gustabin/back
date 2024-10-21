/**
 * 
 */
package ar.com.santanderrio.obp.servicios.canalesautomaticos.entity;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

/**
 * The Class AltaCanalAutomaticoOutEntity.
 *
 * @author alejandro_leal
 */
@Record
public class AltaCanalAutomaticoOutEntity {

	/** The header trama. */
	@Field
	private String headerTrama;

	/** The codigo retorno extendido. */
	@Field(handlerName = "codigoRetornoExtendidoHandler")
	private String codigoRetornoExtendido;

	/** NUP - Numero de Unico de Persona *. */
	@Field
	private String numeroDelCliente;

	/** The persona creada. */
	@Field
	private boolean personaCreada = false;

	/**
	 * Gets the header trama.
	 *
	 * @return the headerTrama
	 */
	public String getHeaderTrama() {
		return headerTrama;
	}

	/**
	 * Sets the header trama.
	 *
	 * @param headerTrama
	 *            the headerTrama to set
	 */
	public void setHeaderTrama(String headerTrama) {
		this.headerTrama = headerTrama;
	}

	/**
	 * Gets the codigo retorno extendido.
	 *
	 * @return the codigoRetornoExtendido
	 */
	public String getCodigoRetornoExtendido() {
		return codigoRetornoExtendido;
	}

	/**
	 * Sets the codigo retorno extendido.
	 *
	 * @param codigoRetornoExtendido
	 *            the codigoRetornoExtendido to set
	 */
	public void setCodigoRetornoExtendido(String codigoRetornoExtendido) {
		this.codigoRetornoExtendido = codigoRetornoExtendido;
	}

	/**
	 * Gets the numero del cliente.
	 *
	 * @return the numeroDelCliente
	 */
	public String getNumeroDelCliente() {
		return numeroDelCliente;
	}

	/**
	 * Sets the numero del cliente.
	 *
	 * @param numeroDelCliente
	 *            the numeroDelCliente to set
	 */
	public void setNumeroDelCliente(String numeroDelCliente) {
		this.numeroDelCliente = numeroDelCliente;
	}

	/**
	 * Checks if is persona creada.
	 *
	 * @return true, if is persona creada
	 */
	public boolean isPersonaCreada() {
		return personaCreada;
	}

	/**
	 * Sets the persona creada.
	 *
	 * @param personaCreada
	 *            the new persona creada
	 */
	public void setPersonaCreada(boolean personaCreada) {
		this.personaCreada = personaCreada;
	}

}
