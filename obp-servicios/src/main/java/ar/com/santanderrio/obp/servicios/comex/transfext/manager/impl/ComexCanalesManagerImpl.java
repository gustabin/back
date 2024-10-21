package ar.com.santanderrio.obp.servicios.comex.transfext.manager.impl;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexCanalesBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.bo.ComexConsultasBO;
import ar.com.santanderrio.obp.servicios.comex.transfext.common.EstadoTransferenciaEnum;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.AdjuntarArchivosDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ComprobanteComexDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaBancosOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaDetalleTrfOBPOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaMonedaOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ConsultaOperacionesOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexInDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.dto.ProcesarTransferenciaComexOutDTO;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ArchivoTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.BancoOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaDetalleTrfOBPOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaMonedasView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesInView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesOutView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ConsultaOperacionesView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosBeneficiarioView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DatosTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.DocumentacionAdjuntaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.EstadosTransferenciaView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ProcesarTransferenciaComexView;
import ar.com.santanderrio.obp.servicios.comex.transfext.view.ReporteView;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ComexCanalesManagerImpl.
 *
 * @author IT Resources
 */
@Component
public class ComexCanalesManagerImpl implements ComexCanalesManager {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ComexCanalesManagerImpl.class);

    /** The comex canales BO. */
    @Autowired
    private ComexCanalesBO comexCanalesBO;

    /** The comex consultas BO. */
    @Autowired
    private ComexConsultasBO comexConsultasBO;

    /** The mensaje BO. */
    @Autowired
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The autentificacion manager. */
    @Autowired
    protected AutentificacionManager autentificacionManager;

    /** The Cuenta BO. */
    @Autowired
    protected CuentaBO cuentaBO;

    @Autowired
    private DesafioOperacionRSA<ProcesarTransferenciaComexView> desafioOperacionRSA;
    
    @Autowired
    private LegalBO legalBO;
    

    /** The valor desafio transferencia comex. */
    @Value("${TRJCOORD.OPERAINDISTINTO.TRANSFERENCIA_COMEX:3}")
    private Integer valorDesafioTransferenciaComex;

    /** The Constant OPS_POR_PAGINA. */
    private static final int OPS_POR_PAGINA = 10;

    /** The Constant ESTADO_TRANSFERENCIA_L. */
    private static final String ESTADO_TRANSFERENCIA_L = "L";

    /** The Constant ESTADO_TRANSFERENCIA_A. */
    private static final String ESTADO_TRANSFERENCIA_A = "A";

    /** The Constant ESTADO_TRANSFERENCIA_A. */
    private static final String ESTADO_TRANSFERENCIA_EN_TRAMITE = "G";

    /** The Constant TIPO_OPERACION_ALTA. */
    private static final String TIPO_OPERACION_ALTA = "A";

    /** The Constant TIPO_OPERACION_MODIFICAR. */
    private static final String TIPO_OPERACION_MODIFICAR = "M";

    /** The Constant ACEPTA_DDJJ_SI. */
    private static final String ACEPTA_DDJJ_SI = "1";

    /** The Constant ACEPTA_DDJJ_NO. */
    private static final String ACEPTA_DDJJ_NO = "0";

    /** The Constant CONCEPTO_I07. */
    private static final String CONCEPTO_I07 = "I07";

    /** The Constant CONCEPTO_I08. */
    private static final String CONCEPTO_I08 = "I08";

    /** The Constant CUENTA. */
    private static final String CUENTA = "Cuenta";

    /** The Constant BARRA. */
    private static final String BARRA = "/";

    /** The Constant DELIMITADORES. */
    private static final char[] DELIMITADORES = { '.', ' ', '-', '(', ')', '/', ',' };

    /** The Constant TIPO_CUENTA. */
    private static final String TIPOS_CUENTA_UNICA = "9|10|09";

    /** The Constant CU_PESOS. */
    private static final String CU_PESOS = "09";

    /** The Constant CU_DOLARES. */
    private static final String CU_DOLARES = "10";

    /** The Constant CUENTA_UNICA. */
    private static final String CUENTA_UNICA = "02";

    /** The Constant PESOS. */
    private static final String PESOS = "1";

    /** The Constant DOLARES. */
    private static final String DOLARES = "2";

    /** The Constant TIPOS_CUENTA_PESOS. */
    private static final String TIPOS_CUENTA_PESOS = "00|02|01|09";

    /** The Constant TIPOS_CUENTA_DOLARES. */
    private static final String TIPOS_CUENTA_DOLARES = "03|04|10";

    private static final String ERROR_AL_OBTENER_RESUMEN_CUENTA = "Error al traer resumen de cuenta";

    /** The max file size. */
    @Value("${COMEX.MAX.FILESIZE}")
    private String maxFileSize;
    /** The max file size. */
    @Value("${COMEX.TIPO.ARCHIVOS.ADJ}")
    private String tiposDeArchivosPermitidos;

    /** The cantidad max de archivos posibles ajuntos. */
    @Value("${COMEX.MAX.FILES.ATACHED}")
    private String cantidadMaxDeArchivosPosiblesAjuntos;

    /** The tiposcuentas validas. */
    @Value("#{'${TIPO.CUENTAS.VALIDAS.COMEX}'.split('\\|')}")
    private List<String> tiposcuentasValidas;

    /** The tipos conceptos validos para adjuntar. */
    @Value("#{'${COMEX.CONCEPTOS.DOCUMENTACION.ADJUNTA}'.split('\\|')}")
    private List<String> conceptosValidosAdjuntar;
    

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfExt.manager.ComexCanalesManager
     * #consultaDetalleTrf(ar.com.santanderrio.obp.servicios.comex.transfExt.view.
     * ConsultaDetalleTrfOBPInView)
     */
    @Override
    public Respuesta<ConsultaDetalleTrfOBPOutView> consultaDetalleTrf(
            ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView) {

        ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
        ConsultaOperacionesView operacion = obtenerOperacion(consultaOperacionesOutView.getOperaciones(),
                consultaDetalleTrfOBPInView.getNroTransferencia());
        if (consultaDetalleTrfOBPInView.getNroTransferencia() == null || consultaOperacionesOutView == null) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO);
        } else if (operacion == null) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO);
        } else {
            ConsultaDetalleTrfOBPInDTO consultaDetalleTrfOBPInDTO = consultaDetalleTrfOBPInDTO(
                    consultaDetalleTrfOBPInView);
            Respuesta<ConsultaDetalleTrfOBPOutDTO> respuestaBO = comexCanalesBO
                    .consultaDetalleTrf(consultaDetalleTrfOBPInDTO, operacion);

            if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
            }

            ConsultaDetalleTrfOBPOutView consultaDetalleTrfOBPOutView;

            BancoOutView bancoObligatorio = null;
            ConsultaBancosInDTO consultaBancosInDTO = new ConsultaBancosInDTO();
            if (StringUtils.isNotBlank(respuestaBO.getRespuesta().getCodigoBanco())) {

                consultaBancosInDTO.setCodigoBancario(respuestaBO.getRespuesta().getDescripcionBanco());
                consultaBancosInDTO.setTipoCodigo(respuestaBO.getRespuesta().getCodigoBanco());
                bancoObligatorio = obtenerBanco(consultaBancosInDTO);
                if (bancoObligatorio == null) {
                    return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                            CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO);
                }

            }

            BancoOutView bancoIntermediario = null;

            if (StringUtils.isNotBlank(respuestaBO.getRespuesta().getCodigoBancoIntermediario())) {
                consultaBancosInDTO.setCodigoBancario(StringUtils.removeStart(respuestaBO.getRespuesta().getDescripcionBancoIntermediario(), "/FW"));
                consultaBancosInDTO.setTipoCodigo(respuestaBO.getRespuesta().getCodigoBancoIntermediario());

                bancoIntermediario = obtenerBanco(consultaBancosInDTO);
                if (bancoIntermediario == null) {
                    return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                            CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO);
                }
            }

            try {
                consultaDetalleTrfOBPOutView = createConsultaDetalleTrfOBPOutView(respuestaBO.getRespuesta(), operacion,
                        bancoObligatorio, bancoIntermediario);
            } catch (BusinessException e) {
                LOGGER.error(e.getMessage(), e);
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
            }

            if (consultaDetalleTrfOBPOutView.getDatosTransferencia().getConceptoCodigo().equals(CONCEPTO_I07)
                    || consultaDetalleTrfOBPOutView.getDatosTransferencia().getConceptoCodigo().equals(CONCEPTO_I08)) {
                estadisticaManager.add(EstadisticasConstants.PROCESAR_TRANSFERENCIA_VER_DETALLE_OK,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            }

            return respuestaFactory.crearRespuestaOk(ConsultaDetalleTrfOBPOutView.class, consultaDetalleTrfOBPOutView);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #consultaOperaciones(ar.com.santanderrio.obp.servicios.comex.transfext.view.
     * ConsultaOperacionesInView)
     */
    @Override
    public Respuesta<ConsultaOperacionesOutView> consultaOperaciones(
            ConsultaOperacionesInView consultaOperacionesInView) {
        ConsultaOperacionesInDTO consultaOperacionesInDTO = createConsultaOperacionesInDTO(consultaOperacionesInView);
        Respuesta<ConsultaOperacionesOutDTO> responseBO = comexCanalesBO.consultaOperaciones(consultaOperacionesInDTO);
        Map<String, ConsultaMonedaOutDTO> monedasMap = sesionParametros.getMonedasComexMap();

        if (monedasMap == null) {
            Respuesta<List<ConsultaMonedaOutDTO>> respuestaBOConsultaMonedas = comexConsultasBO.consultaMonedas();
            if (respuestaBOConsultaMonedas.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            	comexConsultasBO.limpiarMonedas();
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO);
            }
            monedasMap = obtenerMapaMonedas(respuestaBOConsultaMonedas.getRespuesta());
            sesionParametros.setMonedasComexMap(monedasMap);
        }

        if (responseBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO);
        }

        estadisticaManager.add(EstadisticasConstants.CONSULTA_OPERACIONES_COMEX,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        if (responseBO.getRespuesta().getOperaciones().isEmpty()) {
            return armarRespuestaVaciaConsultaOperaciones(responseBO, consultaOperacionesInView.getEsBuscador(),
                    monedasMap);
        }

        ConsultaOperacionesOutView consultaOperacionesOutView = crearConsultaOperacionesOutView(responseBO, monedasMap);
        ConsultaOperacionesOutView consultaOperacionesOutViewClone = consultaOperacionesOutView.clone();
        if (consultaOperacionesInView.getEsBuscador() && !consultaOperacionesOutView.getOperaciones().isEmpty()) {
            estadisticaManager.add(EstadisticasConstants.CONSULTA_OPERACIONES_BUSQUEDA_COMEX,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }
        sesionParametros.setConsultaOperacionesOutView(consultaOperacionesOutView);
        sesionParametros.setCantPaginasDetalleComex(
                (int) Math.ceil((double) consultaOperacionesOutView.getOperaciones().size() / OPS_POR_PAGINA));
        consultaOperacionesOutViewClone.setOperaciones(
                this.obtenerRegistrosPorPagina(sesionParametros.getConsultaOperacionesOutView().getOperaciones()));
        if (sesionParametros.getCantPaginasDetalleComex() > 1) {
            consultaOperacionesOutViewClone.setHayMasOperaciones(true);
        }
        sesionParametros.setNroPaginaComexDetalle(1);
        if (!consultaOperacionesInView.getEsBuscador()) {
            consultaOperacionesOutViewClone.setConsultaMonedasList(obtenerListaMonedas(monedasMap));
        }
        
		try {
			consultaOperacionesOutViewClone.setAvisoLegal(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_AVISO_TRANSFERENCIAS_EXTERIOR));
			consultaOperacionesOutViewClone.setAvisoLegalPopup(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_AVISO_TRANSFERENCIAS_EXTERIOR_POPUP));
		} catch (DAOException e) {
			consultaOperacionesOutViewClone.setAvisoLegal(StringUtils.EMPTY);
			consultaOperacionesOutViewClone.setAvisoLegalPopup(StringUtils.EMPTY);
		}
                
        return respuestaFactory.crearRespuestaOk(ConsultaOperacionesOutView.class, consultaOperacionesOutViewClone);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #consultaOperacionesMostrarMas()
     */
    @Override
    public Respuesta<ConsultaOperacionesOutView> consultaOperacionesMostrarMas() {
        ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
        if (consultaOperacionesOutView == null) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_GENERICO);
        }
        return respuestaFactory.crearRespuestaOk(ConsultaOperacionesOutView.class,
                this.obtenerRegistrosPorPaginaRellamado(consultaOperacionesOutView));
    }

    /**
     * Obtener mapa monedas.
     *
     * @param respuesta
     *            the respuesta
     * @return the map
     */
    private Map<String, ConsultaMonedaOutDTO> obtenerMapaMonedas(List<ConsultaMonedaOutDTO> respuesta) {
        Map<String, ConsultaMonedaOutDTO> monedasMap = new HashMap<String, ConsultaMonedaOutDTO>();
        for (ConsultaMonedaOutDTO moneda : respuesta) {
            monedasMap.put(moneda.getCodigoMoneda(), moneda);
        }
        return monedasMap;
    }

    /**
     * Creates the consulta detalle trf OBP out view.
     *
     * @param respuesta
     *            the respuesta
     * @param operacion
     *            the operacion
     * @param bancoObligatorio
     *            the banco obligatorio
     * @param bancoIntermediario
     *            the banco intermediario
     * @return the consulta detalle trf OBP out view
     * @throws BusinessException
     *             the business exception
     */
    private ConsultaDetalleTrfOBPOutView createConsultaDetalleTrfOBPOutView(ConsultaDetalleTrfOBPOutDTO respuesta,
            ConsultaOperacionesView operacion, BancoOutView bancoObligatorio, BancoOutView bancoIntermediario)
            throws BusinessException {
        Map<String, ConsultaMonedaOutDTO> monedasMap = sesionParametros.getMonedasComexMap();
        ConsultaDetalleTrfOBPOutView consultaDetalleTrfOBPOutView = new ConsultaDetalleTrfOBPOutView();
        consultaDetalleTrfOBPOutView.setImporteTransferencia(respuesta.getImporteTransferencia());
        consultaDetalleTrfOBPOutView.setEstadoTransferencia(respuesta.getEstadoTransferencia());
        consultaDetalleTrfOBPOutView.setFechaOperacion(respuesta.getFechaOperacion());
        consultaDetalleTrfOBPOutView.setMotivoRechazo(respuesta.getMotivoRechazo());
        consultaDetalleTrfOBPOutView.setDescripcionMotivo(respuesta.getDescripcionMotivo());

        if (StringUtils.isNotBlank(respuesta.getCodMoneda()) && monedasMap.containsKey(respuesta.getCodMoneda())) {
            ConsultaMonedaOutDTO moneda = monedasMap.get(respuesta.getCodMoneda());
            consultaDetalleTrfOBPOutView.setCodMoneda(respuesta.getCodMoneda());
            consultaDetalleTrfOBPOutView.setDescripcionMoneda(WordUtils.capitalizeFully(moneda.getDescripcionMoneda()));
            consultaDetalleTrfOBPOutView.setSimboloMoneda(moneda.getCodBCRAMoneda());
        }

        consultaDetalleTrfOBPOutView.setDatosBeneficiario(obtenerDatosBeneficiario(respuesta));
        consultaDetalleTrfOBPOutView.setDatosTransferencia(
                obtenerDatosTransferencia(respuesta, operacion, bancoObligatorio, bancoIntermediario));
        consultaDetalleTrfOBPOutView.setDocumentacionAdjuntaView(obtenerDocumentacionAdjunta(respuesta));
        consultaDetalleTrfOBPOutView.setConceptosValidos(conceptosValidosAdjuntar);

        return consultaDetalleTrfOBPOutView;
    }

    /**
     * Obtener documentacion adjunta.
     * 
     * @param respuesta
     *            the respuesta
     * @return the documentacion adjunta view
     */
    private DocumentacionAdjuntaView obtenerDocumentacionAdjunta(ConsultaDetalleTrfOBPOutDTO respuesta) {
        DocumentacionAdjuntaView documentacionAdjuntaView = new DocumentacionAdjuntaView();
        documentacionAdjuntaView.setArchivos(respuesta.getArchivos());
        return documentacionAdjuntaView;
    }

    /**
     * Obtener datos transferencia.
     * 
     * @param respuesta
     *            the respuesta
     * @param operacion
     *            the operacion
     * @param bancoObligatorio
     *            the banco obligatorio
     * @param bancoIntermediario
     *            the banco intermediario
     * @return the datos transferencia view
     * @throws BusinessException
     *             the business exception
     */
    private DatosTransferenciaView obtenerDatosTransferencia(ConsultaDetalleTrfOBPOutDTO respuesta,
            ConsultaOperacionesView operacion, BancoOutView bancoObligatorio, BancoOutView bancoIntermediario)
            throws BusinessException {
        DatosTransferenciaView datosTransferenciaView = new DatosTransferenciaView();

        Integer idCuentaOrigen = obtenerIdCuentaOrigen(operacion);
        datosTransferenciaView.setCodigoBanco(respuesta.getCodigoBanco());
        datosTransferenciaView.setCodigoBancoIntermediario(respuesta.getCodigoBancoIntermediario());
        datosTransferenciaView.setConceptoCodigo(respuesta.getConceptoCodigo());
        datosTransferenciaView.setConceptoDescripcion(respuesta.getConceptoDescripcion());
        datosTransferenciaView.setDescripcionBanco(respuesta.getDescripcionBanco());
        datosTransferenciaView.setDescripcionBancoIntermediario(respuesta.getDescripcionBancoIntermediario());
        datosTransferenciaView.setDestinoBancoIntermediario(respuesta.getDestinoBancoIntermediario());
        datosTransferenciaView.setDestino(respuesta.getDestino());
        datosTransferenciaView.setGastoACargo(respuesta.getGastoACargo());
        if (idCuentaOrigen != null) {
            datosTransferenciaView.setOrigen(respuesta.getOrigen());
            datosTransferenciaView.setIdCuentaOrigen(idCuentaOrigen);
        }
        datosTransferenciaView.setTipoCuentaOrigen(respuesta.getTipoCuentaOrigen());
        if (StringUtils.isNotBlank(respuesta.getCodTipoCuenta())) {
            if (respuesta.getCodTipoCuenta().matches(TIPOS_CUENTA_PESOS)) {
                datosTransferenciaView.setMoneda(PESOS);
            } else if (respuesta.getCodTipoCuenta().matches(TIPOS_CUENTA_DOLARES)) {
                datosTransferenciaView.setMoneda(DOLARES);
            }
        }

        datosTransferenciaView.setBancoIntermediario(bancoIntermediario);
        datosTransferenciaView.setBancoObligatorio(bancoObligatorio);

        return datosTransferenciaView;
    }

    /**
     * Obtener id cuenta origen.
     *
     * @param operacion
     *            the operacion
     * @return the integer
     * @throws BusinessException
     *             the business exception
     */
    private Integer obtenerIdCuentaOrigen(ConsultaOperacionesView operacion) throws BusinessException {
        Integer indiceCuenta = null;
        String[] parts = operacion.getCuentaCliente().split(Pattern.quote(ISBANStringUtils.GUION_STRING));
        String sucursal = StringUtils.EMPTY;
        String tipoCuenta = StringUtils.EMPTY;
        String nroCuentaProducto = StringUtils.EMPTY;
        for (int i = 0; i < parts.length; i++) {
            if (i == 0) {
                sucursal = parts[i];
            } else if (i == 1) {
                tipoCuenta = parts[i];
                if (tipoCuenta.matches(TIPOS_CUENTA_UNICA)) {
                    tipoCuenta = CUENTA_UNICA;
                }
            } else if (i == 2) {
                nroCuentaProducto = parts[i];
            }
        }

        List<ResumenDetalleCuenta> cuentasComex = sesionParametros.getCuentasComex();
        if (cuentasComex == null) {
            cuentasComex = obtenerCuentas(sesionCliente.getCliente());
            if (sesionCliente.getCliente().getClienteBancaPrivada()) {
				cargarCuentasBP(cuentasComex);
            }
            sesionParametros.setCuentasComex(cuentasComex);
        }

        for (int i = 0; i < cuentasComex.size(); i++) {
            if (tipoCuenta.matches(TIPOS_CUENTA_UNICA)) {
                tipoCuenta = CUENTA_UNICA;
            }

            if (sucursal.equals(ISBANStringUtils.formatearSucursal(cuentasComex.get(i).getNroSucursal()))
                    && tipoCuenta.equals(cuentasComex.get(i).getTipoCuenta()) && nroCuentaProducto
                            .equals(ISBANStringUtils.eliminarCeros(cuentasComex.get(i).getNroCuentaProducto()))) {
                indiceCuenta = i;
                break;
            }
        }
        return indiceCuenta;
    }

	/**
	 * Cargar cuentas BP.
	 *
	 * @param cuentas the cuentas
	 * @throws BusinessException the business exception
	 */
	private void cargarCuentasBP(List<ResumenDetalleCuenta> cuentas) throws BusinessException {
		try {
			Respuesta<List<ResumenDetalleCuenta>> respuesta = 
					comexConsultasBO.getCuentasSaldoOrdenadasBP(sesionCliente.getCliente());
			cuentas.addAll(respuesta.getRespuesta());
		} catch (SQLException e) {
			LOGGER.info("Error obteniendo cuentas BP");
		} catch (ImporteConvertException e) {
			LOGGER.info("Error obteniendo cuentas BP");
		}
	}

    /**
     * Obtener cuentas pesos para transferir.
     *
     * @param clienteOrigen
     *            the cliente origen
     * @return the list
     * @throws BusinessException
     *             the business exception
     */
    private List<ResumenDetalleCuenta> obtenerCuentas(Cliente clienteOrigen) throws BusinessException {
        List<Cuenta> cuentas = clienteOrigen.getCuentas();
        List<Cuenta> cuentasFiltradas = new ArrayList<Cuenta>();
        for (Cuenta cuenta : cuentas) {
            TipoCuenta tipoCuentaEnum = cuenta.getTipoCuentaEnum();
            if (tipoCuentaEnum == null) {
                continue;
            }
            if (tiposcuentasValidas.contains(String.valueOf(tipoCuentaEnum.getCodigo()))) {
                cuentasFiltradas.add(cuenta);
            }
        }

        if (cuentasFiltradas.isEmpty()) {
            return new ArrayList<ResumenDetalleCuenta>();
        }

        List<ResumenDetalleCuenta> cuentasValidas = cuentaBO.getCuentasSaldo(clienteOrigen, cuentasFiltradas)
                .getRespuesta();

        if (cuentasValidas == null || cuentasValidas.isEmpty()) {
            throw new BusinessException(ERROR_AL_OBTENER_RESUMEN_CUENTA);
        }

        return cuentasValidas;
    }

    /**
     * Obtener datos beneficiario.
     *
     * @param respuesta
     *            the respuesta
     * @return the datos beneficiario view
     */
    private DatosBeneficiarioView obtenerDatosBeneficiario(ConsultaDetalleTrfOBPOutDTO respuesta) {
        DatosBeneficiarioView datosBeneficiarioView = new DatosBeneficiarioView();
        datosBeneficiarioView.setNombreBeneficiario(respuesta.getNombreBeneficiario());
        // VINCULO_COMEX
        // datosBeneficiarioView.setVinculo(respuesta.getVinculo());
        datosBeneficiarioView.setDomicilioCalle(respuesta.getDomicilioCalle());
        datosBeneficiarioView.setDomicilioNumero(respuesta.getDomicilioNumero());
        datosBeneficiarioView.setDomicilioLocalidad(respuesta.getDomicilioLocalidad());
        datosBeneficiarioView.setDomicilioPais(respuesta.getDomicilioPais());
        datosBeneficiarioView.setCodPais(respuesta.getCodPais());
        return datosBeneficiarioView;
    }

    /**
     * Consulta detalle trf OBP in DTO.
     *
     * @param consultaDetalleTrfOBPInView
     *            the consulta detalle trf OBP in view
     * @return the consulta detalle trf OBP in DTO
     */
    private ConsultaDetalleTrfOBPInDTO consultaDetalleTrfOBPInDTO(
            ConsultaDetalleTrfOBPInView consultaDetalleTrfOBPInView) {
        ConsultaDetalleTrfOBPInDTO request = new ConsultaDetalleTrfOBPInDTO();
        request.setNroTransferencia(consultaDetalleTrfOBPInView.getNroTransferencia());
        return request;
    }

    /**
     * Obtener lista monedas.
     *
     * @param monedasMap
     *            the monedas map
     * @return the list
     */
    private List<ConsultaMonedasView> obtenerListaMonedas(Map<String, ConsultaMonedaOutDTO> monedasMap) {
        List<ConsultaMonedasView> monedasView = new ArrayList<ConsultaMonedasView>();
        for (Map.Entry<String, ConsultaMonedaOutDTO> entry : monedasMap.entrySet()) {
            ConsultaMonedasView monedaView = new ConsultaMonedasView();
            String descripcion = WordUtils.capitalizeFully(entry.getValue().getDescripcionMoneda()) + "("
                    + entry.getValue().getCodBCRAMoneda() + ")";
            monedaView.setDescripcion(descripcion);
            monedaView.setId(entry.getValue().getCodigoMoneda());
            monedaView.setCodigoBCRA(entry.getValue().getCodBCRAMoneda());
            monedasView.add(monedaView);
        }
        return monedasView;
    }

    /**
     * Obtener registros por pagina rellamado.
     *
     * @param consultaOperacionesOutView
     *            the consulta operaciones out view
     * @return the consulta operaciones out view
     */
    private ConsultaOperacionesOutView obtenerRegistrosPorPaginaRellamado(
            ConsultaOperacionesOutView consultaOperacionesOutView) {
        List<ConsultaOperacionesView> siguientesOperacionesView = new ArrayList<ConsultaOperacionesView>();
        ConsultaOperacionesOutView consultaOperacionesOutViewClone = consultaOperacionesOutView.clone();
        int cantPaginasDetalleComex = sesionParametros.getCantPaginasDetalleComex();
        int nroPaginaComex = sesionParametros.getNroPaginaComexDetalle();
        int siguientePagina = nroPaginaComex + 1;
        int tamanioMenor = nroPaginaComex * OPS_POR_PAGINA;
        if (siguientePagina < cantPaginasDetalleComex) {
            int tamanioSuperior = siguientePagina * OPS_POR_PAGINA;
            for (int i = tamanioMenor; i < tamanioSuperior; i++) {
                siguientesOperacionesView.add(consultaOperacionesOutView.getOperaciones().get(i));
            }
            consultaOperacionesOutViewClone.setHayMasOperaciones(true);
        } else if (siguientePagina == cantPaginasDetalleComex) {
            for (int i = tamanioMenor; i < consultaOperacionesOutView.getOperaciones().size(); i++) {
                siguientesOperacionesView.add(consultaOperacionesOutView.getOperaciones().get(i));
            }
            consultaOperacionesOutViewClone.setHayMasOperaciones(false);
        }
        sesionParametros.setNroPaginaComexDetalle(siguientePagina);
        consultaOperacionesOutViewClone.setOperaciones(siguientesOperacionesView);
        return consultaOperacionesOutViewClone;
    }

    /**
     * Obtener registros por pagina.
     *
     * @param consultaOperacionesViewList
     *            the consulta operaciones view list
     * @return the list
     */
    private List<ConsultaOperacionesView> obtenerRegistrosPorPagina(
            List<ConsultaOperacionesView> consultaOperacionesViewList) {
        List<ConsultaOperacionesView> retorno = new ArrayList<ConsultaOperacionesView>();
        if (consultaOperacionesViewList.size() < OPS_POR_PAGINA) {
            for (int i = 0; i < consultaOperacionesViewList.size(); i++) {
                retorno.add(consultaOperacionesViewList.get(i));
            }
        } else {
            for (int i = 0; i < OPS_POR_PAGINA; i++) {
                retorno.add(consultaOperacionesViewList.get(i));
            }
        }
        return retorno;
    }

    /**
     * Armar respuesta vacia consulta operaciones.
     *
     * @param responseBO
     *            the response BO
     * @param esBuscador
     *            the es buscador
     * @param monedasMap
     *            the monedas map
     * @return the respuesta
     */
    private Respuesta<ConsultaOperacionesOutView> armarRespuestaVaciaConsultaOperaciones(
            Respuesta<ConsultaOperacionesOutDTO> responseBO, Boolean esBuscador,
            Map<String, ConsultaMonedaOutDTO> monedasMap) {
        ConsultaOperacionesOutView consultaOperacionesOutView = new ConsultaOperacionesOutView();
        consultaOperacionesOutView
                .setCantidadTransferenciasGuardadas(responseBO.getRespuesta().getCantidadTransferenciasGuardadas());
        consultaOperacionesOutView
                .setCantidadTransferenciasPendientes(responseBO.getRespuesta().getCantidadTransferenciasPendientes());
        consultaOperacionesOutView
                .setCantidadTransferenciasRechazadas(responseBO.getRespuesta().getCantidadTransferenciasRechazadas());
        
		try {
			consultaOperacionesOutView.setAvisoLegal(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_AVISO_TRANSFERENCIAS_EXTERIOR));
			consultaOperacionesOutView.setAvisoLegalPopup(legalBO.obtenerLegal(CodigoMensajeConstantes.LEGAL_AVISO_TRANSFERENCIAS_EXTERIOR_POPUP));
		} catch (DAOException e) {
			consultaOperacionesOutView.setAvisoLegal(StringUtils.EMPTY);
			consultaOperacionesOutView.setAvisoLegalPopup(StringUtils.EMPTY);
		}
        
        if (esBuscador) {
            return respuestaFactory.crearRespuestaWarning(ConsultaOperacionesOutView.class, consultaOperacionesOutView,
                    TipoError.LISTAVACIA, CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_LISTA_VACIA_BUSCADOR, "");
        } else {
            consultaOperacionesOutView.setConsultaMonedasList(this.obtenerListaMonedas(monedasMap));
            return respuestaFactory.crearRespuestaWarning(ConsultaOperacionesOutView.class, consultaOperacionesOutView,
                    TipoError.LISTAVACIA, CodigoMensajeConstantes.CODIGO_COMEX_CONSULTA_LISTA_VACIA, "");
        }
    }

    /**
     * Creates the consulta operaciones in DTO.
     *
     * @param consultaOperacionesInView
     *            the consulta operaciones in view
     * @return the consulta operaciones in DTO
     */
    private ConsultaOperacionesInDTO createConsultaOperacionesInDTO(
            ConsultaOperacionesInView consultaOperacionesInView) {
        ConsultaOperacionesInDTO consultaOperacionesInDTO = new ConsultaOperacionesInDTO();
        consultaOperacionesInDTO.setEstado(consultaOperacionesInView.getEstado());
        consultaOperacionesInDTO.setFechaDesde(consultaOperacionesInView.getFechaDesde());
        consultaOperacionesInDTO.setFechaHasta(consultaOperacionesInView.getFechaHasta());
        consultaOperacionesInDTO.setMoneda(consultaOperacionesInView.getMoneda());
        consultaOperacionesInDTO.setMontoDesde(consultaOperacionesInView.getMontoDesde());
        consultaOperacionesInDTO.setMontoHasta(consultaOperacionesInView.getMontoHasta());
        consultaOperacionesInDTO.setEsBuscador(consultaOperacionesInView.getEsBuscador());
        return consultaOperacionesInDTO;
    }

    /**
     * Crear consulta operaciones out view.
     *
     * @param responseBO
     *            the response BO
     * @param monedasMap
     *            the monedas map
     * @return the consulta operaciones out view
     */
    private ConsultaOperacionesOutView crearConsultaOperacionesOutView(Respuesta<ConsultaOperacionesOutDTO> responseBO,
            Map<String, ConsultaMonedaOutDTO> monedasMap) {
        ConsultaOperacionesOutView consultaOperacionesOutView = new ConsultaOperacionesOutView();
        List<ConsultaOperacionesView> consultaOperacionesViewList = new ArrayList<ConsultaOperacionesView>();
        for (ConsultaOperacionesDTO c : responseBO.getRespuesta().getOperaciones()) {
            ConsultaOperacionesView consultaOperacionesView = new ConsultaOperacionesView();
            consultaOperacionesView.setCodEstado(c.getCodEstado());
            consultaOperacionesView.setDestinatario(c.getDestinatario());
            consultaOperacionesView.setEstadoDescripcion(c.getEstadoDescripcion());
            consultaOperacionesView.setFechaOperacion(c.getFechaOperacion());
            consultaOperacionesView.setMonto(c.getMonto());
            if (!StringUtils.isBlank(c.getCodMoneda()) && monedasMap.containsKey(c.getCodMoneda())) {
                consultaOperacionesView.setMonedaDescripcion(monedasMap.get(c.getCodMoneda()).getCodBCRAMoneda());
                // solo para accesibilidad por eso no se trata las mayusculas
                consultaOperacionesView.setNombreMoneda(monedasMap.get(c.getCodMoneda()).getDescripcionMoneda());
            }
            consultaOperacionesView.setNroForm(c.getNroForm());
            consultaOperacionesView.setCuentaCliente(c.getCuentaCliente());
            consultaOperacionesViewList.add(consultaOperacionesView);
        }
        consultaOperacionesOutView.setOperaciones(consultaOperacionesViewList);
        consultaOperacionesOutView
                .setCantidadTransferenciasGuardadas(responseBO.getRespuesta().getCantidadTransferenciasGuardadas());
        consultaOperacionesOutView
                .setCantidadTransferenciasPendientes(responseBO.getRespuesta().getCantidadTransferenciasPendientes());
        consultaOperacionesOutView
                .setCantidadTransferenciasRechazadas(responseBO.getRespuesta().getCantidadTransferenciasRechazadas());
        consultaOperacionesOutView.setEstados(obtenerEstadosTransferencia());
        return consultaOperacionesOutView;
    }

    /**
     * Obtener estados transferencia.
     *
     * @return the list
     */
    private List<EstadosTransferenciaView> obtenerEstadosTransferencia() {
        List<EstadosTransferenciaView> retorno = new ArrayList<EstadosTransferenciaView>();
        for (EstadoTransferenciaEnum value : EstadoTransferenciaEnum.values()) {
            if (EstadoTransferenciaEnum.PENDIENTE_PROCOTIZACION.getCodigo().equals(value.getCodigo())) {
                continue;
            }
            retorno.add(new EstadosTransferenciaView(value.getCodigo(), value.getDescripcion()));
        }
        return retorno;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #procesarTransferenciaGuardar(ar.com.santanderrio.obp.servicios.comex.
     * transfext.view.ProcesarTransferenciaComexView)
     */
    @Override
    public Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaGuardar(
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {

        ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO = null;

        if (procesarTransferenciaComexView.getNroTransferencia() != null
                && procesarTransferenciaComexView.getNroFormRel() == null) {
            ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
            ConsultaOperacionesView operacionAGuardar = obtenerOperacion(consultaOperacionesOutView.getOperaciones(),
                    procesarTransferenciaComexView.getNroTransferencia().longValue());
            if (operacionAGuardar == null) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
            }
            procesarTransferenciaComexInDTO = crearProcesarTransferenciaComexGuardarInDTO(
                    procesarTransferenciaComexView, TIPO_OPERACION_MODIFICAR);

        } else {
            procesarTransferenciaComexInDTO = crearProcesarTransferenciaComexGuardarInDTO(
                    procesarTransferenciaComexView, TIPO_OPERACION_ALTA);
        }

        Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = comexCanalesBO
                .procesarTransferenciaComex(procesarTransferenciaComexInDTO);
        if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
        	String tipoError = respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError();
        	if (TipoError.ERROR_TRANSFERENCIA_COMEX_LIMITE.getDescripcion().equals(tipoError)) {
	    		return Respuesta.copy(ProcesarTransferenciaComexView.class, respuestaBO);
	    	} else {
	    		return armarRespuestaProcesarTransferenciaAltaError(procesarTransferenciaComexView);	    		
	    	}
        }
        ProcesarTransferenciaComexView procesarTransferenciaComexOutView = createProcesarTransferenciaComexOutView(
                respuestaBO, procesarTransferenciaComexView);
        if (procesarTransferenciaComexView.getDatosTransferencia().getConceptoCodigo().equals(CONCEPTO_I07)
                || procesarTransferenciaComexView.getDatosTransferencia().getConceptoCodigo().equals(CONCEPTO_I08)) {
            estadisticaManager.add(EstadisticasConstants.PROCESAR_TRANSFERENCIA_GUARDAR_OK_COMEX_I08_I07,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        } else {
            estadisticaManager.add(EstadisticasConstants.PROCESAR_TRANSFERENCIA_GUARDAR_OK_COMEX,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        }

        return respuestaFactory.crearRespuestaOk(procesarTransferenciaComexOutView, "", "");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #procesarTransferenciaBaja(ar.com.santanderrio.obp.servicios.comex.transfext.
     * view.ProcesarTransferenciaComexView)
     */
    @Override
    public Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaBaja(
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
        ConsultaOperacionesView operacionABorrar = obtenerOperacion(consultaOperacionesOutView.getOperaciones(),
                procesarTransferenciaComexView.getNroTransferencia().longValue());
        if (operacionABorrar == null) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR);
        }
        ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO = crearProcesarTransferenciaBajaComexInDTO(
                procesarTransferenciaComexView);
        Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = comexCanalesBO
                .procesarTransferenciaComex(procesarTransferenciaComexInDTO);
        if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR);
        }
        estadisticaManager.add(EstadisticasConstants.BAJA_TRANSFERENCIA_BANCOS_COMEX,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        return respuestaFactory.crearRespuestaOk(
                new ProcesarTransferenciaComexView(OperacionesRSAEnum.TRANSFERENCIA_COMEX), "",
                CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #procesarTransferenciaAlta(ar.com.santanderrio.obp.servicios.comex.transfext.
     * view.ProcesarTransferenciaComexView)
     */
    @Override
    public Respuesta<ProcesarTransferenciaComexView> procesarTransferenciaAlta(
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {
    	//procesarTransferenciaComexView.setIgnorarRSA(true);
        LOGGER.info("Comex Canales Manager Procesar Transferencia Alta");
        // SE CARGA HASH DE VALIDACION PARA VALIDARLO EN LA PROXIMA LLAMADA
        if (procesarTransferenciaComexView.getDesafio() == null) {
            cargarHashValidacion(crearMapaDeLaVista(procesarTransferenciaComexView));
        }
        // RSA
        AutentificacionCodEstDTO autentificacionCodEstDTO = asignarEstadisticasDeAutenticacion();
        Respuesta<ProcesarTransferenciaComexView> respuestaRsa = desafioOperacionRSA.validarOperacionRSA(
                procesarTransferenciaComexView, valorDesafioTransferenciaComex, autentificacionCodEstDTO);
        if (respuestaRsa == null || !EstadoRespuesta.OK.equals(respuestaRsa.getEstadoRespuesta())) {
            return respuestaRsa;
        }

        ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO;
        if (procesarTransferenciaComexView.getNroTransferencia() != null
                && procesarTransferenciaComexView.getNroFormRel() == null) {
            ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
            ConsultaOperacionesView operacion = obtenerOperacion(consultaOperacionesOutView.getOperaciones(),
                    procesarTransferenciaComexView.getNroTransferencia().longValue());
            if (operacion == null) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_ERROR_GENERICO);
            }

            procesarTransferenciaComexInDTO = crearProcesarTransferenciaComexAltaInDTO(procesarTransferenciaComexView,
                    TIPO_OPERACION_MODIFICAR);
        } else {
            procesarTransferenciaComexInDTO = crearProcesarTransferenciaComexAltaInDTO(procesarTransferenciaComexView,
                    TIPO_OPERACION_ALTA);
        }

        estadisticaManager.add(EstadisticasConstants.PROCESAR_TRANSFERENCIA_CONFIRMAR_COMEX,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
        Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO = comexCanalesBO
                .procesarTransferenciaComex(procesarTransferenciaComexInDTO);
        if (respuestaBO.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
            estadisticaManager.add(EstadisticasConstants.PROCESAR_TRANSFERENCIA_CONFIRMAR_OK_COMEX,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
        	String tipoError = respuestaBO.getItemsMensajeRespuesta().get(0).getTipoError();
			if (TipoError.ERROR_SERVICIO.getDescripcion().equals(tipoError)) {
        		Respuesta<ProcesarTransferenciaComexView> respuestaView = armarRespuestaProcesarTransferenciaAltaError(procesarTransferenciaComexView);
        		respuestaView.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_SERVICIO.getDescripcion());
        		return respuestaView;
        	} else if (TipoError.ERROR_TRANSFERENCIA_COMEX_LIMITE.getDescripcion().equals(tipoError)) {
        		return Respuesta.copy(ProcesarTransferenciaComexView.class, respuestaBO);
        	}
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_CARGA_NUESTROS_SERVICIOS);
        }
        ProcesarTransferenciaComexView procesarTransferenciaComexOutView = createProcesarTransferenciaComexOutView(
                respuestaBO, procesarTransferenciaComexView);
        estadisticaManager.add(EstadisticasConstants.PROCESAR_TRANSFERENCIA_CONFIRMAR_OK_COMEX,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

        ComprobanteComexDTO comprobanteComexDTO = guardarComprobante(procesarTransferenciaComexView,
                procesarTransferenciaComexInDTO, procesarTransferenciaComexOutView);
        
        List<String> nombreArchivosAdjuntos = new ArrayList<String>();
        for (ReporteView archivo : procesarTransferenciaComexView.getDocumentacionAdjuntaView().getArchivos()) {
        	nombreArchivosAdjuntos.add(archivo.getNombre());
		}
        comprobanteComexDTO.setArchivos(nombreArchivosAdjuntos);
        this.sesionParametros.setComprobanteComex(comprobanteComexDTO);

        return respuestaFactory.crearRespuestaOk(procesarTransferenciaComexOutView, "", "");
    }

    /**
     * Guardar comprobante.
     *
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     * @param procesarTransferenciaComexInDTO
     *            the procesar transferencia comex in DTO
     * @param procesarTransferenciaComexOutView
     *            the procesar transferencia comex out view
     * @return the comprobante comex DTO
     */
    private ComprobanteComexDTO guardarComprobante(ProcesarTransferenciaComexView procesarTransferenciaComexView,
            ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO,
            ProcesarTransferenciaComexView procesarTransferenciaComexOutView) {
        Map<String, ConsultaMonedaOutDTO> monedasMap = sesionParametros.getMonedasComexMap();
        ConsultaMonedaOutDTO moneda = monedasMap.get(procesarTransferenciaComexView.getCodMoneda());
        String codBCRAMoneda = moneda.getCodBCRAMoneda();
        ResumenDetalleCuenta cuenta = obtenerCuentaSeleccionada(
                procesarTransferenciaComexView.getDatosTransferencia().getIdCuentaOrigen());
        ComprobanteComexDTO comprobante = new ComprobanteComexDTO();
        String domicilioCalleNumero = ISBANStringUtils
                .normalizarCaraceteres(procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioCalle())
                .concat(" ").concat(ISBANStringUtils.normalizarCaraceteres(
                        procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioNumero()));
        String domicilioLocalidadPais = ISBANStringUtils
                .normalizarCaraceteres(procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioLocalidad())
                .concat(",").concat(ISBANStringUtils.normalizarCaraceteres(
                        procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioPais()));
        comprobante.setMoneda(moneda.getDescripcionMoneda());
        comprobante.setNombre(procesarTransferenciaComexView.getDatosBeneficiario().getNombreBeneficiario());
        comprobante.setImporte(codBCRAMoneda.concat(" ").concat(ISBANStringUtils
                .formatearConComaYDosDecimales(String.valueOf(procesarTransferenciaComexView.getMonto()))));
        comprobante.setIdConcepto(procesarTransferenciaComexView.getDatosTransferencia().getConceptoID());

        // VINCULO_COMEX
        // comprobante.setVinculo(procesarTransferenciaComexView.getDatosBeneficiario().getVinculo());
        comprobante.setDomicilio(WordUtils.capitalizeFully(domicilioCalleNumero));
        comprobante.setPais(WordUtils.capitalizeFully(domicilioLocalidadPais, DELIMITADORES));
        comprobante.setNombreEmisor(WordUtils.capitalizeFully(procesarTransferenciaComexInDTO.getRazonSocial()));
        comprobante.setCuentaOrigen(CUENTA.concat(" ")
                .concat(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal())
                        .concat(ISBANStringUtils.GUION_STRING)
                        .concat(ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()))));
        comprobante.setDescripcionCuentaOrigen(procesarTransferenciaComexView.getDatosTransferencia().getDescripcionTipoCuenta());
        comprobante.setCuentaDestino(procesarTransferenciaComexView.getDatosTransferencia().getDestino());
        comprobante.setCodigoBancario(procesarTransferenciaComexView.getDatosTransferencia().getDescripcionBanco());
        comprobante.setIdGastosACargo(procesarTransferenciaComexView.getDatosTransferencia().getGastoACargo());
        comprobante.setBancoIntermediario(
                procesarTransferenciaComexView.getDatosTransferencia().getDestinoBancoIntermediario());
        comprobante.setCodigoIntermediario(
                procesarTransferenciaComexView.getDatosTransferencia().getDescripcionBancoIntermediario());
        comprobante.setNumeroComprobante(String.valueOf(procesarTransferenciaComexOutView.getNroTransferencia()));
        comprobante.setFechaHora(procesarTransferenciaComexOutView.getFecha());
        comprobante.setVinculante(procesarTransferenciaComexView.getVinculante());
        return comprobante;
    }

    /**
     * Obtener operacion.
     *
     * @param operaciones
     *            the operaciones
     * @param nroTransferenciaDetalle
     *            the nro transferencia detalle
     * @return the consulta operaciones view
     */
    private ConsultaOperacionesView obtenerOperacion(List<ConsultaOperacionesView> operaciones,
            Long nroTransferenciaDetalle) {
        ConsultaOperacionesView retorno = null;
        for (ConsultaOperacionesView index : operaciones) {
            long nroForm = index.getNroForm();
            long nroFormDetalle = nroTransferenciaDetalle;
            if (nroForm == nroFormDetalle) {
                retorno = index;
            }
        }
        return retorno;
    }

    /**
     * Crear procesar transferencia comex guardar in DTO.
     *
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     * @param tipoOperacion
     *            the tipo operacion
     * @return the procesar transferencia comex in DTO
     */
    private ProcesarTransferenciaComexInDTO crearProcesarTransferenciaComexGuardarInDTO(
            ProcesarTransferenciaComexView procesarTransferenciaComexView, String tipoOperacion) {
        ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO = new ProcesarTransferenciaComexInDTO();
        procesarTransferenciaComexInDTO.setNroTransferencia(procesarTransferenciaComexView.getNroTransferencia());
        procesarTransferenciaComexInDTO.setEstadoTransferencia(ESTADO_TRANSFERENCIA_A);
        procesarTransferenciaComexInDTO.setTipoOperacion(tipoOperacion);

        // Guardar similar
        if (procesarTransferenciaComexView.getNroFormRel() != null) {
            procesarTransferenciaComexInDTO.setNroTransferencia(null);
            procesarTransferenciaComexInDTO.setNroFormRel(procesarTransferenciaComexView.getNroFormRel());
        }
        setDatosTransferencia(procesarTransferenciaComexInDTO, procesarTransferenciaComexView);
        procesarTransferenciaComexInDTO.setVinculante(procesarTransferenciaComexView.getVinculante());
        return procesarTransferenciaComexInDTO;
    }

    /**
     * Crear procesar transferencia baja comex in DTO.
     *
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     * @return the procesar transferencia comex in DTO
     */
    private ProcesarTransferenciaComexInDTO crearProcesarTransferenciaBajaComexInDTO(
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO = new ProcesarTransferenciaComexInDTO();
        procesarTransferenciaComexInDTO.setEstadoTransferencia(ESTADO_TRANSFERENCIA_L);
        procesarTransferenciaComexInDTO.setTipoOperacion(TIPO_OPERACION_ALTA);
        procesarTransferenciaComexInDTO.setNroTransferencia(procesarTransferenciaComexView.getNroTransferencia());
        return procesarTransferenciaComexInDTO;
    }

    /**
     * Crear procesar transferencia comex alta in DTO.
     *
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     * @param tipoOperacion
     *            the tipo operacion
     * @return the procesar transferencia comex in DTO
     */
    private ProcesarTransferenciaComexInDTO crearProcesarTransferenciaComexAltaInDTO(
            ProcesarTransferenciaComexView procesarTransferenciaComexView, String tipoOperacion) {
    	
        ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO = new ProcesarTransferenciaComexInDTO();
        procesarTransferenciaComexInDTO.setNroTransferencia(procesarTransferenciaComexView.getNroTransferencia());
        procesarTransferenciaComexInDTO.setEstadoTransferencia(ESTADO_TRANSFERENCIA_EN_TRAMITE);
        procesarTransferenciaComexInDTO.setTipoOperacion(tipoOperacion);
        // ver si buscar en sesion
        // para crear similar se trabaja como un alta pero se setea nro de form rel
        if (procesarTransferenciaComexView.getNroFormRel() != null) {
            procesarTransferenciaComexInDTO.setNroFormRel(procesarTransferenciaComexView.getNroFormRel());
        }
        
        if(procesarTransferenciaComexView.getNroFormRel() != null &&
        		procesarTransferenciaComexView.getNroTransferencia() != null && 
        		procesarTransferenciaComexView.getNroFormRel().equals(Long.valueOf(procesarTransferenciaComexView.getNroTransferencia()))){
        	procesarTransferenciaComexInDTO.setNroTransferencia(null);
        } 
        
        setDatosTransferencia(procesarTransferenciaComexInDTO, procesarTransferenciaComexView);
        procesarTransferenciaComexInDTO.setVinculante(procesarTransferenciaComexView.getVinculante());
        
        return procesarTransferenciaComexInDTO;
    }

    /**
     * Sets the datos transferencia.
     *
     * @param procesarTransferenciaComexInDTO
     *            the procesar transferencia comex in DTO
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     */
    private void setDatosTransferencia(ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO,
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        String segundoApellido = "";
        if (sesionCliente.getCliente().getIsCuentaMigrada()) {
            if (sesionCliente.getCliente().getApellido2().length() > 17) {
                segundoApellido = sesionCliente.getCliente().getApellido2().substring(0, 17).trim();
            } else {
                segundoApellido = StringUtils.EMPTY;
            }
        } else {
            segundoApellido = sesionCliente.getCliente().getApellido2();
        }
        StringBuilder razonSocialSB = new StringBuilder(
                StringUtils.defaultIfEmpty(sesionCliente.getCliente().getNombre(), StringUtils.EMPTY));
        razonSocialSB.append(" ");
        razonSocialSB.append(StringUtils.defaultIfEmpty(sesionCliente.getCliente().getApellido1(), StringUtils.EMPTY));
        if (StringUtils.isNotBlank(segundoApellido)) {
            razonSocialSB.append(" ");
            razonSocialSB.append(segundoApellido);
        }
        procesarTransferenciaComexInDTO.setCodMoneda(procesarTransferenciaComexView.getCodMoneda());
        procesarTransferenciaComexInDTO.setImporteTransferencia(procesarTransferenciaComexView.getMonto());
        procesarTransferenciaComexInDTO.setRazonSocial(razonSocialSB.toString());
        procesarTransferenciaComexInDTO.setAceptaDDJJ(ACEPTA_DDJJ_NO);
        if (procesarTransferenciaComexView.getAceptaDDJJ()) {
            procesarTransferenciaComexInDTO.setAceptaDDJJ(ACEPTA_DDJJ_SI);
        }

        if (procesarTransferenciaComexView.getDatosBeneficiario() != null) {
            StringBuilder domicilioBeneficiario = new StringBuilder();
            domicilioBeneficiario.append(StringUtils.defaultIfEmpty(
                    procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioCalle(), StringUtils.EMPTY));
            domicilioBeneficiario.append(" ");
            domicilioBeneficiario.append(StringUtils.defaultIfEmpty(
                    procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioNumero(), StringUtils.EMPTY));
            domicilioBeneficiario.append(", ");
            domicilioBeneficiario.append(StringUtils.defaultIfEmpty(
                    procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioLocalidad(), StringUtils.EMPTY));
            domicilioBeneficiario.append(", ");
            domicilioBeneficiario.append(StringUtils.defaultIfEmpty(
                    procesarTransferenciaComexView.getDatosBeneficiario().getDomicilioPais(), StringUtils.EMPTY));

            procesarTransferenciaComexInDTO.setNombreBeneficiario(
                    procesarTransferenciaComexView.getDatosBeneficiario().getNombreBeneficiario());
            // VINCULO_COMEX
            // procesarTransferenciaComexInDTO.setVinculo(procesarTransferenciaComexView.getDatosBeneficiario().getVinculo());
            procesarTransferenciaComexInDTO
                    .setCodPais(procesarTransferenciaComexView.getDatosBeneficiario().getCodPais());
            procesarTransferenciaComexInDTO.setDomicilio(domicilioBeneficiario.toString());
        }

        if (procesarTransferenciaComexView.getDatosTransferencia() != null) {
            procesarTransferenciaComexInDTO
                    .setIdConcepto(procesarTransferenciaComexView.getDatosTransferencia().getConceptoID());
            ResumenDetalleCuenta cuenta = null;
            if (procesarTransferenciaComexView.getDatosTransferencia().getIdCuentaOrigen() != null) {
                cuenta = obtenerCuentaSeleccionada(
                        procesarTransferenciaComexView.getDatosTransferencia().getIdCuentaOrigen());
            }

            if (cuenta != null) {
                StringBuilder cuentaDebito = new StringBuilder();
                cuentaDebito.append(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()));
                cuentaDebito.append(ISBANStringUtils.GUION_STRING);
                if (TipoCuenta.CUENTA_UNICA.getCodigo() == Integer.valueOf(cuenta.getTipoCuenta())) {
                    if (TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda().equals(procesarTransferenciaComexView.getDatosTransferencia().getDescripcionTipoCuenta())) {
                        cuentaDebito.append(CU_PESOS);
                    } else if (TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcionConMoneda().equals(procesarTransferenciaComexView.getDatosTransferencia().getDescripcionTipoCuenta())) {
                        cuentaDebito.append(CU_DOLARES);
                    }
                } else {
                    cuentaDebito.append(cuenta.getTipoCuenta());
                }
                cuentaDebito.append(ISBANStringUtils.GUION_STRING);
                cuentaDebito.append(ISBANStringUtils.eliminarCeros(cuenta.getNroCuentaProducto()));
                String cuentaAltair = cuentaDebito.toString();
                procesarTransferenciaComexInDTO.setCuentaAltair(cuentaAltair);
                procesarTransferenciaComexInDTO.setCuentaDebito(cuentaDebito.toString());
            }
            procesarTransferenciaComexInDTO
                    .setGastoACargo(procesarTransferenciaComexView.getDatosTransferencia().getGastoACargo());
            procesarTransferenciaComexInDTO
                    .setBancoBeneficiario(procesarTransferenciaComexView.getDatosTransferencia().getDescripcionBanco());
            procesarTransferenciaComexInDTO.setBancoIntermediario(
                    procesarTransferenciaComexView.getDatosTransferencia().getDescripcionBancoIntermediario());
            procesarTransferenciaComexInDTO.setCuentaBcoIntermediario(
                    procesarTransferenciaComexView.getDatosTransferencia().getDestinoBancoIntermediario());
            procesarTransferenciaComexInDTO
                    .setCuentaBeneficiario(procesarTransferenciaComexView.getDatosTransferencia().getDestino());
        }

    }

    /**
     * Obtener cuenta seleccionada.
     *
     * @param idCuentaOrigen
     *            the id cuenta origen
     * @return the resumen detalle cuenta
     */
    private ResumenDetalleCuenta obtenerCuentaSeleccionada(Integer idCuentaOrigen) {
        List<ResumenDetalleCuenta> cuentasComexPesos = sesionParametros.getCuentasComex();
        ResumenDetalleCuenta cuenta = cuentasComexPesos.get(idCuentaOrigen);
        return cuenta;
    }

    /**
     * Armar respuesta procesar transferencia alta error.
     *
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     * @return the respuesta
     */
    private Respuesta<ProcesarTransferenciaComexView> armarRespuestaProcesarTransferenciaAltaError(
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        Map<String, ConsultaMonedaOutDTO> monedasMap = sesionParametros.getMonedasComexMap();
        ConsultaMonedaOutDTO moneda = monedasMap.get(procesarTransferenciaComexView.getCodMoneda());
        String monedaCodigo = StringUtils.EMPTY;
        if (moneda != null) {
            monedaCodigo = moneda.getCodBCRAMoneda();
        }
        String nombreBeneficiario = StringUtils.EMPTY;
        if (procesarTransferenciaComexView.getDatosBeneficiario() != null) {
            nombreBeneficiario = procesarTransferenciaComexView.getDatosBeneficiario().getNombreBeneficiario();
        }
        String monto = StringUtils.EMPTY;
        if (procesarTransferenciaComexView.getMonto() != null) {
            monto = ISBANStringUtils.formatearSaldo(procesarTransferenciaComexView.getMonto());
        }
        String mensajeFormateado = MessageFormat
                .format(mensajeBO
                        .obtenerMensajePorCodigo(
                                CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_ERROR)
                        .getMensaje(), nombreBeneficiario, monedaCodigo, monto);
        return respuestaFactory.crearRespuestaErrorPersonalizadoSinClase(mensajeFormateado,
                TipoError.ERROR_GENERICO.toString());
    }

    /**
     * Creates the procesar transferencia comex out view.
     *
     * @param respuestaBO
     *            the respuesta BO
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     * @return the procesar transferencia comex view
     */
    private ProcesarTransferenciaComexView createProcesarTransferenciaComexOutView(
            Respuesta<ProcesarTransferenciaComexOutDTO> respuestaBO,
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        ProcesarTransferenciaComexView procesarTransferenciaComexOutView = new ProcesarTransferenciaComexView(
                OperacionesRSAEnum.TRANSFERENCIA_COMEX);
        Map<String, ConsultaMonedaOutDTO> monedasMap = sesionParametros.getMonedasComexMap();
        ConsultaMonedaOutDTO moneda = monedasMap.get(procesarTransferenciaComexView.getCodMoneda());
        String monedaCodigo = StringUtils.EMPTY;
        if (moneda != null) {
            monedaCodigo = moneda.getCodBCRAMoneda();
        }
        String mensajeInfo = mensajeBO
                .obtenerMensajePorCodigo(
                        CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_MSG_INFO_OK)
                .getMensaje();
        String monto = ISBANStringUtils.formatearSaldo(procesarTransferenciaComexView.getMonto());
        String mensajeFeedback = MessageFormat.format(
                mensajeBO
                        .obtenerMensajePorCodigo(
                                CodigoMensajeConstantes.CODIGO_COMEX_PROCESAR_TRANSFERENCIA_ALTA_FEEDBACK_OK)
                        .getMensaje(),
                procesarTransferenciaComexView.getDatosBeneficiario().getNombreBeneficiario(), monedaCodigo, monto);
        procesarTransferenciaComexOutView.setNroTransferencia(respuestaBO.getRespuesta().getNroTransferencia());
        procesarTransferenciaComexOutView.setFecha(ISBANStringUtils.formatearFechaConHoraParaComprobante(new Date()));
        procesarTransferenciaComexOutView.setMensajeFeedback(mensajeFeedback);
        procesarTransferenciaComexOutView.setMensajeInfo(mensajeInfo);
        return procesarTransferenciaComexOutView;
    }

    /**
     * Crear mapa de la vista.
     *
     * @param procesarTransferenciaComexView
     *            the procesar transferencia comex view
     * @return the map
     */
    public Map<String, Object> crearMapaDeLaVista(ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        LOGGER.info("Creando hash de los atributos...");
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        ResumenDetalleCuenta cuenta = null;
        if (procesarTransferenciaComexView.getDatosTransferencia() != null) {
            cuenta = obtenerCuentaSeleccionada(
                    procesarTransferenciaComexView.getDatosTransferencia().getIdCuentaOrigen());
        }
        mapaAtributos.put("monto",
                procesarTransferenciaComexView.getMonto() != null ? procesarTransferenciaComexView.getMonto() : "");
        mapaAtributos.put("nroCuentaProducto", cuenta != null ? cuenta.getNroCuentaProducto() : StringUtils.EMPTY);
        mapaAtributos.put("sucursal", cuenta != null ? cuenta.getNroCuentaProducto() : StringUtils.EMPTY);
        mapaAtributos.put("tipo", cuenta != null ? cuenta.getNroCuentaProducto() : StringUtils.EMPTY);
        return mapaAtributos;
    }

    /**
     * Cargar hash.
     *
     * @param viewMap
     *            the view map
     */
    public void cargarHashValidacion(Map<String, Object> viewMap) {
        String hashView = HashUtils.obtenerHash(viewMap);
        LOGGER.info("Se guarda el hash en sesion.");
        sesionParametros.setValidacionHash(hashView);
    }

    /**
     * Crear adjuntar archivos in dto.
     * 
     * @param nroTransferencia
     *            the nro transferencia
     * @param archivo
     *            the archivo
     * @return the adjuntar archivos DTO
     */
    private AdjuntarArchivosDTO crearAdjuntarArchivosInDto(Integer nroTransferencia, ReporteView archivo) {
        AdjuntarArchivosDTO adjuntarArchivosInDto = new AdjuntarArchivosDTO();
        adjuntarArchivosInDto.setNroTransferencia(nroTransferencia);
        adjuntarArchivosInDto.setArchivo(archivo);
        return adjuntarArchivosInDto;
    }

    /**
     * Asignar estadisticas de autenticacion.
     */
    private AutentificacionCodEstDTO asignarEstadisticasDeAutenticacion() {
        AutentificacionCodEstDTO autentificacionCodEstDTO = new AutentificacionCodEstDTO();
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudToken(EstadisticasConstants.COMEX_SOLICITUD_TOKEN);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionToken(EstadisticasConstants.COMEX_VALIDACION_TOKEN);
        autentificacionCodEstDTO
                .setCodigoEstadisticaSolicitudCoordenadas(EstadisticasConstants.COMEX_SOLICITUD_COORDENADAS);
        autentificacionCodEstDTO
                .setCodigoEstadisticaValidacionCoordenadas(EstadisticasConstants.COMEX_VALIDACION_COORDENADAS);
        autentificacionCodEstDTO.setCodigoEstadisticaSolicitudBanelco(EstadisticasConstants.COMEX_SOLICITUD_BANELCO);
        autentificacionCodEstDTO.setCodigoEstadisticaValidacionBanelco(EstadisticasConstants.COMEX_VALIDACION_BANELCO);
        return autentificacionCodEstDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #adjuntarArchivo(ar.com.santanderrio.obp.servicios.comex.transfext.view.
     * ProcesarTransferenciaComexView)
     */
    @Override
    public Respuesta<ArchivoTransferenciaView> adjuntarArchivo(
            ProcesarTransferenciaComexView procesarTransferenciaComexView) {
        // valido el tamanio del archivo y el tipo
        if (!this.validarMaxFileSizeAllowed(
                procesarTransferenciaComexView.getDocumentacionAdjuntaView().getArchivos().get(0).getByteArray())) {
        	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_SIZE_EXCEDIDO, CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_TAMAÑO);
        }
        if (!this.validarTipoDeArchivoValido(procesarTransferenciaComexView.getDocumentacionAdjuntaView()
                        .getArchivos().get(0).getTipoArchivo())) {
        	return respuestaFactory.crearRespuestaError("", TipoError.ERROR_EXTENSION_INVALIDA, CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_EXTENSION);
        }

        AdjuntarArchivosDTO adjuntarArchivosInDto = crearAdjuntarArchivosInDto(
                procesarTransferenciaComexView.getNroTransferencia(),
                procesarTransferenciaComexView.getDocumentacionAdjuntaView().getArchivos().get(0));
        // verifico si el archivo tiene virus
        Respuesta<Boolean> respuesta = this.comexConsultasBO.verificarArchivo(adjuntarArchivosInDto);
        // si tiene virus se devuelve error
        if (!respuesta.getRespuesta()) {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ADJUNTAR_ARCHIVO, CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_CARGA);

        }
        if (procesarTransferenciaComexView.getNroFormRel() != null
                || procesarTransferenciaComexView.getNroTransferencia() == null) {

            ProcesarTransferenciaComexInDTO procesarTransferenciaComexInDTO = null;
            procesarTransferenciaComexInDTO = crearProcesarTransferenciaComexGuardarInDTO(
                    procesarTransferenciaComexView, TIPO_OPERACION_ALTA);

            Respuesta<ProcesarTransferenciaComexOutDTO> responseProcesarTransferenciaGuardar = this.comexCanalesBO
                    .procesarTransferenciaComex(procesarTransferenciaComexInDTO);

            if (responseProcesarTransferenciaGuardar.getEstadoRespuesta().equals(EstadoRespuesta.ERROR)) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ADJUNTAR_ARCHIVO, CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_CARGA);

            } else {
                // si el servicio de guardar responde bien , lo agrego a la lista de operaciones
                // de sesion
                ConsultaOperacionesView consultaOperacionesOutView = new ConsultaOperacionesView();
                consultaOperacionesOutView.setNroForm(
                        responseProcesarTransferenciaGuardar.getRespuesta().getNroTransferencia().longValue());
                if (sesionParametros.getConsultaOperacionesOutView() == null || sesionParametros.getConsultaOperacionesOutView().getOperaciones() == null) {
                	ConsultaOperacionesOutView consultaOpView = new ConsultaOperacionesOutView();
                	consultaOpView.setOperaciones(new ArrayList<ConsultaOperacionesView>());
                	sesionParametros.setConsultaOperacionesOutView(consultaOpView);
                }
                sesionParametros.getConsultaOperacionesOutView().getOperaciones().add(consultaOperacionesOutView);

            }

            adjuntarArchivosInDto
                    .setNroTransferencia(responseProcesarTransferenciaGuardar.getRespuesta().getNroTransferencia());
            Respuesta<Integer> respuestaCargarDoc = this.comexCanalesBO.adjuntarArchivos(adjuntarArchivosInDto);
            if (respuestaCargarDoc.getRespuesta() == null) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ADJUNTAR_ARCHIVO, CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_CARGA);
            } else {
                ArchivoTransferenciaView archivoTransferenciaView = new ArchivoTransferenciaView();
                archivoTransferenciaView
                        .setNroTransferencia(responseProcesarTransferenciaGuardar.getRespuesta().getNroTransferencia());
                archivoTransferenciaView
                        .setArchivo(procesarTransferenciaComexView.getDocumentacionAdjuntaView().getArchivos().get(0));
                archivoTransferenciaView.getArchivo().setId(Integer.toString(respuestaCargarDoc.getRespuesta()));
                return respuestaFactory.crearRespuestaOk(archivoTransferenciaView, "", "");
            }
        } else {
            ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
            ConsultaOperacionesView operacionAverificar = obtenerOperacion(consultaOperacionesOutView.getOperaciones(),
                    procesarTransferenciaComexView.getNroTransferencia().longValue());

            if (operacionAverificar == null) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR);
            }
            Respuesta<Integer> respuestaCargarDoc = this.comexCanalesBO.adjuntarArchivos(adjuntarArchivosInDto);
            if (respuestaCargarDoc.getRespuesta() == null) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ADJUNTAR_ARCHIVO, CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_CARGA);
            } else {
                ArchivoTransferenciaView archivoTransferenciaView = new ArchivoTransferenciaView();
                archivoTransferenciaView.setNroTransferencia(procesarTransferenciaComexView.getNroTransferencia());
                archivoTransferenciaView
                        .setArchivo(procesarTransferenciaComexView.getDocumentacionAdjuntaView().getArchivos().get(0));
                archivoTransferenciaView.getArchivo().setId(Integer.toString(respuestaCargarDoc.getRespuesta()));
                return respuestaFactory.crearRespuestaOk(archivoTransferenciaView, "", "");
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #borrarArchivo(ar.com.santanderrio.obp.servicios.comex.transfext.view.
     * ArchivoTransferenciaView)
     */
    @Override
    public Respuesta<Void> borrarArchivo(ArchivoTransferenciaView archivoTransferenciaView) {
        // tiene q tener nro de transferencia
        AdjuntarArchivosDTO adjuntarArchivosInDto = null;
        if (archivoTransferenciaView.getNroTransferencia() != null
                && archivoTransferenciaView.getArchivo().getId() != null) {

            ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
            ConsultaOperacionesView operacionAverificar = obtenerOperacion(consultaOperacionesOutView.getOperaciones(),
                    archivoTransferenciaView.getNroTransferencia().longValue());
            if (operacionAverificar == null) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR);
            }

            adjuntarArchivosInDto = crearAdjuntarArchivosInDto(archivoTransferenciaView.getNroTransferencia(),
                    archivoTransferenciaView.getArchivo());
            Respuesta<Boolean> responseBorrarDoc = this.comexCanalesBO.borrarDoc(adjuntarArchivosInDto);
            if (responseBorrarDoc.getRespuesta()) {
                return this.respuestaFactory.crearRespuestaOk(null, "", "");
            } else {
    			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ELIMINAR_ARCHIVO_ADJUNTO,
    					CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_ELIMINAR);
            }
        } else {
			return respuestaFactory.crearRespuestaError("", TipoError.ERROR_ELIMINAR_ARCHIVO_ADJUNTO,
					CodigoMensajeConstantes.TRANSFEXT_ADJUNTAR_ERROR_ELIMINAR);
        }
    }

    /**
     * Validar max file size allowed.
     * 
     * @param archivo
     *            the archivo
     * @return true, if successful
     */
    private boolean validarMaxFileSizeAllowed(byte[] archivo) {
        if (archivo != null) {
            try {
                Integer tamanioMaximoArchivo = Integer.parseInt(this.maxFileSize);
                Integer tamanioArchivoEnKb = (int) Math.ceil((double) archivo.length / 1024);
                if (tamanioArchivoEnKb <= tamanioMaximoArchivo) {
                    return true;
                } else {
                    LOGGER.error(
                            "Error el archivo a adjuntar supera el tamaño maximo permitido de: " + tamanioArchivoEnKb);

                }
            } catch (NumberFormatException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return false;
    }

    /**
     * Validar tipo de archivo valido.
     * 
     * @param tipoArchivo
     *            the tipo archivo
     * @return true, if successful
     */
    private boolean validarTipoDeArchivoValido(String tipoArchivo) {
        String archivosPermitidos[] = this.tiposDeArchivosPermitidos.split(",");
        for (int i = 0; i < archivosPermitidos.length; i++) {
            if (archivosPermitidos[i].equals(tipoArchivo)) {
                return true;
            }
        }
        LOGGER.error("Error el archivo a adjuntar no posee un tipo permitido");

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexCanalesManager
     * #obtenerArchivoWsComex(ar.com.santanderrio.obp.servicios.comex.transfext.view
     * .ArchivoTransferenciaView)
     */
    @Override
    public Respuesta<AdjuntarArchivosDTO> obtenerArchivoWsComex(ArchivoTransferenciaView archivoTransferenciaView) {
        if (archivoTransferenciaView.getNroTransferencia() != null) {
            ConsultaOperacionesOutView consultaOperacionesOutView = sesionParametros.getConsultaOperacionesOutView();
            ConsultaOperacionesView operacionAverificar = obtenerOperacion(consultaOperacionesOutView.getOperaciones(),
                    archivoTransferenciaView.getNroTransferencia().longValue());
            if (operacionAverificar == null) {
                return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                        CodigoMensajeConstantes.CODIGO_COMEX_BAJA_TRANSFERENCIA_ERROR);
            }
            AdjuntarArchivosDTO adjuntarArchivosInDto = crearAdjuntarArchivosInDto(
                    archivoTransferenciaView.getNroTransferencia(), archivoTransferenciaView.getArchivo());
            return this.comexCanalesBO.consultaImagenTrf(adjuntarArchivosInDto);
        } else {
            return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO, "");

        }

    }

    /**
     * Obtener banco.
     *
     * @param consultaBancosInDTO
     *            the consulta bancos in DTO
     * @return the banco out view
     */
    private BancoOutView obtenerBanco(ConsultaBancosInDTO consultaBancosInDTO) {
        BancoOutView banco = null;
        if (StringUtils.isNotBlank(consultaBancosInDTO.getCodigoBancario())) {
            Respuesta<List<ConsultaBancosOutDTO>> respuestaBancosBO = comexConsultasBO
                    .consultaBancos(consultaBancosInDTO);
            if (!respuestaBancosBO.getEstadoRespuesta().equals(EstadoRespuesta.OK)
                    || respuestaBancosBO.getRespuesta().isEmpty()) {
                return banco;
            }
            banco = createBancoOutView(respuestaBancosBO.getRespuesta().get(0));
        }
        return banco;
    }

    /**
     * Crea la BancoOutView.
     *
     * @param bancoDTO
     *            the banco DTO
     * @return Banco
     */
    private BancoOutView createBancoOutView(ConsultaBancosOutDTO bancoDTO) {
        BancoOutView bancoView = new BancoOutView();
        if (StringUtils.isNotBlank(bancoDTO.getSwiftBanco())) {
            bancoView.setCodigo(bancoDTO.getSwiftBanco());
        } else if (StringUtils.isNotBlank(bancoDTO.getAbaBanco())) {
            bancoView.setCodigo(bancoDTO.getAbaBanco().replace(BARRA, StringUtils.EMPTY));
        }
        bancoView.setNombre(WordUtils.capitalizeFully(bancoDTO.getNombreBanco(), DELIMITADORES));
        bancoView.setPais(WordUtils.capitalizeFully(bancoDTO.getPaisBanco(), DELIMITADORES));
        bancoView.setLocalidad(WordUtils.capitalizeFully(bancoDTO.getLocalidadBanco(), DELIMITADORES));
        return bancoView;
    }
}
