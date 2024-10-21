/*
 * 
 */
package ar.com.santanderrio.obp.servicios.clientes.dao;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioBloqueadoException;
import ar.com.santanderrio.obp.servicios.exception.RobotException;

/**
 * The Interface CredencialesDAO.
 */
public interface CredencialesDAO {

	/**
	 * Validar credenciales.
	 *
	 * @param cc
	 *            the cc
	 * @return the respuesta
	 * @throws CredencialesException
	 *             the credenciales exception
	 * @throws RobotException
	 *             the robot exception
	 */
	ResumenCliente validarCredenciales(CredencialCliente cc) throws CredencialesException, RobotException;

	/**
	 * Actualizar clave usuario.
	 *
	 * @param credencialCliente
	 *            the credencial cliente
	 * @return the resumen cliente
	 * @throws CredencialesException
	 *             the credenciales exception
	 * @throws CredencialesUsuarioBloqueadoException
	 *             the credenciales usuario bloqueado exception
	 * @throws RobotException
	 *             the robot exception
	 */

	ResumenCliente actualizarClaveUsuario(CredencialCliente credencialCliente)
			throws CredencialesException, CredencialesUsuarioBloqueadoException, RobotException;

	/**
	 * Cambio usuario.
	 *
	 * @param cc
	 *            the cc
	 * @param cambioUsuarioEntity
	 *            the cambio usuario entity
	 * @return the resumen cliente
	 * @throws CredencialesException
	 *             the credenciales exception
	 * @throws RobotException
	 *             the robot exception
	 */
	ResumenCliente cambioUsuario(CredencialCliente cc, CambioUsuarioEntity cambioUsuarioEntity)
			throws CredencialesException, RobotException;

	/**
	 * Validar credenciales proxy login.
	 *
	 * @param cc the cc
	 * @return the resumen cliente
	 * @throws CredencialesException the credenciales exception
	 * @throws RobotException the robot exception
	 */
	ResumenCliente validarCredencialesProxyLogin(CredencialCliente cc) throws CredencialesException, RobotException;

	/**
	 * Cambio usuario proxy login.
	 *
	 * @param cc the cc
	 * @param entity the entity
	 * @param esSoloUsuario the es solo usuario
	 * @return the resumen cliente
	 * @throws CredencialesException the credenciales exception
	 */
	ResumenCliente cambioUsuarioProxyLogin(CredencialCliente cc, CambioUsuarioEntity entity, boolean esSoloUsuario)
			throws CredencialesException;

	/**
	 * Actualizar clave usuario proxy login.
	 *
	 * @param credencialCliente the credencial cliente
	 * @return the resumen cliente
	 * @throws CredencialesException the credenciales exception
	 * @throws CredencialesUsuarioBloqueadoException the credenciales usuario bloqueado exception
	 * @throws RobotException the robot exception
	 */
	ResumenCliente actualizarClaveUsuarioProxyLogin(CredencialCliente credencialCliente)
			throws CredencialesException, CredencialesUsuarioBloqueadoException, RobotException;
	
	ResumenCliente validarCredencialesApiAuth(CredencialCliente in, String csid) throws CredencialesException, RobotException;


}
