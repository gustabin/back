package ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.util.CuentasUtils;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.inversiones.comun.MonedaEspecieEnum;
import ar.com.santanderrio.obp.servicios.login.bo.MyaBO;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoManagerBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPreaprobadoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.SimuladorPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPreaprobadoMonoproductoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamoPreaprobadoUtils;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;
import ar.com.santanderrio.obp.servicios.prestamos.view.*;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.PrestamoPreaprobadoManager;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserRequestData;
import ar.com.santanderrio.obp.servicios.rsa.entities.RsaUpdateUserResponseData;
import ar.com.santanderrio.obp.servicios.rsa.web.manager.RsaManager;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.dao.DestinoPrestamoDAO;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.enums.CanalIngresoEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


@Component
public class PrestamoPreaprobadoManagerImpl implements PrestamoPreaprobadoManager {


	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoPreaprobadoManagerImpl.class);

	@Value("${PRESTAMO.PREAPROBADO.MONOPRODUCTO.TYC}")
	private String urlPrestamoPreaprobadoTyC;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The Prestamo Preaprobado BO. */
	@Autowired
	private PrestamoPreaprobadoBO prestamoPreaprobadoBO;

	/** The estadistica Manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;

	/** The rsa manager. */
	@Autowired
	private RsaManager rsaManager;

	/** The modulo permiso*/
	@Autowired
	private ModuloPermisoBOImpl moduloPermisoBOImpl;

	/** The cliente BO */
	@Autowired
	private ClienteBO clienteBO;

	/** The respuesta Factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	@Autowired
	private PrestamoBO prestamoBO;

	@Autowired
	private DestinoPrestamoDAO destinoPrestamoDAO;

	@Autowired
	private PrestamoService prestamoService;

	private static final String SIMULAR = "S";

	private static final String ALTA = "L";

	private static final String ENCOLAR = "1";

	private static final String LIQUIDAR_ENCOLADO = "3";

	private static final String FASE_CANCELAR_USUARIO = "4";

    private static final String LOAN_TYPE = "PREAPROBADO";

	private static final String INSTRUMENTO_IND_NEGOCIABLE = "S";

	private static final String IND_BONIFC = "N";

	private static final int CUATRO_ENTERO = 4;

	public static final String CERO_STRING = "0";

	private static final int SALDO_CANTIDAD_DIGITOS = 17;

	private static final String NOMBRE_CANAL_OBP = "OBP";
	private static final String CODIGO_CANAL_OBP = "06";
	private static final String CODIGO_CANAL_APP = "70";

	/** The autentificacion manager. */
	@Autowired
	private AutentificacionManager autentificacionManager;

	/** The valor desafio. */
	@Value("${TRJCOORD.OPERAINDISTINTO.PRESTAMOS}")
	private String valorDesafioPrestamo;

	@Autowired
	PrestamoManagerBO prestamoManagerBO;

	/** The mya BO. */
	@Autowired
	protected MyaBO myaBO;

	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> simularPrestamosPreaprobadoMonoproducto(
			PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobado) {

		try {

			BigDecimal monto = ISBANStringUtils.convertirImporte(prestamoPreaprobado.getImporteSeleccionado().replace("$", "").trim());

			LimitePrestamoPreaprobadoView lim = PrestamoPreaprobadoUtils.obtenerOfertaSeleccionada(sesionParametros.getLimitesPreaprobado(), monto, prestamoPreaprobado.getNroCuotas());
			prestamoPreaprobado.setIdOferta(lim.getId());
			Cuenta cuenta = CuentasUtils.obtenerCuentaDesdeVista(prestamoPreaprobado.getCuentaSeleccionada(), sesionCliente.getCliente());
			prestamoPreaprobado.setCuenta(cuenta);
			PrestamoPreaprobadoMonoproductoInEntity in = obtenerRequest(prestamoPreaprobado, lim, SIMULAR);

			prestamoPreaprobado.setRequestSimulacion(in);

			Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> respuesta = prestamoPreaprobadoBO.altaSimulacionPrestamoPreabrobadoMonoproducto(in, sesionCliente.getCliente());

			if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

				//Mappea respuesta de simulacion a prestamoView
				getPrestamoPreaprobadoViewFromRespuestaAltaSimulacion(prestamoPreaprobado, respuesta);

				BigDecimal cft = ISBANStringUtils.stringToBigDecimal(respuesta.getRespuesta().getCft(),3,6, false);
				BigDecimal cftSimp = ISBANStringUtils.stringToBigDecimal(respuesta.getRespuesta().getCftSimp(),3,6, false);

				String legal = MessageFormat.format(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_PRESTAMO_PREAPROBADO_ALTA), ISBANStringUtils.formatearConComaYDosDecimales(cft.toString()), ISBANStringUtils.formatearConComaYDosDecimales(cftSimp.toString()));
				prestamoPreaprobado.setLegales(legal);

				sesionParametros.setPrestamoPreaprobadoMonoproducto(prestamoPreaprobado);

				estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_SIMULACION_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

				ResultadoAltaSimulacionPreaprobadoView resultado = obtenerResultadoSimulacion(prestamoPreaprobado);
				resultado.setLegal(legal);
				resultado.setTyc(urlPrestamoPreaprobadoTyC);
				return respuestaFactory.crearRespuestaOk(resultado);
			} else {
				
	 			if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta()) 
						&& respuesta.getItemsMensajeRespuesta().get(0).getTipoError().equals("CODIGO_ERROR_CUENTA_BLOQUEO33")
						) {
					LOGGER.error("Error Cuenta Bloqueo 33");
					return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ResultadoAltaSimulacionPreaprobadoView.class, mensajeBO
							.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CUENTA_BLOQUEO33).getMensaje(), "CODIGO_ERROR_CUENTA_BLOQUEO33");
				} else {
					if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta()))  {
						LOGGER.error("Error tiene prestamo en cola de acreditacion a 48hs.");
			            return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(ResultadoAltaSimulacionPreaprobadoView.class, mensajeBO
								.obtenerMensajePorCodigo(CodigoMensajeConstantes.FEEDBACK_YA_ENCOLADO_PREAPROBADO).getMensaje(), "CODIGO_ERROR_SOLICITUD_PENDIENTE_YA_EXISTE");

					}
				}
			}
		} catch (DAOException e) {
			LOGGER.error("Error al simular préstamo preaprobado");
			estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_SIMULACION_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIMULACION_PRESTAMO_PREAPROBADO, CodigoMensajeConstantes.FEEDBACK_ERROR_PRESTAMO_PREAPROBADO);
		} catch (ImporteConvertException e) {
			LOGGER.error("Error al simular préstamo preaprobado");
			estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_SIMULACION_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIMULACION_PRESTAMO_PREAPROBADO, CodigoMensajeConstantes.FEEDBACK_ERROR_PRESTAMO_PREAPROBADO);
		}

		estadisticaManager.add(EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_SIMULACION_FEEDBACK, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIMULACION_PRESTAMO_PREAPROBADO, CodigoMensajeConstantes.FEEDBACK_ERROR_PRESTAMO_PREAPROBADO);
	}

	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> encolarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio) {

		String codigoMensajeOk = CodigoMensajeConstantes.FEEDBACK_OK_PRESTAMO_PREAPROBADO_ENCOLAR;
		String codigoMensajeError = CodigoMensajeConstantes.FEEDBACK_ERROR_ENCOLAR_PREAPROBADO;
		String codigoEstadistica = EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_ENCOLAR_FEEDBACK;
		TipoError tipoError = TipoError.ERROR_ENCOLAR_PRESTAMO_PREAPROBADO;
		String mensajeError = "Error al encolar préstamo preaprobado";

		return encolarAdquirirPrestamoPreaprobadoMonoproducto(desafio,null,  codigoMensajeOk, codigoMensajeError, codigoEstadistica, tipoError, mensajeError, ENCOLAR);
	}

	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio) {

		String codigoMensajeOk = CodigoMensajeConstantes.FEEDBACK_OK_PRESTAMO_PREAPROBADO_ALTA;
		String codigoMensajeError = CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_PRESTAMO_48HS;
		String codigoEstadistica = EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_ALTA_FEEDBACK;
		TipoError tipoError = TipoError.ERROR_ALTA_PRESTAMO_PREAPROBADO;
		String mensajeError = "Error al liquidar préstamo preaprobado";

		return encolarAdquirirPrestamoPreaprobadoMonoproducto(desafio,desafio.getToken(), codigoMensajeOk, codigoMensajeError, codigoEstadistica, tipoError, mensajeError, ALTA);
	}

	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> confirmarPrestamoPreaprobadoMonoproductoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView) {

		String codigoMensajeOk = CodigoMensajeConstantes.FEEDBACK_OK_PRESTAMO_PREAPROBADO_ALTA;
		String codigoMensajeError = CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_PRESTAMO_48HS;
		String codigoEstadistica = EstadisticasConstants.CODIGO_PRESTAMO_PREAPROBADO_ALTA_FEEDBACK;
		TipoError tipoError = TipoError.ERROR_ALTA_PRESTAMO_PREAPROBADO;
		String mensajeError = "Error al liquidar préstamo preaprobado";
		ResultadoAltaSimulacionPreaprobadoView desafio = new ResultadoAltaSimulacionPreaprobadoView();
		desafio.setToken(liquidacionPrestamoEncoladoView.getTokenSMS());
		desafio.setDesafio(liquidacionPrestamoEncoladoView.getDesafio());

		//Obtenemos el prestamo encolado desde la api
		PrestamoEncoladoEntity prestamoEncoladoEntity;
		try {
			if(liquidacionPrestamoEncoladoView.getId() == null) {
				liquidacionPrestamoEncoladoView.setId(liquidacionPrestamoEncoladoView.getSolicitudId());
			}
			prestamoEncoladoEntity = prestamoBO.obtenerPrestamoEncolado(liquidacionPrestamoEncoladoView.getId());
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", tipoError, codigoMensajeError);
		}

		//Mappeamos el prestamo encolado a una request
		PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoInOutView = mapPrestamoEncolado(prestamoEncoladoEntity, LIQUIDAR_ENCOLADO);
		sesionParametros.setPrestamoPreaprobadoMonoproducto(prestamoPreaprobadoInOutView);
		sesionParametros.setMaxOfertaPrestamoPreaprobado(new BigDecimal(0));

		//liquidamos el prestamo
		return encolarAdquirirPrestamoPreaprobadoMonoproducto(desafio, liquidacionPrestamoEncoladoView.getTokenSMS(),codigoMensajeOk, codigoMensajeError, codigoEstadistica, tipoError, mensajeError, LIQUIDAR_ENCOLADO);
	}

	@Override
	public void eliminarPrestamoPreaprobadoMonoproductoEncolado(LiquidacionPrestamoEncoladoView liquidacionPrestamoEncoladoView, PrestamoEncoladoEntity prestamoEncoladoEntity) throws DAOException {
			//Mappeamos el prestamo encolado a una request
			PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoInOutView = mapPrestamoEncolado(prestamoEncoladoEntity, FASE_CANCELAR_USUARIO);
			validarTokenRSA(liquidacionPrestamoEncoladoView.getDesafio(),prestamoPreaprobadoInOutView);
			prestamoBO.cancelarPrestamoEncolado(liquidacionPrestamoEncoladoView.getSolicitudId());
	}

	private PrestamoPreaprobadoMonoproductoInOutView mapPrestamoEncolado(PrestamoEncoladoEntity prestamoEncolado, String fase) {
		SimpleDateFormat sdf = new SimpleDateFormat(ISBANStringUtils.FORMATO_FECHA_GUIONES);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String codCanal = NOMBRE_CANAL_OBP.equals(prestamoEncolado.getOrigin()) ? CODIGO_CANAL_OBP : CODIGO_CANAL_APP;
		PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoView = new PrestamoPreaprobadoMonoproductoInOutView();
		PrestamoPreaprobadoMonoproductoInEntity prestamoPreaprobadoEntity = new PrestamoPreaprobadoMonoproductoInEntity(String.valueOf(prestamoEncolado.getProductCode()),
				String.valueOf(prestamoEncolado.getSubProductCode()), String.valueOf(prestamoEncolado.getDestinationFunds()), String.valueOf(prestamoEncolado.getQuotas()), prestamoEncolado.getOffice().getHolderCode(),
				prestamoEncolado.getOffice().getLiftingCode(), prestamoEncolado.getAmount(), prestamoEncolado.getDebitAccount().getContractNumber(), prestamoEncolado.getDebitAccount().getContractNumber(), sesionCliente.getCliente().getNup(),
				DivisaEnum.PESO.getCodigo(),codCanal, INSTRUMENTO_IND_NEGOCIABLE, sdf.format(prestamoEncolado.getApprovalDate()),
				sdf.format(prestamoEncolado.getFormDate()), prestamoEncolado.getFeeType(), new BigDecimal(prestamoEncolado.getTna()), INSTRUMENTO_IND_NEGOCIABLE,
				sdf.format(prestamoEncolado.getFirstExpirationDate()), IND_BONIFC, fase);
		prestamoPreaprobadoView.setRequestSimulacion(prestamoPreaprobadoEntity);
		DestinoPrestamoSeleccionView motivoSeleccionado = destinoPrestamoDAO.obtenerViewPorProductoSubproductoDestino(String.valueOf(prestamoEncolado.getProductCode()),StringUtils.leftPad(String.valueOf(prestamoEncolado.getSubProductCode()), CUATRO_ENTERO, CERO_STRING), String.valueOf(prestamoEncolado.getDestinationFunds()));
		prestamoPreaprobadoView.setMotivoSeleccionado(motivoSeleccionado);
		prestamoPreaprobadoView.setImporteSeleccionado(ISBANStringUtils.formateadorSaldoPrestamo(prestamoEncolado.getAmount(), SALDO_CANTIDAD_DIGITOS));
		prestamoPreaprobadoView.setFechaSeleccionada(prestamoPreaprobadoEntity.getFecPrimerVto());
		prestamoPreaprobadoView.setNroCuotas(String.valueOf(prestamoEncolado.getQuotas()));
		prestamoPreaprobadoView.setCuenta(sesionCliente.getCliente().getCuentaSiContieneNumero(prestamoEncolado.getDebitAccount().getNroCuenta()));
		return prestamoPreaprobadoView;
	}


	private Respuesta<ResultadoAltaSimulacionPreaprobadoView> encolarAdquirirPrestamoPreaprobadoMonoproducto(ResultadoAltaSimulacionPreaprobadoView desafio, String tokenIdentidad, String codigoMensajeOk, String codigoMensajeError, String codigoEstadistica, TipoError tipoError, String mensajeError, String fase) {
		try {

			//Inicio de variables segun la sesion
			PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoInOutView = sesionParametros.getPrestamoPreaprobadoMonoproducto();
			PrestamoPreaprobadoMonoproductoInEntity in = sesionParametros.getPrestamoPreaprobadoMonoproducto().getRequestSimulacion();
			in.setFase(fase);
			//Validación RSA para encolar y liquidar encolados
			if (ENCOLAR.equals(fase) || LIQUIDAR_ENCOLADO.equals(fase)) {
				Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuestaValidacion = validarTokenRSA(desafio.getDesafio(), prestamoPreaprobadoInOutView);
				if (respuestaValidacion != null) {
					return respuestaValidacion;
				}
			}

			//Validación token identidad para liquidar inmediato y encolados
			if (ALTA.equals(fase) || LIQUIDAR_ENCOLADO.equals(fase)) {
				Respuesta<ResultadoAltaSimulacionPreaprobadoView> respuestaValidacion = validarTokenIdentidad(tokenIdentidad);
				if (respuestaValidacion != null) {
					return respuestaValidacion;
				}
			}

			//Si liquidamos fuera de horario, solo se pone en ToPayOff y devuelve ok
			if(ALTA.equals(fase) && !prestamoService.validarHorario()){
				prestamoManagerBO.dequeueKafka(LOAN_TYPE, in.getNroCliente(), !prestamoService.validarHorario());
				ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView();
				resultado.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
				resultado.setNroCuota(prestamoPreaprobadoInOutView.getNroCuotas());
				resultado.setNroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(prestamoPreaprobadoInOutView.getCuenta()));
				return respuestaFactory.crearRespuestaOk(resultado);
			}

			//Alta/Encolado de prestamo
			Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> respuesta = prestamoPreaprobadoBO.altaSimulacionPrestamoPreabrobadoMonoproducto(in, sesionCliente.getCliente());

			//Validacion de respuesta
			if (EstadoRespuesta.OK.equals(respuesta.getEstadoRespuesta())) {

				if (LIQUIDAR_ENCOLADO.equals(fase)) {
					//Mappea respuesta de alta del prestamo encolado a prestamoView
					getPrestamoPreaprobadoViewFromRespuestaAltaSimulacion(prestamoPreaprobadoInOutView, respuesta);
				}

				//(des)Encolamiento en mongo
				if (ENCOLAR.equals(fase)) {
					prestamoManagerBO.enqueueKafka(in, in.getNroCliente());
				} else {
					prestamoManagerBO.dequeueKafka(LOAN_TYPE, in.getNroCliente(), !prestamoService.validarHorario());
				}

				prestamoPreaprobadoInOutView.setNroDeComprobante(respuesta.getRespuesta().getNroComprobante());
				sesionParametros.setPrestamoPreaprobadoMonoproducto(prestamoPreaprobadoInOutView);

				//Armada respuesta satisfactoria
				ResultadoAltaSimulacionPreaprobadoView resultado = obtenerResultadoSimulacion(prestamoPreaprobadoInOutView);
				resultado.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
				resultado.setNroDeComprobante(prestamoPreaprobadoInOutView.getNroDeComprobante());
				resultado.setMensajeFeedback(MessageFormat.format(mensajeBO.obtenerMensajePorCodigo(codigoMensajeOk).getMensaje(),
						respuesta.getRespuesta().getCuenta(), DivisaEnum.PESO.getSimbolo() + ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(prestamoPreaprobadoInOutView.getImporteSeleccionado(), 4), prestamoPreaprobadoInOutView.getNroCuotas()));
				BigDecimal importeEstadistica = ISBANStringUtils.convertirABigDecimal(ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(prestamoPreaprobadoInOutView.getImporteSeleccionado(), 4));
				estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK, resultado.getNroDeComprobante(), importeEstadistica, MonedaEspecieEnum.ARS.getCodigo());

	            // se agregan los datos aparte para poder mostrarlos en el FE
	            resultado.setNroPrestamo(respuesta.getRespuesta().getCuenta());
	            resultado.setNroCuota(prestamoPreaprobadoInOutView.getNroCuotas());
	            resultado.setNroCuenta(ISBANStringUtils.formatearNroCuentaProductoConSucursal(prestamoPreaprobadoInOutView.getCuenta()));

				return respuestaFactory.crearRespuestaOk(resultado);
			}

			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta())
					&& respuesta.getItemsMensajeRespuesta().get(0).getTipoError().equals("CODIGO_ERROR_LIQUIDAR_ENCOLADO")
					&& LIQUIDAR_ENCOLADO.equals(fase)) {
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null, mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_LIQUIDAR_ENCOLADO).getMensaje(), "CODIGO_ERROR_LIQUIDAR_ENCOLADO");
			}
			if (EstadoRespuesta.WARNING.equals(respuesta.getEstadoRespuesta()) 
					&& respuesta.getItemsMensajeRespuesta().get(0).getTipoError().equals("CODIGO_ERROR_CUENTA_BLOQUEO33")
					) {
				return respuestaFactory.crearRespuestaWarningConTipoErrorPersonalizado(null, mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_CUENTA_BLOQUEO33).getMensaje(), "CODIGO_ERROR_CUENTA_BLOQUEO33");
			}
			return respuestaFactory.crearRespuestaError("", tipoError, codigoMensajeError);
		} catch (Exception e) {
			LOGGER.error(mensajeError);
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError("", tipoError, codigoMensajeError);
		}
	}

	private void getPrestamoPreaprobadoViewFromRespuestaAltaSimulacion(PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoInOutView, Respuesta<PrestamoPreaprobadoMonoproductoOutEntity> respuesta) throws ImporteConvertException {
		PrestamoPreaprobadoMonoproductoOutEntity salida = respuesta.getRespuesta();
		prestamoPreaprobadoInOutView.setImporteSeleccionado(salida.getImpconc());
		// Impconc - Impcarg
		prestamoPreaprobadoInOutView.setImporteNeto(ISBANStringUtils.convertirABigDecimal(salida.getImpconc()).subtract(ISBANStringUtils.convertirABigDecimal(salida.getImpcarg())).toString());
		prestamoPreaprobadoInOutView.setGastosOtorgamiento(ISBANStringUtils.convertirABigDecimal(salida.getTotcomi()).toString());
		// Totivat + Totrimp
		prestamoPreaprobadoInOutView.setImpuesto(ISBANStringUtils.convertirABigDecimal(salida.getTotivat()).add(ISBANStringUtils.convertirABigDecimal(salida.getTotrimp())).toString());
		prestamoPreaprobadoInOutView.setFechaSeleccionada(salida.getFeprivt());
		prestamoPreaprobadoInOutView.setCuotaConstante(salida.getCuopur());
		prestamoPreaprobadoInOutView.setTna(salida.getTna());
		prestamoPreaprobadoInOutView.setTea(salida.getTea());
		String montoString = ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales((prestamoPreaprobadoInOutView.getImporteSeleccionado().replace("$", "").trim()),4);
		BigDecimal monto = ISBANStringUtils.convertirImporte(montoString);
		LimitePrestamoPreaprobadoView lim = PrestamoPreaprobadoUtils.obtenerOfertaSeleccionada(sesionParametros.getLimitesPreaprobado(), monto, prestamoPreaprobadoInOutView.getNroCuotas());
		prestamoPreaprobadoInOutView.setTipoTasa(lim.getTipoTasa());
	}

	/**
     * Validacion Token Identidad (por sms)
     *
     * @return Devuelve una respuesta de error si falla, o null si valida correctamente
     */
    private Respuesta<ResultadoAltaSimulacionPreaprobadoView> validarTokenIdentidad(String token) {
        this.sesionParametros.setDesafioPrestamosActivo(true);

        if (!this.prestamoManagerBO.isValidToken(token)) {
            if (!StringUtils.isEmpty(token)) {
                this.sesionParametros.setDesafioPrestamosActivo(false);
                return respuestaFactory.crearRespuestaErrorConTipoErrorPersonalizado(TipoError.TOKEN_PRESTAMO_ERROR.toString(), "No pudimos validar tu identidad");
            }
            return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.SOLICITUD_TOKEN_PRESTAMO, CodigoMensajeConstantes.SOLICITUD_TOKEN_PRESTAMO);
        }
        this.sesionParametros.setDesafioPrestamosActivo(false);
        return null;
    }

    /**
     * Validacion Token RSA
     *
     * @return Devuelve una respuesta de error si falla, o null si valida correctamente
     */
    private Respuesta<ResultadoAltaSimulacionPreaprobadoView> validarTokenRSA(AutentificacionDTO autentificacionDesafio, PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobadoInOutView) {
		Cliente cliente = sesionCliente.getCliente();
		MyaDTOIn myaDTOIn = new MyaDTOIn();
		myaDTOIn.setNup(cliente.getNup());
		MyaDTOOut datosMyA = myaBO.consultaWsEstadoClienteV3(cliente, myaDTOIn);
		SimuladorPrestamoDTO simuladorPrestamoDTO = crearSimuladorPrestamoDTO(prestamoPreaprobadoInOutView, datosMyA);
        if (!this.sesionParametros.getDesafioPrestamosActivo()) {
            if (!sesionCliente.getTieneTokenRSA()) {
                LOGGER.info("RSA Offline no permite encolar el prestamo.");
                return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
                        CodigoMensajeConstantes.PRESTAMO_DENY_RSA);
            }


            AutentificacionDTO autentificacionDTO;
            Respuesta<ResultadoAltaSimulacionPreaprobadoView> estadoDesafio = autentificacionManager
                    .verificarEstadoDesafio(autentificacionDesafio, Integer.parseInt(valorDesafioPrestamo));
            if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
                autentificacionDTO = autentificacionDesafio;
            } else {
                autentificacionDTO = cargarAutentificacionDTO(simuladorPrestamoDTO);
            }

            Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = autentificacionManager.ejecutarValidacionRSA(autentificacionDTO);

            if (EstadoRespuesta.ERROR.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) {
                if (TipoError.SIN_METODO_DESAFIO.toString().equals(respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta().get(0).getTipoError())) {
                    return respuestaFactory.crearRespuestaErrorPersonalizado(ResultadoAltaSimulacionPreaprobadoView.class,
                            respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta().get(0).getMensaje(), TipoError.SIN_METODO_DESAFIO.toString());
                } else if (respEjecucionMetodoAutentificacion.getRespuesta() != null && respEjecucionMetodoAutentificacion.getRespuesta().getBloquearUsuario()) {
                    LOGGER.info("Inicio de bloqueo de usuario.");
                    Respuesta<RsaUpdateUserResponseData> responseBloqueoUsuario = rsaManager.updateUser(new RsaUpdateUserRequestData("LOCKOUT"));
                    if (EstadoRespuesta.OK.equals(responseBloqueoUsuario.getEstadoRespuesta())) {
                        return respuestaFactory.crearRespuestaWarning(StringUtils.EMPTY, TipoError.RSA_BLOQUEAR_USUARIO,
                                CodigoMensajeConstantes.RSA_LOGIN_USUARIO_BLOQUEADO);
                    } else {
                        LOGGER.info("Error al bloquear el usuario");
                        return respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.RSA_OFFLINE,
                                CodigoMensajeConstantes.ERROR_GENERICO_RSA_OFFLINE);
                    }

                } else {
                    return Respuesta.copy(ResultadoAltaSimulacionPreaprobadoView.class, respEjecucionMetodoAutentificacion);
                }


            } else if (EstadoRespuesta.WARNING.equals(respEjecucionMetodoAutentificacion.getEstadoRespuesta())) {
                ResultadoAltaSimulacionPreaprobadoView respuestaDesafio = new ResultadoAltaSimulacionPreaprobadoView();

                respuestaDesafio.setDesafio(respEjecucionMetodoAutentificacion.getRespuesta());
                return respuestaFactory.transformar(respuestaDesafio, respEjecucionMetodoAutentificacion);
            }
        }
        return null;
    }

	/**
	 * Cargamos datos para el desafío
	 * @param rsaDTO
	 * @return
	 */
	private AutentificacionDTO cargarAutentificacionDTO(RsaDTO rsaDTO) {
		return PrestamosUtils.cargarAutentificacionDTO(rsaDTO, valorDesafioPrestamo);
	}

	/**
	 * Obtiene el bean de entrada para llamar a RSA
	 * @param in
	 * @return
	 */
	private SimuladorPrestamoDTO crearSimuladorPrestamoDTO(PrestamoPreaprobadoMonoproductoInOutView in, MyaDTOOut datosMyA) {

		SimuladorPrestamoDTO simuladorDTO = new SimuladorPrestamoDTO();

		try {
			simuladorDTO.setImporteSeleccionado(ISBANStringUtils.convertirImporte(ISBANStringUtils.formatearNumericoConPuntoComaYDosdecimales(in.getImporteSeleccionado(), 4)).toString());
		} catch (ImporteConvertException e) {
			simuladorDTO.setImporteSeleccionado(in.getImporteSeleccionado());
			LOGGER.error("Error al convertir monto seleccionado.");
		}
		simuladorDTO.setCuentaSeleccionada(in.getCuenta());
		simuladorDTO.setFechaSeleccionada(in.getFechaSeleccionada());
		simuladorDTO.setMaxImpPrest(ISBANStringUtils.ajustadorBigDecimalIatx(sesionParametros.getMaxOfertaPrestamoPreaprobado(), 17, 4));
		simuladorDTO.setNyaCliente(sesionCliente.getCliente().getNombre() + " " + sesionCliente.getCliente().getApellido1());
		simuladorDTO.setTipoOperacion(OperacionesRSAEnum.PRESTAMOS);
		simuladorDTO.setValidarBloqueo(Boolean.TRUE);

		simuladorDTO.setTipoPrestamo("Pre-aprobado");
		simuladorDTO.setFase(in.getRequestSimulacion().getFase());

		String codigoProd = in.getRequestSimulacion().getProducto();
		String codigoSubProd = in.getRequestSimulacion().getSubproProp();

		String subProdPrestamo = "";
		if (codigoProd != null && codigoSubProd != null) {
			subProdPrestamo = codigoProd.concat("-".concat(ISBANStringUtils.formateadorConCerosIzq(codigoSubProd, 4)));
		}

		LOGGER.info("SubProducto Preaprobado: " + in.getRequestSimulacion().getSubproProp());
		LOGGER.info("Producto Preaprobado: " + in.getRequestSimulacion().getProducto());

		simuladorDTO.setSubproductoPrestamo(subProdPrestamo);

		String fechaNacimiento = sesionCliente.getCliente().getFechaNacimiento();
		String anioNacimiento = "";

		if (fechaNacimiento.length() > 4)
		{
			anioNacimiento = fechaNacimiento.substring(0, 4);
		} else {
		    anioNacimiento = fechaNacimiento;
		}

		LOGGER.info("Anio Nacimiento Preaprobado: " + anioNacimiento);
		simuladorDTO.setAnioNacimiento(anioNacimiento);

		try {
			Respuesta<List<BigDecimal>> antiguedades = clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Long.valueOf(sesionCliente.getCliente().getNup()));
			if(EstadoRespuesta.OK.equals(antiguedades.getEstadoRespuesta())) {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				if(antiguedades.getRespuesta() != null && !antiguedades.getRespuesta().isEmpty()) {
					if(antiguedades.getRespuesta().get(0) != null) {
						simuladorDTO.setCantDiasUltimoCambioClave(antiguedades.getRespuesta().get(0).intValue());
					}
					if(antiguedades.getRespuesta().get(1) != null) {
						simuladorDTO.setCantDiasUltimoCambioToken(antiguedades.getRespuesta().get(1).intValue());
					}
				}
			} else {
				estadisticaManager.add(EstadisticasConstants.CNS_CLIENTE_CONTROL_SEGURIDAD, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (NumberFormatException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		} catch (BusinessException e) {
			LOGGER.info("ERROR: No se ha podido recuperar antigüedad de cambio de clave y token.", e);
		}

		simuladorDTO.setStopOperacionErrorRSA(true);

		if(datosMyA != null){
			simuladorDTO.setCantidadDiasCambioCel(this.calcularDias(datosMyA.getFechaModificadoCelular()));
			simuladorDTO.setCantidadDiasCambioMail(this.calcularDias(datosMyA.getFechaModificadoEmail()));
		}
		return simuladorDTO;
	}

	/**
	 * Calcula la cantidad de dias desde el cambio de mail/celular
	 * @return
	 */
	private Integer calcularDias(String fecha){
		return PrestamosUtils.calcularDias(fecha);
	}

	/**
	 * Retorna comprobante PDF del prestamo preaprobado monoproducto
	 */
	@Override
	public Respuesta<ReporteView> descargarPrestamoPreaprobadoMonoproducto() {
		PrestamoPreaprobadoMonoproductoInOutView datos = sesionParametros.getPrestamoPreaprobadoMonoproducto();
		Respuesta<Reporte> reporte = prestamoPreaprobadoBO.descargarPrestamoPreaprobadoMonoproducto(datos);
		if (EstadoRespuesta.OK.equals(reporte.getEstadoRespuesta())) {
			ReporteView reporteView = ReporteView.fromReporte(reporte.getRespuesta());
			return respuestaFactory.crearRespuestaOk(reporteView);
		} else {
			return respuestaFactory.crearRespuestaError(ReporteView.class, reporte.getItemsMensajeRespuesta());
		}
	}
	/**
	 * Obtiene simulacion del prestamo para visualizar
	 */
	@Override
	public Respuesta<ResultadoAltaSimulacionPreaprobadoView> obtenerPrestamoPreaprobadoMonoproducto() {
		PrestamoPreaprobadoMonoproductoInOutView datos = sesionParametros.getPrestamoPreaprobadoMonoproducto();
		ResultadoAltaSimulacionPreaprobadoView simulacion = obtenerResultadoSimulacion(datos);
		return respuestaFactory.crearRespuestaOk(simulacion);
	}

	/**
	 * Busca oferta de prestamo preaprobado desde deeplink
	 */
	@Override
	public Respuesta<Void> calcularOfertaPreaprobadoFromDeeplink() {

		if(moduloPermisoBOImpl.tienePermisoMostrar(AccionController.SOLICITUD_PRESTAMO_PREAPROBADO_MONOPRODUCTO) && sesionParametros.getMaxOfertaPrestamoPreaprobado() == null) {
			PrestamoPermitidoEntity resultado = prestamoPreaprobadoBO.getMaxImporteOfertaPrestamoPreaprobado(sesionCliente.getCliente()).getRespuesta();
			if (resultado != null) {
				sesionParametros.setMaxOfertaPrestamoPreaprobado(resultado.getMaxImpPrest() != null ? ISBANStringUtils.stringToBigDecimal(resultado.getMaxImpPrest(), 13, 4, false): null);
			}
		}
		return respuestaFactory.crearRespuestaOk(null);
	}

	/**
	 * Obtiene resultado de simulacion y confirmación
	 * @param prestamo
	 * @return
	 */
	private ResultadoAltaSimulacionPreaprobadoView obtenerResultadoSimulacion(PrestamoPreaprobadoMonoproductoInOutView prestamo) {

		ResultadoAltaSimulacionPreaprobadoView resultado = new ResultadoAltaSimulacionPreaprobadoView.ResultadoSimulacionViewBuilder()
				.importe(prestamo.getImporteSeleccionado())
				.importeNetoLegalItem(prestamo.getImporteNeto())
				.comisionPorOtorgamiento(prestamo.getGastosOtorgamiento())
				.impuestos(prestamo.getImpuesto())
				.cuentaDestinoItem(prestamo.getCuenta())
				.cantidadCuotasLegalItem(prestamo.getNroCuotas(), "(1)")
				.motivoPrestamoItem(prestamo.getMotivoSeleccionado().getDescripcion())
				.fechaPrimeraCuotaLegalItem(FechaUtils.modificarFormatoFechas(prestamo.getFechaSeleccionada(), "yyyy-MM-dd", "dd/MM/yyyy"))
				.capitalInteresesCuotaConstanteItem(prestamo.getCuotaConstante(), "(2)")
				.tipoTasaItem(prestamo.getTipoTasa())
				.tnaItem(prestamo.getTna())
				.teaItem(prestamo.getTea())
				.build();
		return resultado;
	}




	/**
	 * Obtiene request para ALTFOPMOPE100
	 * @param prestamoPreaprobado
	 * @param lim
	 * @param fase
	 * @return
	 * @throws ImporteConvertException
	 */
	private PrestamoPreaprobadoMonoproductoInEntity obtenerRequest(PrestamoPreaprobadoMonoproductoInOutView prestamoPreaprobado, LimitePrestamoPreaprobadoView lim, String fase) throws ImporteConvertException {

		String cuentaAltair = ISBANStringUtils.obtenerFormatoCuentaIATX(prestamoPreaprobado.getCuenta().getOficinaAltair(), prestamoPreaprobado.getCuenta().getContratoAltair());
		BigDecimal monto = ISBANStringUtils.convertirImporte(prestamoPreaprobado.getImporteSeleccionado().replace("$","").trim());

		return new PrestamoPreaprobadoMonoproductoInEntity(lim.getProducto().toString(), lim.getSubProducto().toString(),
				prestamoPreaprobado.getMotivoSeleccionado().getId(), prestamoPreaprobado.getNroCuotas(),
				prestamoPreaprobado.getCuenta().getOficinaAltair(), prestamoPreaprobado.getCuenta().getOficinaAltair(), monto, cuentaAltair, cuentaAltair,
				sesionCliente.getCliente().getNup(), DivisaEnum.PESO.getCodigo(),
				CanalIngresoEnum.CANAL_VTA_MONOPRODUCTO.getCodigo(), INSTRUMENTO_IND_NEGOCIABLE,
				ISBANStringUtils.formatearFechaAnioMesDiaConGuiones(new Date()),
				ISBANStringUtils.formatearFechaAnioMesDiaConGuiones(new Date()),
				lim.getTipoTasa(), ISBANStringUtils.stringToBigDecimal(lim.getTna(), 3, 6, false), INSTRUMENTO_IND_NEGOCIABLE,
				ISBANStringUtils.formatearFecha(ISBANStringUtils.formatearFecha(prestamoPreaprobado.getFechaSeleccionada()),
						ISBANStringUtils.FORMATO_FECHA_GUIONES), IND_BONIFC, fase);
	}
}

