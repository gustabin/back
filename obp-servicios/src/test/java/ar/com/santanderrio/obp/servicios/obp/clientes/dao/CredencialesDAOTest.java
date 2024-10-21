/**
 * 
 */
package ar.com.santanderrio.obp.servicios.obp.clientes.dao;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.dao.CredencialesDAO;
import ar.com.santanderrio.obp.servicios.clientes.dao.impl.CredencialesDAOImpl;
import ar.com.santanderrio.obp.servicios.clientes.entities.CambioUsuarioEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioBloqueadoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesUsuarioConSinonimoException;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPinesUtil;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

/**
 * The Class CredencialesDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CredencialesDAOTest.CredencialesDAOTestConfiguration.class, EncryPinesUtil.class })
@TestPropertySource(properties = { "DB.ENCPINES_3DES.ID=20086", "DB.ENCPINES_RSA_PRIV.ID=20088",
        "DB.ENCPINES_RSA_PUB.ID=20087" })
@Ignore
public class CredencialesDAOTest extends IatxBaseDAOTest {

    /** The credenciales DAO. */
    @Autowired
    @InjectMocks
    private CredencialesDAO credencialesDAO;

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
        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00254131000000002015102217243100000000IBF26364000000000000SEGINFORM_1300000                        00        0  017267461201510221725020000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005100000000õ00MRQT37õ@$TKF$88õ00276937õ19700420õIõ õAõ"; // "200000000000P70HTML00000000003FREEUSER
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ResumenCliente respuesta = credencialesDAO.validarCredenciales(credenciales);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    /**
     * Validar credenciales error test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test(expected = CredencialesException.class)
    public void validarCredencialesErrorTest() throws IatxException, DAOException, CredencialesException {

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
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER ********00155637000000002017010516452700000000IBF29672000000000000SEGINFORM_1300000 00 0 000000000201701051645220000000000 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810001018õSEGõ03õPor favor dir¡jase a cualquier cajero autom　tico de la Red Banelco o a su Sucursal para obtener una clave nueva. õCLIENTE BLOQUEAõCLIENTE BLOQUEADO õ"; // "200000000000P70HTML00000000003FREEUSER
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        credencialesDAO.validarCredenciales(credenciales);
    }

    /**
     * Validar credenciales user con sinonimo test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test(expected = CredencialesUsuarioConSinonimoException.class)
    public void validarCredencialesUserConSinonimoTest() throws IatxException, DAOException, CredencialesException {

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
        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00621698000000002016122317535900000000IBF22743000000000000SEGINFORM_1300000                        00        0  000000000201612231753530000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810001001õSEGõ03õLa operacion solicitada no puede ser realizada. Por favor, comuniquese con su Sucursal.                                                                                                                                                                       õCTE. C/SINONIMOõCLIENTE CON SINONIMO                                                            õ"; // "200000000000P70HTML00000000003FREEUSER
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        credencialesDAO.validarCredenciales(credenciales);
    }

    /**
     * Validar credenciales user bloqueado test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test(expected = CredencialesUsuarioBloqueadoException.class)
    public void validarCredencialesUserBloqueadoTest() throws IatxException, DAOException, CredencialesException {

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
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00974540000000002017010912101600000000IBF27418000000000000SEGINFORM_1300000                        00        0  000000000201701091210100000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810001018õSEGõ03õPor favor dir¡jase a cualquier cajero autom tico de la Red Banelco o a su Sucursal para obtener una clave nueva.                                                                                                                                              õCLIENTE BLOQUEAõCLIENTE BLOQUEADO                                                               õ"; // "200000000000P70HTML00000000003FREEUSER
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        credencialesDAO.validarCredenciales(credenciales);
    }

    /**
     * Actualizar clave usuario test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test
    public void actualizarClaveUsuarioTest() throws IatxException, CredencialesException {

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
        credenciales.setIsAlta(true);
        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00507219000000012016112111253100000000IBF27989000000000000SEGINFORM_1300000                        00        0  011236525201611211125250000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005100000000õ03SORR95õ@$T4XMVRõ03847795õ19731116õIõ õAõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        credencialesDAO.actualizarClaveUsuario(credenciales);
    }

    /**
     * Actualizar clave usuario exception test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test
    public void actualizarClaveUsuarioExceptionTest() throws IatxException, CredencialesException {

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
        credenciales.setIsAlta(true);
        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00974540000000002017010912101600000000IBF27418000000000000SEGINFORM_1300000                        00        0  000000000201701091210100000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036810001018õSEGõ03õPor favor dir¡jase a cualquier cajero autom tico de la Red Banelco o a su Sucursal para obtener una clave nueva.                                                                                                                                              õCLIENTE BLOQUEAõCLIENTE BLOQUEADO                                                               õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        String mensajeError = null;
        try {
            credencialesDAO.actualizarClaveUsuario(credenciales);
        } catch (CredencialesUsuarioBloqueadoException e) {
            mensajeError = e.getMessage();
        }

        Assert.assertEquals(mensajeError,
                "Por favor dir¡jase a cualquier cajero autom tico de la Red Banelco o a su Sucursal para obtener una clave nueva. CLIENTE BLOQUEA CLIENTE BLOQUEADO");
    }

    /**
     * Valida credenciales con error (warning) clave por vencer En principio debe
     * ignorar este error
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test
    public void validarCredClavePorVencerTest() throws IatxException, DAOException, CredencialesException {

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
        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML00099000104FREEUSER  ********00507159000000002016111610354200000000IBF22423000000000000SEGINFORM_1300000                        00        0  000000000201611161035360000000000                            "
                + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0041020001010õSEGõ03õTu clave "
                + "Santander Rio vence en UN DIA .                                                                                                                                                                                                                      õ"
                + "CLVE. VENCE    õClave vence enªUN DIA ª                                                         õ03SORR95õ@CTRZEGLõ03847795õ19731116õIõ õAõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ResumenCliente respuesta = credencialesDAO.validarCredenciales(credenciales);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

    @Test
    public void cambioUsuarioTest() throws IatxException, DAOException, CredencialesException {

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

        CambioUsuarioEntity entity = new CambioUsuarioEntity();
        entity.setStrNewPin("00000000");
        entity.setStrNewUsr("nuevoUsuario");
        entity.setStrOldPin("00000000");
        entity.setStrOldUsr("oldUsr");

        Cliente cliente = new Cliente();
        cliente.setDni("98902444");

        entity.setCliente(cliente);

        String servicio = "SEGINFORM";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00799516000000212017051017345300000000IBF27795000000000000SEGINFORM_1300000            02615492    00        00 017329356201705101734460000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005100000000õPROCESO õSATISFACõTORIO   õ        õ õ õ õ"; // "200000000000P70HTML00000000003FREEUSER
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ResumenCliente resumenCliente = credencialesDAO.validarCredenciales(credenciales);
        Assert.assertNotNull(resumenCliente);
    }
    
    @Test(expected = RobotException.class)
    public void coordinadorConRespuestaNoManejada() throws IatxException, DAOException, CredencialesException {
    	
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
    	
    	CambioUsuarioEntity entity = new CambioUsuarioEntity();
    	entity.setStrNewPin("00000000");
    	entity.setStrNewUsr("nuevoUsuario");
    	entity.setStrOldPin("00000000");
    	entity.setStrOldUsr("oldUsr");
    	
    	Cliente cliente = new Cliente();
    	cliente.setDni("98902444");
    	
    	entity.setCliente(cliente);
    	
    	String servicio = "SEGINFORM";
    	String version = "130";
    	String tramaResponse = "200000000000P04HTML00099000104USRPOBP   ********00078462000000002019101602302900000000IBF3HJ74346000000000SEGINFORM_1300000                        00        0  000000000201910160229540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ00053 - FALLO LINK @SIC0005                              õ"; 
    	when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
    	credencialesDAO.validarCredenciales(credenciales);
    }

    @Test
    public void cambioClaveUsuarioTest() throws IatxException, DAOException, CredencialesException {

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

        CambioUsuarioEntity entity = new CambioUsuarioEntity();
        entity.setStrNewPin("nuevoPin");
        entity.setStrNewUsr("nuevoUsuario");
        entity.setStrOldPin("oldPin");
        entity.setStrOldUsr("oldUsr");

        Cliente cliente = new Cliente();
        cliente.setDni("98902444");

        entity.setCliente(cliente);

        String servicio = "SEGINFORM";
        String version = "130";

        String tramaResponse = "200000000000P04HTML0009900010300MRQT37  ********00976841000000122017052211520500000000IBF23060000000000000SEGINFORM_1300000            00276937    00        00 011513259201705221151570000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005100000000õ00MRQT37õ@C1PJJ5Hõ00276937õ19700420õ õ õAõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ResumenCliente resumenCliente = credencialesDAO.cambioUsuario(credenciales, entity);
        Assert.assertNotNull(resumenCliente);
    }

}
