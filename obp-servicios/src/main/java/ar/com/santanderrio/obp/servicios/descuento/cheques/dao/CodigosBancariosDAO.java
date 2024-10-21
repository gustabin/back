/*
 * 
 */
package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

/**
 * The Interface CodigosBancariosDAO.
 */
public interface CodigosBancariosDAO {

	/**
	 * Obtener indice codigo bancario.
	 *
	 * @param bancoGirado
	 *            the banco girado
	 * @return the int
	 */
	int obtenerIndiceCodigoBancario(String bancoGirado);
	
}
