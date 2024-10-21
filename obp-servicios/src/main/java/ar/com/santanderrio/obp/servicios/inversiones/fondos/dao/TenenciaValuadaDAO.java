/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CarteraTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.CuentaProductoOnlineEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleCustodiaOnlineEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleFondoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.DetalleTenenciaValuadaTitulosEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TenenciaValuadaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DetalleTenenciaValuadaPFEntity;

/**
 * DAO para acceder a las tenencias valuadas.
 *
 * @author marcelo.ruiz
 */
public interface TenenciaValuadaDAO {

	/**
	 * Obtener tenencia valuada detalle fondo online.
	 *
	 * @param request
	 *            the request
	 * @return the detalle tenencia valuada entitys
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleTenenciaValuadaEntity obtenerTenenciaValuadaDetalleFondoOnline(DetalleFondoRequestEntity request)
			throws DAOException;

	/**
	 * Obtener tenencia valuada detalle fondo online para BP.
	 *
	 * @param request
	 *            the request
	 * @return the detalle tenencia valuada entitys
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleTenenciaValuadaEntity obtenerTenenciaValuadaDetalleFondoOnlineBP(DetalleFondoRequestEntity request)
			throws DAOException;

	/**
	 * Obtener tenencia valuada de Plazos Fijos.
	 *
	 * @param request
	 *            the request
	 * @return the detalle tenencia valuada PF entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleTenenciaValuadaPFEntity obtenerTenenciaValuadaDetallePFOnline(DetalleFondoRequestEntity request)
			throws DAOException;

	/**
	 * Obtener tenencia valuada Total de Carteras.
	 *
	 * @param request
	 *            the request
	 * @return the cartera tenencia valuada entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CarteraTenenciaValuadaEntity obtenerTenenciaValuadaCarteraTotalOnline(TenenciaValuadaCarteraRequestEntity request)
			throws DAOException;

	/**
	 * Obtener tenencia valuada de Cuenta Producto Online.
	 *
	 * @param request
	 *            the request
	 * @return the cuenta producto online entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	CuentaProductoOnlineEntity obtenerTenenciaValuadaCuentaProductoOnline(DetalleFondoRequestEntity request)
			throws DAOException;

	/**
	 * Obtener tenencia valuada de Titulos.
	 *
	 * @param request
	 *            the request
	 * @return the Detalle Tenencia Valuada Titulos Entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleTenenciaValuadaTitulosEntity obtenerTenenciaValuadaDetalleTitulosOnline(DetalleFondoRequestEntity request)
			throws DAOException;

	/**
	 * Obtener tenencia valuada detalle cust online.
	 *
	 * @param request
	 *            the request
	 * @return the detalle custodia online entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleCustodiaOnlineEntity obtenerTenenciaValuadaDetalleCustOnline(DetalleFondoRequestEntity request)
			throws DAOException;

}
