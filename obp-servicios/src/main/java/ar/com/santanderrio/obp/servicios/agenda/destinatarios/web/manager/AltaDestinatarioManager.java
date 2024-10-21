/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.manager;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.view.ConfirmacionAltaDestinatarioView;

/**
 * The Interface AltaDestinatarioManager.
 *
 * @author florencia.n.martinez
 */
public interface AltaDestinatarioManager {

	/**
	 * Obtener confirmacion alta destinatario rio.
	 *
	 * @param datosConfirmados
	 *            the datos confirmados
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioRio(
			ConfirmacionAltaDestinatarioView datosConfirmados);

	/**
	 * Obtener confirmacion alta destinatario otros bancos.
	 *
	 * @param datosConfirmados
	 *            the datos confirmados
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioOtrosBancos(
			ConfirmacionAltaDestinatarioView datosConfirmados);

	/**
	 * Obtener confirmacion alta destinatario envio efectivo.
	 *
	 * @param datosConfirmados
	 *            the datos confirmados
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioEnvioEfectivo(
			ConfirmacionAltaDestinatarioView datosConfirmados) throws BusinessException;

	/**
	 * Obtener confirmacion alta destinatario alias.
	 *
	 * @param datosConfirmados
	 *            the datos confirmados
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	Respuesta<ConfirmacionAltaDestinatarioView> obtenerConfirmacionAltaDestinatarioAlias(
			ConfirmacionAltaDestinatarioView datosConfirmados) throws BusinessException;

	/**
	 * Cancelar alta agenda destinatario.
	 */
	void cancelarAltaAgendaDestinatario();

	/**
	 * Graba la estadistica de la configuracion del alta destinatario por alias
	 * CBU.
	 */
	void grabarEstadisticaConfiguracionAltaDestinatarioAliasCBU();

	/**
	 * Ejecutar confirmacion alta destinatario alias.
	 *
	 * @param view
	 *            the view
	 * @return the respuesta
	 */
	Respuesta<ConfirmacionAltaDestinatarioView> ejecutarConfirmacionAltaDestinatarioAlias(
			ConfirmacionAltaDestinatarioView view);
}