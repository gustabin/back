/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosAdicionalSolicitudView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosConfirmadosDelSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DatosSolicitanteView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.DomiciliosView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SolicitudTarjetaCreditoAdicionalView;

/**
 * The Interface TarjetaCreditoAdicionalSEI.
 */
@Path("/tarjetaCreditoAdicional")
public interface TarjetaCreditoAdicionalSEI {

	/**
	 * Gets the datos del solicitante.
	 *
	 * @return lista de tarjetas candidatas
	 */
	@POST
	@Path("/datosIniciales")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<SolicitudTarjetaCreditoAdicionalView> getDatosIniciales();

	/**
	 * Obtiene datos del solicitante accediendo respectivamente a los servicios
	 * CNSPERSFIS110 y CNSPADCUIT100.
	 *
	 * @param datosSolicitanteCriterioView
	 *            the datos solicitante criterio view
	 * @return los datos del solicitante y la lista de tarjetas a crear
	 *         adicional
	 */
	@POST
	@Path("/datosDelSolicitante")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosSolicitanteView> getDatosDelSolicitante(DatosSolicitanteCriterioView datosSolicitanteCriterioView);

	/**
	 * Normalizacion de domicilios.
	 *
	 * @param datosMerlinInEntity
	 *            the datos de domicilio
	 * @return the respuesta
	 */
	@POST
	@Path("/resultadoMerlin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DomiciliosView> getResultadoMerlin(DatosMerlinInEntity datosMerlinInEntity);

	/**
	 * Alta tarjeta credito adicional.
	 *
	 * @param datosSolicitanteConfirmadoViewResponse
	 *            the datos solicitante confirmado view response
	 * @return the respuesta
	 */
	@POST
	@Path("/altaTarjetaCreditoAdicional")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosConfirmadosDelSolicitanteView> altaTarjetaCreditoAdicional(
			DatosConfirmadosDelSolicitanteView datosSolicitanteConfirmadoViewResponse);

	/**
	 * Vaciar desafio.
	 *
	 * @return the object
	 */
	@POST
	@Path("/vaciarDesafio")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Object vaciarDesafio();

	/**
	 * Descarga comprobante alta tarjeta.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/descargaComprobanteAltaTarjeta")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> descargaComprobanteAltaTarjeta();
	
	/**
	 * Continuar solicitud.
	 *
	 * @param datosAdicionalSolicitudView
	 *            the datos adicional solicitud view
	 * @return the respuesta
	 */
	@POST
    @Path("/continuarSolicitud")
    @Produces(value = { MediaType.APPLICATION_JSON })
    @Consumes(value = { MediaType.APPLICATION_JSON })
    Respuesta<String> continuarSolicitud(DatosAdicionalSolicitudView datosAdicionalSolicitudView);

}
