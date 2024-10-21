/**
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.fondos.dao;

import static org.mockito.Mockito.when;

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
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.inversiones.exception.ImporteMenorAlMinimoException;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.ComprobanteTransferenciaFondoOutEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaInEntity;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.TransferenciaOutEntity;
import ar.com.santanderrio.obp.servicios.transferencias.exception.CuentaSinOperarException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.FueraDeHorarioException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TimeOutException;
import ar.com.santanderrio.obp.servicios.transferencias.exception.TransferenciaBloqueadaException;

/**
 * The Class TransferenciaFondoDAOTest.
 *
 * @author
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        TransferenciaFondoDAOTest.TransferenciaFondoDAOTestConfiguration.class })
public class TransferenciaFondoDAOTest extends IatxBaseDAOTest {

    public static final String TIMEOUT_EXCEPTION = "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ";
    private static final int FINALIZAR_SUSCRIPCION_FUERA_HORARIO_CODIGO_RETORNO = 99002400;
    private static final int CODIGO_ERROR_CUENTA_SIN_OPERAR = 10000117;
    /** The transferencia DAO. */
    @Autowired
    @InjectMocks
    private TransferenciaFondoDAO transferenciaDAO;

    
    private TransferenciaInEntity transferenciaInEntity;
    
    private ComprobanteTransferenciaFondoInEntity comprobanteTransferenciaFondoInEntity;
    /**
     * The Class TransferenciaDAOTestConfiguration.
     */
    @Configuration
    public static class TransferenciaFondoDAOTestConfiguration {

        /**
         * Transferencia DAO.
         *
         * @return the transferencia fondo DAO
         */
        @Bean
        public TransferenciaFondoDAO TransferenciaDAO() {
            return new TransferenciaFondoDAOImpl();
        }
    }

    
    @Before
    public void init() throws ServiceException {
    
    transferenciaInEntity = new TransferenciaInEntity();
    transferenciaInEntity.setCliente(new Cliente());
    transferenciaInEntity.setTerminalSafe("");
    transferenciaInEntity.setCodigoFondo("110");
    transferenciaInEntity.setCodigoCliente("00116322952");
    transferenciaInEntity.setCodigoAgente("001");
    transferenciaInEntity.setCodigoCanal("991");
    transferenciaInEntity.setImporteNeto("00000001542334");
    transferenciaInEntity.setPorcentComision("0000");
    transferenciaInEntity.setImprSolicitud("N");
    transferenciaInEntity.setCodigoCustodiaActual("4");
    transferenciaInEntity.setCodigoCustodiaAnterior("4");
    transferenciaInEntity.setCodigoFondoDest("072");
    transferenciaInEntity.setPorcentComisionD("0000");
    
    comprobanteTransferenciaFondoInEntity = new ComprobanteTransferenciaFondoInEntity();
    comprobanteTransferenciaFondoInEntity.setCliente(new Cliente());
    comprobanteTransferenciaFondoInEntity.setTerminalSafe("");
    comprobanteTransferenciaFondoInEntity.setCodigoFondo("002");
    comprobanteTransferenciaFondoInEntity.setCodigoCliente("00123981588");
    comprobanteTransferenciaFondoInEntity.setCodigoAgente("001");
    comprobanteTransferenciaFondoInEntity.setCodigoCanal("991");
    comprobanteTransferenciaFondoInEntity.setImporteNeto("00000002099999");
    comprobanteTransferenciaFondoInEntity.setPorcentajeComis("0000");
    comprobanteTransferenciaFondoInEntity.setImpreSolicitud("N");
    comprobanteTransferenciaFondoInEntity.setCodigoCustodiaActual("4");
    comprobanteTransferenciaFondoInEntity.setCodigoCustodiaAnterior("4");
    comprobanteTransferenciaFondoInEntity.setCodigoFondoDest("001");
    comprobanteTransferenciaFondoInEntity.setPorcentajeComisD("0000");
    comprobanteTransferenciaFondoInEntity.setTipoCuenta("00");
    comprobanteTransferenciaFondoInEntity.setSucursalCuenta("000");
    comprobanteTransferenciaFondoInEntity.setNumeroCuenta("0000000000");
    comprobanteTransferenciaFondoInEntity.setNumeroCertificadoReservar("          ");
    comprobanteTransferenciaFondoInEntity.setCantCuotasPartes("00000000000000");
    
    }
    
    
    /**
     * Transferir.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws DAOException
     *             the DAO exception
     */
    @Test
    public void transferir() throws IatxException, DAOException {
        String servicio = "CNSTRFFCI_";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010360148021  ********00598305000000092016100412074600000000IBF14156000000000000CNSTRFFCI_1200000            60148021    00        00 000000000201610041207410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0020000000000õPESOSõ00000001542334õ00000001542334õ00000215554716õ0000õ00000000000000õ00õACTIVA       õSIN PAGAR    õ000õ0000õPENDIENTE õTRANSFERENõSUPER FONDO COMBINADõPESOSõSUPERGESTION MIX VI CUOTA A   õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        TransferenciaOutEntity respuesta = transferenciaDAO.transferir(transferenciaInEntity);
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    
    /**
     * Transferir error FUERA_HORARIO_CODIGO_RETORNO = 10000024
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void transferirErrorFueraHorario() throws IatxException, DAOException {
        String servicio = "CNSTRFFCI_";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010360148021  ********00598305000000092016100412074600000000IBF14156000000000000CNSTRFFCI_1200000            60148021    00        00 000000000201610041207410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0020010000024õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            TransferenciaOutEntity respuesta = transferenciaDAO.transferir(transferenciaInEntity);
        } catch (Exception e) {
            Assert.assertEquals(FueraDeHorarioException.class, e.getClass());
        }
    }
    
    
    /**
     * Transferir error CODIGO_ERROR_IMPORTE_MENOR_AL_MINIMO = 10000100;
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void transferirErrorImporteMenorMinimo() throws IatxException, DAOException {
        String servicio = "CNSTRFFCI_";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010360148021  ********00598305000000092016100412074600000000IBF14156000000000000CNSTRFFCI_1200000            60148021    00        00 000000000201610041207410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0020010000100õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            TransferenciaOutEntity respuesta = transferenciaDAO.transferir(transferenciaInEntity);
        } catch (Exception e) {
            Assert.assertEquals(ImporteMenorAlMinimoException.class, e.getClass());
        }
    }
    
    
    /**
     * Transferir error SUSCRIPCIONES_BLOQUEADAS = 10000087;
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void transferirErrorBloqueado() throws IatxException, DAOException {
        String servicio = "CNSTRFFCI_";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010360148021  ********00598305000000092016100412074600000000IBF14156000000000000CNSTRFFCI_1200000            60148021    00        00 000000000201610041207410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0020010000087õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            TransferenciaOutEntity respuesta = transferenciaDAO.transferir(transferenciaInEntity);
        } catch (Exception e) {
            Assert.assertEquals(TransferenciaBloqueadaException.class, e.getClass());
        }
    }
    
    /**
     * Transferir error desconocido
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void transferirErrorDesconocido() throws IatxException, DAOException {
        String servicio = "CNSTRFFCI_";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010360148021  ********00598305000000092016100412074600000000IBF14156000000000000CNSTRFFCI_1200000            60148021    00        00 000000000201610041207410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0020011111111õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            TransferenciaOutEntity respuesta = transferenciaDAO.transferir(transferenciaInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    
    /**
     * Transferir IatxException 
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void transferirIatxException () throws IatxException, DAOException {
        String servicio = "CNSTRFFCI_";
        String version = "120";
        String tramaResponse = "200000000000P04HTML0009900010360148021  ********00598305000000092016100412074600000000IBF14156000000000000CNSTRFFCI_1200000            60148021    00        00 000000000201610041207410000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0020011111111õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException());
        try {
            TransferenciaOutEntity respuesta = transferenciaDAO.transferir(transferenciaInEntity);
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    
    
    /**
     * finalizar
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void comprobanteFinalizar() throws IatxException, DAOException {
        String servicio = "TRFFCI____";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022200000000õPESOSõ00000002099999õ00000002099999õ00000125460334õ0000õ00000000000000õ04õACTIVA       õSIN PAGAR    õ000õ0001100518õ0000õPENDIENTE õTRANSFERENõSUPERFONDO RENTA $ CõPESOSõSUPERFONDO ACCIONES CUOTA A   õ0000102063õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        ComprobanteTransferenciaFondoOutEntity respuesta = transferenciaDAO.obtenerComprobante(comprobanteTransferenciaFondoInEntity); 
        Assert.assertNotNull(respuesta);
        Mockito.verify(iatxSender).send(Matchers.argThat(new IatxMatcher(servicio, version)));
    }
    
    
    /**
     * Error Timeout
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void obtenerComprobanteTimeOut () throws IatxException, DAOException {
        String servicio = "TRFFCI____";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022200000000õPESOSõ00000002099999õ00000002099999õ00000125460334õ0000õ00000000000000õ04õACTIVA       õSIN PAGAR    õ000õ0001100518õ0000õPENDIENTE õTRANSFERENõSUPERFONDO RENTA $ CõPESOSõSUPERFONDO ACCIONES CUOTA A   õ0000102063õ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException (TIMEOUT_EXCEPTION));
        try {
            ComprobanteTransferenciaFondoOutEntity respuesta = transferenciaDAO.obtenerComprobante(comprobanteTransferenciaFondoInEntity); 
        } catch (Exception e) {
            Assert.assertEquals(TimeOutException.class, e.getClass());
        }
    }
    
    
    /**
     * Error IatxException
     * @throws IatxException
     * @throws DAOException
     */
    @Test
    public void obtenerComprobanteIatxException() throws IatxException, DAOException {
        String servicio = "TRFFCI____";
        String version = "130";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenThrow(new IatxException ("IATX exception"));
        try {
            ComprobanteTransferenciaFondoOutEntity respuesta = transferenciaDAO.obtenerComprobante(comprobanteTransferenciaFondoInEntity); 
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }
    
    
    
    
    @Test
    public void obtenerComprobanteFueraDeHorarioException() throws IatxException, DAOException {
        String servicio = "TRFFCI____";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022299002400õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteTransferenciaFondoOutEntity respuesta = transferenciaDAO.obtenerComprobante(comprobanteTransferenciaFondoInEntity); 
        } catch (Exception e) {
            Assert.assertEquals(FueraDeHorarioException.class, e.getClass());
        }
    }
    
    @Test
    public void obtenerComprobanteCuentaSinOperarException() throws IatxException, DAOException {
        String servicio = "TRFFCI____";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022210000117õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteTransferenciaFondoOutEntity respuesta = transferenciaDAO.obtenerComprobante(comprobanteTransferenciaFondoInEntity); 
        } catch (Exception e) {
            Assert.assertEquals(CuentaSinOperarException.class, e.getClass());
        }
    }
    
    @Test
    public void obtenerComprobanteErrorInesperadoIatx() throws IatxException, DAOException {
        String servicio = "TRFFCI____";
        String version = "130";
        String tramaResponse = "200000000000P04HTML0009900010310376487  ********00323728000000062017013111593900000000IBF13645000000000000TRFFCI____1300000            10376487    00        00 000000000201701311159330000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0022211111111õFCIõ01õASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASASõ";
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(servicio, version)))).thenReturn(tramaResponse);
        try {
            ComprobanteTransferenciaFondoOutEntity respuesta = transferenciaDAO.obtenerComprobante(comprobanteTransferenciaFondoInEntity); 
        } catch (Exception e) {
            Assert.assertEquals(DAOException.class, e.getClass());
        }
    }

}
