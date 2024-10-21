package ar.com.santanderrio.obp.servicios.echeq.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ar.com.santanderrio.obp.servicios.echeq.entities.OperationEcheqResponse;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cesion;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.generated.webservices.echeq.ResponseFull;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.combos.bo.DatosSelectoresBO;
import ar.com.santanderrio.obp.servicios.comun.combos.dao.impl.DatosSelectoresDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO;
import ar.com.santanderrio.obp.servicios.echeq.common.ECheqAmcoEstados;
import ar.com.santanderrio.obp.servicios.echeq.common.EstadosBae;
import ar.com.santanderrio.obp.servicios.echeq.common.IOperacionECheq;
import ar.com.santanderrio.obp.servicios.echeq.common.OperacionEcheqFactory;
import ar.com.santanderrio.obp.servicios.echeq.common.TiposGrilla;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqAmcoDAO;
import ar.com.santanderrio.obp.servicios.echeq.dao.ECheqDAO;
import ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConfirmarOperacionOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ConsultaImporteTotalesInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ECheqDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.ECheqOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.GrillaECheqInDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.GrillaECheqOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.dto.TotalesECheqOutDTO;
import ar.com.santanderrio.obp.servicios.echeq.entities.BeneficiarioInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ConsultaImporteTotalesInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.DetalleECheqInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEmitidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqEndosadoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.ECheqRecibidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.entities.EcheqCedidoInEntity;
import ar.com.santanderrio.obp.servicios.echeq.enums.OperacionEcheqEnum;
import ar.com.santanderrio.obp.servicios.echeq.utils.ECheqUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ECheqBOImpl.
 */
@Component
public class ECheqBOImpl implements ECheqBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ECheqBOImpl.class);

    /** The Echeq Amco DAO. */
    @Autowired
    @Qualifier("eCheqAmcoDAOImpl")
	private ECheqAmcoDAO eCheqAmcoDAO;

	/** The Echeq DAO. */
	@Autowired
	private ECheqDAO eCheqDAO;

	/** The sesion cliente. */
	@Autowired
	private SesionCliente sesionCliente;

	/** The nup DAO. */
	@Autowired
	private NUPDAO nupDAO;

	/** The respuesta factory. */
	@Autowired
	private RespuestaFactory respuestaFactory;

	/** The sesion parametros. */
	@Autowired
	private SesionParametros sesionParametros;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The legal BO. */
    @Autowired
    private LegalBO legalBO;

    /** The selectores BO. */
    @Autowired
    private DatosSelectoresBO selectoresBO;

    /** The property map. */
	@Autowired
	private PropertyMap propertyMap;

	/** The cantidad registros por pagina default. */
	@Value("${ECHEQ.CANTIDAD.REGISTROS.POR.PAGINA}")
	private String cantidadRegistrosPorPaginaDefault;

	/** The cantidad dias aviso grilla. */
	@Value("${ECHEQ.CANTIDAD.DIAS.AVISO.GRILLA}")
	private int cantidadDiasAvisoGrilla;

	/** The cantidad dias offset fecha pago. */
	@Value("${ECHEQ.CANTIDAD.DIAS.OFFSET.FECHA.PAGO}")
	private int cantidadDiasOffsetFechaPago;

	/** The Constant SIMBOLO_PESOS. */
	private static final String SIMBOLO_PESOS = "$";

	/** The Constant EMPTY. */
	private static final String EMPTY = "";

	/** The Constant ORDERBY_FECHA_EMISION_DESC. */
	private static final String ORDERBY_FECHA_EMISION_DESC = "cheques.fecha_emision!";

	/** The Constant ORDERBY_FECHA_PAGO_DESC. */
	private static final String ORDERBY_FECHA_PAGO_DESC = "cheques.fecha_pago!";

	/** The Constant COD_RET_BENEFICIARIO_VALIDO. */
	private static final String COD_RET_BENEFICIARIO_VALIDO = "12500";

	/** The Constant COD_RET_BENEFICIARIO_INVALIDO. */
	private static final String[] COD_RET_BENEFICIARIO_INVALIDO = {"12501", "12502"};

	/** The Constant COD_RET_BENEFICIARIO_ERROR. */
	private static final String[] COD_RET_BENEFICIARIO_ERROR = {"12506", "12507", "12598", "12599"};

	/** The Constant COD_RET_BENEFICIARIO_INVALIDO_LST. */
	private static final List<String> COD_RET_BENEFICIARIO_INVALIDO_LST = Arrays.asList(COD_RET_BENEFICIARIO_INVALIDO);

	/** The Constant COD_RET_BENEFICIARIO_ERROR_LST. */
	private static final List<String> COD_RET_BENEFICIARIO_ERROR_LST = Arrays.asList(COD_RET_BENEFICIARIO_ERROR);

	/** The Constant COD_RET_OK. */
	private static final String COD_RET_OK_DETALLE = "12400";

	/** The Constant COD_RET_OK_LIST. */
	private static final String COD_RET_OK_LIST = "12400";

	/** The Constant COD_RET_OK_CONS_IMP. */
	private static final String COD_RET_OK_CONS_IMP = "20000";

	/** The Constant CANTIDAD_MONTOS_TOTALES. */
	private static final int CANTIDAD_MONTOS_TOTALES = 5;

	/** The Constant FINALIZADO_CON_ERROR. */
	private static final String FINALIZADO_CON_ERROR = "Finalizado con error";

	/** The Constant ACTUALIZANDO. */
	private static final String ACTUALIZANDO = "Actualizando";

	/** The Constant GUION. */
	private static final String GUION = "-";

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO#obtenerECheqs(ar.com.santanderrio.obp.servicios.echeq.dto.GrillaECheqInDTO)
	 */
	@Override
	public Respuesta<GrillaECheqOutDTO> obtenerECheqs(GrillaECheqInDTO grillaECheqInDTO) {
		List<ResponseFull> out;
		String codigoEstadistica = null;
		String tipoGrilla = null;
		try {
			if (grillaECheqInDTO.getIngresoDesdeEmitidos()) {
				tipoGrilla = TiposGrilla.EMITIDOS.getId();
				codigoEstadistica = EstadisticasConstants.CODIGO_CONSULTA_GRILLA_EMITIDOS_ECHEQ;
				ECheqEmitidoInEntity eCheqEmitidoInEntity = createEmitidoInEntity(grillaECheqInDTO);
				out = eCheqAmcoDAO.obtenerChequesEmitidos(eCheqEmitidoInEntity);
			} else if (grillaECheqInDTO.getIngresoDesdeEndosados()) {
				tipoGrilla = TiposGrilla.ENDOSADOS.getId();
				codigoEstadistica = EstadisticasConstants.CODIGO_CONSULTA_GRILLA_ENDOSADOS_ECHEQ;
				ECheqEndosadoInEntity eCheqEndosadoInEntity = createECheqEndosadoInEntity(grillaECheqInDTO);
				out = eCheqAmcoDAO.obtenerChequesEndosados(eCheqEndosadoInEntity);
			} else if (grillaECheqInDTO.getIngresoDesdeRecibidos()) {
				tipoGrilla = TiposGrilla.RECIBIDOS.getId();
				codigoEstadistica = EstadisticasConstants.CODIGO_CONSULTA_GRILLA_RECIBIDOS_ECHEQ;
				ECheqRecibidoInEntity eCheqRecibidoInEntity = createECheqRecibidoInEntity(grillaECheqInDTO);
				out = eCheqAmcoDAO.obtenerChequesRecibidos(eCheqRecibidoInEntity);
			} else if (grillaECheqInDTO.getIngresoDesdeCedidos()) {
				tipoGrilla = TiposGrilla.CEDIDOS.getId();
				codigoEstadistica = EstadisticasConstants.CODIGO_CONSULTA_GRILLA_CEDIDOS_ECHEQ;
				EcheqCedidoInEntity eCheqCedidoInEntity = createCedidosInEntity(grillaECheqInDTO);
				out = eCheqAmcoDAO.obtenerChequesCedidos(eCheqCedidoInEntity);
			}else {
				tipoGrilla = TiposGrilla.EN_PROCESO.getId();
				//TODO FALTA DEFINIR ESTADISTICA
				codigoEstadistica = EstadisticasConstants.CODIGO_CONSULTA_GRILLA_EN_PROCESO_ECHEQ;
				out = eCheqAmcoDAO.obtenerChequesEnProceso(sesionCliente.getCliente().getNroDocCnsDocXNup());
			}
		} catch (DAOException e) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
		}

		List<Cheque> echeqs = null;
		ResponseFull response = null;
		if (out != null && !out.isEmpty()) {
			response = out.get(0);
			if (COD_RET_OK_LIST.equals(response.getCode())) {
				echeqs = response.getCheques().getEcheq();
			} else {
				estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
				return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
			}
		} else {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
		}

		if (echeqs.isEmpty()) {
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
			return respuestaFactory.crearRespuestaWarning(EMPTY, TipoError.ECHEQ_WARNING_SIN_DATOS_GRILLA, EMPTY);
		}
        if (sesionParametros.getContador() == null) {
            sesionParametros.setContador(new ContadorIntentos(2));
        }

		GrillaECheqOutDTO grillaECheqOutDTO;
		try {
			grillaECheqOutDTO = crearGrillaECheqOutDTO(response, grillaECheqInDTO, tipoGrilla);
		} catch (RuntimeException e) {
			LOGGER.error("Error al obtener echeqs.", e);
			estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
		}
		estadisticaManager.add(codigoEstadistica, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
		return respuestaFactory.crearRespuestaOk(GrillaECheqOutDTO.class, grillaECheqOutDTO);
	}

	private ECheqEmitidoInEntity createEmitidoInEntity(GrillaECheqInDTO grillaECheqInDTO) {
		ECheqEmitidoInEntity eCheqEmitidoInEntity = new ECheqEmitidoInEntity();
		eCheqEmitidoInEntity.setCuit(sesionCliente.getCliente().getNroDocCnsDocXNup());
		eCheqEmitidoInEntity.setChequeNumero(grillaECheqInDTO.getNumeroCheque());
		eCheqEmitidoInEntity.setEmisorCuit(sesionCliente.getCliente().getNroDocCnsDocXNup());
		eCheqEmitidoInEntity.setBeneficiarioDocTipo(EMPTY);
		eCheqEmitidoInEntity.setBeneficiarioDocNro(grillaECheqInDTO.getCuitBuscador());
		eCheqEmitidoInEntity.setEmisorCbu(EMPTY);
		eCheqEmitidoInEntity.setEstado(grillaECheqInDTO.getEstado());
		eCheqEmitidoInEntity.setImporteDesde(EMPTY);
		eCheqEmitidoInEntity.setImporteHasta(EMPTY);
		eCheqEmitidoInEntity.setFechaEmisionDesde(ECheqUtils.obtenerFechaEmision((grillaECheqInDTO.getFechaEmisionDesde())));
		eCheqEmitidoInEntity.setFechaEmisionHasta(ECheqUtils.obtenerFechaEmision(grillaECheqInDTO.getFechaEmisionHasta()));
		eCheqEmitidoInEntity.setFechaPagoDesde(EMPTY);
		eCheqEmitidoInEntity.setFechaPagoHasta(EMPTY);
		eCheqEmitidoInEntity.setNroPagina(obtenerNumeroPagina(grillaECheqInDTO));
		eCheqEmitidoInEntity.setCantidadRegistroPagina(cantidadRegistrosPorPaginaDefault);
		eCheqEmitidoInEntity.setOrderby(ORDERBY_FECHA_PAGO_DESC);
		if (grillaECheqInDTO.getOrderFechaEmision()) {
			eCheqEmitidoInEntity.setOrderby(ORDERBY_FECHA_EMISION_DESC);
		}
		eCheqEmitidoInEntity.setjSessionId(grillaECheqInDTO.getjSessionId());
		return eCheqEmitidoInEntity;
	}

	private ECheqEndosadoInEntity createECheqEndosadoInEntity(GrillaECheqInDTO grillaECheqInDTO) {
		ECheqEndosadoInEntity eCheqEndosadoInEntity = new ECheqEndosadoInEntity();
		eCheqEndosadoInEntity.setCuit(sesionCliente.getCliente().getNroDocCnsDocXNup());
		eCheqEndosadoInEntity.setBeneficiarioDocTipo(EMPTY);
		eCheqEndosadoInEntity.setBeneficiarioDocNro(grillaECheqInDTO.getCuitBuscador());
		eCheqEndosadoInEntity.setFechaEmisionDesde(ECheqUtils.obtenerFechaEmision((grillaECheqInDTO.getFechaEmisionDesde())));
		eCheqEndosadoInEntity.setFechaEmisionHasta(ECheqUtils.obtenerFechaEmision(grillaECheqInDTO.getFechaEmisionHasta()));
		eCheqEndosadoInEntity.setFechaPagoDesde(EMPTY);
		eCheqEndosadoInEntity.setFechaPagoHasta(EMPTY);
		eCheqEndosadoInEntity.setEstado(grillaECheqInDTO.getEstado());
		eCheqEndosadoInEntity.setChequeNumero(grillaECheqInDTO.getNumeroCheque());
		eCheqEndosadoInEntity.setEmisorCuit(EMPTY);
		eCheqEndosadoInEntity.setCuitBeneficiarioOriginal(EMPTY);
		eCheqEndosadoInEntity.setNroPagina(obtenerNumeroPagina(grillaECheqInDTO));
		eCheqEndosadoInEntity.setCantidadRegistroPagina(cantidadRegistrosPorPaginaDefault);
		eCheqEndosadoInEntity.setOrderby(ORDERBY_FECHA_PAGO_DESC);
		if (grillaECheqInDTO.getOrderFechaEmision()) {
			eCheqEndosadoInEntity.setOrderby(ORDERBY_FECHA_EMISION_DESC);
		}
		eCheqEndosadoInEntity.setjSessionId(grillaECheqInDTO.getjSessionId());
		return eCheqEndosadoInEntity;
	}

	private ECheqRecibidoInEntity createECheqRecibidoInEntity(GrillaECheqInDTO grillaECheqInDTO) {
		ECheqRecibidoInEntity eCheqRecibidoInEntity = new ECheqRecibidoInEntity();
		eCheqRecibidoInEntity.setCuit(sesionCliente.getCliente().getNroDocCnsDocXNup());
		eCheqRecibidoInEntity.setChequeNumero(grillaECheqInDTO.getNumeroCheque());
		eCheqRecibidoInEntity.setEmisorCuit(grillaECheqInDTO.getCuitBuscador());
		eCheqRecibidoInEntity.setBeneficiarioDocTipo(EMPTY);
		eCheqRecibidoInEntity.setBeneficiarioDocNro(EMPTY);
		eCheqRecibidoInEntity.setEstado(grillaECheqInDTO.getEstado());
		eCheqRecibidoInEntity.setFechaEmisionDesde(ECheqUtils.obtenerFechaEmision((grillaECheqInDTO.getFechaEmisionDesde())));
		eCheqRecibidoInEntity.setFechaEmisionHasta(ECheqUtils.obtenerFechaEmision(grillaECheqInDTO.getFechaEmisionHasta()));
		eCheqRecibidoInEntity.setFechaPagoDesde(EMPTY);
		eCheqRecibidoInEntity.setFechaPagoHasta(EMPTY);
		eCheqRecibidoInEntity.setNroPagina(obtenerNumeroPagina(grillaECheqInDTO));
		eCheqRecibidoInEntity.setCantidadRegistroPagina(cantidadRegistrosPorPaginaDefault);
		eCheqRecibidoInEntity.setOrderby(ORDERBY_FECHA_PAGO_DESC);
		if (grillaECheqInDTO.getOrderFechaEmision()) {
			eCheqRecibidoInEntity.setOrderby(ORDERBY_FECHA_EMISION_DESC);
		}
		eCheqRecibidoInEntity.setjSessionId(grillaECheqInDTO.getjSessionId());
		return eCheqRecibidoInEntity;
	}

	/**
	 * Prepara filtro de consulta de echeqs cedidos
	 * @param grillaECheqInDTO
	 * @return
	 */
	private EcheqCedidoInEntity createCedidosInEntity(GrillaECheqInDTO grillaECheqInDTO) {

		EcheqCedidoInEntity echeqCedidosInEntity = new EcheqCedidoInEntity();

		echeqCedidosInEntity.setCuit(sesionCliente.getCliente().getNroDocCnsDocXNup());
		echeqCedidosInEntity.setChequeNumero(grillaECheqInDTO.getNumeroCheque() == null ? EMPTY : grillaECheqInDTO.getNumeroCheque());
		echeqCedidosInEntity.setEmisorCuit(grillaECheqInDTO.getCuitBuscador() == null ? EMPTY : grillaECheqInDTO.getCuitBuscador());
		echeqCedidosInEntity.setBeneficiarioDocTipo(EMPTY);
		echeqCedidosInEntity.setBeneficiarioDocNro(EMPTY);
		echeqCedidosInEntity.setEstado(grillaECheqInDTO.getEstado() == null ? EMPTY : grillaECheqInDTO.getEstado());
		echeqCedidosInEntity.setFechaEmisionDesde(grillaECheqInDTO.getFechaEmisionDesde() == null ? EMPTY : ECheqUtils.obtenerFechaEmision((grillaECheqInDTO.getFechaEmisionDesde())));
		echeqCedidosInEntity.setFechaEmisionHasta(grillaECheqInDTO.getFechaEmisionHasta() == null ? EMPTY : ECheqUtils.obtenerFechaEmision(grillaECheqInDTO.getFechaEmisionHasta()));
		echeqCedidosInEntity.setFechaPagoDesde(EMPTY);
		echeqCedidosInEntity.setFechaPagoHasta(EMPTY);
		echeqCedidosInEntity.setNroPagina(obtenerNumeroPagina(grillaECheqInDTO));
		echeqCedidosInEntity.setCantidadRegistroPagina(cantidadRegistrosPorPaginaDefault);
		echeqCedidosInEntity.setOrderby(ORDERBY_FECHA_PAGO_DESC);
		if (grillaECheqInDTO.getOrderFechaEmision()) {
			echeqCedidosInEntity.setOrderby(ORDERBY_FECHA_EMISION_DESC);
		}
		echeqCedidosInEntity.setjSessionId(grillaECheqInDTO.getjSessionId());

		return echeqCedidosInEntity;
	}

	private String obtenerNumeroPagina(GrillaECheqInDTO grillaECheqInDTO) {
		if (grillaECheqInDTO.getEsPrimerLLamado()) {
			sesionParametros.setNroPaginaEcheq(1);
		} else {
			sesionParametros.setNroPaginaEcheq(sesionParametros.getNroPaginaEcheq() + 1);
		}
		return String.valueOf(sesionParametros.getNroPaginaEcheq());
	}

	private GrillaECheqOutDTO crearGrillaECheqOutDTO(ResponseFull response, GrillaECheqInDTO grillaECheqInDTO, String tipoGrilla) {
		GrillaECheqOutDTO grillaECheqOutDTO = new GrillaECheqOutDTO();
		List<ECheqOutDTO> listaEcheqs = new ArrayList<ECheqOutDTO>();
		List<String> idECheqs = new ArrayList<String>();

		if (TiposGrilla.EN_PROCESO.getId().equals(tipoGrilla)) {
			obtenerEcheqsEnProceso(response.getCheques().getEcheq(), tipoGrilla, grillaECheqInDTO, idECheqs, listaEcheqs, grillaECheqOutDTO);
		} else {
			obtenerEcheqs(response.getCheques().getEcheq(), tipoGrilla, grillaECheqInDTO, idECheqs, listaEcheqs, grillaECheqOutDTO);
		}
		if (grillaECheqInDTO.getEsPrimerLLamado()) {
			sesionParametros.setIdECheqs(idECheqs);
		} else {
			sesionParametros.getIdECheqs().addAll(idECheqs);
		}
		grillaECheqOutDTO.setListaEcheqs(listaEcheqs);
		if (sesionParametros.getIdECheqs().size() < response.getTotalCheques().intValue()) {
			grillaECheqOutDTO.setHayMasRegistros(true);
		}
		return grillaECheqOutDTO;
	}

	private void obtenerEcheqsEnProceso(List<Cheque> eCheqs, String tipoGrilla, GrillaECheqInDTO grillaECheqInDTO,
			List<String> idECheqs, List<ECheqOutDTO> listaEcheqs, GrillaECheqOutDTO grillaECheqOutDTO) {
		for (Cheque echeq : eCheqs) {
			ECheqOutDTO eCheqOutDTO = new ECheqOutDTO();
			eCheqOutDTO.setEstado(ACTUALIZANDO);
			if (EstadosBae.F.getId().equals(echeq.getStatus().getBaeCCERegstatus())) {
				eCheqOutDTO.setEstado(FINALIZADO_CON_ERROR);
			}
			String fechaEmision = StringUtils.isBlank(echeq.getFechaEmision()) ? GUION : ECheqUtils.parsearFecha(echeq.getFechaEmision(), ECheqUtils.MASCARA_FECHA_FRONT);
			String chequeNumero = StringUtils.isBlank(echeq.getChequeNumero()) ? GUION : echeq.getChequeNumero();
			String fechaPago = StringUtils.isBlank(echeq.getFechaPago()) ? GUION : ECheqUtils.parsearFecha(echeq.getFechaPago(), ECheqUtils.MASCARA_FECHA_FRONT);

			eCheqOutDTO.setFechaPago(fechaPago);
			eCheqOutDTO.setFechaEmision(fechaEmision);
            eCheqOutDTO.setNumeroCheque(chequeNumero);
			eCheqOutDTO.setNombreBeneficiario(GUION);
			eCheqOutDTO.setCuitBeneficiario(GUION);
			eCheqOutDTO.setAvalistas(ECheqUtils.mapAvalistas(echeq.getAvalistas()));
			if (echeq.getEmitidoA() != null) {
				String nombre = StringUtils.isBlank(echeq.getEmitidoA().getBeneficiarioNombre()) ? GUION : echeq.getEmitidoA().getBeneficiarioNombre();
				String cuit = StringUtils.isBlank(echeq.getEmitidoA().getBeneficiarioDocumento()) ? GUION : ISBANStringUtils.agregarGuionesANumeroCuitCuil(echeq.getEmitidoA().getBeneficiarioDocumento());
				eCheqOutDTO.setNombreBeneficiario(nombre);
				eCheqOutDTO.setCuitBeneficiario(cuit);
			}
			if (StringUtils.isBlank(echeq.getMonto())) {
				eCheqOutDTO.setImporte(GUION);
				eCheqOutDTO.setMonedaSimbolo(EMPTY);
			} else {
				eCheqOutDTO.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(echeq.getMonto())));
				eCheqOutDTO.setMonedaSimbolo(SIMBOLO_PESOS);
			}
			listaEcheqs.add(eCheqOutDTO);
			idECheqs.add(echeq.getIntchequeId());
		}
	}

	/**
	 * Setea los echeqs de las grillas emitidos, endosados y recibidos
	 * @param eCheqs
	 * @param tipoGrilla
	 * @param idECheqs
	 * @param grillaECheqInDTO
	 * @param listaEcheqs
	 * @param grillaECheqOutDTO
	 *
	 */
	private void obtenerEcheqs(List<Cheque> eCheqs, String tipoGrilla, GrillaECheqInDTO grillaECheqInDTO,
			List<String> idECheqs, List<ECheqOutDTO> listaEcheqs, GrillaECheqOutDTO grillaECheqOutDTO) {

		for (Cheque echeq : eCheqs) {
			ECheqOutDTO eCheqOutDTO = new ECheqOutDTO();
			ECheqAmcoEstados eCheqAmcoEstados = ECheqAmcoEstados.getByEstado(tipoGrilla,
					echeq.getStatus().getBaeCCERegstatus(),
					echeq.getEstado(),
					echeq.isSolicitandoAcuerdo(),
					echeq.isChequeAcordado(),
					echeq.isCertificadoEmitido(),
					echeq.isCedido(),
					echeq.isCesionPendiente());
			eCheqOutDTO.setMonedaSimbolo(SIMBOLO_PESOS);
			eCheqOutDTO.setId(echeq.getIntchequeId());
			eCheqOutDTO.setAcciones(eCheqAmcoEstados.getAccionesHabilitadas(echeq, sesionCliente.getCliente()));
			validarMostrarAnularCED(echeq.getCesiones(), eCheqOutDTO.getAcciones(), sesionCliente.getCliente());
			eCheqOutDTO.setImporte(ISBANStringUtils.formatearSaldo(new BigDecimal(echeq.getMonto())));
			eCheqOutDTO.setEstado(eCheqAmcoEstados.getDescripcion());
			if ("CC".equals(echeq.getChequeTipo())) {
				eCheqOutDTO.setChequeTipo("Cheque común");
			} else if ("CPD".equals(echeq.getChequeTipo())){
				eCheqOutDTO.setChequeTipo("Cheque de pago diferido");
			}
			String caracter = echeq.getChequeCaracter().substring(0,1).toUpperCase() + echeq.getChequeCaracter().substring(1).toLowerCase();
			eCheqOutDTO.setChequeCaracter(caracter);
			if("0".equals(echeq.getChequeModo())) {
				eCheqOutDTO.setModalidad("No cruzado");
			} else if ("1".equals(echeq.getChequeModo())){
				eCheqOutDTO.setModalidad("Cruzado");
			}
			eCheqOutDTO.setDomicilioEmisor(obtenerStringSeparadoPorEspacioDomilio(echeq.getCuentaEmisora().getEmisorDomicilio()));
			eCheqOutDTO.setEntidadGirada(obtenerStringSeparadoPorEspacio(echeq.getCuentaEmisora().getBancoNombre()));
			eCheqOutDTO.setDomicilioPago(obtenerStringSeparadoPorEspacio(echeq.getCuentaEmisora().getSucursalDomicilio() + ", " + echeq.getCuentaEmisora().getSucursalProvincia()));
			eCheqOutDTO.setCmc7(echeq.getCmc7());
            eCheqOutDTO.setFechaEmision(ECheqUtils.parsearFecha(echeq.getFechaEmision(), ECheqUtils.MASCARA_FECHA_FRONT));
            eCheqOutDTO.setFechaPago(ECheqUtils.parsearFecha(echeq.getFechaPago(), ECheqUtils.MASCARA_FECHA_FRONT));
			eCheqOutDTO.setNumeroCheque(echeq.getChequeNumero());
			eCheqOutDTO.setEndosos(ECheqUtils.obtenerEndosos(echeq.getEndosos()));
			eCheqOutDTO.setCesiones(ECheqUtils.obtenerCesiones(echeq.getCesiones()));
			eCheqOutDTO.setAvalistas(ECheqUtils.mapAvalistas(echeq.getAvalistas()));
			if (grillaECheqInDTO.getIngresoDesdeRecibidos()) {
				setearEmisor(eCheqOutDTO, echeq);
				setearCuentaDeposito(eCheqOutDTO, echeq);
				eCheqOutDTO.setMensajeWarning(obtenerMensajeWarning(echeq));
			} else if (grillaECheqInDTO.getIngresoDesdeEmitidos()) {
				setearBeneficiario(eCheqOutDTO, echeq);
				setearCuentaDebito(eCheqOutDTO, echeq);
			} else {
				setearEmisor(eCheqOutDTO, echeq);
				setearBeneficiario(eCheqOutDTO, echeq);
			}
			setearLegal(grillaECheqOutDTO, echeq.getEstado(), grillaECheqInDTO.getIngresoDesdeEndosados(), grillaECheqInDTO.getIngresoDesdeEmitidos(), eCheqOutDTO);
			listaEcheqs.add(eCheqOutDTO);
			idECheqs.add(echeq.getIntchequeId());
		}
		if (TiposGrilla.EMITIDOS.getId().equalsIgnoreCase(tipoGrilla)) {
			String legalGrilla = grillaECheqOutDTO.getLegal();
			StringBuilder legal = new StringBuilder(legalGrilla != null ? legalGrilla.concat(ECheqUtils.BREAK) : "");
			legal.append(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGAL_EMITIDOS_PENDIENTE));
			grillaECheqOutDTO.setLegal(legal.toString());
		}
	}


	private void setearEmisor(ECheqOutDTO eCheqOutDTO, Cheque echeq) {
		if (echeq.getCuentaEmisora() != null) {
			eCheqOutDTO.setNombreEmisor(WordUtils.capitalizeFully(echeq.getCuentaEmisora().getEmisorRazonSocial()));
			eCheqOutDTO.setCuitEmisor(ISBANStringUtils.agregarGuionesANumeroCuitCuil(echeq.getCuentaEmisora().getEmisorCuit()));
		}
	}

	private void setearBeneficiario(ECheqOutDTO eCheqOutDTO, Cheque echeq) {
		if (echeq.getEmitidoA() != null) {
			eCheqOutDTO.setNombreBeneficiario(WordUtils.capitalizeFully(echeq.getEmitidoA().getBeneficiarioNombre()));
			eCheqOutDTO.setCuitBeneficiario(ISBANStringUtils.agregarGuionesANumeroCuitCuil(echeq.getEmitidoA().getBeneficiarioDocumento()));
		}
	}

	private void setearCuentaDebito(ECheqOutDTO eCheqOutDTO, Cheque echeq) {
		Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(echeq.getCuentaEmisora().getEmisorCbu());
		if (cuentaCliente != null) {
			eCheqOutDTO.setCuentaNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
			eCheqOutDTO.setCuentaTipo(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());
			eCheqOutDTO.setAlias(cuentaCliente.getAlias());
		}
	}

	private void setearCuentaDeposito(ECheqOutDTO eCheqOutDTO, Cheque echeq) {
		String cbu = null;
		if (!StringUtils.isBlank(echeq.getCbuDeposito())) {
			cbu = echeq.getCbuDeposito();
		} else if (StringUtils.isBlank(echeq.getCbuDeposito()) && !StringUtils.isBlank(echeq.getCbuCustodia())) {
			cbu = echeq.getCbuCustodia();
		} else {
			return;
		}
		Cuenta cuentaCliente = sesionCliente.getCliente().getCuenta(cbu);
		if (cuentaCliente != null) {
			eCheqOutDTO.setCuentaNumero(ISBANStringUtils.formatearNroCuentaProductoConSucursal(cuentaCliente));
			eCheqOutDTO.setCuentaTipo(TipoCuenta.fromCodigo(cuentaCliente.getTipoCuenta()).getDescripcion());
			eCheqOutDTO.setAlias(cuentaCliente.getAlias());
		}
	}

	/**
	 * Setea el mensaje legal en el atributo GrillaECheqOutDTO.legal, una vez seteado no lo vuelve a buscar
	 * Setea si corresponde mostrar legal en el stack de detalle en la variable ECheqOutDTO.setAplicaLegal
	 *
	 * @param grillaECheqOutDTO
	 * @param estado
	 * @param ingresoDesdeEndosados
	 * @param ingresoDesdeEmitidos
	 * @param eCheqOutDTO
	 */
	private void setearLegal(GrillaECheqOutDTO grillaECheqOutDTO, String estado, Boolean ingresoDesdeEndosados, Boolean ingresoDesdeEmitidos, ECheqOutDTO eCheqOutDTO) {
		boolean setLegal = false;
		if (ingresoDesdeEmitidos &&
				(ECheqAmcoEstados.ACTIVO_PENDIENTE_EMITIDOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.ACTIVO_EMITIDOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.CUSTODIA_EMITIDOS_E.getEstadoAmco().equals(estado))) {
			eCheqOutDTO.setAplicaLegal(true);
			if (grillaECheqOutDTO.getLegal() == null) {
				setLegal = true;
			}
		} else if (ingresoDesdeEndosados &&
				(ECheqAmcoEstados.ACTIVO_PENDIENTE_ENDOSADOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.ACTIVO_ENDOSADOS_E.getEstadoAmco().equals(estado)
						|| ECheqAmcoEstados.CUSTODIA_ENDOSADOS_E.getEstadoAmco().equals(estado))) {
			eCheqOutDTO.setAplicaLegal(true);
			if (grillaECheqOutDTO.getLegal() == null) {
				setLegal = true;
			}
		}
		if (setLegal) {
			grillaECheqOutDTO.setLegal(legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGAL_EMITIDOS));
		}
	}

	private String obtenerMensajeWarning(Cheque echeq) {
		String retorno = null;
		if (ECheqAmcoEstados.ACTIVO_PENDIENTE_RECIBIDOS_E.getEstadoAmco().equals(echeq.getEstado())
				|| ECheqAmcoEstados.EMITIDO_PENDIENTE_RECIBIDOS_E.getEstadoAmco().equals(echeq.getEstado())) {
			if (aplicaWarning(echeq.getFechaPago())) {
				retorno = mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ECHEQ_AVISO_GRILLA).getMensaje();
			}
		}
		return retorno;
	}

	private boolean aplicaWarning(String fechaPago) {
		SimpleDateFormat myFormat = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_AMCO);
		Boolean retorno = false;
		Calendar calHoy = ECheqUtils.getCalendar(new Date());
	    String fechaAmco = fechaPago.substring(0, ECheqUtils.LEN_FECHA_AMCO);
		try {
			Date fechaPagoDate = myFormat.parse(fechaAmco);
			Calendar calPago = ECheqUtils.getCalendar(fechaPagoDate);
			calPago.add(Calendar.DATE, cantidadDiasOffsetFechaPago);
			int diferenciaDeDias = (int) ISBANStringUtils.difDiasEntre2fechas(calPago.getTime(), calHoy.getTime());
			if (diferenciaDeDias >= 0 && diferenciaDeDias <= cantidadDiasAvisoGrilla) {
				retorno = true;
			}
		} catch (ParseException e) {
            LOGGER.error("Error al parsear fecha: {}", fechaAmco, e);
			e.printStackTrace();
		}
		return retorno;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO#obtenerTotales(ar.com.santanderrio.obp.servicios.echeq.dto.ConsultaImporteTotalesInDTO)
	 */
	@Override
	public Respuesta<TotalesECheqOutDTO> obtenerTotales(ConsultaImporteTotalesInDTO consultaImporteTotalesInDTO) {
		Cliente cliente = sesionCliente.getCliente();
		ConsultaImporteTotalesInEntity consultaImporteTotalesInEntity = new ConsultaImporteTotalesInEntity(
				consultaImporteTotalesInDTO.getCuit(), consultaImporteTotalesInDTO.getRangoFechaEmision(),
				TipoDocumentoEnum.obtenerTipoDocumento(cliente.getTipoDocCnsDocXNup()).getDescripcion(), cliente.getNroDocCnsDocXNup(),
				consultaImporteTotalesInDTO.getjSessionId());
		try {
			List<ResponseFull> response = this.eCheqAmcoDAO.consultaImporteTotales(consultaImporteTotalesInEntity);
			if (COD_RET_OK_CONS_IMP.equals(response.get(0).getCode())) {
				TotalesECheqOutDTO totalesECheqOutDTO = new TotalesECheqOutDTO();
				totalesECheqOutDTO.setMonedaSimbolo(SIMBOLO_PESOS);
				int cantRegistrosParseados = 0;
				if (response.get(0).getMontoTotalEmitPendRecepcion() != null) {
					totalesECheqOutDTO.setEmitidosPendientesDeAceptacion(ISBANStringUtils.formatearSaldo(new BigDecimal(response.get(0).getMontoTotalEmitPendRecepcion())));
					cantRegistrosParseados++;
				}
				if (response.get(0).getMontoTotalCustodia() != null) {
					totalesECheqOutDTO.setEnCustodia(ISBANStringUtils.formatearSaldo(new BigDecimal(response.get(0).getMontoTotalCustodia())));
					cantRegistrosParseados++;
				}
				if (response.get(0).getMontoTotalRecibPendRecepcion() != null) {
					totalesECheqOutDTO.setRecibidosPendientesDeAceptacion(ISBANStringUtils.formatearSaldo(new BigDecimal(response.get(0).getMontoTotalRecibPendRecepcion())));
					cantRegistrosParseados++;
				}
				if (response.get(0).getMontoTotalEmitido() != null) {
					totalesECheqOutDTO.setTotalEmitidos(ISBANStringUtils.formatearSaldo(new BigDecimal(response.get(0).getMontoTotalEmitido())));
					cantRegistrosParseados++;
				}
				if (response.get(0).getMontoTotalRecibido() != null) {
					totalesECheqOutDTO.setTotalRecibidos(ISBANStringUtils.formatearSaldo(new BigDecimal(response.get(0).getMontoTotalRecibido())));
					cantRegistrosParseados++;
				}
				if (CANTIDAD_MONTOS_TOTALES == cantRegistrosParseados) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_TOTALES_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
				} else {
					estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_TOTALES_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);
				}
				return respuestaFactory.crearRespuestaOk(TotalesECheqOutDTO.class, totalesECheqOutDTO);
			}
		} catch (DAOException e) {
			LOGGER.error("No se pueden obtener los totales para cabecera.", e);
		}
		estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_TOTALES_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
	}

	private Cheque obtenerEcheq(String chequeId, String jSessionId) {
		ECheqDTO echeqDTO = sesionParametros.getEcheqEstadoValidado();
		if (echeqDTO != null && echeqDTO.getEcheqValidado() != null && chequeId.equals(echeqDTO.getEcheqValidado().getIntchequeId())) {
			LOGGER.info("Recuperando echeq desde session {}", echeqDTO.getEcheqValidado().getIntchequeId());
			return echeqDTO.getEcheqValidado();
		}
		return obtenerDetalle(chequeId, jSessionId);
	}

	private Boolean existeECheqEnSesion(String idEcheq) {
		for (String idECheqSesion : sesionParametros.getIdECheqs()) {
			if (idECheqSesion.equals(idEcheq)) {
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO#obtenerCuit()
	 */
	@Override
	public Respuesta<Void> obtenerCuit() {
        Cliente cliente = sesionCliente.getCliente();
        try {
            if (cliente.getNroDocCnsDocXNup() == null || cliente.getNroDocCnsDocXNup().isEmpty()) {
                NupDTO nupDTO = nupDAO.obtenerNUP(cliente);
                String nroDoc = nupDTO.getCuit(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
                String tipoDoc = nupDTO.getTipoDocumento(NupDTO.TIPO_DOC_CUIT, NupDTO.TIPO_DOC_CUIL, NupDTO.TIPO_DOC_CDI);
                if (nroDoc.isEmpty()) {
                    return respuestaFactory.crearRespuestaError(EMPTY, TipoError.DATOS_INVALIDOS, EMPTY);
                }
                sesionCliente.getCliente().setNroDocCnsDocXNup(nroDoc);
                sesionCliente.getCliente().setTipoDocCnsDocXNup(tipoDoc);
            }
            return respuestaFactory.crearRespuestaOk(Void.class);
        } catch (DAOException e) {
            LOGGER.error("No se puede obtener el cuit para el cliente {}.", cliente.getNup(), e);
    		return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
        }
    }

	private Cheque obtenerDetalle(String chequeId, String jSessionId) {
		DetalleECheqInEntity detalleECheqInEntity = new DetalleECheqInEntity(chequeId, sesionCliente.getCliente().getNroDocCnsDocXNup(), jSessionId);
		try {
			List<ResponseFull> out = this.eCheqAmcoDAO.obtenerDetalle(detalleECheqInEntity);
			ResponseFull response = out.get(0);
			if (COD_RET_OK_DETALLE.equals(response.getCode())) {
	            estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_DETALLE_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
	            return response.getCheques().getEcheq().get(0);
			} else {
				estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_DETALLE_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
			}
		} catch (DAOException e) {
            estadisticaManager.add(EstadisticasConstants.CODIGO_CONSULTA_DETALLE_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO#validarBeneficiario(ar.com.santanderrio.obp.servicios.echeq.dto.BeneficiarioDTO)
	 */
	@Override
	public Respuesta<BeneficiarioDTO> validarBeneficiario(BeneficiarioDTO beneficiarioInDTO) {
		try {
			BeneficiarioInEntity beneficiarioInEntity = new BeneficiarioInEntity();
			beneficiarioInEntity.setDocumento(beneficiarioInDTO.getDocumento());
			beneficiarioInEntity.setTipoDocumento(beneficiarioInDTO.getTipoDocumento());
			beneficiarioInEntity.setjSessionId(beneficiarioInDTO.getjSessionId());
			List<ResponseFull> ret = eCheqAmcoDAO.verificarClienteBancario(beneficiarioInEntity);
			if (ret != null && !ret.isEmpty()) {
				ResponseFull response = ret.get(0);
				String codRetorno = response.getCode();
				if (COD_RET_BENEFICIARIO_VALIDO.equals(codRetorno)
						&& StringUtils.isNotEmpty(response.getBeneficiarioDocumento())
						&& StringUtils.isNotEmpty(response.getBeneficiarioDocumentoTipo())
						&& StringUtils.isNotEmpty(response.getBeneficiarioRazonSocial())) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_VALIDACION_BENEFICIARIO_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
					BeneficiarioDTO beneficiarioOutDTO = new BeneficiarioDTO(beneficiarioInDTO.getDocumento(), beneficiarioInDTO.getTipoDocumento(),
							response.getBeneficiarioDocumento(), response.getBeneficiarioDocumentoTipo(),
							ISBANStringUtils.formateoStringPrimeraLetraMayuscula(response.getBeneficiarioRazonSocial()));
					return respuestaFactory.crearRespuestaOk(BeneficiarioDTO.class, beneficiarioOutDTO);
				} else if (COD_RET_BENEFICIARIO_INVALIDO_LST.contains(codRetorno)) {
					estadisticaManager.add(EstadisticasConstants.CODIGO_VALIDACION_BENEFICIARIO_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
					LOGGER.error("Beneficiario no valido - Documento {} : {}.", beneficiarioInDTO.getDocumento(), codRetorno);
					return respuestaFactory.crearRespuestaWarning(EMPTY, TipoError.ECHEQ_WARNING_VALIDAR_BENEFICIARIO, CodigoMensajeConstantes.ECHEQ_WARNING_VALIDAR_BENEFICIARIO);
				} else if (COD_RET_BENEFICIARIO_ERROR_LST.contains(codRetorno)) {
					LOGGER.error("Error al validar el beneficiario con documento {} : {}.", beneficiarioInDTO.getDocumento(), codRetorno);
				} else {
					LOGGER.error("Error al validar el beneficiario con documento {} : {}.", beneficiarioInDTO.getDocumento(), codRetorno);
				}
			} else {
				LOGGER.error("Error general al validar el beneficiario con documento {}.", beneficiarioInDTO.getDocumento());
			}
		} catch (DAOException e) {
			LOGGER.error("No se puede validar el beneficiario con documento {}.", beneficiarioInDTO.getDocumento(), e);
		}
		estadisticaManager.add(EstadisticasConstants.CODIGO_VALIDACION_BENEFICIARIO_ECHEQ, EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
		return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
	}

	private String getTipoGrilla(boolean ingresoDesdeEmitidos, boolean ingresoDesdeEndosados, boolean ingresoDesdeRecibidos, boolean ingresoDesdeCedidos) {
		if (ingresoDesdeEmitidos) {
			return TiposGrilla.EMITIDOS.getId();
		} else if (ingresoDesdeEndosados) {
			return TiposGrilla.ENDOSADOS.getId();
		} else if (ingresoDesdeRecibidos) {
			return TiposGrilla.RECIBIDOS.getId();
		} else if(ingresoDesdeCedidos) {
			return TiposGrilla.CEDIDOS.getId();
		}
		return "";
	}

	private boolean validarEstado(Cheque echeq, String tipoGrilla, OperacionEcheqEnum operacion) {
		if (echeq != null) {
			ECheqAmcoEstados eCheqAmcoEstados = ECheqAmcoEstados.getByEstado(tipoGrilla,
					echeq.getStatus().getBaeCCERegstatus(),
					echeq.getEstado(),
					echeq.isSolicitandoAcuerdo(),
					echeq.isChequeAcordado(),
					echeq.isCertificadoEmitido(),
					echeq.isCedido(),
					echeq.isCesionPendiente());
			List<String> accionesHasbilitadas = eCheqAmcoEstados.getAccionesHabilitadas(echeq, sesionCliente.getCliente());
			validarMostrarAnularCED(echeq.getCesiones(), accionesHasbilitadas, sesionCliente.getCliente());
			return accionesHasbilitadas.contains(operacion.getAccion());
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO#validarEstado(java.lang.String, java.lang.String, java.lang.String, ar.com.santanderrio.obp.servicios.echeq.entities.OperacionEcheqEnum)
	 */
	@Override
	public Respuesta<Void> validarEstado(String chequeId, String tipoGrilla, String jSessionId, OperacionEcheqEnum operacion) {
		ECheqDTO echeqDTO = sesionParametros.getEcheqEstadoValidado();
		Cheque echeq = null;
		if (echeqDTO == null || echeqDTO.getEcheqValidado() == null
				|| !chequeId.trim().equals(echeqDTO.getEcheqValidado().getIntchequeId().trim())
				|| OperacionEcheqEnum.VER_DETALLE.equals(operacion)) {
			echeq = obtenerDetalle(chequeId, jSessionId);
		}
		sesionParametros.setEcheqEstadoValidado(null);
		if (validarEstado(echeq, tipoGrilla, operacion)) {
			echeqDTO = new ECheqDTO();
			echeqDTO.setEcheqValidado(echeq);
			sesionParametros.setEcheqEstadoValidado(echeqDTO);
			return respuestaFactory.crearRespuestaOk(Void.class);
		}
		return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, EMPTY);
	}

    /* (non-Javadoc)
     * @see ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO#obtenerDetalle(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Respuesta<Cheque> obtenerDetalle(String chequeId, String numeroCUILCUIT, String jSessionId) {
        DetalleECheqInEntity detalleECheqInEntity = new DetalleECheqInEntity(chequeId, numeroCUILCUIT, jSessionId);
        try {
        	List<ResponseFull> out = this.eCheqAmcoDAO.obtenerDetalle(detalleECheqInEntity);
        	ResponseFull response = out.get(0);
            if (COD_RET_OK_DETALLE.equals(response.getCode())) {
               return respuestaFactory.crearRespuestaOk(response.getCheques().getEcheq().get(0));
            }
        } catch (DAOException e) {
            LOGGER.error("Error al buscar cheque en ws.-");
        }
        return respuestaFactory.crearRespuestaError(StringUtils.EMPTY,TipoError.ERROR_GENERICO,StringUtils.EMPTY);
    }

    public Respuesta<ConfirmarOperacionOutDTO> confirmarOperacion(final IOperacionECheq operacion,
			ConfirmarOperacionInDTO confirmarOperacionInDTO, Cliente cliente) {
        Cheque echeq = null;
        String tipoGrilla = "";
        if (confirmarOperacionInDTO.getId() != null) {
	        echeq = obtenerEcheq(confirmarOperacionInDTO.getId(), confirmarOperacionInDTO.getjSessionId());
	        // Verifica echeq valido y operacion aplicable al estado
	        tipoGrilla = getTipoGrilla(confirmarOperacionInDTO.getIngresoDesdeEmitidos(), confirmarOperacionInDTO.getIngresoDesdeEndosados(),
					confirmarOperacionInDTO.getIngresoDesdeRecibidos(), confirmarOperacionInDTO.getIngresoDesdeCedidos());
			if (echeq == null || !existeECheqEnSesion(confirmarOperacionInDTO.getId())
					|| !validarEstado(echeq, tipoGrilla, confirmarOperacionInDTO.getOperacion())) {
				return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, CodigoMensajeConstantes.ECHEQ_FEEDBACK_ERROR_ECHEQ_BY_ID);
			}
        }

		try {
			cargarCuentasHabilitadas(confirmarOperacionInDTO.getOperacion(), cliente, operacion);
			operacion.setOperationContext(echeq, cliente, confirmarOperacionInDTO);
			operacion.execute();
        } catch (DAOException e) {
            LOGGER.error(e.getMessage());
            sesionParametros.setEcheqEstadoValidado(null);
            return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, operacion.getMensajeErrorTimeOut(confirmarOperacionInDTO));
        } catch (ParseException e1) {
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ERROR_GENERICO, operacion.getMensajeError(null));
		}

		final OperationEcheqResponse operationResult = operacion.getOperationResult();
		if (!operationResult.getStatus()) {
			return respuestaFactory.crearRespuestaError(EMPTY, TipoError.ECHEQ_ERROR_CONFIRMAR_OPERACION,
				operacion.getMensajeError(operationResult.getIatxErrorCode()));
		}

        ConfirmarOperacionOutDTO confirmarOperacionOutDTO = new ConfirmarOperacionOutDTO();
		confirmarOperacionOutDTO.setMensajeFeedback(operacion.getMensajeOk(confirmarOperacionInDTO, mensajeBO));
        if (operacion.tieneComprobante()) {
        	String legales = obtenerLegales(confirmarOperacionInDTO.getOperacion());
        	SimpleDateFormat sdf = new SimpleDateFormat(ECheqUtils.MASCARA_FECHA_COMPROBANTE);
        	confirmarOperacionOutDTO.setFechaComprobante(sdf.format(new Date()));
        	confirmarOperacionOutDTO.setNumeroComprobante(operationResult.getNumeroComprobante());
        	confirmarOperacionOutDTO.setLegales(legales);
        	ComprobanteECheqDTO comprobante = generarComprobante(operacion, echeq,
        			confirmarOperacionInDTO, operationResult.getNumeroComprobante(), legales);
        	if(OperacionEcheqEnum.EMITIR_CED.getAccion().equals(confirmarOperacionInDTO.getOperacion().getAccion()))
        		comprobante.setEstado(obtenerEstadoDesdeEstadoAMCO(confirmarOperacionInDTO, echeq, tipoGrilla));
        	sesionParametros.setComprobanteEcheqDTO(comprobante);
        } else {
        	sesionParametros.setComprobanteEcheqDTO(null);
        	sesionParametros.setEcheqEstadoValidado(null);
        }

    	if(!operacion.tieneComprobante() && !StringUtils.isEmpty(operationResult.getNumeroComprobante())) {
        	confirmarOperacionOutDTO.setNumeroComprobante(operationResult.getNumeroComprobante());
    	}
		return respuestaFactory.crearRespuestaOk(ConfirmarOperacionOutDTO.class, confirmarOperacionOutDTO);
    }

    /**
     * Obtiene el estado del echeq para mostrar en el comprobante
     * @param confirmarOperacionInDTO
     * @param echeq
     * @param tipoGrilla
     * @return
     */
    private String obtenerEstadoDesdeEstadoAMCO(ConfirmarOperacionInDTO confirmarOperacionInDTO, Cheque echeq, String tipoGrilla) {
    	ECheqAmcoEstados eCheqAmcoEstados = ECheqAmcoEstados.getByEstado(tipoGrilla,
				echeq.getStatus().getBaeCCERegstatus(),
				echeq.getEstado(),
				echeq.isSolicitandoAcuerdo(),
				echeq.isChequeAcordado(),
				echeq.isCertificadoEmitido(),
				echeq.isCedido(),
				echeq.isCesionPendiente());
	    return eCheqAmcoEstados.getDescripcion();
    }

	private String obtenerLegales(OperacionEcheqEnum operacion) {
		if (OperacionEcheqEnum.ALTA.equals(operacion)) {
			return legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGAL_ALTA);
		} else if (OperacionEcheqEnum.ENDOSAR.equals(operacion)) {
			return legalBO.obtenerLegalesPorCodigo(CodigoMensajeConstantes.ECHEQ_LEGAL_ENDOSAR);
		}
		return "";
	}

	private ComprobanteECheqDTO generarComprobante(IOperacionECheq operacion, Cheque echeq,
			ConfirmarOperacionInDTO confirmarOperacionInDTO, String nroComprobante, String legales) {
		if (OperacionEcheqEnum.ALTA.equals(confirmarOperacionInDTO.getOperacion())) {
			confirmarOperacionInDTO.setModalidad(selectoresBO.obtenerDescripcionPorOptionId(DatosSelectoresDAOImpl.TAG_MODALIDADES_ECHEQ, confirmarOperacionInDTO.getModalidad()));
		}
		if (OperacionEcheqEnum.ENDOSAR.equals(confirmarOperacionInDTO.getOperacion())) {
			confirmarOperacionInDTO.setTipoEndoso(obtenerDescTipoEndosoXModalidad(confirmarOperacionInDTO.getModalidad(), confirmarOperacionInDTO.getTipoEndoso()));
		}
		return operacion.generarComprobante(echeq, confirmarOperacionInDTO, nroComprobante, legales);
	}

	private String obtenerDescTipoEndosoXModalidad(String modalidad, String idEndoso) {
		if(ECheqUtils.MODALIDAD_CHEQUE_A_LA_ORDEN.equalsIgnoreCase(selectoresBO.obtenerIdOpcionPorDescripcion(DatosSelectoresDAOImpl.TAG_MODALIDADES_ECHEQ, modalidad)))
			return selectoresBO.obtenerDescripcionPorOptionId(DatosSelectoresDAOImpl.TAG_TIPOS_ENDOSO_ECHEQ_A_LA_ORDEN, idEndoso);
		else
			return selectoresBO.obtenerDescripcionPorOptionId(DatosSelectoresDAOImpl.TAG_TIPOS_ENDOSO_ECHEQ_NO_A_LA_ORDEN, idEndoso);
	}

	private void cargarCuentasHabilitadas(OperacionEcheqEnum op, Cliente cliente, IOperacionECheq operacion) {
		if (ECheqUtils.verificarCuentasHabilitadas(op)) {
        	List<Cuenta> cuentasHabilitadas;
        	if (OperacionEcheqEnum.ALTA.equals(op)) {
        		cuentasHabilitadas = sesionParametros.getCuentasHabilitadasAltaEcheq();
        	} else {
        		String tipoCuentasHabilitadasPrefix = OperacionEcheqFactory.getTipoCuentasHabilitadasPrefix(op);
        		cuentasHabilitadas = ECheqUtils.obtenerCuentasHabilitadas(cliente, null,
    					ECheqUtils.getParamsCuentasHabilitadas(tipoCuentasHabilitadasPrefix, propertyMap));
        	}
			operacion.cargarCuentasHabilitadas(cuentasHabilitadas);
        }
	}

	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.echeq.bo.ECheqBO#descargarComprobante(ar.com.santanderrio.obp.servicios.echeq.dto.ComprobanteECheqDTO)
	 */
	@Override
	public Respuesta<Reporte> descargarComprobante(ComprobanteECheqDTO comprobanteDTO) {
		LOGGER.debug("ECheq - Iniciando generacion de comprobante");
		Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
		try {
			Reporte reporte = eCheqDAO.descargarComprobante(comprobanteDTO);
			respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
			respuestaReporte.setRespuesta(reporte);
		} catch (Exception e) {
			return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_DESCARGAR_COMPROBANTE,
					CodigoMensajeConstantes.PAGO_COMPRAS_DESCARGA_COMPROBANTE_ERROR);
		}
		LOGGER.debug("ECheq - Finalizando generacion de comprobante");
		return respuestaReporte;
	}

	private String obtenerStringSeparadoPorEspacio(String nombreBanco) {
		List<String> valores  = new ArrayList<String>();
		for (String x : nombreBanco.split(" ")) {
			if(x.length() > 0 ){
				valores.add(x);
			}
		}
		String domicilio = "";
		for (String c : valores) {
			domicilio += c + " ";
		}
		return domicilio;
	}

	@Override
	public Respuesta<ResponseFull> obtenerListadoEntidadesHabilitadas(String jsessionid) {
		try {
			List<ResponseFull> response = eCheqAmcoDAO.obtenerListaEntidadesHabilitadas(jsessionid);
			if(response != null && !response.isEmpty()){
				return respuestaFactory.crearRespuestaOk(response.get(0));
			} else {
				return respuestaFactory.crearRespuestaOk(null);
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage());
		}
		return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");
	}


	private String obtenerStringSeparadoPorEspacioDomilio(String domicilio) {
		List<String> valores  = new ArrayList<String>();
		for (String x : domicilio.split(" ")) {
			if(x.length() > 0 ){
				if(sePuedeConvertirEnNumero(x)){
					valores.add(ISBANStringUtils.sacarCerosYBlancosIzq(x));
				} else {
					valores.add(x);

				}
			}
		}
		String domicilioParseado = "";
		for (String c : valores) {
			domicilioParseado += c + " ";
		}
		return domicilioParseado;
	}

	private Boolean sePuedeConvertirEnNumero(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private void validarMostrarAnularCED(List<Cesion> cesiones, List<String> accionesHasbilitadas, Cliente cliente) {
		if(cesiones != null && !cesiones.isEmpty()) {
			Iterator<String> itAcciones = accionesHasbilitadas.iterator();
			while(itAcciones.hasNext()) {
				String accion = itAcciones.next();
				if(OperacionEcheqEnum.ANULAR_CED.getAccion().equals(accion)) {
					if(!cliente.getNroDocCnsDocXNup().equals(cesiones.get(0).getCedenteDocumento())) {
						itAcciones.remove();
					}
				}
			}
		}
	}
}
