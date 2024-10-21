/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.AgendaDestinatarioView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioCBUOutView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfiguracionAltaDestinatarioInView;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;

/**
 * The interface agenda destinatarios.
 *
 * @author dante.omar.olmedo
 */
@Path("/transferencias")
public interface AgendaDestinatariosSEI {

	/**
	 * Obtener agenda destinatarios.
	 *
	 * @param dato
	 *            the dato
	 * @return lista de destinatarios cuentas propias y servicio de agenda
	 */
	@POST
	@Path("/obtenerListaDestinatario")
	@Produces(value = { MediaType.APPLICATION_JSON })
	Respuesta<AgendaDestinatarioView> obtenerAgendaDestinatarios(DatosEntradaAgendaDestinatario dato);

	/**
	 * Recibe el tipo de destinatario sobre el que se quiere ver el detalle y
	 * graba la estadistica correspondiente.
	 *
	 * @param datos
	 *            the datos
	 */
	@POST
	@Path("/verDetalleDestinatario")
	void grabarEstadisticaVerDetalle(DatosEntradaAgendaDestinatario datos);

	/**
	 * Graba estadistica al ingresar al alta de destinatario cliente rio.
	 */
	@POST
	@Path("/configuracionAltaDestinatarioRio")
	void grabarEstadisticaIngresoAltaDestinatarioRio();

	/**
	 * Graba estadistica al ingresar al ver el comprobante cliente rio.
	 */
	@POST
	@Path("/configuracionVerComprobanteRio")
	void grabarEstadisticaVerComprobanteRio();

	/**
	 * Confirma el alta del destinatario Rio-Rio.
	 *
	 * @param datosConfirmados
	 *            the datos confirmados
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	@POST
	@Path("/confirmarAltaDestinatario")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmacionAltaDestinatarioView> confirmarAltaDestinatario(
			ConfirmacionAltaDestinatarioView datosConfirmados) throws BusinessException;

	/**
	 * Confirma el alta del destinatario.
	 *
	 * @param configuracionAltaDestinatarioViewTransferenciaCBU
	 *            the configuracion alta destinatario view transferencia CBU
	 * @param mc
	 *            the mc
	 * @return the respuesta
	 */
	@POST
	@Path("/continuarAltaDestinatario")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfiguracionAltaDestinatarioCBUOutView> configuracionAltaDestinatarioTransferenciaCBU(
			ConfiguracionAltaDestinatarioInView configuracionAltaDestinatarioViewTransferenciaCBU,
			@Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Grabar estadistica ingreso alta destinatario CBU.
	 */
	@POST
	@Path("/configuracionAltaDestinatarioCBU")
	void grabarEstadisticaIngresoAltaDestinatarioCBU();

	/**
	 * Grabar estadistica comprobante alta destinatario CBU.
	 */
	@POST
	@Path("/configuracionVerComprobanteCBU")
	void grabarEstadisticaComprobanteAltaDestinatarioCBU();

	/**
	 * Grabar estadistica ingreso alta destinatario CBU.
	 */
	@POST
	@Path("/configuracionAltaDestinatarioEnvioEfectivo")
	void grabarEstadisticaIngresoEnvioEfectivo();

	/**
	 * Grabar estadistica ingreso modificacion destinatario rio.
	 *
	 * @param datos
	 *            the datos
	 */
	@POST
	@Path("/configuracionEditarDestinatario")
	void grabarEstadisticaConfiguracionModificacionDestinatario(DatosEntradaAgendaDestinatario datos);

	/**
	 * Grabar estadistica ingreso modificacion destinatario rio.
	 *
	 * @param datos
	 *            the datos
	 */
	@POST
	@Path("/feedbackEditarDestinatario")
	void grabarEstadisticaComprobanteModificacionDestinatario(DatosEntradaAgendaDestinatario datos);

	/**
	 * Grabar estadistica ingreso alta destinatario CBU.
	 */
	@POST
	@Path("/configuracionVerComprobanteEnvioEfectivo")
	void grabarEstadisticaComprobanteAltaEnvioEfectivo();

	/**
	 * Confirma el alta del destinatario.
	 *
	 * @param datos
	 *            the datos
	 * @return the respuesta
	 */
	@POST
	@Path("/eliminarDestinatario")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<Void> eliminacionDestinatario(DatosEntradaAgendaDestinatario datos);

	/**
	 * Confirma el alta del destinatario Rio-Rio.
	 *
	 * @param datosEntrada
	 *            the datos entrada
	 * @return the respuesta
	 */
	@POST
	@Path("/confirmarEdicionDestinatario")
	@Consumes(value = { MediaType.APPLICATION_JSON })
	Respuesta<ConfirmacionAltaDestinatarioView> confirmarEdicionDestinatario(
			ConfirmacionAltaDestinatarioView datosEntrada);

	/**
	 * Cancelar alta agenda destinatario.
	 */
	@POST
	@Path("/cancelarAltaAgendaDestinatario")
	void cancelarAltaAgendaDestinatario();

	/**
	 * Graba la estadistica de la configuracion del alta destinatario por alias
	 * CBU.
	 */
	@POST
	@Path("/iniciarAltaDestinatarioAliasCBU")
	void grabarEstadisticaConfiguracionAltaDestinatarioAliasCBU();

	/**
	 * Grabar estadistica ver comprobante alta destinatario alias.
	 *
	 * @return the boolean
	 */
	@POST
	@Path("/verComprobanteAltaDestinatarioAliasCBU")
	Boolean grabarEstadisticaVerComprobanteAltaDestinatarioAlias();

	/**
	 * Valida si el cliente posee segundo factor de autenticacion.
	 *
	 * @return the Respuesta<Boolean>
	 */
	@POST
	@Path("/validarSegundoFactorAutenticacion")
	Respuesta<Boolean> validarSiPoseeSegundoFactorAutenticacion();

}
