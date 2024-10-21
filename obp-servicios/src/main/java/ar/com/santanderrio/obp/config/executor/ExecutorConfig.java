/*
 * 
 */
package ar.com.santanderrio.obp.config.executor;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import ar.com.santanderrio.obp.base.profile.Profiles;

/**
 * Configuracion del executor para los metodos asincronicos.
 * 
 * @author Luis Moyano
 *
 */
@Configuration
@EnableAsync
public class ExecutorConfig extends AsyncConfigurerSupport {

    /**
     * Tamanio del pool principal.
     */
    @Value("${THREAD.CORE.POOL.SIZE:7}")
    private Integer corePoolSize;

    /**
     * Tamanio maximo del pool.
     */
    @Value("${THREAD.MAX.POOL.SIZE:42}")
    private Integer maxPoolSize;

    /**
     * Tamanio maximo del pool.
     */
    @Value("${THREAD.TIMEOUT:60000}")
    private Integer timeOutMiliSeg;

    /**
     * Capacidad de la cola de hilos.
     */
    @Value("${THREAD.QUEUE.CAPACITY:11}")
    private Integer queueCapacity;

    /** Executor para la gestion de los hilos. **/
    @Autowired
    private ThreadPoolTaskExecutor executor;

    /**
     * Gets the async executor.
     *
     * @return the async executor
     */
    @Override
    public Executor getAsyncExecutor() {
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix("Executor-");
        Long secondsToLive = TimeUnit.MILLISECONDS.toSeconds(timeOutMiliSeg);
        executor.setKeepAliveSeconds(secondsToLive.intValue());
        executor.initialize();
        return executor;
    }

    /**
     * Obtener el Executor que gestiona los hilos.<br>
     * Profile Test ya que el ContextAwarePoolExecutor requiere un contexto.
     * 
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    @Profile({ Profiles.TEST, Profiles.TOMCAT })
    public ThreadPoolTaskExecutor getTestExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    /**
     * Obtener el Executor que gestiona los hilos.<br>
     * Profile Producion.
     * 
     * @return ThreadPoolTaskExecutor
     */
    @Bean
    @Profile({ Profiles.PRODUCTION, Profiles.HOMO, Profiles.DESA, Profiles.MOCK })
    public ThreadPoolTaskExecutor getExecutor() {
        return new ContextAwarePoolExecutor();
    }
}
