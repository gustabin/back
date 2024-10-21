/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagohaberes.bo.impl;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.base.web.view.FeedbackMensajeView;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.ValidacionCuentaOutCBUEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.CuentaInvalidaException;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.cuentas.dao.DetalleCuentaDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleCuentaEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO;
import ar.com.santanderrio.obp.servicios.pagohaberes.common.PagoHaberesUtils;
import ar.com.santanderrio.obp.servicios.pagohaberes.dao.PagoHaberesDAO;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobanteAdhesionEmpleadoEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.DatosEmpleadoPagoHaberesSimpleMultipleEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.PagoHaberesEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.PagosInformadosEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.PagosProgramadosEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.CBUInvalidoDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.RecuperarDatosPorCBUDAOException;
import ar.com.santanderrio.obp.servicios.pagohaberes.exceptions.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.DatosDestinatarioView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoHaberesEliminarView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesPagoPorCBUView;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidacionesRespuestaEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.web.view.ValidarPagoSimpleMultipleView;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.CONFIG;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Canal;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Subcanal;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Usuario;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro;
import ar.com.santanderrio.obp.servicios.transferencias.dao.TransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.impl.TransferenciaModtrfe;
import ar.com.santanderrio.obp.servicios.transferencias.exception.DestinatarioNoVerificadoException;

/**
 * The Class PagoHaberesBOImpl.
 */
@Component
public class PagoHaberesBOImpl implements PagoHaberesBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(PagoHaberesBOImpl.class);

	/** The Constant BLANK_SPACES. */
	private static final String BLANK_SPACES = "        ";

	/** The Constant FORMATO_FECHA_IATX. */
	public static final String FORMATO_FECHA_IATX = "yyyyMMdd";

	/** The Constant OK. */
	private static final String OK = "OK";

	/** The Constant FORMATO_FECHA. */
	public static final String FORMATTER_FECHA = "dd/MM/yyyy";

	/** The Constant FORMATO_HORA. */
	public static final String FORMATO_HORA = "HH:mm";

	/** The Constant CONTRATO_ACEPTADO. */
	private static final String CONTRATO_ACEPTADO = "1";

	/** The Constant CLASE_CUENTA_E. */
	private static final String CLASE_CUENTA_E = "E";

	/** The Constant CLASE_CUENTA_B. */
	private static final String CLASE_CUENTA_B = "B";

	/** The Constant CLASE_CUENTA_V. */
	private static final String CLASE_CUENTA_V = "V";

	/** The Constant CLASE_CUENTA_OTROS. */
	private static final String CLASE_CUENTA_OTROS = "OTROS";
	
	/** The Constant CLASE_CUENTA_OTROS. */
	private static final String COD_RET_CUENTA_CERRADA = "0000114";

	/** The Constant PREFIJO_CBU_SANTANDER */
	public static final String PREFIJO_CBU_SANTANDER = "072";

	/** The PropertyMap. */
	@Autowired
	private PropertyMap propertyMap;

	/** The SietePorVeintiCuatroDAO. */
	@Autowired
	private SietePorVenticuatroV1DAO sietePorVenticuatroV1DAO;

	/** The legal dao. */
	@Autowired
	private LegalDAO legalDao;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The mensaje bo. */
	@Autowired
	private MensajeBO mensajeBo;

	/** The pago haberes DAO. */
	@Autowired
	private PagoHaberesDAO pagoHaberesDAO;

	/** The Detalle Cuenta DAO. */
	@Autowired
	private DetalleCuentaDAO detalleCuentaDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The contratos BO. */
	@Autowired
	private ContratoBO contratosBO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The estadistica manager. */
	@Autowired
	private EstadisticaManager estadisticaManager;

	/** The transferencia DAO. */
	@Autowired
	private TransferenciaDAO transferenciaDAO;

	/** The horaDesdePagar *. */
	@Value("${TRANSFERENCIAS.HORADESDEHABHON}")
	private String horaDesdePagar;

	/** The horaHastaPagar *. */
	@Value("${TRANSFERENCIAS.HORAHASTAHABHON}")
	private String horaHastaPagar;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<PagoHaberesEntity> obtenerConsultaAgendamiento7x24(Cliente clienteIngresado) {
		Respuesta<PagoHaberesEntity> respuesta = new Respuesta<PagoHaberesEntity>();
		XMLRequestEntity xmlRequest = new XMLRequestEntity();
		try {
			xmlRequest = generarXMLRequest(clienteIngresado);
			XMLResponseEntity xmlResponseError = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
			DATOSRESULTADO datosResultado = xmlResponseError.getDATOSRESULTADO();
			if ("0".equals(datosResultado.getSeveridad())) {
				// Respuesta OK
				PagoHaberesEntity pagoHaberes = obtenerPagoHaberes(datosResultado);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				respuesta.setRespuesta(pagoHaberes);
			} else {
				// Respuesta ERROR del servicio
				respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
				        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
			}
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error("Ha ocurrido un error al consultar el agendamiento.", e);
			// Respuesta ERROR GENERICA
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
			return respuesta;
		} catch (ProcessingException e) {
			// Respuesta ERROR GENERICA
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
			return respuesta;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * agregarEmpleado7x24(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesValidarView)
	 */
	@Override
	public Respuesta<PagoInformadoView> validarEmpleado(Cliente cliente, PagoInformadoView pagoInformadoView) {
		Respuesta<PagoInformadoView> empleadoRespuesta = new Respuesta<PagoInformadoView>();
		try {
			PagoInformadoView validacion = pagoHaberesDAO.isClienteValido(cliente, pagoInformadoView);
			if (validacion != null) {
				empleadoRespuesta.setRespuesta(validacion);
				empleadoRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				empleadoRespuesta = respuestaFactory.crearRespuestaError(null, TipoError.CUENTA_INVALIDA,
				        CodigoMensajeConstantes.ALTA_AGENDAMIENTO_ERROR);
			}
		} catch (DAOException ex) {
			LOGGER.error("Ha ocurrido un error al validar el empleado", ex);
			empleadoRespuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
		} catch (CuentaInvalidaException e) {
			LOGGER.error("Tipo de cuenta de empleado invalida.", e);
			empleadoRespuesta = respuestaFactory.crearRespuestaError(null, TipoError.CUENTA_INVALIDA,
			        CodigoMensajeConstantes.ALTA_AGENDAMIENTO_ERROR);
		}
		return empleadoRespuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * agregarEmpleado7x24(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesAgregarView)
	 */
	@Override
	public Respuesta<ComprobanteAdhesionEmpleadoEntity> agregarEmpleado7x24(Cliente cliente,
	        PagoInformadoView pagoInformadoView) {
		Respuesta<ComprobanteAdhesionEmpleadoEntity> respuesta = new Respuesta<ComprobanteAdhesionEmpleadoEntity>();
		ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado = new ComprobanteAdhesionEmpleadoEntity();
		XMLRequestEntity agregarEmpleado = new XMLRequestEntity();
		modificarTipoCuentaUnica(pagoInformadoView);
		try {
			agregarEmpleado = generarXMLRequestAgregarEmpleado(cliente, pagoInformadoView);
			XMLResponseEntity xmlResponseError = sietePorVenticuatroV1DAO.ejecutar(agregarEmpleado);
			DATOSRESULTADO datosResultado = xmlResponseError.getDATOSRESULTADO();
			if ("0".equals(datosResultado.getSeveridad())) {
				// Respuesta OK
				Mensaje mensaje = mensajeBo
				        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_AGREGAR_EMPLEADO_OK);
				comprobanteAdhesionEmpleado
				        .setMensaje(MessageFormat.format(mensaje.getMensaje(), pagoInformadoView.getAliasDestino()));
				comprobanteAdhesionEmpleado.setNroDeComprobante(datosResultado.getIdTransaccion());
				comprobanteAdhesionEmpleado.setLegalesSEO(legalDao.obtenerLegal("1005"));
				Date fechaHoraActual = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");
				String fechaHoraActualFormateada = sdf.format(fechaHoraActual);
				comprobanteAdhesionEmpleado.setFechaHora(fechaHoraActualFormateada);
				respuesta.setRespuesta(comprobanteAdhesionEmpleado);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else if ("ZBE0117".equals(datosResultado.getSeveridad())) {
				// Si la cuenta esta inactiva no se permite reintentar.
				LOGGER.debug("La cuenta esta Inactiva.");
				respuesta = respuestaFactory.crearRespuestaError(null, TipoError.CUENTA_INACTIVA,
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_DESTINATARIO_INACTIVO);
				respuesta.getRespuesta().setMensaje(MessageFormat.format(respuesta.getRespuesta().getMensaje(),
				        pagoInformadoView.getCuentaDestino()));
			} else {
				// Respuesta ERROR del servicio
				LOGGER.debug("Se produjo un error generico.");
				respuesta = crearErrorAgregarEmpleado(
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_AGREGAR_EMPLEADO_ERROR,
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_AGREGAR_EMPLEADO_ERROR,
				        pagoInformadoView.getAliasDestino());
			}
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error("Ha ocurrido un error al validar el cliente.", e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
			// crearErrorAgregarEmpleado(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_AGREGAR_EMPLEADO_ERROR,
			// CodigoMensajeConstantes.CODIGO_PAGO_HABERES_AGREGAR_EMPLEADO_ERROR,
			// pagoInformadoView.getAliasDestino());
			return respuesta;
		} catch (CuentaInvalidaException e) {
			LOGGER.error("Ha ocurrido un error al validar la cuenta origen o la cuenta destino.", e);
			respuesta = crearErrorAgregarEmpleado(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_AGREGAR_EMPLEADO_ERROR,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_AGREGAR_EMPLEADO_ERROR,
			        pagoInformadoView.getAliasDestino());
			return respuesta;
		}
	}

	/**
	 * Modificar tipo cuenta unica.
	 *
	 * @param pagoInformadoView the pago informado view
	 */
	private void modificarTipoCuentaUnica(PagoInformadoView pagoInformadoView) {
		if ("CU".equals(pagoInformadoView.getTipoCuentaDestino())) {
			pagoInformadoView.setTipoCuentaDestino("CUP");
		}
		if ("CU".equals(pagoInformadoView.getTipoCuentaOrigen())) {
			pagoInformadoView.setTipoCuentaOrigen("CUP");
		}
	}

	/**
	 * Crear error agregar empleado.
	 *
	 * @param codigoMensajeConstantesConReintento the codigo mensaje constantes con
	 *                                            reintento
	 * @param codigoMensajeConstantesSinReintento the codigo mensaje constantes sin
	 *                                            reintento
	 * @param aliasDestino                        the alias destino
	 * @return the respuesta
	 */
	private Respuesta<ComprobanteAdhesionEmpleadoEntity> crearErrorAgregarEmpleado(
	        String codigoMensajeConstantesConReintento, String codigoMensajeConstantesSinReintento,
	        String aliasDestino) {
		Respuesta<ComprobanteAdhesionEmpleadoEntity> respuesta;
		if (sesionParametros.getContador().permiteReintentar()) {
			// MENSAJE ERROR QUE PERMITE REINTENTAR
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AGENDAR_EMPLEADO_CON_REINTENTO,
			        codigoMensajeConstantesConReintento);
		} else {
			sesionParametros.setContador(null);
			// MENSAJE ERROR QUE NO PERMITE REINTENTAR
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_AGENDAR_EMPLEADO_SIN_REINTENTO,
			        codigoMensajeConstantesSinReintento);
		}
		respuesta.getItemsMensajeRespuesta().get(0).setMensaje(
		        MessageFormat.format(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), aliasDestino));

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * eliminarEmpleado7x24(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente, ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesEliminarView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> eliminarEmpleado7x24(Cliente clienteIngresado,
	        PagoHaberesEliminarView pagoHaberesEliminarView) {
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		XMLRequestEntity xmlRequest = new XMLRequestEntity();
		try {
			xmlRequest = generarXMLRequestEliminarEmpleado(clienteIngresado, pagoHaberesEliminarView);
			XMLResponseEntity xmlResponseError = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
			DATOSRESULTADO datosResultado = xmlResponseError.getDATOSRESULTADO();
			if ("0".equals(datosResultado.getSeveridad())) {
				// Respuesta OK
				Mensaje mensaje = mensajeBo
				        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_EMPLEADO_OK);
				FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();
				feedbackMensajeView.setMensaje(mensaje.getMensaje());
				respuesta.setRespuesta(feedbackMensajeView);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				// Respuesta ERROR del servicio
				if (sesionParametros.getContador().permiteReintentar()) {
					// MENSAJE ERROR QUE PERMITE REINTENTAR
					respuesta = respuestaFactory.crearRespuestaError(null,
					        TipoError.ERROR_ELIMINAR_EMPLEADO_CON_REINTENTO,
					        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
				} else {
					sesionParametros.setContador(null);
					// MENSAJE ERROR QUE NO PERMITE REINTENTAR
					respuesta = respuestaFactory.crearRespuestaError(null,
					        TipoError.ERROR_ELIMINAR_EMPLEADO_SIN_REINTENTO,
					        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
				}
			}
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error("Ha ocurrido un error al eliminar el empleado", e);
			// Respuesta ERROR GENERICA
			if (sesionParametros.getContador().permiteReintentar()) {
				// MENSAJE ERROR QUE PERMITE REINTENTAR
				respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ELIMINAR_EMPLEADO_CON_REINTENTO,
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
			} else {
				sesionParametros.setContador(null);
				// MENSAJE ERROR QUE NO PERMITE REINTENTAR
				respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_ELIMINAR_EMPLEADO_SIN_REINTENTO,
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
			}
			return respuesta;
		}
	}

	/**
	 * Obtener pago haberes.
	 *
	 * @param datosResultado the datos resultado
	 * @return the pago haberes
	 */
	private PagoHaberesEntity obtenerPagoHaberes(DATOSRESULTADO datosResultado) {
		PagoHaberesEntity pagoHaberes = new PagoHaberesEntity();
		List<PagosInformadosEntity> pagosInformadosList = new ArrayList<PagosInformadosEntity>();
		PagosInformadosEntity pagosInformados;
		List<PagosProgramadosEntity> pagosProgramadosList = new ArrayList<PagosProgramadosEntity>();
		PagosProgramadosEntity pagosProgramados;
		BigDecimal totalNomina = new BigDecimal(0);
		BigDecimal totalPagosProgramados = new BigDecimal(0);

		List<Registro> registros = datosResultado.getRegistro();

		for (Iterator<Registro> iterator = registros.iterator(); iterator.hasNext();) {
			Registro registro = (Registro) iterator.next();

			pagosInformados = new PagosInformadosEntity();
			// XML-DatosAdicionales-DatosSueldo- Destinatario
			pagosInformados.setEmpleado(
			        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().getDestinatario());
			// XML-DatosAdicionales-DatosSueldo- TipoCUIL
			pagosInformados.setTipoCuitCuil(
			        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().getTipoCUIL());
			// XML-DatosAdicionales-DatosSueldo- NumeroCUIL
			pagosInformados.setCuitCuil(
			        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().getNumeroCUIL());
			// XML-datos- TipoCuentaCredito.
			pagosInformados.setTipoCtaCredito(registro.getDATOSENTRADA().getTipoCtaCredito());
			// XML-datos- SucursalCredito.
			pagosInformados.setSucCtaCredito(registro.getDATOSENTRADA().getSucCtaCredito());
			// XML-datos- NumeroCuentaCredito
			pagosInformados.setNroCtaCredito(registro.getDATOSENTRADA().getNroCtaCredito());
			// XML-DatosAdicionales-DatosSueldo- TipoPago
			pagosInformados
			        .setTipoPago(registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().getTipoPago());
			// XML-DatosAdicionales-DatosSueldo- DescripcionConcepto
			pagosInformados.setDescripcionConcepto(
			        registro.getDatosAdicionales().getDatosAdicionales().getDatosSueldos().getDescripcionConcepto());
			// XML-datos- Importe
			pagosInformados.setImporte(new BigDecimal(String.valueOf(registro.getDATOSENTRADA().getImporte())));
			pagosInformados.setTipoPersona(registro.getDATOSENTRADA().getTipoPersona());
			pagosInformados.setTipoId(registro.getDATOSENTRADA().getTipoId());
			pagosInformados.setIdCliente(registro.getDATOSENTRADA().getIdCliente());
			pagosInformados.setFechaNac(registro.getDATOSENTRADA().getFechaNac());
			pagosInformados.setNup(registro.getDATOSENTRADA().getComprobanteCredito());
			pagosInformados.setIdTransaccion(registro.getIdTransaccion());
			pagosInformados.setDivisa(DivisaEnum.PESO.getSimbolo());
			pagosInformados.setSucCtaDebito(registro.getDATOSENTRADA().getSucCtaDebito());
			pagosInformados.setNroCtaDebito(registro.getDATOSENTRADA().getNroCtaDebito());
			pagosInformados.setTipoCtaDebito(registro.getDATOSENTRADA().getTipoCtaDebito());
			pagosInformados.setNroRecurrencia(registro.getNroRecurrencia());
			pagosInformados.setFechaBaseRecurrencia(registro.getFechaBaseRecurrencia());
			pagosInformados.setFrecRecurrencia(registro.getFrecRecurrencia());
			pagosInformados.setMaxRecurrencia(registro.getMaxRecurrencia());
			pagosInformados.setTipoRecurrencia(registro.getTipoRecurrencia());
			pagosInformados.setTienePagoProgramado(false);
			totalNomina = totalNomina.add(pagosInformados.getImporte());

			if ("E".equals(registro.getTipoAgendamiento())) {
				pagosInformados.setTienePagoProgramado(true);
				pagosProgramados = new PagosProgramadosEntity();
				try {
					BeanUtils.copyProperties(pagosProgramados, pagosInformados);
					// XML-FechaEjecucion (es lo unico que se le agrega a
					// PagosInformados)
					pagosProgramados.setFechaEjecucion(ISBANStringUtils.parsearFechaIATX(registro.getFechaEjecucion()));
					// <TipoAgendamiento>
					pagosProgramados.setTipoAgendamiento(registro.getTipoAgendamiento());
					totalPagosProgramados = totalPagosProgramados.add(pagosProgramados.getImporte());

					pagosProgramadosList.add(pagosProgramados);
				} catch (Exception e) {
					LOGGER.error("Ha ocurrido un error al obtener pago de haberes.", e);
				}

			}
			pagosInformadosList.add(pagosInformados);

		}

		ordenarPagosInformados(pagosInformadosList);
		pagoHaberes.setPagosInformadosList(pagosInformadosList);
		ordenarPagosProgramados(pagosProgramadosList);
		pagoHaberes.setPagosProgramadosList(pagosProgramadosList);
		pagoHaberes.setTotalNomina(totalNomina);
		pagoHaberes.setTotalPagosProgramados(totalPagosProgramados);
		return pagoHaberes;
	}

	/**
	 * Ordenar pagos informados.
	 *
	 * @param pagosInformadosList the pagos informados list
	 */
	private void ordenarPagosInformados(List<PagosInformadosEntity> pagosInformadosList) {
		Collections.sort(pagosInformadosList, new Comparator<PagosInformadosEntity>() {
			@Override
			public int compare(PagosInformadosEntity pagosInformados1, PagosInformadosEntity pagosInformados2) {
				// Si el empleado viene null desde la base, lo mostramos como
				// vacio. No deberia ocurrir.
				if (pagosInformados1.getEmpleado() == null) {
					pagosInformados1.setEmpleado("");
				}
				if (pagosInformados2.getEmpleado() == null) {
					pagosInformados2.setEmpleado("");
				}
				return pagosInformados1.getEmpleado().compareTo(pagosInformados2.getEmpleado());
			}
		});
	}

	/**
	 * Ordenar pagos programados.
	 *
	 * @param pagosProgramadosList the pagos programados list
	 */
	private void ordenarPagosProgramados(List<PagosProgramadosEntity> pagosProgramadosList) {
		Collections.sort(pagosProgramadosList, new Comparator<PagosProgramadosEntity>() {
			@Override
			public int compare(PagosProgramadosEntity pagosProgramados1, PagosProgramadosEntity pagosProgramados2) {
				// Si el empleado viene null desde la base, lo mostramos como
				// vacio. No deberia ocurrir.
				if (pagosProgramados1.getEmpleado() == null) {
					pagosProgramados1.setEmpleado("");
				}
				if (pagosProgramados2.getEmpleado() == null) {
					pagosProgramados2.setEmpleado("");
				}
				return pagosProgramados1.getEmpleado().compareTo(pagosProgramados2.getEmpleado());
			}
		});

	}

	/**
	 * Generar XML request.
	 *
	 * @param clienteIngresado the cliente ingresado
	 * @return the XML request
	 */
	private XMLRequestEntity generarXMLRequest(Cliente clienteIngresado) {
		XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
		generateConfig(config);

		XMLRequestEntity.META meta = new XMLRequestEntity.META();
		generateMeta(meta, "CONSULTA_TX_AG", "100", "0", BLANK_SPACES, "I");

		generatePartOfRequest(clienteIngresado, meta);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();
		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		parametros.setNUPGuardado(clienteIngresado.getNup());
		parametros.setNombreGuardado("PAGHABHON_");
		Calendar today = Calendar.getInstance();
		parametros.setFechaEjecucionDesde(ISBANStringUtils.formatearFecha(today.getTime(), "yyyyMMdd"));

		Calendar fechaEjecucionHasta = Calendar.getInstance();
		fechaEjecucionHasta.add(Calendar.YEAR, 1);
		parametros.setFechaEjecucionHasta(
		        ISBANStringUtils.formatearFecha(fechaEjecucionHasta.getTime(), "yyyyMMdd").toString());
		datosentrada.setParametros(parametros);

		XMLRequestEntity xmlRequest = new XMLRequestEntity();
		xmlRequest.setCONFIG(config);
		xmlRequest.setMETA(meta);
		xmlRequest.setDATOSENTRADA(datosentrada);
		return xmlRequest;
	}

	/**
	 * Generar XML request eliminar empleado.
	 *
	 * @param clienteIngresado        the cliente ingresado
	 * @param pagoHaberesEliminarView the pago haberes eliminar view
	 * @return the XML request
	 */
	private XMLRequestEntity generarXMLRequestEliminarEmpleado(Cliente clienteIngresado,
	        PagoHaberesEliminarView pagoHaberesEliminarView) {
		XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
		generateConfig(config);

		XMLRequestEntity.META meta = new XMLRequestEntity.META();
		generateMeta(meta, "CANCEL_TOTAL", "100", "0", BLANK_SPACES, "I");
		meta.setIdTransaccion(pagoHaberesEliminarView.getIdTransaccion());

		generatePartOfRequest(clienteIngresado, meta);

		XMLRequestEntity xmlRequest = new XMLRequestEntity();
		xmlRequest.setCONFIG(config);
		xmlRequest.setMETA(meta);
		return xmlRequest;
	}

	/**
	 * Generate part of request.
	 *
	 * @param clienteIngresado the cliente ingresado
	 * @param meta             the meta
	 */
	private void generatePartOfRequest(Cliente clienteIngresado, XMLRequestEntity.META meta) {
		XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
		generateCliente(cliente, clienteIngresado);
		meta.setCliente(cliente);

		XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
		generateUsuario(clienteIngresado, usuario);
		meta.setUsuario(usuario);

		XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
		generateCanal(canal);
		meta.setCanal(canal);

		XMLRequestEntity.META.Subcanal subcanal = new XMLRequestEntity.META.Subcanal();
		generateSubCanal(subcanal);
		meta.setSubcanal(subcanal);
	}

	/**
	 * Generate cliente.
	 *
	 * @param cliente          the cliente
	 * @param clienteIngresado the cliente ingresado
	 */
	private void generateCliente(XMLRequestEntity.META.Cliente cliente, Cliente clienteIngresado) {
		cliente.setTipoPersona("I");
		cliente.setTipoId(clienteIngresado.getTipoDocumento());
		cliente.setIdCliente(clienteIngresado.getDni());
		cliente.setFechaNac(clienteIngresado.getFechaNacimiento());
		cliente.setNUP(clienteIngresado.getNup());
	}

	/**
	 * Generar XML request agregar empleado.
	 *
	 * @param clienteIngresado  the cliente ingresado
	 * @param pagoInformadoView the pago informado view
	 * @return the XML request
	 * @throws CuentaInvalidaException the cuenta invalida exception
	 */
	private XMLRequestEntity generarXMLRequestAgregarEmpleado(Cliente clienteIngresado,
	        PagoInformadoView pagoInformadoView) throws CuentaInvalidaException {
		XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
		generateConfig(config);

		Calendar today = Calendar.getInstance();
		Calendar ejecucion = Calendar.getInstance();
		XMLRequestEntity.META meta = new XMLRequestEntity.META();
		generateMeta(meta, "PAGHABHON_", "100", "0", BLANK_SPACES, "A");
		meta.setTipoAgendamiento("I");
		meta.setLogueoAgendaHistorica("S");
		ejecucion.add(Calendar.MONTH, 1);
		meta.setFechaEjecucion(ISBANStringUtils.formatearFecha(ejecucion.getTime(), FORMATO_FECHA_IATX));
		generatePartOfRequest(clienteIngresado, meta);

		XMLRequestEntity.META.Recurrencias recurrencias = new XMLRequestEntity.META.Recurrencias();
		recurrencias.setFechaBaseRecurrencia(ISBANStringUtils.formatearFecha(today.getTime(), FORMATO_FECHA_IATX));
		recurrencias.setFrecRecurrencia("M1" + String.format("%02d", today.get(Calendar.DATE)) + "01");
		recurrencias.setMaxRecurrencia("0");
		recurrencias.setTipoRecurrencia("I");

		meta.setRecurrencias(recurrencias);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosSueldos datosSueldos = new XMLRequestEntity.DATOSADICIONALES.DatosSueldos();

		datosSueldos.setDescripcionConcepto(pagoInformadoView.getConcepto());
		datosSueldos.setDestinatario(pagoInformadoView.getAliasDestino());
		datosSueldos.setNumeroCUIL(pagoInformadoView.getCuil());
		datosSueldos.setTipoCUIL(pagoInformadoView.getTipoCuil());
		datosSueldos.setTipoPago(pagoInformadoView.getTipoPago());

		datosAdicionales.setDatosSueldos(datosSueldos);

		XMLRequestEntity.DATOSENTRADA datosEntrada = new XMLRequestEntity.DATOSENTRADA();
		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();

		boolean isSueldo = "S".equals(pagoInformadoView.getTipoPago());

		parametros.setTipoCtaDebito(ISBANStringUtils.tipoCtaIatx(pagoInformadoView.getTipoCuentaOrigen()));
		parametros.setSucCtaDebito(ISBANStringUtils.extraerSucursal(pagoInformadoView.getCuentaOrigen()));
		parametros.setNroCtaDebito(ISBANStringUtils.extraerCuenta(pagoInformadoView.getCuentaOrigen()));
		parametros.setCodigoDebito(
		        getCodDeb(corregirTipoCuentaOrigenParaLlamado(pagoInformadoView.getTipoCuentaOrigen()), isSueldo));
		parametros.setSubcodigoDebito(
		        getSubCodDeb(corregirTipoCuentaOrigenParaLlamado(pagoInformadoView.getTipoCuentaOrigen()), isSueldo));
		parametros.setTipoCtaCredito(ISBANStringUtils.tipoCtaIatx(pagoInformadoView.getTipoCuentaDestino()));
		parametros.setSucCtaCredito(ISBANStringUtils.extraerSucursal(pagoInformadoView.getCuentaDestino()));
		parametros.setNroCtaCredito(ISBANStringUtils.extraerCuenta(pagoInformadoView.getCuentaDestino()));
		parametros.setCodigoCredito(
		        getCodCred(corregirTipoCuentaOrigenParaLlamado(pagoInformadoView.getTipoCuentaDestino()), isSueldo));
		parametros.setSubcodigoCredito(
		        getSubCodCred(corregirTipoCuentaOrigenParaLlamado(pagoInformadoView.getTipoCuentaDestino()), isSueldo));
		parametros.setComprobanteCredito(pagoInformadoView.getNup());
		parametros.setImporte(pagoInformadoView.getImporte());
		parametros.setLimiteSobregiro("00000000000000");
		datosEntrada.setParametros(parametros);

		XMLRequestEntity consultaPerfilInversor = new XMLRequestEntity();
		consultaPerfilInversor.setCONFIG(config);
		consultaPerfilInversor.setMETA(meta);
		consultaPerfilInversor.setDATOSENTRADA(datosEntrada);
		consultaPerfilInversor.setDatosAdicionales(datosAdicionales);
		return consultaPerfilInversor;
	}

	/**
	 * Corregir tipo cuenta origen para llamado.
	 *
	 * @param tipoCuentaOrigen the tipo cuenta origen
	 * @return the string
	 */
	private String corregirTipoCuentaOrigenParaLlamado(String tipoCuentaOrigen) {
		if ("CUP".equals(tipoCuentaOrigen)) {
			return "CU";
		}
		return tipoCuentaOrigen;
	}

	/**
	 * Generar XML request eliminar pago programado.
	 *
	 * @param clienteIngresado        the cliente ingresado
	 * @param pagoHaberesEliminarView the pago haberes eliminar view
	 * @return the XML request
	 */
	private XMLRequestEntity generarXMLRequestEliminarPagoProgramado(Cliente clienteIngresado,
	        PagoHaberesEliminarView pagoHaberesEliminarView) {
		XMLRequestEntity consultaPerfilInveror = generarXMLRequestEliminarEmpleado(clienteIngresado,
		        pagoHaberesEliminarView);
		consultaPerfilInveror.getMETA().setNombre("CANCEL_OCURRENCIA");
		consultaPerfilInveror.getMETA().setNroRecurrencia(pagoHaberesEliminarView.getNroRecurrencia());
		return consultaPerfilInveror;
	}

	/**
	 * Generate sub canal.
	 *
	 * @param subcanal the subcanal
	 */
	private void generateSubCanal(XMLRequestEntity.META.Subcanal subcanal) {
		subcanal.setSubcanalTipo("99");
		subcanal.setSubcanalId("0001");
	}

	/**
	 * Generate canal.
	 *
	 * @param canal the canal
	 */
	private void generateCanal(XMLRequestEntity.META.Canal canal) {
		canal.setCanalTipo("04");
		canal.setCanalId("HTML");
		canal.setCanalVersion("000");
	}

	/**
	 * Generate usuario.
	 *
	 * @param clienteIngresado the cliente ingresado
	 * @param usuario          the usuario
	 */
	private void generateUsuario(Cliente clienteIngresado, XMLRequestEntity.META.Usuario usuario) {
		usuario.setUsuarioTipo("03");
		usuario.setUsuarioId(clienteIngresado.getUsuarioRacf());
		usuario.setUsuarioPwd(clienteIngresado.getPasswordRacf());
		usuario.setUsuarioAtrib("  ");
	}

	/**
	 * Generate meta.
	 *
	 * @param meta          the meta
	 * @param nombre        the nombre
	 * @param version       the version
	 * @param indAuten      the ind auten
	 * @param idSesionCnt   the id sesion cnt
	 * @param modoEjecucion the modo ejecucion
	 */
	private void generateMeta(XMLRequestEntity.META meta, String nombre, String version, String indAuten,
	        String idSesionCnt, String modoEjecucion) {
		meta.setNombre(nombre);
		meta.setVersion(version);
		meta.setIndAuten(indAuten);
		meta.setIdSesionCnt(idSesionCnt);
		meta.setModoEjecucion(modoEjecucion);
	}

	/**
	 * Generate config.
	 *
	 * @param config the config
	 */
	private void generateConfig(XMLRequestEntity.CONFIG config) {
		config.setVersionXML("2.0.0");
		config.setEcoDatosEntrada("N");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * isClienteAdheridoPagoHaberes(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente)
	 */
	@Override
	public Respuesta<Boolean> isClienteAdheridoPagoHaberes(Cliente cliente) {
		Respuesta<Boolean> clienteRespuesta = new Respuesta<Boolean>();
		try {
			TransferenciaModtrfe transferenciaModtrfe = transferenciaDAO.ejecutarModTrfe(cliente);
			if (isClienteAdheridoPagoHaberes(transferenciaModtrfe)) {
				clienteRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				clienteRespuesta = respuestaFactory.crearRespuestaError(null, TipoError.USUARIO_NO_AFILIADO,
				        CodigoMensajeConstantes.CODIGO_USUARIO_NO_AFILIADO);
			}
		} catch (DAOException ex) {
			LOGGER.error("Ha ocurrido un error al validar si el cliente esta habilitado para transferir", ex);
			clienteRespuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
		}
		return clienteRespuesta;
	}

	private boolean isClienteAdheridoPagoHaberes(TransferenciaModtrfe transferenciaModtrfe) {
		return ("01").equals(transferenciaModtrfe.getPosicionRespuesta())
		        || ("02").equals(transferenciaModtrfe.getPosicionRespuesta())
		        || ("03").equals(transferenciaModtrfe.getPosicionRespuesta());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * eliminarPagoProgramado7x24(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesEliminarView)
	 */
	@Override
	public Respuesta<FeedbackMensajeView> eliminarPagoProgramado7x24(Cliente clienteIngresado,
	        PagoHaberesEliminarView pagoHaberesEliminarView) {
		Respuesta<FeedbackMensajeView> respuesta = new Respuesta<FeedbackMensajeView>();
		XMLRequestEntity xmlRequest = new XMLRequestEntity();
		try {
			xmlRequest = generarXMLRequestEliminarPagoProgramado(clienteIngresado, pagoHaberesEliminarView);
			XMLResponseEntity xmlResponseError = sietePorVenticuatroV1DAO.ejecutar(xmlRequest);
			DATOSRESULTADO datosResultado = xmlResponseError.getDATOSRESULTADO();
			if ("0".equals(datosResultado.getSeveridad())) {
				// Respuesta OK
				Mensaje mensaje = mensajeBo.obtenerMensajePorCodigo(
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_PAGO_PROGRAMADO_OK);
				FeedbackMensajeView feedbackMensajeView = new FeedbackMensajeView();
				feedbackMensajeView.setMensaje(mensaje.getMensaje());
				respuesta.setRespuesta(feedbackMensajeView);
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				// Respuesta ERROR del servicio
				if (sesionParametros.getContador().permiteReintentar()) {
					// MENSAJE ERROR QUE PERMITE REINTENTAR
					respuesta = respuestaFactory.crearRespuestaError(null,
					        TipoError.ERROR_ELIMINAR_AGENDAMIENTO_CON_REINTENTO,
					        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
				} else {
					sesionParametros.setContador(null);
					// MENSAJE ERROR QUE NO PERMITE REINTENTAR
					respuesta = respuestaFactory.crearRespuestaError(null,
					        TipoError.ERROR_ELIMINAR_AGENDAMIENTO_SIN_REINTENTO,
					        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
				}
			}
			return respuesta;
		} catch (DAOException e) {
			// Respuesta ERROR GENERICA
			LOGGER.error("Ha ocurrido un error al eliminar el pago programado.", e);
			if (sesionParametros.getContador().permiteReintentar()) {
				// MENSAJE ERROR QUE PERMITE REINTENTAR
				respuesta = respuestaFactory.crearRespuestaError(null,
				        TipoError.ERROR_ELIMINAR_AGENDAMIENTO_CON_REINTENTO,
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
			} else {
				sesionParametros.setContador(null);
				// MENSAJE ERROR QUE NO PERMITE REINTENTAR
				respuesta = respuestaFactory.crearRespuestaError(null,
				        TipoError.ERROR_ELIMINAR_AGENDAMIENTO_SIN_REINTENTO,
				        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ELIMINAR_ERROR);
			}
			return respuesta;
		}
	}

	/**
	 * Gets the cod deb.
	 *
	 * @param tipoCta  the tipo cta
	 * @param isSueldo the is sueldo
	 * @return the cod deb
	 */
	public String getCodDeb(String tipoCta, boolean isSueldo) {
		tipoCta = "CUP".equals(tipoCta) ? "CU" : tipoCta;
		return propertyMap.get("PAGHABHON.COD.DEB." + (isSueldo ? "HAB." : "HON.") + tipoCta);
	}

	/**
	 * Gets the cod cred.
	 *
	 * @param tipoCta  the tipo cta
	 * @param isSueldo the is sueldo
	 * @return the cod cred
	 */
	public String getCodCred(String tipoCta, boolean isSueldo) {
		tipoCta = "CUP".equals(tipoCta) ? "CU" : tipoCta;
		return propertyMap.get("PAGHABHON.COD.CRD." + (isSueldo ? "HAB." : "HON.") + tipoCta);
	}

	/**
	 * Gets the sub cod deb.
	 *
	 * @param tipoCta  the tipo cta
	 * @param isSueldo the is sueldo
	 * @return the sub cod deb
	 */
	public String getSubCodDeb(String tipoCta, boolean isSueldo) {
		tipoCta = "CUP".equals(tipoCta) ? "CU" : tipoCta;
		return propertyMap.get("PAGHABHON.SUB.DEB." + (isSueldo ? "HAB." : "HON.") + tipoCta);
	}

	/**
	 * Gets the sub cod cred.
	 *
	 * @param tipoCta  the tipo cta
	 * @param isSueldo the is sueldo
	 * @return the sub cod cred
	 */
	public String getSubCodCred(String tipoCta, boolean isSueldo) {
		tipoCta = "CUP".equals(tipoCta) ? "CU" : tipoCta;
		return propertyMap.get("PAGHABHON.SUB.CRD." + (isSueldo ? "HAB." : "HON.") + tipoCta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * eliminarPagoProgramado7x24(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesEliminarView)
	 */
	@Override
	public Respuesta<DatosDestinatarioView> validarCBU(Cliente clienteIngresado,
	        ValidacionesPagoPorCBUView validacionesPagoPorCBU) {

		Boolean cbuValido = ISBANStringUtils.validarCBU(validacionesPagoPorCBU.getNumeroCBUDestino());
		if (!cbuValido) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_USUARIO_CBU_INVALIDO,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_USUARIO_CBU_INVALIDO);
		}

		String tarjetaBanelco = obtenerNumeroTarjetaBanelco(clienteIngresado);
		if (tarjetaBanelco == null) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_USUARIO_NO_POSEE_TARJETA_BANELCO,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_USUARIO_SIN_TARJETA_BANELCO);
		}
		validacionesPagoPorCBU.setTarjetaBanelco(tarjetaBanelco);
		validacionesPagoPorCBU.setDireccionIP(sesionCliente.obtenerIpV4SinPuntos());
		ValidacionCuentaOutCBUEntity datosCliente = new ValidacionCuentaOutCBUEntity();
		try {
			datosCliente = pagoHaberesDAO.validarCBU(clienteIngresado, validacionesPagoPorCBU);
		} catch (DestinatarioNoVerificadoException e) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
		} catch (CBUInvalidoDAOException e) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_USUARIO_CBU_INVALIDO,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_USUARIO_CBU_INVALIDO);
		} catch (RecuperarDatosPorCBUDAOException e) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_OBTENER_DATOS_POR_CBU,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_OBTENER_DATOS_POR_CBU);
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
		}

		Respuesta<DatosDestinatarioView> respuesta = new Respuesta<DatosDestinatarioView>();
		armarRespuestaOKValidarCBU(respuesta, datosCliente);

		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * eliminarPagoProgramado7x24(ar.com.santanderrio.obp.servicios.clientes.
	 * entities.Cliente, ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * PagoHaberesEliminarView)
	 */
	@Override
	public Respuesta<ComprobantePagoHaberesCBUEntity> pagoHaberesCBU(Cliente clienteIngresado,
	        DatosDestinatarioView datosDestinatarioView, Cuenta cuentaOrigen) {

		String tarjetaBanelco = obtenerNumeroTarjetaBanelco(clienteIngresado);
		if (tarjetaBanelco == null) {
			// Si el usuario no posee tarjeta banelco, no permito reintentar.
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_USUARIO_NO_POSEE_TARJETA_BANELCO,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_USUARIO_SIN_TARJETA_BANELCO);
		}
		Respuesta<ComprobantePagoHaberesCBUEntity> respuesta = new Respuesta<ComprobantePagoHaberesCBUEntity>();
		try {
			IatxResponse iatxResponse = pagoHaberesDAO.pagoHaberesCBU(clienteIngresado, datosDestinatarioView,
			        cuentaOrigen);
			armarRespuestaPagoHaberesCBU(respuesta, iatxResponse, datosDestinatarioView);
		} catch (SaldoInsuficienteException e) {
			// Si el usuario no posee saldo suficiente para hacer el pago, no
			// permito reintentar
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SALDO_INSUFICIENTE,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_SALDO_INSUFICIENTE_PERMITE_SELECCIONAR);
		} catch (DAOException e) {
			Mensaje mensaje = mensajeBo
			        .obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_PAGAR_CBU);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(MessageFormat.format(mensaje.getMensaje(), datosDestinatarioView.getNombreDestinatario(),
			        "$ " + ISBANStringUtils.formatearSaldo(new BigDecimal(datosDestinatarioView.getImporte())
			                .setScale(2, java.math.RoundingMode.FLOOR))));
			if (sesionParametros.getContador().permiteReintentar()) {
				// TIPO ERROR QUE PERMITE REINTENTAR
				item.setTipoError(TipoError.ERROR_PAGAR_CBU_PERMITE_REINTENTAR.getDescripcion());
			} else {
				sesionParametros.setContador(null);
				// TIPO ERROR QUE NO PERMITE REINTENTAR
				item.setTipoError(TipoError.ERROR_PAGAR_CBU_NO_PERMITE_REINTENTAR.getDescripcion());
			}
			respuesta.add(item);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			return respuesta;
		}

		return respuesta;
	}

	/**
	 * Armar respuesta pago haberes CBU.
	 *
	 * @param respuesta             the respuesta
	 * @param iatxResponse          the iatx response
	 * @param datosDestinatarioView the datos destinatario view
	 */
	private void armarRespuestaPagoHaberesCBU(Respuesta<ComprobantePagoHaberesCBUEntity> respuesta,
	        IatxResponse iatxResponse, DatosDestinatarioView datosDestinatarioView) {
		ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU = new ComprobantePagoHaberesCBUEntity();
		comprobantePagoHaberesCBU.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		try {
			comprobantePagoHaberesCBU.setLegalesSEO(legalDao.obtenerLegal("1005"));
		} catch (DAOException e) {
			LOGGER.error("ERROR al recuperar Legales SEO");
			comprobantePagoHaberesCBU.setLegalesSEO("");
		}
		Mensaje mensaje = mensajeBo.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_PAGO_HABERES_CBU_OK);
		comprobantePagoHaberesCBU.setMensaje(MessageFormat.format(mensaje.getMensaje(),
		        "$ " + ISBANStringUtils.formatearSaldo(
		                new BigDecimal(datosDestinatarioView.getImporte()).setScale(2, java.math.RoundingMode.FLOOR)),
		        datosDestinatarioView.getNombreDestinatario()));
		if (iatxResponse.getData(3) == null || "".equalsIgnoreCase(iatxResponse.getData(3))) {
			comprobantePagoHaberesCBU.setNroDeComprobante("");
		} else {
			comprobantePagoHaberesCBU.setNroDeComprobante(ISBANStringUtils.eliminarCeros(iatxResponse.getData(3)));
		}

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(comprobantePagoHaberesCBU);
	}

	/**
	 * Armar respuesta OK validar CBU.
	 *
	 * @param respuesta    the respuesta
	 * @param datosCliente the datos cliente
	 */
	private void armarRespuestaOKValidarCBU(Respuesta<DatosDestinatarioView> respuesta,
	        ValidacionCuentaOutCBUEntity datosCliente) {

		DatosDestinatarioView datosDestinatarioView = new DatosDestinatarioView();
		datosDestinatarioView
		        .setNombreDestinatario(ISBANStringUtils.convertirStringToCamelcase(datosCliente.getTitular()));
		datosDestinatarioView.setCuilCuitDestinatario(ISBANStringUtils.formatearCuil(datosCliente.getCuit()));
		datosDestinatarioView.setFechaDeEjecucion(FechaUtils.obtenerFechaActual());
		datosDestinatarioView.setBancoDestinatario(datosCliente.getBandes());

		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(datosDestinatarioView);
	}

	/**
	 * Obtener numero tarjeta banelco.
	 *
	 * Aclaracion: En AltaDestinatiarioCBU se utiliza la misma logica pero con
	 * diferente orden de prioridad. Nostros seguimos dicha prioridad segun el
	 * codigo productivo.
	 * 
	 * @param cliente the cliente
	 * @return the string
	 */
	private String obtenerNumeroTarjetaBanelco(Cliente cliente) {
		HashMap<String, Cuenta> cuentasBanelco = new HashMap<String, Cuenta>();
		for (Cuenta cuenta : cliente.getCuentas()) {
			if (cuenta.getTipoCuentaEnum().equals(TipoCuenta.BANELCO)) {
				if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_E)) {
					return cuenta.getNroTarjetaCredito();
				}
				if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_V) && !cuentasBanelco.containsKey(CLASE_CUENTA_V)) {
					cuentasBanelco.put(CLASE_CUENTA_V, cuenta);
				} else if (cuenta.getClaseCuenta().equals(CLASE_CUENTA_B)
				        && !cuentasBanelco.containsKey(CLASE_CUENTA_B)) {
					cuentasBanelco.put(CLASE_CUENTA_B, cuenta);
				} else if (!cuentasBanelco.containsKey(CLASE_CUENTA_OTROS)) {
					cuentasBanelco.put(CLASE_CUENTA_OTROS, cuenta);
				}
			}
		}
		Cuenta cuenta = obtenerCuentaBanelcoPorPrioridad(cuentasBanelco);
		if (cuenta == null) {
			return null;
		} else {
			return cuenta.getNroTarjetaCredito();
		}
	}

	/**
	 * Obtener cuenta banelco por prioridad.
	 *
	 * @param cuentasBanelco the cuentas banelco
	 * @return the cuenta
	 */
	private Cuenta obtenerCuentaBanelcoPorPrioridad(HashMap<String, Cuenta> cuentasBanelco) {
		if (cuentasBanelco.containsKey(CLASE_CUENTA_V)) {
			return cuentasBanelco.get(CLASE_CUENTA_V);
		}
		if (cuentasBanelco.containsKey(CLASE_CUENTA_B)) {
			return cuentasBanelco.get(CLASE_CUENTA_B);
		}
		if (cuentasBanelco.containsKey(CLASE_CUENTA_OTROS)) {
			return cuentasBanelco.get(CLASE_CUENTA_OTROS);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * generarComprobanteAdhesion(ar.com.santanderrio.obp.servicios.pagohaberes.
	 * entities.ComprobanteAdhesionEmpleado)
	 */
	@Override
	public Respuesta<Reporte> generarComprobanteAdhesion(
	        ComprobanteAdhesionEmpleadoEntity comprobanteAdhesionEmpleado) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = pagoHaberesDAO.generarComprobanteAdhesion(comprobanteAdhesionEmpleado);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * generarComprobantePagoPorCBU(ar.com.santanderrio.obp.servicios.
	 * pagohaberes.entities.ComprobantePagoHaberesCBU)
	 */
	@Override
	public Respuesta<Reporte> generarComprobantePagoPorCBU(ComprobantePagoHaberesCBUEntity comprobantePagoHaberesCBU) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = pagoHaberesDAO.generarComprobantePagoPorCBU(comprobantePagoHaberesCBU);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * validarImporte(ar.com.santanderrio.obp.servicios.clientes.entities. Cliente,
	 * ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * validacionesPagoPorCBU)
	 */
	@Override
	public Respuesta<DatosDestinatarioView> validarImporte(Cliente cliente, String tipoCuentaOrigen,
	        String nroCuentaOrigen, String importe) {
		Respuesta<DatosDestinatarioView> respuesta = new Respuesta<DatosDestinatarioView>();
		if ("CU".equals(tipoCuentaOrigen)) {
			tipoCuentaOrigen = "CUP";
		}
		String limiteAcuerdoCtaCtePesos = "0.00";
		String disponible = "0";
		BigDecimal importeDisponible = new BigDecimal(disponible);
		List<Cuenta> cuentas = cliente.getCuentas();
		Iterator<Cuenta> itCuentas = cuentas.iterator();
		while (itCuentas.hasNext()) {
			Cuenta cuenta = itCuentas.next();
			if (cuenta.getTipoCuentaEnum().getAbreviatura().equals(tipoCuentaOrigen)
			        && ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto())
			                .equals(ISBANStringUtils.eliminarCeros(ISBANStringUtils.extraerCuenta(nroCuentaOrigen)))) {

				// Se consulta CNSCTADATO para verificar el limite acuerdo y el
				// disponible
				Respuesta<DetalleCuentaEntity> respuestaDetalleCuenta = detalleCuentaDAO.obtenerDetalleCuenta(cuenta);
				if (EstadoRespuesta.OK.equals(respuestaDetalleCuenta.getEstadoRespuesta())) {
					DetalleCuentaEntity detalleCuenta = respuestaDetalleCuenta.getRespuesta();
					limiteAcuerdoCtaCtePesos = detalleCuenta.getLimiteAcuerdoCtaCtePesos();
					disponible = detalleCuenta.getDisponibleCuentaPesos();
					importeDisponible = new BigDecimal(disponible);
				} else {
					// Se retorna error generico porque no recuperar datos del
					// CNSCTADATO.
					return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
					        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
				}

				BigDecimal importeCuenta = new BigDecimal("0");
				if ("CUP".equals(tipoCuentaOrigen) && cuenta.getSaldoCUPesos() != "") {
					importeCuenta = new BigDecimal(cuenta.getSaldoCUPesos());
				} else {
					importeCuenta = new BigDecimal(cuenta.getSaldoCuenta());
				}
				BigDecimal importeADebitar = new BigDecimal(importe);
				// Las validaciones son:
				// El importe de la cuenta debe ser mayor o igual al importe a
				// debitar. De no ser asi
				// El limite de acuerdo Cta Cte en Pesos debe ser distinto 0.00.
				// De ser asi
				// El importe Disponible debe ser mayor o igual al importe a
				// debitar. De no ser asi
				// El importe Disponible sumado al importe de la cuenta debe ser
				// mayor o igual al importe a debitar.
				if ("-1".equals(String.valueOf(importeCuenta.compareTo(importeADebitar)))) {
					if ("0.00".equals(limiteAcuerdoCtaCtePesos) || ("-1"
					        .equals(String.valueOf(importeDisponible.compareTo(importeADebitar)))
					        && "-1".equals(
					                String.valueOf(importeCuenta.add(importeDisponible).compareTo(importeADebitar))))) {
						return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_SALDO_INSUFICIENTE,
						        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_SALDO_INSUFICIENTE_PERMITE_SELECCIONAR);
					}
				}
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);

				return respuesta;
			}
		}
		// Se retorna error generico porque no pude encontrar la cuenta
		// seleccionada.
		respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
		        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * validacionesPagoSimpleMultiple(java.util.Date, java.util.Date,
	 * ar.com.santanderrio.obp.servicios.pagohaberes.web.view.
	 * ValidarPagoSimpleMultipleView)
	 */
	@Override
	public Respuesta<ValidacionesRespuestaEntity> validacionesPagoSimpleMultiple(Date date, Date today,
	        ValidarPagoSimpleMultipleView validarPagoSimpleMultipleView) {
		Respuesta<ValidacionesRespuestaEntity> respuesta = new Respuesta<ValidacionesRespuestaEntity>();
		ValidacionesRespuestaEntity validacionesRespuesta = new ValidacionesRespuestaEntity();

		if (date.before(today)) {
			LOGGER.info("Pago de Haberes - ERROR GENERICO. La fecha ingresada es menor a la actual.");
			respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
			        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
			return respuesta;
		} else if (date.after(today)) {
			LOGGER.info(
			        "Pago de Haberes - La fecha ingresada es superior a la actual. Intentando programar el pago...");
			// if (validateYear(validarPagoSimpleMultipleView.getFecha(), FORMATTER_FECHA))
			if (validateYear(date, today)) {
				Respuesta<Void> respuestaContrato = buscarContratoAceptado();
				if (EstadoRespuesta.ERROR.equals(respuestaContrato.getEstadoRespuesta())) {
					if (TipoError.ERROR_PAGO_HABERES
					        .equals(respuestaContrato.getItemsMensajeRespuesta().get(0).getTipoError())) {
						grabarEstadisticaValidacionesSimpleMultiple(
						        validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
						        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
						return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
						        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
					} else {
						Respuesta<Boolean> contratoAceptado = aceptacionContratoPagoProgramado();
						if (EstadoRespuesta.OK.equals(contratoAceptado)) {
							respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
							// false = modo ejecucion A porque se va a programar
							validacionesRespuesta.setModoEjecucion(false);
							validacionesRespuesta.setFecha(validarPagoSimpleMultipleView.getFecha());
							respuesta.setRespuesta(validacionesRespuesta);
							grabarEstadisticaValidacionesSimpleMultiple(
							        validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
							        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
							return respuesta;
						} else {
							grabarEstadisticaValidacionesSimpleMultiple(
							        validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
							        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
							return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
							        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
						}
					}
				}
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				// false = modo ejecucion A porque se va a programar
				validacionesRespuesta.setModoEjecucion(false);
				validacionesRespuesta.setFecha(validarPagoSimpleMultipleView.getFecha());
				respuesta.setRespuesta(validacionesRespuesta);
				grabarEstadisticaValidacionesSimpleMultiple(validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
				        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				return respuesta;
			} else {
				LOGGER.info(
				        "Pago de Haberes - El lapso de tiempo es mayor es superior a un año. No se puede programar el pago");
				grabarEstadisticaValidacionesSimpleMultiple(validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
				        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
				        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
			}
		} else {
			String horaDesde = horaDesdePagar.trim();
			String horaHasta = horaHastaPagar.trim();
			DateTimeFormatter dtf = DateTimeFormat.forPattern(FORMATO_HORA);
			DateTime horarioComparacionInicio = dtf.parseLocalTime(horaDesde).toDateTimeToday();
			DateTime horarioComparacionFinal = dtf.parseLocalTime(horaHasta).toDateTimeToday();
			DateTime horarioActual = new DateTime();
			Interval intervaloHorarioPermitido = new Interval(horarioComparacionInicio, horarioComparacionFinal);

			if (intervaloHorarioPermitido.contains(horarioActual)) {
				LOGGER.info(
				        "Pago de Haberes - El horario se encuentra dentro del rango especificado en Hbconfig.properties. Se puede realizar el pago");
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
				// true = modo ejecucion IA
				validacionesRespuesta.setModoEjecucion(true);
				validacionesRespuesta.setFecha(validarPagoSimpleMultipleView.getFecha());
				respuesta.setRespuesta(validacionesRespuesta);
			} else {
				LOGGER.info("WARNING. El horario se encuentra fuera del rango especificado en Hbconfig.properties.");

				Respuesta<Void> respuestaContrato = buscarContratoAceptado();
				if (EstadoRespuesta.ERROR.equals(respuestaContrato.getEstadoRespuesta())) {
					if (TipoError.ERROR_PAGO_HABERES
					        .equals(respuestaContrato.getItemsMensajeRespuesta().get(0).getTipoError())) {
						grabarEstadisticaValidacionesSimpleMultiple(
						        validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
						        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
						return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
						        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
					} else {
						Respuesta<Boolean> contratoAceptado = aceptacionContratoPagoProgramado();
						if (EstadoRespuesta.ERROR.equals(contratoAceptado.getEstadoRespuesta())) {
							grabarEstadisticaValidacionesSimpleMultiple(
							        validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
							        EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
							return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
							        CodigoMensajeConstantes.CODIGO_ERROR_GENERICO_PAGO_HABERES);
						}
					}
				}

				Integer.parseInt(horaDesde.substring(0, horaDesde.indexOf(":")));
				if (horarioActual.getHourOfDay() < horarioComparacionInicio.getHourOfDay()) {
					LOGGER.info(
					        "WARNING. La hora es menor a la especificada. El pago sera ejecutado luego de iniciarse el horario de operacion bancaria");
					respuesta = respuestaFactory.crearRespuestaWarning(null, TipoError.ERROR_PAGO_HABERES_FUERA_HORARIO,
					        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_HORA_INFERIOR);

				} else {
					LOGGER.info(
					        "WARNING. La hora es mayor a la especificada. Los pagos de sueldos seran ejecutados el proximo dia.");
					date = DateUtils.addDays(date, 1);

					validacionesRespuesta.setFecha(ISBANStringUtils.formatearFecha(date));
					respuesta = respuestaFactory.crearRespuestaWarning(null, TipoError.ERROR_PAGO_HABERES_FUERA_HORARIO,
					        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_HORA_SUPERIOR);
				}
				// false = modo ejecucion A
				validacionesRespuesta.setModoEjecucion(false);
				respuesta.setRespuesta(validacionesRespuesta);
				grabarEstadisticaValidacionesSimpleMultiple(validarPagoSimpleMultipleView.getPagoHaberesEmpleadosView(),
				        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			}
		}
		return respuesta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * realizarPagoSimple(ar.com.santanderrio.obp.servicios.clientes.entities.
	 * Cliente,
	 * ar.com.santanderrio.obp.servicios.pagohaberes.web.view.PagoInformadoView,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> realizarPagoSimple(Cliente cliente,
	        PagoInformadoView pagoInformadoView, String modoEjecucion, String fecha) {
		Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity> respuesta = new Respuesta<DatosEmpleadoPagoHaberesSimpleMultipleEntity>();
		XMLRequestEntity pagoHaber = new XMLRequestEntity();
		try {
			pagoHaber = generarXMLRequestPagarHaberes(cliente, pagoInformadoView, modoEjecucion, fecha);
			XMLResponseEntity xmlResponse = sietePorVenticuatroV1DAO.ejecutar(pagoHaber);
			DATOSRESULTADO datosResultado = xmlResponse.getDATOSRESULTADO();
			if ("0".equals(datosResultado.getSeveridad())) {
				respuesta.setRespuesta(armarComprobantePagoSimpleMultiple(datosResultado, pagoInformadoView, fecha));
				respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			} else {
				DatosEmpleadoPagoHaberesSimpleMultipleEntity comprobanteEmpleadoERROR = new DatosEmpleadoPagoHaberesSimpleMultipleEntity();
				comprobanteEmpleadoERROR.setDescripcionEmpleado(pagoInformadoView.getDescripcionEmpleado());
				comprobanteEmpleadoERROR.setImporte(
						PagoHaberesUtils.formateadorImporte(new BigDecimal(pagoInformadoView.getImporte())));
				
				if (COD_RET_CUENTA_CERRADA.equals(datosResultado.getCodRet())) {
					LOGGER.info("Mensaje Error CUENTA CERRADA: " + datosResultado.getDescripcionStatusResultado().get(0).getTextoMensaje());
					comprobanteEmpleadoERROR.setMensaje(datosResultado.getDescripcionStatusResultado().get(0).getTextoMensaje());
					comprobanteEmpleadoERROR.setConcepto(datosResultado.getCodRet());
				} else {
					comprobanteEmpleadoERROR.setMensaje("No se pudo realizar la operación");
				}
				respuesta.setRespuesta(comprobanteEmpleadoERROR);
				respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
			}
		} catch (CuentaInvalidaException e) {
			LOGGER.error("Ocurrió un error al validar la cuenta origen o la cuenta destino.", e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_PAGAR_HABER);
			return respuesta;
		} catch (DAOException e) {
			LOGGER.error("Ocurrió un error con el servicio.", e);
			respuesta = respuestaFactory.crearRespuestaError(null, TipoError.ERROR_GENERICO,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_PAGAR_HABER);
			return respuesta;
		}

		return respuesta;
	}

	/**
	 * Armar comprobante pago simple multiple.
	 *
	 * @param datosResultado    the datos resultado
	 * @param pagoInformadoView the pago informado view
	 * @param fecha             the fecha
	 * @return the datos empleado pago haberes simple multiple entity
	 */
	private DatosEmpleadoPagoHaberesSimpleMultipleEntity armarComprobantePagoSimpleMultiple(
	        DATOSRESULTADO datosResultado, PagoInformadoView pagoInformadoView, String fecha) {
		DatosEmpleadoPagoHaberesSimpleMultipleEntity datosEmpleados = new DatosEmpleadoPagoHaberesSimpleMultipleEntity();

		datosEmpleados.setEsPagoOk(true);
		datosEmpleados.setDescripcionEmpleado(pagoInformadoView.getDescripcionEmpleado());
		datosEmpleados.setImporte(PagoHaberesUtils.formateadorImporte(new BigDecimal(pagoInformadoView.getImporte())));
		datosEmpleados.setCuentaOrigen(pagoInformadoView.getCuentaOrigen());
		datosEmpleados.setCuentaDestino(pagoInformadoView.getCuentaDestino());
		datosEmpleados.setTipoCuentaOrigen(pagoInformadoView.getTipoCuentaOrigen());
		datosEmpleados.setTipoCuentaDestino(pagoInformadoView.getTipoCuentaDestino());
		datosEmpleados.setCuil(ISBANStringUtils.formatearCuil(pagoInformadoView.getCuil()));
		datosEmpleados.setFecha(fecha);
		datosEmpleados.setTipoPago(pagoInformadoView.getTipoPago());
		datosEmpleados.setConcepto(pagoInformadoView.getConcepto());
		if (datosResultado.getIdTransaccion() == null || "".equalsIgnoreCase(datosResultado.getIdTransaccion())) {
			datosEmpleados.setComprobante("");
		} else {
			datosEmpleados.setComprobante(ISBANStringUtils.eliminarCeros(datosResultado.getIdTransaccion()));
		}
		datosEmpleados.setMensaje("");
		datosEmpleados.setReintentar(false);

		return datosEmpleados;
	}

	/**
	 * Genera el request XML de pagarhaberes.
	 *
	 * @param clienteIngresado  the cliente ingresado
	 * @param pagoInformadoView the pago informado view
	 * @param modoEjecucion     the modo ejecucion
	 * @param fecha             the fecha
	 * @return the XML request entity
	 * @throws CuentaInvalidaException the cuenta invalida exception
	 */
	private XMLRequestEntity generarXMLRequestPagarHaberes(Cliente clienteIngresado,
	        PagoInformadoView pagoInformadoView, String modoEjecucion, String fecha) throws CuentaInvalidaException {

		CONFIG config = new CONFIG();
		config.setVersionXML("2.0.0");
		config.setEcoDatosEntrada("N");

		META meta = new META();
		meta.setNombre("PAGHABHON_");
		meta.setVersion("100");

		ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Cliente clienteXML = new ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity.META.Cliente();
		clienteXML.setTipoPersona("I");
		clienteXML.setTipoId(clienteIngresado.getTipoDocumento());
		clienteXML.setIdCliente(clienteIngresado.getDni());
		clienteXML.setFechaNac(clienteIngresado.getFechaNacimiento());
		clienteXML.setNUP(clienteIngresado.getNup());
		meta.setCliente(clienteXML);

		Usuario usuario = new Usuario();
		usuario.setUsuarioTipo("03");
		usuario.setUsuarioId(clienteIngresado.getUsuarioRacf());
		usuario.setUsuarioPwd(clienteIngresado.getPasswordRacf());
		usuario.setUsuarioAtrib(ISBANStringUtils.repeat(" ", 2));
		meta.setUsuario(usuario);

		meta.setIndAuten("0");
		meta.setIdSesionCnt(ISBANStringUtils.repeat(" ", 8));

		Canal canal = new Canal();
		canal.setCanalId("HTML");
		canal.setCanalTipo("04");
		canal.setCanalVersion("000");
		meta.setCanal(canal);

		Subcanal subcanal = new Subcanal();
		subcanal.setSubcanalTipo("99");
		subcanal.setSubcanalId("0001");
		meta.setSubcanal(subcanal);

		XMLRequestEntity.META.Recurrencias recurrencias = new XMLRequestEntity.META.Recurrencias();
		recurrencias.setTipoRecurrencia(pagoInformadoView.getTipoRecurrencia());
		recurrencias.setFechaBaseRecurrencia(pagoInformadoView.getFechaBaseRecurrencia());
		recurrencias.setFrecRecurrencia(pagoInformadoView.getFrecRecurrencia());
		recurrencias.setMaxRecurrencia(pagoInformadoView.getMaxRecurrencia());
		meta.setRecurrencias(recurrencias);

		meta.setNroRecurrencia(pagoInformadoView.getNroRecurrencia());
		meta.setIdTransaccion(pagoInformadoView.getIdTransaccion());
		meta.setModoEjecucion(modoEjecucion);
		meta.setTipoAgendamiento("E");
		Date date = ISBANStringUtils.formatearFecha(fecha, FORMATTER_FECHA);
		meta.setFechaEjecucion(ISBANStringUtils.formatearFechaIATX(date));
		meta.setLogueoAgendaHistorica("S");

		XMLRequestEntity.DATOSENTRADA datosEntrada = new XMLRequestEntity.DATOSENTRADA();
		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		boolean isSueldo = "Sueldo".equals(pagoInformadoView.getTipoPago());
		parametros.setTipoCtaDebito(ISBANStringUtils.tipoCtaIatx(pagoInformadoView.getAbreviaturaOrigen()));
		parametros.setSucCtaDebito(ISBANStringUtils.extraerSucursal(pagoInformadoView.getCuentaOrigen()));
		parametros.setNroCtaDebito(ISBANStringUtils.extraerCuenta(pagoInformadoView.getCuentaOrigen()));
		parametros.setCodigoDebito(getCodDeb(pagoInformadoView.getAbreviaturaOrigen(), isSueldo));
		parametros.setSubcodigoDebito(getSubCodDeb(pagoInformadoView.getAbreviaturaOrigen(), isSueldo));
		parametros.setTipoCtaCredito(ISBANStringUtils.tipoCtaIatx(pagoInformadoView.getAbreviaturaDestino()));
		parametros.setSucCtaCredito(ISBANStringUtils.extraerSucursal(pagoInformadoView.getCuentaDestino()));
		parametros.setNroCtaCredito(ISBANStringUtils.extraerCuenta(pagoInformadoView.getCuentaDestino()));
		parametros.setCodigoCredito(getCodCred(pagoInformadoView.getAbreviaturaDestino(), isSueldo));
		parametros.setSubcodigoCredito(getSubCodCred(pagoInformadoView.getAbreviaturaDestino(), isSueldo));
		parametros.setComprobanteCredito(pagoInformadoView.getNup());
		parametros.setImporte(String.valueOf(pagoInformadoView.getImporte()));
		parametros.setLimiteSobregiro("00000000000000");
		datosEntrada.setParametros(parametros);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosSueldos datosSueldos = new XMLRequestEntity.DATOSADICIONALES.DatosSueldos();
		datosSueldos.setDescripcionConcepto(pagoInformadoView.getConcepto());
		datosSueldos.setDestinatario(pagoInformadoView.getDescripcionEmpleado());
		String tipoPago = "Honorario".equals(pagoInformadoView.getTipoPago()) ? "H" : "S";
		datosSueldos.setTipoPago(tipoPago);
		datosSueldos.setNumeroCUIL(pagoInformadoView.getCuil());
		datosSueldos.setTipoCUIL(pagoInformadoView.getTipoCuil());
		datosAdicionales.setDatosSueldos(datosSueldos);

		XMLRequestEntity request = new XMLRequestEntity();
		request.setCONFIG(config);
		request.setMETA(meta);
		request.setDATOSENTRADA(datosEntrada);
		request.setDatosAdicionales(datosAdicionales);

		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * validarImporteCuentas(java.util.List)
	 */
	@Override
	public Respuesta<Boolean> validarImporteCuentas(List<PagoInformadoView> pagoHaberesEmpleadosView) {
		Respuesta<Boolean> respuesta = new Respuesta<Boolean>();
		boolean isFirst = true;
		Map<String, String> mapCuentasImporte = new HashMap<String, String>();
		Map<String, String> mapCuentasEmpleados = new HashMap<String, String>();
		int tagEmpleado = 0;
		for (PagoInformadoView empleado : pagoHaberesEmpleadosView) {
			String tipoCuentaOrigen = TipoCuenta.fromDescripcionConMoneda(empleado.getTipoCuentaOrigen())
			        .getAbreviatura();
			String key = tipoCuentaOrigen + "|" + empleado.getCuentaOrigen();
			String value = empleado.getImporte();
			if (isFirst) {
				mapCuentasImporte.put(key, value);
				isFirst = false;
			} else if (mapCuentasImporte.containsKey(key)) {
				BigDecimal importeActual = new BigDecimal(mapCuentasImporte.get(key));
				BigDecimal sumaTotal = (new BigDecimal(value)).add(importeActual);
				String sumTotal = String.valueOf(sumaTotal);
				mapCuentasImporte.put(key, sumTotal);
			} else {
				mapCuentasImporte.put(key, value);
			}
			mapCuentasEmpleados.put(key, String.valueOf(tagEmpleado));
			tagEmpleado++;
		}

		Respuesta<DatosDestinatarioView> respuestaValidaciones = new Respuesta<DatosDestinatarioView>();
		List<ItemMensajeRespuesta> itemMensajeRespuestaList = new ArrayList<ItemMensajeRespuesta>();
		boolean isError = false;
		for (Map.Entry<String, String> entry : mapCuentasImporte.entrySet()) {
			String tipoCuentaOrigen = entry.getKey().split("\\|")[0];
			String cuentaOrigen = entry.getKey().split("\\|")[1];
			respuestaValidaciones = validarImporte(sesionCliente.getCliente(), tipoCuentaOrigen, cuentaOrigen,
			        entry.getValue());
			if (EstadoRespuesta.ERROR.equals(respuestaValidaciones.getEstadoRespuesta())) {
				isError = true;
				LOGGER.error("Error al validar Importe en Pago de Haberes pago simple-multiple. Cuenta: "
				        + tipoCuentaOrigen + "-" + cuentaOrigen);
				if (TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion()
				        .equals(respuestaValidaciones.getItemsMensajeRespuesta().get(0).getTipoError())) {
					for (Map.Entry<String, String> entryEmpleado : mapCuentasEmpleados.entrySet()) {
						if (entryEmpleado.getKey().equals(entry.getKey())) {
							ItemMensajeRespuesta itemMensajeRespuesta = new ItemMensajeRespuesta();
							itemMensajeRespuesta.setTipoError(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
							itemMensajeRespuesta.setMensaje(mensajeBo
							        .obtenerMensajePorCodigo(
							                CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_SALDO_INSUFICIENTE)
							        .getMensaje());
							itemMensajeRespuesta.setTag(entryEmpleado.getValue());
							itemMensajeRespuestaList.add(itemMensajeRespuesta);
						}
					}
				} else {
					respuesta.setEstadoRespuesta(respuestaValidaciones.getEstadoRespuesta());
					respuesta.setItemMensajeRespuesta(respuestaValidaciones.getItemsMensajeRespuesta());
					return respuesta;
				}
			}
		}

		if (isError) {
			respuesta.setItemMensajeRespuesta(itemMensajeRespuestaList);
			respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
		} else {
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		}

		return respuesta;
	}

	/**
	 * Evalua si la diferencia entre dos fechas es mayo a un año
	 * 
	 * @param fechaProgramada
	 * @param today
	 * @return
	 */
	private boolean validateYear(Date fechaProgramada, Date today) {
		long timeElapsed = Math.abs(fechaProgramada.getTime() - today.getTime());
		long oneYearMiliseconds = 31536000000L;
		if (timeElapsed > oneYearMiliseconds) {
			return false;
		}
		return true;
	}

	/**
	 * Aceptacion contrato pago programado.
	 *
	 * @return the respuesta
	 */
	private Respuesta<Boolean> aceptacionContratoPagoProgramado() {
		Respuesta<String> respuestaAceptacion = contratosBO.confirmarAceptacionContrato(
		        sesionCliente.getCliente().getFechaNacimiento(), sesionCliente.getCliente().getDni(), CampoEnum.OPPROG,
		        SinonimoEnum.NO_REPETIDO);

		if (respuestaAceptacion.getEstadoRespuesta().equals(EstadoRespuesta.OK)
		        && respuestaAceptacion.getRespuesta().equals(OK)) {
			return respuestaFactory.crearRespuestaOk(Boolean.class, Boolean.TRUE);
		}
		return respuestaFactory.crearRespuestaError(Boolean.class, "No se acepto el contrato", "");
	}

	/**
	 * Buscar contrato aceptado.
	 *
	 * @return the respuesta
	 */
	private Respuesta<Void> buscarContratoAceptado() {
		Respuesta<String> respuestaContratoAceptado = new Respuesta<String>();
		respuestaContratoAceptado = contratosBO.buscarContratoAceptado(sesionCliente.getCliente().getFechaNacimiento(),
		        sesionCliente.getCliente().getDni(), CampoEnum.OPPROG, SinonimoEnum.NO_REPETIDO);

		Respuesta<Void> respuesta = new Respuesta<Void>();
		if (respuestaContratoAceptado.getEstadoRespuesta().equals(EstadoRespuesta.OK)
		        && respuestaContratoAceptado.getRespuesta().equals(CONTRATO_ACEPTADO)) {
			LOGGER.info("Aviso que se va a programar el pago");
			respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
			return respuesta;
		} else {
			LOGGER.info("El contrato no esta aceptado por lo que se debe realizar la previa aprobacion");
			return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_PAGO_HABERES_CONTRATO_NO_ACEPTADO,
			        CodigoMensajeConstantes.CODIGO_PAGO_HABERES_ERROR_CONTRATO_NO_ACEPTADO);
		}
	}

	/**
	 * Grabar estadistica validaciones simple multiple.
	 *
	 * @param pagosInformadoView the pagos informado view
	 * @param codigoEstadistica  the codigo estadistica
	 */
	private void grabarEstadisticaValidacionesSimpleMultiple(List<PagoInformadoView> pagosInformadoView,
	        String codigoEstadistica) {

		for (PagoInformadoView pagoInformado : pagosInformadoView) {
			String estadisticaConstasts = null;
			TipoCuenta tipoCuenta = TipoCuenta.fromAbreviatura(pagoInformado.getAbreviaturaOrigen());
			if ("Sueldo".equals(pagoInformado.getTipoPago())) {
				LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Sueldo");
				if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(tipoCuenta)) {
					LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Corriente en Pesos");
					estadisticaConstasts = "10532";
				} else if (TipoCuenta.CAJA_AHORRO_PESOS.equals(tipoCuenta)) {
					LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Caja de Ahorro en Pesos");
					estadisticaConstasts = "10533";
				} else if (TipoCuenta.CUENTA_UNICA.equals(tipoCuenta)) {
					LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Unica");
					estadisticaConstasts = "10534";
				}
			} else {
				LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Honorario");
				if (TipoCuenta.CUENTA_CORRIENTE_PESOS.equals(tipoCuenta)) {
					LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Corriente en Pesos");
					estadisticaConstasts = "10535";
				} else if (TipoCuenta.CAJA_AHORRO_PESOS.equals(tipoCuenta)) {
					LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Caja de Ahorro en Pesos");
					estadisticaConstasts = "10536";
				} else if (TipoCuenta.CUENTA_UNICA.equals(tipoCuenta)) {
					LOGGER.info("Grabando estadistica - Pago de Haberes Simple/Multiple - Cuenta Unica");
					estadisticaConstasts = "10537";
				}
			}
			if (estadisticaConstasts != null) {
				estadisticaManager.add(estadisticaConstasts, codigoEstadistica);
				LOGGER.info("Codigo transaccion estadística: " + estadisticaConstasts + " - Resultado: "
				        + codigoEstadistica);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * obtenerDetalleCuenta(ar.com.santanderrio.obp.servicios.cuentas.entities.
	 * Cuenta)
	 */
	@Override
	public Respuesta<DetalleCuentaEntity> obtenerDetalleCuenta(Cuenta cuenta) {
		return detalleCuentaDAO.obtenerDetalleCuenta(cuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.pagohaberes.bo.PagoHaberesBO#
	 * generarComprobantePagoSimpleMultiple(ar.com.santanderrio.obp.servicios.
	 * pagohaberes.entities.ComprobantePagoHaberesCBU)
	 */
	@Override
	public Respuesta<Reporte> generarComprobantePagoSimpleMultiple(ComprobantePagoHaberesCBUEntity comprobante) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = pagoHaberesDAO.generarComprobantePagoSimple(comprobante);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
		respuesta.setRespuesta(reporte);
		return respuesta;
	}

	@Override
	public Boolean esTipoDeClaveCVU(ValidacionesPagoPorCBUView validacionesPagoPorCBUView) {
		return ISBANStringUtils.validarCVU(validacionesPagoPorCBUView.getNumeroCBUDestino());
	}

	@Override
	public Boolean esCBUSantander(ValidacionesPagoPorCBUView validacionesPagoPorCBUView) {
		return validacionesPagoPorCBUView.getNumeroCBUDestino().startsWith(PREFIJO_CBU_SANTANDER);
	}

}