/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

import static org.mockito.Mockito.when;

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
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.PrestamoPermitidoDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoInEntity;
import ar.com.santanderrio.obp.servicios.prestamos.entity.PrestamoPermitidoOutEntity;

/**
 * The Class PrestamoPermitidoDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        PrestamoPermitidoDAOTest.PrestamoPermitidoDAOTestConfiguration.class })
public class PrestamoPermitidoDAOTest extends IatxBaseDAOTest {

    /** The prestamo permitido DAO. */
    @Autowired
    @InjectMocks
    private PrestamoPermitidoDAO prestamoPermitidoDAO;

    /**
     * The Class TransferenciaDAOTestConfiguration.
     */
    @Configuration
    public static class PrestamoPermitidoDAOTestConfiguration {

        /**
         * Prestamo permitido DAO.
         *
         * @return the prestamo permitido DAO
         */
        @Bean
        public PrestamoPermitidoDAO PrestamoPermitidoDAO() {
            return new PrestamoPermitidoDAOImpl();
        }
    }

    /**
     * Consultar prestamo permitido.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Ignore
    @Test
    public void consultarPrestamoPermitido() throws IatxException, DAOException {
        String servicio = "CNSPMOPERM";
        String version = "110";
        String tramaResponse = "200000000000P04HTML0009900010301KOOT45  ********00047923000000102018102212442200000000IBF207J9000000000000CNSPMOPERM1100000            01044945    00        00 000000000201810221244000000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0064100000000õ004õ90õ0350õNõ00000000000õNõ99000õARSõFõ0003õ0036õ00000000010000000õ00000020000000000õ0072õ35õ0046õ35008õ   õR250õ049000000õZ10õVP1õ    õ    õ000000000000õ   õNõ90õ0350õNõ00000000000õNõ99000õARSõFõ0037õ0072õ00000000010000000õ00000020000000000õ0072õ35õ0053õ35008õ   õR251õ035000000õZ10õVP1õ    õ    õ000000000000õ   õNõ90õ0350õNõ00000000000õNõ99000õARSõVõ0003õ0036õ00000000010000000õ00000020000000000õ0072õ35õ0002õ35008õ025õ    õ052000000õZ10õVP1õ    õ    õ000000000000õ   õNõ90õ0350õNõ00000000000õNõ99000õARSõVõ0037õ0072õ00000000010000000õ00000020000000000õ0072õ35õ0002õ35008õ025õ    õ052000000õZ10õVP1õ    õ    õ000000000000õ   õNõ";
        Cuenta cuenta = new Cuenta();

        cuenta.setTipoCuentaSinUnificar("2");
        cuenta.setNroCuentaProducto("00234566");
        cuenta.setNroSucursal("0024");
        cuenta.setSucursalPaquete("00123");
        cuenta.setNumeroPaquete("0123456789012");
        PrestamoPermitidoInEntity entity = new PrestamoPermitidoInEntity();
        entity.setCliente(new Cliente());
        entity.setCuenta(cuenta);

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        PrestamoPermitidoOutEntity respuesta = prestamoPermitidoDAO.consultarPrestamosPermitidosPreacordados(entity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

}
