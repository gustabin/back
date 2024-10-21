/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.base.respuesta.entities.Respuesta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.ConsultaUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.entities.DetalleUltimosMovimientos;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionMovimientos;

/**
 * The Class SessionMovimientosImpl.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionMovimientosImpl implements SessionMovimientos {

	/** The detalle ultimos movimientos original. */
	private Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosOriginal;

	/** The detalle ultimos movimientos filtrado. */
	private Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosFiltrado;

	/** The filtro. */
	private ConsultaUltimosMovimientos filtro;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.web.manager.SessionMovimientos#
	 * getDetalleUltimosMovimientosOriginal()
	 */
	public Respuesta<DetalleUltimosMovimientos> getDetalleUltimosMovimientosOriginal() {
		return detalleUltimosMovimientosOriginal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.web.manager.SessionMovimientos#
	 * setDetalleUltimosMovimientosOriginal(ar.com.santanderrio.base.respuesta.
	 * entities.Respuesta)
	 */
	public void setDetalleUltimosMovimientosOriginal(
			Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosOriginal) {
		this.detalleUltimosMovimientosOriginal = detalleUltimosMovimientosOriginal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.web.manager.SessionMovimientos#
	 * getDetalleUltimosMovimientosFiltrado()
	 */
	public Respuesta<DetalleUltimosMovimientos> getDetalleUltimosMovimientosFiltrado() {
		return detalleUltimosMovimientosFiltrado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.web.manager.SessionMovimientos#
	 * setDetalleUltimosMovimientosFiltrado(ar.com.santanderrio.base.respuesta.
	 * entities.Respuesta)
	 */
	public void setDetalleUltimosMovimientosFiltrado(
			Respuesta<DetalleUltimosMovimientos> detalleUltimosMovimientosFiltrado) {
		this.detalleUltimosMovimientosFiltrado = detalleUltimosMovimientosFiltrado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.web.manager.SessionMovimientos#getFiltro(
	 * )
	 */
	public ConsultaUltimosMovimientos getFiltro() {
		return filtro;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.cuentas.web.manager.SessionMovimientos#setFiltro(
	 * ar.com.santanderrio.obp.cuentas.entities.ConsultaUltimosMovimientos)
	 */
	public void setFiltro(ConsultaUltimosMovimientos filtro) {
		this.filtro = filtro;
	}

}
