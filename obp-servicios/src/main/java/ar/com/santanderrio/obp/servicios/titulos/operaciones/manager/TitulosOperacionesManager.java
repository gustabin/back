/*
 * 
 */
package ar.com.santanderrio.obp.servicios.titulos.operaciones.manager;

import java.util.List;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.web.view.ReporteView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ComprobanteDetalleOperacionLicitacionCanje;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.CuentasOperativasView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.OperacionesTitulosView;
import ar.com.santanderrio.obp.servicios.titulos.operaciones.view.ParametrosOperacionesView;

/**
 * The Interface TitulosOperacionesManager.
 */
public interface TitulosOperacionesManager {

    /**
	 * Obtener operaciones primera vez.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return the respuesta
	 */
    Respuesta<OperacionesTitulosView> obtenerOperacionesPrimeraVez(ParametrosOperacionesView parametrosOperacionesView);
    
    /**
	 * Obtener mas operaciones.
	 *
	 * @return the respuesta
	 */
    Respuesta<OperacionesTitulosView> obtenerMasOperaciones();

    /**
	 * Obtener cuentas operativas.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return the respuesta
	 */
    Respuesta<List<CuentasOperativasView>> obtenerCuentasOperativas(ParametrosOperacionesView parametrosOperacionesView);

    /**
	 * Obtener detalle operacion.
	 *
	 * @param parametrosOperacionesView
	 *            the parametros operaciones view
	 * @return the respuesta
	 */
    Respuesta<OperacionTitulosView> obtenerDetalleOperacion(ParametrosOperacionesView parametrosOperacionesView);
    
    /**
	 * Descargar comprobante detalle operacion.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the respuesta
	 */
    Respuesta<ReporteView> descargarComprobanteDetalleOperacion(String tipoOperacion);
    
    /**
	 * Descargar comprobante detalle operacion.
	 *
	 * @param tipoOperacion
	 *            the tipo operacion
	 * @return the respuesta
	 */
    Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanje(ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje);
    
    Respuesta<ReporteView> descargarComprobanteDetalleOperacionCanjeBPriv(ComprobanteDetalleOperacionLicitacionCanje comprobanteDetalleOperacionLicitacionCanje);
    
    Respuesta<Boolean> vaciarCacheOperaciones();

}
