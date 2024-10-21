package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.reporte.entities.TipoArchivoEnum;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.ResumenMensualCuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.CuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl.ResumenCuentaManagerImpl;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ConsultaCuentaView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.resumen.entities.ReporteResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;

/**
 * The Class CuentasObtenerResumenesPDFTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CuentasObtenerResumenesPDFTest {

    /** The cuenta manager. */
    @InjectMocks
    private ResumenCuentaManager resumenCuentaManager = new ResumenCuentaManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The cuentas service. */
    @Mock
    private CuentaBO cuentaBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion resumen cuenta. */
    @Mock
    private SessionResumenCuenta sesionResumenCuenta;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager = new CuentaManagerImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;
    
    @Mock
    private ResumenMensualCuentaBO resumenMensualCuentaBO;
    
//    /** The respuesta Factory. */
//    @Mock
//    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Obtener resumenes PDF test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerResumenesPDFTest() throws ServiceException {

        ConsultaCuentaView consultaCuentaView = new ConsultaCuentaView();
        List<ResumenMensualCuenta> listResumenesMensualCuenta = new ArrayList<ResumenMensualCuenta>();
        Respuesta<ReporteResumenMensualCuenta> respuestaReporteMensualCuenta = new Respuesta<ReporteResumenMensualCuenta>();
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();

        reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaReporteMensualCuenta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaReporteMensualCuenta.setRespuesta(reporteResumenMensualCuenta);
        consultaCuentaView.setNumeroCuenta("123-123456/7");
        consultaCuentaView.setFechas(new String[] { "25/12/2015", "07/10/2016", "10/09/2016" });

        when(sesionResumenCuenta.getResumenesByIds(Matchers.anyList(), Matchers.any(Cuenta.class)))
                .thenReturn(listResumenesMensualCuenta);
        when(resumenMensualCuentaBO.obtenerResumenesPDF(Matchers.anyList(), Matchers.any(Cuenta.class)))
                .thenReturn(respuestaReporteMensualCuenta);

        Respuesta<ReporteView> respuesta = resumenCuentaManager.obtenerResumenesPDF(consultaCuentaView);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

        ReporteView reporteView = respuesta.getRespuesta();
        assertNotNull(reporteView);
        assertEquals(respuesta.getRespuesta().getTipoArchivo(), "application/pdf");

        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGAR_RESUMENES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

    /**
     * Obtener resumenes PDF error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @SuppressWarnings("unchecked")
	@Test
    public void obtenerResumenesPDFErrorTest() throws ServiceException {

        ConsultaCuentaView consultaCuentaView = new ConsultaCuentaView();
        List<ResumenMensualCuenta> listResumenesMensualCuenta = new ArrayList<ResumenMensualCuenta>();
        Respuesta<ReporteResumenMensualCuenta> respuestaReporteMensualCuenta = new Respuesta<ReporteResumenMensualCuenta>();
        ReporteResumenMensualCuenta reporteResumenMensualCuenta = new ReporteResumenMensualCuenta();

        reporteResumenMensualCuenta.setTipoArchivo(TipoArchivoEnum.PDF);
        respuestaReporteMensualCuenta.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaReporteMensualCuenta.setRespuesta(reporteResumenMensualCuenta);
        consultaCuentaView.setNumeroCuenta("123-123456/7");
        consultaCuentaView.setFechas(new String[] { "25/12/2015", "07/10/2016", "10/09/2016" });

        when(sesionResumenCuenta.getResumenesByIds(Matchers.anyList(), Matchers.any(Cuenta.class)))
                .thenReturn(listResumenesMensualCuenta);
        when(resumenMensualCuentaBO.obtenerResumenesPDF(Matchers.anyList(), Matchers.any(Cuenta.class)))
                .thenReturn(respuestaReporteMensualCuenta);

        Respuesta<ReporteView> respuesta = resumenCuentaManager.obtenerResumenesPDF(consultaCuentaView);

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.ERROR);

        ReporteView reporteView = respuesta.getRespuesta();
        assertNotNull(reporteView);
        assertEquals(respuesta.getRespuesta().getTipoArchivo(), "application/pdf");

        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TRANSACCION_ESTADISTICAS_DESCARGAR_RESUMENES,
                EstadisticasConstants.CODIGO_ESTADISTICAS_ERROR);

    }

}
