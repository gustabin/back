/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.contrato.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.ContratoInEntity;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;

/**
 * The Interface ContratoDAO.
 *
 * @author pablo.d.gargaglione
 */

public interface ContratoDAO {

	/**
	 * Obtiene el estado de la casilla de verificación de aceptación de
	 * contrato.
	 *
	 * @param input
	 *            the input
	 * @param campoEnum
	 *            the campo enum
	 * @param sinonimoEnum
	 *            the sinonimo enum
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String verContrato(ContratoInEntity input, CampoEnum campoEnum, SinonimoEnum sinonimoEnum) throws DAOException;

	/**
	 * Obtiene el check o marca realizada por el cliente.
	 *
	 * @param input
	 *            the input
	 * @param campoEnum
	 *            the campo enum
	 * @param sinonimoEnum
	 *            the sinonimo enum
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	String aceptarContrato(ContratoInEntity input, CampoEnum campoEnum, SinonimoEnum sinonimoEnum) throws DAOException;

}
