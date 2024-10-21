/*
 * 
 */
package ar.com.santanderrio.obp.servicios.extracto.dao;

import javax.xml.datatype.XMLGregorianCalendar;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.extractos.CuentasFirmadasResponse;
import ar.com.santanderrio.obp.generated.webservices.extractos.MovFondosResponse;

/**
 * The Interface ExtractoDAO.
 */
public interface ExtractoDAO {

	/**
	 * Consulta movimientos oly.
	 *
	 * @param cuentaTitulo
	 *            the cuenta titulo
	 * @param codigoFondo
	 *            the codigo fondo
	 * @param fechaDesde
	 *            the fecha desde
	 * @param fechaHasta
	 *            the fecha hasta
	 * @param nup
	 *            the nup
	 * @return the mov fondos response
	 * @throws DAOException
	 *             the DAO exception
	 */
	MovFondosResponse consultaMovimientosOly(String cuentaTitulo, String codigoFondo, XMLGregorianCalendar fechaDesde,
			XMLGregorianCalendar fechaHasta, String nup) throws DAOException;

	/**
	 * Firmas cuentas por nup.
	 *
	 * @param nup
	 *            the nup
	 * @param banca
	 *            the banca
	 * @return the cuentas firmadas response
	 * @throws DAOException
	 *             the DAO exception
	 */
	CuentasFirmadasResponse firmasCuentasPorNup(String nup, String banca) throws DAOException;


}
