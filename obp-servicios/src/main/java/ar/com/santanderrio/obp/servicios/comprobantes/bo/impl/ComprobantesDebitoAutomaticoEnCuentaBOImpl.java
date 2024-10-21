/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comprobantes.bo.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.ComprobantesDebitoAutomaticoEnCuentaBO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaAdhesionDebitoAutomaticoCuentaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaDebitoDirectoDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresaAdheridaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.CabeceraComprobantesEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.HistorialComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoDetalleComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.DatoItemMensaje;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * ComprobantesDebitoAutomaticoEnCuentaBOImpl.
 */
@Component
public class ComprobantesDebitoAutomaticoEnCuentaBOImpl implements ComprobantesDebitoAutomaticoEnCuentaBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ComprobantesDebitoAutomaticoEnCuentaBOImpl.class);

    /** The respuestaFactory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The consultaAdhesionDebitoAutomaticoCuentaDAO. */
    @Autowired
    private ConsultaAdhesionDebitoAutomaticoCuentaDAO consultaAdhesionDebitoAutomaticoCuentaDAO;

    /** The consultaDebitoDirectoDAO. */
    @Autowired
    private ConsultaDebitoDirectoDAO consultaDebitoDirectoDAO;

    /** The medioPagoBO. */
    @Autowired
    private MediosPagoBO medioPagoBO;

    /** The diasFechaDesde. */
    @Value("${COMPROBANTES.FECHA.ANTERIOR}")
    private String diasFechaDesde;

    /** The paginado desktop. */
    @Value("${PDMotivoRechazo.R10}")
    private String faltaFondos;

    /** The paginado mobile. */
    @Value("${PDMotivoRechazo.R14}")
    private String idErronea;

    /** The Constant formatoFecha. */
    private static final String FORMATO_FECHA = "yyyyMMdd";

    /** The Constant menos. */
    private static final String MENOS = "-";

    /** The Constant obteniendoComprobantes. */
    private static final String OBTENIENDO_COMPROBANTES = "Obteniendo comprobantes de tipo debito automatico en cuenta";

    /** The Constant espacio. */
    private static final String ESPACIO = " ";

    /** The Constant errorServicioAdhesionDebitoAutomatico. */
    private static final String ERROR_SERVICIO_DEBITOAUTOMATICO = "Error en servicio de consulta adhesion debito automatico";

    /** The Constant errorServicioConsultaDebito. */
    private static final String ERROR_SERVICIO_CONSULTA_DEBITO = "Error en servicio consulta debito directo: ";

    /** The Constant error. */
    private static final String ERROR = "Error: ";

    /** The Constant cero. */
    private static final String CERO = "0";

    /** The Constant uno. */
    private static final String UNO = "1";

    /** The Constant cuatro. */
    private static final String CUATRO = "4";

    /** The Constant codigoEstadoR00. */
    private static final String CODIGO_ESTADO_R00 = "R00";

    /** The Constant ceroCero. */
    private static final String DOBLE_CERO = "00";

    /** The Constant ceroUno. */
    private static final String CEROUNO = "01";

    /** The Constant ceroDos. */
    private static final String CERODOS = "02";

    /** The Constant ceroNueve. */
    private static final String CERONUEVE = "09";

    /** The Constant pesos. */
    private static final String PESOS = "$";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
     * ComprobantesDebitoAutomaticoEnCuentaBO#
     * obtenerComprobantesDebitoAutomaticoEnCuentaAsync(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    @Async
    public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesDebitoAutomaticoEnCuentaAsync(Cliente cliente) {
        return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesDebitoAutomaticoEnCuenta(cliente));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
     * ComprobantesDebitoAutomaticoEnCuentaBO#
     * obtenerComprobantesDebitoAutomaticoEnCuentaAsync(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    @Async
    public Future<Respuesta<ComprobantesDTO>> obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(Cliente cliente,
            TransaccionDTO dto) {
        return new AsyncResult<Respuesta<ComprobantesDTO>>(obtenerComprobantesDebitoAutomaticoEnCuenta(cliente, dto));
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.comprobantes.bo.
     * ComprobantesDebitoAutomaticoEnCuentaBO#
     * obtenerComprobantesDebitoAutomaticoEnCuenta(ar.com.santanderrio.obp.
     * servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<ComprobantesDTO> obtenerComprobantesDebitoAutomaticoEnCuenta(Cliente cliente) {
        return obtenerComprobantesDebitoAutomaticoEnCuenta(cliente, new TransaccionDTO());
    }

    /**
     * Obtener comprobantes debito automatico en cuenta.
     *
     * @param cliente
     *            the cliente
     * @param dto
     *            the dto
     * @return the respuesta
     */
    private Respuesta<ComprobantesDTO> obtenerComprobantesDebitoAutomaticoEnCuenta(Cliente cliente,
            TransaccionDTO dto) {
        LOGGER.info(OBTENIENDO_COMPROBANTES);
        EmpresasAdheridasDebitoAutoOutEntity outEntityEmpresasAdheridas = new EmpresasAdheridasDebitoAutoOutEntity();
        try {
            outEntityEmpresasAdheridas = obtenerEmpresasAdheridas(cliente);
        } catch (DAOException e) {
            LOGGER.error(ERROR_SERVICIO_DEBITOAUTOMATICO, e);
            return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
        }
        if (!StringUtils.containsOnly(outEntityEmpresasAdheridas.getCodigoRetornoExtendido(), CERO)) {
            return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
        }
        HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>> comprobantesOutEntity = consultarDebitosAutomaticosEmpresas(
                outEntityEmpresasAdheridas, cliente, dto);
        List<ComprobantesDTO> listaComprobantesDTO = new ArrayList<ComprobantesDTO>();
        Integer erroresServicio = procesarRespuestas(outEntityEmpresasAdheridas, listaComprobantesDTO,
                comprobantesOutEntity);
        return crearRespuesta(listaComprobantesDTO, erroresServicio, outEntityEmpresasAdheridas);
    }

    /**
     * Obtener empresas adheridas.
     *
     * @param cliente
     *            the cliente
     * @return the empresas adheridas debito auto out entity
     * @throws DAOException
     *             the DAO exception
     */
    private EmpresasAdheridasDebitoAutoOutEntity obtenerEmpresasAdheridas(Cliente cliente) throws DAOException {
        EmpresasAdheridasDebitoAutoInEntity inEntity = new EmpresasAdheridasDebitoAutoInEntity();
        inEntity.setCliente(cliente);
        inEntity.setNombreApellido(obtenerNombreApellido(cliente));
        return consultaAdhesionDebitoAutomaticoCuentaDAO.consultar(inEntity);
    }

    /**
     * Obtener nombre apellido.
     *
     * @param cliente
     *            the cliente
     * @return the string
     */
    private String obtenerNombreApellido(Cliente cliente) {
        StringBuilder sb = new StringBuilder();
        sb.append(cliente.getApellido1().trim());
        sb.append(ESPACIO);
        sb.append(cliente.getApellido2().trim());
        sb.append(ESPACIO);
        sb.append(cliente.getNombre().trim());
        return ISBANStringUtils.normalizarCampoAlfanumericoIatx(sb.toString(), 30);
    }

    /**
     * Obtener in entity comprobante debito automatico.
     *
     * @param empresa
     *            the empresa
     * @param cliente
     *            the cliente
     * @param dto
     *            the dto
     * @return the comprobante debito automatico in entity
     */
    private ComprobanteDebitoAutomaticoInEntity obtenerInEntityComprobanteDebitoAutomatico(
            EmpresaAdheridaEntity empresa, Cliente cliente, TransaccionDTO dto) {
        ComprobanteDebitoAutomaticoInEntity inEntity = new ComprobanteDebitoAutomaticoInEntity();
        setearFechas(inEntity, dto);
        inEntity.setCliente(cliente);
        inEntity.setEmpresa(empresa);
        inEntity.setFechaContinuacion("");
        inEntity.setIDDebitoDDICont("");
        return inEntity;
    }

    /**
     * Setear fechas.
     *
     * @param inEntity
     *            the in entity
     * @param dto
     *            the dto
     */
    private void setearFechas(ComprobanteDebitoAutomaticoInEntity inEntity, TransaccionDTO dto) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO_FECHA);
        if (dto == null) {
            Date date = new Date();
            inEntity.setFechaHasta(sdf.format(date));
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, Integer.parseInt(MENOS + diasFechaDesde));
            date = cal.getTime();
            inEntity.setFechaDesde(sdf.format(date));
        } else {
            inEntity.setFechaDesde(sdf.format(dto.getFechaDesde()));
            inEntity.setFechaHasta(sdf.format(dto.getFechaHasta()));
        }
    }

    /**
     * Consultar debitos automaticos empresas.
     *
     * @param outEntityEmpresasAdheridas
     *            the out entity empresas adheridas
     * @param cliente
     *            the cliente
     * @param dto
     *            the dto
     * @return the hash map
     */
    private HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>> consultarDebitosAutomaticosEmpresas(
            EmpresasAdheridasDebitoAutoOutEntity outEntityEmpresasAdheridas, Cliente cliente, TransaccionDTO dto) {
        HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>> comprobantes = new HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>>();
        Integer llamados = 0;
        for (EmpresaAdheridaEntity empresa : outEntityEmpresasAdheridas.getEmpresas()) {
            ComprobanteDebitoAutomaticoInEntity inEntity = obtenerInEntityComprobanteDebitoAutomatico(empresa, cliente,
                    dto);
            comprobantes.put(empresa, consultaDebitoDirectoDAO.consultarAsync(inEntity));
            llamados = paginarLlamados(llamados);
        }
        sleepWhileExecute(comprobantes);
        return comprobantes;
    }

    private Integer paginarLlamados(Integer llamados) {
    	llamados++;
		if(llamados >= 30){
			try {
				LOGGER.info("Limpiando hilos de debitos en cuenta");
                Thread.sleep(200);
                llamados = 0;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error(ERROR + e);
            }			
		}	
		return llamados;
	}

	/**
     * Sleep while execute.
     *
     * @param comprobantes
     *            the comprobantes
     */
    private void sleepWhileExecute(
            HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>> comprobantes) {
        while (!terminaronLosHilos(comprobantes)) {
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error(ERROR + e);
            }
        }
    }

    /**
     * Terminaron los hilos.
     *
     * @param comprobantes
     *            the comprobantes
     * @return true, if successful
     */
    private boolean terminaronLosHilos(
            HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>> comprobantes) {
        boolean termino = true;
        for (Future<ComprobanteDebitoAutomaticoOutEntity> response : comprobantes.values()) {
            if (!response.isDone()) {
                termino = false;
            }
        }
        return termino;
    }

    /**
     * Procesar respuestas.
     *
     * @param outEntityEmpresasAdheridas
     *            the out entity empresas adheridas
     * @param comprobantesDTO
     *            the comprobantes DTO
     * @param comprobantesOutEntity
     *            the comprobantes out entity
     * @return the integer
     */
    private Integer procesarRespuestas(EmpresasAdheridasDebitoAutoOutEntity outEntityEmpresasAdheridas,
            List<ComprobantesDTO> comprobantesDTO,
            HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>> comprobantesOutEntity) {
        Integer erroresServicio = 0;
        for (EmpresaAdheridaEntity empresa : outEntityEmpresasAdheridas.getEmpresas()) {
            ComprobanteDebitoAutomaticoOutEntity outEntity = obtenerOutEntity(comprobantesOutEntity.get(empresa));
            if (StringUtils.containsOnly(outEntity.getCodigoRetornoExtendido(), CERO)) {
                comprobantesDTO.add(obtenerComprobantesDTO(outEntity, empresa));
            } else {
                erroresServicio++;
            }
        }
        return erroresServicio;
    }

    /**
     * Obtener out entity.
     *
     * @param future
     *            the future
     * @return the comprobante debito automatico out entity
     */
    private ComprobanteDebitoAutomaticoOutEntity obtenerOutEntity(Future<ComprobanteDebitoAutomaticoOutEntity> future) {
        try {
            return future.get();
        } catch (ExecutionException e) {
            LOGGER.error(ERROR_SERVICIO_CONSULTA_DEBITO + e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(ERROR_SERVICIO_CONSULTA_DEBITO + e);
        }
        ComprobanteDebitoAutomaticoOutEntity outEntity = new ComprobanteDebitoAutomaticoOutEntity();
        outEntity.setCodigoRetornoExtendido(UNO);
        return outEntity;
    }

    /**
     * Obtener comprobantes DTO.
     *
     * @param outEntity
     *            the out entity
     * @param empresa
     *            the empresa
     * @return the comprobantes DTO
     */
    private ComprobantesDTO obtenerComprobantesDTO(ComprobanteDebitoAutomaticoOutEntity outEntity,
            EmpresaAdheridaEntity empresa) {
        Boolean hayError = false;
        LinkedList<ComprobanteDTO> listaComprobantesDTO = new LinkedList<ComprobanteDTO>();
        for (ComprobanteDebitoAutomatico comprobante : outEntity.getComprobantes()) {
            if (esDebitoAplicado(comprobante) || esDebitoRechazado(comprobante) || esDebitoReversado(comprobante)) {
                ComprobanteDTO comprobanteDTO = obtenerComprobanteDTO(comprobante, empresa);
                if (comprobanteDTO.getTieneError()) {
                    LOGGER.debug(
                            "Fallo la obtencion del comprobante de debito automatico {} para la empresa {} con el comprobante: {}",
                            comprobanteDTO, empresa, comprobante);
                    hayError = true;
                } else {
                    listaComprobantesDTO.add(comprobanteDTO);
                }
            }
        }
        return new ComprobantesDTO(listaComprobantesDTO, hayError);
    }

    /**
     * Es debito aplicado.
     *
     * @param comprobante
     *            the comprobante
     * @return the boolean
     */
    private Boolean esDebitoAplicado(ComprobanteDebitoAutomatico comprobante) {
        if (StringUtils.equals(comprobante.getTipoConsultaDDI(), UNO)
                && StringUtils.equals(comprobante.getCodigoEstadoDDI(), CODIGO_ESTADO_R00)) {
            return true;
        }
        return false;
    }

    /**
     * Es debito rechazado.
     *
     * @param comprobante
     *            the comprobante
     * @return the boolean
     */
    private Boolean esDebitoRechazado(ComprobanteDebitoAutomatico comprobante) {
        if (StringUtils.equals(comprobante.getTipoConsultaDDI(), UNO)
                && !StringUtils.equals(comprobante.getCodigoEstadoDDI(), CODIGO_ESTADO_R00)) {
            return true;
        }
        return false;
    }

    /**
     * Es debito reversado.
     *
     * @param comprobante
     *            the comprobante
     * @return the boolean
     */
    private Boolean esDebitoReversado(ComprobanteDebitoAutomatico comprobante) {
        if (StringUtils.equals(comprobante.getTipoConsultaDDI(), CUATRO)
                && StringUtils.equals(comprobante.getCodigoEstadoDDI(), CODIGO_ESTADO_R00)) {
            return true;
        }
        return false;
    }

    /**
     * Obtener comprobante DTO.
     *
     * @param outEntity
     *            the out entity
     * @param empresa
     *            the empresa
     * @return the comprobante DTO
     */
    private ComprobanteDTO obtenerComprobanteDTO(ComprobanteDebitoAutomatico outEntity, EmpresaAdheridaEntity empresa) {
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        try {
            comprobanteDTO.setFecha(
                    new SimpleDateFormat(FORMATO_FECHA, Locale.ENGLISH).parse(outEntity.getFechaVencimientoDDI()));
            comprobanteDTO.setIdDebitoDDI(outEntity.getIdDebitoDDI());
            comprobanteDTO.setCuitEmpresa(empresa.getCuitEmpresa());
            comprobanteDTO.setServicio(empresa.getNombreServicioEmpresa());
            comprobanteDTO.setPartida(empresa.getNroPartidaServicioEmpresa());
            comprobanteDTO.setTipoOperacion(obtenerTipoOperacion(outEntity));
            comprobanteDTO.setHistorial(HistorialComprobanteEnum.DEBITO_AUTOMATICO_CUENTA);
            MedioPago medioPago = consultarMedioPago(empresa);
            setearImporte(comprobanteDTO, outEntity, medioPago);
            if (medioPago != null) {
                comprobanteDTO.setDestinatario(medioPago.getNombreFantasia());
            } else {
                comprobanteDTO.setDestinatario(empresa.getNombreServicioEmpresa());
            }
            setearMedioDePago(comprobanteDTO, outEntity);
            comprobanteDTO.setDetalle(obtenerDetalle(outEntity, empresa, comprobanteDTO));
            setearTitulo(comprobanteDTO);
        } catch (ParseException e) {
            comprobanteDTO.setTieneError(true);
            LOGGER.error("error en parseo", e);
        } catch (ImporteConvertException e) {
            comprobanteDTO.setTieneError(true);
            LOGGER.error("error al convertir importe", e);
        }
        return comprobanteDTO;
    }

    /**
     * Consultar medio pago.
     *
     * @param empresa
     *            the empresa
     * @return the medio pago
     */
    private MedioPago consultarMedioPago(EmpresaAdheridaEntity empresa) {
        Respuesta<MedioPago> medioPago = medioPagoBO.getByCuitServicio(StringUtils.trim(empresa.getCuitEmpresa()),
                StringUtils.trim(empresa.getNombreServicioEmpresa()));
        return medioPago.getRespuesta();
    }

    /**
     * Obtener detalle.
     *
     * @param outEntity
     *            the out entity
     * @param empresa
     *            the empresa
     * @param comprobanteDTO
     *            the comprobante DTO
     * @return the detalle comprobante DTO
     * @throws ParseException
     *             the parse exception
     */
    private DetalleComprobanteDTO obtenerDetalle(ComprobanteDebitoAutomatico outEntity, EmpresaAdheridaEntity empresa,
            ComprobanteDTO comprobanteDTO) throws ParseException {
        DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO detalle = new DetalleComprobanteIatxDebitoAutomaticoEnCuentaDTO();
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_INFORME_DEBITOS_AUTOMATICOS_EN_CUENTA);
        detalle.setIdentificacion(empresa.getNroPartidaServicioEmpresa());
        detalle.setFechaDePago(
                new SimpleDateFormat(FORMATO_FECHA, Locale.ENGLISH).parse(outEntity.getFechaDebitoRevisionDDI()));
        detalle.setMotivoRechazo(conseguirMotivoRechazo(outEntity));
        if (comprobanteDTO.getImporteDolares() == null) {
            detalle.setNroCuentaOrigen(comprobanteDTO.getCtaMedioDePagoPesos());
            detalle.setTipoCuentaOrigen(comprobanteDTO.getTipoCtaMedioDePagoPesos());
        } else {
            detalle.setNroCuentaOrigen(comprobanteDTO.getCtaMedioDePagoDolares());
            detalle.setTipoCuentaOrigen(comprobanteDTO.getTipoCtaMedioDePagoDolares());
        }
        detalle.setTipoComprobante(TipoDetalleComprobanteEnum.IATX_INFORME_DEBITOS_AUTOMATICOS_EN_CUENTA);
        detalle.setEmpresa(empresa);
        return detalle;
    }

    /**
     * Sets the ear titulo.
     *
     * @param comprobanteDTO
     *            the new ear titulo
     */
    private void setearTitulo(ComprobanteDTO comprobanteDTO) {
        if (comprobanteDTO.getTipoOperacion().equals(TipoOperacionComprobanteEnum.DEBITO_AUTOMATICO_CUENTA_RECHAZADO)) {
            comprobanteDTO.getDetalle()
                    .setTituloComprobante(CabeceraComprobantesEnum.DEBITO_AUTOMATICO_EN_CUENTA_RECHAZADO.getDetalle());
        } else {
            comprobanteDTO.getDetalle()
                    .setTituloComprobante(CabeceraComprobantesEnum.DEBITO_AUTOMATICO_EN_CUENTA.getDetalle());
        }
    }

    /**
     * Conseguir motivo rechazo.
     *
     * @param outEntity
     *            the out entity
     * @return the string
     */
    private String conseguirMotivoRechazo(ComprobanteDebitoAutomatico outEntity) {
        // Rechazado
        if ("1".equals(outEntity.getTipoConsultaDDI()) && !"R00".equals(outEntity.getCodigoEstadoDDI())) {
            return traerMotivoDeProperty(outEntity);
            // Reversado
        } else if ("4".equals(outEntity.getTipoConsultaDDI()) && "R00".equals(outEntity.getCodigoEstadoDDI())) {
            return "Débito Rechazado";
        }
        return null;
    }

    /**
     * Traer motivo de property.
     *
     * @param outEntity
     *            the out entity
     * @return the string
     */
    private String traerMotivoDeProperty(ComprobanteDebitoAutomatico outEntity) {
        if ("R10".equals(outEntity.getCodigoEstadoDDI())) {
            return faltaFondos;
        } else if ("R14".equals(outEntity.getCodigoEstadoDDI())) {
            return idErronea;
        } else {
            return "Débito Rechazado";
        }
    }

    /**
     * Obtener tipo operacion.
     *
     * @param comprobante
     *            the comprobante
     * @return the tipo operacion comprobante enum
     */
    private TipoOperacionComprobanteEnum obtenerTipoOperacion(ComprobanteDebitoAutomatico comprobante) {
        if (esDebitoRechazado(comprobante) || esDebitoReversado(comprobante)) {
            return TipoOperacionComprobanteEnum.DEBITO_AUTOMATICO_CUENTA_RECHAZADO;
        }
        return TipoOperacionComprobanteEnum.DEBITO_AUTOMATICO_CUENTA;
    }

    /**
     * Setear importe.
     *
     * @param comprobanteDTO
     *            the comprobante DTO
     * @param outEntity
     *            the out entity
     * @param medioPago
     *            the medio pago
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    private void setearImporte(ComprobanteDTO comprobanteDTO, ComprobanteDebitoAutomatico outEntity,
            MedioPago medioPago) throws ImporteConvertException {
        BigDecimal importe = ISBANStringUtils.convertirStrToBigDecimal(outEntity.getImporteDebitoOriginalDDI(), 2);
        List<String> tiposCuenta = new ArrayList<String>(Arrays.asList(DOBLE_CERO, CEROUNO, CERONUEVE));
        if (tiposCuenta.contains(outEntity.getTipoCuentaDebito())) {
            comprobanteDTO.setImportePesos(importe);
        } else {
            String moneda = recuperarMoneda(medioPago);
            if (CERODOS.equals(outEntity.getTipoCuentaDebito()) && PESOS.equals(moneda)) {
                comprobanteDTO.setImportePesos(importe);
            } else {
                comprobanteDTO.setImporteDolares(importe);
            }
        }
    }

    /**
     * Recuperar moneda.
     *
     * @param medioPago
     *            the medio pago
     * @return the string
     */
    private String recuperarMoneda(MedioPago medioPago) {
        if (medioPago != null) {
            return medioPago.getMoneda();
        }
        return PESOS;
    }

    /**
     * Setear medio de pago.
     *
     * @param comprobanteDTO
     *            the comprobante DTO
     * @param outEntity
     *            the out entity
     */
    private void setearMedioDePago(ComprobanteDTO comprobanteDTO, ComprobanteDebitoAutomatico outEntity) {
        TipoCuenta tipoCuenta = TipoCuenta.fromCodigo(outEntity.getTipoCuentaDebito());
        IdentificacionCuenta idCuenta = new IdentificacionCuenta(outEntity.getSucursalCuentaDebito(),
                outEntity.getNroCuentaProductoDebito());
        if (comprobanteDTO.getImportePesos() != null) {
            comprobanteDTO.setTipoCtaMedioDePagoPesos(tipoCuenta);
            comprobanteDTO.setCtaMedioDePagoPesos(idCuenta.toString());
        } else {
            comprobanteDTO.setTipoCtaMedioDePagoDolares(tipoCuenta);
            comprobanteDTO.setCtaMedioDePagoDolares(idCuenta.toString());
        }
    }

    /**
     * Crear respuesta.
     *
     * @param listaComprobantesDTO
     *            the lista comprobantes DTO
     * @param erroresServicio
     *            the errores servicio
     * @param outEntityEmpresasAdheridas
     *            the out entity empresas adheridas
     * @return the respuesta
     */
    private Respuesta<ComprobantesDTO> crearRespuesta(List<ComprobantesDTO> listaComprobantesDTO,
            Integer erroresServicio, EmpresasAdheridasDebitoAutoOutEntity outEntityEmpresasAdheridas) {
        if (erroresServicio == outEntityEmpresasAdheridas.getEmpresas().size()) {
            return respuestaFactory.crearRespuestaError(ComprobantesDTO.class, new ArrayList<ItemMensajeRespuesta>());
        }
        Integer erroresDTO = 0;
        LinkedList<ComprobanteDTO> linkedListComprobantesDTO = new LinkedList<ComprobanteDTO>();
        for (ComprobantesDTO comprobanteDTO : listaComprobantesDTO) {
            linkedListComprobantesDTO.addAll(comprobanteDTO.getComprobantes());
            if (comprobanteDTO.getTieneError()) {
                erroresDTO++;
            }
        }
        if (erroresServicio == 0 && erroresDTO == 0) {
            return respuestaFactory.crearRespuestaOk(new ComprobantesDTO(linkedListComprobantesDTO));
        }
        return respuestaFactory.crearRespuestaWarning(new ArrayList<DatoItemMensaje>(),
                new ComprobantesDTO(linkedListComprobantesDTO));
    }

	@Override
	public Respuesta<ComprobantesDTO> obtenerComprobantesDebitoAutomaticoEnCuentaPorEmpresa(Cliente cliente,
			EmpresaAdheridaEntity empresa, TransaccionDTO dto) {
		EmpresasAdheridasDebitoAutoOutEntity outEntityEmpresasAdheridas = new EmpresasAdheridasDebitoAutoOutEntity();
		outEntityEmpresasAdheridas.setEmpresas(new ArrayList<EmpresaAdheridaEntity>(Arrays.asList(empresa)));
        HashMap<EmpresaAdheridaEntity, Future<ComprobanteDebitoAutomaticoOutEntity>> comprobantesOutEntity = consultarDebitosAutomaticosEmpresas(
                outEntityEmpresasAdheridas, cliente, dto);
        List<ComprobantesDTO> listaComprobantesDTO = new ArrayList<ComprobantesDTO>();
        Integer erroresServicio = procesarRespuestas(outEntityEmpresasAdheridas, listaComprobantesDTO,
                comprobantesOutEntity);
        return crearRespuesta(listaComprobantesDTO, erroresServicio, outEntityEmpresasAdheridas);
	}
}
