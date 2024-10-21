package ar.com.santanderrio.obp.servicios.mya.dao;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaCodOperacionEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestino;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinoVinculado;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaDestinosPermitidos;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaFrecPermitida;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMensaje;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaMensajeError;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaProducto;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaSuscripcion;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoIdEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaTipoPersonaEnum;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlRequest;
import ar.com.santanderrio.obp.generated.webservices.mya.dominio.MyaXmlResponse;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaCodigoRetornoErrorException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaMailRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaServiceException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaTelefonoRegistradoException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.MyaWarningException;
import ar.com.santanderrio.obp.servicios.login.dao.impl.MyaWSDAOImpl;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOIn;
import ar.com.santanderrio.obp.servicios.login.dto.MyaDTOOut;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaDTOIn;
import ar.com.santanderrio.obp.servicios.mya.entities.UpdateMensajesMyaSuscripcion;

@RunWith(MockitoJUnitRunner.class)
public class MyaWSDAOIT {

    @InjectMocks
    private MyaWSDAOImpl myaWSDAO;
    
    @Mock
    private MyaDAO myaDAO;
    
    private static final String NUP = "12345";
    
    
    @Test
    public void getEstadoClienteOK() throws DAOException, MyaServiceException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(armarRespuesta());
        
        //Then
        MyaDTOOut respuesta = myaWSDAO.getEstadoCliente(myaDTOIn);
        
        //Expected
        Assert.assertNotNull(respuesta);
 
    }
    
    
    @Test
    public void getEstadoClienteOKSoloPrincipales() throws DAOException, MyaServiceException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        myaDTOIn.setSoloPrincipales(true);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(armarRespuesta());
        
        //Then
        MyaDTOOut respuesta = myaWSDAO.getEstadoCliente(myaDTOIn);
        
        //Expected
        Assert.assertNotNull(respuesta);
 
    }
    
    
    @SuppressWarnings("unchecked")
    @Test (expected = MyaServiceException.class)
    public void getEstadoClienteMyaServiceException() throws DAOException, MyaServiceException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenThrow(DAOException.class);
        
        //Then
        myaWSDAO.getEstadoCliente(myaDTOIn);
 
    }
    
    
    @Test
    public void updateDestinoOK() throws MyaCodigoRetornoErrorException, MyaServiceException, MyaMailRegistradoException, MyaTelefonoRegistradoException, DAOException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("0");

        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateDestino(myaDTOIn);    
        
    } 
    
    
    @SuppressWarnings("unchecked")
    @Test (expected = MyaServiceException.class)
    public void updateDestinoMyaServiceException() throws MyaCodigoRetornoErrorException, MyaServiceException, MyaMailRegistradoException, MyaTelefonoRegistradoException, DAOException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);

        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenThrow(DAOException.class);
        
        //Then
        myaWSDAO.updateDestino(myaDTOIn);    
        
    } 
    
    
    @Test (expected = MyaMailRegistradoException.class)
    public void updateDestinoMyaMailRegistradoException() throws MyaCodigoRetornoErrorException, MyaServiceException, MyaMailRegistradoException, MyaTelefonoRegistradoException, DAOException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("2");

        MyaMensajeError mensajeError = new MyaMensajeError();
        mensajeError.setCodMensaje("20");
        MyaMensajeError mensajeErrorIncorrecto = new MyaMensajeError();
        mensajeErrorIncorrecto.setCodMensaje("24");
        
        List<MyaMensajeError> listaErrores = new ArrayList<MyaMensajeError>();
        listaErrores.add(mensajeErrorIncorrecto);
        listaErrores.add(mensajeError);
        
        respuesta.setListMyaMensajeError(listaErrores);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateDestino(myaDTOIn);    
        
    }
    
    
    @Test
    public void updateDestinoMyaMailRegistradoExceptionNoMensajesError() throws DAOException, MyaCodigoRetornoErrorException, MyaServiceException, MyaMailRegistradoException, MyaTelefonoRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("2");
               
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateDestino(myaDTOIn);    
        
    } 
    
    
    @Test (expected = MyaTelefonoRegistradoException.class)
    public void updateDestinoMyaTelefonoRegistradoException() throws DAOException, MyaServiceException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException, MyaMailRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("1");
        
        MyaMensajeError mensajeError = new MyaMensajeError();
        mensajeError.setCodMensaje("8");
        
        List<MyaMensajeError> listaErrores = new ArrayList<MyaMensajeError>();
        listaErrores.add(mensajeError);
        
        respuesta.setListMyaMensajeError(listaErrores);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateDestino(myaDTOIn);    
        
    } 
    
    
    @Test (expected = MyaCodigoRetornoErrorException.class)
    public void updateDestinoMyaCodigoRetornoErrorException() throws DAOException, MyaServiceException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException, MyaMailRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("1");
        
        MyaMensajeError mensajeError = new MyaMensajeError();
        mensajeError.setCodMensaje("10");
        
        List<MyaMensajeError> listaErrores = new ArrayList<MyaMensajeError>();
        listaErrores.add(mensajeError);
        
        respuesta.setListMyaMensajeError(listaErrores);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateDestino(myaDTOIn);    
        
    }
    
    
    @Test
    public void registrarConDestinoOk() throws DAOException, MyaServiceException, MyaWarningException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("0");

        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.registrarConDestino(myaDTOIn);    
        
    } 
    
    
    @SuppressWarnings("unchecked")
    @Test (expected = MyaServiceException.class)
    public void registrarConDestinoMyaServiceException() throws DAOException, MyaServiceException, MyaWarningException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenThrow(DAOException.class);
        
        //Then
        myaWSDAO.registrarConDestino(myaDTOIn);    
        
    } 
    
    
    @Test (expected = MyaWarningException.class)
    public void registrarConDestinoMyaWarningException() throws DAOException, MyaServiceException, MyaWarningException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("2");
        
        MyaMensajeError mensajeError = new MyaMensajeError();
        mensajeError.setCodMensaje("20");
        MyaMensajeError mensajeErrorIncorrecto = new MyaMensajeError();
        mensajeErrorIncorrecto.setCodMensaje("24");
        
        List<MyaMensajeError> listaErrores = new ArrayList<MyaMensajeError>();
        listaErrores.add(mensajeErrorIncorrecto);
        listaErrores.add(mensajeError);
        
        respuesta.setListMyaMensajeError(listaErrores);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.registrarConDestino(myaDTOIn);    
        
    } 
    
    
    @Test
    public void registrarConDestinoMyaWarningExceptionNoMensajesError() throws DAOException, MyaServiceException, MyaWarningException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("2");
               
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.registrarConDestino(myaDTOIn);    
        
    } 
    
    
    @Test (expected = MyaTelefonoRegistradoException.class)
    public void registrarConDestinoMyaTelefonoRegistradoException() throws DAOException, MyaServiceException, MyaWarningException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("1");
        
        MyaMensajeError mensajeError = new MyaMensajeError();
        mensajeError.setCodMensaje("8");
        
        List<MyaMensajeError> listaErrores = new ArrayList<MyaMensajeError>();
        listaErrores.add(mensajeError);
        
        respuesta.setListMyaMensajeError(listaErrores);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.registrarConDestino(myaDTOIn);    
        
    } 
    
    
    @Test (expected = MyaCodigoRetornoErrorException.class)
    public void registrarConDestinoMyaCodigoRetornoErrorException() throws DAOException, MyaServiceException, MyaWarningException, MyaCodigoRetornoErrorException, MyaTelefonoRegistradoException {
        
        //When
        MyaDTOIn myaDTOIn = cargarMyaDTOIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("1");
        
        MyaMensajeError mensajeError = new MyaMensajeError();
        mensajeError.setCodMensaje("10");
        
        List<MyaMensajeError> listaErrores = new ArrayList<MyaMensajeError>();
        listaErrores.add(mensajeError);
        
        respuesta.setListMyaMensajeError(listaErrores);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.registrarConDestino(myaDTOIn);    
        
    }
    
    
    @Test
    public void updateEstadoClienteOk() throws DAOException, MyaWarningException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("0");

        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateEstadoCliente(myaDTOIn);    
        
    }    
    
    
    @SuppressWarnings("unchecked")
    @Test (expected = MyaWarningException.class)
    public void updateEstadoClienteMyaWarningException() throws DAOException, MyaWarningException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenThrow(MyaWarningException.class);
        
        //Then
        myaWSDAO.updateEstadoCliente(myaDTOIn);    
        
    } 

    
    @Test (expected = MyaWarningException.class)
    public void updateEstadoClienteMyaWarningExceptionErrorEnMetodo() throws DAOException, MyaWarningException {
        
        //When
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("1");

        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
       
        //Then
        myaWSDAO.updateEstadoCliente(myaDTOIn);    
        
    }
    
    
    @Test
    public void getSuscripcionesOK() throws MyaServiceException, DAOException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(armarRespuesta());
        
        //Then
        MyaXmlResponse respuesta = myaWSDAO.getSuscripciones(cliente, "1");
        
        //Expected
        Assert.assertNotNull(respuesta);
        
    }
    
    
    @SuppressWarnings("unchecked")
    @Test (expected = MyaServiceException.class)
    public void getSuscripcionesDAOException() throws MyaServiceException, DAOException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenThrow(MyaServiceException.class);
        
        //Then
        myaWSDAO.getSuscripciones(cliente, "1");
        
    }
    
    
    @Test
    public void updateMensajeOk() throws DAOException, MyaServiceException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        UpdateMensajesMyaDTOIn updateMensajesMyaDTOIn = updateMensajeIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setClienteEstado("SA");
        respuesta.setCodigoRetorno("0");

        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateMensajes(cliente, updateMensajesMyaDTOIn);    
        
    }
        

    @Test (expected = MyaServiceException.class)
    public void updateMensajeMyaServiceException() throws DAOException, MyaServiceException {
        
        //When
        Cliente cliente = mock(Cliente.class);
        UpdateMensajesMyaDTOIn updateMensajesMyaDTOIn = updateMensajeIn();
        
        MyaXmlResponse respuesta = new MyaXmlResponse();
        respuesta.setCodigoRetorno("1");

        when(myaDAO.invocarMya(Matchers.any(MyaXmlRequest.class))).thenReturn(respuesta);
        
        //Then
        myaWSDAO.updateMensajes(cliente, updateMensajesMyaDTOIn); 
        
        //Expected
        
        
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

        MyaDestino destino3 = new MyaDestino();
        destino3.setTipo("MAIL");
        destino3.setSecuencia("2");
        destino3.setDescripcion("mail2@prueba.com");
        
        MyaDestino destino4 = new MyaDestino();
        destino4.setId("4");
        destino4.setTipo("CEL");
        destino4.setSecuencia("2");
        destino4.setDescripcion("11-12435678");
        destino4.setEmpresaCel("MOVI");
        destino4.setEstado("1");

        List<MyaDestino> listDestinos = new ArrayList<MyaDestino>();
        listDestinos.add(destino1);
        listDestinos.add(destino2);
        listDestinos.add(destino3);
        listDestinos.add(destino4);
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
    
    
    private UpdateMensajesMyaDTOIn updateMensajeIn() {
        
        UpdateMensajesMyaDTOIn updateMyaIn = new UpdateMensajesMyaDTOIn();
        List<UpdateMensajesMyaSuscripcion> listaUpdateMensajes = new ArrayList<UpdateMensajesMyaSuscripcion>();
        
        UpdateMensajesMyaSuscripcion updateMensajeAlta = new UpdateMensajesMyaSuscripcion();
        updateMensajeAlta.setMyaCodOperacionEnum (MyaCodOperacionEnum.ALTA);
        updateMensajeAlta.setNumeroMensajeSuscripcion("1");
        updateMensajeAlta.setCodigoFrecuenciaSuscripcion("ECM");
        updateMensajeAlta.setAtributoVinculadoClave("");
        updateMensajeAlta.setAtributoVinculadoValor("");
        
        UpdateMensajesMyaSuscripcion updateMensajeBaja = new UpdateMensajesMyaSuscripcion();
        updateMensajeBaja.setMyaCodOperacionEnum (MyaCodOperacionEnum.BAJA);
        updateMensajeBaja.setNumeroMensajeSuscripcion("2");
        updateMensajeBaja.setCodigoFrecuenciaSuscripcion("ECM");
        updateMensajeBaja.setNumeroSuscripcion("123456");

        UpdateMensajesMyaSuscripcion updateMensajeAltaAtributoVinculado = new UpdateMensajesMyaSuscripcion();
        updateMensajeAltaAtributoVinculado.setMyaCodOperacionEnum (MyaCodOperacionEnum.ALTA);
        updateMensajeAltaAtributoVinculado.setNumeroMensajeSuscripcion("3");
        updateMensajeAltaAtributoVinculado.setCodigoFrecuenciaSuscripcion("ECM");
        updateMensajeAltaAtributoVinculado.setAtributoVinculadoClave("MINARS");
        updateMensajeAltaAtributoVinculado.setAtributoVinculadoValor("1000");
        
        UpdateMensajesMyaSuscripcion updateMensajeAltaAtributoVinculadoSinClave = new UpdateMensajesMyaSuscripcion();
        updateMensajeAltaAtributoVinculadoSinClave.setMyaCodOperacionEnum (MyaCodOperacionEnum.ALTA);
        updateMensajeAltaAtributoVinculadoSinClave.setNumeroMensajeSuscripcion("3");
        updateMensajeAltaAtributoVinculadoSinClave.setCodigoFrecuenciaSuscripcion("ECM");
        updateMensajeAltaAtributoVinculadoSinClave.setAtributoVinculadoClave("");
        updateMensajeAltaAtributoVinculadoSinClave.setAtributoVinculadoValor("1000");
        
        UpdateMensajesMyaSuscripcion updateMensajeAltaAtributoVinculadoSinValor = new UpdateMensajesMyaSuscripcion();
        updateMensajeAltaAtributoVinculadoSinValor.setMyaCodOperacionEnum (MyaCodOperacionEnum.ALTA);
        updateMensajeAltaAtributoVinculadoSinValor.setNumeroMensajeSuscripcion("3");
        updateMensajeAltaAtributoVinculadoSinValor.setCodigoFrecuenciaSuscripcion("ECM");
        updateMensajeAltaAtributoVinculadoSinValor.setAtributoVinculadoClave("MINARS");
        updateMensajeAltaAtributoVinculadoSinValor.setAtributoVinculadoValor("");
        
        listaUpdateMensajes.add(updateMensajeAlta);
        listaUpdateMensajes.add(updateMensajeBaja);
        listaUpdateMensajes.add(updateMensajeAltaAtributoVinculado);
        listaUpdateMensajes.add(updateMensajeAltaAtributoVinculadoSinClave);
        listaUpdateMensajes.add(updateMensajeAltaAtributoVinculadoSinValor);

        updateMyaIn.setListaUpdateMensajesMyaSuscripcion(listaUpdateMensajes);
        
        return updateMyaIn;
    }
 
    
    private List<MyaDestino> cargarListaDestinos() {
        
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

        MyaDestino destino3 = new MyaDestino();
        destino3.setTipo("MAIL");
        destino3.setSecuencia("2");
        destino3.setDescripcion("mail2@prueba.com");

        List<MyaDestino> listDestinos = new ArrayList<MyaDestino>();
        listDestinos.add(destino1);
        listDestinos.add(destino2);
        listDestinos.add(destino3);
        
        return listDestinos;
        
    }
    
    private MyaDTOIn cargarMyaDTOIn() {
        
        MyaDTOIn myaDTOIn = new MyaDTOIn();
        myaDTOIn.setNup(NUP);
        myaDTOIn.setMyaTipoIdEnum(MyaTipoIdEnum.DNI);
        myaDTOIn.setDni("34519673");
        myaDTOIn.setMyaTipoPersonaEnum(MyaTipoPersonaEnum.INDIVIDUO);
        myaDTOIn.setFechaDeNacimiento("12/10/91");
        myaDTOIn.setNombre("Pepe");
        myaDTOIn.setPrimerApellido("Tarjota");
        myaDTOIn.setSegundoApellido("Perez");
        myaDTOIn.setListaDestinos(cargarListaDestinos());
        
        return myaDTOIn;
        
    }
    
}