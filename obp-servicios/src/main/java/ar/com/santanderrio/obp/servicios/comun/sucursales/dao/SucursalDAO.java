/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.dao;

import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sucursales.dao.impl.SucursalOutEntity;

/**
 * Consultar a iatx para obtener las sucursales.
 * 
 * @author sergio.e.goldentair
 *
 */
public interface SucursalDAO {
    /**
     * Obtener las sucursales actualizadas.
     * 
     * @param resumenCliente
     * @return
     * @throws DAOException
     */
    SucursalOutEntity cnsSucursales(ResumenCliente resumenCliente) throws DAOException;
}
