/*
 *
 */
package ar.com.santanderrio.obp.servicios.nuevopago.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.*;
import ar.com.santanderrio.obp.servicios.cuit.entities.Cuit;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfiguracionRecargaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaConfiguracionView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.PagoPendienteView;
import ar.com.santanderrio.obp.servicios.seguros.entities.EmisionOfertaIntegrada;
import ar.com.santanderrio.obp.servicios.seguros.entities.GastoProtegido;
import ar.com.santanderrio.obp.servicios.seguros.entities.Poliza;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.view.PuntoDeAccesoView;

/**
 * The Interface NuevoPagoSEI.
 */
@Path("/nuevoPago")
public interface NuevoPagoSEI {

    /**
     * Obtengo las facturas pendientes del cliente.
     *
     * @param medioPagoView
     *            the medio pago view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerPagos")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<MedioPagoSelectionView> obtenerPagos(MedioPagoView medioPagoView);

    /**
     * Valido el cuit pasado como parametro.
     *
     * @param cuit
     *            the cuit
     * @return the respuesta
     */
    @POST
    @Path("/validarCuit")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<String> validarCuit(Cuit cuit);

    /**
     * Obtiene la lista de cuentas para realizar un pago sin factura, ej. VEP,
     * DOMESTICO
     *
     * @param consulta
     *            the consulta
     * @return the respuesta
     */
    @POST
    @Path("/obtenerCuentas")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<MedioPagoSelectionView> obtenerCuentas(ConsultaConfiguracionView consulta);

    /**
     * Obtiene la lista de cuentas e importes para realizar una recarga de telefono
     * o sube.
     *
     * @param consultaConfiguracionRecargaView
     *            the consulta configuracion recarga view
     * @return the respuesta
     */
    @POST
    @Path("/obtenerConfiguracionRecarga")
    @Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfiguracionRecargaView> obtenerConfiguracionRecarga(
            ConsultaConfiguracionView consultaConfiguracionRecargaView);

    /**
     * Estadistica comprobante nuevo pago.
     *
     * @param seleccion
     *            the seleccion
     */
    @POST
    @Path("/estadisticaComprobanteNuevoPago")
    void estadisticaComprobanteNuevoPago(CuentaSeleccionView seleccion);

    /**
     * Solicitud de Pago puntual. Inicio de la acción pagar/recargar para
     * empresa/servicio vinculado a PMCuentas. referencia 9775.
     *
     * @author Manuel.Vargas B041299
     * @param pagoPendienteView
     *            the pago pendiente view
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaPagoPuntual")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    void grabarEstadisticaPagoPuntual(PagoPendienteView pagoPendienteView);

    /**
     * Pago puntual.
     *
     * @param nuevoPagoView
     *            the nuevo pago view
     * @return the respuesta
     */
    @POST
    @Path("/confirmarPagoPuntual")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<NuevoPago> confirmarPagoPuntual(NuevoPago nuevoPagoView);

    /**
     * Estadistica Nueva recarga – Ingreso N° identificador – Acceso.
     *
     * @param puntoDeAcceso
     *            the punto de acceso
     * @return the respuesta
     */
    @POST
    @Path("/grabarEstadisticaRecargaIngresoNumero")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> grabarEstadisticaRecargaIngresoNumero(PuntoDeAccesoView puntoDeAcceso);

    /**
     * Verificar factores autenticacion.
     *
     * @return the respuesta
     */
    @POST
    @Path("/verificarFactoresAutenticacion")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Boolean> verificarFactoresAutenticacion();

    /**
     * Descargar comprobante PDF.
     *
     * @param nuevoPagoView
     *            the nuevo pago view
     * @return the respuesta
     */
    @POST
    @Path("/descargarComprobantePDF")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<ReporteView> descargarComprobantePDF(NuevoPago nuevoPagoView);

    /**
     * Continuar Pago - Carga en sesion el hash del pago.
     *
     * @param nuevoPagoView
     *            the nuevo pago view
     * @return the respuesta
     */
    @POST
    @Path("/continuarPago")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> continuarPago(NuevoPago nuevoPagoView);

    @POST
    @Path("/emitirGastosProtegidos")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<EmisionGastosProtegidosView> emitirGastosProtegidos(EmisionOfertaIntegrada nuevaEmisioView);

    @POST
    @Path("/consultarGastosProtegidos")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<GastoProtegidoView> consultarGastosProtegidos(GastoProtegido gastoProtegido);

    @POST
    @Path("/consultarFlagCompraProtegida")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<FlagCompraProtegidaView> getFlagCompraProtegida();

    @POST
    @Path("/consultarPolizaExistente")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Poliza> consultarPolizaExistente(String tokenJwt, String ramo);
}
