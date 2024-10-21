/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comex.transfext.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;

/**
 * The Interface ComexCanalesSEI.
 *
 * @author IT Resources
 */
@Path("/comexCanales")
public interface ComexCanalesSEI {


    /**
	 * Consulta detalle trf.
	 *
	 * @param consultaDetalleTrfOBPInView
	 *            the consulta detalle trf OBP in view
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaDetalleTrf")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConsultaDetalleTrfOBPOutView> consultaDetalleTrf(ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView);
    
    /**
	 * Consulta operaciones.
	 *
	 * @param consultaOperacionesInView
	 *            the consulta operaciones in view
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaOperaciones")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConsultaOperacionesOutView> consultaOperaciones(ConsultaOperacionesInView consultaOperacionesInView);
    
    /**
	 * Consulta operaciones mostrar mas.
	 *
	 * @param consultaOperacionesInView
	 *            the consulta operaciones in view
	 * @return the respuesta
	 */
    @POST
    @Path("/consultaOperacionesMostrarMas")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ConsultaOperacionesOutView> consultaOperacionesMostrarMas(ConsultaOperacionesInView consultaOperacionesInView);

    /**
	 * Procesar transferencia guardar.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
    @POST
    @Path("/procesarTransferenciaGuardar")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaGuardar(ProcesarTransferenciaComexView procesarTransferenciaComexView);
    
    /**
	 * Procesar transferencia baja.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
    @POST
    @Path("/procesarTransferenciaBaja")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaBaja(ProcesarTransferenciaComexView procesarTransferenciaComexView);
    
    /**
	 * Procesar transferencia alta.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
    @POST
    @Path("/procesarTransferenciaAlta")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaAlta(ProcesarTransferenciaComexView procesarTransferenciaComexView);
        
    /**
	 * Adjuntar archivo.
	 *
	 * @param procesarTransferenciaComexView
	 *            the procesar transferencia comex view
	 * @return the respuesta
	 */
    @POST
    @Path("/adjuntarArchivo")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<ArchivoTransferenciaView> adjuntarArchivo(ProcesarTransferenciaComexView procesarTransferenciaComexView);
    
    /**
	 * Borrar archivo.
	 *
	 * @param archivoTransferenciaView
	 *            the archivo transferencia view
	 * @return the respuesta
	 */
    @POST
    @Path("/borrarArchivo")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<Void> borrarArchivo(ArchivoTransferenciaView archivoTransferenciaView);
    
    
    /**
	 * Obtener archivo ws comex.
	 *
	 * @param archivoTransferenciaView
	 *            the archivo transferencia view
	 * @return the respuesta
	 */
    @POST
    @Path("/obtenerArchivoComex")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<AdjuntarArchivosDTO> obtenerArchivoWsComex(ArchivoTransferenciaView archivoTransferenciaView);

}
