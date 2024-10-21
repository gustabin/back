/*
 * 
 */
package ar.com.santanderrio.obp.config.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.RequestContextHolder;

import ar.com.santanderrio.obp.servicios.log.Log4j2Constant;

/**
 * The Class ContextAwarePoolExecutor.
 */
public class ContextAwarePoolExecutor extends ThreadPoolTaskExecutor {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2686420449860966958L;

    /**
     * Submit.
     *
     * @param <T>
     *            the generic type
     * @param task
     *            the task
     * @return the future
     * @see org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor#submit(java.util.concurrent.Callable)
     */
    @Override
    public <T> Future<T> submit(Callable<T> task) {
        return super.submit(new ContextAwareCallable<T>(ThreadContext.get(Log4j2Constant.USERID), task,
                RequestContextHolder.currentRequestAttributes()));
    }

    /**
     * Submit listenable.
     *
     * @param <T>
     *            the generic type
     * @param task
     *            the task
     * @return the listenable future
     * @see org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor#submitListenable(java.util.concurrent.Callable)
     */
    @Override
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        return super.submitListenable(new ContextAwareCallable<T>(ThreadContext.get(Log4j2Constant.USERID), task,
                RequestContextHolder.currentRequestAttributes()));
    }
}
