package ar.com.santanderrio.obp.servicios.afip.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;

public interface AfipWSDAO {
    Boolean consultaTieneDeuda(String cuit) throws DAOException;
}
