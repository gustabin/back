package ar.com.santanderrio.obp.config.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * The Interface CacheConstants.
 *
 * @author sergio.e.goldentair
 */
public enum CacheConstants {
	// @formatter:off
    CACHE_LEGALES(Names.CACHE_LEGALES), 
    CACHE_DESTINOS_BCRA(Names.CACHE_DESTINOS_BCRA), 
    CACHE_MENSAJES(Names.CACHE_MENSAJES),
    CACHE_MODULOS_PERMISOS(Names.CACHE_MODULOS_PERMISOS),
    CACHE_FONDOS(Names.CACHE_FONDOS), 
    CACHE_COMPROBANTES_SCOMP(Names.CACHE_COMPROBANTES_SCOMP),
    CACHE_COMPROBANTES_HISTORIAL(Names.CACHE_COMPROBANTES_HISTORIAL),
    CACHE_PLAZOS_FIJOS(Names.CACHE_PLAZOS_FIJOS),
    CACHE_TASAS_PLAZOS_FIJOS(Names.CACHE_TASAS_PLAZOS_FIJOS),
    CACHE_ULTIMOS_MOVIMIENTOS(Names.CACHE_ULTIMOS_MOVIMIENTOS),
    CACHE_DESCRIPCION_ACCIONES_VENCIMIENTO(Names.CACHE_DESCRIPCION_ACCIONES_VENCIMIENTO),
    CACHE_MOVIMIENTOS_BANCA_PRIVADA(Names.CACHE_MOVIMIENTOS_BANCA_PRIVADA),
    CACHE_OPERACIONES_COMPRA_VENTA(Names.CACHE_OPERACIONES_COMPRA_VENTA),
    CACHE_OPERACIONES_LICITACION(Names.CACHE_OPERACIONES_LICITACION),
    CACHE_OBTENER_PAISES_MARCACION(Names.CACHE_OBTENER_PAISES_MARCACION),
    CACHE_MONEDAS_COMEX(Names.CACHE_MONEDAS_COMEX),
    CACHE_PAISES_COMEX(Names.CACHE_PAISES_COMEX),
    CACHE_INFOMERCADO(Names.CACHE_INFOMERCADO),
    CACHE_SUSCRIPCIONES_MAPS(Names.CACHE_SUSCRIPCIONES_MAPS),
    CACHE_LEGALES_COMEX(Names.CACHE_LEGALES_COMEX),
    CACHE_CHANCES(Names.CACHE_CHANCES),
	CACHE_CNS_PAGO_HAB(Names.CACHE_CNS_PAGO_HAB),
	CACHE_CNS_DDIADHG(Names.CACHE_CNS_DDIADHG),
	//las cache que tienen la expresion CACHE_NYO_ o CACHE_NUP_ son de session por lo que si se requiere utilizar alguna CONSULTAR
	CACHE_NUP_CNSPAQCNLS(Names.CACHE_NUP_CNSPAQCNLS),
	CACHE_NUP_IDEPESBANE(Names.CACHE_NUP_IDEPESBANE),
	CACHE_NYO_CNSTJCDATO(Names.CACHE_NYO_CNSTJCDATO),
	CACHE_NYO_CNSCTAMOVS(Names.CACHE_NYO_CNSCTAMOVS),
	CACHE_NYO_CNSDDIDERE(Names.CACHE_NYO_CNSDDIDERE),
	//las cache que tienen la expresion CACHE_NYO_ o CACHE_NUP_ son de session por lo que si se requiere utilizar alguna CONSULTAR
	CACHE_CAMPANIAS_PUBLICAS(Names.CACHE_CAMPANIAS_PUBLICAS),
	CACHE_QUEUE_ST_TOKEN(Names.CACHE_QUEUE_ST_TOKEN),
	CACHE_OAUTHV2(Names.CACHE_OAUTHV2),
	CACHE_FONDOS_COTIZACIONES(Names.CACHE_FONDOS_COTIZACIONES),
	CACHE_NUP_FONDOS_CNSTENVAL(Names.CACHE_NUP_FONDOS_CNSTENVAL),
	CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE(Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE),
	CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP(Names.CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP),
	CACHE_API_AUTH_CLIENTS_IDS(Names.CACHE_API_AUTH_CLIENTS_IDS),
	CACHE_DIAS_NO_HABILES(Names.CACHE_DIAS_NO_HABILES),
	CACHE_NUP_ACCESS_FONDOS_TENENCIA_BFF(Names.CACHE_NUP_ACCESS_FONDOS_TENENCIA_BFF),
	CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF(Names.CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF),
	CACHE_ALYCS_CUITS(Names.CACHE_ALYCS_CUITS);
    // @formatter:on

	public class Names {
		/** Cache para legales. */
		public static final String CACHE_LEGALES = "legalesCache";
		/** Cache para destinos BCRA. */
		public static final String CACHE_DESTINOS_BCRA = "destinosBCRA";
		/** Cache para mensajes. */
		public static final String CACHE_MENSAJES = "mensajesCache";
		/** Cache para modulos excluidos. */
		public static final String CACHE_MODULOS_PERMISOS = "moduloPermisoCache";
		/** cache para fondos. */
		public static final String CACHE_FONDOS = "fondosCache";
		/** cache para Comprobantes SCOMP por nup. */
		public static final String CACHE_COMPROBANTES_SCOMP = "comprobanteScompCache";
		/** cache para Comprobantes HISTORIAL por nup. */
		public static final String CACHE_COMPROBANTES_HISTORIAL = "comprobanteHistorialCache";
		/** cache para plazos fijos. */
		public static final String CACHE_PLAZOS_FIJOS = "plazosFijosCache";
		/** cache para tasas de plazos fijos. */
		public static final String CACHE_TASAS_PLAZOS_FIJOS = "tasasPlazosFijosCache";
		/** cache para ultimos movimientos de cuentas. */
		public static final String CACHE_ULTIMOS_MOVIMIENTOS = "ultimosMovimientosCache";
		/** cache para acciones al vencimiento plazos fijos. */
		public static final String CACHE_DESCRIPCION_ACCIONES_VENCIMIENTO = "descripcionAccionesVencimientoCache";
		/** cache para acciones al vencimiento plazos fijos. */
		public static final String CACHE_MOVIMIENTOS_BANCA_PRIVADA = "cacheMovimientosBancaPrivada";
		/** The Constant CACHE_OPERACIONES_COMPRA_VENTA. */
		public static final String CACHE_OPERACIONES_COMPRA_VENTA = "cacheOperacionesCompraVenta";
		/** The Constant CACHE_OPERACIONES_LICITACION. */
		public static final String CACHE_OPERACIONES_LICITACION = "cacheOperacionesLicitacion";
		/** cache para acciones al obtener Paises Marcacion. */
		public static final String CACHE_OBTENER_PAISES_MARCACION = "obtenerPaisesMarcacion";
		/** cache para Consulta Monedas Comex. */
		public static final String CACHE_MONEDAS_COMEX = "monedasComexCache";
		/** cache para Consulta Paises Comex. */
		public static final String CACHE_PAISES_COMEX = "paisesComexCache";
		/** cache para informacion de mercado. */
		public static final String CACHE_INFOMERCADO = "infomercadoCache";
		/** cache para informacion de mercado. */
		public static final String CACHE_SUSCRIPCIONES_MAPS = "suscripcionesMapsCache";
		/** cache para Consulta Legales Comex. */
		public static final String CACHE_LEGALES_COMEX = "legalesComexCache";
		/** cache de session para consulta los paquetes del cliente. */
		public static final String CACHE_NUP_CNSPAQCNLS = "obpNupCnsPaqCnls";
		/** cache de session para consulta datos de la tarjeta de credito. */
		public static final String CACHE_NYO_CNSTJCDATO = "obpNyoCnsTjcDato";
		/** cache para CACHE CHANCES PREMIACIONES. */
		public static final String CACHE_CHANCES = "chancesCache";
		/** cache para CACHE CONSULTA ADH. PAGO DE HABERES. */
		public static final String CACHE_CNS_PAGO_HAB = "cnsPagoHaberes";
		/** cache para CACHE Consulta adhesion al debito automatico. */
		public static final String CACHE_CNS_DDIADHG = "cnsDdiadhg";

		//las cache que tienen la expresion CACHE_NYO_ o CACHE_NUP_ son de session por lo que si se requiere utilizar alguna CONSULTAR
		/** cache de session para consulta Pago Electronico Banelco. */
		public static final String CACHE_NUP_IDEPESBANE = "obpNupIdePesBane";
		/** cache de session para consulta los paquetes del cliente. */
		/** cache de session para consulta debito directo de debitos reversiones. */
		public static final String CACHE_NYO_CNSDDIDERE = "obpNyoCnsDDiDeRe";
		/** cache de session para consulta datos de la tarjeta de credito. */
		/**
		 * cache de session para consulta los movimientos de la cuenta cuando es
		 * consolidada 'C' y los ultimos 7 dias NO para mas tiempo ni los 'D'.
		 */
		public static final String CACHE_NYO_CNSCTAMOVS = "obpNyoCnsctamovs";
		//las cache que tienen la expresion CACHE_NYO_ o CACHE_NUP_ son de session por lo que si se requiere utilizar alguna CONSULTAR

		/** cache para conceptos solicitud orden de pago. */
		public static final String CACHE_CONCEPTOS_LIQ_ORDENPAGO = "conceptosCache";	

		/** cache para campanias publicas. */
		public static final String CACHE_CAMPANIAS_PUBLICAS = "campaniaPublicaCache";

		/** cache para queue-st token. */
		public static final String CACHE_QUEUE_ST_TOKEN = "queueSTTokenCache";

		/** cache para OAUTHV2. */
		public static final String CACHE_OAUTHV2 = "OAuthV2Cache";

		/** cache para la consulta de cotizaciones de fondos **/
		public static final String CACHE_FONDOS_COTIZACIONES = "cacheCotizacionesFondos";
		
		/** cache para Bonificaciones seguros por nup. */
		public static final String CACHE_BONIFICACIONES_SEGUROS = "bonificacionesSegurosCache";

		/** cache de sesion para la consulta de tenencia de fondos **/
		public static final String CACHE_NUP_FONDOS_CNSTENVAL = "obpNupConsultaTenenciasFondos";

		/** cache de sesion para obtenerTenenciaValuadaDetalleFondoOnline PyL RTL**/
		public static final String CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE = "obpNupObtenerTenenciaValuadaDetalleFondoOnline";

		/** cache de sesion para obtenerTenenciaValuadaDetalleFondoOnline PyL BP **/
		public static final String CACHE_NUP_FONDOS_TENVAL_DETALLE_FONDO_ONLINE_BP = "obpNupObtenerTenenciaValuadaDetalleFondoOnlineBP";

		public static final String CACHE_API_AUTH_CLIENTS_IDS = "apiAuthClientsIds";

		/** cache para el servicio ConsultaDiasNoHabiles */
		public static final String CACHE_DIAS_NO_HABILES = "cacheConsultaDiasNoHabiles";
		public static final String CACHE_TRANSFERS_API_TOKENS = "transfersApiTokensCache";

		/** cache para el servicio access en fondos-tenencia-bff */
		public static final String CACHE_NUP_ACCESS_FONDOS_TENENCIA_BFF = "obpNupAccessFondosTenenciaBff";

		/** cache para el servicio holding de fondos-tenencia-bff */
		public static final String CACHE_NUP_HOLDING_FONDOS_TENENCIA_BFF = "obpNupHoldingFondosTenenciaBff";

		/** cache billeteras registradas */
		public static final String CACHE_SSO_CONSENTS_CLIENTS = "SSO_CONSENTS_CLIENTS_CACHE";

		public static final String CACHE_ALYCS_CUITS = "alycsCuitsCache";

		/**
		 * 
		 */
		private Names() {
			super();
		}
	}

	/**
	 * Nombre para las caches.
	 */
	private final String cacheName;

	private static List<String> cachesObpNup = new ArrayList<String>();
	private static List<String> cachesObpNyo = new ArrayList<String>();

	static {
		generarListaFiltrada("obpNup*", cachesObpNup);
		generarListaFiltrada("obpNyo*", cachesObpNyo);
	}

	/**
	 * 
	 */
	private CacheConstants(String cacheName) {
		this.cacheName = cacheName;
	}

	/**
	 * Filtrar el enum para obtener un sub conjunto segun una expresion regular.
	 * 
	 * @param patron
	 * @param listaCaches
	 */
	private static void generarListaFiltrada(String patron, List<String> listaCaches) {
		Pattern pattern = Pattern.compile(patron);
		for (CacheConstants cache : CacheConstants.values()) {
			if (pattern.matcher(cache.toString()).find()) {
				listaCaches.add(cache.toString());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.cacheName;
	}

	/**
	 * @return the cachesObpNup
	 */
	public static List<String> getCachesObpNup() {
		return cachesObpNup;
	}

	/**
	 * @return the cachesObpNyo
	 */
	public static List<String> getCachesObpNyo() {
		return cachesObpNyo;
	}
}
