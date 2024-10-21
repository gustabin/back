/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sucursales.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursales;

/**
 * The Interface ConsultarSucursalesDAO.
 */
public interface ConsultarSucursalesDAO {

    /**
     * Busca una sucursal por numero de identificacion.
     *
     * @param id
     *            debe tener 4 caracteres, completar con 0 (cero) a la izquierda
     * @return sucursal, o null si no existe
     * @throws DAOException
     *             si hubo error
     */
    Sucursal consultarSucursalPorId(String id) throws DAOException;

    /**
     * Actualizar entidades y escribir el archivo, evaluando la existencia de un pid
     * file.
     * 
     * @param sucursales
     * @throws DAOException
     */
    void actualizarSucursales(Sucursales sucursales) throws DAOException;

}
