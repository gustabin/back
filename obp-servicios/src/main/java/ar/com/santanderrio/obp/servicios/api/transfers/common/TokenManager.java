package ar.com.santanderrio.obp.servicios.api.transfers.common;

import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.servicios.api.transfers.common.model.Token;

import java.io.UnsupportedEncodingException;

public interface TokenManager {

    Token getToken(String tokenProviderUrl, Credential credential) throws UnsupportedEncodingException;

}
