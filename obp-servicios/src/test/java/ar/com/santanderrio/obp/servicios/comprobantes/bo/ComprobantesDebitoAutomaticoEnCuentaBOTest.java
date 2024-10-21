package ar.com.santanderrio.obp.servicios.comprobantes.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.commons.lang3.reflect.FieldUtils;
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

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.ComprobantesDebitoAutomaticoEnCuentaBOImpl;
import ar.com.santanderrio.obp.servicios.comprobantes.bo.impl.TransaccionEnum;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaAdhesionDebitoAutomaticoCuentaDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.ConsultaDebitoDirectoDAO;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.ComprobanteDebitoAutomaticoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresaAdheridaEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoInEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dao.entity.EmpresasAdheridasDebitoAutoOutEntity;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.ComprobantesDTO;
import ar.com.santanderrio.obp.servicios.comprobantes.dto.TransaccionDTO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

@RunWith(MockitoJUnitRunner.class)
public class ComprobantesDebitoAutomaticoEnCuentaBOTest {

    @InjectMocks
    private ComprobantesDebitoAutomaticoEnCuentaBO comprobanteBO = new ComprobantesDebitoAutomaticoEnCuentaBOImpl();
    

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje DAO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The consultaAdhesionDebitoAutomaticoCuentaDAO. */
    @Mock
    private ConsultaAdhesionDebitoAutomaticoCuentaDAO consultaAdhesionDebitoAutomaticoCuentaDAO;

    /** The consultaDebitoDirectoDAO. */
    @Mock
    private ConsultaDebitoDirectoDAO consultaDebitoDirectoDAO;

    /** The medioPagoBO. */
    @Mock
    private MediosPagoBO medioPagoBO;
    
    @Mock
    Future<ComprobanteDebitoAutomaticoOutEntity> thread;
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaAsyncTest() throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        TransaccionDTO transaccion = new TransaccionDTO(); 
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());
        transaccion.setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
        
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida=new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteDebitoAutomaticoOutEntity();
        
        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa );
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteDebitoAutomatico>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteDebitoAutomatico());
        
        Mockito.when(consultaAdhesionDebitoAutomaticoCuentaDAO
                .consultar(Matchers.any(EmpresasAdheridasDebitoAutoInEntity.class))).thenReturn(empresaAdherida);
        Mockito.when(consultaDebitoDirectoDAO.consultarAsync(Matchers.any(ComprobanteDebitoAutomaticoInEntity.class))).thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenReturn(comprobanteDebitoAutomaticoOutEntity);
        FieldUtils.writeDeclaredField(comprobanteBO, "diasFechaDesde", "180", true);

        Future<Respuesta<ComprobantesDTO>> res = comprobanteBO.obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(obtenerCliente(),transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.get().getEstadoRespuesta());
        
    }
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaAsyncWaitingTest() throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        TransaccionDTO transaccion = new TransaccionDTO(); 
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());
        transaccion.setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
        
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida=new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteDebitoAutomaticoOutEntity();
        
        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa );
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteDebitoAutomatico>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteDebitoAutomatico());
        
        Mockito.when(consultaAdhesionDebitoAutomaticoCuentaDAO
                .consultar(Matchers.any(EmpresasAdheridasDebitoAutoInEntity.class))).thenReturn(empresaAdherida);
        Mockito.when(consultaDebitoDirectoDAO.consultarAsync(Matchers.any(ComprobanteDebitoAutomaticoInEntity.class))).thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenReturn(comprobanteDebitoAutomaticoOutEntity);
        FieldUtils.writeDeclaredField(comprobanteBO, "diasFechaDesde", "180", true);
        
        Mockito.when(thread.isDone()).then(new Answer<Boolean>() {
            boolean esPrimerLlamado = true;
            @Override
            public Boolean answer(InvocationOnMock invocation) throws Throwable {
                if(esPrimerLlamado){
                    esPrimerLlamado = false;
                    return false;
                }
                return true;
            }
        });

        Future<Respuesta<ComprobantesDTO>> res = comprobanteBO.obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(obtenerCliente(),transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.get().getEstadoRespuesta());
        
    }
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaAsyncExecExeptionTest() throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        TransaccionDTO transaccion = new TransaccionDTO(); 
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());
        transaccion.setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
        
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida=new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteDebitoAutomaticoOutEntity();
        
        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa );
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteDebitoAutomatico>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteDebitoAutomatico());
        
        Mockito.when(consultaAdhesionDebitoAutomaticoCuentaDAO
                .consultar(Matchers.any(EmpresasAdheridasDebitoAutoInEntity.class))).thenReturn(empresaAdherida);
        Mockito.when(consultaDebitoDirectoDAO.consultarAsync(Matchers.any(ComprobanteDebitoAutomaticoInEntity.class))).thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenThrow(new ExecutionException(new NullPointerException()));
        FieldUtils.writeDeclaredField(comprobanteBO, "diasFechaDesde", "180", true);


        Future<Respuesta<ComprobantesDTO>> res = comprobanteBO.obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(obtenerCliente(),transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());
        
    }
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaAsyncInterrExeptionTest() throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        TransaccionDTO transaccion = new TransaccionDTO(); 
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());
        transaccion.setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
        
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida=new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteDebitoAutomaticoOutEntity();
        
        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa );
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteDebitoAutomatico>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteDebitoAutomatico());
        
        Mockito.when(consultaAdhesionDebitoAutomaticoCuentaDAO
                .consultar(Matchers.any(EmpresasAdheridasDebitoAutoInEntity.class))).thenReturn(empresaAdherida);
        Mockito.when(consultaDebitoDirectoDAO.consultarAsync(Matchers.any(ComprobanteDebitoAutomaticoInEntity.class))).thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenThrow(new InterruptedException());
        FieldUtils.writeDeclaredField(comprobanteBO, "diasFechaDesde", "180", true);


        Future<Respuesta<ComprobantesDTO>> res = comprobanteBO.obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(obtenerCliente(),transaccion);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());
        
    }
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaAsyncDAOExceptionTest() throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida=new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteDebitoAutomaticoOutEntity();
        
        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa );
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteDebitoAutomatico>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteDebitoAutomatico());
        
        Mockito.when(consultaAdhesionDebitoAutomaticoCuentaDAO
                .consultar(Matchers.any(EmpresasAdheridasDebitoAutoInEntity.class))).thenThrow(new DAOException());
        

        Future<Respuesta<ComprobantesDTO>> res = comprobanteBO.obtenerComprobantesDebitoAutomaticoEnCuentaAsync(obtenerCliente());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());
        
    }
    
    @Test
    public void obtenerComprobantesDTOOkDebitoAplicadoTest() throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        TransaccionDTO transaccion = new TransaccionDTO(); 
        transaccion.setFechaDesde(new Date());
        transaccion.setFechaHasta(new Date());
        transaccion.setTransaccion(TransaccionEnum.DEBITO_AUTOMATICO);
        
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida=new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        empresa.setNombreServicioEmpresa("Lucho SA");
        empresa.setCuitEmpresa("987324984732");
        ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteDebitoAutomaticoOutEntity();
        ComprobanteDebitoAutomatico comprobanteDTO = new ComprobanteDebitoAutomatico();
        
        empresaAdherida.setCodigoRetornoExtendido("0");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa );
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteDebitoAutomatico>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(comprobanteDTO);
        
        comprobanteDTO.setTipoConsultaDDI("1");
        comprobanteDTO.setCodigoEstadoDDI("R00");
        comprobanteDTO.setFechaVencimientoDDI("20170604");
        comprobanteDTO.setFechaDebitoRevisionDDI("20170604");
        comprobanteDTO.setImporteDebitoOriginalDDI("12");
        comprobanteDTO.setTipoCuentaDebito("09");
        
        Mockito.when(consultaAdhesionDebitoAutomaticoCuentaDAO
                .consultar(Matchers.any(EmpresasAdheridasDebitoAutoInEntity.class))).thenReturn(empresaAdherida);
        Mockito.when(consultaDebitoDirectoDAO.consultarAsync(Matchers.any(ComprobanteDebitoAutomaticoInEntity.class))).thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenReturn(comprobanteDebitoAutomaticoOutEntity);
        Mockito.when(medioPagoBO.getByCuitServicio(Matchers.anyString(), Matchers.anyString())).thenReturn(new Respuesta<MedioPago>());
        FieldUtils.writeDeclaredField(comprobanteBO, "diasFechaDesde", "180", true);
        

        Future<Respuesta<ComprobantesDTO>> res = comprobanteBO.obtenerComprobantesDebitoAutomaticoEnCuentaFiltradosAsync(obtenerCliente(),transaccion);
        Assert.assertEquals(EstadoRespuesta.OK, res.get().getEstadoRespuesta());
        
    }
    
    @Test
    public void obtenerComprobantesDebitoAutomaticoEnCuentaErrorTest() throws DAOException, InterruptedException, ExecutionException, IllegalAccessException {
        EmpresasAdheridasDebitoAutoOutEntity empresaAdherida=new EmpresasAdheridasDebitoAutoOutEntity();
        EmpresaAdheridaEntity empresa = new EmpresaAdheridaEntity();
        ComprobanteDebitoAutomaticoOutEntity comprobanteDebitoAutomaticoOutEntity = new ComprobanteDebitoAutomaticoOutEntity();
        
        empresaAdherida.setCodigoRetornoExtendido("1");
        empresaAdherida.setEmpresas(new ArrayList<EmpresaAdheridaEntity>());
        empresaAdherida.getEmpresas().add(empresa );
        
        comprobanteDebitoAutomaticoOutEntity.setCodigoRetornoExtendido("0");
        comprobanteDebitoAutomaticoOutEntity.setComprobantes(new ArrayList<ComprobanteDebitoAutomatico>());
        comprobanteDebitoAutomaticoOutEntity.getComprobantes().add(new ComprobanteDebitoAutomatico());
        
        Mockito.when(consultaAdhesionDebitoAutomaticoCuentaDAO
                .consultar(Matchers.any(EmpresasAdheridasDebitoAutoInEntity.class))).thenReturn(empresaAdherida);
        Mockito.when(consultaDebitoDirectoDAO.consultarAsync(Matchers.any(ComprobanteDebitoAutomaticoInEntity.class))).thenReturn(thread);
        Mockito.when(thread.isDone()).thenReturn(true);
        Mockito.when(thread.get()).thenReturn(comprobanteDebitoAutomaticoOutEntity);
        FieldUtils.writeDeclaredField(comprobanteBO, "diasFechaDesde", "180", true);

        Future<Respuesta<ComprobantesDTO>> res = comprobanteBO.obtenerComprobantesDebitoAutomaticoEnCuentaAsync(obtenerCliente());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.get().getEstadoRespuesta());
        
    }
    
    private Cliente obtenerCliente() {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("201111112");
        cliente.setDni("38677554");
        cliente.setNup("02372381723");
        cliente.setApellido1("Gomez");
        cliente.setApellido2("Ortiz");
        cliente.setNombre("Gabriel");
        cliente.setTipoDocumento("I");
        cliente.setPasswordRacf("@Isdnas98");
        cliente.setUsuarioRacf("@aiskw23");
        return cliente;
    }

}
