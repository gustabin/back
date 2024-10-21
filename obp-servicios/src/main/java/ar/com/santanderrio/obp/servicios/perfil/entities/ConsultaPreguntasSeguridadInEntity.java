/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import org.beanio.annotation.Field;
import org.beanio.annotation.Record;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * ConsultaPreguntasSeguridadInEntity.
 *
 * @author Silvina_Luque
 */
@Record
public class ConsultaPreguntasSeguridadInEntity {

	/** The cliente. */
	@Field
	private Cliente cliente;

	/** The recuperar respuestas. */
	@Field
	private String recuperarRespuestas;

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the recuperar respuestas.
	 *
	 * @return the recuperar respuestas
	 */
	public String getRecuperarRespuestas() {
		return recuperarRespuestas;
	}

	/**
	 * Sets the recuperar respuestas.
	 *
	 * @param recuperarRespuestas
	 *            the new recuperar respuestas
	 */
	public void setRecuperarRespuestas(String recuperarRespuestas) {
		this.recuperarRespuestas = recuperarRespuestas;
	}

}
