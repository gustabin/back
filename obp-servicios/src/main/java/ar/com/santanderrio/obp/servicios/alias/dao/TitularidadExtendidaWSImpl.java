/*
 * 
 */
package ar.com.santanderrio.obp.servicios.alias.dao;

import javax.annotation.PostConstruct;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;
import ar.com.santanderrio.obp.generated.webservices.alias.TitularidadExtendidaBCRAClient;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelperDTO;

/**
 * The Class TitularidadExtendidaWSImpl.
 */
@Component("gestionTitularidadExtendida")
public class TitularidadExtendidaWSImpl extends GestionarWSAbstract<TitularidadExtendidaBCRAClient> {

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
		this.cryptoHelperDTO.setAliasIdSeguridad(env.getProperty("KEYSTORE.TITULARIDADEXTENDIDA.IDSEGURIDAD"));
		this.cryptoHelperDTO.setKeyStorePath(env.getProperty("KEYSTORE.TITULARIDADEXTENDIDA.PATH"));
		this.cryptoHelperDTO.setKeyStoreType(env.getProperty("KEYSTORE.TITULARIDADEXTENDIDA.TYPE"));
		this.cryptoHelperDTO.setPrismaPubKeyAlias(env.getProperty("TITULARIDADEXTENDIDACBU.ENCUSER"));
		this.cryptoHelperDTO.setTtlTimestamp(env.getProperty("TITULARIDADEXTENDIDACBU.TTL.TIMESTAMP"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	@Override
	public String getCodigoWS() {
		return "TITULARIDADEXTENDIDA";
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
		Wss4jFaultInterceptor wss4jFaultInterceptor = new Wss4jFaultInterceptor(Phase.RECEIVE);
		wss4jFaultInterceptor.addAfter(BaseLoggingInterceptor.class.getName());
		cliente.getInInterceptors().add(wss4jFaultInterceptor);
	}

}
