/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
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
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteSuscripcionLicitacionCanje;
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
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeRequest;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionCanjeResponse;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVIgentesBprivOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.LicitacionesVigentesOutView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListaFechasComprobantes;
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
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.AceptacionCondicionesCuentasCustodiaViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.TituloView;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ComprobanteDetalleOperacionLicitacionCanje;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.CuentasOperativasView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionesTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaRequestView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaViewResponse;

/**
 * The Interface TitulosSEI.
 *
 * @author juan.pablo.picate
 */
@Path("/titulos")
public interface TitulosSEI {

	/**
	 * Consulta las ordenes de licitaciones para banca personal.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarOrdenesLicitaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesRTL();

	/**
	 * Consulta las ordenes de licitaciones para banca personal.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultaOrdenesLicitacionesBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesBPriv();

	/**
	 * Al Ver detalle de la orden.
	 */
	@POST
	@Path("/grabarEstadisticaDetalleOrdenBpriv")
	void grabarEstadisticaDetalleOrdenBpriv(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Consulta las licitaciones vigentes.
	 *
	 * @return Respuesta<ConsultarOrdenesOutView>
	 */
	@POST
	@Path("/licitacionesVigentes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<LicitacionesVigentesOutView> licitacionesVigentes();

	/**
	 * Consulta las licitaciones vigentes.
	 *
	 * @param request the request
	 * @return Respuesta<ConsultarOrdenesOutView>
	 */
	@POST
	@Path("/licitacionesVigentesBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<LicitacionesVIgentesBprivOutView> licitacionesVigentesBPriv(NuevaLicitacionViewReq request);

	/**
	 * Nueva licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/nuevaLicitacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<NuevaLicitacionViewResponse> nuevaLicitacion(NuevaLicitacionViewReq request);

	/**
	 * Nueva licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/nuevaLicitacionBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<NuevaLicitacionViewResponseBPriv> nuevaLicitacionBpriv(NuevaLicitacionViewReq request);

	/**
	 * configurarLicitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */

	@POST
	@Path("/configurarLicitacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigurarLicitacionOutView> configurarLicitacion(ConfigurarLicitacionViewReq request);

	/**
	 * configurarLicitacionBPriv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */

	@POST
	@Path("/configurarLicitacionBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigurarLicitacionBPrivOutView> configurarLicitacionBPriv(ConfigurarLicitacionViewReq request);

	/**
	 * Paso 1 de Reversa.
	 */
	@POST
	@Path("/grabarEstadisticaReversa")
	void grabarEstadisticaReversa(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Paso 1 de Reversa Banca Privada.
	 */
	@POST
	@Path("/grabarEstadisticaReversaBpriv")
	void grabarEstadisticaReversaBpriv(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Graba estadistica al ver el detalle de una orden.
	 */
	@POST
	@Path("/grabarEstadisticaDetalleOrden")
	void grabarEstadisticaDetalleOrden(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Graba estadistica del comprobante de reversa.
	 */
	@POST
	@Path("/grabarEstadisticaComprobanteReversa")
	void grabarEstadisticaComprobanteReversa(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Graba estadistica del comprobante de reversa banca privada.
	 */
	@POST
	@Path("/grabarEstadisticaComprobanteReversaBpriv")
	void grabarEstadisticaComprobanteReversaBpriv(ConsultaOrdenesEstadisticaRequest request);

	/**
	 * Reversar orden.
	 * 
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/reversarOrdenLicitacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacion(ReversaRequestView request);

	/**
	 * Simular licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularLicitacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimularLicitacionViewResponse> simularLicitacion(SimularLicitacionRequest request);

	/**
	 * Simular licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularLicitacionBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimularLicitacionViewResponse> simularLicitacionBPriv(SimularLicitacionRequest request);

	/**
	 * Obtener tenencias titulos RTL.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/tenenciasTitulosRTL")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosRTL(EstadisticasTotalesInView request);

	/**
	 * Finalizar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarLicitacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarLicitacionViewResp> finalizarLicitacion(FinalizarLicitacionRequest request);

	/**
	 * Finalizar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarLicitacionBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarLicitacionViewResp> finalizarLicitacionBPriv(FinalizarLicitacionRequest request);

	/**
	 * Ver Comprobante licitacion.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteLicitacion")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacion();

	/**
	 * Ver Comprobante licitacion.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteLicitacionBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacionBPriv();

	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteSuscripcionLicitacionPDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionPDF(ComprobanteSuscripcionLicitacion request);

	/**
	 * Consultar Operaciones.
	 *
	 * @param viewRequest the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/consultarOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultaOperacionesView> consultarOperacionesText(ConsultaOperaciones viewRequest);

	/**
	 * Consultar Ordenes Titulos B. personal.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarOrdenes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultaOrdenesView> consultarOrdenes(ConsultarOrdenesViewRequest viewRequest);

	/**
	 * Consultar Ordenes Titulos B. privada.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarOrdenesBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultaOrdenesView> consultarOrdenesBPriv(ConsultarOrdenesViewRequest viewRequest);

	/**
	 * Consultar Operaciones de licitacion.
	 *
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/consultarOperacionesLicitacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConsultaOperacionesView> consultarOperacionesLicitacion();

	/**
	 * Terminos y condiciones.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/verCondicionesCuentaCustodia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<CondicionesGeneralesCuentaCustodiaDTO> firmarCuentasPorNup(CondicionesGeneralesViewRequest viewRequest);

	/**
	 * Firmar cuentas.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/firmarCuentasCustodia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> firmarCuentas(FirmarCuentasViewRequest request);

	/**
	 * Firmar cuentas B priv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/firmarCuentasCustodiaBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> firmarCuentasBPriv(FirmarCuentasViewRequest request);

	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteReversaLicitacionPDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDF(ComprobanteReversaLicitacion viewRequest);

	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteReversaLicitacionPDFBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDFBPriv(ComprobanteReversaLicitacion viewRequest);

	/**
	 * Graba estadistica Consulta Detalle Operaciones.
	 *
	 * @param viewRequest the view request
	 */
	@POST
	@Path("/grabarEstadisticaDetalleOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaDetalleOperaciones(ListarOperaciones viewRequest);

	/**
	 * Graba estadistica Listar Operaciones, segun sea Compra o Venta.
	 *
	 * @param viewRequest the view request
	 */
	@POST
	@Path("/grabarEstadisticaListarOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void grabarEstadisticaListarOperaciones(ListarOperaciones viewRequest);

	/**
	 * Ver el listado de los comprobantes de Aceptacion contrato CNV.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/listarComprobantesCNV")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ListarComprobantesViewResponse> listarComprobantesCNV(ListarComprobantesViewRequest viewRequest);

	/**
	 * Ver el listado de los comprobantes de Aceptacion contrato CNV.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerComprobanteCNV")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteCnvViewResponse> obtenerComprobanteCNV(ObtenerComprobanteViewRequest viewRequest);

	/**
	 * Descargar comprobante Aceptacion CNV.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteAceptacionCNV")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteAceptacionCNV(ComprobanteAceptacionCNVRequest request);

	/**
	 * Descargar comprobante Aceptacion CNV.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/consultarCondicionesGeneralesCC")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigurarLicitacionOutView> consultarCondicionesGeneralesCC();

	/**
	 * Firmar cuentas orden compras.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/firmarCuentasCustodiaOrdenCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> firmarCuentasOrdenCompras(FirmarCuentasViewRequest request);

	/**
	 * Firmar cuentas orden compras bpriv.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/firmarCuentasCustodiaOrdenCompraBpriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<String> firmarCuentasOrdenComprasBpriv(FirmarCuentasViewRequest request);

	/**
	 * Inicio flujo orden venta titulos.
	 *
	 * @param ingresoView the ingreso view
	 * @return the respuesta
	 */
	@POST
	@Path("/ingresoOrdenVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<List<TituloView>> ingresoOrdenVenta(IngresoOrdenVentaInView ingresoView);

	/**
	 * Buscador de ordenes de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/busquedaOrdenesCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<BuscadorEspeciesView> busquedaOrdenesCompra(BusquedaOrdenesCompra request);

	/**
	 * Buscador de ordenes de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/busquedaOrdenesCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<BuscadorEspeciesView> busquedaOrdenesCompraBPriv(BusquedaOrdenesCompra request);

	/**
	 * Ingreso configuración orden venta titulos.
	 *
	 * @param datosEntrada the datos entrada
	 * @return the respuesta
	 */
	@POST
	@Path("/configuracionOrdenVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionOrdenVentaView> configuracionOrdenVenta(ConfiguracionOrdenVentaInView datosEntrada);

	/**
	 * Configuracion de ordenes de compra.
	 *
	 * @param request the request
	 * @return the respuestafina
	 */
	@POST
	@Path("/configuracionOrdenesCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompra(ConfiguracionOrdenesCompraRequest request);

	@POST
	@Path("/configuracionOrdenesCompraRellamado")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraRellamado(
			ConfiguracionOrdenesCompraRequest request);

	/**
	 * Configuracion de ordenes de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/configuracionOrdenesCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraBPriv(
			ConfiguracionOrdenesCompraRequest request);

	@POST
	@Path("/configuracionOrdenesCompraRellamadoBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraRellamadoBPriv(
			ConfiguracionOrdenesCompraRequest request);

	/**
	 * Configuracion de ordenes de compra.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaConfiguracionOrdenesCompraGrilla")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void configuracionOrdenesCompraGrilla();

	/**
	 * Confirmar de ordenes de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularOrdenCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmarOrdenCompraResponse> simularOrdenCompra(ConfirmarOrdenCompraRequest request);

	/**
	 * Confirmar de ordenes de compra para la banca privada.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularOrdenCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmarOrdenCompraResponse> simularOrdenCompraBPriv(ConfirmarOrdenCompraRequest request);

	/**
	 * Consultar Operaciones Banca Privada.
	 *
	 * @param parametrosOperacionesView the view request
	 * @return respuesta con el objeto view response.
	 */
	@POST
	@Path("/obtenerOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<OperacionesTitulosView> obtenerOperaciones(ParametrosOperacionesView parametrosOperacionesView);

	/**
	 * Obtener cuentas operativas.
	 *
	 * @param parametrosOperacionesView the parametros operaciones view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerCuentas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<List<CuentasOperativasView>> obtenerCuentasOperativas(
			ParametrosOperacionesView parametrosOperacionesView);

	/**
	 * Obtener mas operaciones.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerMasOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<OperacionesTitulosView> obtenerMasOperaciones();

	/**
	 * Aceptacion de resolucion origen de fondos.
	 *
	 * @param inView the in view
	 * @return the respuesta
	 */

	@POST
	@Path("/aceptacionResolucionOrigenDeFondos")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	void aceptacionResolucionOrigenDeFondos(IngresoOrdenVentaInView inView);

	/**
	 * Confirmacion orden venta.
	 *
	 * @param confirmacionInView the confirmacion in view
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmacionOrdenVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmacionVentaTitulosView> confirmacionOrdenVenta(ConfirmacionVentaTitulosInView confirmacionInView);

	/**
	 * Aceptacion de condiciones de cuentas custodia desde orden de venta.
	 *
	 * @param aceptacionView the aceptacion view
	 * @return the respuesta
	 */
	@POST
	@Path("/aceptacionCondicionesCuentasOrdenVenta")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> aceptacionCondicionesCuentasOrdenVenta(AceptacionCondicionesCuentasCustodiaViewIn aceptacionView);

	/**
	 * Descargar comprobante Aceptacion CNV desde orden venta.
	 *
	 * @param descargaInView the descarga in view
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteAceptacionCNVOrdenVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteAceptacionCNVOrdenVenta(IngresoOrdenVentaInView descargaInView);

	/**
	 * Ejecutar orden venta.
	 *
	 * @param confirmacionInView the confirmacion in view
	 * @return the respuesta
	 */
	@POST
	@Path("/ejecutarOrdenVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmacionVentaTitulosView> ejecutarOrdenVenta(ConfirmacionVentaTitulosInView confirmacionInView);

	/**
	 * Obtener detalle operacion.
	 *
	 * @param parametrosOperacionesView the parametros operaciones view
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDetalleOperacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<OperacionTitulosView> obtenerDetalleOperacion(ParametrosOperacionesView parametrosOperacionesView);

	/**
	 * Estadistica detalle licitacion RTL.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/estadisticaDetalleLicitacionRTL")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> estadisticaDetalleLicitacionRTL();

	/**
	 * Obtener tenencias titulos BP.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/tenenciasTitulosBP")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosBP(EstadisticasTotalesInView request);

	/**
	 * Visualizar comprobante orden venta.
	 *
	 * @param descargaInView the descarga in view
	 */
	@POST
	@Path("/visualizarComprobanteOrdenVenta")
	void visualizarComprobanteOrdenVenta(IngresoOrdenVentaInView descargaInView);

	/**
	 * Descargar comprobante orden venta PDF.
	 *
	 * @param descargaInView the descarga in view
	 * @return the respuesta
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@POST
	@Path("/descargarComprobanteOrdenVentaPDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobanteOrdenVentaPDF(IngresoOrdenVentaInView descargaInView) throws IOException;

	/**
	 * FeedBack de ordenes de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarOrdenCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmarOrdenCompraResponse> confirmarOrdenCompra(ConfirmarOrdenCompraRequest request);

	/**
	 * FeedBack de ordenes de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarOrdenCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmarOrdenCompraResponse> confirmarOrdenCompraBPriv(ConfirmarOrdenCompraRequest request);

	/**
	 * Graba estadistica Listar Operaciones, segun sea Compra o Venta.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaInicioOrdenesCompra")
	Respuesta<Void> grabarEstadisticaInicioOrdenesCompra();

	/**
	 * Graba estadistica Listar Operaciones, segun sea Compra o Venta.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaInicioOrdenesCompraBPriv")
	Respuesta<Void> grabarEstadisticaInicioOrdenesCompraBPriv();

	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteOrdenCompraPDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteOrdenCompraPDF(ComprobanteOrdenCompra request);

	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteOrdenCompraPDFBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteOrdenCompraPDFBPriv(ComprobanteOrdenCompra request);

	/**
	 * Grabar estadisticas comprobante orden compra y devolver fecha y hora actual.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteOrdenCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteOrdenCompraView> verComprobanteOrdenCompra();

	/**
	 * Grabar estadisticas comprobante orden compra y devolver fecha y hora actual
	 * BPRIV.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteOrdenCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteOrdenCompraView> verComprobanteOrdenCompraBPriv();

	/**
	 * Grabar estadistica ordenes venta.
	 *
	 * @param estadistica the estadistica
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaOrdenesVenta")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaOrdenesVenta(EstadisticasOrdenesVentaView estadistica);

	/**
	 * Grabado de estadisticas de ver detalle Orden de Compra.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verDetalleOrdenCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verDetalleOrdenCompra();

	/**
	 * Grabado de estadisticas de ver detalle Orden de Venta.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verDetalleOrdenVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verDetalleOrdenVenta();

	/**
	 * Grabado de estadisticas Buscador de Compra.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/buscadorOrdenCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> buscadorOrdenCompra();

	/**
	 * Grabado de estadisticas Buscador de Venta.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/buscadorOrdenVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> buscadorOrdenVenta();

	/**
	 * Grabado de estadisticas Buscador de Compra BPriv.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/buscadorOrdenCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> buscadorOrdenCompraBPriv();

	/**
	 * Grabado de estadisticas Buscador de Venta BPriv.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/buscadorOrdenVentaBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> buscadorOrdenVentaBPriv();

	/**
	 * Grabar estadistica ordenes venta B priv.
	 *
	 * @param respuesta the respuesta
	 * @return the respuesta
	 */
	@POST
	@Path("/grabarEstadisticaOrdenesVentaBPriv")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> grabarEstadisticaOrdenesVentaBPriv(EstadisticasOrdenesVentaView respuesta);

	/**
	 * Grabado de estadisticas de ver detalle Orden de Compra BPriv.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verDetalleOrdenCompraBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verDetalleOrdenCompraBPriv();

	/**
	 * Grabado de estadisticas de ver detalle Orden de Venta BPriv.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verDetalleOrdenVentaBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verDetalleOrdenVentaBPriv();

	/**
	 * Preconfirmar Reversar Orden.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/reversarOrden")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReversarCompraVentaViewResponse> ReversarOrdenCompraVenta(ReversarCompraVentaRequestView request);

	/**
	 * Finalizar Reversar Orden.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarReversarOrden")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarReversarOrdenView> finalizarReversarOrden(FinalizarReversarOrdenViewRequest viewRequest);

	/**
	 * Finalizar Reversar Orden BPriv.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarReversarOrdenBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarReversarOrdenView> finalizarReversarOrdenBPriv(FinalizarReversarOrdenViewRequest viewRequest);

	/**
	 * Ver comprobante reversa.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteReversa")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> verComprobanteReversa(ReversarCompraVentaRequestView request);

	/**
	 * Detalle titulo RTL.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	@POST
	@Path("/detalleTituloRTL")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<DetalleTitulosBPView> detalleTituloRTL(DetalleTituloInView view);

	/**
	 * Detalle titulo B priv.
	 *
	 * @param view the view
	 * @return the respuesta
	 */
	@POST
	@Path("/detalleTituloBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<DetalleTitulosBPView> detalleTituloBPriv(DetalleTituloInView view);

	/**
	 * paso 2 de suscribir licitaciones desde grilla de licitaciones vigentes, banca
	 * personal.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/licitacionesVigentesGoToLicitar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigurarLicitacionOutView> licitacionesVigentesGoToLicitar(ConfigurarLicitacionViewReq request);

	/**
	 * paso 2 de suscribir licitaciones desde grilla de licitaciones vigentes, banca
	 * privada.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/licitacionesVigentesGoToLicitarBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfigurarLicitacionBPrivOutView> licitacionesVigentesGoToLicitarBPriv(
			ConfigurarLicitacionViewReq request);

	/**
	 * Obtener rendimiento.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerRendimiento")
	@CustomPreAuthorize(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RendimientoView> obtenerRendimiento();

	/**
	 * Obtener rendimiento B priv.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerRendimientoBPriv")
	@CustomPreAuthorize(AccionController.RENDIMIENTO_TENENCIA_INVERSIONES)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv();

	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteReversaOrdenCompraVenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteReversaOrdenCompraVenta(ComprobanteReversaOrdenCompraVenta request);

	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteReversaOrdenCompraVentaBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteReversaOrdenCompraVentaBPriv(ComprobanteReversaOrdenCompraVenta request);

	/**
	 * Descargar comprobante PDF operacion compra/venta.
	 *
	 * @param parametrosOperacionesView the parametros operaciones view
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteDetalleOperacion")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteDetalleOperacion(ParametrosOperacionesView parametrosOperacionesView);

	/**
	 * Actualizar poder de compra.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/actualizarPoderDeCompra")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionOrdenesCompraResponse> actualizarPoderDeCompra(ConfiguracionOrdenesCompraRequest request);

	@POST
	@Path("/exportarGrillaTitulosValoresRTL")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarGrillaTenenciasRTL();

	@POST
	@Path("/exportarGrillaTitulosValoresPriv")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarGrillaTenenciasBP();

	@POST
	@Path("/obtenerListaComprobantes")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ListaFechasComprobantes> obtenerListaComprobantes();

	@POST
	@Path("/descargaComprobantesPDF")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ListadoPDF> descargaComprobantesPDF(ConsultaResumenCuentaBP cuenta);

	@POST
	@Path("/obtenerEspeciesCanje")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<LicitacionCanjeResponse> ObtenerEspeciesCanje(LicitacionCanjeRequest request);

	@POST
	@Path("/obtenerEspeciesCanjeBPriv")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<LicitacionCanjeResponse> ObtenerEspeciesCanjeBPriv(LicitacionCanjeRequest request);

	/**
	 * Simular licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularLicitacionCanje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanje(SimularLicitacionCanjeRequest request);

	/**
	 * Finalizar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarLicitacionCanje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanje(FinalizarLicitacionRequest request);
	
	/**
	 * Grabar estadisticas comprobante orden compra y devolver fecha y hora actual.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteCanje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteLicitacionOutView> verComprobanteCanje();	
	
	/**
	 * Simular licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/simularLicitacionCanjeBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanjeBPriv(SimularLicitacionCanjeRequest request);
	
	
	@POST
	@Path("/descargarComprobanteSuscripcionLicitacionCanjePDF")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionCanjePDF(ComprobanteSuscripcionLicitacionCanje request);
	
	/**
	 * Reversar orden.
	 * 
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/reversarOrdenLicitacionCanje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanje(ReversaRequestView request);
	
	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteReversaLicitacionCanjePDF")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDF(ComprobanteReversaLicitacionCanje viewRequest);

	/**
	 * Descargar comprobante PDF operacion compra/venta.
	 *
	 * @param parametrosOperacionesView the parametros operaciones view
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteDetalleOperacionCanje")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanje(ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje);
	
	/**
	 * Finalizar licitacion.
	 *
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarLicitacionCanjeBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanjeBPriv(FinalizarLicitacionRequest request);
	
	/**
	 * Grabar estadisticas comprobante orden compra y devolver fecha y hora actual.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/verComprobanteCanjeBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteLicitacionOutView> verComprobanteCanjeBPriv();
	
	@POST
	@Path("/descargarComprobanteSuscripcionLicitacionCanjePDFBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionCanjePDFBPriv(ComprobanteSuscripcionLicitacionCanje request);
	
	/**
	 * Reversar orden.
	 * 
	 * @param request the request
	 * @return the respuesta
	 */
	@POST
	@Path("/reversarOrdenLicitacionCanjeBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanjeBPriv(ReversaRequestView request);

	/**
	 * Descargar comprobante PDF operacion compra/venta.
	 *
	 * @param parametrosOperacionesView the parametros operaciones view
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteDetalleOperacionCanjeBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanjeBPriv(ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje);
	
	/**
	 * Descargar comprobante PDF de suscripcion de una licitacion.
	 *
	 * @param viewRequest the view request
	 * @return the respuesta
	 */
	@POST
	@Path("/descargarComprobanteReversaLicitacionCanjePDFBPriv")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDFBPriv(ComprobanteReversaLicitacionCanje viewRequest);
}
