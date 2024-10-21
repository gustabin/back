/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.bo;

import java.math.BigDecimal;
import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.AperturaGraficaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleRentabilidadTotalDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.DetalleSubclasificionDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroCarteraDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.FiltroPorFechaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.GrillasProductosDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.dto.RentabilidadPeriodoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.FiltroPorFechaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.GraficoRentabilidadView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadPeriodoRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RentabilidadTotalInView;
import ar.com.santanderrio.obp.servicios.inversiones.analisiscartera.view.RequestDashboard;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;

/**
 * The Interface AnalisisCarteraBO.
 */
public interface AnalisisCarteraBO {

	/**
	 * Obtener rentabilidad total.
	 *
	 * @param cliente
	 *            the cliente
	 * @param rentabilidadView
	 *            the rentabilidad view
	 * @param esBancaPrivada
	 *            the es banca privada
	 * @return the detalle rentabilidad total DTO
	 */
	DetalleRentabilidadTotalDTO obtenerRentabilidadTotal(Cliente cliente, RentabilidadTotalInView rentabilidadView, Boolean esBancaPrivada);
	
	/**
	 * Obtener filtro cartera.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<FiltroCarteraDTO> obtenerFiltroCartera(Cliente cliente, TipoBancaEnum tipoBancaEnum);
	
	/**
	 * Obtener filtro por fecha.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<FiltroPorFechaDTO> obtenerFiltroPorFecha(Cliente cliente, FiltroPorFechaRequestView request, TipoBancaEnum tipoBancaEnum);
	
	/**
	 * Obtener rentabilidad periodo.
	 *
	 * @param cliente
	 *            the cliente
	 * @param rentabilidadPeriodoView
	 *            the rentabilidad periodo view
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the respuesta
	 */
	Respuesta<RentabilidadPeriodoDTO> obtenerRentabilidadPeriodo(Cliente cliente, RentabilidadPeriodoRequestView rentabilidadPeriodoView, TipoBancaEnum tipoBanca);
	
	/**
	 * Armar lista escala grafico distribucion rentabilidad.
	 *
	 * @param valorMaximo
	 *            the valor maximo
	 * @return the list
	 */
	List<String> armarListaEscalaGraficoDistribucionRentabilidad(BigDecimal valorMaximo);
	
	/**
	 * Obtener filtro rentabilidad.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the respuesta
	 */
	Respuesta<AperturaGraficaDTO> obtenerFiltroRentabilidad(Cliente cliente, RequestDashboard request, TipoBancaEnum tipoBanca);
	
	/**
	 * Obtener grillas DTO.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @param isMobile
	 *            the is mobile
	 * @return the respuesta
	 */
	Respuesta<GrillasProductosDTO> obtenerGrillasDTO(Cliente cliente, RequestDashboard request, TipoBancaEnum tipoBanca, boolean isMobile);
	
	/**
	 * Obtener grafico rendimiento.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the respuesta
	 */
	Respuesta<GraficoRendimientoView> obtenerGraficoRendimiento(Cliente cliente, RequestDashboard request, TipoBancaEnum tipoBanca);
	
	/**
	 * Obtener detalle sub clasificacion.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the respuesta
	 */
	Respuesta<DetalleSubclasificionDTO> obtenerDetalleSubClasificacion(Cliente cliente, RequestDashboard request, TipoBancaEnum tipoBanca);
	
	/**
	 * Obtener grafico rentabilidad.
	 *
	 * @param cliente
	 *            the cliente
	 * @param request
	 *            the request
	 * @param tipoBanca
	 *            the tipo banca
	 * @return the respuesta
	 */
	Respuesta<GraficoRentabilidadView> obtenerGraficoRentabilidad(Cliente cliente, RequestDashboard request, TipoBancaEnum tipoBanca);
	
}
