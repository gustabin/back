package ar.com.santanderrio.obp.servicios.pagoautomatico.manager;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validator;

import org.apache.commons.lang3.reflect.FieldUtils;
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

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.debitoautomatico.bo.DebitoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.debitoautomatico.entities.ComprobanteDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagoautomatico.bo.PagoAutomaticoBO;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionDebitoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.AdhesionPagoAutomatico;
import ar.com.santanderrio.obp.servicios.pagoautomatico.entities.TerminosCondiciones;
import ar.com.santanderrio.obp.servicios.pagoautomatico.manager.impl.PagoAutomaticoManagerImpl;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnCuentaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.AdhesionDebitoAutomaticoEnTarjetaView;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.CuentaMock;
import ar.com.santanderrio.obp.servicios.terminoscondiciones.bo.TerminosCondicionesBO;

/**
 * The Class NuevoPagoAutomaticoManagerTest.
 * 
 * @author julian.ariel.karp
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoAutomaticoManagerTest {

    /** The manager impl. */
    @InjectMocks
    private PagoAutomaticoManagerImpl pagoAutomaticoManager;

    /** The terminos condiciones BO. */
    @Mock
    private TerminosCondicionesBO terminosCondicionesBO;

    /** The nuevo pago automatico BO. */
    @Mock
    private PagoAutomaticoBO pagoAutomaticoBO;

    /** The debito automatico BO. */
    @Mock
    private DebitoAutomaticoBO debitoAutomaticoBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    @Mock
    private MediosPagoBO mediosPagoBO;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private Validator validator;

    @Mock
    AutentificacionManager autentificacionManager;

    @Before
    public void initMocks() throws IllegalAccessException {
        FieldUtils.writeDeclaredField(pagoAutomaticoManager, "valorDesafio", "2", true);
    }

    @Test
    public void cargarTerminosCondiciones() throws BusinessException {
        Respuesta<TerminosCondiciones> resp = new Respuesta<TerminosCondiciones>();
        resp.setEstadoRespuesta(EstadoRespuesta.OK);

        when(terminosCondicionesBO.cargarTerminosCondiciones()).thenReturn(resp);

        Respuesta<TerminosCondiciones> resul = pagoAutomaticoManager.cargarTerminosCondiciones();
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void cargarTerminosCondicionesError() throws BusinessException {
        Respuesta<TerminosCondiciones> resp = new Respuesta<TerminosCondiciones>();
        resp.setEstadoRespuesta(EstadoRespuesta.WARNING);

        when(terminosCondicionesBO.cargarTerminosCondiciones()).thenThrow(new BusinessException());
        when(respuestaFactory.crearRespuestaWarning(TerminosCondiciones.class,
                "Error al recuperar el archivo de terminos", null)).thenReturn(resp);

        Respuesta<TerminosCondiciones> resul = pagoAutomaticoManager.cargarTerminosCondiciones();
        Assert.assertEquals(resul.getEstadoRespuesta(), EstadoRespuesta.WARNING);
    }

    @Test
    public void obtenerCuentas() throws BusinessException {
        Cliente cli = new Cliente();
        List<Cuenta> cuentasDebito = new ArrayList<Cuenta>();
        cuentasDebito.add(CuentaMock.completarInfoCuenta());

        when(cuentaBO.obtenerCuentasDebito(cli)).thenReturn(cuentasDebito);

        Respuesta<MedioPagoSelectionView> resul = pagoAutomaticoManager.obtenerCuentas(cli);
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void obtenerCuentasError() throws BusinessException {
        Cliente cli = new Cliente();
        List<Cuenta> cuentasDebito = new ArrayList<Cuenta>();

        when(cuentaBO.obtenerCuentasDebito(cli)).thenReturn(cuentasDebito);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());

        Respuesta<MedioPagoSelectionView> resul = pagoAutomaticoManager.obtenerCuentas(cli);
        Assert.assertEquals(EstadoRespuesta.ERROR, resul.getEstadoRespuesta());
    }

    @Test
    public void adherirDebito() throws BusinessException {
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resp = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        resp.setEstadoRespuesta(EstadoRespuesta.OK);
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");
        resp.setRespuesta(view);

        Respuesta<AdhesionDebitoAutomatico> respDebito = new Respuesta<AdhesionDebitoAutomatico>();
        respDebito.setEstadoRespuesta(EstadoRespuesta.OK);
        respDebito.setRespuesta(new AdhesionDebitoAutomatico());

        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        Cliente cli = new Cliente();
        cli.setNup("nup");
        RegistroSesion rs = new RegistroSesion();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("tipo");
        cuenta.setNroCuentaProducto("12");
        cuenta.setNroSucursal("44");
        cuenta.setNroOrdenFirmante("orden firmante");

        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta);

        cli.setCuentas(lista);

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();

        when(validator.validate(view)).thenReturn(validaciones);
        when(debitoAutomaticoBO.adherir(Matchers.any(Cliente.class), Matchers.any(RegistroSesion.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(respDebito);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(sesionCliente.getCliente()).thenReturn(cli);
        when(respuestaFactory.crearRespuestaOk(AdhesionDebitoAutomaticoEnCuentaView.class, view)).thenReturn(resp);

        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void adherirDebitoOk2() throws BusinessException {
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resp = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        resp.setEstadoRespuesta(EstadoRespuesta.OK);
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");

        view.setCuentaSeleccionada("044-000001/2");
        resp.setRespuesta(view);

        Respuesta<AdhesionDebitoAutomatico> respDebito = new Respuesta<AdhesionDebitoAutomatico>();
        respDebito.setEstadoRespuesta(EstadoRespuesta.OK);
        respDebito.setRespuesta(new AdhesionDebitoAutomatico());

        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        Cliente cli = new Cliente();
        cli.setNup("nup");
        RegistroSesion rs = new RegistroSesion();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("tipo");
        cuenta.setNroCuentaProducto("12");
        cuenta.setNroSucursal("44");
        cuenta.setCbu("044-000001/2");
        cuenta.setTipoCuentaEnum(TipoCuenta.ABON);
        cuenta.setNroOrdenFirmante("orden firmante");

        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta);

        cli.setCuentas(lista);

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();

        when(validator.validate(view)).thenReturn(validaciones);
        when(debitoAutomaticoBO.adherir(Matchers.any(Cliente.class), Matchers.any(RegistroSesion.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(respDebito);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(sesionCliente.getCliente()).thenReturn(cli);
        when(respuestaFactory.crearRespuestaOk(AdhesionDebitoAutomaticoEnCuentaView.class, view)).thenReturn(resp);

        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void adherirDebitoOkCuentaUnicaDolaresTest() throws BusinessException {
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resp = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        resp.setEstadoRespuesta(EstadoRespuesta.OK);
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");

        view.setCuentaSeleccionada("044-000001/2");
        resp.setRespuesta(view);

        Respuesta<AdhesionDebitoAutomatico> respDebito = new Respuesta<AdhesionDebitoAutomatico>();
        respDebito.setEstadoRespuesta(EstadoRespuesta.OK);
        respDebito.setRespuesta(new AdhesionDebitoAutomatico());

        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        Cliente cli = new Cliente();
        cli.setNup("nup");
        RegistroSesion rs = new RegistroSesion();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("tipo");
        cuenta.setNroCuentaProducto("12");
        cuenta.setNroSucursal("44");
        cuenta.setCbu("044-000001/2");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        cuenta.setNroOrdenFirmante("orden firmante");

        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta);

        cli.setCuentas(lista);

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();

        when(validator.validate(view)).thenReturn(validaciones);
        when(debitoAutomaticoBO.adherir(Matchers.any(Cliente.class), Matchers.any(RegistroSesion.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(respDebito);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(sesionCliente.getCliente()).thenReturn(cli);
        when(respuestaFactory.crearRespuestaOk(AdhesionDebitoAutomaticoEnCuentaView.class, view)).thenReturn(resp);

        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void adherirDebitoRespuestaServicioErrorTest() throws BusinessException {
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resp = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(1);
        contadorIntentos.permiteReintentar();
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");
        view.setCuentaSeleccionada("12");
        resp.setRespuesta(view);

        Respuesta<AdhesionDebitoAutomatico> respDebito = new Respuesta<AdhesionDebitoAutomatico>();
        respDebito.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respDebito.setRespuesta(new AdhesionDebitoAutomatico());
        respDebito.add(new ItemMensajeRespuesta());

        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        Cliente cli = new Cliente();
        cli.setNup("nup");
        RegistroSesion rs = new RegistroSesion();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("tipo");
        cuenta.setNroCuentaProducto("12");
        cuenta.setNroSucursal("44");
        cuenta.setNroOrdenFirmante("orden firmante");
        cuenta.setCbu("12");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cli.setCuentas(new ArrayList<Cuenta>());
        cli.getCuentas().add(cuenta);

        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta);

        cli.setCuentas(lista);

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();

        when(validator.validate(view)).thenReturn(validaciones);
        when(debitoAutomaticoBO.adherir(Matchers.any(Cliente.class), Matchers.any(RegistroSesion.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(respDebito);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(sesionCliente.getCliente()).thenReturn(cli);
        when(respuestaFactory.crearRespuestaError(AdhesionDebitoAutomaticoEnCuentaView.class, null)).thenReturn(resp);
        when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.ERROR, resul.getEstadoRespuesta());
    }

    @Test
    public void adherirDebitoRespuestaServicioErrorReintentos() throws BusinessException {
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resp = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        ContadorIntentos contadorIntentos = new ContadorIntentos(3);
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");
        view.setCuentaSeleccionada("12");
        resp.setRespuesta(view);

        Respuesta<AdhesionDebitoAutomatico> respDebito = new Respuesta<AdhesionDebitoAutomatico>();
        respDebito.setEstadoRespuesta(EstadoRespuesta.OK);
        respDebito.setRespuesta(new AdhesionDebitoAutomatico());
        respDebito.add(new ItemMensajeRespuesta());
        respDebito.setRespuesta(new AdhesionDebitoAutomatico());

        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        Cliente cli = new Cliente();
        cli.setNup("nup");
        RegistroSesion rs = new RegistroSesion();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("tipo");
        cuenta.setNroCuentaProducto("12");
        cuenta.setNroSucursal("44");
        cuenta.setNroOrdenFirmante("orden firmante");
        cuenta.setCbu("12");
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cli.setCuentas(new ArrayList<Cuenta>());
        cli.getCuentas().add(cuenta);

        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta);

        cli.setCuentas(lista);

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();

        when(validator.validate(view)).thenReturn(validaciones);
        when(debitoAutomaticoBO.adherir(Matchers.any(Cliente.class), Matchers.any(RegistroSesion.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(respDebito);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(sesionCliente.getCliente()).thenReturn(cli);
        when(respuestaFactory.crearRespuestaError(AdhesionDebitoAutomaticoEnCuentaView.class, null)).thenReturn(resp);
        when(sesionParametros.getContador()).thenReturn(contadorIntentos);
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void adherirDebitoRespuestaServicioWarningTest() throws BusinessException {
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resp = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        resp.setEstadoRespuesta(EstadoRespuesta.ERROR);
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");
        resp.setRespuesta(view);

        Respuesta<AdhesionDebitoAutomatico> respDebito = new Respuesta<AdhesionDebitoAutomatico>();
        respDebito.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respDebito.setRespuesta(new AdhesionDebitoAutomatico());

        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        Cliente cli = new Cliente();
        cli.setNup("nup");
        RegistroSesion rs = new RegistroSesion();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("tipo");
        cuenta.setNroCuentaProducto("12");
        cuenta.setNroSucursal("44");
        cuenta.setNroOrdenFirmante("orden firmante");

        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta);

        cli.setCuentas(lista);

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();

        when(validator.validate(view)).thenReturn(validaciones);
        when(debitoAutomaticoBO.adherir(Matchers.any(Cliente.class), Matchers.any(RegistroSesion.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenReturn(respDebito);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(sesionCliente.getCliente()).thenReturn(cli);
        when(respuestaFactory.crearRespuestaError(AdhesionDebitoAutomaticoEnCuentaView.class, null)).thenReturn(resp);

        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.ERROR, resul.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void adherirDebitoError2() throws BusinessException {
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resp = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        resp.setEstadoRespuesta(EstadoRespuesta.OK);
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");
        view.setCuentaSeleccionada("044-000001/2");
        view.setReintentar("true");
        resp.setRespuesta(view);

        Respuesta<AdhesionDebitoAutomatico> respDebito = new Respuesta<AdhesionDebitoAutomatico>();
        respDebito.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> respError = new Respuesta<AdhesionDebitoAutomaticoEnCuentaView>();
        respError.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respError.setItemMensajeRespuesta(obtenerItemErrorTimeOut());

        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        Cliente cli = new Cliente();
        cli.setNup("nup");
        RegistroSesion rs = new RegistroSesion();
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuenta("tipo");
        cuenta.setNroCuentaProducto("12");
        cuenta.setNroSucursal("44");
        cuenta.setNroOrdenFirmante("orden firmante");

        List<Cuenta> lista = new ArrayList<Cuenta>();
        lista.add(cuenta);

        cli.setCuentas(lista);

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();

        when(validator.validate(view)).thenReturn(validaciones);
        when(debitoAutomaticoBO.adherir(Matchers.any(Cliente.class), Matchers.any(RegistroSesion.class),
                Matchers.any(AdhesionDebitoAutomatico.class))).thenThrow(new BusinessException());
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(sesionCliente.getCliente()).thenReturn(cli);
        when(respuestaFactory.crearRespuestaError(Matchers.eq(AdhesionDebitoAutomaticoEnCuentaView.class),
                Matchers.anyList())).thenReturn(respError);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(obtenerMensajeErrorTimeOutAdhesionDebito());

        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.ERROR, resul.getEstadoRespuesta());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void adherirDebitoValidacionError() throws BusinessException {
        AdhesionDebitoAutomaticoEnCuentaView view = new AdhesionDebitoAutomaticoEnCuentaView();
        view.setFiid("fii");
        view.setImporteLimite("45");
        view.setCodigoPagoElectronico("2");
        view.setCuentaSeleccionada("044-000001/2");
        view.setReintentar("true");
        RegistroSesion rs = new RegistroSesion();

        Set<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>> validaciones = new HashSet<ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView>>();
        ConstraintViolation<AdhesionDebitoAutomaticoEnCuentaView> constraintViolation = Mockito
                .mock(ConstraintViolation.class);
        validaciones.add(constraintViolation);

        when(constraintViolation.getPropertyPath()).thenReturn(Mockito.mock(Path.class));
        when(validator.validate(view)).thenReturn(validaciones);

        Respuesta<AdhesionDebitoAutomaticoEnCuentaView> resul = pagoAutomaticoManager.adherirDebitoEnCuenta(rs, view);
        Assert.assertEquals(EstadoRespuesta.WARNING, resul.getEstadoRespuesta());
    }

    @Test
    public void generarComoprobanteAdhesion() throws BusinessException {
        Respuesta<Reporte> resp = new Respuesta<Reporte>();
        resp.setEstadoRespuesta(EstadoRespuesta.OK);

        ComprobanteDebitoAutomatico comprobante = new ComprobanteDebitoAutomatico();

        when(pagoAutomaticoBO.generarComprobanteAdhesion(Matchers.any(ComprobanteDebitoAutomatico.class)))
                .thenReturn(resp);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

        when(sesionParametros.getNuevoPagoView()).thenReturn(new NuevoPago());
        Respuesta<ReporteView> resul = pagoAutomaticoManager.generarComprobanteAdhesion(comprobante);
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void generarComoprobanteAdhesion2() throws BusinessException {
        Respuesta<Reporte> resp = new Respuesta<Reporte>();
        Reporte repo = new Reporte();
        repo.setNombre("nombre");
        repo.setTipoArchivo(TipoArchivoEnum.PDF);

        resp.setRespuesta(repo);
        resp.setEstadoRespuesta(EstadoRespuesta.OK);

        ComprobanteDebitoAutomatico comprobante = new ComprobanteDebitoAutomatico();

        when(pagoAutomaticoBO.generarComprobanteAdhesion(Matchers.any(ComprobanteDebitoAutomatico.class)))
                .thenReturn(resp);
        when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);
        when(sesionParametros.getNuevoPagoView()).thenReturn(new NuevoPago());
        
        Respuesta<ReporteView> resul = pagoAutomaticoManager.generarComprobanteAdhesion(comprobante);
        Assert.assertEquals(EstadoRespuesta.OK, resul.getEstadoRespuesta());
    }

    @Test
    public void obtenerDatosAdhesionDebitoAutomaticoEnTarjetaOK() {

        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("BILOG");
        empresa.setVisaGifFactura("CLIENTE CONSTA DE 15 POSICIONES NUMERICAS");
        empresa.setVisaIdentificador("CLIENTE");
        empresa.setCuit("20177427056");
        empresa.setVisaHabilitado("S");
        empresa.setAmexHabilitado("S");

        when(mediosPagoBO.obtenerPorNombreFantasiaExactoTarjeta(Matchers.anyString())).thenReturn(empresa);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        

        // Then
        Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> respuesta = pagoAutomaticoManager
                .obtenerDatosAdhesionDebitoAutomaticoEnTarjeta("BILOG");

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

    }

    @Test
    public void obtenerDatosAdhesionDebitoAutomaticoEnTarjetaErrorAlBuscarEmpresa() {

        // When
        when(mediosPagoBO.obtenerPorNombreFantasia(Matchers.anyString())).thenReturn(null);

        // Then
        Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> respuesta = pagoAutomaticoManager
                .obtenerDatosAdhesionDebitoAutomaticoEnTarjeta("BILOG");

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }

    @Test
    public void obtenerDatosAdhesionDebitoAutomaticoEnTarjetaErrorNoHayTarjetas() {

        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("BILOG");
        empresa.setVisaGifFactura("CLIENTE CONSTA DE 15 POSICIONES NUMERICAS");
        empresa.setVisaIdentificador("CLIENTE");
        empresa.setCuit("20177427056");
        empresa.setVisaHabilitado("N");
        empresa.setAmexHabilitado("N");

        Mensaje mensajeError = new Mensaje();
        mensajeError.setMensaje("NO HAY TARJETAS COMPATIBLES");

        when(mediosPagoBO.obtenerPorNombreFantasia(Matchers.anyString())).thenReturn(empresa);
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mensajeBO.obtenerMensajePorCodigo("1527")).thenReturn(mensajeError);

        // Then
        Respuesta<AdhesionDebitoAutomaticoEnTarjetaView> respuesta = pagoAutomaticoManager
                .obtenerDatosAdhesionDebitoAutomaticoEnTarjeta("BILOG");

        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

    }

    private Mensaje obtenerMensajeErrorTimeOutAdhesionDebito() {
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1281");
        mensaje.setMensaje("<b>¡Lo sentimos!</b><p>No pudimos adherir {0} al {1} en tu cuenta {2}.</p>");
        return mensaje;
    }

    private List<ItemMensajeRespuesta> obtenerItemErrorTimeOut() {
        List<ItemMensajeRespuesta> items = new ArrayList<ItemMensajeRespuesta>();
        items.add(new ItemMensajeRespuesta("No se pudo realizar la adhesión a Débito Automático a <b>servicio</b>"));
        items.get(0).setTipoError(TipoError.NUEVO_PAGO_AUTOMATICO_TIME_OUT.getDescripcion());
        return items;
    }

    @Test
    public void confirmarAdhesionPagoAutomaticoAllowOK() {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<AutentificacionDTO> resRCA = new Respuesta<AutentificacionDTO>();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AdhesionPagoAutomatico> respuestaAdhesionPagoAutomatico = new Respuesta<AdhesionPagoAutomatico>();

        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        adhesionPagoAutomatico.setDesafio(new AutentificacionDTO());
        adhesionPagoAutomatico.setIsFromCalendario(Boolean.FALSE);
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAdhesionPagoAutomatico.setEstadoRespuesta(EstadoRespuesta.OK);
        
        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(obtenerMedioPago());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(resRCA);
        when(pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenReturn(respuestaAdhesionPagoAutomatico);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoManager
                .confirmarAdhesionPagoAutomatico(adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmarAdhesionPagoAutomaticoAllowReintentoError() {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<AutentificacionDTO> resRCA = new Respuesta<AutentificacionDTO>();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();
        Respuesta<AdhesionPagoAutomatico> respuestaAdhesionPagoAutomatico = new Respuesta<AdhesionPagoAutomatico>();

        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setReintentar("true");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        adhesionPagoAutomatico.setDesafio(new AutentificacionDTO());
        adhesionPagoAutomatico.setIsFromCalendario(Boolean.FALSE);
        resRCA.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAdhesionPagoAutomatico.setEstadoRespuesta(EstadoRespuesta.ERROR);
        
        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(obtenerMedioPago());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(resRCA);
        when(pagoAutomaticoBO.ejecutarAdhesionPagoAutomatico(Matchers.any(Cliente.class),
                Matchers.any(AdhesionPagoAutomatico.class))).thenReturn(respuestaAdhesionPagoAutomatico);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoManager
                .confirmarAdhesionPagoAutomatico(adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmarAdhesionPagoAutomaticoChallenge() {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<AutentificacionDTO> resRCA = new Respuesta<AutentificacionDTO>();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();

        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        adhesionPagoAutomatico.setIsFromCalendario(Boolean.FALSE);
        resRCA.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.WARNING);
        
        MedioPago medioPago = new MedioPago();
        medioPago.setCuit("234");
        medioPago.setServicio("servicio");

        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(obtenerMedioPago());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class))).thenReturn(resRCA);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoManager
                .confirmarAdhesionPagoAutomatico(adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
    }

    @Test
    public void confirmarAdhesionPagoAutomaticoErrorSinDesafios() {
        // When
        Cliente cliente = ClienteMock.completarInfoClienteVisaYAmex();
        AdhesionPagoAutomatico adhesionPagoAutomatico = new AdhesionPagoAutomatico();
        Respuesta<Object> respuestaEstadoDesafio = new Respuesta<Object>();

        adhesionPagoAutomatico.setImporteLimite("5000.2");
        adhesionPagoAutomatico.setCuentaSeleccionada(cliente.getCuentas().get(0).getCbu());
        respuestaEstadoDesafio.setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(obtenerMedioPago());
        when(sesionCliente.getCliente()).thenReturn(cliente);
        when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class), Matchers.anyInt()))
                .thenReturn(respuestaEstadoDesafio);
        // Then
        Respuesta<AdhesionPagoAutomatico> respuesta = pagoAutomaticoManager
                .confirmarAdhesionPagoAutomatico(adhesionPagoAutomatico);
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    @Test
    public void estadisticaComprobanteNuevoPagoAutomatico() {
        pagoAutomaticoManager.estadisticaComprobanteNuevoPagoAutomatico();
        Mockito.verify(estadisticaManager).add(EstadisticasConstants.PAGO_AUTOMATICO_ADHESION_VISUALIZACION_COMPROBANTE,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
    }

    @Test
    public void generarHashSeguridad() {
        Boolean respuesta = pagoAutomaticoManager.generarHashSeguridad(new HashDebitoAutomaticoTarjetaView());
        Assert.assertNotNull(respuesta);
    }

    @Test
    public void validarAutenticacionPagoAutomaticoOK() {
        Respuesta<Boolean> respuestaBoolean = new Respuesta<Boolean>();
        respuestaBoolean.setEstadoRespuesta(EstadoRespuesta.OK);
        when(autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaBoolean);
        Respuesta<Boolean> respuesta = pagoAutomaticoManager.validarAutenticacionPagoAutomatico();
        Assert.assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    @Test
    public void validarAutenticacionPagoAutomaticoError() {
        Respuesta<Boolean> respuestaBoolean = new Respuesta<Boolean>();
        respuestaBoolean.setEstadoRespuesta(EstadoRespuesta.ERROR);
        when(autentificacionManager.tieneAlgunDesafioHabilitadoParaLaOperacion(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(respuestaBoolean);
        Respuesta<Boolean> respuesta = pagoAutomaticoManager.validarAutenticacionPagoAutomatico();
        Assert.assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
    }

    private MedioPago obtenerMedioPago(){
    	MedioPago empresa = new MedioPago();
        empresa.setNombreFantasia("BILOG");
        empresa.setVisaGifFactura("CLIENTE CONSTA DE 15 POSICIONES NUMERICAS");
        empresa.setVisaIdentificador("CLIENTE");
        empresa.setCuit("20177427056");
        empresa.setVisaHabilitado("S");
        empresa.setAmexHabilitado("S");
        return empresa;
    }
}
