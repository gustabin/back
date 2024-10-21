/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.web.manager.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.entities.Opcion;
import ar.com.santanderrio.obp.servicios.comun.combos.service.DatosSelectoresService;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.DatosSolicitanteDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsDTO;
import ar.com.santanderrio.obp.servicios.monedero.dto.TagsTransacDTO;
import ar.com.santanderrio.obp.servicios.monedero.entities.NombreTransaccionEnum;
import ar.com.santanderrio.obp.servicios.monedero.entities.TagEntity;
import ar.com.santanderrio.obp.servicios.monedero.entities.TipoDocMonederoEnum;
import ar.com.santanderrio.obp.servicios.monedero.entities.TransaccionEntity;
import ar.com.santanderrio.obp.servicios.monedero.exception.SinAccesoALaInformacionException;
import ar.com.santanderrio.obp.servicios.monedero.service.DatosSolicitanteService;
import ar.com.santanderrio.obp.servicios.monedero.utils.MonederoUtils;
import ar.com.santanderrio.obp.servicios.monedero.web.manager.DatosSolicitanteManager;
import ar.com.santanderrio.obp.servicios.monedero.web.view.CuentaMonederoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosAltaCanalesAutomaticosView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosMonederoConMovimientosYSaldoView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteCriterioView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteResponseView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitanteView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.DatosSolicitudTagAdicionalView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.ImporteARecargarView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.LimiteDeRecargaMensualView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.LineaView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.MonederoTagView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.NacionalidadView;
import ar.com.santanderrio.obp.servicios.monedero.web.view.SaldosYMovimientosMonederoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.manager.TarjetaCreditoAdicionalManager;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.EstadoCivilView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.PaisDeNacimientoView;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.web.view.SexoView;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.MonederoDTO;

/**
 * The Class DatosSolicitanteManagerImpl.
 */
@Component
public class DatosSolicitanteManagerImpl implements DatosSolicitanteManager {

	/** The datos solicitante service. */
	@Autowired
	private DatosSolicitanteService datosSolicitanteService;

	/** The datos padron BO. */
	@Autowired
	private DatosSolicitanteBO datosPadronBO;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The Tarjeta Credito Adicional Manager. */
	@Autowired
	private TarjetaCreditoAdicionalManager tarjetaCreditoAdicionalManager;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The datos selectores service. */
	@Autowired
	private DatosSelectoresService datosSelectoresService;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The hora desde ALTA. */
	@Value("${MONEDERO.ALTA.HORA.DESDE}")
	private String horaDesdeALTA;

	/** The hora hasta ALTA. */
	@Value("${MONEDERO.ALTA.HORA.HASTA}")
	private String horaHastaALTA;

	/** The id banco. */
	@Value("${MONEDERO.IDBANCO}")
	private String idBanco;
	
	/** The url token. */
	@Value("${TRJCOORD.AYUDA.URL}")
	private String urlToken;


	/** The Constant MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO. */
	public static final String MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO = "6000";

	/** The Constant MENSAJE_SOLICITUD_TAG_ADICIONAL. */
	public static final String MENSAJE_SOLICITUD_TAG_ADICIONAL = "5031";

	/** The Constant TIME_FORMAT_PATTERN. */
	public static final String TIME_FORMAT_PATTERN = "HH:mm";

	/** The Constant BLANK. */
	private static final String BLANK = " ";

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(DatosSolicitanteManagerImpl.class);

	/** The Constant SIN_INFORMACION. */
	private static final String SIN_INFORMACION = "sinInformacion";

	/** The Constant PREFIJO_X. */
	private static final String PREFIJO_X = "XXXX";

	/** The Constant GUION_STRING. */
	private static final String GUION_STRING = "-";

	/** The Constant CANTIDAD_CARACTERES. */
	private static final int CANTIDAD_CARACTERES = 4;

	/** private static final String TIPOCTA_VISA = "07";. */

	/** The Constant TIPOCTA_AMEX. */
	/** private static final String TIPOCTA_AMEX = "42"; */

	public static final String CODIGO_TITULARIDAD_TI = "TI";

	/** The Constant CALIDAD_DE_PARTICIPACION_TI. */
	public static final String CALIDAD_DE_PARTICIPACION_TI = "TI";

	/** The Constant CALIDAD_DE_PARTICIPACION_AD. */
	public static final String CALIDAD_DE_PARTICIPACION_AD = "AD";

	/** The Constant PRODUCTO_ALTAIR. */
	public static final String PRODUCTO_ALTAIR = "43";

	/** The Constant SUB_PRODUCTO_ALTAIR. */
	public static final String SUB_PRODUCTO_ALTAIR = "MONE";
	/** The Constant CODIGO_TITULARIDAD_CT. */
	public static final String CODIGO_TITULARIDAD_CT = "CT";

	/** The Constant TIPO_CUENTA_VISA. */
	public static final String TIPO_CUENTA_VISA = "07";

	/** The Constant TIPO_CUENTA_AMEX. */
	public static final String TIPO_CUENTA_AMEX = "42";

	/** The Constant TIPO_CUENTA_DEBITO. */
	public static final String TIPO_CUENTA_DEBITO = "05";

	/** The Constant ESTADO_TARJETA_CREDITO_VALIDO. */
	public static final String ESTADO_TARJETA_CREDITO_VALIDO = "20";

	/** The Constant ESTADO_TARJETA_CREDITO_INVALIDO. */
	public static final String ESTADO_TARJETA_CREDITO_INVALIDO = "09";

	/** The Constant MIN_FECHA. */
	public static final String MIN_FECHA = "01/01/0001";

	/** The Constant PAG_CANT_REG. */
	public static final String PAG_CANT_REG = "0";

	/** The Constant PAG_NUM. */
	public static final String PAG_NUM = "0";

	/** The Constant TARJETA_ACTIVADA. */
	public static final String TARJETA_ACTIVADA = "30";

	/** The Constant TARJETA_NO_ACTIVADA. */
	public static final String ESTADO_ENTREGADO = "31";

	/** The Constant TARJETA_TIPO. */
	public static final String TARJETA_TIPO = "1";

	/** The Constant ESTADO_ACTIVO. */
	public static final String TAG_ACTIVO = "20";

	/** The Constant TAG_NO_ACTIVO. */
	private static final String TAG_NO_ACTIVO = "19";
	/** The Constant ESTADO_TITULAR. */
	public static final String ESTADO_TITULAR = "0";

	/**
	 * private static final String ID_MARCA_TARJETA_AMEX = "AB"; private static
	 * final String ID_MARCA_TARJETA_VISA_DEBITO = "VD"; private static final
	 * String ID_MARCA_TARJETA_VISA_CREDITO = "VC";.
	 */
	private static final double MAX_IMPORTE = 99999.99;

	/** The Constant CATEGORIA_ADICIONAL. */
	public static final String CATEGORIA_ADICIONAL = "1";

	/** The Constant CATEGORIA_TITULAR. */
	public static final String CATEGORIA_TITULAR = "0";

	/** The Constant CATEGORIA_TODOS. */
	public static final String CATEGORIA_TODOS = "2";

	/** The Constant ERROR_CANALES_AUTOM. */
	private static final String ERROR_CANALES_AUTOM = "Error en DatosSolicitanteManagerImpl.ejecutarAltaCanalesAutomaticos:";

	/** The Constant MONEDERO_WS_CLAVE_BANCO_RIO. */
	private static final String MONEDERO_WS_CLAVE_BANCO_RIO = "clavebancorio";

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * DatosSolicitanteManager#getDatosDelSolicitante(ar.com.santanderrio.obp.
	 * servicios.monedero.web.view.DatosSolicitanteCriterioView)
	 */
	@Override
	public Respuesta<DatosSolicitanteResponseView> getDatosDelSolicitante(DatosSolicitanteCriterioView datosSolicitante) {
		Respuesta<DatosSolicitanteResponseView> respuestaView = null;
		
	     if(!(sesionCliente.getCliente().tieneSoftToken())) {
	 			DatosSolicitanteResponseView datosViewWarning  = new DatosSolicitanteResponseView();
	            LOGGER.debug("DatosSolicitanteManager warning cliente sin token");
	            datosViewWarning.setUrlAyuda(urlToken);
	            return respuestaFactory.crearRespuestaWarning( datosViewWarning,"", TipoError.MONEDERO_CLIENTE_SIN_TOKEN, CodigoMensajeConstantes.MENSAJE_ERROR_MONEDERO_SIN_TOKEN) ;
	    }
		try {
			String solicitudTagMonederoMsg = horarioBancario();

			if (StringUtils.isBlank(solicitudTagMonederoMsg)) {
				DatosSolicitanteCriterioDTO datosSolicitanteDTO = new DatosSolicitanteCriterioDTO();
				BeanUtils.copyProperties(datosSolicitanteDTO, datosSolicitante);
				Respuesta<DatosSolicitanteDTO> respuestaDTO = datosSolicitanteService
						.getDatosDelSolicitante(datosSolicitanteDTO, sesionCliente.getCliente());

				// si entro aca, es que existe
				if (respuestaDTO != null && esRespuestaOK(respuestaDTO.getEstadoRespuesta())
						&& respuestaDTO.getRespuesta().getNup() != null) {
					// ALTAIR
					respuestaView = datosSolicitanteTitularOAdicional(datosSolicitante, respuestaDTO);
				} else {
					// Padron
					// Invocar al servicio CNSPADCUIT para obtener los datos del
					// padrón
					respuestaView = cnsPadCuitAdicional(datosSolicitante, respuestaView);
				}

				// busco la cuenta a la que puedo asociar un monedero
				respuestaView = generarRespuestaAdicional(datosSolicitante, respuestaView);
			} else {
				respuestaView = respuestaFactory.crearRespuestaError(null, TipoError.FUERA_DE_HORARIO,
						CodigoMensajeConstantes.MENSAJE_ERROR_FUERA_DE_HORARIO_MONEDERO);
			}

		} catch (BusinessException e) {
			LOGGER.info(SIN_INFORMACION, e);
			respuestaView = crearRespuestaErroneaInformacionNoDisponibleOperacion(datosSolicitante.isAdicional());
		} catch (DAOException e) {
			LOGGER.info(SIN_INFORMACION, e);
			respuestaView = crearRespuestaErroneaInformacionNoDisponibleOperacion(datosSolicitante.isAdicional());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return respuestaView;
	}

	/**
	 * Generar respuesta adicional.
	 * 
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param respuestaView
	 *            the respuesta view
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteResponseView> generarRespuestaAdicional(
			DatosSolicitanteCriterioView datosSolicitante, Respuesta<DatosSolicitanteResponseView> respuestaView) {
		if (datosSolicitante.isAdicional()) {
			Cuenta cuenta = MonederoUtils.obtenerCuentaMonedero(sesionCliente.getCliente());

			if (cuenta != null && cuenta.getId().toString() != null && respuestaView != null) {
				respuestaView.getRespuesta().getDatosSolicitanteView().setNroCuenta(cuenta.getId().toString());

			} else {
				// OJO ver si esta ok esto.. no deberia entrar por aca
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TAG,
						CodigoMensajeConstantes.MENSAJE_ERROR_FUERA_DE_HORARIO_MONEDERO);
			}
		}
		return respuestaView;
	}

	/**
	 * Cns pad cuit adicional.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param respuestaView
	 *            the respuesta view
	 * @return the respuesta
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<DatosSolicitanteResponseView> cnsPadCuitAdicional(DatosSolicitanteCriterioView datosSolicitante,
			Respuesta<DatosSolicitanteResponseView> respuestaView) throws DAOException {

		if (datosSolicitante.isAdicional()) {
			Respuesta<ConsultaPadronCuitOutEntity> responseConsultaPadronOutEntity = this.datosPadronBO
					.getDatosPadronBO(datosSolicitante, sesionCliente.getCliente());

			if (responseConsultaPadronOutEntity.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				Respuesta<DatosSolicitanteResponseView> respuestaView1 = this
						.crearRespuestaConsultaPadron(responseConsultaPadronOutEntity);
				respuestaView1.getRespuesta().setExiste(false);
				return respuestaView1;

			} else if (responseConsultaPadronOutEntity.getItemsMensajeRespuesta().get(0).getTipoError()
					.equals(TipoError.PERSONA_NO_EXISTE_EN_PADRON.getDescripcion())) {
				estadisticaManager.add(EstadisticasConstants.MONEDERO_ADIC_NO_EXISTE_PADRON,
						EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaWarning("", TipoError.DATOS_INVALIDOS,
						CodigoMensajeConstantes.ERROR_NO_EXISTE_EN_PADRON);
			}
		}
		return respuestaView;
	}

	/**
	 * Datos solicitante adicional.
	 *
	 * @param datosSolicitante
	 *            the datos solicitante
	 * @param respuestaDTO
	 *            the respuesta DTO
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteResponseView> datosSolicitanteTitularOAdicional(
			DatosSolicitanteCriterioView datosSolicitante, Respuesta<DatosSolicitanteDTO> respuestaDTO) {
		Respuesta<DatosSolicitanteResponseView> respuestaView;
		if (datosSolicitante.isAdicional()) {
			respuestaView = crearRespuestaDatosDelSolicitanteAdicional(respuestaDTO);
			respuestaView.getRespuesta().getDatosSolicitanteView().setExiste(true);

		} else {
			respuestaView = crearRespuestaDatosDelSolicitanteTitular(respuestaDTO);
			estadisticaManager.add(EstadisticasConstants.MONEDERO_SOLICITUD_TITULAR_TOKEN,
					EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		}
		return respuestaView;
	}

	/**
	 * Es respuesta OK.
	 *
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @return the boolean
	 */
	public Boolean esRespuestaOK(EstadoRespuesta estadoRespuesta) {
		return estadoRespuesta.equals(EstadoRespuesta.OK);
	}

	/**
	 * Gets the cuentas.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the cuentas
	 */
	public List<CuentaMonederoView> getCuentas(Cliente cliente) {

		List<Cuenta> cuentas = cliente.getCuentas();
		List<CuentaMonederoView> cuentasMonedero = new ArrayList<CuentaMonederoView>();

		for (Cuenta cuenta : cuentas) {
			CuentaMonederoView cuentaMonederoView = new CuentaMonederoView();
			if (isCuentaVisa(cuenta) || isCuentaDebito(cuenta)) {
				String marca = isCuentaVisa(cuenta) ? "Visa" + BLANK : "Débito" + BLANK;
				cuentaMonederoView.setId(cuenta.getId().toString());
				cuentaMonederoView.setDescripcion(marca + this.mascaraTarjetaDeCredito(cuenta.getNroTarjetaCredito()));
				cuentasMonedero.add(cuentaMonederoView);
			}
		}
		return cuentasMonedero;
	}

	/**
	 * Checks if is cuenta visa.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is cuenta visa
	 */
	public boolean isCuentaVisa(Cuenta cuenta) {
		return CODIGO_TITULARIDAD_TI.equals(cuenta.getCodigoTitularidad())
				&& TIPO_CUENTA_VISA.equals(cuenta.getTipoCuenta())
				&& ESTADO_TARJETA_CREDITO_VALIDO.equals(cuenta.getEstadoTarjetaCredito());
	}

	/**
	 * Checks if is cuenta amex.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is cuenta amex
	 */
	public boolean isCuentaAmex(Cuenta cuenta) {
		return CODIGO_TITULARIDAD_TI.equals(cuenta.getCodigoTitularidad())
				&& TIPO_CUENTA_AMEX.equals(cuenta.getTipoCuenta())
				&& ESTADO_TARJETA_CREDITO_VALIDO.equals(cuenta.getEstadoTarjetaCredito());
	}

	/**
	 * Checks if is cuenta debito.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return true, if is cuenta debito
	 */
	public boolean isCuentaDebito(Cuenta cuenta) {
		return (CODIGO_TITULARIDAD_TI.equals(cuenta.getCodigoTitularidad())
				|| CODIGO_TITULARIDAD_CT.equals(cuenta.getCodigoTitularidad()))
				&& TIPO_CUENTA_DEBITO.equals(cuenta.getTipoCuenta())
				&& !ESTADO_TARJETA_CREDITO_INVALIDO.equals(cuenta.getEstadoTarjetaCredito());
	}

	/**
	 * Mascara tarjeta de credito.
	 *
	 * @param cuenta
	 *            the cuenta
	 * @return the string
	 */
	public String mascaraTarjetaDeCredito(String cuenta) {
		return PREFIJO_X + GUION_STRING + StringUtils.right(cuenta, CANTIDAD_CARACTERES);
	}

	/**
	 * Crear respuesta datos del solicitante titular.
	 *
	 * @param respuestaDatosSolicitanteDTO
	 *            the respuesta datos solicitante DTO
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteResponseView> crearRespuestaDatosDelSolicitanteTitular(
			Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO) {
		DatosSolicitanteView datosSolicitanteView = new DatosSolicitanteView(
				respuestaDatosSolicitanteDTO.getRespuesta());
		DatosSolicitanteResponseView datosSolicitanteViewResponse = new DatosSolicitanteResponseView();
		datosSolicitanteView
				.setSexo("M".equals(respuestaDatosSolicitanteDTO.getRespuesta().getSexo()) ? "Masculino" : "Femenino");
		datosSolicitanteViewResponse.setDatosSolicitanteView(datosSolicitanteView);
		datosSolicitanteViewResponse.setCuentasMonederoView(this.getCuentas(sesionCliente.getCliente()));
		datosSolicitanteViewResponse
				.setImportesARecargarView(dtoToView(datosSelectoresService.obtenerImportesARecargar()));
		datosSolicitanteViewResponse.setLimitesDeRecargaMensualView(
				limiteDtoToView(datosSelectoresService.obtenerLimitesDeRecargaMensual()));
		return getRespuestaFactory().crearRespuestaOk(DatosSolicitanteResponseView.class, datosSolicitanteViewResponse);
	}

	/**
	 * 
	 * Metodo que responde un objeto ViewResponse a partir de un objeto
	 * Respuesta OutEntity *.
	 *
	 * @param respuestaEntity
	 *            the respuesta entity
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteResponseView> crearRespuestaConsultaPadron(
			Respuesta<ConsultaPadronCuitOutEntity> respuestaEntity) {
		DatosSolicitanteView datosPadronView = new DatosSolicitanteView(respuestaEntity.getRespuesta()); // esto
																											// solo
																											// admite
																											// un
																											// objeto
																											// DTO,
																											// voy
																											// a
																											// sobrecargar
																											// el
																											// metodo
		DatosSolicitanteResponseView datosPadronViewResponse = new DatosSolicitanteResponseView();
		datosPadronViewResponse.setDatosSolicitanteView(datosPadronView);

		datosPadronViewResponse.setPaisNacimientoView(
				tarjetaCreditoAdicionalManager.dtoToViewPaises(datosSelectoresService.obtenerPaisesDeNacimiento()));
		datosPadronViewResponse
				.setSexoView(tarjetaCreditoAdicionalManager.dtoToViewSexo(datosSelectoresService.obtenerSexo()));
		datosPadronViewResponse.setEstadoCivilView(
				tarjetaCreditoAdicionalManager.dtoToViewEstadoCivil(datosSelectoresService.obtenerEstadoCivil()));
		datosPadronViewResponse
				.setNacionalidadView(dtoToViewNacionalidad(datosSelectoresService.obtenerNacionalidad()));
		datosPadronViewResponse.setImportesARecargarView(dtoToView(datosSelectoresService.obtenerImportesARecargar()));
		datosPadronViewResponse.setLimitesDeRecargaMensualView(
				limiteDtoToView(datosSelectoresService.obtenerLimitesDeRecargaMensual()));

		return getRespuestaFactory().crearRespuestaOk(DatosSolicitanteResponseView.class, datosPadronViewResponse);
	}

	/**
	 * Crear respuesta datos del solicitante adicional.
	 *
	 * @param respuestaDatosSolicitanteDTO
	 *            the respuesta datos solicitante DTO
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteResponseView> crearRespuestaDatosDelSolicitanteAdicional(
			Respuesta<DatosSolicitanteDTO> respuestaDatosSolicitanteDTO) {
		DatosSolicitanteView datosSolicitanteView = new DatosSolicitanteView(
				respuestaDatosSolicitanteDTO.getRespuesta());
		datosSolicitanteView
				.setSexo("H".equals(respuestaDatosSolicitanteDTO.getRespuesta().getSexo()) ? "Masculino" : "Femenino");
		DatosSolicitanteResponseView datosSolicitanteViewResponse = new DatosSolicitanteResponseView();
		datosSolicitanteViewResponse.setDatosSolicitanteView(datosSolicitanteView);
		datosSolicitanteViewResponse.setCuentasMonederoView(this.getCuentas(sesionCliente.getCliente()));
		datosSolicitanteViewResponse
				.setImportesARecargarView(dtoToView(datosSelectoresService.obtenerImportesARecargar()));
		datosSolicitanteViewResponse.setLimitesDeRecargaMensualView(
				limiteDtoToView(datosSelectoresService.obtenerLimitesDeRecargaMensual()));

		if (datosSolicitanteView.getPaisNacimiento() == null) {
			datosSolicitanteViewResponse.setPaisNacimientoView(
					tarjetaCreditoAdicionalManager.dtoToViewPaises(datosSelectoresService.obtenerPaisesDeNacimiento()));
		} else {
			List<PaisDeNacimientoView> paises = new ArrayList<PaisDeNacimientoView>();
			PaisDeNacimientoView paisDeNacimientoView = new PaisDeNacimientoView();
			paisDeNacimientoView.setId("0");
			paisDeNacimientoView.setDescripcion(datosSolicitanteView.getPaisNacimiento());
			paises.add(paisDeNacimientoView);
			datosSolicitanteViewResponse.setPaisNacimientoView(paises);
		}

		if (datosSolicitanteView.getSexo() == null) {
			datosSolicitanteViewResponse
					.setSexoView(tarjetaCreditoAdicionalManager.dtoToViewSexo(datosSelectoresService.obtenerSexo()));
		} else {
			List<SexoView> sexo = new ArrayList<SexoView>();
			SexoView sexoView = new SexoView();
			sexoView.setId("0");
			sexoView.setDescripcion(datosSolicitanteView.getSexo());
			sexo.add(sexoView);
			datosSolicitanteViewResponse.setSexoView(sexo);
		}

		if (datosSolicitanteView.getEstadoCivil() == null) {
			datosSolicitanteViewResponse.setEstadoCivilView(
					tarjetaCreditoAdicionalManager.dtoToViewEstadoCivil(datosSelectoresService.obtenerEstadoCivil()));
		} else {
			List<EstadoCivilView> estadoCivil = new ArrayList<EstadoCivilView>();
			EstadoCivilView estadoCivilView = new EstadoCivilView();
			estadoCivilView.setId("0");
			estadoCivilView.setDescripcion(datosSolicitanteView.getEstadoCivil());
			estadoCivil.add(estadoCivilView);
			datosSolicitanteViewResponse.setEstadoCivilView(estadoCivil);
		}

		if (datosSolicitanteView.getNacionalidad() == null) {
			datosSolicitanteViewResponse
					.setNacionalidadView(dtoToViewNacionalidad(datosSelectoresService.obtenerNacionalidad()));
		} else {
			List<NacionalidadView> nacionalidad = new ArrayList<NacionalidadView>();
			NacionalidadView nacionalidadView = new NacionalidadView();
			nacionalidadView.setId("0");
			nacionalidadView.setDescripcion(datosSolicitanteView.getNacionalidad());
			nacionalidad.add(nacionalidadView);
			datosSolicitanteViewResponse.setNacionalidadView(nacionalidad);
		}

		return getRespuestaFactory().crearRespuestaOk(DatosSolicitanteResponseView.class, datosSolicitanteViewResponse);
	}

	/**
	 * Tipos docdto to view.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<String> tiposDocdtoToView(List<Opcion> lista) {
		List<String> listaRespuesta = new ArrayList<String>();
		for (int i = 0; i < lista.size(); i++) {
			Opcion opcion = lista.get(i);
			listaRespuesta.add(opcion.getOpcion());
		}
		return listaRespuesta;
	}

	/**
	 * Dto to view.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<ImporteARecargarView> dtoToView(List<Opcion> lista) {
		List<ImporteARecargarView> listaRespuesta = new ArrayList<ImporteARecargarView>();
		for (int i = 0; i < lista.size(); i++) {
			Opcion opcion = lista.get(i);
			ImporteARecargarView importesARecargarView = new ImporteARecargarView();
			importesARecargarView.setId(Integer.toString(i));
			importesARecargarView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(importesARecargarView);
		}
		return listaRespuesta;
	}

	/**
	 * Limite dto to view.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<LimiteDeRecargaMensualView> limiteDtoToView(List<Opcion> lista) {
		List<LimiteDeRecargaMensualView> listaRespuesta = new ArrayList<LimiteDeRecargaMensualView>();
		for (int i = 0; i < lista.size(); i++) {
			Opcion opcion = lista.get(i);
			LimiteDeRecargaMensualView limitesDeRecargaMensualView = new LimiteDeRecargaMensualView();
			limitesDeRecargaMensualView.setId(Integer.toString(i));
			limitesDeRecargaMensualView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(limitesDeRecargaMensualView);
		}
		return listaRespuesta;
	}

	/**
	 * Dto to view nacionalidad.
	 *
	 * @param lista
	 *            the lista
	 * @return the list
	 */
	private List<NacionalidadView> dtoToViewNacionalidad(List<Opcion> lista) {
		List<NacionalidadView> listaRespuesta = new ArrayList<NacionalidadView>();
		for (int i = 0; i < lista.size(); i++) {
			Opcion opcion = lista.get(i);
			NacionalidadView importesARecargarView = new NacionalidadView();
			importesARecargarView.setId(opcion.getOpcion());
			importesARecargarView.setDescripcion(opcion.getOpcion());
			listaRespuesta.add(importesARecargarView);
		}
		return listaRespuesta;
	}

	/**
	 * Crear respuesta erronea informacion no disponible operacion.
	 *
	 * @param isAdicional
	 *            the is adicional
	 * @return the respuesta
	 */
	private Respuesta<DatosSolicitanteResponseView> crearRespuestaErroneaInformacionNoDisponibleOperacion(
			boolean isAdicional) {
		if (isAdicional) {
			return getRespuestaFactory().crearRespuestaError(SIN_INFORMACION, TipoError.ERROR_SOLICITAR_TAG_ADICIONAL,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_SOLICITANTE_MONEDERO);
		} else {
			return getRespuestaFactory().crearRespuestaError(SIN_INFORMACION, TipoError.ERROR_SOLICITAR_TAG_TITULAR,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_SOLICITANTE_MONEDERO);
		}

	}

	/**
	 * Gets the respuesta factory.
	 *
	 * @return the respuesta factory
	 */
	public RespuestaFactory getRespuestaFactory() {
		return respuestaFactory;
	}

	/**
	 * Horario bancario.
	 *
	 * @return the string
	 */
	public String horarioBancario() {

		DateTimeFormatter df = DateTimeFormat.forPattern(TIME_FORMAT_PATTERN);

		DateTime horarioBancarioFinal = df.parseLocalTime(horaHastaALTA).toDateTimeToday();
		DateTime horarioBancarioInicio = df.parseLocalTime(horaDesdeALTA).toDateTimeToday().plusDays(1);

		DateTime horaActual = new DateTime();

		Interval intervaloFueraHorario = new Interval(horarioBancarioFinal, horarioBancarioInicio);

		if (intervaloFueraHorario.contains(horaActual)) {
			return mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MENSAJE_PAGO_PROGRAMADO_FUERA_HORARIO)
					.getMensaje();
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Armar cabecera monedero titular.
	 *
	 * @param tag
	 *            the tag
	 * @return the monedero tag
	 */
	private MonederoTagView armarCabeceraMonederoTitular(TagEntity tag) {
		MonederoTagView monTag = new MonederoTagView();
		monTag.setNumero(MonederoUtils.getMaskedNum(tag.getMedioDeRecarga().getIdMarcaTarjeta(),
				tag.getMedioDeRecarga().getUlt4DigitosTarjetas()));
		monTag.setMarca(MonederoUtils.getMarcaTarjeta(tag.getMedioDeRecarga().getIdMarcaTarjeta()));
		monTag.setTipoTarjeta(MonederoUtils.getTipoTarjeta(tag.getMedioDeRecarga().getIdMarcaTarjeta()));
		monTag.setImporteRecarga(ISBANStringUtils.formatearConComaYDosDecimales(tag.getClteModuloRecarga()));
		monTag.setLimiteMensual(ISBANStringUtils.formatearConComaYDosDecimales(tag.getClteLimiteMensualRecarga()));
		monTag.setSaldo(ISBANStringUtils.formatearConComaYDosDecimales(tag.getSaldo()));
		monTag.setIsTitular(true);
		monTag.setActivo(tag.getEstado().equals(TAG_ACTIVO));
		return monTag;
	}

	/**
	 * private MonederoTag armarCabeceraMonederTitular(Cuenta cuenta, Map
	 * obtenerTagsListMap){ String nroMonederoTag =
	 * cuenta.getNroTarjetaCredito(); String marca =
	 * MonederoUtils.resuelveMarcaRecarga(cuenta); MonederoTag monTag =
	 * (MonederoTag)obtenerTagsListMap.get(nroMonederoTag);
	 * monTag.setNumero(TarjetaBOUtils.formatearNumeroTarjeta(monTag.getNumero(),
	 * monTag.getMarca())); monTag.setMarca(marca);
	 * monTag.setTipoTarjeta(MonederoUtils.getMarcaTarjeta(monTag.getTipoTarjeta()));
	 * monTag.setImporteRecarga("$" + monTag.getImporteRecarga());
	 * monTag.setLimiteMensual("$" + monTag.getLimiteMensual()); return monTag;
	 * }
	 *
	 * @param tag
	 *            the tag
	 * @param cuentas
	 *            the cuentas
	 * @param monederosInactivos
	 *            the monederos inactivos
	 * @return the saldos Y movimientos monedero
	 */

	private SaldosYMovimientosMonederoView armarSaldosYMovimientosMonedero(TagEntity tag, List<Cuenta> cuentas,
			List<MonederoDTO> monederosInactivos) {
		SaldosYMovimientosMonederoView saldosYMovimientosMonedero = new SaldosYMovimientosMonederoView();
		saldosYMovimientosMonedero.setNumero(MonederoUtils.getMaskedNum(tag.getMedioDeRecarga().getIdMarcaTarjeta(),
				tag.getMedioDeRecarga().getUlt4DigitosTarjetas()));
		saldosYMovimientosMonedero.setIsTitular(tag.getCategoria().equals(ESTADO_TITULAR));
		saldosYMovimientosMonedero.setNombreAdicional(!tag.getCategoria().equals(ESTADO_TITULAR)
				? (tag.getNombre().trim() + " " + tag.getApellido().trim()) : null);
		saldosYMovimientosMonedero.setActivo(
				tag.getEstado().equals(TAG_ACTIVO) && !isTarjetaParaActivarCNSCTAMONE(tag, monederosInactivos));
		saldosYMovimientosMonedero.setSaldo(tag.getEstado().equals(TAG_ACTIVO)
				? ISBANStringUtils.formatearConComaYDosDecimales(tag.getSaldo()) : " $ -");
		saldosYMovimientosMonedero.setHasError(false);
		saldosYMovimientosMonedero.setNroCuentaProducto(tag.getTag());
		saldosYMovimientosMonedero.setIdCuenta(tag.getEstado().equals(TAG_NO_ACTIVO)
				? MonederoUtils.obtieneCuentaPorTagNro(tag.getTag(), cuentas).getId().toString() : null);
		return saldosYMovimientosMonedero;
	}

	/**
	 * Armar linea monedero.
	 *
	 * @param obtenerTransaccionesTags
	 *            the obtener transacciones tags
	 * @return the linea view
	 */
	private LineaView armarLineaMonedero(TransaccionEntity obtenerTransaccionesTags) {
		LineaView lm = new LineaView();
		lm.setDescripcion(obtenerTransaccionesTags.getLugar());
		lm.setFecha(FechaUtils.modificarFormatoFechas(obtenerTransaccionesTags.getFecha(), "yyyyMMdd HH:mm:ss",
				"dd/MM/yyyy HH:mm:ss"));
		lm.setFechaMobile(FechaUtils.modificarFormatoFechas(obtenerTransaccionesTags.getFecha(), "yyyyMMdd HH:mm:ss",
				"dd/MM/yyyy"));
		lm.setImporte(ISBANStringUtils.formatearConComaYDosDecimales(obtenerTransaccionesTags.getImporte()));
		lm.setMovimiento(
				NombreTransaccionEnum.get(Integer.parseInt(obtenerTransaccionesTags.getTipo())).getDescripcion());
		lm.setDescripcion(obtenerTransaccionesTags.getLugar());
		return lm;
	}

	/**
	 * Obtener tags.
	 *
	 * @param cliente
	 *            the cliente
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	public List<TagEntity> obtenerTagsWS(Cliente cliente) throws BusinessException {
		List<TagEntity> tagListResult = new ArrayList<TagEntity>();
		Integer documentoNro = new Integer(cliente.getDni().trim());
		String tipoNumDoc = TipoDocMonederoEnum.obtenerTipoDocMonedero(cliente.getTipoDocumento()).getDescripcion()
				+ "|" + documentoNro.toString();
		TagsDTO dto = new TagsDTO();
		dto.setIdBanco(idBanco);
		dto.setPassword(MONEDERO_WS_CLAVE_BANCO_RIO);
		dto.setTipoNumDoc(tipoNumDoc);
		dto.setIdCuentaVirtual("");
		dto.setCategoria("");
		dto.setIdUsuario("");
		dto.setIdTag("0");
		dto.setPagCantReg("0");
		dto.setPagNum("0");

		Respuesta<List<TagEntity>> respuestaObtenerTags = datosSolicitanteService.obtenerTags(dto);
		List<TagEntity> tagsList = respuestaObtenerTags.getRespuesta();

		/** Obtiene tag titular */
		TagEntity tagTitular = obtieneTagTitular(tagsList);
		if (tagTitular != null) {
			tagListResult.add(0, tagTitular);
			/** Obtiene tags adicionales */
			for (Iterator<TagEntity> iterator = tagsList.iterator(); iterator.hasNext();) {
				TagEntity tag = iterator.next();
				if (datosValidos(tag) && tag.getCategoria().equals(CATEGORIA_ADICIONAL)
						&& (TAG_ACTIVO.equals(tag.getEstado()) || TAG_NO_ACTIVO.equals(tag.getEstado()))) {
					tagListResult.add(tag);
				}
			}
		}
		return tagListResult;
	}

	/**
	 * Obtener transacciones tags.
	 *
	 * @param cliente
	 *            the cliente
	 * @param tag
	 *            the tag
	 * @return the list
	 * @throws BusinessException
	 *             the business exception
	 */
	public List<TransaccionEntity> obtenerTransaccionesTags(Cliente cliente, TagEntity tag) throws BusinessException {

		Integer documentoNro = new Integer(cliente.getDni().trim());
		String tipoNumDoc = TipoDocMonederoEnum.obtenerTipoDocMonedero(cliente.getTipoDocumento()).getDescripcion()
				+ "|" + documentoNro.toString();

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String fechaActual = formatter.format(new Date());

		TagsTransacDTO dto = new TagsTransacDTO();
		dto.setIdBanco(idBanco);
		dto.setPassword(MONEDERO_WS_CLAVE_BANCO_RIO);
		dto.setTipoNumDoc(tipoNumDoc);
		dto.setIdTag(tag.getIdTag());
		dto.setFecDesde(MIN_FECHA);
		dto.setFecHasta(fechaActual);
		dto.setPagCantReg(PAG_CANT_REG);
		dto.setPagNum(PAG_NUM);

		Respuesta<List<TransaccionEntity>> respuestaObtenerTransaccionesTagsList = datosSolicitanteService
				.obtenerTransaccionesTags(dto);
		return respuestaObtenerTransaccionesTagsList.getRespuesta();
	}

	/**
	 * Obtiene tag titular.
	 *
	 * @param tags
	 *            the tags
	 * @return the tag entity
	 */
	private TagEntity obtieneTagTitular(List<TagEntity> tags) {
		TagEntity respuesta = null;
		for (Iterator<TagEntity> iterator = tags.iterator(); iterator.hasNext();) {
			TagEntity tag = iterator.next();
			if ("0".equals(tag.getCategoria())) {
				respuesta = tag;
			}
		}
		return respuesta;
	}

	/**
	 * Importe valido.
	 *
	 * @param var
	 *            the var
	 * @param max
	 *            the max
	 * @param field
	 *            the field
	 * @return true, if successful
	 */
	private boolean importeValido(String var, double max, String field) {
		try {
			Double n = Double.parseDouble(var);
			if (Math.abs(n) > max) {
				return false;
			}
		} catch (NumberFormatException e) {
			LOGGER.error("ObtenerTags: " + field + " invalido: " + var + "|" + e);
			return false;
		}
		return true;
	}

	/**
	 * Datos validos.
	 *
	 * @param tag
	 *            the tag
	 * @return true, if successful
	 * @throws BusinessException
	 *             the business exception
	 */
	private boolean datosValidos(TagEntity tag) throws BusinessException {
		if (!importeValido(tag.getSaldo(), MAX_IMPORTE, "saldo")) {
			return false;
		}
		if (!importeValido(tag.getClteLimiteMensualRecarga(), MAX_IMPORTE, "limiteMensualRecarga")) {
			return false;
		}
		if (!importeValido(tag.getClteModuloRecarga(), MAX_IMPORTE, "moduloRecarga")) {
			return false;
		}
		if (!tag.getCategoria().equals(CATEGORIA_TITULAR) && !tag.getCategoria().equals(CATEGORIA_ADICIONAL)) {
			LOGGER.debug("ObtenerTags: categoria invalida:" + tag.getCategoria());
			throw new BusinessException();
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * DatosSolicitanteManager#consultaMonederoTag(ar.com.santanderrio.obp.
	 * servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<DatosMonederoConMovimientosYSaldoView> consultaMonederoTag(Cliente cliente) {
		Respuesta<DatosMonederoConMovimientosYSaldoView> respuestaView = new Respuesta<DatosMonederoConMovimientosYSaldoView>();
		respuestaView.setEstadoRespuesta(EstadoRespuesta.OK);

		DatosMonederoConMovimientosYSaldoView datosMonederoConMovimientosYSaldo = new DatosMonederoConMovimientosYSaldoView();

		/** servicio CNSCTAMONE100 - Obtiene solo cuentas monedero Inactivas) */
		List<MonederoDTO> monederosInactivosCNSCTAMONE = datosSolicitanteService.getDatosTarjetaMonedero(cliente, "I")
				.getRespuesta();
		/** servicio CNSCTAMONE100 - Obtiene todas las cuentas monedero) */
		List<MonederoDTO> monederosCNSCTAMONE = datosSolicitanteService.getDatosTarjetaMonedero(cliente, "T")
				.getRespuesta();

		try {
			/** CU01_Inicio_SinMonedero */
			/**
			 * Valida lista de tags vacia - misma cantidad de tags informados
			 * por CNSCTAMONE100 Vs WSPrisma - tiene tags monedero titular
			 * informados por sesion
			 */
			if (monederosCNSCTAMONE.isEmpty()) {
				return respuestaFactory.crearRespuestaWarning(null, TipoError.ERROR_SIN_TAGS,
						CodigoMensajeConstantes.MENSAJE_ERROR_SIN_TAG_MONEDERO);
			}

			/** Obtiene tags */
			List<TagEntity> tags = obtenerTagsWS(cliente);

			/** CU02_Inicio_MonederoTitularSinActivar */
			if (isMonederoSinActivar(monederosInactivosCNSCTAMONE, tags)) {
				return respuestaFactory.crearRespuestaWarning(null, TipoError.MONEDERO_SIN_ACTIVAR,
						CodigoMensajeConstantes.MENSAJE_TAG_MONEDERO_SIN_ACTIVAR);
			}

			/** Datos de Prisma no disponibles */
			if (tags.isEmpty()) {
				LOGGER.info("Datos de Prisma no disponibles...");
				return respuestaFactory.crearRespuestaWarning(null, TipoError.MONEDERO_SIN_DATOS_PRISMA,
						CodigoMensajeConstantes.MENSAJE_ERROR_SIN_DATOS_PRISMA);
			}

			/** CU03_Inicio_MonederoTitularActivo - SaldosyMovimientos */
			/** CU04_Inicio_MonederoTitularActivo_AdicionalSinActivar */
			/** CU05_Inicio_MonederoTitularActivo_AdicionalActivo */
			MonederoTagView monederoTag = armarCabeceraMonederoTitular(tags.get(0));
			datosMonederoConMovimientosYSaldo.setTag(monederoTag);

			List<SaldosYMovimientosMonederoView> saldosYMovimientos = new ArrayList<SaldosYMovimientosMonederoView>();

			obtenerSaltosYMovimientos(cliente, monederosInactivosCNSCTAMONE, tags, saldosYMovimientos);

			datosMonederoConMovimientosYSaldo.setSaldosYMovimientos(saldosYMovimientos);
			respuestaView.setRespuesta(datosMonederoConMovimientosYSaldo);

		} catch (BusinessException e) {
			LOGGER.info(TipoError.ERROR_TAG.toString(), e);
			return respuestaFactory.crearRespuestaWarning(null, TipoError.ERROR_CARGA_SALDOS_Y_MOVIMIENTOS,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
		}
		return respuestaView;
	}

	/**
	 * Checks if is monedero sin activar.
	 *
	 * @param monederosInactivosCNSCTAMONE
	 *            the monederos inactivos CNSCTAMONE
	 * @param tags
	 *            the tags
	 * @return true, if is monedero sin activar
	 */
	private boolean isMonederoSinActivar(List<MonederoDTO> monederosInactivosCNSCTAMONE, List<TagEntity> tags) {
		return tags.size() == 1 && TAG_NO_ACTIVO.equals(tags.get(0).getEstado())
				&& isTarjetaParaActivarCNSCTAMONE(tags.get(0), monederosInactivosCNSCTAMONE);
	}

	/**
	 * Obtener saltos Y movimientos.
	 *
	 * @param cliente
	 *            the cliente
	 * @param monederosInactivosCNSCTAMONE
	 *            the monederos inactivos CNSCTAMONE
	 * @param tags
	 *            the tags
	 * @param saldosYMovimientos
	 *            the saldos Y movimientos
	 * @throws BusinessException
	 *             the business exception
	 */
	private void obtenerSaltosYMovimientos(Cliente cliente, List<MonederoDTO> monederosInactivosCNSCTAMONE,
			List<TagEntity> tags, List<SaldosYMovimientosMonederoView> saldosYMovimientos) throws BusinessException {
		for (Iterator<TagEntity> iterator = tags.iterator(); iterator.hasNext();) {
			TagEntity tag = iterator.next();

			/** Obtiene transacciones del tag */
			/**
			 * Verificar si es posible pasar parametro tagId = 0 para resolver
			 * todas las transacciones de una vez
			 */
			List<TransaccionEntity> transaccionesDelTag = obtenerTransaccionesTags(cliente, tag);

			List<LineaView> lineasDelTag = new ArrayList<LineaView>();

			for (Iterator<TransaccionEntity> iterator1 = transaccionesDelTag.iterator(); iterator1.hasNext();) {
				TransaccionEntity transaccion = iterator1.next();
				LineaView linea = armarLineaMonedero(transaccion);
				lineasDelTag.add(linea);
			}

			SaldosYMovimientosMonederoView saldosYMovimientosMonedero = armarSaldosYMovimientosMonedero(tag,
					cliente.getCuentas(), monederosInactivosCNSCTAMONE);
			saldosYMovimientosMonedero.setLineas(lineasDelTag);
			saldosYMovimientos.add(saldosYMovimientosMonedero);
		}
	}

	/**
	 * Checks if is tarjeta para activar CNSCTAMONE.
	 *
	 * @param tag
	 *            the tag
	 * @param tagsMonederos
	 *            the tags monederos
	 * @return true, if is tarjeta para activar CNSCTAMONE
	 */
	private boolean isTarjetaParaActivarCNSCTAMONE(TagEntity tag, List<MonederoDTO> tagsMonederos) {
		for (int n = 0; n < tagsMonederos.size(); ++n) {
			if (tag.getTag().equals(tagsMonederos.get(n).getNumeroTarjetaTag().trim())) {
				if (TARJETA_TIPO.equals(tagsMonederos.get(n).getTipoTarjeta())
						&& ESTADO_ENTREGADO.equals(tagsMonederos.get(n).getEstadoTarjetaTag())
						&& TAG_NO_ACTIVO.equals(tag.getEstado().trim())) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * DatosSolicitanteManager#getDatosSolicitudTagAdicional()
	 */
	@Override
	public Respuesta<DatosSolicitudTagAdicionalView> getDatosSolicitudTagAdicional() {
		Respuesta<DatosSolicitudTagAdicionalView> respuesta = new Respuesta<DatosSolicitudTagAdicionalView>();
		String mensajeDeErrorFueraDeHorario = horarioBancario();
		if (StringUtils.isBlank(mensajeDeErrorFueraDeHorario)) {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			List<String> tiposDocumentoView = tiposDocdtoToView(datosSelectoresService.obtenerTiposDeDocumento());
			DatosSolicitudTagAdicionalView datosSolicitudTagAdicionalView = new DatosSolicitudTagAdicionalView();
			datosSolicitudTagAdicionalView.setTipoDocumentoView(tiposDocumentoView);
			datosSolicitudTagAdicionalView.setMensaje(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_MENSAJE_MONEDERO).getMensaje());
			respuesta.setRespuesta(datosSolicitudTagAdicionalView);

		} else {
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
			ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
			itemMensajeRespuesta.setMensaje(mensajeDeErrorFueraDeHorario);
			itemMensajeRespuesta.setTipoError(TipoError.FUERA_DE_HORARIO.getDescripcion());
			itemMensajeRespuestaList.add(itemMensajeRespuesta);
			respuesta.setItemMensajeRespuesta(itemMensajeRespuestaList);
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * DatosSolicitanteManager#ejecutarAltaCanalesAutomaticos(ar.com.
	 * santanderrio.obp.servicios.monedero.web.view.
	 * DatosAltaCanalesAutomaticosView)
	 */
	@Override
	public Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
			DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView) {

		estadisticaManager.add(EstadisticasConstants.MONEDERO_CONFIRMACION_TITULAR,
				EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

		AltaCanalAutomaticoInEntity altaCanalAutomaticoInEntity;
		Respuesta<AltaCanalAutomaticoOutEntity> respuesta;

		try {
			altaCanalAutomaticoInEntity = buildDatosAltaCanalesAutomaticosInDTO(datosAltaCanalesAutomaticosView,
					sesionCliente.getCliente());
			respuesta = datosSolicitanteService.ejecutarAltaCanalesAutomaticos(altaCanalAutomaticoInEntity,
					sesionCliente.getCliente());
			if (respuesta != null && respuesta.getRespuesta() != null) {
				respuesta.getRespuesta().setPersonaCreada(true);
			} else {
				return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_GENERICO_ALTA_PERSONA_FISICA,
						CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_ALTA_PERSONA_FISICA);
			}

		} catch (BusinessException e) {
			LOGGER.error(ERROR_CANALES_AUTOM, e);
			return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_GENERICO_ALTA_PERSONA_FISICA,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_ALTA_PERSONA_FISICA);

		} catch (DAOException e) {
			LOGGER.error(ERROR_CANALES_AUTOM, e);
			return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_GENERICO_ALTA_PERSONA_FISICA,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_ALTA_PERSONA_FISICA);

		} catch (SinAccesoALaInformacionException e) {
			LOGGER.error(ERROR_CANALES_AUTOM, e);
			return getRespuestaFactory().crearRespuestaError("", TipoError.ERROR_GENERICO_ALTA_PERSONA_FISICA,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_ALTA_PERSONA_FISICA);
		}

		return respuesta;
	}

	/**
	 * Builds the datos alta canales automaticos in DTO.
	 *
	 * @param datosAltaCanalesAutomaticosView
	 *            the datos alta canales automaticos view
	 * @param cliente
	 *            the cliente
	 * @return the alta canal automatico in entity
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 * @throws SinAccesoALaInformacionException
	 *             the sin acceso A la informacion exception
	 */
	private AltaCanalAutomaticoInEntity buildDatosAltaCanalesAutomaticosInDTO(
			DatosAltaCanalesAutomaticosView datosAltaCanalesAutomaticosView, Cliente cliente)
			throws BusinessException, DAOException, SinAccesoALaInformacionException {
		AltaCanalAutomaticoInEntity altaCanalAutomaticoInEntity = new AltaCanalAutomaticoInEntity();

		try {

			altaCanalAutomaticoInEntity.setNombre(datosAltaCanalesAutomaticosView.getNombre());
			altaCanalAutomaticoInEntity.setApellido(datosAltaCanalesAutomaticosView.getApellido());
			altaCanalAutomaticoInEntity.setSegundoApellido(datosAltaCanalesAutomaticosView.getApellido());
			altaCanalAutomaticoInEntity.setNumDocumentoTIT(datosAltaCanalesAutomaticosView.getDocumento());
			altaCanalAutomaticoInEntity.setTipoDocumentoTIT(datosAltaCanalesAutomaticosView.getDocTipo());
			altaCanalAutomaticoInEntity.setEstadoCivil(datosAltaCanalesAutomaticosView.getEstadoCivil());
			altaCanalAutomaticoInEntity.setFecNacimiento(datosAltaCanalesAutomaticosView.getFechaNacimiento());
			altaCanalAutomaticoInEntity.setNacionalidad(datosAltaCanalesAutomaticosView.getNacionalidad());
			altaCanalAutomaticoInEntity.setPaisNacimiento(datosAltaCanalesAutomaticosView.getPais());
			altaCanalAutomaticoInEntity.setSexo(datosAltaCanalesAutomaticosView.getSexo());

			// traigo los datos del titula
			DatosSolicitanteCriterioView solicitanteCriterioView = new DatosSolicitanteCriterioView();
			solicitanteCriterioView.setDoc(cliente.getDni());
			solicitanteCriterioView.setDocTipo(cliente.getTipoDocumento());
			solicitanteCriterioView.setFechaNacimiento(cliente.getFechaNacimiento());
			solicitanteCriterioView.setNup(cliente.getNup());
			DatosSolicitanteCriterioDTO datosSolicitanteCriterioDTO = new DatosSolicitanteCriterioDTO();
			BeanUtils.copyProperties(datosSolicitanteCriterioDTO, solicitanteCriterioView);
			Respuesta<DatosSolicitanteDTO> respuestaDTO = datosSolicitanteService
					.getDatosDelSolicitante(datosSolicitanteCriterioDTO, cliente);
			DatosSolicitanteDTO datosSolicitanteDTO = respuestaDTO.getRespuesta();

			// si es adicional, copio los datos de domicilio del titular
			altaCanalAutomaticoInEntity.setDomPartCalle(datosSolicitanteDTO.getCalle());
			altaCanalAutomaticoInEntity.setDomPartNro(datosSolicitanteDTO.getCalleNro());
			altaCanalAutomaticoInEntity.setDomPartCPPatente(datosSolicitanteDTO.getCpPatente());
			altaCanalAutomaticoInEntity.setDomPartCPCodPostal(datosSolicitanteDTO.getCp());
			altaCanalAutomaticoInEntity.setDomPartCPManzana(datosSolicitanteDTO.getCpManzana());
			altaCanalAutomaticoInEntity.setDomPartLocalidad(datosSolicitanteDTO.getLocalidad());
			altaCanalAutomaticoInEntity.setDomPartProvincia(datosSolicitanteDTO.getCodProvincia());
			altaCanalAutomaticoInEntity.setDomCodPais(datosAltaCanalesAutomaticosView.getNacionalidad());
			altaCanalAutomaticoInEntity.setTipoInscripcionCuitCuil("CUIL");
			altaCanalAutomaticoInEntity.setNroInscripcion(datosAltaCanalesAutomaticosView.getCuitOCuil());
			altaCanalAutomaticoInEntity.setTelefono1DDN(datosSolicitanteDTO.getTelefono());
			altaCanalAutomaticoInEntity.setTelefono1Caracteristica(datosSolicitanteDTO.getTelefono());
			altaCanalAutomaticoInEntity.setTelefono1Numero(datosSolicitanteDTO.getTelefono());

			// OJO! Esta no es la sucursal principal.. la suc principal, hay que
			// obtenerla con una logica que esta
			// desarrollada en com.rio.ijhb.fep.login.Identificacion del OBP
			// productivo, linea 315
			altaCanalAutomaticoInEntity.setSucursal(cliente.getCuentas().get(0).getNroSucursal());

			tarjetaCreditoAdicionalManager.obtenerConsultaUnidadControlInEntity(cliente, altaCanalAutomaticoInEntity);

		} catch (IllegalAccessException e) {
			// nothing
		} catch (InvocationTargetException e) {
			// nothing
		}

		return altaCanalAutomaticoInEntity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.monedero.web.manager.
	 * DatosSolicitanteManager#datosPadron(ar.com.santanderrio.obp.servicios.
	 * monedero.web.view.DatosSolicitanteCriterioView)
	 */
	@Override
	public Respuesta<DatosSolicitanteResponseView> datosPadron(
			DatosSolicitanteCriterioView datosSolicitanteCriterioView) {
		try {
			Respuesta<DatosSolicitanteResponseView> respuestaView = null;

			Respuesta<ConsultaPadronCuitOutEntity> responseConsultaPadronOutEntity = this.datosPadronBO
					.getDatosPadronBO(datosSolicitanteCriterioView, sesionCliente.getCliente());
			// Hay datos en el padron
			if (responseConsultaPadronOutEntity.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				respuestaView = this.crearRespuestaConsultaPadron(responseConsultaPadronOutEntity);
				respuestaView.getRespuesta().setExiste(false);
			}
			return respuestaView;
		} catch (DAOException e) {
			LOGGER.error(TipoError.ERROR_TAG.toString(), e);
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_TAG,
					CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_TAG_MONEDERO);
		}
	}

}
