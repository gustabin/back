/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.manager;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.servicios.compraventa.web.manager.impl.CompraVentaDolaresManagerImpl;
import ar.com.santanderrio.obp.servicios.compraventa.web.util.CompraVentaDolaresUtil;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.cuentas.service.CuentasService;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;

/**
 * The Class CompraVentaDolaresManagerTest.
 *
 * @author sabrina.cis
 */
@RunWith(MockitoJUnitRunner.class)
public class CompraVentaDolaresManagerTest {

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The cuentas service. */
    @Mock
    private CuentasService cuentasService;

    /** The compra O venta dolares util. */
    @Mock
    private CompraVentaDolaresUtil compraOVentaDolaresUtil;

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO;

    /** The respuesta factory. */
    @Mock
    private RespuestaFactory respuestaFactory;

    /** The compra venta dolares manager. */
    @InjectMocks
    private CompraVentaDolaresManagerImpl manager = new CompraVentaDolaresManagerImpl();

    /**
     * Es respuesta OK test.
     *
     * @return the cliente test
     * @throws ParseException
     *             the parse exception
     */
    @Test
    public void getClienteTest() throws ParseException {
        assertNotNull(false);
    }
}