package ar.com.santanderrio.obp.servicios.pagocompras.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.entities.AutentificacionDTO;
import ar.com.santanderrio.obp.servicios.comun.autentificacion.manager.AutentificacionManager;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.utils.HashUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagocompras.bo.PagoComprasBO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasInDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.dto.PagoComprasOutDTO;
import ar.com.santanderrio.obp.servicios.pagocompras.entities.DeudaPagoComprasEntity;
import ar.com.santanderrio.obp.servicios.pagocompras.manager.impl.PagoComprasManagerImpl;
import ar.com.santanderrio.obp.servicios.pagos.bo.MediosPagoBO;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoSelectionView;
import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPagoView;
import ar.com.santanderrio.obp.servicios.pagos.entities.NuevoPago;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class PagoComprasManagerTest.
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class PagoComprasManagerTest {

    /** The pago compras manager. */
    @InjectMocks
    private PagoComprasManagerImpl pagoComprasManager;

    /** The pago compras BO. */
    @Mock
    private PagoComprasBO pagoComprasBO;

    /** The medios pago BO. */
    @Mock
    private MediosPagoBO mediosPagoBO;

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The autentificacion manager. */
    @Mock
    private AutentificacionManager autentificacionManager;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The contador. */
    @Mock
    private ContadorIntentos contador = new ContadorIntentos(2);

    /** The pago compras. */
    private NuevoPago pagoCompras = new NuevoPago();

    /** The hash. */
    private String hash;

    /** The cliente. */
    private Cliente cliente = new Cliente();

    /** The cuenta. */
    private Cuenta cuenta = new Cuenta();

    /** The medio pago. */
    private MedioPago medioPago = new MedioPago();

    /** The divisa. */
    private DivisaEnum divisa = DivisaEnum.DOLAR;

    /** The registro sesion. */
    private RegistroSesion registroSesion = new RegistroSesion();

    /** The dto. */
    private PagoComprasOutDTO dto = new PagoComprasOutDTO();

    /** The mensaje feedback. */
    private Mensaje mensajeFeedback = new Mensaje();

    /**
     * Inits the.
     *
     * @throws IllegalAccessException
     *             the illegal access exception
     */
    @Before
    public void init() throws IllegalAccessException {
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Mensaje");
        mensaje.setCodigo("0000");
        mensaje.setTag("Tag");
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        FieldUtils.writeDeclaredField(pagoComprasManager, "valorDesafio", "1", true);
        // Ejecucion pago de compras
        pagoCompras.setNombreEmpresa("Club de corredores");
        pagoCompras.setFechaVencimiento("");
        pagoCompras.setCodigoPagoElectronico("3017524874");
        pagoCompras.setMonto("300");
        pagoCompras.setCuentaSeleccionada("0720084788000042562872");
        pagoCompras.setReintentar("false");
        pagoCompras.setFechaHora("12/09/2018 - 18:00");
        hash = HashUtils.obtenerHash(crearMapaView(pagoCompras));
        cliente = ClienteMock.infoCliente();
        cuenta.setCbu("0720084788000042562872");
        cuenta.setTipoCuentaEnum(TipoCuenta.CAJA_AHORRO_DOLARES);
        cliente.getCuentas().add(cuenta);
        medioPago.setPpdctaIdEmpAcuerdo("345345234562");
        medioPago.setPpdctaCodProdAcuerdo("345");
        medioPago.setPpdctaNroInstaCuerdo("24");
        registroSesion.setPid("122343453453");
        dto.setTieneError(false);
        dto.setComprobante("223434535356");
        dto.setNumeroBoleta("6546465675656564");
        mensajeFeedback.setMensaje("<p>Tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b> fue realizado con éxito.</p>");
        mensajeFeedback.setCodigo("1267");
    }

    /**
     * Obtener deudas ok.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerDeudasOk() throws BusinessException {
        // Given
        List<DeudaPagoComprasEntity> deudas = new ArrayList<DeudaPagoComprasEntity>();
        DeudaPagoComprasEntity deuda1 = new DeudaPagoComprasEntity();
        deuda1.setFechaVencimientoDeuda("20181003");
        deuda1.setImporteSaldoDeuda("00000009005500");
        deuda1.setNroComprobanteDeuda("YACHT  257CUOTA");
        deuda1.setNroCuotaDeuda("0016");
        deudas.add(deuda1);

        DeudaPagoComprasEntity deuda2 = new DeudaPagoComprasEntity();
        deuda2.setFechaVencimientoDeuda("20181103");
        deuda2.setImporteSaldoDeuda("00000009005500");
        deuda2.setNroComprobanteDeuda("YACHT  257CUOTA");
        deuda2.setNroCuotaDeuda("0017");
        deudas.add(deuda2);
        // When
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        when(pagoComprasBO.obtenerDeudas(Matchers.any(Cliente.class), Matchers.any(MedioPago.class),
                Matchers.anyString())).thenReturn(deudas);
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(new MedioPago());
        when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        // Then
        Respuesta<MedioPagoSelectionView> respuesta = pagoComprasManager.obtenerDeudas(new MedioPagoView());
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(2, respuesta.getRespuesta().getCantidadFacturas().intValue());
        assertEquals("03/10/2018", respuesta.getRespuesta().getListaFacturas().get(0).getFechaVencimiento());
        assertEquals("$ 90.055,00", respuesta.getRespuesta().getListaFacturas().get(0).getMonto());
    }

    /**
     * Obtener cuentas ok.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerCuentasOk() throws BusinessException {
        // Given
        List<Cuenta> cuentas = new ArrayList<Cuenta>();
        Cuenta cuenta1 = new Cuenta();
        cuenta1.setTipoCuenta("02");
        cuenta1.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        cuenta1.setNroCuentaProducto("0000000001600148");
        cuenta1.setNroSucursal("0099");
        cuenta1.setNroTarjetaCredito("00000000000000000000");
        cuenta1.setSaldoCuenta("");
        cuenta1.setSaldoCUPesos("8457707.60");
        cuenta1.setSaldoCUDls("9490505.58");
        cuentas.add(cuenta1);
        // When
        when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(new MedioPago());
        when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        when(pagoComprasBO.obtenerCuentas(Matchers.any(Cliente.class), Matchers.any(DivisaEnum.class)))
                .thenReturn(cuentas);
        // Then
        Respuesta<MedioPagoSelectionView> respuesta = pagoComprasManager.obtenerConfiguracion(new MedioPagoView());
        // Expected
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
    }

    /**
     * Continuar pago compra OK test.
     */
    @Test
    public void continuarPagoComprasOKTest() {
        // Given
        NuevoPago pagoCompras = new NuevoPago();
        pagoCompras.setNombreEmpresa("Club de corredores");
        pagoCompras.setFechaVencimiento("");
        pagoCompras.setCodigoPagoElectronico("3017524874");
        pagoCompras.setMonto("300");
        pagoCompras.setCuentaSeleccionada("0720084788000042562872");
        pagoCompras.setReintentar("false");

        // Then
        Respuesta<Boolean> resp = pagoComprasManager.continuarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Ejecutar pago compras sin deuda OK test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaOKTest() throws BusinessException {
        // Given
        Respuesta<Object> estadoDesafio = new Respuesta<Object>();
        estadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> rtaAutentificacion = new Respuesta<AutentificacionDTO>();
        rtaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);

        // When
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        Mockito.when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        Mockito.when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),
                Matchers.any(Integer.class))).thenReturn(estadoDesafio);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rtaAutentificacion);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(pagoComprasBO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenReturn(dto);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);

        // Then
        Respuesta<NuevoPago> resp = pagoComprasManager.ejecutarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Ejecutar pago compras sin deuda error generico test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaErrorGenericoTest() throws BusinessException {
        // Given
        Respuesta<Object> estadoDesafio = new Respuesta<Object>();
        estadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> rtaAutentificacion = new Respuesta<AutentificacionDTO>();
        rtaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        pagoCompras.setReintentar("true");
        dto.setTieneError(true);
        dto.setCodError("1232432434");
        Mensaje mensaje = new Mensaje();
        mensaje.setCodigo("1268");
        mensaje.setMensaje("<p>No pudimos realizar tu <b>{0}</b> a <b>{1}</b> por <b>{2}</b>.</p>");

        // When
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        Mockito.when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        Mockito.when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),
                Matchers.any(Integer.class))).thenReturn(estadoDesafio);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rtaAutentificacion);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(pagoComprasBO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenReturn(dto);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Mockito.when(sesionParametros.getContador()).thenReturn(contador);
        Mockito.when(sesionParametros.getContador().permiteReintentar()).thenReturn(true);

        // Then
        Respuesta<NuevoPago> resp = pagoComprasManager.ejecutarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
        Assert.assertEquals("ERROR_GENERICO", resp.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Ejecutar pago compras sin deuda business exception test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoComprasSinDeudaBusinessExceptionTest() throws BusinessException {
        // Given
        Respuesta<Object> estadoDesafio = new Respuesta<Object>();
        estadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> rtaAutentificacion = new Respuesta<AutentificacionDTO>();
        rtaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        BusinessException businessException = new BusinessException("Business Exception!");

        // When
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        Mockito.when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        Mockito.when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),
                Matchers.any(Integer.class))).thenReturn(estadoDesafio);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rtaAutentificacion);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(pagoComprasBO.ejecutarPagoComprasSinDeuda(Matchers.any(Cliente.class),
                Matchers.any(PagoComprasInDTO.class))).thenThrow(businessException);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);

        // Then
        Respuesta<NuevoPago> resp = pagoComprasManager.ejecutarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
        Assert.assertEquals("ERROR_TIME_OUT", resp.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Ejecutar pago compras con deuda OK test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaOKTest() throws BusinessException {
        // Given
        Respuesta<Object> estadoDesafio = new Respuesta<Object>();
        estadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> rtaAutentificacion = new Respuesta<AutentificacionDTO>();
        rtaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        pagoCompras.setFechaVencimiento("21/09/2018");
        hash = "24A2F0E60D1D46426B0ED90CCE67D975";

        // When
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        Mockito.when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        Mockito.when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),
                Matchers.any(Integer.class))).thenReturn(estadoDesafio);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rtaAutentificacion);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(
                pagoComprasBO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class), Matchers.any(NuevoPago.class),
                        Matchers.any(MedioPago.class), Matchers.any(Cuenta.class), Matchers.any(DivisaEnum.class)))
                .thenReturn(dto);
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensajeFeedback);

        // Then
        Respuesta<NuevoPago> resp = pagoComprasManager.ejecutarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
    }

    /**
     * Ejecutar pago compras con deuda error moneda incorrecta test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaErrorMonedaIncorrectaTest() throws BusinessException {
        // Given
        Respuesta<Object> estadoDesafio = new Respuesta<Object>();
        estadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> rtaAutentificacion = new Respuesta<AutentificacionDTO>();
        rtaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        pagoCompras.setFechaVencimiento("21/09/2018");
        hash = "24A2F0E60D1D46426B0ED90CCE67D975";
        dto.setTieneError(true);
        dto.setCodError("10740127");

        // When
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        Mockito.when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        Mockito.when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),
                Matchers.any(Integer.class))).thenReturn(estadoDesafio);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rtaAutentificacion);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(
                pagoComprasBO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class), Matchers.any(NuevoPago.class),
                        Matchers.any(MedioPago.class), Matchers.any(Cuenta.class), Matchers.any(DivisaEnum.class)))
                .thenReturn(dto);
        Mockito.when(sesionParametros.getContador()).thenReturn(contador);
        Mockito.when(sesionParametros.getContador().permiteReintentar()).thenReturn(true);

        // Then
        Respuesta<NuevoPago> resp = pagoComprasManager.ejecutarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
        Assert.assertEquals("MONEDA_INCORRECTA", resp.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Ejecutar pago compras con deuda error cuenta sin saldo test.
     *
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void ejecutarPagoComprasConDeudaErrorCuentaSinSaldoTest() throws BusinessException {
        // Given
        Respuesta<Object> estadoDesafio = new Respuesta<Object>();
        estadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> rtaAutentificacion = new Respuesta<AutentificacionDTO>();
        rtaAutentificacion.setEstadoRespuesta(EstadoRespuesta.OK);
        pagoCompras.setFechaVencimiento("21/09/2018");
        hash = "24A2F0E60D1D46426B0ED90CCE67D975";
        dto.setTieneError(true);
        dto.setCodError("11262122");

        // When
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(DivisaEnum.PESO);
        Mockito.when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        Mockito.when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),
                Matchers.any(Integer.class))).thenReturn(estadoDesafio);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rtaAutentificacion);
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(
                pagoComprasBO.ejecutarPagoComprasConDeuda(Matchers.any(Cliente.class), Matchers.any(NuevoPago.class),
                        Matchers.any(MedioPago.class), Matchers.any(Cuenta.class), Matchers.any(DivisaEnum.class)))
                .thenReturn(dto);
        Mockito.when(sesionParametros.getContador()).thenReturn(contador);
        Mockito.when(sesionParametros.getContador().permiteReintentar()).thenReturn(false);

        // Then
        Respuesta<NuevoPago> resp = pagoComprasManager.ejecutarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.WARNING, resp.getEstadoRespuesta());
        Assert.assertEquals("ERROR_REINTENTOS_AGOTADOS", resp.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Ejecutar pago compras error RSA test.
     */
    @Test
    public void ejecutarPagoComprasErrorRSATest() {
        // Given
        Respuesta<Object> estadoDesafio = new Respuesta<Object>();
        estadoDesafio.setEstadoRespuesta(EstadoRespuesta.OK);
        Respuesta<AutentificacionDTO> rtaAutentificacion = new Respuesta<AutentificacionDTO>();
        rtaAutentificacion.setEstadoRespuesta(EstadoRespuesta.ERROR);

        // When
        Mockito.when(sesionParametros.getValidacionHash()).thenReturn(hash);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        Mockito.when(mediosPagoBO.obtenerMedioPagoPorCodigo(Matchers.anyString())).thenReturn(medioPago);
        Mockito.when(pagoComprasBO.obtenerDivisa(Matchers.any(MedioPago.class))).thenReturn(divisa);
        Mockito.when(autentificacionManager.verificarEstadoDesafio(Matchers.any(AutentificacionDTO.class),
                Matchers.any(Integer.class))).thenReturn(estadoDesafio);
        Mockito.when(autentificacionManager.ejecutarValidacionRSA(Matchers.any(AutentificacionDTO.class)))
                .thenReturn(rtaAutentificacion);

        // Then
        Respuesta<NuevoPago> resp = pagoComprasManager.ejecutarPagoCompras(pagoCompras);

        // Expected
        Assert.assertNotNull(resp);
        Assert.assertEquals(EstadoRespuesta.ERROR, resp.getEstadoRespuesta());
    }

    /**
     * Crear mapa view.
     *
     * @param pagoCompras
     *            the pago compras
     * @return the map
     */
    private Map<String, Object> crearMapaView(NuevoPago pagoCompras) {
        Map<String, Object> mapaAtributos = new HashMap<String, Object>();
        mapaAtributos.put("empresa", pagoCompras.getNombreEmpresa());
        mapaAtributos.put("fechaVencimiento", pagoCompras.getFechaVencimiento());
        mapaAtributos.put("numeroIdentificacion", pagoCompras.getCodigoPagoElectronico());
        mapaAtributos.put("importe", pagoCompras.getMonto());
        mapaAtributos.put("numeroCuenta", pagoCompras.getCuentaSeleccionada());
        return mapaAtributos;
    }

    /**
     * Grabar estadistica comprobante pago compras OK test.
     */
    @Test
    public void grabarEstadisticaComprobantePagoComprasOKTest() {
        // When
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.TRUE);

        // Then
        Respuesta<Boolean> resp = pagoComprasManager.grabarEstadisticaComprobantePagoCompras();

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
        Assert.assertEquals(Boolean.TRUE, resp.getRespuesta());
    }

    /**
     * Grabar estadistica comprobante pago compras error test.
     */
    @Test
    public void grabarEstadisticaComprobantePagoComprasErrorTest() {
        // When
        Mockito.when(estadisticaManager.add(Matchers.anyString(), Matchers.anyString())).thenReturn(Boolean.FALSE);

        // Then
        Respuesta<Boolean> resp = pagoComprasManager.grabarEstadisticaComprobantePagoCompras();

        // Expected
        Assert.assertEquals(EstadoRespuesta.OK, resp.getEstadoRespuesta());
        Assert.assertEquals(Boolean.FALSE, resp.getRespuesta());
    }
}
