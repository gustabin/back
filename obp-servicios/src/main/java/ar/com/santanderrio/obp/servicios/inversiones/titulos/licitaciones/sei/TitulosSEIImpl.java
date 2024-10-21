/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.TipoPDFEnum;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.manager.DescargaPdfManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.dto.CondicionesGeneralesCuentaCustodiaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ListarOperaciones;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.manager.TitulosManager;
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
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.ConfiguracionOrdenVentaTituloManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.manager.TitulosOrdenVentaManager;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.AceptacionCondicionesCuentasCustodiaViewIn;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView;
import ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.TituloView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ComprobanteDetalleOperacionLicitacionCanje;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.CuentasOperativasView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionesTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaRequestView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaViewResponse;

/**
 * The Class TitulosSEIImpl.
 *
 * @author juan.pablo.picate
 */
@Component("licitacionesSEI")
public class TitulosSEIImpl implements TitulosSEI {

	/** The fondo manager. */
	@Autowired
	private TitulosManager titulosManager;

	/** The titulos orden venta manager. */
	@Autowired
	private TitulosOrdenVentaManager titulosOrdenVentaManager;

	/** The configuracion orden venta manager. */
	@Autowired
	private ConfiguracionOrdenVentaTituloManager configuracionOrdenVentaManager;

	/** The titulos operaciones manager. */
	@Autowired
	private TitulosOperacionesManager titulosOperacionesManager;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;
	
	@Autowired
	private DescargaPdfManager descargaPdfManager;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesRTL() {
		return titulosManager.consultarLicitacionesRTL();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<ConsultarOrdenesPorCuentaOutView> consultarLicitacionesBPriv() {
		return titulosManager.consultarLicitacionesBPriv();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#licitacionesVigentes()
	 */
	@Override
	public Respuesta<LicitacionesVigentesOutView> licitacionesVigentes() {
		return titulosManager.licitacionesVigentes();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<LicitacionesVIgentesBprivOutView> licitacionesVigentesBPriv(NuevaLicitacionViewReq request) {
		return titulosManager.licitacionesVigentesBPriv(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#nuevaLicitacion(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.view.NuevaLicitacionViewReq)
	 */
	@Override
	public Respuesta<NuevaLicitacionViewResponse> nuevaLicitacion(NuevaLicitacionViewReq request) {
		return titulosManager.nuevaLicitacion(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#simularLicitacion(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.SimularLicitacionRequest)
	 */
	@Override
	public Respuesta<SimularLicitacionViewResponse> simularLicitacion(SimularLicitacionRequest request) {
		return titulosManager.simularLicitacion(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#grabarEstadisticaReversa()
	 */
	@Override
	public void grabarEstadisticaReversa(ConsultaOrdenesEstadisticaRequest request) {
		titulosManager.grabarEstadisticaReversa(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#reversarOrden(ar.com.santanderrio.obp.servicios.inversiones.
	 * titulos.licitaciones.view.ReversaRequestView)
	 */
	@Override
	public Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacion(ReversaRequestView request) {
		return titulosManager.reversarOrdenLicitacion(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#configurarLicitacion(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> configurarLicitacion(ConfigurarLicitacionViewReq request) {
		return titulosManager.configurarLicitacion(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#grabarEstadisticaDetalleOrden()
	 */
	@Override
	public void grabarEstadisticaDetalleOrden(ConsultaOrdenesEstadisticaRequest request) {
		titulosManager.grabarEstadisticaDetalleOrden(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#finalizarLicitacion(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.FinalizarLicitacionRequest)
	 */
	@Override
	public Respuesta<FinalizarLicitacionViewResp> finalizarLicitacion(FinalizarLicitacionRequest request) {
		return titulosManager.finalizarLicitacion(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#finalizarLicitacion(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.FinalizarLicitacionRequest)
	 */
	@Override
	public Respuesta<FinalizarLicitacionViewResp> finalizarLicitacionBPriv(FinalizarLicitacionRequest request) {
		return titulosManager.finalizarLicitacionBPriv(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#verComprobanteLicitacion()
	 */
	@Override
	public Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacion() {
		return titulosManager.verComprobanteLicitacion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#verComprobanteLicitacion()
	 */
	@Override
	public Respuesta<ComprobanteLicitacionOutView> verComprobanteLicitacionBPriv() {
		return titulosManager.verComprobanteLicitacionBPriv();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#obtenerTenenciasTitulosRTL(ar.com.santanderrio.obp.servicios.
	 * inversiones.titulos.licitaciones.view.EstadisticasTotalesInView)
	 */
	@Override
	public Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosRTL(EstadisticasTotalesInView request) {
		return titulosManager.obtenerTenenciasTitulosRTL(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#grabarEstadisticaComprobanteReversa()
	 */
	@Override
	public void grabarEstadisticaComprobanteReversa(ConsultaOrdenesEstadisticaRequest request) {
		titulosManager.grabarEstadisticaComprobanteReversa(request);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#grabarEstadisticaComprobanteReversaBpriv()
	 */
	@Override
	public void grabarEstadisticaComprobanteReversaBpriv(ConsultaOrdenesEstadisticaRequest request) {
		titulosManager.grabarEstadisticaComprobanteReversaBpriv(request);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.
	 * TitulosSEI#descargarComprobanteSuscripcionLicitacionPDF(ar.com.
	 * santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.
	 * ComprobanteSuscripcionLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionPDF(
			ComprobanteSuscripcionLicitacion request) {
		return titulosManager.descargarComprobanteSuscripcionLicitacionPDF(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#consultarOperacionesText(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ConsultaOperaciones)
	 */
	@Override
	public Respuesta<ConsultaOperacionesView> consultarOperacionesText(ConsultaOperaciones viewRequest) {
		return titulosManager.consultarOperacionesText(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#consultarOrdenes(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.ConsultarOrdenesViewRequest)
	 */
	@Override
	public Respuesta<ConsultaOrdenesView> consultarOrdenes(ConsultarOrdenesViewRequest viewRequest) {
		return titulosManager.consultarOrdenes(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#consultarOrdenesBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.ConsultarOrdenesViewRequest)
	 */
	@Override
	public Respuesta<ConsultaOrdenesView> consultarOrdenesBPriv(ConsultarOrdenesViewRequest viewRequest) {
		return titulosManager.consultarOrdenesBPriv(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#firmarCuentasPorNup(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.CondicionesGeneralesViewRequest)
	 */
	@Override
	public Respuesta<CondicionesGeneralesCuentaCustodiaDTO> firmarCuentasPorNup(
			CondicionesGeneralesViewRequest viewRequest) {
		return titulosManager.condicionesGeneralesCuentaCustodia(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#firmarCuentas(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentas(FirmarCuentasViewRequest viewRequest) {
		return titulosManager.firmarCuentas(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#firmarCuentasBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentasBPriv(FirmarCuentasViewRequest viewRequest) {
		return titulosManager.firmarCuentasBPriv(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteReversaLicitacionPDF(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDF(ComprobanteReversaLicitacion viewRequest) {
		return titulosManager.descargarComprobanteReversaLicitacionPDF(viewRequest);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteReversaLicitacionPDFBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaLicitacion)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionPDFBPriv(ComprobanteReversaLicitacion viewRequest) {
		return titulosManager.descargarComprobanteReversaLicitacionPDFBPriv(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaDetalleOperaciones(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ListarOperaciones)
	 */
	@Override
	public void grabarEstadisticaDetalleOperaciones(ListarOperaciones viewRequest) {
		titulosManager.grabarEstadisticaDetalleOperaciones(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaListarOperaciones(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.entity.ListarOperaciones)
	 */
	@Override
	public void grabarEstadisticaListarOperaciones(ListarOperaciones viewRequest) {
		titulosManager.grabarEstadisticaListarOperaciones(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#listarComprobantesCNV(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ListarComprobantesViewRequest)
	 */
	@Override
	public Respuesta<ListarComprobantesViewResponse> listarComprobantesCNV(ListarComprobantesViewRequest viewRequest) {
		return titulosManager.listarComprobantesCNV(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerComprobanteCNV(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ObtenerComprobanteViewRequest)
	 */
	@Override
	public Respuesta<ComprobanteCnvViewResponse> obtenerComprobanteCNV(ObtenerComprobanteViewRequest viewRequest) {
		return titulosManager.obtenerComprobanteCNV(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteAceptacionCNV(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteAceptacionCNVRequest)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteAceptacionCNV(ComprobanteAceptacionCNVRequest request) {
		return titulosManager.descargarComprobanteAceptacionCNV(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaDetalleOrdenBpriv()
	 */
	@Override
	public void grabarEstadisticaDetalleOrdenBpriv(ConsultaOrdenesEstadisticaRequest request) {
		titulosManager.grabarEstadisticaDetalleOrdenBpriv(request);

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#simularLicitacionBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.SimularLicitacionRequest)
	 */
	@Override
	public Respuesta<SimularLicitacionViewResponse> simularLicitacionBPriv(SimularLicitacionRequest request) {
		return titulosManager.simularLicitacionBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#nuevaLicitacionBpriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.NuevaLicitacionViewReq)
	 */
	@Override
	public Respuesta<NuevaLicitacionViewResponseBPriv> nuevaLicitacionBpriv(NuevaLicitacionViewReq request) {
		return titulosManager.nuevaLicitacionBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaReversaBpriv()
	 */
	@Override
	public void grabarEstadisticaReversaBpriv(ConsultaOrdenesEstadisticaRequest request) {
		titulosManager.grabarEstadisticaReversaBpriv(request);

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#configurarLicitacionBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq)
	 */
	public Respuesta<ConfigurarLicitacionBPrivOutView> configurarLicitacionBPriv(ConfigurarLicitacionViewReq request) {
		return titulosManager.configurarLicitacionBPriv(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#busquedaOrdenesCompra(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra)
	 */
	@Override
	public Respuesta<BuscadorEspeciesView> busquedaOrdenesCompra(BusquedaOrdenesCompra request) {
		return titulosManager.busquedaOrdenesCompra(request, TipoBancaEnum.BANCA_PERSONAL);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#busquedaOrdenesCompraBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.BusquedaOrdenesCompra)
	 */
	@Override
	public Respuesta<BuscadorEspeciesView> busquedaOrdenesCompraBPriv(BusquedaOrdenesCompra request) {
		return titulosManager.busquedaOrdenesCompra(request, TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#configuracionOrdenesCompra(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompra(
			ConfiguracionOrdenesCompraRequest request) {
		return titulosManager.configuracionOrdenesCompra(request,TipoBancaEnum.BANCA_PERSONAL);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#configuracionOrdenesCompraBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraBPriv(
			ConfiguracionOrdenesCompraRequest request) {
		return titulosManager.configuracionOrdenesCompra(request,TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#consultarOperacionesLicitacion()
	 */
	@Override
	public Respuesta<ConsultaOperacionesView> consultarOperacionesLicitacion() {
		return titulosManager.consultarOperacionesLicitacion(TipoBancaEnum.BANCA_PERSONAL);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#ingresoOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView)
	 */
	@Override
	public Respuesta<List<TituloView>> ingresoOrdenVenta(IngresoOrdenVentaInView ingresoView) {
		return titulosOrdenVentaManager.ingresoOrdenVenta(ingresoView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerOperaciones(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView)
	 */
	@Override
	public Respuesta<OperacionesTitulosView> obtenerOperaciones(ParametrosOperacionesView parametrosOperacionesView) {
		return titulosOperacionesManager.obtenerOperacionesPrimeraVez(parametrosOperacionesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerCuentasOperativas(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView)
	 */
	@Override
	public Respuesta<List<CuentasOperativasView>> obtenerCuentasOperativas(ParametrosOperacionesView parametrosOperacionesView) {
		return titulosOperacionesManager.obtenerCuentasOperativas(parametrosOperacionesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerMasOperaciones()
	 */
	@Override
	public Respuesta<OperacionesTitulosView> obtenerMasOperaciones() {
		return titulosOperacionesManager.obtenerMasOperaciones();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerDetalleOperacion(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView)
	 */
	@Override
	public Respuesta<OperacionTitulosView> obtenerDetalleOperacion(
			ParametrosOperacionesView parametrosOperacionesView) {
		return titulosOperacionesManager.obtenerDetalleOperacion(parametrosOperacionesView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#configuracionOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfiguracionOrdenVentaInView)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenVentaView> configuracionOrdenVenta(ConfiguracionOrdenVentaInView datosEntrada) {
		return configuracionOrdenVentaManager.configuracionOrdenVenta(datosEntrada);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#aceptacionResolucionOrigenDeFondos(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView)
	 */
	@Override
	public void aceptacionResolucionOrigenDeFondos(IngresoOrdenVentaInView inView) {
		configuracionOrdenVentaManager.aceptacionResolucionOrigenDeFondos(inView.getEsBancaPrivada());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaOrdenesVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.EstadisticasOrdenesVentaView)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaOrdenesVenta(EstadisticasOrdenesVentaView estadistica) {
		return titulosManager.grabarEstadisticaOrdenesVenta(estadistica);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#verDetalleOrdenCompra()
	 */
	@Override
	public Respuesta<Void> verDetalleOrdenCompra() {
		return titulosManager.grabaEstadisticaVerDetalleOrdenCompra();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#verDetalleOrdenVenta()
	 */
	@Override
	public Respuesta<Void> verDetalleOrdenVenta() {
		return titulosManager.grabaEstadisticaVerDetalleOrdenVenta();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#buscadorOrdenVenta()
	 */
	@Override
	public Respuesta<Void> buscadorOrdenVenta() {
		return titulosManager.grabaEstadisticaBuscadorOrdenVenta();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#buscadorOrdenCompra()
	 */
	@Override
	public Respuesta<Void> buscadorOrdenCompra() {
		return titulosManager.grabaEstadisticaBuscadorOrdenCompra();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#buscadorOrdenCompraBPriv()
	 */
	@Override
	public Respuesta<Void> buscadorOrdenCompraBPriv() {
		return titulosManager.grabaEstadisticaBuscadorOrdenCompraBPriv();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#buscadorOrdenVentaBPriv()
	 */
	@Override
	public Respuesta<Void> buscadorOrdenVentaBPriv() {
		return titulosManager.grabaEstadisticaBuscadorOrdenVentaBPriv();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaOrdenesVentaBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.EstadisticasOrdenesVentaView)
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaOrdenesVentaBPriv(EstadisticasOrdenesVentaView estadistica) {
		return titulosManager.grabarEstadisticaOrdenesVentaBPriv(estadistica);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#verDetalleOrdenCompraBPriv()
	 */
	@Override
	public Respuesta<Void> verDetalleOrdenCompraBPriv() {
		return titulosManager.grabaEstadisticaVerDetalleOrdenCompraBPriv();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#verDetalleOrdenVentaBPriv()
	 */
	@Override
	public Respuesta<Void> verDetalleOrdenVentaBPriv() {
		return titulosManager.grabaEstadisticaVerDetalleOrdenVentaBPriv();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#confirmacionOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView)
	 */
	@Override
	public Respuesta<ConfirmacionVentaTitulosView> confirmacionOrdenVenta(
			ConfirmacionVentaTitulosInView confirmacionInView) {
		return titulosOrdenVentaManager.confirmacionOrdenVenta(confirmacionInView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#aceptacionCondicionesCuentasOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.AceptacionCondicionesCuentasCustodiaViewIn)
	 */
	@Override
	public Respuesta<Void> aceptacionCondicionesCuentasOrdenVenta(AceptacionCondicionesCuentasCustodiaViewIn aceptacionView) {
		return configuracionOrdenVentaManager.aceptacionCondicionesCuentasOrdenVenta(aceptacionView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#ejecutarOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.ConfirmacionVentaTitulosInView)
	 */
	@Override
	public Respuesta<ConfirmacionVentaTitulosView> ejecutarOrdenVenta(
			ConfirmacionVentaTitulosInView confirmacionInView) {
		return titulosOrdenVentaManager.ejecutarOrdenVenta(confirmacionInView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteAceptacionCNVOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteAceptacionCNVOrdenVenta(IngresoOrdenVentaInView descargaInView) {
		return configuracionOrdenVentaManager.descargarComprobanteAceptacionCNVOrdenVenta(descargaInView.getEsBancaPrivada());
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteOrdenVentaPDF(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteOrdenVentaPDF(IngresoOrdenVentaInView descargaInView) throws IOException {
		return titulosOrdenVentaManager.descargarComprobanteOrdenVentaPDF(descargaInView);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#visualizarComprobanteOrdenVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenventa.view.IngresoOrdenVentaInView)
	 */
	@Override
	public void visualizarComprobanteOrdenVenta(IngresoOrdenVentaInView descargaInView) {
		estadisticaManager.add(descargaInView.getEsBancaPrivada() ?
				EstadisticasConstants.VISUALIZAR_COMPROBANTE_ORDEN_VENTA_TITULOS_PRIVADA :
				EstadisticasConstants.VISUALIZAR_COMPROBANTE_ORDEN_VENTA_TITULOS_RETAIL,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerTenenciasTitulosBP(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.EstadisticasTotalesInView)
	 */
	@Override
	public Respuesta<TenenciaTitulosOutView> obtenerTenenciasTitulosBP(EstadisticasTotalesInView request) {
		return titulosManager.obtenerTenenciasTitulosBP(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaInicioOrdenesCompra()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaInicioOrdenesCompra() {
		return titulosManager.grabarEstadisticaInicioOrdenesCompra(TipoBancaEnum.BANCA_PERSONAL);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#grabarEstadisticaInicioOrdenesCompraBPriv()
	 */
	@Override
	public Respuesta<Void> grabarEstadisticaInicioOrdenesCompraBPriv() {
		return titulosManager.grabarEstadisticaInicioOrdenesCompra(TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#consultarCondicionesGeneralesCC()
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> consultarCondicionesGeneralesCC() {
		return titulosManager.consultarCondicionesGeneralesCC();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#simularOrdenCompra(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest)
	 */
	@Override
	public Respuesta<ConfirmarOrdenCompraResponse> simularOrdenCompra(ConfirmarOrdenCompraRequest request) {
		return titulosManager.simularOrdenCompra(request, TipoBancaEnum.BANCA_PERSONAL);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#simularOrdenCompraBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest)
	 */
	@Override
	public Respuesta<ConfirmarOrdenCompraResponse> simularOrdenCompraBPriv(ConfirmarOrdenCompraRequest request) {
		return titulosManager.simularOrdenCompra(request, TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#confirmarOrdenCompra(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest)
	 */
	@Override
	public Respuesta<ConfirmarOrdenCompraResponse> confirmarOrdenCompra(ConfirmarOrdenCompraRequest request) {
		return titulosManager.confirmarOrdenCompra(request, TipoBancaEnum.BANCA_PERSONAL);
	}
		
		/* (non-Javadoc)
		 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#confirmarOrdenCompraBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfirmarOrdenCompraRequest)
		 */
		@Override
		public Respuesta<ConfirmarOrdenCompraResponse> confirmarOrdenCompraBPriv(ConfirmarOrdenCompraRequest request) {
			return titulosManager.confirmarOrdenCompra(request, TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteOrdenCompraPDF(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompra)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteOrdenCompraPDF(ComprobanteOrdenCompra request) {
		return titulosManager.descargarComprobanteOrdenCompraPDF(request, TipoBancaEnum.BANCA_PERSONAL);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteOrdenCompraPDFBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteOrdenCompra)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteOrdenCompraPDFBPriv(ComprobanteOrdenCompra request) {
		return titulosManager.descargarComprobanteOrdenCompraPDF(request, TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#verComprobanteOrdenCompra()
	 */
	@Override
	public Respuesta<ComprobanteOrdenCompraView> verComprobanteOrdenCompra() {
		return titulosManager.verComprobanteOrdenCompra(TipoBancaEnum.BANCA_PERSONAL);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#verComprobanteOrdenCompraBPriv()
	 */
	@Override
	public Respuesta<ComprobanteOrdenCompraView> verComprobanteOrdenCompraBPriv() {
		return titulosManager.verComprobanteOrdenCompra(TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#firmarCuentasOrdenCompras(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentasOrdenCompras(FirmarCuentasViewRequest request) {
		return titulosManager.firmarCuentasOrdenCompras(request);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#firmarCuentasOrdenComprasBpriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.FirmarCuentasViewRequest)
	 */
	@Override
	public Respuesta<String> firmarCuentasOrdenComprasBpriv(FirmarCuentasViewRequest request){
		return titulosManager.firmarCuentasOrdenComprasBpriv(request);
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#estadisticaDetalleLicitacionRTL()
     */
    @Override
    public Respuesta<Void> estadisticaDetalleLicitacionRTL() {
        return titulosManager.estadisticaDetalleLicitacionRTL();
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#ReversarOrdenCompraVenta(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaRequestView)
	 */
	@Override
	public Respuesta<ReversarCompraVentaViewResponse> ReversarOrdenCompraVenta(ReversarCompraVentaRequestView preconfirmarReversarView) {
		return titulosManager.ReversarOrdenCompraVenta(preconfirmarReversarView);
	}

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#finalizarReversarOrden(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest)
	 */
	@Override
	public Respuesta<FinalizarReversarOrdenView> finalizarReversarOrden(FinalizarReversarOrdenViewRequest viewRequest) {
		return titulosManager.finalizarReversarOrden(viewRequest);
	}

	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#finalizarReversarOrdenBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.ordenes.view.FinalizarReversarOrdenViewRequest)
	 */
	@Override
	public Respuesta<FinalizarReversarOrdenView> finalizarReversarOrdenBPriv(FinalizarReversarOrdenViewRequest viewRequest) {
		return titulosManager.finalizarReversarOrdenBPriv(viewRequest);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#verComprobanteReversa(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ReversarCompraVentaRequestView)
	 */
	@Override
	public Respuesta<Void> verComprobanteReversa(ReversarCompraVentaRequestView request) {
		return titulosManager.verComprobanteReversa(request);
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#detalleTituloRTL(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.DetalleTituloInView)
     */
    @Override
    public Respuesta<DetalleTitulosBPView> detalleTituloRTL(DetalleTituloInView view) {
        return titulosManager.detalleTituloRTL(view);
    }
    
    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#detalleTituloBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.DetalleTituloInView)
     */
    @Override
    public Respuesta<DetalleTitulosBPView> detalleTituloBPriv(DetalleTituloInView view) {
        return titulosManager.detalleTituloBPriv(view);
    }

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#licitacionesVigentesGoToLicitar(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionOutView> licitacionesVigentesGoToLicitar(ConfigurarLicitacionViewReq request) {
		return titulosManager.licitacionesVigentesGoToLicitar(request);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#licitacionesVigentesGoToLicitarBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfigurarLicitacionViewReq)
	 */
	@Override
	public Respuesta<ConfigurarLicitacionBPrivOutView> licitacionesVigentesGoToLicitarBPriv(
			ConfigurarLicitacionViewReq request) {
		return titulosManager.licitacionesVigentesGoToLicitarBPriv(request);
	}
	

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#configuracionOrdenesCompraGrilla()
	 */
	@Override
	public void configuracionOrdenesCompraGrilla() {
		titulosManager.configuracionOrdenesCompraGrilla();
		
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerRendimiento()
	 */
	@Override
	public Respuesta<RendimientoView> obtenerRendimiento() {
		return titulosManager.obtenerRendimiento();
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#obtenerRendimientoBPriv()
	 */
	@Override
	public Respuesta<RendimientoBPrivView> obtenerRendimientoBPriv() {
		return titulosManager.obtenerRendimientoBPriv();

	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteReversaOrdenCompraVenta(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaOrdenCompraVenta)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaOrdenCompraVenta(ComprobanteReversaOrdenCompraVenta request) {
		return titulosManager.descargarComprobanteReversaOrdenCompraVenta(request, TipoBancaEnum.BANCA_PERSONAL);
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteReversaOrdenCompraVentaBPriv(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ComprobanteReversaOrdenCompraVenta)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaOrdenCompraVentaBPriv(ComprobanteReversaOrdenCompraVenta request) {
		return titulosManager.descargarComprobanteReversaOrdenCompraVenta(request, TipoBancaEnum.BANCA_PRIVADA);
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#descargarComprobanteDetalleOperacion(ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView)
	 */
	@Override
	public Respuesta<ReporteView> descargarComprobanteDetalleOperacion(ParametrosOperacionesView parametrosOperacionesView) {
		return titulosOperacionesManager.descargarComprobanteDetalleOperacion(parametrosOperacionesView.getTipoOperacion());
	}
	
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.sei.TitulosSEI#actualizarPoderDeCompra(ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view.ConfiguracionOrdenesCompraRequest)
	 */
	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> actualizarPoderDeCompra(ConfiguracionOrdenesCompraRequest request) {
		return titulosManager.actualizarPoderDeCompra(request);
	}

	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraRellamado(
			ConfiguracionOrdenesCompraRequest request) {
		return titulosManager.configuracionOrdenesCompraRellamado(request,TipoBancaEnum.BANCA_PERSONAL);
	}

	@Override
	public Respuesta<ConfiguracionOrdenesCompraResponse> configuracionOrdenesCompraRellamadoBPriv(
			ConfiguracionOrdenesCompraRequest request) {
		return titulosManager.configuracionOrdenesCompraRellamado(request,TipoBancaEnum.BANCA_PRIVADA);
	}


	@Override
	public Response exportarGrillaTenenciasRTL() {
		return titulosManager.exportarGrillaTenencias(TipoBancaEnum.BANCA_PERSONAL);
	}

	@Override
	public Response exportarGrillaTenenciasBP() {
		return titulosManager.exportarGrillaTenencias(TipoBancaEnum.BANCA_PRIVADA);
	}

	@Override
	public Respuesta<ListaFechasComprobantes> obtenerListaComprobantes() {
		return descargaPdfManager.obtenerListaComprobantes(TipoPDFEnum.TITULOS_VALORES);
	}

	@Override
	public Respuesta<ListadoPDF> descargaComprobantesPDF(ConsultaResumenCuentaBP cuenta) {
		return descargaPdfManager.obtenerPDF(cuenta, TipoPDFEnum.TITULOS_VALORES);
	}

	@Override
	public Respuesta<LicitacionCanjeResponse> ObtenerEspeciesCanje(LicitacionCanjeRequest request) {
		return titulosManager.obtenerEspeciesCanje(request);
	}

	@Override
	public Respuesta<LicitacionCanjeResponse> ObtenerEspeciesCanjeBPriv(LicitacionCanjeRequest request) {
		return titulosManager.obtenerEspeciesCanjeBPriv(request);
	}

	@Override
	public Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanje(SimularLicitacionCanjeRequest request) {
		return titulosManager.simularLicitacionCanje(request);
	}
	
	@Override
	public Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanje(FinalizarLicitacionRequest request) {
		return titulosManager.finalizarLicitacionCanje(request);
	}

	@Override
	public Respuesta<ComprobanteLicitacionOutView> verComprobanteCanje() {
		return titulosManager.verComprobanteCanje(TipoBancaEnum.BANCA_PERSONAL);
	}

	@Override
	public Respuesta<SimularLicitacionCanjeResponseView> simularLicitacionCanjeBPriv(
			SimularLicitacionCanjeRequest request) {
		return titulosManager.simularLicitacionCanjeBPriv(request);
	}
	
	public Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionCanjePDF(ComprobanteSuscripcionLicitacionCanje request) {
		return titulosManager.descargarComprobanteSuscripcionLicitacionCanjePDF(request, TipoBancaEnum.BANCA_PERSONAL);
	}

	@Override
	public Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanje(ReversaRequestView request) {
		return titulosManager.reversarOrdenLicitacionCanje(request);
	}
	
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDF(ComprobanteReversaLicitacionCanje viewRequest) {
		return titulosManager.descargarComprobanteReversaLicitacionCanjePDF(viewRequest);
	}

	@Override
	public Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanje(
			ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje) {
		return titulosOperacionesManager.descargarComprobanteDetalleOperacionCanje(comprobanteDetalleOperacionLicitacionCanje);
	}

	@Override
	public Respuesta<FinalizarLicitacionCanjeView> finalizarLicitacionCanjeBPriv(FinalizarLicitacionRequest request) {
		return titulosManager.finalizarLicitacionCanjeBPriv(request);
	}
	
	@Override
	public Respuesta<ComprobanteLicitacionOutView> verComprobanteCanjeBPriv() {
		return titulosManager.verComprobanteCanje(TipoBancaEnum.BANCA_PRIVADA);
	}
	
	@Override
	public Respuesta<ReporteView> descargarComprobanteSuscripcionLicitacionCanjePDFBPriv(ComprobanteSuscripcionLicitacionCanje request) {
		return titulosManager.descargarComprobanteSuscripcionLicitacionCanjePDF(request, TipoBancaEnum.BANCA_PRIVADA);
	}
	
	@Override
	public Respuesta<ReversarOrdenViewResponse> reversarOrdenLicitacionCanjeBPriv(ReversaRequestView request) {
		return titulosManager.reversarOrdenLicitacionCanje(request);
	}
	
	@Override
	public Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanjeBPriv(
			ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje) {
		return titulosOperacionesManager.descargarComprobanteDetalleOperacionCanjeBPriv(comprobanteDetalleOperacionLicitacionCanje);
	}
	
	@Override
	public Respuesta<ReporteView> descargarComprobanteReversaLicitacionCanjePDFBPriv(ComprobanteReversaLicitacionCanje viewRequest) {
		return titulosManager.descargarComprobanteReversaLicitacionCanjePDFBPriv(viewRequest);
	}
}
