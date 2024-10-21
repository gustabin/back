/*
 * 
 */
package ar.com.santanderrio.obp.config.executor;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import ar.com.santanderrio.obp.servicios.log.Log4j2Constant;

/**
 * Implementacion de Callable para el executor de OBP.
 *
 * @author B025331
 * @param <T>
 *            the generic type
 */
public class ContextAwareCallable<T> implements Callable<T> {

    /** The task. */
    private Callable<T> task;

    /** The context. */
    private RequestAttributes context;

    /** El nombre del log del usuario. */
    private String logFileName;

    /**
	 * Instantiates a new context aware callable.
	 *
	 * @param logFileName
	 *            the log file name
	 * @param task
	 *            the task
	 * @param context
	 *            the context
	 */
    public ContextAwareCallable(String logFileName, Callable<T> task, RequestAttributes context) {
        this.task = task;
        this.context = context;
        this.logFileName = logFileName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public T call() throws Exception {
        if (context != null) {
            RequestContextHolder.setRequestAttributes(context);
            ThreadContext.put(Log4j2Constant.USERID, logFileName);
        }

        try {
            return task.call();
        } finally {
            RequestContextHolder.resetRequestAttributes();
            ThreadContext.remove(Log4j2Constant.USERID);
        }
    }
}
