package ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo;


import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ISBANRuntimeException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.bo.impl.ExtraccionYComprasExteriorBOImpl;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dao.ExtraccionYComprasExteriorDAO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CambioTarjetaOperaExteriorOutDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.CuentaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.DatosExtraccionYComprasExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.dto.TarjetaOperacionExteriorDTO;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.CambioTarjetaOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ComprobanteOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaCuentasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorInEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.entity.ConsultaTarjetasOperaExteriorOutEntity;
import ar.com.santanderrio.obp.servicios.extraccioncomprasexterior.mock.ExtraccionYComprasExteriorObjectsMock;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;



@RunWith(MockitoJUnitRunner.class)
public class ExtraccionYComprasExteriorBOTest  {
    

    @InjectMocks
    private ExtraccionYComprasExteriorBO extraccionYComprasExteriorBO = new ExtraccionYComprasExteriorBOImpl();  
    

    @Mock
    private ExtraccionYComprasExteriorDAO extraccionYComprasExteriorDAO;  
    
    
    @Mock
    private MensajeBO mensajeBO;
    
    
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
    private SesionParametros sesionParametros;
    
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    
    Mensaje mensaje = new Mensaje();

    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        cliente = new Cliente();
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        cliente.setNombre("Silvina");
        cliente.setApellido1("Luque");
        cliente.setApellido2("M");
        cliente.setSegmento(segmento);
        cliente.setNup("123456789");
        
        mensaje.setMensaje("Mensaje");
    }

    @Test
    public void consultarCuentasOperaExteriorTest() throws DAOException{
        ConsultaCuentasOperaExteriorOutEntity outDAO = ExtraccionYComprasExteriorObjectsMock.obtenerConsultaCuentasOutEntity();
        String numeroTarjeta = "1234123412341234";
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(extraccionYComprasExteriorDAO.consultarCuentasOperaExterior(Matchers.any(ConsultaCuentasOperaExteriorInEntity.class))).thenReturn(outDAO);
        Respuesta<List<CuentaOperacionExteriorDTO>> respuesta = extraccionYComprasExteriorBO.consultarCuentasOperaExterior(numeroTarjeta);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
   
    }
    
    @Test
    public void consultarCuentasOperaExteriorErrorTest() throws DAOException{
        String numeroTarjeta = "1234123412341234";
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(extraccionYComprasExteriorDAO.consultarCuentasOperaExterior(Matchers.any(ConsultaCuentasOperaExteriorInEntity.class))).thenThrow(new DAOException());
        Respuesta<List<CuentaOperacionExteriorDTO>> respuesta = extraccionYComprasExteriorBO.consultarCuentasOperaExterior(numeroTarjeta);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    
    
    
    @Test
    public void consultarTarjetasOperaExteriorTest() throws DAOException{
        ConsultaTarjetasOperaExteriorOutEntity outDAO = ExtraccionYComprasExteriorObjectsMock.obtenerConsultaTarjetasOutEntity(false);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(extraccionYComprasExteriorDAO.consultarTarjetasOperaExterior(Matchers.any(ConsultaTarjetasOperaExteriorInEntity.class))).thenReturn(outDAO);
        Respuesta<List<TarjetaOperacionExteriorDTO>> respuesta = extraccionYComprasExteriorBO.consultarTarjetasOperaExterior();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
   
    }
    
    @Test
    public void consultarTarjetasSegundoLlamadoTest() throws DAOException{
        ConsultaTarjetasOperaExteriorOutEntity outDAO = ExtraccionYComprasExteriorObjectsMock.obtenerConsultaTarjetasOutEntity(true);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(extraccionYComprasExteriorDAO.consultarTarjetasOperaExterior(Matchers.any(ConsultaTarjetasOperaExteriorInEntity.class))).thenReturn(outDAO);
        Respuesta<List<TarjetaOperacionExteriorDTO>> respuesta = extraccionYComprasExteriorBO.consultarTarjetasOperaExterior();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
   
    }
   
    @Test
    public void consultarTarjetasOperaExteriorErrorTest() throws DAOException{
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(extraccionYComprasExteriorDAO.consultarTarjetasOperaExterior(Matchers.any(ConsultaTarjetasOperaExteriorInEntity.class))).thenThrow(new DAOException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<TarjetaOperacionExteriorDTO>> respuesta = extraccionYComprasExteriorBO.consultarTarjetasOperaExterior();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    
    
    @Test
    public void cambioTarjetaOperaExteriorTest() throws DAOException{
        CambioTarjetaOperaExteriorOutEntity outDAO = ExtraccionYComprasExteriorObjectsMock.obtenerCambioTarjetaOutEntity();
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(extraccionYComprasExteriorDAO.cambioTarjetaOperaExterior(Matchers.any(CambioTarjetaOperaExteriorInEntity.class))).thenReturn(outDAO);
        Respuesta<CambioTarjetaOperaExteriorOutDTO> respuesta = extraccionYComprasExteriorBO.cambioTarjetaOperaExterior(ExtraccionYComprasExteriorObjectsMock.obtenerDatosCambioTarjeta());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
   
    }
    
    @Test
    public void cambioTarjetaOperaExteriorErrorTest() throws DAOException{
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(extraccionYComprasExteriorDAO.cambioTarjetaOperaExterior(Matchers.any(CambioTarjetaOperaExteriorInEntity.class))).thenThrow(new DAOException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<CambioTarjetaOperaExteriorOutDTO> respuesta = extraccionYComprasExteriorBO.cambioTarjetaOperaExterior(ExtraccionYComprasExteriorObjectsMock.obtenerDatosCambioTarjeta());
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    
    
    @Test
    public void descargarComprobanteTest() throws DAOException{
        Reporte outDAO = new Reporte();
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO =  ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionParametros.getNroComprobante()).thenReturn("123456789");
        when(extraccionYComprasExteriorDAO.descargarComprobante(Matchers.any(ComprobanteOperaExteriorInEntity.class))).thenReturn(outDAO);
        Respuesta<Reporte> respuesta = extraccionYComprasExteriorBO.descargarComprobante();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        
    }
    
    @Test
    public void descargarComprobanteErrorTest() throws DAOException{
        DatosExtraccionYComprasExteriorDTO datosOperaExteriorDTO =  ExtraccionYComprasExteriorObjectsMock.obtenerDatosOperaExteriorDTO();
        when(sesionParametros.getDatosExtraccionYComprasExterior()).thenReturn(datosOperaExteriorDTO);
        when(sesionParametros.getNroComprobante()).thenReturn("123456789");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(extraccionYComprasExteriorDAO.descargarComprobante(Matchers.any(ComprobanteOperaExteriorInEntity.class))).thenThrow(new ISBANRuntimeException("Error"));
        Respuesta<Reporte> respuesta = extraccionYComprasExteriorBO.descargarComprobante();
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        
    }
    
}
