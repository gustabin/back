package ar.com.santanderrio.obp.servicios.onboarding.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import ar.com.santanderrio.obp.base.dao.DAOException;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.onboarding.dao.OnboardingDAO;

@RunWith(MockitoJUnitRunner.class)
public class OnboardingBOTest {

    @InjectMocks
    private OnboardingBOImpl onboardingBO;
    
    @Mock
    OnboardingDAO onboardingDAO;
    
    @Test
    public void obtenerSeccionesActivas() throws DAOException {
        // When
        Mockito.when(onboardingDAO.obtenerSeccionesActivas(Matchers.anyString(), Matchers.anyString())).thenReturn(new ArrayList<String>());
        // Then
        List<String> respuesta = onboardingBO.obtenerSeccionesActivas(new Cliente(), "desktop");
        // Expected
        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void obtenerSeccionesActivasException() throws DAOException {
        // When
        Mockito.when(onboardingDAO.obtenerSeccionesActivas(Matchers.anyString(), Matchers.anyString())).thenThrow(new DAOException());
        // Then
        List<String> respuesta = onboardingBO.obtenerSeccionesActivas(new Cliente(), "phone");
        // Expected
        Assert.assertNotNull(respuesta);
    }
    
    @Test
    public void informarListo() throws DAOException {
        // When
        Mockito.when(onboardingDAO.informarListo(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenReturn(true);
        // Then
        Boolean respuesta = onboardingBO.informarListo(new Cliente(), "desktop", "HOME");
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(true, respuesta);
    }
    
    @Test
    public void informarListoException() throws DAOException {
        // When
        Mockito.when(onboardingDAO.informarListo(Matchers.anyString(), Matchers.anyString(), Matchers.anyString())).thenThrow(new DAOException());
        // Then
        Boolean respuesta = onboardingBO.informarListo(new Cliente(), "mobile", "HOME");
        // Expected
        Assert.assertNotNull(respuesta);
        Assert.assertEquals(false, respuesta);
    }
}
