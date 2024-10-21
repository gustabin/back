/*
 * 
 */
package ar.com.santanderrio.obp.servicios.transferencias.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.constants.SietePorVeintiCuatroConstant;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.entities.SietePorVeintiCuatroServicioEnum;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.dao.SietePorVenticuatroV1DAO;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLRequestEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity;
import ar.com.santanderrio.obp.servicios.sieteporveinticuatro.version1.entities.XMLResponseEntity.DATOSRESULTADO.Registro;
import ar.com.santanderrio.obp.servicios.transferencias.dao.AgendaTransferenciaDAO;
import ar.com.santanderrio.obp.servicios.transferencias.dao.util.AgendaTransferenciaMapper;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ComprobanteCancelTotal;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ComprobanteStopDebit;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaAgendaTransferencias;
import ar.com.santanderrio.obp.servicios.transferencias.entities.ConsultaCancelTotal;
import ar.com.santanderrio.obp.servicios.transferencias.entities.PlazoAcreditacion;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.AgendamientoTransferenciaCreacionRequest;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.AgendamientoTransferenciaModificacionRequest;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.FrecuenciaTransferenciaAgendada;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaCreacionRequest;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaAgendadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaEjecutadaDTO;
import ar.com.santanderrio.obp.servicios.transferencias.entities.agenda.TransferenciaRequest;
import ar.com.santanderrio.obp.servicios.transferencias.web.util.TransferenciaUtil;

/**
 * The Class AgendaTransferenciaDAOImpl.
 *
 * @author B039542 - ignacio_valek@itrsa.com.ar - 10/03/2017
 */
@Component
public class AgendaTransferenciaDAOImpl implements AgendaTransferenciaDAO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgendaTransferenciaDAOImpl.class);

	/** The Constant NUP_LENGTH. */
	private static final int NUP_LENGTH = 8;

	/** The Constant FORMATO_FECHA. */
	private static final String FORMATO_FECHA = "yyyyMMdd";

	/** The Constant TIPO_ERROR. */
	private static final String TIPO_ERROR = "1";

	/** The Constant ERROR_SALDO_INSUFICIENTE_1. */
	private static final int ERROR_SALDO_INSUFICIENTE_1 = 515;

	/** The Constant ERROR_SALDO_INSUFICIENTE_2. */
	private static final int ERROR_SALDO_INSUFICIENTE_2 = 2122;
	
	/** The Constant ERROR_SALDO_INSUFICIENTE_2A. */
	private static final int ERROR_SALDO_INSUFICIENTE_2A = 58;
	
	/** The Constant ERROR_SALDO_INSUFICIENTE_2B. */
	private static final int ERROR_SALDO_INSUFICIENTE_2B = 59;

	/** The Constant CUENTA_180_DIAS_SIN_OPERAR. */
	private static final int CUENTA_180_DIAS_SIN_OPERAR = 117;

	/** The Constant ERROR_BANELCO_TIMEOUT. */
	private static final int ERROR_BANELCO_TIMEOUT = 9976;

	/** The Constant STRING_VACIO_LONGITUD_1. */
	private static final String STRING_VACIO_LONGITUD_1 = " ";

	/** The Constant STRING_VACIO. */
	private static final String STRING_VACIO = "";

	/** The Constant ERROR_AL_CONSULTAR_EL_SERVICIO. */
	private static final String ERROR_AL_CONSULTAR_EL_SERVICIO = "Error al consultar el servicio.";

	/** The Constant TIPO_AGENDAMIENTO_RECORDATORIO. */
	private static final String TIPO_AGENDAMIENTO_RECORDATORIO = "RECORDATORIO";

	/** The Constant TIPO_AGENDAMIENTO_AUTOMATICO. */
	private static final String TIPO_AGENDAMIENTO_AUTOMATICO = "AUTOMATICO";

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

	/** The siete por venticuatro V 1 DAO. */
	@Autowired
	private SietePorVenticuatroV1DAO sietePorVenticuatroV1DAO;

	/** The error banelco coelsa habilitado. */
	@Value("${ERRORBANELCO.COELSA.HABILITADO}")
	private String errorBanelcoCoelsaHabilitado;

	/**
	 * Armar comprobante stop debit.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the comprobante stop debit
	 */
	private ComprobanteStopDebit armarComprobanteStopDebit(XMLResponseEntity respuesta) {
		ComprobanteStopDebit comprobante = new ComprobanteStopDebit();
		comprobante.setIdRec(respuesta.getDATOSRESULTADO().getIdRecibo());
		return comprobante;
	}

	/**
	 * Armar comprobante cancel total.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @return the comprobante cancel total
	 */
	private ComprobanteCancelTotal armarComprobanteCancelTotal(XMLResponseEntity respuesta) {
		ComprobanteCancelTotal comprobante = new ComprobanteCancelTotal();
		comprobante.setIdRecibo(respuesta.getDATOSRESULTADO().getIdRecibo());
		return comprobante;
	}

	/**
	 * Armar request stop debit.
	 *
	 * @param transferenciaAgendadaDTO
	 *            the transferencia agendada DTO
	 * @param cliente
	 *            the cliente
	 * @return the siete por veinti cuatro request
	 */
	private XMLRequestEntity armarRequestStopDebit(TransferenciaAgendadaDTO transferenciaAgendadaDTO, Cliente cliente) {
		Cliente clienteStopDebit = cliente;
		String nup = StringUtils.leftPad(cliente.getNup(), NUP_LENGTH, '0');
		TransferenciaAgendada transferenciaAgendadadto = transferenciaAgendadaDTO.getDatosOrigen()
				.getTransferenciaAgendada();

		XMLRequestEntity request = setearDatosComunesRequest();

		request.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_CANCEL_7X24);
		request.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24);

		request.getMETA().getCliente().setTipoId(clienteStopDebit.getTipoDocumento());
		request.getMETA().getCliente().setIdCliente(clienteStopDebit.getDni());
		request.getMETA().getCliente().setFechaNac(clienteStopDebit.getFechaNacimiento());
		request.getMETA().getCliente().setNUP(nup);

		request.getMETA().getUsuario().setUsuarioId(clienteStopDebit.getUsuarioRacf());
		request.getMETA().getUsuario().setUsuarioPwd(clienteStopDebit.getPasswordRacf());
		request.getMETA().setIdTransaccion(transferenciaAgendadadto.getIdTransaccion());
		request.getMETA().setNroRecurrencia(transferenciaAgendadadto.getNroRecurrencia());

		request.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24);

		return request;
	}

	/**
	 * Armar request cancel total.
	 *
	 * @param consultaCancelTotal
	 *            the consulta cancel total
	 * @param clienteCancel
	 *            the cliente cancel
	 * @return the siete por veinti cuatro request
	 */
	private XMLRequestEntity armarRequestCancelTotal(ConsultaCancelTotal consultaCancelTotal, Cliente clienteCancel) {
		Cliente cliente = clienteCancel;
		String nup = StringUtils.leftPad(clienteCancel.getNup(), NUP_LENGTH, '0');
		TransferenciaAgendada transferenciaCanceladaDto = consultaCancelTotal.getDatosOrigen()
				.getTransferenciaAgendada();
		XMLRequestEntity request = setearDatosComunesRequest();
		request.getCONFIG().setVersionXML(SietePorVeintiCuatroConstant.VERSION_XML_7X24);
		request.getCONFIG().setEcoDatosEntrada(SietePorVeintiCuatroConstant.ECO_DATOS_ENTRADA_7X24);
		request.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_CANCEL_TOTAL_7X24);
		request.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24);
		request.getMETA().setIndAuten(SietePorVeintiCuatroConstant.IND_AUTENT_7X24);
		request.getMETA().setIdSesionCnt(SietePorVeintiCuatroConstant.ID_SESIONCNT_7X24);
		request.getMETA().getCliente().setTipoId(cliente.getTipoDocumento());
		request.getMETA().getCliente().setIdCliente(cliente.getDni());
		request.getMETA().getCliente().setFechaNac(cliente.getFechaNacimiento());
		request.getMETA().getCliente().setNUP(nup);
		request.getMETA().getUsuario().setUsuarioId(cliente.getUsuarioRacf());
		request.getMETA().getUsuario().setUsuarioPwd(cliente.getPasswordRacf());
		request.getMETA().getCanal().setCanalTipo(SietePorVeintiCuatroConstant.CANAL_TIPO_7X24);
		request.getMETA().getCanal().setCanalId(SietePorVeintiCuatroConstant.CANAL_ID_7X24);
		request.getMETA().getCanal().setCanalVersion(SietePorVeintiCuatroConstant.CANAL_VERSION_7X24);
		request.getMETA().getSubcanal().setSubcanalTipo(SietePorVeintiCuatroConstant.SUBCANAL_TIPO_7X24);
		request.getMETA().getSubcanal().setSubcanalId(SietePorVeintiCuatroConstant.SUBCANAL_ID_7X24);
		request.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24);
		request.getMETA().setIdTransaccion(transferenciaCanceladaDto.getIdTransaccion());
		return request;
	}

	/**
	 * Checks if is response ok.
	 *
	 * @param response
	 *            the response
	 * @return true, if is response ok
	 */
	private boolean isResponseOk(String response) {
		return Long.valueOf(response).equals(0L);
	}

	/**
	 * Mapear lista.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param isRioRio
	 *            the is rio rio
	 * @return the list
	 * @throws JAXBException
	 *             the JAXB exception
	 *
	 *
	 *             private List <TransferenciaAgendada>
	 *             mapearLista(CNSAgendaResponse respuesta, boolean isRioRio)
	 *             throws JAXBException { List <TransferenciaAgendada>
	 *             listTransferenciaAgendada = new
	 *             ArrayList<TransferenciaAgendada>(); CNSAgendaDatosResponse
	 *             cnsAgendaDatosResponse = respuesta.getDatos();
	 * 
	 *             for (CNSAgendaRegResponse regResponse :
	 *             cnsAgendaDatosResponse.getReg()) { TransferenciaAgendada
	 *             transferenciaAgendada = AgendaTransferenciaMapper
	 *             .obtenerTransferenciaAgendada(regResponse, isRioRio);
	 *             transferenciaAgendada.setRioRio(isRioRio);
	 *             listTransferenciaAgendada.add(transferenciaAgendada); }
	 *             return listTransferenciaAgendada; }
	 */

	private List<TransferenciaAgendada> mapearLista(XMLResponseEntity respuesta, boolean isRioRio)
			throws JAXBException {
		List<TransferenciaAgendada> listTransferenciaAgendada = new ArrayList<TransferenciaAgendada>();
		List<Registro> datosResponse = respuesta.getDATOSRESULTADO().getRegistro();
		for (Registro registro : datosResponse) {
			TransferenciaAgendada transferenciaAgendada = AgendaTransferenciaMapper
					.obtenerTransferenciaAgendada(registro, isRioRio);
			transferenciaAgendada.setRioRio(isRioRio);
			listTransferenciaAgendada.add(transferenciaAgendada);
		}
		return listTransferenciaAgendada;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.dao.
	 * AgendaTransferenciaDAO#obtenerTransferenciasAgendadas(ar.com.santanderrio
	 * .obp.servicios.transferencias.entities.ConsultaAgendaTransferencias,
	 * boolean)
	 */
	@Override
	public List<TransferenciaAgendada> obtenerTransferenciasAgendadas(
			ConsultaAgendaTransferencias consultaAgendaTransferencias, boolean isRioRio) throws DAOException {
		try {

			XMLRequestEntity request = new XMLRequestEntity();
			request = armarRequest(consultaAgendaTransferencias, isRioRio);
			XMLResponseEntity respuesta = (XMLResponseEntity) sietePorVenticuatroV1DAO.ejecutar(request);

			if (TIPO_ERROR.equals(respuesta.getDATOSRESULTADO().getCodRet())
					&& TIPO_ERROR.equals(respuesta.getDATOSRESULTADO().getSeveridad())) {
				throw new DAOException("No se encontro o esta vacio el tag 'usuario' en el xml de entrada");
			}
			if (!isResponseOk(respuesta.getDATOSRESULTADO().getCodRet())
					|| !isResponseOk(respuesta.getDATOSRESULTADO().getSeveridad())) {
				throw new DAOException("Se ha producido un error del servicio");
			}
			return mapearLista(respuesta, isRioRio);

		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Armar request.
	 *
	 * @param consultaAgendaTransferencias
	 *            the consulta agenda transferencias
	 * @param isRioRio
	 *            the is rio rio
	 * @return the XML request entity
	 */
	protected XMLRequestEntity armarRequest(ConsultaAgendaTransferencias consultaAgendaTransferencias,
			boolean isRioRio) {
		XMLRequestEntity request;
		if (isRioRio) {
			request = armarRequestAgendaV1(consultaAgendaTransferencias,
					SietePorVeintiCuatroServicioEnum.TRANSF_BCO_RIO_120);
		} else {
			request = armarRequestAgendaV1(consultaAgendaTransferencias,
					SietePorVeintiCuatroServicioEnum.TRANSF_TRFCCI_160);
		}
		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.dao.
	 * AgendaTransferenciaDAO#stopDebit(ar.com.santanderrio.obp.servicios.
	 * transferencias.entities.ConsultaStopDebit)
	 */
	@Override
	public ComprobanteStopDebit stopDebit(TransferenciaAgendadaDTO transferenciaAgendadaDTO, Cliente cliente)
			throws DAOException {
		try {
			XMLRequestEntity request = armarRequestStopDebit(transferenciaAgendadaDTO, cliente);
			XMLResponseEntity respuesta = sietePorVenticuatroV1DAO.ejecutar(request);
			String errorId = respuesta.getDATOSRESULTADO().getCodRet();
			if (!isResponseOk(errorId)) {
				mapearErroresPorTipo(errorId);
				return null;
			} else {
				return armarComprobanteStopDebit(respuesta);
			}
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.transferencias.dao.AgendaTransferenciaDAO#
	 * cancelTotal(ar.com.santanderrio.obp.transferencias.entities.
	 * ConsultaStopDebit)
	 */
	@Override
	public ComprobanteCancelTotal cancelTotal(ConsultaCancelTotal consultaCancelTotal, Cliente cliente)
			throws DAOException {
		try {
			XMLRequestEntity request = armarRequestCancelTotal(consultaCancelTotal, cliente);
			XMLResponseEntity respuesta = sietePorVenticuatroV1DAO.ejecutar(request);
			String errorId = respuesta.getDATOSRESULTADO().getCodRet();
			if (!isResponseOk(errorId)) {
				mapearErroresPorTipo(errorId);
				return null;
			} else {
				return armarComprobanteCancelTotal(respuesta);
			}
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			throw new DAOException(e.getMessage());
		}
	}

	/**
	 * Armar request agenda V 1.
	 *
	 * @param consultaAgendaTransferencias
	 *            the consulta agenda transferencias
	 * @param sietePorVeintiCuatroServicio
	 *            the siete por veinti cuatro servicio
	 * @return the siete por veinti cuatro request
	 */
	private XMLRequestEntity armarRequestAgendaV1(ConsultaAgendaTransferencias consultaAgendaTransferencias,
			SietePorVeintiCuatroServicioEnum sietePorVeintiCuatroServicio) {

		String nup = StringUtils.leftPad(consultaAgendaTransferencias.getCliente().getNup(), NUP_LENGTH, '0');

		XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
		config.setVersionXML(SietePorVeintiCuatroConstant.VERSION_XML_7X24);
		config.setEcoDatosEntrada(SietePorVeintiCuatroConstant.ECO_DATOS_ENTRADA_7X24);

		XMLRequestEntity.META meta = new XMLRequestEntity.META();
		meta.setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24);
		meta.setVersion(SietePorVeintiCuatroConstant.VERSION_7X24);
		meta.setIndAuten(SietePorVeintiCuatroConstant.IND_AUTENT_7X24);
		meta.setIdSesionCnt(SietePorVeintiCuatroConstant.ID_SESIONCNT_7X24);
		meta.setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24);

		XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
		cliente.setTipoPersona(SietePorVeintiCuatroConstant.TIPO_PERSONA_7X24);
		cliente.setTipoId(consultaAgendaTransferencias.getCliente().getTipoDocumento()); // ?
		cliente.setIdCliente(consultaAgendaTransferencias.getCliente().getDni());
		cliente.setFechaNac(consultaAgendaTransferencias.getCliente().getFechaNacimiento());
		cliente.setNUP(nup);
		meta.setCliente(cliente);

		XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
		usuario.setUsuarioTipo(SietePorVeintiCuatroConstant.USUARIO_TIPO_7X24);
		usuario.setUsuarioAtrib(SietePorVeintiCuatroConstant.USUARIO_ATRIBUTO_7X24);
		usuario.setUsuarioId(consultaAgendaTransferencias.getCliente().getUsuarioRacf());
		usuario.setUsuarioPwd(consultaAgendaTransferencias.getCliente().getPasswordRacf());
		meta.setUsuario(usuario);

		XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
		canal.setCanalTipo(SietePorVeintiCuatroConstant.CANAL_TIPO_7X24);
		canal.setCanalId(SietePorVeintiCuatroConstant.CANAL_ID_7X24);
		canal.setCanalVersion(SietePorVeintiCuatroConstant.CANAL_VERSION_7X24);
		meta.setCanal(canal);

		XMLRequestEntity.META.Subcanal subcanal = new XMLRequestEntity.META.Subcanal();
		subcanal.setSubcanalTipo(SietePorVeintiCuatroConstant.SUBCANAL_TIPO_7X24);
		subcanal.setSubcanalId(SietePorVeintiCuatroConstant.SUBCANAL_ID_7X24);
		meta.setSubcanal(subcanal);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		parametros.setNombreGuardado(sietePorVeintiCuatroServicio.getServicio());
		parametros.setNUPGuardado(nup);
		parametros.setFechaEjecucionDesde(
				ISBANStringUtils.formatearFecha(consultaAgendaTransferencias.getFechaDesde(), FORMATO_FECHA));
		parametros.setFechaEjecucionHasta(
				ISBANStringUtils.formatearFecha(consultaAgendaTransferencias.getFechaHasta(), FORMATO_FECHA));

		datosentrada.setParametros(parametros);

		XMLRequestEntity request = new XMLRequestEntity();

		request.setCONFIG(config);
		request.setMETA(meta);
		request.setDATOSENTRADA(datosentrada);

		return request;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.dao.
	 * AgendaTransferenciaDAO#ejecutarAgendaTransferencia(ar.com.santanderrio.
	 * obp.servicios.transferencias.entities.EjecutarTransferencia)
	 */
	@Override
	public TransferenciaEjecutadaDTO ejecutarTransferenciaAgendada(TransferenciaAgendadaDTO transferenciaDeAgendaDTO,
			Cliente cliente) throws DAOException {

		XMLRequestEntity request = new XMLRequestEntity();
		TransferenciaRequest transferenciaRequest = new TransferenciaAgendadaCreacionRequest(transferenciaDeAgendaDTO,
				cliente, "", sesionParametros, errorBanelcoCoelsaHabilitado);
		if (transferenciaDeAgendaDTO.isHaciaOtroBanco()) {
			request = transferenciaRequest.armarRequestOtrosBancos();
		} else {
			request = transferenciaRequest.armarRequestRioRio();
		}

		XMLResponseEntity respuesta = sietePorVenticuatroV1DAO.ejecutar(request);

		String errorId = respuesta.getDATOSRESULTADO().getCodRet();

		if (!isResponseOk(errorId)) {
			mapearErroresPorTipo(errorId);
			return null;
		} else {
			return mapearResponseADTO(respuesta, !transferenciaDeAgendaDTO.isHaciaOtroBanco());
		}
	}

	/**
	 * Existen errores al agendar.
	 *
	 * @param codRet
	 *            the cod ret
	 * @return true, if successful
	 * @throws DAOException
	 *             the DAO exception
	 */
	private void mapearErroresPorTipo(String codRet) throws DAOException {
		Integer codigoError = Integer.parseInt(codRet);
		switch (codigoError) {
		case ERROR_SALDO_INSUFICIENTE_1:
			LOGGER.debug(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
			throw new DAOException(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
		case ERROR_SALDO_INSUFICIENTE_2: 
			LOGGER.debug(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
			throw new DAOException(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
		case ERROR_SALDO_INSUFICIENTE_2A:
			LOGGER.debug(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
			throw new DAOException(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
		case ERROR_SALDO_INSUFICIENTE_2B: 
			LOGGER.debug(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
			throw new DAOException(TipoError.ERROR_SALDO_INSUFICIENTE.getDescripcion());
		case CUENTA_180_DIAS_SIN_OPERAR:
			LOGGER.debug(TipoError.ERROR_CUENTA_180_DIAS_SIN_OPERAR.getDescripcion());
			throw new DAOException(TipoError.ERROR_CUENTA_180_DIAS_SIN_OPERAR.getDescripcion());
		case ERROR_BANELCO_TIMEOUT:
			LOGGER.debug(TipoError.ERROR_BANELCO_TIMEOUT.getDescripcion());
			throw new DAOException(TipoError.ERROR_BANELCO_TIMEOUT.getDescripcion());
		default:
			LOGGER.debug(TipoError.ERROR_GENERICO.getDescripcion() + " codigo Error: " + codigoError);
			throw new DAOException(TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	/**
	 * Setear datos comunes request.
	 *
	 * @return the XML request entity
	 */
	private XMLRequestEntity setearDatosComunesRequest() {
		XMLRequestEntity request = new XMLRequestEntity();

		XMLRequestEntity.CONFIG config = new XMLRequestEntity.CONFIG();
		config.setEcoDatosEntrada(SietePorVeintiCuatroConstant.ECO_DATOS_ENTRADA_7X24);
		config.setVersionXML(SietePorVeintiCuatroConstant.VERSION_XML_7X24);

		XMLRequestEntity.META meta = new XMLRequestEntity.META();
		meta.setIndAuten(SietePorVeintiCuatroConstant.IND_AUTENT_7X24);
		meta.setIdSesionCnt(SietePorVeintiCuatroConstant.ID_SESIONCNT_7X24);

		XMLRequestEntity.META.Cliente cliente = new XMLRequestEntity.META.Cliente();
		cliente.setTipoPersona(SietePorVeintiCuatroConstant.TIPO_PERSONA_7X24);
		meta.setCliente(cliente);

		XMLRequestEntity.META.Usuario usuario = new XMLRequestEntity.META.Usuario();
		usuario.setUsuarioTipo(SietePorVeintiCuatroConstant.USUARIO_TIPO_7X24);
		usuario.setUsuarioAtrib(SietePorVeintiCuatroConstant.USUARIO_ATRIBUTO_7X24);
		meta.setUsuario(usuario);

		XMLRequestEntity.META.Canal canal = new XMLRequestEntity.META.Canal();
		canal.setCanalTipo(SietePorVeintiCuatroConstant.CANAL_TIPO_7X24);
		canal.setCanalId(SietePorVeintiCuatroConstant.CANAL_ID_7X24);
		canal.setCanalVersion(SietePorVeintiCuatroConstant.CANAL_VERSION_7X24);
		meta.setCanal(canal);

		XMLRequestEntity.META.Subcanal subcanal = new XMLRequestEntity.META.Subcanal();
		subcanal.setSubcanalTipo(SietePorVeintiCuatroConstant.SUBCANAL_TIPO_7X24);
		subcanal.setSubcanalId(SietePorVeintiCuatroConstant.SUBCANAL_ID_7X24);
		meta.setSubcanal(subcanal);

		request.setCONFIG(config);
		request.setMETA(meta);
		return request;
	}

	/**
	 * Mapear lista to DTO para RR y OBancos.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param isRioRio
	 *            the is rio rio
	 * @return the transferencia ejecutada
	 */
	private TransferenciaEjecutadaDTO mapearResponseADTO(XMLResponseEntity respuesta, Boolean isRioRio) {
		return AgendaTransferenciaMapper.obtenerTransferenciaEjecutada(respuesta, isRioRio);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.dao.
	 * AgendaTransferenciaDAO#ejecutarModificacionDeTransferenciaAgendada(ar.com
	 * .santanderrio.obp.servicios.transferencias.entities.agenda.
	 * TransferenciaAgendadaDTO, java.lang.String,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public TransferenciaAgendadaDTO ejecutarModificacionDeTransferenciaAgendada(
			TransferenciaAgendadaDTO transferenciaDTO, String tipoAgendamiento, Cliente cliente) throws DAOException {
		XMLRequestEntity request = new XMLRequestEntity();
		TransferenciaRequest transferenciaRequest = new AgendamientoTransferenciaModificacionRequest(transferenciaDTO,
				cliente, tipoAgendamiento, sesionParametros, errorBanelcoCoelsaHabilitado);
		if (transferenciaDTO.isHaciaOtroBanco()) {
			request = transferenciaRequest.armarRequestOtrosBancos();
		} else {
			request = transferenciaRequest.armarRequestRioRio();
		}

		XMLResponseEntity respuesta = sietePorVenticuatroV1DAO.ejecutar(request);

		String errorId = respuesta.getDATOSRESULTADO().getCodRet();
		if (!Long.valueOf(errorId).equals(0L)) {
			throw new DAOException(ERROR_AL_CONSULTAR_EL_SERVICIO);
		} else {
			transferenciaDTO.setIdRecibo(respuesta.getDATOSRESULTADO().getIdRecibo().substring(8,
					respuesta.getDATOSRESULTADO().getIdRecibo().length()));
			String fechaFormateada = new String();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			fechaFormateada = sdf.format(new Date());
			if (!transferenciaDTO.isHaciaOtroBanco()) {
				transferenciaDTO.setFechaCompensacion(fechaFormateada);
			} else {
				transferenciaDTO.setFechaCompensacion(fechaFormateada);
				transferenciaDTO.setIdRecibo(respuesta.getDATOSRESULTADO().getIdRecibo());
			}
			return transferenciaDTO;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.dao.
	 * AgendaTransferenciaDAO#ejecutarAgendamientoRecordatorioTransferencia(ar.
	 * com.santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public TransferenciaDTO ejecutarAgendamientoRecordatorioTransferencia(TransferenciaDTO transferenciaDTO,
			Cliente cliente) throws DAOException {
		XMLRequestEntity request = new XMLRequestEntity();
		TransferenciaRequest transferenciaRequest = new AgendamientoTransferenciaCreacionRequest(
				(TransferenciaAgendadaDTO) transferenciaDTO, cliente, TIPO_AGENDAMIENTO_RECORDATORIO, sesionParametros, errorBanelcoCoelsaHabilitado);
		if (transferenciaDTO.isHaciaOtroBanco()) {
			request = transferenciaRequest.armarRequestOtrosBancos();
		} else {
			request = transferenciaRequest.armarRequestRioRio();
		}

		XMLResponseEntity respuesta = sietePorVenticuatroV1DAO.ejecutar(request);

		String errorId = respuesta.getDATOSRESULTADO().getCodRet();

		if (!Long.valueOf(errorId).equals(0L)) {
			throw new DAOException(ERROR_AL_CONSULTAR_EL_SERVICIO);
		} else {
			return armarResponseDTO(respuesta, transferenciaDTO);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.transferencias.dao.
	 * AgendaTransferenciaDAO#ejecutarAgendamientoAutomaticoTransferencia(ar.com
	 * .santanderrio.obp.servicios.transferencias.entities.TransferenciaDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public TransferenciaDTO ejecutarAgendamientoAutomaticoTransferencia(TransferenciaDTO transferenciaDTO,
			Cliente cliente) throws DAOException {
		XMLRequestEntity request = new XMLRequestEntity();
		TransferenciaRequest transferenciaRequest = new AgendamientoTransferenciaCreacionRequest(
				(TransferenciaAgendadaDTO) transferenciaDTO, cliente, TIPO_AGENDAMIENTO_AUTOMATICO, sesionParametros, errorBanelcoCoelsaHabilitado);
		if (transferenciaDTO.isHaciaOtroBanco()) {
			request = transferenciaRequest.armarRequestOtrosBancos();
		} else {
			request = transferenciaRequest.armarRequestRioRio();
		}

		// if (!transferenciaDTO.isHaciaOtroBanco()) {
		// request =
		// armarRequestRioRioAgendamientoTransferencia(transferenciaDTO,
		// cliente,
		// TIPO_AGENDAMIENTO_AUTOMATICO);
		// } else {
		// request =
		// armarRequestOBancosAgendamientoTransferencia(transferenciaDTO,
		// cliente,
		// TIPO_AGENDAMIENTO_AUTOMATICO);
		// }

		XMLResponseEntity respuesta = sietePorVenticuatroV1DAO.ejecutar(request);

		String errorId = respuesta.getDATOSRESULTADO().getCodRet();

		if (!Long.valueOf(errorId).equals(0L)) {
			throw new DAOException(ERROR_AL_CONSULTAR_EL_SERVICIO);
		} else {
			return armarResponseDTO(respuesta, transferenciaDTO);
		}
	}

	/**
	 * Armar response DTO.
	 *
	 * @param respuesta
	 *            the respuesta
	 * @param transferenciaEjecutada
	 *            the transferencia ejecutada
	 * @return the transferencia DTO
	 */
	private TransferenciaDTO armarResponseDTO(XMLResponseEntity respuesta, TransferenciaDTO transferenciaEjecutada) {
		TransferenciaDTO transferencia = transferenciaEjecutada;
		transferencia.setIdRecibo(respuesta.getDATOSRESULTADO().getIdRecibo());
		String fechaFormateada = new String();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		fechaFormateada = sdf.format(new Date());
		if (!transferencia.isHaciaOtroBanco()) {
			transferencia.setFechaCompensacion(fechaFormateada);
		} else {
			transferencia.setFechaCompensacion(fechaFormateada);
		}
		return transferencia;
	}

	/**
	 * Armar request rio rio agendamiento transferencia.
	 *
	 * @param transferenciaDTO
	 *            the transferencia DTO
	 * @param cliente
	 *            the cliente
	 * @param tipoDeAgendamiento
	 *            the tipo de agendamiento
	 * @return the XML request entity
	 */
	public XMLRequestEntity armarRequestRioRioAgendamientoTransferencia(TransferenciaDTO transferenciaDTO,
			Cliente cliente, String tipoDeAgendamiento) {
		String nup = StringUtils.leftPad(cliente.getNup(), NUP_LENGTH, '0');

		XMLRequestEntity request = setearDatosComunesRequest();
		request.getMETA().setNombre(SietePorVeintiCuatroConstant.NOMBRE_7X24_TX_BC_RIO);
		request.getMETA().setVersion(SietePorVeintiCuatroConstant.VERSION_7X24_110);
		request.getMETA().setModoEjecucion(SietePorVeintiCuatroConstant.MODO_EJECUCION_7X24_A);
		if (tipoDeAgendamiento.equals(TIPO_AGENDAMIENTO_RECORDATORIO)) {
			request.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24_I);
		} else {
			request.getMETA().setTipoAgendamiento(SietePorVeintiCuatroConstant.TIPO_AGENDAMIENTO_7X24);
		}
		request.getMETA().setFechaEjecucion(
				ISBANStringUtils.formatearFecha(transferenciaDTO.getFechaProgramada(), FORMATO_FECHA));
		request.getMETA().setLogueoAgendaHistorica(SietePorVeintiCuatroConstant.LOGUEO_AGENDA_HISTORICO);

		request.getMETA().getCliente().setTipoId(cliente.getTipoDocumento());
		request.getMETA().getCliente().setIdCliente(cliente.getDni());
		request.getMETA().getCliente().setFechaNac(cliente.getFechaNacimiento());
		request.getMETA().getCliente().setNUP(nup);

		request.getMETA().getUsuario().setUsuarioId(cliente.getUsuarioRacf());
		request.getMETA().getUsuario().setUsuarioPwd(cliente.getPasswordRacf());

		XMLRequestEntity.META.Recurrencias recurrencias = new XMLRequestEntity.META.Recurrencias();
		if (tipoDeAgendamiento.equals(TIPO_AGENDAMIENTO_RECORDATORIO)) {
			recurrencias.setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24);
		} else {
			recurrencias.setTipoRecurrencia(SietePorVeintiCuatroConstant.TIPO_RECURRENCIA_7x24_E);
		}
		recurrencias.setFechaBaseRecurrencia(
				ISBANStringUtils.formatearFecha(transferenciaDTO.getFechaProgramada(), FORMATO_FECHA));
		recurrencias.setFrecRecurrencia(obtenerCodigoFrecuenciaParaServicio7x24(transferenciaDTO.getFrecuencia(),
				transferenciaDTO.getFechaProgramada()));
		recurrencias.setMaxRecurrencia(SietePorVeintiCuatroConstant.MAX_RECURRENCIA_7x24);
		request.getMETA().setRecurrencias(recurrencias);

		XMLRequestEntity.DATOSENTRADA datosentrada = new XMLRequestEntity.DATOSENTRADA();

		XMLRequestEntity.DATOSENTRADA.Parametros parametros = new XMLRequestEntity.DATOSENTRADA.Parametros();
		Cuenta cuentaOrigen = transferenciaDTO.getCuentaOrigen();
		String nroSucursal = transferenciaDTO.getCuentaOrigen().getNroSucursal();
		String nroCuentaProduxcto = transferenciaDTO.getCuentaOrigen().getNroCuentaProducto();
		if (cuentaOrigen.getTipoCuenta().equals(TipoCuenta.CUENTA_UNICA)) {
			TipoCuenta tipoCuenta = TransferenciaUtil
					.matchearTipoCuentaDestino(transferenciaDTO.getMoneda().getMoneda());
			parametros.setTipoCtaDebito(StringUtils.leftPad(tipoCuenta.getCodigo().toString(), 2, "0"));
		} else {
			parametros.setTipoCtaDebito(StringUtils.leftPad(transferenciaDTO.getTipoCuentaOrigen(), 2, "0"));
		}
		parametros.setSucCtaDebito(
				StringUtils.leftPad(nroSucursal.substring(nroSucursal.length() - 3, nroSucursal.length()), 3, "0"));
		parametros.setNroCtaDebito(StringUtils.leftPad(
				nroCuentaProduxcto.substring(nroCuentaProduxcto.length() - 7, nroCuentaProduxcto.length()), 7, "0"));
		parametros.setImporteDebito(transferenciaDTO.getImporte().toString());
		parametros.setTipoCtaCredito(
				StringUtils.leftPad(transferenciaDTO.getTipoCuentaDestino().getCodigo().toString(), 2, "0"));
		parametros.setSucCtaCredito(
				StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroSucursal(), 3, "0"));
		parametros.setNroCtaCredito(
				StringUtils.leftPad(transferenciaDTO.getNumeroCuentaDestino().getNroCuentaProducto(), 7, "0"));
		parametros.setNombreCtaCredito(transferenciaDTO.getNombreReceptor());
		parametros.setCotizacionTransferencia(SietePorVeintiCuatroConstant.COTIZACION_TRANSFEERENCIA);
		parametros.setImporteCredito(SietePorVeintiCuatroConstant.IMPORTE_CREDITO_7X24);
		parametros.setNroComprobante(SietePorVeintiCuatroConstant.NRO_COMPROBANTE);
		parametros.setIndicadorAfectarDisponible(SietePorVeintiCuatroConstant.INDICADOR_AFECTAR_DISPONIBLE);
		parametros.setIndicadorLimiteMax(SietePorVeintiCuatroConstant.INDICADOR_LIMITE_MAX);
		parametros.setCodigoConcepto(transferenciaDTO.getConcepto().getCodigo());
		parametros.setDescripcionConcepto(transferenciaDTO.getDescConcepto());
		parametros.setCuentaPropia(transferenciaDTO.isHaciaCuentaPropia() == true ? "S" : "N");
		parametros.setIndTransfDiferida(
				PlazoAcreditacion.PLAZO_48_HS.equals(transferenciaDTO.getPlazoAcreditacion()) ? "S" : "N");
		datosentrada.setParametros(parametros);

		XMLRequestEntity.DATOSADICIONALES datosAdicionales = new XMLRequestEntity.DATOSADICIONALES();
		XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos datosMensAvisos = new XMLRequestEntity.DATOSADICIONALES.DatosMensAvisos();
		datosMensAvisos.setTitularDebito(cliente.getNombre() + STRING_VACIO_LONGITUD_1 + cliente.getApellido1());
		datosMensAvisos.setTitularCredito(transferenciaDTO.getNombreReceptor());
		datosMensAvisos.setMailCredito(transferenciaDTO.getEmail());
		datosMensAvisos.setComentario(transferenciaDTO.getComentario());
		datosMensAvisos.setConcepto(transferenciaDTO.getConcepto().getCodigo());
		datosMensAvisos.setInfoAdicional(transferenciaDTO.getDescConcepto());
		datosMensAvisos.setMailClienteReply(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional1(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional1(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional2(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional2(STRING_VACIO);
		datosMensAvisos.setConceptoAdicional3(STRING_VACIO);
		datosMensAvisos.setDescripcionAdicional3(STRING_VACIO);
		datosMensAvisos.setAnotacionesPersonales(STRING_VACIO);
		datosAdicionales.setDatosMensAvisos(datosMensAvisos);

		request.setDATOSENTRADA(datosentrada);
		request.setDatosAdicionales(datosAdicionales);

		return request;
	}

	/**
	 * Obtener codigo frecuencia para servicio 7 x 24.
	 *
	 * @param frecuencia
	 *            the frecuencia
	 * @param fechaProgramada
	 *            the fecha programada
	 * @return the string
	 */
	private String obtenerCodigoFrecuenciaParaServicio7x24(FrecuenciaTransferenciaAgendada frecuencia,
			Date fechaProgramada) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaProgramada);
		String diaDelMes = StringUtils.leftPad(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)), 2, "0");
		String mes = StringUtils.leftPad(Integer.toString(calendar.get(Calendar.MONTH)), 2, "0");
		int diaDeLaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		String codigoFrecuencia7x24 = "";

		if ("TS".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "S1".concat(obtenerInicialDelDia(diaDeLaSemana));
		}
		if ("2S".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "S2".concat(obtenerInicialDelDia(diaDeLaSemana));
		}
		if ("TM".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("01");
		}
		if ("2M".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("02");
		}
		if ("3M".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("03");
		}
		if ("6M".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "M1".concat(diaDelMes).concat("06");
		}
		if ("1A".equals(frecuencia.getCodigo())) {
			codigoFrecuencia7x24 = "A1".concat(mes).concat(diaDelMes);
		}

		return codigoFrecuencia7x24;
	}

	/**
	 * Obtener inicial del dia.
	 *
	 * @param diaDeLaSemana
	 *            the dia de la semana
	 * @return the string
	 */
	private String obtenerInicialDelDia(int diaDeLaSemana) {
		String inicialDelDia = "";
		switch (diaDeLaSemana) {
		case 0:
			inicialDelDia = "D";
		case 1:
			inicialDelDia = "L";
		case 2:
			inicialDelDia = "T";
		case 3:
			inicialDelDia = "M";
		case 4:
			inicialDelDia = "J";
		case 5:
			inicialDelDia = "V";
		case 6:
			inicialDelDia = "S";
		}
		return inicialDelDia;
	}
}