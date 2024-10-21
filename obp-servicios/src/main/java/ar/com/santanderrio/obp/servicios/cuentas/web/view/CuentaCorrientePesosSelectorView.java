/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CuentaCorrientePesosSelectorView.
 */
public class CuentaCorrientePesosSelectorView extends CuentaSelectorDetalleView {

	/**
	 * Instantiates a new cuenta corriente pesos selector view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		if (resumenDetalleCuenta.isCuentaCerrada()) {
			this.setSaldoPesos(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoPesos(), resumenDetalleCuenta));
			this.setSaldoPesosValue(resumenDetalleCuenta.getSaldoPesos());
		}
		this.setSaldoPesos(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoPesos(), resumenDetalleCuenta));
		this.setSaldoPesosValue(resumenDetalleCuenta.getSaldoPesos());
		setDescubierto(resumenDetalleCuenta);
		if (!resumenDetalleCuenta.isCuentaCerrada() && !SOBREGIRO.equals(resumenDetalleCuenta.getIndicadorSobregiro())
				&& resumenDetalleCuenta.getTraspasoAutomaticoActivo() != null) {
			this.setSaldoPesos(null);
			this.setSaldoPesosValue(null);
		}
		this.setMoneda(CuentaSelectorDetalleView.PESO);
	}

}
