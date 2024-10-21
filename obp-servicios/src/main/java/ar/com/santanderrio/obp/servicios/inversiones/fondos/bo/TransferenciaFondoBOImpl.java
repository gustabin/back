/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.bo;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.*;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype.FondosTenenciasPrototype;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.contrato.bo.ContratoBO;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.CampoEnum;
import ar.com.santanderrio.obp.servicios.comun.contrato.entity.SinonimoEnum;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.inversiones.TipoMonedaInversionEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.EnumFondosDisponiblesTipoOperacion;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.comun.TipoBancaEnum;
import ar.com.santanderrio.obp.servicios.inversiones.comun.bo.InversionesAbstractBO;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.bancaprivada.dao.FondoBPrivDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.FondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dao.TransferenciaFondoDAO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.ConfigTransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentaTituloDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.CuentasConsultaFondoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FinalizarTransferenciaBprivDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.FondoResumidoDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimulacionInDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.SimularSuscripcionOutDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.dto.TransferenciaDTO;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.view.ConfigTransferenciaInView;
import ar.com.santanderrio.obp.servicios.tarjetas.exceptions.SimulacionDAOException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.ImporteLimiteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TransferenciaBloqueadaException;

/**
 * The Class TransferenciaFondoBOImpl.
 *
 * @author
 */
@Component()
public class TransferenciaFondoBOImpl extends InversionesAbstractBO implements TransferenciaFondoBO {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TransferenciaFondoBOImpl.class);

	/** The Constant LARGO_COD_FONDO. */
	private static final int LARGO_COD_FONDO = 3;

	/** The Constant PREFIJO_COD_AGENTE. */
	private static final String PREFIJO_COD_AGENTE = "001";

	/** The Constant PREFIJO_COD_CANAL. */
	private static final String PREFIJO_COD_CANAL = "991";

	/** The Constant PREFIJO_IMPRE_SOLICITUD. */
	private static final String PREFIJO_IMPRE_SOLICITUD = "N";

	/** The Constant PREFIJO_CODIGO_CUSTODIA. */
	private static final String PREFIJO_CODIGO_CUSTODIA = "4";

	/** The Constant LARGO_IMPORTE_NETO. */
	private static final int LARGO_IMPORTE_NETO = 14;

	/** The Constant MENSAJE_LEGALES_INICIATIVA_PROPIA. */
	private static final String MENSAJE_LEGALES_INICIATIVA_PROPIA = "50010";

	/** The Constant LARGO_CUENTA_TITULO. */
	private static final int LARGO_CUENTA_TITULO = 8;

	/** The Constant PREFIJO_COD_CLIENTE. */
	private static final String PREFIJO_COD_CLIENTE = "001";

	/** The Constant CUATRO_ESPACIOS. */
	private static final String CUATRO_ESPACIOS = "    ";

	/** The Constant CUATRO_CEROS. */
	private static final String CUATRO_CEROS = "0000";

	/** The Constant LEGAL_TERMINOS_COND_TRANSFERENCIAS_BANCA_PERSONAL. */
	private static final String LEGAL_TERMINOS_COND_TRANSFERENCIAS_BANCA_PERSONAL = "10002";

	/** The Constant LEGAL_TRANSFERENCIA_BANCA_PERSONAL. */
	private static final String LEGAL_TRANSFERENCIA_BANCA_PERSONAL = "10014";

	/** The Constant OK_CODIGO_RETORNO. */
	private static final String OK_CODIGO_RETORNO = "00000000";

	/** The Constant SUSCRIPCION_CORRECTA. */
	private static final String SUSCRIPCION_TRANSACCION_CORRECTA = "10460";

	/** The Constant FINALIZAR_TRANSFERENCIA_FALLO_GENERICO. */
	public static final String FINALIZAR_TRANSFERENCIA_FALLO_GENERICO = "10452";

	/** The Constant OPERACION TRANSFERENCIA. */
	private static final String TRANSFERENCIA = "transferencia";

	/** dao. */
	@Autowired
	private TransferenciaFondoDAO transferenciaDAO;

	/** The fondo DAO. */
	@Autowired
	private FondoDAO fondoDAO;

	/** The fondo B priv DAO. */
	@Autowired
	private FondoBPrivDAO fondoBPrivDAO;

	/** The fondo BO. */
	@Autowired
	private FondoBO fondoBO;

	/** The transferencia fondo DAO. */
	@Autowired
	private TransferenciaFondoDAO transferenciaFondoDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion cliente. */

	@Autowired
	private SesionCliente sesionCliente;

	/** The session parametros. */
	@Autowired
	private SesionParametros sessionParametros;

	/** The contratosBO. */
	@Autowired
	private ContratoBO contratoBO;

	/** The legal BO. */
	@Autowired
	private LegalBO legalBO;

	/** The mensaje BO. */
	@Autowired
	private MensajeBO mensajeBO;
	
	@Autowired
	private InversionWSHelper inversionWSHelper;

	@Autowired
	private FondosTenenciasPrototype fondosTenenciasPrototype;

	@Autowired
	private FundsApi fundsApi;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.
	 * TransferenciaFondoBO#obtenerFondosSuscriptosYDisponibles(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.dto.
	 * CuentasConsultaFondoDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponibles(CuentasConsultaFondoDTO requestDTO,
			Cliente cliente) {
		
		Date fecha = new Date();
		LOGGER.info("CACHE_DIAS_NO_HABILES: Consulta al metodo");
		boolean esFeriado = inversionWSHelper.esFeriado(fecha);
		
		if (!validarHorarioFondos() || esFinDeSemana() || esFeriado) {
			LOGGER.error("Los fondos no se encuentran dentro del horario de inicio ({} hs.) y fin ({} hs.) de operatoria.",
					horarioDesdeOperarFondos, horarioHastaOperarFondos);
//			if (requestDTO.isFondoSeleccionado()) {
			return respuestaFactory.crearRespuestaWarning(new CuentasConsultaFondoDTO(), "", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_TRANSF_GRILLA_FONDOS_FUERA_HORARIO);
//			} else {
//				return respuestaFactory.crearRespuestaWarning(new CuentasConsultaFondoDTO(), "", TipoError.FUERADEHORARIO,
//						CodigoMensajeConstantes.CODIGO_MENSAJE_TRANSF_FUERA_HORARIO_FONDOS);
//			}
		}
		
		ConsultaTenenciaFCIInEntity requestEntity = genererRequestEntity(requestDTO, false);

		CuentasConsultaFondoDTO returnDTO = new CuentasConsultaFondoDTO();
		returnDTO.setTipoBanca(requestDTO.getTipoBanca());
		Mensaje mensajeInformacion = mensajeBO
				.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_INFORMACION_GO_TO);
		returnDTO.setMensajeInformacion(mensajeInformacion.getMensaje());
		try {
			fondosTenenciasPrototype.setEstadisticaConsultaTenenciaFCI(cliente);
			ConsultaTenenciaFCIOutEntity retornoDAO = fondoDAO.consultaTenenciaFCI(cliente, requestEntity);
			returnDTO.setCuentasTitulo(entityToDTO(retornoDAO.getListaTenencia(), requestDTO));

			// OBTENGO FONDOS TOTALES DISPONIBLES
			List<FondoResumidoDTO> fondosResumidosDTO;
			fondosResumidosDTO = entityToDTOs(consultaFondoDAO.obtenerFondos(predicadoObtenerFondosHabilitados()));

			returnDTO.setFondosTotales(fondosResumidosDTO);
		} catch (DAOException e) {
			LOGGER.error("Error DAOException. ", e);
			fondosTenenciasPrototype.cleanCacheConsultaTenenciaFCI(cliente);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (!returnDTO.getCuentasTitulo().isEmpty()) {
			return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoDTO.class, returnDTO);
		} else {
			return respuestaFactory.crearRespuestaWarning("", TipoError.SIN_FONDOS_A_RESCATAR,
					CodigoMensajeConstantes.SIN_TENENCIA_PARA_TRANSFERIR);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.
	 * TransferenciaFondoBO#obtenerFondosSuscriptosYDisponiblesBpriv(ar.com.
	 * santanderrio.obp.servicios.inversiones.fondos.dto.
	 * CuentasConsultaFondoDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<CuentasConsultaFondoDTO> obtenerFondosSuscriptosYDisponiblesBpriv(
			CuentasConsultaFondoDTO requestDTO, Cliente cliente) {

		Date fecha = new Date();
		LOGGER.info("CACHE_DIAS_NO_HABILES: Consulta al metodo");
		boolean esFeriado = inversionWSHelper.esFeriado(fecha);
		
		if (!validarHorarioFondos() || esFinDeSemana() || esFeriado) {
			LOGGER.error("Los fondos no se encuentran dentro del horario de inicio ({} hs.) y fin ({} hs.) de operatoria.",
					horarioDesdeOperarFondos, horarioHastaOperarFondos);
			return respuestaFactory.crearRespuestaWarning(new CuentasConsultaFondoDTO(), "", TipoError.FUERADEHORARIO,
					CodigoMensajeConstantes.CODIGO_MENSAJE_TRANSF_GRILLA_FONDOS_FUERA_HORARIO); //trans_grilla
		}
		
		
		CuentasConsultaFondoDTO returnDTO = new CuentasConsultaFondoDTO();
		returnDTO.setTipoBanca(requestDTO.getTipoBanca());

		try {
			// OBTENGO FONDOS DISPONIBLES

			List<FondoResumidoDTO> fondosResumidosDTO;
			fondosResumidosDTO = entityToDTOs(consultaFondoDAO.obtenerFondos(predicadoObtenerFondosHabilitadosBP()));
			returnDTO.setFondosTotales(fondosResumidosDTO);
			// OBTENGO FONDOS SUSCRIPTOS

			returnDTO.setCuentasTitulo(obtenerRelacionOperativaTitulo(cliente));
			fondoBO.cargarTenencia(returnDTO.getCuentasTitulo(), predicadoObtenerFondosHabilitadosBP());

			CollectionUtils.filter(returnDTO.getCuentasTitulo(), predicadoCuentaTituloSinFondos());
			Mensaje mensajeInformacion = mensajeBO
					.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_INFORMACION_GO_TO);
			returnDTO.setMensajeInformacion(mensajeInformacion.getMensaje());
		} catch (BusinessException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (DAOException e1) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		if (!returnDTO.getCuentasTitulo().isEmpty()) {
			return respuestaFactory.crearRespuestaOk(CuentasConsultaFondoDTO.class, returnDTO);
		} else {
			return respuestaFactory.crearRespuestaWarning("", TipoError.SIN_FONDOS_A_RESCATAR,
					CodigoMensajeConstantes.SIN_TENENCIA_PARA_TRANSFERIR);
		}
	}

	/**
	 * retorno mis tenencias que estan habilitadas para trasnferencia.
	 *
	 * @param listaTenencia
	 *            the lista tenencia
	 * @param requestDTO
	 *            the request DTO
	 * @return the list
	 * @throws DAOException
	 *             the DAO exception
	 */
	private List<CuentaTituloDTO> entityToDTO(List<ConsultaTenenciaFCIEntity> listaTenencia,
			CuentasConsultaFondoDTO requestDTO) throws DAOException {
		CuentaTituloDTO cuentaTitDTO = null;
		// RECORRO TODOS LOS FONDOS DEVUELTOS
		List<CuentaTituloDTO> cuentasTitulosConFondos = new ArrayList<CuentaTituloDTO>();
		for (ConsultaTenenciaFCIEntity tenencia : listaTenencia) {

			// ASIGNO EL FONDO ACTUAL A LA "cuentaTitDTO" CORRESPONDIENTE
			cuentaTitDTO = requestDTO.getCuentaByNumero(tenencia.getNroCuenta());

			if (cuentaTitDTO != null) {
				if (cuentaTitDTO.getFondosSuscriptos() == null) {
					cuentaTitDTO.setFondosSuscriptos(new ArrayList<FondoResumidoDTO>());
				}
				FondoResumidoDTO fondoResumido = obtenerFondoPorCodigoHabiliTR(
						StringUtils.right(tenencia.getEspecieCodigo(), LARGO_COD_FONDO));
				if (fondoResumido != null) {
					fondoResumido.setMoneda(
							TipoMonedaInversionEnum.fromCodigoNumericoString(tenencia.getMonedaTipo()).getSimbolo());
					fondoResumido
							.setSaldo(ISBANStringUtils.formatearSaldosConCerosYSignos(tenencia.getTeneciaValuada()));
					cuentaTitDTO.getFondosSuscriptos().add(fondoResumido);
					Collections.sort(cuentaTitDTO.getFondosSuscriptos());
					if (!cuentasTitulosConFondos.contains(cuentaTitDTO)) {
						cuentasTitulosConFondos.add(cuentaTitDTO);
					}
				}
			}
		}
		requestDTO.setCuentasTitulo(cuentasTitulosConFondos);
		return requestDTO.getCuentasTitulo();
	}

	/**
	 * Entity to DT os.
	 *
	 * @param consultasFondos
	 *            the consultas fondos
	 * @return the list
	 */
	private List<FondoResumidoDTO> entityToDTOs(List<ConsultaFondoOutEntity> consultasFondos) {
		List<FondoResumidoDTO> fondosResumidosDTO = new ArrayList<FondoResumidoDTO>();

		for (ConsultaFondoOutEntity consultaFondos : consultasFondos) {
			FondoResumidoDTO fondoResumidoDTO = cargarFondoDTO(consultaFondos);
			fondosResumidosDTO.add(fondoResumidoDTO);
		}
		return fondosResumidosDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.
	 * TransferenciaFondoBO#transferir(ar.com.santanderrio.obp.servicios.
	 * inversiones.fondos.dto.TransferenciaDTO)
	 */
	@Override
	public Respuesta<TransferenciaDTO> simularTransferencia(TransferenciaDTO dtoRequest, Cliente cliente) {
		TransferenciaInEntity transferenciaInEntity = null;
		try {
			transferenciaInEntity = createInEntity(dtoRequest);
			TransferenciaOutEntity simulacionTransferencia = transferenciaDAO.transferir(transferenciaInEntity);
			if (OK_CODIGO_RETORNO.equals(simulacionTransferencia.getCodigoRetornoExtendido())) {
				return armarRespuestaTransferencia(dtoRequest);
			}
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new TransferenciaDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
		} catch (BusinessException e) {
			LOGGER.error("Error convirtiendo view en DTO. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (TransferenciaBloqueadaException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} catch (ImporteMenorAlMinimoException e) {
			LOGGER.error("Error Importe Mayor al Maximo. ", e);
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_LIMITE_MINIMO, "");
		} catch (SaldoInsuficienteException e) {
			LOGGER.error("importe supera saldo disponible");
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SUPERA_SALDO_DISPONIBLE,
					CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE);
		} catch (DAOException e) {
			LOGGER.error("Error en fondoDAO metodo suscribir. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
				CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	}

	/**
	 * Arma la respuesta de la simulacion de tranaferencia.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @return the respuesta
	 * @throws BusinessException
	 *             the business exception
	 * @throws DAOException
	 *             the DAO exception
	 */
	private Respuesta<TransferenciaDTO> armarRespuestaTransferencia(TransferenciaDTO dtoRequest)
			throws BusinessException, DAOException {
		TransferenciaDTO dtoResponse = new TransferenciaDTO();
		dtoResponse.setUrlReglamento(armarUrlReglamento(dtoRequest.getCodigoFondoDest()));
		dtoResponse.setLegalesInformacion(obtenerLegalesInformacion(dtoRequest.getCodigoFondoDest()));
		Respuesta<String> respuestaTerminosYCondiciones = legalBO
				.buscarLegal(LEGAL_TERMINOS_COND_TRANSFERENCIAS_BANCA_PERSONAL);
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(LEGAL_TRANSFERENCIA_BANCA_PERSONAL);
		if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
				|| respuestaTerminosYCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)
				&& respuestaTerminosYCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			dtoResponse.setLegales(respuestaLegales.getRespuesta());
			dtoResponse.setTerminosYCondiciones(respuestaTerminosYCondiciones.getRespuesta());
		}
		return respuestaFactory.crearRespuestaOk(TransferenciaDTO.class, dtoResponse);
	}

	/**
	 * Buscar id legal informacion.
	 *
	 * @param codigoFondo
	 *            the codigo fondo
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String buscarIdLegalInformacion(String codigoFondo) throws DAOException {
		FondoResumidoDTO fondoResumidoDTO = obtenerFondoPorCodigo(codigoFondo);
		return (fondoResumidoDTO != null) ? fondoResumidoDTO.getIdLegalesInformacion() : null;
	}

	/**
	 * crear una entidad para llegar al dao.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @return entidad de entrada.
	 * @throws BusinessException
	 *             the business exception
	 */
	private TransferenciaInEntity createInEntity(TransferenciaDTO requestDTO) throws BusinessException {

		TransferenciaInEntity transferenciaInEntity = new TransferenciaInEntity();
        try {
            transferenciaInEntity.setCliente(sesionCliente.getCliente());
            transferenciaInEntity.setTerminalSafe(CUATRO_ESPACIOS);
            transferenciaInEntity.setCodigoFondo(requestDTO.getCodigoFondo());
            transferenciaInEntity.setCodigoCliente(PREFIJO_COD_CLIENTE
                    + StringUtils.leftPad(requestDTO.getCuentaTitulo().replace("/", ""), LARGO_CUENTA_TITULO, "0"));
            transferenciaInEntity.setImporteNeto(ISBANStringUtils.formatearImporteRescate(requestDTO.getImporteNeto()));
            transferenciaInEntity.setCodigoAgente(PREFIJO_COD_AGENTE);
            transferenciaInEntity.setCodigoCanal(PREFIJO_COD_CANAL);
            transferenciaInEntity.setCodigoFondoDest(requestDTO.getCodigoFondoDest());
            transferenciaInEntity.setImprSolicitud(PREFIJO_IMPRE_SOLICITUD);
            transferenciaInEntity.setCodigoCustodiaActual(PREFIJO_CODIGO_CUSTODIA);
            transferenciaInEntity.setCodigoCustodiaAnterior(PREFIJO_CODIGO_CUSTODIA);
            transferenciaInEntity.setPorcentComisionD(CUATRO_CEROS);
            transferenciaInEntity.setPorcentComision(CUATRO_CEROS);
            return transferenciaInEntity;

        } catch (Exception ex) {
			LOGGER.error("Error generando entity request para Simulacion Transferencia ", ex);
			throw new BusinessException();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.
	 * TransferenciaFondoBO#finalizarTransferenciaBPriv(ar.com.santanderrio.obp.
	 * servicios.inversiones.fondos.dto.FinalizarTransferenciaBprivDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<FinalizarTransferenciaBprivDTO> finalizarTransferenciaBPriv(
			FinalizarTransferenciaBprivDTO dtoRequest, Cliente cliente) {
		boolean permiteReintentar;
		String segmento = Segmento.BANCA_PRIVADA.getCodigo();

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity = null;
		try {
			fondosTenenciasPrototype.cleanCacheObtenerTenenciaValuadaDetalleFondoOnline(cliente, segmento);
			ejecucionFondoBancaPrivadaOutEntity = fondoBPrivDAO.transferir(dtoToEntity(dtoRequest));

		}catch(SaldoInsuficienteException e){
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_SUPERA_SALDO_DISPONIBLE,
					CodigoMensajeConstantes.ERRO_SUPERA_SALDO_DISPONIBLE);
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new FinalizarTransferenciaBprivDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
		} catch (ImporteLimiteException e) {
			return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_LIMITE_MINIMO, CodigoMensajeConstantes.TRANSFERENCIA_FONDOS_IMPORTE_MENOR_AL_MINIMO);
		} catch (TransferenciaBloqueadaException e) {
			return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar,
					CodigoMensajeConstantes.ERROR_GENERICO_TRANSFERENCIA_BPRIV, dtoRequest, e);
		} catch (TimeOutException exc) {
			LOGGER.error("Error en BO timeOut. ", exc);
			String mensajeError = obtenerMensajeError(CodigoMensajeConstantes.ERROR_GENERICO_TRANSFERENCIA_BPRIV,
					dtoRequest.getCodigoFondo(), dtoRequest.getCodigoFondoDest());
			return respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString());
		} catch (DAOException e) {
			LOGGER.error("Error al consultar DAO Finalizar Transferencia", e);
			return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar,
					CodigoMensajeConstantes.ERROR_GENERICO_TRANSFERENCIA_BPRIV, dtoRequest, e);
		} catch (BusinessException e) {
			return manejarReintentoRescateBPriv(dtoRequest, permiteReintentar,
					CodigoMensajeConstantes.ERROR_GENERICO_TRANSFERENCIA_BPRIV, dtoRequest, e);
		}

		cleanCacheHoldingBff(dtoRequest.getCuentaTitulo(), cliente, segmento);

		return armarRespuestaOKFinalizarTransferenciaBpriv(dtoRequest, ejecucionFondoBancaPrivadaOutEntity);
	}

	/**
	 * Manejar reintento rescate B priv.
	 *
	 * @param daoIn
	 *            the dao in
	 * @param permiteReintentar
	 *            the permite reintentar
	 * @param codigoError
	 *            the codigo error
	 * @param dtoRequest
	 *            the dto request
	 * @param e
	 *            the e
	 * @return the respuesta
	 */
	Respuesta<FinalizarTransferenciaBprivDTO> manejarReintentoRescateBPriv(FinalizarTransferenciaBprivDTO daoIn,
			boolean permiteReintentar, String codigoError, FinalizarTransferenciaBprivDTO dtoRequest, Exception e) {
		Respuesta<FinalizarTransferenciaBprivDTO> respuesta;
		LOGGER.error("Error en fondoDAO metodo finalizarTransfenciaBPriv. ", e);
		String mensajeError = obtenerMensajeError(codigoError, dtoRequest.getCodigoFondo(),
				dtoRequest.getCodigoFondoDest());
		if (permiteReintentar) {
			respuesta = respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO.toString());

		} else {
			respuesta = respuestaFactory.crearRespuestaError(FinalizarTransferenciaBprivDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString());
		}
		return respuesta;
	}

	/**
	 * Obtener mensaje error.
	 *
	 * @param codigoError
	 *            the codigo error
	 * @param codigoFondoOrigen
	 *            the codigo fondo origen
	 * @param codigoFondoDest
	 *            the codigo fondo dest
	 * @return the string
	 */
	private String obtenerMensajeError(String codigoError, String codigoFondoOrigen, String codigoFondoDest) {
		ConsultaFondoOutEntity fondoOrigen;
		ConsultaFondoOutEntity fondoDestino;
		try {
			fondoOrigen = consultaFondoDAO.obtenerPorCodigo(codigoFondoOrigen);
			fondoDestino = consultaFondoDAO.obtenerPorCodigo(codigoFondoDest);
		} catch (DAOException ex) {
			return "";
		}
		String mensajeError = mensajeBO.obtenerMensajePorCodigo(codigoError).getMensaje();
		return MessageFormat.format(mensajeError, fondoOrigen.getNombreFondo(), fondoDestino.getNombreFondo());
	}

	/**
	 * Armar respuesta OK finalizar transferencia bpriv.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @param ejecucionFondoBancaPrivadaOutEntity
	 *            the ejecucion fondo out entity
	 * @return the respuesta
	 */
	private Respuesta<FinalizarTransferenciaBprivDTO> armarRespuestaOKFinalizarTransferenciaBpriv(
			FinalizarTransferenciaBprivDTO dtoRequest, EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity) {

		FinalizarTransferenciaBprivDTO finalizarRescateBPrivDTO = crearDTOFinalizarTransferenciaBPriv(
				ejecucionFondoBancaPrivadaOutEntity, dtoRequest);
		return respuestaFactory.crearRespuestaOk(FinalizarTransferenciaBprivDTO.class, finalizarRescateBPrivDTO);
	}

	/**
	 * Crear DTO finalizar transferencia B priv.
	 *
	 * @param ejecucionFondoBancaPrivadaOutEntity
	 *            the ejecucion fondo out entity
	 * @param dtoRequest
	 *            the dto request
	 * @return the finalizar transferencia bpriv DTO
	 */
	private FinalizarTransferenciaBprivDTO crearDTOFinalizarTransferenciaBPriv(
			EjecucionFondoBancaPrivadaOutEntity ejecucionFondoBancaPrivadaOutEntity, FinalizarTransferenciaBprivDTO dtoRequest) {

		ConsultaFondoOutEntity fondoOrigen;
		ConsultaFondoOutEntity fondoDestino;
		try {
			fondoOrigen = consultaFondoDAO.obtenerPorCodigo(dtoRequest.getCodigoFondo());
			fondoDestino = consultaFondoDAO.obtenerPorCodigo(dtoRequest.getCodigoFondoDest());
		} catch (DAOException ex) {
			return null;
		}

		FinalizarTransferenciaBprivDTO finalizarRescateBPrivDTO = new FinalizarTransferenciaBprivDTO();
		String mensaje = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.TRANSFERENCIA_FONDO_CORRECTA)
				.getMensaje();
		String importeFormateado = ISBANStringUtils.formatearConComaYDosDecimales(dtoRequest.getImporte().toString());
		String mensajeTransferenciaOK = MessageFormat.format(mensaje, fondoOrigen.getNombreFondo(),
				fondoDestino.getNombreFondo(), dtoRequest.getMoneda(), importeFormateado);
		finalizarRescateBPrivDTO.setDisclaimer(ejecucionFondoBancaPrivadaOutEntity.getDisclaimer());
		finalizarRescateBPrivDTO
				.setImporte(ISBANStringUtils.formatearConComaYDosDecimales(ejecucionFondoBancaPrivadaOutEntity.getCapital()));
		finalizarRescateBPrivDTO.setNroCertificado(ejecucionFondoBancaPrivadaOutEntity.getNroCertificado());
		finalizarRescateBPrivDTO.setNroOrden(ejecucionFondoBancaPrivadaOutEntity.getNroOrden());
		finalizarRescateBPrivDTO.setMensajeSuscripcion(mensajeTransferenciaOK);
		finalizarRescateBPrivDTO.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		finalizarRescateBPrivDTO.setCodigoFondo(fondoOrigen.getCodigoFondo());
		finalizarRescateBPrivDTO.setCodigoFondoDest(fondoDestino.getCodigoFondo());

		return finalizarRescateBPrivDTO;
	}

	/**
	 * Dto to entity.
	 *
	 * @param dtoRequest
	 *            the dto request
	 * @return the ejecucion fondo in entity
	 * @throws BusinessException
	 *             the business exception
	 */
	private EjecucionFondoBancaPrivadaInEntity dtoToEntity(FinalizarTransferenciaBprivDTO dtoRequest) throws BusinessException {
		EjecucionFondoBancaPrivadaInEntity entity = new EjecucionFondoBancaPrivadaInEntity();
		Credential credential = getRACFCredential();
		entity.setTipo(getTipo());

		String numeroCtaBpriv = dtoRequest.getCuentaTitulo();
		String resultadoFormateado = numeroCtaBpriv.substring(4, numeroCtaBpriv.length());
		entity.setNroCuenta(PREFIJO_CUENTA_TITULO
				+ llenarConCerosIzqOTruncar(resultadoFormateado.replaceAll("/", ""), LONGITUD_CUENTA));
		entity.setCuotasPartes(null);
		entity.setEspecieDestino(dtoRequest.getCodigoFondoDest());
		entity.setMoneda(dtoRequest.getMoneda());
		entity.setCapital(new BigDecimal(dtoRequest.getImporte()));
		entity.setIsPerfilInversion(dtoRequest.getDentroDelPerfil());
		entity.setUsuarioRacf(credential.getUsuario());
		entity.setPasswordRacf(credential.getPassword());

		ConsultaFondoOutEntity fondoOrigen;
		ConsultaFondoOutEntity fondoDestino;
		try {
			fondoOrigen = consultaFondoDAO.obtenerPorCodigo(dtoRequest.getCodigoFondo());
			fondoDestino = consultaFondoDAO.obtenerPorCodigo(dtoRequest.getCodigoFondoDest());
			entity.setEspecie(fondoOrigen.getEspecie());
			entity.setEspecieDestino(fondoDestino.getEspecie());
			if (DivisaEnum.PESO.getCodigo().equals(fondoOrigen.getMoneda())) {
				entity.setMoneda(MONEDA_PESO);
			} else {
				entity.setMoneda(DivisaEnum.DOLAR.getCodigo());
			}
		} catch (DAOException e) {
			LOGGER.error("Error recuperando fondo Entity ", e);
			throw new BusinessException(e);
		}

		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.
	 * TransferenciaFondoBO#obtenerDatosConfig(ar.com.santanderrio.obp.servicios
	 * .inversiones.fondos.view.ConfigTransferenciaInView,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<ConfigTransferenciaDTO> obtenerDatosConfig(ConfigTransferenciaInView configTransferenciaInView,
			Cliente cliente) {

		ConfigTransferenciaDTO configTransferenciaDTO = new ConfigTransferenciaDTO();
		try {
			if ("true".equals(configTransferenciaInView.getTieneGastos())) {
				configTransferenciaDTO.setInformacionGastos(
						legalBO.buscarLegal(configTransferenciaInView.getIdMensajeGastos()).getRespuesta());
			}
			configTransferenciaDTO.setContratoAceptado(contratoBO.buscarContratoAceptadoOld(
					cliente.getFechaNacimiento(), cliente.getDni(), CampoEnum.CPI_FCI_T, SinonimoEnum.NO_REPETIDO));
		} catch (DAOException e) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
		Respuesta<String> respuestaLegales = legalBO.buscarLegal(MENSAJE_LEGALES_INICIATIVA_PROPIA);
		if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		} else if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
			configTransferenciaDTO.setLegales(respuestaLegales.getRespuesta());
		}
		
		return respuestaFactory.crearRespuestaOk(ConfigTransferenciaDTO.class, configTransferenciaDTO);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.comun.bo.
	 * InversionesAbstractBO#getTipo()
	 */
	@Override
	protected String getTipo() {
		return EnumFondosDisponiblesTipoOperacion.TR.getCodigo();
	}

	/**
	 * Setear datos entrada transferencia fondo.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param cliente
	 *            the cliente
	 * @return the comprobante transferencia fondo in entity
	 * @throws BusinessException
	 *             the business exception
	 */
	public ComprobanteTransferenciaFondoInEntity setearDatosEntradaComprobanteTransferirFondo(
			TransferenciaDTO requestDTO, Cliente cliente) throws BusinessException {
		try {
			ComprobanteTransferenciaFondoInEntity requestEntity = new ComprobanteTransferenciaFondoInEntity();
			requestEntity.setCliente(cliente);
			requestEntity.setCodigoFondo(requestDTO.getCodigoFondo());
			requestEntity.setCodigoCliente(PREFIJO_COD_AGENTE
					+ StringUtils.leftPad(requestDTO.getCuentaTitulo().replace("/", ""), LARGO_CUENTA_TITULO, "0"));
//			String importeNeto = StringUtils.replaceChars(requestDTO.getImporteNeto(), ".", "");
//			importeNeto = StringUtils.replaceChars(importeNeto, ",", "");
			requestEntity.setImporteNeto(ISBANStringUtils.formatearImporteRescate(StringUtils.leftPad(requestDTO.getImporteNeto(), LARGO_IMPORTE_NETO, "0")));
			requestEntity.setCodigoFondoDest(requestDTO.getCodigoFondoDest());
			return requestEntity;
		} catch (Exception ex) {
			LOGGER.error("Error generando entity request para SuscripcionFondo. ", ex);
			throw new BusinessException();
		}
	}

	/**
	 * Cargar datos.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param respuestaDAO
	 *            the respuesta DAO
	 * @return the fondo DTO
	 */
	public TransferenciaDTO cargarDatosTransferencia(TransferenciaDTO requestDTO,
			ComprobanteTransferenciaFondoOutEntity respuestaDAO) {
		TransferenciaDTO dtoResponse = new TransferenciaDTO();
		String moneda = respuestaDAO.getDescripcionMoneda();
		if ("PESOS".equals(moneda)) {
			moneda = "$";
		} else {
			moneda = "u$s";
		}
		String saldo = ISBANStringUtils.formatearConComaYDosDecimales(requestDTO.getImporteNeto());
		dtoResponse.setImporteNeto(saldo);
		String mensaje = mensajeBO.obtenerMensajePorCodigo(SUSCRIPCION_TRANSACCION_CORRECTA).getMensaje();
		ConsultaFondoOutEntity descFondoOrigen=null;
		ConsultaFondoOutEntity descFondoDestino=null;
		try {
			descFondoOrigen = consultaFondoDAO.obtenerPorCodigo(requestDTO.getCodigoFondo());
			descFondoDestino = consultaFondoDAO.obtenerPorCodigo(requestDTO.getCodigoFondoDest());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		String mensajeSuscripcion = MessageFormat.format(mensaje, descFondoOrigen.getNombreFondo(),
				descFondoDestino.getNombreFondo(), moneda, dtoResponse.getImporteNeto());
		dtoResponse.setMensajeSuscripcion(mensajeSuscripcion);
		dtoResponse.setFechaHora(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
		dtoResponse.setNumeroCertificado(respuestaDAO.getNroCertificadoDestino());
		dtoResponse.setNumeroOperacion(respuestaDAO.getNroOperacion());
		return dtoResponse;
	}
	
	/**
	 * Formatear nombre de bonos.
	 *
	 * @param cadena
	 *            Cadena a foramtear
	 * @return the cadena
	 */
	public String convertirCadena(String cadena) {
		char[] caracteres = cadena.toCharArray();
		for (int i = 0; i < cadena.length() - 2; i++)
			if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
				caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);
		return new String(caracteres);
	}

	/**
	 * Manejar reintento.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param permiteReintentar
	 *            the permite reintentar
	 * @param e
	 *            the e
	 * @return the respuesta
	 */
	Respuesta<TransferenciaDTO> manejarReintentoTransferencia(TransferenciaDTO requestDTO, boolean permiteReintentar,
			DAOException e) {

		try {
			ConsultaFondoOutEntity fondoOrigen = consultaFondoDAO.obtenerPorCodigo(requestDTO.getCodigoFondo());
			ConsultaFondoOutEntity fondoDestino = consultaFondoDAO.obtenerPorCodigo(requestDTO.getCodigoFondoDest());

			Respuesta<TransferenciaDTO> respuesta;
			LOGGER.error("Error en transferenciaDAO metodo finalizarTransferirFondos. ", e);
			String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_TRANSFERENCIA_FALLO_GENERICO).getMensaje();
			String mensajeError = MessageFormat.format(mensaje, fondoOrigen.getNombreFondo(),
					fondoDestino.getNombreFondo());
			if (permiteReintentar) {
				respuesta = respuestaFactory.crearRespuestaError(TransferenciaDTO.class, mensajeError,
						TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_CON_REINTENTO.toString());
			} else {
				respuesta = respuestaFactory.crearRespuestaError(TransferenciaDTO.class, mensajeError,
						TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString());
			}
			return respuesta;

		} catch (DAOException er) {
			LOGGER.error("Error recuperando fondo Entity ", e);
			return respuestaFactory.crearRespuestaError(TransferenciaDTO.class, "", "");
		}

	}

	/**
	 * Manejar caso feliz.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @param respuestaDAO
	 *            the respuesta DAO
	 * @return the respuesta
	 */
	Respuesta<TransferenciaDTO> manejarCasoFelizTransferencia(TransferenciaDTO requestDTO,
			ComprobanteTransferenciaFondoOutEntity respuestaDAO) {
		TransferenciaDTO dtoResponse = cargarDatosTransferencia(requestDTO, respuestaDAO);
		return respuestaFactory.crearRespuestaOk(TransferenciaDTO.class, dtoResponse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.inversiones.fondos.bo.
	 * TransferenciaFondoBO#finalizarTransferirFondos(ar.com.santanderrio.obp.
	 * servicios.inversiones.fondos.dto.TransferenciaDTO,
	 * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
	 */
	@Override
	public Respuesta<TransferenciaDTO> finalizarTransferirFondos(TransferenciaDTO requestDTO, Cliente cliente) {
		boolean permiteReintentar;
		String segmento = Segmento.BANCA_PERSONAL.getCodigo();

		if (sessionParametros.getContador() != null) {
			permiteReintentar = sessionParametros.getContador().permiteReintentar();
		} else {
			LOGGER.debug("Error, Contador no inicializado!!");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}

		try {
			fondosTenenciasPrototype.cleanCacheObtenerTenenciaValuadaDetalleFondoOnline(cliente, segmento);
			ComprobanteTransferenciaFondoInEntity entity = setearDatosEntradaComprobanteTransferirFondo(requestDTO,
					cliente);
			ComprobanteTransferenciaFondoOutEntity respuestaDAO = transferenciaFondoDAO.obtenerComprobante(entity);
			cleanCacheHoldingBff(requestDTO.getCuentaTitulo(), cliente, segmento);

			return manejarCasoFelizTransferencia(requestDTO, respuestaDAO);
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new TransferenciaDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
		} catch (TimeOutException exc) {
			LOGGER.error("Error en BO timeOut. ", exc);
			return generarRespuestaTimeout(requestDTO);

		} catch (CuentaSinOperarException e) {
			LOGGER.error("Error en BO 180 dias cuentasinOperar. ", e);
			return manejarReintentoTransferencia(requestDTO, permiteReintentar, e);
		} catch (DAOException e) {
			return manejarReintentoTransferencia(requestDTO, permiteReintentar, e);
		} catch (BusinessException e) {
			LOGGER.error("Error convirtiendo view en DTO. ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Arma la respuesta para el error de timeout, debo recuperar los dos fondos
	 * para el mensaje al usuario.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * @return the respuesta
	 */
	private Respuesta<TransferenciaDTO> generarRespuestaTimeout(TransferenciaDTO requestDTO) {
		try {
			ConsultaFondoOutEntity fondoOrigen = consultaFondoDAO.obtenerPorCodigo(requestDTO.getCodigoFondo());
			ConsultaFondoOutEntity fondoDestino = consultaFondoDAO.obtenerPorCodigo(requestDTO.getCodigoFondoDest());
			String mensaje = mensajeBO.obtenerMensajePorCodigo(FINALIZAR_TRANSFERENCIA_FALLO_GENERICO).getMensaje();
			String mensajeError = MessageFormat.format(mensaje, fondoOrigen.getNombreFondo(),
					fondoDestino.getNombreFondo());
			return respuestaFactory.crearRespuestaError(TransferenciaDTO.class, mensajeError,
					TipoError.ERROR_FINALIZAR_TRANSACCION_FONDO_SIN_REINTENTO.toString());
		} catch (DAOException e) {
			LOGGER.error("Error recuperando fondo Entity ", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Crea al predicado para filtar los fondos por habilitados para
	 * transferencias de banca privada.
	 *
	 * @return the predicate
	 */
	private Predicate predicadoObtenerFondosHabilitadosBP() {

		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				ConsultaFondoOutEntity fondo = (ConsultaFondoOutEntity) object;
				return "1".equals(fondo.getHabilitarTransferencia())
						&& (fondo.getTipoBanca().equalsIgnoreCase(TipoBancaEnum.BANCA_PRIVADA.getCodigo())
								|| fondo.getTipoBanca().equalsIgnoreCase(TipoBancaEnum.AMBAS_BANCAS.getCodigo()));
			}
		};
	}

	/**
	 * Retorna un predicado que filtra las cuentas titulo que no tengan fondos
	 * suscriptos.
	 *
	 * @return the predicate
	 */
	private Predicate predicadoCuentaTituloSinFondos() {
		return new Predicate() {
			@Override
			public boolean evaluate(Object object) {
				CuentaTituloDTO cuenta = (CuentaTituloDTO) object;
				return !cuenta.getFondosSuscriptos().isEmpty();
			}
		};
	}

	/**
	 * Crea el DTO de respuesta a partir del resutado del DAO.
	 *
	 * @param out
	 *            the out
	 * @return the simulacion out DTO
	 */
	private SimularSuscripcionOutDTO createDTOout(SimulacionFondoBancaPrivadaOutEntity out) {
		SimularSuscripcionOutDTO simulacionOut = new SimularSuscripcionOutDTO();
		simulacionOut.setDentroDelPerfil(out.getDentroDelPerfil());
		simulacionOut.setDisclaimer(out.getDisclaimer());
		simulacionOut.setCuotasParte(out.getCuotasPartes());
		return simulacionOut;
	}

	/**
	 * Ver si va cliente o no Crea el objeto de entrada al DAO de transferencia
	 * a partir del DTO recibido, en codifo de operacion es retornado por cada
	 * clase concreta que extienda de esta por parametro.
	 *
	 * @param requestDTO
	 *            the request DTO
	 * 
	 * @param credential
	 *            the credential
	 * @return the simular suscripcion B priv in
	 * @throws BusinessException
	 *             the business exception
	 */
	private SimulacionFondoBancaPrivadaInEntity crearTransferenciaDAOin(SimulacionInDTO requestDTO, Credential credential)
			throws BusinessException {
		SimulacionFondoBancaPrivadaInEntity rta = new SimulacionFondoBancaPrivadaInEntity();
		rta.setCodigoOperacion(getTipo());

		String numeroCtaBpriv = requestDTO.getNroCuentaBancaPrivada();
		String resultadoFormateado = numeroCtaBpriv.substring(4, numeroCtaBpriv.length());
		rta.setCuentaTitulo(PREFIJO_CUENTA_TITULO
				+ llenarConCerosIzqOTruncar(resultadoFormateado.replaceAll("/", ""), LONGITUD_CUENTA));

		rta.setCapital(requestDTO.getImporte());
		rta.setUssRacf(credential.getUsuario());
		rta.setPassRacf(credential.getPassword());

		try {
			ConsultaFondoOutEntity rtaConsultaFondo = consultaFondoDAO
					.obtenerPorCodigo(StringUtils.right(requestDTO.getCodigoFondo(), LARGO_COD_FONDO));
			ConsultaFondoOutEntity rtaConsultaFondoDes = consultaFondoDAO
					.obtenerPorCodigo(StringUtils.right(requestDTO.getCodigoFondoDes(), LARGO_COD_FONDO));

			rta.setEspecie(rtaConsultaFondo.getEspecie());
			rta.setEspecieDestino(rtaConsultaFondoDes.getEspecie());

			if (DivisaEnum.PESO.getCodigo().equals(rtaConsultaFondo.getMoneda())) {
				rta.setMoneda(MONEDA_PESO);
			} else {
				rta.setMoneda(DivisaEnum.DOLAR.getCodigo());
			}
		} catch (DAOException e) {
			LOGGER.error("Error recuperando especie del fondo", e);
			throw new BusinessException(e);
		}
		return rta;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Respuesta<SimularSuscripcionOutDTO> simularTransferenciaBPriv(SimulacionInDTO requestDTO) {
		SimulacionFondoBancaPrivadaOutEntity out = new SimulacionFondoBancaPrivadaOutEntity();
		SimularSuscripcionOutDTO respuesta = new SimularSuscripcionOutDTO();
		try {
			out = fondoBPrivDAO.simularTransferenciaBPriv(crearTransferenciaDAOin(requestDTO, getRACFCredential()));
			respuesta = createDTOout(out);
			respuesta.setUrlReglamento(armarUrlReglamento(requestDTO.getCodigoFondoDes()));
			String idLegalInformacion = buscarIdLegalInformacion(requestDTO.getCodigoFondoDes());
			if (idLegalInformacion != null) {
				Respuesta<String> respuestaLegalesInformacion = legalBO.buscarLegal(idLegalInformacion);
				if (EstadoRespuesta.OK.equals(respuestaLegalesInformacion.getEstadoRespuesta())) {
					respuesta.setLegalesInformacion(respuestaLegalesInformacion.getRespuesta());
				}
			}
			Respuesta<String> respuestaTerminosYCondiciones = legalBO
					.buscarLegal(LEGAL_TERMINOS_COND_TRANSFERENCIAS_BANCA_PERSONAL);
			Respuesta<String> respuestaLegales = legalBO.buscarLegal(LEGAL_TRANSFERENCIA_BANCA_PERSONAL);
			if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)
					|| respuestaTerminosYCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			} else if (respuestaLegales.getEstadoRespuesta().equals(EstadoRespuesta.OK)
					&& respuestaTerminosYCondiciones.getEstadoRespuesta().equals(EstadoRespuesta.OK)) {
				respuesta.setLegales(respuestaLegales.getRespuesta());
				respuesta.setTerminosYCondiciones(respuestaTerminosYCondiciones.getRespuesta());
			}
			return respuestaFactory.crearRespuestaOk(SimularSuscripcionOutDTO.class, respuesta);
		} catch (FueraDeHorarioException e) {
			LOGGER.error("Error Fuera de horario. ", e);
			return respuestaFactory.crearRespuestaWarning(new SimularSuscripcionOutDTO(), "",
					TipoError.FUERADEHORARIO, CodigoMensajeConstantes.CODIGO_MENSAJE_FUERA_HORARIO_FONDOS);
		} catch (SimulacionDAOException er) {
			LOGGER.error("Error llamando a la simulacion", er);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		} catch (DAOException e) {
			LOGGER.error("Error llamando a simulacion de suscripcion", e);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.TENENCIA_RESCATE_FONDO_FALLO_GENERICO);
		} catch (BusinessException err) {
			LOGGER.error("Error llamando a simulacion de suscripcion", err);
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
		}
	}

	/**
	 * Obtiene los legales de informacion de el codigo pasado como parametro si
	 * no tiene legales de informacion retorna null.
	 *
	 * @param codigoFondo
	 *            the codigo fondo
	 * @return the string
	 * @throws DAOException
	 *             the DAO exception
	 */
	private String obtenerLegalesInformacion(String codigoFondo) throws DAOException {
		String idLegalInformacion = buscarIdLegalInformacion(codigoFondo);
		if (idLegalInformacion != null) {
			Respuesta<String> respuestaLegalesInformacion = legalBO.buscarLegal(idLegalInformacion);
			if (EstadoRespuesta.OK.equals(respuestaLegalesInformacion.getEstadoRespuesta())) {
				return respuestaLegalesInformacion.getRespuesta();
			}
		}
		return null;
	}

	private void cleanCacheHoldingBff(String cuentaTitulo, Cliente cliente, String segmento) {
		Boolean holdingsByBff = fundsApi.verifyAccessToGetHolding(cliente);
		if (holdingsByBff.equals(Boolean.TRUE)) {
			fundsApi.cleanCacheFunds(cuentaTitulo, cliente, TRANSFERENCIA, segmento);
			fondosTenenciasPrototype.cleanCacheObpNupHoldingFondosTenenciaBff(cliente);
			try {
				fundsApi.getHoldingBff(cliente);
			} catch (ApiException e) {
				LOGGER.error("Error al obtener las tenencias desde FONDOS-TENENCIA-BFF", e);
			} catch (Exception e) {
				LOGGER.error("Error al obtener las tenencias desde FONDOS-TENENCIA-BFF - Exeption");
			}
		}
	}

}
