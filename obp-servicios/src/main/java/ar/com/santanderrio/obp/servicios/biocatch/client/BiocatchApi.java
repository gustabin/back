package ar.com.santanderrio.obp.servicios.biocatch.client;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BaseRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.GetScoreRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.InitRequestDataDTO;
import org.springframework.stereotype.Component;

@Component
public interface BiocatchApi {

    BiocatchResponseDataDTO getScore(GetScoreRequestDataDTO request) throws ApiException;
    BiocatchResponseDataDTO getScoreLogin(BaseRequestDataDTO request) throws ApiException;
    Void init(InitRequestDataDTO request) throws ApiException;
}
