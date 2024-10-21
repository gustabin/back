package ar.com.santanderrio.obp.servicios.cuentas.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensajes.util.CodigoMensajeConstantes;
import ar.com.santanderrio.obp.servicios.comun.ondemand.dao.OndemandDAO;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.ResumenMensualCuentaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.ondemand.entities.WSODException;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class ResumenMensualCuentaBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ResumenMensualCuentaBOTest {

    /** The resumen mensual cuenta BO. */
    @InjectMocks
    private ResumenMensualCuentaBO resumenMensualCuentaBO = new ResumenMensualCuentaBOImpl();

    /** The ondemand DAO. */
    @Mock
    private OndemandDAO ondemandDAO;

    // @Mock
    // private RespuestaBO respuestaBO;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The respuesta BO. */
    @InjectMocks
    @Spy
    private RespuestaBO respuestaBO = new RespuestaBOImpl();

    /**
     * Obtener resumen descarga multiple OK test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws WSODException
     *             the WSOD exception
     */
    @Test
    public void obtenerResumenDescargaMultipleOKTest() throws BusinessException, WSODException {
        Respuesta<ReporteResumenMensualCuenta> respuesta = new Respuesta<ReporteResumenMensualCuenta>();

        List<ResumenMensualCuenta> resumenesSeleccionados = new ArrayList<ResumenMensualCuenta>();
        ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
        resumenMensualCuenta.setAdvance(true);
        resumenMensualCuenta.setFecha(new Date());
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        String bytes = "bytes";

        segmento.setDuo(true);
        segmento.setPyme(false);
        cliente.setSegmento(segmento);

        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("Mi cuenta");
        cuenta.setCliente(cliente);

        resumenMensualCuenta.setId(new Long(0));

        reporteResumenMensualCuenta.setBytes(bytes.getBytes());
        reporteResumenMensualCuenta.setNombre("Prueba");
        reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);

        resumenesSeleccionados.add(resumenMensualCuenta);

        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(ondemandDAO.obtenerReporteMensualAdvance(resumenMensualCuenta, cuenta))
                .thenReturn(reporteResumenMensualCuenta);
        Mockito.doNothing().when(respuestaBO).armarRespuestaOk(respuesta, reporteResumenMensualCuenta);
        respuesta = resumenMensualCuentaBO.obtenerResumenDescargaMultiple(resumenesSeleccionados, cuenta, 1);
        assertNotNull(respuesta);
    }

    /**
     * Obtener resumen descarga multiple uno error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws WSODException
     *             the WSOD exception
     */
    @Test
    public void obtenerResumenDescargaMultipleUnoErrorTest() throws BusinessException, WSODException {
        Respuesta<ReporteResumenMensualCuenta> respuesta = new Respuesta<ReporteResumenMensualCuenta>();
        List<ResumenMensualCuenta> resumenesSeleccionados = new ArrayList<ResumenMensualCuenta>();
        ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
        resumenMensualCuenta.setFecha(new Date());
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        Respuesta<Mensaje> respMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();

        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        String bytes = "bytes";

        segmento.setDuo(true);
        segmento.setPyme(false);
        cliente.setSegmento(segmento);

        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("Mi cuenta");
        cuenta.setCliente(cliente);

        mensaje.setCodigo("1219");
        mensaje.setMensaje(
                "Ocurrió un error con la descarga de tu resumen. Por favor, volvé a intentarlo en unos minutos.");
        respMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        respMensaje.setRespuesta(mensaje);

        resumenMensualCuenta.setId(new Long(0));

        reporteResumenMensualCuenta.setBytes(bytes.getBytes());
        reporteResumenMensualCuenta.setNombre("Prueba");
        reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);

        resumenesSeleccionados.add(resumenMensualCuenta);
        Mockito.when(ondemandDAO.obtenerReporteMensualAdvance(resumenMensualCuenta, cuenta)).thenReturn(null);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_DESCARGA_MULTIPLE_UNO))
                .thenReturn(mensaje);
        respuesta = resumenMensualCuentaBO.obtenerResumenDescargaMultiple(resumenesSeleccionados, cuenta, 1);
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
                "Ocurrió un error con la descarga de tu resumen. Por favor, volvé a intentarlo en unos minutos.");
    }

    /**
     * Obtener resumen descarga multiple varios error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws WSODException
     *             the WSOD exception
     */
    @Test
    public void obtenerResumenDescargaMultipleVariosErrorTest() throws BusinessException, WSODException {
        Respuesta<ReporteResumenMensualCuenta> respuesta = new Respuesta<ReporteResumenMensualCuenta>();
        List<ResumenMensualCuenta> resumenesSeleccionados = new ArrayList<ResumenMensualCuenta>();
        ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
        resumenMensualCuenta.setFecha(new Date());
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        Respuesta<Mensaje> respMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();

        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        String bytes = "bytes";

        segmento.setDuo(true);
        segmento.setPyme(false);
        cliente.setSegmento(segmento);

        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("Mi cuenta");
        cuenta.setCliente(cliente);

        mensaje.setCodigo("1220");
        mensaje.setMensaje(
                "Ocurrió un error y alguno de los resúmenes no se pudieron descargar correctamente. Por favor, intentalo de nuevo en unos minutos.");
        respMensaje.setEstadoRespuesta(EstadoRespuesta.OK);
        respMensaje.setRespuesta(mensaje);

        resumenMensualCuenta.setId(new Long(0));

        reporteResumenMensualCuenta.setBytes(bytes.getBytes());
        reporteResumenMensualCuenta.setNombre("Prueba");
        reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);

        resumenesSeleccionados.add(resumenMensualCuenta);
        Mockito.when(ondemandDAO.obtenerReporteMensualAdvance(resumenMensualCuenta, cuenta)).thenReturn(null);
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.CODIGO_ERROR_DESCARGA_MULTIPLE_VARIOS))
                .thenReturn(mensaje);
        respuesta = resumenMensualCuentaBO.obtenerResumenDescargaMultiple(resumenesSeleccionados, cuenta, 2);
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getMensaje(),
                "Ocurrió un error y alguno de los resúmenes no se pudieron descargar correctamente. Por favor, intentalo de nuevo en unos minutos.");
    }

    /**
     * Obtener resumen descarga simple test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws WSODException
     *             the WSOD exception
     */
    @Test
    public void obtenerResumenDescargaSimpleTest() throws BusinessException, WSODException {
        Respuesta<ReporteResumenMensualCuenta> respuesta = new Respuesta<ReporteResumenMensualCuenta>();

        List<ResumenMensualCuenta> resumenesSeleccionados = new ArrayList<ResumenMensualCuenta>();
        ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
        resumenMensualCuenta.setFecha(new Date());
        resumenMensualCuenta.setAdvance(true);
        Cliente cliente = new Cliente();
        Segmento segmento = new Segmento();
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();
        String bytes = "bytes";

        segmento.setDuo(true);
        segmento.setPyme(false);
        cliente.setSegmento(segmento);

        Cuenta cuenta = new Cuenta();
        cuenta.setAlias("Mi cuenta");
        cuenta.setCliente(cliente);

        resumenMensualCuenta.setId(new Long(0));

        reporteResumenMensualCuenta.setBytes(bytes.getBytes());
        reporteResumenMensualCuenta.setNombre("Descarga Simple");
        reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);

        resumenesSeleccionados.add(resumenMensualCuenta);

        respuesta.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(ondemandDAO.obtenerReporteMensualAdvance(resumenMensualCuenta, cuenta))
                .thenReturn(reporteResumenMensualCuenta);
        // Mockito.doNothing().when(respuestaBO).armarRespuestaOk(respuesta,
        // reporteResumenMensualCuenta);
        respuesta = resumenMensualCuentaBO.obtenerResumenesPDF(resumenesSeleccionados, cuenta);
        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertEquals(respuesta.getRespuesta().getNombre(), "Descarga Simple");
        assertEquals(respuesta.getRespuesta().getTipoArchivo(), TipoArchivoEnum.PDF);
    }
    
    
    @Test
    public void obtenerMensajeErrorPorCantidadFallidosUnResumenTest(){
        Mensaje mensaje = new  Mensaje();
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_RESUMEN_UNICA_DESCARGA)).thenReturn(mensaje);
        ItemMensajeRespuesta res = resumenMensualCuentaBO.obtenerMensajeErrorPorCantidadFallidos(1);
        Assert.assertEquals(TipoError.ERROR_DESCARGA_SINGULAR.getDescripcion(), res.getTipoError());
    }
    
    @Test
    public void obtenerMensajeErrorPorCantidadFallidosVariosResumenesTest(){
        Mensaje mensaje = new  Mensaje();
        Mockito.when(mensajeDAO.obtenerMensajePorCodigo(CodigoMensajeConstantes.ERROR_RESUMEN_VARIAS_DESCARGAS)).thenReturn(mensaje);
        ItemMensajeRespuesta res = resumenMensualCuentaBO.obtenerMensajeErrorPorCantidadFallidos(3);
        Assert.assertEquals(TipoError.ERROR_DESCARGA_MULTIPLE.getDescripcion(), res.getTipoError());
    }
}
