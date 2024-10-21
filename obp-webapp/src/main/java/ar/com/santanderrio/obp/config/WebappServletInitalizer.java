package ar.com.santanderrio.obp.config;

import com.github.ziplet.filter.compression.CompressingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.util.Set;

/**
 * Created by pablo.martin.gore on 8/17/2016.
 */
public class WebappServletInitalizer implements ServletContainerInitializer {
    /**
     * The Constant LOGGER.
     */
    private static final Logger logger = LoggerFactory.getLogger(WebappServletInitalizer.class);

    private static final String MAPPING = "/*";

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        logger.info("*************************");
        logger.info("*************************");
        logger.info("INICIALIZANDO SERVLET  OBP-WEBAPP");
        FilterRegistration.Dynamic compressingFilter = servletContext.addFilter("CompressingFilter", CompressingFilter.class);
        compressingFilter.setInitParameter("forceEncoding", "true");
        compressingFilter.addMappingForUrlPatterns(null, true, MAPPING);
    }
}