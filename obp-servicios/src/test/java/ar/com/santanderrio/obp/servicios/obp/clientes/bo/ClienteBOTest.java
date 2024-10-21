package ar.com.santanderrio.obp.servicios.obp.clientes.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.clientes.entities.CredencialCliente;
import ar.com.santanderrio.obp.base.clientes.entities.ResumenCliente;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.keystore.KeyStoreException;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.bo.impl.ClienteBOImpl;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteDAO;
import ar.com.santanderrio.obp.servicios.clientes.dao.ClienteSeguridadDAO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.ClienteConSaldoResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.delete.account.utils.AccountUtils;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class ClienteBOTest.
 *
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ClienteBOTest.TestLocalConfiguration.class })
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles(Profiles.TEST)
public class ClienteBOTest {

    /** The cliente BO. *
    @Autowired
    @InjectMocks
    private ClienteBO clienteBO;

    /** The cliente T 1. *
    private static Cliente clienteT1 = new Cliente();

    /** The cuenta. *
    private static Cuenta cuenta = new Cuenta();

    /** The segmento. *
    private static Segmento segmento = new Segmento();

    /** The credenciales. *
    private static CredencialCliente credenciales = new CredencialCliente();

    //private static ClienteConSaldoResponse clienteConSaldo = new ClienteConSaldoResponse();

    private static final Integer CLIENTE_ES_PAS = 00000000000000000001;

    /** The respuesta factory. *
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The cliente DAO. *
    @Mock
    ClienteDAO clienteDAO;

    /** The mensaje BO. *
    @Mock
    MensajeBO mensajeBO;
    
    @Mock
    private ClienteSeguridadDAO clienteSeguridadDAO;
    
    @Mock 
    SesionParametros sesionParametros;

    /**
     * Inits the.
     *
     * @throws KeyStoreException
     *             the key store exception
     *
    @Before
    public void init() throws KeyStoreException {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * The Class TestLocalConfiguration.
     *
    @Configuration
    public static class TestLocalConfiguration {

        /**
         * Respuesta BO.
         *
         * @return the respuesta BO
         *
        @Bean
        public RespuestaBO respuestaBO() {
            return Mockito.mock(RespuestaBO.class);
        }

        /**
         * Mensaje DAO.
         *
         * @return the mensaje DAO
         *
        @Bean
        public MensajeDAO mensajeDAO() {
            return Mockito.mock(MensajeDAO.class);
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         *
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }
*/
        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         *
        @Bean
        public RespuestaFactory respuestaFactory() {

            return new RespuestaFactory();
        }
*/
        /**
         * Cuenta BO.
         *
         * @return the cuenta BO
         *
        @Bean
        public CuentaBO cuentaBO() {
            return Mockito.mock(CuentaBO.class);
        }
*/
        /**
         * Cliente BO.
         *
         * @return the cliente BO
         */
/**        @Bean
        public ClienteBO clienteBO() {
            return new ClienteBOImpl();
        }
        
        @Bean
        public ClienteSeguridadDAO clienteSeguridadDAO() {
            return Mockito.mock(ClienteSeguridadDAO.class);
        }
**/
        /**
         * Cliente DAO.
         *
         * @return the cliente DAO
         */
/**        @Bean
        public ClienteDAO clienteDAO() {
            ClienteDAO clienteDAO = mock(ClienteDAO.class);

            segmento.setDuo(false);
            segmento.setPyme(false);
            segmento.setSelect(false);

            List<Cuenta> cuentas = new ArrayList<Cuenta>();
            cuenta.setClasePaquete("90");
            cuenta.setCodigoPaquete("2182");
            cuenta.setNroTarjetaCredito("00000000000000000001");
            cuentas.add(cuenta);

            clienteT1.setCuentas(cuentas);

            segmento.setDuo(false);
            segmento.setPyme(false);
            segmento.setSelect(false);
            clienteT1.setSegmento(segmento);

            credenciales.setClave("330A330A330A330A200A57A239A200A");
            // credenciales.setClaveNueva("330A330A330A330A330A330A330A330A");
            credenciales.setDni("330A330A330A271A349A297A57A200A349A57A102A");
            credenciales.setDniOri("21587183");
            credenciales.setFechaNacimiento(null);
            credenciales.setIp("0:0:0:0:0:0:0:1");
            credenciales.setPrefijo("143A");
            credenciales.setSufijo("A");
            credenciales.setUsuario("348A304A37A304A200A57A239A250A315A315A315A315A315A315A315A315A315A315A315A315A");
            // credenciales.setUsuarioNuevo(
            // "315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A");

            Respuesta<Cliente> clienteRespuesta = new Respuesta<Cliente>();
            ClienteConSaldoResponse clsr = new ClienteConSaldoResponse();

            Cliente cliente = new Cliente();
            clsr.setCliente(cliente);
            clienteRespuesta.setRespuesta(cliente);
            clsr.setEstadoRespuesta(EstadoRespuesta.WARNING);

            clienteRespuesta.setEstadoRespuesta(EstadoRespuesta.OK);

            try {
                when(clienteDAO.obtenerCliente(Matchers.any(ResumenCliente.class))).thenReturn(clsr);
            } catch (Exception e) {
            }

            return clienteDAO;
        }
        
        @Bean
        public SesionParametros sesionParametros() {
            return Mockito.mock(SesionParametros.class);
        }
    }
**/
    /**
     * Obtener cliente test OK.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
   /* @Test
    public void obtenerClienteTestOK() throws BusinessException, DAOException {
        ClienteConSaldoResponse respuestaCliente = new ClienteConSaldoResponse();
        respuestaCliente.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCliente.setCodigoRespuesta(000000);
        Mockito.when(clienteDAO.obtenerCliente(Matchers.any(ResumenCliente.class))).thenReturn(respuestaCliente);
        
        Respuesta<Cliente> respuesta = clienteBO.obtenerCliente(new ResumenCliente());
        
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }*/

    /**
     * Obtener cliente test warning.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
   /* @Test
    public void obtenerClienteWarningVISAMASTERTest() throws BusinessException, IllegalAccessException {
        ClienteDAO clienteDAO = mock(ClienteDAO.class);
        ClienteConSaldoResponse respuestaCliente = new ClienteConSaldoResponse();
        respuestaCliente.setCodigoRespuesta(20010071);
        respuestaCliente.setEstadoRespuesta(EstadoRespuesta.WARNING);
        ResumenCliente resumenCliente = new ResumenCliente();
        Mockito.when(clienteDAO.obtenerCliente(resumenCliente)).thenReturn(respuestaCliente);

        ArgumentCaptor<Cliente> cc = ArgumentCaptor.forClass(Cliente.class);
        when(respuestaCliente.getCliente()).thenReturn(cc.capture());
        Cliente capturedC = cc.getValue();
        when(capturedC.getCuentas()).thenReturn(Arrays.asList(cuenta));

        FieldUtils.writeField(clienteBO, "clienteDAO", clienteDAO, true);
        Respuesta<Cliente> respuesta = clienteBO.obtenerCliente(resumenCliente);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_TARJETA_VISA_MASTERCARD.getDescripcion());
    }*/

    /**
     * Obtener cliente test warning.
     *
     * @throws BusinessException
     *             the business exception
     * @throws IllegalAccessException
     *             the illegal access exception
     */
   /* @Test
    public void obtenerClienteWarningVISAMASTERBanelcoTest() throws BusinessException, IllegalAccessException {
        ClienteDAO clienteDAO = mock(ClienteDAO.class);
        ClienteConSaldoResponse respuestaCliente = new ClienteConSaldoResponse();
        respuestaCliente.setCodigoRespuesta(20010075);
        respuestaCliente.setEstadoRespuesta(EstadoRespuesta.WARNING);
        ResumenCliente resumenCliente = new ResumenCliente();
        Mockito.when(clienteDAO.obtenerCliente(resumenCliente)).thenReturn(respuestaCliente);
        Mockito.when(respuestaCliente.getCliente().getCuentas()).thenReturn(clienteT1.getCuentas());
        FieldUtils.writeField(clienteBO, "clienteDAO", clienteDAO, true);
        Respuesta<Cliente> respuesta = clienteBO.obtenerCliente(resumenCliente);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_TARJETA_MASTERCARD_VISA_BANELCO.getDescripcion());
    }*/

    /**
     * Obtener cliente test OK.
     *
     * @throws BusinessException
     *             the business exception
     * @throws DAOException
     *             the DAO exception
     */
    /*@Test
    public void obtenerClienteTestErrorTotal() throws BusinessException, DAOException {

        ClienteConSaldoResponse clienteConSaldo = new ClienteConSaldoResponse();

        clienteConSaldo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(clienteDAO.obtenerCliente(Matchers.any(ResumenCliente.class))).thenReturn(clienteConSaldo);

        Respuesta<Cliente> respuesta = clienteBO.obtenerCliente(new ResumenCliente());

        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertTrue(respuesta.getItemsMensajeRespuesta().size() > 0);
        assertEquals(TipoError.LOGIN_ERROR_TOTAL.getDescripcion(), respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }
    
    @Test
    public void obtenerClienteTestErrorWarning() throws BusinessException, IllegalAccessException {
    	ClienteConSaldoResponse clienteConSaldo = new ClienteConSaldoResponse();

        clienteConSaldo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        clienteConSaldo.setCodigoRespuesta(AccountUtils.ERROR_SALDO);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(clienteDAO.obtenerCliente(Matchers.any(ResumenCliente.class))).thenReturn(clienteConSaldo);

        ClienteConSaldoResponse respuestaCliente = new ClienteConSaldoResponse();
        respuestaCliente.setCodigoRespuesta(20010095);
        respuestaCliente.setEstadoRespuesta(EstadoRespuesta.ERROR);
        ResumenCliente resumenCliente = new ResumenCliente();
        Mockito.when(clienteDAO.obtenerCliente(resumenCliente)).thenReturn(respuestaCliente);
        FieldUtils.writeField(clienteBO, "clienteDAO", clienteDAO, true);
        Respuesta<Cliente> respuesta = clienteBO.obtenerCliente(resumenCliente);

        assertEquals(EstadoRespuesta.WARNING,respuesta.getEstadoRespuesta());
        assertEquals(TipoError.ERROR_SALDO.toString(),respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

}*/
