/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.legal.bo;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;

/**
 * BO Legal.
 *
 * @author B025331
 */
public interface LegalBO {

	/**
	 * Buscar legal. Por lo general este metodo se debera llamar cuando SEA
	 * bloqueante el legal.
	 *
	 * @param codigoLegal
	 *            the codigo legal
	 * @return the respuesta
	 */
	Respuesta<String> buscarLegal(String codigoLegal);

	/**
	 * Obtener el texto desde la respuesta. Por lo general este metodo se debera
	 * llamar cuando NO SEA bloqueante el legal.
	 *
	 * @param codigoLegal
	 *            the codigo legal
	 * @return the string
	 * @deprecated reemplazara por {@link buscarLegal}
	 */
	@Deprecated
	String obtenerTextoLegal(String codigoLegal);

	/**
	 * Limpiar-refrescar los legales cacheados.
	 *
	 * @return the respuesta
	 */
	Respuesta<Boolean> limpiarLegales();

	/**
	 * Obtener legal.
	 *
	 * @param codigo
	 *            the codigo
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String obtenerLegal(String codigo) throws DAOException;

	/**
	 * Obtener legales por codigo.
	 *
	 * @param codLegal
	 *            the cod legal
	 * @return the string
	 */
	String obtenerLegalesPorCodigo(String codLegal);
}
