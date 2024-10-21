/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager;

import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.CondicionesGeneralesCuentaCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ListarOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BuscadorEspeciesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteAceptacionCNVRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteCnvViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompra;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompraView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaLicitacionCanje;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaOrdenCompraVenta;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteSuscripcionLicitacion;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CondicionesGeneralesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionBPrivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultaOperacionesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultaOrdenesEstadisticaRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultaOrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConsultarOrdenesPorCuentaOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.DetalleTituloInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.DetalleTitulosBPView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticasTotalesInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionCanjeView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarLicitacionViewResp;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FinalizarReversarOrdenView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteSuscripcionLicitacionCanje;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVIgentesBprivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVigentesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewResponseBPriv;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ObtenerComprobanteViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ReversaRequestView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ReversarOrdenViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionCanjeResponseView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.TenenciaTitulosOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.ConsultarOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.EstadisticasOrdenesVentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaRequestView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaViewResponse;

/**
 * The Interface TitulosManager.
 *
 * @author juan.pablo.picate
 */
public interface TitulosManager {

	/**
	 * Consultar ordenes licitaciones banca personal.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesRTL();

	/**
	 * Consultar ordenes licitaciones banca personal.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesBPriv();

	/**
	 * Obtiene las licitaciones vigentes de banca personal.
	 *
	 * @return the respuesta
	 */
	Respuesta<LicitacionesVigentesOutView> licitacionesVigentes();

	/**
	 * Obtiene las licitaciones vigentes de banca privada.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<LicitacionesVIgentesBprivOutView> licitacionesVigentesBPriv(NuevaLicitacionViewReq request);

	/**
	 * Nueva licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<NuevaLicitacionViewResponse> nuevaLicitacion(NuevaLicitacionViewReq request);

	/**
	 * Nueva licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<NuevaLicitacionViewResponseBPriv> nuevaLicitacionBPriv(NuevaLicitacionViewReq request);

	/**
	 * Reversar orden.
	 * 
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacion(ReversaRequestView request);

	/**
	 * Grabar estadistica para el paso 1 de reversar.
	 */
	void grabarEstadisticaReversa(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Grabar estadistica para el paso 1 de reversar.
	 */
	void grabarEstadisticaReversaBpriv(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Grabar estadistica al ver el detalle de la orden.
	 */
	void grabarEstadisticaDetalleOrden(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Grabar estadistica del comprobante de reversa.
	 */
	void grabarEstadisticaComprobanteReversa(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Grabar estadistica del comprobante de reversa Bpriv.
	 */
	void grabarEstadisticaComprobanteReversaBpriv(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Realiza la simulacion la suscripcion a la licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<SimularLicitacionViewResponse> simularLicitacion(SimularLicitacionRequest request);

	/**
	 * Realiza configurar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ConfigurarLicitacionOutView> configurarLicitacion(ConfigurarLicitacionViewReq request);

	/**
	 * Realiza configurar licitacion BPriv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ConfigurarLicitacionBPrivOutView> configurarLicitacionBPriv(ConfigurarLicitacionViewReq request);

	/**
	 * Finalizar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<FinalizarLicitacionViewResp> finalizarLicitacion(FinalizarLicitacionRequest request);

	/**
	 * Finalizar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<FinalizarLicitacionViewResp> finalizarLicitacionBPriv(FinalizarLicitacionRequest request);

	/**
	 * Graba estadistica y devuelve legales.
	 *
	 * @return the respuesta
	 */
	Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacion();

	/**
	 * Graba estadistica y devuelve legales.
	 *
	 * @return the respuesta
	 */
	Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacionBPriv();

	/**
	 * Realiza configurar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosRTL(EstadisticasTotalesInView request);

	/**
	 * Descargar comprobante Suscripcion PDF.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionPDF(ComprobanteSuscripcionLicitacion viewRequest);

	/**
	 * Consultar OperacionesText.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ConsultaOperacionesView> consultarOperacionesText(ConsultaOperaciones viewRequest);

	/**
	 * Condiciones generales cuenta custodia.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<CondicionesGeneralesCuentaCustodiaDTO> condicionesGeneralesCuentaCustodia(
			CondicionesGeneralesViewRequest viewRequest);

	/**
	 * Firmar cuentas.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<String> firmarCuentas(FirmarCuentasViewRequest request);

	/**
	 * Firmar cuentas B priv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<String> firmarCuentasBPriv(FirmarCuentasViewRequest request);

	/**
	 * Descargar comprobante reversa PDF.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDF(ComprobanteReversaLicitacion viewRequest);

	/**
	 * Descargar comprobante reversa PDF BPriv.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDFBPriv(ComprobanteReversaLicitacion viewRequest);

	/**
	 * Graba estadistica Consulta Detalle Operaciones.
	 *
	 * @param viewRequest the view request
	 */
	void grabarEstadisticaDetalleOperaciones(ListarOperaciones viewRequest);

	/**
	 * Descargar comprobante Aceptacion CNV.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteAceptacionCNV(ComprobanteAceptacionCNVRequest request);

	/**
	 * Graba estadisticas cuando lista operaciones segun compra o venta.
	 *
	 * @param viewRequest the view request
	 */
	void grabarEstadisticaListarOperaciones(ListarOperaciones viewRequest);

	/**
	 * Listar comprobantes CNV.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ListarComprobantesViewResponse> listarComprobantesCNV(ListarComprobantesViewRequest viewRequest);

	/**
	 * Obtener comprobante CNV.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ComprobanteCnvViewResponse> obtenerComprobanteCNV(ObtenerComprobanteViewRequest viewRequest);

	/**
	 * Consultar condiciones generales CC.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConfigurarLicitacionOutView> consultarCondicionesGeneralesCC();

	/**
	 * Firmar cuentas de orden de compras.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<String> firmarCuentasOrdenCompras(FirmarCuentasViewRequest request);

	/**
	 * Firmar cuentas orden compras bpriv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<String> firmarCuentasOrdenComprasBpriv(FirmarCuentasViewRequest request);

	/**
	 * Grabar estadistica detalle orden bpriv.
	 */
	void grabarEstadisticaDetalleOrdenBpriv(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Simular licitacion B priv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<SimularLicitacionViewResponse> simularLicitacionBPriv(SimularLicitacionRequest request);

	/**
	 * Consultar operaciones licitacion.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ConsultaOperacionesView> consultarOperacionesLicitacion(TipoBancaEnum tipoBancaEnum);

	/**
	 * Consultar ordenes.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ConsultaOrdenesView> consultarOrdenes(ConsultarOrdenesViewRequest viewRequest);

	/**
	 * Consultar ordenes B priv.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<ConsultaOrdenesView> consultarOrdenesBPriv(ConsultarOrdenesViewRequest viewRequest);

	/**
	 * Grabar estadistica ordenes venta.
	 *
	 * @param estadistica the estadistica
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaOrdenesVenta(EstadisticasOrdenesVentaView estadistica);

	/**
	 * Graba estadistica ver detalle orden compra.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaVerDetalleOrdenCompra();

	/**
	 * Graba estadistica ver detalle orden venta.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaVerDetalleOrdenVenta();

	/**
	 * Graba estadistica buscador orden venta.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaBuscadorOrdenVenta();

	/**
	 * Graba estadistica buscador orden compra.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaBuscadorOrdenCompra();

	/**
	 * Graba estadistica buscador orden venta B priv.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaBuscadorOrdenVentaBPriv();

	/**
	 * Graba estadistica buscador orden compra B priv.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaBuscadorOrdenCompraBPriv();

	/**
	 * Grabar estadistica ordenes venta B priv.
	 *
	 * @param estadistica the estadistica
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaOrdenesVentaBPriv(EstadisticasOrdenesVentaView estadistica);

	/**
	 * Graba estadistica ver detalle orden compra B priv.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaVerDetalleOrdenCompraBPriv();

	/**
	 * Graba estadistica ver detalle orden venta B priv.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabaEstadisticaVerDetalleOrdenVentaBPriv();

	/**
	 * Estadistica detalle licitacion RTL.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> estadisticaDetalleLicitacionRTL();

	/**
	 * Obtiene tenencias de titulos BP.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosBP(EstadisticasTotalesInView request);

	/**
	 * Buscador ordenes de compra.
	 *
	 * @param request       the request
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<BuscadorEspeciesView> busquedaOrdenesCompra(BusquedaOrdenesCompra request, TipoBancaEnum tipoBancaEnum);

	/**
	 * Configuracion ordenes de compra.
	 *
	 * @param request       the request
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompra(ConfiguracionOrdenesCompraRequest request,
			TipoBancaEnum tipoBancaEnum);

	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraRellamado(
			ConfiguracionOrdenesCompraRequest request, TipoBancaEnum tipoBancaEnum);

	/**
	 * Grabar estadistica para el paso 1 de reversar.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaInicioOrdenesCompra(TipoBancaEnum tipoBancaEnum);

	/**
	 * Simular la orden de compra.
	 *
	 * @param request       the request
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ConfirmarOrdenCompraResponse> simularOrdenCompra(ConfirmarOrdenCompraRequest request,
			TipoBancaEnum tipoBancaEnum);

	/**
	 * Descargar comprobante Orden Compra PDF.
	 *
	 * @param viewRequest   the view request
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteOrdenCompraPDF(ComprobanteOrdenCompra viewRequest,
			TipoBancaEnum tipoBancaEnum);

	/**
	 * Grabar estadistica comprobante compra.
	 *
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ComprobanteOrdenCompraView> verComprobanteOrdenCompra(TipoBancaEnum tipoBancaEnum);

	/**
	 * Confirmar la orden de compra.
	 *
	 * @param request       the request
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ConfirmarOrdenCompraResponse> confirmarOrdenCompra(ConfirmarOrdenCompraRequest request,
			TipoBancaEnum tipoBancaEnum);

	/**
	 * Reversar orden compra venta.
	 *
	 * @param preconfirmarReversarView the preconfirmar reversar view
	 * @return the respuesta
	 */
	Respuesta<ReversarCompraVentaViewResponse> ReversarOrdenCompraVenta(
			ReversarCompraVentaRequestView preconfirmarReversarView);

	/**
	 * Finalizar reversar orden.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<FinalizarReversarOrdenView> finalizarReversarOrden(FinalizarReversarOrdenViewRequest viewRequest);

	/**
	 * Finalizar reversar orden B priv.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	Respuesta<FinalizarReversarOrdenView> finalizarReversarOrdenBPriv(FinalizarReversarOrdenViewRequest viewRequest);

	/**
	 * Ver comprobante reversa.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<Void> verComprobanteReversa(ReversarCompraVentaRequestView request);

	/**
	 * Detalle titulo B priv.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	Respuesta<DetalleTitulosBPView> detalleTituloBPriv(DetalleTituloInView view);

	/**
	 * Detalle titulo RTL.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	Respuesta<DetalleTitulosBPView> detalleTituloRTL(DetalleTituloInView view);

	/**
	 * paso 2 de suscribir licitaciones desde grilla de licitaciones vigentes, banca
	 * personal.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ConfigurarLicitacionOutView> licitacionesVigentesGoToLicitar(ConfigurarLicitacionViewReq request);

	/**
	 * paso 2 de suscribir licitaciones desde grilla de licitaciones vigentes, banca
	 * privada.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ConfigurarLicitacionBPrivOutView> licitacionesVigentesGoToLicitarBPriv(
			ConfigurarLicitacionViewReq request);

	/**
	 * Configuracion ordenes compra grilla.
	 */
	/*
	 * Solo guarda la estadistica al momento de llamar la configuracion de orden de
	 * compra desde la grilla de tenencia
	 */
	void configuracionOrdenesCompraGrilla();

	/**
	 * Obtener rendimiento.
	 *
	 * @return the respuesta
	 */
	Respuesta<RendimientoView> obtenerRendimiento();

	/**
	 * Obtener rendimiento B priv.
	 *
	 * @return the respuesta
	 */
	Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv();

	/**
	 * Descargar comprobante reversa PDF.
	 *
	 * @param viewRequest   the view request
	 * @param tipoBancaEnum the tipo banca enum
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteReversaOrdenCompraVenta(ComprobanteReversaOrdenCompraVenta viewRequest,
			TipoBancaEnum tipoBancaEnum);

	/**
	 * Actualizar poder de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionOrdenesCompraResponse> actualizarPoderDeCompra(ConfiguracionOrdenesCompraRequest request);

	Response exportarGrillaTenencias(TipoBancaEnum tipoBanca);

	Respuesta<LicitacionCanjeResponse> obtenerEspeciesCanje(LicitacionCanjeRequest request);

	Respuesta<LicitacionCanjeResponse> obtenerEspeciesCanjeBPriv(LicitacionCanjeRequest request);

	/**
	 * Realiza la simulacion la suscripcion a la licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanje(SimularLicitacionCanjeRequest request);

	Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanje(FinalizarLicitacionRequest request);

	Respuesta<ComprobanteLicitacionOutView> verComprobanteCanje(TipoBancaEnum tipoBancaEnum);

	Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanjeBPriv(SimularLicitacionCanjeRequest request);

	Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionCanjePDF(ComprobanteSuscripcionLicitacionCanje request, TipoBancaEnum tipoBanca);

	/**
	 * Reversar orden.
	 * 
	 * @param request the request
	 * @return the respuesta
	 */
	Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanje(ReversaRequestView request);

	Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDF(ComprobanteReversaLicitacionCanje viewRequest);
	
	Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanjeBPriv(FinalizarLicitacionRequest request);
	
	Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanjeBPriv(ReversaRequestView request);
	
	Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDFBPriv(ComprobanteReversaLicitacionCanje viewRequest);
}
