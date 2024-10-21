package ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
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
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.impl.CambioDireccionamientoDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaCambioDireccionamientoInEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        CambioDireccionamientoDAOTest.CambioDireccionamientoDAOTestConfiguration.class })
public class CambioDireccionamientoDAOTest extends IatxBaseDAOTest {
    
    /** The fondo DAO. */
    @Autowired
    @InjectMocks
    private CambioDireccionamientoDAO cambioDireccionamientoDAO;

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
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
    
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao" })
    public static class CambioDireccionamientoDAOTestConfiguration {
        @Bean
        CambioDireccionamientoDAO cambioDireccionamientoDAO() {
            return new CambioDireccionamientoDAOImpl();
        }
        @Bean
        PackagesApi packagesApi(){
            return Mockito.mock(PackagesApi.class);
        }

    }
    
    @Test
    public void consultarPaquetesTest() throws IatxException, DAOException {
        String servicio = "CMBCTAINDI";
        String version = "100";
        String tramaResponse ="200000000000P04HTML0009900010304LPPM26  ********00621744000000112016122616331500000000IBF22561000000000000CMBCTAINDI1000000            04155226    00        00 016316237201612261633090000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900000000õ";
        ConsultaCambioDireccionamientoInEntity consultaCambioDireccionamientoInEntity = new ConsultaCambioDireccionamientoInEntity();
        
        consultaCambioDireccionamientoInEntity.setCliente(cliente);
        consultaCambioDireccionamientoInEntity.setIndicadorDireccionaFondos("1");
        consultaCambioDireccionamientoInEntity.setNumeroPaquete("123456789012345");
        consultaCambioDireccionamientoInEntity.setSucursalPaquete("1234");
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        cambioDireccionamientoDAO.cambiarDireccionamiento(consultaCambioDireccionamientoInEntity);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
}
