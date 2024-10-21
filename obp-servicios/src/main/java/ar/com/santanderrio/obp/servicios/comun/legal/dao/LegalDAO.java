/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.dao;

import java.util.Map;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * DAO para obtener los legales de la aplicacion.
 * 
 * @author B025331
 *
 */
public interface LegalDAO {

	/**
	 * Obtener todos los legales vigentes al dia de la fecha. Cualquier error o
	 * collection vacia lanza exception.
	 *
	 * @return the map
	 * @throws DAOException
	 *             the DAO exception
	 */
	Map<String, String> obtenerLegales() throws DAOException;

	/**
	 * Obtener el texto del legal segun el codigo recibido.
	 *
	 * @param codigo
	 *            del legal a consultar
	 * @return texto del legal a mostrar
	 * @throws DAOException
	 *             the DAO exception
	 */
	String obtenerLegal(String codigo) throws DAOException;

	/**
	 * Limpia la cache de legales para que impacten los refrescos externos.
	 */
	void limpiarLegales();
}
