package ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
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
import ar.com.santanderrio.obp.servicios.comun.merlin.dao.MerlinDAO;
import ar.com.santanderrio.obp.servicios.comun.merlin.dao.impl.MerlinDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.DatosMerlinInEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.entities.ResultadoMerlinEntity;
import ar.com.santanderrio.obp.servicios.comun.merlin.exception.MerlinError1Exception;


@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
		MerlinDAOTest.MerlinDAOTestConfiguration.class })
public class MerlinDAOTest extends IatxBaseDAOTest {
	
    /** The fondo DAO. */
    @Autowired
    private MerlinDAO merlinDAO;

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
    
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.tarjetacreditoadicional.dao" })
    public static class MerlinDAOTestConfiguration {
        @Bean
        MerlinDAO merlinDAO() {
            return new MerlinDAOImpl();
        }
    }
	
    @Test
    public void domicilioUnicoExisteOKTest() throws DAOException, IatxException, MerlinError1Exception {
        // Given
    	String servicio = "CNSDMERLIN";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300FPQG25  ********00544807000000032017080511134300000000IBF0009D000000000000CNSDMERLIN1000000            00556625    00        00 011110116201708051113340000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0018400000000õC1437IYKõ01õCAPITAL                       õ1437õ                              õPROF DR PEDRO CHUTRO          õ0000003135õ1   õ1    õ                    õOKõ õ õ õ õ õ õ õ õ00õ";
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity ();
        datosMerlinInEntity.setCliente(cliente);
        datosMerlinInEntity.setNup("556625");
        datosMerlinInEntity.setTipoDeDomicilio("");
        datosMerlinInEntity.setNombreCalle("PROF DR PEDRO CHUTRO");
        datosMerlinInEntity.setNumeroBloque("3135");
        datosMerlinInEntity.setCodigoPostal("1437");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("Parque Patricios");
        datosMerlinInEntity.setBarrio("");
        datosMerlinInEntity.setPiso("1");
        datosMerlinInEntity.setDepartamento("1");
        datosMerlinInEntity.setTimestamp("");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("12345678");
        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        ResultadoMerlinEntity respuesta = merlinDAO.getResultadoMerlin(datosMerlinInEntity);
        // Expected
        Assert.assertEquals("00000000", respuesta.getCodigoRetornoExtendido());
        Assert.assertEquals(0, respuesta.getCantidadDeDudas().intValue());
    }

    @Test
    public void domicilioDudosoConAlternativas() throws DAOException, IatxException, MerlinError1Exception {
        // Given
        String servicio = "CNSDMERLIN";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00156550000000262018072015584600000000IBF010QY000000000000CNSDMERLIN1000000            00276937    00        00 015504916201807201558260000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0080320000006õDPEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õNORMALIZACION DUDOSA - VERIFIQUE ALTERNATIVAS                                   õ        õ  õ                              õ    õ                              õ                              õ0000001452õ5   õA    õ                    õDUõ õ õ õ õ õ õ õ õ02õ01õ01õCAPITAL                       õ                              õSAN JOSE                      õ0000000001õ0000002100õC1136AAFõ02õ01õCAPITAL                       õ                              õAV JOSE M MORENO              õ0000000001õ0000002000õC1424ABOõ";
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        datosMerlinInEntity.setCliente(cliente);
        datosMerlinInEntity.setNup("556625");
        datosMerlinInEntity.setTipoDeDomicilio("");
        datosMerlinInEntity.setNombreCalle("nose");
        datosMerlinInEntity.setNumeroBloque("1452");
        datosMerlinInEntity.setCodigoPostal("1587");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("Palermo");
        datosMerlinInEntity.setBarrio("");
        datosMerlinInEntity.setPiso("5");
        datosMerlinInEntity.setDepartamento("A");
        datosMerlinInEntity.setTimestamp("");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("12345678");
        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        ResultadoMerlinEntity respuesta = merlinDAO.busquedaMerlin(datosMerlinInEntity);
        // Expected
        Assert.assertEquals("20000006", respuesta.getCodigoRetornoExtendido());
        Assert.assertEquals(2, respuesta.getCantidadDeDudas().intValue());
    }

    @Test
    public void domicilioCambioDeDomicilio() throws DAOException, IatxException, MerlinError1Exception {
        // Given
        String servicio = "CNSDMERLIN";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00945254000000142018071816534900000000IBF01FSS000000000000CNSDMERLIN1000000            00276937    00        00 016570493201807181653280000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0054320000004õDPEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õCAMBIO DE DOMICILIO - ANALIZAR INDICADORES                                      õC1136AAFõ01õCAPITAL                       õ1136õ                              õSAN JOSE                      õ0000001452õ5   õA    õ                    õCOõ õ õ õ õ õ õ õ õ00õ";
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        datosMerlinInEntity.setCliente(cliente);
        datosMerlinInEntity.setNup("556625");
        datosMerlinInEntity.setTipoDeDomicilio("");
        datosMerlinInEntity.setNombreCalle("SAN JOSE");
        datosMerlinInEntity.setNumeroBloque("1452");
        datosMerlinInEntity.setCodigoPostal("1136");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("CAPITAL");
        datosMerlinInEntity.setBarrio("");
        datosMerlinInEntity.setPiso("5");
        datosMerlinInEntity.setDepartamento("A");
        datosMerlinInEntity.setTimestamp("");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("23213124");
        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        ResultadoMerlinEntity respuesta = merlinDAO.busquedaMerlin(datosMerlinInEntity);
        // Expected
        Assert.assertEquals("20000004", respuesta.getCodigoRetornoExtendido());
        Assert.assertEquals(0, respuesta.getCantidadDeDudas().intValue());
    }
    
    @Test
    public void domicilioDatoInvalido() throws DAOException, IatxException, MerlinError1Exception {
        // Given
        String servicio = "CNSDMERLIN";
        String version = "100";
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00945253000000152018071816140800000000IBF01CBF000000000000CNSDMERLIN1000000            00276937    00        00 016163688201807181613480000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0067320000017õDPEõ03õNo es posible acceder a la informacion solicitada. Por favor, reintente mas tarde.                                                                                                                                                                            õ               õNO SE PUEDE NORMALIZAR - VERIFIQUE ALTERNATIVAS                                 õ        õ  õ                              õ    õ                              õ                              õ0000099999õ5   õA    õ                    õNOõYõ õ õ õ õ õ õ õ01õ01õ01õCAPITAL                       õ                              õSAN JOSE                      õ0000000001õ0000002100õAI      õ";
        DatosMerlinInEntity datosMerlinInEntity = new DatosMerlinInEntity();
        datosMerlinInEntity.setCliente(cliente);
        datosMerlinInEntity.setNup("556625");
        datosMerlinInEntity.setTipoDeDomicilio("");
        datosMerlinInEntity.setNombreCalle("SAN JOSE");
        datosMerlinInEntity.setNumeroBloque("99999");
        datosMerlinInEntity.setCodigoPostal("1136");
        datosMerlinInEntity.setProvincia("01");
        datosMerlinInEntity.setLocalidad("CAPITAL");
        datosMerlinInEntity.setBarrio("");
        datosMerlinInEntity.setPiso("5");
        datosMerlinInEntity.setDepartamento("A");
        datosMerlinInEntity.setTimestamp("");
        datosMerlinInEntity.setTelediscado("11");
        datosMerlinInEntity.setTelefono("23213124");
        // When
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        // Then
        ResultadoMerlinEntity respuesta = merlinDAO.getResultadoMerlin(datosMerlinInEntity);
        // Expected
        Assert.assertEquals("20000017", respuesta.getCodigoRetornoExtendido());
        Assert.assertEquals(1, respuesta.getCantidadDeDudas().intValue());
    }
    
}