/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CuentaUnicaDolaresBoxCuentaView.
 */
public class CuentaUnicaDolaresBoxCuentaView extends BoxCuentaView {

	/**
	 * Instantiates a new cuenta unica dolares box cuenta view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		this.setIsCuentaUnica(Boolean.TRUE);
		this.setMoneda(MONEDA_DOLAR);
		this.setSaldo(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCajaAhorroDolares(), resumenDetalleCuenta));
		this.setSaldoValue(resumenDetalleCuenta.getSaldoCajaAhorroDolares());
	}

}
