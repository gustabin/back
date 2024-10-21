/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.cvv2.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Interface Cvv2BO.
 */
public interface Cvv2BO extends BusinessObject {
	/**
	 * Solicitar Cvv2.
	 * 
	 * @param codigoEstadisticaSolicitud
	 *            the codigo estadistica solicitud
	 * @return the respuesta
	 */
	Respuesta<AutentificacionDTO> solicitarCvv2(String codigoEstadisticaSolicitud);

	/**
	 * Validar Cvv2.
	 *
	 * @param autentificacionDTO
	 *            the autentificacion DTO
	 * @param codigoEstadisticaValidacion
	 *            the codigo estadistica validacion
	 * @return the respuesta
	 */
	Respuesta<AutentificacionDTO> validarCvv2(AutentificacionDTO autentificacionDTO,
			String codigoEstadisticaValidacion);

	/**
	 * Gets the tiene trj cred.
	 *
	 * @return the tiene trj cred
	 */
	boolean getTieneTrjCred();

}
