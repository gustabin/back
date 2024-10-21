/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.rendimiento.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.DetalleRentabilidadTotalEntity;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.entity.RentabilidadTotalRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.AperturaGraficaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ComparativaCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ComparativaCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleMovimientosPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleMovimientosPeriodoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.DetalleSubclasificacionRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroCarteraEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroComparativoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroComparativoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.FiltroPorFechaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRendimientoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.GraficoRentabilidadRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroCarteraRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.ObtenerFiltroPorFechaRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoConsolidadoRequestEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RendimientoTenenciaEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoEntity;
import ar.com.santanderrio.obp.servicios.inversiones.rendimiento.entity.RentabilidadPeriodoRequestEntity;

/**
 * Conexion al servicio PLDashboardService.
 *
 * @author b039920
 */
public interface RendimientoTenenciaDAO {

	/**
	 * Obtener rendimiento consolidado.
	 *
	 * @param request
	 *            the request
	 * @return the rendimiento tenencia entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	RendimientoTenenciaEntity obtenerRendimientoConsolidado(RendimientoConsolidadoRequestEntity request) throws DAOException;

	/**
	 * Obtener rentabilidad total.
	 *
	 * @param request
	 *            the request
	 * @return the detalle rentabilidad total entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleRentabilidadTotalEntity obtenerRentabilidadTotal(RentabilidadTotalRequestEntity request) throws DAOException;
	
	/**
	 * Obtener rentabilidad periodo.
	 *
	 * @param request
	 *            the request
	 * @return the rentabilidad periodo entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	RentabilidadPeriodoEntity obtenerRentabilidadPeriodo(RentabilidadPeriodoRequestEntity request) throws DAOException;

	/**
	 * Obtener filtro por fecha.
	 *
	 * @param request
	 *            the request
	 * @return the filtro por fecha entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	FiltroPorFechaEntity obtenerFiltroPorFecha(ObtenerFiltroPorFechaRequestEntity request) throws DAOException;
	
	/**
	 * Obtener filtro cartera.
	 *
	 * @param request
	 *            the request
	 * @return the filtro cartera entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	FiltroCarteraEntity obtenerFiltroCartera(ObtenerFiltroCarteraRequestEntity request) throws DAOException;
	
	/**
	 * Obtener detalle rentabilidad.
	 *
	 * @param request
	 *            the request
	 * @return the detalle rentabilidad entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleRentabilidadEntity obtenerDetalleRentabilidad(DetalleRentabilidadRequestEntity request) throws DAOException;
	
	/**
	 * Obtener apertura grafica.
	 *
	 * @param request
	 *            the request
	 * @return the apertura grafica entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	AperturaGraficaEntity obtenerAperturaGrafica(AperturaGraficaRequestEntity request) throws DAOException;
	
	/**
	 * 
	 *
	 * @param request
	 *            the request
	 * @return the grafico rendimiento entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	GraficoRendimientoEntity obtenerGraficoRendimiento(AperturaGraficaRequestEntity request) throws DAOException;
	
	/**
	 * Obtener grafico rentabilidad.
	 *
	 * @param request
	 *            the request
	 * @return the grafico rentabilidad entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	GraficoRentabilidadEntity obtenerGraficoRentabilidad(GraficoRentabilidadRequestEntity request) throws DAOException;
	
	/**
	 * Obtener detalle subclasificacion.
	 *
	 * @param request
	 *            the request
	 * @return the detalle subclasificacion entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleSubclasificacionEntity obtenerDetalleSubclasificacion(DetalleSubclasificacionRequestEntity request) throws DAOException;
	
	/**
	 * Obtener detalle movimientos periodo.
	 *
	 * @param request
	 *            the request
	 * @return the detalle movimientos periodo entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	DetalleMovimientosPeriodoEntity obtenerDetalleMovimientosPeriodo(DetalleMovimientosPeriodoRequestEntity request) throws DAOException;
	
	/**
	 * Obtener filtro comparativo.
	 *
	 * @param request
	 *            the request
	 * @return the filtro comparativo entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	FiltroComparativoEntity obtenerFiltroComparativo(FiltroComparativoRequestEntity request) throws DAOException;
	
	/**
	 * Obtener comparativa cartera.
	 *
	 * @param request
	 *            the request
	 * @return the comparativa cartera entity
	 * @throws DAOException
	 *             the DAO exception
	 */
	ComparativaCarteraEntity obtenerComparativaCartera(ComparativaCarteraRequestEntity request) throws DAOException;
	
	

	
}
