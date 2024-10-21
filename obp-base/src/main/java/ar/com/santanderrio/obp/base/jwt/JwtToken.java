/*
 * 
 */
package ar.com.santanderrio.obp.base.jwt;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.com.santanderrio.obp.base.comun.NetworkUtil;
import ar.com.santanderrio.obp.base.context.ContextHolder;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTSigner.Options;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;

// TODO: Auto-generated Javadoc
/**
 * Utility class for handling JWT tokens.
 * 
 * @author pablo.martin.gore
 */
public class JwtToken {
    private static final String JWT_NUP_NO_VALIDO = "El nup de la sesion no se corresponde con el nup del JWT.";

	/** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtToken.class);

    /** The Constant AUTHORIZATION_HEADER. */
    public static final String AUTHORIZATION_HEADER = "Authorization";

    /** The Constant HEADER_PREFIX. */
    private static final String HEADER_PREFIX = "Bearer ";

    /** The token. */
    private String token;

    /**
     * Constructor para validar el token recibido.
     *
     * @param token
     *            the token
     */
    public JwtToken(String token) {
        this.token = token;
    }

    /**
     * Crear un nuevo token.
     *
     * @param expirySeconds
     *            the expiry seconds
     * @param secret
     *            the secret
     * @param nup
     *            the nup
     * @param dni
     *            the dni
     */
    public JwtToken(Integer expirySeconds, String secret, String nup, String dni) {
        Map<String, Object> payload = new HashMap<String, Object>();

        payload.put("sub", nup);
        payload.put("aud", "bff-graphql");
        payload.put("iss", "7000");
        payload.put("instanceId", NetworkUtil.getHostName());
        payload.put(JWTClaimEnumeration.ID.getName(), dni);

        payload.put(JWTClaimEnumeration.USER_NUP.getName(), nup);

        JWTSigner jwtSigner = new JWTSigner(secret);
        token = jwtSigner.sign(payload, buildOptions(expirySeconds));
    }

    /**
     * Verificar el estado del token.
     *
     * @param secret
     *            the secret
     * @param nup
     *            the nup
     * @return the map
     * @throws JwtVerifyException
     *             the jwt verify exception
     */
    public Map<String, Object> verifyToken(String secret, String nup) throws JwtVerifyException {
        try {
            LOGGER.debug("Validar que el token sea correcto y mantenga la integridad de los datos.");
            Map<String, Object> decodedPayload = new JWTVerifier(secret).verify(token);
            String nupToken = (String) decodedPayload.get(JWTClaimEnumeration.USER_NUP.getName());
            if (!nup.equals(nupToken)) {
                throw new JwtVerifyException("No corresponde el nup de la session con el nup del token.");
            }
            return decodedPayload;
        } catch (InvalidKeyException ike) {
            LOGGER.error("Invalid cryptography keys.", ike);
            throw new JwtVerifyException(ike);
        } catch (NoSuchAlgorithmException nsae) {
            LOGGER.error("Intentar acceder a un metodo no provisto por la libreria.", nsae);
            throw new JwtVerifyException(nsae);
        } catch (IllegalStateException ise) {
            LOGGER.error("Invocacion a un metodo en un momento o con un estado incorrecto.", ise);
            throw new JwtVerifyException(ise);
        } catch (SignatureException se) {
            LOGGER.error("Error generico para las firmas.", se);
            throw new JwtVerifyException(se);
        } catch (IOException ioe) {
            LOGGER.error("Error de entrada salida de los datos.", ioe);
            throw new JwtVerifyException(ioe);
        } catch (JWTVerifyException jve) {
            LOGGER.error("Error verificacion de jwt.", jve);
            throw new JwtVerifyException(jve);
        }
    }

    /**
     * Accepts header with Bearer prefix and without it.
     *
     * @param header
     *            the header
     * @return the jwt token
     */
    public static JwtToken parseToken(String header) {
        LOGGER.trace(header);
        if (header == null) {
            return null;
        }
        return new JwtToken(header.replace(HEADER_PREFIX, ""));
    }

    /**
     * Gets the token.
     *
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets the token.
     *
     * @param token
     *            the new token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Builds the options.
     *
     * @param expirySeconds
     *            the expiry seconds
     * @return the options
     */
    private Options buildOptions(int expirySeconds) {
        Options options = new Options();
        options.setExpirySeconds(expirySeconds);
        options.setAlgorithm(Algorithm.HS256);
        options.setJwtId(true);
        options.setIssuedAt(Boolean.TRUE);
        return options;
    }
    
    public Jws<Claims> verifyToken(String nup) throws JwtVerifyException, ExpiredJwtException {
        
        try {
            String publicKey  = ContextHolder.getContext().getEnvironment().getProperty("APIAUTH_PUBLIC_KEY");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(org.bouncycastle.util.encoders.Base64.decode(publicKey));
            RSAPublicKey pubKey;
            pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
            Jws<Claims> claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(this.getToken());
            String nupToken = (String) claims.getBody().get("nup");
            if (!nup.equals(StringUtils.leftPad(nupToken,8,'0'))) {
            	LOGGER.error(JWT_NUP_NO_VALIDO);
                throw new JwtVerifyException(JWT_NUP_NO_VALIDO);
            }
            return claims;
        } catch (ExpiredJwtException expExcep) {
            LOGGER.error("Error token expirado.", expExcep);
            throw expExcep;
        } catch (InvalidKeySpecException ike) {
            LOGGER.error("Error en clave publica.", ike);
            throw new JwtVerifyException(ike);
        } catch (NoSuchAlgorithmException nsae) {
            LOGGER.error("Intentar acceder a un metodo no provisto por la libreria.", nsae);
            throw new JwtVerifyException(nsae);
        } catch (IllegalStateException ise) {
            LOGGER.error("Invocacion a un metodo en un momento o con un estado incorrecto.", ise);
            throw new JwtVerifyException(ise);
        }
    }
}