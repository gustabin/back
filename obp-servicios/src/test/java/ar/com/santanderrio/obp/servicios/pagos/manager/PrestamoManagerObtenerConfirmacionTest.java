package ar.com.santanderrio.obp.servicios.pagos.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.base.service.ServiceException;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.bo.LegalBO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.CuentaManager;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasAdhesionDebitoView;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.CuentasView;
import ar.com.santanderrio.obp.servicios.pagos.entities.CuentaPrestamoDebitoAdherido;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendiente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.PreFormalizacion;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;
import ar.com.santanderrio.obp.servicios.pagos.service.PreFormalizacionPrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.service.PrestamoService;
import ar.com.santanderrio.obp.servicios.pagos.web.manager.impl.SessionPagos;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConfirmacionPagoPrestamoView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ConsultaPrestamo;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ImporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.TasaView;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.impl.PrestamoManagerImpl;

/**
 * The Class PrestamoManagerObtenerConfirmacionTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoManagerObtenerConfirmacionTest {

    /** The prestamo manager. */
    @InjectMocks
    private PrestamoManagerImpl prestamoManager = new PrestamoManagerImpl();

    /** The session parametros. */
    @Mock
    private SesionParametros sessionParametros;

    /** The session pagos. */
    @Mock
    private SessionPagos sessionPagos;

    /** The cuenta manager. */
    @Mock
    private CuentaManager cuentaManager;

    /** The pre formalizacion prestamo service. */
    @Mock
    private PreFormalizacionPrestamoService preFormalizacionPrestamoService;

    /** The prestamo service. */
    @Mock
    private PrestamoService prestamoService;

    /** The legal BO. */
    @Mock
    private LegalBO legalBO;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /**
     * Obtener confirmacion test.
     *
     * @throws ServiceException
     *             the service exception
     */
    @Ignore
    @Test
    public void obtenerConfirmacionTest() throws ServiceException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        List<PagoPendiente> listaPagoPendiente = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView = new CuentasAdhesionDebitoView();
        Respuesta<PreFormalizacion> respuestaPreformalizacion = new Respuesta<PreFormalizacion>();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();

        consultaPrestamo.setIdProceso("123456");
        consultaPrestamo.setNumeroPrestamo("200688");
        pagoPrestamo.setIdProceso("123456");

        cuenta.setNroSucursal("123");
        cuenta.setAlias("Soy Alias");
        cuenta.setTipoCuenta("1");

        prestamo.setNumeroCuentaProducto("200688");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setClaseCuenta("1");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("1234");
        prestamo.setDivisa(DivisaEnum.PESO);
        prestamo.setSaldoPrevio(new BigDecimal("500"));
        prestamo.setImporteAjussal(new BigDecimal("250"));
        prestamo.setIndex("");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(1000));
        prestamo.setImporteAmortizacion(new BigDecimal("400"));
        prestamo.setFactorIndex("000000000");
        prestamo.setNroExp("3");
        prestamo.setCotizCambio(new BigDecimal("90"));
        prestamo.setAjusteSaldo(new BigDecimal("1500"));
        prestamo.setCapitalPend(new BigDecimal("1000"));
        prestamo.setInteresesPend(new BigDecimal("4000"));

        prestamoDebitoAdherido.setNroSucursal("123");
        prestamoDebitoAdherido.setNumero("200688");
        preFormalizacion.setPlazo("-");

        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);

        pagoPendientePrestamo.setPreFormalizacion(preFormalizacion);
        pagoPendientePrestamo.setPrestamo(prestamo);

        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);

        listaPagoPendiente.add(pagoPendientePrestamo);
        listCuentasView.add(cuentaView);
        cuentasView.setCuentas(listCuentasView);
        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaCuentasView.setRespuesta(cuentasView);
        respuestaPreformalizacion.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPreformalizacion.setRespuesta(preFormalizacion);

        when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respuestaPreformalizacion);
        when(cuentaManager.getCuenta(Matchers.anyString())).thenReturn(respuestaCuentasView);
        when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        Respuesta<ConfirmacionPagoPrestamoView> respuestaConfirmacionPagoPrestamoView = prestamoManager
                .obtenerConfirmacion(consultaPrestamo);

        assertNotNull(respuestaConfirmacionPagoPrestamoView);
        ConfirmacionPagoPrestamoView confirmacionPagoPrestamoView = respuestaConfirmacionPagoPrestamoView
                .getRespuesta();
        assertEquals(confirmacionPagoPrestamoView.getTipoPrestamoLabel(), "1");
        assertEquals(confirmacionPagoPrestamoView.getTipoPrestamo(), TipoPrestamoEnum.PERSONAL.name());
        assertEquals(confirmacionPagoPrestamoView.getNumeroPrestamo(), "123-00000020068/8");
        assertEquals(confirmacionPagoPrestamoView.getNumeroCuenta(), "123-020068/8");
        assertEquals(confirmacionPagoPrestamoView.getPlazoPrestamo(), "-");
        assertEquals(confirmacionPagoPrestamoView.getAliasCuenta(), "Soy Alias");
        assertEquals(confirmacionPagoPrestamoView.getTipoCuenta(),
                TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda());
        assertEquals(confirmacionPagoPrestamoView.getCuotaPrestamo(), "1234");
        assertEquals(confirmacionPagoPrestamoView.getDivisa(), DivisaEnum.PESO.getSimbolo());
        assertEquals(confirmacionPagoPrestamoView.getSaldoAnteriorCapitalSinAjustar(), "500,00");
        assertEquals(confirmacionPagoPrestamoView.isShowCoeficienteIndexacionReciboEnTermino(), Boolean.FALSE);
        assertEquals(confirmacionPagoPrestamoView.getCapitalAjustodoSobreLaCuota(), "750,00");
        assertEquals(confirmacionPagoPrestamoView.isShowCapitalAjustodoSobreLaCuota(), Boolean.TRUE);
        assertEquals(confirmacionPagoPrestamoView.getFechaDeVencimiento(), "20/10/2017");
        assertEquals(confirmacionPagoPrestamoView.getImporteValue(), "1.000,00");

    }

    // Se testea lo mismo pero con algunas variantes:
    // La Respuesta de la cuenta da Error, y el manager debería armar una cuenta
    // nueva que se guarda en CuentaDebito
    // La PreFormalizacion falla, por lo que no hay numero de cuenta en la
    // respuesta, y el plazo es SIN_PLAZO
    /**
     * Obtener confirmacion cuenta error test.
     *
     * @throws ServiceException
     *             the service exception
     */
    // Tipo de Prestamo Dolar y se verifica ShowIndex y CoeficienteIndex
    @Ignore
    @Test
    public void obtenerConfirmacionCuentaErrorTest() throws ServiceException {

        ConsultaPrestamo consultaPrestamo = new ConsultaPrestamo();
        PagoPrestamo pagoPrestamo = new PagoPrestamo();
        PreFormalizacion preFormalizacion = new PreFormalizacion();
        Respuesta<List<PagoPendiente>> respuestaListaPagoPendiente = new Respuesta<List<PagoPendiente>>();
        List<PagoPendiente> listaPagoPendiente = new ArrayList<PagoPendiente>();
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        Prestamo prestamo = new Prestamo();
        Cuenta cuenta = new Cuenta();
        Respuesta<CuentasView> respuestaCuentasView = new Respuesta<CuentasView>();
        CuentasView cuentasView = new CuentasView();
        List<CuentasAdhesionDebitoView> listCuentasView = new ArrayList<CuentasAdhesionDebitoView>();
        CuentasAdhesionDebitoView cuentaView = new CuentasAdhesionDebitoView();
        Respuesta<PreFormalizacion> respuestaPreformalizacion = new Respuesta<PreFormalizacion>();
        CuentaPrestamoDebitoAdherido prestamoDebitoAdherido = new CuentaPrestamoDebitoAdherido();

        consultaPrestamo.setIdProceso("123456");
        consultaPrestamo.setNumeroPrestamo("200688");
        consultaPrestamo.setNumeroCuenta("234567");

        pagoPrestamo.setIdProceso("123456");
        prestamoDebitoAdherido.setNroSucursal("234");
        prestamoDebitoAdherido.setNumero("1234567");

        cuenta.setNroSucursal("123");
        cuenta.setAlias("Soy Alias");
        cuenta.setTipoCuenta("1");
        cuenta.setNroCuentaProducto("12704060");

        prestamo.setNumeroCuentaProducto("200688");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setClaseCuenta("1");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("1234");
        prestamo.setDivisa(DivisaEnum.DOLAR);
        prestamo.setSaldoPrevio(new BigDecimal("500"));
        prestamo.setImporteAjussal(new BigDecimal("250"));
        prestamo.setIndex("12");
        prestamo.setFactorIndex("123123456");

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 9, 20);
        prestamo.setVencimientoCuota(calendar.getTime());
        prestamo.setImporteTotalCuota(BigDecimal.valueOf(1000));
        prestamo.setImporteAmortizacion(new BigDecimal("400"));
        prestamo.setImporteIntereses(new BigDecimal("50"));
        prestamo.setImporteAjusteCapmor(new BigDecimal("15"));
        prestamo.setImportesPunitorios(new BigDecimal("32"));
        prestamo.setInteresCompensatorioPendiente(new BigDecimal("43"));
        prestamo.setImporteSeguroVida(new BigDecimal("12"));
        prestamo.setImporteSeguroDelBien(new BigDecimal("18"));
        prestamo.setImporteTotalSeguros(new BigDecimal("31"));
        prestamo.setImporteIVA(new BigDecimal("5"));
        prestamo.setImporteIVAAdicional(new BigDecimal("7"));
        prestamo.setImporteEndeudamiento(new BigDecimal("90"));
        prestamo.setIngresosBrutos(new BigDecimal("37"));
        prestamo.setOtrosImpuestos(new BigDecimal("95"));
        prestamo.setGastosPendientes(new BigDecimal("26"));
        prestamo.setComisionesPendientes(new BigDecimal("64"));
        prestamo.setTasaAnualEfectiva(new BigDecimal("100"));
        prestamo.setTasaPrestamo(new BigDecimal("30"));
        prestamo.setCostoFinancieroTotal(new BigDecimal("25"));
        prestamo.setCostoFinancieroTotalSinImpuestos(new BigDecimal("15"));
        prestamo.setNroExp("3");
        prestamo.setCotizCambio(new BigDecimal("90"));
        prestamo.setAjusteSaldo(new BigDecimal("1500"));
        prestamo.setCapitalPend(new BigDecimal("1000"));
        prestamo.setInteresesPend(new BigDecimal("4000"));

        pagoPendientePrestamo.setPrestamo(prestamo);
        preFormalizacion.setPlazo("-");
        preFormalizacion.setPrestamoDebitoAdherido(prestamoDebitoAdherido);
        pagoPendientePrestamo.setPreFormalizacion(preFormalizacion);
        listaPagoPendiente.add(pagoPendientePrestamo);
        respuestaListaPagoPendiente.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaListaPagoPendiente.setRespuesta(listaPagoPendiente);
        listCuentasView.add(cuentaView);
        cuentasView.setCuentas(listCuentasView);
        respuestaCuentasView.setEstadoRespuesta(EstadoRespuesta.ERROR);
        respuestaPreformalizacion.setEstadoRespuesta(EstadoRespuesta.ERROR);

        pagoPrestamo.setPagoPendientePrestamo(pagoPendientePrestamo);

        when(preFormalizacionPrestamoService.obtenerPreFormalizacion(Matchers.any(Prestamo.class)))
                .thenReturn(respuestaPreformalizacion);
        when(cuentaManager.getCuenta(Matchers.anyString())).thenReturn(respuestaCuentasView);
        when(cuentaManager.obtenerCuentaById(Matchers.anyString())).thenReturn(cuenta);
        when(sessionParametros.getPagoPrestamo()).thenReturn(pagoPrestamo);
        when(sessionParametros.getRespuestaPagosPendientes()).thenReturn(respuestaListaPagoPendiente);

        Respuesta<ConfirmacionPagoPrestamoView> respuestaConfirmacionPagoPrestamoView = prestamoManager
                .obtenerConfirmacion(consultaPrestamo);

        assertNotNull(respuestaConfirmacionPagoPrestamoView);
        ConfirmacionPagoPrestamoView confirmacionPagoPrestamoView = respuestaConfirmacionPagoPrestamoView
                .getRespuesta();
        assertEquals(confirmacionPagoPrestamoView.getTipoPrestamoLabel(), "1");
        assertEquals(confirmacionPagoPrestamoView.getTipoPrestamo(), TipoPrestamoEnum.PERSONAL.name());
        assertEquals(confirmacionPagoPrestamoView.getNumeroPrestamo(), "123-00000020068/8");
        assertEquals(confirmacionPagoPrestamoView.getPlazoPrestamo(), "-");
        assertEquals(confirmacionPagoPrestamoView.getCuentaDebito().getAlias(), "Soy Alias");
        assertEquals(confirmacionPagoPrestamoView.getCuentaDebito().getDescripcionTipoCuenta(),
                TipoCuenta.CAJA_AHORRO_PESOS.getDescripcionConMoneda());
        assertEquals(confirmacionPagoPrestamoView.getCuentaDebito().getSaldoPesos(), "--");
        assertEquals(confirmacionPagoPrestamoView.getCuotaPrestamo(), "1234");
        assertEquals(confirmacionPagoPrestamoView.getDivisa(), DivisaEnum.DOLAR.getSimbolo());
        assertEquals(confirmacionPagoPrestamoView.getSaldoAnteriorCapitalSinAjustar(), "500,00");
        assertEquals(confirmacionPagoPrestamoView.getCapitalAjustodoSobreLaCuota(), "750,00");
        assertEquals(confirmacionPagoPrestamoView.isShowCapitalAjustodoSobreLaCuota(), Boolean.TRUE);
        assertEquals(confirmacionPagoPrestamoView.getFechaDeVencimiento(), "20/10/2017");
        assertEquals(confirmacionPagoPrestamoView.getImporteValue(), "1.000,00");
        assertEquals(confirmacionPagoPrestamoView.getCoeficienteIndexacionReciboEnTermino(), "12 123,12");
        assertEquals(confirmacionPagoPrestamoView.isShowCoeficienteIndexacionReciboEnTermino(), Boolean.TRUE);
        List<TasaView> tasaViewList = confirmacionPagoPrestamoView.getTasas();
        assertEquals(tasaViewList.get(0).getLabel(), "Tasa Nominal Anual (T.N.A)");
        assertEquals(tasaViewList.get(0).getTasa(), "30,00");
        assertEquals(tasaViewList.get(1).getLabel(), "Tasa Efectiva Anual (T.E.A)");
        assertEquals(tasaViewList.get(1).getTasa(), "100,00");
        assertEquals(tasaViewList.get(2).getLabel(), "Costo Financiero Total Efectivo Anual");
        assertEquals(tasaViewList.get(2).getTasa(), "25,00");
        assertEquals(tasaViewList.get(3).getLabel(), "Costo Financiero Total Efectivo Anual (Sin Impuestos)");
        assertEquals(tasaViewList.get(3).getTasa(), "15,00");
        List<ImporteView> importeView = confirmacionPagoPrestamoView.getImportes();
        assertEquals(importeView.get(0).getLabel(), "Capital");
        assertEquals(importeView.get(0).getImporte(), "400,00");
        assertEquals(importeView.get(1).getLabel(), "Intereses compensatorios del periodo");
        assertEquals(importeView.get(1).getImporte(), "50,00");
        assertEquals(importeView.get(2).getLabel(), "Ajuste de capital en mora");
        assertEquals(importeView.get(2).getImporte(), "15,00");
        assertEquals(importeView.get(3).getLabel(), "Intereses punitorios");
        assertEquals(importeView.get(3).getImporte(), "32,00");
        assertEquals(importeView.get(4).getLabel(), "Intereses compensatorios posteriores al vto.");
        assertEquals(importeView.get(4).getImporte(), "43,00");
        assertEquals(importeView.get(5).getLabel(), "Cargo por seguro de vida (2)");
        assertEquals(importeView.get(5).getImporte(), "12,00");
        assertEquals(importeView.get(6).getLabel(), "Seguro del bien");
        assertEquals(importeView.get(6).getImporte(), "18,00");
        assertEquals(importeView.get(7).getLabel(), "Otros seguros");
        assertEquals(importeView.get(7).getImporte(), "1,00");
        assertEquals(importeView.get(8).getLabel(), "IVA Tasa general");
        assertEquals(importeView.get(8).getImporte(), "5,00");
        assertEquals(importeView.get(9).getLabel(), "IVA Adicional");
        assertEquals(importeView.get(9).getImporte(), "7,00");
        assertEquals(importeView.get(10).getLabel(), "Impuesto al endeudamiento");
        assertEquals(importeView.get(10).getImporte(), "90,00");
        assertEquals(importeView.get(11).getLabel(), "Percepcion ingresos brutos");
        assertEquals(importeView.get(11).getImporte(), "37,00");
        assertEquals(importeView.get(12).getLabel(), "Otros impuestos (3)");
        assertEquals(importeView.get(12).getImporte(), "95,00");
        assertEquals(importeView.get(13).getLabel(), "Gastos anexos");
        assertEquals(importeView.get(13).getImporte(), "26,00");
        assertEquals(importeView.get(14).getLabel(), "Comisiones");
        assertEquals(importeView.get(14).getImporte(), "64,00");

    }

}
