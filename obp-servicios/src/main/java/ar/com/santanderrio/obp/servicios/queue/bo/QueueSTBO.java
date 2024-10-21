package ar.com.santanderrio.obp.servicios.queue.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.queue.entities.QueueSTDTO;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnStatesEnum;

/**
 * The Interface QueueSTBO.
 */
public interface QueueSTBO {

	/**
	 * Verificar turno.
	 *
	 * @param turnId the turn id
	 * @return the respuesta
	 */
	public Respuesta<QueueSTDTO> verificarTurno(String turnId);

	/**
	 * Crear turno.
	 *
	 * @param clientID the client ID
	 * @return the respuesta
	 */
	public Respuesta<QueueSTDTO> crearTurno(String clientID);

	/**
	 * Actualizar turno.
	 *
	 * @param turnId the turn id
	 * @param turnStatus the turn status
	 * @return the respuesta
	 */
	public Respuesta<Void> actualizarTurno(String turnId, TurnStatesEnum turnStatus);

}
