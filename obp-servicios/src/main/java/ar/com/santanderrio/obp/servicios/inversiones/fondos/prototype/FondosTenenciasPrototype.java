package ar.com.santanderrio.obp.servicios.inversiones.fondos.prototype;

import ar.com.santanderrio.obp.config.ws.CacheConstants;
import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.comun.estadistica.web.manager.EstadisticaManager;
import ar.com.santanderrio.obp.servicios.comun.estadisticas.util.EstadisticasConstants;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.entity.Segmento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FondosTenenciasPrototype {

    /** The Constants */
    private static final Logger LOGGER = LoggerFactory.getLogger(FondosTenenciasPrototype.class);

    private static final String ELIMINAR_CACHE = "Se elimina cache {} para el NUP {}.";

    private static final String GENERAR_CACHE = "Se genera cache {} para el NUP {}.";

    private static final String CONSUMIR_CACHE = "Se consume cache {} para el NUP {}.";

    /** Cache Mananger */
    @Autowired
    private CacheManager cacheManager;

    /** The estadistica manager. */
    @Autowired
    private EstadisticaManager estadisticaManager;

    public void cleanCacheConsultaTenenciaFCI(Cliente cliente) {
        Cache cache = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_CNSTENVAL);
        Cache.ValueWrapper nupTenenciaFCI = cache.get(cliente.getNup());
        if (nupTenenciaFCI != null) {
            cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_CNSTENVAL).evict(cliente.getNup());
            LOGGER.info(ELIMINAR_CACHE, CacheConstants.CACHE_NUP_FONDOS_CNSTENVAL, cliente.getNup());
        }
    }

    public void setEstadisticaConsultaTenenciaFCI(Cliente cliente) {
        Cache cache = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_CNSTENVAL);
        Cache.ValueWrapper nupTenenciaFCI = cache.get(cliente.getNup());
        if (nupTenenciaFCI == null) {
            estadisticaManager.add(EstadisticasConstants.FONDOS_TENENCIAS_CNSTENVAL,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(GENERAR_CACHE, CacheConstants.CACHE_NUP_FONDOS_CNSTENVAL, cliente.getNup());
        } else {
            LOGGER.info(CONSUMIR_CACHE, CacheConstants.CACHE_NUP_FONDOS_CNSTENVAL, cliente.getNup());
        }
    }

    public void cleanCacheObtenerTenenciaValuadaDetalleFondoOnline(Cliente cliente, String segmento) {
        Cache cacheRtl = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE);
        Cache cacheBP = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP);

        if (Segmento.BANCA_PERSONAL.getCodigo().equals(segmento)) {
            Cache.ValueWrapper cacheTenValDetalleFondoOnlineRtl = cacheRtl.get(cliente.getNup());
            if (cacheTenValDetalleFondoOnlineRtl != null) {
                cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE)
                        .evict(cliente.getNup());
                LOGGER.info(ELIMINAR_CACHE, CacheConstants.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE, cliente.getNup());
            }
        }

        if (Segmento.BANCA_PRIVADA.getCodigo().equals(segmento)) {
            Cache.ValueWrapper cacheTenValDetalleFondoOnlineBP = cacheBP.get(cliente.getNup());
            if (cacheTenValDetalleFondoOnlineBP != null) {
                cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP)
                        .evict(cliente.getNup());
                LOGGER.info(ELIMINAR_CACHE, CacheConstants.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP,
                        cliente.getNup());
            }
        }
    }

    public void setEstadisticaObtenerTenenciaValuadaDetalleFondoOnline(Cliente cliente, String segmento) {
        Cache cacheRtl = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE);
        Cache cacheBP = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP);

        Cache.ValueWrapper cacheTenValDetalleFondoOnlineRtl = cacheRtl.get(cliente.getNup());
        if (Segmento.BANCA_PERSONAL.getCodigo().equals(segmento)) {
            if (cacheTenValDetalleFondoOnlineRtl == null) {
                estadisticaManager.add(EstadisticasConstants.FONDOS_TENVAL_DETALLE_FONDO_ONLINE,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                LOGGER.info(GENERAR_CACHE, CacheConstants.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE, cliente.getNup());
            } else {
                LOGGER.info(CONSUMIR_CACHE, CacheConstants.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE, cliente.getNup());
            }
        }

        if (Segmento.BANCA_PRIVADA.getCodigo().equals(segmento)) {
            Cache.ValueWrapper cacheTenValDetalleFondoOnlineBP = cacheBP.get(cliente.getNup());
            if (cacheTenValDetalleFondoOnlineBP == null) {
                estadisticaManager.add(EstadisticasConstants.FONDOS_TENVAL_DETALLE_FONDO_ONLINE,
                        EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
                LOGGER.info(GENERAR_CACHE, CacheConstants.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP,
                        cliente.getNup());
            } else {
                LOGGER.info(CONSUMIR_CACHE, CacheConstants.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP,
                        cliente.getNup());
            }
        }
    }

    public void cleanCacheObpNupHoldingFondosTenenciaBff(Cliente cliente) {
        String nup = cliente.getNup();
        Cache cache = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF);
        Cache.ValueWrapper cacheObpNupHoldingFondosTenenciaBff = cache.get(nup);
        if (cacheObpNupHoldingFondosTenenciaBff != null) {
            cache.evict(nup);
            LOGGER.info(ELIMINAR_CACHE, CacheConstants.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF, nup);
        }
    }

    public void setEstadisticaGetHoldingsBff(Cliente cliente) {
        Cache cache = cacheManager.getCache(CacheConstants.Names.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF);
        Cache.ValueWrapper nupHoldingsBff = cache.get(cliente.getNup());
        if (nupHoldingsBff == null) {
            estadisticaManager.add(EstadisticasConstants.CONSUMO_FONDOS_TENENCIA_BFF,
                    EstadisticasConstants.CODIGO_ESTADISTICAS_OK);
            LOGGER.info(GENERAR_CACHE, CacheConstants.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF, cliente.getNup());
        } else {
            LOGGER.info(CONSUMIR_CACHE, CacheConstants.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF, cliente.getNup());
        }
    }
}
