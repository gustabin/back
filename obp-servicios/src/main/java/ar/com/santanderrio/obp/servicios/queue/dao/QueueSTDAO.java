package ar.com.santanderrio.obp.servicios.queue.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.queue.entities.TurnResponse;

/**
 * The Interface QueueSTDAO.
 */
public interface QueueSTDAO {

	/**
	 * Verificar turno.
	 *
	 * @param turnId the turn id
	 * @return the turn response
	 * @throws DAOException the DAO exception
	 */
	public TurnResponse verificarTurno(String turnId) throws DAOException;

	/**
	 * Crear turno.
	 *
	 * @param dni the dni
	 * @return the turn response
	 * @throws DAOException the DAO exception
	 */
	public TurnResponse crearTurno(String dni) throws DAOException;

	/**
	 * Actualizar turno.
	 *
	 * @param turnId the turn id
	 * @param turnStatus the turn status
	 * @throws DAOException the DAO exception
	 */
	public void actualizarTurno(String turnId, String turnStatus) throws DAOException;

}
