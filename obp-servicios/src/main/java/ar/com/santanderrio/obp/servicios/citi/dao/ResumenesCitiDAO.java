/*
 * 
 */
package ar.com.santanderrio.obp.servicios.citi.dao;

import java.util.List;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.citi.entities.FechasResumenCitiIn;
import ar.com.santanderrio.obp.servicios.citi.entities.ResumenFechaOutEntity;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;

/**
 * The Interface ResumenesCitiDAO.
 */
public interface ResumenesCitiDAO { 

	/**
	 * Consultar fechas tarjetas.
	 *
	 * @param fechasResumenCitiIn
	 *            the fechas resumen citi in
	 * @param marca
	 *            the marca
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<ResumenMensualTarjetaDTO> consultarFechasTarjetas(FechasResumenCitiIn fechasResumenCitiIn, String marca) throws DAOException;

	/**
	 * Consultar fechas por cuenta.
	 *
	 * @param fechasResumenCitiIn
	 *            the fechas resumen citi in
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	List<ResumenFechaOutEntity> consultarFechasPorCuenta(FechasResumenCitiIn fechasResumenCitiIn) throws DAOException;

	/**
	 * Consultar resumen city por id.
	 *
	 * @param resumenMensualCuenta
	 *            the resumen mensual cuenta
	 * @return the reporte resumen mensual cuenta
	 * @throws DAOException
	 *             the DAO exception
	 * @throws WSODException
	 *             the WSOD exception
	 */
	ReporteResumenMensualCuenta consultarResumenCityPorId(ResumenMensualCuenta resumenMensualCuenta)
			throws DAOException, WSODException;

	/**
	 * Consultar resumenes tarjeta mastercard.
	 *
	 * @param reporteSeleccionado
	 *            the reporte seleccionado
	 * @param marca
	 *            the marca
	 * @return the reporte resumen puntual
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 * @throws WSODException
	 *             the WSOD exception
	 */
	ReporteResumenPuntual consultarResumenesTarjetaMastercard(ReporteSeleccionado reporteSeleccionado, String marca)
			throws OnDemandDAOException, WSODException;
}