package ar.com.santanderrio.obp.servicios.trackingtarjetas.bo;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.security.sign.Sign;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasIn;
import ar.com.santanderrio.obp.generated.webservices.solicitudes.andreani.TrackingTarjetasOut;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.dao.ConsultaTarjetasDAO;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasIn;
import ar.com.santanderrio.obp.servicios.comun.tarjetas.entity.ConsultaDatosTarjetasOut;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.bo.impl.TrackingTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dao.TrackingTarjetasDAO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.dto.TrackingPiezaDTO;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.entities.ConsultaTarjetasMonederoOutEntity;
import ar.com.santanderrio.obp.servicios.trackingtarjetas.mock.TrackingTarjetasObjectsMock;

@RunWith(MockitoJUnitRunner.class)
public class TrackingTarjetasBOTest {

    /**trackingTarjetasBO */
    @InjectMocks
    private TrackingTarjetasBO trackingTarjetasBO = new TrackingTarjetasBOImpl();  
    
    /**trackingTarjetasDAO */
    @Mock
    private TrackingTarjetasDAO trackingTarjetasDAO;  
    
    /**tarjetasDAO */
    @Mock
    private ConsultaTarjetasDAO tarjetasDAO;
    
    /**mensajeBO */
    @Mock
    private MensajeBO mensajeBO;
    
    /**respuestaFactory */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**sesionCliente */
    @Mock
    private SesionCliente sesionCliente;
    
    /**sesionParametros */
    @Mock
    private SesionParametros sesionParametros;
    
    
    /** The cliente. */
    private Cliente cliente = new Cliente();
    
    /**Mensaje */
    Mensaje mensaje = new Mensaje();
    
    
    @Mock
    private Sign sign;

    /**
     * Inits.
     */
    @Before
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        cliente = TrackingTarjetasObjectsMock.obtenerDatosClienteOKTest();
        
        mensaje.setMensaje("Mensaje");

    }
    
    /**
     *  obtenerTrackingTarjetasTest
     * @throws DAOException
     */
    @Test
    public void obtenerTrackingTarjetasTest() throws DAOException{
        TrackingTarjetasOut outDAO = TrackingTarjetasObjectsMock.obtenerTrackingTarjetasOutOK();
        when(sesionCliente.getCliente()).thenReturn(cliente);
        
        ConsultaTarjetasMonederoOutEntity tarjetasMonederoEntity = TrackingTarjetasObjectsMock.obtenerCnsTarjetasMonederoOutOK();
        when(trackingTarjetasDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(tarjetasMonederoEntity);
        ConsultaDatosTarjetasOut tarjetasOut = TrackingTarjetasObjectsMock.obtenerTarjetasCreditoOutOK();
        when(tarjetasDAO.consultaDatosTarjetas(Matchers.any(ConsultaDatosTarjetasIn.class))).thenReturn(tarjetasOut);
        when(sign.buildB64Signature(Matchers.any(byte[].class), Matchers.anyString(), Matchers.anyBoolean())).thenReturn(new byte[0]);
        when(trackingTarjetasDAO.consultarTraza(Matchers.any(TrackingTarjetasIn.class))).thenReturn(outDAO);
        Respuesta<List<TrackingPiezaDTO>> respuesta = trackingTarjetasBO.obtenerTrackingTarjetas(cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
   
    }
    
    /**
     *  obtenerTrackingTarjetasTest
     * @throws DAOException
     */
    @Test
    @Ignore
    public void obtenerTrackingTarjetasTestErrorWS() throws DAOException{
        TrackingTarjetasOut outDAO = TrackingTarjetasObjectsMock.obtenerTrackingTarjetasOutError();
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        ConsultaTarjetasMonederoOutEntity tarjetasMonederoEntity = TrackingTarjetasObjectsMock.obtenerCnsTarjetasMonederoOutOK();
        when(trackingTarjetasDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(tarjetasMonederoEntity);
        ConsultaDatosTarjetasOut tarjetasOut = TrackingTarjetasObjectsMock.obtenerTarjetasCreditoOutOK();
        when(tarjetasDAO.consultaDatosTarjetas(Matchers.any(ConsultaDatosTarjetasIn.class))).thenReturn(tarjetasOut);
        when(sign.buildB64Signature(Matchers.any(byte[].class), Matchers.anyString(), Matchers.anyBoolean())).thenReturn(new byte[0]);
        when(trackingTarjetasDAO.consultarTraza(Matchers.any(TrackingTarjetasIn.class))).thenReturn(outDAO);
        Respuesta<List<TrackingPiezaDTO>> respuesta = trackingTarjetasBO.obtenerTrackingTarjetas(cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    
    
    
    /**
     *  obtenerTrackingTarjetasTestErrorPiezas
     * @throws DAOException
     */
    @Test
    @Ignore
    public void obtenerTrackingTarjetasTestErrorPiezas() throws DAOException{
        TrackingTarjetasOut outDAO = TrackingTarjetasObjectsMock.obtenerTrackingTarjetasOutPiezasError();
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        ConsultaTarjetasMonederoOutEntity tarjetasMonederoEntity = TrackingTarjetasObjectsMock.obtenerCnsTarjetasMonederoOutOK();
        when(trackingTarjetasDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(tarjetasMonederoEntity);
        ConsultaDatosTarjetasOut tarjetasOut = TrackingTarjetasObjectsMock.obtenerTarjetasCreditoOutOK();
        when(tarjetasDAO.consultaDatosTarjetas(Matchers.any(ConsultaDatosTarjetasIn.class))).thenReturn(tarjetasOut);
        when(sign.buildB64Signature(Matchers.any(byte[].class), Matchers.anyString(), Matchers.anyBoolean())).thenReturn(new byte[0]);
        when(trackingTarjetasDAO.consultarTraza(Matchers.any(TrackingTarjetasIn.class))).thenReturn(outDAO);
        Respuesta<List<TrackingPiezaDTO>> respuesta = trackingTarjetasBO.obtenerTrackingTarjetas(cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    
    /**
     *  obtenerTrackingTarjetasTestErrorFirma
     * @throws DAOException
     */
    @Test
    public void obtenerTrackingTarjetasTestErrorFirma() throws DAOException{
        when(sesionCliente.getCliente()).thenReturn(cliente);
        ConsultaTarjetasMonederoOutEntity tarjetasMonederoEntity = TrackingTarjetasObjectsMock.obtenerCnsTarjetasMonederoOutOK();
        when(trackingTarjetasDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(tarjetasMonederoEntity);
        ConsultaDatosTarjetasOut tarjetasOut = TrackingTarjetasObjectsMock.obtenerTarjetasCreditoOutOK();
        when(tarjetasDAO.consultaDatosTarjetas(Matchers.any(ConsultaDatosTarjetasIn.class))).thenReturn(tarjetasOut);
        when(sign.buildB64Signature(Matchers.any(byte[].class), Matchers.anyString(), Matchers.anyBoolean())).thenReturn(null);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<TrackingPiezaDTO>> respuesta = trackingTarjetasBO.obtenerTrackingTarjetas(cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    
    
    /**
     *  obtenerTrackingTarjetasTestErrorFirma
     * @throws DAOException
     */
    @Test
    public void obtenerTrackingTarjetasTestErrorDAO() throws DAOException{
        when(sesionCliente.getCliente()).thenReturn(cliente);
        ConsultaTarjetasMonederoOutEntity tarjetasMonederoEntity = TrackingTarjetasObjectsMock.obtenerCnsTarjetasMonederoOutOK();
        when(trackingTarjetasDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(tarjetasMonederoEntity);
        when(tarjetasDAO.consultaDatosTarjetas(Matchers.any(ConsultaDatosTarjetasIn.class))).thenThrow(new DAOException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<TrackingPiezaDTO>> respuesta = trackingTarjetasBO.obtenerTrackingTarjetas(cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    
    /**
     *  obtenerTrackingTarjetasTestErrorFirma
     * @throws DAOException
     */
    @Test
    public void obtenerTrackingTarjetasTestErrorFirma2() throws DAOException{
        when(sesionCliente.getCliente()).thenReturn(cliente);
        ConsultaTarjetasMonederoOutEntity tarjetasMonederoEntity = TrackingTarjetasObjectsMock.obtenerCnsTarjetasMonederoOutOK();
        when(trackingTarjetasDAO.getDatosTarjetaMonedero(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(tarjetasMonederoEntity);
        ConsultaDatosTarjetasOut tarjetasOut = TrackingTarjetasObjectsMock.obtenerTarjetasCreditoOutOK();
        when(tarjetasDAO.consultaDatosTarjetas(Matchers.any(ConsultaDatosTarjetasIn.class))).thenReturn(tarjetasOut);
        when(sign.buildB64Signature(Matchers.any(byte[].class), Matchers.anyString(), Matchers.anyBoolean())).thenThrow(new DAOException());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Respuesta<List<TrackingPiezaDTO>> respuesta = trackingTarjetasBO.obtenerTrackingTarjetas(cliente);
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
   
    }
    

    
}
