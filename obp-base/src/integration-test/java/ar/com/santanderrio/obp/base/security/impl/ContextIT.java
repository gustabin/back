/**
 * 
 */
package ar.com.santanderrio.obp.base.security.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.base.security.impl.ContextIT.ContextITConfiguration;

/**
 * The Class ContextIT.
 *
 * @author sergio.e.goldentair
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ContextITConfiguration.class
        // ,BackEndSecurityConfig.class, AspectJConfig.class,
        // BackEndSecurityConfig.class
})
@ActiveProfiles(value = Profiles.DIAGNOSTIC)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class ContextIT {

    /** The environment. */
    @Autowired
    private Environment environment;

    /**
     * The Class ContextITConfiguration.
     */
    @Configuration
    @Configurable
    @EnableAspectJAutoProxy
    @PropertySource(value = { "classpath:/integration-test/credentialSecurityImplTest.properties" })
    @ComponentScan(basePackages = "ar.com.santanderrio.obp.base")
    public static class ContextITConfiguration {

        /**
         * Property configurer.
         *
         * @return the property sources placeholder configurer
         */
        @Bean
        public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
            return new PropertySourcesPlaceholderConfigurer();
        }
    }

    /**
     * Env not null.
     */
    @Test
    public void envNotNull() {
        Assert.assertNotNull(environment);
    }
}
