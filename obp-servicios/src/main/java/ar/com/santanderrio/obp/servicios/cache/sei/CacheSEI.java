/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cache.sei;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cache.view.CacheView;

/**
 * SEI para limpiar-refrescar las cache utilizadas.
 * 
 * @author sergio.e.goldentair
 *
 */
@Path("/cache")
public interface CacheSEI {

    /**
     * Obtener las estadisticas de las cache utilizadas en la app para poder tunear
     * sus valores.
     *
     * @return the respuesta
     */
    @GET
    @Path("/obtenerEstadisticas")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<List<CacheView>> obtenerEstadisticas();

    /**
     * Limpiar el cache de legales.
     * 
     * @return ok o error
     */
    @GET
    @Path("/limpiarLegales")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarLegales();

    /**
     * Limpiar cache de mensajes.
     *
     * @return true o false
     */
    @GET
    @Path("/limpiarMensajes")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarMensajes();

    /**
     * Limpiar el cache de modulos permitidos.
     * 
     * @return true o false
     */
    @GET
    @Path("/limpiarModulosPermitidos")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarModulosPermitidos();

    /**
     * Limpiar cache DestinosBCRA.
     * 
     * @return true o false
     */
    @GET
    @Path("/limpiarMotivosPrestamo")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarMotivosPrestamo();

    /**
     * Limpiar cache fondos.
     * 
     * @return true o false
     */
    @GET
    @Path("/limpiarFondos")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarFondos();

    /**
     * Limpiar cache de plazos fijos.
     * 
     * @return true o false
     */
    @GET
    @Path("/limpiarPlazosFijos")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarPlazosFijos();

    /**
     * Limpia la cache de tasas de plazos fijos.
     *
     * @return the respuesta
     */
    @GET
    @Path("/limpiarTasasPlazosFijos")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarTasasPlazosFijos();

    /**
     * Limpia la cache de tasas de plazos fijos.
     *
     * @return the respuesta
     */
    @GET
    @Path("/limpiarAccionesPlazosFijos")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarAccionesPlazosFijos();

    /**
     * Limpia la cache de paises comex.
     *
     * @return the respuesta
     */
    @GET
    @Path("/limpiarPaisesComex")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarPaisesComex();

    /**
     * Limpia la cache de monedas comex.
     *
     * @return the respuesta
     */
    @GET
    @Path("/limpiarMonedasComex")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarMonedasComex();

    /**
     * Limpia mapa de medios de pago y reindexa lucene.
     *
     * @return the respuesta
     */
    @GET
    @Path("/limpiarMediosDePago")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarMediosDePago();
    
    /**
     * Limpia cache de conceptos liquidacion orden de pago.
     *
     * @return the respuesta
     */
    @GET
    @Path("/limpiarConceptos")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarConceptosLiquidacionOrdenPago();

    @GET
    @Path("/limpiarCampaniasPublicas")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarCampaniasPublicas();

	@GET
	@Path("/limpiarCacheOperaciones")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Boolean> limpiarCacheOperaciones();

	@GET
	@Path("/limpiarCacheApiAuth")
	@Produces(MediaType.APPLICATION_JSON)
	Respuesta<Boolean> limpiarCacheApiAuth();

    @GET
    @Path("/limpiarCacheCuitsAlycs")
    @Produces(MediaType.APPLICATION_JSON)
    Respuesta<Boolean> limpiarCacheCuitsAlycs();

}
