/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAgendaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.AgendaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ComprobanteAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuit.entities.Cuit;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CasuisticaAgendaDestinatariosImpl.
 */
@Component
public class CasuisticaAgendaDestinatariosImpl implements CasuisticaAgendaDestinatarios {

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The Constant PARAMETRO_NULO. */
	public static final String PARAMETRO_NULO = "{0}";
	
	/** The Constant ERROR_CUENTA_MIGRADA. */
	private static final int ERROR_CUENTA_MIGRADA = 10010094; // Cierre de sucursales
	
	/** The Constant ERROR_CUENTA_MIGRADA. */
	private static final String PATTERN_MSG_CTA_MIGRADA = "la cuenta.+([0-9]{3,4}-[0-9]{6}/[0-9]{1}) se encuentra migrada"
			+ ".+continuadora es.+([0-9]{3,4}-[0-9]{6}/[0-9]{1})\\."; // Cierre de sucursales

	/** The filtro transferencia. */
	private boolean filtroTransferencia;

	/**
	 * Checks if is filtro transferencia.
	 *
	 * @return true, if is filtro transferencia
	 */
	public boolean isFiltroTransferencia() {
		return filtroTransferencia;
	}

	/**
	 * Sets the filtro transferencia.
	 *
	 * @param filtroTransferencia
	 *            the new filtro transferencia
	 */
	public void setFiltroTransferencia(boolean filtroTransferencia) {
		this.filtroTransferencia = filtroTransferencia;
	}

	/**
	 * Crea la respuesta para agenda de destinatrio DTO.
	 * 
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AgendaDestinatarioDTO> crearRespuesta(AgendaDestinatarioDTO dto) {

		if (esRespuestaTipoOk(dto)) {
			return respuestaFactory.crearRespuestaOk(AgendaDestinatarioDTO.class, dto);
		}
		ErrorAgendaDestinatariosEnum error = obtenerEnumWarning(dto);
		if (error != null) {
			return crearRespuestaWarning(dto, error);
		}
		error = obtenerEnumError(dto);
		if (error != null) {
			return crearRespuestaError(error);
		}
		setFiltroTransferencia(false);
		return respuestaErrorServicios();
	}

	/**
	 * Crea la respuesta con error.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param errorEnum
	 *            the error enum
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> crearRespuestaError(ErrorAgendaDestinatariosEnum errorEnum) {
		if (errorEnum.equals(ErrorAgendaDestinatariosEnum.ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER)) {
			return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(
					"Por favor, verificá el número de cuenta ingresado.",
					TipoError.ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER.getDescripcion());
		}

		return respuestaFactory.crearRespuestaError(errorEnum.getTag(), errorEnum.getTipoError(),
				errorEnum.getCodigoMensaje());
	}

	/**
	 * Crea la respuesta warning para agenda de destinatario DTO.
	 * 
	 * @param dto
	 *            the dto
	 * @param errorEnum
	 *            the error enum
	 * @return the respuesta
	 */
	@Override
	public Respuesta<AgendaDestinatarioDTO> crearRespuestaWarning(AgendaDestinatarioDTO dto,
			ErrorAgendaDestinatariosEnum errorEnum) {
		return respuestaFactory.crearRespuestaWarning(dto, errorEnum.getTag(), errorEnum.getTipoError(),
				errorEnum.getCodigoMensaje());
	}

	/**
	 * Respuesta con error de servicios.
	 * 
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> respuestaErrorServicios() {
		return crearRespuestaError(ErrorAgendaDestinatariosEnum.UNACUENTAERRORAGENDAMIENTOS);
	}

	/**
	 * Respuesta con error de cuenta invalida en configuracion del alta de
	 * destinatario.
	 * 
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> respuestaErrorAltaConfiguracionCuentaInvalida() {
		return crearRespuestaError(ErrorAgendaDestinatariosEnum.CUENTAINEXISTENTE);
	}
	
	/**
	 * Respuesta con error de cuenta invalida en configuracion del alta de
	 * destinatario.
	 *
	 * @param <T>
	 *            the generic type
	 * @param ctaNrosMigrada
	 *            the cta nros migrada
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> respuestaErrorAltaConfiguracionCuentaMigrada(String[] ctaNrosMigrada) {
		return respuestaFactory.crearRespuestaError(ctaNrosMigrada[1], ErrorAgendaDestinatariosEnum.CUENTAMIGRADA.getTipoError(),
				ErrorAgendaDestinatariosEnum.CUENTAMIGRADA.getCodigoMensaje(), ctaNrosMigrada[0], ctaNrosMigrada[1]);
	}

	/**
	 * Respuesta con error de servicio en configuracion del alta de
	 * destinatario.
	 * 
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> respuestaErrorAltaConfiguracionErrorServicio() {
		return crearRespuestaError(ErrorAgendaDestinatariosEnum.ERRORSERVICIOINFORMACIONCUENTA);
	}

	/**
	 * Respuesta con error de cuenta propia en configuracion del alta de
	 * destinatario.
	 * 
	 * @param <T>
	 *            the generic type
	 * @return the respuesta
	 */
	@Override
	public <T> Respuesta<T> respuestaErrorAltaConfiguracionEsCuentaPropia() {
		return crearRespuestaError(ErrorAgendaDestinatariosEnum.CUENTAINGRESADAPROPIA);
	}

	/**
	 * Crea la respuesta de configuracion del alta de destinatario DTO.
	 * 
	 * @param datosCliente
	 *            the datos cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ConfiguracionAltaDestinatarioDTO> crearRespuestaConfiguracion(DatosCliente datosCliente) {
		if (datosCliente.getCodigoError() == 10000054) {
			return respuestaErrorAltaConfiguracionCuentaInvalida();
		} else if (datosCliente.getCodigoError() == ERROR_CUENTA_MIGRADA) {
			String[] nrosCuentaMigradas = obtenerNrosCuentaMigracion(datosCliente.getMensajeError());			
			return respuestaErrorAltaConfiguracionCuentaMigrada(nrosCuentaMigradas); // Cierre sucursales
		}
		return respuestaErrorAltaConfiguracionErrorServicio();

	}

	/**
	 * Crea la respuesta del comprobante del alta de destinatario DTO.
	 *
	 * @param dto
	 *            the dto
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the respuesta
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuesta(ComprobanteAltaDestinatarioDTO dto,
			String referenciaTitular) {
		if (esRespuestaOkComprobanteAltaDestinatario(dto)) {
			return respuestaFactory.crearRespuestaOk(ComprobanteAltaDestinatarioDTO.class, dto);
		}
		ErrorAgendaDestinatariosEnum error = obtenerEnumError(dto);
		Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = new Respuesta<ComprobanteAltaDestinatarioDTO>();
		if (error != null) {
			respuestaDTO = crearRespuestaError(error);
		}
		cambiarMensajeItem(respuestaDTO.getItemsMensajeRespuesta().get(0),
				obtenerMensajeItemFormateado(respuestaDTO, referenciaTitular));
		return respuestaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * CasuisticaAgendaDestinatarios#crearRespuestaOtrosBancos(ar.com.
	 * santanderrio.obp.servicios.agenda.destinatarios.dto.
	 * ComprobanteAltaDestinatarioDTO, java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioDTO> crearRespuestaOtrosBancos(ComprobanteAltaDestinatarioDTO dto,
			String referenciaTitular) {
		if (esRespuestaOkComprobanteAltaDestinatario(dto)) {
			return respuestaFactory.crearRespuestaOk(ComprobanteAltaDestinatarioDTO.class, dto);
		}
		ErrorAgendaDestinatariosEnum error = obtenerEnumErrorOtrosBancos(dto);
		Respuesta<ComprobanteAltaDestinatarioDTO> respuestaDTO = new Respuesta<ComprobanteAltaDestinatarioDTO>();
		if (error != null) {
			respuestaDTO = crearRespuestaError(error);
		}
		cambiarMensajeItem(respuestaDTO.getItemsMensajeRespuesta().get(0),
				obtenerMensajeItemFormateado(respuestaDTO, referenciaTitular));
		return respuestaDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * CasuisticaAgendaDestinatarios#respuestaErrorSinCuentasValidas()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Respuesta<AgendaDestinatarioDTO> respuestaErrorSinCuentasValidas() {
		return crearRespuestaError(ErrorAgendaDestinatariosEnum.SINCUENTAVALIDA);
	}

	/**
	 * Cambiar mensaje item.
	 *
	 * @param itemMensajeRespuesta
	 *            the item mensaje respuesta
	 * @param mensajeFormateado
	 *            the mensaje formateado
	 */
	private void cambiarMensajeItem(ItemMensajeRespuesta itemMensajeRespuesta, String mensajeFormateado) {
		itemMensajeRespuesta.setMensaje(mensajeFormateado);
	}

	/**
	 * Obtener mensaje item formateado.
	 *
	 * @param <T>
	 *            the generic type
	 * @param respuesta
	 *            the respuesta
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the string
	 */
	public static <T> String obtenerMensajeItemFormateado(Respuesta<T> respuesta, String referenciaTitular) {
		return agregarReferenciaTitularAMensajeError(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
				referenciaTitular);
	}

	/**
	 * Agrega la referencia o el titular al mensaje de error.
	 *
	 * @param mensaje
	 *            the mensaje
	 * @param referenciaTitular
	 *            the referencia titular
	 * @return the string
	 */
	public static String agregarReferenciaTitularAMensajeError(String mensaje, String referenciaTitular) {
		return StringUtils.replace(mensaje, PARAMETRO_NULO, referenciaTitular);
	}

	/**
	 * Obtiene el enum de error.
	 *
	 * @param dto
	 *            the dto
	 * @return the error agenda destinatarios enum
	 */
	private ErrorAgendaDestinatariosEnum obtenerEnumError(ComprobanteAltaDestinatarioDTO dto) {
		if (dto.getTieneErrorCuentaInvalida()) {
			return ErrorAgendaDestinatariosEnum.CUENTA_INVALIDA_ALTA_DESTINATARIO_FEEDBACK_RIO;
		} else if (dto.getTieneErrorDestinatarioAgendado()) {
			return ErrorAgendaDestinatariosEnum.DESTINATARIO_AGENDADO_ALTA_DESTINATARIO_FEEDBACK;
		} else if (dto.getTieneErrorCuentaInexistenteSantander()) {
			return ErrorAgendaDestinatariosEnum.ERROR_CUENTA_INGRESADA_INEXISTENTE_SANTANDER;
		} else {
			return ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_ALTA_DESTINATARIO_FEEDBACK_RIO;
		}
	}

	/**
	 * Obtener enum error otros bancos.
	 *
	 * @param dto
	 *            the dto
	 * @return the error agenda destinatarios enum
	 */
	private ErrorAgendaDestinatariosEnum obtenerEnumErrorOtrosBancos(ComprobanteAltaDestinatarioDTO dto) {
		if (dto.getTieneErrorDestinatarioAgendado()) {
			return ErrorAgendaDestinatariosEnum.DESTINATARIO_AGENDADO_ALTA_DESTINATARIO_FEEDBACK;
		} else {
			if (dto.getTieneErrorCuentaInvalida()) {
				return ErrorAgendaDestinatariosEnum.ALTA_CBU_INVALIDO;
			}
		}
		return ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_ALTA_DESTINATARIO_FEEDBACK_OTROS_BANCOS;
	}

	/**
	 * Es respuesta OK para el comprobante del alta de un destinatario.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	private Boolean esRespuestaOkComprobanteAltaDestinatario(ComprobanteAltaDestinatarioDTO dto) {
		return !dto.getTieneError() && !dto.getTieneErrorCuentaInvalida() && !dto.getTieneErrorDestinatarioAgendado()
				&& !dto.getTieneErrorCuentaInexistenteSantander();
	}

	/**
	 * Obtener enum error. Caso1 (1442): Time out. Caso 2 (1392) y Caso8 (1392)
	 * UNACUENTASINAGENDAMIENTOS. Caso 3 (1407) y 10 (1407)
	 * UNACUENTAERRORAGENDAMIENTOS. Caso 11 (1484) SINCUENTAVALIDA
	 *
	 * @param dto
	 *            the dto
	 * @return the error agenda destinatarios enum
	 */
	public ErrorAgendaDestinatariosEnum obtenerEnumError(AgendaDestinatarioDTO dto) {
		if (esCaso1(dto)) {
			if (filtroTransferencia) {
				return ErrorAgendaDestinatariosEnum.TIMEOUTDESTINATARIO;
			} else {
				return ErrorAgendaDestinatariosEnum.TIMEOUT;
			}
		}
		if (esCaso2(dto) || esCaso8(dto)) {
			return ErrorAgendaDestinatariosEnum.UNACUENTASINAGENDAMIENTOS;
		}
		if (esCaso3(dto) || esCaso10(dto)) {
            return ErrorAgendaDestinatariosEnum.UNACUENTAERRORAGENDAMIENTOS;
        }
        if (esCaso11(dto)) {
            if (filtroTransferencia) {
                return ErrorAgendaDestinatariosEnum.SINCUENTAVALIDATRANSFERENCIA;
            } else {
                return ErrorAgendaDestinatariosEnum.SINCUENTAVALIDA;
            }
        }
        return null;
	}

	/**
	 * Obtener enum warning. Caso4 o Caso12(1408)
	 * VARIASCUENTASERRORAGENDAMIENTOS. Caso5 (1442), Caso6 (1442), Caso7, Caso9
	 * (1442) WARNINGCARGAPARCIAL
	 * 
	 * @param dto
	 *            the dto
	 * @return the error agenda destinatarios enum
	 */
	public ErrorAgendaDestinatariosEnum obtenerEnumWarning(AgendaDestinatarioDTO dto) {

		ErrorAgendaDestinatariosEnum errorEnum = null;

		if (esCaso4(dto) || esCaso12(dto)) {
			if (filtroTransferencia) {
				errorEnum = ErrorAgendaDestinatariosEnum.WARNINGERRORCARGAPARCIAL;
			} else {
				errorEnum = ErrorAgendaDestinatariosEnum.VARIASCUENTASERRORAGENDAMIENTOS;
			}
		}
		if (esCaso5(dto) || esCaso6(dto) || esCaso7(dto) || esCaso9(dto)) {
			errorEnum = ErrorAgendaDestinatariosEnum.WARNINGCARGAPARCIAL;
		}
		return errorEnum;
	}

	/**
	 * Es respuesta tipo ok.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	@Override
	public Boolean esRespuestaTipoOk(AgendaDestinatarioDTO dto) {
		return esCaso1Ok(dto) || esCaso2Ok(dto) || esCaso3Ok(dto);
	}

	/**
	 * Tienen una cta propia y varios agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	private Boolean esCaso1Ok(AgendaDestinatarioDTO dto) {
		return tieneCeroCuentaPropia(dto) && sinErrorAgendados(dto) && tieneAgendados(dto);
	}

	/**
	 * Varias cuentas propias y sin agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	private Boolean esCaso2Ok(AgendaDestinatarioDTO dto) {
		return tieneVariasCuentasPropias(dto) && sinErrorAgendados(dto) && !tieneAgendados(dto);
	}

	/**
	 * Tiene Varias cuentas propias y tienen agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	private Boolean esCaso3Ok(AgendaDestinatarioDTO dto) {
		return tieneVariasCuentasPropias(dto) && sinErrorAgendados(dto) && tieneAgendados(dto);
	}

	/**
	 * Es caso 1 (1435): Se posee una sola cuenta propia y el servicio de
	 * agendamiento da error de timeout.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso1(AgendaDestinatarioDTO dto) {
		return tieneCeroCuentaPropia(dto) && dto.isTieneErrorTimeOut() && !dto.isTieneErrorCuentasNoPropias();
	}

	/**
	 * Es caso 2. Caso2 (1392): Se posee una sola cuenta propia ( no se
	 * visualiza ) y el servicio de agendamiento no trae agendamientos por no
	 * poseer. ( no por error ).
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso2(AgendaDestinatarioDTO dto) {
		return tieneCeroCuentaPropia(dto) && dto.isTieneCuentaPropias() && sinAgendados(dto) && sinErrorAgendados(dto);
	}

	/**
	 * Es caso 3. Caso3 (1407): Se posee solo una cuenta propia ( no se
	 * visualiza ), y el servicio de agendamiento devuelve error.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso3(AgendaDestinatarioDTO dto) {
		return tieneCeroCuentaPropia(dto) && dto.isTieneErrorCuentasNoPropias() && !dto.isTieneErrorRellamado();
	}

	/**
	 * Es caso 4 y 12. Caso 4 (1408): Se posee más de una cuenta propia ( se
	 * visualizan estas cuentas ) y el servicio de agendamiento devuelve
	 * error/Time Out.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso4(AgendaDestinatarioDTO dto) {
		return tieneVariasCuentasPropias(dto) && dto.isTieneErrorCuentasNoPropias() && !dto.isTieneErrorRellamado();
	}

	/**
	 * Es caso 5. Caso5 (1442): Se posee una cuenta propia ( no se visualiza ),
	 * y el servicio de agendamiento trae al menos 1 agendamiento ( no todos )
	 * por error.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso5(AgendaDestinatarioDTO dto) {
		return tieneCeroCuentaPropia(dto) && dto.isTieneErrorRellamado();
	}

	/**
	 * Es caso 6. Caso6 (1442): Se posee más de una cuenta propia ( se
	 * visualizan esas cuentas ) y el servicio de agendamiento trae al menos 1
	 * agendamiento ( no todos ) por error.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso6(AgendaDestinatarioDTO dto) {
		return tieneVariasCuentasPropias(dto) && dto.isTieneErrorRellamado();
	}

	/**
	 * Es caso 7. Caso7 (1442): Error ctas propias con destinatarios agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso7(AgendaDestinatarioDTO dto) {
		return dto.isTieneErrorCuentasPropias() && tieneAgendados(dto) && sinErrorAgendados(dto);
	}

	/**
	 * Es caso 8. Caso8 (1392): Error ctas propias sin destinatarios agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso8(AgendaDestinatarioDTO dto) {
		return dto.isTieneErrorCuentasPropias() && sinAgendados(dto) && sinErrorAgendados(dto);
	}

	/**
	 * Es caso 9. Caso9 (1442): Error ctas propias y carga parcial de servicios
	 * de agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso9(AgendaDestinatarioDTO dto) {
		return dto.isTieneErrorCuentasPropias() && tieneAgendados(dto) && dto.isTieneErrorRellamado();
	}

	/**
	 * Es caso 10. Caso10 (1407): Error de ctas propias y error total de
	 * servicio de agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso10(AgendaDestinatarioDTO dto) {
		return dto.isTieneErrorCuentasPropias() && dto.isTieneErrorCuentasNoPropias() && !dto.isTieneErrorRellamado();
	}

	/**
	 * Es caso 11. Caso11 : Sin Cuentas propias
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso11(AgendaDestinatarioDTO dto) {
		return dto.getCantCuentasPropias() == 0 && !dto.isTieneErrorCuentasPropias() && !dto.isTieneCuentaPropias();
	}

	/**
	 * Es caso 12 (1408): Se posee mas de una cuenta propia y el servicio de
	 * agendamiento da error de timeout.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private Boolean esCaso12(AgendaDestinatarioDTO dto) {
		return tieneVariasCuentasPropias(dto) && dto.isTieneErrorTimeOut() && !dto.isTieneErrorCuentasNoPropias();
	}

	/**
	 * Tiene cero cuenta propia.
	 *
	 * @param dto
	 *            the dto
	 * @return the boolean
	 */
	private Boolean tieneCeroCuentaPropia(AgendaDestinatarioDTO dto) {
		return dto.getCantCuentasPropias() == 0 && !dto.isTieneErrorCuentasPropias();
	}

	/**
	 * Tiene varias cuentas propias.
	 *
	 * @param dto
	 *            the dto
	 * @return the Boolean
	 */
	private Boolean tieneVariasCuentasPropias(AgendaDestinatarioDTO dto) {
		return dto.getCantCuentasPropias() > 1 && !dto.isTieneErrorCuentasPropias();
	}

	/**
	 * Sin agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return the Boolean
	 */
	private Boolean sinAgendados(AgendaDestinatarioDTO dto) {
		return dto.getCantidadCuentasNoPropias() == 0 && sinErrorAgendados(dto);
	}

	/**
	 * Tiene agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return the Boolean
	 */
	private Boolean tieneAgendados(AgendaDestinatarioDTO dto) {
		return dto.getCantidadCuentasNoPropias() > 0 && !dto.isTieneErrorCuentasNoPropias();
	}

	/**
	 * Sin error agendados.
	 *
	 * @param dto
	 *            the dto
	 * @return true, if successful
	 */
	private boolean sinErrorAgendados(AgendaDestinatarioDTO dto) {
		return !dto.isTieneErrorCuentasNoPropias() && !dto.isTieneErrorTimeOut();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * CasuisticaAgendaDestinatarios#validarCuit(java.lang.String)
	 */
	@Override
	public Respuesta<ComprobanteAltaDestinatarioDTO> validarCuit(String numeroDocumento) {
		Cuit cuit = new Cuit(numeroDocumento.replace("-", ""));
		if (!cuit.esCuitValido()) {
			return crearRespuestaError(
					ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_ALTA_DESTINATARIO_FEEDBACK_OTROS_BANCOS);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * CasuisticaAgendaDestinatarios#obtenerRespuestaGenericaEditarAltaRio()
	 */
	@Override
	public ItemMensajeRespuesta obtenerRespuestaGenericaEditarAltaRio() {
		return crearRespuestaError(ErrorAgendaDestinatariosEnum.ERROR_SERVICIO_ALTA_DESTINATARIO_FEEDBACK_RIO)
				.getItemsMensajeRespuesta().get(0);
	}
	
	
	/**
	 * Obtiene los numeros de una cuenta migrada a partir de mensaje de error.
	 *
	 * @param msgCtaMigrada
	 *            the error msg
	 * @return the string
	 */
	private String[] obtenerNrosCuentaMigracion(String msgCtaMigrada) {
		String msg = msgCtaMigrada.toLowerCase();
		String[] ret = new String[2];
		Matcher m = Pattern.compile(PATTERN_MSG_CTA_MIGRADA).matcher(msg.trim());
		if (m.find()) {
			ret[0] = m.group(1);
			ret[1] = m.group(2);
		}
		return ret;
	}		

}
