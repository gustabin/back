package ar.com.santanderrio.obp.servicios.api.transfers.common;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;

import java.io.UnsupportedEncodingException;

public interface TokenProvider {

    Token getToken(String url, Credential credential) throws UnsupportedEncodingException;

    void evictTokenCache(Credential credential);

}
