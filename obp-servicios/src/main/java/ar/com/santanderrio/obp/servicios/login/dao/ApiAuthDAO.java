package ar.com.santanderrio.obp.servicios.login.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.login.entity.ApiAuthLoginResponse;

	public interface ApiAuthDAO {
	    
	    
	    
		ApiAuthLoginResponse obtenerToken(CredencialCliente cc) throws DAOException;

		/**
		 * Obtener token login.
		 *
		 * @param cc the cc
		 * @return the proxy login response
		 * @throws DAOException the DAO exception
		 */
		ApiAuthLoginResponse obtenerTokenLogin(CredencialCliente in, String csid) throws DAOException;

		/**
		 * Sets the pin.
		 *
		 * @param cc the cc
		 * @param entity the entity
		 * @return the proxy login response
		 * @throws DAOException the DAO exception
		 */
		ApiAuthLoginResponse setPin(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException;

		/**
		 * Sets the password.
		 *
		 * @param cc the cc
		 * @param entity the entity
		 * @return the proxy login response
		 * @throws DAOException the DAO exception
		 */
		ApiAuthLoginResponse setPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException;

		/**
		 * Sets the credentials.
		 *
		 * @param altaSGIClaveIn the alta SGI clave in
		 * @return the proxy login response
		 * @throws DAOException the DAO exception
		 */
		ApiAuthLoginResponse setCredentials(AltaSGIClaveInEntity altaSGIClaveIn) throws DAOException;

		/**
		 * Sets the pin and password.
		 *
		 * @param cc the cc
		 * @param entity the entity
		 * @return the proxy login response
		 * @throws DAOException the DAO exception
		 */
		ApiAuthLoginResponse setPinAndPassword(CredencialCliente cc, CambioUsuarioEntity entity) throws DAOException;

		/**
		 * Update pin password.
		 *
		 * @param cc the cc
		 * @return the proxy login response
		 * @throws DAOException the DAO exception
		 */
		ApiAuthLoginResponse updatePinPassword(CredencialCliente cc) throws DAOException;
	    
		ApiAuthLoginResponse refreshToken(String jwt, String refreshToken) throws DAOException;

		void logout(String token) throws DAOException;

		List<String> getApiAuthClients(String archivo);
		
		void limpiarCacheApiAuth();
}
