/**
 * 
 */
package ar.com.santanderrio.obp.servicios.prestamos.dao;

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
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.prestamos.dao.impl.CalificacionCrediticiaDAOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.entity.CalificacionCrediticiaOutEntity;

/**
 * The Class CalificacionCrediticiaDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CalificacionCrediticiaDAOTest.CalificacionCrediticiaDAOTestConfiguration.class })
public class CalificacionCrediticiaDAOTest extends IatxBaseDAOTest {

    /** The calificacion crediticia DAO. */
    @Autowired
    @InjectMocks
    private CalificacionCrediticiaDAO calificacionCrediticiaDAO;

    /**
     * The Class CalificacionCrediticiaDAOTestConfiguration.
     */
    @Configuration
    public static class CalificacionCrediticiaDAOTestConfiguration {

        /**
         * Calificacion crediticia DAO.
         *
         * @return the calificacion crediticia DAO
         */
        @Bean
        public CalificacionCrediticiaDAO calificacionCrediticiaDAO() {
            return new CalificacionCrediticiaDAOImpl();
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
    @Test
    public void consultar() throws IatxException, DAOException {
        String servicio = "CNSPMOCACR";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010390229769  ********00311044000000102016090911370700000000IBF22402000000000000CNSPMOCACR1300000            90229769    00        00 011323916201609091137030000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0033200000000õ000000000000033õ000000000000199õ000000000000278000õ000000000000000000õ003500õ001200õ000500õ00000001801200õ01070õ00000003332747õ00000000900839õ00000000219116õ00000000059227õ001õOK(APROBADO)                                      õ000000000000117õ000000000600000õ000000003600000õ000000005000000õ000000000000000õ000000002100000õ"; // ej
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     // 0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0005310000000õ00137600000õ000000000000000õ000000000000000õ";
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("111111");
        cuenta.setNroSucursal("11");
        cuenta.setTipoCuentaSinUnificar("01");

        Cliente cliente = new Cliente();
        cuenta.setCliente(cliente);

        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        CalificacionCrediticiaOutEntity respuesta = calificacionCrediticiaDAO.obtenerSituacionCrediticia(cuenta, "C");
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }

}
