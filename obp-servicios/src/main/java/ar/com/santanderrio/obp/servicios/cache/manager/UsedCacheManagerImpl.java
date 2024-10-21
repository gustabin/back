/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cache.manager;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.cache.view.CacheView;
import ar.com.santanderrio.obp.servicios.factory.RespuestaFactory;
import net.sf.ehcache.statistics.StatisticsGateway;

/**
 * Clase gestion de las operaciones sobre la cache utilizadas. Poder tener
 * metricas e ir depurando el uso de cada una.
 * 
 * @author sergio.e.goldentair
 *
 */
@Component
public class UsedCacheManagerImpl implements UsedCacheManager {
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UsedCacheManagerImpl.class);

    /** The cache manager. */
    @Autowired
    private CacheManager cacheManager;

    /** The respuesta factory. */
    @Autowired
    private RespuestaFactory respuestaFactory;

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.manager.UsedCacheManager#
     * obtenerEstadisticas()
     */
    @Override
    public Respuesta<List<CacheView>> obtenerEstadisticas() {
        List<CacheView> listCacheView = new ArrayList<CacheView>();
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_LEGALES));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_MODULOS_PERMISOS));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_MENSAJES));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_DESTINOS_BCRA));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_FONDOS));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_COMPROBANTES_SCOMP));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_COMPROBANTES_HISTORIAL));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NUP_CNSPAQCNLS));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NUP_FONDOS_CNSTENVAL));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NUP_IDEPESBANE));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NYO_CNSTJCDATO));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NYO_CNSCTAMOVS));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NYO_CNSDDIDERE));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_API_AUTH_CLIENTS_IDS));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF));
        listCacheView.add(getEstadisticaCache(CacheConstants.Names.CACHE_NUP_ACCESS_FONDOS_TENENCIA_BFF));
        return respuestaFactory.crearRespuestaOk(listCacheView);
    }

    /**
     * Generar la view con la info de la cache.
     *
     * @param nombreCache
     *            the nombre cache
     * @return the estadistica cache
     */
    private CacheView getEstadisticaCache(String nombreCache) {
        Cache cacheConsultiva = cacheManager.getCache(nombreCache);
        net.sf.ehcache.Cache ehCache = (net.sf.ehcache.Cache) cacheConsultiva.getNativeCache();
        StatisticsGateway estadisticas = ehCache.getStatistics();
        CacheView cacheView = new CacheView();
        cacheView.setNombreCache(nombreCache);
        cacheView.setNumeroHintsRealizados(Long.toString(estadisticas.cacheHitCount()));
        cacheView.setNumeroEvicts(Long.toString(estadisticas.cacheEvictedCount()));
        cacheView.setNumeroElementos(Long.toString(estadisticas.getSize()));
        LOGGER.info("Obtener estadisticas para cache {}.", cacheView);
        return cacheView;
    }
}
