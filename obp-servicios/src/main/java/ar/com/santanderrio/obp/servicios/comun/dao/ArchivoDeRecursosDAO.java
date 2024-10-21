/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.types.ExternalPropertyType;

/**
 * The Interface ArchivoDeRecursosDAO.
 */
public interface ArchivoDeRecursosDAO {

	/**
	 * Leer archivo.
	 *
	 * @param fileDestinoPrestamo
	 *            the file destino prestamo
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<String> leerArchivo(ExternalPropertyType fileDestinoPrestamo) throws DAOException;
	/**
	 * Leer archivo.
	 *
	 * @param filename
	 *            the filename
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<String> leerArchivo(String filename) throws DAOException;
}
