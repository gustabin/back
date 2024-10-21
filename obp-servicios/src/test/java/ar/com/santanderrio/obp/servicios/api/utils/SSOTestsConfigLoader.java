package ar.com.santanderrio.obp.servicios.api.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

@Configuration
public class SSOTestsConfigLoader {

    //TODO: refactor to add metadata to placeholderconfigurer via annotation
    @Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
        final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        ppc.setLocations(ArrayUtils.toArray(
                new PathMatchingResourcePatternResolver().getResources("classpath:config/files/obp-sso.properties")
        ));
        return ppc;
    }
}
