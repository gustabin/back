/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.AgendaDestinatarioOutEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;

/**
 * The Interface CasuisticaAltaDestinatarios.
 */
public interface CasuisticaAltaDestinatarios {

	/**
	 * Crear respuesta ok, u error dependiendo de la respuesta del servicio de
	 * configuracion alta por transferencia CBU.
	 *
	 * @param destinatariosAgendados
	 *            the destinatarios agendados
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearRespuestaConfiguracionCbu(
			ValidacionCuentaOutCBUEntity destinatariosAgendados);

	/**
	 * Crear error cbu invalido.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearErrorCbuInvalido();

	/**
	 * Crear error cuenta propia.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearErrorCuentaPropia();

	/**
	 * Crear error sin medio de pago.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearErrorSinMedioDePago();

	/**
	 * Crear derivacion alta rio.
	 *
	 * @param numeroCuenta
	 *            the numero cuenta
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearDerivacionAltaRio(String numeroCuenta);

	/**
	 * Crear respuesta error servicio.
	 *
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearRespuestaErrorServicio();

	/**
	 * Crear respuesta error servicio envio efectivo.
	 *
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaErrorServicioEnvioEfectivo();

	/**
	 * Crear respuesta envio efectivo.
	 *
	 * @param outEntity
	 *            the out entity
	 * @return the agenda destinatario out entity
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaEnvioEfectivo(AgendaDestinatarioOutEntity outEntity);

	/**
	 * retorna el resultado del respuestaFactory.validate
	 *
	 * @param inEntity
	 *            the in entity
	 * @return el resultado del bean validator
	 */
	Respuesta<ValidacionCuentaInCBUEntity> validarInEntity(ValidacionCuentaInCBUEntity inEntity);
}
