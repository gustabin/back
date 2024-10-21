package ar.com.santanderrio.obp.servicios.trackingtarjetas.dao;

import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.webservice.GestionarWS;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasIn;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasSecurityException_Exception;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasService;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dao.impl.TrackingTarjetasDAOImpl;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.ConsultaTarjetasMonederoOutEntity;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.mock.TrackingTarjetasObjectsMock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {TrackingTarjetasDAOTest.TrackingTarjetasDAOTestConfiguration.class })
public class TrackingTarjetasDAOTest extends IatxBaseDAOTest {
    
    /** The TrackingTarjetasDAO. */
    @Autowired
    @InjectMocks
    private TrackingTarjetasDAO trackingTarjetasDAO;
    
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();

    @Mock
    private TrackingTarjetasService trackingService;
    
    @Mock
    private GestionarWS<TrackingTarjetasService> trackingTarjetasClient;
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Mock
    private IatxComm iatxComm;


    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.trackingtarjetas.dao",
    "ar.com.santanderrio.obp.base.webservice" , "ar.com.santanderrio.obp.servicios.iatx.dao"})
    public static class TrackingTarjetasDAOTestConfiguration {
        /**
         * CambioDomicilioDAO.
         *
         * @return the CambioDomicilioDAO
         */
        @Bean
        public TrackingTarjetasDAO trackingTarjetasDAO() {
            return new TrackingTarjetasDAOImpl();
            
        }
    }
   
    @Before
    public void init() throws DAOException {
        MockitoAnnotations.initMocks(this);

        Mockito.when(trackingTarjetasClient.obtenerPort()).thenReturn(trackingService);
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");

    }
    
    /**
     * getDatosTarjetaMonedero
     * @throws DAOException 
     * @throws IatxException 
     */
    @Test
    public void  getDatosTarjetaMonederoTest() throws DAOException, IatxException {
        String servicio = "CNSCTAMONE";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010301DJAG39  ********00721035000000042017121514520000000000IBF300H8000000000000CNSCTAMONE1000000            01390639    00        00 000000000201712151451470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0046300000000õ005õ041õ5õ0554946223õ3õ06õMONEDERO            õ1õ5036457000000074õPISANO KOZIOL/MARCE     õ32õ041õ5õ0554946223õ3õ06õMONEDERO            õ4õ5036457000000082õPISANO KOZIOL/MARCE     õ32õ066õ5õ0554816063õ3õ06õMONEDERO            õ1õ5036457000000017õGARAY/JORGE DANIEL      õ30õ066õ5õ0554816063õ3õ06õMONEDERO            õ4õ5036457000000025õGARAY/JORGE DANIEL      õ32õ066õ5õ0554816063õ3õ06õMONEDERO            õ1õ5036457000000090õGARAY OJEDA/IGNACIO     õ31õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setErrorCode(0);
        iatxResponse.setTrama(tramaResponse);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        ConsultaTarjetasMonederoOutEntity respuestaDAO = trackingTarjetasDAO.getDatosTarjetaMonedero(cliente, "T");
        Assert.assertNotNull(respuestaDAO);

    }
    
    /**
     * getDatosTarjetaMonedero
     * @throws DAOException 
     * @throws IatxException 
     */
    @Test(expected = DAOException.class)
    public void  getDatosTarjetaMonederoTestError() throws DAOException, IatxException {
        String servicio = "CNSCTAMONE";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010301DJAG39  ********00721035000000042017121514520000000000IBF300H8000000000000CNSCTAMONE1000000            01390639    00        00 000000000201712151451470000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0046300000000õ005õ041õ5õ0554946223õ3õ06õMONEDERO            õ1õ5036457000000074õPISANO KOZIOL/MARCE     õ32õ041õ5õ0554946223õ3õ06õMONEDERO            õ4õ5036457000000082õPISANO KOZIOL/MARCE     õ32õ066õ5õ0554816063õ3õ06õMONEDERO            õ1õ5036457000000017õGARAY/JORGE DANIEL      õ30õ066õ5õ0554816063õ3õ06õMONEDERO            õ4õ5036457000000025õGARAY/JORGE DANIEL      õ32õ066õ5õ0554816063õ3õ06õMONEDERO            õ1õ5036457000000090õGARAY OJEDA/IGNACIO     õ31õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setErrorCode(1);
        iatxResponse.setTrama(tramaResponse);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        ConsultaTarjetasMonederoOutEntity respuestaDAO = trackingTarjetasDAO.getDatosTarjetaMonedero(cliente, "T");
        Assert.assertNotNull(respuestaDAO);

    }
  
    /**
     * consultarTraza
     * @throws DAOException
     * @throws TrackingTarjetasSecurityException_Exception
     */
    @Test
    public void consultarTraza() throws DAOException, TrackingTarjetasSecurityException_Exception {
        Mockito.when(trackingService.consultarTraza(Matchers.any(TrackingTarjetasIn.class))).thenReturn(TrackingTarjetasObjectsMock.obtenerTrackingTarjetasOutOK());
        TrackingTarjetasIn inRequest = null;
        TrackingTarjetasOut respuestaDAO = trackingTarjetasDAO.consultarTraza(inRequest);
        Assert.assertNotNull(respuestaDAO);
       
    }
    
    /**
     * consultarTrazaTestError
     * @throws DAOException
     * @throws TrackingTarjetasSecurityException_Exception
     */
    @Test(expected = DAOException.class)
    public void consultarTrazaTestError() throws DAOException, TrackingTarjetasSecurityException_Exception {
        Mockito.when(trackingService.consultarTraza(Matchers.any(TrackingTarjetasIn.class))).thenThrow(new TrackingTarjetasSecurityException_Exception());
        TrackingTarjetasIn inRequest = null;
        TrackingTarjetasOut respuestaDAO = trackingTarjetasDAO.consultarTraza(inRequest);
        Assert.assertNotNull(respuestaDAO);
    }
    
    
    
    
}

