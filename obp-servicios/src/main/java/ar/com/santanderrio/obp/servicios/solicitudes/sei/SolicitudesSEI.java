/*
 * 
 */
package ar.com.santanderrio.obp.servicios.solicitudes.sei;

import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.seguridad.CustomPreAuthorize;
import ar.com.santanderrio.obp.servicios.solicitudes.view.AdhesionWomenView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.BeneficioTransferiTuSueldoView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ConfirmacionBajaAdhesionView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.SolicitudesView;
import ar.com.santanderrio.obp.servicios.solicitudes.view.ValidaAltaView;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.view.TrackingTarjetasView;


/**
 * The Interface SolicitudesSEI.
 */
@Path("/solicitudes")
public interface SolicitudesSEI {
    
    /**
     * Obtener cuentas Y paquetes.
     *
     * @return the respuesta
     */
    @POST
    @Path("/obtenerCuentasYPaquetes")
    @CustomPreAuthorize(AccionController.IR_INICIO_CUENTA_Y_PAQUETES)
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<SolicitudesView> obtenerCuentasYPaquetes();

    /**
     * Obtener tarjetas.
     *
     * @return the respuesta
     */
    @POST
    @Path("/obtenerTarjetas")
    @CustomPreAuthorize(AccionController.IR_INICIO_TARJETAS_SOLICITUDES)
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<SolicitudesView> obtenerTarjetas();
    
    
    /**
	 * Obtiene datos basicos para las solicitudes de <b>Caja de Ahorro</b> y
	 * <b>Cuenta Tiutlo</b>.
     * 
	 * @return the respuesta
     */
    @POST
    @Path("/obtenerDatosBasicosACCT")
	@Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<Map<String, String>> obtenerDatosBasicosACCT();
    
    @POST
    @Path("/solicitarCtaTit")
	@Produces(value = { MediaType.APPLICATION_JSON })
    Respuesta<ValidaAltaView> solicitudCtaTit();
    
    
    /**
	 * obtenerTrackingTarjetas.
	 *
	 * @return the respuesta
	 */
    @POST
    @Path("/obtenerTrackingTarjetas")
    @CustomPreAuthorize(AccionController.IR_INICIO_TARJETAS_SOLICITUDES)
    @Produces(value = {MediaType.APPLICATION_JSON})
    Respuesta<TrackingTarjetasView> obtenerTrackingTarjetas();

    /**
	 * obtenerOtrosMediosPago.
	 *
	 * @return the respuesta
	 */
    @POST
    @Path("/obtenerOtrosMediosPago")
    @CustomPreAuthorize(AccionController.IR_INICIO_MEDIOS_PAGO_SOLICITUDES)
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<SolicitudesView> obtenerOtrosMediosPago();

    @POST
    @Path("/inicioAdhesionWomen")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AdhesionWomenView> inicioAdhesionWomen();
    
    @POST
    @Path("/configuracionAdhesionWomen")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AdhesionWomenView> configuracionAdhesionWomen();
    
    @POST
    @Path("/confirmacionAdhesionWomen")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AdhesionWomenView> confirmacionAdhesionWomen();

    @POST
    @Path("/ejecutarAdhesionWomen")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AdhesionWomenView> ejecutarAdhesionWomen(AdhesionWomenView tarjetasParaHabilitar);
    
    
    @POST
    @Path("/ejecutarBajaAdhesionWomen")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AdhesionWomenView> ejecutarBajaAdhesionWomen(AdhesionWomenView tarjetasHabilitadas);
    
    
    @POST
    @Path("/confirmacionBajaAdhesionWomen")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConfirmacionBajaAdhesionView> confirmacionBajaAdhesionWomen();
    
    @POST
    @Path("/inicioBeneficioTransferiTuSueldo")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<BeneficioTransferiTuSueldoView> inicioBeneficioTransferiTuSueldo();
    
    @POST
    @Path("/solicitarBeneficioTransferiTuSueldo")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<BeneficioTransferiTuSueldoView> solicitarBeneficioTransferiTuSueldo();
    
}
