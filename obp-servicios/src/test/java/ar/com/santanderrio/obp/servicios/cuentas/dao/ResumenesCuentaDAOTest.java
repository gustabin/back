package ar.com.santanderrio.obp.servicios.cuentas.dao;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.cambiogrupoafiinidad.entities.ConsultaDatosTarjetaInEntity;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.cuentas.dao.impl.ResumenesCuentaDAOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.CuentaOnDemandDTO;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ResumenesCuentaDAOTest.ResumenesCuentaDAOTestConfiguration.class })
public class ResumenesCuentaDAOTest extends IatxBaseDAOTest{

	@Autowired
    @InjectMocks
    private ResumenesCuentaDAO resumenesCuentaDAO;
	
	/** The cliente. */
    private Cliente cliente = new Cliente();
	
	@Before 
    public void init() throws DAOException {
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Constancio");
        cliente.setApellido1("Percy");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");

    }
	
	@Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.tarjetas.dao",
    "ar.com.santanderrio.obp.base.webservice" , "ar.com.santanderrio.obp.servicios.iatx.dao"})
    public static class ResumenesCuentaDAOTestConfiguration {
        /**
         * CambioDomicilioDAO.
         *
         * @return the CambioDomicilioDAO
         */
        @Bean
        public ResumenesCuentaDAO resumenesCuentaDAOTest() {
            return new ResumenesCuentaDAOImpl();
            
        }
    }
	
	@Test
    public void  consultaCuentaXNupConPagosOK() throws DAOException, IatxException {
        String servicio = "CNSCTAXNUP";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0001150010305438603  ********DRDXhNq8000000102018073016343300000000IBF2CV5T000000000000CNSCTAXNUP1000000            00199863    00        0  016369482201807301634130000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0051320000143õAZBõ03õLa operacion solicitada no puede ser realizada. Por favor, reintente mas tarde.                                                                                                                                                                               õ               õZBA0143-FIN DE DATOS                                                            õ003õ0072õ0201õ000000030973õTIõ05õ1001õ00õARSõNõMõSõ0072õ0201õ000000031945õTIõ05õ1004õ00õARSõNõMõNõ0072õ0201õ000000045153õTIõ06õ1002õ03õUSDõNõMõNõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaDatosTarjetaInEntity inDAO = new ConsultaDatosTarjetaInEntity();
        inDAO.setMarca("");
        inDAO.setNroCuenta("123412341234");
        inDAO.setTipoCuenta("05");
        inDAO.setCliente(cliente);
        List<CuentaOnDemandDTO> respuestaDAO = resumenesCuentaDAO.ejecutarCTAXNUP(cliente);
        Assert.assertEquals(3, respuestaDAO.size());
    }
	
	@Test
    public void  consultaCuentaXNupSinPagosOK() throws DAOException, IatxException {
        String servicio = "CNSCTAXNUP";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010320064145  ********00305579000000022018073015231200000000IBF1YNMT000000000000CNSCTAXNUP1000000            20064145    00        00 015280228201807301522510000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0037220000143õAZBõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õZBA0143-FIN DE DATOS                                                            õ000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaDatosTarjetaInEntity inDAO = new ConsultaDatosTarjetaInEntity();
        inDAO.setMarca("");
        inDAO.setNroCuenta("123412341234");
        inDAO.setTipoCuenta("05");
        inDAO.setCliente(cliente);
        List<CuentaOnDemandDTO> respuestaDAO = resumenesCuentaDAO.ejecutarCTAXNUP(cliente);
        Assert.assertTrue(respuestaDAO.isEmpty());
    }
	
	@Test
    public void  consultaCuentaXNupERROR() throws DAOException, IatxException {
        String servicio = "CNSCTAXNUP";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010320064145  ********00305579000000022018073015231200000000IBF1YNMT000000000000CNSCTAXNUP1000000            20064145    00        00 015280228201807301522510000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0037220000143õAZBõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õZBA0143-FIN DE DATOS                                                            õ000õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ConsultaDatosTarjetaInEntity inDAO = new ConsultaDatosTarjetaInEntity();
        inDAO.setMarca("");
        inDAO.setNroCuenta("123412341234");
        inDAO.setTipoCuenta("05");
        inDAO.setCliente(cliente);
        List<CuentaOnDemandDTO> respuestaDAO = resumenesCuentaDAO.ejecutarCTAXNUP(cliente);
        Assert.assertTrue(respuestaDAO.isEmpty());
    }
	
}