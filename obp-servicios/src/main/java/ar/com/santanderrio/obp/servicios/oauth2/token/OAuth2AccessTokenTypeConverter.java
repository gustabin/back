package ar.com.santanderrio.obp.servicios.oauth2.token;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;

public class OAuth2AccessTokenTypeConverter implements JsonDeserializer<OAuth2AccessToken> {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String EXPIRES_IN = "expires_in";
    private static final String TOKEN_TYPE = "token_type";
    private static final String SCOPE_RESPONSE = "scope_response";
    private static final String SCOPE = "scope";
    private static final String REFRESH_TOKEN = "refresh_token";
    private static final String REFRESH_EXPIRES_IN = "refresh_expires_in";

    @Override
    public OAuth2AccessToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();
        String accessToken = jsonObject.get(ACCESS_TOKEN).getAsString();
        String tokenType = jsonObject.get(TOKEN_TYPE).getAsString();
        String scope = jsonObject.has(SCOPE_RESPONSE)
                ? jsonObject.get(SCOPE_RESPONSE).getAsString()
                : jsonObject.get(SCOPE).getAsString();
        long expiresIn = jsonObject.get(EXPIRES_IN).getAsLong();
        String refreshTokenValue = "";
        if(jsonObject.has(REFRESH_TOKEN)) {
            refreshTokenValue = jsonObject.get(REFRESH_TOKEN).getAsString();
        }
        long refreshExpires = jsonObject.get(REFRESH_EXPIRES_IN).getAsLong();

        OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken();
        oAuth2AccessToken.setAccessToken(accessToken);
        oAuth2AccessToken.setTokenType(tokenType);
        oAuth2AccessToken.setScope(scope);
        oAuth2AccessToken.setExpiration(new Date(System.currentTimeMillis() + (expiresIn * 1000)));
        OAuth2RefreshToken refreshToken = new OAuth2RefreshToken();
        refreshToken.setRefreshToken(refreshTokenValue);
        refreshToken.setExpiration(new Date(System.currentTimeMillis() + (refreshExpires * 1000)));
        oAuth2AccessToken.setRefreshToken(refreshToken);
        return oAuth2AccessToken;
    }
}
