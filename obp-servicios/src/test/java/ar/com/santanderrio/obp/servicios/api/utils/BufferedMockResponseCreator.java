package ar.com.santanderrio.obp.servicios.api.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.web.client.response.DefaultResponseCreator;

import java.io.IOException;

public class BufferedMockResponseCreator extends DefaultResponseCreator {
    public BufferedMockResponseCreator(HttpStatus statusCode) {
        super(statusCode);
    }

    @Override
    public ClientHttpResponse createResponse(ClientHttpRequest request) throws IOException {
        ClientHttpResponse response = super.createResponse(request);
        return new BufferedMockClientHttpResponse(response);
    }
}
