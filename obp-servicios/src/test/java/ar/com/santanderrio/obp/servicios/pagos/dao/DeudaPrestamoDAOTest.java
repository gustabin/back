/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
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
import ar.com.santanderrio.obp.iatx.IatxMatcherRellamado;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.DeudaPrestamoDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class DeudaPrestamoDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        DeudaPrestamoDAOTest.DeudaPrestamoDAOTestConfiguration.class })
public class DeudaPrestamoDAOTest extends IatxBaseDAOTest {

    /** The deuda prestamo DAO. */
    @Autowired
    @InjectMocks
    private DeudaPrestamoDAO deudaPrestamoDAO;

    /**
     * The Class DeudaPrestamoDAOTestConfiguration.
     */
    @Configuration
    public static class DeudaPrestamoDAOTestConfiguration {

        /**
         * Deuda prestamo DAO.
         *
         * @return the deuda prestamo DAO
         */
        @Bean
        public DeudaPrestamoDAO deudaPrestamoDAO() {
            return new DeudaPrestamoDAOImpl();
        }
    }

    /**
     * Consultar.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    // @Ignore
    @Test
    public void consultar() throws IatxException, DAOException {
        String servicio = "CNSPMOCUO_";
        String version = "140";
        String tramaResponse = "200000000000P04HTML0009900010303DBHJ82  ********00692003000000092016122016384700000000IBF35350000000000000CNSPMOCUO_1400000            03317982N   00        00 000000000201612201638410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0098000000000õ                    õ2012-07-12õ00008õ00000000004263800õ00000000003489400õ00000000000000000õ00000000000000000õ00000000000183400õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ025000000õ00000000000183400õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000007936600õARSõ00000000167493200õ000000000õ00000000000000000+õ00000000000000000+õ00000000000000000+õ000000000õ    õ00000000000000000õ028073156õ011000000õ013000000õ                    õ0001-01-01õ00000õ    õ00000000000õ0001-01-01õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000õ00000000000000000+õ00000000000000000+õ00000000000000000+õ00000000000000000õ"; // 000000000201606141305360000000000
        // 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005310000000õ00137600000õ000000000000000õ000000000000000õ";
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("111111");
        cuenta.setNroSucursal("11");
        cuenta.setTipoCuentaSinUnificar("01");
        cuenta.setEstadoTarjetaCredito("20");

        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);
        when(iatxSender.send(Matchers.argThat(new IatxMatcherRellamado("CNSPMOCUO_", "140", "000"))))
                .thenReturn(tramaResponse);

        Prestamo respuesta = deudaPrestamoDAO.consultarDeudaDePrestamo(cliente, cuenta);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

}
