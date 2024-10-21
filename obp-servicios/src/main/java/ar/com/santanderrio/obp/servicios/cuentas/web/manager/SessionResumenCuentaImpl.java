/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.SessionResumenCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenMensualCuenta;
import ar.com.santanderrio.obp.servicios.resumen.entities.ResumenesMensualesDisponiblesCuenta;

/**
 * The Class SessionResumenCuentaImpl.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionResumenCuentaImpl implements SessionResumenCuenta {

	/** The resumenes mensuales disponibles. */
	private List<ResumenesMensualesDisponiblesCuenta> resumenesMensualesDisponibles = new ArrayList<ResumenesMensualesDisponiblesCuenta>();

	/** The resumenes mensuales disponibles. */
	private List<ResumenesMensualesDisponiblesCuenta> resumenesMensualesDisponiblesBP = new ArrayList<ResumenesMensualesDisponiblesCuenta>();

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
	 * SessionResumenCuenta#setResumenesMensualesDisponiblesByCuenta(ar.com.
	 * santanderrio.obp.base.respuesta.entities.Respuesta,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public void setResumenesMensualesDisponiblesByCuenta(
			Respuesta<List<ResumenMensualCuenta>> resumenesMensualesDisponibles, AbstractCuenta cuenta) {
		ResumenesMensualesDisponiblesCuenta resumenesMensualesDisponiblesCuenta = new ResumenesMensualesDisponiblesCuenta();
		resumenesMensualesDisponiblesCuenta.setCuenta(cuenta);
		resumenesMensualesDisponiblesCuenta.setResumenMensualCuenta(resumenesMensualesDisponibles);
		this.resumenesMensualesDisponibles.add(resumenesMensualesDisponiblesCuenta);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
	 * SessionResumenCuenta#getResumenesByCuenta(ar.com.santanderrio.obp.
	 * servicios.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public Respuesta<List<ResumenMensualCuenta>> getResumenesByCuenta(AbstractCuenta cuenta) {
		if (resumenesMensualesDisponibles != null) {
			for (ResumenesMensualesDisponiblesCuenta resumenMensualDisponible : resumenesMensualesDisponibles) {
				if (resumenMensualDisponible.getCuenta().getNroCuentaProducto().equals(cuenta.getNroCuentaProducto())) {
					return resumenMensualDisponible.getResumenesMensualesCuenta();
				}
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
	 * SessionResumenCuenta#getResumenesByIds(java.util.List,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public List<ResumenMensualCuenta> getResumenesByIds(List<String> ids, AbstractCuenta cuenta) {
		List<ResumenMensualCuenta> resumenesCuentaEnSesion = null;
		List<ResumenMensualCuenta> resumenesMensuales = new ArrayList<ResumenMensualCuenta>();
		if (resumenesMensualesDisponibles != null) {
			// obtengo la lista de resumenes en sesion por cuenta
			for (ResumenesMensualesDisponiblesCuenta resumenMensualDisponible : resumenesMensualesDisponibles) {
				if (resumenMensualDisponible.getCuenta().getNroCuentaProducto().equals(cuenta.getNroCuentaProducto())) {
					resumenesCuentaEnSesion = resumenMensualDisponible.getResumenesMensualesCuenta().getRespuesta();
				}
			}

			// filtra la lista en sesion segun la cuenta por id
			for (String id : ids) {
				for (ResumenMensualCuenta resumenMensualCuenta : resumenesCuentaEnSesion) {
					if (resumenMensualCuenta.getId().toString().equals(id)) {
						resumenesMensuales.add(resumenMensualCuenta);
						break;
					}
				}
			}
		}
		return resumenesMensuales;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.comun.sesion.web.entities.
	 * SessionResumenCuenta#marcarVistos(java.util.List,
	 * ar.com.santanderrio.obp.servicios.cuentas.entities.AbstractCuenta)
	 */
	@Override
	public void marcarVistos(List<ResumenMensualCuenta> resumenesVistos, AbstractCuenta cuenta) {
		List<ResumenMensualCuenta> resumenesEnSesion = getResumenesByCuenta(cuenta).getRespuesta();
		// Recorre todos los resumenes que vienen del front
		for (ResumenMensualCuenta resumenVisto : resumenesVistos) {
			// Busca el resumen que coincida con el que esta en sesion y lo
			// marca como visto
			for (ResumenMensualCuenta resumenEnSesion : resumenesEnSesion) {
				if (resumenVisto.getId().equals(resumenEnSesion.getId())) {
					resumenEnSesion.setVisto(true);
					break;
				}
			}
		}

	}
	
	@Override
	public void setResumenesMensualesDisponiblesByCuentaBP(
			Respuesta<List<ResumenMensualCuenta>> resumenesMensualesDisponibles, AbstractCuenta cuenta) {
		ResumenesMensualesDisponiblesCuenta resumenesMensualesDisponiblesCuenta = new ResumenesMensualesDisponiblesCuenta();
		resumenesMensualesDisponiblesCuenta.setCuenta(cuenta);
		resumenesMensualesDisponiblesCuenta.setResumenMensualCuenta(resumenesMensualesDisponibles);
		this.resumenesMensualesDisponiblesBP.add(resumenesMensualesDisponiblesCuenta);
	}
	
	@Override
	public Respuesta<List<ResumenMensualCuenta>> getResumenesByCuentaBP(AbstractCuenta cuenta) {
		if (resumenesMensualesDisponiblesBP != null) {
			for (ResumenesMensualesDisponiblesCuenta resumenMensualDisponible : resumenesMensualesDisponiblesBP) {
				if (resumenMensualDisponible.getCuenta().getNroCuentaProducto().equals(cuenta.getNroCuentaProducto())) {
					return resumenMensualDisponible.getResumenesMensualesCuenta();
				}
			}
		}
		return null;
	}
	
	@Override
	public List<ResumenMensualCuenta> getResumenesBPByIds(List<String> ids, AbstractCuenta cuenta) {
		List<ResumenMensualCuenta> resumenesCuentaEnSesion = null;
		List<ResumenMensualCuenta> resumenesMensuales = new ArrayList<ResumenMensualCuenta>();
		if (resumenesMensualesDisponiblesBP != null) {
			// obtengo la lista de resumenes en sesion por cuenta
			for (ResumenesMensualesDisponiblesCuenta resumenMensualDisponible : resumenesMensualesDisponiblesBP) {
				if (resumenMensualDisponible.getCuenta().getNroCuentaProducto().equals(cuenta.getNroCuentaProducto())) {
					resumenesCuentaEnSesion = resumenMensualDisponible.getResumenesMensualesCuenta().getRespuesta();
				}
			}

			// filtra la lista en sesion segun la cuenta por id
			for (String id : ids) {
				for (ResumenMensualCuenta resumenMensualCuenta : resumenesCuentaEnSesion) {
					if (resumenMensualCuenta.getId().toString().equals(id)) {
						resumenesMensuales.add(resumenMensualCuenta);
						break;
					}
				}
			}
		}
		return resumenesMensuales;
	}
	

}
