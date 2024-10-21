package ar.com.santanderrio.obp.servicios.tarjetas.web.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionTarjetas;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.IdentificacionCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenPuntual;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ReporteSeleccionado;
import ar.com.santanderrio.obp.servicios.tarjetas.web.manager.impl.DescargaResumenAnteriorManagerImpl;

/**
 * 
 * @author dante.omar.olmedo
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class DescargaResumenAnteriorManagerTest {
    
    @Mock
    private SesionTarjetas sesionTarjetas;
    
    @Mock
    private ResumenMensualCuentaBO resumenMensualCuentaBOImpl;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private EstadisticaManager estadisticaManager;
    
    @InjectMocks
    private DescargaResumenAnteriorManager descargaManager = new DescargaResumenAnteriorManagerImpl();
    
    @SuppressWarnings("unchecked")
    @Test
    public void exportarOnDemandMultipleResumenErrorTest() throws BusinessException{
        List<ReporteSeleccionado> listReporte = new ArrayList<ReporteSeleccionado>();
        Respuesta<ReporteResumenPuntual> retBo = new Respuesta<ReporteResumenPuntual>();
        ReporteResumenPuntual reporte= new ReporteResumenPuntual();
        
        listReporte.add(new ReporteSeleccionado());
        retBo.setEstadoRespuesta(EstadoRespuesta.ERROR);
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        retBo.setRespuesta(reporte);
        retBo.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        
        Mockito.when(sesionTarjetas.getReportesSelccionados()).thenReturn(listReporte);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(resumenMensualCuentaBOImpl
                        .obtenerResumenPuntualPDF(Matchers.anyList(), Matchers.any(IdentificacionCuenta.class),
                                Matchers.any(Cliente.class), Matchers.anyInt())).thenReturn(retBo);
        Mockito.when(resumenMensualCuentaBOImpl.obtenerMensajeErrorPorCantidadFallidos(Matchers.anyInt())).thenReturn(new ItemMensajeRespuesta());
        Respuesta<ReporteView>  res = descargaManager.exportarOnDemandMultipleResumen("123-123456/7", 3, 4,"");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void exportarOnDemandMultipleResumenOkTest() throws BusinessException{
        List<ReporteSeleccionado> listReporte = new ArrayList<ReporteSeleccionado>();
        listReporte.add(new ReporteSeleccionado());
        Respuesta<ReporteResumenPuntual> retBo = new Respuesta<ReporteResumenPuntual>();
        retBo.setEstadoRespuesta(EstadoRespuesta.OK);
        ReporteResumenPuntual reporte= new ReporteResumenPuntual();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        retBo.setRespuesta(reporte);
        
        Mockito.when(sesionTarjetas.getReportesSelccionados()).thenReturn(listReporte);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(sesionTarjetas.getListaVistos()).thenReturn(new HashMap<String,Set<Integer>>());
        Mockito.when(resumenMensualCuentaBOImpl
                        .obtenerResumenPuntualPDF(Matchers.anyList(), Matchers.any(IdentificacionCuenta.class),
                                Matchers.any(Cliente.class), Matchers.anyInt())).thenReturn(retBo);
        
        Respuesta<ReporteView>  res = descargaManager.exportarOnDemandMultipleResumen("123-123456/7", 3, 4,"");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void exportarOnDemandMultipleResumenCambiosSesionOkTest() throws BusinessException{
        List<ReporteSeleccionado> listReporte = new ArrayList<ReporteSeleccionado>();
        listReporte.add(new ReporteSeleccionado());
        Respuesta<ReporteResumenPuntual> retBo = new Respuesta<ReporteResumenPuntual>();
        retBo.setEstadoRespuesta(EstadoRespuesta.OK);
        ReporteResumenPuntual reporte= new ReporteResumenPuntual();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        retBo.setRespuesta(reporte);
        
        Mockito.when(sesionTarjetas.getReportesSelccionados()).thenReturn(listReporte);
        Mockito.doAnswer(new Answer() {
            private int count = 0;
            
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                if (++count == 1)
                    return 0;
                
                return 1;
            
            }}).when(sesionTarjetas).getContadorParaEstadistica();
        
        
        Mockito.doAnswer(new Answer() {
            private int count2 = 0;
            
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                if (++count2 == 1)
                    return null;
                
                Map<String,HashSet<Integer>> toRetHash = new HashMap<String,HashSet<Integer>>();
                toRetHash.put("123-123456/7", new HashSet<Integer>());
                toRetHash.get("123-123456/7").add(3);
                return toRetHash;
            
            }}).when(sesionTarjetas).getListaVistos();
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(resumenMensualCuentaBOImpl
                        .obtenerResumenPuntualPDF(Matchers.anyList(), Matchers.any(IdentificacionCuenta.class),
                                Matchers.any(Cliente.class), Matchers.anyInt())).thenReturn(retBo);
        
        Respuesta<ReporteView>  res = descargaManager.exportarOnDemandMultipleResumen("123-123456/7", 3, 4,"");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void exportarOnDemandPuntualConEstadisticaOkTest() throws BusinessException{
        List<ReporteSeleccionado> listReporte = new ArrayList<ReporteSeleccionado>();
        listReporte.add(new ReporteSeleccionado());
        Respuesta<ReporteResumenPuntual> retBo = new Respuesta<ReporteResumenPuntual>();
        retBo.setEstadoRespuesta(EstadoRespuesta.OK);
        ReporteResumenPuntual reporte= new ReporteResumenPuntual();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        retBo.setRespuesta(reporte);
        
        Mockito.when(sesionTarjetas.getReportesSelccionados()).thenReturn(listReporte);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(resumenMensualCuentaBOImpl
                        .obtenerResumenPuntualPDF(Matchers.anyList(), Matchers.any(IdentificacionCuenta.class),
                                Matchers.any(Cliente.class), Matchers.anyInt())).thenReturn(retBo);
        Mockito.when(sesionTarjetas.getListaVistos()).thenReturn(new HashMap<String,Set<Integer>>());
        Respuesta<ReporteView>  res = descargaManager.exportarOnDemandPuntualResumenConEstadistica("123-123456/7", 3,"");
        Assert.assertEquals(EstadoRespuesta.OK, res.getEstadoRespuesta());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void exportarOnDemandPuntualConEstadisticaWarningTest() throws BusinessException{
        List<ReporteSeleccionado> listReporte = new ArrayList<ReporteSeleccionado>();
        listReporte.add(new ReporteSeleccionado());
        Respuesta<ReporteResumenPuntual> retBo = new Respuesta<ReporteResumenPuntual>();
        retBo.setEstadoRespuesta(EstadoRespuesta.WARNING);
        ReporteResumenPuntual reporte= new ReporteResumenPuntual();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        retBo.setRespuesta(reporte);
        
        Mockito.when(sesionTarjetas.getReportesSelccionados()).thenReturn(listReporte);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(resumenMensualCuentaBOImpl
                        .obtenerResumenPuntualPDF(Matchers.anyList(), Matchers.any(IdentificacionCuenta.class),
                                Matchers.any(Cliente.class), Matchers.anyInt())).thenReturn(retBo);
        
        Respuesta<ReporteView>  res = descargaManager.exportarOnDemandPuntualResumenConEstadistica("123-123456/7", 3,"");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }
    
    @Test
    public void exportarOnDemandMultipleResumenErrorSinResumenesTest() throws BusinessException{
        List<ReporteSeleccionado> listReporte = new ArrayList<ReporteSeleccionado>();
        listReporte.add(new ReporteSeleccionado());
        Respuesta<ReporteResumenPuntual> retBo = new Respuesta<ReporteResumenPuntual>();
        retBo.setEstadoRespuesta(EstadoRespuesta.OK);
        ReporteResumenPuntual reporte= new ReporteResumenPuntual();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        retBo.setRespuesta(reporte);
        
        Mockito.when(sesionTarjetas.getReportesSelccionados()).thenReturn(null);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        
        Respuesta<ReporteView>  res = descargaManager.exportarOnDemandMultipleResumen("123-123456/7", 3, 4,"");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }
    
    @Test
    public void exportarOnDemandMultipleResumenBusinessExceptionTest() throws BusinessException{
        List<ReporteSeleccionado> listReporte = new ArrayList<ReporteSeleccionado>();
        listReporte.add(new ReporteSeleccionado());
        ReporteResumenPuntual reporte= new ReporteResumenPuntual();
        reporte.setTipoArchivo(TipoArchivoEnum.PDF);
        
        Mockito.when(sesionTarjetas.getReportesSelccionados()).thenReturn(listReporte);
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(resumenMensualCuentaBOImpl
                        .obtenerResumenPuntualPDF(Matchers.anyList(), Matchers.any(IdentificacionCuenta.class),
                                Matchers.any(Cliente.class), Matchers.anyInt())).thenThrow(new BusinessException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        
        Respuesta<ReporteView>  res = descargaManager.exportarOnDemandPuntualResumenConEstadistica("123-123456/7", 3,"");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

}
