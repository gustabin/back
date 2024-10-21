package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesPMCUnificadoBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobanteDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePMCVEPDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobantePagoMisCuentasDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompAfipDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompConDeudaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompSinDeudaDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.DetalleComprobanteScompVepDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesPMCUnificadoBOTest {

    @InjectMocks
    ComprobantesPMCUnificadoBO comprobante = new ComprobantesPMCUnificadoBOImpl();

    @Mock
    private ScompBO scomp;
    
    @Mock
    private ComprobantesPagoMisCuentasBO pagoMisCuentasBO;
    
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobante;
    
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobante2;
    
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobante3;
    
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobante4;
    
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobante5;
    
    @Mock
    private Future<Respuesta<ComprobantesDTO>> futureComprobante6;
    
    @Ignore
    @Test
    public void obtenerComprobantesConRepetido() throws InterruptedException, ExecutionException {
        Date date = new Date();
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
        DetalleComprobantePagoMisCuentasDTO detalle3 = new DetalleComprobantePagoMisCuentasDTO();
        detalle3.setNroComprobante("1234");
        detalle3.setFechaDePago(date );
        detalle3.setHoraDePago("123213");
        detalle3.setNroControl("156A");
        detalle3.setAnotaciones("detalle3");
        
        ComprobanteDTO comprobanteDTO3 = new ComprobanteDTO();
        comprobanteDTO3.setDetalle(detalle3 );
        
        LinkedList<ComprobanteDTO> comprobantes3 = new LinkedList<ComprobanteDTO>();
        comprobantes3.add(comprobanteDTO3);
        
        Respuesta<ComprobantesDTO> respuesta3 = new Respuesta<ComprobantesDTO>();
        respuesta3.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta3.setRespuesta(new ComprobantesDTO(comprobantes3));
        
        DetalleComprobanteScompConDeudaDTO detalle2 = new DetalleComprobanteScompConDeudaDTO();
        detalle2.setNroComprobante("1234");
        detalle2.setFechaDePago(date);
        detalle2.setHoraDePago("123213");
        detalle2.setNroControl("156A");
        detalle2.setAnotaciones("detalle2");
        
        ComprobanteDTO comprobanteDTO2 = new ComprobanteDTO();
        comprobanteDTO2.setDetalle(detalle2 );
        
        LinkedList<ComprobanteDTO> comprobantes2 = new LinkedList<ComprobanteDTO>();
        comprobantes2.add(comprobanteDTO2);
        
        Respuesta<ComprobantesDTO> respuesta2 = new Respuesta<ComprobantesDTO>();
        respuesta2.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta2.setRespuesta(new ComprobantesDTO(comprobantes2));
        
        DetalleComprobanteScompSinDeudaDTO detalle = new DetalleComprobanteScompSinDeudaDTO();
        detalle.setNroComprobante("1234");
        detalle.setFechaDePago(date);
        detalle.setHoraDePago("123213");
        detalle.setNroControl("156A");
        detalle.setAnotaciones("detalle");
        
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setDetalle(detalle );
        
        LinkedList<ComprobanteDTO> comprobantes = new LinkedList<ComprobanteDTO>();
        comprobantes.add(comprobanteDTO);
        
        Respuesta<ComprobantesDTO> respuesta = new Respuesta<ComprobantesDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(new ComprobantesDTO(comprobantes));
        
        
        
        Mockito.when(scomp.obtenerComprobantesPMCSinDeudaAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCConDeudaAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPMCVEPAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCAfipAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(pagoMisCuentasBO.obtenerComprobantesPagoMisCuentas(Matchers.any(Cliente.class), Matchers.any(TransaccionDTO.class)))
        .thenReturn(respuesta3);
        Mockito.when(scomp.obtenerComprobantesPMCSinDeudaTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCConDeudaTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPagoDeComprasAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPMCVEPTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCAfipTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(futureComprobante.isDone()).thenReturn(true);
        Mockito.when(futureComprobante2.isDone()).thenReturn(true);
        Mockito.when(futureComprobante.get()).thenReturn(respuesta);
        Mockito.when(futureComprobante2.get()).thenReturn(respuesta2);
        
        Respuesta<ComprobantesDTO> res = comprobante.obtenerComprobantesPMC(new Cliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(1,res.getRespuesta().getComprobantes().size());
    }
    
    
    @Ignore
    @Test
    public void obtenerComprobantesConRepetido2() throws InterruptedException, ExecutionException {
        Date date = new Date();
        
        DetalleComprobantePagoMisCuentasDTO detalle6 = new DetalleComprobantePagoMisCuentasDTO();
        detalle6.setNroComprobante("1234");
        detalle6.setFechaDePago(date);
        detalle6.setHoraDePago("123213");
        detalle6.setNroControl("156A");
        
        ComprobanteDTO comprobanteDTO6 = new ComprobanteDTO();
        comprobanteDTO6.setDetalle(detalle6);
        
        LinkedList<ComprobanteDTO> comprobantes6 = new LinkedList<ComprobanteDTO>();
        comprobantes6.add(comprobanteDTO6);
        
        Respuesta<ComprobantesDTO> respuesta6 = new Respuesta<ComprobantesDTO>();
        respuesta6.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta6.setRespuesta(new ComprobantesDTO(comprobantes6));
        
        DetalleComprobantePagoMisCuentasDTO detalle5 = new DetalleComprobantePagoMisCuentasDTO();
        detalle5.setNroComprobante("1234");
        detalle5.setFechaDePago(date);
        detalle5.setHoraDePago("123213");
        detalle5.setNroControl("156A");
        
        ComprobanteDTO comprobanteDTO5 = new ComprobanteDTO();
        comprobanteDTO5.setDetalle(detalle5 );
        
        LinkedList<ComprobanteDTO> comprobantes5 = new LinkedList<ComprobanteDTO>();
        comprobantes5.add(comprobanteDTO5);
        
        Respuesta<ComprobantesDTO> respuesta5 = new Respuesta<ComprobantesDTO>();
        respuesta5.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta5.setRespuesta(new ComprobantesDTO(comprobantes5));
        
        DetalleComprobanteScompAfipDTO detalle4 = new DetalleComprobanteScompAfipDTO();
        detalle4.setNroComprobante("1234");
        detalle4.setFechaDePago(date);
        detalle4.setHoraDePago("123213");
        detalle4.setNroControl("156A");
        
        ComprobanteDTO comprobanteDTO4 = new ComprobanteDTO();
        comprobanteDTO4.setDetalle(detalle4 );
        
        LinkedList<ComprobanteDTO> comprobantes4 = new LinkedList<ComprobanteDTO>();
        comprobantes4.add(comprobanteDTO4);
        
        Respuesta<ComprobantesDTO> respuesta4 = new Respuesta<ComprobantesDTO>();
        respuesta4.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta4.setRespuesta(new ComprobantesDTO(comprobantes4));
        
        DetalleComprobanteScompVepDTO detalle3 = new DetalleComprobanteScompVepDTO();
        detalle3.setNroComprobante("1234");
        detalle3.setFechaDePago(date);
        detalle3.setHoraDePago("123213");
        detalle3.setNroControl("156A");
        
        ComprobanteDTO comprobanteDTO3 = new ComprobanteDTO();
        comprobanteDTO3.setDetalle(detalle3 );
        
        LinkedList<ComprobanteDTO> comprobantes3 = new LinkedList<ComprobanteDTO>();
        comprobantes3.add(comprobanteDTO3);
        
        Respuesta<ComprobantesDTO> respuesta3 = new Respuesta<ComprobantesDTO>();
        respuesta3.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta3.setRespuesta(new ComprobantesDTO(comprobantes3));
        
        DetalleComprobanteScompConDeudaDTO detalle2 = new DetalleComprobanteScompConDeudaDTO();
        detalle2.setNroComprobante("1234");
        detalle2.setFechaDePago(date);
        detalle2.setHoraDePago("123213");
        detalle2.setNroControl("156A");        
        ComprobanteDTO comprobanteDTO2 = new ComprobanteDTO();
        comprobanteDTO2.setDetalle(detalle2 );
        
        LinkedList<ComprobanteDTO> comprobantes2 = new LinkedList<ComprobanteDTO>();
        comprobantes2.add(comprobanteDTO2);
        
        Respuesta<ComprobantesDTO> respuesta2 = new Respuesta<ComprobantesDTO>();
        respuesta2.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta2.setRespuesta(new ComprobantesDTO(comprobantes2));
        
        DetalleComprobanteScompSinDeudaDTO detalle = new DetalleComprobanteScompSinDeudaDTO();
        detalle.setNroComprobante("123441");
        detalle.setFechaDePago(date);
        detalle.setHoraDePago("123213");
        detalle.setNroControl("156A11");
        
        ComprobanteDTO comprobanteDTO = new ComprobanteDTO();
        comprobanteDTO.setDetalle(detalle );
        
        LinkedList<ComprobanteDTO> comprobantes = new LinkedList<ComprobanteDTO>();
        comprobantes.add(comprobanteDTO);
        
        Respuesta<ComprobantesDTO> respuesta = new Respuesta<ComprobantesDTO>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuesta(new ComprobantesDTO(comprobantes));
        
        
        
        Mockito.when(scomp.obtenerComprobantesPMCSinDeudaAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCConDeudaAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPMCVEPAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante3);
        Mockito.when(scomp.obtenerComprobantesPMCAfipAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante4);
//        Mockito.when(scomp.obtenerComprobantesPagoDeComprasAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class))).thenReturn(value)
        Mockito.when(pagoMisCuentasBO.obtenerComprobantesPagoMisCuentas(Matchers.any(Cliente.class), Matchers.any(TransaccionDTO.class)))
        .thenReturn(respuesta5);
        Mockito.when(scomp.obtenerComprobantesPMCSinDeudaTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCConDeudaTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPagoDeComprasAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPMCVEPTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCAfipTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        
        Mockito.when(futureComprobante.isDone()).thenReturn(true);
        Mockito.when(futureComprobante2.isDone()).thenReturn(true);
        Mockito.when(futureComprobante3.isDone()).thenReturn(true);
        Mockito.when(futureComprobante4.isDone()).thenReturn(true);
        Mockito.when(futureComprobante.get()).thenReturn(respuesta);
        Mockito.when(futureComprobante2.get()).thenReturn(respuesta2);
        Mockito.when(futureComprobante3.get()).thenReturn(respuesta3);
        Mockito.when(futureComprobante4.get()).thenReturn(respuesta4);
        
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
        Respuesta<ComprobantesDTO> res = comprobante.obtenerComprobantesPMC(new Cliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
        Assert.assertEquals(2,res.getRespuesta().getComprobantes().size());
    }
    
    @Test
    public void obtenerComprobantesInterruptedException() throws InterruptedException, ExecutionException {
        Date date = new Date();
        
        DetalleComprobantePagoMisCuentasDTO detalle3 = new DetalleComprobantePagoMisCuentasDTO();
        detalle3.setNroComprobante("1234");
        detalle3.setFechaDePago(date );
        detalle3.setHoraDePago("123213");
        detalle3.setNroControl("156A");
        
        ComprobanteDTO comprobanteDTO3 = new ComprobanteDTO();
        comprobanteDTO3.setDetalle(detalle3 );
        
        LinkedList<ComprobanteDTO> comprobantes3 = new LinkedList<ComprobanteDTO>();
        comprobantes3.add(comprobanteDTO3);
        
        Respuesta<ComprobantesDTO> respuesta3 = new Respuesta<ComprobantesDTO>();
        respuesta3.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta3.setRespuesta(new ComprobantesDTO(comprobantes3));
        
        
        Mockito.when(scomp.obtenerComprobantesPMCSinDeudaAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCConDeudaAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPMCVEPAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCAfipAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(pagoMisCuentasBO.obtenerComprobantesPagoMisCuentas(Matchers.any(Cliente.class), Matchers.any(TransaccionDTO.class)))
        .thenReturn(respuesta3);
        Mockito.when(scomp.obtenerComprobantesPMCSinDeudaTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCConDeudaTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPagoDeComprasAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPMCVEPTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante);
        Mockito.when(scomp.obtenerComprobantesPMCAfipTCAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
        .thenReturn(futureComprobante2);
        Mockito.when(scomp.obtenerComprobantesPMCOpenBankAsync(Matchers.anyString(), Matchers.any(TransaccionDTO.class)))
                .thenReturn(futureComprobante2);

        Mockito.when(futureComprobante.isDone()).thenReturn(true);
        Mockito.when(futureComprobante2.isDone()).thenReturn(true);
        Mockito.when(futureComprobante.get()).thenThrow(new InterruptedException());
        Mockito.when(futureComprobante2.get()).thenThrow(new InterruptedException());
        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setTransaccion(TransaccionEnum.PAGOS_DE_SERVICIOS);
        Respuesta<ComprobantesDTO> res = comprobante.obtenerComprobantesPMC(new Cliente(), transaccion);
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(1,res.getRespuesta().getComprobantes().size());
    }
    
    @Test
    public void equalHashClassesTest(){
        Date date = new Date();
        BigDecimal importe = new BigDecimal("20");
        
        ComprobanteDTO comprobante = new ComprobanteDTO();
        DetalleComprobanteScompSinDeudaDTO detalle = new DetalleComprobanteScompSinDeudaDTO();
        detalle.setNroComprobante("1");
        detalle.setFechaDePago(date);
        detalle.setHoraDePago("123213");
        detalle.setNroControl("156A");
        detalle.setAnotaciones("detalle");
        detalle.setImporte(importe);
        detalle.setIdentificacion("identificacion");
        detalle.setIdClienteEmpresa("prueba");
        comprobante.setDetalle(detalle);
        
        ComprobanteDTO comprobante2 = new ComprobanteDTO();
        DetalleComprobanteScompConDeudaDTO detalle2 = new DetalleComprobanteScompConDeudaDTO();
        detalle2.setNroComprobante("2");
        detalle2.setFechaDePago(date);
        detalle2.setHoraDePago("123213");
        detalle2.setNroControl("156A");
        detalle2.setAnotaciones("detalle2");
        detalle2.setImporte(importe);
        detalle2.setIdentificacion("identificacion");
        detalle2.setIdClienteEmpresa("prueba");
        comprobante2.setDetalle(detalle2);
        
        ComprobanteDTO comprobante3 = new ComprobanteDTO();
        DetalleComprobantePagoMisCuentasDTO detalle3 = new DetalleComprobantePagoMisCuentasDTO();
        detalle3.setNroComprobante("3");
        detalle3.setFechaDePago(date);
        detalle3.setHoraDePago("123213");
        detalle3.setNroControl("156A");
        detalle3.setAnotaciones("detalle2");
        detalle3.setImporte(importe);
        detalle3.setIdentificacion("identificacion");
        detalle3.setIdClienteEmpresa("prueba");
        comprobante3.setDetalle(detalle3);
        
        ComprobanteDTO comprobante4 = new ComprobanteDTO();
        DetalleComprobanteScompVepDTO detalle4 = new DetalleComprobanteScompVepDTO();
        detalle4.setNroComprobante("4");
        detalle4.setFechaDePago(date);
        detalle4.setHoraDePago("123213");
        detalle4.setNroControl("156A");
        detalle4.setAnotaciones("detalle2");
        detalle4.setImporte(importe);
        detalle4.setIdentificacion("identificacion");
        detalle4.setIdClienteEmpresa("prueba");
        comprobante4.setDetalle(detalle4);
        
        ComprobanteDTO comprobante5 = new ComprobanteDTO();
        DetalleComprobantePMCVEPDTO detalle5 = new DetalleComprobantePMCVEPDTO();
        detalle5.setNroComprobante("4");
        detalle5.setFechaDePago(date);
        detalle5.setHoraDePago("123213");
        detalle5.setNroControl("156A");
        detalle5.setAnotaciones("detalle2");
        detalle5.setImporte(importe);
        detalle5.setIdentificacion("identificacion");
        detalle5.setIdClienteEmpresa("prueba");
        comprobante5.setDetalle(detalle5);
        
        ComprobanteDTO comprobante6 = new ComprobanteDTO();
        DetalleComprobanteScompAfipDTO detalle6 = new DetalleComprobanteScompAfipDTO();
        detalle6.setNroComprobante("5");
        detalle6.setFechaDePago(date);
        detalle6.setHoraDePago("123213");
        detalle6.setNroControl("156A");
        detalle6.setAnotaciones("detalle2");
        detalle6.setImporte(importe);
        detalle6.setIdentificacion("identificacion");
        detalle6.setIdClienteEmpresa("prueba");
        comprobante6.setDetalle(detalle6);
            
        HashSet<ComprobanteDTO> set = new HashSet<ComprobanteDTO>();
        set.add(comprobante);
        set.add(comprobante2);
        set.add(comprobante3);
        set.add(comprobante4);
        set.add(comprobante5);
        set.add(comprobante6);
        
        Assert.assertTrue(detalle.equals(detalle4));
        Assert.assertTrue(detalle.hashCode() == detalle4.hashCode());
        Assert.assertEquals(1, set.size());
        
    }
    
    

}
