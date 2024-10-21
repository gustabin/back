package ar.com.santanderrio.obp.servicios.echeq.utils;

import ar.com.santanderrio.obp.generated.webservices.echeq.Aval;
import ar.com.santanderrio.obp.generated.webservices.echeq.Beneficiario;
import ar.com.santanderrio.obp.generated.webservices.echeq.Cheque;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class ECheqUtilsTest {

    @Test
    public void obtenerTipoCheque_fechaEmisionYPago() throws ParseException {
        assertEquals(ECheqUtils.TIPO_CHEQUE_DIFERIDO, ECheqUtils.obtenerTipoCheque("2020-10-10", "2020-10-20"));
        assertEquals(ECheqUtils.TIPO_CHEQUE_COMUN, ECheqUtils.obtenerTipoCheque("2020-10-10", "2020-10-10"));
    }

    @Test
    public void eliminarEspacios() {
        assertNull(ECheqUtils.eliminarEspacios(null));
        assertEquals("sin espacios", ECheqUtils.eliminarEspacios("   sin    espacios   "));
    }

    @Test
    public void obtenerDocumento() {
        assertEquals("30-61734087-5", ECheqUtils.obtenerDocumento("30617340875"));
    }

    @Test
    public void formatearFechaIatx() {
        assertEquals("2020-10-01", ECheqUtils.formatearFechaIatx("01/10/2020", "dd/MM/yyyy"));
    }

    @Test
    public void isAvalista() {
        Cliente cliente = new Cliente();
        cliente.setNumeroCUILCUIT("30-61734087-5");
        Aval aval = new Aval();
        aval.setAvalDocumento("30617340875");
        assertTrue(ECheqUtils.isAvalista(cliente, aval));
    }

    @Test
    public void isBeneficiario() {
        Cliente cliente = new Cliente();
        cliente.setNumeroCUILCUIT("30-61734087-5");
        Cheque cheque = new Cheque();
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setBeneficiarioDocumento("30617340875");
        cheque.setTenencia(beneficiario);
        assertTrue(ECheqUtils.isBeneficiario(cliente, cheque));
    }

    @Test
    public void obtenerFechaEmision() {
        assertNull(ECheqUtils.obtenerFechaEmision(null));
        assertNull(ECheqUtils.obtenerFechaEmision("noEsUnaFecha"));
        assertEquals("20201020", ECheqUtils.obtenerFechaEmision("20/10/2020"));
    }
}
