package ar.com.santanderrio.obp.servicios.perfil.bo;

import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.perfil.bo.impl.PreguntasSeguridadBOImpl;
import ar.com.santanderrio.obp.servicios.perfil.dao.PreguntasSeguridadPerfilDAO;

import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaDomiciliosInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ConsultaPreguntasSeguridadOutEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.DatosComprobanteEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadInEntity;
import ar.com.santanderrio.obp.servicios.perfil.entities.ModificacionPreguntasSeguridadOutEntity;

import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntaSeguridadView;
import ar.com.santanderrio.obp.servicios.perfil.web.view.PreguntasSeguridadView;



@RunWith(MockitoJUnitRunner.class)
public class PreguntasSeguridadBOTest  {
    

    @InjectMocks
    private PreguntasSeguridadBO preguntasSeguridadBO = new PreguntasSeguridadBOImpl();  
    

    @Mock
    private PreguntasSeguridadPerfilDAO preguntasSeguridadPerfilDAO;  
        
    @Mock
    private MensajeBO mensajeBO;
    
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private SesionParametros sesionParametros;
    
    Mensaje mensaje = new Mensaje();

   
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    @Test
    public void consultarPreguntasSeguridadTest() throws DAOException{
        ConsultaPreguntasSeguridadOutEntity outDAO = new ConsultaPreguntasSeguridadOutEntity();
        Cliente cliente = new Cliente();
        cliente.setMarcaANPH("");
        ConsultaDomiciliosInEntity consultaDomiciliosInEntity = new ConsultaDomiciliosInEntity();
        consultaDomiciliosInEntity.setCliente(cliente);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preguntasSeguridadPerfilDAO.consultaPreguntasSeguridad(Matchers.any(ConsultaPreguntasSeguridadInEntity.class))).thenReturn(outDAO);
        Respuesta<PreguntasSeguridadView>respuesta = preguntasSeguridadBO.consultarPreguntasSeguridad();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    
    @Test
    public void consultarPreguntasSeguridadErrorTest() throws DAOException{
        Cliente cliente = new Cliente();
        cliente.setMarcaANPH("");
        ConsultaDomiciliosInEntity consultaDomiciliosInEntity = new ConsultaDomiciliosInEntity();
        consultaDomiciliosInEntity.setCliente(cliente);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(
                preguntasSeguridadPerfilDAO.consultaPreguntasSeguridad(Matchers.any(ConsultaPreguntasSeguridadInEntity.class)))
                .thenThrow(new DAOException("Servicio falló."));
        
        Respuesta<PreguntasSeguridadView>respuesta = preguntasSeguridadBO.consultarPreguntasSeguridad();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }
    
    
    
    
    @Test
    public void guardarPreguntasSeguridadTest() throws DAOException{
        List<PreguntaSeguridadView> preguntas = new ArrayList<PreguntaSeguridadView>();
        PreguntaSeguridadView r1 = new PreguntaSeguridadView();
        r1.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r1.setId("050");
        PreguntaSeguridadView r2 = new PreguntaSeguridadView();
        r2.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r2.setId("051");
        PreguntaSeguridadView r3 = new PreguntaSeguridadView();
        r3.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r3.setId("052");
        PreguntaSeguridadView r4 = new PreguntaSeguridadView();
        r4.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r4.setId("053");
        PreguntaSeguridadView r5 = new PreguntaSeguridadView();
        r5.setRespuesta("143A301A304A273A265A36A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315A315AA");
        r5.setId("054");
        preguntas.add(r1);
        preguntas.add(r2);
        preguntas.add(r3);
        preguntas.add(r4);
        preguntas.add(r5);

        ModificacionPreguntasSeguridadOutEntity modificacionPreguntasSeguridadOutEntity = new ModificacionPreguntasSeguridadOutEntity();
        when(preguntasSeguridadPerfilDAO.guardarPreguntasSeguridad(Matchers.any(ModificacionPreguntasSeguridadInEntity.class))).thenReturn(modificacionPreguntasSeguridadOutEntity);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ModificacionPreguntasSeguridadOutEntity> respuesta = preguntasSeguridadBO.guardarPreguntasSeguridad(preguntas);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    
    
    @Test
    public void guardarPreguntasSeguridadErrorTest() throws DAOException{
        List<PreguntaSeguridadView> preguntas = new ArrayList<PreguntaSeguridadView>();
        when(preguntasSeguridadPerfilDAO.guardarPreguntasSeguridad(Matchers.any(ModificacionPreguntasSeguridadInEntity.class))).thenThrow(new DAOException("Error en servicio"));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<ModificacionPreguntasSeguridadOutEntity> respuesta = preguntasSeguridadBO.guardarPreguntasSeguridad(preguntas);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
    }
    
    
        
    
    @Test
    public void descargarComprobanteTest() throws DAOException{
        Reporte reporte = new Reporte();
        DatosComprobanteEntity datos = new DatosComprobanteEntity();
        Cliente cliente = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preguntasSeguridadPerfilDAO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class))).thenReturn(reporte);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<Reporte> respuesta = preguntasSeguridadBO.descargarComprobante(datos);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        
    }
    
    
    @Test
    public void descargarComprobanteErrorTest() throws DAOException{
        DatosComprobanteEntity datos = new DatosComprobanteEntity();
        Cliente cliente = new Cliente();
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(preguntasSeguridadPerfilDAO.descargarComprobante(Matchers.any(DatosComprobanteEntity.class))).thenThrow(new ISBANRuntimeException("Error reporte"));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<Reporte> respuesta = preguntasSeguridadBO.descargarComprobante(datos);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        
    }
    
    
    
    
}
