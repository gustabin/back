package ar.com.santanderrio.obp.servicios.nuevarecarga.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.CelularView;

/**
 * The Interface AgendaCelularDAO.
 */
public interface AgendaCelularDAO {

	/**
	 * Agregar celular.
	 *
	 * @param nup the nup
	 * @param numero the numero
	 * @param descripcion the descripcion
	 * @param compania the compania
	 * @throws DAOException the DAO exception
	 */
	public void agregarCelular(Long nup, String numero, String descripcion, String compania) throws DAOException;

	/**
	 * Actualizar alias celular.
	 *
	 * @param nup the nup
	 * @param numero the numero
	 * @param descripcion the descripcion
	 * @throws DAOException the DAO exception
	 */
	public void actualizarAliasCelular(Long nup, String numero, String descripcion) throws DAOException;
	
	/**
	 * Obtener celulares nup.
	 *
	 * @param nup the nup
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	public List<CelularView> obtenerCelularesNup(Long nup) throws DAOException;
	
	/**
	 * Eliminar celular.
	 *
	 * @param nup the nup
	 * @param numero the numero
	 * @throws DAOException the DAO exception
	 */
	public void eliminarCelular(Long nup, String numero) throws DAOException;

	/**
	 * Obtener alias.
	 *
	 * @param nup the nup
	 * @param numero the numero
	 * @return the string
	 * @throws DAOException the DAO exception
	 */
	public String obtenerAlias(Long nup, String numero) throws DAOException;

	/**
	 * Existe celular.
	 *
	 * @param nup the nup
	 * @param numero the numero
	 * @throws DAOException the DAO exception
	 */
	void existeCelular(Long nup, String numero) throws DAOException;
}
