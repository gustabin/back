package ar.com.santanderrio.obp.servicios.comun.tarjetas.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.mockito.Mockito.when;
import java.util.Vector;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.dao.impl.ConsultaTarjetasDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;



/**
 * ConsultaTarjetasDAOTest
 * @author Silvina_Luque
 *
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {ConsultaTarjetasDAOTest.ConsultaTarjetasDAOTestConfiguration.class })
public class ConsultaTarjetasDAOTest extends IatxBaseDAOTest {
    
    /** The TrackingTarjetasDAO. */
    @Autowired
    @InjectMocks
    private ConsultaTarjetasDAO consultaTarjetasDAO;
    
    /** The app context. */
    ApplicationContext appContext = new ClassPathXmlApplicationContext();

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    @Mock
    private IatxComm iatxComm;


    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.tarjetas.dao",
    "ar.com.santanderrio.obp.base.webservice" , "ar.com.santanderrio.obp.servicios.iatx.dao"})
    public static class ConsultaTarjetasDAOTestConfiguration {
        /**
         * CambioDomicilioDAO.
         *
         * @return the CambioDomicilioDAO
         */
        @Bean
        public ConsultaTarjetasDAO consultaTarjetasDAO() {
            return new ConsultaTarjetasDAOImpl();
            
        }
    }
   
    @Before 
    public void init() throws DAOException {
        MockitoAnnotations.initMocks(this);
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
     * ConsultaTarjetasTest
     * @throws DAOException 
     * @throws IatxException 
     */
    @Test
    public void  consultaTarjetasTest() throws DAOException, IatxException {
        String servicio = "CNSTJCDATC";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010301DJAG39  ********00370646000000062017122615290300000000IBF307LP000000000000CNSTJCDATC1000000            01390639    00        00 015266391201712261528500000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0069200000000õ0000000000000000õNõ3õ00006942812õ002õ3777920002327170õAEXPõ201õ2õ0000006942812õ01õ20õACTIVA         õ20080313õ2õA127106 õ000000õ      õ00õ2010õ08õ2013õ07õ20100605õATCõ233809õAMAS232 õ õ           õ00000000õ0õ           õARABELI MELINA          õ1õPERSONAL  õ00000000õ00õ                    õ1õSUCURSAL        õ0õ4õAMEXõ00000õ00000õ00000õLõPLATINUM            õ3777920002954580õAEXPõ201õ2õ0000006942812õ02õ20õACTIVA         õ20080529õ2õA131908 õ000000õ      õ00õ2010õ08õ2013õ07õ20100605õATCõ233809õAMAS232 õ õ           õ00000000õ0õ           õPIO MAINQUE             õ1õPERSONAL  õ00000000õ00õ                    õ1õSUCURSAL        õ1õ4õAMEXõ10000õ10000õ10000õLõPLATINUM            õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setErrorCode(0);
        iatxResponse.setTrama(tramaResponse);
        iatxResponse.setIatxBody(buildVectorTrama());
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        ConsultaDatosTarjetasIn inDAO = new ConsultaDatosTarjetasIn();
        inDAO.setMarca("");
        inDAO.setNroCuenta("123412341234");
        inDAO.setTipoCuenta("05");
        inDAO.setCliente(cliente);
        ConsultaDatosTarjetasOut respuestaDAO = consultaTarjetasDAO.consultaDatosTarjetas(inDAO ); 
        Assert.assertNotNull(respuestaDAO);

    }
    
    /**
     * consultaTarjetasDAOErrorTest
     * @throws DAOException
     * @throws IatxException
     */
    @Test(expected = DAOException.class)
    public void  consultaTarjetasDAOErrorTest() throws DAOException, IatxException {
        String servicio = "CNSTJCDATC";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010301DJAG39  ********00370646000000052017122615290300000000IBF307LO000000000000CNSTJCDATC1000000            01390639    00        00 015266390201712261528490000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0101500000000õ0000000000000000õNõ1õ00095915988õ003õ4050710025497038õAVISõ201õ2õ0000095915988õ02õ20õACTIVA         õ20090601õ2õAMAS060 õ000000õ      õ00õ2011õ05õ2014õ04õ20110312õATCõ004550õAMAS232 õ õ           õ00000000õ0õ           õBRISA RENATA            õ1õPERSONAL  õ00000000õ00õ                    õ1õSUCURSAL        õ1õ1õVISAõ00000õ00000õ00000õLõPLATINUM            õ4050710025497046õAVISõ201õ2õ0000095915988õ04õ20õACTIVA         õ20090601õ2õAMAS060 õ000000õ      õ00õ2011õ05õ2014õ04õ20110312õATCõ004550õAMAS232 õ1õNORMAL     õ00000000õ0õ           õPIO MAINQUE             õ1õPERSONAL  õ00000000õ00õ                    õ1õSUCURSAL        õ1õ1õVISAõ10000õ10000õ10000õLõPLATINUM            õ4507990000004905õAVISõ201õ2õ0000095915988õ01õ20õACTIVA         õ20090601õ2õAMAS060 õ000000õ      õ00õ2011õ05õ2014õ04õ20110312õATCõ004550õAMAS232 õ õ           õ00000000õ0õ           õARABELI MELINA          õ1õPERSONAL  õ00000000õ00õ                    õ1õSUCURSAL        õ0õ1õVISAõ00000õ00000õ00000õLõPLATINUM            õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setErrorCode(1);
        iatxResponse.setTrama(tramaResponse);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        ConsultaDatosTarjetasIn inDAO = new ConsultaDatosTarjetasIn();
        inDAO.setMarca("");
        inDAO.setNroCuenta("123412341234");
        inDAO.setTipoCuenta("05");
        inDAO.setCliente(cliente);
        ConsultaDatosTarjetasOut respuestaDAO = consultaTarjetasDAO.consultaDatosTarjetas(inDAO );
        Assert.assertNotNull(respuestaDAO);

    }
    /**
     * buildVectorTrama
     * @return
     */
    private Vector<String> buildVectorTrama() {
        Vector<String> vector = new Vector<String>();
        vector.add("00000000");
        vector.add("0000000000000000");
        vector.add("N");
        vector.add("3");
        vector.add("3");
        vector.add("001");
        for(int x = 0; x < 28 ; x++) {
            vector.add("22332321312");
        }
        return vector;
    }
   
    
}

