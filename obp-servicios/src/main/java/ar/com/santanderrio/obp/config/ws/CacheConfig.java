/**
 * 
 */
package ar.com.santanderrio.obp.config.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.CacheConfiguration.TransactionalMode;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;

/**
 * Gestion de la cache para la aplicacion.
 * 
 * @author sergio.e.goldentair
 *
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

	/** The comprobante size. */
	@Value("${COMPROBANTE_SIZE:1000}")
	private Integer comprobanteSize;

	/** The comprobante idle. */
	@Value("${COMPROBANTE_IDLE:300}")
	private Integer comprobanteIdle;

	/** The comprobante live. */
	@Value("${COMPROBANTE_LIVE:0}")
	private Integer comprobanteLive;

	/** The Constant DIA_SEGUNDOS. */
	private static final Integer DIA_SEGUNDOS = 86400;

	/** cache para CACHE CHANCES SIZE. */
	private static final Integer CACHE_CHANCES_SIZE = 300;

	private static final Integer HORA_SEGUNDOS = 3600;

	private static final Integer MINUTO_SEGUNDOS = 60;

	/**
	 * Cache manager.
	 *
	 * @return the cache manager
	 */
	@Bean
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager());
	}

	/**
	 * Eh cache manager.
	 *
	 * @return manager
	 */
	@Bean(destroyMethod = "shutdown")
	public net.sf.ehcache.CacheManager ehCacheManager() {
		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(cacheLegales());
		config.addCache(cacheDestinosPrestamo());
		config.addCache(cacheModulosPermisos());
		config.addCache(cacheMensajes());
		config.addCache(cacheFondos());
		config.addCache(cacheComprobantesScomp());
		config.addCache(cacheComprobantesHistorial());
		config.addCache(cachePlazosFijos());
		config.addCache(cacheTasasPlazosFijos());
		config.addCache(cacheUltimosMovimientos());
		config.addCache(cacheAccionesPlazosFijos());
		config.addCache(cacheObtenerPaisesMarcacion());
		config.addCache(cacheMovimientosBancaPrivada());
		config.addCache(cacheOperacionesTitulosBancaPrivada());
		config.addCache(cacheOperacionesLicitacionBancaPrivada());
		config.addCache(cacheMonedasComex());
		config.addCache(cachePaisesComex());
		config.addCache(cacheInformacionMercado());
		config.addCache(cacheSuscripcionesMaps());
		config.addCache(cacheLegalesComex());
		config.addCache(cacheChances());
		config.addCache(cachePagHaberes());
		config.addCache(cacheAdhesionDebitoAutomatico());
		config.addCache(cacheObpNupIdePesBane());
		config.addCache(cacheObpNupCnsPaqCnls());
		config.addCache(cacheObpNyoCnsTjcDato());
		config.addCache(cacheObpNyoCnsCtaMovs());
		config.addCache(cacheObpNyoCnsDDiDeRe());
		config.addCache(cacheConceptos());
		config.addCache(cacheCampaniasPublicas());
		config.addCache(cacheQueueSTAuth());
		config.addCache(cacheOAuthV2());
		config.addCache(cacheCotizacionesFondos());
		config.addCache(cacheBonificacionSeguros());
		config.addCache(cacheObpNupConsultaTenenciasFondos());
		config.addCache(cacheObpNupObtenerTenenciaValuadaDetalleFondoOnline());
		config.addCache(cacheObpNupObtenerTenenciaValuadaDetalleFondoOnlineBP());
		config.addCache(cacheApiAuthClientIds());
		config.addCache(cacheDiasNoHabiles());
		config.addCache(cacheObpNupAccessFondosTenenciaBff());
		config.addCache(cacheObpNupHoldingFondosTenenciaBff());
		config.addCache(cacheTransfersApiTokens());
		config.addCache(cacheSSOClients());
		config.addCache(cacheAlycsCuits());
		return net.sf.ehcache.CacheManager.newInstance(config);
	}

	private CacheConfiguration cacheObpNyoCnsTjcDato() {
		CacheConfiguration modulosPermisosCache = new CacheConfiguration();
		modulosPermisosCache.setName(CacheConstants.Names.CACHE_NYO_CNSTJCDATO);
		modulosPermisosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		modulosPermisosCache.setMaxEntriesLocalHeap(100);
		modulosPermisosCache.setTimeToIdleSeconds(300);
		modulosPermisosCache.setTimeToLiveSeconds(900);
		modulosPermisosCache.setTransactionalMode(TransactionalMode.OFF.toString());

		Searchable searchable = new Searchable();
		modulosPermisosCache.addSearchable(searchable);
		searchable.addSearchAttribute(new SearchAttribute().name("nup"));
		return modulosPermisosCache;
	}

	private CacheConfiguration cacheObpNyoCnsCtaMovs() {
		CacheConfiguration modulosPermisosCache = new CacheConfiguration();
		modulosPermisosCache.setName(CacheConstants.Names.CACHE_NYO_CNSCTAMOVS);
		modulosPermisosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		modulosPermisosCache.setMaxEntriesLocalHeap(100);
		modulosPermisosCache.setTimeToIdleSeconds(300);
		modulosPermisosCache.setTimeToLiveSeconds(900);
		modulosPermisosCache.setTransactionalMode(TransactionalMode.OFF.toString());

		Searchable searchable = new Searchable();
		modulosPermisosCache.addSearchable(searchable);
		searchable.addSearchAttribute(new SearchAttribute().name("nup"));
		return modulosPermisosCache;
	}

	private CacheConfiguration cacheObpNyoCnsDDiDeRe() {
		CacheConfiguration modulosPermisosCache = new CacheConfiguration();
		modulosPermisosCache.setName(CacheConstants.Names.CACHE_NYO_CNSDDIDERE);
		modulosPermisosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		modulosPermisosCache.setMaxEntriesLocalHeap(100);
		modulosPermisosCache.setTimeToIdleSeconds(300);
		modulosPermisosCache.setTimeToLiveSeconds(900);
		modulosPermisosCache.setTransactionalMode(TransactionalMode.OFF.toString());

		Searchable searchable = new Searchable();
		modulosPermisosCache.addSearchable(searchable);
		searchable.addSearchAttribute(new SearchAttribute().name("nup"));
		return modulosPermisosCache;
	}

	private CacheConfiguration cacheObpNupCnsPaqCnls() {
		CacheConfiguration modulosPermisosCache = new CacheConfiguration();
		modulosPermisosCache.setName(CacheConstants.Names.CACHE_NUP_CNSPAQCNLS);
		modulosPermisosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		modulosPermisosCache.setMaxEntriesLocalHeap(100);
		modulosPermisosCache.setTimeToIdleSeconds(300);
		modulosPermisosCache.setTimeToLiveSeconds(900);
		modulosPermisosCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return modulosPermisosCache;
	}

	private CacheConfiguration cacheObpNupIdePesBane() {
		CacheConfiguration modulosPermisosCache = new CacheConfiguration();
		modulosPermisosCache.setName(CacheConstants.Names.CACHE_NUP_IDEPESBANE);
		modulosPermisosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		modulosPermisosCache.setMaxEntriesLocalHeap(100);
		modulosPermisosCache.setTimeToIdleSeconds(300);
		modulosPermisosCache.setTimeToLiveSeconds(900);
		modulosPermisosCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return modulosPermisosCache;
	}

	/**
	 * Cache Modulos Permitidos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheModulosPermisos() {
		CacheConfiguration modulosPermisosCache = new CacheConfiguration();
		modulosPermisosCache.setName(CacheConstants.Names.CACHE_MODULOS_PERMISOS);
		modulosPermisosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		modulosPermisosCache.setMaxEntriesLocalHeap(250);
		modulosPermisosCache.setEternal(true);
		modulosPermisosCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return modulosPermisosCache;
	}

	/**
	 * Cache Legales.
	 * 
	 * @return cache legales
	 */
	private CacheConfiguration cacheLegales() {
		CacheConfiguration legalesCache = new CacheConfiguration();
		legalesCache.setName(CacheConstants.Names.CACHE_LEGALES);
		legalesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		legalesCache.setMaxEntriesLocalHeap(200);
		legalesCache.setEternal(true);
		legalesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return legalesCache;
	}

	/**
	 * Cache destinos de prestamos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheDestinosPrestamo() {
		CacheConfiguration destinosPrestamoCache = new CacheConfiguration();
		destinosPrestamoCache.setName(CacheConstants.Names.CACHE_DESTINOS_BCRA);
		destinosPrestamoCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		destinosPrestamoCache.setMaxEntriesLocalHeap(300);
		destinosPrestamoCache.setEternal(true);
		destinosPrestamoCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return destinosPrestamoCache;
	}

	/**
	 * Cache mensajes.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheMensajes() {
		CacheConfiguration mensajesCache = new CacheConfiguration();
		mensajesCache.setName(CacheConstants.Names.CACHE_MENSAJES);
		mensajesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		mensajesCache.setMaxEntriesLocalHeap(1000);
		mensajesCache.setEternal(true);
		mensajesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return mensajesCache;
	}

	/**
	 * Cache fondos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheFondos() {
		CacheConfiguration fondosCache = new CacheConfiguration();
		fondosCache.setName(CacheConstants.Names.CACHE_FONDOS);
		fondosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		fondosCache.setMaxEntriesLocalHeap(100);
		fondosCache.setEternal(true);
		fondosCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return fondosCache;
	}

	private CacheConfiguration cacheCotizacionesFondos() {
		CacheConfiguration cotizacionesFondosCache = new CacheConfiguration();
		cotizacionesFondosCache.setName(CacheConstants.Names.CACHE_FONDOS_COTIZACIONES);
		cotizacionesFondosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		cotizacionesFondosCache.setMaxEntriesLocalHeap(100);
		cotizacionesFondosCache.setTimeToIdleSeconds(HORA_SEGUNDOS * 2);
		cotizacionesFondosCache.setTimeToLiveSeconds(HORA_SEGUNDOS * 2);
		cotizacionesFondosCache.setEternal(false);
		cotizacionesFondosCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return cotizacionesFondosCache;
	}
	/**
	 * Cache comprobantes scomp.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheComprobantesScomp() {
		CacheConfiguration comprobantesCache = new CacheConfiguration();
		comprobantesCache.setName(CacheConstants.Names.CACHE_COMPROBANTES_SCOMP);
		comprobantesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		comprobantesCache.setMaxEntriesLocalHeap(comprobanteSize);
		comprobantesCache.setTimeToIdleSeconds(comprobanteIdle);
		comprobantesCache.setTimeToLiveSeconds(comprobanteLive);
		comprobantesCache.setEternal(false);
		comprobantesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return comprobantesCache;
	}

	private CacheConfiguration cacheComprobantesHistorial() {
		CacheConfiguration comprobantesCache = new CacheConfiguration();
		comprobantesCache.setName(CacheConstants.Names.CACHE_COMPROBANTES_HISTORIAL);
		comprobantesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		comprobantesCache.setMaxEntriesLocalHeap(comprobanteSize);
		comprobantesCache.setTimeToIdleSeconds(comprobanteIdle);
		comprobantesCache.setTimeToLiveSeconds(comprobanteLive);
		comprobantesCache.setEternal(false);
		comprobantesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return comprobantesCache;
	}

	/**
	 * Cache ultimos movimientos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheUltimosMovimientos() {
		CacheConfiguration comprobantesCache = new CacheConfiguration();
		comprobantesCache.setName(CacheConstants.Names.CACHE_ULTIMOS_MOVIMIENTOS);
		comprobantesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		comprobantesCache.setMaxEntriesLocalHeap(1000);
		comprobantesCache.setTimeToIdleSeconds(90);
		comprobantesCache.setTimeToLiveSeconds(180);
		comprobantesCache.setEternal(false);
		comprobantesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return comprobantesCache;
	}

	/**
	 * Cache ultimos movimientos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheMovimientosBancaPrivada() {
		CacheConfiguration movimientosBancaPrivadaCache = new CacheConfiguration();
		movimientosBancaPrivadaCache.setName(CacheConstants.Names.CACHE_MOVIMIENTOS_BANCA_PRIVADA);
		movimientosBancaPrivadaCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		movimientosBancaPrivadaCache.setMaxEntriesLocalHeap(1000);
		movimientosBancaPrivadaCache.setTimeToIdleSeconds(90);
		movimientosBancaPrivadaCache.setTimeToLiveSeconds(180);
		movimientosBancaPrivadaCache.setEternal(false);
		movimientosBancaPrivadaCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return movimientosBancaPrivadaCache;
	}

	/**
	 * Cache Operaciones Compra Venta.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheOperacionesTitulosBancaPrivada() {
		CacheConfiguration movimientosBancaPrivadaCache = new CacheConfiguration();
		movimientosBancaPrivadaCache.setName(CacheConstants.Names.CACHE_OPERACIONES_COMPRA_VENTA);
		movimientosBancaPrivadaCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		movimientosBancaPrivadaCache.setMaxEntriesLocalHeap(1000);
		movimientosBancaPrivadaCache.setTimeToIdleSeconds(90);
		movimientosBancaPrivadaCache.setTimeToLiveSeconds(180);
		movimientosBancaPrivadaCache.setEternal(false);
		movimientosBancaPrivadaCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return movimientosBancaPrivadaCache;
	}

	/**
	 * Cache Operaciones Licitacion.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheOperacionesLicitacionBancaPrivada() {
		CacheConfiguration movimientosBancaPrivadaCache = new CacheConfiguration();
		movimientosBancaPrivadaCache.setName(CacheConstants.Names.CACHE_OPERACIONES_LICITACION);
		movimientosBancaPrivadaCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		movimientosBancaPrivadaCache.setMaxEntriesLocalHeap(1000);
		movimientosBancaPrivadaCache.setTimeToIdleSeconds(90);
		movimientosBancaPrivadaCache.setTimeToLiveSeconds(180);
		movimientosBancaPrivadaCache.setEternal(false);
		movimientosBancaPrivadaCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return movimientosBancaPrivadaCache;
	}

	/**
	 * Cache fondos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cachePlazosFijos() {
		CacheConfiguration plazosFijosCache = new CacheConfiguration();
		plazosFijosCache.setName(CacheConstants.Names.CACHE_PLAZOS_FIJOS);
		plazosFijosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		plazosFijosCache.setMaxEntriesLocalHeap(100);
		plazosFijosCache.setEternal(true);
		plazosFijosCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return plazosFijosCache;
	}

	/**
	 * Cache tasas fondos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheTasasPlazosFijos() {
		CacheConfiguration tasasPlazosFijosCache = new CacheConfiguration();
		tasasPlazosFijosCache.setName(CacheConstants.Names.CACHE_TASAS_PLAZOS_FIJOS);
		tasasPlazosFijosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		tasasPlazosFijosCache.setMaxEntriesLocalHeap(100);
		tasasPlazosFijosCache.setTimeToLiveSeconds(600);
		tasasPlazosFijosCache.setEternal(false);
		tasasPlazosFijosCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return tasasPlazosFijosCache;
	}

	/**
	 * Cache tasas fondos.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheAccionesPlazosFijos() {
		CacheConfiguration cacheAccionesPlazosFijos = new CacheConfiguration();
		cacheAccionesPlazosFijos.setName(CacheConstants.Names.CACHE_DESCRIPCION_ACCIONES_VENCIMIENTO);
		cacheAccionesPlazosFijos.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		cacheAccionesPlazosFijos.setMaxEntriesLocalHeap(100);
		cacheAccionesPlazosFijos.setTimeToLiveSeconds(600);
		cacheAccionesPlazosFijos.setEternal(true);
		cacheAccionesPlazosFijos.setTransactionalMode(TransactionalMode.OFF.toString());
		return cacheAccionesPlazosFijos;
	}

	/**
	 * Cache paises marcacion.
	 * 
	 * @return cache legales
	 */
	private CacheConfiguration cacheObtenerPaisesMarcacion() {
		CacheConfiguration paisesCache = new CacheConfiguration();
		paisesCache.setName(CacheConstants.Names.CACHE_OBTENER_PAISES_MARCACION);
		paisesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		paisesCache.setMaxEntriesLocalHeap(1);
		paisesCache.setTimeToLiveSeconds(43200);
		paisesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return paisesCache;
	}

	/**
	 * Cache monedas comex.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheMonedasComex() {
		CacheConfiguration cacheMonedasComex = new CacheConfiguration();
		cacheMonedasComex.setName(CacheConstants.Names.CACHE_MONEDAS_COMEX);
		cacheMonedasComex.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		cacheMonedasComex.setTimeToIdleSeconds(DIA_SEGUNDOS);
		cacheMonedasComex.setTimeToLiveSeconds(DIA_SEGUNDOS);
		cacheMonedasComex.setEternal(false);
		cacheMonedasComex.setMaxEntriesLocalHeap(200);
		cacheMonedasComex.setTransactionalMode(TransactionalMode.OFF.toString());
		return cacheMonedasComex;
	}

	/**
	 * Cache paises comex.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cachePaisesComex() {
		CacheConfiguration cachePaisesComex = new CacheConfiguration();
		cachePaisesComex.setName(CacheConstants.Names.CACHE_PAISES_COMEX);
		cachePaisesComex.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		cachePaisesComex.setTimeToIdleSeconds(DIA_SEGUNDOS);
		cachePaisesComex.setTimeToLiveSeconds(DIA_SEGUNDOS);
		cachePaisesComex.setMaxEntriesLocalHeap(300);
		cachePaisesComex.setEternal(false);
		cachePaisesComex.setTransactionalMode(TransactionalMode.OFF.toString());
		return cachePaisesComex;
	}

	/**
	 * Cache informacion de mercado.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheInformacionMercado() {
		CacheConfiguration infomercadoCache = new CacheConfiguration();
		infomercadoCache.setName(CacheConstants.Names.CACHE_INFOMERCADO);
		infomercadoCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		infomercadoCache.setMaxEntriesLocalHeap(1000);
		infomercadoCache.setTimeToIdleSeconds(90);
		infomercadoCache.setTimeToLiveSeconds(180);
		infomercadoCache.setEternal(false);
		infomercadoCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return infomercadoCache;
	}

	/**
	 * Cache informacion de mercado.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheSuscripcionesMaps() {
		CacheConfiguration cacheSuscripcionesMaps = new CacheConfiguration();
		cacheSuscripcionesMaps.setName(CacheConstants.Names.CACHE_SUSCRIPCIONES_MAPS);
		cacheSuscripcionesMaps.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		cacheSuscripcionesMaps.setMaxEntriesLocalHeap(100);
		cacheSuscripcionesMaps.setTimeToIdleSeconds(1200);
		cacheSuscripcionesMaps.setTimeToLiveSeconds(1200);
		cacheSuscripcionesMaps.setEternal(false);
		cacheSuscripcionesMaps.setTransactionalMode(TransactionalMode.OFF.toString());
		return cacheSuscripcionesMaps;
	}

	/**
	 * Cache legales comex.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheLegalesComex() {
		CacheConfiguration cacheLegalesComex = new CacheConfiguration();
		cacheLegalesComex.setName(CacheConstants.Names.CACHE_LEGALES_COMEX);
		cacheLegalesComex.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		cacheLegalesComex.setTimeToIdleSeconds(DIA_SEGUNDOS);
		cacheLegalesComex.setTimeToLiveSeconds(DIA_SEGUNDOS);
		cacheLegalesComex.setMaxEntriesLocalHeap(100);
		cacheLegalesComex.setEternal(false);
		cacheLegalesComex.setTransactionalMode(TransactionalMode.OFF.toString());
		return cacheLegalesComex;
	}

	/**
	 * Cache chances.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheChances() {
		CacheConfiguration chancesCache = new CacheConfiguration();
		chancesCache.setName(CacheConstants.Names.CACHE_CHANCES);
		chancesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		chancesCache.setMaxEntriesLocalHeap(CACHE_CHANCES_SIZE);
		chancesCache.setTimeToIdleSeconds(300);
		chancesCache.setTimeToLiveSeconds(300);
		chancesCache.setEternal(false);
		chancesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return chancesCache;
	}

	/**
	 * Cache chances.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cachePagHaberes() {
		CacheConfiguration chancesCache = new CacheConfiguration();
		chancesCache.setName(CacheConstants.Names.CACHE_CNS_PAGO_HAB);
		chancesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		chancesCache.setMaxEntriesLocalHeap(150);
		chancesCache.setTimeToIdleSeconds(86400);
		chancesCache.setTimeToLiveSeconds(86400);
		chancesCache.setEternal(false);
		chancesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return chancesCache;
	}

	/**
	 * Cache chances.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheAdhesionDebitoAutomatico() {
		CacheConfiguration chancesCache = new CacheConfiguration();
		chancesCache.setName(CacheConstants.Names.CACHE_CNS_DDIADHG);
		chancesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		chancesCache.setMaxEntriesLocalHeap(150);
		chancesCache.setTimeToIdleSeconds(86400);
		chancesCache.setTimeToLiveSeconds(86400);
		chancesCache.setEternal(false);
		chancesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return chancesCache;
	}
	
	  /**
     * Cache Legales.
     * 
     * @return cache legales
     */
    private CacheConfiguration cacheConceptos() {
        CacheConfiguration cacheConceptos = new CacheConfiguration();
        cacheConceptos.setName(CacheConstants.Names.CACHE_CONCEPTOS_LIQ_ORDENPAGO);
        cacheConceptos.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
        cacheConceptos.setMaxEntriesLocalHeap(200);
        cacheConceptos.setEternal(true);
        cacheConceptos.setTransactionalMode(TransactionalMode.OFF.toString());
        return cacheConceptos;
    }
    
    /**
     * Cache campanias publicas.
     *
     * @return the cache configuration
     */
    private CacheConfiguration cacheCampaniasPublicas() {
        CacheConfiguration mensajesCache = new CacheConfiguration();
        mensajesCache.setName(CacheConstants.Names.CACHE_CAMPANIAS_PUBLICAS);
        mensajesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
        mensajesCache.setMaxEntriesLocalHeap(1000);
        mensajesCache.setEternal(true);
        mensajesCache.setTransactionalMode(TransactionalMode.OFF.toString());
        return mensajesCache;
    }

    /**
     * Cache queue it auth.
     *
     * @return the cache configuration
     */
    private CacheConfiguration cacheQueueSTAuth() {
        CacheConfiguration queueSTAuthCache = new CacheConfiguration();
        queueSTAuthCache.setName(CacheConstants.Names.CACHE_QUEUE_ST_TOKEN);
        queueSTAuthCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
        queueSTAuthCache.setMaxEntriesLocalHeap(100);
        queueSTAuthCache.setTimeToIdleSeconds(7100);
        queueSTAuthCache.setTimeToLiveSeconds(7100);
        queueSTAuthCache.setEternal(false);
        queueSTAuthCache.setTransactionalMode(TransactionalMode.OFF.toString());
        return queueSTAuthCache;
    }

    /**
     * Cache OAuthV2.
     *
     * @return the cache configuration
     */
    private CacheConfiguration cacheOAuthV2() {
        CacheConfiguration oAuthV2Cache = new CacheConfiguration();
        oAuthV2Cache.setName(CacheConstants.Names.CACHE_OAUTHV2);
        oAuthV2Cache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
        oAuthV2Cache.setMaxEntriesLocalHeap(100);
        oAuthV2Cache.setTimeToIdleSeconds(1500);
        oAuthV2Cache.setTimeToLiveSeconds(1500);
        oAuthV2Cache.setEternal(false);
        oAuthV2Cache.setTransactionalMode(TransactionalMode.OFF.toString());
        return oAuthV2Cache;
    }

	private CacheConfiguration cacheObpNupConsultaTenenciasFondos() {
		CacheConfiguration consultaTenenciasFondosCache = new CacheConfiguration();
		consultaTenenciasFondosCache.setName(CacheConstants.Names.CACHE_NUP_FONDOS_CNSTENVAL);
		consultaTenenciasFondosCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		consultaTenenciasFondosCache.setMaxEntriesLocalHeap(100);
		consultaTenenciasFondosCache.setTimeToIdleSeconds(300);
		consultaTenenciasFondosCache.setTimeToLiveSeconds(300);
		consultaTenenciasFondosCache.setEternal(false);
		consultaTenenciasFondosCache.setTransactionalMode(TransactionalMode.OFF.toString());

		return consultaTenenciasFondosCache;
	}

	private CacheConfiguration cacheObpNupObtenerTenenciaValuadaDetalleFondoOnline() {
		CacheConfiguration obtenerTenenciaValuadaDetalleFondoOnlineCache = new CacheConfiguration();
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setName(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setMaxEntriesLocalHeap(100);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setTimeToIdleSeconds(300);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setTimeToLiveSeconds(300);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setEternal(false);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setTransactionalMode(TransactionalMode.OFF.toString());

		return obtenerTenenciaValuadaDetalleFondoOnlineCache;
	}

	private CacheConfiguration cacheObpNupObtenerTenenciaValuadaDetalleFondoOnlineBP() {
		CacheConfiguration obtenerTenenciaValuadaDetalleFondoOnlineCache = new CacheConfiguration();
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setName(CacheConstants.Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setMaxEntriesLocalHeap(100);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setTimeToIdleSeconds(300);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setTimeToLiveSeconds(300);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setEternal(false);
		obtenerTenenciaValuadaDetalleFondoOnlineCache.setTransactionalMode(TransactionalMode.OFF.toString());

		return obtenerTenenciaValuadaDetalleFondoOnlineCache;
	}

	private CacheConfiguration cacheApiAuthClientIds() {
		CacheConfiguration apiAuthClientsIds = new CacheConfiguration();
		apiAuthClientsIds.setName(CacheConstants.Names.CACHE_API_AUTH_CLIENTS_IDS);
		apiAuthClientsIds.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		apiAuthClientsIds.setMaxEntriesLocalHeap(200);
		apiAuthClientsIds.setEternal(true);
		apiAuthClientsIds.setTransactionalMode(TransactionalMode.OFF.toString());
		return apiAuthClientsIds;
	}
		
	private CacheConfiguration cacheDiasNoHabiles(){
		CacheConfiguration obtenerDiasNoHabiles = new CacheConfiguration();
		obtenerDiasNoHabiles.setName(CacheConstants.Names.CACHE_DIAS_NO_HABILES);
		obtenerDiasNoHabiles.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		obtenerDiasNoHabiles.setMaxEntriesLocalHeap(250);
		obtenerDiasNoHabiles.setEternal(true);
		obtenerDiasNoHabiles.setTransactionalMode(TransactionalMode.OFF.toString());

		return obtenerDiasNoHabiles;
	}

	private CacheConfiguration cacheObpNupAccessFondosTenenciaBff() {
		CacheConfiguration obtenerAccessFondosTenenciaBffCache = new CacheConfiguration();
		obtenerAccessFondosTenenciaBffCache.setName(CacheConstants.Names.CACHE_NUP_ACCESS_FONDOS_TENENCIA_BFF);
		obtenerAccessFondosTenenciaBffCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		obtenerAccessFondosTenenciaBffCache.setMaxEntriesLocalHeap(100);
		obtenerAccessFondosTenenciaBffCache.setTimeToIdleSeconds(300);
		obtenerAccessFondosTenenciaBffCache.setTimeToLiveSeconds(300);
		obtenerAccessFondosTenenciaBffCache.setEternal(false);
		obtenerAccessFondosTenenciaBffCache.setTransactionalMode(TransactionalMode.OFF.toString());

		return obtenerAccessFondosTenenciaBffCache;
	}

	private CacheConfiguration cacheObpNupHoldingFondosTenenciaBff() {
		CacheConfiguration obtenerHoldingFondosTenenciaBffCache = new CacheConfiguration();
		obtenerHoldingFondosTenenciaBffCache.setName(CacheConstants.Names.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF);
		obtenerHoldingFondosTenenciaBffCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU.toString());
		obtenerHoldingFondosTenenciaBffCache.setMaxEntriesLocalHeap(100);
		obtenerHoldingFondosTenenciaBffCache.setTimeToIdleSeconds(300);
		obtenerHoldingFondosTenenciaBffCache.setTimeToLiveSeconds(300);
		obtenerHoldingFondosTenenciaBffCache.setEternal(false);
		obtenerHoldingFondosTenenciaBffCache.setTransactionalMode(TransactionalMode.OFF.toString());

		return obtenerHoldingFondosTenenciaBffCache;
	}
	
	private CacheConfiguration cacheSSOClients() {
		CacheConfiguration ssoClientsCache = new CacheConfiguration();
		ssoClientsCache.setName(CacheConstants.Names.CACHE_SSO_CONSENTS_CLIENTS);
		ssoClientsCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		ssoClientsCache.setMaxEntriesLocalHeap(100);
		ssoClientsCache.setTimeToIdleSeconds(3600);
		ssoClientsCache.setTimeToLiveSeconds(3600);
		ssoClientsCache.setEternal(false);
		ssoClientsCache.setTransactionalMode(CacheConfiguration.TransactionalMode.OFF.toString());
		return ssoClientsCache;
	}

	private CacheConfiguration cacheTransfersApiTokens() {

		CacheConfiguration transfersApiTokensCache = new CacheConfiguration();
		transfersApiTokensCache.setName(CacheConstants.Names.CACHE_TRANSFERS_API_TOKENS);
		transfersApiTokensCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		transfersApiTokensCache.setMaxEntriesLocalHeap(100);
		transfersApiTokensCache.setTimeToIdleSeconds(1800);
		transfersApiTokensCache.setTimeToLiveSeconds(1800);
		transfersApiTokensCache.setEternal(false);
		transfersApiTokensCache.setTransactionalMode(TransactionalMode.OFF.toString());

		return transfersApiTokensCache;

	}

	private CacheConfiguration cacheAlycsCuits() {

		CacheConfiguration alycsCuitsCache = new CacheConfiguration();
		alycsCuitsCache.setName(CacheConstants.Names.CACHE_ALYCS_CUITS);
		alycsCuitsCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		alycsCuitsCache.setMaxEntriesLocalHeap(300);
		alycsCuitsCache.setEternal(true);
		alycsCuitsCache.setTransactionalMode(TransactionalMode.OFF.toString());

		return alycsCuitsCache;

	}

	/**
	 * Key generator.
	 *
	 * @return the key generator
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		return new SimpleKeyGenerator();
	}
	
	/**
	 * Cache comprobantes scomp.
	 *
	 * @return the cache configuration
	 */
	private CacheConfiguration cacheBonificacionSeguros() {
		CacheConfiguration comprobantesCache = new CacheConfiguration();
		comprobantesCache.setName(CacheConstants.Names.CACHE_BONIFICACIONES_SEGUROS);
		comprobantesCache.setMemoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LFU.toString());
		comprobantesCache.setTimeToIdleSeconds(DIA_SEGUNDOS);
		comprobantesCache.setTimeToLiveSeconds(DIA_SEGUNDOS);
		comprobantesCache.setEternal(false);
		comprobantesCache.setMaxEntriesLocalHeap(200);
		comprobantesCache.setTransactionalMode(TransactionalMode.OFF.toString());
		return comprobantesCache;
	}
}
