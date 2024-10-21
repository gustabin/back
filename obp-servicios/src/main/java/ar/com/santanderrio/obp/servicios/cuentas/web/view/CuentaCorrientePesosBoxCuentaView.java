/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CuentaCorrientePesosBoxCuentaView.
 */
public class CuentaCorrientePesosBoxCuentaView extends BoxCuentaView {

	/**
	 * Instantiates a new cuenta corriente pesos box cuenta view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		this.setIsCuentaUnica(Boolean.FALSE);
		this.setMoneda(MONEDA_PESO);
		this.setSaldo(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoPesos(), resumenDetalleCuenta));
		this.setSaldoValue(resumenDetalleCuenta.getSaldoPesos());
	}

}
