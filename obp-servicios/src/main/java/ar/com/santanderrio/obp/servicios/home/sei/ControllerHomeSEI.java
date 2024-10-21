/*
 * 
 */
package ar.com.santanderrio.obp.servicios.home.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;

/**
 * The Interface HomeSEI.
 */
@Path("/controller")
public interface ControllerHomeSEI {

    /** The Constant NOTIFICAR_ACCESO_CONSULTAS_CUENTAS. */
    String NOTIFICAR_ACCESO_CONSULTAS_CUENTAS = "/notificarAccesoConsultasCuentas";

    /** The notificar acceso una sola cuenta rtl. */
    String NOTIFICAR_ACCESO_UNA_SOLA_CUENTA_RTL = "/notificarAccesoUnaSolaCuentaRTL";
    
    /** The Constant NOTIFICAR_ACCESO_CONSULTAS_CUENTAS. */
    String NOTIFICAR_ACCESO_CONSULTAS_CUENTAS_BANCA_PRIVADA = "/notificarAccesoConsultasBancaPrivada";

    /** The notificar acceso una sola cuenta bp. */
    String NOTIFICAR_ACCESO_UNA_SOLA_CUENTA_BP = "/notificarAccesoUnaSolaCuentaBP";
    
    /** The Constant NOTIFICAR_ACCESO_CONSULTAS_TARJETAS. */
    String NOTIFICAR_ACCESO_CONSULTAS_TARJETAS = "/notificarAccesoConsultasTarjetas";

    /** The Constant NOTIFICAR_ACCESO_CONSULTAS_PRESTAMOS. */
    String NOTIFICAR_ACCESO_CONSULTAS_PRESTAMOS = "/notificarAccesoConsultasPrestamos";

    /** The Constant NOTIFICAR_ACCESO_TRANSACCIONES_CALENDARIO. */
    String NOTIFICAR_ACCESO_TRANSACCIONES_CALENDARIO = "/notificarAccesoTransaccionesCalendario";

    /** The Constant NOTIFICAR_ACCESO_TRANSACCIONES_TRANSFERENCIAS. */
    String NOTIFICAR_ACCESO_TRANSACCIONES_TRANSFERENCIAS = "/notificarAccesoTransaccionesTransferencias";

    /** The Constant NOTIFICAR_ACCESO_TRANSACCIONES_PAGO_TARJETAS. */
    String NOTIFICAR_ACCESO_TRANSACCIONES_PAGO_TARJETAS = "/notificarAccesoTransaccionesPagoTarjetas";

    /** The Constant NOTIFICAR_ACCESO_TRANSACCIONES_PAGO_HABERES. */
    String NOTIFICAR_ACCESO_TRANSACCIONES_PAGO_HABERES = "/notificarAccesoTransaccionesPagoHaberes";

    /** The Constant NOTIFICAR_ACCESO_SOLICITUDES_PRESTAMOS. */
    String NOTIFICAR_ACCESO_SOLICITUDES_PRESTAMOS = "/notificarAccesoSolicitudesPrestamos";

    /** The Constant NOTIFICAR_ACCESO_CONSULTA_MONEDERO. */
    String NOTIFICAR_ACCESO_CONSULTA_MONEDERO = "/notificarAccesoConsultasMonedero";

    /** The Constant NOTIFICAR_ACCESO_SOLICITUDES_TARJETA_MONEDERO. */
    String NOTIFICAR_ACCESO_SOLICITUDES_TARJETA_MONEDERO = "/notificarAccesoSolicitudesTarjetaMonedero";

    /** The Constant NOTIFICAR_ACCESO_COMPROBANTES. */
    String NOTIFICAR_ACCESO_COMPROBANTES = "/notificarAccesoConsultasComprobantes";

    /** The Constant NOTIFICAR_ACCESO_SOLICITUDES_TARJETA_CREDITO_ADICIONAL. */
    String NOTIFICAR_ACCESO_SOLICITUDES_TARJETA_CREDITO_ADICIONAL = "/notificarAccesoSolicitudesTarjetaCreditoAdicional";

    /** The Constant NOTIFICAR_ACCESO_TRANSACCIONES_BILLETERA. */
    String NOTIFICAR_ACCESO_TRANSACCIONES_BILLETERA = "/notificarAccesoTransaccionesBilleteraVirtual";

    /** The notificar acceso consulta resumen impositivo. */
    String NOTIFICAR_ACCESO_CONSULTA_RESUMEN_IMPOSITIVO = "/notificarAccesoConsultasResumenImpositivo";

    /** The notificar acceso consulta resumen impositivo. */
    String NOTIFICAR_ACCESO_CONSULTA_DESCUENTO_CHEQUES = "/notificarAccesoConsultasDescuentoCheques";
    
    /** The notificar acceso consulta resumen impositivo. */
    String NOTIFICAR_ACCESO_TRANSACCIONES_TRANSFERENCIAS_BANCA_PRIVADA = "/notificarAccesoTransaccionesTransferenciasBancaPrivada";

    /** The notificar acceso servicios de inversion. */
    String NOTIFICAR_ACCESO_SERVICIOS_DE_INVERSION = "/notificarAccesoServiciosDeInversion";

    /**
     * Notificar AccesoConsultasCuentas.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_CONSULTAS_CUENTAS)
    @CustomPreAuthorize({AccionController.IR_INICIO_CUENTAS, AccionController.IR_INICIO_UNA_SOLA_CUENTA})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoConsultasCuentas();
    
    /**
     * Notificar AccesoConsultasCuentasBancaPrivada.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_CONSULTAS_CUENTAS_BANCA_PRIVADA)
    @CustomPreAuthorize({AccionController.IR_INICIO_CUENTAS_BANCA_PRIVADA, AccionController.IR_INICIO_UNA_SOLA_CUENTA_BANCA_PRIVADA})
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoConsultasCuentasBancaPrivada();

    /**
     * Notificar AccesoConsultasTarjetas.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_CONSULTAS_TARJETAS)
    @CustomPreAuthorize(AccionController.IR_INICIO_TARJETAS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoConsultasTarjetas();

    /**
     * Notificar AccesoConsultasPrestamos.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_CONSULTAS_PRESTAMOS)
    @CustomPreAuthorize(AccionController.IR_INICIO_PRESTAMOS_CONSULTAS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoConsultasPrestamos();

    /**
     * Notificar AccesoTransaccionesCalendario.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_TRANSACCIONES_CALENDARIO)
    @CustomPreAuthorize(AccionController.IR_INICIO_CALENDARIO_DE_PAGOS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoTransaccionesCalendario();

    /**
     * Notificar AccesoTransaccionesTransferencias.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_TRANSACCIONES_TRANSFERENCIAS)
    @CustomPreAuthorize(AccionController.IR_INICIO_TRASNFERENCIAS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoTransaccionesTransferencias();

    /**
     * Notificar AccesoTransaccionesPagoHaberes.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_TRANSACCIONES_PAGO_HABERES)
    @CustomPreAuthorize(AccionController.IR_INICIO_PAGO_DE_HABERES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoTransaccionesPagoHaberes();

    /**
     * Notificar AccesoSolicitudesPrestamos.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_SOLICITUDES_PRESTAMOS)
    @CustomPreAuthorize(AccionController.IR_INICIO_PRESTAMOS_SOLICITUDES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoSolicitudesPrestamos();

    /**
     * Notificar AccesoConsultasMonedero.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_CONSULTA_MONEDERO)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoConsultasMonedero();

    /**
     * Notificar AccesoSolicitudesTarjetaMonedero.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_SOLICITUDES_TARJETA_MONEDERO)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoSolicitudesTarjetaMonedero();

    /**
     * Notificar AccesoComprobantes.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_COMPROBANTES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoComprobantes();

    /**
     * Notificar AccesoSolicitudesTarjetaCreditoAdicional.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_SOLICITUDES_TARJETA_CREDITO_ADICIONAL)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoSolicitudesTarjetaCreditoAdicional();

    /**
     * Notificar notificarAccesoTransaccionesBilleteraVirtual.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_TRANSACCIONES_BILLETERA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoTransaccionesBilleteraVirtual();

    /**
     * Notificar notificarAccesoTransaccionesBilleteraVirtual.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_CONSULTA_RESUMEN_IMPOSITIVO)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoConsultaResumenImpositivo();

    /**
     * Notificar notificarAccesoServiciosDeInversion.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_CONSULTA_DESCUENTO_CHEQUES)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoConsultaDescuentoCheques();
    
    /**
     * Notificar notificarAccesoTransaccionesTransferenciasBancaPrivada.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_TRANSACCIONES_TRANSFERENCIAS_BANCA_PRIVADA)
    @CustomPreAuthorize(AccionController.IR_TRANSFERENCIAS_BANCA_PRIVADA)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoTransaccionesTransferenciasBancaPrivada();


    /**
     * Notificar notificarAccesoServiciosDeInversion.
     *
     * @return the respuesta
     */
    @POST
    @Path(NOTIFICAR_ACCESO_SERVICIOS_DE_INVERSION)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Void> notificarAccesoServiciosDeInversion();

}
