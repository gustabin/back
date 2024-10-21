package ar.com.santanderrio.obp.servicios.obp.base.resource;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.profile.Profiles;

/**
 * The Class ResourceLoaderTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@ActiveProfiles(Profiles.TEST)
public class ResourceLoaderTest {

    /** The resource loader. */
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Test resource loader.
     */
    @Test
    public void testResourceLoader() {
        Resource resource = resourceLoader.getResource("classpath:/report/cbucuenta.jasper");
        assertNotNull(resource);
    }

}
