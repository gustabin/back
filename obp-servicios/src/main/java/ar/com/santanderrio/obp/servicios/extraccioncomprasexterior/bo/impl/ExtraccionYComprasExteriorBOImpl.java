/*
 * 
 */

package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.ListUtil;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.ExtraccionYComprasExteriorBO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.ExtraccionYComprasExteriorDAO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorInDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorOutDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CuentaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.DatosExtraccionYComprasExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.TarjetaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ComprobanteOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CuentaOperacionExteriorEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.TarjetaOperacionExteriorEntity;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * Clase CambioDomicilioBOImpl.
 *
 * @author Silvina_Luque
 */
@Component
public class ExtraccionYComprasExteriorBOImpl implements ExtraccionYComprasExteriorBO {

    /** The sesion cliente. */
    @Autowired
    private SesionCliente sesionCliente;

    /** The ion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The datos cambioDomicilioDAO. */
    @Autowired
    private ExtraccionYComprasExteriorDAO extraccionComprasExteriorDao;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(ExtraccionYComprasExteriorBOImpl.class);

    /** The Constant OPCION_CONSULTA. */
    private static final String CONSULTA_TARJETA = "T";

    /** The Constant TIPO_TARJETA. */
    private static final String TIPO_TARJETA = "E";

    /** The Constant CUENTAS_RELACIONADAS. */
    private static final String CUENTAS_RELACIONADAS = "C";

    /** The Constant CUENTA_NO_PREFERIDA. */
    private static final String CUENTA_NO_PREFERIDA = "N";

    /** The Constant CNSTARJETAS_PRIMER_LLAMADO. */
    private static final String CNSTARJETAS_PRIMER_LLAMADO = "P";

    /** The Constant CNSTARJETAS_SEGUNDO_LLAMADO. */
    private static final String CNSTARJETAS_SEGUNDO_LLAMADO = "S";

    /** The Constant CNSTARJETAS_CANTIDAD_PEDIDA. */
    private static final String CNSTARJETAS_CANTIDAD_PEDIDA = "50";

    /** The Constant CNSTARJETAS_FIL_80. */
    private static final int CNSTARJETAS_FIL_80 = 80;

    /** The Constant CMBTARJETA_TIPO_LLAMADO_M. */
    private static final String CMBTARJETA_TIPO_LLAMADO_M = "M";

    /** The Constant CMBTARJETA_TIPO_MODIF_T. */
    private static final String CMBTARJETA_TIPO_MODIF_T = "T";

    /** The Constant CMB_TARJETA_CODIGO_11. */
    private static final String CMB_TARJETA_CODIGO_11 = "11";

    /** The Constant CTA_LEN. */
    private static final int CTA_LEN = 23;

    /** The Constant TPODOC_LEN. */
    private static final int TPODOC_LEN = 2;

    /** The Constant NRODOC_LEN. */
    private static final int NRODOC_LEN = 12;

    /** The Constant DATA_LEN. */
    private static final int DATA_LEN = 120;

    /** The Constant CUENTA_ESTADO_PRINCIPAL. */
    private static final String CUENTA_ESTADO_PRINCIPAL = "3";

    /** The Constant CUENTA_ESTADO_ABIERTA. */
    private static final String CUENTA_ESTADO_ABIERTA = "1";

    /** The Constant Estado Tarjeta Activa. */
    private static final String ESTADO_TARJETA_ACTIVA = "1";

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.
     * ExtraccionYComprasExteriorBO#consultarCuentasOperaExterior(java.lang.
     * String)
     */
    @Override
    public Respuesta<List<CuentaOperacionExteriorDTO>> consultarCuentasOperaExterior(String numeroTarjeta) {
        LOGGER.info("ExtraccionYComprasExteriorBO _ Iniciando metodo consultarCuentas ");

        ConsultaCuentasOperaExteriorInEntity cuentasExteriorInEntity = generarInputEntityCnsCuentas(numeroTarjeta);
        List<CuentaOperacionExteriorDTO> lCtasDTO = new ArrayList<CuentaOperacionExteriorDTO>();
        try {
            LOGGER.debug("Iniciando llamada a ExtraccionYComprasExteriorDAO consultarCuentasOperaExterior");
            ConsultaCuentasOperaExteriorOutEntity outEntity = extraccionComprasExteriorDao
                    .consultarCuentasOperaExterior(cuentasExteriorInEntity);
            LOGGER.debug("Finalizando llamada a ExtraccionYComprasExteriorDAO  consultarCuentasOperaExterior");

            for (Iterator<CuentaOperacionExteriorEntity> iterator = ListUtil.safe(outEntity.getListaCuentas())
                    .iterator(); iterator.hasNext();) {
                CuentaOperacionExteriorDTO cuentaDTO = new CuentaOperacionExteriorDTO();
                CuentaOperacionExteriorEntity cuentaEntity = iterator.next();
                if (CUENTA_ESTADO_ABIERTA.equals(cuentaEntity.getRelacionEstado())
                        || CUENTA_ESTADO_PRINCIPAL.equals(cuentaEntity.getRelacionEstado())) {
                    cuentaDTO.setId(UUID.randomUUID().toString());
                    cuentaDTO.setCuentaRelacionada(cuentaEntity.getCuentaRelacionada());
                    cuentaDTO.setNumeroTarjeta(numeroTarjeta);
                    cuentaDTO.setCuentaPreferida(
                            CUENTA_NO_PREFERIDA.equals(cuentaEntity.getCuentaPreferida()) ? false : true);
                    lCtasDTO.add(cuentaDTO);
                }

            }

        } catch (DAOException e) {
            LOGGER.error("consultarCuentasOperaExterior", e);
            return this.respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_CUENTAS,
                    CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CONSULTA_CUENTAS);
        }
        LOGGER.debug("ExtraccionYComprasExteriorBO _ Finalizando llamada a consultar cuentas");
        return this.respuestaFactory.crearRespuestaOk(lCtasDTO);

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.
     * ExtraccionYComprasExteriorBO#consultarTarjetasOperaExterior()
     */
    @Override
    public Respuesta<List<TarjetaOperacionExteriorDTO>> consultarTarjetasOperaExterior() {
        LOGGER.info("ExtraccionYComprasExteriorBO _ Iniciando metodo consultaTarjetas ");
        String claveSegundoLlamado = "";

        ConsultaTarjetasOperaExteriorInEntity tarjetasExteriorInEntity = generarInputEntityCnsTarjetas(
                CNSTARJETAS_PRIMER_LLAMADO, claveSegundoLlamado);
        List<TarjetaOperacionExteriorDTO> lTarjetasDTO = new ArrayList<TarjetaOperacionExteriorDTO>();
        try {
            LOGGER.debug("ExtraccionYComprasExteriorBO _ Iniciando llamada a ExtraccionYComprasExteriorDAO ");
            ConsultaTarjetasOperaExteriorOutEntity outEntity = extraccionComprasExteriorDao
                    .consultarTarjetasOperaExterior(tarjetasExteriorInEntity);
            LOGGER.debug("ExtraccionYComprasExteriorBO _ Finalizando llamada a ExtraccionYComprasExteriorDAO ");

            for (Iterator<TarjetaOperacionExteriorEntity> iterator = ListUtil.safe(outEntity.getListaTarjetas())
                    .iterator(); iterator.hasNext();) {
                TarjetaOperacionExteriorEntity tarjetaEntity = iterator.next();
                if(ESTADO_TARJETA_ACTIVA.equals(tarjetaEntity.getEstadoTarjeta())) {
                    lTarjetasDTO.add(generarTarjetaDTO(tarjetaEntity));
                }
            }

            if (CNSTARJETAS_SEGUNDO_LLAMADO.equals(outEntity.getHayMasDatos()) && outEntity.getListaTarjetas() != null
                    && CNSTARJETAS_CANTIDAD_PEDIDA.equals(outEntity.getCantidadTarjetas().toString())) {
                do {
                    claveSegundoLlamado = outEntity.getClaveRellamada().substring(0, CNSTARJETAS_FIL_80);
                    tarjetasExteriorInEntity = generarInputEntityCnsTarjetas(CNSTARJETAS_SEGUNDO_LLAMADO,
                            claveSegundoLlamado);
                    LOGGER.debug(
                            "ExtraccionYComprasExteriorBO _ Iniciando segundo llamado a ExtraccionYComprasExteriorDAO ");
                    outEntity = extraccionComprasExteriorDao.consultarTarjetasOperaExterior(tarjetasExteriorInEntity);
                    LOGGER.debug(
                            "ExtraccionYComprasExteriorBO _ Finalizando segundo llamado a ExtraccionYComprasExteriorDAO ");

                    for (Iterator<TarjetaOperacionExteriorEntity> iterator = ListUtil.safe(outEntity.getListaTarjetas())
                            .iterator(); iterator.hasNext();) {
                        TarjetaOperacionExteriorEntity tarjetaEntity = iterator.next();
                        if(ESTADO_TARJETA_ACTIVA.equals(tarjetaEntity.getEstadoTarjeta())) {
                            lTarjetasDTO.add(generarTarjetaDTO(tarjetaEntity));
                        }
                    }

                } while (CNSTARJETAS_SEGUNDO_LLAMADO.equals(outEntity.getHayMasDatos())
                        && outEntity.getListaTarjetas() != null
                        && CNSTARJETAS_CANTIDAD_PEDIDA.equals(String.valueOf(outEntity.getListaTarjetas().size())));

            }

        } catch (DAOException e) {
            LOGGER.error("ConsultarDomiciliosRegistrados", e);
            return this.respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_TARJETAS,
                    CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CONSULTA_TARJETAS);
        }
        LOGGER.debug("ExtraccionYComprasExteriorBO _ Finalizando llamada a consultar tarjetas");
        return this.respuestaFactory.crearRespuestaOk(lTarjetasDTO);

    }

    /**
	 * generarTarjetaDTO.
	 *
	 * @param tarjetaEntity
	 *            the tarjeta entity
	 * @return the tarjeta operacion exterior DTO
	 */
    private TarjetaOperacionExteriorDTO generarTarjetaDTO(
            TarjetaOperacionExteriorEntity tarjetaEntity) {
        TarjetaOperacionExteriorDTO tarjetaDTO = new TarjetaOperacionExteriorDTO();
        tarjetaDTO.setNumeroTarjeta(tarjetaEntity.getNroPan());
        tarjetaDTO.setNumeroDocumento(tarjetaEntity.getNumeroDocumento());
        tarjetaDTO.setTipoDocumento(tarjetaEntity.getTipoDocumento());
        tarjetaDTO.setId(UUID.randomUUID().toString());
        return tarjetaDTO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.
     * ExtraccionYComprasExteriorBO#cambioTarjetaOperaExterior(ar.com.
     * santanderrio.obp.servicios.extraccioncomprasexterior.dto.
     * CambioTarjetaOperaExteriorInDTO)
     */
    @Override
    public Respuesta<CambioTarjetaOperaExteriorOutDTO> cambioTarjetaOperaExterior(
            CambioTarjetaOperaExteriorInDTO datosCambio) {
        LOGGER.info("ExtraccionYComprasExteriorBO _ Iniciando metodo cambio tarjeta para operar exterior ");

        CambioTarjetaOperaExteriorInEntity cambioTarjetaExteriorInEntity = generarInputEntityCmbTarjeta(datosCambio);
        CambioTarjetaOperaExteriorOutDTO outDTO = new CambioTarjetaOperaExteriorOutDTO();
        try {
            LOGGER.debug("ExtraccionYComprasExteriorBO _ Iniciando llamada a ExtraccionYComprasExteriorDAO ");
            CambioTarjetaOperaExteriorOutEntity outEntity = extraccionComprasExteriorDao
                    .cambioTarjetaOperaExterior(cambioTarjetaExteriorInEntity);
            LOGGER.debug("ExtraccionYComprasExteriorBO _ Finalizando llamada a ExtraccionYComprasExteriorDAO ");
            outDTO.setNumeroComprobante(outEntity.getNroComprobante());
            outDTO.setFecha(outEntity.getFecha());
            outDTO.setHora(outEntity.getHora());

        } catch (DAOException e) {
            LOGGER.error("Error en cambio de tarjeta para operar en el exterior", e);
            return this.respuestaFactory.crearRespuestaError("", TipoError.OPERACIONES_EXTERIOR_ERROR_CMBTARJETA,
                    CodigoMensajeConstantes.OPERACIONES_EXTERIOR_ERROR_CAMBIO_TARJETA);
        }
        LOGGER.debug("ExtraccionYComprasExteriorBO _ Finalizando llamada a cambio tarjeta");
        return this.respuestaFactory.crearRespuestaOk(outDTO);

    }

    /**
     * Generar input entity cmb tarjeta.
     *
     * @param datosCambioTarjeta
     *            the datos cambio tarjeta
     * @return the cambio tarjeta opera exterior in entity
     */
    private CambioTarjetaOperaExteriorInEntity generarInputEntityCmbTarjeta(
            CambioTarjetaOperaExteriorInDTO datosCambioTarjeta) {
        CambioTarjetaOperaExteriorInEntity entity = new CambioTarjetaOperaExteriorInEntity();
        entity.setCliente(sesionCliente.getCliente());
        entity.setTipoLlamado(CMBTARJETA_TIPO_LLAMADO_M);
        entity.setTipoModificacion(CMBTARJETA_TIPO_MODIF_T);
        entity.setClaveModificacion(ISBANStringUtils.fillStr(datosCambioTarjeta.getNumeroTarjeta(), 26));
        entity.setCodigoCambio(CMB_TARJETA_CODIGO_11);
        entity.setDatosVariables(generarDatosVariables(datosCambioTarjeta));

        return entity;
    }

    /**
     * generarDatosVariables.
     *
     * @param cambioTarjeta
     *            the cambio tarjeta
     * @return the string
     */
    private String generarDatosVariables(CambioTarjetaOperaExteriorInDTO cambioTarjeta) {
        StringBuilder datos = new StringBuilder();
        String tipoDocumento = cambioTarjeta.getTipoDocumento().trim();
        String numeroDocumento = cambioTarjeta.getNumeroDocumento().trim();
        datos.append(cambioTarjeta.getCuenta().substring(0, CTA_LEN));
        if (numeroDocumento == null || numeroDocumento.isEmpty()) {
            numeroDocumento = sesionCliente.getCliente().getDni();
            tipoDocumento = sesionCliente.getCliente().getTipoDocumento();
        }
        datos.append(ISBANStringUtils.fillStr(tipoDocumento, TPODOC_LEN));
        datos.append(ISBANStringUtils.fillNum(numeroDocumento, NRODOC_LEN));
        return ISBANStringUtils.fillStr(datos.toString(), DATA_LEN);
    }

    /**
     * generarInputEntityCnsTarjetas.
     *
     * @param tipoLlamado
     *            the tipo llamado
     * @param claveSegundoLlamado
     *            the clave segundo llamado
     * @return ConsultaTarjetasOperaExteriorInEntity
     */
    private ConsultaTarjetasOperaExteriorInEntity generarInputEntityCnsTarjetas(String tipoLlamado,
            String claveSegundoLlamado) {
        ConsultaTarjetasOperaExteriorInEntity entity = new ConsultaTarjetasOperaExteriorInEntity();
        entity.setCliente(sesionCliente.getCliente());
        entity.setNup(entity.getCliente().getNup());
        entity.setCantidadPedida(CNSTARJETAS_CANTIDAD_PEDIDA);
        entity.setDatosADevolver(CONSULTA_TARJETA);
        entity.setTipoConsulta(tipoLlamado);
        if (CNSTARJETAS_SEGUNDO_LLAMADO.equals(tipoLlamado)) {
            entity.setClaveSegundoLlamado(claveSegundoLlamado);
        } else {
            entity.setClaveSegundoLlamado(ISBANStringUtils.fillStr("", CNSTARJETAS_FIL_80));
        }
        entity.setDatosAdicionaes(ISBANStringUtils.fillStr("", CNSTARJETAS_FIL_80));
        return entity;
    }

    /**
     * generarInputEntityCnsCuentas.
     *
     * @param numeroTarjeta
     *            the numero tarjeta
     * @return ConsultaCuentasOperaExteriorInEntity
     */
    private ConsultaCuentasOperaExteriorInEntity generarInputEntityCnsCuentas(String numeroTarjeta) {
        Cliente cliente = sesionCliente.getCliente();
        ConsultaCuentasOperaExteriorInEntity entity = new ConsultaCuentasOperaExteriorInEntity();
        entity.setCliente(cliente);
        entity.setCuentasRelacionadas(CUENTAS_RELACIONADAS);
        entity.setNumeroTarjeta(numeroTarjeta);
        entity.setTipoConsulta(CONSULTA_TARJETA);
        entity.setTipoTarjeta(TIPO_TARJETA);
        return entity;

    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.
     * ExtraccionYComprasExteriorBO#descargarComprobante()
     */
    @Override
    public Respuesta<Reporte> descargarComprobante() {
        ComprobanteOperaExteriorInEntity datosComprobante = new ComprobanteOperaExteriorInEntity();
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO = sesionParametros
                .getDatosExtraccionYComprasExterior();
        datosComprobante.setNroComprobante(sesionParametros.getNroComprobante());
        datosComprobante.setNroTarjeta(datosOperaExteriorDTO.getTarjetaSeleccionada());
        datosComprobante.setNroCuenta(
                datosOperaExteriorDTO.getDescripcionCuenta() + "  " + datosOperaExteriorDTO.getCuentaSeleccionada());
        datosComprobante.setFecha(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        LOGGER.debug("ExtraccionYComprasExterior _ iniciando descargarComprobante");
        Respuesta<Reporte> respuestaReporte = new Respuesta<Reporte>();
        try {
            Reporte reporte = extraccionComprasExteriorDao.descargarComprobante(datosComprobante);
            respuestaReporte.setEstadoRespuesta(EstadoRespuesta.OK);
            respuestaReporte.setRespuesta(reporte);
        } catch (Exception e) {
            return this.respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.CODIGO_MENSAJE_ERROR_GENERICO);

        }
        LOGGER.debug("ExtraccionYComprasExterior _ finalizando descargarComprobante");
        return respuestaReporte;
    }
}
