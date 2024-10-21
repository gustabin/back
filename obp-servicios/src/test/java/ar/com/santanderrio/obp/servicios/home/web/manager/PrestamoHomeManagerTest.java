package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.PrestamoHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomePrestamosView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.bo.PrestamoBO;
import ar.com.santanderrio.obp.servicios.prestamos.bo.impl.PrestamoBOImpl;
import ar.com.santanderrio.obp.servicios.prestamos.dto.InfoPrestamosDTO;

/**
 * The Class PrestamoHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrestamoHomeManagerTest {

    
    private static final String PRESTAMOS_HIPOTECARIOS_TITULO = "S\u00DAPER PR\u00C9STAMO HIPOTECARIO";

    /** The prestamo home manager. */
    @InjectMocks
    private PrestamoHomeManager prestamoHomeManager = new PrestamoHomeManagerImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;
    
    /** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Mock
    private PrestamoBO prestamoBO = new PrestamoBOImpl();

    /** The estadistica manager. */
    @Mock
    private EstadisticaManager estadisticaManager;
 
    /** The modulo permiso BO. */
    @Mock
    private ModuloPermisoBOImpl moduloPermisoBOImpl;

    /**
     * Obtener cajas cuentas test.
     */
    @Test
    public void obtenerCajasCuentasTest() {
        Respuesta<Boolean> respuestaAplicaGrupo = prestamoHomeManager.aplicaGrupo();
        assertNotNull(respuestaAplicaGrupo);
        assertTrue(respuestaAplicaGrupo.getRespuesta());
        InfoPrestamosDTO infoPrestamosDTO = new InfoPrestamosDTO();
        
        infoPrestamosDTO.setPrestamosHipotecarios(1);
        when(prestamoBO.obtenerCantidadPrestamosPorClase(null)).thenReturn(infoPrestamosDTO);
        when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());

        GrupoCajaHomeView grupoCajaHomeView = prestamoHomeManager.obtenerGrupoElementos();
        assertNotNull(grupoCajaHomeView);

        List<Caja> cajas = grupoCajaHomeView.getCajas();
        assertTrue(cajas.size() > 0);
        CajaHomePrestamosView caja = (CajaHomePrestamosView) cajas.get(0);

        assertEquals(caja.getEncabezado(), WordUtils.capitalizeFully(PRESTAMOS_HIPOTECARIOS_TITULO));
        assertTrue(caja.getIsPrestamos());

    }

}
