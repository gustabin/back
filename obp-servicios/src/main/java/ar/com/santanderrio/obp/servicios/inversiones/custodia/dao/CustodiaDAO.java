/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.custodia.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaBPInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaBPOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.custodia.entity.CustodiaOutEntity;

/**
 * The Interface CustodiaDAO.
 *
 * @author pablo.d.gargaglione
 */
public interface CustodiaDAO {

	/**
	 * Obtiene el valor de una tenencia ol de fondos.
	 *
	 * @param in
	 *            the in
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	CustodiaOutEntity verTenenciaOl(CustodiaInEntity in) throws DAOException;

	/**
	 * Obtiene el valor de una tenencia para BP.
	 *
	 * @param in
	 *            the inCustodiaOutEntity
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	CustodiaBPOutEntity verTenenciaBP(CustodiaBPInEntity in) throws DAOException;

}
