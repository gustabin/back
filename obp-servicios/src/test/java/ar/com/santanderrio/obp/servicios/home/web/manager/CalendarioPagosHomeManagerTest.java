package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.CalendarioPagosHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeCalendarioPagosView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

/**
 * The Class CalendarioPagosHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CalendarioPagosHomeManagerTest {

    /** The calendario pagos home manager. */
    @InjectMocks
    private CalendarioPagosHomeManager calendarioPagosHomeManager = new CalendarioPagosHomeManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /**
     * Obtener cajas cuentas test.
     */
    @Test
    public void obtenerCajasCuentasTest() {
        Respuesta<Boolean> respuestaAplicaGrupo = calendarioPagosHomeManager.aplicaGrupo();
        assertNotNull(respuestaAplicaGrupo);
        assertTrue(respuestaAplicaGrupo.getRespuesta());

        GrupoCajaHomeView grupoCajaHomeView = calendarioPagosHomeManager.obtenerGrupoElementos();
        assertNotNull(grupoCajaHomeView);

        List<Caja> cajas = grupoCajaHomeView.getCajas();
        assertTrue(cajas.size() > 0);

        CajaHomeCalendarioPagosView caja = (CajaHomeCalendarioPagosView) cajas.get(0);

        assertEquals(caja.getTitulo(), "Pago de Servicios e Impuestos");
        assertTrue(caja.getIsCalendarioPagos());

    }

}
