/*
 * 
 */
package ar.com.santanderrio.obp.servicios.perfil.entities;

import java.util.List;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * ModificacionPreguntasSeguridadInEntity.
 *
 * @author Silvina_Luque
 */
public class ModificacionPreguntasSeguridadInEntity {

	/** The cliente. */
	private Cliente cliente;

	/** Cantidad de preguntas. */
	private String cantidadPreguntas;

	/** Preguntas. */
	private List<RespuestaSeguridadEntity> respuestasSeguridad;

	/**
	 * Gets the cantidad preguntas.
	 *
	 * @return the cantidad preguntas
	 */
	public String getCantidadPreguntas() {
		return cantidadPreguntas;
	}

	/**
	 * Sets the cantidad preguntas.
	 *
	 * @param cantidadPreguntas
	 *            the new cantidad preguntas
	 */
	public void setCantidadPreguntas(String cantidadPreguntas) {
		this.cantidadPreguntas = cantidadPreguntas;
	}

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
	 * Gets the respuestas seguridad.
	 *
	 * @return the respuestas seguridad
	 */
	public List<RespuestaSeguridadEntity> getRespuestasSeguridad() {
		return respuestasSeguridad;
	}

	/**
	 * Sets the respuestas seguridad.
	 *
	 * @param respuestasSeguridad
	 *            the new respuestas seguridad
	 */
	public void setRespuestasSeguridad(List<RespuestaSeguridadEntity> respuestasSeguridad) {
		this.respuestasSeguridad = respuestasSeguridad;
	}

}
