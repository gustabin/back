/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.clientes.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.dao.CredencialesDAO;
import ar.com.santanderrio.obp.servicios.clientes.dao.impl.CredencialesDAOImpl;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesClavePinVencidaNotieneUsuarioException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.loggedinusercache.dao.UserCacheDAO;
import ar.com.santanderrio.obp.servicios.loggedinusercache.dao.UserCacheDAOImpl;
import ar.com.santanderrio.obp.servicios.login.dao.ApiAuthDAO;
import ar.com.santanderrio.obp.servicios.login.dao.ProxyLoginDAO;
import ar.com.santanderrio.obp.servicios.login.dao.impl.ApiAuthDAOImpl;
import ar.com.santanderrio.obp.servicios.login.dao.impl.ProxyLoginDAOImpl;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPinesUtil;
import ar.com.santanderrio.obp.servicios.login.entity.ProxyLoginResponse;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.oauth2.connector.OAuthV2Connector;

/**
 * The Class CredencialesDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CredencialesDAOCUETest.CredencialesDAOTestConfiguration.class, EncryPinesUtil.class, SesionCliente.class })
@TestPropertySource(properties = { "DB.ENCPINES_3DES.ID=20086", "DB.ENCPINES_RSA_PRIV.ID=20088",
        "DB.ENCPINES_RSA_PUB.ID=20087", "PROXYLOGIN.ENC.SEC.ID = 20133", "PROXYLOGIN.IV.SEC.ID = 20136", "PROXYLOGIN.OAUTHV2.SEC.ID = 20141",
        "PROXYLOGIN.URL = ", "PROXYLOGIN.SCOPE = proxy-login.read", "IATX.CICS = FEINIH2", "IATX.GATEPORT = 2300" })
public class CredencialesDAOCUETest extends IatxBaseDAOTest {

    /** The credenciales DAO. */
    @Autowired
    @InjectMocks
    private CredencialesDAO credencialesDAO;

	/** The proxy login DAO. */
    @Mock
	private ProxyLoginDAO proxyLoginDAO;
    
    @Mock
    private UserCacheDAOImpl userCacheDAO;

    @Mock
    private ApiAuthDAOImpl apiAuthDAO;
    /**
     * The Class CredencialesDAOTestConfiguration.
     */
    @Configuration
    public static class CredencialesDAOTestConfiguration {

        /**
         * Credenciales DAO.
         *
         * @return the credenciales DAO
         */
        @Bean
        public CredencialesDAO credencialesDAO() {
            return new CredencialesDAOImpl();
        }

        /**
         * Proxy login DAO.
         *
         * @return the proxy login DAO
         */
        @Bean
        public ProxyLoginDAO proxyLoginDAO() {
            return new ProxyLoginDAOImpl();
        }
        @Bean
        public UserCacheDAO userCacheDAO() {
        	return new UserCacheDAOImpl();
        }
        @Bean
        public ApiAuthDAO apiAuthDAO() {
        	return new ApiAuthDAOImpl();
        }

        /**
         * Rest template.
         *
         * @return the rest template
         */
        @Bean
        @Qualifier("restLoginTemplate")
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }

        @Bean
        public OAuthV2Connector oAuthV2Connector() {
        	return Mockito.mock(OAuthV2Connector.class);
        }

        /**
         * Al estar apagado la logica de pines no se invoca el codigo referido a la base
         * de seg. Si se prendiera hay q mockear este comportamiento.
         * 
         * @return
         */
        @Bean
        public CredentialSecurityFactory credentialSecurityFactory() {
            return Mockito.mock(CredentialSecurityFactory.class);
        }

        /**
         * Se apaga el permiso para que la logica de encripcion de pines no se aplique.
         * 
         * @return
         */
        @Bean
        public ModuloPermisoBO moduloPermisoBO() {
            ModuloPermisoBO moduloPermisoBO = Mockito.mock(ModuloPermisoBO.class);
            ModuloPermiso moduloPermiso = new ModuloPermiso();
            moduloPermiso.setModuloEstado(ModuloEstado.OCULTAR);
            Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(AccionController.LOGICA_ENC_LOGIN))
                    .thenReturn(moduloPermiso);
            return moduloPermisoBO;
        }
    }

    /**
     * Consultar.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Ignore
    @Test
    public void validarCredencialesOkTest() throws IatxException, DAOException, CredencialesException {

        CredencialCliente credenciales = new CredencialCliente();
        credenciales.setClave("330A330A330A330A200A57A239A200A");
        credenciales.setClaveNueva("330A330A330A330A330A330A330A330A");
        credenciales.setDni("330A330A330A271A349A297A57A200A349A57A102A");
        credenciales.setDniOri("21587183");
        credenciales.setFechaNacimiento(null);
        credenciales.setIp("0:0:0:0:0:0:0:1");
        credenciales.setPrefijo("143A");
        credenciales.setSufijo("A");
        credenciales.setUsuario("348A304A37A304A200A57A239A250A315A315A315A315A315A315A315A315A315A315A315A315A");
        credenciales
                .setUsuarioNuevo("315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A");
        credenciales.setIsAlta(true);
        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00254131000000002015102217243100000000IBF26364000000000000SEGINFORM_1300000                        00        0  017267461201510221725020000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005100000000õ00MRQT37õ@$TKF$88õ00276937õ19700420õIõ õAõ"; // "200000000000P70HTML00000000003FREEUSER
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        ResumenCliente resumenCliente = credencialesDAO.actualizarClaveUsuario(credenciales);
        assertNotNull(resumenCliente);
        assertEquals(resumenCliente.getDni(), "21587183");
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    /**
     * Validar cred error clave pin venc sin usuario test.
     *
     * @throws IatxException the iatx exception
     * @throws DAOException the DAO exception
     * @throws CredencialesException the credenciales exception
     */
    @Ignore
    @Test(expected = CredencialesClavePinVencidaNotieneUsuarioException.class)
    public void validarCredErrorClavePinVencSinUsuarioTest()
            throws IatxException, DAOException, CredencialesException {

        CredencialCliente credenciales = new CredencialCliente();
        credenciales.setClave("330A330A330A330A200A57A239A200A");
        credenciales.setClaveNueva("330A330A330A330A330A330A330A330A");
        credenciales.setDni("330A330A330A271A349A297A57A200A349A57A102A");
        credenciales.setDniOri("21587183");
        credenciales.setFechaNacimiento(new Date());
        credenciales.setIp("0:0:0:0:0:0:0:1");
        credenciales.setPrefijo("143A");
        credenciales.setSufijo("A");
        credenciales.setUsuario("348A304A37A304A200A57A239A250A315A315A315A315A315A315A315A315A315A315A315A315A");
        credenciales
                .setUsuarioNuevo("315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A");
        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00552805000000002016111612041300000000IBF30698000000000000SEGINFORM_1300000                        00        0  000000000201611161204070000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810001002õSEGõ03õPor favor, verifique los datos ingresados.                                                                                                                                                                                                                    õCLAVE VENC.S/USõPIN VENCIDO Y NO TIENE USUARIO DEFINIDO                                         õ"; // "200000000000P70HTML00000000003FREEUSER
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        credencialesDAO.validarCredenciales(credenciales);
    }

    /**
     * Validar credenciales proxy login ok test.
     *
     * @throws IatxException the iatx exception
     * @throws DAOException the DAO exception
     * @throws CredencialesException the credenciales exception
     */
    @Ignore
    @Test
    public void validarCredencialesProxyLoginOkTest() throws IatxException, DAOException, CredencialesException {

        CredencialCliente credenciales = new CredencialCliente();
        credenciales.setDniOri("16998479");
        credenciales.setIsAlta(true);
        ProxyLoginResponse proxyLoginResponse = new ProxyLoginResponse();
        proxyLoginResponse.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MjE0MjkzNDAsImlhdCI6MTYyMTQyOTI4MCwidW5pcXVlUGVyc29uTnVtYmVyIjoiMDAwMTE5MDMiLCJiaXJ0aERhdGUiOiIxOTY0LTA3LTE0IiwidXNlclR5cGUiOiJJIiwiYW5waEZsYWciOiIgIiwicGFzc3dvcmRUeXBlIjoiQSIsInRpY2tldE51bWJlciI6IjEwMDE2OTUzIn0.HvwGvvqGQZ2J8XeAdxA9KR_816mSXKvnZagZafAZEiM");

		when(proxyLoginDAO.updatePinPassword(Matchers.any(CredencialCliente.class))).thenReturn(proxyLoginResponse);

        ResumenCliente resumenCliente = credencialesDAO.actualizarClaveUsuarioProxyLogin(credenciales);
        assertNotNull(resumenCliente);
        assertEquals(resumenCliente.getDni(), "16998479");
        assertEquals(resumenCliente.getNup(), "00011903");
    }

    /**
     * Validar cred error clave pin venc sin usuario proxy login test.
     *
     * @throws IatxException the iatx exception
     * @throws DAOException the DAO exception
     * @throws CredencialesException the credenciales exception
     */
    @Ignore
    @Test(expected = CredencialesClavePinVencidaNotieneUsuarioException.class)
    public void validarCredErrorClavePinVencSinUsuarioProxyLoginTest()
            throws IatxException, DAOException, CredencialesException {
        CredencialCliente credenciales = new CredencialCliente();
        credenciales.setDniOri("16998479");
        ProxyLoginResponse proxyLoginResponse = new ProxyLoginResponse();
        proxyLoginResponse.setCode("400");
        proxyLoginResponse.setMessage("10001002");
        proxyLoginResponse.setDescription("No es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.");

		when(proxyLoginDAO.obtenerTokenLogin(Matchers.any(CredencialCliente.class))).thenReturn(proxyLoginResponse);

        credencialesDAO.validarCredencialesProxyLogin(credenciales);
    }
}
