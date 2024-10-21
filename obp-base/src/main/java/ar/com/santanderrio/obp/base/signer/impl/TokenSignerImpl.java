/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.impl;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.signer.TokenSigner;
import ar.com.santanderrio.obp.base.signer.factory.TokenFactory;
import ar.com.santanderrio.obp.base.signer.token.Token;

// TODO: Auto-generated Javadoc
/**
 * The Class TokenSignerImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("tokenSigner")
public class TokenSignerImpl implements TokenSigner {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenSignerImpl.class);

	/** The token factory. */
	@Autowired
	private TokenFactory tokenFactory;

	/** The sign. */
	@Autowired
	private Sign sign;

	/** The app encoding. */
	@Value("${APP.ENCODING}")
	private String appEncoding;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.base.signer.TokenSigner#obtenerTokenFirmado(java.lang
	 * .String, ar.com.santanderrio.base.entities.Entity[])
	 */
	@Override
	public String obtenerTokenFirmado(String tokenCode, Entity... entidades) throws DAOException {
		LOGGER.info("Obtener token para {}.", tokenCode);
		Token token = tokenFactory.crearToken(tokenCode, entidades);
		LOGGER.info("Token obtenido {}", token.toString());
		try {
			return sign.buildPemSignature(token.toString().getBytes(appEncoding), token.getJks(),
					token.getRequiereCertificado());
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error al generar la firma con el encoding {}", appEncoding, e);
			throw new DAOException("Error con el encoding al firmar el token.");
		}
	}

}
