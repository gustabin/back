/**
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dao.impl;

import javax.annotation.PostConstruct;

import org.apache.cxf.endpoint.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract;
import ar.com.santanderrio.obp.generated.webservices.billetera.consultacuenta2.BilleteraCuentaPortType;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelper;
import ar.com.santanderrio.obp.servicios.prisma.helper.CryptoHelperDTO;

/**
 * The Class BilleteraCuentaGestionarWSImpl.
 *
 */
@Component("gestionBilleteraCuenta")
public class BilleteraCuentaGestionarWSImpl extends GestionarWSAbstract<BilleteraCuentaPortType> {

    /** The crypto helper. */
    @Autowired
    private CryptoHelper cryptoHelper;

    /** The env. */
    @Autowired
    private Environment env;

    /** The crypto helper DTO. */
    private CryptoHelperDTO cryptoHelperDTO;

    /**
     * Gets the codigo WS.
     *
     * @return the codigo WS
     * @see ar.com.santanderrio.obp.base.webservice.GestionarWSAbstract#getCodigoWS()
     */
    @Override
    public String getCodigoWS() {
        return "BILLETERA.BILLETERACUENTA";
    }

    /**
     * Inits the.
     */
    @PostConstruct
    private void init() {
        this.cryptoHelperDTO = new CryptoHelperDTO();
        this.cryptoHelperDTO.setAliasIdSeguridad(env.getProperty("KEYSTORE.BILLETERA.IDSEGURIDAD"));
        this.cryptoHelperDTO.setKeyStorePath(env.getProperty("KEYSTORE.BILLETERA.PATH"));
        this.cryptoHelperDTO.setKeyStoreType(env.getProperty("KEYSTORE.BILLETERA.TYPE"));
        this.cryptoHelperDTO.setPrismaPubKeyAlias(env.getProperty("BILLETERA.ENC_USER"));
        this.cryptoHelperDTO.setTtlTimestamp(env.getProperty("BILLETERA.TTL_TIMESTAMP"));
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
