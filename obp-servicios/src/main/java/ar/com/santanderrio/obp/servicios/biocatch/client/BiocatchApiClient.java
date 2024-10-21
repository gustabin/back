package ar.com.santanderrio.obp.servicios.biocatch.client;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BaseRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.BiocatchResponseDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.GetScoreRequestDataDTO;
import ar.com.santanderrio.obp.servicios.biocatch.dto.InitRequestDataDTO;
import ar.com.santanderrio.obp.servicios.oauth2.OAuth2RestTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class BiocatchApiClient implements BiocatchApi  {

    @Value("${BIOCATCH-API.PATH.GETSCORE}")
    private String getScorePath;

    @Value("${BIOCATCH-API.PATH.GETSCORELOGIN}")
    private String getScoreLoginPath;

    @Value("${BIOCATCH-API.PATH.INIT}")
    private String initPath;

    private final OAuth2RestTemplate restTemplate;
    private final URI baseUrl;
    
    protected BiocatchApiClient(URI baseUrl, OAuth2RestTemplate restTemplate) {
      this.restTemplate = restTemplate;
      this.baseUrl = baseUrl;
    }

    @Override
    public BiocatchResponseDataDTO getScore(GetScoreRequestDataDTO requestDataDTO) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(this.baseUrl.toString() + getScorePath)
                .buildAndExpand().toUri();

        RequestEntity<GetScoreRequestDataDTO> getAdditionalDataRequest = RequestEntity.post(requestUri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(requestDataDTO);

        return this.execute(getAdditionalDataRequest, BiocatchResponseDataDTO.class);
    }

    @Override
    public BiocatchResponseDataDTO getScoreLogin(BaseRequestDataDTO requestDataDTO) throws ApiException {
        URI requestUri = UriComponentsBuilder
                .fromUriString(this.baseUrl.toString() + getScoreLoginPath)
                .buildAndExpand().toUri();

        RequestEntity<BaseRequestDataDTO> getAdditionalDataRequest = RequestEntity.post(requestUri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(requestDataDTO);

        return this.execute(getAdditionalDataRequest, BiocatchResponseDataDTO.class);
    }

    @Override
    public Void init(InitRequestDataDTO requestDataDTO){
        URI requestUri = UriComponentsBuilder
                .fromUriString(this.baseUrl.toString() + initPath)
                .buildAndExpand().toUri();

        RequestEntity<InitRequestDataDTO> getAdditionalDataRequest = RequestEntity.post(requestUri).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(requestDataDTO);

        return this.execute(getAdditionalDataRequest, Void.class);
    }


    private <T> T execute (RequestEntity<?> request, Class<T> responseClass) throws ApiException {
        try {
            return this.restTemplate.exchange(request, responseClass).getBody();
        } catch (ResourceAccessException ioException) {
            throw new ApiException(new ErrorResponse().code("ERROR").message(ioException.getMessage()));
        } catch (HttpClientErrorException ex) {
            throw new ApiException(new ErrorResponse().code(ex.getStatusCode().name()).message(ex.getMessage()));
        } catch (HttpServerErrorException ex) {
            throw new ApiException(new ErrorResponse().code(ex.getStatusCode().name()).message(ex.getMessage()));
        }catch (Exception e){
            throw new ApiException(ErrorResponse.emptyErrorResponse());
        }
    }
}
