/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoAgendaEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoOperacionACTAGEDESTEnum;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;

/**
 * The Interface AgendaDestinatariosRCAManager.
 */
public interface AgendaDestinatariosRCAManager {

	/**
	 * Ejecutar validacion RSA. Este metodo se encarga de validar los datos
	 * provenientes de la pantalla de agendar transferencia y si la
	 * transferencia no es entre cuenta propias, se encarga de analizar RSA.
	 *
	 * @param confirmacionAltaDestinatario
	 *            the confirmacion alta destinatario
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @param tipoAgenda
	 *            the tipo agenda
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionAltaDestinatarioView> ejecutarValidacionRSA(
			ConfirmacionAltaDestinatarioView confirmacionAltaDestinatario, TipoOperacionACTAGEDESTEnum tipoOperacion,
			TipoAgendaEnum tipoAgenda);

	/**
	 * Valida si el cliente posee segundo factor de autenticacion.
	 *
	 * @return the Respuesta<Boolean>
	 */
	Respuesta<Boolean> validarSiPoseeSegundoFactorAutenticacion();

}