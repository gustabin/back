/**
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.routing.RoutingAppender;
import org.apache.logging.log4j.core.config.AppenderControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.session.HttpSessionDestroyedEvent;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionCliente;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SesionParametros;
import ar.com.santanderrio.obp.servicios.log.Log4j2Constant;
import ar.com.santanderrio.obp.servicios.session.manager.SessionControlManager;
import net.sf.ehcache.search.Query;
import net.sf.ehcache.search.Result;
import net.sf.ehcache.search.Results;

/**
 * The listener interface for receiving sessionEnded events. The class that is
 * interested in processing a sessionEnded event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addSessionEndedListener<code> method. When the sessionEnded
 * event occurs, that object's appropriate method is invoked.
 *
 * @author sergio.e.goldentair
 */
@Component
public class SessionEndedListener implements ApplicationListener<HttpSessionDestroyedEvent> {

    /** The jwt dev mode active. */
    @Value("${JWT_DEV_MODE:false}")
    private Boolean jwtDevModeActive;

    /** The session control manager. */
    @Autowired
    private SessionControlManager sessionControlManager;

    @Autowired
    private CacheManager cacheManager;

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.
     * springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(HttpSessionDestroyedEvent applicationEvent) {
        SesionParametros sesionParametros = (SesionParametros) applicationEvent.getSession()
                .getAttribute("scopedTarget.sesionParametros");
        if (sesionParametros != null) {
            Boolean loginOK = sesionParametros.getJwtTokenActivo();
            Logger coreLogger = (Logger) LogManager.getLogger(SessionEndedListener.class.getCanonicalName());
            if (loginOK) {
                String nup = ((SesionCliente) applicationEvent.getSession().getAttribute("scopedTarget.sesionCliente"))
                        .getCliente().getNup();
                limpiarCachesObpNup(coreLogger, nup);
                limpiarCachesObpNyo(coreLogger, nup);
                if (Boolean.FALSE.equals(jwtDevModeActive)) {
                    sessionControlManager.saveOrUpdate(Long.valueOf(nup), null);
                }
            }
            limpiarInactiveLog(applicationEvent, coreLogger);
        }
    }

    /**
     * Limpiar todas las entradas vinculadas a la session del usuario con caches
     * cuya clave sea solamente el nup.
     * 
     * @param coreLogger
     * @param nup
     */
    private void limpiarCachesObpNup(Logger coreLogger, String nup) {
        try {
            for (String cacheName : CacheConstants.getCachesObpNup()) {
                cacheManager.getCache(cacheName).evict(nup);
            }
        } catch (Exception e) {
            coreLogger.error("No se pudieron liberar las cache asociadas al usuario.", e);
        }
    }

    /**
     * Limpiar las caches vinculadas a la session donde la clave esta compuesta por
     * el nup y otros valores.
     * 
     * @param coreLogger
     * @param nup
     */
    public void limpiarCachesObpNyo(Logger coreLogger, String nup) {
        try {
            for (String cacheName : CacheConstants.getCachesObpNyo()) {
                Cache articleListCache = cacheManager.getCache(cacheName);
                net.sf.ehcache.Cache ehCache = (net.sf.ehcache.Cache) articleListCache.getNativeCache();
                Query query = ehCache.createQuery();
                query.addCriteria(Query.KEY.ilike(nup + "*"));
                query.includeKeys();
                Results results = query.execute();
                for (Result result : results.all()) {
                    String key = (String) result.getKey();
                    articleListCache.evict(key);
                }
                results.discard();
            }
        } catch (Exception e) {
            coreLogger.error("No se pudieron liberar las cache asociadas al usuario.", e);
        }
    }

    /**
     * La version 2.3 de log4j2 no permite cerrar el appender sobre el routing de
     * cada archivo de session, solo se liberan cuanto se estopea t-odo
     * RoutingAppender.stop(), por lo que si se borra sigue tomado el espacio en el
     * file system. Esto esta resuelto en la version 2.5 pero requiere jdk 7. <br/>
     * https://issues.apache.org/jira/browse/LOG4J2-510
     *
     * @param applicationEvent
     *            the application event
     */
    private void limpiarInactiveLog(HttpSessionDestroyedEvent applicationEvent, Logger coreLogger) {
        String key = getKey(applicationEvent);
        LoggerContext context = coreLogger.getContext();
        RoutingAppender appender = (RoutingAppender) context.getConfiguration().getAppender("Routing");
        try {
            Method method = appender.getClass().getDeclaredMethod("getControl", String.class, LogEvent.class);
            method.setAccessible(true);
            AppenderControl appenderControl = (AppenderControl) method.invoke(appender, key, null);
            appenderControl.getAppender().stop();
        } catch (Exception e) {
            coreLogger.error("Unable to close the logger {}", key, e);
        }
    }

    /**
     * Obtener el log de session que se quiere cerrar, si la invalidacion de session
     * viene de accion del usuario lo debe tomar del contexto de log4j en cambio si
     * viene por una inactividad lo levanta de la misma session. <br/>
     * En ningun caso deberia llegar <b>vacio</b>.
     *
     * @param applicationEvent
     *            the application event
     * @return the key
     */
    private String getKey(HttpSessionDestroyedEvent applicationEvent) {
        String key = ThreadContext.get(Log4j2Constant.USERID);
        if (StringUtils.isNotBlank(key)) {
            return key;
        }
        return (String) applicationEvent.getSession().getAttribute(Log4j2Constant.LOGFILENAME);
    }

}
