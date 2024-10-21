/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.comun.manager;

import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetalleCustodiaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.InicioInversionesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TarjetaTenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaConsolidadaView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.TenenciaPorProductoInView;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.InicioFondoView;

/**
 * The Interface InversionesManager.
 */
public interface InversionesManager {

	/**
	 * Inicio fondos.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<InicioFondoView> inicioInversiones(InicioInversionesViewRequest request);

	/**
	 * Obtener tenencia Consolidada por producto.
	 *
	 * @param estadisticasTotales
	 *            the estadisticas totales
	 * @return the respuesta
	 */
	Respuesta<TenenciaConsolidadaView> obtenerTenenciaConsolidadaPorProducto(
			TenenciaPorProductoInView estadisticasTotales);

	/**
	 * Obtener tenencia consolidada por producto B priv.
	 *
	 * @param estadisticasTotales
	 *            the estadisticas totales
	 * @return the respuesta
	 */
	Respuesta<TenenciaConsolidadaBPrivView> obtenerTenenciaConsolidadaPorProductoBPriv(
			TenenciaPorProductoInView estadisticasTotales);
	
	
	/**
	 * Retorna la tenencia consolidada en Pesos y Dolares para la tarjeta home.
	 *
	 * @param requestView
	 *            the request view
	 * @param banca : banca privada o retail
	 * @return the respuesta
	 */
	Respuesta<TarjetaTenenciaConsolidadaView> obtenerTenenciaConsolidadaHome(InicioInversionesViewRequest requestView, TipoBancaEnum banca);
	
	/**
	 * Obtener detalle cuenta custodia por producto.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the respuesta
	 */
	Respuesta<DetalleCustodiaView> obtenerDetalleCuentaCustodiaPorProducto(DetalleCustodiaInView detalleIn);

	Response exportarTenenciaConsolidada(TipoBancaEnum tipoBanca);
	
}
