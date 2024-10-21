/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.ondemand.dao;

import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.impl.OnDemandDAOException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.ondemand.entities.ResumenParams;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenFinanciacion;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualInversiones;
import ar.com.santanderrio.obp.servicios.tarjetas.dao.entities.ResumenMensualTarjetaDTO;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tenencias.dto.ResumenFinancieroDTO;

/**
 * The Interface OndemandDAO.
 */
public interface OndemandDAO {

	/**
	 * Servicio de consulta de resumenes para clientes individuos o select.
	 *
	 * @param resumenMensualCuenta
	 *            the resumen mensual cuenta
	 * @param cuenta
	 *            the cuenta
	 * @return the reporte resumen mensual cuenta
	 * @throws WSODException
	 *             Invoca al servicio de consultaPuntual
	 */
	ReporteResumenMensualCuenta obtenerReporteMensual(ResumenMensualCuenta resumenMensualCuenta, AbstractCuenta cuenta)
			throws WSODException;

	/**
	 * Servicio de consulta de resumenes para clientes advance.
	 *
	 * @param resumenMensualCuenta
	 *            the resumen mensual cuenta
	 * @param cuenta
	 *            the cuenta
	 * @return the reporte resumen mensual cuenta
	 * @throws WSODException
	 *             Invoca al servicio de consultaPuntual
	 */
	ReporteResumenMensualCuenta obtenerReporteMensualAdvance(ResumenMensualCuenta resumenMensualCuenta,
			AbstractCuenta cuenta) throws WSODException;

	/**
	 * Obtener lista resumen.
	 *
	 * @param params
	 *            the params
	 * @return the list
	 * @throws WSODException
	 *             invoca al servicio de Resumen por Fechas
	 */
	List<ResumenMensualCuenta> obtenerListaResumen(ResumenParams params) throws WSODException;

	/**
	 * Servicio.
	 *
	 * @param params
	 *            the params
	 * @return the list
	 * @throws WSODException
	 *             invoca al servicio de Resumen por Fechas
	 */
	List<ResumenMensualCuenta> obtenerListaResumenAdvance(ResumenParams params) throws WSODException;

	/**
	 * Servicio de consulta de listado de resumenes anteriores de tarjetas de
	 * credito.
	 *
	 * @param params
	 *            the params
	 * @return the list
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 */
	List<ResumenMensualTarjetaDTO> obtenerListaResumenesAnterioresTarjeta(ResumenParams params)
			throws WSODException, OnDemandDAOException;

	/**
	 * Servicio de consulta resumen puntual de tarjeta de credito.
	 *
	 * @param reporteSeleccionado
	 *            the reporte seleccionado
	 * @param params
	 *            the params
	 * @return the reporte resumen puntual
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 */
	ReporteResumenPuntual obtenerResumenPuntualPDF(ReporteSeleccionado reporteSeleccionado, ResumenParams params)
			throws WSODException, OnDemandDAOException;

	/**
	 * Servicio de consulta que obtiene la marca de impresion para un cliente
	 * dado.
	 *
	 * @param params
	 *            the params
	 * @return the string
	 * @throws WSODException
	 *             the WSOD exception
	 * @throws OnDemandDAOException
	 *             the on demand DAO exception
	 */
	String obtenerMarcaImpresion(ResumenParams params) throws WSODException, OnDemandDAOException;

	/**
	 * Modificar marca impresion.
	 *
	 * @param params
	 *            the params
	 * @return the string
	 * @throws WSODException
	 *            the WSOD exception
	 * @throws OnDemandDAOException
	 *            the on demand DAO exception
	 */
	void modificarMarcaImpresion(ResumenParams params) throws WSODException, OnDemandDAOException;

	/**
	 * Obtener lista resumen inversiones.
	 *
	 * @param params
	 *             the params
	 * @param isBP
	 *             the isBP
	 * @return the list
	 * @throws WSODException
	 *             the WSOD exception
	 */
	List<ResumenMensualInversiones> obtenerListaResumenInversiones(ResumenParams params, boolean isBP) throws WSODException;

	/**
	 * Obtener reporte mensual inversiones.
	 *
	 * @param resumenMensualInversiones
	 *            the resumen mensual inversiones
	 * @param cuenta
	 *            the cuenta
	 * @param isBP
	 *            the isBP
	 * @return the reporte resumen mensual inversiones
	 * @throws WSODException
	 *            the WSOD exception
	 */
	ReporteResumenMensualInversiones obtenerReporteMensualInversiones(
			ResumenMensualInversiones resumenMensualInversiones, AbstractCuenta cuenta, boolean isBP)
			throws WSODException;
	
	List<ResumenMensualCuenta> obtenerListaResumenBP(ResumenParams params) throws WSODException;
	
	/**
	 * Obtener resumen financiero tenencias PDF.
	 *
	 * @param view the view
	 * @return the reporte resumen financiacion
	 * @throws WSODException the WSOD exception
	 * @throws OnDemandDAOException the on demand DAO exception
	 */
	ReporteResumenFinanciacion obtenerResumenFinancieroTenenciasPDF(ResumenFinancieroDTO dto)
		throws WSODException;
	
	ReporteResumenMensualCuenta obtenerReporteMensualBP(ResumenMensualCuenta resumenMensualCuenta,
            AbstractCuenta cuenta) throws WSODException;
	
	List<ResumenMensualCuenta> obtenerListaComprobantesBancaPrivada(ResumenParams params) throws WSODException;
	
	ReporteResumenMensualCuenta obtenerReporteComprobantesBP(ResumenMensualCuenta resumenMensualCuenta,
            AbstractCuenta cuenta, TipoPDFEnum tipoPDF) throws WSODException;
}
