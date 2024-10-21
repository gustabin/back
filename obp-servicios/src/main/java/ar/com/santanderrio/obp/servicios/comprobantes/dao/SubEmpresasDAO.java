/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Interface SubEmpresasDAO.
 */
public interface SubEmpresasDAO {

	/**
	 * Obtener sub empresas.Devuelve la matriz que se forma de el parseo del
	 * archivo
	 *
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<String[]> obtenerSubEmpresas() throws DAOException;

	/**
	 * Obtener sub empresa.Busca por fila entre las N columnas una coincidencia
	 * con la empresa, de encontrarla devuelve el primer elemento de esa fila(el
	 * cual seria el codigo a utilizar en el archivo de medios de pago).
	 *
	 * @param empresa
	 *            the empresa
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String obtenerSubEmpresa(String empresa) throws DAOException;

	/**
	 * Validar que se haya cargado correctamente el archivo.
	 *
	 * @return true, if successful
	 */
	boolean cargoPesSubempresasFile();
}
