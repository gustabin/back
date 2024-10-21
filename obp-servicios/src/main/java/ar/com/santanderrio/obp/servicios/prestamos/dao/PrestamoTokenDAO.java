package ar.com.santanderrio.obp.servicios.prestamos.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;

public interface PrestamoTokenDAO {

    /**
     * Obtiene el token de un cliente.
     *
     * @return El token valido
     * @throws DAOException
     *             the DAO exception
     */
    String getToken();

}
