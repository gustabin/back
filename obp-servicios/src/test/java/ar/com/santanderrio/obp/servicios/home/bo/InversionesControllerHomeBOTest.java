package ar.com.santanderrio.obp.servicios.home.bo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.bo.CuentaBO;
import ar.com.santanderrio.obp.servicios.home.bo.impl.InversionesControllerHomeBOImpl;

/**
 * The Class InversionesControllerHomeBOTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class InversionesControllerHomeBOTest {

    /** The inversiones controller home BO. */
    @InjectMocks
    private InversionesControllerHomeBO inversionesControllerHomeBO = new InversionesControllerHomeBOImpl();
    
    @Mock
    private CuentaBO cuentaBO;

    /**
     * Menu consultas test.
     */
    @Test
    public void menuConsultasTest() {

        Cliente cliente = new Cliente();
        
        Mockito.when(cuentaBO.hasCuentasMonetariasActivas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);

        assertTrue(inversionesControllerHomeBO.aplicaAccionesBonos(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaConsolidado(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaFondos(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaLicitaciones(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaPlazoFijo(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaTitulos(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaPyl(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaServiciosInversion(cliente));
        assertTrue(inversionesControllerHomeBO.aplicaDinamico(cliente));

    }

}
