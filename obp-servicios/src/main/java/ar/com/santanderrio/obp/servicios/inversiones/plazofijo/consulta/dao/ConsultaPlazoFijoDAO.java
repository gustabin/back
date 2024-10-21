/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.consulta.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.DescripcionAccionAlVencimientoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.PlazoFijoEntity;

/**
 * The Interface ConsultaPlazoFijoDAO.
 *
 * @author juan.pablo.picate
 */
public interface ConsultaPlazoFijoDAO {

	/**
	 * Obtener plazos fijos.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<PlazoFijoEntity> obtenerPlazosFijos() throws DAOException;

	/**
	 * Obtener plazos fijos habilitados.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<PlazoFijoEntity> obtenerPlazosFijosHabilitados() throws DAOException;

	/**
	 * Obtener plazos fijos habilitados por moneda.
	 *
	 * @param moneda
	 *            the moneda
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<PlazoFijoEntity> obtenerPlazosFijosHabilitadosPorMoneda(String moneda) throws DAOException;

	/**
	 * Obtener por codigo.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the plazo fijo entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	PlazoFijoEntity obtenerPorCodigo(String codigo) throws DAOException;

	/**
	 * Obtener descripcion accion al vencimiento.
	 *
	 * @param codigoAccion
	 *            the codigo accion
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	/*
	 * Obtener la descripcion de las acciones de vencimiento
	 * 
	 */
	List<DescripcionAccionAlVencimientoOutEntity> obtenerDescripcionAccionAlVencimiento(String codigoAccion)
			throws DAOException;

	/**
	 * Obtener descripciones accion al vencimiento.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<DescripcionAccionAlVencimientoOutEntity> obtenerDescripcionesAccionAlVencimiento() throws DAOException;
	/**
	 * Vaciar cache plazos fijos.
	 */
	void vaciarCachePlazosFijos();
	
	/**
	 * Vaciar cache plazos fijos.
	 */
	void vaciarCacheAcciones();
	

}
