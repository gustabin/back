package ar.com.santanderrio.obp.servicios.home.web.manager;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.home.bo.ConsultasControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.bo.InversionesControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.bo.SolicitudesControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.bo.TransaccionesControllerHomeBO;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.AdministradorPermisos;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.ControllerHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.MenuHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionConsultasHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionInversionesHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionSolicitudesHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionSuperClubHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.manager.impl.SeccionTransaccionesHomeManagerImpl;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.home.web.view.ControllerView;
import ar.com.santanderrio.obp.servicios.home.web.view.SeccionView;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
/**
 * The Class ControllerHomeManagerTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerHomeManagerTest {

    /** The controller home manager. */
    @InjectMocks
    private ControllerHomeManagerImpl controllerHomeManager = new ControllerHomeManagerImpl();

    /** The consultas controller home BO. */
    @Mock
    private ConsultasControllerHomeBO consultasControllerHomeBO;

    /** The transacciones controller home BO. */
    @Mock
    private TransaccionesControllerHomeBO transaccionesControllerHomeBO;

    /** The inversiones controller home BO. */
    @Mock
    private InversionesControllerHomeBO inversionesControllerHomeBO;

    /** The solicitudes controller home BO. */
    @Mock
    private SolicitudesControllerHomeBO solicitudesControllerHomeBO;

    /** The seccion consultas home manager. */
    @Spy
    @InjectMocks
    private SeccionConsultasHomeManagerImpl seccionConsultasHomeManager = new SeccionConsultasHomeManagerImpl();

    /** The seccion inversiones home manager. */
    @Spy
    @InjectMocks
    private SeccionInversionesHomeManagerImpl seccionInversionesHomeManager = new SeccionInversionesHomeManagerImpl();

    /** The seccion transacciones home manager. */
    @Spy
    @InjectMocks
    private SeccionTransaccionesHomeManagerImpl seccionTransaccionesHomeManager = new SeccionTransaccionesHomeManagerImpl();

    /** The seccion solicitudes home manager. */
    @Spy
    @InjectMocks
    private SeccionSolicitudesHomeManagerImpl seccionSolicitudesHomeManager = new SeccionSolicitudesHomeManagerImpl();

    /** The seccion super club home manager. */
    @Spy
    @InjectMocks
    private SeccionSuperClubHomeManagerImpl seccionSuperClubHomeManager = new SeccionSuperClubHomeManagerImpl();

    /** The moduloPermiso BO. */
    @Mock
    private ModuloPermisoBO moduloPermisoBO;
    
    @Mock
    private AdministradorPermisos administradorPermisos;
    
    @Mock MenuHomeManager menuHomeManager = new MenuHomeManagerImpl();

    /**
     * Pre test.
     */
    @Before
    public void preTest() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setAccionController(AccionController.IR_CONSULTA);
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class)))
                .thenReturn(moduloPermiso);

    }

    /**
     * Obtener controller consultas todas test.
     */
    @Ignore
    @Test
    public void obtenerControllerConsultasTodasTest() {
        Cliente cliente = new Cliente();
        when(consultasControllerHomeBO.aplicaCuentas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaTarjetas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaPrestamos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaSeguros(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaAhorrosBeneficios(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaComprobantes(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaResumenImpositivo(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaTurnosOnline(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);


        when(transaccionesControllerHomeBO.aplicaTransferencias(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaCalendarioPagos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaPagoTarjeta(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaEnvioEfectivo(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaBilleteraVirtual(Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaDescuentoCheques(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaPagoHaberes(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);

        when(inversionesControllerHomeBO.aplicaConsolidado(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaPlazoFijo(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaFondos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaTitulos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaLicitaciones(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);

        when(solicitudesControllerHomeBO.aplicaCuentasPaquetes(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaPrestamos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaTarjetas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaSeguros(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaOtrosMediosPago(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);

        ControllerView controllerView = controllerHomeManager.obtenerController(cliente);
        assertEquals(controllerView.getSecciones().get(0).getTitulo(), "Consultas");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(0).getTitulo(), "Cuentas");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(1).getTitulo(), "Tarjetas");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(2).getTitulo(), "Préstamos");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(3).getTitulo(), "Seguros");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(4).getTitulo(), "Ahorros y Beneficios");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(5).getTitulo(), "Comprobantes");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(6).getTitulo(), "Tarjeta Monedero");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(7).getTitulo(), "Resumen Impositivo");

        assertEquals(controllerView.getSecciones().get(1).getItems().get(0).getTitulo(), "Calendario de Pagos");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(1).getTitulo(), "Transferencias");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(2).getTitulo(), "Pago de Tarjeta");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(3).getTitulo(), "Envío de Efectivo");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(4).getTitulo(), "Billetera Virtual");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(6).getTitulo(), "Pago de Haberes");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(5).getTitulo(), "Descuento de Cheques");
        

        assertEquals(controllerView.getSecciones().get(2).getItems().get(0).getTitulo(), "Tenencia Consolidada");
        assertEquals(controllerView.getSecciones().get(2).getItems().get(1).getTitulo(), "Plazos Fijos");
        assertEquals(controllerView.getSecciones().get(2).getItems().get(2).getTitulo(), "Fondos Comunes de Inversión");
        assertEquals(controllerView.getSecciones().get(2).getItems().get(3).getTitulo(), "Títulos Valores");

        assertEquals(controllerView.getSecciones().get(3).getItems().get(0).getTitulo(), "Cuentas y Paquetes");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(1).getTitulo(), "Préstamos");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(2).getTitulo(), "Tarjetas");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(3).getTitulo(), "Seguros");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(4).getTitulo(), "Centro de Ayuda");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(5).getTitulo(), "Otros Medios de Pago");
    }

    /**
     * Obtener controller algunas opciones test.
     */
    @Ignore
    @Test
    public void obtenerControllerAlgunasOpcionesTest() {
        Cliente cliente = new Cliente();
        when(consultasControllerHomeBO.aplicaCuentas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaTarjetas(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(consultasControllerHomeBO.aplicaPrestamos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaSeguros(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(consultasControllerHomeBO.aplicaComprobantes(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaAhorrosBeneficios(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(consultasControllerHomeBO.aplicaResumenImpositivo(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(consultasControllerHomeBO.aplicaTurnosOnline(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        
        when(transaccionesControllerHomeBO.aplicaTransferencias(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaCalendarioPagos(Matchers.any(Cliente.class)))
                .thenReturn(Boolean.FALSE);
        when(transaccionesControllerHomeBO.aplicaPagoTarjeta(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaEnvioEfectivo(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(transaccionesControllerHomeBO.aplicaBilleteraVirtual(Matchers.any(Cliente.class)))
                .thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaCesionCheques(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(transaccionesControllerHomeBO.aplicaPagoHaberes(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);

        when(inversionesControllerHomeBO.aplicaConsolidado(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(inversionesControllerHomeBO.aplicaPlazoFijo(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaFondos(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(inversionesControllerHomeBO.aplicaTitulos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaLicitaciones(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);

        when(solicitudesControllerHomeBO.aplicaCuentasPaquetes(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(solicitudesControllerHomeBO.aplicaPrestamos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaTarjetas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaSeguros(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaOtrosMediosPago(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);

        ControllerView controllerView = controllerHomeManager.obtenerController(cliente);
        assertEquals(controllerView.getSecciones().get(0).getTitulo(), "Consultas");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(0).getTitulo(), "Cuentas");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(1).getTitulo(), "Préstamos");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(2).getTitulo(), "Comprobantes");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(3).getTitulo(), "Tarjeta Monedero");

        assertEquals(controllerView.getSecciones().get(1).getItems().get(0).getTitulo(), "Transferencias");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(1).getTitulo(), "Pago de Tarjeta");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(2).getTitulo(), "Billetera Virtual");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(3).getTitulo(), "Pago de Haberes");

        assertEquals(controllerView.getSecciones().get(2).getItems().get(0).getTitulo(), "Plazos Fijos");
        assertEquals(controllerView.getSecciones().get(2).getItems().get(1).getTitulo(), "Títulos Valores");

        assertEquals(controllerView.getSecciones().get(3).getItems().get(0).getTitulo(), "Préstamos");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(1).getTitulo(), "Tarjetas");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(2).getTitulo(), "Seguros");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(3).getTitulo(), "Centro de Ayuda");
    }

    /**
     * Obtener controller algunas opciones 2 test.
     */
    @Ignore
    @Test
    public void obtenerControllerAlgunasOpciones2Test() {
        Cliente cliente = new Cliente();
        when(consultasControllerHomeBO.aplicaCuentas(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(consultasControllerHomeBO.aplicaTarjetas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaPrestamos(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(consultasControllerHomeBO.aplicaSeguros(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaComprobantes(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(consultasControllerHomeBO.aplicaAhorrosBeneficios(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaResumenImpositivo(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(consultasControllerHomeBO.aplicaTurnosOnline(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        
        when(transaccionesControllerHomeBO.aplicaTransferencias(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(transaccionesControllerHomeBO.aplicaCalendarioPagos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaPagoTarjeta(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(transaccionesControllerHomeBO.aplicaEnvioEfectivo(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaBilleteraVirtual(Matchers.any(Cliente.class)))
                .thenReturn(Boolean.FALSE);
        when(transaccionesControllerHomeBO.aplicaDescuentoCheques(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(transaccionesControllerHomeBO.aplicaPagoHaberes(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);

        when(inversionesControllerHomeBO.aplicaConsolidado(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaPlazoFijo(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(inversionesControllerHomeBO.aplicaFondos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(inversionesControllerHomeBO.aplicaTitulos(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);
        when(inversionesControllerHomeBO.aplicaLicitaciones(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);

        when(solicitudesControllerHomeBO.aplicaCuentasPaquetes(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaPrestamos(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaTarjetas(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaSeguros(Matchers.any(Cliente.class))).thenReturn(Boolean.TRUE);
        when(solicitudesControllerHomeBO.aplicaOtrosMediosPago(Matchers.any(Cliente.class))).thenReturn(Boolean.FALSE);

        ControllerView controllerView = controllerHomeManager.obtenerController(cliente);
        assertEquals(controllerView.getSecciones().get(0).getTitulo(), "Consultas");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(0).getTitulo(), "Tarjetas");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(1).getTitulo(), "Seguros");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(2).getTitulo(), "Ahorros y Beneficios");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(3).getTitulo(), "Tarjeta Monedero");
        assertEquals(controllerView.getSecciones().get(0).getItems().get(4).getTitulo(), "Resumen Impositivo");

        assertEquals(controllerView.getSecciones().get(1).getItems().get(0).getTitulo(), "Calendario de Pagos");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(1).getTitulo(), "Envío de Efectivo");
        assertEquals(controllerView.getSecciones().get(1).getItems().get(2).getTitulo(), "Descuento de Cheques");

        assertEquals(controllerView.getSecciones().get(2).getItems().get(0).getTitulo(), "Tenencia Consolidada");
        assertEquals(controllerView.getSecciones().get(2).getItems().get(1).getTitulo(), "Fondos Comunes de Inversión");

        assertEquals(controllerView.getSecciones().get(3).getItems().get(0).getTitulo(), "Cuentas y Paquetes");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(1).getTitulo(), "Préstamos");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(2).getTitulo(), "Tarjetas");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(3).getTitulo(), "Seguros");
        assertEquals(controllerView.getSecciones().get(3).getItems().get(4).getTitulo(), "Centro de Ayuda");
    }
    
    @Test
    public void obtenerControllerConApiMenu() {
    	Cliente cliente = new Cliente();
    	when(menuHomeManager.utilizarApiMenu()).thenReturn(Boolean.TRUE);
    	when(menuHomeManager.construirMenu(cliente)).thenReturn(crearMockSecciones());
    	assertEquals(Boolean.FALSE, controllerHomeManager.obtenerController(cliente).getSecciones().isEmpty());
    }
    
    
    private List<SeccionView> crearMockSecciones(){
    	List<SeccionView> secciones = new ArrayList<SeccionView>();
    	secciones.add(crearSeccion("Consulta"));
    	secciones.add(crearSeccion("Transaccion"));
    	secciones.add(crearSeccion("Inversiones"));
    	secciones.add(crearSeccion("Solicitudes y trámites"));
    	secciones.add(crearSeccion("Beneficios"));
    	return secciones;
    }
    
    private SeccionView crearSeccion(String titulo) {
    	SeccionView seccion = new SeccionView();
    	seccion.setTitulo(titulo);
    	return seccion;
    }

}
