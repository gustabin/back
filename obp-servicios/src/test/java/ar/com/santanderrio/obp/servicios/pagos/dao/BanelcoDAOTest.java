/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.BanelcoDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPagoMisCuentas;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class BanelcoDAOTest.
 *
 * @author florencia.n.martinez
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        BanelcoDAOTest.BanelcoDAOTestConfiguration.class })
public class BanelcoDAOTest extends IatxBaseDAOTest {

    /** The banelco DAO. */
    @Autowired
    @InjectMocks
    private BanelcoDAOImpl banelcoDAO;

    /** The mensaje dao. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /**
     * The Class BanelcoDAOTestConfiguration.
     */
    @Configuration
    public static class BanelcoDAOTestConfiguration {

        /**
         * Banelco DAO.
         *
         * @return the banelco DAO
         */
        @Bean
        public BanelcoDAO banelcoDAO() {
            return new BanelcoDAOImpl();
        }

        /**
         * Mensaje DAO.
         *
         * @return the mensaje DAO
         */
        @Bean
        public MensajeDAO mensajeDAO() {
            return Mockito.mock(MensajeDAO.class);
        }

        /**
         * Respuesta factory.
         *
         * @return the respuesta factory
         */
        @Bean
        public RespuestaFactory respuestaFactory() {
            return Mockito.mock(RespuestaFactory.class);
        }

        /**
         * Mensaje BO.
         *
         * @return the mensaje BO
         */
        @Bean
        public MensajeBO mensajeBO() {
            return Mockito.mock(MensajeBO.class);
        }
    }

    /**
     * Obtener cuentas banelco habilitadas sin cuentas banelco test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void obtenerCuentasBanelcoHabilitadasSinCuentasBanelcoTest() throws IatxException, DAOException {
        // Given
        String tramaResponse = "200000000000P04HTML0009900010300KTNK98  ********00076585000000062018061114342900000000IBF201Q7000000000000IDEPESBANE1000000            00093098    00        00 014340461201806111434120000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0052800000000õCAPARROS MELENDEZ G     õ õ24101966õ0õ00000000000õ   181024  õ                                                                 õE õ0004517660063696412õ  õ                   õ  õ                   õ  õ                   õ  õ                   õ000õ";
        Cliente cliente = ClienteMock.infoCliente();
        Cuenta c1 = new Cuenta();
        c1.setTipoCuentaEnum(TipoCuenta.BANELCO);
        c1.setNroTarjetaCredito("0000000000000000000000");
        Cuenta c2 = new Cuenta();
        c2.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_PESOS);
        c2.setTipoCuentaSinUnificar("01");
        c2.setNroCuentaProducto("000122345435");
        c2.setSucursalPaquete("012");
        cliente.getCuentas().add(c1);
        cliente.getCuentas().add(c2);

        // When
        Mockito.when(iatxSender.send(Matchers.argThat(new IatxMatcher("IDEPESBANE", "100")))).thenReturn(tramaResponse);

        // Then
        List<CuentaPagoMisCuentas> cuentasBanelco = banelcoDAO.obtenerCuentasBanelcoHabilitadas(cliente);

        // Expecteds
        Assert.assertNotNull(cuentasBanelco);
    }
}
