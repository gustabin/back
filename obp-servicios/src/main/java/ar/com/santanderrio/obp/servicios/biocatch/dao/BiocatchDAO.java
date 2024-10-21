package ar.com.santanderrio.obp.servicios.biocatch.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BaseRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.GetScoreRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.InitRequestDataDTO;

public interface BiocatchDAO {
    BiocatchResponseDataDTO getScore(GetScoreRequestDataDTO biocatchRequestDataDTO) throws DAOException;
    BiocatchResponseDataDTO getScoreLogin(BaseRequestDataDTO biocatchRequestDataDTO) throws DAOException;
    Void init(InitRequestDataDTO initRequestDataDTO) throws DAOException;
}
