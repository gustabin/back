package ar.com.santanderrio.obp.servicios.compraventa.bo;

import java.util.ArrayList;

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
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaCasuisticaRespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.bo.impl.CompraVentaDolaresBOImpl;
import ar.com.santanderrio.obp.servicios.compraventa.dto.CompraVentaInicioDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.ComprobanteCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.dto.SimulacionCompraVentaDTO;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaStringUtil;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.CompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.ComprobanteCompraVentaView;
import ar.com.santanderrio.obp.servicios.compraventa.web.view.SimulacionCompraVentaDolarView;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CompraVentaCasuisticaRespuestaBOTest.
 *
 * @author dante.omar.olmedo
 */
@RunWith(MockitoJUnitRunner.class)
public class CompraVentaCasuisticaRespuestaBOTest {

    /** The casuistica BO. */
    @InjectMocks
    @Spy
    private CompraVentaCasuisticaRespuestaBO casuistica = new CompraVentaCasuisticaRespuestaBOImpl();

    /** The mensaje manager. */
    @Mock
    private MensajeManager mensajeManager;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /** The mensaje. */
    Mensaje mensaje = new Mensaje();

    /**
     * Inits the mocks.
     */
    @Before
    public void init() {
        mensaje.setMensaje("Mensaje");
    }

    /**
     * Crear respuesta erronea documento invalido test.
     */
    @Test
    public void crearRespuestaErroneaDocumentoInvalidoTest() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        // Random related class for template
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaErroneaDocumentoInvalido();
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals("documentoInvalido", res.getItemsMensajeRespuesta().get(0).getTag());
    }

    /**
     * Crear respuesta erronea cuenta inhabilitada 1 test.
     */
    @Test
    public void crearRespuestaErroneaCuentaInhabilitada1Test() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaErroneaCuentaInhabilitada1();
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals("servicioNoFunciona", res.getItemsMensajeRespuesta().get(0).getTag());
    }

    /**
     * Crear respuesta erronea cuenta inhabilitada 2 test.
     */
    @Test
    public void crearRespuestaErroneaCuentaInhabilitada2Test() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaErroneaCuentaInhabilitada2();
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals("sinCuentaHabilitada", res.getItemsMensajeRespuesta().get(0).getTag());
    }

    /**
     * Crear respuesta erronea cuenta origen fuera horario test.
     */
    @Test
    public void crearRespuestaErroneaCuentaOrigenFueraHorarioTest() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaErroneaCuentaOrigenFueraHorario();
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals("cuentaOrigenFueraHorario", res.getItemsMensajeRespuesta().get(0).getTag());
    }

    /**
     * Crear respuesta warning test.
     */
    @Test
    public void crearRespuestaWarningTest() {
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaWarning(CompraVentaDolaresBO.class,
                new CompraVentaDolaresBOImpl(), new ArrayList<ItemMensajeRespuesta>());
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta error cotizacion test.
     */
    @Test
    public void crearRespuestaErrorCotizacionTest() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaErrorCotizacion();
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
        Assert.assertEquals("cotizacionServicioError", res.getItemsMensajeRespuesta().get(0).getTag());
    }

    /**
     * Crear respuesta error test.
     */
    @Test
    public void crearRespuestaErrorTest() {
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaError(CompraVentaDolaresBO.class,
                new ArrayList<ItemMensajeRespuesta>());
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta error other.
     */
    @Test
    public void crearRespuestaErrorOther() {
        Mockito.when(mensajeBO.obtenerMensajePorCodigo(Matchers.any(String.class))).thenReturn(mensaje);
        Respuesta<CompraVentaDolaresBO> res = casuistica.crearRespuestaError(CompraVentaStringUtil.DOCUMENTO_INVALIDO,
                TipoError.DOCUMENTO_INVALIDO, CodigoMensajeConstantes.CODIGO_ERROR_DOCUMENTO_INVALIDO);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta erronea con datos exception warning test.
     */
    @Test
    public void crearRespuestaErroneaConDatosExceptionWarningTest() {
        Respuesta<SimulacionCompraVentaDTO> param1 = new Respuesta<SimulacionCompraVentaDTO>();
        param1.setEstadoRespuesta(EstadoRespuesta.WARNING);
        Respuesta<SimulacionCompraVentaDolarView> res = casuistica.crearRespuestaErroneaConDatosException(param1, null,
                "V");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertTrue(res.getRespuesta().getEsVenta());

        // Compra
        res = casuistica.crearRespuestaErroneaConDatosException(param1, null, "C");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertTrue(!res.getRespuesta().getEsVenta());
    }

    /**
     * Crear respuesta erronea con datos exception not warning test.
     */
    @Test
    public void crearRespuestaErroneaConDatosExceptionNotWarningTest() {
        Respuesta<SimulacionCompraVentaDTO> param1 = new Respuesta<SimulacionCompraVentaDTO>();
        param1.setEstadoRespuesta(EstadoRespuesta.ERROR);
        Respuesta<SimulacionCompraVentaDolarView> res = casuistica.crearRespuestaErroneaConDatosException(param1, null,
                "V");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta erronea con datos exception operacion feedback warning
     * test.
     */
    @Test
    public void crearRespuestaErroneaConDatosExceptionOperacionFeedbackWarningTest() {
        Respuesta<ComprobanteCompraVentaDTO> rtaDto = new Respuesta<ComprobanteCompraVentaDTO>();
        rtaDto.setEstadoRespuesta(EstadoRespuesta.WARNING);
        rtaDto.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        rtaDto.getItemsMensajeRespuesta().add(new ItemMensajeRespuesta());
        rtaDto.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.ERROR_CAMBIO_COTIZACION_DOLAR.getDescripcion());
        Respuesta<ComprobanteCompraVentaView> res = casuistica
                .crearRespuestaErroneaConDatosExceptionOperacionFeedback(rtaDto, new Cuenta(), "C");
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
        Assert.assertTrue(res.getRespuesta().getEsCompra());
    }

    /**
     * Crear respuesta erronea con datos exception operacion feedback error
     * test.
     */
    @Test
    public void crearRespuestaErroneaConDatosExceptionOperacionFeedbackErrorTest() {
        Respuesta<ComprobanteCompraVentaDTO> rtaDto = new Respuesta<ComprobanteCompraVentaDTO>();
        rtaDto.setEstadoRespuesta(EstadoRespuesta.ERROR);
        rtaDto.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        rtaDto.getItemsMensajeRespuesta().add(new ItemMensajeRespuesta());
        rtaDto.getItemsMensajeRespuesta().get(0).setTipoError(TipoError.CLAVEINVALIDA.getDescripcion());
        Respuesta<ComprobanteCompraVentaView> res = casuistica
                .crearRespuestaErroneaConDatosExceptionOperacionFeedback(rtaDto, new Cuenta(), "C");
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());
    }

    /**
     * Crear respuesta warning fuera horario test.
     */
    @Test
    public void crearRespuestaWarningFueraHorarioTest() {
        Respuesta<CompraVentaInicioDTO> respuesta = new Respuesta<CompraVentaInicioDTO>();
        respuesta.setItemMensajeRespuesta(new ArrayList<ItemMensajeRespuesta>());
        respuesta.getItemsMensajeRespuesta().add(new ItemMensajeRespuesta());
        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        resMensaje.setRespuesta(new Mensaje());
        resMensaje.getRespuesta().setMensaje("");
        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(new Mensaje());
        Respuesta<CompraVentaDolarView> res = casuistica.crearRespuestaWarningFueraHorario(respuesta, null);
        Assert.assertEquals(EstadoRespuesta.ERROR, res.getEstadoRespuesta());

        res = casuistica.crearRespuestaWarningFueraHorario(respuesta, new ArrayList<CuentasAdhesionDebitoView>());
        Assert.assertEquals(EstadoRespuesta.WARNING, res.getEstadoRespuesta());
    }

    /**
     * Obtener mensaje compra venta test.
     */
    @Test
    public void obtenerMensajeCompraVentaTest() {
        Respuesta<Mensaje> resMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        mensaje.setMensaje("Tipo operacion:{0} , Importe:{1}");
        resMensaje.setRespuesta(mensaje);

        Mockito.when(mensajeManager.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);
        Assert.assertEquals("Tipo operacion:compra , Importe:12", casuistica.obtenerMensajeCompraVenta("12", true));
        Assert.assertEquals("Tipo operacion:venta , Importe:13", casuistica.obtenerMensajeCompraVenta("13", false));
    }
}
