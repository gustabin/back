/*
 * 
 */
package ar.com.santanderrio.obp.servicios.turnosweb.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.home.web.view.OperadorEjecutivoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.AltaModificacionCitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.BajaTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.CitaOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.ComprobanteTurnoRemotoView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesInView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.HorariosDisponiblesOutView;
import ar.com.santanderrio.obp.servicios.turnosweb.view.SucursalesOutView;

/**
 * The Interface TurnosWebSEI.
 *
 * @author IT Resources
 */
@Path("/turnosWeb")
public interface TurnosWebSEI {


	/**
	 * Consulta cita.
	 *
	 * @param citaInView the cita in view
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaCita")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<CitaOutView> consultaCita(CitaInView citaInView);
    
    /**
	 * Consulta los horarios disponibles para cierto sector y sucursal.
	 *
	 * @param horariosDisponiblesInView
	 *            the horarios disponibles in view
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaHorarioDisponibles")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<HorariosDisponiblesOutView> consultaHorarioDisponibles(HorariosDisponiblesInView horariosDisponiblesInView);
    
    /**
	 * Consulta las sucursales disponibles para solicitar turno.
	 *
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaSucursales")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<SucursalesOutView> consultaSucursales();
    
    /**
	 * Puede dar de alta o modificar un turno para ambos sectores.
	 *
	 * @param altaModificacionCitaInView
	 *            the alta modificacion cita in view
	 * @return the respuesta
	 */
    @POST
    @Path("/altaModificacionCita")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AltaModificacionCitaOutView> altaModificacionCita(AltaModificacionCitaInView altaModificacionCitaInView);
    
    /**
	 * Da de baja un turno .
	 *
	 * @param bajaTurnoInView
	 *            the baja turno in view
	 * @return the respuesta
	 */
    @POST
    @Path("/bajaTurno")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> bajaTurno(BajaTurnoInView bajaTurnoInView);
    	
    /**
	 * descarga comprobante pdf.
	 *
	 * @param comprobanteTurnoInView
	 *            the comprobante turno in view
	 * @return the respuesta
	 */
    @POST
    @Path("/descargarComprobantePDF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargarComprobantePDF(ComprobanteTurnoInView comprobanteTurnoInView);
    
    /**
	 * descarga comprobante remoto.
	 *
	 * @param comprobanteTurnoRenitiView the comprobante turno Remoto view
	 * @return the respuesta
	 */
    @POST
    @Path("/descargarComprobanteRemotoPDF")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ReporteView> descargarComprobanteRemotoPDF(ComprobanteTurnoRemotoView comprobanteTurnoRemotoView);
        
    @POST
    @Path("/consultaSantanderExpress")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value= {MediaType.APPLICATION_JSON})
    Respuesta<String> consultaSantanderExpress();

    /**
     * Retorna el Operador Ejecutivo 
     * obtenido desde la sesión del cliente.
     * 
     * @parama 
     * @return the respuesta
     */
    @POST
    @Path("/obtenerOperadorEjecutivoSelectOnline")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(value= {MediaType.APPLICATION_JSON})
    Respuesta<OperadorEjecutivoView> obtenerOperadorEjecutivoSelectOnline();

}
