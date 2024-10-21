package ar.com.santanderrio.obp.servicios.login.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaAtributosVinculados;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinoVinculado;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinosPermitidos;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaFrecPermitida;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMensaje;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaProducto;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaSuscripcion;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaServiceException;
import ar.com.santanderrio.obp.servicios.comun.legal.dao.LegalDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.login.bo.impl.MyaSuscripcionesBOImpl;
import ar.com.santanderrio.obp.servicios.login.dao.MyaWSDAO;
import ar.com.santanderrio.obp.servicios.login.dto.CredencialesMya;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaDTOIn;
import ar.com.santanderrio.obp.servicios.mya.web.view.MyaUpdateMensajeView;
import ar.com.santanderrio.obp.servicios.mya.web.view.ProductoMyAEnum;
import ar.com.santanderrio.obp.servicios.mya.web.view.UpdateMensajeMyaView;
import ar.com.santanderrio.obp.servicios.mya.web.view.UpdateSuscripcionMensajeMyaView;
import ar.com.santanderrio.obp.servicios.suscripciones.dao.SuscripcionesParametrosDAO;
import ar.com.santanderrio.obp.servicios.suscripciones.dto.SuscripcionesDTO;


@RunWith(MockitoJUnitRunner.class)
public class MyaSuscripcionesBOTest {

    /** The mya BO. */
    @InjectMocks
    private MyaSuscripcionesBOImpl myaSuscripcionesBO;

    /** The mya WSDAO. */
    @Mock
    private MyaWSDAO myaWSDAO;

    /** The respuesta factory. */
    @Spy
    private RespuestaFactory respuestaFactory;

    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private SuscripcionesParametrosDAO suscripcionesParametrosDAO;
    
    @Mock
    private LegalDAO legalDAO;
    
    
    @Test
    public void obtenerMensajesSuscripcionesOK() throws MyaServiceException {

        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");

        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(armarRespuesta());
        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("FRECUENCIA");

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals("Nombre mensaje", respuesta.getRespuesta().getMensajes().get(0).getLabel());

    }

    @Test
    public void obtenerMensajesSuscripcionesErrorNoHayDestinosActivos() throws MyaServiceException, IllegalAccessException {

        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("NO HAY DESTINOS");
        
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(armarRespuestaTarjetas());
        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("FRECUENCIA");
        when(mensajeBO.obtenerMensajePorCodigo("1662")).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);
        
        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.TARJETAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());

    }    
    
    
    @Test
    public void getSuscripcionesErrorCodigoRetorno() throws MyaServiceException, IllegalAccessException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = mock(CredencialesMya.class);
        MyaXmlResponse response = new MyaXmlResponse();
        response.setCodigoRetorno("1");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("prueba error");

        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(response);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SERVICIO_MYA)).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals(1, respuesta.getItemsMensajeRespuesta().size());
        assertEquals("prueba error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    
    @SuppressWarnings("unchecked")
    @Test
    public void getSuscripcionesErrorException() throws IllegalAccessException, MyaServiceException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = mock(CredencialesMya.class);
        MyaXmlResponse response = new MyaXmlResponse();
        response.setCodigoRetorno("1");

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("prueba error");

        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString()))
                .thenThrow(MyaServiceException.class);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SERVICIO_MYA)).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals(1, respuesta.getItemsMensajeRespuesta().size());
        assertEquals("prueba error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    
    @Test
    public void getSuscripcionesErrorSinMensajes() throws MyaServiceException, IllegalAccessException {
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = mock(CredencialesMya.class);
        MyaXmlResponse response = armarRespuesta();
        response.setListMyaProducto(new ArrayList<MyaProducto>());
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("prueba error");

        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(response);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MYA_SIN_MENSAJES)).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);

        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals(1, respuesta.getItemsMensajeRespuesta().size());
        assertEquals("prueba error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        assertEquals(TipoError.SIN_MENSAJES.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    @Test
    public void getSuscripcionesErrorSinDestinosHabilitados() throws MyaServiceException, IllegalAccessException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = mock(CredencialesMya.class);
        MyaXmlResponse response = armarRespuesta();
        MyaDestinosPermitidos destinos = new MyaDestinosPermitidos();
        destinos.setDpCelular("no");
        destinos.setDpMail("no");
        response.getListMyaProducto().get(0).getListMyaMensaje().get(0).setDestinosPermitidos(destinos);
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("prueba error");

        //Then
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(response);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.MYA_SIN_DESTINOS)).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);

        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertEquals(1, respuesta.getItemsMensajeRespuesta().size());
        assertEquals("prueba error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        assertEquals(TipoError.SIN_DESTINOS.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    
    @Test
    public void getSuscripcionesErrorMailInvalido() throws MyaServiceException, IllegalAccessException {
    
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("ejemplo@error.com");
        MyaXmlResponse response = armarRespuesta();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("prueba error");

        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(response);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SERVICIO_MYA)).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected        
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertEquals(1, respuesta.getItemsMensajeRespuesta().size());
        assertEquals("prueba error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }
    
    
    @Test
    public void getSuscripcionesErrorFrecuenciaNoEncontrada() throws MyaServiceException, IllegalAccessException {
    
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");
        MyaXmlResponse response = armarRespuesta();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("prueba error");

        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("");
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(response);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SERVICIO_MYA)).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertEquals(1, respuesta.getItemsMensajeRespuesta().size());
        assertEquals("prueba error", respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        assertEquals(TipoError.ERROR_SERVICIO.getDescripcion(),
                respuesta.getItemsMensajeRespuesta().get(0).getTipoError());
    }
    
    
    @Test
    public void updateMensajeOK() {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();
        
        //Then
        Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void updateMensajeModificacionOk() {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setOperacionUpdate("modificacion");
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setNumeroSuscripcion("59675066");
        
        List<String> listaDias = new ArrayList<String>();
        listaDias.add("Lunes");
        listaDias.add("Martes");
        listaDias.add("Miércoles");
        listaDias.add("Jueves");
        listaDias.add("Viernes");

        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setListaDias(listaDias);
        
        //Then
        Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void updateMensajeBajaOk() {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setOperacionUpdate("baja");
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setNumeroSuscripcion("59675066");

        //Then
        Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void updateMensajeErrorOperacionUpdateErronea() {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setOperacionUpdate("PEPE");
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setNumeroSuscripcion("59675066");

        //Then
        Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void updateMensajeErrorOperacionUpdateNula() {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setOperacionUpdate(null);
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setNumeroSuscripcion("59675066");

        //Then
        Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void updateMensajeAlertaSaldoOk() {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setImporteMinimo("100");
        
        //Then
        Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void updateMensajeListaDAPOk() {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setFrecuencia(null);

        List<String> listaDAP = new ArrayList<String>();
        listaDAP.add("0");
        listaDAP.add("1");
        listaDAP.add("2");
        listaDAP.add("3");
        
        updateMensajeView.getDatos().getListaUpdatesSuscripciones().get(0).setListaDAP(listaDAP);
        
        //Then
        Respuesta<Void> respuesta = myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
        //Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        
    }
    
    
    @Test
    public void updateMensajeMyaServiceException() throws MyaServiceException, IllegalAccessException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        MyaUpdateMensajeView updateMensajeView = updateMensajeIn();

        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("ERROR AL HACER UPDATE");
        
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_MYA_UPDATE_MENSAJES)).thenReturn(mensaje);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);
        Mockito.doThrow(MyaServiceException.class).when(myaWSDAO).updateMensajes(Matchers.any(Cliente.class), Matchers.any(UpdateMensajesMyaDTOIn.class));

        //Then
        myaSuscripcionesBO.updateMensajes(cliente, updateMensajeView);
        
    }
    

    @Test
    public void getSuscripcionesSorpresaOk() throws MyaServiceException, DAOException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");
        credenciales.setCelular("11-49392827");
        credenciales.setCelularSecundario("11-94827263");
        
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(armarRespuestaSorpresa());
        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("FRECUENCIA");
        when(legalDAO.obtenerLegal(Matchers.anyString())).thenReturn("LEGALES");

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals("Nombre mensaje", respuesta.getRespuesta().getMensajes().get(0).getLabel());
        
    }
    
    
    @SuppressWarnings("unchecked")
    @Test
    public void getSuscripcionesSorpresaDAOException() throws MyaServiceException, DAOException, IllegalAccessException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");
        credenciales.setCelular("11-49392827");
        credenciales.setCelularSecundario("11-94827263");
        
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("MENSAJE ERROR");
        
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(armarRespuestaSorpresa());
        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("FRECUENCIA");
        when(legalDAO.obtenerLegal(Matchers.anyString())).thenThrow(DAOException.class);
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SERVICIO_MYA)).thenReturn(mensajeError);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);
        
        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());        
    }
    
    
    @Test
    public void getSuscripcionesCasoAlertaSaldo() throws MyaServiceException, DAOException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");
        credenciales.setCelular("11-49392827");
        credenciales.setCelularSecundario("11-94827263");
        
        MyaXmlResponse respuestaXml = armarRespuesta();
        respuestaXml.getListMyaProducto().get(0).getListMyaMensaje().get(0).setNroMensaje("18");
        
        MyaAtributosVinculados atributosVinculados = new MyaAtributosVinculados();
        atributosVinculados.setAttrVincId("1");
        atributosVinculados.setAttrVincClave("MINARS");
        atributosVinculados.setAttrVincDato("100");
        
        List<MyaAtributosVinculados> listaAtributosVinculados = new ArrayList<MyaAtributosVinculados>();
        listaAtributosVinculados.add(atributosVinculados);
        respuestaXml.getListMyaProducto().get(0).getListMyaMensaje().get(0)
        .getListMyaSuscripcion().get(0).setListMyaAtributosVinculados(listaAtributosVinculados);
        
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(respuestaXml);
        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("FRECUENCIA");
        when(legalDAO.obtenerLegal(Matchers.anyString())).thenReturn("LEGALES");

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals("Nombre mensaje", respuesta.getRespuesta().getMensajes().get(0).getLabel());
        
    }
    
    
    @Test
    public void getSuscripcionesListaFrecuenciasOK() throws MyaServiceException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");
        credenciales.setCelular("11-49392827");
        credenciales.setCelularSecundario("11-94827263");
        
        MyaXmlResponse respuestaXml = armarRespuesta();
        respuestaXml.getListMyaProducto().get(0).getListMyaMensaje().get(0).setNroMensaje("70");
        
        
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(respuestaXml);
        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("FRECUENCIA");

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals("Nombre mensaje", respuesta.getRespuesta().getMensajes().get(0).getLabel());
        
    }
    
    
    @Test
    public void getSuscripcionesListaDAPOK() throws MyaServiceException, IllegalAccessException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        CredencialesMya credenciales = new CredencialesMya();
        credenciales.setEmail("mail@prueba.com");
        credenciales.setEmailSecundario("mail2@prueba.com");
        credenciales.setCelular("11-49392827");
        credenciales.setCelularSecundario("11-94827263");
        
        MyaXmlResponse respuestaXml = armarRespuesta();
        respuestaXml.getListMyaProducto().get(0).getListMyaMensaje().get(0).setNroMensaje("1");
        
        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("MENSAJE ERROR DE DIA AVISO PREVIO");
        
        when(myaWSDAO.getSuscripciones(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(respuestaXml);
        when(suscripcionesParametrosDAO.obtenerFrecuencia(Matchers.anyString())).thenReturn("FRECUENCIA");
        when(suscripcionesParametrosDAO.obtenerCodigoDiaAvisoPrevio(Matchers.anyString())).thenReturn("DIA AVISO PREVIO");
        when(suscripcionesParametrosDAO.obtenerDiaAvisoPrevio(Matchers.anyString())).thenReturn("XX DIAS PREVIOS");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SERVICIO_MYA)).thenReturn(mensajeError);
        FieldUtils.writeField(respuestaFactory, "mensajeBO", mensajeBO, true);

        //Then
        Respuesta<SuscripcionesDTO> respuesta = myaSuscripcionesBO.obtenerMensajesSuscripciones(cliente, credenciales,
                ProductoMyAEnum.CUENTAS);

        //Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals("Nombre mensaje", respuesta.getRespuesta().getMensajes().get(0).getLabel());
        
    }
    
        
    private MyaUpdateMensajeView updateMensajeIn() {
        
        MyaUpdateMensajeView updateMensajeView = new MyaUpdateMensajeView();
        UpdateMensajeMyaView datos = new UpdateMensajeMyaView();
        List<UpdateSuscripcionMensajeMyaView> listaUpdatesSuscripciones = new ArrayList <UpdateSuscripcionMensajeMyaView>();
        UpdateSuscripcionMensajeMyaView suscripcionUno = new UpdateSuscripcionMensajeMyaView();
        
        suscripcionUno.setDestinoSuscripcion("MAIL");
        suscripcionUno.setFrecuencia("Avisar cuando hay novedades");
        suscripcionUno.setNumeroMensaje("4");
        suscripcionUno.setOperacionUpdate("alta");
        suscripcionUno.setSecuencia("1");
        
        listaUpdatesSuscripciones.add(suscripcionUno);
        datos.setListaUpdatesSuscripciones(listaUpdatesSuscripciones);
        updateMensajeView.setDatos(datos);
        
        return updateMensajeView;
    }

    
    private MyaXmlResponse armarRespuesta() {

        MyaXmlResponse respuesta = new MyaXmlResponse();

        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("0");

        MyaDestino destino1 = new MyaDestino();
        destino1.setId("1");
        destino1.setTipo("CEL");
        destino1.setSecuencia("1");
        destino1.setDescripcion("11-12345678");
        destino1.setEmpresaCel("MOVI");
        destino1.setEstado("1");

        MyaDestino destino2 = new MyaDestino();
        destino2.setTipo("MAIL");
        destino2.setSecuencia("1");
        destino2.setDescripcion("mail@prueba.com");
        destino2.setEstado("1");

        MyaDestino destino3 = new MyaDestino();
        destino3.setTipo("MAIL");
        destino3.setSecuencia("2");
        destino3.setDescripcion("mail2@prueba.com");
        destino3.setEstado("1");
        
        List<MyaDestino> listDestinos = new ArrayList<MyaDestino>();
        listDestinos.add(destino1);
        listDestinos.add(destino2);
        listDestinos.add(destino3);
        respuesta.setListMyaDestino(listDestinos);

        MyaFrecPermitida frecuenciaPermitida = new MyaFrecPermitida();
        frecuenciaPermitida.setIdFrecPermitida("1");
        frecuenciaPermitida.setFrecuenciaPermitida("ECM");
        List<MyaFrecPermitida> listaMyaFrecPermitidas = new ArrayList<MyaFrecPermitida>();
        listaMyaFrecPermitidas.add(frecuenciaPermitida);

        MyaDestinosPermitidos destinos = new MyaDestinosPermitidos();
        destinos.setDpMail("SI");
        destinos.setDpCelular("SI");
        destinos.setDpAgenda("SI");

        MyaDestinoVinculado destinoVinculado = new MyaDestinoVinculado();
        destinoVinculado.setDestVincTipo("MAIL");
        destinoVinculado.setDestVincSecuencia("1");

        MyaSuscripcion suscripcion = new MyaSuscripcion();
        suscripcion.setIdSuscripcion("1");
        suscripcion.setNroSuscripcion("14702786");
        suscripcion.setFrcVinculada("ECM");
        suscripcion.setDapVinculada("NONE");
        suscripcion.setDestinoVinculado(destinoVinculado);

        List<MyaSuscripcion> listaSuscripciones = new ArrayList<MyaSuscripcion>();
        listaSuscripciones.add(suscripcion);

        MyaMensaje mensaje = new MyaMensaje();
        mensaje.setIdMensaje("1");
        mensaje.setNombreMensaje("NOMBRE MENSAJE");
        mensaje.setDescripcionMensaje("DESCRIPCION MENSAJE");
        mensaje.setFrecuenciaMensaje("ECM");
        mensaje.setListMyaFrecPermitida(listaMyaFrecPermitidas);
        mensaje.setDapDefault("NONE");
        mensaje.setDestinosPermitidos(destinos);
        mensaje.setListMyaSuscripcion(listaSuscripciones);

        List<MyaMensaje> listaMensajes = new ArrayList<MyaMensaje>();
        listaMensajes.add(mensaje);

        MyaProducto producto = new MyaProducto();
        producto.setNroProducto("1");
        producto.setListMyaMensaje(listaMensajes);

        List<MyaProducto> listaProductos = new ArrayList<MyaProducto>();
        listaProductos.add(producto);

        respuesta.setListMyaProducto(listaProductos);

        return respuesta;
    }
    
    private MyaXmlResponse armarRespuestaTarjetas() {

        MyaXmlResponse respuesta = new MyaXmlResponse();

        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("0");

        MyaDestino destino1 = new MyaDestino();
        destino1.setId("1");
        destino1.setTipo("CEL");
        destino1.setSecuencia("1");
        destino1.setDescripcion("11-12345678");
        destino1.setEmpresaCel("MOVI");
        destino1.setEstado("1");

        MyaDestino destino2 = new MyaDestino();
        destino2.setTipo("MAIL");
        destino2.setSecuencia("1");
        destino2.setDescripcion("mail@prueba.com");
        destino2.setEstado("0");

        MyaDestino destino3 = new MyaDestino();
        destino3.setTipo("MAIL");
        destino3.setSecuencia("2");
        destino3.setDescripcion("mail2@prueba.com");
        destino3.setEstado("0");
        
        List<MyaDestino> listDestinos = new ArrayList<MyaDestino>();
        listDestinos.add(destino1);
        listDestinos.add(destino2);
        listDestinos.add(destino3);
        respuesta.setListMyaDestino(listDestinos);

        MyaFrecPermitida frecuenciaPermitida = new MyaFrecPermitida();
        frecuenciaPermitida.setIdFrecPermitida("1");
        frecuenciaPermitida.setFrecuenciaPermitida("ECM");
        List<MyaFrecPermitida> listaMyaFrecPermitidas = new ArrayList<MyaFrecPermitida>();
        listaMyaFrecPermitidas.add(frecuenciaPermitida);

        MyaDestinosPermitidos destinos = new MyaDestinosPermitidos();
        destinos.setDpMail("SI");
        destinos.setDpCelular("NO");
        destinos.setDpAgenda("SI");

        MyaDestinoVinculado destinoVinculado = new MyaDestinoVinculado();
        destinoVinculado.setDestVincTipo("MAIL");
        destinoVinculado.setDestVincSecuencia("1");

        MyaSuscripcion suscripcion = new MyaSuscripcion();
        suscripcion.setIdSuscripcion("1");
        suscripcion.setNroSuscripcion("14702786");
        suscripcion.setFrcVinculada("ECM");
        suscripcion.setDapVinculada("NONE");
        suscripcion.setDestinoVinculado(destinoVinculado);

        List<MyaSuscripcion> listaSuscripciones = new ArrayList<MyaSuscripcion>();
        listaSuscripciones.add(suscripcion);

        MyaMensaje mensaje = new MyaMensaje();
        mensaje.setIdMensaje("1");
        mensaje.setNombreMensaje("NOMBRE MENSAJE");
        mensaje.setDescripcionMensaje("DESCRIPCION MENSAJE");
        mensaje.setFrecuenciaMensaje("ECM");
        mensaje.setListMyaFrecPermitida(listaMyaFrecPermitidas);
        mensaje.setDapDefault("NONE");
        mensaje.setDestinosPermitidos(destinos);
        mensaje.setListMyaSuscripcion(listaSuscripciones);

        List<MyaMensaje> listaMensajes = new ArrayList<MyaMensaje>();
        listaMensajes.add(mensaje);

        MyaProducto producto = new MyaProducto();
        producto.setNroProducto("1");
        producto.setListMyaMensaje(listaMensajes);

        List<MyaProducto> listaProductos = new ArrayList<MyaProducto>();
        listaProductos.add(producto);

        respuesta.setListMyaProducto(listaProductos);

        return respuesta;
    }
    
    
    private MyaXmlResponse armarRespuestaSorpresa() {
        
        MyaXmlResponse respuesta = armarRespuesta();
        respuesta.getListMyaProducto().get(0).getListMyaMensaje().get(0).setNroMensaje("102");
        respuesta.getListMyaProducto().get(0).getListMyaMensaje().get(0).getListMyaSuscripcion().get(0).getDestinoVinculado().setDestVincTipo("CEL");
        
        MyaDestino destino4 = new MyaDestino();
        destino4.setId("1");
        destino4.setTipo("CEL");
        destino4.setSecuencia("2");
        destino4.setDescripcion("11-12366678");
        destino4.setEmpresaCel("MOVI");
        destino4.setEstado("1");
        
        respuesta.getListMyaDestino().add(destino4);
        
        MyaDestinoVinculado destinoVinculado = new MyaDestinoVinculado();
        destinoVinculado.setDestVincTipo("CEL");
        destinoVinculado.setDestVincSecuencia("2");

        MyaSuscripcion suscripcion = new MyaSuscripcion();
        suscripcion.setIdSuscripcion("1");
        suscripcion.setNroSuscripcion("14702786");
        suscripcion.setFrcVinculada("ECM");
        suscripcion.setDapVinculada("NONE");
        suscripcion.setDestinoVinculado(destinoVinculado);
        
        respuesta.getListMyaProducto().get(0).getListMyaMensaje().get(0).getListMyaSuscripcion().add(suscripcion);
        
        return respuesta;
    }
    
}