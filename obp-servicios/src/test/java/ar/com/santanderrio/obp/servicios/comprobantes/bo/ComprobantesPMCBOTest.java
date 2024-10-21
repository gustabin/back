package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesPagoMisCuentasBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ComprobantesPagoMisCuentasDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesPMCBOTest {
    
    @InjectMocks
    ComprobantesPagoMisCuentasBO comprobante = new ComprobantesPagoMisCuentasBOImpl();
    
    @Mock
    private ComprobantesPagoMisCuentasDAO comprobantesPagoMisCuentasDAO;
    
    @Mock
    private MediosPagoBO medioPagoBO;
    
    /** The respuesta factory. */
    @InjectMocks
    @Spy
    RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;
    
    @Test
    public void obtenerComprobantesPMCAsyncTest(){
        
    }

}
