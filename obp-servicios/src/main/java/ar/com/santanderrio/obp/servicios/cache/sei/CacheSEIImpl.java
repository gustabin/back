/**
 * 
 */
package ar.com.santanderrio.obp.servicios.cache.sei;

import java.util.List;

import ar.com.santanderrio.obp.servicios.transferencias.web.manager.AlycsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cache.manager.UsedCacheManager;
import ar.com.santanderrio.obp.servicios.cache.view.CacheView;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexConsultasManager;
import ar.com.santanderrio.obp.servicios.comex.transfext.manager.ComexLiquidacionOrdenPagoManager;
import ar.com.santanderrio.obp.servicios.comun.campaniapublica.manager.CampaniaPublicaManager;
import ar.com.santanderrio.obp.servicios.comun.legal.manager.LegalManager;
import ar.com.santanderrio.obp.servicios.comun.mensajes.web.manager.MensajeManager;
import ar.com.santanderrio.obp.servicios.inversiones.fondos.manager.FondoManager;
import ar.com.santanderrio.obp.servicios.inversiones.plazofijo.manager.PlazoFijoManager;
import ar.com.santanderrio.obp.servicios.login.manager.ApiAuthManager;
import ar.com.santanderrio.obp.servicios.modulos.manager.ModuloPermisoManager;
import ar.com.santanderrio.obp.servicios.pagos.manager.BuscadorEmpresaIndexadoManager;
import ar.com.santanderrio.obp.servicios.prestamos.web.manager.DestinoPrestamoManager;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.manager.TitulosOperacionesManager;

/**
 * The Class CacheSEIImpl.
 *
 * @author sergio.e.goldentair
 */
@Component("cacheSEI")
public class CacheSEIImpl implements CacheSEI {

    /** The legal manager. */
    @Autowired
    private LegalManager legalManager;

    /** The used cache manager. */
    @Autowired
    private UsedCacheManager usedCacheManager;

    /** The used cache manager. */
    @Autowired
    private ModuloPermisoManager moduloPermisoManager;

    /** The mensaje manager. */
    @Autowired
    private MensajeManager mensajeManager;

    /** The destino prestamo manager. */
    @Autowired
    private DestinoPrestamoManager destinoPrestamoManager;

    /** The fondo manager. */
    @Autowired
    private FondoManager fondoManager;

    /** The plazo fijo manager. */
    @Autowired
    private PlazoFijoManager plazoFijoManager;

    /** The comex consultas manager. */
    @Autowired
    private ComexConsultasManager comexConsultasManager;

    @Autowired
    private BuscadorEmpresaIndexadoManager buscadorEmpresaIndexadoManager;
    
    @Autowired
    private ComexLiquidacionOrdenPagoManager comexLiquidacionOrdenPagoManager;

 
    @Autowired
    private CampaniaPublicaManager campaniaPublicaManager;
    
    /** The titulos operaciones manager. */
	@Autowired
	private TitulosOperacionesManager titulosOperacionesManager;

	@Autowired
	private ApiAuthManager apiAuthManager;

    @Autowired
    private AlycsManager alycsManager;



    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#limpiarLegales()
     */
    @Override
    public Respuesta<Boolean> limpiarLegales() {
        return legalManager.vaciarLegales();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#obtenerEstadisticas( )
     */
    @Override
    public Respuesta<List<CacheView>> obtenerEstadisticas() {
        return usedCacheManager.obtenerEstadisticas();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#limpiarMensajes()
     */
    @Override
    public Respuesta<Boolean> limpiarMensajes() {
        return mensajeManager.limpiarMensajes();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#
     * limpiarModulosPermitidos()
     */
    @Override
    public Respuesta<Boolean> limpiarModulosPermitidos() {
        return moduloPermisoManager.limpiarModuloPermisos();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#
     * limpiarMotivosPrestamo()
     */
    @Override
    public Respuesta<Boolean> limpiarMotivosPrestamo() {
        return destinoPrestamoManager.vaciarDestinosPrestamo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#limpiarFondos()
     */
    @Override
    public Respuesta<Boolean> limpiarFondos() {
        return fondoManager.vaciarCache();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#limpiarPlazosFijos()
     */
    @Override
    public Respuesta<Boolean> limpiarPlazosFijos() {
        return plazoFijoManager.vaciarCachePlazosFijos();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#
     * limpiarTasasPlazosFijos()
     */
    @Override
    public Respuesta<Boolean> limpiarTasasPlazosFijos() {
        return plazoFijoManager.vaciarCacheTasas();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#
     * limpiarAccionesPlazosFijos()
     */
    @Override
    public Respuesta<Boolean> limpiarAccionesPlazosFijos() {
        return plazoFijoManager.vaciarCacheAcciones();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#
     * limpiarPaisesComex()
     */
    @Override
    public Respuesta<Boolean> limpiarPaisesComex() {
        return comexConsultasManager.vaciarPaises();
    }

    /*
     * (non-Javadoc)
     * 
     * @see ar.com.santanderrio.obp.servicios.cache.sei.CacheSEI#
     * limpiaMonedasComex()
     */
    @Override
    public Respuesta<Boolean> limpiarMonedasComex() {
        return comexConsultasManager.vaciarMonedas();
    }

    @Override
    public Respuesta<Boolean> limpiarMediosDePago() {
        return buscadorEmpresaIndexadoManager.limpiarMediosDePago();
    }

	@Override
	public Respuesta<Boolean> limpiarConceptosLiquidacionOrdenPago() {
		return comexLiquidacionOrdenPagoManager.limpiarConceptos();
	}

	@Override
	public Respuesta<Boolean> limpiarCampaniasPublicas() {
		return campaniaPublicaManager.limpiarCampaniasPublicas();
	}

	@Override
	public Respuesta<Boolean> limpiarCacheOperaciones() {
		return titulosOperacionesManager.vaciarCacheOperaciones();
	}

	
	@Override
	public Respuesta<Boolean> limpiarCacheApiAuth() {
		
		return apiAuthManager.limpiarCacheApiAuth();
	}

    @Override
    public Respuesta<Boolean> limpiarCacheCuitsAlycs() {

        return alycsManager.limpiarCacheCuitsAlycs();
    }


}
