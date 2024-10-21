package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.dto.EventosComercialesDTO;
import ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.RefinancingHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeMicrofrontView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;

@RunWith(MockitoJUnitRunner.class)
public class RefinancingHomeManagerTest {
	
	/** The prestamo home manager. */
    @InjectMocks
    private RefinancingHomeManager refinancingHomeManager = new RefinancingHomeManagerImpl();
    
    /** The sesion parametros. */
	@Mock
	private SesionParametros sesionParametros;
	
	/** The consultasControllerHomeBO*/
	@Mock
	private ConsultasControllerHomeBO consultasControllerHomeBO;
	
	 /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Test
    public void obtenerCajasCuentasTest() {
        Respuesta<Boolean> respuestaAplicaGrupo = refinancingHomeManager.aplicaGrupo();
        assertNotNull(respuestaAplicaGrupo);
        assertTrue(respuestaAplicaGrupo.getRespuesta());
        
        when(sesionParametros.getOfertasComerciales()).thenReturn(new EventosComercialesDTO());
        when(consultasControllerHomeBO.aplicaRecuperaciones()).thenReturn(true);

        GrupoCajaHomeView grupoCajaHomeView = refinancingHomeManager.obtenerGrupoElementos();
        assertNotNull(grupoCajaHomeView);

        List<Caja> cajas = grupoCajaHomeView.getCajas();
        assertTrue(cajas.size() > 0);
        CajaHomeMicrofrontView caja = (CajaHomeMicrofrontView) cajas.get(0);

        assertEquals("Tu Salud Financiera", caja.getEncabezado());
        assertEquals(AccionController.IR_FINANCIAL_HEALTH, refinancingHomeManager.obtenerAccion());
    }
}
