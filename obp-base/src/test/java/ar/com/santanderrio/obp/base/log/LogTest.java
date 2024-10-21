/**
 * 
 */
package ar.com.santanderrio.obp.base.log;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import ar.com.santanderrio.obp.base.log.LogTest.TestConfiguration;
import ar.com.santanderrio.obp.base.log.aop.aspect.LogAuditAspect;
import ar.com.santanderrio.obp.base.profile.Profiles;

/**
 * The Class LogTest.
 *
 * @author Jonatan_Bocian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { TestConfiguration.class })
@ActiveProfiles(Profiles.TEST)
public class LogTest {

    /**
     * The Class TestConfiguration.
     */
    @Configurable
    @EnableAspectJAutoProxy
    @ComponentScan("ar.com.santanderrio.obp.base.log.aop.aspect")
    public static class TestConfiguration {
    }

    /** The aspect. */
    @Autowired
    private LogAuditAspect aspect;

    /**
     * Test.
     */
    @Test
    public void test() {
        assertNotNull(aspect);
    }

}
