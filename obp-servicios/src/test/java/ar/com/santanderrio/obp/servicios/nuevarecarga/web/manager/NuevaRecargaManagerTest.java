/**
 * 
 */
package ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.base.mensaje.entities.MensajeMock;
import ar.com.santanderrio.obp.servicios.clientes.bo.ClienteBO;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionFactory;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.Desafio;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.mock.AutentificacionDTOMock;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.entities.Estadistica;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.nuevarecarga.bo.AgendaCelularBO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaInDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.dto.NuevaRecargaOutDTO;
import ar.com.santanderrio.obp.servicios.nuevarecarga.mock.ConfirmacionRecargaViewMock;
import ar.com.santanderrio.obp.servicios.nuevarecarga.mock.NuevaRecargaOutDTOMock;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.manager.impl.NuevaRecargaManagerImpl;
import ar.com.santanderrio.obp.servicios.nuevarecarga.web.view.ConfirmacionRecargaView;
import ar.com.santanderrio.obp.servicios.nuevopago.bo.NuevoPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PrismaTimeOutException;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.mock.MedioPagoMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class NuevaRecargaManagerTest.
 *
 * @author florencia.n.martinez
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class NuevaRecargaManagerTest {

    /** The Constant MSJ_SOFT_TOKEN_INPUT. */
    private static final String MSJ_SOFT_TOKEN_INPUT = " <p><b>¡Ya casi terminás!</b></p><p>Confirmá tu operación ingresando los 6 dígitos de seguridad.</p>";

    /** The Constant MSJ_ERROR_CARGA_NUESTROS_SERVICIOS. */
    private static final String MSJ_ERROR_CARGA_NUESTROS_SERVICIOS = "<p>Ocurrió un error en nuestros servicios.Por favor, volvé a ingresar en unos minutos.</p>";

    /** The Constant MSJ_SOFT_TOKEN_BLOQUEO_ERROR. */
    private static final String MSJ_SOFT_TOKEN_BLOQUEO_ERROR = "La operación solicitada no pudo ser validada. Por favor, dirigite a un Cajero Automático de la Red Banelco para activar tu Token de seguridad.";

    /** The nueva recarga manager. */
    @InjectMocks
    private NuevaRecargaManagerImpl nuevaRecargaManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The autentificacion factory. */
    @Mock
    private AutentificacionFactory autentificacionFactory;

    /** The autentificacion metodo. */
    @Mock
    private Desafio<AutentificacionDTO> autentificacionMetodo;

    /** The autentificacion manager. */
    @Mock
    private AutentificacionManager autentificacionManager;

    /** The medio pago BO. */
    @Mock
    private MediosPagoBO medioPagoBO;

    /** The nuevo pago BO. */
    @Mock
    private NuevoPagoBO nuevoPagoBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica BO. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The contador intentos. */
    @Mock
    private ContadorIntentos contadorIntentos;
    
    @Mock
    private AgendaCelularBO agendaCelularBO;
    
    @Mock
    private ClienteBO clienteBO;

    @Before
    public void initMocks() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(nuevaRecargaManager, "valorDesafio", "1", true);
    }
    
    /**
     * Dado un objeto ConfirmacionRecargaView, cuando se invoca al metodo
     * "ejecutarValidacionRSA", obtengo una respuesta warning de
     * ConfirmacionRecargaView con nuevo desafio.
     *
     */
    @Test
    public void dadoConfirmacionRecargaInViewCuandoInvocaEjecutarValidacionRSAObtengoRespuestaWarningConfirmacionRecargaInViewConNuevoDesafio() {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        Respuesta<AutentificacionDTO> resp = obtenerRespuestaAutentificacionDTONuevoDesafioWaring();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(resp);
        when(sesionParametros.getValidacionHash()).thenReturn("E4542949281E61029B7A179041C67E38");

        // Then
        Respuesta<ConfirmacionRecargaView> respConfirmacionRecargaInView = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(
                        ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud());

        // Expected
        Assert.assertNotNull(respConfirmacionRecargaInView);
        Assert.assertEquals(EstadoRespuesta.WARNING, respConfirmacionRecargaInView.getEstadoRespuesta());
    }
    
    /**
     * Dado un objeto ConfirmacionRecargaView, cuando se invoca al metodo
     * "obtenerConfirmacionNuevaRecarga", obtengo respuesta OK de
     * ConfirmacionRecargaView con nuevo desafio. Sigue al flujo de Feedback +
     * Comprobante.
     *
     * @throws BusinessException
     *             the business exception
     * @throws PrismaTimeOutException 
     */
    @Test
    public void dadoConfirmacionRecargaInViewCuandoInvocaObtenerConfirmacionNuevaRecargaObtengoRespuestaOKConfirmacionRecargaInViewConNuevoDesafio()
            throws BusinessException, PrismaTimeOutException {
        
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        Respuesta<AutentificacionDTO> respAutentificacionDTO = obtenerRespuestaAutentificacionDTONuevoDesafioOK();
        MedioPago medioPago = MedioPagoMock.completarInfo("RECARGA CLARO", 0, null);
        NuevaRecargaOutDTO dto = NuevaRecargaOutDTOMock.completarInfo(Boolean.TRUE, null);
        Mensaje mensaje = MensajeMock.completarInfoMensaje("1267",
                "<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> se realizó exitosamente.</p>");
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        
        Respuesta<List<BigDecimal>> antiguedades = new Respuesta<List<BigDecimal>>();
        List<BigDecimal> listaValores = new ArrayList<BigDecimal>();
        
        BigDecimal valorUno = new BigDecimal("1");
        BigDecimal valorDos = new BigDecimal("2");
        
        listaValores.add(valorUno);
        listaValores.add(valorDos);
        antiguedades.setRespuesta(listaValores);
               
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respAutentificacionDTO);
        when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagarRecarga(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.any(NuevaRecargaInDTO.class)))
                .thenReturn(dto);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        when(estadisticaManager.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
                Matchers.any(Cliente.class))).thenReturn(true);
        when(contadorIntentos.permiteReintentar()).thenReturn(Boolean.TRUE);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("E4542949281E61029B7A179041C67E38");
        
        Respuesta<Void> respuestaAgregar = new Respuesta<Void>();
        respuestaAgregar.setEstadoRespuesta(EstadoRespuesta.OK);
        when(agendaCelularBO.agregarCelular(Mockito.any(Cliente.class), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(respuestaAgregar);
        when(agendaCelularBO.existeCelular(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(respuestaAgregar);
        when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Matchers.anyLong())).thenReturn(antiguedades);
        
        // Then
        Respuesta<ConfirmacionRecargaView> respConfirmacionRecargaInView = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(
                        ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud());

        // Expected
        Assert.assertNotNull(respConfirmacionRecargaInView);
        Assert.assertEquals(EstadoRespuesta.OK, respConfirmacionRecargaInView.getEstadoRespuesta());
    }

    /**
     * Dado un objeto ConfirmacionRecargaView, cuando se invoca al metodo
     * "obtenerConfirmacionNuevaRecarga", obtengo una respuesta error con nuevo
     * desafio.
     * @throws BusinessException 
     *
     */
    @Test
    public void dadoConfirmacionRecargaInViewCuandoInvocaObtenerConfirmacionNuevaRecargaObtengoRespuestaErrorConfirmacionRecargaInViewConNuevoDesafio() throws BusinessException {
        // When
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");

        Respuesta<List<BigDecimal>> antiguedades = new Respuesta<List<BigDecimal>>();
        List<BigDecimal> listaValores = new ArrayList<BigDecimal>();
        
        BigDecimal valorUno = new BigDecimal("1");
        BigDecimal valorDos = new BigDecimal("2");
        
        listaValores.add(valorUno);
        listaValores.add(valorDos);
        antiguedades.setRespuesta(listaValores);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(obtenerRespuestaAutentificacionDTONuevoDesafioError());
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                "1550", "Ocurrió un error en la operación y no se puede realizar en estos momentos."));
        when(sesionParametros.getValidacionHash()).thenReturn("E4542949281E61029B7A179041C67E38");
        when(clienteBO.obtenerAntiguedadDiasUltCambioClaveToken(Matchers.anyLong())).thenReturn(antiguedades);
        
        // Then
        Respuesta<ConfirmacionRecargaView> respConfirmacionRecargaInView = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(
                        ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud());

        // Expected
        Assert.assertNotNull(respConfirmacionRecargaInView);
        Assert.assertEquals(EstadoRespuesta.ERROR, respConfirmacionRecargaInView.getEstadoRespuesta());
    }

    /**
     * Dado un objeto ConfirmacionRecargaView, cuando se invoca al metodo
     * "obtenerConfirmacionNuevaRecarga", obtengo una respuesta OK de
     * ConfirmacionRecargaView con desafio en curso.
     *
     * @throws BusinessException
     *             the business exception
     * @throws PrismaTimeOutException 
     */
    @Test
    public void dadoConfirmacionRecargaInViewCuandoInvocaObtenerConfirmacionNuevaRecargaObtengoRespuestaOKConDesafioEnCurso()
            throws BusinessException, PrismaTimeOutException {
        
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        
        MedioPago medioPago = MedioPagoMock.completarInfo("RECARGA CLARO", 0, null);
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(obtenerRespuestaAutentificacionDTODesafioEnCursoOK());
        when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagarRecarga(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.any(NuevaRecargaInDTO.class)))
                .thenReturn(NuevaRecargaOutDTOMock.completarInfo(Boolean.TRUE, null));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                "1267", "<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> se realizó exitosamente.</p>"));
        when(estadisticaManager.add(Matchers.any(Estadistica.class), Matchers.any(RegistroSesion.class),
                Matchers.any(Cliente.class))).thenReturn(true);
        when(contadorIntentos.permiteReintentar()).thenReturn(Boolean.TRUE);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("18AAE981F8AD6B11EA5BA2A5868E045B");
        
        Respuesta<Void> respuestaAgregar = new Respuesta<Void>();
        respuestaAgregar.setEstadoRespuesta(EstadoRespuesta.OK);
        when(agendaCelularBO.existeCelular(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(respuestaAgregar);
        when(agendaCelularBO.agregarCelular(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString(), 
                Matchers.anyString())).thenReturn(respuestaAgregar);
        // Then
        Respuesta<ConfirmacionRecargaView> respConfirmacionRecargaInView = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(ConfirmacionRecargaViewMock
                        .completarInfoConfirmacionRecargaViewWarningTarjetaCoordenadasDesafio());

        // Expected
        Assert.assertNotNull(respConfirmacionRecargaInView);
        Assert.assertEquals(EstadoRespuesta.OK, respConfirmacionRecargaInView.getEstadoRespuesta());
    }

    /**
     * Dado un objeto ConfirmacionRecargaView, cuando se invoca al metodo
     * "ejecutarValidacionRSA", obtengo una respuesta warning de
     * ConfirmacionRecargaView con desafio en curso.
     *
     */
    @Test
    public void dadoPagoMisCuentasViewCuandoInvocaEjecutarValidacionRSAObtengoRespuestaWarningPagoMisCuentasViewConDesafioEnCurso() {
        // When
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(obtenerRespuestaAutentificacionDTODesafioEnCursoWarning());
        when(sesionParametros.getValidacionHash()).thenReturn("3DA072F41D9444E2390AD62B0207C2DF");
        
        // Then
        Respuesta<ConfirmacionRecargaView> respPagoMisCuentasView = nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(
                ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputValidacion());
        
        // Expected
        Assert.assertNotNull(respPagoMisCuentasView);
    }

    /**
     * Dado un objeto ConfirmacionRecargaView, cuando se invoca al metodo
     * "ejecutarValidacionRSA", obtengo una respuesta error de
     * ConfirmacionRecargaView con desafio en curso.
     *
     */
    @Test
    public void dadoPagoMisCuentasViewCuandoinvocaEjecutarValidacionRSAObtengoRespuestaErrorPagoMisCuentasViewConDesafioEnCurso() {

        // When
        Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = obtenerRespuestaAutentificacionDTONuevoDesafioError();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacionDTO);
        when(sesionParametros.getValidacionHash()).thenReturn("3DA072F41D9444E2390AD62B0207C2DF");

        // Then
        Respuesta<ConfirmacionRecargaView> respPagoMisCuentasView = nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(
                ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputValidacionSinCoordenadas());

        // Expected
        Assert.assertNotNull(respPagoMisCuentasView);
    }

    /**
     * Nueva recarga out DTO respuesta error con reintentos.
     *
     * @throws BusinessException
     *             the business exception
     * @throws PrismaTimeOutException 
     */
    @Test
    public void nuevaRecargaOutDTORespuestaErrorConReintentos() throws BusinessException, PrismaTimeOutException {

        // When
        sesionParametros.setValidacionHash("7BBCDE1393CA2DC65D42803F88B81243");
        Cliente cliente = ClienteMock.completarInfoCliente();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        ConfirmacionRecargaView datosConfirmacionRecarga = ConfirmacionRecargaViewMock
                .completarInfoConfirmacionRecargaViewInputValidacion();
        Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = respuestaFactory
                .crearRespuestaOk(AutentificacionDTO.class, AutentificacionDTOMock.completarDesafioToken());
        MedioPago medioPago = MedioPagoMock.completarInfo("RECARGA CLARO", 0, null);
        NuevaRecargaOutDTO nuevaRecargaOutDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.FALSE,
                TipoError.ERROR_SALDO_INSUFICIENTE);
        nuevaRecargaOutDTO.setMensaje("Mensaje");
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacionDTO);
        when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagarRecarga(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.any(NuevaRecargaInDTO.class)))
                .thenReturn(nuevaRecargaOutDTO);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(2));
        when(sesionParametros.getValidacionHash()).thenReturn("3DA072F41D9444E2390AD62B0207C2DF");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SALDO_INSUFICIENTE_RECARGA_CELULAR)).
            thenReturn(MensajeMock.completarInfoMensaje("2105", "<p>Saldo Insuficiente.</p>"));
        // Then
        Respuesta<ConfirmacionRecargaView> respuesta = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(datosConfirmacionRecarga);

        // Expected
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Nueva recarga out DTO respuesta error sin reintentos.
     *
     * @throws BusinessException
     *             the business exception
     * @throws PrismaTimeOutException 
     */
    @Test
    public void nuevaRecargaOutDTORespuestaErrorSinReintentos() throws BusinessException, PrismaTimeOutException {
        
        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        ConfirmacionRecargaView datosConfirmacionRecarga = ConfirmacionRecargaViewMock
                .completarInfoConfirmacionRecargaViewInputValidacion();
        Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = respuestaFactory
                .crearRespuestaOk(AutentificacionDTO.class, AutentificacionDTOMock.completarDesafioToken());
        MedioPago medioPago = MedioPagoMock.completarInfo("RECARGA CLARO", 0, null);
        NuevaRecargaOutDTO nuevaRecargaOutDTO = NuevaRecargaOutDTOMock.completarInfo(Boolean.FALSE,
                TipoError.ERROR_SALDO_INSUFICIENTE);
        nuevaRecargaOutDTO.setMensaje("Mensaje");
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        contadorIntentos.permiteReintentar();
        
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacionDTO);
        when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagarRecarga(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.any(NuevaRecargaInDTO.class)))
                .thenReturn(nuevaRecargaOutDTO);
        when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        when(sesionParametros.getValidacionHash()).thenReturn("3DA072F41D9444E2390AD62B0207C2DF");
        when(mensajeBO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_SALDO_INSUFICIENTE_RECARGA_CELULAR)).
        thenReturn(MensajeMock.completarInfoMensaje("2105", "<p>Saldo Insuficiente.</p>"));
        // Then
        Respuesta<ConfirmacionRecargaView> respuesta = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(datosConfirmacionRecarga);

        // Expected
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    /**
     * Nueva recarga out DTO business exception con reintentos.
     *
     * @throws BusinessException
     *             the business exception
     * @throws PrismaTimeOutException 
     */
    @Test
    public void nuevaRecargaOutDTOBusinessExceptionConReintentos() throws BusinessException, PrismaTimeOutException {
        
        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        ConfirmacionRecargaView datosConfirmacionRecarga = ConfirmacionRecargaViewMock
                .completarInfoConfirmacionRecargaViewInputValidacion();
        Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = respuestaFactory
                .crearRespuestaOk(AutentificacionDTO.class, AutentificacionDTOMock.completarDesafioToken());
        MedioPago medioPago = MedioPagoMock.completarInfo("RECARGA CLARO", 0, null);
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacionDTO);
        when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagarRecarga(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.any(NuevaRecargaInDTO.class)))
                .thenThrow(new BusinessException("Business Exception."));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                "1268",
                        "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>"));
        when(contadorIntentos.permiteReintentar()).thenReturn(Boolean.FALSE);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(3));
        when(sesionParametros.getValidacionHash()).thenReturn("3DA072F41D9444E2390AD62B0207C2DF");

        // Then
        Respuesta<ConfirmacionRecargaView> respuesta = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(datosConfirmacionRecarga);

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    /**
     * Nueva recarga out DTO business exception con reintentar false.
     *
     * @throws BusinessException
     *             the business exception
     * @throws PrismaTimeOutException 
     */
    @Test
    public void nuevaRecargaOutDTOBusinessExceptionConReintentarFalse() throws BusinessException, PrismaTimeOutException {
        
        // When
        Cliente cliente = ClienteMock.completarInfoCliente();
        ConfirmacionRecargaView datosConfirmacionRecarga = ConfirmacionRecargaViewMock
                .completarInfoConfirmacionRecargaViewInputValidacion();
        datosConfirmacionRecarga.setReintentar("false");
        Respuesta<AutentificacionDTO> respuestaAutentificacionDTO = respuestaFactory
                .crearRespuestaOk(AutentificacionDTO.class, AutentificacionDTOMock.completarDesafioToken());
        MedioPago medioPago = MedioPagoMock.completarInfo("RECARGA CLARO", 0, null);
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);

        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaAutentificacionDTO);
        when(medioPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(nuevoPagoBO.pagarRecarga(Matchers.any(Cliente.class), Matchers.any(Cuenta.class), Matchers.any(NuevaRecargaInDTO.class)))
                .thenThrow(new BusinessException("Business Exception."));
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(MensajeMock.completarInfoMensaje(
                "1268",
                        "<p>¡Lo sentimos!</p><p>No hemos podido realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>"));
        when(contadorIntentos.permiteReintentar()).thenReturn(Boolean.FALSE);
        when(sesionParametros.getContador()).thenReturn(new ContadorIntentos(3));
        when(sesionParametros.getValidacionHash()).thenReturn("3DA072F41D9444E2390AD62B0207C2DF");
        
        // Then
        Respuesta<ConfirmacionRecargaView> respuesta = nuevaRecargaManager
                .obtenerConfirmacionNuevaRecarga(datosConfirmacionRecarga);

        // Expected
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmarNuevaRecargaErrorSinDesafios() {
        // When
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        cliente.getCuentas().get(0).setCbu("0720025088000006005072");
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        when(sesionParametros.getValidacionHash()).thenReturn("3DA072F41D9444E2390AD62B0207C2DF");

        // Then
        Respuesta<ConfirmacionRecargaView> respuesta = nuevaRecargaManager.obtenerConfirmacionNuevaRecarga(
                ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputValidacion());

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }
    
    /**
     * Estadistica comprobante OK.
     */
    @Test
    public void estadisticaComprobanteOK() {
        
        // When
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

        // Then
        Boolean esOK = nuevaRecargaManager.estadisticaVerComprobante();

        // Expected
        Assert.assertTrue(esOK);
    }

    /**
     * Estadistica comprobante Error.
     */
    @Test
    public void estadisticaComprobanteError() {
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.FALSE);

        Boolean esOK = nuevaRecargaManager.estadisticaVerComprobante();

        Assert.assertFalse(esOK);
    }
    
    /**
     * Obtener respuesta autentificacion DTO desafio en curso warning.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> obtenerRespuestaAutentificacionDTODesafioEnCursoWarning() {
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setRespuesta(new AutentificacionDTO());
        respAutentificacionDTO.setItemMensajeRespuesta(obtenerItemsMensajeTipoErrorBloqueoToken());
        respAutentificacionDTO.setRespuestaVacia(false);
        return respAutentificacionDTO;
    }
    
    /**
     * Obtener items mensaje tipo error bloqueo token.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorBloqueoToken() {
        ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(new ItemMensajeRespuesta());
        items.get(0).setTipoError(TipoError.ERROR_BLOQUEO_TOKEN.getDescripcion());
        items.get(0).setMensaje(MSJ_SOFT_TOKEN_BLOQUEO_ERROR);
        items.trimToSize();
        return items;
    }

    /**
     * Obtener respuesta autentificacion DTO desafio en curso OK.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> obtenerRespuestaAutentificacionDTODesafioEnCursoOK() {
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setRespuesta(
                ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud().getDesafio());
        respAutentificacionDTO.setRespuestaVacia(false);
        return respAutentificacionDTO;
    }

    /**
     * Obtener respuesta autentificacion DTO nuevo desafio error.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> obtenerRespuestaAutentificacionDTONuevoDesafioError() {
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respAutentificacionDTO.setRespuesta(
                ConfirmacionRecargaViewMock.completarInfoConfirmacionRecargaViewInputSolicitud().getDesafio());
        respAutentificacionDTO.setItemMensajeRespuesta(obtenerItemsMensajeTipoErrorGenerico());
        respAutentificacionDTO.setRespuestaVacia(false);
        return respAutentificacionDTO;
    }

    /**
     * Obtener items mensaje tipo error generico.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorGenerico() {
        ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(new ItemMensajeRespuesta());
        items.get(0).setTipoError(TipoError.ERROR_GENERICO.getDescripcion());
        items.get(0).setMensaje(MSJ_ERROR_CARGA_NUESTROS_SERVICIOS);
        items.trimToSize();
        return items;
    }

    /**
     * Obtener respuesta autentificacion DTO nuevo desafio OK.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> obtenerRespuestaAutentificacionDTONuevoDesafioOK() {
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.OK);
        respAutentificacionDTO.setRespuesta(new AutentificacionDTO());
        respAutentificacionDTO.setRespuestaVacia(false);
        return respAutentificacionDTO;
    }
    
    /**
     * Obtener respuesta autentificacion DTO nuevo desafio waring.
     *
     * @return the respuesta
     */
    private Respuesta<AutentificacionDTO> obtenerRespuestaAutentificacionDTONuevoDesafioWaring() {
        Respuesta<AutentificacionDTO> respAutentificacionDTO = new Respuesta<AutentificacionDTO>();
        respAutentificacionDTO.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respAutentificacionDTO.setRespuesta(AutentificacionDTOMock.completarDesafioToken());
        respAutentificacionDTO.setItemMensajeRespuesta(obtenerItemsMensajeTipoErrorDesafioToken());
        respAutentificacionDTO.setRespuestaVacia(false);
        return respAutentificacionDTO;
    }
    
    /**
     * Obtener items mensaje tipo error desafio token.
     *
     * @return the list
     */
    private List<ItemMensajeRespuesta> obtenerItemsMensajeTipoErrorDesafioToken() {
        ArrayList<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(new ItemMensajeRespuesta());
        items.get(0).setTipoError(TipoError.DESAFIO.getDescripcion());
        items.get(0).setMensaje(MSJ_SOFT_TOKEN_INPUT);
        items.trimToSize();
        return items;
    }
    
}
