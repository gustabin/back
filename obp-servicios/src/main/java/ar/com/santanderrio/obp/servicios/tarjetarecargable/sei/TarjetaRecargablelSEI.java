/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.ComprobanteAltaSolicitudTarjetaRecargableView;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosSolicitanteTarjetaRecargableView;

/**
 * The Interface TarjetaRecargablelSEI.
 */
@Path("/solicitudTarjetaRecargable")
public interface TarjetaRecargablelSEI {

	/**
	 * Obtener datos iniciales.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosIniciales")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosSolicitanteTarjetaRecargableView> obtenerDatosIniciales();

	/**
	 * Alta solicitud tarjeta recargable.
	 *
	 * @param comprobanteAltaSolicitudTarjetaRecargableView
	 *            the comprobante alta solicitud tarjeta recargable view
	 * @return the respuesta
	 */
	@POST
	@Path("/altaSolicitudTarjetaRecargable")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ComprobanteAltaSolicitudTarjetaRecargableView> altaSolicitudTarjetaRecargable(
			ComprobanteAltaSolicitudTarjetaRecargableView comprobanteAltaSolicitudTarjetaRecargableView);

	/**
	 * Comprobante solicitud tarjeta recargable.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteSolicitudTarjetaRecargable")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> comprobanteSolicitudTarjetaRecargable();

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

}
