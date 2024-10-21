/*
 * 
 */
package ar.com.santanderrio.obp.servicios.route;

import java.util.concurrent.Callable;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import ar.com.santanderrio.obp.servicios.log.Log4j2Constant;

/**
 * Route.
 *
 * @author emilio.watemberg
 * @param <V>
 *            the value type
 * @see {@link RouteBuilder}
 * @since Aug 23, 2016.
 */
public abstract class Route<V> implements Callable<V> {

    /** The logger. */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /** The request attributes. */
    private final RequestAttributes requestAttributes;

    /** The thread. */
    private Thread thread;

    /** El nombre del log del usuario. */
    private String logFileName;

    /**
     * Instantiates a new route.
     */
    public Route() {
        this.requestAttributes = RequestContextHolder.getRequestAttributes();
        this.thread = Thread.currentThread();
        this.logFileName = ThreadContext.get(Log4j2Constant.USERID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.concurrent.Callable#call()
     */
    public V call() {
        try {
            RequestContextHolder.setRequestAttributes(requestAttributes);
            ThreadContext.put(Log4j2Constant.USERID, logFileName);

            return onCall();
        } finally {
            if (Thread.currentThread() != thread) {
                RequestContextHolder.resetRequestAttributes();
                ThreadContext.remove(Log4j2Constant.USERID);
            }
            thread = null;
        }
    }

    /**
     * On call.
     *
     * @return the v
     */
    public abstract V onCall();

}
