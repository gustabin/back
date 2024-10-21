/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CuentaUnicaPesosBoxCuentaView.
 */
public class CuentaUnicaPesosBoxCuentaView extends BoxCuentaView {

	/**
	 * Instantiates a new cuenta unica pesos box cuenta view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		setIsCuentaUnica(Boolean.TRUE);
		setMoneda(MONEDA_PESO);
		this.setSaldo(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoPesos(), resumenDetalleCuenta));
		setSaldoValue(resumenDetalleCuenta.getSaldoPesos());
		if (!resumenDetalleCuenta.getModoNocturno()) {
			if (resumenDetalleCuenta.isConvenioPAS()) {
				this.setSaldoCajaAhorro(
						formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCuentaSueldoPesos(), resumenDetalleCuenta));
				this.setSaldoCajaAhorroValue(resumenDetalleCuenta.getSaldoCuentaSueldoPesos());
			} else {
				this.setSaldoCajaAhorro(
						formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCajaAhorroPesos(), resumenDetalleCuenta));
				this.setSaldoCajaAhorroValue(resumenDetalleCuenta.getSaldoCajaAhorroPesos());
			}
			if (SOBREGIRO.equals(resumenDetalleCuenta.getIndicadorSobregiro())) {
				this.setSaldoCuentaCorriente(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCuentaCorrientePesos(),
						resumenDetalleCuenta));
				this.setSaldoCuentaCorrienteValue(resumenDetalleCuenta.getSaldoCuentaCorrientePesos());
			}
		} else {
			this.setSaldoCajaAhorro(GUION);
			this.setSaldoCajaAhorroValue(null);
			if (SOBREGIRO.equals(resumenDetalleCuenta.getIndicadorSobregiro())) {
				this.setSaldoCuentaCorriente(GUION);
				this.setSaldoCuentaCorrienteValue(null);
			}
		}
	}

}
