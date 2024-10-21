package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.MensajeDAO;
import ar.com.santanderrio.obp.servicios.comun.mensaje.dao.impl.MensajeDAOImpl;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.HomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeCuentaView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;

/**
 * The Class HomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeManagerTest {

    /** The home manager. */
    @InjectMocks
    private HomeManager homeManager = new HomeManagerImpl();

    /** The tablero home manager. */
    @Mock
    private TableroHomeManager tableroHomeManager;

    /** The respuesta factory. */
    @Spy
    @InjectMocks
    private RespuestaFactory respuestaFactory = new RespuestaFactory();

    /** The mensaje DAO. */
    @Mock
    private MensajeDAO mensajeDAO = new MensajeDAOImpl();

    /** The sesion cliente. */
    @Mock
    private SesionCliente sesionCliente;

    /**
     * Obtener tablero test.
     */
    @Test
    public void obtenerTableroTest() {

        Respuesta<TableroHomeView> respuestaTableroHomeView = new Respuesta<TableroHomeView>();
        TableroHomeView tableroHomeView = new TableroHomeView();
        List<GrupoCajaHomeView> listGrupoCajaHomeView = new ArrayList<GrupoCajaHomeView>();
        GrupoCajaHomeView grupoCajaHomeView1 = new GrupoCajaHomeView();
        GrupoCajaHomeView grupoCajaHomeView2 = new GrupoCajaHomeView();
        List<Caja> listCajas1 = new ArrayList<Caja>();
        List<Caja> listCajas2 = new ArrayList<Caja>();
        Caja caja1 = new CajaHomeCuentaView();
        Caja caja2 = new CajaHomeCuentaView();
        Caja caja3 = new CajaHomeCuentaView();

        listCajas1.add(caja1);
        listCajas1.add(caja2);
        listCajas2.add(caja3);
        grupoCajaHomeView1.setCajas(listCajas1);
        grupoCajaHomeView2.setCajas(listCajas2);
        listGrupoCajaHomeView.add(grupoCajaHomeView1);
        listGrupoCajaHomeView.add(grupoCajaHomeView2);
        tableroHomeView.setGrupos(listGrupoCajaHomeView);

        respuestaTableroHomeView.setRespuesta(tableroHomeView);
        respuestaTableroHomeView.setEstadoRespuesta(EstadoRespuesta.OK);

        when(tableroHomeManager.obtenerTablero()).thenReturn(respuestaTableroHomeView);

        Respuesta<TableroHomeView> respuesta = homeManager.obtenerTablero();

        assertNotNull(respuesta);
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);

        TableroHomeView tablero = respuesta.getRespuesta();

        assertNotNull(tablero);
        assertEquals(tablero.getGrupos().size(), 2);
        assertEquals(tablero.getGrupos().get(0).getCajas().size(), 2);
        assertEquals(tablero.getGrupos().get(1).getCajas().size(), 1);

    }

}