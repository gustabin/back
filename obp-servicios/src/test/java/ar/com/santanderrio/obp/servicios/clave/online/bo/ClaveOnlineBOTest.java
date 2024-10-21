package ar.com.santanderrio.obp.servicios.clave.online.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

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
import ar.com.santanderrio.obp.servicios.clave.online.bo.impl.ClaveOnlineBOImpl;
import ar.com.santanderrio.obp.servicios.clave.online.dao.AltaSGIClaveDAO;
import ar.com.santanderrio.obp.servicios.clave.online.dao.PreguntasSeguridadDAO;
import ar.com.santanderrio.obp.servicios.clave.online.entities.AltaSGIClaveInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.CredencialesClaveOnline;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteInEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.IdentificadorClienteOutEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasEntity;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridad;
import ar.com.santanderrio.obp.servicios.clave.online.entities.PreguntasSeguridadResponse;
import ar.com.santanderrio.obp.servicios.clave.online.entities.RespuestaEntity;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineClaveRepeException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineClaveTrivialException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineUsuRepeException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClaveOnlineUsuTrivialException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ClienteBloqueadoException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorCicsException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorDb2Exception;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.ErrorModuloException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.FuncionInvalidaException;
import ar.com.santanderrio.obp.servicios.clave.online.excepciones.HayRespuestasErroneasException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.CredencialesException;
import ar.com.santanderrio.obp.servicios.clientes.exceptions.ErrorRutinaFechasException;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.exception.RobotException;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;

/**
 * The Class ClaveOnlineBOTest.
 */

@RunWith(MockitoJUnitRunner.class)
public class ClaveOnlineBOTest {

    @InjectMocks
    private ClaveOnlineBOImpl claveOnlineBO;

    /** The Credenciales dao. */
    @Mock
    private PreguntasSeguridadDAO preguntasSeguridadDAO;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

	/** The ModuloPermiso BO. */
    @Mock
	private ModuloPermisoBO moduloPermisoBO;

    /** The alta SGI clave DAO. */
    @Mock
    private AltaSGIClaveDAO altaSGIClaveDAO;

    /**
     * Validar credenciales usuario cambio pendiente test.
     * @throws ErrorModuloException 
     * @throws FuncionInvalidaException 
     * @throws ErrorCicsException 
     * @throws ErrorDb2Exception 
     * @throws ErrorRutinaFechasException 
     * @throws HayRespuestasErroneasException 
     *
     * @throws CredencialesException
     *             the credenciales exception
     */
    @Test
    public void obtenerPreguntasSeguridadTest() throws ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException, HayRespuestasErroneasException {
    	CredencialesClaveOnline cliente = new CredencialesClaveOnline();
        
        IdentificadorClienteOutEntity identificador = new IdentificadorClienteOutEntity();
        cliente.setDniOri("323214124");
        cliente.setFechaDeNacimiento("19981223");

        PreguntasSeguridadResponse answer = new PreguntasSeguridadResponse();
        answer.setCantidadPreguntas(2);
        answer.setCantidadRespuestas(2);
        
        ArrayList<PreguntasEntity> preguntas = new ArrayList<PreguntasEntity>();
        PreguntasEntity pregunta = new PreguntasEntity();
        pregunta.setIdentificadorPregunta("01");
        pregunta.setTexto("Nombre ?");
        preguntas.add(pregunta);

        PreguntasEntity pregunta2 = new PreguntasEntity();
        pregunta2.setIdentificadorPregunta("02");
        pregunta2.setTexto("apellido ?");
        preguntas.add(pregunta2);
        
        answer.setPreguntas(preguntas);
        
        ArrayList<RespuestaEntity> respuestas = new ArrayList<RespuestaEntity>();
        RespuestaEntity respuesta = new RespuestaEntity();
        respuesta.setOpcion1("pepe1");
        respuestas.add(respuesta);
        respuesta.setOpcion2("pepe2");
        respuestas.add(respuesta);
        respuesta.setOpcion3("pepe3");
        respuestas.add(respuesta);
        respuesta.setOpcion4("pepe4");
        respuestas.add(respuesta);
        respuesta.setOpcion5("");
        respuestas.add(respuesta);
        
        RespuestaEntity respuesta2 = new RespuestaEntity();
        respuesta2.setOpcion1("pepe1");
        respuestas.add(respuesta);
        respuesta2.setOpcion2("pepe2");
        respuestas.add(respuesta);
        respuesta2.setOpcion3("pepe3");
        respuestas.add(respuesta);
        respuesta2.setOpcion4("pepe4");
        respuestas.add(respuesta);
        respuesta2.setOpcion5("");
        respuestas.add(respuesta2);
        
        answer.setRespuestas(respuestas);
        
        Mockito.when(preguntasSeguridadDAO.obtenerPreguntasSeguridad(Matchers.any(IdentificadorClienteInEntity.class)))
                .thenReturn(answer);
        
        Respuesta<PreguntasSeguridad> response = claveOnlineBO.obtenerPreguntasSeguridad(cliente, identificador);

        assertNotNull(response);
        PreguntasSeguridad pregun = response.getRespuesta();
        
        assertEquals(pregun.getCantidadPreguntas(),new Integer(2));
        assertEquals(pregun.getPreguntas().get(0).getIdPregunta(),"01");
        
    }

    @Test
    public void altaSGIClaveCtxOK() {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.OK, response.getEstadoRespuesta());
    }

    @Test
    public void altaSGIClaveProxyLoginOK() {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.OK, response.getEstadoRespuesta());
    }

    @Test
    public void altaSGIClaveCtxErrorDAO() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new DAOException()).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CONFIRMACION_CLAVE_TIMEOUT.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorClaveRepetida() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineClaveRepeException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorClaveUsuRepetido() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineUsuRepeException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorClaveUsuTrivial() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineUsuTrivialException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorClaveTrivial() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineClaveTrivialException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorBloqueado() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClienteBloqueadoException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_CLIENTE_BLOQUEADO.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorTotal() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new RobotException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.LOGIN_ERROR_TOTAL.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorFechas() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorRutinaFechasException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_RUTINA_FECHAS.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorModulo() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorModuloException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_MODULO.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorDB2() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorDb2Exception("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_SQL_CODE_DB2.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorFuncionInvalida() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new FuncionInvalidaException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_FUNCION_INVALIDA.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveCtxErrorCics() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(false);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorCicsException("")).when(altaSGIClaveDAO).altaSGIClave(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_CICS.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorDAO() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new DAOException()).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CONFIRMACION_CLAVE_TIMEOUT.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorClaveRepetida() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineClaveRepeException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorClaveUsuRepetido() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineUsuRepeException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorClaveUsuTrivial() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineUsuTrivialException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorClaveTrivial() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClaveOnlineClaveTrivialException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_PUEDE_REINTENTAR.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorBloqueado() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ClienteBloqueadoException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.CLAVE_ONLINE_CLIENTE_BLOQUEADO.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorTotal() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new RobotException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.LOGIN_ERROR_TOTAL.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorFechas() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorRutinaFechasException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_RUTINA_FECHAS.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorModulo() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorModuloException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_MODULO.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorDB2() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorDb2Exception("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_SQL_CODE_DB2.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorFuncionInvalida() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new FuncionInvalidaException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_FUNCION_INVALIDA.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }

    @Test
    public void altaSGIClaveProxyLoginErrorCics() throws ClaveOnlineClaveTrivialException, ClaveOnlineUsuTrivialException, ClaveOnlineClaveRepeException, ClaveOnlineUsuRepeException, DAOException, ClienteBloqueadoException, ErrorRutinaFechasException, ErrorDb2Exception, ErrorCicsException, FuncionInvalidaException, ErrorModuloException {
    	AltaSGIClaveInEntity in = new AltaSGIClaveInEntity();
    	Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("MensajeError");

    	Mockito.when(moduloPermisoBO.tienePermisoMostrar(Matchers.any(AccionController.class))).thenReturn(true);
    	Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
    	Mockito.doThrow(new ErrorCicsException("")).when(altaSGIClaveDAO).altaSGIClaveProxyLogin(Matchers.any(AltaSGIClaveInEntity.class));

		Respuesta<Void> response = claveOnlineBO.altaSGIClave(in);
		assertNotNull(response);
		assertEquals(EstadoRespuesta.ERROR, response.getEstadoRespuesta());
		assertEquals(TipoError.ERROR_CICS.getDescripcion(), response.getItemsMensajeRespuesta().get(0).getTipoError());
		assertEquals("MensajeError", response.getItemsMensajeRespuesta().get(0).getMensaje());
    }
}
