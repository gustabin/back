/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidad;
import ar.com.santanderrio.obp.generated.webservices.alias.ConsultarDatosTitularidadResponse;
import ar.com.santanderrio.obp.generated.webservices.alias.CuentaDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.TerminalDTO;
import ar.com.santanderrio.obp.generated.webservices.alias.UsuarioDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.AltaDestinatarioCbuBO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.CasuisticaAltaDestinatarios;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dao.AgendaDestinatarioDAO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto.ConfiguracionAltaDestinatarioCBUDTO;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaInCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.web.util.ErrorAgendaDestinatariosEnum;
import ar.com.santanderrio.obp.servicios.alias.dao.AliasCbuDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.debinws.bo.impl.TipoDocumentoDebinWSEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class AltaDestinatarioCbuBOImpl.
 *
 * @author federico.n.flores
 */
@Component
public class AltaDestinatarioCbuBOImpl implements AltaDestinatarioCbuBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AltaDestinatarioCbuBOImpl.class);

	/** The agenda destinatario DAO. */
	@Autowired
	private AgendaDestinatarioDAO agendaDestinatarioDAO;

	/** The casuistica. */
	@Autowired
	private CasuisticaAltaDestinatarios casuistica;

	/** The respuestaFactory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The alias cbu DAO. */
	@Autowired
	private AliasCbuDAO aliasCbuDAO;

	/** sesion cliente. */
	@Autowired
	protected SesionCliente sesionCliente;

	/** The Constant CLASE_CUENTA_E. */
	private static final String CLASE_CUENTA_E = "E";

	/** The Constant CLASE_CUENTA_B. */
	private static final String CLASE_CUENTA_B = "B";

	/** The Constant CLASE_CUENTA_V. */
	private static final String CLASE_CUENTA_V = "V";

	/** The Constant CLASE_CUENTA_OTROS. */
	private static final String CLASE_CUENTA_OTROS = "OTROS";
	
	/** The Constant MONEDA_DTO_CODIGO_PESOS. */
	private static final String MONEDA_DTO_CODIGO_PESOS = "1";

	/** The Constant MONEDA_DTO_CODIGO_USD. */
	private static final String MONEDA_DTO_CODIGO_USD = "2";

	/** The Constant TIPO_CUENTA_DTO_CODIGO_CC. */
	private static final String TIPO_CUENTA_DTO_CODIGO_CC = "0";

	/** The Constant TIPO_CUENTA_DTO_CODIGO_CA. */
	private static final String TIPO_CUENTA_DTO_CODIGO_CA = "1";
	
	/** The Constant CERO. */
	private static final String CERO = "0";
	
	/** The Constant RESPUESTA_CONSULTA_TITULARIDAD_OK. */
	private static final int RESPUESTA_CONSULTA_TITULARIDAD_OK = 0;
	
	/** The trf cvu dolares habilitado. */
	@Value("${TRFCVU.DOLARES.HABILITADO}")
	private int cvuDolaresHabilitado;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.bo.
	 * AltaDestinatarioCbuBO#continuarAltaDestinatarioCBU(ar.com.santanderrio.
	 * obp.servicios.clientes.entities.Cliente, java.lang.String,
	 * java.lang.Boolean, java.lang.String)
	 */
	@Override
	public Respuesta<ConfiguracionAltaDestinatarioCBUDTO> continuarAltaDestinatarioCBU(Cliente cliente, String cbu,
			Boolean isPeso, String ip, String userAgent) {
		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> validacionesCBU = validarCBUVarios(cbu, cliente);
		if (validacionesCBU != null) {
			return validacionesCBU;
		}
		Cuenta cuenta = obtenerCuentaPorMoneda(cliente, isPeso);
		if (cuenta == null) {
			ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_CARGA_MANUAL;
			return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
		}
		String numeroTarjetaBanelco = obtenerNumeroTarjetaBanelco(cliente);
		ValidacionCuentaInCBUEntity inEntity = obtenerValidacionCuentaInCbuEntity(cuenta, cbu, ip, numeroTarjetaBanelco,
				isPeso);
		Respuesta<ValidacionCuentaInCBUEntity> validacion = casuistica.validarInEntity(inEntity);
		if (EstadoRespuesta.WARNING.equals(validacion.getEstadoRespuesta())) {
			return casuistica.crearRespuestaErrorServicio();
		}
		return ejecutarDAO(inEntity, cuenta, userAgent, isPeso);

	}

	/**
	 * Ejecutar logica CVU.
	 *
	 * @param isPeso
	 *            the is peso
	 * @param userAgent
	 *            the user agent
	 * @param cuenta
	 *            the cuenta
	 * @param cvuDestino
	 *            the cvu destino
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> ejecutarLogicaCVU(Boolean isPeso, String userAgent, Cuenta cuenta, String cvuDestino) {
		if (!isPeso && cvuDolaresHabilitado == 0) {
            LOGGER.error("AltaDestinatarioCbuBO Consultar Titularidad Exception DAO");
			ErrorAgendaDestinatariosEnum errorEnum = ErrorAgendaDestinatariosEnum.ERROR_CVU_DOLARES_NO_HABILITADO;
			return respuestaFactory.crearRespuestaError(errorEnum.getTag(), errorEnum.getTipoError(),
					errorEnum.getCodigoMensaje());
		} else {
	        LOGGER.debug("AltaDestinatarioCbuBO Consultar Titularidad Request de entrada");
	        ConsultarDatosTitularidad consultarDatosTitularidadIn = generarRequestWsConsultarTitularidad(userAgent, cuenta, cvuDestino);
	        try {
		        LOGGER.debug("AltaDestinatarioCbuBO Consultar Titularidad Llamando alias Dao");
				ConsultarDatosTitularidadResponse response =  aliasCbuDAO.consultarDatosTitularidad(consultarDatosTitularidadIn);
				return obtenerRespuesta(response, cvuDestino);
	        } catch (DAOException e) {
	            LOGGER.error("AltaDestinatarioCbuBO Consultar Titularidad Exception DAO");
				ErrorAgendaDestinatariosEnum errorEnum = ErrorAgendaDestinatariosEnum.ERROR_CARGA_MANUAL;
				return respuestaFactory.crearRespuestaError(errorEnum.getTag(), errorEnum.getTipoError(),
						errorEnum.getCodigoMensaje());
			}
		}
	}

	/**
	 * Obtener respuesta.
	 *
	 * @param response
	 *            the response
	 * @param cvuDestino
	 *            the cvu destino
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> obtenerRespuesta(ConsultarDatosTitularidadResponse response, String cvuDestino) {
		if (response.getCodigo() != null && Integer.parseInt(response.getCodigo()) != RESPUESTA_CONSULTA_TITULARIDAD_OK) {
			ErrorAgendaDestinatariosEnum errorEnum = ErrorAgendaDestinatariosEnum.ERROR_CARGA_MANUAL;
			return respuestaFactory.crearRespuestaError(errorEnum.getTag(), errorEnum.getTipoError(),
					errorEnum.getCodigoMensaje());
		}
		ValidacionCuentaOutCBUEntity validacionCuentaOutCBUEntity = new ValidacionCuentaOutCBUEntity();
		validacionCuentaOutCBUEntity.setCuit(response.getTitularidad().getCuits());
		validacionCuentaOutCBUEntity.setBandes("-");
		validacionCuentaOutCBUEntity.setTitular(response.getTitularidad().getNombreTitular());
		return respuestaFactory.crearRespuestaOk(ConfiguracionAltaDestinatarioCBUDTO.class,
                new ConfiguracionAltaDestinatarioCBUDTO(validacionCuentaOutCBUEntity, false, cvuDestino));
	}

	/**
	 * Generar request ws consultar titularidad.
	 *
	 * @param userAgent
	 *            the user agent
	 * @param cuenta
	 *            the cuenta
	 * @param cvuDestino
	 *            the cvu destino
	 * @return the consultar datos titularidad
	 */
	private ConsultarDatosTitularidad generarRequestWsConsultarTitularidad(String userAgent, Cuenta cuenta, String cvuDestino) {
        Cliente cliente = sesionCliente.getCliente();
        String tipoDoc = TipoDocumentoDebinWSEnum.getTipoDocumentoDebinWS(sesionCliente.getCliente().getTipoDocumento()).getCodigoNumero();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNroDocumento(cliente.getDni());
        usuarioDTO.setTipoDocumento(tipoDoc);
        TerminalDTO terminalDTO = new TerminalDTO(userAgent, sesionCliente.getIpCliente());
        terminalDTO.setCanal("E");
        CuentaDTO cuentaDTO = new CuentaDTO(obtenerCodigoTipoMonedaPorTipoDeCuenta(cuenta.getTipoCuentaEnum()),
                                    obtenerNroCuenta(cuenta), 
                                    cuenta.getCbu(), 
                                    obtenerCodigoTipoCuenta(cuenta.getTipoCuentaEnum()));
        return  new ConsultarDatosTitularidad(usuarioDTO, terminalDTO, cuentaDTO, cvuDestino);
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
            return TIPO_CUENTA_DTO_CODIGO_CC;
        }
        if (tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA)) {
            return TIPO_CUENTA_DTO_CODIGO_CA;
        }
        return StringUtils.EMPTY;
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
     * Obtener codigo tipo moneda en base a la cuenta.
     *
     * @param tipoCuenta
     *            the tipo cuenta
     * @return the string
     */
    private String obtenerCodigoTipoMonedaPorTipoDeCuenta(TipoCuenta tipoCuenta) {
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_PESOS) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_PESOS)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_PESOS)) {
            return MONEDA_DTO_CODIGO_PESOS;
        }
        if (tipoCuenta.equals(TipoCuenta.CUENTA_CORRIENTE_DOLARES) || tipoCuenta.equals(TipoCuenta.CAJA_AHORRO_DOLARES)
                || tipoCuenta.equals(TipoCuenta.CUENTA_UNICA_DOLARES)) {
            return MONEDA_DTO_CODIGO_USD;
        }
        return StringUtils.EMPTY;
    }
	
	/**
	 * Validar CBU varios.
	 *
	 * @param cbu
	 *            the cbu
	 * @param cliente
	 *            the cliente
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> validarCBUVarios(String cbu, Cliente cliente) {
		if (!ISBANStringUtils.validarCBU(cbu)) {
			return casuistica.crearErrorCbuInvalido();
		}
		if (ISBANStringUtils.validarCBURio(cbu) && !ISBANStringUtils.validarCBUCtaRecaudadora(cbu)) {
			if (esCbuPropio(cliente, cbu)) {
				return casuistica.crearErrorCuentaPropia();
			} else {
				return casuistica.crearDerivacionAltaRio(ISBANStringUtils.obtenerNumeroCuentaDesdeCBU(cbu));
			}
		}
		if (!poseeCuentaBanelco(cliente)) {
			return casuistica.crearErrorSinMedioDePago();
		}
		return null;
	}

	/**
	 * Ejecutar DAO.
	 *
	 * @param inEntity
	 *            the in entity
	 * @param cuenta
	 *            the cuenta
	 * @param userAgent
	 *            the user agent
	 * @param isPeso
	 *            the is peso
	 * @return the respuesta
	 */
	private Respuesta<ConfiguracionAltaDestinatarioCBUDTO> ejecutarDAO(ValidacionCuentaInCBUEntity inEntity, Cuenta cuenta, String userAgent, Boolean isPeso) {
		Respuesta<ConfiguracionAltaDestinatarioCBUDTO> resCasuistica = null;
		try {
			if (ISBANStringUtils.validarCVU(inEntity.getCbu())) {
				return ejecutarLogicaCVU(isPeso, userAgent, cuenta, inEntity.getCbu());
			}
			ValidacionCuentaOutCBUEntity outEntity = agendaDestinatarioDAO.validarCuentaTransferenciaCBU(inEntity);
			resCasuistica = casuistica.crearRespuestaConfiguracionCbu(outEntity);
		} catch (DAOException e) {
			LOGGER.error("Error servicio cbu", e);
			ErrorAgendaDestinatariosEnum error = ErrorAgendaDestinatariosEnum.ERROR_CARGA_MANUAL;
			return respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(), error.getCodigoMensaje());
		}
		return resCasuistica;
	}

	/**
	 * Es cbu propio.
	 *
	 * @param cliente
	 *            the cliente
	 * @param cbu
	 *            the cbu
	 * @return the boolean
	 */
	private Boolean esCbuPropio(Cliente cliente, String cbu) {
		List<TipoCuenta> filtroTipoCuenta = new ArrayList<TipoCuenta>(Arrays.asList(TipoCuenta.CAJA_AHORRO_DOLARES,
				TipoCuenta.CAJA_AHORRO_PESOS, TipoCuenta.CUENTA_CORRIENTE_DOLARES, TipoCuenta.CUENTA_CORRIENTE_PESOS,
				TipoCuenta.CUENTA_UNICA, TipoCuenta.CUENTA_UNICA_DOLARES, TipoCuenta.CUENTA_UNICA_PESOS));
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (filtroTipoCuenta.contains(cuenta.getTipoCuentaEnum()) && cbu.equals(cuenta.getCbu())) {
				return true;
			}
		}
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			if (filtroTipoCuenta.contains(cuenta.getTipoCuentaEnum()) && cbu.equals(cuenta.getCbu())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Posee cuenta banelco.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the boolean
	 */
	private Boolean poseeCuentaBanelco(Cliente cliente) {
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (cuenta.getTipoCuentaEnum().equals(TipoCuenta.BANELCO)) {
				return true;
			}
		}
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			if (cuenta.getTipoCuentaEnum().equals(TipoCuenta.BANELCO)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Crea el objeto a ser enviado al servicio CNSTITCBU.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @param cbu
	 *            the cbu
	 * @param ip
	 *            the ip
	 * @param numeroTarjetaBanelco
	 *            the numero tarjeta banelco
	 * @param isPeso
	 *            the is peso
	 * @return the validacion cuenta in CBU entity
	 */
	private ValidacionCuentaInCBUEntity obtenerValidacionCuentaInCbuEntity(Cuenta cuenta, String cbu, String ip,
			String numeroTarjetaBanelco, Boolean isPeso) {
		ValidacionCuentaInCBUEntity inEntity = new ValidacionCuentaInCBUEntity();

		String tipoCuentaUnica = StringUtils.leftPad(TipoCuenta.CUENTA_UNICA.getCodigo().toString(), 2, CERO);

		if (cuenta.getTipoCuenta().equals(tipoCuentaUnica)) {

			if (isPeso) {
				inEntity.setTipoCuentaCredito(cuenta.getTipoCuentaSinUnificar());
			} else {
				inEntity.setTipoCuentaCredito(cuenta.getTipoCuentaSinUnificarDls());
			}

		} else {
			inEntity.setTipoCuentaCredito(
					StringUtils.leftPad(cuenta.getTipoCuentaEnum().getCodigo().toString(), 2, CERO));
		}

		inEntity.setCliente(cuenta.getCliente());
		inEntity.setSucursalCuentaCredito(StringUtils.right(cuenta.getNroSucursal(), 3));
		inEntity.setNumeroCuentaCredito(StringUtils.right(cuenta.getNroCuentaProducto(), 7));
		inEntity.setCbu(cbu);
		inEntity.setNroTarjeta(StringUtils.rightPad(StringUtils.right(numeroTarjetaBanelco, 16), 18, " "));
		inEntity.setDireccionIp(ip);
		return inEntity;
	}

	/**
	 * Devuelve la primer cuenta que vale, en base a el booleano dado, caso
	 * contrario, devuelve null.
	 *
	 * @param cliente
	 *            the cliente
	 * @param isPeso
	 *            the is peso
	 * @return the cuenta
	 */
	@Override
	public Cuenta obtenerCuentaPorMoneda(Cliente cliente, Boolean isPeso) {
		if (isPeso) {
			return obtenerCuentaPesos(cliente);
		} else {
			return obtenerCuentaDolares(cliente);
		}
	}

	/**
	 * Devuelve la primer cuenta pesos que vale, caso contrario, devuelve null.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaPesos(Cliente cliente) {
		List<TipoCuenta> filtroTipoCuenta = new ArrayList<TipoCuenta>(
				Arrays.asList(TipoCuenta.CUENTA_CORRIENTE_PESOS, TipoCuenta.CAJA_AHORRO_PESOS, TipoCuenta.CUENTA_UNICA,
						TipoCuenta.CUENTA_UNICA_PESOS, TipoCuenta.CUENTA_UNICA_DOLARES));
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (filtroTipoCuenta.contains(cuenta.getTipoCuentaEnum())) {
				return cuenta;
			}
		}
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			if (filtroTipoCuenta.contains(cuenta.getTipoCuentaEnum())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Devuelve la primer cuenta dolares que vale, caso contrario, devuelve
	 * null.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaDolares(Cliente cliente) {
		List<TipoCuenta> filtroTipoCuenta = new ArrayList<TipoCuenta>(
				Arrays.asList(TipoCuenta.CUENTA_CORRIENTE_DOLARES, TipoCuenta.CAJA_AHORRO_DOLARES,
						TipoCuenta.CUENTA_UNICA, TipoCuenta.CUENTA_UNICA_DOLARES, TipoCuenta.CUENTA_UNICA_PESOS));
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (filtroTipoCuenta.contains(cuenta.getTipoCuentaEnum())) {
				return cuenta;
			}
		}
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			if (filtroTipoCuenta.contains(cuenta.getTipoCuentaEnum())) {
				return cuenta;
			}
		}
		return null;
	}

	/**
	 * Obtener numero tarjeta banelco.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the string
	 */
	private String obtenerNumeroTarjetaBanelco(Cliente cliente) {
		HashMap<String, Cuenta> cuentasBanelco = new HashMap<String, Cuenta>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (cuenta.getTipoCuentaEnum().equals(TipoCuenta.BANELCO)) {
				if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_E)) {
					return cuenta.getNroTarjetaCredito();
				}
				if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_B) && !cuentasBanelco.containsKey(CLASE_CUENTA_B)) {
					cuentasBanelco.put(CLASE_CUENTA_B, cuenta);
				} else if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_V)
						&& !cuentasBanelco.containsKey(CLASE_CUENTA_V)) {
					cuentasBanelco.put(CLASE_CUENTA_V, cuenta);
				} else if (!cuentasBanelco.containsKey(CLASE_CUENTA_OTROS)) {
					cuentasBanelco.put(CLASE_CUENTA_OTROS, cuenta);
				}
			}
		}
		for (Cuenta cuenta : cliente.getCuentasPrivadas()) {
			if (cuenta.getTipoCuentaEnum().equals(TipoCuenta.BANELCO)) {
				if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_E)) {
					return cuenta.getNroTarjetaCredito();
				}
				if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_B) && !cuentasBanelco.containsKey(CLASE_CUENTA_B)) {
					cuentasBanelco.put(CLASE_CUENTA_B, cuenta);
				} else if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_V)
						&& !cuentasBanelco.containsKey(CLASE_CUENTA_V)) {
					cuentasBanelco.put(CLASE_CUENTA_V, cuenta);
				} else if (!cuentasBanelco.containsKey(CLASE_CUENTA_OTROS)) {
					cuentasBanelco.put(CLASE_CUENTA_OTROS, cuenta);
				}
			}
		}
		return obtenerCuentaBanelcoPorPrioridad(cuentasBanelco).getNroTarjetaCredito();
	}

	/**
	 * Obtener cuenta banelco por prioridad.
	 *
	 * @param cuentasBanelco
	 *            the cuentas banelco
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaBanelcoPorPrioridad(HashMap<String, Cuenta> cuentasBanelco) {
		if (cuentasBanelco.containsKey(CLASE_CUENTA_B)) {
			return cuentasBanelco.get(CLASE_CUENTA_B);
		}
		if (cuentasBanelco.containsKey(CLASE_CUENTA_V)) {
			return cuentasBanelco.get(CLASE_CUENTA_V);
		} else {
			return cuentasBanelco.get(CLASE_CUENTA_OTROS);
		}
	}

}
