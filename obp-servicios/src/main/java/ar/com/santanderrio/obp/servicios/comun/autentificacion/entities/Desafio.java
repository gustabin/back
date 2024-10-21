/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * Clase abstracta de definición de metodos comunes a la autorizacion de
 * transacciones. Cada método de autorización de transacciones debe extender de
 * esta clase.
 *
 * @author ignacio.valek
 * @author emilio.watemberg
 * @param <AutentificacionDTO>
 *            the generic type
 * @since Sep 22, 2016.
 */
@Component
public abstract class Desafio<AutentificacionDTO> implements Comparable<Desafio<AutentificacionDTO>> {

	/*
	 * Prioridad seteada por defecto para todos los modos de autentificacion de
	 * transacciones. En un futuro se puede hacer que esta prioridad se levante
	 * adicionalmente de un @Property para el caso que se requiera que un método
	 * de autentificacion sea mas prioritario ante otros independientemente de
	 * lo que se setea en la clase AutentificacionManager
	 */
	/** The prioridad. */
	private int prioridad = 0;

	/** The codigo estadistica solicitud. */
	protected String codigoEstadisticaSolicitud = null;

	/** The codigo estadistica validacion. */
	protected String codigoEstadisticaValidacion = null;

	/** The autentificacion DTO de entrada. */
	protected AutentificacionDTO autentificacionDTO = null;

	/** The verificando si hay desafios. */
	protected boolean soloEstaVerificandoSiHayDesafios = false;

	/**
	 * Solicitar.
	 *
	 * @return the respuesta
	 */
	public abstract Respuesta<AutentificacionDTO> solicitar();

	/**
	 * Ejecutar.
	 *
	 * @param auntentificacionDTO
	 *            the auntentificacion DTO
	 * @return the respuesta
	 */
	public abstract Respuesta<AutentificacionDTO> ejecutar(AutentificacionDTO auntentificacionDTO);

	/**
	 * Gets the prioridad.
	 *
	 * @return the prioridad
	 */
	public int getPrioridad() {
		return prioridad;
	}

	/**
	 * Sets the prioridad.
	 *
	 * @param prioridad
	 *            the new prioridad
	 */
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}

	/**
	 * The Class Comparators.
	 */
	public static class Comparators {

		/** The prioridad. */
		@SuppressWarnings("rawtypes")
		public static Comparator<Desafio> PRIORIDAD = new Comparator<Desafio>() {
			@Override
			public int compare(Desafio object1, Desafio object2) {
				int prioridad1 = object1.getPrioridad();
				int prioridad2 = object2.getPrioridad();
				if (prioridad1 == prioridad2) {
					return 0;
				} else if (prioridad1 > prioridad2) {
					return 1;
				} else {
					return -1;
				}
			}
		};
	}

	/**
	 * Gets the codigo estadistica solicitud.
	 *
	 * @return the codigo estadistica solicitud
	 */
	public String getCodigoEstadisticaSolicitud() {
		return codigoEstadisticaSolicitud;
	}

	/**
	 * Sets the codigo estadistica solicitud.
	 *
	 * @param codigoEstadisticaSolicitud
	 *            the new codigo estadistica solicitud
	 */
	public void setCodigoEstadisticaSolicitud(String codigoEstadisticaSolicitud) {
		this.codigoEstadisticaSolicitud = codigoEstadisticaSolicitud;
	}

	/**
	 * Gets the codigo estadistica validacion.
	 *
	 * @return the codigo estadistica validacion
	 */
	public String getCodigoEstadisticaValidacion() {
		return codigoEstadisticaValidacion;
	}

	/**
	 * Sets the codigo estadistica validacion.
	 *
	 * @param codigoEstadisticaValidacion
	 *            the new codigo estadistica validacion
	 */
	public void setCodigoEstadisticaValidacion(String codigoEstadisticaValidacion) {
		this.codigoEstadisticaValidacion = codigoEstadisticaValidacion;
	}

	/**
	 * Checks if is verificando si hay desafios.
	 *
	 * @return true, if is verificando si hay desafios
	 */
	public boolean isVerificandoSiHayDesafios() {
		return soloEstaVerificandoSiHayDesafios;
	}

	/**
	 * Sets the verificando si hay desafios.
	 *
	 * @param verificandoSiHayDesafios
	 *            the new verificando si hay desafios
	 */
	public void setVerificandoSiHayDesafios(boolean verificandoSiHayDesafios) {
		this.soloEstaVerificandoSiHayDesafios = verificandoSiHayDesafios;
	}

	/**
	 * Gets the autentificacion DTO.
	 *
	 * @return the autentificacion DTO
	 */
	public AutentificacionDTO getAutentificacionDTO() {
		return autentificacionDTO;
	}

	/**
	 * Sets the autentificacion DTO.
	 *
	 * @param autentificacionDTO
	 *            the new autentificacion DTO
	 */
	public void setAutentificacionDTO(AutentificacionDTO autentificacionDTO) {
		this.autentificacionDTO = autentificacionDTO;
	}

}
