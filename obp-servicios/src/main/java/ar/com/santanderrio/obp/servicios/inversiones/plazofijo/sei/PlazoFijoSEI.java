/*
 * 
 */

package ar.com.santanderrio.obp.servicios.inversiones.plazofijo.sei;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
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
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.view.ComprobantePrecancelarUVA;
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
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface PlazoFijoSEI.
 *
 * @author juan.pablo.picate
 */
@Path("/plazoFijo")
public interface PlazoFijoSEI {

    /**
     * Consultar Condiciones para Plazo Fijo Servicio CNSSIMPFCN_160.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    @POST
    @Path("/consultar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<SimularPlazoFijoOutView> simularPlazoFijo(SimularPlazoFijoInView inView);

    /**
     * Consulta las cuentas, importe minimo, plazo minimo y legales para un
     * plazo fijo.
     *
     * @param configPlazoFijoInView
     *            the configPlazoFijoInView
     * @return the respuesta
     */
    @POST
    @Path("/configuracion")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ConfigPlazoFijoOutView> configuracionPlazoFijo(ConfigPlazoFijoInView configPlazoFijoInView);

    /**
     * Finalizar plazo fijo.
     *
     * @param finalizarPlazoFijoInView
     *            the finalizar plazo fijo in view
     * @return the respuesta
     */
    @POST
    @Path("/finalizarPlazoFijo")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<FinalizarPlazoFijoView> finalizarPlazoFijo(FinalizarPlazoFijoInView finalizarPlazoFijoInView);

    /**
     * Obtiene las acciones al vencimiento para el codigo de plazo fijo
     * recibido.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    @POST
    @Path("/accionesAlVencimiento")
    @Consumes(value = { MediaType.APPLICATION_JSON })
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<AccionesAlVencimientoOutView> accionesAlVencimiento(AccionesAlVencimientoInView inView);

    /**
     * Grabar estadistica plazo fijo.
     *
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaPlazoFijo")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> grabarEstadisticaPlazoFijo();

    /**
     * Grabar estadistica plazo fijo B priv.
     *
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaPlazoFijoBPriv")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> grabarEstadisticaPlazoFijoBPriv();

    /**
     * Confirmacion plazo fijo.
     *
     * @param codigoPlazoFijo
     *            the codigo plazo fijo
     * @return the respuesta
     */
    @POST
    @Path("/confirmacion")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ConfirmacionPlazoFijoOutView> confirmacionPlazoFijo(AccionesAlVencimientoInView codigoPlazoFijo);

    /**
     * Ver Comprobante plazo fijo.
     *
     * @param comprobantePlazoFijoInView
     *            the comprobante plazo fijo in view
     * @return the respuesta
     */
    @POST
    @Path("/verComprobantePlazoFijo")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ComprobantePlazoFijoOutView> verComprobante(ComprobantePlazoFijoInView comprobantePlazoFijoInView);

    /**
     * Calcular intereses.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    @POST
    @Path("/calcularIntereses")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<InteresesView> calcularIntereses(CalcularInteresesInView inView);

    /**
     * Obtener tasas plazos fijos.
     *
     * @return the respuesta
     */
    @POST
    @Path("/consultarTasas")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ConsultaTasasPlazosFijoView> consultarTasas(ConsultaTasasPlazosFijoView consultaTasasPlazosFijoView);

    /**
     * Recupera las tenencias de Plazos Fijos del usuario de Banca Personal .
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */

    @POST
    @Path("/tenenciasPlazoFijo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<TenenciaPlazoFijoView> obtenerTenenciasPlazoFijo(ObtenerTenenciasPlazoFijoInView inview);

    /**
     * Recupera las tenencias de Plazos Fijos del usuario de Banca Privada .
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */

    @POST
    @Path("/tenenciasPlazoFijoBPriv")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<TenenciaPlazoFijoBprivView> obtenerTenenciasPlazoFijoBPriv(ObtenerTenenciasPlazoFijoInView inview);

    /**
     * Grabar estadistica ver detalle banca personal.
     *
	 * @param codigoPlazoFijo
	 *            the codigo plazo fijo
     * @return the respuesta
     */
    @POST
    @Path("/verDetalle")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ConfirmacionPlazoFijoOutView> verDetalle(AccionesAlVencimientoInView codigoPlazoFijo);

    /**
     * Grabar estadistica ver detalle banca privada.
     *
	 * @param codigoPlazoFijo
	 *            the codigo plazo fijo
     * @return the respuesta
     */
    @POST
    @Path("/verDetalleBPriv")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ConfirmacionPlazoFijoOutView> verDetalleBPriv(AccionesAlVencimientoInView codigoPlazoFijo);

    /**
     * Solicitar Precancelar Plazo Fijo banca personal.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    @POST
    @Path("/solicitarPrecancelar")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<SolicitarPrecancelarOutView> solicitarPrecancelar(SolicitarPrecancelarInView inview);

    /**
     * Solicitar Precancelar Plazo Fijo banca personal.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    @POST
    @Path("/simularPrecancelar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<SimulacionPrecancelableOutView> simularPrecancelar(SimularPrecancelarInView inview);

    /**
     * Ver Comprobante plazo fijo.
     *
     * @param comprobantePlazoFijoInView
     *            the comprobante plazo fijo in view
     * @return the respuesta
     */
    @POST
    @Path("/verComprobantePrecancelar")
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<ComprobantePlazoFijoOutView> verComprobantePrecancelacion(
			ComprobantePlazoFijoInView comprobantePlazoFijoInView);

    /**
     * Finalizar Plazo Fijo banca personal.
     *
     * @param inview
     *            the inview
     * @return the respuesta
     */
    @POST
    @Path("/finalizarPrecancelar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<FinalizarPrecancelableOutView> finalizarPrecancelar(SimularPrecancelarInView inview);

    /**
     * Grabar estadistica modificar accion al vencimiento.
     *
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaModificarAccion")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> grabarEstadisticaModificarAccion();

    /**
     * Efectua la modificacion de la accion al vencimiento.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    @POST
    @Path("/modificarAccion")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ModificarAccionOutView> modificarAccion(ModificarAccionInView inView);

    /**
	 * Ver Comprobante plazo fijo.
	 *
	 * @param inView
	 *            the in view
	 * @return the respuesta
	 */
    @POST
    @Path("/verComprobanteModificarAccion")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ComprobantePlazoFijoOutView> verComprobanteModificarAccion(VerComprobanteModificacionVencimientoView inView);

    /**
     * Efectua la modificacion de la accion al vencimiento.
     *
     * @param inView
     *            the in view
     * @return the respuesta
     */
    @POST
    @Path("/detalleIntereses")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<DetalleCobroInteresesViewResponse> obtenerDetalleIntereses(DetalleInteresesViewRequest inView);
    
    /**
     * Grabar estadistica plazo fijo.
     *
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaDetallePFVencidos")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> grabarEstadisticaDetallePFVencidos();

    /**
	 * Descargar comprobante PDF de consitucion de un plazo fijo.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
    @POST
    @Path("/descargarComprobanteConstitucionPDF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargarComprobanteConstitucionPDF(ComprobanteConstitucionPlazoFijo viewRequest);
    
    /**
	 * Descargar comprobante PDF de modificacion accion al vencimiento de un
	 * plazo fijo tradicional.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
    @POST
    @Path("/descargarComprobanteModificacionAccionVencimientoPDF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargarComprobanteModificacionAccionVencimientoPDF(ComprobanteModificacionAccionVencimiento viewRequest);

    /**
	 * Descargar comprobante PDF de cancelacion plazo fijo precancelable.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
    @POST
    @Path("/descargarComprobanteCancelacionPrecancelable")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargarComprobanteCancelacionPrecancelable(ComprobanteCancelacionPrecancelable viewRequest);
    
    /**
	 * Obtener ordenes por cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the respuesta
	 */
    @POST
    @Path("/obtenerOrdenesOperacionesPorCuenta")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<OrdenesView> obtenerOrdenesPorCuenta(ObtenerOrdenesViewRequest cuenta);
    
    /**
     * Grabar estadistica ver detalle de ordenes y operaciones.
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaDetalleOrdenes")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> grabarEstadisticaDetalleOrdenes();
    
    /**
     * Grabar estadistica ver detalle de ordenes y operaciones.
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaConfirmarOrdenes")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> grabarEstadisticaConfirmarOrdenes();
    
    
    /**
	 * Confirmar orden.
	 *
	 * @param entity
	 *            the entity
	 * @return the respuesta
	 */
    @POST
	@Path("/confirmarOrdenPF")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmarOrdenPFView> confirmarOrden(ConfirmarOrdenInEntity entity);
    
    /**
	 * Alta de comprobante en SCOMP.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
    @POST
    @Path("/altaComprobante")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AltaComprobantePlazoFijoView> altaComprobante(AltaComprobantePlazoFijoView viewRequest);
    
    
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
	Respuesta<RendimientoView>obtenerRendimiento();
	
	
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
	Respuesta<RendimientoBPrivView>obtenerRendimientoBPriv();

	/**
	 * Obtener detalle PF interesante BP.
	 *
	 * @param detalleIn
	 *            the detalle in
	 * @return the respuesta
	 */
	@POST
    @Path("/obtenerDetallePFInteresanteBP")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<List<FrecuenciaCobroPFInteresanteView>> obtenerDetallePFInteresanteBP(DetallePFInteresanteInView detalleIn);	
	
	@POST
	@Path("/exportarMovimientosRTL")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarMovimientosRTL();
	
	@POST
	@Path("/exportarMovimientosPriv")
	@Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
			MediaType.APPLICATION_JSON })
	Response exportarMovimientosBP();
	
	/**
	 * Solicitar precancelacion plazo fijo UVA.
	 *
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/simularPrecancelarUVA")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<SimularPrecancelarUVAView> simularPrecancelarUVA(SimularPrecancelarInView request);
	
	/**
	 * Solicitar precancelacion plazo fijo UVA.
	 *
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/finalizarPrecancelarUVA")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<FinalizarPrecancelarUVAView> finalizarPrecancelarUVA(SimularPrecancelarInView request);
	
	/**
     * Ver Comprobante plazo fijo.
     *
     * @param comprobantePlazoFijoInView
     *            the comprobante plazo fijo in view
     * @return the respuesta
     */
    @POST
    @Path("/verComprobantePrecancelarUVA")
    @Produces(MediaType.APPLICATION_JSON)
	Respuesta<Void> verComprobantePrecancelarUVA(ComprobantePrecancelarUVA request);
    
    /**
	 * Descargar comprobante PDF de cancelacion plazo fijo precancelable.
	 *
	 * @param viewRequest
	 *            the view request
	 * @return the respuesta
	 */
    @POST
    @Path("/descargarComprobantePrecancelarUVA")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargarComprobantePrecancelarUVA(ComprobanteCancelacionPrecancelableUVA viewRequest);
    
    
    @POST
    @Path("/obtenerRecomendaciones")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<RecomendacionResponseEntity> obtenerRecomendacion(RecomendacionInView viewRequest);
}
