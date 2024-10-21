package ar.com.santanderrio.obp.servicios.onboarding.sei;

import static org.mockito.Mockito.when;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.AbstractSEITest;
import ar.com.santanderrio.obp.servicios.onboarding.manager.OnboardingManager;
import ar.com.santanderrio.obp.servicios.onboarding.sei.OnboardingSEIIT.OnboardingSEITestConfiguration;
import ar.com.santanderrio.obp.servicios.onboarding.view.OnboardingView;

@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { OnboardingSEITestConfiguration.class })
public class OnboardingSEIIT extends AbstractSEITest {

    @Autowired
    private OnboardingManager onboardingManager;

    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    protected Object getServiceBeanToTest() {
        return applicationContext.getBean("onboardingSEI");
    }

    @Configuration
    public static class OnboardingSEITestConfiguration {

        @Bean(name = "onboardingSEI")
        public OnboardingSEI onboardingSEI() {
            return new OnboardingSEIImpl();
        }

        @Bean
        public OnboardingManager onboardingManager() {
            return Mockito.mock(OnboardingManager.class);
        }

    }

    @SuppressWarnings("unchecked")
    @Test
    public void obtenerSeccionesActivas() {
        // When
        when(onboardingManager.obtenerSeccionesActivas()).thenReturn(new Respuesta<OnboardingView>());
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/onboarding/obtenerSeccionesActivas");
        // Then
        Respuesta<OnboardingView> respuesta = client.post(null, Respuesta.class);
        // Expected
        Assert.assertNotNull(respuesta);
    }

    @Test
    public void informarListo() {
        // When
        WebClient client = getWebClient();
        client.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
        client.path("/onboarding/informarListo");
        // Then
        client.post(new OnboardingView(), Respuesta.class);
        // Expected
        Mockito.verify(onboardingManager).informarListo(Matchers.any(OnboardingView.class));
    }

}
