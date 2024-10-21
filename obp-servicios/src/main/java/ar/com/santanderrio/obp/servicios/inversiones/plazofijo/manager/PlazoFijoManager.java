/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager;

import java.util.List;

import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.DetallePFInteresanteInView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.FrecuenciaCobroPFInteresanteView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoBPrivView;
import ar.com.santanderrio.obp.servicios.inversiones.comun.view.RendimientoView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.entity.ConfirmarOrdenInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.ConfirmarOrdenPFView;
import ar.com.santanderrio.obp.servicios.inversiones.ordenes.web.view.OrdenesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.entity.RecomendacionResponseEntity;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AccionesAlVencimientoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.AltaComprobantePlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.CalcularInteresesInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteCancelacionPrecancelable;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteCancelacionPrecancelableUVA;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteConstitucionPlazoFijo;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobanteModificacionAccionVencimiento;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfigPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConfirmacionPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ConsultaTasasPlazosFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleCobroInteresesViewResponse;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.DetalleInteresesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.FinalizarPrecancelarUVAView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.InteresesView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ModificarAccionOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerOrdenesViewRequest;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ObtenerTenenciasPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.RecomendacionInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimulacionPrecancelableOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPlazoFijoOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SimularPrecancelarUVAView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarInView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.SolicitarPrecancelarOutView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoBprivView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.TenenciaPlazoFijoView;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.VerComprobanteModificacionVencimientoView;

/**
 * The Interface PlazoFijoManager.
 *
 * @author juan.pablo.picate
 */
public interface PlazoFijoManager {

    /**
     * Simular plazo fijo.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    Respuesta<SimularPlazoFijoOutView> simularPlazoFijo(SimularPlazoFijoInView inView);

    /**
     * Inicio.
     *
     * @return the respuesta
     */
    // Respuesta<InicioPlazoFijoView> inicio();

    /**
     * Configuracion plazo fijo que devueve legales, cuentas, importe minimo y
     * plazo minimo.
     * 
     * @param configPlazoFijoInView
     *            the configPlazoFijoInView
     * @return the respuesta
     */
    Respuesta<ConfigPlazoFijoOutView> configuracionPlazoFijo(ConfigPlazoFijoInView configPlazoFijoInView);

    /**
     * Acciones al vencimiento.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    Respuesta<AccionesAlVencimientoOutView> accionesAlVencimiento(AccionesAlVencimientoInView inView);

    /**
     * Grabar estadistica plazo fijo.
     *
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaPlazoFijo();

    /**
     * Grabar estadistica plazo fijo B priv.
     *
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaPlazoFijoBPriv();

    /**
     * Confirmacion plazo fijo.
     *
     * @param codigoPlazoFijo
     *            the codigo plazo fijo
     * @return the respuesta
     */
    Respuesta<ConfirmacionPlazoFijoOutView> confirmacionPlazoFijo(String codigoPlazoFijo, String banca);

    /**
     * Ver comprobante Plazo Fijo.
     *
     * @param comprobantePlazoFijoInView
     *            the comprobante plazo fijo in view
     * @return the respuesta
     */
    Respuesta<ComprobantePlazoFijoOutView> verComprobante(ComprobantePlazoFijoInView comprobantePlazoFijoInView);

    /**
     * Finalizar plazo fijo.
     *
     * @param finalizarPlazoFijoInView
     *            the finalizar plazo fijo in view
     * @return the respuesta
     */
    Respuesta<FinalizarPlazoFijoView> finalizarPlazoFijo(FinalizarPlazoFijoInView finalizarPlazoFijoInView);

    /**
     * Vaciar cache tasas.
     *
     * @return the respuesta
     */
    Respuesta<Boolean> vaciarCacheTasas();

    /**
     * Vaciar cache plazos fijos.
     *
     * @return the respuesta
     */
    Respuesta<Boolean> vaciarCachePlazosFijos();

    /**
     * Calcular intereses.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    Respuesta<InteresesView> calcularIntereses(CalcularInteresesInView inView);

    /**
     * Consulta de tasas de plazos fijos.
     *
     * @return the respuesta
     */
    Respuesta<ConsultaTasasPlazosFijoView> consultarTasas(ConsultaTasasPlazosFijoView consultaTasasPlazosFijoView);

    /**
     * Obtiene Tenencias de PlazoFijo Banca Personal.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    Respuesta<TenenciaPlazoFijoView> obtenerTenenciasPlazoFijo(ObtenerTenenciasPlazoFijoInView inview);

    /**
     * Obtiene Tenencias de PlazoFijo Banca Privada.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    Respuesta<TenenciaPlazoFijoBprivView> obtenerTenenciasPlazoFijoBPriv(ObtenerTenenciasPlazoFijoInView inview);

    /**
     * Graba estadistica para ver detalle de plazo fijo banca personal.
     *
	 * @param inView
	 *            the in view
     * @return the respuesta
     */
    Respuesta<ConfirmacionPlazoFijoOutView> verDetalle(AccionesAlVencimientoInView inView);

    /**
     * Graba estadistica para ver detalle de plazo fijo banca personal.
     *
	 * @param inView
	 *            the in view
     * @return the respuesta
     */
    Respuesta<ConfirmacionPlazoFijoOutView> verDetalleBPriv(AccionesAlVencimientoInView inView);

    /**
     * Solicitar Precancelar Plazo Fijo banca personal.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    Respuesta<SolicitarPrecancelarOutView> solicitarPrecancelar(SolicitarPrecancelarInView inview);

    /**
     * Simula la precancelacion de un plazo fijo precancelable.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    Respuesta<SimulacionPrecancelableOutView> simularPrecancelacion(SimularPrecancelarInView inview);

    /**
     * Finaliza la precancelacion.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    Respuesta<FinalizarPrecancelableOutView> finalizarPrecancelacion(SimularPrecancelarInView inview);

    /**
     * Ver comprobante precancelacion Devuelve lista de legales.
     *
     * @param comprobantePlazoFijoInView
     *            the comprobante plazo fijo in view
     * @return the respuesta
     */
	Respuesta<ComprobantePlazoFijoOutView> verComprobantePrecancelacion(
			ComprobantePlazoFijoInView comprobantePlazoFijoInView);

    /**
     * Graba estadistica al ingresar al stack de modificacion de accion al
     * vencimiento para privada y personal. Ademas inicializa el contador de
     * reintentos
     *
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaModificarAccion();

    /**
     * Modifica la accion al vencimiento.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    Respuesta<ModificarAccionOutView> modificarAccion(ModificarAccionInView inView);

    /**
	 * Graba estadistica y devuelve legales.
	 *
	 * @param inView
	 *            the in view
	 * @return the respuesta
	 */
    Respuesta<ComprobantePlazoFijoOutView> verComprobanteModificarAccion(VerComprobanteModificacionVencimientoView inView);

    /**
     * Obtener el detalle de la frecuencia de cobro de intereses.
     *
     * @param viewRequest
     *            the view request
     * @return the respuesta
     */
    Respuesta<DetalleCobroInteresesViewResponse> obtenerDetalleIntereses(DetalleInteresesViewRequest viewRequest);
    
    /**
     * Grabar estadistica plazo fijo.
     *
     * @return the respuesta
     */
    Respuesta<Void> grabarEstadisticaDetallePFVencidos();

    /**
	 * Descargar comprobante constitucion plazo fijo PDF.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteSuscripcionPDF(ComprobanteConstitucionPlazoFijo viewRequest);
	
    /**
	 * Descargar comprobante modificacion al vencimiento de un plazo fijo PDF.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteModificacionAccionVencimientoPDF(ComprobanteModificacionAccionVencimiento viewRequest);
	
	/**
	 * Descargar comprobante precancelacion de un plazo fijo precancelable PDF.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteCancelacionPrecancelable(ComprobanteCancelacionPrecancelable viewRequest);
	
	/**
     * Vaciar cache tasas.
     *
     * @return the respuesta
     */
    Respuesta<Boolean> vaciarCacheAcciones();

	/**
	 * Obtener ordenes por cuenta.
	 *
	 * @param request
	 *            the request
	 * @return the respuesta
	 */
	Respuesta<OrdenesView> obtenerOrdenesPorCuenta(ObtenerOrdenesViewRequest request);

	/**
	 * Grabar estadistica ver detalle ordenes y operaciones plazo fijo.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaDetalleOrdenes();

	/**
	 * Grabar estadistica cuando carga el stack de confirmacion de ordenes y
	 * operaciones plazo fijo.
	 *
	 * @return the respuesta
	 */
	Respuesta<Void> grabarEstadisticaConfirmarOrdenes();

	/**
	 * Confirmar Orden Plazo fijo.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
	Respuesta<ConfirmarOrdenPFView> confirmarOrden(ConfirmarOrdenInEntity entity);
	
	/**
	 * Alta de comprobante en SCOMP.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<AltaComprobantePlazoFijoView> altaComprobante(AltaComprobantePlazoFijoView viewRequest);

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
	 * Obtener detalle PF interesante BP.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the respuesta
	 */
	Respuesta<List<FrecuenciaCobroPFInteresanteView>> obtenerDetallePFInteresanteBP(DetallePFInteresanteInView detalleIn);

	Response exportarMovimientos(TipoBancaEnum tipoBanca);
	
	Respuesta<SimularPrecancelarUVAView> simularPrecancelarUVA(SimularPrecancelarInView request);
	
	Respuesta<FinalizarPrecancelarUVAView> finalizarPrecancelarUVA(SimularPrecancelarInView request);

	Respuesta<Void> verComprobantePrecancelarUVA(String banca);
	
	/**
	 * Descargar comprobante precancelacion de un plazo fijo precancelable UVA PDF.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
	Respuesta<ReporteView> descargarComprobanteCancelacionPrecancelableUVA(ComprobanteCancelacionPrecancelableUVA viewRequest);

	Respuesta<RecomendacionResponseEntity> obtenerRecomendacionManager(RecomendacionInView viewRequest);
}
