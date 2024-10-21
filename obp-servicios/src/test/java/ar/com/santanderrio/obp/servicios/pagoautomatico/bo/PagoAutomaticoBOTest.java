package ar.com.santanderrio.obp.servicios.pagoautomatico.bo;

import java.util.Date;

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
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.ResultadoTransaccion;
import ar.com.santanderrio.obp.servicios.comun.buscador.bo.BuscadorEmpresaBO;
import ar.com.santanderrio.obp.servicios.comun.estadistica.bo.EstadisticaBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagoautomatico.bo.impl.PagoAutomaticoBOImpl;
import ar.com.santanderrio.obp.servicios.pagoautomatico.dao.PagoAutomaticoDAO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class NuevoPagoAutomaticoBOTest.
 * @author julian.ariel.karp
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoAutomaticoBOTest {

    /** The nuevo pago automatico BO. */
    @InjectMocks
    private PagoAutomaticoBOImpl pagoAutomaticoBO;

    /** The pago automatico DAO. */
    @Mock
    private PagoAutomaticoDAO pagoAutomaticoDAO;

    /** The estadistica BO. */
    @Mock
    private EstadisticaBO estadisticaBO;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private SesionParametros sessionParametros;

    @Mock
    private BuscadorEmpresaBO buscadorEmpresaBO;

    /**
     * Generar comprobante adhesion.
     */
    @Test
    public void generarComprobanteAdhesion() {
        // When
        Respuesta<Reporte> respuesta = null;
        Reporte reporte = new Reporte();
        ComprobanteDebitoAutomatico comprobante = new ComprobanteDebitoAutomatico();
        Mockito.when(pagoAutomaticoDAO.generarComprobanteAdhesion(comprobante)).thenReturn(reporte);
        // Then
        respuesta = pagoAutomaticoBO.generarComprobanteAdhesion(comprobante);
        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());

    }

    /**
     * Test de Eliminar pago puntual.
     * 
     * @throws DAOException
     */
    @Test
    public void eliminarPagoPuntual() throws DAOException {
        // When
        Cliente cliente = new Cliente();
        PagoPendiente pagoPendiente = new PagoPendiente();
        pagoPendiente.setNombreEmpresa("ENOR");
        pagoPendiente.setCodigoClienteEmpresa("008068147996       ");

        Respuesta<ResultadoTransaccion> respuesta = new Respuesta<ResultadoTransaccion>();
        respuesta.setId(1L);
        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuesta.setRespuestaVacia(false);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                pagoAutomaticoDAO.eliminarPagoPuntual(Matchers.any(PagoPendiente.class), Matchers.any(Cliente.class)))
                .thenReturn(respuesta);

        // Then
        Respuesta<ResultadoTransaccion> resultadoPagoAutomatico = pagoAutomaticoBO.eliminarPagoPuntual(pagoPendiente,
                cliente);
        // Expected
        Assert.assertNotNull(resultadoPagoAutomatico);
        Assert.assertNotNull(resultadoPagoAutomatico.getEstadoRespuesta());
        Assert.assertTrue(!resultadoPagoAutomatico.isRespuestaVacia());
    }

    @Test
    public void eliminarPagoPuntualError() throws DAOException {
        // When
        Cliente cliente = new Cliente();
        PagoPendiente pagoPendiente = new PagoPendiente();
        pagoPendiente.setNombreEmpresa("ENOR");
        pagoPendiente.setCodigoClienteEmpresa("008068147996       ");

        Respuesta<ResultadoTransaccion> respuesta = new Respuesta<ResultadoTransaccion>();
        respuesta.setId(1L);
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuesta.setRespuestaVacia(false);

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                pagoAutomaticoDAO.eliminarPagoPuntual(Matchers.any(PagoPendiente.class), Matchers.any(Cliente.class)))
                .thenReturn(respuesta);

        // Then
        Respuesta<ResultadoTransaccion> resultadoPagoAutomatico = pagoAutomaticoBO.eliminarPagoPuntual(pagoPendiente,
                cliente);
        // Expected
        Assert.assertNotNull(resultadoPagoAutomatico);
        Assert.assertNotNull(resultadoPagoAutomatico.getEstadoRespuesta());
        Assert.assertTrue(!resultadoPagoAutomatico.isRespuestaVacia());
    }

    @Test
    public void eliminarPagoPuntualDAOException() throws DAOException {
        // When
        Cliente cliente = new Cliente();
        PagoPendiente pagoPendiente = new PagoPendiente();

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                pagoAutomaticoDAO.eliminarPagoPuntual(Matchers.any(PagoPendiente.class), Matchers.any(Cliente.class)))
                .thenThrow(new DAOException());

        // Then
        Respuesta<ResultadoTransaccion> resultadoPagoAutomatico = pagoAutomaticoBO.eliminarPagoPuntual(pagoPendiente,
                cliente);
        // Expected
        Assert.assertNotNull(resultadoPagoAutomatico);
        Assert.assertNotNull(resultadoPagoAutomatico.getEstadoRespuesta());
        Assert.assertTrue(!resultadoPagoAutomatico.isRespuestaVacia());
    }

    @Test
    public void eliminarPagoPuntualException() throws DAOException {
        // When
        Cliente cliente = new Cliente();
        PagoPendiente pagoPendiente = new PagoPendiente();

        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Mockito.when(
                pagoAutomaticoDAO.eliminarPagoPuntual(Matchers.any(PagoPendiente.class), Matchers.any(Cliente.class)))
                .thenThrow(new RuntimeException());

        // Then
        Respuesta<ResultadoTransaccion> resultadoPagoAutomatico = pagoAutomaticoBO.eliminarPagoPuntual(pagoPendiente,
                cliente);
        // Expected
        Assert.assertNotNull(resultadoPagoAutomatico);
        Assert.assertNotNull(resultadoPagoAutomatico.getEstadoRespuesta());
        Assert.assertTrue(!resultadoPagoAutomatico.isRespuestaVacia());
    }

    @Test
    public void ejecutarAdhesionPagoAutomatico() throws DAOException {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        ResultadoTransaccion resultadoTransaccion = new ResultadoTransaccion();
        Respuesta<MedioPagoView> respuestaMedioPagoView = new Respuesta<MedioPagoView>();
        MedioPagoView medioPagoView = new MedioPagoView();
        adhesionPagoAutomatico.setIsFromCalendario(false);
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        adhesionPagoAutomatico.setEmisionGastosProtegido(null);
        adhesionPagoAutomatico.setNroPolizaGastosProtegido("1234");
        resultadoTransaccion.setFechaTransaccion(new Date());
        medioPagoView.setNombreFantasia("SPARKLING");
        respuestaMedioPagoView.setRespuesta(medioPagoView);

        Mockito.when(pagoAutomaticoDAO.adhierePagoMisCuentas(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenReturn("00407468");
        Mockito.when(pagoAutomaticoDAO.adhierePagoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenReturn(resultadoTransaccion);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString()))
                .thenReturn(new Mensaje());
        Mockito.when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMedioPagoView);

        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(cliente,
                adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAdhesionPagoAutomaticoDAOExceptionPagoMisCuentas() throws DAOException {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<MedioPagoView> respuestaMedioPagoView = new Respuesta<MedioPagoView>();
        Mensaje mensaje = new Mensaje();
        MedioPagoView medioPagoView = new MedioPagoView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(3);

        adhesionPagoAutomatico.setIsFromCalendario(false);
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        mensaje.setMensaje(
                "<b>¡Lo sentimos!</b><p>No se pudo realizar la <b>adhesión</b> de <b>{0}</b> a <b>pago automático</b>.</p>");
        medioPagoView.setNombreFantasia("SPARKLING");
        respuestaMedioPagoView.setRespuesta(medioPagoView);

        Mockito.when(pagoAutomaticoDAO.adhierePagoMisCuentas(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMedioPagoView);
        Mockito.when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(cliente,
                adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAdhesionPagoAutomaticoDAOExceptionPagoMisCuentas2() throws DAOException {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<MedioPagoView> respuestaMedioPagoView = new Respuesta<MedioPagoView>();
        Mensaje mensaje = new Mensaje();
        MedioPagoView medioPagoView = new MedioPagoView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(3);

        adhesionPagoAutomatico.setIsFromCalendario(false);
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        mensaje.setMensaje(
                "<b>¡Lo sentimos!</b><p>No se pudo realizar la <b>adhesión</b> de <b>{0}</b> a <b>pago automático</b>.</p>");
        medioPagoView.setNombreFantasia("SPARKLING");
        respuestaMedioPagoView.setRespuesta(medioPagoView);

        Mockito.when(pagoAutomaticoDAO.adhierePagoMisCuentas(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenThrow(new DAOException("10000044", "10000044"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMedioPagoView);
        Mockito.when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(cliente,
                adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAdhesionPagoAutomaticoDAOExceptionPagoMisCuentasTimeout() throws DAOException {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<MedioPagoView> respuestaMedioPagoView = new Respuesta<MedioPagoView>();
        Mensaje mensaje = new Mensaje();
        MedioPagoView medioPagoView = new MedioPagoView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(3);

        adhesionPagoAutomatico.setIsFromCalendario(false);
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        mensaje.setMensaje(
                "<b>¡Lo sentimos!</b><p>No se pudo realizar la <b>adhesión</b> de <b>{0}</b> a <b>pago automático</b>.</p>");
        medioPagoView.setNombreFantasia("SPARKLING");
        respuestaMedioPagoView.setRespuesta(medioPagoView);

        Mockito.when(pagoAutomaticoDAO.adhierePagoMisCuentas(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class)))
                .thenThrow(new DAOException(
                        "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMedioPagoView);
        Mockito.when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(cliente,
                adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAdhesionPagoAutomaticoDAOException() throws DAOException {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<MedioPagoView> respuestaMedioPagoView = new Respuesta<MedioPagoView>();
        Mensaje mensaje = new Mensaje();
        MedioPagoView medioPagoView = new MedioPagoView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(3);

        adhesionPagoAutomatico.setIsFromCalendario(false);
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        mensaje.setMensaje(
                "<b>¡Lo sentimos!</b><p>No se pudo realizar la <b>adhesión</b> de <b>{0}</b> a <b>pago automático</b>.</p>");
        medioPagoView.setNombreFantasia("SPARKLING");
        respuestaMedioPagoView.setRespuesta(medioPagoView);

        Mockito.when(pagoAutomaticoDAO.adhierePagoMisCuentas(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenReturn("00407468");
        Mockito.when(pagoAutomaticoDAO.adhierePagoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMedioPagoView);
        Mockito.when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(cliente,
                adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAdhesionPagoAutomaticoDAOException2BajaPagoMisCuentasDAOException() throws DAOException {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<MedioPagoView> respuestaMedioPagoView = new Respuesta<MedioPagoView>();
        Mensaje mensaje = new Mensaje();
        MedioPagoView medioPagoView = new MedioPagoView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(3);

        adhesionPagoAutomatico.setIsFromCalendario(false);
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        mensaje.setMensaje(
                "<b>¡Lo sentimos!</b><p>No se pudo realizar la <b>adhesión</b> de <b>{0}</b> a <b>pago automático</b>.</p>");
        medioPagoView.setNombreFantasia("SPARKLING");
        respuestaMedioPagoView.setRespuesta(medioPagoView);

        Mockito.when(pagoAutomaticoDAO.adhierePagoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenThrow(new DAOException("10000044", "10000044"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMedioPagoView);
        Mockito.when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        Mockito.when(pagoAutomaticoDAO.bajaPagoMisCuentas(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenThrow(new DAOException());
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(cliente,
                adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarAdhesionPagoAutomaticoDAOExceptionTimeout() throws DAOException {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<MedioPagoView> respuestaMedioPagoView = new Respuesta<MedioPagoView>();
        Mensaje mensaje = new Mensaje();
        MedioPagoView medioPagoView = new MedioPagoView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        contadorIntentos.permiteReintentar();

        adhesionPagoAutomatico.setIsFromCalendario(true);
        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setFiid("SPAR");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        mensaje.setMensaje(
                "<b>¡Lo sentimos!</b><p>No se pudo realizar la <b>adhesión</b> de <b>{0}</b> a <b>pago automático</b>.</p>");
        medioPagoView.setNombreFantasia("SPARKLING");
        respuestaMedioPagoView.setRespuesta(medioPagoView);

        Mockito.when(pagoAutomaticoDAO.adhierePagoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class)))
                .thenThrow(new DAOException(
                        "iatx.exceptions.IatxConnectException: Se envió la transacción al CICS pero no se recibió respuesta.õ"));
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString(), Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(buscadorEmpresaBO.getByCodigo(Matchers.anyString())).thenReturn(respuestaMedioPagoView);
        Mockito.when(sessionParametros.getContador()).thenReturn(contadorIntentos);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(cliente,
                adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarBajaAdhesion() throws DAOException {
        // When
        Mockito.when(
                pagoAutomaticoDAO.ejecutarBajaAdhesion(Matchers.any(PagoPendiente.class), Matchers.any(Cliente.class)))
                .thenReturn(new ResultadoTransaccion());
        // Then
        Respuesta<ResultadoTransaccion> respuesta = pagoAutomaticoBO.ejecutarBajaAdhesion(new PagoPendiente(),
                new Cliente());
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void ejecutarBajaAdhesionDAOException() throws DAOException {
        // When
        Mockito.when(
                pagoAutomaticoDAO.ejecutarBajaAdhesion(Matchers.any(PagoPendiente.class), Matchers.any(Cliente.class)))
                .thenThrow(new DAOException());
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        // Then
        Respuesta<ResultadoTransaccion> respuesta = pagoAutomaticoBO.ejecutarBajaAdhesion(new PagoPendiente(),
                new Cliente());
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }
}