package ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;
import ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles.SpringProfilesTest.TestConfiguration;
import ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles.beans.DummyInterface;
import ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles.beans.ProfileConfig;
import ar.com.santanderrio.obp.servicios.obp.servicios.base.profiles.beans.impl.DummyTest;

/**
 * The Class SpringProfilesTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { ProfileConfig.class,
        TestConfiguration.class })
@ActiveProfiles(Profiles.TEST)
public class SpringProfilesTest {

    /**
     * The Class TestConfiguration.
     */
    @Configurable
    @ComponentScan(basePackages = { "ar.com.santanderrio.obp.base.profiles.beans" })
    public static class TestConfiguration {
    }

    /** The dummy. */
    @Autowired
    private DummyInterface dummy;

    /**
     * Test.
     */
    @Test
    public void test() {
        assertTrue((dummy instanceof DummyTest));
    }
}
