package ar.com.santanderrio.obp.servicios.tarjetas.dao;
import static org.mockito.Mockito.when;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.iatx.IatxMatcher;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxComm;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.impl.StopDebitTarjetasDAOImpl;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

/**
 *
 * @author mariano.g.pulera
 *  
 **/
public class StopDebitTarjetasDAOIT extends IatxBaseDAOTest {

    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private LegalDAO legalDAO;
    
    @Mock
    private MensajeBO messageBO;
    
	@Mock
    private IatxComm iatxComm;
    
    private static final String NOMBRE_SERVICIO = "STPDEB____";
    
    private static final String VERSION_SERVICIO = "100";
    
    private static final String TARJETA_CREDITO = "VISA XXXX-1234";
    
    @InjectMocks
    private StopDebitTarjetasDAOImpl stopDebitTarjetasDAO = new StopDebitTarjetasDAOImpl();
    
	
    @Before
    public void setUp() throws Exception {
	    Mockito.when(messageBO.obtenerMensajePorCodigo("1261"))
	    .thenReturn(MensajeMock.completarInfoMensaje("1261", "1261"));
    }
    
    @Test
    public void realizarStopDebitTarjetaOK() throws IatxException, DAOException {
        
        //When
        String tramaResponse = "200000000000P04HTML0009900010340064766  ********00779185000000292016042815070700000000IBF31745000015006792STPDEB____1000000            40064766    00        00 015006792201604281507370000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900000000õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosStopDebit datos = crearDatos();
        datos.setTipoCuenta("00");
 
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        iatxResponse.setErrorCode(0);
        iatxResponse.setTrama(tramaResponse);
        iatxResponse.setIatxBody(buildVectorTrama());
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(NOMBRE_SERVICIO, VERSION_SERVICIO)))).thenReturn(tramaResponse);

        //Then
        StopDebitOut respuesta = stopDebitTarjetasDAO.realizarStopDebitTarjeta(cliente, datos);

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("OK", respuesta.getResultado());
    }
    
    
    @Test
    public void realizarStopDebitTarjetaError() throws IatxException, DAOException {
        
        //When
        String tramaResponse = "200000000000P04HTML0009900010340064766  ********00779185000000292016042815070700000000IBF31745000015006792STPDEB____1000000            40064766    00        00 015006792201604281507370000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900463626õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosStopDebit datos = crearDatos();
        
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        iatxResponse.setErrorCode(-1);
        iatxResponse.setTrama(tramaResponse);
        iatxResponse.setIatxBody(buildVectorTrama());
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(NOMBRE_SERVICIO, VERSION_SERVICIO)))).thenReturn(tramaResponse);

        //Then
        StopDebitOut respuesta = stopDebitTarjetasDAO.realizarStopDebitTarjeta(cliente, datos);

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals("ERROR", respuesta.getResultado());
    
    }
    
    
    @SuppressWarnings("unchecked")
    @Test (expected = DAOException.class)
    public void realizarStopDebitTarjetaIATXException() throws IatxException, DAOException {
        String tramaResponse = "200000000000P04HTML0009900010340064766  ********00779185000000292016042815070700000000IBF31745000015006792STPDEB____1000000            40064766    00        00 015006792201604281507370000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900463626õ";
    	
        //When
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosStopDebit datos = crearDatos();
       
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        iatxResponse.setErrorCode(0);
        iatxResponse.setTrama(tramaResponse);
        iatxResponse.setIatxBody(buildVectorTrama());
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenThrow(IatxException.class );
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(NOMBRE_SERVICIO, VERSION_SERVICIO)))).thenThrow(IatxException.class);

        //Then
        stopDebitTarjetasDAO.realizarStopDebitTarjeta(cliente, datos);
    
    }
    
    
    @Test
    public void cancelarStopDebitTarjetaOK() throws IatxException, DAOException {
        
        //When
        String tramaResponse = "200000000000P04HTML0009900010302FLND81  ********00615474000000262017092011373000000000IBF003JF000011315497STPDEB____1000000            02513381    00        10 011315497201709201137190000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900000000õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosStopDebit datos = crearDatos();
        datos.setTipoCuenta("00");
        
        Mensaje mensajeOK = new Mensaje();
        mensajeOK.setMensaje("MENSAJE OK");
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(NOMBRE_SERVICIO, VERSION_SERVICIO)))).thenReturn(tramaResponse);
        when(mensajeDAO.obtenerMensajePorCodigo("1261")).thenReturn(mensajeOK);
        
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        iatxResponse.setErrorCode(0);
        iatxResponse.setTrama(tramaResponse);
        iatxResponse.setFecha("01/01/2001");
        iatxResponse.setHora("10:00");
        iatxResponse.setIatxBody(buildVectorTrama());
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
                
        //Then
        ComprobanteFeedbackView respuesta = stopDebitTarjetasDAO.cancelarStopDebitTarjeta(cliente, datos, TARJETA_CREDITO);

        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(true, respuesta.getAccionRealizada());
    
    }
    
    
    @Test (expected = DAOException.class)
    public void cancelarStopDebitTarjetaErrorNoHayStopDebitParaCancelar() throws IatxException, DAOException {
        
        //When
        String tramaResponse = "200000000000P04HTML0009900010300CRQJ37  ********00615492000000302017092110541300000000IBF002IF000010513824STPDEB____1000000            00276937    00        10 010513824201709211054030000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0032810001054õAZPõ03õLa operacion solicitada no puede ser realizada. Por favor, reintente más tarde                                                                                                                                                                                õNO SE ENCONTRO õZPE1054 - NO SE ENCONTRO TRANS. ORIGINALõ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosStopDebit datos = crearDatos();
        
        Mensaje mensajeError =  new Mensaje();
        mensajeError.setMensaje("ERROR NO HAY STOP DEBIT PARA CANCELAR");
        
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        iatxResponse.setErrorCode(-1);
        iatxResponse.setTrama(tramaResponse);
        iatxResponse.setIatxBody(buildVectorTrama());
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(NOMBRE_SERVICIO, VERSION_SERVICIO)))).thenReturn(tramaResponse);
        when(mensajeDAO.obtenerMensajePorCodigo("1621")).thenReturn(mensajeError);
        
        //Then
        stopDebitTarjetasDAO.cancelarStopDebitTarjeta(cliente, datos, TARJETA_CREDITO);
    
    }
    
    
    @Test (expected = DAOException.class)
    public void cancelarStopDebitTarjetaErrorGenerico() throws IatxException, DAOException {
        
        //When
        String tramaResponse = "200000000000P04HTML0009900010340064766  ********00779185000000292016042815070700000000IBF31745000015006792STPDEB____1000000            40064766    00        00 015006792201604281507370000000000                            0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000DHõ0000900463626õ";
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosStopDebit datos = crearDatos();
        
        Mensaje mensajeError =  new Mensaje();
        mensajeError.setMensaje("ERROR GENERICO");
        
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        iatxResponse.setErrorCode(-1);
        iatxResponse.setTrama(tramaResponse);
        iatxResponse.setIatxBody(buildVectorTrama());
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(NOMBRE_SERVICIO, VERSION_SERVICIO)))).thenReturn(tramaResponse);
        when(mensajeDAO.obtenerMensajePorCodigo("1262")).thenReturn(mensajeError);
        
        //Then
        stopDebitTarjetasDAO.cancelarStopDebitTarjeta(cliente, datos, TARJETA_CREDITO);
    
    }
    
    
    @SuppressWarnings("unchecked")
    @Test (expected = DAOException.class)
    public void cancelarStopDebitTarjetaIATXException() throws IatxException, DAOException {
        
        //When
        Cliente cliente = ClienteMock.completarInfoCliente();
        DatosStopDebit datos = crearDatos();
        
        Mensaje mensajeError =  new Mensaje();
        mensajeError.setMensaje("ERROR GENERICO");
        
        IatxResponse iatxResponse = new IatxResponse();
        iatxResponse.setEstadoRespuesta(EstadoRespuesta.OK);
        iatxResponse.setErrorCode(-1);
        when(iatxComm.exec(Matchers.any(IatxRequest.class))).thenReturn(iatxResponse);
        
        
        when(iatxSender.send(Matchers.argThat(new IatxMatcher(NOMBRE_SERVICIO, VERSION_SERVICIO)))).thenThrow(IatxException.class);
        when(mensajeDAO.obtenerMensajePorCodigo("1262")).thenReturn(mensajeError);
        
        //Then
        stopDebitTarjetasDAO.cancelarStopDebitTarjeta(cliente, datos, TARJETA_CREDITO);
    
    }
    
    
    private DatosStopDebit crearDatos() {
        
        DatosStopDebit datos = new DatosStopDebit();
        datos.setNroCuenta("3642016");
        datos.setSucursalCuenta("009");
        datos.setTipoCuenta("02");
        datos.setCodigoServicio("216");
        datos.setNroPartida("1234567890");
        
        return datos;
        
    }
    
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