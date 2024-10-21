/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;

/**
 * The Interface AgendaDestinatariosRespuestaBO.
 */
public interface CasuisticaAgendaDestinatarios {

	/**
	 * Crear respuesta.
	 *
	 * @param destinatariosAgendados
	 *            the destinatarios agendados
	 * @return the respuesta
	 */
	Respuesta<AgendaDestinatarioDTO> crearRespuesta(AgendaDestinatarioDTO destinatariosAgendados);

	/**
	 * Crear respuesta error.
	 *
	 * @param <T>
	 *            the generic type
	 * @param errorEnum
	 *            the error enum
	 * @return the respuesta
	 */
	<T> Respuesta<T> crearRespuestaError(ErrorAgendaDestinatariosEnum errorEnum);

	/**
	 * Crear respuesta warning.
	 *
	 * @param destinatariosAgendados
	 *            the destinatarios agendados
	 * @param errorEnum
	 *            the error enum
	 * @return the respuesta
	 */
	Respuesta<AgendaDestinatarioDTO> crearRespuestaWarning(AgendaDestinatarioDTO destinatariosAgendados,
			ErrorAgendaDestinatariosEnum errorEnum);

	/**
	 * Es respuesta tipo ok.
	 *
	 * @param destinatariosAgendados
	 *            the destinatarios agendados
	 * @return true, if successful
	 */
	Boolean esRespuestaTipoOk(AgendaDestinatarioDTO destinatariosAgendados);

	/**
	 * Crear respuesta error por cuenta inexistente.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> respuestaErrorAltaConfiguracionCuentaInvalida();

	/**
	 * Crear respuesta error por error de servicio.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> respuestaErrorAltaConfiguracionErrorServicio();
	
	/**
	 * Crear respuesta error por error de cuenta migrada.
	 *
	 * @param <T>
	 *            the generic type
	 * @param ctaNrosMigrada
	 *            the cta nros migrada
	 * @return the respuesta
	 */
	<T> Respuesta<T> respuestaErrorAltaConfiguracionCuentaMigrada(String[] ctaNrosMigrada);

	/**
	 * Crear respuesta error al pasar una cuenta propia para dar de alta.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> respuestaErrorAltaConfiguracionEsCuentaPropia();

	/**
	 * Crear respuesta configuracion.
	 *
	 * @param datosCliente
	 *            the datos cliente
	 * @return the respuesta
	 */
	Respuesta<ConfiguracionAltaDestinatarioDTO> crearRespuestaConfiguracion(DatosCliente datosCliente);

	/**
	 * Crea la respuesta de ComprobanteAltaDestinatarioDTO.
	 *
	 * @param dto
	 *            the dto
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuesta(ComprobanteAltaDestinatarioDTO dto,
			String referenciaTitular);

	/**
	 * Respuesta error servicios.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> respuestaErrorServicios();

	/**
	 * Respuesta error sin cuentas validas.
	 *
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	<T> Respuesta<T> respuestaErrorSinCuentasValidas();

	/**
	 * Crear respuesta otros bancos.
	 *
	 * @param dto
	 *            the dto
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaOtrosBancos(ComprobanteAltaDestinatarioDTO dto,
			String referenciaTitular);

	/**
	 * Validar cuit.
	 *
	 * @param numeroDocumento
	 *            the numero documento
	 * @return the respuesta
	 */
	Respuesta<ComprobanteAltaDestinatarioDTO> validarCuit(String numeroDocumento);

	/**
	 * Obtener respuesta generica editar alta rio.
	 *
	 * @return the item mensaje respuesta
	 */
	ItemMensajeRespuesta obtenerRespuestaGenericaEditarAltaRio();

	/**
	 * Checks if is filtro transferencia.
	 *
	 * @return true, if is filtro transferencia
	 */
	boolean isFiltroTransferencia();

	/**
	 * Sets the filtro transferencia.
	 *
	 * @param filtroTransferencia
	 *            the new filtro transferencia
	 */
	void setFiltroTransferencia(boolean filtroTransferencia);

}