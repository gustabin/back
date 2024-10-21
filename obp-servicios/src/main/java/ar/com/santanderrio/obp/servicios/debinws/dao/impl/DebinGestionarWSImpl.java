/*
 * 
 */
package ar.com.santanderrio.obp.servicios.debinws.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.debin.V3_002fDebinClient;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelperDTO;

/**
 * The Class DebinGestionarWSImpl.
 */
@Component("gestionDebin")
public class DebinGestionarWSImpl extends GestionarWSAbstract<V3_002fDebinClient> {

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
		this.cryptoHelperDTO.setAliasIdSeguridad(env.getProperty("KEYSTORE.DEBINWS.IDSEGURIDAD"));
		this.cryptoHelperDTO.setKeyStorePath(env.getProperty("KEYSTORE.DEBINWS.PATH"));
		this.cryptoHelperDTO.setKeyStoreType(env.getProperty("KEYSTORE.DEBINWS.TYPE"));
		this.cryptoHelperDTO.setPrismaPubKeyAlias(env.getProperty("DEBINWS.ENC_USER"));
		this.cryptoHelperDTO.setTtlTimestamp(env.getProperty("DEBINWS.TTL_TIMESTAMP"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
	        return "DEBINWS";
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
