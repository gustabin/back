/**
 * 
 */
package ar.com.santanderrio.obp.servicios.rsa.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.rsa.*;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Interface RsaDAO.
 *
 * @author Ignacio_Valek
 */
public interface RsaDAO {

	/**
	 * Analizar.
	 *
	 * @param request
	 *            the request
	 * @return the analyze response2
	 * @throws DAOException
	 *             the DAO exception
	 */
	AnalyzeResponse2 analizar(AnalyzeRequest request) throws DAOException;

	/**
	 * Notificar.
	 *
	 * @param request
	 *            the request
	 * @return the notify response2
	 * @throws DAOException
	 *             the DAO exception
	 */
	NotifyResponse2 notificar(NotifyRequest request) throws DAOException;

	/**
	 * Crear usuario.
	 *
	 * @param requestData
	 *            the request data
	 * @return the creates the user response2
	 * @throws DAOException
	 *             the DAO exception
	 */
	CreateUserResponse2 crearUsuario(CreateUserRequest requestData) throws DAOException;

	/**
	 * Challenge.
	 *
	 * @param request
	 *            the request
	 * @return the challenge response 2
	 * @throws DAOException
	 *             the DAO exception
	 */
	ChallengeResponse2 challenge(ChallengeRequest request) throws DAOException;
	
	
	/**
	 * Update User
	 * @param request
	 * @return
	 * @throws DAOException
	 */
	UpdateUserResponse updateUser(UpdateUserRequest request) throws DAOException;

	/**
	 * Grabar estadistica ejecucion pago multiple.
	 *
	 * @param response
	 * @param cliente
	 * @param analyzeRequest
	 * @param tipoOperacion
	 *
	 */
	void grabarEstadisticaEjecucionRSA(GenericResponse response, Cliente cliente, AnalyzeRequest analyzeRequest, String tipoOperacion);
}
