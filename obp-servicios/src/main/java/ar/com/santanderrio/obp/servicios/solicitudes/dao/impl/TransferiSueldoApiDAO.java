package ar.com.santanderrio.obp.servicios.solicitudes.dao.impl;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoRequestDTO;
import ar.com.santanderrio.obp.servicios.solicitudes.dto.TransferiSueldoResponseDTO;

public interface TransferiSueldoApiDAO {
    TransferiSueldoResponseDTO transferiSueldo(TransferiSueldoRequestDTO transferiSueldoRequestDTO) throws DAOException;
}
