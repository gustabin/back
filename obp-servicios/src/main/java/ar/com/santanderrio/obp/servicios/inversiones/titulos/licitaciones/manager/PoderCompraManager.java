/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.AdhesionPDCRequestDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.FinalizarAdhesionPDC;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteActivarPoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompraView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticaComprobantePoderCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarAdhesionPDCResponse;

/**
 * The Interface PoderCompraManager.
 */
public interface PoderCompraManager {

	/**
	 * Simular adhesion a poder de compra para banca personal.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<AdhesionPDCOutDTO> simularAdhesionPDC(AdhesionPDCRequestDTO request);
	
	/**
	 * Simular adhesion a poder de compra para banca privada.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<AdhesionPDCOutDTO> simularAdhesionPDCBPriv(AdhesionPDCRequestDTO request);
	
	/**
	 * Finalizar la adhesion a pdc banca personal.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDC(FinalizarAdhesionPDC request);
	
	/**
	 * Finalizar la adhesion a pdc banca privada.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<FinalizarAdhesionPDCResponse> finalizarAdhesionPDCBPriv(FinalizarAdhesionPDC request);

	/**
	 * Ver comprobante adhesion a poder de compra .
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<ComprobanteOrdenCompraView> verComprobanteActivarPoderComprar(EstadisticaComprobantePoderCompra request);
	
	/**
	 * Descargar comprobante adhesion a poder de compra.
	 *
	 * @param viewRequest
	 *            the view request
	 * @param tipoBancaEnum
	 *            the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarAceptarPoderCompra(ComprobanteActivarPoderCompra viewRequest,
			TipoBancaEnum tipoBancaEnum);

	/**
	 * configurar adhesion Poder de compra.- Venta titulos valores
	 * @param request
	 * @return
	 */
	Respuesta<AdhesionPDCOutDTO> configurarAdhesionPoderCompra(AdhesionPDCRequestDTO request);
	
	/**
	 * Confirmar Adhesion Poder de Compra desde Venta Titulos valores.
	 * @param request
	 * @return
	 */
	Respuesta<FinalizarAdhesionPDCResponse> confirmarAdhesionPDC(FinalizarAdhesionPDC request);
	
	
	/**
	 * Comprobante adhesion Poder de Compra desde Venta Titulos valores.
	 * @return
	 */
	Respuesta<Void> verComprobanteActivarPoderComprarVTV();
	
	/**
	 * Descargar Comprobante adhesion Poder de Compra desde Venta Titulos valores.
	 * @param viewRequest
	 * @return
	 */
	Respuesta<ReporteView> descargarComprobanteActivarPoderComprarVTV(ComprobanteActivarPoderCompra viewRequest);

}
