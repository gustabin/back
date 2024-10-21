/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.AltaDatosReimpresionTarjetasView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.DomiciliosDisponiblesView;
import ar.com.santanderrio.obp.servicios.tarjetas.reimpresion.web.view.SolicitudReimpresionTarjetasView;

/**
 * The Interface ReimpresionTarjetasSEI.
 */
@Path("/reimpresionTarjetas")
public interface ReimpresionTarjetasSEI {

	/**
	 * Gets the datos del solicitante.
	 *
	 * @return lista de tarjetas candidatas
	 */
	@POST
	@Path("/datosIniciales")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<SolicitudReimpresionTarjetasView> getDatosIniciales();

	/**
	 * Gets the datos del solicitante.
	 *
	 * @return lista de tarjetas candidatas
	 */
	@POST
	@Path("/domiciliosDisponibles")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DomiciliosDisponiblesView> getDomiciliosDisponibles();

	/**
	 * Gets the datos del solicitante.
	 *
	 * @param altaDatosReimpresionTarjetas
	 *            the alta datos reimpresion tarjetas
	 * @return lista de tarjetas candidatas
	 */
	@POST
	@Path("/altaReimpresionTarjetas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AltaDatosReimpresionTarjetasView> altaReimpresionTarjetas(
			AltaDatosReimpresionTarjetasView altaDatosReimpresionTarjetas);

	/**
	 * Gets the comprobante.
	 *
	 * @return the comprobante
	 */
	@POST
	@Path("/comprobante")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> getComprobante();

}
