package ar.com.santanderrio.obp.servicios.pagohaberes.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.reporte.entities.Reporte;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.pagohaberes.dao.impl.PagoHaberesDAOImpl;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.ComprobantePagoHaberesCBUEntity;
import ar.com.santanderrio.obp.servicios.pagohaberes.entities.DatosEmpleadoPagoHaberesEntity;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ComprobanteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.ImporteView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.TasaView;
import ar.com.santanderrio.obp.servicios.pagos.web.view.UvaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ProximaCuotaView;
import ar.com.santanderrio.obp.servicios.prestamos.view.ReportSimulacionPrestamosView;

@RunWith(MockitoJUnitRunner.class)
public class PagoHaberesDAOIT {

    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    @InjectMocks
    PagoHaberesDAO pagoHaberesDAO = new PagoHaberesDAOImpl();

    @Mock
    ComprobanteView detalleView;

    @Mock
    ReportSimulacionPrestamosView reportSimulacionPrestamosView;

    @Mock
    ProximaCuotaView proximaCuota;

    private ApplicationContext appContext = new ClassPathXmlApplicationContext();

    @Test
    public void testReporteComprobantePDFTransferencia() throws IllegalAccessException, DAOException, IOException {
        FieldUtils.writeField(pagoHaberesDAO, "fileJasperComprobantePagoSimple", appContext.getResource(
                "classpath:/report/pagoHaberes/pagoSimpleMultiple/ComprobantePagoSimpleMultiple.jasper"), true);
        FieldUtils.writeField(pagoHaberesDAO, "imagenLogoTop",
                appContext.getResource("classpath:/report/comprobantes/logo-santander-gris-comp.png"), true);
        FieldUtils.writeField(pagoHaberesDAO, "logoCabecera", appContext
                .getResource("classpath:/report/comprobantes/logo_cabecera_comprobante.png"), true);
        FieldUtils.writeField(pagoHaberesDAO, "logoDefault",
                appContext.getResource("classpath:/report/comprobantes/logo_default.png"), true);
        FieldUtils.writeField(pagoHaberesDAO, "logoCierre",
                appContext.getResource("classpath:/report/comprobantes/logo_cierre_comprobante.png"),
                true);
        Reporte reporte = pagoHaberesDAO.generarComprobantePagoSimple(comprobante());
        FileUtils.writeByteArrayToFile(new File("C:/tools/comprobantePagoHaberesTest.pdf"), reporte.getBytes());
        Assert.assertNotNull(reporte.getBytes());
    }

    private ComprobantePagoHaberesCBUEntity comprobante() {
        ComprobantePagoHaberesCBUEntity comprobante = new ComprobantePagoHaberesCBUEntity();
        DatosEmpleadoPagoHaberesEntity datosEmpleadoPagoHaberes = new DatosEmpleadoPagoHaberesEntity();
        comprobante.setDatosEmpleadoPagoHaberes(datosEmpleadoPagoHaberes);
        datosEmpleadoPagoHaberes.setDescripcionEmpleado("Buendia Crispn Cabeller Alastr");
        datosEmpleadoPagoHaberes.setCuentaOrigen("084-425628/7");
        datosEmpleadoPagoHaberes.setTipoCuentaOrigen("Cuenta única en $");
        datosEmpleadoPagoHaberes.setCuentaDestino("037-118734/0");
        datosEmpleadoPagoHaberes.setTipoCuentaDestino("Caja de ahorro en $");
        datosEmpleadoPagoHaberes.setCuil("20-12345678-9");
        datosEmpleadoPagoHaberes.setFechaEjecucion("16/10/2018");
        datosEmpleadoPagoHaberes.setImporte("$ 0,10");
        datosEmpleadoPagoHaberes.setTipoPago("Sueldo");
        datosEmpleadoPagoHaberes.setConcepto("asdasd");
        comprobante.setNroDeComprobante("20180817263168");
        comprobante.setFechaHora("16/10/2018 - 10:28");
        comprobante.setLegalesSEO(
                "Incluye Cargo por Seguro de Vida, e invalidez total y permanente solo si el Préstamo fue otorgado y desembolsado hasta el 31/08/2016, inclusive.");
        comprobante.setModoEjecucion(true);

        return comprobante;
    }

    private HashMap<String, Object> obtenerParametrosPDF() {
        HashMap<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("TITULO_COMPROBANTE", "Comprobante de pago de cuota de Préstamo");
        parametros.put("IMPORTE_ACREDITAR", "$ 734,00");
        parametros.put("TIPO_PRESTAMO", "Super Préstamo Personal");
        parametros.put("NUMERO_PRESTAMO", "066-03519064787/6");
        parametros.put("TITULAR", "Vitoldo sofanor alain conquista");
        parametros.put("TIPO_DOC", "CUIL");
        parametros.put("NRO_DOC", "20-21767058-7");
        parametros.put("CONDICION_IVA", "Consumidor final");
        parametros.put("NRO_CUENTA_DEBITO", "066-102066/7");
        parametros.put("TIPO_CUENTA_DEBITO", "Cuenta única");
        parametros.put("CUOTA_PRESTAMO", "3");
        parametros.put("PLAZO_PRESTAMO", "25");
        parametros.put("SALDO_SIN_AJUSTAR", "$ 10.164,35");
        parametros.put("IMPORTE_CUOTA", "734,00");
        List<ImporteView> importes = new ArrayList<ImporteView>();
        ImporteView importeView = new ImporteView();
        importeView.setLabel("Capital  para que se alargue y todo eso pero nasidn ai nasdi nasdi uuu yy er");
        importeView.setImporte("$ 286,16");
        importes.add(importeView);
        parametros.put("IMPORTES", importes);
        List<TasaView> tasas = new ArrayList<TasaView>();
        TasaView tasa = new TasaView();
        tasa.setLabel("Tasa Nominal Anual de nuevo para que se alargue y todo eso");
        tasa.setTasa("45,00 %");
        tasas.add(tasa);
        parametros.put("TASAS", tasas);
        List<UvaView> ubas = new ArrayList<UvaView>();
        UvaView uba = new UvaView();
        uba.setLabel("ubaDato para que la banda se alargue pero si sigo probando no se si la banda");
        uba.setUva("pepepepepe");
        ubas.add(uba);
        UvaView uba2 = new UvaView();
        uba2.setLabel("ubaDato para que la banda se alargue pero si sigo probando no se si la banda(mrk2)");
        uba2.setUva("pepepepepe");
        ubas.add(uba2);
        parametros.put("UBAS", ubas);
        parametros.put("SALDO_DEUDA_CAPITAL", "$ 9.878,19");
        parametros.put("FECHA_VENCIMIENTO", "15/03/2018");
        parametros.put("NRO_COMPROBANTE", "27162602");
        parametros.put("FECHA_ACTUAL", "29/06/2018 - 15:05");
        return parametros;
    }

}
