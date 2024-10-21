package ar.com.santanderrio.obp.servicios.pagos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.bo.BusinessException;
import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.RespuestaBO;
import ar.com.santanderrio.obp.servicios.cuentas.bo.impl.RespuestaBOImpl;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.ReporteComprobanteBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.dao.ReporteComprobanteDAO;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class ReporteComprobanteBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReporteComprobanteBOTest {

    /** The reporte comprobante BO. */
    @InjectMocks
    private ReporteComprobanteBOImpl reporteComprobanteBO = new ReporteComprobanteBOImpl();

    /** The reporte comprobante DAO. */
    @Mock
    private ReporteComprobanteDAO reporteComprobanteDAO;

    /** The cuenta BO. */
    @Mock
    private CuentaBO cuentaBO;

    /** The respuesta BO. */
    @InjectMocks
    @Spy
    private RespuestaBO respuestaBO = new RespuestaBOImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /**
     * Builds the report comprobante test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @SuppressWarnings("deprecation")
    @Test
    public void buildReportComprobanteTest() throws DAOException, BusinessException {

        PagoPendientePrestamo pagoPendiente = new PagoPendientePrestamo();

        Prestamo prestamo = new Prestamo();

        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000011");
        cuenta.setNroSucursal("000");
        cuenta.setTipoCuenta("30");
        cuenta.setAlias("Pepe");

        Interviniente interviniente = new Interviniente();
        interviniente.setApellido("Apellido");
        interviniente.setNombre("Nombre");
        interviniente.setCuitcuil("2044444444444");

        ComprobantePrestamo comprobante = new ComprobantePrestamo();
        comprobante.setTea(new BigDecimal(1.1));
        comprobante.setTasaPrestamo(new BigDecimal(1.1));
        comprobante.setCftna(new BigDecimal(1.21));
        comprobante.setCftnaSinImp(new BigDecimal(1.1));
        comprobante.setImporteAmortizacion(new BigDecimal(1000.01));
        comprobante.setImporteIntereses(new BigDecimal(1000.01));
        comprobante.setImpAjusCapmor(new BigDecimal(1000.01));
        comprobante.setImportePunitorios(new BigDecimal(100.01));
        comprobante.setImporteComplementario(new BigDecimal(1000.01));
        comprobante.setImporteSeguroVida(new BigDecimal(1000.01));
        comprobante.setImporteSeguroBien(new BigDecimal(1000.01));
        comprobante.setImporteTotalSeguros(new BigDecimal(5000.01));
        comprobante.setImporteIVA(new BigDecimal(210.05));

        comprobante.setImporteIVAAdicional(new BigDecimal(10.01));
        comprobante.setImpuestoEndFinal(new BigDecimal(210.01));
        comprobante.setIngresosBrutos(new BigDecimal(210.01));
        comprobante.setOtrosImpuestos(new BigDecimal(210.01));
        comprobante.setGastos(new BigDecimal(210.01));
        comprobante.setComisiones(new BigDecimal(210.01));
        comprobante.setDescripcionIVA("responsable inscripto");

        comprobante.setInterviniente(interviniente);
        comprobante.setCuenta(cuenta);
        comprobante.setNio("3232131232");

        prestamo.setNumeroCuentaProducto("0000011");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setCuenta(cuenta);

        pagoPendiente.setPrestamo(prestamo);
        pagoPendiente.setTipoPrestamo("Personal");
        pagoPendiente.setNumeroCuotas("3");
        pagoPendiente.setImporte(new BigDecimal(300));
        pagoPendiente.setVencimiento(new Date());

        when(reporteComprobanteDAO.buildReportComprobante(Matchers.any(ComprobantePrestamoReporte.class)))
                .thenReturn("comprobante".getBytes());

        Respuesta<Reporte> reporte = reporteComprobanteBO.obtenerReporte(pagoPendiente, comprobante);
        assertNotNull(reporte);
        assertEquals(reporte.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

    /**
     * Obtener reporte detalle prestamo test.
     *
     * @throws DAOException
     *             the DAO exception
     * @throws BusinessException
     *             the business exception
     */
    @Test
    public void obtenerReporteDetallePrestamoTest() throws DAOException, BusinessException {
        String SIN_PLAZO = "-";
        ComprobantePrestamoReporte comprobante = new ComprobantePrestamoReporte();
        comprobante.setCuotaPrestamo("1");
        comprobante.setPlazoPrestamo(SIN_PLAZO);

        when(reporteComprobanteDAO.buildReportComprobanteDetalle((Matchers.any(ComprobantePrestamoReporte.class))))
                .thenReturn("comprobante".getBytes());

        Respuesta<Reporte> reporte = reporteComprobanteBO.obtenerReporteDetallePrestamo(comprobante);
        assertNotNull(reporte);
        assertEquals(reporte.getEstadoRespuesta(), EstadoRespuesta.OK);
    }

}
