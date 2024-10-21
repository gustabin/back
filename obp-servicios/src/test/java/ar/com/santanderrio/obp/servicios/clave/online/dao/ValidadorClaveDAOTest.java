package ar.com.santanderrio.obp.servicios.clave.online.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.clave.online.excepciones.*;
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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.security.credential.CredentialSecurityFactory;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clave.online.dao.impl.ValidadorClaveDAOImpl;
import ar.com.santanderrio.obp.servicios.clave.online.entities.DatosAutenticacionInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.FuncionEnum;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.login.encrypines.EncryPinesUtil;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ValidadorClaveDAOTest.ValidadorClaveDAOTestConfiguration.class, EncryPinesUtil.class })
@TestPropertySource(properties = { "DB.ENCPINES_3DES.ID=20086", "DB.ENCPINES_RSA_PRIV.ID=20088",
        "DB.ENCPINES_RSA_PUB.ID=20087" })
public class ValidadorClaveDAOTest extends IatxBaseDAOTest {

    /** The PreguntasSeguridad DAO. */
    @Autowired
    @InjectMocks
    private ValidadorClaveDAO validadorClaveDAO;

    /**
     * The Class ValidadorClaveDAOTestConfiguration.
     */
    @Configuration
    public static class ValidadorClaveDAOTestConfiguration {

        /**
         * PreguntasSeguridad DAO.
         *
         * @return the Preguntas SeguridadDAO
         */
        @Bean
        public ValidadorClaveDAO validadorClaveDAO() {
            return new ValidadorClaveDAOImpl();
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
     * @throws ClienteBloqueadoException
     * @throws ErrorClaveOnlineConReintentoException
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     * @throws SinReintentosSMSException
     * @throws ErrorEnvioSMSException
     * @throws ClienteBloqueado2Exception
     * @throws ErrorLogicoOtpException 
     * @throws ErrorModuloException 
     * @throws FuncionInvalidaException 
     * @throws ErrorCicsException 
     * @throws ErrorDb2Exception 
     * @throws ErrorRutinaFechasException 
     * @throws ErrorPinOtpException 
     * @throws ErrorPinBanelcoException
     * @throws OtpVencidoException
     */
    @Test
    public void validarClaveTest()
            throws DAOException, ErrorClaveOnlineConReintentoException, ClienteBloqueadoException, IatxException,
            SinReintentosSMSException, ErrorEnvioSMSException, ClienteBloqueado2Exception, ErrorPinBanelcoException, ErrorPinOtpException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, ErrorLogicoOtpException, OtpVencidoException {

        DatosAutenticacionInEntity datosAutenticacionInEntity = new DatosAutenticacionInEntity();
        datosAutenticacionInEntity.setDni("22424141");
        datosAutenticacionInEntity.setIp("224.241.412.242");
        datosAutenticacionInEntity.setFuncionEnum(FuncionEnum.BANE);
        datosAutenticacionInEntity.setEmpresaCelularElegida("MOVI");
        String servicio = "SEGSGIAUT_";
        String version = "100";
        String tramaResponse = "200000000000P04HTML00099000104USRDOBP   ********00907240000000032020061914575900000000IBF30A66000000000000SEGSGIAUT_1200000            02733179    00        0  000000000202006191457190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900000000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        validadorClaveDAO.validarClave(datosAutenticacionInEntity);

    }

}
