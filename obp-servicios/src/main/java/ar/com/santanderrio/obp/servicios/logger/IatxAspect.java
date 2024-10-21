/*
 * 
 */
package ar.com.santanderrio.obp.servicios.logger;

import java.io.IOException;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.logging.log4j.ThreadContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.iatx.entities.IatxRequest;
import ar.com.santanderrio.obp.servicios.iatx.dao.IatxResponse;

/**
 * The Class IatxAspect.
 */
@Aspect
@Component
public class IatxAspect {

    /**
     * Log audit.
     */
    @Pointcut("execution(public * ar.com.santanderrio.obp.*.*.*.IatxComm.* (..))")
    public void logAudit() {
    }

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(IatxAspect.class);

    /**
     * Clave para el nombre del servicio.
     */
    public static final String NOMBRE_SERVICIO = "nombreServicio";

    /**
     * Clave para el codigo de error.
     */
    public static final String ERROR_CODE = "errorCode";

    /**
     * Clave para mensaje de error.
     */
    public static final String ERROR_MESSAGE = "errorMessage";

    /**
     * Clave para el error de sistema.
     */
    public static final String ERROR_SYSTEM = "errorSystem";
    
    /** The Constant ERROR_TRAMA. */
    public static final String ERROR_TRAMA = "errorTrama";

    /**
     * Before.
     *
     * @param joinPoint
     *            the join point
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    @Before(value = "logAudit()")
    public void before(JoinPoint joinPoint) throws IOException {
        Object[] args = joinPoint.getArgs();

        if (args.length > 0) {
            for (Object object : args) {
                if (object instanceof IatxRequest) {
                    IatxRequest iatxRequest = (IatxRequest) object;
                    LOGGER.info(iatxRequest.getNombreServicio());

                    ThreadContext.put(NOMBRE_SERVICIO, iatxRequest.getNombreServicio());
                }
            }
        } else {
            LOGGER.info("Inicio: {}", joinPoint.getSignature().toShortString());
        }
    }

    /**
     * After.
     *
     * @param joinPoint
     *            the join point
     * @param returnValue
     *            the return value
     */
    @AfterReturning(value = "logAudit()", returning = "returnValue")
    public void after(JoinPoint joinPoint, Object returnValue) {
        if (returnValue instanceof IatxResponse) {
            IatxResponse iatxRequest = (IatxResponse) returnValue;

            if (NumberUtils.INTEGER_ZERO.equals(iatxRequest.getErrorCode())) {
                ThreadContext.remove(NOMBRE_SERVICIO);
                ThreadContext.remove(ERROR_CODE);
                ThreadContext.remove(ERROR_MESSAGE);
                ThreadContext.remove(ERROR_SYSTEM);
                ThreadContext.remove(ERROR_TRAMA);
            } else {
                ThreadContext.put(ERROR_CODE, Integer.toString(iatxRequest.getErrorCode()));
                ThreadContext.put(ERROR_MESSAGE, iatxRequest.getErrorMessage());
                ThreadContext.put(ERROR_SYSTEM, iatxRequest.getErrorSystem());
                ThreadContext.put(ERROR_TRAMA, iatxRequest.getTrama());
            }
        }
    }
}
