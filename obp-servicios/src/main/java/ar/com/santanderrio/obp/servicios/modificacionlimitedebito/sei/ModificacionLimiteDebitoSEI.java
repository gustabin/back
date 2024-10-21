/*
 * 
 */
package ar.com.santanderrio.obp.servicios.modificacionlimitedebito.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.MessageContext;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteDescargaCambioLimiteView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.ComprobanteModificacionLimiteDebitoView;
import ar.com.santanderrio.obp.servicios.modificacionlimitedebito.web.view.DatosModificacionLimiteDebitoView;

/**
 * The Interface ModificacionLimiteDebitoSEI.
 */
@Path("/modificacionLimiteDebito")
public interface ModificacionLimiteDebitoSEI {

	/**
	 * Obtener datos modificacion limite debito.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/obtenerDatosModificacionLimiteDebito")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<DatosModificacionLimiteDebitoView> obtenerDatosModificacionLimiteDebito();

	/**
	 * Modificar limites extraccion.
	 *
	 * @param comprobanteModificacionLimiteDebitoView
	 *            the comprobante modificacion limite debito view
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@POST
	@Path("/modificarLimitesExtraccion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ComprobanteModificacionLimiteDebitoView> modificarLimitesExtraccion(
			ComprobanteModificacionLimiteDebitoView comprobanteModificacionLimiteDebitoView, @Context MessageContext mc);

	/**
	 * Comprobante modif limites extraccion.
	 *
	 * @param comprobanteView
	 *            the comprobante view
	 * @return the respuesta
	 */
	@POST
	@Path("/comprobanteModifLimitesExtraccion")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ReporteView> comprobanteModifLimitesExtraccion(ComprobanteDescargaCambioLimiteView comprobanteView);

	/**
	 * Vaciar desafio.
	 *
	 * @return the respuesta
	 */
	@POST
	@Path("/vaciarDesafio")
	@Produces(value = { MediaType.APPLICATION_JSON })
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> vaciarDesafio();

}
