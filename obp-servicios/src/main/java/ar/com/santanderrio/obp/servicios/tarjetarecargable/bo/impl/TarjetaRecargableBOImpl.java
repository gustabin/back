/*
 * 
 */
package ar.com.santanderrio.obp.servicios.tarjetarecargable.bo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.bo.TarjetaRecargableBO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dao.TarjetaRecargableDAO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableInDTO;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableInEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.entities.SolicitudTarjetaRecargableOutEntity;
import ar.com.santanderrio.obp.servicios.tarjetarecargable.web.view.DatosComprobanteSolicitudTarjetaRecargableView;

/**
 * The Class TarjetaRecargableBOImpl.
 */
@Component
public class TarjetaRecargableBOImpl implements TarjetaRecargableBO {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TarjetaRecargableBOImpl.class);

    /** The Constant PIPE. */
    private static final String PIPE = "|";

    /** The Constant NL. */
    private static final String NL = "\n";

    /** The Constant ESPACIO. */
    private static final String ESPACIO = " ";

    /** The Constant LOGGER_ERROR. */
    private static final String LOGGER_ERROR = "Error al llamar a Store Procedure para solicitar una tarjeta recargable.";

    /** The Constant LOGGER_OK. */
    private static final String LOGGER_OK = "Respuesta DTO OK obtenida al llamar a Store Procedure para solicitar una tarjeta recargable.";

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    /** The tarjeta recargable DAO. */
    @Autowired
    private TarjetaRecargableDAO tarjetaRecargableDAO;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.bo.
     * TarjetaRecargableBO#altaSolicitudTarjetaRecargable(ar.com.santanderrio.
     * obp.servicios.tarjetarecargable.dto.DatosSolicitudTarjetaRecargableInDTO)
     */
    @Override
    public Respuesta<DatosSolicitudTarjetaRecargableDTO> altaSolicitudTarjetaRecargable(
            DatosSolicitudTarjetaRecargableInDTO datosSolicitudTarjetaRecargableInDTO) {
        LOGGER.info("TarjetaRecargableBOImpl - altaSolicitudTarjetaRecargable");

        String spAtributos = construirParametroDeEntrada(datosSolicitudTarjetaRecargableInDTO);
        SolicitudTarjetaRecargableInEntity solicitudTarjetaRecargableInEntity = new SolicitudTarjetaRecargableInEntity();
        solicitudTarjetaRecargableInEntity.setSpAtributos(spAtributos);

        Respuesta<DatosSolicitudTarjetaRecargableDTO> respuestaDTO = new Respuesta<DatosSolicitudTarjetaRecargableDTO>();
        Respuesta<SolicitudTarjetaRecargableOutEntity> solicitudTarjetaRecargableOutEntity = new Respuesta<SolicitudTarjetaRecargableOutEntity>();
        try {
            solicitudTarjetaRecargableOutEntity = tarjetaRecargableDAO
                    .altaSolicitudTarjetaRecargable(solicitudTarjetaRecargableInEntity);

            respuestaDTO.setEstadoRespuesta(solicitudTarjetaRecargableOutEntity.getEstadoRespuesta());
            respuestaDTO.setRespuesta(
                    new DatosSolicitudTarjetaRecargableDTO(solicitudTarjetaRecargableOutEntity.getRespuesta()));
            respuestaDTO.setRespuestaVacia(false);

            respuestaDTO = respuestaFactory.crearRespuestaOk(DatosSolicitudTarjetaRecargableDTO.class,
                    new DatosSolicitudTarjetaRecargableDTO(solicitudTarjetaRecargableOutEntity.getRespuesta()));
            estadisticaManager.add(EstadisticasConstants.ALTA_GESTION_SOLICITUD_TARJ_CREDITO_ADICIONAL,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

            LOGGER.info(LOGGER_OK, respuestaDTO);
            return respuestaDTO;
        } catch (DAOException e) {
            LOGGER.error(LOGGER_ERROR, e);
            Respuesta<DatosSolicitudTarjetaRecargableDTO> res = respuestaFactory.crearRespuestaError(StringUtils.EMPTY, TipoError.ERROR_GENERICO,
                    CodigoMensajeConstantes.ERROR_SOLICITUDES_TARJETA_RECARGABLE);
            res.setRespuesta(respuestaDTO.getRespuesta());
            return res;
            }
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.tarjetarecargable.bo.
     * TarjetaRecargableBO#comprobanteSolicitudTarjetaRecargable(ar.com.
     * santanderrio.obp.servicios.tarjetarecargable.web.view.
     * DatosComprobanteSolicitudTarjetaRecargableView)
     */
    @Override
    public Respuesta<Reporte> comprobanteSolicitudTarjetaRecargable(
            DatosComprobanteSolicitudTarjetaRecargableView datosComprobante) {
        Respuesta<Reporte> respuesta = new Respuesta<Reporte>();
        Reporte reporte = tarjetaRecargableDAO.comprobanteSolicitudTarjetaRecargable(datosComprobante);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(reporte);
        return respuesta;
    }

    /**
     * Construir parametro de entrada.
     *
     * @param datosSolicitudTarjetaRecargableInDTO
     *            the datos solicitud tarjeta recargable in DTO
     * @return the string
     */
    private String construirParametroDeEntrada(
            DatosSolicitudTarjetaRecargableInDTO datosSolicitudTarjetaRecargableInDTO) {

        StringBuilder comentario = new StringBuilder();
        comentario.append("Apellido y Nombre del Titular de la Cuenta: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getNombreCliente());
        comentario.append(", ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getApellido1Cliente() + NL);
        comentario.append("Número de Documento: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getNroDocumentoCliente() + NL);
        comentario.append("Apellido y Nombre del adicional: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getNombreAdic() + ESPACIO);
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getApellidoAdic() + NL);
        comentario.append("Número de documento del adicional: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getNroDocumentoAdic() + NL);
        comentario.append("Fecha de nacimiento del adicional: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getFechaNacimientoAdic() + NL);
        comentario.append("Nacionalidad del adicional: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getNacionalidad() + NL);
        comentario.append("Domicilio del adicional: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getDomicilio() + ESPACIO);
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getNro() + ESPACIO);
        comentario.append(StringUtils.defaultString(datosSolicitudTarjetaRecargableInDTO.getPisoDepto()) + ESPACIO);
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getCodigoPostal() + ESPACIO);
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getLocalidadBarrio() + ESPACIO);
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getProvincia() + NL);
        comentario.append("Teléfono del adicional: ");
        comentario.append(datosSolicitudTarjetaRecargableInDTO.getCodArea().trim()
                + datosSolicitudTarjetaRecargableInDTO.getTelefono().trim() + NL);

        StringBuilder spAtributos = new StringBuilder();
        spAtributos.append("|GCLI|HBAN|");
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getNupCliente() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getTipoDocumentoCliente() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getNroDocumentoCliente() + PIPE);
        spAtributos.append("F|");
        String apellido1Cliente = datosSolicitudTarjetaRecargableInDTO.getApellido1Cliente();
        spAtributos.append(apellido1Cliente.trim().length() == 0
                ? datosSolicitudTarjetaRecargableInDTO.getApellido2Cliente() : apellido1Cliente + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getNombreCliente() + PIPE);
        String fechaNacimientoCliente = datosSolicitudTarjetaRecargableInDTO.getFechaNacimientoCliente();
        spAtributos.append(fechaNacimientoCliente.substring(6));
        spAtributos.append(fechaNacimientoCliente.substring(4, 6));
        spAtributos.append(fechaNacimientoCliente.substring(0, 4) + PIPE);
        spAtributos.append("0000" + PIPE + "IND" + PIPE);
        spAtributos.append(ESPACIO + PIPE + "0000|" + "0000000000000000|" + ESPACIO + PIPE + "00|" + ESPACIO + PIPE
                + ESPACIO + PIPE);
        spAtributos.append("0" + PIPE + "000000" + PIPE + PIPE + ESPACIO + PIPE + "4" + PIPE);
        spAtributos.append(StringUtils.trim(datosSolicitudTarjetaRecargableInDTO.getCircuito()) + PIPE);
        spAtributos.append(comentario.toString() + PIPE);
        spAtributos.append(ESPACIO + PIPE + "HBAN0001|" + "00000000000000000000" + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getCodigoAplicacion() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getNroSucursal4() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getNroCuentaProducto() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getMonedaAltair() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getNroOrdenFirmante() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getProductoAltair() + PIPE);
        spAtributos.append(datosSolicitudTarjetaRecargableInDTO.getSubproductoAltair() + PIPE);

        datosSolicitudTarjetaRecargableInDTO.setComentario(comentario.toString());
        return spAtributos.toString();
    }

    /**
     * Crear lista item mensaje.
     *
     * @param solicitudTarjetaRecargableOutEntity
     *            the solicitud tarjeta recargable out entity
     * @return the list
     */
    private List<ItemMensajeRespuesta> crearListaItemMensaje(
            SolicitudTarjetaRecargableOutEntity solicitudTarjetaRecargableOutEntity) {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(new ItemMensajeRespuesta(solicitudTarjetaRecargableOutEntity != null
                && solicitudTarjetaRecargableOutEntity.getDescError() != null
                        ? solicitudTarjetaRecargableOutEntity.getDescError() : LOGGER_ERROR));
        items.get(0).setTag(StringUtils.EMPTY);
        items.get(0).setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        return items;
    }

}
