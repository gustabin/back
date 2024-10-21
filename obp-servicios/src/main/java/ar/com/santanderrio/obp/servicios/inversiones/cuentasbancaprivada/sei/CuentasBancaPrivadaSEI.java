/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ComprobanteTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConfirmarTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaResumenCuentaBP;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaUltimosMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CopiarMensajeView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasIntermedioView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DatosComprobanteTransferencia;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.DetalleMovimientosView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.IntervinienteinEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.MovimientoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.ComprobanteNuevaTransferencia;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.DetalleCuentaCBUBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.NuevaTransferenciaView;
import ar.com.santanderrio.obp.servicios.inversiones.cuentasbancaprivada.view.SelectorCuentasBancaPrivadaView;
import ar.com.santanderrio.obp.servicios.inversiones.descargapdf.entities.ListadoPDF;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface CuentasBancaPrivadaSEI.
 */
@Path("/cuentasBancaPrivada")
public interface CuentasBancaPrivadaSEI {

    /**
     * Obtener inicio.
     *
     * @return the respuesta
     */
    @POST
    @Path("/obtenerInicio")
    @CustomPreAuthorize({ AccionController.IR_INICIO_CUENTAS_BANCA_PRIVADA,
            AccionController.IR_INICIO_UNA_SOLA_CUENTA_BANCA_PRIVADA })
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<CuentasIntermedioView> obtenerInicio();

    /**
     * Modificar apodo.
     *
     * @param cuenta
     *            the cuenta
     * @return the respuesta
     */
    @POST
    @Path("/modificarApodo")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> modificarApodo(ConsultaCuentaView cuenta);

    /**
     * Ver detalle CBU.
     *
     * @param cuenta
     *            the cuenta
     * @return the respuesta
     */
    @POST
    @Path("/verDetalleCBU")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<DetalleCuentaCBUBancaPrivadaView> verDetalleCBU(ConsultaCuentaView cuenta);

    /**
     * Obtener cuentas.
     *
     * @param cuenta
     *            the cuenta
     * @return the respuesta
     */
    @POST
    @Path("/obtenerCuentas")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<SelectorCuentasBancaPrivadaView> obtenerCuentas(ConsultaCuentaView cuenta);

    /**
     * Obtener movimientos.
     *
     * @param consultaUltimosMovimientosView
     *            the consulta ultimos movimientos view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerMovimientos")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<MovimientoView> obtenerMovimientos(ConsultaUltimosMovimientosView consultaUltimosMovimientosView);

    /**
     * Descargar comprobante CBU cuenta.
     *
     * @return the respuesta
     */
    @POST
    @Path("/descargarComprobanteCBUCuenta")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ReporteView> descargarComprobanteCBUCuenta();

    /**
     * Copiar CBU.
     *
     * @return the respuesta
     */
    @POST
    @Path("/copiarCBU")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<CopiarMensajeView> copiarCBU();

    /**
     * Compartir CBU.
     *
     * @return the respuesta
     */
    @POST
    @Path("/compartirCBU")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> compartirCBU();

    /**
     * Obtener mas movimientos.
     *
     * @return the respuesta
     */
    @POST
    @Path("/obtenerMasMovimientos")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<MovimientoView> obtenerMasMovimientos();

    /**
     * Ver detalle movimiento.
     *
     * @return the respuesta
     */
    @POST
    @Path("/verDetalleMovimiento")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<DetalleMovimientosView> verDetalleMovimiento();

    /**
     * Nueva transferencia.
     *
     * @return the respuesta
     */
    @POST
    @Path("/nuevaTransferencia")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<NuevaTransferenciaView> nuevaTransferencia();

    /**
     * Obtener interviniente.
     *
     * @param entity
     *            the entity
     * @return the respuesta
     */
    @POST
    @Path("/obtenerInterviniente")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<IntervinienteView> obtenerInterviniente(IntervinienteinEntity entity);

    /**
     * Confirmar transferencia.
     *
     * @param entity
     *            the entity
     * @return the respuesta
     */
    @POST
    @Path("/confirmarTransferencia")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfirmarTransferenciaView> confirmarTransferencia(ConfirmarTransferenciaInEntity entity);

    /**
     * Ver comprobante transferencia.
     *
     * @param viewRequest
     *            the view request
     * @return the respuesta
     */
    @POST
    @Path("/comprobanteTransferencia")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ComprobanteTransferenciaView> verComprobanteTransferencia(DatosComprobanteTransferencia viewRequest);

    /**
     * Exportar movimientos.
     *
     * @return the response
     */
    @POST
    @Path("/exportarMovimientos")
    @Produces({ "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8",
            MediaType.APPLICATION_JSON })
    Response exportarMovimientos();

    /**
     * Descargar comprobante PDF de suscripcion de un fondo.
     *
     * @param viewRequest
     *            the view request
     * @return the respuesta
     */
    @POST
    @Path("/descargarComprobantePDFNuevaTransferencia")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargarComprobantePDFNuevaTransferencia(ComprobanteNuevaTransferencia viewRequest);

    @POST
    @Path("/exportarMovimientosPDF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> exportarMovimientosPDF();
    
    @POST
    @Path("/obtenerListaResumen")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ResumenesMensualesCuentaView> obtenerListaResumen(ConsultaResumenCuentaBP cuenta);
    
	@POST
	@Path("/descargaResumenesPDF")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<ListadoPDF> obtenerResumenesPDF(ConsultaResumenCuentaBP cuenta);

}