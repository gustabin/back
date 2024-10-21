/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.combos.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;

/**
 * The Interface ConsultarTagsMaximoVezDAO.
 */
public interface ConsultarImportesDeRecargaDAO {

	/**
	 * Busca una sucursal por numero de identificacion.
	 *
	 * @return TagMaximoVez, o null si no existe
	 * @throws DAOException
	 *             si hubo error
	 * 
	 *             ImporteARecargar consultarImporteARecargarPorId(String id)
	 *             throws DAOException;
	 */

	List<Opcion> obtenerImportesMaximosMensuales() throws DAOException;

	/**
	 * Obtener limites de recarga mensual.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerLimitesDeRecargaMensual() throws DAOException;

	/**
	 * Obtener provincias.
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<Opcion> obtenerProvincias() throws DAOException;

	/**
	 * Obtener opcion descripcion.
	 *
	 * @param comboTag
	 *            the combo tag
	 * @param id
	 *            the id
	 * @return the string
	 */
	String obtenerOpcionDescripcion(String comboTag, String id);
}
