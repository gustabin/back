/*
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import javax.annotation.PostConstruct;

import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.alias.AliasBCRAClient;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelperDTO;

/**
 * The Class AliasCbuGestionarWSImpl.
 */
@Component("gestionAliasCbu")
public class AliasCbuGestionarWSImpl extends GestionarWSAbstract<AliasBCRAClient> {

	/** The crypto helper. */
	@Autowired
	private CryptoHelper cryptoHelper;

	/** The env. */
	@Autowired
	private Environment env;

	/** The crypto helper DTO. */
	private CryptoHelperDTO cryptoHelperDTO;

	/**
	 * Inits the.
	 */
	@PostConstruct
	private void init() {
		this.cryptoHelperDTO = new CryptoHelperDTO();
		this.cryptoHelperDTO.setAliasIdSeguridad(env.getProperty("KEYSTORE.ALIAS.IDSEGURIDAD"));
		this.cryptoHelperDTO.setKeyStorePath(env.getProperty("KEYSTORE.ALIAS.PATH"));
		this.cryptoHelperDTO.setKeyStoreType(env.getProperty("KEYSTORE.ALIAS.TYPE"));
		this.cryptoHelperDTO.setPrismaPubKeyAlias(env.getProperty("ALIASCBU.ENCUSER"));
		this.cryptoHelperDTO.setTtlTimestamp(env.getProperty("ALIASCBU.TTL.TIMESTAMP"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "ALIAS";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.alias.dao.AliasGestionarWSImpl#
	 * asignarInterceptors(org.apache.cxf.endpoint.Client)
	 */
	@Override
	protected void asignarInterceptors(Client cliente) {
		cryptoHelper.asignarInterceptors(cliente, cryptoHelperDTO);
	}

}
