package ar.com.santanderrio.obp.servicios.descuento.cheques.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.accounts.PackagesApi;
import org.junit.Assert;
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
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.dao.ArchivoDeRecursosDAO;
import ar.com.santanderrio.obp.servicios.comun.dao.impl.ArchivoDeRecursosDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.dao.impl.ClienteHabilitadoChequesDAOImpl;
import ar.com.santanderrio.obp.servicios.descuento.cheques.entities.DatosCesionEntity;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ClienteHabilitadoChequesDAOTest.ClienteHabilitadoChequesDAOTestConfiguration.class })
public class ClienteHabilitadoChequesDAOTest extends IatxBaseDAOTest {

    @Autowired
    ClienteHabilitadoChequesDAO clienteHabilitadoChequesDAO;
    
    @InjectMocks
    ArchivoDeRecursosDAO recursos = new ArchivoDeRecursosDAOImpl();

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
     * The Class AgendaDestinatarioDAOTest.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.descuento.cheques.dao",
            "ar.com.santanderrio.obp.base.comun" })
    public static class ClienteHabilitadoChequesDAOTestConfiguration {
        @Bean
        ClienteHabilitadoChequesDAO clienteHabilitadoChequesDAO() {
            return new ClienteHabilitadoChequesDAOImpl();
        }
        
        @Bean
        ArchivoDeRecursosDAO archivoDeRecursosDAO() {
            return new ArchivoDeRecursosDAOImpl();
        }

        @Bean
        PackagesApi packagesApi(){
            return Mockito.mock(PackagesApi.class);
        }
    }

    @Test
    public void obtenerHabilitadoCesionOkTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00202857000000062017110717250500000000IBF00CPU000000000000CNSCLICALO1000000            03682408    00        00 017238011201711071724560000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0004500000000õSõ000000003800000õ000000003800000õ õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);
        
        DatosCesionEntity  datosCesion = clienteHabilitadoChequesDAO.obtenerHabilitadoCesion(cliente);
        
        Assert.assertTrue(datosCesion!=null);
    }
    
    @Test
    public void obtenerHabilitadoCesionErrorTest() throws IatxException, DAOException {
        String tramaResponsePrimera = "200000000000P04HTML0009900010303GSME08  ********00202857000000062017110717250500000000IBF00CPU000000000000CNSCLICALO1000000            03682408    00        00 017238011201711071724560000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0036710000088õAZYõ03õExisten cheques cuya fecha de pago no se encuentra entre el plazo mínimo o Maximo permitido.(Menor a 48 hs hábiles o mayor a 365 días corridos).                                                                                                              õFEC. FUERA RANGõZYE0088 - FECHA PAGO DEL CH 60766833 FUERA DEL PLAZO PERMITIDO (-48HS O +365DS)õ";

        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenReturn(tramaResponsePrimera);
        
        DatosCesionEntity  datosCesion = clienteHabilitadoChequesDAO.obtenerHabilitadoCesion(cliente);
        
        Assert.assertTrue(datosCesion!=null);
    }
    
    @Test(expected=DAOException.class)
    public void obtenerHabilitadoCesionExceptionTest() throws IatxException, DAOException {
        when(iatxSender.send(Matchers.any(IatxRequest.class))).thenThrow(new IatxException());
        clienteHabilitadoChequesDAO.obtenerHabilitadoCesion(cliente);
    }

}
