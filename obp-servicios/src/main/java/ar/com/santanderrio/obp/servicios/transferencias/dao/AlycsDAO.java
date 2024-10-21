package ar.com.santanderrio.obp.servicios.transferencias.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import java.util.List;

public interface AlycsDAO {

    List<String> getCuitsAlycs() throws DAOException;

    void cleanCacheCuitsAlycs();
}
