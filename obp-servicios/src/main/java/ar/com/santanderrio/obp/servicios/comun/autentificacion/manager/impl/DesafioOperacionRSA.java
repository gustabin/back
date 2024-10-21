/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl;

import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.comun.OperacionesRSAEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionCodEstDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.CoordenadasOperacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.RsaDTOParaDesafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.PedidoCoordenada;
import ar.com.santanderrio.obp.servicios.tarjetacoordenadas.entities.TarjetaCoordenadaOperacionEnum;

/**
 * @author sergio.e.goldentair
 *
 */
@Component
public class DesafioOperacionRSA<T extends RsaDTOParaDesafio> {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(DesafioOperacionRSA.class);

    /** The sesion parametros. */
    @Autowired
    private SesionParametros sesionParametros;

    /** The autentificacion manager. */
    @Autowired
    private AutentificacionManager autentificacionManager;

    /**
     * @param view
     * @return
     */
    public Respuesta<T> validarOperacionRSA(T view, Integer valorDesafio, AutentificacionCodEstDTO codigoEstadistica) {
        AutentificacionDTO autentificacionDTO;
        LOGGER.info("Validando operación de RSA... ");
        Respuesta<T> estadoDesafio = autentificacionManager.verificarEstadoDesafio(view.getDesafio(), valorDesafio);
        if (EstadoRespuesta.OK.equals(estadoDesafio.getEstadoRespuesta())) {
            LOGGER.info("EstadoRespuesta.OK....Segunda fase: luego de ingresar desafio");
            autentificacionDTO = view.getDesafio();
        } else if (EstadoRespuesta.WARNING.equals(estadoDesafio.getEstadoRespuesta())) {
            LOGGER.info("EstadoRespuesta.WARNING....Primera fase: antes de invocar a RSA Analyze");
            autentificacionDTO = cargarAutentificacionDTO(view, valorDesafio, codigoEstadistica);
        } else {
            LOGGER.info("validarOperacionRSA ... return estadoDesafio");
            return estadoDesafio;
        }

        Respuesta<T> respuesta = new Respuesta<T>();
        LOGGER.info("validarOperacionRSA ->>  ejecutarValidacionRSA previo a notify");
        Respuesta<AutentificacionDTO> respEjecucionMetodoAutentificacion = autentificacionManager
                .ejecutarValidacionRSA(autentificacionDTO);
        view.setDesafio(respEjecucionMetodoAutentificacion.getRespuesta());
        respuesta.setEstadoRespuesta(respEjecucionMetodoAutentificacion.getEstadoRespuesta());
        respuesta.setItemMensajeRespuesta(respEjecucionMetodoAutentificacion.getItemsMensajeRespuesta());
        respuesta.setRespuesta(view);
        return respuesta;
    }

    /**
     * @param view
     * @param valorDesafio
     * @param codigoEstadistica
     * @return
     */
    public AutentificacionDTO cargarAutentificacionDTO(T view, Integer valorDesafio,
            AutentificacionCodEstDTO codigoEstadistica) {
        AutentificacionDTO autentificacionDTO = new AutentificacionDTO();
        autentificacionDTO.setOperacion(valorDesafio);

        if (view.getDesafio() != null) {
            autentificacionDTO = view.getDesafio();
        }

        asignarEstadisticasDeAutenticacion(autentificacionDTO, codigoEstadistica);
        PedidoCoordenada pedidoCoordenada = new PedidoCoordenada(TarjetaCoordenadaOperacionEnum.VALIDACION);
        pedidoCoordenada.setIp("192168001001");
        if (autentificacionDTO.getCoordenadasOperacion() != null) {
            autentificacionDTO.getCoordenadasOperacion().setPedidoCoordenada(pedidoCoordenada);
        } else {
            CoordenadasOperacionDTO coordenadasOperacionDTO = new CoordenadasOperacionDTO();
            coordenadasOperacionDTO.setPedidoCoordenada(pedidoCoordenada);
            autentificacionDTO.setCoordenadasOperacion(coordenadasOperacionDTO);
        }
        autentificacionDTO.setRsaDTO(view);
//        if (OperacionesRSAEnum.SOLICITUD_TARJ_RECARGABLE.equals(view.getTipoOperacion())) {
//        	autentificacionDTO.setEvitarRsa(true);
//        }
        return autentificacionDTO;
    }

    /**
     * Modificar AutentificacionDTO para que en lugar de tener los atributos sueltos
     * tenga una propiedad de AutCodigoEstadisticaDTO.
     * 
     * @param autentificacionDTO
     * @param codigoEstadistica
     */
    private void asignarEstadisticasDeAutenticacion(AutentificacionDTO autentificacionDTO,
            AutentificacionCodEstDTO codigoEstadistica) {
        if (codigoEstadistica != null) {
            autentificacionDTO
                    .setCodigoEstadisticaSolicitudToken(codigoEstadistica.getCodigoEstadisticaSolicitudToken());
            autentificacionDTO
                    .setCodigoEstadisticaValidacionToken(codigoEstadistica.getCodigoEstadisticaValidacionToken());
            autentificacionDTO.setCodigoEstadisticaSolicitudCoordenadas(
                    codigoEstadistica.getCodigoEstadisticaSolicitudCoordenadas());
            autentificacionDTO.setCodigoEstadisticaValidacionCoordenadas(
                    codigoEstadistica.getCodigoEstadisticaValidacionCoordenadas());
            autentificacionDTO
                    .setCodigoEstadisticaSolicitudBanelco(codigoEstadistica.getCodigoEstadisticaSolicitudBanelco());
            autentificacionDTO
                    .setCodigoEstadisticaValidacionBanelco(codigoEstadistica.getCodigoEstadisticaValidacionBanelco());
            autentificacionDTO.setCodigoEstadisticaSinDesafios(codigoEstadistica.getCodigoEstadisticaSinDesafios());
        } else {
            LOGGER.info("No se dispone de estadisticas para la invocacion.");
        }
    }

}
