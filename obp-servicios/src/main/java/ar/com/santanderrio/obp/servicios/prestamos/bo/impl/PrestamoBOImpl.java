/*
 *
 */
package ar.com.santanderrio.obp.servicios.prestamos.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.prestamos.BCRAResponse;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf158770343432493182NillableInfoRequeridaWS;
import ar.com.santanderrio.obp.generated.webservices.productos.ArrayOf324931821440052060NillableString;
import ar.com.santanderrio.obp.generated.webservices.productos.InfoRequeridaWS;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CodigoErrorEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesBOEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.AliasCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuentaPrestamosEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.debinws.dao.DebinWSSolicitudesDAO;
import ar.com.santanderrio.obp.servicios.debinws.dto.ValidarCbuInDTO;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCBUEntityIn;
import ar.com.santanderrio.obp.servicios.debinws.entities.ConsultaCbuEntityOut;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.PreFormalizacionPrestamoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.DeudaPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PreFormalizacionPrestamoDAO;
import ar.com.santanderrio.obp.servicios.pagos.dao.PrestamoSueldosDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.AdjuntarArchivosInEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldoTasaSubsidiadaEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosAgregarCBUEntity;
import ar.com.santanderrio.obp.servicios.pagos.entities.PrestamoSueldosConfigEntity;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoPermitidoBO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.CalificacionCrediticiaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoBackendDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoEncoladoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoNormativoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamoPermitidoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamosBffDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.PrestamosOBPBffDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamoDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReportePrestamosTasaSubsidiadaDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.ReporteStopDebitPrestamosDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.StopDebitPrestamosDAO;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CalificacionCrediticiaDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelacionAnticipadaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.CancelarPrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmacionStopDebitDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ConfirmarPrestamoSueldosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.EncolamientoResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.InfoPrestamosDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.LiquidacionResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.ObtenerPrestamosInDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.OrdenPrestamos;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaRequestDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PagoCuotaResponseDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoCanceladoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoPermitidoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.dto.PrestamoSueldosPermitidoDTO;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CancelarPrestamoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CancelarPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CompStopDebitPrestamoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoCanceladoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoEncoladoEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoNormativoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoNormativoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosCanceladosOutEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamosEncoladosEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.StopDebitPrestamosInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.TipoOfertaEnum;
import ar.com.santanderrio.obp.servicios.prestamos.utils.CodigosPrestamos;
import ar.com.santanderrio.obp.servicios.prestamos.utils.PrestamosUtils;
import ar.com.santanderrio.obp.servicios.prestamos.view.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.prestamos.view.CancelacionPrestamoRequestView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteCancelacionTotalPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobantePrestamoTasaSubsidiadaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ComprobanteStopDebitPrestamoView;
import ar.com.santanderrio.obp.servicios.prestamos.view.PeriodosStopDebitPrestamo;
import ar.com.santanderrio.obp.servicios.prestamos.view.PrestamoSueldoTasaSubsidiadaView;
import ar.com.santanderrio.obp.servicios.simuladorprestamo.bo.DestinoPrestamoBO;


/**
 * The Class PrestamoBOImpl.
 *
 * @author
 */
@Component("prestamoBO")
public class PrestamoBOImpl implements PrestamoBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(PrestamoBOImpl.class);

    /** The Constant PRESTAMO_SECURITIZADO. */
    private static final String PRESTAMO_SECURITIZADO = "12";

    /** The Constant CODIGO_ERROR_NO_LINEA_CREDITICIA. */
    private static final String CODIGO_ERROR_NO_LINEA_CREDITICIA = "16010007";

    /** The Constant CODIGO_ERROR_OPERACION_INHABILITADA. */
    private static final String CODIGO_ERROR_OPERACION_INHABILITADA = "10099906";

    /** Identificador prestamo open credit. */
    private static final String CLASE_PRESTAMO_OPEN_CREDIT = "O";

    /** CODIGO_PRODUCTO_39. */
    private static final String CODIGO_PRODUCTO_39 = "39";

    /** CODIGO_PRODUCTO_71. */
    private static final String CODIGO_PRODUCTO_71 = "71";

	/** The Constant TIPO_PERSONA_FISICA. */
	private static final String TIPO_PERSONA_FISICA = "F";

	/** The Constant COD_USER. */
	private static final String COD_USER = "HBAN0002";

	/** The Constant COD_SECTOR. */
	private static final String COD_SECTOR = "NEIT";

	/** The Constant MEDIO_INGRESO. */
	private static final Integer MEDIO_INGRESO = 35;

	/** The Constant COD_ASOCIACION. */
	private static final Integer COD_ASOCIACION = 100;

	/** The Constant COMENTARIO_ALTA. */
	private static final String COMENTARIO_ALTA = "Alta de gestión automatica desde OBP, derivada hacia la sucursal administradora." +
			"Si la cuenta de debito de los prestamos no esta radicada en tu sucursal, asigna esta gestión en TAREA ESPECIAL a la sucursal del debito para procesar el pedido del cliente." +
			"Luego de la resolucion, asegura resolverla con la respuesta predefinida que corresponda (que se envia al cliente en forma automatica).";

	/** The Constant COD_TIPO_PRESTAMO. */
	private static final Integer COD_TIPO_PRESTAMO = 216;

	/** The Constant COD_TIPO_PERIODO. */
	private static final Integer COD_TIPO_PERIODO = 3286;

	/** The Constant DESC_TIPO_PRESTAMO. */
	private static final String DESC_TIPO_PRESTAMO = "Numero de Prestamo";

	/** The Constant DESC_TIPO_PERIODO. */
	private static final String DESC_TIPO_PERIODO = "Periodos a modificar";

    /** The Constant FOREX_OK. */
    private static final String FOREX_OK = "0000";

    /** The Constant FOREX_NO_VALIDO. */
    private static final String FOREX_NO_VALIDO = "1005";

    /** The Constant MSG_ERROR_FOREX_NO_VALIDO. */
    private static final String MSG_ERROR_FOREX_NO_VALIDO = "El préstamo no puede ser liquidado dado que registramos una operación de cambios contemplada el art. 1 Com. A 7001 BCRA.";
    
    /**
     * Map containing:
     *  left: the TipoError
     *  right: Id of MensajeError
     */
    private static final Map<String, Pair<TipoError, String>>iatxErrorMap;

    static {
        iatxErrorMap = new HashMap<String,Pair<TipoError, String>>();
        iatxErrorMap.put(PrestamoDAOImpl.CODIGO_ERROR_CANCELAR_PRESTAMO_ERROR_DEUDA,
                Pair.of(TipoError.PRESTAMO_CANCELACION_ERROR_CUOTA_IMPAGA,
                        CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_ERROR_CUOTAS_IMPAGAS));
        iatxErrorMap.put(PrestamoDAOImpl.CODIGO_ERROR_CANCELAR_PRESTAMO_INCIDENCIA_FUTURA,
                Pair.of(TipoError.PRESTAMO_CANCELACION_ERROR_CUOTA_IMPAGA,
                        CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_ERROR_CUOTAS_IMPAGAS));
        iatxErrorMap.put(PrestamoDAOImpl.CODIGO_ERROR_CANCELAR_PRESTAMO_CUOTA_IMPAGA,
                Pair.of(TipoError.PRESTAMO_CANCELACION_ERROR_CUOTA_IMPAGA,
                        CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_ERROR_CUOTAS_IMPAGAS));
        iatxErrorMap.put(PrestamoDAOImpl.CODIGO_ERROR_CANCELAR_PRESTAMO_SALDO_INSUFICIENTE,
                Pair.of(TipoError.PRESTAMO_CANCELACION_ERROR_SALDO_INSUFICIENTE,
                        CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_ERROR_SALDO_INSUFICIENTE));
        iatxErrorMap.put(PrestamoDAOImpl.CODIGO_ERROR_CANCELAR_PRESTAMO_CUOTA_ADELANTADA,
                Pair.of(TipoError.PRESTAMO_CANCELACION_ERROR_CUOTA_ADELANTADA,
                        CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_ERROR_CUOTA_ADELANTADA));
        iatxErrorMap.put(PrestamoDAOImpl.CODIGO_ERROR_PRESTAMO_VENCIDO,
                Pair.of(TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CANCELACION_TOTAL_PRESTAMO_PRESTAMO_VENCIDO));
        iatxErrorMap.put(TipoError.ERROR_GENERICO.getDescripcion(),
                Pair.of(TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_MENSAJE_TIMEOUT_SIMULADOR_PRESTAMOS));
    }

    /** dao. */
    @Autowired
    private CalificacionCrediticiaDAO calificacionCrediticiaDAO;
    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;
    /** The deuda prestamo dao. */
    @Autowired
    private DeudaPrestamoDAO deudaPrestamoDAO;

    /** The prestamo sueldos DAO. */
    @Autowired
    private PrestamoSueldosDAO prestamoSueldosDAO;

    /** The stop debit prestamos DAO. */
    @Autowired
    private StopDebitPrestamosDAO stopDebitPrestamosDAO;
    /** The destino prestamo DAO. */

    @Autowired
    private ReporteStopDebitPrestamosDAO reporteStopDebitPrestamosDAO;

    @Autowired
    private ReportePrestamosTasaSubsidiadaDAO reportePrestamosTasaSubsidiadaDAO;

    /** The mensaje DAO. */
    @Autowired
    private MensajeDAO mensajeDAO;

    /** The prestamo PDFDAO. */
    @Autowired
    private ReportePrestamoDAO prestamoPDFDAO;

    /** The prestamo permitido BO. */
    @Autowired
    private PrestamoPermitidoBO prestamoPermitidoBO;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The cuenta BO. */
    @Autowired
    private CuentaBO cuentaBO;

    /** The prestamo permitido DAO. */
    @Autowired
    private PrestamoPermitidoDAO prestamoPermitidoDAO;

    /** The pre formalizacion prestamo DAO. */
    @Autowired
    private PreFormalizacionPrestamoDAO preFormalizacionPrestamoDAO;

    /** The prestamo DAO. */
    @Autowired
    private PrestamoDAO prestamoDAO;

    /** The destino prestamo BO. */
    @Autowired
    private DestinoPrestamoBO destinoPrestamoBO;

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The prestamo normativo DAO. */
    @Autowired
    private PrestamoNormativoDAO prestamoNormativoDAO;

    /** The codigos prestamos. */
    @Autowired
    private CodigosPrestamos codigosPrestamos;

    @Autowired
    private SesionCliente sesionCliente;

    @Autowired
    private DebinWSSolicitudesDAO solicitudesDebinWSDAO;

	@Autowired
	private AliasCuentaBO aliasCuentaBO;

	@Autowired
    private PreFormalizacionPrestamoBO preFormalizacionPrestamoBO;

    @Autowired
    private PrestamoEncoladoDAO prestamoEncoladoDAO;

    @Autowired
    private PrestamoBackendDAO prestamoBackendDAO;

	@Autowired
	@Qualifier("prestamosBffDAO")
	private PrestamosBffDAO prestamosBffDAO;

	@Autowired
	@Qualifier("prestamosObpBffDAO")
	private PrestamosOBPBffDAO prestamosObpBffDAO;

    /**
     * crear una entidad PrestamoDTO para retornar al PrestamoBO.
     *
     * @param calificacionCrediticiaOutEntity
     *            the calificacion crediticia out entity
     * @return entidad DTO.
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    private CalificacionCrediticiaDTO createDTO(CalificacionCrediticiaOutEntity calificacionCrediticiaOutEntity)
            throws ImporteConvertException {

        CalificacionCrediticiaDTO calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
        calificacionCrediticiaDTO
                .setLineaTotalCrediticia(toBigDecimal(calificacionCrediticiaOutEntity.getPorcentajeLimitePrestamo()));
        calificacionCrediticiaDTO
                .setLineaActualDisponible(toBigDecimal(calificacionCrediticiaOutEntity.getImporteDisponiblePrestamo()));
        return calificacionCrediticiaDTO;
    }

    /**
     * Obtener prestamos.
     *
     * @param obtenerPrestamosInDTO the obtener prestamos in DTO
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * obtenerPrestamos(ar.com.santanderrio.obp.servicios.clientes.entities.
     * Cliente)
     */
    @Override
    public Respuesta<List<PrestamoDTO>> obtenerPrestamos(ObtenerPrestamosInDTO obtenerPrestamosInDTO) {
        final List<PrestamoDTO> prestamos = new ArrayList<PrestamoDTO>();
        final Cliente cliente = obtenerPrestamosInDTO.getCliente();
        final List<ItemMensajeRespuesta> listaItems = new ArrayList<ItemMensajeRespuesta>();
        final List<PrestamoDTO> prestamosNoInformadoUva = new ArrayList<PrestamoDTO>();
        List<Prestamo> prestamosVencidos = new ArrayList<Prestamo>();

        int cantidadErrores = 0;
        int cantidadPrestamos = 0;

        for (Cuenta cuenta : cliente.getCuentas()) {
            try {
                if (!CLASE_PRESTAMO_OPEN_CREDIT.equals(cuenta.getClaseCuenta()) &&
                        isTipoPrestamoRequerido(cuenta, obtenerPrestamosInDTO)) {
                    cantidadPrestamos++;
                    PrestamoDTO prestamoDetails =
                            getPrestamoDetails(cliente, cuenta, isFromCalendario(obtenerPrestamosInDTO));
                    Prestamo prestamo = prestamoDetails.getPrestamo();

                    if (prestamo.isInformeCoeficienteNoDisponible()) {
                        prestamosNoInformadoUva.add(prestamoDetails);
                        continue;
                    }
                    if (prestamo.getIsCuotaVencida()) {
                        prestamosVencidos.add(prestamo);
                    }
                    prestamos.add(prestamoDetails);
                }
            } catch (DAOException e) {
                LOGGER.error("Error en el DAO de deudaPrestamoDAO.", e);
                if ((CodigoErrorEnum.CODIGO_ERROR_NO_INFORMADO_UVA_CALENDARIO.getCodigo().toString().equals(e.getErrorCode()))) {
                	cantidadErrores++;
                	continue;
                }
                cantidadErrores++;
            } catch (RuntimeException e) {
                LOGGER.error("Error generico en deudaPrestamoDAO.", e);
                cantidadErrores++;
            }
        }

        if (prestamos.size() != 0 && (cantidadErrores == cantidadPrestamos)) {
            return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
                    CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
        }

        if (cantidadPrestamos > 1 && obtenerPrestamosInDTO.getOrdenPrestamos() != null) {
            ordenarPrestamos(obtenerPrestamosInDTO.getOrdenPrestamos(), prestamos);
        }

        if (cantidadErrores > 0) {
            listaItems.add(crearItem(CodigoMensajeConstantes.ERROR_PARCIAL_BUSCAR_PRESTAMOS,
                    TipoError.ERROR_PARCIAL_BUSCAR_PRESTAMOS));
        }

        if (prestamosVencidos.size() == 1) {
            ItemMensajeRespuesta item = crearItem(CodigoMensajeConstantes.ERROR_PRESTAMO_VENCIDO,
                    TipoError.ERROR_PRESTAMO_VENCIDO);
            item.setTag(String.valueOf(prestamosVencidos.get(0).getCuenta().getIndex()));
            listaItems.add(item);
        }

        if (prestamosVencidos.size() >= 2) {
            ItemMensajeRespuesta item = crearItem(CodigoMensajeConstantes.ERROR_PRESTAMOS_VARIOS_VENCIDO,
                    TipoError.ERROR_PRESTAMOS_VARIOS_VENCIDO);
            //TODO: ORDER THIS TO GET LAST ITEM INSTEAD OF THIS
            prestamosVencidos = ordenarPrestamosVencidos(prestamosVencidos);
            Prestamo prestamo = obtenerUltimoPrestamo(prestamosVencidos);
            item.setTag(String.valueOf(prestamo.getCuenta().getIndex()));
            listaItems.add(item);
        }

        if (!prestamosNoInformadoUva.isEmpty()) {
        	 ItemMensajeRespuesta item = crearItem(CodigoMensajeConstantes.AVISO_PROXIMA_CUOTA_NO_DISPONIBLE,
                     TipoError.AVISO_PROXIMA_CUOTA_NO_DISPONIBLE);
        	 listaItems.add(item);
		}

        prestamos.addAll(prestamosNoInformadoUva);
        if (CollectionUtils.isNotEmpty(listaItems)) {
            return respuestaFactory.crearRespuestaWarning(prestamos, listaItems);
        } else {
            return respuestaFactory.crearRespuestaOk(prestamos);
        }

    }

    /**
     * Obtener prestamo sueldo.
     *
     * @param obtenerPrestamosInDTO the obtener prestamos in DTO
     * @return the respuesta
     */
    @Override
    public Respuesta<PrestamoSueldosPermitidoDTO> obtenerPrestamoSueldo(ObtenerPrestamosInDTO obtenerPrestamosInDTO) {

    	PrestamoSueldosConfigEntity prestamosConfig = new PrestamoSueldosConfigEntity();
        Cliente cliente = obtenerPrestamosInDTO.getCliente();
        PrestamoSueldosPermitidoDTO prestamoPermitido = new PrestamoSueldosPermitidoDTO();
            try {
            	 prestamosConfig = prestamoSueldosDAO.consultarPrestamoSueldos(cliente);
            	 if (prestamosConfig.getSolicitado() == null || !"T".equalsIgnoreCase(prestamosConfig.getSolicitado().trim())) {
	            	 // Validacion de pago de deuda vencido
	            	 BCRAResponse respuestaOperacionCambio = prestamoSueldosDAO.consultarWSFOREXBCRA(cliente);
	        		 if (FOREX_NO_VALIDO.equals(respuestaOperacionCambio.getCodError())) {
	        			 return respuestaFactory.crearRespuestaErrorPersonalizado(PrestamoSueldosPermitidoDTO.class,
	        					MSG_ERROR_FOREX_NO_VALIDO, TipoError.ERROR_PRESTAMOS_FOREX.getDescripcion());
	        		 } else if (!FOREX_OK.equals(respuestaOperacionCambio.getCodError())) {
	        			 return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
	        					CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
	        		 }
            	 }
            	 prestamoPermitido.setMontoPrestamo(prestamosConfig.getMontoPrestamoSueldos());
            	 prestamoPermitido.setSolicitado(prestamosConfig.getSolicitado());
            	 prestamoPermitido.setAdjuntaDocumentacion(prestamosConfig.getAdjuntaDocumentacion());
            	 return respuestaFactory.crearRespuestaOk(prestamoPermitido);
            } catch (DAOException e) {
                LOGGER.error("Error en el DAO de prestamoSaldosDAO.", e);
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
                        CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
            } catch (RuntimeException e) {
                LOGGER.error("Error generico en prestamoSaldosDAO.", e);
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
                        CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
            }
    }


	/**
	 * Confirmar prestamo sueldo.
	 *
	 * @param confirmarPrestamoSueldosInDTO the confirmar prestamo sueldos in DTO
	 * @return the respuesta
	 */
	@Override
	public Respuesta<PrestamoSueldosPermitidoDTO> confirmarPrestamoSueldo(ConfirmarPrestamoSueldosInDTO confirmarPrestamoSueldosInDTO) {
        Cliente cliente = confirmarPrestamoSueldosInDTO.getCliente();
        PrestamoSueldosPermitidoDTO prestamoPermitido = new PrestamoSueldosPermitidoDTO();
        PrestamoSueldosConfigEntity prestamosConfig = new PrestamoSueldosConfigEntity();
        	try {
            	 prestamosConfig = prestamoSueldosDAO.insertarPrestamoSueldos(cliente,confirmarPrestamoSueldosInDTO);
            	 prestamoPermitido.setNroComprobante(prestamosConfig.getNroComprobante());
            	 return respuestaFactory.crearRespuestaOk(prestamoPermitido);
            } catch (DAOException e) {
                LOGGER.error("Error en el DAO de prestamoSaldosDAO.", e);
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
                        CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
            } catch (RuntimeException e) {
                LOGGER.error("Error generico en prestamoSaldosDAO.", e);
                return respuestaFactory.crearRespuestaError(null, TipoError.ERROR_BUSCAR_PRESTAMOS,
                        CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
            }
	}

     /**
		 * Checks if is from calendario.
		 *
		 * @param obtenerPrestamosInDTO
		 *            the obtener prestamos in DTO
		 * @return true, if is from calendario
		 */
     private boolean isFromCalendario( ObtenerPrestamosInDTO obtenerPrestamosInDTO){

    	if (null == obtenerPrestamosInDTO.getIsHipotecario() && null == obtenerPrestamosInDTO.getIsPersonal()
    			&& null == obtenerPrestamosInDTO.getIsPrendario() ) {
    		return true;
    	} else {
    		return false;
    	}
     }

    /**
     * Checks if is prestamo normativo.
     *
     * @param productoAltair the producto altair
     * @param subproductoAltair the subproducto altair
     * @return the boolean
     */
    private Boolean isPrestamoNormativo(String productoAltair, String subproductoAltair) {
		Integer.valueOf(productoAltair);
		Integer.valueOf(subproductoAltair);

		if(codigosPrestamos.getData().get(Integer.valueOf(productoAltair)) != null) {
			if(codigosPrestamos.getData().get(Integer.valueOf(productoAltair)).contains(Integer.valueOf(subproductoAltair))) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
     * se ordena la lista 2 en base al orden de sus elementos en la lista 1.
     *
     * @param prestamosVencidos lista reducida con vencidos
     * @return the list
     */
    private List<Prestamo> ordenarPrestamosVencidos(List<Prestamo> prestamosVencidos) {
        List<Prestamo> prestamosVencidosOrdenados = new ArrayList<Prestamo>();
        for (Prestamo prestamo : prestamosVencidos) {
            if (prestamosVencidos.contains(prestamo)) {
                prestamosVencidosOrdenados.add(prestamo);
            }
        }
        return prestamosVencidosOrdenados;
    }

    /**
     * Checks if is tipo prestamo requerido.
     *
     * @param cuenta
     *            the cuenta
     * @param obtenerPrestamosInDTO
     *            the obtener prestamos in DTO
     * @return true, if is tipo prestamo requerido
     */
    private boolean isTipoPrestamoRequerido(Cuenta cuenta, ObtenerPrestamosInDTO obtenerPrestamosInDTO) {
        if (TipoCuentaPrestamosEnum.fromCodigo(cuenta.getTipoCuenta()) != null
                && !cuenta.getEstadoTarjetaCredito().equalsIgnoreCase(PRESTAMO_SECURITIZADO)) {
            if (obtenerPrestamosInDTO.getIsTodos() != null && obtenerPrestamosInDTO.getIsTodos()) {
                return true;
            } else if (obtenerPrestamosInDTO.getIsHipotecario()) {
                return TipoPrestamoEnum.HIPOTECARIOS.equals(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()));
            } else if (obtenerPrestamosInDTO.getIsPersonal()) {
                return isPrestamoPersonal(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()))
                        || CODIGO_PRODUCTO_39.equals(cuenta.getProductoAltair());
            } else if (obtenerPrestamosInDTO.getIsPrendario()) {
                return TipoPrestamoEnum.PRENDARIO.equals(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()));
            }
        }
        return false;
    }

    private PrestamoDTO getPrestamoDetails(Cliente cliente, Cuenta cuenta, boolean isFromCalendario)
            throws DAOException {
        final PrestamoDTO response = new PrestamoDTO();
        final Prestamo prestamo = getPrestamo(cliente, cuenta, isFromCalendario);

        Respuesta<PreFormalizacion> responsePreFormalizacion = preFormalizacionPrestamoBO.obtenerPreFormalizacion(cuenta);
        final PreFormalizacion preFormalizacion = responsePreFormalizacion.getRespuesta();

        if (EstadoRespuesta.OK.equals(responsePreFormalizacion.getEstadoRespuesta())) {
            PrestamosUtils.parseNumeroPropuesta(prestamo, preFormalizacion.getPrestamoDebitoAdherido().getNumeroPropuesta());
        }
        response.setPrestamo(prestamo);
        response.setPreFormalizacion(preFormalizacion);
        return response;
    }

    private Prestamo getPrestamo(Cliente cliente, Cuenta cuenta, boolean isFromCalendario) throws DAOException {
        Prestamo prestamo;
        try {
            prestamo = deudaPrestamoDAO.consultarDeudaDePrestamo(cliente, cuenta);
            prestamo.setClaseCuenta(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()).getDescripcion());
            prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()));
            prestamo.setNumeroCuentaProducto(cuenta.getNroCuentaProducto());
            prestamo.setIsPrestamoNormativo(isPrestamoNormativo(cuenta.getProductoAltair(), cuenta.getSubproductoAltair()));
            prestamo.setIsRefinanciacion(CODIGO_PRODUCTO_71.equals(cuenta.getProductoAltair()));
            prestamo.setCuenta(cuenta);
        } catch (DAOException e) {
            LOGGER.error("Error no esta informado el importa UVA para prestamos hipotecarios.", e);
            if ((CodigoErrorEnum.CODIGO_ERROR_NO_INFORMADO_UVA_CALENDARIO.getCodigo().toString().equals(e.getErrorCode())) &&
                    !isFromCalendario ) {
                /* En caso de se un error tipo 10001918 y que sea hipotecario se le setea un flag por true */
                prestamo = new Prestamo();
                prestamo.setClaseCuenta(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()).getDescripcion());
                prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()));
                prestamo.setNumeroCuentaProducto(cuenta.getNroCuentaProducto());
                prestamo.setCuenta(cuenta);
                prestamo.setIsPrestamoNormativo(isPrestamoNormativo(cuenta.getProductoAltair(), cuenta.getSubproductoAltair()));
                prestamo.setInformeCoeficienteNoDisponible(true);
            } else {
                throw new DAOException(e.getErrorCode());
            }
        }
        return prestamo;
    }

    /**
     * Ordenar prestamos.
     *
     * @param ordenPrestamos
     *            the orden prestamos
     * @param prestamos
     *            the prestamos
     * @return the list
     */
    private void ordenarPrestamos(OrdenPrestamos ordenPrestamos, List<PrestamoDTO> prestamos) {

        if (OrdenPrestamos.ASCENSDENTE.equals(ordenPrestamos)) {
            Collections.sort(prestamos, new Comparator<PrestamoDTO>() {
                @Override
                public int compare(PrestamoDTO prestamoDTO1, PrestamoDTO prestamoDTO2) {
                    return prestamoDTO1.getPrestamo().getVencimientoCuota()
                            .compareTo(prestamoDTO2.getPrestamo().getVencimientoCuota());
                }
            });
        }

        if (OrdenPrestamos.DESCENDENTE.equals(ordenPrestamos)) {
            Collections.sort(prestamos, new Comparator<PrestamoDTO>() {
                @Override
                public int compare(PrestamoDTO prestamoDTO1, PrestamoDTO prestamoDTO2) {
                    return prestamoDTO1.getPrestamo().getVencimientoCuota()
                            .compareTo(prestamoDTO2.getPrestamo().getVencimientoCuota());
                }
            });
        }
    }

    /**
     * Checks if is prestamo personal.
     *
     * @param tipoPrestamo
     *            the tipo prestamo
     * @return true, if is prestamo personal
     */
    protected boolean isPrestamoPersonal(TipoPrestamoEnum tipoPrestamo) {
       if (TipoPrestamoEnum.PERSONAL.equals(tipoPrestamo) ||
           TipoPrestamoEnum.SOLAFIRMA.equals(tipoPrestamo) ||
           TipoPrestamoEnum.DESCUENTOSDESCONTADOS.equals(tipoPrestamo) ||
           TipoPrestamoEnum.ADELANTOSESPECIALES.equals(tipoPrestamo) ||
           TipoPrestamoEnum.PLANDEPAGOS .equals(tipoPrestamo) ||
           TipoPrestamoEnum.OTROSPRESTAMOS.equals(tipoPrestamo)) {
            return true;
        }
        return false;
    }

    /**
     * Obtener ultimo prestamo.
     *
     * @param prestamosVencidos
     *            the prestamos vencidos
     * @return the prestamo
     */
    private Prestamo obtenerUltimoPrestamo(List<Prestamo> prestamosVencidos) {
        Prestamo ultimoPrestamo = prestamosVencidos.get(0);

        for (Prestamo prestamo : prestamosVencidos) {
            if (!ultimoPrestamo.getVencimientoCuota().after(prestamo.getVencimientoCuota())) {
                ultimoPrestamo = prestamo;
            }
        }

        return ultimoPrestamo;
    }

    /**
     * Crear item.
     *
     * @param errorParcialBuscarPrestamos
     *            the error parcial buscar prestamos
     * @param tipoError
     *            the tipo error
     * @return the item mensaje respuesta
     */
    private ItemMensajeRespuesta crearItem(String errorParcialBuscarPrestamos, TipoError tipoError) {

        Mensaje mensaje = mensajeDAO.obtenerMensajePorCodigo(errorParcialBuscarPrestamos);
        ItemMensajeRespuesta item = new ItemMensajeRespuesta(mensaje.getMensaje());
        item.setTipoError(tipoError.getDescripcion());
        return item;
    }


    /**
     * Obtiene la situacion crediticia del cliente.
     *
     * @param cliente
     *            the cliente
     * @return the respuesta
     */
    @Override
    public Respuesta<List<CalificacionCrediticiaDTO>> obtenerSituacionCrediticia(Cliente cliente) {
    	List<Cuenta> cuentasUnicasPesos = cliente.getCuentasUnicasPesos();
    	List<CalificacionCrediticiaDTO> calificaciones = new ArrayList<CalificacionCrediticiaDTO>();
    	CalificacionCrediticiaDTO  calificacionCrediticiaDTO = new CalificacionCrediticiaDTO();
    	@SuppressWarnings("unused")
		String mensajePrincipal = null;
    	if (cuentasUnicasPesos.isEmpty()) {
            LOGGER.debug("El cliente no tiene Cuentas Unicas en pesos: {}.", cliente);
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_INICIO_PRESTAMOS,
                    CodigoMensajeConstantes.PRESTAMO_INHABILITADO);
        }
    	else {
    		sesionParametros.clearCalificacionesCrediticias();
    		for (Cuenta cuenta:cuentasUnicasPesos) {
    			try {

                    LOGGER.info("MALUG-2276 Obteniendo situación crediticia de la cuenta: {}", cuenta);
					calificacionCrediticiaDTO = obtenerSituacionCrediticia(cuenta);

                    LOGGER.info("MALUG-2276 Situación crediticia de la cuenta {} es la siguiente {}", cuenta.getNroCuentaTit(), calificacionCrediticiaDTO);
					calificaciones.add(calificacionCrediticiaDTO);

				} catch (BusinessException e) {
					if (cuenta.isCuentaPrincipal()) {
						return respuestaFactory.crearRespuestaError("", TipoError.ERROR_INICIO_PRESTAMOS, CodigoMensajeConstantes.ERROR_LINEA_DISPONIBLE_PRESTAMO);
					}
				}catch (DAOException daoe) {
		            LOGGER.error("Error al consultar DAO con {}", cuenta, daoe);
		            if (CODIGO_ERROR_OPERACION_INHABILITADA.equals(daoe.getErrorCode())) {
		            	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA, CodigoMensajeConstantes.CALIFICACION_CREDITICIA_INHABILITADA);
		            }
		            if (cuenta.isCuentaPrincipal()) {
		            	if (StringUtils.isNotBlank(daoe.getErrorCode()) && StringUtils.equals(CODIGO_ERROR_NO_LINEA_CREDITICIA, daoe.getErrorCode())
		            			&& sesionParametros.getMaxOfertaPrestamoPreaprobado() == null) {
			            	mensajePrincipal = CodigoMensajeConstantes.LINEA_CREDITICIA_SIN_MONTO_PERMITIDO;
			               /// return respuestaFactory.crearRespuestaError("", TipoError.ERROR_INICIO_PRESTAMOS_SIN_LINEA_DISPONIBLE, CodigoMensajeConstantes.LINEA_CREDITICIA_SIN_MONTO_PERMITIDO);
			            }}
			            continue;
			          //  return respuestaFactory.crearRespuestaError("", TipoError.ERROR_INICIO_PRESTAMOS, CodigoMensajeConstantes.ERROR_LINEA_DISPONIBLE_PRESTAMO);

		        } catch (ImporteConvertException e) {
		            LOGGER.error(e.getMessage(), e);
		            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_INICIO_PRESTAMOS,CodigoMensajeConstantes.ERROR_LINEA_DISPONIBLE_PRESTAMO);
		        }

    		}
    	}

    	Respuesta<PrestamoPermitidoDTO> prestamoPermitidoRespuesta = prestamoPermitidoBO.obtenerPrestamoConSaldoMayor(cliente);
		if (EstadoRespuesta.OK.equals(prestamoPermitidoRespuesta.getEstadoRespuesta())) {
			PrestamoPermitidoDTO prestamoPermitidoDTO = prestamoPermitidoRespuesta.getRespuesta();
			CalificacionCrediticiaDTO calificacionPrincipal = getCalificacionPrincipal(calificaciones);
			BigDecimal lineaDisponible = null;
			if (calificacionPrincipal != null) {
	            	   lineaDisponible = calificacionPrincipal.getLineaActualDisponible().min(prestamoPermitidoDTO.getMontoPrestamo());
			}
	                //BigDecimal lineaDisponible = calificaciones.get(0).getLineaActualDisponible().min(prestamoPermitidoDTO.getMontoPrestamo());
			if (!calificaciones.isEmpty()) {
				calificaciones.get(0).setLineaActualDisponible(lineaDisponible);
			}
			Respuesta<List<CalificacionCrediticiaDTO>> rta = new Respuesta<List<CalificacionCrediticiaDTO>>();
			rta.setRespuesta(calificaciones);
			rta.setEstadoRespuesta(EstadoRespuesta.OK);
			return rta;
		} else if (EstadoRespuesta.ERROR.equals(prestamoPermitidoRespuesta.getEstadoRespuesta())
				&& TipoError.ERROR_OPERACION_INHABILITADA.getDescripcion()
						.equals(prestamoPermitidoRespuesta.getItemsMensajeRespuesta().get(0).getTipoError())) {
			LOGGER.error("Error - Consulta de Prestamos Permitidos inhabilitada");
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_OPERACION_INHABILITADA,
					CodigoMensajeConstantes.CONSULTA_PRESTAMO_PERMITIDO_INHABILITADA);
		} else {
			Respuesta<List<CalificacionCrediticiaDTO>> rta = new Respuesta<List<CalificacionCrediticiaDTO>>();
			rta.setEstadoRespuesta(EstadoRespuesta.WARNING);
			rta.setRespuestaVacia(true);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje("");
			item.setTipoError(TipoError.ERROR_INICIO_PRESTAMOS.getDescripcion());
			item.setTag(null);
			rta.add(item);
			return rta;
		}
    }

	/**
	 * Obtengo la calificacion crediticia de la cuenta principal, si es que existe
	 * sino retorna null.
	 *
	 * @param calificaciones the calificaciones
	 * @return the calificacion principal
	 */
    private CalificacionCrediticiaDTO getCalificacionPrincipal(List<CalificacionCrediticiaDTO> calificaciones) {

    	for (CalificacionCrediticiaDTO calificacion:calificaciones) {
    		if (calificacion.getCuenta().esCuentaPrincipal()) {
    			return calificacion;
    		}
    	}
    	return null;
    }



    /**
     * Obtener situacion crediticia.
     *
     * @param cuenta the cuenta
     * @return the calificacion crediticia DTO
     * @throws BusinessException the business exception
     * @throws DAOException the DAO exception
     * @throws ImporteConvertException the importe convert exception
     */
    public CalificacionCrediticiaDTO obtenerSituacionCrediticia(Cuenta cuenta) throws BusinessException, DAOException, ImporteConvertException{
            String consultaServicio = "C";
            CalificacionCrediticiaOutEntity outDAO = calificacionCrediticiaDAO.obtenerSituacionCrediticia(cuenta,   consultaServicio);

            if (!"001".equals(outDAO.getCodigoInhabilitado())) {
                throw new BusinessException();
            }
            sesionParametros.putCalificacionCrediticia(cuenta, outDAO);
			CalificacionCrediticiaDTO calificacionCrediticiaDTO = createDTO(outDAO);
			calificacionCrediticiaDTO.setCuenta(cuenta);
			return calificacionCrediticiaDTO;
    }

    /**
     * Buscar mensaje ayuda linea.
     *
     * @param codigoMensaje
     *            the codigo mensaje
     * @return the string
     */
    private String buscarMensajeAyudaLinea(String codigoMensaje) {
        Mensaje mensaje = mensajeBO.obtenerMensajePorCodigo(codigoMensaje);
        return mensaje.getMensaje();
    }

    /**
     * Validar linea crediticia.
     *
     * @param calificacionCrediticiaDTO the calificacion crediticia DTO
     * @param cantidadPrestamos the cantidad prestamos
     * @param cliente the cliente
     * @return the respuesta
     */
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * validarLineaCrediticia(ar.com.santanderrio.obp.servicios.prestamos.dto.
     * CalificacionCrediticiaDTO, int,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<CalificacionCrediticiaDTO> validarLineaCrediticia(
            CalificacionCrediticiaDTO calificacionCrediticiaDTO, int cantidadPrestamos, Cliente cliente) {

        Respuesta<CalificacionCrediticiaDTO> respuesta = new Respuesta<CalificacionCrediticiaDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        // No se recupero situacion Crediticia
        if (calificacionCrediticiaDTO == null) {

            // no tiene cuentas
            if (!cuentaBO.hasCuentasMonetariasActivas(cliente)) {
                if (cantidadPrestamos == 0) {
                    // el cliente no tiene cuentas monetarias activas
                    return respuestaFactory.crearRespuestaWarning("",
                            TipoError.ERROR_VALIDACION_SIN_CUENTAS_MONETARIAS_ACTIVAS,
                            CodigoMensajeConstantes.ERROR_PRESTAMO_SIN_CUENTAS_MONETARIAS_ACTIVAS);
                } else {

                    return respuestaFactory.crearRespuestaWarning("",
                            TipoError.ERROR_VALIDACION_SIN_CUENTAS_MONETARIAS_ACTIVAS_CON_PRESTAMOS,
                            CodigoMensajeConstantes.ERROR_PRESTAMO_SIN_CUENTAS_MONETARIAS_ACTIVAS_CON_PRESTAMOS);
                }
            }
            // el servicio falla
            if (cantidadPrestamos == 0) {
                List<DatoItemMensaje> datosItems = new ArrayList<DatoItemMensaje>();

                // error grilla
                DatoItemMensaje datosItem = new DatoItemMensaje();
                datosItem.setCodigoMensaje(CodigoMensajeConstantes.ERROR_BUSCAR_PRESTAMOS);
                datosItem.setTipoError(TipoError.ERROR_BUSCAR_PRESTAMOS);
                datosItems.add(datosItem);

                // error cabecera
                DatoItemMensaje datosItemInicio = new DatoItemMensaje();
                datosItemInicio.setCodigoMensaje(CodigoMensajeConstantes.ERROR_LINEA_DISPONIBLE_PRESTAMO);
                datosItemInicio.setTipoError(TipoError.ERROR_INICIO_PRESTAMOS);
                datosItems.add(datosItemInicio);

                return respuestaFactory.crearRespuestaError(datosItems);
            }
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_INICIO_PRESTAMOS,
                    CodigoMensajeConstantes.ERROR_LINEA_DISPONIBLE_PRESTAMO);
        }

        if (hasLineaDisponibleZeroAndLineaTotalMayorZero(calificacionCrediticiaDTO)) {

            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_VALIDACION_NO_DISPONIBLE,
                    CodigoMensajeConstantes.ERROR_VALIDACION_NO_DISPONIBLE);
        }
        if (hasLineaDisponibleAndTotalZero(calificacionCrediticiaDTO)) {

            if (cantidadPrestamos == 0) {

                return respuestaFactory.crearRespuestaWarning("",
                        TipoError.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL_NO_REGISTROS,
                        CodigoMensajeConstantes.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL_NO_REGISTROS);
            }
            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL,
                    CodigoMensajeConstantes.ERROR_VALIDACION_NO_DISPONIBLE_NO_TOTAL);
        }

        if (hasLineaDisponibleZeroAndRegistrosZero(calificacionCrediticiaDTO, cantidadPrestamos)) {

            return respuestaFactory.crearRespuestaWarning("", TipoError.ERROR_NO_REGISTROS_CON_LINEA_CREDITICIA,
                    CodigoMensajeConstantes.NO_REGISTROS_PRESTAMOS_PERSONALES);

        }
        return respuesta;
    }

    /**
     * Checks for linea disponible zero and registros zero.
     *
     * @param calificacionCrediticiaDTO
     *            the calificacion crediticia DTO
     * @param cantidadPrestamos
     *            the cantidad prestamos
     * @return true, if successful
     */
    private boolean hasLineaDisponibleZeroAndRegistrosZero(CalificacionCrediticiaDTO calificacionCrediticiaDTO,
            int cantidadPrestamos) {
        return calificacionCrediticiaDTO.getLineaActualDisponible()!= null &&
        		BigDecimal.ZERO.compareTo(calificacionCrediticiaDTO.getLineaActualDisponible()) != 0
                && cantidadPrestamos == 0;
    }

    /**
     * Checks for linea disponible and total zero.
     *
     * @param calificacionCrediticiaDTO
     *            the calificacion crediticia DTO
     * @return true, if successful
     */
    private boolean hasLineaDisponibleAndTotalZero(CalificacionCrediticiaDTO calificacionCrediticiaDTO) {
        return calificacionCrediticiaDTO.getLineaActualDisponible()!= null &&
        		BigDecimal.ZERO.compareTo(calificacionCrediticiaDTO.getLineaActualDisponible()) == 0
                && BigDecimal.ZERO.compareTo(calificacionCrediticiaDTO.getLineaTotalCrediticia()) == 0;
    }

    /**
     * Checks for linea disponible zero and linea total mayor zero.
     *
     * @param calificacionCrediticiaDTO
     *            the calificacion crediticia DTO
     * @return true, if successful
     */
    private boolean hasLineaDisponibleZeroAndLineaTotalMayorZero(CalificacionCrediticiaDTO calificacionCrediticiaDTO) {
        return calificacionCrediticiaDTO.getLineaActualDisponible()!= null &&
        		BigDecimal.ZERO.compareTo(calificacionCrediticiaDTO.getLineaActualDisponible()) == 0
                && BigDecimal.ZERO.compareTo(calificacionCrediticiaDTO.getLineaTotalCrediticia()) < 0;
    }

    /**
     * crear una entidad PrestamoDTO para retornar al PrestamoBO.
     *
     * @param numero
     *            the numero
     * @return entidad DTO.
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    private BigDecimal toBigDecimal(String numero) throws ImporteConvertException {
        return ISBANStringUtils.parseStringToBigDecimal(numero);
    }

    /**
     * Obtener mensaje ayuda linea total crediticia.
     *
     * @return the string
     */
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * obtenerMensajeAyudaLineaTotalCrediticia()
     */
    @Override
    public String obtenerMensajeAyudaLineaTotalCrediticia() {
        return buscarMensajeAyudaLinea(CodigoMensajeConstantes.AYUDA_PRESTAMO_LINEA_TOTAL_CREDITICIA);
    }

    /**
     * Obtener mensaje ayuda linea actual disponible.
     *
     * @return the string
     */
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * obtenerMensajeAyudaLineaActualDisponible()
     */
    @Override
    public String obtenerMensajeAyudaLineaActualDisponible() {
        return buscarMensajeAyudaLinea(CodigoMensajeConstantes.AYUDA_PRESTAMO_LINEA_CREDITO_DISPONIBLE);
    }

    /**
     * Obtiene el detalle del prestamo.
     *
     * @param numeroPrestamo
     *            the numero prestamo
     * @param cliente
     *            the cliente
     * @return the prestamo
     * @throws BusinessException
     *             the business exception
     */
    @Override
    public PrestamoDTO obtenerDetallePrestamo(String numeroPrestamo, Cliente cliente)
            throws BusinessException {
        List<Cuenta> cuentas = cliente.getCuentas();
        Cuenta cuentaPrestamo = new Cuenta();
        for (Cuenta cuenta : cuentas) {
            if (Integer.valueOf(numeroPrestamo).equals(cuenta.getIndex())) {
                cuentaPrestamo = cuenta;
            }
        }
        try {
            return getPrestamoDetails(cliente, cuentaPrestamo, false);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BusinessException();
        }
    }

    /**
     * Obtener detalle prestamo por cuenta.
     *
     * @param cliente the cliente
     * @param numeroPrestamo the numero prestamo
     * @return the prestamo
     * @throws BusinessException the business exception
     */
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * obtenerDetallePrestamoPorCuenta(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente, java.lang.String)
     */
    @Override
    public Prestamo obtenerDetallePrestamoPorCuenta(Cliente cliente, String numeroPrestamo) throws BusinessException {
        Prestamo prestamo = new Prestamo();
        try {
            prestamo = deudaPrestamoDAO.consultarDeudaDePrestamo(cliente, numeroPrestamo);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BusinessException();
        }

        return prestamo;
    }

    /**
     * Obtener info prestamos permitidos.
     *
     * @param cliente the cliente
     * @param cuenta the cuenta
     * @return the prestamo permitido out entity
     * @throws BusinessException the business exception
     */
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * obtenerInfoPrestamosPermitidos(ar.com.santanderrio.obp.servicios.clientes
     * .entities.Cliente,
     * ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta)
     */
    @Override
    public PrestamoPermitidoOutEntity obtenerInfoPrestamosPermitidos(Cliente cliente, Cuenta cuenta)
            throws BusinessException {

        PrestamoPermitidoInEntity datos = new PrestamoPermitidoInEntity();
        datos.setCliente(cliente);
        datos.setCuenta(cuenta);
        try {
            return prestamoPermitidoDAO.consultarPrestamosPermitidosPreacordados(datos);
        } catch (DAOException e) {
            LOGGER.error(e.getMessage(), e);
            if (CODIGO_ERROR_OPERACION_INHABILITADA.equals(e.getErrorCode())) {
            	throw new BusinessException(CodigoMensajeConstantes.CONSULTA_PRESTAMO_PERMITIDO_INHABILITADA);
            }
            throw new BusinessException(CodigoMensajeConstantes.ERROR_GENERICO_SIMULADOR);
        }
    }

    /**
     * Obtener prestamo cancelado.
     *
     * @param cliente the cliente
     * @return the list
     * @throws BusinessException the business exception
     */
    @Override
    /*
     * Solo deja pasar los prestamos Hipotecarios, Prendarios y Personales
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * obtenerPrestamoCancelado(ar.com.santanderrio.obp.servicios.clientes.
     * entities.Cliente)
     */
    public List<PrestamoCanceladoDTO> obtenerPrestamoCancelado(Cliente cliente) throws BusinessException {

        PrestamosCanceladosOutEntity prestamosCanceladosOutEntity = null;
        try {
            prestamosCanceladosOutEntity = prestamoDAO.obtenerCancelados(cliente);

        } catch (DAOException e) {
            LOGGER.error("Error al obtener prestamos cancelado", e);
            throw new BusinessException();
        }

        List<PrestamoCanceladoDTO> prestamosCancelados = new ArrayList<PrestamoCanceladoDTO>();
        int[] mostrarPrestamos = { TipoPrestamoEnum.HIPOTECARIOS.getId(), TipoPrestamoEnum.PERSONAL.getId(),
                TipoPrestamoEnum.PRENDARIO.getId() };
        for (PrestamoCanceladoOutEntity prestamoCancelado : prestamosCanceladosOutEntity.getListaResult()) {
            PreFormalizacion preformalizacion = null;
            String motivoPrestamo = null;
            try {
                if (StringUtils.isNotBlank(prestamoCancelado.getNumeroDeContrato())) {
                    preformalizacion = preFormalizacionPrestamoDAO.obtenerPreFormalizacion(cliente,
                            prestamoCancelado.getSucursal(), prestamoCancelado.getNumeroDeContrato());
                    motivoPrestamo = destinoPrestamoBO
                            .buscarDescripcionPorCodigoDestinoFondo(preformalizacion.getCodigoDestinoPrestamo());
                }
            } catch (DAOException e) {
                LOGGER.error("Error al obtener preformalizacion", e);
            }

            if (NumberUtils.isNumber(prestamoCancelado.getTipoDePrestamo())
                    && ArrayUtils.contains(mostrarPrestamos, Integer.valueOf(prestamoCancelado.getTipoDePrestamo()))) {
                prestamosCancelados.add(new PrestamoCanceladoDTO(prestamoCancelado, preformalizacion, motivoPrestamo));
            }
        }
        return prestamosCancelados;
    }

    /**
     * Obtener cantidad prestamos por clase.
     *
     * @param cliente the cliente
     * @return the info prestamos DTO
     */
    /*
     * (non-Javadoc)
     *
     * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#
     * obtenerCantidadPrestamosPorClase(ar.com.santanderrio.obp.servicios.
     * clientes.entities.Cliente)
     */
    @Override
    public InfoPrestamosDTO obtenerCantidadPrestamosPorClase(Cliente cliente) {

        InfoPrestamosDTO info = new InfoPrestamosDTO();

        for (Cuenta cuenta : cliente.getCuentas()) {

            if (StringUtils.isNumeric(cuenta.getClaseCuenta())
                    && TipoCuentaPrestamosEnum.fromCodigo(cuenta.getTipoCuenta()) != null
                    && !cuenta.getEstadoTarjetaCredito().equalsIgnoreCase(PRESTAMO_SECURITIZADO)) {
                if (TipoPrestamoEnum.HIPOTECARIOS.equals(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()))) {
                    info.setPrestamosHipotecarios(info.getPrestamosHipotecarios() + 1);
                }
                if (isPrestamoPersonal(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()))) {
                     info.setPrestamosPersonales(info.getPrestamosPersonales() + 1);
                }
                if (TipoPrestamoEnum.PRENDARIO.equals(TipoPrestamoEnum.fromIdString(cuenta.getClaseCuenta()))) {
                    info.setPrestamosPrendarios(info.getPrestamosPrendarios() + 1);
                }
            }
        }

        return info;
    }

	/**
	 * Obtener reporte pdf.
	 *
	 * @param comprobante the comprobante
	 * @return the respuesta
	 */
	/* (non-Javadoc)
	 * @see ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO#obtenerReportePdf(ar.com.santanderrio.obp.servicios.comprobantes.web.view.ReportComprobanteView)
	 */
	@Override
	public Respuesta<Reporte> obtenerReportePdf(ReportComprobanteView comprobante) {
		try {
			Reporte reporte = prestamoPDFDAO.obtenerReportePrestamoPDF(comprobante);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}


    /**
     * Obtener mensaje ayuda.
     *
     * @param codigoMensaje the codigo mensaje
     * @return the string
     */
    @Override
    public String obtenerMensajeAyuda(String codigoMensaje) {
        return buscarMensajeAyudaLinea(codigoMensaje);
    }

    /**
     * Obtener reporte pdf.
     *
     * @param comprobante the comprobante
     * @param isUva the is uva
     * @return the respuesta
     */
    @Override
	public Respuesta<Reporte> obtenerReportePdf(ReportComprobanteView comprobante, boolean isUva) {
		try {
			Reporte reporte = prestamoPDFDAO.obtenerReportePrestamoPDF(comprobante, isUva);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

	/**
	 * Verificar archivo prestamo sueldo.
	 *
	 * @param adjuntarArchivosInDto the adjuntar archivos in dto
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Boolean> verificarArchivoPrestamoSueldo(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		AdjuntarArchivosInEntity adjuntarArchivosInEntity = new AdjuntarArchivosInEntity();
		adjuntarArchivosInEntity.setArchivo(adjuntarArchivosInDto.getArchivo());
		return prestamoSueldosDAO.verificarVirusArchivoFs(adjuntarArchivosInEntity);
	}


	/**
	 * Borrar doc.
	 *
	 * @param adjuntarArchivosInDto the adjuntar archivos in dto
	 * @return the respuesta
	 */
	@Override
	public Respuesta<Boolean> borrarDoc(AdjuntarArchivosDTO adjuntarArchivosInDto) {
		AdjuntarArchivosInEntity adjuntarArchivosInEntity = new AdjuntarArchivosInEntity();
		adjuntarArchivosInEntity.setArchivo(adjuntarArchivosInDto.getArchivo());
		return prestamoSueldosDAO.borrarDoc(adjuntarArchivosInEntity);
	}

	/**
	 * Obtener nro prestamo normativo.
	 *
	 * @param cuenta the cuenta
	 * @param sucursal the sucursal
	 * @return the prestamo normativo out entity
	 */
	//TODO: ADD IATX CALL HERE
	private PrestamoNormativoOutEntity obtenerNroPrestamoNormativo(String cuenta, String sucursal) {
		PrestamoNormativoInEntity in = new PrestamoNormativoInEntity();
		in.setCuenta(cuenta);
		in.setSucursal(sucursal);
		try {
			List<PrestamoNormativoOutEntity> list = prestamoNormativoDAO.consultarNroPrestamoViejo(in);
			if(!list.isEmpty()) {
				return list.get(0);
			}
		} catch (DAOException e) {
			LOGGER.error("Error consultando el store");
		}
		return null;
	}

	private PreFormalizacion getTipoPrestamo(Prestamo prestamo) {
        PreFormalizacion preFormalizacion = null;
	    try {
            preFormalizacion = preFormalizacionPrestamoDAO.obtenerPreFormalizacion(prestamo);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return preFormalizacion;
    }

	/**
	 * Confirmar prestamos stop debit.
	 *
	 * @param confirmacionStopDebitDTO the confirmacion stop debit DTO
	 * @param sesionCliente the sesion cliente
	 * @return the respuesta
	 */
	@Override
	public Respuesta<CompStopDebitPrestamoOutEntity> confirmarPrestamosStopDebit(ConfirmacionStopDebitDTO confirmacionStopDebitDTO, SesionCliente sesionCliente) {
		StopDebitPrestamosInEntity inEntity = new StopDebitPrestamosInEntity();
		Cliente cliente = sesionCliente.getCliente();

		this.cargarInfoCabeceraWS(inEntity, cliente.getNup());
		this.cargarInfoRequeridaWS(inEntity, confirmacionStopDebitDTO.getPeriodosStopDebitPrestamos());
		CompStopDebitPrestamoOutEntity rsp  = new CompStopDebitPrestamoOutEntity();
		try {
			rsp = stopDebitPrestamosDAO.confirmarStopDebitPrestamos(inEntity , sesionCliente);
			if (rsp.getComprobante() != null) {
				return respuestaFactory.crearRespuestaOk(rsp);
			} else {
				LOGGER.error("Error en el DAO de confirmarPrestamosStopDebit.");
				return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
						CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
			}
		} catch (DAOException e) {
			LOGGER.error("Hubo un error al invocar al ws altaGestionUser en confirmarPrestamosStopDebit", e);
			throw new RobotException(TipoError.ERROR_GENERICO.getDescripcion());
		}
	}

	/**
	 * Cargar info cabecera WS.
	 *
	 * @param inEntity the in entity
	 * @param nup the nup
	 */
	private void cargarInfoCabeceraWS(StopDebitPrestamosInEntity inEntity, String nup) {
		LOGGER.info("Se ejecuta el metodo de carga de info parcial PrestamoBO.cargarInfoCabeceraWS");
		inEntity.setCodAsociacion(COD_ASOCIACION);
		inEntity.setTipoPersona(TIPO_PERSONA_FISICA);
		inEntity.setNup(Integer.parseInt(nup));
		inEntity.setCodUser(COD_USER);
		inEntity.setCodSector(COD_SECTOR);
		inEntity.setMedioIngreso(MEDIO_INGRESO);
		inEntity.setComentario(COMENTARIO_ALTA);

	}


	/**
	 * Cargar info requerida WS.
	 *
	 * @param inEntity the in entity
	 * @param listPeriodosPrestamos the list periodos prestamos
	 */
	private void cargarInfoRequeridaWS(StopDebitPrestamosInEntity inEntity, List<PeriodosStopDebitPrestamo> listPeriodosPrestamos) {
		LOGGER.info(
				"Se ejecuta el metodo de carga de info requerida para el WS PrestamoBO.cargarInfoRequerida");
		ArrayOf158770343432493182NillableInfoRequeridaWS infoRequerida = new ArrayOf158770343432493182NillableInfoRequeridaWS();

        InfoRequeridaWS infoTipoPrestamo = crearItemInfoRef(COD_TIPO_PRESTAMO, DESC_TIPO_PRESTAMO, listPeriodosPrestamos, true);
        InfoRequeridaWS infoTipoPeriodo = crearItemInfoRef(COD_TIPO_PERIODO, DESC_TIPO_PERIODO, listPeriodosPrestamos,false);

		infoRequerida.getInfoRequeridaWS().add(infoTipoPrestamo);
		infoRequerida.getInfoRequeridaWS().add(infoTipoPeriodo);
		inEntity.setInfoRequerida(infoRequerida);
	}

	/**
	 * Crear item info ref.
	 *
	 * @param cod the cod
	 * @param desc the desc
	 * @param listPeriodosPrestamos the list periodos prestamos
	 * @param isprestamo the isprestamo
	 * @return the info requerida WS
	 */
	private InfoRequeridaWS crearItemInfoRef(Integer cod, String desc, List<PeriodosStopDebitPrestamo> listPeriodosPrestamos,boolean isprestamo) {
		InfoRequeridaWS infoRequerida = new InfoRequeridaWS();
		infoRequerida.setCodigo(cod);
		infoRequerida.setDescripcion(desc);
		ArrayOf324931821440052060NillableString valor = new ArrayOf324931821440052060NillableString();
		if (listPeriodosPrestamos != null) {
			for (PeriodosStopDebitPrestamo prestamosperiodosStopDebitPrestamo : listPeriodosPrestamos) {

				if(isprestamo) {
					String[] prestamoSplit = prestamosperiodosStopDebitPrestamo.getNroPrestamo().split("\\-");
					String prestamoSinBarra = prestamoSplit[1].replace("/", "");
					String prestamo = ISBANStringUtils.formateadorConCerosIzq(prestamoSinBarra, 20);

					valor.getString().add(prestamo);
				}else {
					String periodos = prestamosperiodosStopDebitPrestamo.getPeriodoStopDebit().replace(",", "");
					valor.getString().add(periodos);
				}
			}
		}
		infoRequerida.setValor(valor);
		return infoRequerida;
	}


	@Override
	public Respuesta<Reporte> descargarCompStopDebitPrestamo(ComprobanteStopDebitPrestamoView comprobanteStopDebitPrestamoView) {
		try {
			Reporte reporte = reporteStopDebitPrestamosDAO.generarComprobanteStopDebitPrestamos(comprobanteStopDebitPrestamoView);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

	@Override
	public PrestamoSueldoTasaSubsidiadaEntity consultarPrestamoATPVigente(Cliente cliente){
		PrestamoSueldoTasaSubsidiadaEntity prestamoSueldoTasaSubsidiadaEntity;
		try {
			if (cliente.getNumeroCUILCUIT() == null) {
	            try {
	                DetalleDocumentoDTO detalleDocumento;
	                detalleDocumento = aliasCuentaBO.obtenerDocumentoValidoDTO(cliente);
	                String cuilCuitCliente = aliasCuentaBO.obtenerCuitPorDetalle(detalleDocumento);
	                cliente.setNumeroCUILCUIT(cuilCuitCliente);
	            } catch (DAOException e) {
	                cliente.setNumeroCUILCUIT(null);
	            }
	        }
			prestamoSueldoTasaSubsidiadaEntity = prestamoSueldosDAO.consultarPrestamoATPVigente(cliente);
		} catch (DAOException e) {
			PrestamoSueldoTasaSubsidiadaEntity prestamoSueldoError = new PrestamoSueldoTasaSubsidiadaEntity();
			prestamoSueldoError.setEstado("ERROR");
			return prestamoSueldoError;
		}
		return prestamoSueldoTasaSubsidiadaEntity;
	}

	@Override
	public Respuesta<String> altaPrestamoSueldoTasaSubsidiada(PrestamoSueldoTasaSubsidiadaView prestamoSueldoView, Cliente cliente, Cuenta cuenta) {
		try {
			return respuestaFactory.crearRespuestaOk(prestamoSueldosDAO.altaPrestamoSueldoTasaSubsidiada(prestamoSueldoView, sesionParametros.getComprobantePrestamoTasaSub(), cliente, cuenta));
		} catch (DAOException e) {
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje("Error al dar de alta el prestamo de sueldo con tasa subsidiada.");
			item.setTipoError(TipoError.ERROR_ALTA_PRESTAMO_TASA_SUBSIDIADA.getDescripcion());
			return respuestaFactory.crearRespuestaError(String.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

	@Override
	public Respuesta<Reporte> descargarCompPrestamoTasaSub(ComprobantePrestamoTasaSubsidiadaView comprobante) {
		try {
			Reporte reporte = reportePrestamosTasaSubsidiadaDAO.generarComprobantePrestamoTasaSubsidiada(comprobante);
			return respuestaFactory.crearRespuestaOk(reporte);
		} catch (DAOException e) {
			LOGGER.error(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle(), e);
			ItemMensajeRespuesta item = new ItemMensajeRespuesta();
			item.setMensaje(ComprobantesBOEnum.MENSAJE_ERROR_REPORTE_PDF.getDetalle());
			item.setTipoError(TipoError.ERROR_DESCARGA_COMPROBANTE.getDescripcion());
			return respuestaFactory.crearRespuestaError(Reporte.class,
					new ArrayList<ItemMensajeRespuesta>(Arrays.asList(item)));
		}
	}

	@Override
	public Respuesta<ConsultaCbuEntityOut> validarCbuPrestamos(ValidarCbuInDTO validarCbuInDTO) throws Exception {
		ConsultaCBUEntityIn entityIn = generarConsultaCbuRequest(validarCbuInDTO);
        LOGGER.info("DebinWSSolicitudesBO iniciando llamada DAO CNSTITCBU");
        ConsultaCbuEntityOut entityOut = solicitudesDebinWSDAO.consultarCNSTITCBU(entityIn);
        if (StringUtils.isEmpty(entityOut.getTitular())){
            return respuestaFactory.crearRespuestaError(null, TipoError.DEBINWS_ERROR_VALIDACION_ALIAS_CBU, CodigoMensajeConstantes.DEBINWS_ERROR_CBU_TITULARIDAD); //1568 en transferencias
        }

        return respuestaFactory.crearRespuestaOk(entityOut);
	}

    private ConsultaCBUEntityIn generarConsultaCbuRequest(ValidarCbuInDTO validarCbuAliasInDTO) {
        ConsultaCBUEntityIn rq = new ConsultaCBUEntityIn();
        rq.setCbuDestino(validarCbuAliasInDTO.getCbuDestino());
        rq.setNroCuenta(validarCbuAliasInDTO.getNroCuenta());
        rq.setNroSucursal(validarCbuAliasInDTO.getNroSucursal());
        rq.setTipoCuenta(validarCbuAliasInDTO.getTipoCuenta());
        rq.setCliente(sesionCliente.getCliente());
        rq.setDireccionIP(sesionCliente.obtenerIpV4SinPuntos());
        rq.setNroTarjeta(validarCbuAliasInDTO.getNroTarjeta());
        return rq;
    }

	@Override
	public String agregarCBU(PrestamoSueldosAgregarCBUEntity agregarCBUEntity) {
		try {
			return prestamoSueldosDAO.agregarCBU(agregarCBUEntity);
		} catch (DAOException e) {
			return "-1";
		}
	}

	//TODO: REVIEW TABLA/SERVICE NORMATIVOS
	@Override
    public Respuesta<CancelarPrestamoDTO> consultarCancelarPrestamos(final CancelacionPrestamoRequestView cancelacionView, final boolean simular)
            throws BusinessException {
        final Respuesta<CancelarPrestamoOutEntity> iatxResponse = consultarServicioCancelarPrestamos(cancelacionView, simular);
        final Respuesta<CancelarPrestamoDTO> response = Respuesta.copy(CancelarPrestamoDTO.class, iatxResponse);
        final CancelarPrestamoDTO cancelarPrestamoDTO = new CancelarPrestamoDTO();
        cancelarPrestamoDTO.setIatxResponse(iatxResponse.getRespuesta());

        if(!simular && EstadoRespuesta.OK.equals(response.getEstadoRespuesta())) {
            Cuenta prestamo = buscarObjetoCuentaUtilizadoEnCancelacion(cancelacionView.getNumeroPrestamo().split("-"));
            PrestamoNormativoOutEntity prestamoNormativo = obtenerNroPrestamoNormativo(prestamo.getNroCuentaProducto(),
                    prestamo.getNroSucursal());
            cancelarPrestamoDTO.setPrestamoNormativo(prestamoNormativo);
        }
        response.setRespuesta(cancelarPrestamoDTO);
        return response;
    }

    @Override
    public Respuesta<CancelarPrestamoDTO> consultarCancelarPrestamos(final CancelacionPrestamoRequestView cancelacionView)
            throws BusinessException {
	    return consultarCancelarPrestamos(cancelacionView, false);
    }

    private Respuesta<CancelarPrestamoOutEntity> consultarServicioCancelarPrestamos(final CancelacionPrestamoRequestView cancelacionView, final boolean simular)
            throws BusinessException {
        final Cliente cliente = sesionCliente.getCliente();
        final CancelarPrestamoInEntity request = prepararDatosIATX(cancelacionView, simular);
        final Respuesta<CancelarPrestamoOutEntity> serviceResponse = new Respuesta<CancelarPrestamoOutEntity>();

        try {
            CancelarPrestamoOutEntity respuestaEntity = prestamoDAO.consultarServicioCancelarPrestamos(cliente, request);
            processCancelarPrestamosResponse(respuestaEntity, serviceResponse);
            return serviceResponse;
        } catch (DAOException e) {
            throw new BusinessException();
        }
    }

    private void processCancelarPrestamosResponse(CancelarPrestamoOutEntity responseIatx, Respuesta<CancelarPrestamoOutEntity> target) {
        if (responseIatx.getTieneError()) {
            Pair<TipoError, String> errorPair = iatxErrorMap.containsKey(responseIatx.getCodigoRetornoExtendido()) ?
                    iatxErrorMap.get(responseIatx.getCodigoRetornoExtendido()) : iatxErrorMap.get(TipoError.ERROR_GENERICO.getDescripcion());
            target.add(respuestaFactory.crearItemMensajeRespuesta("", errorPair.getKey(), errorPair.getValue()));
            target.setEstadoRespuesta(EstadoRespuesta.ERROR);
        } else {
            target.setRespuesta(responseIatx);
            target.setEstadoRespuesta(EstadoRespuesta.OK);
        }
    }

	private CancelarPrestamoInEntity prepararDatosIATX(CancelacionPrestamoRequestView cancelacionView, boolean simular) {
		Cuenta prestamo = buscarObjetoCuentaUtilizadoEnCancelacion(cancelacionView.getNumeroPrestamo().split("-"));
		Cuenta cuentaElegida = buscarObjetoCuentaUtilizadoEnCancelacion(cancelacionView.getNumeroCuenta().split("-"));
		DateTime fecha = new DateTime();

        if(!simular) {
            ComprobanteCancelacionTotalPrestamoView comprobanteCancelacion = sesionParametros.getComprobanteCancelacionTotalPrestamo();
            comprobanteCancelacion.setNroCuentaDebito(cancelacionView.getNumeroCuenta());
            comprobanteCancelacion.setTipoCuentaDebito(cuentaElegida.getTipoCuentaEnum().getDescripcionConMoneda());
            sesionParametros.setComprobanteCancelacionTotalPrestamo(comprobanteCancelacion);
        }

        return CancelarPrestamoInEntity.builder()
            .sucursalPmo(prestamo.getNroSucursal())
            .cuentaPmo(prestamo.getNroCuentaProducto().substring(prestamo.getNroCuentaProducto().length()-12, prestamo.getNroCuentaProducto().length()))
            .divisa(DivisaEnum.PESO.getCodigo())
            .motivoCancelacion("A")
		    .indSimulacion(simular ? "S" : "N")
		    .fecValor(fecha.toString("yyyy-MM-dd"))
		    .formaPagCargo("1")
            .sucursalAltairCargo(cuentaElegida.getOficinaAltair())
		    .cuentaAltairCargo(cuentaElegida.getContratoAltair().substring(cuentaElegida.getContratoAltair().length()-12, cuentaElegida.getContratoAltair().length()))
		    .codigoConcepto("   ")
		    .tipoApl("000000000")
		    .impApl("00000000000000000")
            .build();
	}

	private Cuenta buscarObjetoCuentaUtilizadoEnCancelacion(String[] numeroCuentaVector) {
		String[] numeroCuenta = numeroCuentaVector[1].split("\\/");
		return sesionCliente.getCliente().getCuentaSiContieneNumero(numeroCuenta[0]);
	}

	@Override
	public Respuesta<Reporte> generarComprobantePDF(ComprobanteCancelacionTotalPrestamoView datosComprobante) {
		Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
		Reporte reporte = prestamoDAO.generarComprobantePDF(datosComprobante);
		respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(reporte);
        return respuesta;
	}

	@Override
	public Boolean getPrestamoCom12123(Prestamo prestamo) {
		try {
			return prestamoDAO.getIsPrestamosCOM12123(prestamo.getCtaRelacionada());
		} catch (DAOException e) {
			LOGGER.info("No se pudo consultar si el prestamo califica con COM12123");
			return Boolean.FALSE;
		}
	}

    @Override
    public PrestamosEncoladosEntity obtenerPrestamosEncolados() {

        String nup = sesionCliente.getCliente().getNup();
        try {
            return prestamoEncoladoDAO.getPrestamosEncolados(nup);
        } catch (DAOException e) {
            LOGGER.info("No se pudo consultar el servicio de prestamos encolados.");
            return new PrestamosEncoladosEntity();
        }
    }

    @Override
    public PrestamoEncoladoEntity obtenerPrestamoEncolado(String id) throws DAOException {
        return prestamoEncoladoDAO.getPrestamoEncolado(id);
    }

    @Override
    public void cancelarPrestamoEncolado(String loandId) throws DAOException {
        prestamoBackendDAO.cancelarPrestamo(loandId);
    }

	@Override
	public EncolamientoResponseDTO encolarPrestamo(EncolamientoRequestDTO encolamientoRequestDTO) throws DAOException {
		if ("PRESTAMOS".equalsIgnoreCase(encolamientoRequestDTO.getOrigenFront()))
			return prestamosObpBffDAO.encolarPrestamo(encolamientoRequestDTO);
		return prestamosBffDAO.encolarPrestamo(encolamientoRequestDTO);
	}

	@Override
	public LiquidacionResponseDTO liquidarPrestamo(TipoOfertaEnum tipoOferta, String nup) throws DAOException {
		return this.liquidarPrestamo(tipoOferta, nup, null);
	}

	@Override
	public LiquidacionResponseDTO liquidarPrestamo(TipoOfertaEnum tipoOferta, String nup, String origenFront)
			throws DAOException {
		if ("PRESTAMOS".equalsIgnoreCase(origenFront))
			return prestamosObpBffDAO.liquidarPrestamo(tipoOferta, nup);
		return prestamosBffDAO.liquidarPrestamo(tipoOferta, nup);
	}

	@Override
	public PagoCuotaResponseDTO pagarCuota(PagoCuotaRequestDTO pagoCuotaRequestDTO) throws DAOException {
		return prestamosObpBffDAO.pagarCuota(sesionCliente.getCliente().getNup(), pagoCuotaRequestDTO);
	}

	@Override
	public CancelacionAnticipadaResponseDTO cancelarAnticipadamente(CancelacionAnticipadaRequestDTO request) throws DAOException {
		return prestamosObpBffDAO.cancelarAnticipadamente(sesionCliente.getCliente().getNup(), request);
	}
}
