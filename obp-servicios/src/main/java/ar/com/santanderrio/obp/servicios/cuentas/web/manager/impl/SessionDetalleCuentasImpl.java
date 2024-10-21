/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.manager.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.cuentas.web.manager.DetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.web.manager.SessionDetalleCuentas;

/**
 * The Class SessionDetalleCuentasImpl.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionDetalleCuentasImpl implements SessionDetalleCuentas {

	/** The detalle cuenta. */
	private List<DetalleCuenta> detalleCuenta;

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.web.manager.SessionDetalleCuentas#
	 * getDetalleCuentaList()
	 */
	@Override
	public List<DetalleCuenta> getDetalleCuentaList() {
		return detalleCuenta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.cuentas.web.manager.SessionDetalleCuentas#
	 * setDetalleCuentaList(java.util.List)
	 */
	@Override
	public void setDetalleCuentaList(List<DetalleCuenta> detalleCuenta) {
		this.detalleCuenta = detalleCuenta;
	}

}
