/**
 * 
 */
package ar.com.santanderrio.obp.base.security.keystore;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.PropertyMap;
import ar.com.santanderrio.obp.base.security.keystore.PropertyMapTest.PropMapTestConfiguration;

/**
 * The Class PropertyMapTest.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { PropMapTestConfiguration.class })
@TestPropertySource(properties = { "clave1 = valor1" })
@ActiveProfiles(Profiles.TEST)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class PropertyMapTest {

    /** The property map. */
    @Autowired
    private PropertyMap propertyMap;

    /**
     * The Class PropMapTestConfiguration.
     */
    @Configuration
    public static class PropMapTestConfiguration {

        /**
         * Property map.
         *
         * @return the property map
         */
        @Bean
        PropertyMap propertyMap() {
            return new PropertyMap();
        }
    }

    /**
     * Obtener prop clave 1.
     */
    @Test
    public void obtenerPropClave1() {
        Assert.assertEquals("valor1", propertyMap.get("clave1"));
    }

    /**
     * Setear prop clave 2.
     */
    @Test
    public void setearPropClave2() {
        Map<String, Object> propiedades = new HashMap<String, Object>();
        propiedades.put("clave2", "valor2");
        propertyMap.setProperties(propiedades);
        Assert.assertEquals("valor2", propertyMap.get("clave2"));
    }

    /**
     * Prop no presente.
     */
    @Test
    public void propNoPresente() {
        Assert.assertNull(propertyMap.get("clave2"));
    }
}
