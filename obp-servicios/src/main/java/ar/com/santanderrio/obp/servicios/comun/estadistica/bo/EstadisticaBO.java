/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.estadistica.bo;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.bo.BusinessObject;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;

/**
 * The Interface EstadisticaBO.
 */
public interface EstadisticaBO extends BusinessObject {

    /**
     * Adds the.
     *
     * @param estadistica
     *            the estadistica
     * @param registroSesion
     *            the registro sesion
     * @param cliente
     *            the cliente
     * @return the estadistica
     * @throws BusinessException
     *             the business exception
     */
    Estadistica add(Estadistica estadistica, RegistroSesion registroSesion, Cliente cliente) throws BusinessException;

}
