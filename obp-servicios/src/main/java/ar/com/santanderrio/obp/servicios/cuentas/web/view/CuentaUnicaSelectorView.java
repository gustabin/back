/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import java.math.BigDecimal;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;

/**
 * The Class CuentaUnicaSelectorView.
 */
public class CuentaUnicaSelectorView extends CuentaSelectorDetalleView {

	/** The Constant DESCRIPCION_CUENTA_UNICA_NORMATIVO. */
	private static final String DESCRIPCION_CUENTA_UNICA_NORMATIVO = "Cuentas";

    /**
	 * Instantiates a new cuenta unica selector view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		this.setDescripcionTipoCuenta(DESCRIPCION_CUENTA_UNICA_NORMATIVO); 
		this.setSaldoDolares(
				formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCajaAhorroDolares(), resumenDetalleCuenta));
		this.setSaldoDolaresValue(resumenDetalleCuenta.getSaldoCajaAhorroDolares());
		this.setIsCuentaUnica(Boolean.TRUE);
		if (resumenDetalleCuenta.isConvenioPAS()) {
			this.setSaldoCajaAhorro(
					formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCuentaSueldoPesos(), resumenDetalleCuenta));
			this.setSaldoCajaAhorroValue(resumenDetalleCuenta.getSaldoCuentaSueldoPesos());
		} else {
			this.setSaldoCajaAhorro(
					formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoCajaAhorroPesos(), resumenDetalleCuenta));
			this.setSaldoCajaAhorroValue(resumenDetalleCuenta.getSaldoCajaAhorroPesos());
		}
		this.setSaldoPesos(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoPesos(), resumenDetalleCuenta));
		this.setSaldoPesosValue(resumenDetalleCuenta.getSaldoPesos());
		setDescubierto(resumenDetalleCuenta);
		if (!resumenDetalleCuenta.isCuentaCerrada()
				&& !SOBREGIRO.equals(resumenDetalleCuenta.getIndicadorSobregiro())) {
			this.setSaldoPesos(null);
			this.setSaldoPesosValue(null);
		}
		if (resumenDetalleCuenta.isCuentaCerrada()) {
			this.setSaldoDolares(formatearSaldoSinAbs(BigDecimal.ZERO, resumenDetalleCuenta));
			this.setSaldoDescubierto(null);
			this.setSaldoCuentaCorriente(null);
			this.setSaldoCajaAhorro(null);
			this.setSaldoPesos(formatearSaldoSinAbs(BigDecimal.ZERO, resumenDetalleCuenta));
		}
		if (!resumenDetalleCuenta.isCuentaCerrada() && resumenDetalleCuenta.getTraspasoAutomaticoActivo() == null
				&& resumenDetalleCuenta.getSolicitudPendienteTraspasoAutomatico() == null) {
			this.setSaldoCajaAhorro(GUION);
			this.setSaldoPesos(null);
			this.setSaldoDolares(GUION);
			this.setSaldoDescubierto(null);
			this.setSaldoCuentaCorriente(null);
		}
		if (!resumenDetalleCuenta.isCuentaCerrada() && resumenDetalleCuenta.getModoNocturno()) {
			this.setSaldoCajaAhorro(GUION);
			if (SOBREGIRO.equals(resumenDetalleCuenta.getIndicadorSobregiro())) {
				this.setSaldoCuentaCorriente(GUION);
				this.setSaldoCuentaCorrienteValue(null);
			}
		}
	}

}
