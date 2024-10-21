package ar.com.santanderrio.obp.servicios.login.dao;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.login.entity.ProxyLoginResponse;

/**
 * The Interface ProxyLoginDAOImpl.
 */
public interface ProxyLoginDAO {

	/**
	 * Obtener token.
	 *
	 * @param cc the cc
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	ProxyLoginResponse obtenerToken(CredencialCliente cc) throws DAOException;

	/**
	 * Obtener token login.
	 *
	 * @param cc the cc
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	ProxyLoginResponse obtenerTokenLogin(CredencialCliente cc) throws DAOException;

	/**
	 * Sets the pin.
	 *
	 * @param cc the cc
	 * @param entity the entity
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	ProxyLoginResponse setPin(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException;

	/**
	 * Sets the password.
	 *
	 * @param cc the cc
	 * @param entity the entity
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	ProxyLoginResponse setPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException;

	/**
	 * Sets the credentials.
	 *
	 * @param altaSGIClaveIn the alta SGI clave in
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	ProxyLoginResponse setCredentials(AltaSGIClaveInEntity altaSGIClaveIn) throws DAOException;

	/**
	 * Sets the pin and password.
	 *
	 * @param cc the cc
	 * @param entity the entity
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	ProxyLoginResponse setPinAndPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException;

	/**
	 * Update pin password.
	 *
	 * @param cc the cc
	 * @return the proxy login response
	 * @throws DAOException the DAO exception
	 */
	ProxyLoginResponse updatePinPassword(CredencialCliente cc) throws DAOException;

}
