/**
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasCbuException;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidad;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestConsultaCBU;
import ar.com.santanderrio.obp.generated.webservices.alias.RequestModificaAlias;
import ar.com.santanderrio.obp.generated.webservices.alias.ResponseAlias;
import ar.com.santanderrio.obp.servicios.alias.exception.AliasCBUCuentaInactivaException;

/**
 * Consumir ws soap de alias.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface AliasCbuDAO {

	/**
	 * Registrar un alias.
	 *
	 * @param request
	 *            the request
	 * @return the response alias
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResponseAlias altaAlias(RequestAlias request) throws DAOException;

	/**
	 * Quitar un alias.
	 *
	 * @param request
	 *            the request
	 * @return the response alias
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResponseAlias bajaAlias(RequestAlias request) throws DAOException;

	/**
	 * Obtener CBU desde alias.
	 *
	 * @param request
	 *            the request
	 * @return the response alias
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResponseAlias obtenerCBUDesdeAlias(RequestConsultaAlias request) throws DAOException;

	/**
	 * Obtener alias desde CBU.
	 *
	 * @param request
	 *            the request
	 * @return the response alias
	 * @throws DAOException
	 *             the DAO exception
	 * @throws AliasCBUCuentaInactivaException
	 *             the alias CBU cuenta inactiva exception
	 * @throws AliasCbuException
	 *             the alias cbu exception
	 */
	ResponseAlias obtenerAliasDesdeCBU(RequestConsultaCBU request)
			throws DAOException, AliasCBUCuentaInactivaException, AliasCbuException;

	/**
	 * Modificar alias.
	 *
	 * @param req
	 *            the req
	 * @return the response alias
	 * @throws DAOException
	 *             the DAO exception
	 */
	ResponseAlias modificarAlias(RequestModificaAlias req) throws DAOException;

	/**
	 * Consultar datos titularidad extendido.
	 *
	 * @param req
	 *            the req
	 * @return the consultar datos titularidad extendido response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultarDatosTitularidadExtendidoResponse consultarDatosTitularidadExtendido(
			ConsultarDatosTitularidadExtendido req) throws DAOException;
	
	/**
	 * Consultar datos titularidad.
	 *
	 * @param req
	 *            the req
	 * @return the consultar datos titularidad response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultarDatosTitularidadResponse consultarDatosTitularidad(ConsultarDatosTitularidad req) throws DAOException;
	
}