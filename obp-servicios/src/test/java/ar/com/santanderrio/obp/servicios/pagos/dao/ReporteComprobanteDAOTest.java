package ar.com.santanderrio.obp.servicios.pagos.dao;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.exceptions.ImporteConvertException;
import ar.com.santanderrio.obp.base.iatx.entities.IatxException;
import ar.com.santanderrio.obp.iatx.IatxBaseDAOTest;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.ComprobantePrestamoReporte;
import ar.com.santanderrio.obp.servicios.pagos.bo.ItemReporteDetalle;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.NoTieneFondosSuficientesException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.PagoConAnterioridadException;
import ar.com.santanderrio.obp.servicios.pagos.dao.impl.ReporteComprobanteDAOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Interviniente;
import ar.com.santanderrio.obp.servicios.pagos.entities.PagoPendientePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class ReporteComprobanteDAOTest.
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ReporteComprobanteDAOTest.ReporteComprobanteDAOTestConfiguration.class })

public class ReporteComprobanteDAOTest extends IatxBaseDAOTest {

    /** The reporte comprobante DAO. */
    @Autowired
    @InjectMocks
    private ReporteComprobanteDAOImpl reporteComprobanteDAO = new ReporteComprobanteDAOImpl();

    /**
     * The Class ReporteComprobanteDAOTestConfiguration.
     */
    @Configuration
    public static class ReporteComprobanteDAOTestConfiguration {

        /**
         * Operacion reporte comprobante DAO.
         *
         * @return the reporte comprobante DAO
         */
        @Bean
        public ReporteComprobanteDAO operacionReporteComprobanteDAO() {
            return new ReporteComprobanteDAOImpl();
        }

    }

    /**
     * Builds the report comprobante ok test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws DAOException
     *             the DAO exception
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    @Test
    public void buildReportComprobanteOkTest() throws IatxException, PagoConAnterioridadException,
            NoTieneFondosSuficientesException, DAOException, ImporteConvertException {

        Cuenta cuenta = obtenerCuenta();

        ComprobantePrestamo comprobante = obtenerComprobantePrestamo(cuenta);

        Prestamo prestamo = obtenerPrestamo(cuenta, comprobante);

        PagoPendientePrestamo pagoPendientePrestamo = obtenerPagoPendientePrestamo();

        pagoPendientePrestamo.setPrestamo(prestamo);

        ComprobantePrestamoReporte comprobantePrestamoReporte = obtenerComprobantePrestamoReporte(cuenta,
                pagoPendientePrestamo, comprobante);

        byte[] byteArray = reporteComprobanteDAO.buildReportComprobante(comprobantePrestamoReporte);

        Assert.assertNotNull(byteArray);

    }

    /**
     * Builds the report comprobante detalle ok test test.
     *
     * @throws IatxException
     *             the iatx exception
     * @throws PagoConAnterioridadException
     *             the pago con anterioridad exception
     * @throws NoTieneFondosSuficientesException
     *             the no tiene fondos suficientes exception
     * @throws DAOException
     *             the DAO exception
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    @Test
    public void buildReportComprobanteDetalleOkTestTest() throws IatxException, PagoConAnterioridadException,
            NoTieneFondosSuficientesException, DAOException, ImporteConvertException {

        Cuenta cuenta = obtenerCuenta();

        ComprobantePrestamo comprobante = obtenerComprobantePrestamo(cuenta);

        Prestamo prestamo = obtenerPrestamo(cuenta, comprobante);

        PagoPendientePrestamo pagoPendientePrestamo = obtenerPagoPendientePrestamo();

        pagoPendientePrestamo.setPrestamo(prestamo);

        ComprobantePrestamoReporte comprobantePrestamoReporte = obtenerComprobantePrestamoReporte(cuenta,
                pagoPendientePrestamo, comprobante);

        byte[] byteArray = reporteComprobanteDAO.buildReportComprobanteDetalle(comprobantePrestamoReporte);

        Assert.assertNotNull(byteArray);
    }

    /**
     * Obtener comprobante prestamo reporte.
     *
     * @param cuenta
     *            the cuenta
     * @param pagoPendientePrestamo
     *            the pago pendiente prestamo
     * @param comprobantePrestamo
     *            the comprobante prestamo
     * @return the comprobante prestamo reporte
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    private ComprobantePrestamoReporte obtenerComprobantePrestamoReporte(Cuenta cuenta,
            PagoPendientePrestamo pagoPendientePrestamo, ComprobantePrestamo comprobantePrestamo)
            throws ImporteConvertException {

        Interviniente interviniente = getInterviniente();

        ComprobantePrestamoReporte comprobantePrestamoReporte = new ComprobantePrestamoReporte();

        comprobantePrestamoReporte.setImportes(buildImportes(comprobantePrestamoReporte, comprobantePrestamo));
        comprobantePrestamoReporte.setTasas(buildTasas(comprobantePrestamoReporte, comprobantePrestamo));

        comprobantePrestamoReporte.setDescripcionPrestamo(pagoPendientePrestamo.getTipoPrestamo());
        comprobantePrestamoReporte.setValorDescripcionPrestamo(
                ISBANStringUtils.formatearSucursal(pagoPendientePrestamo.getPrestamo().getCuenta().getNroSucursal())
                        + "-" + ISBANStringUtils.agregarBarraNumeroPrestamo(ISBANStringUtils.formateadorConCerosIzq(
                                pagoPendientePrestamo.getPrestamo().getNumeroCuentaProducto(), 12)));

        comprobantePrestamoReporte.setTitular(StringUtils.capitalize(StringUtils.lowerCase(interviniente.getNombre()))
                + " " + StringUtils.capitalize(StringUtils.lowerCase(interviniente.getApellido())));
        comprobantePrestamoReporte.setCuil(ISBANStringUtils.formatearCuil(interviniente.getCuitcuil()));
        comprobantePrestamoReporte.setCondicionIVA(
                StringUtils.capitalize(StringUtils.lowerCase(comprobantePrestamo.getDescripcionIVA())));
        comprobantePrestamoReporte
                .setCuotaPrestamo(String.valueOf(Long.parseLong(pagoPendientePrestamo.getNumeroCuotas())));
        if (pagoPendientePrestamo.getPreFormalizacion() != null) {
            comprobantePrestamoReporte.setPlazoPrestamo(
                    String.valueOf(Long.parseLong(pagoPendientePrestamo.getPreFormalizacion().getPlazo())));
        } else {
            comprobantePrestamoReporte.setPlazoPrestamo("");
        }
        comprobantePrestamoReporte
                .setImporteCuota("$" + ISBANStringUtils.formatearSaldoConSigno(pagoPendientePrestamo.getImporte()));
        comprobantePrestamoReporte.setImporteCuotaPrincipal(
                "$" + ISBANStringUtils.formatearSaldoConSigno(pagoPendientePrestamo.getImporte()));
        // Cuenta cuenta = comprobantePrestamo.getCuenta();
        comprobantePrestamoReporte.setCuentaDebito(ISBANStringUtils.formatearSucursal(cuenta.getNroSucursal()) + "-"
                + ISBANStringUtils.formatearNumeroCuenta(cuenta.getNroCuentaProducto()));
        comprobantePrestamoReporte.setTipoCuenta(TipoCuenta.fromCodigo(cuenta.getTipoCuenta()).getDescripcion());
        comprobantePrestamoReporte
                .setFechaVencimiento(ISBANStringUtils.formatearFecha(pagoPendientePrestamo.getVencimiento()));
        comprobantePrestamoReporte.setTipoPrestamo(pagoPendientePrestamo.getPrestamo().getTipoPrestamoEnum());
        comprobantePrestamoReporte.setNroComprobante(comprobantePrestamo.getNio());
        comprobantePrestamoReporte.setAliasCuentaDebito(cuenta.getAlias());
        return comprobantePrestamoReporte;
    }

    /**
     * Gets the interviniente.
     *
     * @return the interviniente
     */
    private Interviniente getInterviniente() {
        Interviniente interviniente = new Interviniente();
        interviniente.setApellido("Apellido");
        interviniente.setNombre("Nombre");
        interviniente.setCuitcuil("30-61734087-5");
        return interviniente;
    }

    /**
     * Obtener pago pendiente prestamo.
     *
     * @return the pago pendiente prestamo
     */
    private PagoPendientePrestamo obtenerPagoPendientePrestamo() {
        PagoPendientePrestamo pagoPendientePrestamo = new PagoPendientePrestamo();
        pagoPendientePrestamo.setTipoPrestamo("Personal");
        pagoPendientePrestamo.setNumeroCuotas("3");
        pagoPendientePrestamo.setImporte(new BigDecimal(300));
        pagoPendientePrestamo.setVencimiento(new Date());
        return pagoPendientePrestamo;
    }

    /**
     * Obtener prestamo.
     *
     * @param cuenta
     *            the cuenta
     * @param comprobante
     *            the comprobante
     * @return the prestamo
     */
    private Prestamo obtenerPrestamo(Cuenta cuenta, ComprobantePrestamo comprobante) {
        Prestamo prestamo = new Prestamo();
        prestamo.setNumeroCuentaProducto("0000011");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setCuenta(cuenta);
        return prestamo;
    }

    /**
     * Obtener comprobante prestamo.
     *
     * @param cuenta
     *            the cuenta
     * @return the comprobante prestamo
     */
    private ComprobantePrestamo obtenerComprobantePrestamo(Cuenta cuenta) {
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

        comprobante.setInterviniente(getInterviniente());
        comprobante.setCuenta(cuenta);
        comprobante.setNio("3232131232");
        return comprobante;
    }

    /**
     * Byte array to file.
     *
     * @param bArray
     *            the b array
     * @param comprobante
     *            the comprobante
     */
    static void byteArrayToFile(byte[] bArray, String comprobante) {

        try {

            FileOutputStream pdf = new FileOutputStream(comprobante);
            for (Byte b : bArray) {
                pdf.write(b);
            }
            pdf.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Builds the tasas.
     *
     * @param comprobantePrestamoReporte
     *            the comprobante prestamo reporte
     * @param comprobantePrestamo
     *            the comprobante prestamo
     * @return the list
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    private List<ItemReporteDetalle> buildTasas(ComprobantePrestamoReporte comprobantePrestamoReporte,
            ComprobantePrestamo comprobantePrestamo) throws ImporteConvertException {

        List<ItemReporteDetalle> tasas = new ArrayList<ItemReporteDetalle>();
        tasas.add(getImporteDetalle("Tasa Efectiva Anual", comprobantePrestamo.getTea()));
        tasas.add(getImporteDetalle("Tasa Nominal Anual", comprobantePrestamo.getTasaPrestamo()));
        tasas.add(getImporteDetalle("Costo Financiero Total Nominal Anual", comprobantePrestamo.getCftna()));
        tasas.add(getImporteDetalle("Costo Financiero Total Nominal Anual (Sin impuestos)",
                comprobantePrestamo.getCftnaSinImp()));
        return tasas;
    }

    /**
     * Gets the importe detalle.
     *
     * @param label
     *            the label
     * @param importe
     *            the importe
     * @return the importe detalle
     */
    private ItemReporteDetalle getImporteDetalle(String label, BigDecimal importe) {
        ItemReporteDetalle itemReporteDetalle = new ItemReporteDetalle();
        itemReporteDetalle.setLabel(label);
        itemReporteDetalle.setValue("$" + ISBANStringUtils.formatearSaldoConSigno(importe));
        return itemReporteDetalle;
    }

    /**
     * Builds the importes.
     *
     * @param comprobantePrestamoReporte
     *            the comprobante prestamo reporte
     * @param comprobantePrestamo
     *            the comprobante prestamo
     * @return the list
     * @throws ImporteConvertException
     *             the importe convert exception
     */
    private List<ItemReporteDetalle> buildImportes(ComprobantePrestamoReporte comprobantePrestamoReporte,
            ComprobantePrestamo comprobantePrestamo) throws ImporteConvertException {
        List<ItemReporteDetalle> importes = new ArrayList<ItemReporteDetalle>();

        importes.add(getImporteDetalle("Capital", comprobantePrestamo.getImporteAmortizacion()));
        importes.add(
                getImporteDetalle("Intereses compensatorios del periodo", comprobantePrestamo.getImporteIntereses()));
        importes.add(getImporteDetalle("Ajuste de Capital en Mora", comprobantePrestamo.getImpAjusCapmor()));
        importes.add(getImporteDetalle("Intereses Punitorios", comprobantePrestamo.getImportePunitorios()));
        importes.add(getImporteDetalle("Intereses compensatorios posteriores al vto.",
                comprobantePrestamo.getImporteComplementario()));
        importes.add(getImporteDetalle("Cargo por Seguro de Vida", comprobantePrestamo.getImporteSeguroVida()));
        importes.add(getImporteDetalle("Seguro del Bien", comprobantePrestamo.getImporteSeguroBien()));
        // Aca valido los otros seguros
        // if (comprobantePrestamo.getImporteTotalSeguros()!= null &&
        // !isZero(comprobantePrestamo.getImporteTotalSeguros(),13,4,false)){
        // BigDecimal resultado =
        // comprobantePrestamo.getImporteTotalSeguros().add(comprobantePrestamo.getImporteTotalSeguros());
        // if (isValorMinimoSeguros(resultado,13, 4, false)){
        // importes.add(getImporteDetalle("Otros seguros",
        // comprobantePrestamo.getImporteTotalSeguros()));
        // }
        // }
        importes.add(getImporteDetalle("I.V.A. Tasa General", comprobantePrestamo.getImporteIVA()));
        importes.add(getImporteDetalle("I.V.A. Adicional", comprobantePrestamo.getImporteIVAAdicional()));
        importes.add(getImporteDetalle("Impuesto al Endeudamiento", comprobantePrestamo.getImpuestoEndFinal()));
        importes.add(getImporteDetalle("Percepcion Ingresos Brutos", comprobantePrestamo.getIngresosBrutos()));
        importes.add(getImporteDetalle("Otros Impuestos", comprobantePrestamo.getOtrosImpuestos()));
        importes.add(getImporteDetalle("Gastos Anexos", comprobantePrestamo.getGastos()));
        importes.add(getImporteDetalle("Comisiones", comprobantePrestamo.getComisiones()));
        return importes;
    }

    /**
     * Hardcode Cuenta.
     *
     * @return Cuenta
     */
    private Cuenta obtenerCuenta() {
        Cuenta cuenta = new Cuenta();
        cuenta.setNroCuentaProducto("0000011");
        cuenta.setNroSucursal("000");
        cuenta.setTipoCuenta("30");
        cuenta.setAlias("Pepe");
        cuenta.setCliente(obtenerCliente());
        return cuenta;
    }

    /**
     * Hardcode Cliente.
     *
     * @return Cliente
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
        c1.setNumeroCUILCUIT("33333333333");
        c1.setSegmento(segmento);
        return c1;
    }

}
