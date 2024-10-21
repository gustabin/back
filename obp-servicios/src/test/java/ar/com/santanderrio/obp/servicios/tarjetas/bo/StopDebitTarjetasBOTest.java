package ar.com.santanderrio.obp.servicios.tarjetas.bo;
import static org.mockito.Mockito.when;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.bo.impl.StopDebitTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.dao.StopDebitTarjetasDAO;
import ar.com.santanderrio.obp.servicios.stopdebittarjetas.entities.DatosStopDebit;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.StopDebitOut;
import ar.com.santanderrio.obp.servicios.tarjetas.view.ComprobanteFeedbackView;

@RunWith(MockitoJUnitRunner.class)
public class StopDebitTarjetasBOTest {

    @InjectMocks
    StopDebitTarjetasBOImpl stopDebitTarjetasBO;
    
    @Mock
    StopDebitTarjetasDAO stopDebitTarjetasDao;
    
    @Mock
    MensajeBO mensajeBO;
    
    
    @Test
    public void realizarStopDebitTarjeta() throws DAOException {
        
        //When
        StopDebitOut stopDebitOut = new StopDebitOut();
        stopDebitOut.setResultado("OK");
        stopDebitOut.setNroDeComprobante("123456");
        
        when(stopDebitTarjetasDao.realizarStopDebitTarjeta(
                Matchers.any(Cliente.class), Matchers.any(DatosStopDebit.class))).thenReturn(stopDebitOut);
        
        //Then
        StopDebitOut respuesta = stopDebitTarjetasBO.realizarStopDebitTarjeta(new Cliente(), new DatosStopDebit());
        
        //Expected
        Assert.assertNotNull(respuesta);
        
    }
    
    @Test
    public void cancelarStopDebitTarjeta() throws DAOException, BusinessException {
        
        //When
        ComprobanteFeedbackView comprobante = new ComprobanteFeedbackView();
        comprobante.setAccionRealizada(true);
        
        when(stopDebitTarjetasDao.cancelarStopDebitTarjeta(
                Matchers.any(Cliente.class), Matchers.any(DatosStopDebit.class), Matchers.anyString())).thenReturn(comprobante);
        
        //Then
        ComprobanteFeedbackView respuesta = stopDebitTarjetasBO.cancelarStopDebitTarjeta(new Cliente(), new DatosStopDebit(), "12345");
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(true, comprobante.getAccionRealizada());
        
    }
    
  
    @Test
    @Ignore
    public void revisarHorarioSolicitudEnHorario() throws IllegalAccessException, DAOException {
        
        //When
        DateTime fechaHoy = new DateTime();
        fechaHoy = fechaHoy.plusHours(1);
        FieldUtils.writeDeclaredField(stopDebitTarjetasBO, "horaLimiteCancelacion", fechaHoy.toString("HH:mm"), true);
       
      
        //Then
        Boolean respuesta = stopDebitTarjetasBO.revisarHorarioSolicitud(fechaHoy.toString("dd/MM/YY"));
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(true, respuesta);
               
    }
        
    @Test (expected = DAOException.class)
    @Ignore
    public void revisarHorarioSolicitudFueraDeHorario() throws IllegalAccessException, DAOException {
        
        //When
        DateTime fechaActual = new DateTime().plusHours(-1);
        FieldUtils.writeDeclaredField(stopDebitTarjetasBO, "horaLimiteCancelacion", fechaActual.toString("HH:mm"), true);
        
        Mensaje mensajeFueraHorario = new Mensaje();
        mensajeFueraHorario.setMensaje("FUERA DE HORARIO");
        
        when(mensajeBO.obtenerMensajePorCodigo("1109")).thenReturn(mensajeFueraHorario);
        
        //Then
        stopDebitTarjetasBO.revisarHorarioSolicitud(fechaActual.toString("dd/MM/yyyy"));

    }
}