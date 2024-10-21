/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CajaAhorroPesosSelectorView.
 */
public class CajaAhorroPesosSelectorView extends CuentaSelectorDetalleView {

	/**
	 * Instantiates a new caja ahorro pesos selector view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		if (!resumenDetalleCuenta.getModoNocturno()) {
			this.setSaldoPesos(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoPesos(), resumenDetalleCuenta));
			this.setSaldoPesosValue(resumenDetalleCuenta.getSaldoPesos());
		} else {
			this.setSaldoPesos(GUION);
			this.setSaldoPesosValue(null);
		}
		this.setMoneda(CuentaSelectorDetalleView.PESO);
	}

}
