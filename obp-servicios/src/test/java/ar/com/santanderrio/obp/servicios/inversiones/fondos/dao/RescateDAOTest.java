package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import static org.mockito.Mockito.when;

import ar.com.santanderrio.obp.servicios.api.funds.FundsApi;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import org.beanio.InvalidRecordException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.utils.RestWebClient;
import ar.com.santanderrio.obp.servicios.inversiones.comun.InversionWSHelper;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteRescateEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.FondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.RescateFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.maps.dao.MapServiceDAOImpl;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.SaldoInsuficienteException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;

/**
 * The Class RescateDAOTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        RescateDAOTest.RescateDAOTestConfiguration.class })
@TestPropertySource(properties = { 
		"DB.TIMEOUT = 30000",
		"APP.ENCODING = UTF-8", 
		"FONDOS.CANALTIPO=04",
		"FONDOS.CANALID=0099"
		})
public class RescateDAOTest extends IatxBaseDAOTest {

    /** The fondo DAO. */
    @Autowired
    @InjectMocks
    private RescateDAOImpl rescateDAO;

    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    private FondoInEntity fondoInEntity ;
    
    private RescateFondoInEntity rescateFondoInEntity;

    public static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";

    /**
     * The Class RescateDAOTestConfiguration.
     */
    @Configuration
    public static class RescateDAOTestConfiguration {

        /**
         * Rescate DAO.
         *
         * @return the rescate DAO
         */
        @Bean
        public RescateDAO rescateDAO() {
            return new RescateDAOImpl();
        }
        
        @Bean
        RestWebClient restWebClient() {
            return Mockito.mock(RestWebClient.class);
        }

        @Bean
        InversionWSHelper inversionWSHelper() {
            return Mockito.mock(InversionWSHelper.class);
        }

        @Bean
        FundsApi fundsApi() {
            return Mockito.mock(FundsApi.class);
        }

        @Bean
        SesionParametros SesionParametros() {
            return Mockito.mock(SesionParametros.class);
        }

        @Bean
        Sign sign() {
            return Mockito.mock(Sign.class);
        }
        
        @Bean
        MapServiceDAOImpl mapServiceDAOImpl() {
        	return Mockito.mock(MapServiceDAOImpl.class);
        }

        @Bean
        SesionCliente sesionCliente() {
        	return Mockito.mock(SesionCliente.class);
        }

    }

    
    @Before
    public void init() {
        fondoInEntity = new FondoInEntity();
        fondoInEntity.setCliente(cliente);
        fondoInEntity.setCodigoFondo("007");
        fondoInEntity.setCodigoCliente("00109374933");
        fondoInEntity.setCodigoAgente("001");
        fondoInEntity.setCodigoCanal("991");
        fondoInEntity.setImporteBruto("00000000001200");
        fondoInEntity.setCantidadCuotasPartes("00000000000000");
        fondoInEntity.setFormaPago("02");
        fondoInEntity.setTipoCuenta("09");
        fondoInEntity.setSucCuenta("000");
        fondoInEntity.setNroCuenta("639170");
        fondoInEntity.setMoneda("0");
        fondoInEntity.setNroCertifAReversar("0000000000");
        fondoInEntity.setMontoAReversar("00000000000000");
        fondoInEntity.setImporteRescateComision("00000000000000");
        fondoInEntity.setImporteRescateNeto("00000000001199");
        
        
        rescateFondoInEntity = new RescateFondoInEntity();
        rescateFondoInEntity.setTerminalSafe(rescateFondoInEntity.getTerminalSafe());
        rescateFondoInEntity.setCodigoFondo("007");
        rescateFondoInEntity.setCodigoCliente("00109374933");
        rescateFondoInEntity.setCodigoAgente("001");
        rescateFondoInEntity.setCodigoCanal("991");
        rescateFondoInEntity.setImporteNeto("00000000001200");
        rescateFondoInEntity.setCantidadCuotas("00000000000000");
        rescateFondoInEntity.setFormaDePago("02");
        rescateFondoInEntity.setTipoCuenta("09");
        rescateFondoInEntity.setSucursalCuenta("000");
        rescateFondoInEntity.setNumeroCuenta("639170");
        rescateFondoInEntity.setImpreSolicitud("N");
        rescateFondoInEntity.setMoneda("0");
    }
    /**
     * Comprobante rescate test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void comprobanteRescateTest() throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00855421000000082016101810581800000000IBF27902000000000000RESFCI____1600000            02615492    00        00 000000000201610181058130000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0016700000000õPESOSõ             õ             õSUPER AHORRO $ CUOTAõ0000938106õ00000000001199õ00000000000000õ00000000001199õ00000000229520õ00000000000000õ00000000000000õ1õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);

        ComprobanteRescateEntity respuesta = rescateDAO.comprobanteRescate(fondoInEntity);
        Assert.assertNotNull(respuesta);
    }
    
    
    /***
     * Error code 90000100, importe menor al minimo
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateImporteMenor() throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022290000100õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.confirmacionRescateIATXService(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(ImporteMenorAlMinimoException.class, e.getClass());
        }
    }
    
    /***
     * Error code 99000024, fuera horario
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateFueraHorario() throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022299000024õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.confirmacionRescateIATXService(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(FueraDeHorarioException.class, e.getClass());
        }
    }
    
    /***
     * Error code 10000117, sin operar
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateSinOperarException() throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022210000117õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.confirmacionRescateIATXService(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(CuentaSinOperarException.class, e.getClass());
        }
    }

    /***
     * Error code 99000174, SaldoInsuficienteException
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateSaldoInsuficiente() throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022299000174õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.confirmacionRescateIATXService(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(SaldoInsuficienteException.class, e.getClass());
        }
    }
    
    /***
     * Error code 11111111, error inesperado
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateCodigoErrorInesperado() throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022211111111õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.comprobanteRescate(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    /**
     * iatx exception, time out
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateTimeOut() throws IatxException, DAOException, TimeOutException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException(TIMEOUT_EXCEPTION));
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.comprobanteRescate(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    
    /**
     * iatx exception
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateIATXException() throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException("IatxException"));
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.comprobanteRescate(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    /**
     * InvalidRecordException 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteRescateInvalidRecordException () throws IatxException, DAOException, Exception {
        String servicio = "RESFCI____";
        String version = "160";
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new InvalidRecordException(null, "InvalidRecordException"));
        try {
            ComprobanteRescateEntity respuesta = rescateDAO.comprobanteRescate(fondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    /**
     * Confirmacion rescate test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void simulacionRescateOK() throws IatxException, DAOException, Exception {
        String servicio = "CNSRESFCI_";
        String version = "140";
        String tramaResponse = "200000000000P04HTML0009900010302QLPO92  ********00855421000000072016101810581600000000IBF27901000000000000CNSRESFCI_1400000            02615492    00        00 000000000201610181058110000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0015600000000õPESOSõ             õ             õSUPER AHORRO $ CUOTAõ00000000001199õ00000000000000õ00000000001199õ00000000229520õ00000000000000õ00000000000000õ1õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        RescateFondoOutEntity respuesta = rescateDAO.simulacionRescate(cliente, rescateFondoInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    /**
     * IatxException 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void simulacionRescateIatxException () throws IatxException, DAOException {
        String servicio = "CNSRESFCI_";
        String version = "140";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException ());
        try {
            RescateFondoOutEntity respuesta = rescateDAO.simulacionRescate(cliente, rescateFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

    
    /**
     * RuntimeException 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void simulacionRescateRuntimeException () throws IatxException, DAOException {
        String servicio = "CNSRESFCI_";
        String version = "140";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new RuntimeException());
        try {
            RescateFondoOutEntity respuesta = rescateDAO.simulacionRescate(cliente, rescateFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    /**
     * FueraDeHorarioException errorcode 10000024
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void simulacionRescateFueraDeHorario() throws IatxException, DAOException {
        String servicio = "CNSRESFCI_";
        String version = "140";
        String tramaError = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022210000024õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaError);
          try {
            RescateFondoOutEntity respuesta = rescateDAO.simulacionRescate(cliente, rescateFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    /**
     * codigo error inesperado
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void simulacionRescateErrorInesperado() throws IatxException, DAOException {
        String servicio = "CNSRESFCI_";
        String version = "140";
        String tramaError = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022211111111õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaError);
          try {
            RescateFondoOutEntity respuesta = rescateDAO.simulacionRescate(cliente, rescateFondoInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
}
