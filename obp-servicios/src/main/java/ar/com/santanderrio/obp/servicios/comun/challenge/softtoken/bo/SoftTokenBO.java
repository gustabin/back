/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo;

import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Interface SoftTokenBO.
 */
public interface SoftTokenBO extends BusinessObject {

    /**
	 * Consulta si el cliente tiene habilitado el método de validación por
	 * SoftToken.
	 *
	 * @return the respuesta
	 * @last_updated: b039542 - ignacio_valek@itrsa.com.ar - 20/09/2016
	 * @last_updated: retirar estadistica - 08/08/2018
	 */
    Respuesta<AutentificacionDTO> tieneSoftTokenHabilitado();

    /**
     * Validar soft token.
     *
     * @param autentificacionDTO
     *            the autentificacion DTO
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @return the respuesta
     * @last_updated: b039542 - ignacio_valek@itrsa.com.ar - 20/09/2016
     */
    Respuesta<AutentificacionDTO> validarSoftToken(AutentificacionDTO autentificacionDTO,
            String codigoEstadisticaValidacion);

    /**
     * Tiene soft token.
     *
     * @return true, if successful
     */
    boolean tieneSoftToken();
}
