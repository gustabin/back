package ar.com.santanderrio.obp.servicios.debinrecurrente.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.debinrecurrente.dto.BuyerRecurrenceListRequestDTO;

public interface DebinRecurrenteApiDAO {

    /**
     * Get recurrences
     *
     * @param cbu
     * @param formattedCuit
     * @param pageNumber
     * @return the list
     * @throws Exception
     */

    BuyerRecurrenceListRequestDTO getRecurrenceList(String cbu, String formattedCuit, String pageNumber) throws DAOException;
}
