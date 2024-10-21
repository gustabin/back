/*
 * 
 */
package ar.com.santanderrio.obp.servicios.onboarding.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;

/**
 * The Interface OnboardingDAO.
 */
public interface OnboardingDAO {

	/**
	 * Obtener secciones activas.
	 *
	 * @param nupCliente
	 *            the nup cliente
	 * @param dispositivo
	 *            the dispositivo
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<String> obtenerSeccionesActivas(String nupCliente, String dispositivo) throws DAOException;

	/**
	 * Informar listo.
	 *
	 * @param nupCliente
	 *            the nup cliente
	 * @param dispositivo
	 *            the dispositivo
	 * @param seccion
	 *            the seccion
	 * @return the boolean
	 * @throws DAOException
	 *             the DAO exception
	 */
	Boolean informarListo(String nupCliente, String dispositivo, String seccion) throws DAOException;
}
