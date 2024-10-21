package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TipoOperacionComprobanteEnum;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesBOTest {

    @Mock
    private Future<ComprobantesDTO> thread;

    @InjectMocks
    private ComprobantesBOImpl comprobantes = new ComprobantesBOImpl();

    @Mock
    private MediosPagoBO mediosPagoBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Test
    public void esperarHastaFinDeListaDeHilosTest() throws InterruptedException {
        List<Future<ComprobantesDTO>> listaFuture = new ArrayList<Future<ComprobantesDTO>>();
        listaFuture.add(thread);

        Mockito.when(thread.isDone()).then(new Answer<Boolean>() {
            boolean esPrimerLlamado = true;

            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if (esPrimerLlamado) {
                    esPrimerLlamado = false;
                    return false;
                }
                return true;
            }
        });

        comprobantes.esperarHastaFinDeListaDeHilos(listaFuture);
        Assert.assertTrue(thread.isDone());
    }

    @Test
    public void obtenerMedioDePagoNULLTest() {
        MedioPago medioPago = new MedioPago();
        Mockito.when(mediosPagoBO.obtenerPorCodEstablecimiento(Matchers.anyString())).thenReturn(medioPago);
        String res = comprobantes.obtenerMedioDePago("15");
        Assert.assertEquals("Establecimiento 15", res);
    }

    @Test
    public void obtenerMedioDePagoConNombreFantasiaTest() {
        MedioPago medioPago = new MedioPago();
        medioPago.setNombreFantasia("Freezer");
        Mockito.when(mediosPagoBO.obtenerPorCodEstablecimiento(Matchers.anyString())).thenReturn(medioPago);
        String res = comprobantes.obtenerMedioDePago("");
        Assert.assertEquals("Freezer", res);
    }

    @Test
    public void esRespuestaErrorTest() {
        Respuesta<ComprobantesDTO> respuesta = new Respuesta<ComprobantesDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Assert.assertTrue(comprobantes.esRespuestaError(respuesta));
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Assert.assertTrue(!comprobantes.esRespuestaError(respuesta));
    }

    @Test
    public void tieneItemsMensajesTest() {
        Respuesta<ComprobantesDTO> respuesta = new Respuesta<ComprobantesDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        Assert.assertTrue(!comprobantes.tieneItemsMensajes(respuesta));
        respuesta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuesta.add(new ItemMensajeRespuesta());
        Assert.assertTrue(comprobantes.tieneItemsMensajes(respuesta));
    }

    @Test
    public void obtenerTituloArchivoTest() {
        Assert.assertTrue("Comprobante de transferencia programada rechazada".equals(
                comprobantes.obtenerTituloArchivo(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA, "Rechazado")));
        
        Assert.assertTrue("Comprobante de transferencia programada".equals(
                comprobantes.obtenerTituloArchivo(TipoOperacionComprobanteEnum.TRANSFERENCIA_PROGRAMADA, "Aceptada")));
        
        Assert.assertTrue("Comprobante de pago rechazado".equals(
                comprobantes.obtenerTituloArchivo(TipoOperacionComprobanteEnum.PAGO_SUELDOS, "Rechazado")));
        
        Assert.assertTrue("Comprobante de pago".equals(
                comprobantes.obtenerTituloArchivo(TipoOperacionComprobanteEnum.PAGO_HONORARIOS, "Aceptada")));
        
        Assert.assertTrue("Comprobante de transferencia".equals(
                comprobantes.obtenerTituloArchivo(TipoOperacionComprobanteEnum.TRANSFERENCIA_INMEDIATA, "Rechazado")));
        
        Assert.assertTrue("Comprobante de pago de tarjeta Santander".equals(
                comprobantes.obtenerTituloArchivo(TipoOperacionComprobanteEnum.PAGO_TARJETA, "Rechazado")));
        
        Assert.assertTrue("Comprobante de pago".equals(
                comprobantes.obtenerTituloArchivo(TipoOperacionComprobanteEnum.PAGO_SERVICIOS, "Rechazado")));
    }

}
