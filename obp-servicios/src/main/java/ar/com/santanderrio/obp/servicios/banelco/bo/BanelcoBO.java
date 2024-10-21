/*
 * 
 */
package ar.com.santanderrio.obp.servicios.banelco.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;

/**
 * The Interface BanelcoBO.
 */
public interface BanelcoBO {

    /**
     * Valida si los 8 digitos ingrasados por el usuario corresponden a la tarjeta
     * de debito que tienen asociada.
     *
     * @param numTarjeta
     *            nros. ingresados por el cliente
     * @return the respuesta
     * @throws BusinessException
     *             the business exception
     */
    Respuesta<Boolean> validarTarjetaDebito(String numTarjeta) throws BusinessException;

    /**
     * Obtiene ultimos diguitos de tarjeta banelco.
     *
     * @return the respuesta
     */
    Respuesta<AutentificacionDTO> obtenerUltimosDigitosValidacion();

    /**
     * Validar ocho digitos.
     *
     * @param auntentificacionDTO
     *            the auntentificacion DTO
     * @param codigoEstadisticaValidacion
     *            the codigo estadistica validacion
     * @return the respuesta
     */
    Respuesta<AutentificacionDTO> validarOchoDigitos(AutentificacionDTO auntentificacionDTO,
            String codigoEstadisticaValidacion);

}