/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.TipoDocumentoEnum;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.dao.AltaCanalAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.canalesautomaticos.entity.AltaCanalAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.compraventa.bo.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.dao.ConsultaPadronOCuitDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitInEntity;
import ar.com.santanderrio.obp.servicios.comun.dao.entities.ConsultaPadronCuitOutEntity;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.utils.FechaUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.DatosSolicitanteTarjetaAdicionalBO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteCriterioDTO;
import ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dto.DatosSolicitanteDTO;

/**
 * The Class DatosSolicitanteBOImpl.
 */
@Component
public class DatosSolicitanteTarjetaAdicionalBOImpl implements DatosSolicitanteTarjetaAdicionalBO {

    private static final String FECHA_DEFAULT_PADRON = "01/01/1901";

    /** The Constant CUIT. */
    private static final String CUIT = "CUIT";

    /** The alta canal automatico DAO. */
    @Autowired
    private AltaCanalAutomaticoDAO altaCanalAutomaticoDAO;

    /** The consulta padron cuit DAO. */
    @Autowired
    private ConsultaPadronOCuitDAO consultaPadronOCuitDAO;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DatosSolicitanteTarjetaAdicionalBOImpl.class);

    /** The Constant ERROR_CON_DATOS_SOLICITANTE_MONEDERO_TAG. */
    public static final String ERROR_CON_DATOS_SOLICITANTE_MONEDERO_TAG = "sinDatos";

    /** The Constant SIN_INFORMACION. */
    private static final String SIN_INFORMACION = "sinInformacion";

    /** The Constant BUSQUEDA_PADRON_ARGUMENTO. */
    private static final String BUSQUEDA_PADRON_ARGUMENTO = "DNI";

    /** The Constant BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI. */
    /** private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI = "1"; */
    // En el documento de CNSPADCUIT dice que hay que si es DNI el campo opcion
    // es 1.. ESTA MAL!! ES 3!!
    private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI = "3";

    /** The Constant BUSQUEDA_PADRON_ARGUMENTO_MAP_CUITL. */
    private static final String BUSQUEDA_PADRON_ARGUMENTO_MAP_CUITL = "2";

    /**
     * Crear respuesta erronea informacion no disponible operacion.
     *
     * @param <T>
     *            the generic type
     * @return the respuesta
     */
    public <T> Respuesta<T> crearRespuestaErroneaInformacionNoDisponibleOperacion() {
        return respuestaFactory.crearRespuestaError(SIN_INFORMACION, TipoError.ERROR_SOLICITAR_TAG_TITULAR,
                CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_DATOS_SOLICITANTE_MONEDERO);
    }

    /**
     * Crea respuesta erronea get datos solicitante adicional.
     *
     * @return the respuesta
     */
    public Respuesta<DatosSolicitanteDTO> creaRespuestaErroneaGetDatosSolicitanteAdicional() {
        return respuestaFactory.crearRespuestaWarning(null, TipoError.DATOS_INVALIDOS,
                CodigoMensajeConstantes.MENSAJE_WARNING_DATOS_INVALIDOS);
    }

    /**
     * Crear respuesta error.
     *
     * @param <T>
     *            the generic type
     * @param error
     *            the error
     * @param tipoOperacion
     *            the tipo operacion
     * @return the respuesta
     */
    public <T> Respuesta<T> crearRespuestaError(ErrorCompraVentaEnum error, String tipoOperacion) {
        Respuesta<T> respuesta = respuestaFactory.crearRespuestaError(error.getTag(), error.getTipoError(),
                error.getCodigoMensaje());
        if (error.getTag().equals(CompraVentaDolaresUtil.SUCURSAL_ORIGEN_FUERA_HORARIO)) {
            String mensajeParseado = StringUtils.replace(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
                    "{0}", CompraVentaDolaresUtil.obtenerDetalleOperacion(tipoOperacion));
            respuesta.getItemsMensajeRespuesta().get(0).setMensaje(mensajeParseado);
        }
        return respuesta;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.bo.
     * DatosSolicitanteTarjetaAdicionalBO#ejecutarAltaCanalesAutomaticos(ar.com.
     * santanderrio.obp.servicios.canalesautomaticos.entity.
     * AltaCanalAutomaticoInEntity,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<AltaCanalAutomaticoOutEntity> ejecutarAltaCanalesAutomaticos(
            AltaCanalAutomaticoInEntity altaCanalAutomaticoInEntity, Cliente cliente) {
        try {
            altaCanalAutomaticoInEntity.setCliente(cliente);
            AltaCanalAutomaticoOutEntity altaCanalAutomaticoOutEntity = altaCanalAutomaticoDAO
                    .altaCanalAutomatico(altaCanalAutomaticoInEntity);
            if (altaCanalAutomaticoOutEntity != null) {
                return respuestaFactory.crearRespuestaOk(AltaCanalAutomaticoOutEntity.class,
                        altaCanalAutomaticoOutEntity);
            }
            LOGGER.error("Error dando de alta la persona fisica");
        } catch (DAOException e) {
            LOGGER.error("Error dando de alta la persona fisica", e);
        }
        return respuestaFactory.crearRespuestaError("", TipoError.ERROR_GENERICO,
                CodigoMensajeConstantes.MENSAJE_ERROR_GENERICO_ALTA_PERSONA_FISICA);
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.monedero.bo.DatosSolicitanteBO#
     * getDatosPadronBO(ar.com.santanderrio.obp.servicios.monedero.web.view.
     * DatosSolicitanteCriterioView,
     * ar.com.santanderrio.obp.servicios.clientes.entities.Cliente)
     */
    @Override
    public Respuesta<DatosSolicitanteDTO> getDatosPadronBO(DatosSolicitanteCriterioDTO datosSolicitante,
            Cliente cliente) throws DAOException {
        ConsultaPadronCuitInEntity objConsultaPadronInEntity = new ConsultaPadronCuitInEntity();
        objConsultaPadronInEntity.setCliente(cliente);
        if (datosSolicitante.getDocTipo().trim().equals(BUSQUEDA_PADRON_ARGUMENTO)
                || datosSolicitante.getDocTipo().trim().equals(TipoDocumentoEnum.DNI.getCampo())) {
            objConsultaPadronInEntity.setOpcion(BUSQUEDA_PADRON_ARGUMENTO_MAP_DNI);
            objConsultaPadronInEntity.setArgumento(String.format("%011d", Integer.parseInt(datosSolicitante.getDoc())));
        } else {
            objConsultaPadronInEntity.setOpcion(BUSQUEDA_PADRON_ARGUMENTO_MAP_CUITL);
            objConsultaPadronInEntity.setArgumento(datosSolicitante.getDoc());
        }

        List<ConsultaPadronCuitOutEntity> coincidencias = consultaPadronOCuitDAO
                .consultaPadronRetornandoListaCoincidencias(objConsultaPadronInEntity);

        if (!coincidencias.isEmpty()) {
            DatosSolicitanteDTO primeraCoincidencia = obtienePrimerCoincidencia(datosSolicitante, coincidencias);

            if (primeraCoincidencia.getFechaNacimiento() != null) {
                primeraCoincidencia.setFechaNacimiento(FechaUtils
                        .modificarFormatoFechas(primeraCoincidencia.getFechaNacimiento(), "dd/MM/yyyy", "ddMMyyyy"));
                return respuestaFactory.crearRespuestaOk(DatosSolicitanteDTO.class, primeraCoincidencia);
            } else {
                return respuestaFactory.crearRespuestaWarning("", TipoError.FECHA_NACIMIENTO_NO_COINCIDE,
                        CodigoMensajeConstantes.ERROR_FECHA_NACIMIENTO_NO_COINCIDE);
            }
        } else {
            return respuestaFactory.crearRespuestaWarning("", TipoError.PERSONA_NO_EXISTE_EN_PADRON,
                    CodigoMensajeConstantes.ERROR_DOCUMENTO_INVALIDO);
        }
    }

    /**
     * Obtiene primer coincidencia.
     *
     * @param datosSolicitante
     *            the datos solicitante
     * @param coincidencias
     *            the coincidencias
     * @return the datos solicitante DTO
     */
    private DatosSolicitanteDTO obtienePrimerCoincidencia(DatosSolicitanteCriterioDTO datosSolicitante,
            List<ConsultaPadronCuitOutEntity> coincidencias) {
        DatosSolicitanteDTO datosSolicitanteDTO = new DatosSolicitanteDTO();
        if (!coincidencias.isEmpty()) {
            for (Iterator<ConsultaPadronCuitOutEntity> iterator = coincidencias.iterator(); iterator.hasNext();) {
                ConsultaPadronCuitOutEntity objConsultaPadronOutEntity = iterator.next();
                if ((objConsultaPadronOutEntity.getAbaFechaNacimiento() != null && FECHA_DEFAULT_PADRON.equals(objConsultaPadronOutEntity.getAbaFechaNacimiento().trim()))
                   || (fechasNacimientoNotNull(datosSolicitante, objConsultaPadronOutEntity) && objConsultaPadronOutEntity
                        .getAbaFechaNacimiento().trim().equals(datosSolicitante.getFechaNacimiento().trim()))) {
                    this.setDatosSolicitanteDTO(datosSolicitanteDTO, objConsultaPadronOutEntity);
                }
            }
        }
        return datosSolicitanteDTO;
    }

    /**
     * Sets the datos solicitante DTO.
     *
     * @param datosSolicitanteDTO
     *            the datos solicitante DTO
     * @param objConsultaPadronOutEntity
     *            the obj consulta padron out entity
     */
    private void setDatosSolicitanteDTO(DatosSolicitanteDTO datosSolicitanteDTO,
            ConsultaPadronCuitOutEntity objConsultaPadronOutEntity) {
        /**
         * EstadoCivil, Nacionalidad y PaisNacimiento no son retornados por el servicio
         * CNSPADCUIT
         */
        datosSolicitanteDTO.setApellido(objConsultaPadronOutEntity.getAbaApellido().trim());
        datosSolicitanteDTO.setNombre(objConsultaPadronOutEntity.getAbaNombre());
        datosSolicitanteDTO.setCuitCuil(CUIT);
        datosSolicitanteDTO.setCuitCuilNroIns(objConsultaPadronOutEntity.getAbaNroCuit().trim());
        datosSolicitanteDTO.setDocumentoNro(objConsultaPadronOutEntity.getAbaNroDocumento().trim());
        datosSolicitanteDTO.setDocumentoTipo(objConsultaPadronOutEntity.getAbaTipoDocumento());
        datosSolicitanteDTO.setFechaNacimiento(objConsultaPadronOutEntity.getAbaFechaNacimiento());
        datosSolicitanteDTO.setSexo("M".equals(objConsultaPadronOutEntity.getAbaSexo()) ? "Masculino" : "Femenino");
    }

    /**
     * fechasNacimientoNotNull.
     *
     * @param datosSolicitante
     *            the datos solicitante
     * @param objConsultaPadronOutEntity
     *            the obj consulta padron out entity
     * @return true, if successful
     */
    private boolean fechasNacimientoNotNull(DatosSolicitanteCriterioDTO datosSolicitante,
            ConsultaPadronCuitOutEntity objConsultaPadronOutEntity) {
        return datosSolicitante.getFechaNacimiento() != null && !"".equals(datosSolicitante.getFechaNacimiento().trim())
                && objConsultaPadronOutEntity.getAbaFechaNacimiento() != null
                && !"".equals(objConsultaPadronOutEntity.getAbaFechaNacimiento().trim());
    }

}
