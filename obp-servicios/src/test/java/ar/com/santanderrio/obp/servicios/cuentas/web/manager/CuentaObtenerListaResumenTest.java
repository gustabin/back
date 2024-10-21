package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiEntity;
import ar.com.santanderrio.obp.servicios.citi.entities.CuentaCitiMigrada;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.MensajeBO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.ResumenCuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenMesualCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ResumenesMensualesCuentaView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class CuentaObtenerListaResumenTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentaObtenerListaResumenTest {

    /** The cuenta manager. */
    @Mock
    private CuentaManagerImpl cuentaManager = new CuentaManagerImpl();

    /** The resumen cuenta manager. */
    @InjectMocks
    private ResumenCuentaManager resumenCuentaManager = new ResumenCuentaManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The sesion cliente. */
    @Mock
    private SesionParametros sesionParametros;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The sesion resumen cuenta. */
    @Mock
    private SessionResumenCuenta sesionResumenCuenta;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The respuesta factory. */
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The resumen mensual cuenta BO. */
    @Mock
    private ResumenMensualCuentaBO resumenMensualCuentaBO;

    /** The mensaje BO. */
    @Mock
    private MensajeBO mensajeBO;

    /**
     * Mockear get cliente.
     *
     * @param esExCiti
     *            the es ex citi
     */
    private void mockearGetCliente(Boolean esExCiti) {
        Cliente cliente = new Cliente();
        cliente.setIsExCiti(esExCiti);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
    }

    /**
     * Mockear resp marca.
     *
     * @param cuenta
     *            the cuenta
     */
    private void mockearRespMarca(AbstractCuenta cuenta) {
        Respuesta<Boolean> respMarca = new Respuesta<Boolean>();
        respMarca.setRespuesta(Boolean.TRUE);
        respMarca.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(resumenMensualCuentaBO.obtenerMarcaResumenOnline(cuenta))
                .thenReturn(respMarca);
    }

    /**
     * Mockear respuesta.
     *
     * @param respuesta
     *            the respuesta
     */
    private void mockearRespuesta(
            Respuesta<List<ResumenMensualCuenta>> respuesta) {
        when(sesionResumenCuenta
                .getResumenesByCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuesta);
    }

    /**
     * Mockear obtener lista resumen.
     *
     * @param respuesta
     *            the respuesta
     * @throws BusinessException
     *             the business exception
     */
    private void mockearObtenerListaResumen(
            Respuesta<List<ResumenMensualCuenta>> respuesta)
            throws BusinessException {
        when(resumenMensualCuentaBO
                .obtenerListaResumen(Matchers.any(Cuenta.class)))
                        .thenReturn(respuesta);
    }

    /**
     * Mockear obtener mensaje por codigo.
     */
    private void mockearObtenerMensajePorCodigo() {
        Mensaje mensaje = new Mensaje();
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(mensaje);
    }

    /**
     * Mockear obtener mensaje informativo descarga resumenes.
     */
    private void mockearObtenerMensajeInformativoDescargaResumenes() {
        String mensajeInformativo = "Al evitar el uso de papel, contribuimos al cuidado del planeta";
        Mockito.when(resumenMensualCuentaBO
                .obtenerMensajeInformativoDescargaResumenes())
                .thenReturn(mensajeInformativo);
    }

    /**
     * Mockear obtener cuenta by id.
     *
     * @param cuenta
     *            the cuenta
     */
    private void mockearObtenerCuentaById(AbstractCuenta cuenta) {
        when(cuentaManager.obtenerCuentaById(Matchers.anyString()))
                .thenReturn(cuenta);
    }

    /**
     * Mockear consultar fechas por cuenta.
     *
     * @param estadoRespuesta
     *            the estado respuesta
     */
    private void mockearConsultarFechasPorCuenta(
            EstadoRespuesta estadoRespuesta) {
        Respuesta<List<ResumenMensualCuenta>> resumenesCiti = new Respuesta<List<ResumenMensualCuenta>>();
        resumenesCiti.setEstadoRespuesta(estadoRespuesta);
        List<ResumenMensualCuenta> cuentas = new ArrayList<ResumenMensualCuenta>();
        resumenesCiti.setRespuesta(cuentas);
        Mockito.when(resumenMensualCuentaBO
                .consultarFechasPorCuenta(Matchers.anyString()))
                .thenReturn(resumenesCiti);
    }

    /**
     * Mockear obtener cuenta citi.
     *
     * @param estadoRespuesta
     *            the estado respuesta
     */
    private void mockearObtenerCuentaCiti(EstadoRespuesta estadoRespuesta) {
        Respuesta<CuentaCitiMigrada> respCuentaMigrada = new Respuesta<CuentaCitiMigrada>();
        respCuentaMigrada.setEstadoRespuesta(estadoRespuesta);
        List<CuentaCitiEntity> citiEntitiesList = new ArrayList<CuentaCitiEntity>();
        citiEntitiesList.add(new CuentaCitiEntity());
        respCuentaMigrada.setRespuesta(new CuentaCitiMigrada());
        respCuentaMigrada.getRespuesta().getCuentasCiti()
                .add(new CuentaCitiEntity());
        Mockito.when(cuentaBO.obtenerCuentaCiti(Matchers.any(AbstractCuenta.class),Matchers.any(Cliente.class))).thenReturn(respCuentaMigrada);
    }

    /**
     * Mockear get resumenes by cuenta.
     *
     * @param cuenta
     *            the cuenta
     * @throws ParseException
     *             the parse exception
     */
    private void mockearGetResumenesByCuenta(AbstractCuenta cuenta)
            throws ParseException {
        Respuesta<List<ResumenMensualCuenta>> respuestaListaResumenMensual = new Respuesta<List<ResumenMensualCuenta>>();
        List<ResumenMensualCuenta> listaResumenes = new ArrayList<ResumenMensualCuenta>();
        ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String stringFecha = "10-10-2016";
        Date fecha = sdf.parse(stringFecha);
        resumenMensualCuenta.setFecha(fecha);
        resumenMensualCuenta.setId(new Long(1));
        resumenMensualCuenta.setVisto(false);
        listaResumenes.add(resumenMensualCuenta);
        respuestaListaResumenMensual.setRespuesta(listaResumenes);
        respuestaListaResumenMensual.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(sesionResumenCuenta.getResumenesByCuenta(cuenta))
                .thenReturn(respuestaListaResumenMensual);
    }

    /**
     * Gets the cuenta.
     *
     * @return the cuenta
     */
    private AbstractCuenta getCuenta() {
        AbstractCuenta cuenta = new Cuenta();
        cuenta.setAlias("Alias");
        cuenta.setNroCuentaProducto("1234567");
        cuenta.setNroSucursal("123");
        cuenta.setTraspasoAutomaticoActivo(Boolean.FALSE);
        cuenta.setTipoCuentaEnum(TipoCuenta.CUENTA_UNICA);
        cuenta.setSolicitudPendienteTraspasoAutomatico(Boolean.TRUE);
        cuenta.setTipoCuenta("01");
        return cuenta;
    }

    /**
     * Obtener lista resumen test.
     *
     * @throws BusinessException
     *             the service exception
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void obtenerListaResumenTest()
            throws BusinessException, ParseException {

        AbstractCuenta cuenta = getCuenta();
        when(cuentaManager.obtenerCuentaById(Matchers.anyString()))
                .thenReturn(cuenta);

        Cliente cliente = new Cliente();
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);
        String mensajeInformativo = "Al evitar el uso de papel, contribuimos al cuidado del planeta";
        Mockito.when(resumenMensualCuentaBO
                .obtenerMensajeInformativoDescargaResumenes())
                .thenReturn(mensajeInformativo);
        Respuesta<List<ResumenMensualCuenta>> respuestaListaResumenMensual = new Respuesta<List<ResumenMensualCuenta>>();
        List<ResumenMensualCuenta> listaResumenes = new ArrayList<ResumenMensualCuenta>();
        ResumenMensualCuenta resumenMensualCuenta = new ResumenMensualCuenta();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String stringFecha = "10-10-2016";
        Date fecha = sdf.parse(stringFecha);
        resumenMensualCuenta.setFecha(fecha);
        resumenMensualCuenta.setId(new Long(1));
        resumenMensualCuenta.setVisto(false);
        listaResumenes.add(resumenMensualCuenta);
        respuestaListaResumenMensual.setRespuesta(listaResumenes);
        respuestaListaResumenMensual.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(sesionResumenCuenta.getResumenesByCuenta(cuenta))
                .thenReturn(respuestaListaResumenMensual);

        Respuesta<Boolean> respMarca = new Respuesta<Boolean>();
        respMarca.setRespuesta(Boolean.TRUE);
        respMarca.setEstadoRespuesta(EstadoRespuesta.OK);
        Mockito.when(resumenMensualCuentaBO.obtenerMarcaResumenOnline(cuenta))
                .thenReturn(respMarca);

        Respuesta<ResumenesMensualesCuentaView> respuestaListaResumen = resumenCuentaManager
                .obtenerListaResumen("000-111111/2");
        ResumenMesualCuentaView resumenMesualCuentaView = respuestaListaResumen
                .getRespuesta().getResumenes().get(0);
        assertNotNull(respuestaListaResumen.getRespuesta());
        assertEquals(respuestaListaResumen.getRespuesta().getMensaje(),
                "Al evitar el uso de papel, contribuimos al cuidado del planeta");
        assertEquals(respuestaListaResumen.getRespuesta().getNumeroCuenta(),
                "123-123456/7");
        assertEquals(respuestaListaResumen.getRespuesta().getTipoCuenta(),
                "Caja de ahorro en $");
        assertNotNull(resumenMesualCuentaView);
        assertEquals(resumenMesualCuentaView.getVisto(), false);
        assertEquals(resumenMesualCuentaView.getAnio(), "2016");
        assertEquals(resumenMesualCuentaView.getDia(), "10");
        assertEquals(resumenMesualCuentaView.getMes(), "Octubre");

    }

    /**
     * Obtener lista resumen null test.
     *
     * @throws BusinessException
     *             the service exception
     */
    @Test
    public void obtenerListaResumenNullTest() throws BusinessException {

        Respuesta<List<ResumenMensualCuenta>> respuestaListResumenMensualCuenta = null;
        Respuesta<List<ResumenMensualCuenta>> respuestaListResumenMensualCuenta2 = new Respuesta<List<ResumenMensualCuenta>>();
        AbstractCuenta cuenta = getCuenta();
        respuestaListResumenMensualCuenta2
                .setEstadoRespuesta(EstadoRespuesta.WARNING);

        when(sesionResumenCuenta
                .getResumenesByCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaListResumenMensualCuenta);
        when(resumenMensualCuentaBO
                .obtenerListaResumen(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaListResumenMensualCuenta2);

        mockearGetCliente(Boolean.FALSE);
        mockearRespMarca(cuenta);

        when(cuentaManager.obtenerCuentaById(Matchers.anyString()))
                .thenReturn(cuenta);

        Respuesta<ResumenesMensualesCuentaView> respuesta = resumenCuentaManager
                .obtenerListaResumen("123-123456/7");

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.WARNING, respuesta.getEstadoRespuesta());
        assertTrue(respuesta.isRespuestaVacia());
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VER_RESUMENES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

    /**
     * Obtener lista resumen test.
     *
     * @throws BusinessException
     *             the service exception
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void generarEstadisticasErrorResumenExCitiTest()
            throws BusinessException, ParseException {

        AbstractCuenta cuenta = getCuenta();
        mockearObtenerCuentaById(cuenta);

        mockearGetCliente(true);
        mockearObtenerMensajeInformativoDescargaResumenes();
        mockearGetResumenesByCuenta(cuenta);
        mockearRespMarca(cuenta);

        Respuesta<List<ResumenMensualCuenta>> respuesta = new Respuesta<List<ResumenMensualCuenta>>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

        mockearRespuesta(respuesta);
        mockearObtenerListaResumen(respuesta);
        mockearObtenerCuentaCiti(EstadoRespuesta.OK);
        mockearConsultarFechasPorCuenta(EstadoRespuesta.ERROR);
        mockearObtenerMensajePorCodigo();

        Respuesta<ResumenesMensualesCuentaView> respuestaListaResumen = resumenCuentaManager
                .obtenerListaResumen("000-111111/2");
        assertNotNull(respuestaListaResumen);
    }

    /**
     * Generar estadisticas OK resumen ex citi test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void generarEstadisticasOKResumenExCitiTest()
            throws BusinessException, ParseException {

        AbstractCuenta cuenta = getCuenta();
        mockearObtenerCuentaById(cuenta);
        mockearGetCliente(true);
        mockearObtenerMensajeInformativoDescargaResumenes();
        mockearGetResumenesByCuenta(cuenta);
        mockearRespMarca(cuenta);

        Respuesta<List<ResumenMensualCuenta>> respuesta = new Respuesta<List<ResumenMensualCuenta>>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        mockearRespuesta(respuesta);
        mockearObtenerListaResumen(respuesta);
        mockearObtenerCuentaCiti(EstadoRespuesta.OK);
        mockearConsultarFechasPorCuenta(EstadoRespuesta.OK);
        mockearObtenerMensajePorCodigo();

        Respuesta<ResumenesMensualesCuentaView> respuestaListaResumen = resumenCuentaManager
                .obtenerListaResumen("000-111111/2");
        assertNotNull(respuestaListaResumen);
    }

    /**
     * Obtener cuenta citi error test.
     *
     * @throws BusinessException
     *             the business exception
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void obtenerCuentaCitiErrorTest()
            throws BusinessException, ParseException {

        AbstractCuenta cuenta = getCuenta();

        mockearObtenerCuentaById(cuenta);
        mockearGetCliente(true);
        mockearObtenerMensajeInformativoDescargaResumenes();
        mockearGetResumenesByCuenta(cuenta);

        Respuesta<List<ResumenMensualCuenta>> respuesta = new Respuesta<List<ResumenMensualCuenta>>();
        respuesta.setEstadoRespuesta(EstadoRespuesta.ERROR);

        mockearRespuesta(respuesta);
        mockearObtenerListaResumen(respuesta);
        mockearObtenerCuentaCiti(EstadoRespuesta.ERROR);
        mockearRespMarca(cuenta);
        mockearConsultarFechasPorCuenta(EstadoRespuesta.OK);
        mockearObtenerMensajePorCodigo();

        Respuesta<ResumenesMensualesCuentaView> respuestaListaResumen = resumenCuentaManager
                .obtenerListaResumen("000-111111/2");
        assertNotNull(respuestaListaResumen);
    }

    /**
     * Obtener lista resumen error test.
     *
     * @throws BusinessException
     *             the service exception
     */
    @Test
    public void obtenerListaResumenErrorTest() throws BusinessException {

        AbstractCuenta cuenta = getCuenta();

        Cliente cliente = new Cliente();
        cliente.setIsExCiti(Boolean.FALSE);
        Mockito.when(sesionCliente.getCliente()).thenReturn(cliente);

        Respuesta<List<ResumenMensualCuenta>> respuestaListResumenMensualCuenta = null;
        Respuesta<List<ResumenMensualCuenta>> respuestaListResumenMensualCuenta2 = new Respuesta<List<ResumenMensualCuenta>>();

        respuestaListResumenMensualCuenta2
                .setEstadoRespuesta(EstadoRespuesta.ERROR);

        when(sesionResumenCuenta
                .getResumenesByCuenta(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaListResumenMensualCuenta);
        when(resumenMensualCuentaBO
                .obtenerListaResumen(Matchers.any(Cuenta.class)))
                        .thenReturn(respuestaListResumenMensualCuenta2);

        when(cuentaManager.obtenerCuentaById(Matchers.anyString()))
                .thenReturn(cuenta);

        Respuesta<ResumenesMensualesCuentaView> respuesta = resumenCuentaManager
                .obtenerListaResumen("123-123456/7");

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertTrue(respuesta.isRespuestaVacia());
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VER_RESUMENES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

    }

    /**
     * Obtener lista resumen exception test.
     *
     * @throws BusinessException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void obtenerListaResumenExceptionTest() throws BusinessException {

        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Mensaje mensaje = new Mensaje();
        AbstractCuenta cuenta = getCuenta();
        when(cuentaManager.obtenerCuentaById(Matchers.anyString()))
                .thenReturn(cuenta);

        mensaje.setMensaje("Error, Error!");
        respuestaMensaje.setRespuesta(mensaje);

        when(resumenMensualCuentaBO
                .obtenerListaResumen(Matchers.any(Cuenta.class)))
                        .thenThrow(BusinessException.class);
        when(mensajeBO.obtenerMensajePorCodigo(Matchers.anyString()))
                .thenReturn(mensaje);

        Respuesta<ResumenesMensualesCuentaView> respuesta = resumenCuentaManager
                .obtenerListaResumen("123-123456/7");

        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.ERROR, respuesta.getEstadoRespuesta());
        assertEquals("Error, Error!",
                respuesta.getItemsMensajeRespuesta().get(0).getMensaje());
        verify(estadisticaManager).add(
                EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_VER_RESUMENES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);
    }
}
