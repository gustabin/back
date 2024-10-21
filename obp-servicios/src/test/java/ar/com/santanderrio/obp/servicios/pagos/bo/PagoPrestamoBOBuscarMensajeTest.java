package ar.com.santanderrio.obp.servicios.pagos.bo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.mensaje.entities.Mensaje;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.bo.impl.MensajeBOImpl;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoPrestamoEnum;
import ar.com.santanderrio.obp.servicios.pagos.bo.impl.PagoPrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.pagos.entities.ComprobantePrestamo;
import ar.com.santanderrio.obp.servicios.pagos.entities.Prestamo;

/**
 * The Class PagoPrestamoBOBuscarMensajeTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoPrestamoBOBuscarMensajeTest {

    /** The pago prestamo BO. */
    @InjectMocks
    private PagoPrestamoBOImpl pagoPrestamoBO = new PagoPrestamoBOImpl();

    /** The mensaje BO. */
    @InjectMocks
    @Spy
    private MensajeBOImpl mensajeBO = new MensajeBOImpl();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /**
     * Buscar mensaje OK test.
     */
    @Test
    public void buscarMensajeOKTest() {

        Prestamo prestamo = new Prestamo();
        Respuesta<Mensaje> respuestaMensaje = new Respuesta<Mensaje>();
        Cuenta cuenta = new Cuenta();
        Mensaje mensaje = new Mensaje();

        mensaje.setMensaje("{0} {1} {2} {3} {4}");
        respuestaMensaje.setRespuesta(mensaje);
        cuenta.setNroSucursal("123");
        cuenta.setAlias("Cuenta Alias");
        prestamo.setCuenta(cuenta);
        prestamo.setNumeroRecibo("200688");
        prestamo.setNumeroCuentaProducto("1234567");
        prestamo.setTipoPrestamoEnum(TipoPrestamoEnum.PERSONAL);
        prestamo.setImporteTotalCuota(new BigDecimal("5000"));

        when(mensajeDAO.obtenerMensajePorCodigo(Matchers.anyString())).thenReturn(mensaje);

        ComprobantePrestamo comprobantePrestamo = new ComprobantePrestamo();
        comprobantePrestamo.setImporteDebito(new BigDecimal(5500.23));
        String respuesta = pagoPrestamoBO.obtenerMensajePagoOk(prestamo, comprobantePrestamo);

        assertNotNull(respuesta);
        assertEquals(respuesta, "200688 " + TipoPrestamoEnum.PERSONAL.getDescripcion()
                + " \"Cuenta Alias\" 123-00000123456/7 $5.500,23");

    }

}
