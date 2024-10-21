package ar.com.santanderrio.obp.servicios.compraventa.bo;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dao.NUPDAO;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.ErrorCompraVentaEnum;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCompraVenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleDocumentoDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.NupDTO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

@RunWith(MockitoJUnitRunner.class)
public class CompraVentaDolaresBOTest {

    @InjectMocks
    private CompraVentaDolaresBOImpl compraVentaDolaresBOImpl = new CompraVentaDolaresBOImpl();

    @Mock
    private NUPDAO nupDAO;

    @Mock
    private LegalBO legalBO;

    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @Mock
    private MensajeDAO mensajeDAO;

    @Spy
    private CompraVentaDolaresUtil compraVentaDolaresUtil;

    @Mock
    private SesionCompraVenta sesionCompraVenta;

    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    @Mock
    private MensajeBO mensajeBO;

    Mensaje mensaje = new Mensaje();

    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Metodo para testear comportamiento de obtenerConsultaNUP del BO,
     * CompraVentaDolaresBOImpl. Caso: Feliz.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void ConsultaCompraVentaDolaresBO_Ok() throws Exception {

        // mockeo respuesta de servicio

        DetalleDocumentoDTO detalleDocumento1 = mock(DetalleDocumentoDTO.class);
        DetalleDocumentoDTO detalleDocumento2 = mock(DetalleDocumentoDTO.class);
        Map<String, DetalleDocumentoDTO> coleccionDetalleDocumento = new HashMap<String, DetalleDocumentoDTO>();

        when(detalleDocumento1.getTipoDocumento()).thenReturn("L");
        when(detalleDocumento1.getNroDocumento()).thenReturn("24331129956");
        when(detalleDocumento1.isDocumentoPrincipal()).thenReturn(false);

        when(detalleDocumento2.getTipoDocumento()).thenReturn("N");
        when(detalleDocumento2.getNroDocumento()).thenReturn("00033112995");
        when(detalleDocumento2.isDocumentoPrincipal()).thenReturn(true);

        coleccionDetalleDocumento.put(detalleDocumento1.getTipoDocumento(), detalleDocumento1);
        coleccionDetalleDocumento.put(detalleDocumento2.getTipoDocumento(), detalleDocumento2);

        NupDTO consultaNUP = mock(NupDTO.class);
        when(consultaNUP.getDetalleDocumento()).thenReturn(coleccionDetalleDocumento);

        when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(consultaNUP);
        DetalleDocumentoDTO detalleDocumentoRes = compraVentaDolaresBOImpl.obtenerDocumentoValido(obtenerCliente());
        Assert.assertNotNull(detalleDocumentoRes);
    }

    /**
     * Metodo para testear comportamiento de obtenerConsultaNUP del BO,
     * CompraVentaDolaresBOImpl. Caso: SinAccesoALaInformacionException
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void ConsultaCompraVentaDolaresBO_SinAccesoAInformacion() throws Exception {

        // mockeo respuesta de servicio

        DetalleDocumentoDTO detalleDocumento1 = mock(DetalleDocumentoDTO.class);
        DetalleDocumentoDTO detalleDocumento2 = mock(DetalleDocumentoDTO.class);
        Map<String, DetalleDocumentoDTO> coleccionDetalleDocumento = new HashMap<String, DetalleDocumentoDTO>();

        when(detalleDocumento1.getTipoDocumento()).thenReturn("W");
        when(detalleDocumento1.getNroDocumento()).thenReturn("24331129956");
        when(detalleDocumento1.isDocumentoPrincipal()).thenReturn(false);

        when(detalleDocumento2.getTipoDocumento()).thenReturn("N");
        when(detalleDocumento2.getNroDocumento()).thenReturn("00033112995");
        when(detalleDocumento2.isDocumentoPrincipal()).thenReturn(true);

        coleccionDetalleDocumento.put(detalleDocumento1.getTipoDocumento(), detalleDocumento1);
        coleccionDetalleDocumento.put(detalleDocumento2.getTipoDocumento(), detalleDocumento2);

        NupDTO consultaNUP = mock(NupDTO.class);
        when(consultaNUP.getDetalleDocumento()).thenReturn(coleccionDetalleDocumento);

        when(nupDAO.obtenerNUP(Matchers.any(Cliente.class))).thenReturn(consultaNUP);
        compraVentaDolaresBOImpl.obtenerDocumentoValido(obtenerCliente());

    }

    /**
     * Obtener descripcion tipo cuenta unica pesos O dolares test.
     */
    @Test
    public void obtenerDescripcionTipoCuentaUnicaPesosODolaresTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_DOLARES);
        String res = compraVentaDolaresBOImpl.obtenerDescripcionTipoCuenta(cuenta, true);
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_DOLARES.getDescripcion() + " en u$s", res);

        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA_PESOS);
        res = compraVentaDolaresBOImpl.obtenerDescripcionTipoCuenta(cuenta, false);
        Assert.assertEquals(TipoCuenta.CUENTA_UNICA_PESOS.getDescripcionConMoneda(), res);
    }

    /**
     * Obtener descripcion tipo cuenta unica not pesos O dolares test.
     */
    @Test
    public void obtenerDescripcionTipoCuentaUnicaNotPesosODolaresTest() {
        Cuenta cuenta = new Cuenta();
        cuenta.setTipoCuentaEnum(TipoCuenta.ABON);
        String res = compraVentaDolaresBOImpl.obtenerDescripcionTipoCuenta(cuenta, true);
        Assert.assertEquals(TipoCuenta.ABON.getDescripcionConMoneda(), res);

        cuenta.setTipoCuentaEnum(TipoCuenta.CHEQUES_SECIONADOS);
        res = compraVentaDolaresBOImpl.obtenerDescripcionTipoCuenta(cuenta, true);
        Assert.assertEquals(TipoCuenta.CHEQUES_SECIONADOS.getDescripcionConMoneda(), res);
    }

    /**
     * Crear respuesta error no fuera horario O saldo insuficiente test.
     */
    @Test
    public void crearRespuestaErrorNoFueraHorarioOSaldoInsuficienteTest() {
        Cuenta cuenta = new Cuenta();
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<Object> res = compraVentaDolaresBOImpl.crearRespuestaError(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION,
                "", cuenta, "");
        Assert.assertEquals(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta error no saldo insuficiente test.
     */
    @Test
    public void crearRespuestaErrorNoSaldoInsuficienteTest() {
        Cuenta cuentaNueva = new Cuenta();
        cuentaNueva.setNroSucursal("1234");
        cuentaNueva.setNroCuentaProducto("1231241254");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<Object> res = compraVentaDolaresBOImpl.crearRespuestaError(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_1,
                "", cuentaNueva, "");
        Assert.assertEquals(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_1.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Crear respuesta error no saldo insuficiente 2 test.
     */
    @Test
    public void crearRespuestaErrorNoSaldoInsuficiente2Test() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("1234");
        cuenta.setNroCuentaProducto("1231241254");
        Respuesta<Object> res = compraVentaDolaresBOImpl.crearRespuestaError(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_2,
                "", cuenta, "");
        Assert.assertEquals(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_2.getTipoError().getDescripcion(),
                res.getItemsMensajeRespuesta().get(0).getTipoError());
    }

    /**
     * Obtener datos del error test.
     */
    @Test
    public void obtenerDatosDelErrorTest() {

        List<CuentasAdhesionDebitoView> lista = new ArrayList<CuentasAdhesionDebitoView>();

        ErrorCompraVentaEnum res = compraVentaDolaresBOImpl.obtenerDatosDelError(10000077, true);
        Assert.assertEquals(ErrorCompraVentaEnum.OPERACION_FUERA_HORARIO, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10000006, true);
        Assert.assertEquals(ErrorCompraVentaEnum.CAMBIO_LA_COTIZACION, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10000004, true);
        Assert.assertEquals(ErrorCompraVentaEnum.ACTUALIZACION_DATOS_CUENTA, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10000002, true);
        Assert.assertEquals(ErrorCompraVentaEnum.MONTO_EXCEDE_EL_PERMITIDO_2, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10002001, true);
        Assert.assertEquals(ErrorCompraVentaEnum.CUENTA_INHABILITADA_1, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10000008, true);
        Assert.assertEquals(ErrorCompraVentaEnum.CUENTA_INHABILITADA_2, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10002065, true);
        Assert.assertEquals(ErrorCompraVentaEnum.CUENTAINHABILITADA_3, res);

        Mockito.when(sesionCompraVenta.getCuentasDolares()).thenReturn(lista);
        Mockito.when(sesionCompraVenta.getCuentasPesos()).thenReturn(lista);
        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10002122, true);
        Assert.assertEquals(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_1, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10000515, true);
        Assert.assertEquals(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_2, res);

        lista.add(new CuentasAdhesionDebitoView());
        lista.add(new CuentasAdhesionDebitoView());

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10002122, false);
        Assert.assertEquals(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_3, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10000515, false);
        Assert.assertEquals(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_3, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10009079, false);
        Assert.assertEquals(ErrorCompraVentaEnum.COD_CUENTA_ALFA_NO_VERIFICADA_NUNCA_OPERO_USD, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(10009080, false);
        Assert.assertEquals(ErrorCompraVentaEnum.COD_CUENTA_ALFA_NO_VERIFICADA, res);

        res = compraVentaDolaresBOImpl.obtenerDatosDelError(5, true);
        Assert.assertEquals(ErrorCompraVentaEnum.NO_SE_PUEDE_REALIZAR_LA_OPERACION, res);
    }

    /**
     * Crear respuesta error sucursal fuera horario.
     */
    @Test
    public void crearRespuestaErrorSucursalFueraHorario() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("1231241254");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<Object> res = compraVentaDolaresBOImpl
                .crearRespuestaError(ErrorCompraVentaEnum.OPERACION_FUERA_HORARIO, "C", cuenta, "");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(ErrorCompraVentaEnum.OPERACION_FUERA_HORARIO.getTag(),
                res.getItemsMensajeRespuesta().get(0).getTag());
    }

    /**
     * Crear respuesta error saldo insuficiente.
     */
    @Test
    public void crearRespuestaErrorSaldoInsuficiente() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("1231241254");
        cuenta.setNroSucursal("1234");
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<Object> res = compraVentaDolaresBOImpl.crearRespuestaError(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_1,
                "C", cuenta, "");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertEquals(ErrorCompraVentaEnum.SALDO_INSUFICIENTE_1.getTag(),
                res.getItemsMensajeRespuesta().get(0).getTag());
    }

    /**
     * Obtener cliente.
     *
     * @return the cliente
     */
    private Cliente obtenerCliente() {
        Segmento segmento = new Segmento();
        segmento.setSelect(false);
        segmento.setDuo(false);
        segmento.setPyme(false);
        segmento.setUniversidad(false);
        Cliente c1 = new Cliente();
        c1.setNombre("CONSTANCIO PERCY");
        c1.setApellido1("MILANDO");
        c1.setApellido2("M");
        c1.setSegmento(segmento);
        return c1;
    }

}
