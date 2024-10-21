package ar.com.santanderrio.obp.servicios.debinapi.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListDTO;
import ar.com.santanderrio.obp.servicios.debinapi.dto.DebinListRequest;

public interface DebinApiDAO {
    /**
     * Get debins
     *
     * @param debinListRequest
     * @return the list
     * @throws Exception
     */

    DebinListDTO getDebinList(DebinListRequest debinListRequest) throws DAOException;


}
