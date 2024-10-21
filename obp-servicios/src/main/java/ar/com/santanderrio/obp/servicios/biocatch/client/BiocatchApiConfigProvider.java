package ar.com.santanderrio.obp.servicios.biocatch.client;

import ar.com.santanderrio.obp.servicios.api.common.config.ApiConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Qualifier("biocatchApiConfig")
public class BiocatchApiConfigProvider implements ApiConfig {

    @Value("${BIOCATCH-API.PATH.BASE}")
    private String biocatchBasePath;

    @Value("${BIOCATCH-API.SCOPE.V2}")
    private String scope;

    @Value("${BIOCATCH-API.READ.TIMEOUT}")
    private int readTimeout;

    @Value("${BIOCATCH-API.CONNECT.TIMEOUT}")
    private int connectTimeout;

    @Override
    public String getBasePath() {
        return biocatchBasePath;
    }

    @Override
    public String getScope() {
        return scope;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }
}
