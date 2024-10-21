package ar.com.santanderrio.obp.servicios.marcaviajero.web.manager;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;
import ar.com.santanderrio.obp.servicios.marcaviajero.bo.DatosTarjetasBOImpl;
import ar.com.santanderrio.obp.servicios.marcaviajero.entities.InfoTarjetas;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class DatosTarjetasBoTest {

    private DatosTarjetasBOImpl datosTarjetasBO;

    @Before
    public void setUp() {
        this.datosTarjetasBO = new DatosTarjetasBOImpl();
    }

    @Test
    public void testInactiveVisaIsNotAdded() {
        Cuenta creditCard = mockCard(TipoCuenta.VISA, false);
        List<InfoTarjetas> response = datosTarjetasBO.preparatInfoTarjetas(Collections.singletonList(creditCard));
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void testActiveVisaIsAdded() {
        Cuenta creditCard = mockCard(TipoCuenta.VISA, true);
        mockExtraData(creditCard);
        List<InfoTarjetas> response = datosTarjetasBO.preparatInfoTarjetas(Collections.singletonList(creditCard));
        Assert.assertFalse(response.isEmpty());
    }

    @Test
    public void testInactiveAmexIsNotAdded() {
        Cuenta creditCard = mockCard(TipoCuenta.AMEX, false);
        List<InfoTarjetas> response = datosTarjetasBO.preparatInfoTarjetas(Collections.singletonList(creditCard));
        Assert.assertTrue(response.isEmpty());
    }

    @Test
    public void testActiveAmexIsAdded() {
        Cuenta creditCard = mockCard(TipoCuenta.AMEX, true);
        mockExtraData(creditCard);
        List<InfoTarjetas> response = datosTarjetasBO.preparatInfoTarjetas(Collections.singletonList(creditCard));
        Assert.assertFalse(response.isEmpty());
    }

    private Cuenta mockCard(TipoCuenta tipoCuenta, boolean isActive) {
        Cuenta creditCard = Mockito.mock(Cuenta.class);
        Mockito.when(creditCard.getTipoCuentaEnum()).thenReturn(tipoCuenta);
        Mockito.when(creditCard.esEstadoTarjetaCredito()).thenReturn(isActive);
        return creditCard;
    }

    private void mockExtraData(Cuenta creditCard) {
        Cliente client = Mockito.mock(Cliente.class);
        Mockito.when(creditCard.getNroTarjetaCredito()).thenReturn("4123412341234123");
        Mockito.when(creditCard.getCalidadDeParticipacion()).thenReturn("TI");
        Mockito.when(creditCard.getAlias()).thenReturn("ALIAS");
        Mockito.when(creditCard.getNroCuentaProducto()).thenReturn("PRODUCTO");
        Mockito.when(creditCard.getCliente()).thenReturn(client);
        Mockito.when(client.getApellido1()).thenReturn("APELLIDO");
    }
}
