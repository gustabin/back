/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.excel.helpers;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.Segmento;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.comun.ImporteInvalidoException;
import ar.com.santanderrio.obp.servicios.comun.excel.helpers.ExcelBuilderHelperTest.ExcelBuilderHelperTestConfiguration;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sucursales.entities.Sucursal;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoChequesYValoresEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleMovimientoEntity;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DivisaEnum;
import ar.com.santanderrio.obp.servicios.cuentas.entities.EstadoOperacionMovimimiento;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoDeCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientoExcelItem;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcel;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosExcelCabecera;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosPendientesDeConfirmacion;
import ar.com.santanderrio.obp.servicios.cuentas.entities.MovimientosValoresPendientes;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoOperacionMovimimiento;

/**
 * The Class ExcelBuilderHelperTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {
        ExcelBuilderHelperTestConfiguration.class })
@ActiveProfiles(Profiles.TEST)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExcelBuilderHelperTest {

    /** The helper. */
    @Autowired
    private ExcelBuilderHelper helper;

    /**
     * The Class ExcelBuilderHelperTestConfiguration.
     */
    @Configuration
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.servicios.comun.excel.helpers",
            "ar.com.santanderrio.obp.base.jexl" })
    public static class ExcelBuilderHelperTestConfiguration {

    }

    /**
     * Test ultimos movimientos A.
     *
     * @throws Exception
     *             the exception
     */
    /*
     * #if (root.cabecera.dispositivo != "phone" && root.cabecera.moneda ==
     * "peso" && root.cabecera.mostrarSaldoAperturado)
     * 
     */
    @Test
    public void testUltimosMovimientosA() throws Exception {
        SesionCliente sessionClient = new SesionCliente();
        Cliente cliente = new Cliente();
        sessionClient.setCliente(cliente);
        Segmento segmento = new Segmento();
        cliente.setSegmento(segmento);

        MovimientosExcel movimientosExcel = crearCasosMovimientosExcel("celular", "Pesos", true);
        byte[] excel = helper.hacerExcel(cliente, "UltimosMovimientos", movimientosExcel);
        Assert.assertNotNull(excel);

    }

    /**
     * Test ultimos movimientos B.
     *
     * @throws Exception
     *             the exception
     */
    /*
     * #if (root.cabecera.dispositivo != "phone" && root.cabecera.moneda ==
     * "peso" && !root.cabecera.mostrarSaldoAperturado)
     * 
     */
    @Test
    public void testUltimosMovimientosB() throws Exception {
        SesionCliente sessionClient = new SesionCliente();
        Cliente cliente = new Cliente();
        sessionClient.setCliente(cliente);
        Segmento segmento = new Segmento();
        cliente.setSegmento(segmento);

        MovimientosExcel movimientosExcel = crearCasosMovimientosExcel("phone", "Pesos", false);
        byte[] excel = helper.hacerExcel(cliente, "UltimosMovimientos", movimientosExcel);
        // try {
        // FileOutputStream out = new FileOutputStream(new
        // File("C:\\tmp\\UltimosMovimientosB.xlsx"));
        // out.write(excel);
        // out.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        Assert.assertNotNull(excel);
    }

    /**
     * Test ultimos movimientos C.
     *
     * @throws Exception
     *             the exception
     */
    /*
     * #if (root.cabecera.dispositivo != "phone" && root.cabecera.moneda ==
     * "dolar")
     * 
     */
    @Test
    public void testUltimosMovimientosC() throws Exception {
        SesionCliente sessionClient = new SesionCliente();
        Cliente cliente = new Cliente();
        sessionClient.setCliente(cliente);
        Segmento segmento = new Segmento();
        cliente.setSegmento(segmento);

        MovimientosExcel movimientosExcel = crearCasosMovimientosExcel("phone", "Pesos", true);
        byte[] excel = helper.hacerExcel(cliente, "UltimosMovimientos", movimientosExcel);
        Assert.assertNotNull(excel);
    }

    /**
     * Test ultimos movimientos D.
     *
     * @throws Exception
     *             the exception
     */
    /*
     * #if (root.cabecera.dispositivo == "phone" &&
     * root.cabecera.mostrarSaldoAperturado)
     * 
     */
    @Test
    public void testUltimosMovimientosD() throws Exception {
        SesionCliente sessionClient = new SesionCliente();
        Cliente cliente = new Cliente();
        sessionClient.setCliente(cliente);
        Segmento segmento = new Segmento();
        cliente.setSegmento(segmento);

        MovimientosExcel movimientosExcel = crearCasosMovimientosExcel("phone", "Pesos", true);
        byte[] excel = helper.hacerExcel(cliente, "UltimosMovimientos", movimientosExcel);
        Assert.assertNotNull(excel);

    }

    /**
     * Test ultimos movimientos E.
     *
     * @throws Exception
     *             the exception
     */
    /*
     * #if (root.cabecera.dispositivo == "phone" &&
     * !root.cabecera.mostrarSaldoAperturado)
     * 
     */
    @Test
    public void testUltimosMovimientosE() throws Exception {
        SesionCliente sessionClient = new SesionCliente();
        Cliente cliente = new Cliente();
        sessionClient.setCliente(cliente);
        Segmento segmento = new Segmento();
        cliente.setSegmento(segmento);

        MovimientosExcel movimientosExcel = crearCasosMovimientosExcel("phone", "Pesos", true);
        byte[] excel = helper.hacerExcel(cliente, "UltimosMovimientos", movimientosExcel);
        Assert.assertNotNull(excel);

    }

    /**
     * Crear casos movimientos excel.
     *
     * @param dispositivo
     *            the dispositivo
     * @param moneda
     *            the moneda
     * @param mostrarSaldoApertura
     *            the mostrar saldo apertura
     * @return the movimientos excel
     */
    private MovimientosExcel crearCasosMovimientosExcel(String dispositivo, String moneda,
            boolean mostrarSaldoApertura) {
        MovimientosExcel movimientosExcel = new MovimientosExcel();
        MovimientosExcelCabecera cabecera = crearCabeceraExcel(dispositivo, moneda, mostrarSaldoApertura);

        List<DetalleMovimientoEntity> movimientos = crearListDetalleMovimiento();
        List<MovimientoExcelItem> items = new ArrayList<MovimientoExcelItem>();
        for (DetalleMovimientoEntity detalleMovimiento : movimientos) {
            MovimientoExcelItem movimiento = crearItemExcel(detalleMovimiento);
            items.add(movimiento);
        }

        movimientosExcel.setCabecera(cabecera);
        movimientosExcel.setMovimientos(items);
        return movimientosExcel;
    }

    /**
     * Crear cabecera excel.
     *
     * @param dispositivo
     *            the dispositivo
     * @param moneda
     *            the moneda
     * @param mostrarSaldoApertura
     *            the mostrar saldo apertura
     * @return the movimientos excel cabecera
     */
    private MovimientosExcelCabecera crearCabeceraExcel(String dispositivo, String moneda,
            boolean mostrarSaldoApertura) {

        Date fecha = new Date();

        String fechaDesde = ISBANStringUtils.formatearFecha(fecha);
        String fechaHasta = ISBANStringUtils.formatearFecha(fecha);

        MovimientosExcelCabecera cabecera = new MovimientosExcelCabecera();
        cabecera.setDispositivo(dispositivo);
        cabecera.setMoneda(moneda);
        cabecera.setMostrarSaldoAperturado(mostrarSaldoApertura);

        cabecera.setTipoCuenta("cuenta1");
        cabecera.setNumeroCuenta("000-063917/0");
        cabecera.setMostrarFiltroFecha(true);
        cabecera.setFechaDesde(fechaDesde);
        cabecera.setFechaHasta(fechaHasta);
        cabecera.setPalabraClave("palabraClave1");
        cabecera.setImporteDesde("20");
        cabecera.setImporteHasta("60");
        cabecera.setIsConvenioPAS(true);
        return cabecera;
    }

    /**
     * Crear list detalle movimiento.
     *
     * @return the list
     */
    private List<DetalleMovimientoEntity> crearListDetalleMovimiento() {
        List<DetalleMovimientoEntity> detallesDeMovimiento = new ArrayList<DetalleMovimientoEntity>();

        DetalleMovimientoEntity detalleUno = new DetalleMovimientoEntity();
        detalleUno.setFecha(new Date(99, 2, 12));
        detalleUno.setDescripcion("descripcion detalleuno");
        detalleUno.setDescripcionAdicional("descripcion adicional detalleuno");
        detalleUno.setSaldoCuenta(BigDecimal.valueOf(22.22));
        detalleUno.setImporteMovimiento(BigDecimal.valueOf(22.22));
        detalleUno.setIsCajaDeAhoroEnPesos(true);
        detalleUno.setIsCuentaCorrienteEnPesos(true);
        detalleUno.setIsMovimientoEnDolares(true);
        detalleUno.setNumeroReferencia("2222");
        detalleUno.setHora("22");
        detalleUno.setNumeroSucursal("suc1");
        detalleUno.setDescripcionSucursal("sucursal descripcion detalleuno");
        detalleUno.setObservacion("observacion detalleuno");

        DetalleMovimientoEntity detalleDos = new DetalleMovimientoEntity();
        detalleDos.setFecha(new Date(99, 2, 12));
        detalleDos.setDescripcion("descripcion2");
        detalleDos.setDescripcionAdicional("descripcio Adicional");
        detalleDos.setSaldoCuenta(BigDecimal.valueOf(2.23));
        detalleDos.setImporteMovimiento(BigDecimal.valueOf(2.66));
        detalleDos.setIsCajaDeAhoroEnPesos(true);
        detalleDos.setIsCuentaCorrienteEnPesos(true);
        detalleDos.setIsMovimientoEnDolares(true);
        detalleDos.setNumeroReferencia("ref001");
        detalleDos.setHora("20");
        detalleDos.setNumeroSucursal("suc002");
        detalleDos.setDescripcionSucursal("descripcion sucursal1");
        detalleDos.setObservacion("descripcion sucursal observacion");

        detallesDeMovimiento.add(detalleUno);
        detallesDeMovimiento.add(detalleDos);

        return detallesDeMovimiento;
    }

    /**
     * Crear item excel.
     *
     * @param detalleMovimiento
     *            the detalle movimiento
     * @return the movimiento excel item
     */
    private MovimientoExcelItem crearItemExcel(DetalleMovimientoEntity detalleMovimiento) {
        MovimientoExcelItem movimiento = new MovimientoExcelItem();
        movimiento.setDescripcion(detalleMovimiento.getDescripcionAdicional());

        String fecha = ISBANStringUtils.formatearFecha(detalleMovimiento.getFecha());

        movimiento.setFechaHora(fecha + " " + detalleMovimiento.getHora());

        movimiento.setIsCajaDeAhoroEnPesos(detalleMovimiento.getIsCajaDeAhoroEnPesos());
        movimiento.setIsCuentaCorrienteEnPesos(detalleMovimiento.getIsCuentaCorrienteEnPesos());
        movimiento.setIsMovimientoEnDolares(detalleMovimiento.getIsMovimientoEnDolares());

        movimiento.setImporte(detalleMovimiento.getImporteMovimiento().doubleValue());
        movimiento.setSaldo(detalleMovimiento.getSaldoCuenta().doubleValue());

        movimiento.setSucursalOrigen(
                detalleMovimiento.getNumeroSucursal() + " " + detalleMovimiento.getDescripcionSucursal());

        movimiento.setReferencia(detalleMovimiento.getNumeroReferencia());

        return movimiento;

    }

    /**
     * Test movimientos pendientes de confirmacion.
     *
     * @throws Exception
     *             the exception
     */
    @Test
    public void testMovimientosPendientesDeConfirmacion() throws Exception {
        SesionCliente sessionClient = new SesionCliente();
        Cliente cliente = new Cliente();
        sessionClient.setCliente(cliente);
        Segmento segmento = new Segmento();
        cliente.setSegmento(segmento);

        MovimientosPendientesDeConfirmacion movimientoDeCuenta = new MovimientosPendientesDeConfirmacion();
        movimientoDeCuenta.setMovimientosPendientesDeConfirmacion(agregarListMovimientos());
        movimientoDeCuenta.setCuenta("234234234324");
        movimientoDeCuenta.setTipoCuenta("07");
        byte[] excel = helper.hacerExcel(cliente, "movimientosPendientesDeConfirmacion", movimientoDeCuenta);
        Assert.assertNotNull(excel);
    }

    /**
     * Agregar list movimientos.
     *
     * @return the list
     * @throws ImporteInvalidoException
     *             the importe invalido exception
     */
    private List<MovimientoDeCuenta> agregarListMovimientos() throws ImporteInvalidoException {
        List<MovimientoDeCuenta> listMovDeCuentas = new ArrayList<MovimientoDeCuenta>();

        // MovimientoDeCuenta
        MovimientoDeCuenta movDeCuentaA = new MovimientoDeCuenta();
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal("001");
        sucursal.setDescripcion("Central");

        movDeCuentaA.setTipoDeCuenta("tipo cuenta 1");
        movDeCuentaA.setNumeroDeCuenta("00000000001");
        movDeCuentaA.setDivisa(DivisaEnum.fromCodigoString("codigo1"));
        movDeCuentaA.setOrigenTransaccion("transaccion1");
        movDeCuentaA.setFecha(new SimpleDateFormat("dd/MM/yyy").format(new Date()));
        movDeCuentaA.setHora("12");
        movDeCuentaA.setTipoDeOperacion(TipoOperacionMovimimiento.DEPOSITO_DE_CHEQUE);
        movDeCuentaA.setNumeroDeTicket("0000001");
        movDeCuentaA.setSucursalOrigen(sucursal);
        movDeCuentaA.setUsuarioBanco("usuario Bancario 1");
        movDeCuentaA.setImporteOperacion(new BigDecimal(ISBANStringUtils.importePtoFijo2Canonico("233")));
        movDeCuentaA.setEstado(EstadoOperacionMovimimiento.A_CONFIRMAR);

        // MovimientoDeCuenta
        MovimientoDeCuenta movDeCuentaB = new MovimientoDeCuenta();
        movDeCuentaB.setTipoDeCuenta("tipo cuenta 2");
        movDeCuentaB.setNumeroDeCuenta("00000000002");
        movDeCuentaB.setDivisa(DivisaEnum.fromCodigoString("codigo2"));
        movDeCuentaB.setOrigenTransaccion("transaccion2");
        movDeCuentaB.setFecha(new SimpleDateFormat("dd/MM/yyy").format(new Date()));
        movDeCuentaB.setHora("13");
        movDeCuentaB.setTipoDeOperacion(TipoOperacionMovimimiento.DEPOSITO_DE_CHEQUE);
        movDeCuentaB.setNumeroDeTicket("0000002");
        movDeCuentaB.setSucursalOrigen(sucursal);
        movDeCuentaB.setUsuarioBanco("usuario Bancario 2");
        movDeCuentaB.setImporteOperacion(new BigDecimal(ISBANStringUtils.importePtoFijo2Canonico("444")));
        movDeCuentaB.setEstado(EstadoOperacionMovimimiento.CONFIRMADA_CON_DIFERENCIA);

        listMovDeCuentas.add(movDeCuentaA);
        listMovDeCuentas.add(movDeCuentaB);

        return listMovDeCuentas;
    }

	/**
	 * Agregar Movimientos Cheques Y Valores.
	 *
	 * @param isDebito
	 *            the is debito
	 * @return the list
	 * @throws ImporteInvalidoException
	 *             the importe invalido exception
	 */
	private List<DetalleMovimientoChequesYValoresEntity> agregarMovimientosChequesYValores(Boolean isDebito)
			throws ImporteInvalidoException {
		List<DetalleMovimientoChequesYValoresEntity> listMovDeCuentas = new ArrayList<DetalleMovimientoChequesYValoresEntity>();

		DetalleMovimientoChequesYValoresEntity mov = new DetalleMovimientoChequesYValoresEntity();
		Sucursal sucursal = new Sucursal();
		sucursal.setIdSucursal("001");
		mov.setCheque(Boolean.TRUE);
		mov.setDebito(isDebito);
		mov.setDescripcion("Descripcion");
		Date fecha = new Date();
		mov.setFecha(fecha);
		mov.setFechaValor(fecha);
		mov.setImporteMovimiento(new BigDecimal(14));
		mov.setIsMovimientoEnDolares(Boolean.FALSE);
		listMovDeCuentas.add(mov);
		listMovDeCuentas.add(mov);
		return listMovDeCuentas;
	}

	/**
	 * Test valores pendientes.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testValoresPendientes() throws Exception {
		SesionCliente sessionClient = new SesionCliente();
		Cliente cliente = new Cliente();
		sessionClient.setCliente(cliente);
		Segmento segmento = new Segmento();
		cliente.setSegmento(segmento);

		MovimientosValoresPendientes movimientosValoresPendientes = new MovimientosValoresPendientes();
		movimientosValoresPendientes.setTipoCuenta(TipoCuenta.CUENTA_UNICA.getDescripcion());
		movimientosValoresPendientes.setCuenta("00000000002");
		movimientosValoresPendientes.setValoresDebito(agregarMovimientosChequesYValores(Boolean.TRUE));
		movimientosValoresPendientes.setValoresCredito(agregarMovimientosChequesYValores(Boolean.FALSE));
		byte[] excel = helper.hacerExcel(cliente, "valoresPendientes", movimientosValoresPendientes);
		Assert.assertNotNull(excel);
	}

}
