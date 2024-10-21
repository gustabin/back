package ar.com.santanderrio.obp.servicios.blanqueopin.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.phase.Phase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.base.webservice.interceptor.BaseLoggingInterceptor;
import ar.com.santanderrio.obp.generated.webservices.banelco.ICentroDeServiciosClientPortType;
import ar.com.santanderrio.obp.servicios.alias.dao.Wss4jFaultInterceptor;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelperDTO;

@Component("wsBanelco")
public class GestionarBanelcoWSImpl extends GestionarWSAbstract<ICentroDeServiciosClientPortType> {

	
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
		this.cryptoHelperDTO.setAliasIdSeguridad(env.getProperty("KEYSTORE.WSBANELCO.IDSEGURIDAD"));
		this.cryptoHelperDTO.setKeyStorePath(env.getProperty("KEYSTORE.WSBANELCO.PATH"));
		this.cryptoHelperDTO.setKeyStoreType(env.getProperty("KEYSTORE.WSBANELCO.TYPE"));
		this.cryptoHelperDTO.setPrismaPubKeyAlias(env.getProperty("KEYSTORE.WSBANELCO.ENCUSER"));
		this.cryptoHelperDTO.setTtlTimestamp(env.getProperty("KEYSTORE.WSBANELCO.TTL.TIMESTAMP"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
	 */
	
	@Override
	public String getCodigoWS() {
		return "WSBANELCO";
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
