package ar.com.santanderrio.obp.servicios.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {
    @Value("${PROXY.REQUIRED:false}")
    private Boolean proxyRequired;

    @Value("${PROXY.ADDRESS}")
    private String proxyAddress;

    @Value("${PROXY.PORT:8080}")
    private Integer proxyPort;

    public Boolean getProxyRequired() {
        return proxyRequired;
    }

    public String getProxyAddress() {
        return proxyAddress;
    }

    public Integer getProxyPort() {
        return proxyPort;
    }
}
