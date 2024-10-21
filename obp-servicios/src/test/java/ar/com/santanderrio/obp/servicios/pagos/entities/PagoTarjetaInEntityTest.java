/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.tarjetas.entities.ClienteMock;

/**
 * The Class PagoTarjetaInEntityTest.
 *
 * @author florencia.n.martinez
 */
@RunWith(MockitoJUnitRunner.class)
public class PagoTarjetaInEntityTest {

    /**
     * Obtener pago tarjeta in entity OK test.
     */
    @Test
    public void obtenerPagoTarjetaInEntityOKTest() {
        PagoInEntity pagoInEntity = new PagoInEntity();
        pagoInEntity.setCodigoEmpresa("UNCF");
        pagoInEntity.setIdentificacion("32323232");
        pagoInEntity.setTipoMonto("0");
        pagoInEntity.setTipoSeleccion("F");
        pagoInEntity.setNumeroFactura("");
        pagoInEntity.setNroTarjeta("4050710020980178");
        pagoInEntity.setMoneda("ARS");
        pagoInEntity.setMonto("20,00");
        pagoInEntity.setTipoCuentaTC(7);
        Cliente cliente = ClienteMock.infoCliente();
        cliente.setNombre("ALBERTO                             ");
        cliente.setApellido1("GONZALEZ                   ");
        cliente.setFechaNacimiento("19440612");
        cliente.setSexo("H");
        PagoTarjetaInEntity entity = new PagoTarjetaInEntity(pagoInEntity, cliente);

        Assert.assertNotNull(entity);
        Assert.assertEquals("UNCF", entity.getCodigoEmpresa());
        Assert.assertEquals("32323232           ", entity.getIdentificacion());
        Assert.assertEquals("0", entity.getTipoMonto());
        Assert.assertEquals("F", entity.getTipoSeleccion());
        Assert.assertEquals(StringUtils.repeat(" ", 20), entity.getNumeroFactura());
        Assert.assertEquals("31", entity.getTipoCuenta());
        Assert.assertEquals("4050710020980178", entity.getNroTarjeta());
        Assert.assertEquals("ARS", entity.getMoneda());
        Assert.assertEquals("00000000002000", entity.getMonto());
        Assert.assertEquals("0", entity.getModoAlta());
        Assert.assertEquals("01", entity.getCuotas());
        Assert.assertEquals("04", entity.getCodigo());
        Assert.assertEquals(StringUtils.repeat(" ", 4), entity.getVto());
        Assert.assertEquals("ALBERTO GONZALEZ              ", entity.getNombre());
        Assert.assertEquals("0", entity.getSexo());
        Assert.assertEquals("12061944", entity.getFecNac());
        Assert.assertEquals(StringUtils.repeat(" ", 10), entity.getTxnsps());
        Assert.assertEquals(StringUtils.repeat(" ", 8), entity.getNrocom());
        Assert.assertEquals(StringUtils.repeat(" ", 21), entity.getUsrfld());
    }
}
