/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CajaAhorroDolaresSelectorView.
 */
public class CajaAhorroDolaresSelectorView extends CuentaSelectorDetalleView {

	/**
	 * Instantiates a new caja ahorro dolares selector view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		if (!resumenDetalleCuenta.getModoNocturno()) {
			this.setSaldoDolares(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoDolares(), resumenDetalleCuenta));
			this.setSaldoDolaresValue(resumenDetalleCuenta.getSaldoDolares());
		} else {
			this.setSaldoDolares(GUION);
			this.setSaldoDolaresValue(null);
		}
		this.setMoneda(CuentaSelectorDetalleView.DOLAR);
	}

}
