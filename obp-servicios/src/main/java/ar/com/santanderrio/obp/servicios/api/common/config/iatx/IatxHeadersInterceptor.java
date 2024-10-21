package ar.com.santanderrio.obp.servicios.api.common.config.iatx;

import ar.com.santanderrio.obp.servicios.oauth.config.APIcConfigConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

public class IatxHeadersInterceptor implements ClientHttpRequestInterceptor {

    private final IatxObpContext iatxObpContext;

    public IatxHeadersInterceptor(IatxObpContext iatxObpContext) {
        super();
        this.iatxObpContext = iatxObpContext;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        HttpHeaders headers = request.getHeaders();
        headers.set(APIcConfigConstants.SAN_IATX_USER_ID, iatxObpContext.getIatxUser());
        headers.set(APIcConfigConstants.SAN_IATX_USER_PASS, iatxObpContext.getIatxPassword());
        headers.set(APIcConfigConstants.SAN_IATX_CHANNEL_ID, iatxObpContext.getIatxChannelId());
        headers.set(APIcConfigConstants.SAN_IATX_CHANNEL_TYPE, iatxObpContext.getIatxChannelType());
        headers.set(APIcConfigConstants.SAN_IATX_SUBCHANNEL_ID, iatxObpContext.getSubChannelId());
        headers.set(APIcConfigConstants.SAN_IATX_SUBCHANNEL_TYPE, iatxObpContext.getSubChannelType());
        return execution.execute(request, body);
    }
}
