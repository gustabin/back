/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import java.util.HashMap;
import java.util.Map;

import ar.com.santanderrio.obp.servicios.debinws.bo.impl.TipoDocumentoDebinWSEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendido;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadExtendidoResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.UsuarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioAliasBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.alias.exception.ValidacionAliasInexistenteEliminadoException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaDestinatarioAliasBOImpl.
 *
 * @author florencia.n.martinez
 */
@Component
public class AltaDestinatarioAliasBOImpl implements AltaDestinatarioAliasBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaDestinatarioAliasBOImpl.class);

	/** The Constant UNO_STRING. */
	private static final String UNO_STRING = "1";

	/** The Constant DOS_STRING. */
	private static final String DOS_STRING = "2";

	/** The Constant CERO_STRING. */
	private static final String CERO_STRING = "0";

	/** The Constant SIN_CODIGO_ERROR_CONTEMPLADO. */
	private static final String SIN_CODIGO_ERROR_CONTEMPLADO = "Error con cÃ³digo de error distinto a los contemplados.";

	/** The Constant ERROR_TIPO_CUENTA. */
	private static final String ERROR_TIPO_CUENTA = "El tipo de cuenta no se encuentra contemplado dentro la definiciÃ³n.";

	/** The alias cbu DAO. */
	@Autowired
	private AliasCbuDAO aliasCbuDAO;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * AltaDestinatarioAliasBO#continuarAltaDestinatarioAlias(ar.com.
	 * santanderrio.obp.servicios.clientes.entities.Cliente,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta,
	 * java.lang.String, java.lang.Boolean, java.lang.String)
	 */
	@Override
	public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> continuarAltaDestinatarioAlias(Cliente cliente, Cuenta cuenta,
			String alias, Boolean isPesos, String userAgent)
			throws BusinessException, ValidacionAliasInexistenteEliminadoException

	{
		try {
			ConsultarDatosTitularidadExtendidoResponse responseWS = realizarConsultaWS(cliente, userAgent, cuenta,
					alias);
			if (responseWS.getTitularidadExtendido() != null) {
				verificarCBURespuestaWSNoCorrespondaCuentaPropia(cliente, responseWS);
				Boolean esRio = verificarCuentaRioUOtrosBanco(responseWS);
				ConfiguracionAltaDestinatarioCBUDTO dto = crearDTO(responseWS, esRio);
				return crearRespuestaOK(dto);
			} else {
				return crearRespuestaErrorEsperada(responseWS);
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new BusinessException(e.getMessage());
		} catch (AliasCorrespondienteCuentaPropiaException acpe) {
			LOGGER.error(acpe.getMessage(), acpe);
			return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUDTO.class, "",
					TipoError.ERROR_ALIAS_USADO, CodigoMensajeConstantes.ERROR_ALIAS_CUENTA);
		}
	}

	/**
	 * Obtener tipo cuenta.
	 *
	 * @param responseWS
	 *            the response WS
	 * @return the tipo cuenta
	 * @throws BusinessException
	 *             the business exception
	 */
	public static TipoCuenta obtenerTipoCuenta(ConsultarDatosTitularidadExtendidoResponse responseWS)
			throws BusinessException {
		String digTipoCuenta = responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU().substring(8, 9);
		String digMoneda = responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU().substring(9, 10);

		final String tipoCuentaCorriente = "2";
		final String tipoCuentaCajaAhorro = "3";
		final String tipoCuentaFondoDesempleo = "4";
		final String tipoCuentaCuentaUnico = "8";
		final String pesos = "0";
		final String dolares = "1";

		Map<String, TipoCuenta> tipoCuenta = new HashMap<String, TipoCuenta>();
		tipoCuenta.put(tipoCuentaCorriente + pesos, TipoCuenta.CUENTA_CORRIENTE_PESOS);
		tipoCuenta.put(tipoCuentaCorriente + dolares, TipoCuenta.CUENTA_CORRIENTE_DOLARES);
		tipoCuenta.put(tipoCuentaCajaAhorro + pesos, TipoCuenta.CAJA_AHORRO_PESOS);
		tipoCuenta.put(tipoCuentaCajaAhorro + dolares, TipoCuenta.CAJA_AHORRO_DOLARES);
		tipoCuenta.put(tipoCuentaFondoDesempleo + pesos, TipoCuenta.CAJA_AHORRO_PESOS);
		tipoCuenta.put(tipoCuentaFondoDesempleo + dolares, TipoCuenta.CAJA_AHORRO_DOLARES);
		tipoCuenta.put(tipoCuentaCuentaUnico + tipoCuentaCuentaUnico, TipoCuenta.CUENTA_UNICA);

		if (tipoCuenta.containsKey(digTipoCuenta + digMoneda)) {
			return tipoCuenta.get(digTipoCuenta + digMoneda);
		}
		LOGGER.error(ERROR_TIPO_CUENTA);
		throw new BusinessException(ERROR_TIPO_CUENTA);
	}

	/**
	 * Crear respuesta OK.
	 *
	 * @param dto
	 *            the dto
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearRespuestaOK(ConfiguracionAltaDestinatarioCBUDTO dto) {
		return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUDTO.class, dto);
	}

	/**
	 * Verificar cuenta rio U otros banco.
	 *
	 * @param responseWS
	 *            the response WS
	 * @return the boolean
	 */
	private Boolean verificarCuentaRioUOtrosBanco(ConsultarDatosTitularidadExtendidoResponse responseWS) {
		return ISBANStringUtils.validarCBURio(responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU());
	}

	/**
	 * Crea el dto.
	 *
	 * @param responseWS
	 *            the response WS
	 * @param esRio
	 *            the es rio
	 * @return the configuracion alta destinatario CBUDTO
	 * @throws BusinessException
	 *             the business exception
	 */
	private ConfiguracionAltaDestinatarioCBUDTO crearDTO(ConsultarDatosTitularidadExtendidoResponse responseWS,
			Boolean esRio) throws BusinessException {
		return new ConfiguracionAltaDestinatarioCBUDTO(responseWS, esRio);
	}

	/**
	 * Crear respuesta error esperada.
	 *
	 * @param responseWS
	 *            the response WS
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 */
	private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> crearRespuestaErrorEsperada(
			ConsultarDatosTitularidadExtendidoResponse responseWS) throws BusinessException {
		if (StringUtils.equals(responseWS.getCodigo(), "0110")) {
			return respuestaFactory.crearRespuestaWarning(ConfiguracionAltaDestinatarioCBUDTO.class, null,
					TipoError.ALIAS_INEXISTENTE, null, "");
		}
		if (StringUtils.equals(responseWS.getCodigo(), "0160")) {
			return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUDTO.class, "",
					TipoError.ALIAS_CON_CTA_INACTIVA, CodigoMensajeConstantes.ALIAS_INEXISTENTE);
		}
		if (StringUtils.equals(responseWS.getCodigo(), "0190")) {
			return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUDTO.class, "",
					TipoError.ALIAS_ELIMINADO, CodigoMensajeConstantes.ALIAS_INEXISTENTE);
		}
		if (StringUtils.equals(responseWS.getCodigo(), "36")) {
			return respuestaFactory.crearRespuestaError(ConfiguracionAltaDestinatarioCBUDTO.class, "",
					TipoError.MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION,
					CodigoMensajeConstantes.MONEDA_CTA_NO_COINCIDE_MONEDA_SELECCION);
		}
		LOGGER.error(SIN_CODIGO_ERROR_CONTEMPLADO);
		throw new BusinessException(SIN_CODIGO_ERROR_CONTEMPLADO);
	}

	/**
	 * Verificar CBU respuesta WS no corresponda cuenta propia.
	 *
	 * @param cliente
	 *            the cliente
	 * @param responseWS
	 *            the response WS
	 * @throws AliasCorrespondienteCuentaPropiaException
	 *             the alias correspondiente cuenta propia exception
	 */
	private void verificarCBURespuestaWSNoCorrespondaCuentaPropia(Cliente cliente,
			ConsultarDatosTitularidadExtendidoResponse responseWS) throws AliasCorrespondienteCuentaPropiaException {
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (StringUtils.equals(responseWS.getTitularidadExtendido().getCtaDestino().getNumeroCBU(),
					cuenta.getCbu())) {
				throw new AliasCorrespondienteCuentaPropiaException();
			}
		}
	}

	/**
	 * Realizar consulta WS.
	 *
	 * @param cliente
	 *            the cliente
	 * @param userAgent
	 *            the user agent
	 * @param cuenta
	 *            the cuenta
	 * @param alias
	 *            the alias
	 * @return the consultar datos titularidad extendido response
	 * @throws DAOException
	 *             the DAO exception
	 * @throws ValidacionAliasInexistenteEliminadoException
	 *             the validacion alias inexistente eliminado exception
	 */
	public ConsultarDatosTitularidadExtendidoResponse realizarConsultaWS(Cliente cliente, String userAgent,
			Cuenta cuenta, String alias) throws DAOException, ValidacionAliasInexistenteEliminadoException {
		UsuarioDTO usuarioDTO = new UsuarioDTO(cliente.getDni());
		usuarioDTO.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(cliente.getTipoDocumento()).getCodigoNumero());
		TerminalDTO terminalDTO = new TerminalDTO(userAgent, sesionParametros.getRegistroSession().getIp());
		CuentaDTO cuentaDTO = new CuentaDTO(obtenerCodigoTipoMonedaPorTipoDeCuenta(cuenta.getTipoCuentaEnum()),
				obtenerNroCuenta(cuenta), cuenta.getCbu(), obtenerCodigoTipoCuenta(cuenta.getTipoCuentaEnum()));
		return realizarConsultaWS(usuarioDTO, terminalDTO, cuentaDTO, alias);
	}

	/**
	 * Realizar consulta WS.
	 *
	 * @param cliente
	 *            the cliente
	 * @param userAgent
	 *            the user agent
	 * @param cuentasAdhesionDebitoView
	 *            the cuentas adhesion debito view
	 * @param alias
	 *            the alias
	 * @param divisa
	 *            the divisa
	 * @return the consultar datos titularidad extendido response
	 * @throws DAOException
	 *             the DAO exception
	 */
	public ConsultarDatosTitularidadExtendidoResponse realizarConsultaWSconCuentaSaldo(Cliente cliente,
			String userAgent, CuentasAdhesionDebitoView cuentasAdhesionDebitoView, String alias, DivisaEnum divisa)
			throws DAOException {
		UsuarioDTO usuarioDTO = new UsuarioDTO(cliente.getDni());
		usuarioDTO.setTipoDocumento(TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(cliente.getTipoDocumento()).getCodigoNumero());
		TerminalDTO terminalDTO = new TerminalDTO(userAgent, sesionParametros.getRegistroSession().getIp());
		TipoCuenta tipoCuenta = TipoCuenta.fromAbreviatura(cuentasAdhesionDebitoView.getAbreviaturaTipoCuenta().trim());
		CuentaDTO cuentaDTO = new CuentaDTO(obtenerCodigoTipoMonedaPorDivisa(divisa),
				obtenerNroCuenta(cuentasAdhesionDebitoView), cuentasAdhesionDebitoView.getCbu(),
				obtenerCodigoTipoCuenta(tipoCuenta));
		return realizarConsultaWS(usuarioDTO, terminalDTO, cuentaDTO, alias.trim());
	}

	/**
	 * Realizar consulta WS.
	 *
	 * @param usuarioDTO
	 *            the usuario DTO
	 * @param terminalDTO
	 *            the terminal DTO
	 * @param cuentaDTO
	 *            the cuenta DTO
	 * @param alias
	 *            the alias
	 * @return the consultar datos titularidad extendido response
	 * @throws DAOException
	 *             the DAO exception
	 */
	private ConsultarDatosTitularidadExtendidoResponse realizarConsultaWS(UsuarioDTO usuarioDTO,
			TerminalDTO terminalDTO, CuentaDTO cuentaDTO, String alias) throws DAOException {
		ConsultarDatosTitularidadExtendido consultarDatosTitularidadExtendido = new ConsultarDatosTitularidadExtendido(
				usuarioDTO, terminalDTO, cuentaDTO, alias);
		return aliasCbuDAO.consultarDatosTitularidadExtendido(consultarDatosTitularidadExtendido);
	}

	/**
	 * Obtener nro cuenta.
	 *
	 * @param cuentasAdhesionDebitoView
	 *            the cuentas adhesion debito view
	 * @return the string
	 */
	private String obtenerNroCuenta(CuentasAdhesionDebitoView cuentasAdhesionDebitoView) {
		String nroCuenta = ISBANStringUtils.extraerCuenta(cuentasAdhesionDebitoView.getNumero());
		String sucursal = ISBANStringUtils.extraerSucursal(cuentasAdhesionDebitoView.getNumero());
		return "0".concat(sucursal).concat("00000000000").concat(nroCuenta);
	}

	/**
	 * Obtener nro cuenta.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	private String obtenerNroCuenta(Cuenta cuenta) {
		return cuenta.getNroSucursal().concat("00").concat(cuenta.getNroCuentaProducto());
	}

	/**
	 * Obtener codigo en base al tipo de cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the string
	 */
	private String obtenerCodigoTipoCuenta(TipoCuenta tipoCuenta) {
		if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS)
				|| tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES)
				|| tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS)
				|| tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
			return CERO_STRING;
		}
		if (tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
				|| tipoCuenta.equals(TipoCuenta.CUENTA_UNICA)) {
			return UNO_STRING;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Obtener codigo tipo moneda en base a la cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipo cuenta
	 * @return the string
	 */
	private String obtenerCodigoTipoMonedaPorTipoDeCuenta(TipoCuenta tipoCuenta) {
		if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS)
				|| tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
			return UNO_STRING;
		}
		if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
				|| tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
			return DOS_STRING;
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Obtener codigo tipo moneda en base a la divisa.
	 *
	 * @param divisa
	 *            the divisa
	 * @return the string
	 */
	private String obtenerCodigoTipoMonedaPorDivisa(DivisaEnum divisa) {
		if (DivisaEnum.PESO.equals(divisa)) {
			return UNO_STRING;
		} else if (DivisaEnum.DOLAR.equals(divisa)) {
			return DOS_STRING;
		}
		return StringUtils.EMPTY;
	}

}
