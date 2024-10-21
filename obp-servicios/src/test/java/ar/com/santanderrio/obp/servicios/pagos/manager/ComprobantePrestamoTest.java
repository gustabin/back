package ar.com.santanderrio.obp.servicios.pagos.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.clientes.entities.TipoIdentificacion;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.service.RespuestaService;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoManagerImpl;

/**
 * The Class ComprobantePrestamoTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ComprobantePrestamoTest {

    /** The prestamo manager. */
    @InjectMocks
    private PrestamoManagerImpl prestamoManager = new PrestamoManagerImpl();

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The respuesta service. */
    @Mock
    private RespuestaService respuestaService;

    /** The prestamo service. */
    @Mock
    private PrestamoService prestamoService;

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    @Mock
    private LegalBO legalBO;

    /**
     * Test detalle prestamos.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Test
    public void testDetallePrestamos() throws ServiceException {
        // When
        PagoPendientePrestamo pagoPendiente = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroCuentaProducto("0000000000000000000000000011");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PRENDARIO);
        prestamo.setNumeroRecibo("0000000000000001111");
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setSaldoPrevio(new BigDecimal(10));
        prestamo.setIndex("1");
        prestamo.setFactorIndex("10");
        prestamo.setImporteAjussal(new BigDecimal("10"));
        prestamo.setVencimientoCuota(new Date());
        prestamo.setImporteTotalCuota(new BigDecimal("100"));
        prestamo.setPlazo("-");

        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();

        comprobantePrestamo.setImporteAmortizacion(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteIntereses(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImpAjusCapmor(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImportePunitorios(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteComplementario(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteSeguroVida(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteSeguroBien(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteTotalSeguros(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteIVA(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteIVAAdicional(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImpuestoEndFinal(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setIngresosBrutos(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setOtrosImpuestos(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setGastos(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setComisiones(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setDescripcionIVA("IMPUESTO VALOR AGREGADO");
        comprobantePrestamo.setImporteDebito(new BigDecimal("10000000000001234"));
        comprobantePrestamo.setImporteTotalRecibo(new BigDecimal("10.00"));

        comprobantePrestamo.setTea(new BigDecimal("100000000"));
        comprobantePrestamo.setTasaPrestamo(new BigDecimal("100000000"));
        comprobantePrestamo.setCftna(new BigDecimal("100000000"));
        comprobantePrestamo.setCftnaSinImp(new BigDecimal("100000000"));
        comprobantePrestamo.setVencimientoRecibo("2016-08-08");
        comprobantePrestamo.setNio("5678");
        comprobantePrestamo.setSaldoCapital(new BigDecimal("10000000000001234"));

        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        pagoPrestamo.setComprobantePrestamo(comprobantePrestamo);
        pagoPrestamo.setPagoPendientePrestamo(pagoPendiente);

        Interviniente interviniente = new Interviniente();
        interviniente.setNombre("DIEGO");
        interviniente.setApellido("GONZALES");
        interviniente.setTipoInscripcion(TipoIdentificacion.CUIT.getKey());
        interviniente.setCuitcuil("20-93901222-3");
        comprobantePrestamo.setInterviniente(interviniente);

        Cuenta cuenta = new Cuenta();
        cuenta.setNroSucursal("0000");
        cuenta.setTipoCuenta("2");
        cuenta.setNroCuentaProducto("0000000000000000000000000011");
        comprobantePrestamo.setCuenta(cuenta);
        prestamo.setCuenta(cuenta);

        pagoPendiente.setPrestamo(prestamo);
        List<PagoPendiente> listaPagoPendiente = new ArrayList<PagoPendiente>();
        listaPagoPendiente.add(pagoPendiente);
        Respuesta<List<PagoPendiente>> respuestaPagoPendiente = new Respuesta<List<PagoPendiente>>();
        respuestaPagoPendiente.setRespuesta(listaPagoPendiente);

        Mockito.when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        Mockito.when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaPagoPendiente);

        // Then
        Respuesta<ComprobanteView> respuesta = prestamoManager.obtenerComprobante("1234");
        // Expected
        assertNotNull(respuesta);
        assertEquals(EstadoRespuesta.OK, respuesta.getEstadoRespuesta());
        assertEquals(respuesta.getRespuesta().getNroComprobante(), "5678");
        assertEquals(respuesta.getRespuesta().getTipoInscripcion(), TipoIdentificacion.CUIT.getDescripcion());
        assertEquals(respuesta.getRespuesta().getCondicionIVA(), "Impuesto valor agregado");
        assertEquals(respuesta.getRespuesta().getCuotaPrestamo(), "1111");
        assertEquals(respuesta.getRespuesta().getFechaDeVencimiento(), "08/08/2016");
        assertNotNull(respuesta.getRespuesta().getImportes());
        assertEquals(respuesta.getRespuesta().getNroComprobante(), "5678");
        assertEquals(respuesta.getRespuesta().getNroCuenta(), "000-000001/1");
        assertEquals(respuesta.getRespuesta().getPlazoPrestamo(), "-");
        assertEquals(respuesta.getRespuesta().getTipoCuenta(), "Cuenta única");
        assertEquals(respuesta.getRespuesta().getTipoPrestamo(), "PRENDARIO");
        assertEquals(respuesta.getRespuesta().getTipoPrestamoLabel(), "Préstamo Prendario");
        assertEquals(respuesta.getRespuesta().getTitular(), "Diego gonzales");
        assertEquals(respuesta.getRespuesta().getImporteValue(), "10,00");

    }

}
