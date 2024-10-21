package ar.com.santanderrio.obp.servicios.api.common.utils;

import ar.com.santanderrio.obp.servicios.api.common.config.ErrorResponse;
import ar.com.santanderrio.obp.servicios.api.common.config.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestClientException;
import java.util.List;
import java.io.IOException;

public class CommonApiErrorResponseHandler extends DefaultResponseErrorHandler {

    private final List<HttpMessageConverter<?>> messageConverters;
    private final Logger logs = LoggerFactory.getLogger(CommonApiErrorResponseHandler.class);

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpMessageConverterExtractor<ErrorResponse> msgExtractorError =
                new HttpMessageConverterExtractor<ErrorResponse>(ErrorResponse.class, messageConverters);
        ErrorResponse errorResp;

        try {
            errorResp = msgExtractorError.extractData(response);
            if(errorResp == null || (errorResp.getMessage() == null && errorResp.getCode() == null )) {
                errorResp = ErrorResponse.emptyErrorResponse();
            }
        } catch (HttpMessageNotReadableException e) {
            logs.error("Error reading entity", e);
            errorResp = ErrorResponse.emptyErrorResponse();
        } catch (RestClientException e) {
            logs.error("Error reading entity", e);
            errorResp = ErrorResponse.emptyErrorResponse();
        }

        throw new ApiException(errorResp, response.getHeaders(), response.getStatusCode());
    }

    public CommonApiErrorResponseHandler(List<HttpMessageConverter<?>> messageConverters) {
        this.messageConverters = messageConverters;
    }
}
