/**
 * 
 */
package ar.com.santanderrio.obp.base.security.keystore;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.base.security.credential.Credential;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.base.security.keystore.impl.KeyStoreFactoryImpl;

/**
 * The Class KeyStoreHelperTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(MockitoJUnitRunner.class)
public class KeyStoreHelperTest {

    /** The Constant KS_TYPE. */
    private static final String KS_TYPE = "KEYSTORE.VISAAMEX.TYPE";

    /** The Constant KS_TYPE_VALUE. */
    private static final String KS_TYPE_VALUE = "JKS";

    /** The Constant KS_PATH. */
    private static final String KS_PATH = "KEYSTORE.VISAAMEX.PATH";

    /** The Constant KS_PATH_VALUE. */
    private static final String KS_PATH_VALUE = "/aplicaciones/hb/conf/keyStore/hbkey.jks";

    /** The Constant KS_IDSEG. */
    private static final String KS_IDSEG = "KEYSTORE.VISAAMEX.IDSEGURIDAD";

    /** The Constant KS_IDSEG_VALUE. */
    private static final String KS_IDSEG_VALUE = "20014";

    /** The Constant KS_KEY. */
    private static final String KS_KEY = "KEYSTORE.VISAAMEX.KEY.ALIAS";

    /** The Constant KS_KEY_VALUE. */
    private static final String KS_KEY_VALUE = "clave";

    /** The Constant KS_KEY_PASS. */
    private static final String KS_KEY_PASS = "KEYSTORE.VISAAMEX.KEY.PASSWORD";

    /** The Constant KS_KEY_PASS_VALUE. */
    private static final String KS_KEY_PASS_VALUE = "passval";

    /** The Constant KS_TEST. */
    private static final String KS_TEST = "VISAAMEX";

    /** The key store factory impl. */
    @InjectMocks
    private KeyStoreFactoryImpl keyStoreFactoryImpl;

    /** The credential security factory. */
    @Mock
    private CredentialSecurityFactory credentialSecurityFactory;

    /** The property map. */
    @Mock
    private PropertyMap propertyMap;

    /**
     * Al crear un KeyStore con IDSEGURIDAD el alias y password se toman de las
     * credenciales.
     *
     * @throws SQLException
     *             the SQL exception
     * @throws KeyStoreException
     *             the key store exception
     */
    @Test
    public void DadoVISAAMEXcreateKeyStoreConIDSEGURIDAD() throws SQLException, KeyStoreException {
        Mockito.when(propertyMap.get(KS_TYPE)).thenReturn(KS_TYPE_VALUE);
        Mockito.when(propertyMap.get(KS_PATH)).thenReturn(KS_PATH_VALUE);
        Mockito.when(propertyMap.get(KS_IDSEG)).thenReturn(KS_IDSEG_VALUE);
        Credential credential = new Credential();
        credential.setUsuario("usuario_test");
        credential.setPassword("password_test");

        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
        KeyStore ks = keyStoreFactoryImpl.createKeyStore(KS_TEST);
        Assert.assertEquals(ks.getKeystoreAlias(), credential.getUsuario());
        Assert.assertEquals(ks.getKeystorePassword(), credential.getPassword());
        Mockito.verify(credentialSecurityFactory, Mockito.times(1)).getCredentialById(Matchers.anyString());
    }

    /**
     * Al crear un KeyStore sin IDSEGURIDAD el alias y password se toman del
     * Map.
     *
     * @throws SQLException
     *             the SQL exception
     * @throws KeyStoreException
     *             the key store exception
     */
    @Test
    public void DadoVISAAMEXcreateKeyStoreSinIDSEGURIDAD() throws SQLException, KeyStoreException {
        Mockito.when(propertyMap.get(KS_TYPE)).thenReturn(KS_TYPE_VALUE);
        Mockito.when(propertyMap.get(KS_PATH)).thenReturn(KS_PATH_VALUE);
        Mockito.when(propertyMap.get(KS_KEY)).thenReturn(KS_KEY_VALUE);
        Mockito.when(propertyMap.get(KS_KEY_PASS)).thenReturn(KS_KEY_PASS_VALUE);
        Credential credential = new Credential();
        credential.setUsuario("usuario_test");
        credential.setPassword("password_test");

        Mockito.when(credentialSecurityFactory.getCredentialById(Matchers.anyString())).thenReturn(credential);
        KeyStore ks = keyStoreFactoryImpl.createKeyStore(KS_TEST);
        Assert.assertEquals(ks.getKeystoreAlias(), KS_KEY_VALUE);
        Assert.assertEquals(ks.getKeystorePassword(), KS_KEY_PASS_VALUE);
        Mockito.verify(credentialSecurityFactory, Mockito.never()).getCredentialById(Matchers.anyString());
    }
}
