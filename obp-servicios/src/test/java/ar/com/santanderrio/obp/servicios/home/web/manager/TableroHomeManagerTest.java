package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.obp.servicios.comun.challenge.softtoken.bo.SoftTokenBO;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.ItemMensajeRespuesta;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.impl.EstadisticaManagerImpl;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.home.entitites.HomeDTO;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.PuntoSuperclubHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.RefinancingHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SuperclubPlusHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.TableroHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.Caja;
import ar.com.santanderrio.obp.servicios.home.web.view.CajaHomeCuentaView;
import ar.com.santanderrio.obp.servicios.home.web.view.GrupoCajaHomeView;
import ar.com.santanderrio.obp.servicios.home.web.view.TableroHomeView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.premify.bo.impl.PremifyBOImpl;

/**
 * The Class TableroHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class TableroHomeManagerTest {

    /** The tablero home manager. */
    @InjectMocks
    private TableroHomeManager tableroHomeManager = new TableroHomeManagerImpl();

    /** The sesion parametros. */
    @Mock
    private SesionParametros sesionParametros;

    /** The cuenta home manager. */
    @Mock
    private CuentaHomeManager cuentaHomeManager;

    /** The tarjeta home manager. */
    @Mock
    private TarjetaHomeManager tarjetaHomeManager;

    /** The calendario pagos home manager. */
    @Mock
    private CalendarioPagosHomeManager calendarioPagosHomeManager;

    /** The prestamo home manager. */
    @Mock
    private PrestamoHomeManager prestamoHomeManager;

    @Mock
    private SeguroHomeManager seguroHomeManager;

    @Mock
    private InversionesHomeManager inversionesHomeManager;
    
    @Mock
    private InversionesHomeManager inversionesHomeBPManager;

    /** The estadistica manager. */
    @Mock
    EstadisticaManagerImpl estadisticaManager;

    /** The moduloPermiso BO. */
    @Mock
    private ModuloPermisoBO moduloPermisoBO;

    @Mock
    private AdministradorPermisos administradorPermisos;

    @Mock
    private PuntoSuperclubHomeManager puntoSuperclubHomeManager;
    
    @Mock
    private RefinancingHomeManager refinancingHomeManager;
    
    @Mock
    private PremifyBOImpl premifyBo;
    
    @Mock
    private SesionCliente sesionCliente;
    
    @Mock
	private PuntoSuperclubHomeManagerImpl puntosSuperclubHomeManager;
    
    @Mock
	private SuperclubPlusHomeManagerImpl superclubPlusHomeManagerImpl;
    
    @Mock
	private TransferenciasHomeManager transferenciasHomeManager;

    @Mock
	private PrestamoHomeManager prestamosHomeManager;

    @Mock
	private RefinancingHomeManagerImpl refinancingHomeManagerImpl;

    @Mock
    private SoftTokenBO softTokenBo;

    /**
     * Pre test.
     */
    @Before
    public void preTest() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        
        when(sesionCliente.getCliente()).thenReturn(new Cliente());
        when(prestamoHomeManager.obtenerAccion()).thenReturn(AccionController.IR_HOME_PRESTAMO);
        when(tarjetaHomeManager.obtenerAccion()).thenReturn(AccionController.IR_HOME_TARJETA);
        when(cuentaHomeManager.obtenerAccion()).thenReturn(AccionController.IR_HOME_CUENTA);
        when(calendarioPagosHomeManager.obtenerAccion()).thenReturn(AccionController.IR_HOME_CALENDARIO);
        when(seguroHomeManager.obtenerAccion()).thenReturn(AccionController.IR_HOME_SEGURO);
        when(puntoSuperclubHomeManager.obtenerAccion()).thenReturn(AccionController.IR_SUPERCLUB);
        when(refinancingHomeManager.obtenerAccion()).thenReturn(AccionController.IR_FINANCIAL_HEALTH);

        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setAccionController(AccionController.IR_CONSULTA);
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
                .thenReturn(moduloPermiso);

    }

    /**
     * Obtener cantidad ingresos test.
     */
    @Test
    @Ignore
    public void obtenerCantidadIngresosTest() {

        Respuesta<Boolean> respuestaAplicaCuentas = new Respuesta<Boolean>();
        respuestaAplicaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCuentas.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaTarjeta = new Respuesta<Boolean>();
        respuestaAplicaTarjeta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaTarjeta.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaCalendarioPagos = new Respuesta<Boolean>();
        respuestaAplicaCalendarioPagos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCalendarioPagos.setRespuesta(Boolean.TRUE);
        Respuesta<Boolean> respuestaAplicaPrestamos = new Respuesta<Boolean>();
        respuestaAplicaPrestamos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaPrestamos.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaSeguros = new Respuesta<Boolean>();
        respuestaAplicaSeguros.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaSeguros.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaInversiones = new Respuesta<Boolean>();
        respuestaInversiones.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaInversiones.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaPuntosSuperclub = new Respuesta<Boolean>();
        respuestaPuntosSuperclub.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPuntosSuperclub.setRespuesta(Boolean.TRUE);

        when(prestamoHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaPrestamos);
        when(prestamoHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(tarjetaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaTarjeta);
        when(tarjetaHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(cuentaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCuentas);
        when(cuentaHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(calendarioPagosHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCalendarioPagos);
        when(calendarioPagosHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(seguroHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaSeguros);
        when(seguroHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(inversionesHomeManager.aplicaGrupo()).thenReturn(respuestaInversiones);
        when(inversionesHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(puntoSuperclubHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(puntoSuperclubHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(refinancingHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(refinancingHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();
        ArgumentCaptor<HomeDTO> homeCaptor = ArgumentCaptor.forClass(HomeDTO.class);
        verify(sesionParametros).setHomeDTO(homeCaptor.capture());
        HomeDTO homeDTO = homeCaptor.getValue();
        assertEquals(homeDTO.getCantidadIngresos(), 1);
        assertNotNull(respuesta);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TABLERO_HOME,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

    /**
     * Obtener tablero segundo ingreso test.
     */
    @Test
    @Ignore
    public void obtenerTableroSegundoIngresoTest() {

        Respuesta<Boolean> respuestaAplicaCuentas = new Respuesta<Boolean>();
        respuestaAplicaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCuentas.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaTarjeta = new Respuesta<Boolean>();
        respuestaAplicaTarjeta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaTarjeta.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaCalendarioPagos = new Respuesta<Boolean>();
        respuestaAplicaCalendarioPagos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCalendarioPagos.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaPrestamos = new Respuesta<Boolean>();
        respuestaAplicaPrestamos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaPrestamos.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaSeguros = new Respuesta<Boolean>();
        respuestaAplicaSeguros.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaSeguros.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaInversiones = new Respuesta<Boolean>();
        respuestaInversiones.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaInversiones.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaPuntosSuperclub = new Respuesta<Boolean>();
        respuestaPuntosSuperclub.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPuntosSuperclub.setRespuesta(Boolean.TRUE);

        when(prestamoHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaPrestamos);
        when(prestamoHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());

        when(tarjetaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaTarjeta);
        when(tarjetaHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(cuentaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCuentas);
        when(cuentaHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(calendarioPagosHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCalendarioPagos);
        when(calendarioPagosHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(seguroHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaSeguros);
        when(seguroHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(inversionesHomeManager.aplicaGrupo()).thenReturn(respuestaInversiones);
        when(inversionesHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(puntoSuperclubHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(puntoSuperclubHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(refinancingHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(refinancingHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        

        HomeDTO homeDto = new HomeDTO();
        homeDto.setCantidadIngresos(1);
        when(sesionParametros.getHomeDTO()).thenReturn(homeDto);

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();
        assertEquals(homeDto.getCantidadIngresos(), 2);
        assertNotNull(respuesta);
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TABLERO_HOME,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

    /**
     * Obtener tablero test.
     */
    @Test
    @Ignore
    public void obtenerTableroTest() {
        GrupoCajaHomeView grupoCajaView = new GrupoCajaHomeView();
        List<Caja> cajas = new ArrayList<Caja>();

        Caja caja = new CajaHomeCuentaView();
        cajas.add(caja);

        grupoCajaView.setCajas(cajas);

        Respuesta<Boolean> respuestaAplicaCuentas = new Respuesta<Boolean>();
        respuestaAplicaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCuentas.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaTarjeta = new Respuesta<Boolean>();
        respuestaAplicaTarjeta.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaTarjeta.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaCalendarioPagos = new Respuesta<Boolean>();
        respuestaAplicaCalendarioPagos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCalendarioPagos.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaPrestamos = new Respuesta<Boolean>();
        respuestaAplicaPrestamos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaPrestamos.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaSeguros = new Respuesta<Boolean>();
        respuestaAplicaSeguros.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaSeguros.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaInversiones = new Respuesta<Boolean>();
        respuestaInversiones.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaInversiones.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaPuntosSuperclub = new Respuesta<Boolean>();
        respuestaPuntosSuperclub.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPuntosSuperclub.setRespuesta(Boolean.TRUE);
        
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setCajas(cajas);

        when(prestamoHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaPrestamos);
        when(prestamoHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(tarjetaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaTarjeta);
        when(tarjetaHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(cuentaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCuentas);
        when(cuentaHomeManager.obtenerGrupoElementos()).thenReturn(grupoCajaHomeView);
        when(calendarioPagosHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCalendarioPagos);
        when(calendarioPagosHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(seguroHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaSeguros);
        when(seguroHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(inversionesHomeManager.aplicaGrupo()).thenReturn(respuestaInversiones);
        when(inversionesHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(puntoSuperclubHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(puntoSuperclubHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(refinancingHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(refinancingHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();
        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.OK);
        assertNotNull(respuesta.getRespuesta().getGrupos().get(0));
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TABLERO_HOME,
                EstadisticasConstants.CODIGO_ESTADISTICAS_OK);

    }

    /**
     * Obtener tablero warning test.
     */
    @Test
    @Ignore
    public void obtenerTableroWarningTest() {
        GrupoCajaHomeView grupoCajaView = new GrupoCajaHomeView();
        List<Caja> cajas = new ArrayList<Caja>();

        Caja caja = new CajaHomeCuentaView();
        cajas.add(caja);

        grupoCajaView.setCajas(cajas);

        Respuesta<Boolean> respuestaAplicaCuentas = new Respuesta<Boolean>();
        respuestaAplicaCuentas.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCuentas.setRespuesta(Boolean.TRUE);

        ItemMensajeRespuesta itemRta = new ItemMensajeRespuesta();
        itemRta.setTipoError(TipoError.ERROR_TABLERO_HOME.getDescripcion());

        Respuesta<Boolean> respuestaAplicaTarjeta = new Respuesta<Boolean>();
        respuestaAplicaTarjeta.setEstadoRespuesta(EstadoRespuesta.WARNING);
        respuestaAplicaTarjeta.setRespuesta(Boolean.TRUE);
        respuestaAplicaTarjeta.add(itemRta);

        Respuesta<Boolean> respuestaAplicaCalendarioPagos = new Respuesta<Boolean>();
        respuestaAplicaCalendarioPagos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaCalendarioPagos.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaPrestamos = new Respuesta<Boolean>();
        respuestaAplicaPrestamos.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaPrestamos.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaAplicaSeguros = new Respuesta<Boolean>();
        respuestaAplicaSeguros.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaAplicaSeguros.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaInversiones = new Respuesta<Boolean>();
        respuestaInversiones.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaInversiones.setRespuesta(Boolean.TRUE);

        Respuesta<Boolean> respuestaPuntosSuperclub = new Respuesta<Boolean>();
        respuestaPuntosSuperclub.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaPuntosSuperclub.setRespuesta(Boolean.TRUE);
        
        GrupoCajaHomeView grupoCajaHomeView = new GrupoCajaHomeView();
        grupoCajaHomeView.setCajas(cajas);

        when(prestamoHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaPrestamos);
        when(prestamoHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(tarjetaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaTarjeta);
        when(tarjetaHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(cuentaHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCuentas);
        when(cuentaHomeManager.obtenerGrupoElementos()).thenReturn(grupoCajaHomeView);
        when(calendarioPagosHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaCalendarioPagos);
        when(calendarioPagosHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(seguroHomeManager.aplicaGrupo()).thenReturn(respuestaAplicaSeguros);
        when(seguroHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(inversionesHomeManager.aplicaGrupo()).thenReturn(respuestaInversiones);
        when(inversionesHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(puntoSuperclubHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(puntoSuperclubHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());
        when(refinancingHomeManager.aplicaGrupo()).thenReturn(respuestaPuntosSuperclub);
        when(refinancingHomeManager.obtenerGrupoElementos()).thenReturn(new GrupoCajaHomeView());

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();
        assertNotNull(respuesta);
        assertNotNull(respuesta.getRespuesta());
        assertEquals(respuesta.getEstadoRespuesta(), EstadoRespuesta.WARNING);
        assertEquals(respuesta.getItemsMensajeRespuesta().size(), 1);
        assertEquals(respuesta.getItemsMensajeRespuesta().get(0).getTipoError(),
                TipoError.ERROR_TABLERO_HOME.getDescripcion());

        assertNotNull(respuesta.getRespuesta().getGrupos().get(0));
        verify(estadisticaManager).add(EstadisticasConstants.CODIGO_TABLERO_HOME,
                EstadisticasConstants.CODIGO_ESTADISTICAS_PARCIAL);

    }
    
    @Test
    public void obtenerTablero_permisoVerBuscadorDashboardActivado() {
        mockNoAplicaNingunGrupoHome();
        mockModuloPermiso(AccionController.VER_BUSCADOR_DASHBOARD, ModuloEstado.MOSTRAR);

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();
        
        assertTrue(respuesta.getRespuesta().isMostrarBuscador());
    }

    @Test
    public void obtenerTablero_permisoVerBuscadorDashboardDesactivado() {
        mockNoAplicaNingunGrupoHome();
        mockModuloPermiso(AccionController.VER_BUSCADOR_DASHBOARD, ModuloEstado.OCULTAR);
        
        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();
        
        assertFalse(respuesta.getRespuesta().isMostrarBuscador());
    }

    @Test
    public void obtenerTablero_permisoVerOnboardingsActivado() {
        mockNoAplicaNingunGrupoHome();
        mockModuloPermiso(AccionController.VER_ONBOARDING, ModuloEstado.MOSTRAR);

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();

        assertTrue(respuesta.getRespuesta().isMostrarOnboarding());
    }

    @Test
    public void obtenerTablero_permisoVerOnboardingsDesactivado() {
        mockNoAplicaNingunGrupoHome();
        mockModuloPermiso(AccionController.VER_ONBOARDING, ModuloEstado.OCULTAR);

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();

        assertFalse(respuesta.getRespuesta().isMostrarOnboarding());
    }


    @Test
    public void obtenerTablero_clienteTieneSoftToken() {
        mockNoAplicaNingunGrupoHome();
        mockClienteTieneToken();

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();

        assertTrue(respuesta.getRespuesta().isHasTp());
    }

    @Test
    public void obtenerTablero_clienteNoTieneSoftToken() {
        mockNoAplicaNingunGrupoHome();
        mockClienteNoTieneToken();

        Respuesta<TableroHomeView> respuesta = tableroHomeManager.obtenerTablero();

        assertFalse(respuesta.getRespuesta().isHasTp());
    }

    private void mockModuloPermiso(AccionController accion, ModuloEstado estado) {
    	ModuloPermiso moduloPermiso = new ModuloPermiso();
    	moduloPermiso.setAccionController(accion);
        moduloPermiso.setModuloEstado(estado);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(accion))
                .thenReturn(moduloPermiso);
    }

    private void mockNoAplicaNingunGrupoHome() {
        mockNoAplica(cuentaHomeManager);
        mockNoAplica(tarjetaHomeManager);
        mockNoAplica(puntosSuperclubHomeManager);
        mockNoAplica(superclubPlusHomeManagerImpl);
        mockNoAplica(calendarioPagosHomeManager);
        mockNoAplica(transferenciasHomeManager);
        mockNoAplica(inversionesHomeManager);
        mockNoAplica(inversionesHomeBPManager);
        mockNoAplica(prestamosHomeManager);
        mockNoAplica(seguroHomeManager);
        mockNoAplica(refinancingHomeManagerImpl);
    }

    private void mockNoAplica(GrupoHomeManager grupoHomeManager) {
        Respuesta<Boolean> respuestaFalse = new Respuesta<Boolean>();
        respuestaFalse.setEstadoRespuesta(EstadoRespuesta.OK);
        respuestaFalse.setRespuesta(false);
    	when(grupoHomeManager.aplicaGrupo()).thenReturn(respuestaFalse);
    }

    private void mockClienteTieneToken() {
        when(softTokenBo.tieneSoftToken()).thenReturn(true);
    }

    private void mockClienteNoTieneToken() {
        when(softTokenBo.tieneSoftToken()).thenReturn(false);
    }

}
