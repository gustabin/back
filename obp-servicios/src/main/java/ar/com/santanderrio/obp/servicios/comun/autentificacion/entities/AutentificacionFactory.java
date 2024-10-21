/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.context.ContextHolder;

/**
 * Esta clase permite la creación de los metodos de autentificacion.
 *
 * @author ignacio.valek
 * @author emilio.watemberg
 * @since Sep 26, 2016.
 */
@Component
public class AutentificacionFactory {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AutentificacionFactory.class);

    /**
     * Crear autentificacion.
     *
     * @param clazz
     *            the clazz
     * @return the autentificacion
     */
    public Desafio<AutentificacionDTO> crearAutentificacion(Class<? extends Desafio> clazz) {
        return crearAutentificacion(clazz, 1);
    }

    /**
     * Crear autentificacion.
     *
     * @param clazz
     *            the clazz
     * @param prioridad
     *            the prioridad
     * @return the autentificacion
     */
    public Desafio<AutentificacionDTO> crearAutentificacion(Class<? extends Desafio> clazz, Integer prioridad) {
        Desafio autentificacion = ContextHolder.getContext().getBean(clazz);
        autentificacion.setPrioridad(prioridad);
        return autentificacion;
    }

    /**
     * Crear autentificacion.
     *
     * @param clazz
     *            the clazz
     * @param prioridad
     *            the prioridad
     * @param codigoEstadisticaSolicitud
     *            the codigo estadistica solicitud
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the autentificacion
     */
    public Desafio<AutentificacionDTO> crearAutentificacion(Class<? extends Desafio> clazz, Integer prioridad,
            String codigoEstadisticaSolicitud, String codigoEstadisticaValidacion,
            AutentificacionDTO autentificacionDTO) {
        Desafio autentificacion = ContextHolder.getContext().getBean(clazz);
        autentificacion.setPrioridad(prioridad);
        autentificacion.setCodigoEstadisticaSolicitud(codigoEstadisticaSolicitud);
        autentificacion.setCodigoEstadisticaValidacion(codigoEstadisticaValidacion);
        autentificacion.setAutentificacionDTO(autentificacionDTO);
        return autentificacion;
    }

    /**
     * Crear autentificacion.
     *
     * @param clazz
     *            the clazz
     * @param prioridad
     *            the prioridad
     * @param codigoEstadisticaSolicitud
     *            the codigo estadistica solicitud
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @param verificandoSiHayDesafios
     *            the verificando si hay desafios
     * @return the autentificacion
     */
    public Desafio<AutentificacionDTO> crearAutentificacion(Class<? extends Desafio> clazz, Integer prioridad,
            String codigoEstadisticaSolicitud, String codigoEstadisticaValidacion,
            AutentificacionDTO autentificacionDTO, boolean verificandoSiHayDesafios) {
        Desafio autentificacion = ContextHolder.getContext().getBean(clazz);
        autentificacion.setPrioridad(prioridad);
        autentificacion.setCodigoEstadisticaSolicitud(codigoEstadisticaSolicitud);
        autentificacion.setCodigoEstadisticaValidacion(codigoEstadisticaValidacion);
        autentificacion.setVerificandoSiHayDesafios(verificandoSiHayDesafios);
        autentificacion.setAutentificacionDTO(autentificacionDTO);
        return autentificacion;
    }

}
