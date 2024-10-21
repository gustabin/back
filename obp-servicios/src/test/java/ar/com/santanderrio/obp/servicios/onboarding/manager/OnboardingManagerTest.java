package ar.com.santanderrio.obp.servicios.onboarding.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.clientes.entities.RegistroSesion;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;
import ar.com.santanderrio.obp.servicios.modulos.bo.ModuloPermisoBO;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloEstado;
import ar.com.santanderrio.obp.servicios.modulos.dominio.ModuloPermiso;
import ar.com.santanderrio.obp.servicios.onboarding.bo.OnboardingBO;
import ar.com.santanderrio.obp.servicios.onboarding.view.OnboardingView;

@RunWith(MockitoJUnitRunner.class)
public class OnboardingManagerTest {

    @InjectMocks
    private OnboardingManagerImpl onboardingManager;
    
    @Mock
    OnboardingBO onboardingBO;
    
    @Mock
    private SesionCliente sesionCliente;

    @Mock
    private SesionParametros sesionParametros;

    @Mock
    private ModuloPermisoBO moduloPermisoBO;
    
    @InjectMocks
    @Spy
    private RespuestaFactory respuestaFactory = new RespuestaFactory();
    
    @Test
    public void obtenerSeccionesActivas() throws DAOException {
        // When
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("desktop");
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.MOSTRAR);
        List<String> secciones = new ArrayList<String>();
        secciones.add("HOME");
        
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(onboardingBO.obtenerSeccionesActivas(Matchers.any(Cliente.class), Matchers.anyString())).thenReturn(secciones);
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        // Then
        Respuesta<OnboardingView> respuesta = onboardingManager.obtenerSeccionesActivas();
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(1, respuesta.getRespuesta().getSecciones().size());
    }
    
    @Test
    public void obtenerSeccionesActivasPermisoDesactivado() throws DAOException {
        // When
        ModuloPermiso moduloPermiso = new ModuloPermiso();
        moduloPermiso.setModuloEstado(ModuloEstado.OCULTAR);
        
        Mockito.when(moduloPermisoBO.obtenerModuloPermisoPorAccion(Matchers.any(AccionController.class))).thenReturn(moduloPermiso);
        
        // Then
        Respuesta<OnboardingView> respuesta = onboardingManager.obtenerSeccionesActivas();
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(0, respuesta.getRespuesta().getSecciones().size());
    }
    
    @Test
    public void informarListo() throws DAOException {
        // When
        RegistroSesion registroSesion = new RegistroSesion();
        registroSesion.setDispositivo("desktop");
        Mockito.when(sesionCliente.getCliente()).thenReturn(new Cliente());
        Mockito.when(sesionParametros.getRegistroSession()).thenReturn(registroSesion);
        Mockito.when(onboardingBO.informarListo(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString())).thenReturn(true);
        // Then
        onboardingManager.informarListo(new OnboardingView());
        // Expected
        Mockito.verify(onboardingBO).informarListo(Matchers.any(Cliente.class), Matchers.anyString(), Matchers.anyString());
    }
}
