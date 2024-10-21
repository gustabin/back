/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tenencias.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.tenencias.entity.TipClienteCitiTenenciasOutEntity;

/**
 * The Interface TenenciasCitiDAO.
 */
public interface TenenciasCitiDAO {

	/**
	 * Se invoca al SP <br>
	 * 
	 * PROCEDURE CONSULTAR_TENENCIAS_2017 ( <br>
	 * p_resultado IN OUT string, <br>
	 * p_error_tecnico OUT string, <br>
	 * p_err_amigable IN OUT string, <br>
	 * l_nup IN LONG, <br>
	 * .
	 *
	 * @param nup
	 *            the nup
	 * @param anioDesde
	 *            the anio desde
	 * @param anioHasta
	 *            the anio hasta
	 * @return the TipClienteCitiTenenciasOutEntity out entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	TipClienteCitiTenenciasOutEntity obtenerTipoCLienteTenencias(String nup, String anioDesde, String anioHasta) throws DAOException;
}