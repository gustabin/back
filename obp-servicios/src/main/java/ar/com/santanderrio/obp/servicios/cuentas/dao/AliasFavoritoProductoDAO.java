/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AliasFavoritoProducto;

/**
 * The Interface AliasFavoritoProductoDAO.
 */
public interface AliasFavoritoProductoDAO {

	/**
	 * Actualiza favorito.
	 *
	 * @param nup
	 *            the nup
	 * @param nroCtaProducto
	 *            the nro cta producto
	 * @param favorito
	 *            the favorito
	 * @throws DAOException
	 *             the DAO exception
	 */
	void actualizaFavorito(Long nup, String nroCtaProducto, Boolean favorito) throws DAOException;

	/**
	 * Actualiza alias.
	 *
	 * @param nup
	 *            the nup
	 * @param nroCtaProducto
	 *            the nro cta producto
	 * @param alias
	 *            the alias
	 * @throws DAOException
	 *             the DAO exception
	 */
	void actualizaAlias(Long nup, String nroCtaProducto, String alias) throws DAOException;

	/**
	 * Obtener alias favorito nup.
	 *
	 * @param nup
	 *            the nup
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<AliasFavoritoProducto> obtenerAliasFavoritoNup(Long nup) throws DAOException;
}
