/*
 * 
 */
package ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.sei;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.entity.DatosEntradaAgendaDestinatario;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.DestinatarioView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.dto.TISFechasHabilitadasResponse;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AgendaDestinatarioLimiteTransferenciasView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoLimiteTransferenciaInOutView;
import ar.com.santanderrio.obp.servicios.aumentolimitetransferencia.web.view.AumentoTransferenciaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.transferencias.web.view.TransferenciaView;

/** The Interface AumentoLimiteTransferenciaSEI. */
@Path("/limiteTransferencia")
public interface AumentoLimiteTransferenciaSEI {

	/**
	 * Obtiene agenda de destinatarios desde AgendaDestinatariosManager,
	 * seteando un mensaje de ayuda adicional.
	 *
	 * @param dato
	 *            request
	 * @return lista de destinatarios cuentas propias y servicio de agenda, mas
	 *         el mensaje de ayuda
	 */
	@POST
	@Path("/obtenerAgendaDestinatarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AgendaDestinatarioLimiteTransferenciasView> obtenerAgendaDestinatarios(
			DatosEntradaAgendaDestinatario dato);

	/**
	 * Obtiene la informacion del destinatario desde TransferenciaManager.
	 *
	 * @param destinatarioView
	 *            request
	 * @return vista devuelta por el manager original de transferencias, mas los
	 *         parametros agregados de limites minimos en cada moneda y el
	 *         mensaje de ayuda
	 */
	@POST
	@Path("/obtenerInformacionDestinatario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AumentoTransferenciaView> obtenerInformacionDestinatario(DestinatarioView destinatarioView);

	/**
	 * Obtiene lista de los dias habiles para realizar la solicitud.
	 *
	 *            request
	 * @return Lista String con los dias habiles para realizar la solicitud
	 */
	@POST
	@Path("/obtenerFechasHabilitadas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AumentoLimiteTransferenciaInOutView> obtenerFechasHabilitadas(AumentoLimiteTransferenciaInOutView inView);

	/**
	 * Consulta titularidad desde TransferenciaManager.
	 *
	 * @param transferencia
	 *            request
	 * @param mc
     *            the mc
	 * @return vista devuelta por el manager original de transferencias
	 */
	@POST
	@Path("/consultarTitularidad")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<TransferenciaView> consultarTitularidad(TransferenciaView transferencia,
            @Context org.apache.cxf.jaxrs.ext.MessageContext mc);

	/**
	 * Da de alta la gestion de solicitud de aumento de limite de transferencia.
	 *
	 * @param inView
	 *            request
	 * @return vista con los datos del resultado del alta de gestion, mas el
	 *         desafio de autenticacion
	 */
	@POST
	@Path("/altaSolicitudAumentoLimite")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<AumentoLimiteTransferenciaInOutView> altaSolicitudAumentoLimiteTransferencia(
			AumentoLimiteTransferenciaInOutView inView);

	/**
	 * Genera el PDF del comprobante.
	 *
	 * @return PDF generado
	 */
	@POST
	@Path("/generarComprobanteAumentoLimiteTransferencia")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<ReporteView> generarComprobanteAumentoLimiteTransferencia();

	/**
	 * Vacia el desafio almacenado en sesion.
	 *
	 * @return Void
	 */
	@POST
	@Path("/vaciarDesafio")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	Respuesta<Void> vaciarDesafio();
}
