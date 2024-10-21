package ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.bo.impl.TraspasoAutomaticoBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.CambioDireccionamientoDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.dao.ConsultaPaquetesDAO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaCambioDireccionamientoInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesInEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.ConsultaPaquetesOutEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.PaqueteEntity;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.entity.TraspasoAutomaticoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.traspaso.web.util.IndicadorTraspasoEnum;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class TraspasoAutomaticoBOTest {

    @InjectMocks
    private TraspasoAutomaticoBOImpl traspasoAutomaticoBO = new TraspasoAutomaticoBOImpl();
    
    @Mock
    private ConsultaPaquetesDAO consultaPaquetesDAO;
    
    @Mock
    private CambioDireccionamientoDAO cambioDireccionamientoDAO;
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private MensajeBO mensajeBO;

    @Test
    public void confirmarTraspasoAutomaticoTest() throws DAOException{
        String mockNumeroCuenta = "1234567";
        String mockSucursalCuenta = "123";
        String mockTipoCuenta = "12";
        
        TraspasoAutomaticoDTO traspasoAutomaticoDTO = new TraspasoAutomaticoDTO();
        Cliente cliente = new Cliente();
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = new ConsultaPaquetesOutEntity();
        List<PaqueteEntity> paquetes = new ArrayList<PaqueteEntity>();
        PaqueteEntity paqueteEntity = new PaqueteEntity();
        paqueteEntity.setNumeroCuenta(mockNumeroCuenta);
        paqueteEntity.setSucursalCuenta(mockSucursalCuenta);
        paqueteEntity.setTipoCuenta(mockTipoCuenta);

        paquetes.add(paqueteEntity);
        consultaPaquetesOutEntity.setCantidadCuentas(1L);
        consultaPaquetesOutEntity.setPaquetes(paquetes);
        traspasoAutomaticoDTO.setCliente(cliente);
        traspasoAutomaticoDTO.setIndicadorFondosCA(IndicadorTraspasoEnum.INDICADOR_ACTIVO);
        traspasoAutomaticoDTO.setNumeroCuenta(mockNumeroCuenta);
        traspasoAutomaticoDTO.setSucursalCuenta(mockSucursalCuenta);
        traspasoAutomaticoDTO.setTipoCuenta(mockTipoCuenta);
        
        when(consultaPaquetesDAO.consultar(Matchers.any(ConsultaPaquetesInEntity.class))).thenReturn(consultaPaquetesOutEntity);
        
        Respuesta<Void> respuesta = traspasoAutomaticoBO.confirmarTraspasoAutomatico(traspasoAutomaticoDTO);
        
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }
    
    @Test
    public void confirmarTraspasoAutomaticoErrorPaquetesTest() throws DAOException{
        
        TraspasoAutomaticoDTO traspasoAutomaticoDTO = new TraspasoAutomaticoDTO();
        Cliente cliente = new Cliente();
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = new ConsultaPaquetesOutEntity();
        List<PaqueteEntity> paquetes = new ArrayList<PaqueteEntity>();
        PaqueteEntity paqueteEntity = new PaqueteEntity();
        Mensaje mensaje = new Mensaje();
        
        mensaje.setMensaje("Alto error");
        paquetes.add(paqueteEntity);
        consultaPaquetesOutEntity.setCantidadCuentas(1L);
        consultaPaquetesOutEntity.setPaquetes(paquetes);
        traspasoAutomaticoDTO.setCliente(cliente);
        traspasoAutomaticoDTO.setIndicadorFondosCA(IndicadorTraspasoEnum.INDICADOR_ACTIVO);
        traspasoAutomaticoDTO.setNumeroCuenta("1234567");
        traspasoAutomaticoDTO.setSucursalCuenta("123");
        traspasoAutomaticoDTO.setTipoCuenta("12");
        
        when(consultaPaquetesDAO.consultar(Matchers.any(ConsultaPaquetesInEntity.class))).thenThrow(new DAOException("Error"));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<Void> respuesta = traspasoAutomaticoBO.confirmarTraspasoAutomatico(traspasoAutomaticoDTO);
        
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().size(), 1);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), "ERROR_SOLICITUD_TRASPASO");
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Alto error");
    }
    
    @Test
    public void confirmarTraspasoAutomaticoErrorDireccionamientoTest() throws DAOException{
        String mockNumeroCuenta = "1234567";
        String mockSucursalCuenta = "123";
        String mockTipoCuenta = "12";

        TraspasoAutomaticoDTO traspasoAutomaticoDTO = new TraspasoAutomaticoDTO();
        Cliente cliente = new Cliente();
        ConsultaPaquetesOutEntity consultaPaquetesOutEntity = new ConsultaPaquetesOutEntity();
        List<PaqueteEntity> paquetes = new ArrayList<PaqueteEntity>();
        PaqueteEntity paqueteEntity = new PaqueteEntity();
        paqueteEntity.setNumeroCuenta(mockNumeroCuenta);
        paqueteEntity.setSucursalCuenta(mockSucursalCuenta);
        paqueteEntity.setTipoCuenta(mockTipoCuenta);
        Mensaje mensaje = new Mensaje();
        
        mensaje.setMensaje("Alto error");
        paquetes.add(paqueteEntity);
        consultaPaquetesOutEntity.setCantidadCuentas(1L);
        consultaPaquetesOutEntity.setPaquetes(paquetes);
        traspasoAutomaticoDTO.setCliente(cliente);
        traspasoAutomaticoDTO.setIndicadorFondosCA(IndicadorTraspasoEnum.INDICADOR_ACTIVO);
        traspasoAutomaticoDTO.setNumeroCuenta(mockNumeroCuenta);
        traspasoAutomaticoDTO.setSucursalCuenta(mockSucursalCuenta);
        traspasoAutomaticoDTO.setTipoCuenta(mockTipoCuenta);
        
        when(consultaPaquetesDAO.consultar(Matchers.any(ConsultaPaquetesInEntity.class))).thenReturn(consultaPaquetesOutEntity);
        doThrow(new DAOException("Error")).when(cambioDireccionamientoDAO).cambiarDireccionamiento(Matchers.any(ConsultaCambioDireccionamientoInEntity.class));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        
        Respuesta<Void> respuesta = traspasoAutomaticoBO.confirmarTraspasoAutomatico(traspasoAutomaticoDTO);
        
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().size(), 1);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(), "ERROR_SOLICITUD_TRASPASO");
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(), "Alto error");
    }
}
