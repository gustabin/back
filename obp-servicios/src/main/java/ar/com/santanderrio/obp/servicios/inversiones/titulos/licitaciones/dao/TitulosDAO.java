/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConfirmarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovable;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultarTenenciaRenovableResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.DownloadArchivoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramo;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCanalTramoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodia;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasCustodiaResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasTitulos;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerSaldoCuentasTitulosResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerTiposPliego;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ObtenerTiposPliegoResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenEntity;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ReversarOrdenResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrden;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.SimularOrdenResponse;

/**
 * DAO de licitaciones.
 *
 * @author marcelo.ruiz
 */

public interface TitulosDAO {

	/**
	 * Evaluacion de riesgo.
	 *
	 * @param request
	 *            the request
	 * @return the evaluacion de riesgo response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ObtenerCuentasTitulosResponse obtenerCuentasTitulos(ObtenerCuentasTitulos request) throws DAOException;

	/**
	 * Consultar tenencia renovable.
	 *
	 * @param request
	 *            the request
	 * @return the consultar tenencia renovable response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultarTenenciaRenovableResponse consultarTenenciaRenovable(ConsultarTenenciaRenovable request)
			throws DAOException;

	/**
	 * Download archivo.
	 *
	 * @param request
	 *            the request
	 * @return the download archivo response
	 * @throws DAOException
	 *             the DAO exception
	 */
	DownloadArchivoResponse downloadArchivo(DownloadArchivo request) throws DAOException;

	/**
	 * Reversar orden.
	 *
	 * @param request
	 *            the request
	 * @return the reversar orden response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ReversarOrdenResponse reversarOrdenLicitacion(ReversarOrdenEntity request) throws DAOException;;

	/**
	 * Obtener licitaciones.
	 *
	 * @param request
	 *            the request
	 * @return the obtener canal tramo response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ObtenerCanalTramoResponse obtenerLicitaciones(ObtenerCanalTramo request) throws DAOException;

	/**
	 * Obtener saldo cuentas custodia.
	 *
	 * @param request
	 *            the request
	 * @return the obtener saldo cuentas custodia response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ObtenerSaldoCuentasCustodiaResponse obtenerSaldoCuentasCustodia(ObtenerSaldoCuentasCustodia request)
			throws DAOException;

	/**
	 * Confirmar orden.
	 *
	 * @param request
	 *            the request
	 * @return the confirmar orden response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConfirmarOrdenResponse confirmarOrden(ConfirmarOrden request) throws DAOException;

	/**
	 * Obtener tipos pliego.
	 *
	 * @param request
	 *            the request
	 * @return the obtener tipos pliego response
	 */
	ObtenerTiposPliegoResponse obtenerTiposPliego(ObtenerTiposPliego request);

	/**
	 * Consultar orden Licitacion.
	 *
	 * @param request
	 *            the request
	 * @return the consultar orden response
	 * @throws DAOException
	 *             the DAO exception
	 */
	ConsultarOrdenResponse consultarOrdenLicitacion(ConsultarOrdenLicitacion request) throws DAOException;

	/**
	 * Simular orden.
	 *
	 * @param request
	 *            the request
	 * @return the simular orden response
	 * @throws DAOException
	 *             the DAO exception
	 */
	SimularOrdenResponse simularOrden(SimularOrden request) throws DAOException;

	/**
	 * Obtener saldo cuentas titulos.
	 *
	 * @param request
	 *            the request
	 * @return the obtener saldo cuentas titulos response
	 */
	ObtenerSaldoCuentasTitulosResponse obtenerSaldoCuentasTitulos(ObtenerSaldoCuentasTitulos request);

}
