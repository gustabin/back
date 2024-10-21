package ar.com.santanderrio.obp.servicios.billetera.web.util;

import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

public class BilleteraUtilsTest {

    private static final DateFormat DF = new SimpleDateFormat(BilleteraConstants.FMT_YYYYMMDD);

    @Test
    public void llenarConCerosDerecha() {
        assertEquals("123000", BilleteraUtils.llenarConCerosDerecha("123", 6));
        assertEquals("123", BilleteraUtils.llenarConCerosDerecha("123", 3));
        assertEquals("3", BilleteraUtils.llenarConCerosDerecha("123", 1));
    }

    @Test
    public void checkEdad_mayorDeEdad() throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("19600101");
        assertTrue(BilleteraUtils.checkEdad(cliente));
    }

    @Test
    public void checkEdad_menorDeEdad() throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento(DF.format(new Date()));
        assertFalse(BilleteraUtils.checkEdad(cliente));
    }

    @Test(expected = ParseException.class)
    public void checkEdad_exception() throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("noEsUnaFecha");
        BilleteraUtils.checkEdad(cliente);
    }

    @Test
    public void getCtaFromNroTrj_clienteSinCuentas() {
        Cliente cliente = new Cliente();
        assertNull(BilleteraUtils.getCtaFromNroTrj(cliente, "nada"));
    }

    @Test
    public void getCtaFromNroTrj_clienteConCuenta() {
        String tarjeta = "1234";
        Cliente cliente = new Cliente();
        Cuenta cuenta = new Cuenta();
        cuenta.setNroTarjetaCredito(tarjeta);
        cliente.setCuentas(Arrays.asList(cuenta));
        assertEquals(cuenta, BilleteraUtils.getCtaFromNroTrj(cliente, tarjeta));
    }

    @Test
    public void getEdad_edadValida() throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento(DF.format(new Date()));
        assertEquals(0, BilleteraUtils.getEdad(cliente));
    }

    @Test(expected = ParseException.class)
    public void getEdad_exception() throws ParseException {
        Cliente cliente = new Cliente();
        cliente.setFechaNacimiento("noEsUnaFecha");
        BilleteraUtils.getEdad(cliente);
    }

    @Test(expected = NumberFormatException.class)
    public void getDescCta_cuentaInvalida() {
        BilleteraUtils.getDescCta("tipoCtaInválido");
    }

    @Test
    public void getDescCta_cuentaValida() {
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_CORRIENTE_PESOS;
        assertEquals(tipoCuenta.getDescripcion(), BilleteraUtils.getDescCta(String.valueOf(tipoCuenta.getCodigo())));
    }

    @Test
    public void getDescCta_cuentaUnica() {
        TipoCuenta tipoCuenta = TipoCuenta.CUENTA_UNICA_DOLARES;
        assertEquals(BilleteraConstants.CUENTA_UNICA, BilleteraUtils.getDescCta(String.valueOf(tipoCuenta.getCodigo())));
    }
}
