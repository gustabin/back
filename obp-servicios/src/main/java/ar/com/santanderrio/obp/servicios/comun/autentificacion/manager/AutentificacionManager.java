/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.autentificacion.manager;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.challenge.entities.TipoDesafioEnum;

/**
 * Clase que utiliza el patrón visitor {@link DesafioVisitor} para exteder el
 * comportamiento de autentificacion entre todos los componentes existentes. Se
 * encarga de exponer las operaciones necesarias para obtener un metodo de
 * autentificacion y ejecutarlo.
 *
 * @author ignacio.valek
 * @author emilio.watemberg
 * @see {@link DesafioVisitor}
 * @since Sep 22, 2016.
 */
public interface AutentificacionManager {

    /**
     * Analizar operacion de riesgo.
     *
     * @param auntentificacionDTO
     *            the auntentificacion DTO
     * @return OK si no necesita desafio, ERROR si se denega la ejecución, y WARNING
     *         si debe presentar desafio
     */
    Respuesta<AutentificacionDTO> analizarRSAyObtenerAutenticacionValida(AutentificacionDTO auntentificacionDTO);

    /**
     * Evaluar desafio operacion de riesgo. Utilizar el componente
     * {@link ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.impl.DesafioOperacionRSA}
     * gestinar de forma unica las consulta y validaciones de token mobile.
     *
     * @param desafioDto
     *            the desafio dto
     * @return the respuesta
     */
    @Deprecated
    Respuesta<AutentificacionDTO> ejecutarMetodoAutenticacionNotificandoRSA(AutentificacionDTO desafioDto);

    /**
     * Obtener metodo de autentificacion.
     *
     * @param dto
     *            the dto
     * @return the desafio
     */
    Respuesta<AutentificacionDTO> obtenerAutentificacionValidaParaCliente(AutentificacionDTO dto);

    /**
     * Obtener metodo de autentificacion.
     *
     * @param dto
     *            the dto
     * @param codigoEstadisticaSolicitud
     *            the codigo estadistica solicitud
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @return the desafio
     */
    Respuesta<AutentificacionDTO> obtenerAutentificacionValidaParaCliente(AutentificacionDTO dto,
            String codigoEstadisticaSolicitud, String codigoEstadisticaValidacion);

    /**
     * Obtener desafio habilitado operacion.
     *
     * @param autentificacionDTO the autentificacion DTO
     * @return the tipo desafio enum
     */
    TipoDesafioEnum obtenerDesafioHabilitadoOperacion(AutentificacionDTO autentificacionDTO);

    /**
     * Ejecutar metodo autentificacion.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the desafio
     */
    Respuesta<AutentificacionDTO> ejecutarMetodoAutentificacion(AutentificacionDTO autentificacionDTO);

    /**
     * Sets the codigo estadistica solicitud coordenadas.
     *
     * @param codigoEstadisticaSolicitudCoordenadas
     *            the new codigo estadistica solicitud coordenadas
     */
    void setCodigoEstadisticaSolicitudCoordenadas(String codigoEstadisticaSolicitudCoordenadas);

    /**
     * Sets the codigo estadistica validacion coordenadas.
     *
     * @param codigoEstadisticaValidacionCoordenadas
     *            the new codigo estadistica validacion coordenadas
     */
    void setCodigoEstadisticaValidacionCoordenadas(String codigoEstadisticaValidacionCoordenadas);

    /**
     * Sets the codigo estadistica solicitud token.
     *
     * @param codigoEstadisticaSolicitudToken
     *            the new codigo estadistica solicitud token
     */
    void setCodigoEstadisticaSolicitudToken(String codigoEstadisticaSolicitudToken);

    /**
     * Sets the codigo estadistica validacion token.
     *
     * @param codigoEstadisticaValidacionToken
     *            the new codigo estadistica validacion token
     */
    void setCodigoEstadisticaValidacionToken(String codigoEstadisticaValidacionToken);

    /**
     * Sets the codigo estadistica solicitud banelco.
     *
     * @param codigoEstadisticaSolicitudBanelco
     *            the new codigo estadistica solicitud banelco
     */
    void setCodigoEstadisticaSolicitudBanelco(String codigoEstadisticaSolicitudBanelco);

    /**
     * Sets the codigo estadistica validacion banelco.
     *
     * @param codigoEstadisticaValidacionBanelco
     *            the new codigo estadistica validacion banelco
     */
    void setCodigoEstadisticaValidacionBanelco(String codigoEstadisticaValidacionBanelco);

    /**
     * Sets the codigo estadistica solicitud cvv2.
     *
     * @param codigoEstadisticaSolicitudCvv2
     *            the new codigo estadistica solicitud cvv2
     */
    void setCodigoEstadisticaSolicitudCvv2(String codigoEstadisticaSolicitudCvv2);

    /**
     * Sets the codigo estadistica validacion cvv2.
     *
     * @param codigoEstadisticaValidacionCvv2
     *            the new codigo estadistica validacion cvv2
     */
    void setCodigoEstadisticaValidacionCvv2(String codigoEstadisticaValidacionCvv2);

    /**
     * Este metodo permite consultar si el cliente tiene algun desafio habilitado
     * para la operacion solicitada.
     * 
     * Tipo de validacion por operacion: 0 - Solo Coordenadas 1 - Coordenadas sino 8
     * digitos 2 - Token sino Coordenadas 3 - Token sino Coordenadas sino 8 digitos
     * 4 - Token sino Coordenadas sino 8 digitos sino CVV2
     *
     * @author emilio.watemberg
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return {@link TipoDesafioEnum}
     * @since Jul 19, 2017
     */
    Respuesta<Boolean> tieneAlgunDesafioHabilitadoParaLaOperacion(AutentificacionDTO autentificacionDTO);

    /**
     * Ejecuta la validacion de RSA. Si tiene un desafio en curso, obtiene la
     * respuesta del mismo. Caso contrario, analizar RSA para obtener un desafio.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return the respuesta
     */
    Respuesta<AutentificacionDTO> ejecutarValidacionRSA(AutentificacionDTO autentificacionDTO);

    /**
     * Necesita nuevo desafio.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @return true, if successful
     */
    boolean necesitaNuevoDesafio(AutentificacionDTO autentificacionDTO);

    /**
     * Tiene algun desafio habilitado para la operacion.
     *
     * @param operacion
     *            the operacion
     * @return true, if successful
     */
    boolean tieneAlgunDesafioHabilitadoParaLaOperacion(int operacion);

    /**
     * Verificar estado desafio.
     *
     * @param <T>
     *            the generic type
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @param operacion
     *            the operacion
     * @return the respuesta
     */
    <T> Respuesta<T> verificarEstadoDesafio(AutentificacionDTO autentificacionDTO, int operacion);

}