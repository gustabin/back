/**
 * 
 */
package ar.com.santanderrio.obp.base.signer.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.signer.factory.TokenFactory;
import ar.com.santanderrio.obp.base.signer.token.Token;

// TODO: Auto-generated Javadoc
/**
 * The Class TokenFactoryImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("tokenFactory")
public final class TokenFactoryImpl implements TokenFactory {
	/** The logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TokenFactoryImpl.class);

	/** The Constant FACTORY. */
	private static final String FACTORY = "TokenFactory";

	/** The bean factory. */
	@Autowired
	private BeanFactory beanFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.base.signer.factory.TokenFactory#crearToken(java.lang
	 * .String, ar.com.santanderrio.base.entities.Entity[])
	 */
	@Override
	public Token crearToken(String tokenCode, Entity... entidades) {
		LOGGER.info("Invocar Factoria para {}", tokenCode);
		TokenFactory factory = (TokenFactory) beanFactory.getBean(tokenCode + FACTORY);
		return factory.crearToken(tokenCode, entidades);
	}

}
