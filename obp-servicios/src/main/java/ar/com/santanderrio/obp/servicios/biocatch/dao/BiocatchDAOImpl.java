package ar.com.santanderrio.obp.servicios.biocatch.dao;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BaseRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.client.BiocatchApi;
import ar.com.santanderrio.obp.servicios.biocatch.dto.GetScoreRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.InitRequestDataDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BiocatchDAOImpl implements BiocatchDAO {

    @Autowired
    private BiocatchApi biocatchApiClient;

    public BiocatchResponseDataDTO getScore(GetScoreRequestDataDTO biocatchRequestDataDTO) throws DAOException {
        try {
            return biocatchApiClient.getScore(biocatchRequestDataDTO);
        } catch (ApiException e) {
            throw new DAOException(e.getErrorResponse().getMessage());
        }
    }

    public BiocatchResponseDataDTO getScoreLogin(BaseRequestDataDTO biocatchRequestDataDTO) throws DAOException {
        try {
            return biocatchApiClient.getScoreLogin(biocatchRequestDataDTO);
        } catch (ApiException e) {
            throw new DAOException(e.getErrorResponse().getMessage());
        }
    }

    public Void init(InitRequestDataDTO biocatchRequestDataDTO) throws DAOException {
        try {
            return biocatchApiClient.init(biocatchRequestDataDTO);
        } catch (ApiException e) {
            throw new DAOException(e.getErrorResponse().getMessage());
        }
    }

}
