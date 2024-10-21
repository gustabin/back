/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaTraspasoManualOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.impl.TraspasoManualDAOImpl;

/**
 * The Class TraspasoManual.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        TraspasoManualDAOTest.TraspasoManualDAOTestConfiguration.class })
public class TraspasoManualDAOTest extends IatxBaseDAOTest {

    /** The detalleCuenta DAO. */
    @Autowired
    private TraspasoManualDAOImpl traspasoManualDAO;

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    /**
     * Inits the.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Before
    public void init() throws ServiceException {
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("CONSTANCIO PERCY");
        cliente.setApellido1("MILANDO");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);        
    }
    
    /**
     * The Class DetalleCuentaDAOTestConfiguration.
     */
    @Configuration
    public static class TraspasoManualDAOTestConfiguration {

        
        /**
         * DetalleCuenta DAO.
         *
         * @return the DetalleCuenta DAO
         */
        @Bean
        public TraspasoManualDAO traspasoManualDAO() {
            return new TraspasoManualDAOImpl();
        }
    }
    
    @Test
    public void consultaDetalleCuenta()
            throws IatxException, DAOException, CredencialesException {
        ConsultaTraspasoManualInEntity consultaTraspasoManualInEntity = new ConsultaTraspasoManualInEntity();
        String servicio = "TRFCTACRU_";
        String version = "100";
        
        String sucursalCuenta = "0007";
        String nroCuenta = "123";
        String indDebitoCreditoCA = "D";
        String indDebitoCreditoCC = "D";
        String importeDebitoCredito = "12345678";
        String nio = "123123123";
        String centroOrigen = "1234";
        consultaTraspasoManualInEntity.setCliente(cliente); 
        consultaTraspasoManualInEntity.setCentroOrigen(centroOrigen);
        consultaTraspasoManualInEntity.setIndDebitoCreditoCA(indDebitoCreditoCA);
        consultaTraspasoManualInEntity.setIndDebitoCreditoCC(indDebitoCreditoCC);
        consultaTraspasoManualInEntity.setImporteDebitoCredito(importeDebitoCredito);
        consultaTraspasoManualInEntity.setNio(nio);
        consultaTraspasoManualInEntity.setNroCuenta(nroCuenta);
        consultaTraspasoManualInEntity.setSucursalCuenta(sucursalCuenta);
        
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00833308000000262017011614450000000000IBF29261000000000000TRFCTACRU_1000000            02615492    00        00 014411555201701161444540000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0003900000000õBGX87I2017-01-1614445482õ8550õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaTraspasoManualOutEntity consultaTraspasoManualOutEntity = traspasoManualDAO.ejecutarTraspasoManual(consultaTraspasoManualInEntity);
        Assert.assertNotNull(consultaTraspasoManualOutEntity);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));

    }
}
