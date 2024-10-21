package ar.com.santanderrio.obp.servicios.api.utils;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BufferedMockClientHttpResponse extends MockClientHttpResponse {
    private byte[] bodyBytes;

    public BufferedMockClientHttpResponse(ClientHttpResponse response) throws IOException {
        super(response.getBody(), response.getStatusCode());
        this.getHeaders().putAll(response.getHeaders());
    }

    @Override
    public InputStream getBody() throws IOException {
        if (bodyBytes == null) {
            bodyBytes = StreamUtils.copyToByteArray(super.getBody());
        }
        return new ByteArrayInputStream(bodyBytes);
    }
}
