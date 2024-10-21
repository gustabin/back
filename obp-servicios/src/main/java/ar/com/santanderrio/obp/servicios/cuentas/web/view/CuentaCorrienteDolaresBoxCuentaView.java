/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CuentaCorrienteDolaresBoxCuentaView.
 */
public class CuentaCorrienteDolaresBoxCuentaView extends BoxCuentaView {

	private static final String CODIGO_PRODUCTO_REPATRIACION_2021 = "03";
	private static final String CODIGO_SUBPRODUCTO_REPATRIACION_2021 = "0007";

	/**
	 * Instantiates a new cuenta corriente dolares box cuenta view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 */
	@Override
	public void build(ResumenDetalleCuenta resumenDetalleCuenta) {
		super.build(resumenDetalleCuenta);
		this.setIsCuentaUnica(Boolean.FALSE);
		this.setMoneda(MONEDA_DOLAR);
		this.setIsCuentaRepatriacion(isCuentaRepatriacion(resumenDetalleCuenta));
		if (!resumenDetalleCuenta.getModoNocturno()) {
			this.setSaldo(formatearSaldoSinAbs(resumenDetalleCuenta.getSaldoDolares(), resumenDetalleCuenta));
			this.setSaldoValue(resumenDetalleCuenta.getSaldoDolares());
		} else {
			this.setSaldo(GUION);
			this.setSaldoValue(null);
		}
	}

	private boolean isCuentaRepatriacion(ResumenDetalleCuenta cuenta) {
		return TipoCuenta.CAJA_AHORRO_DOLARES.equals(TipoCuenta.fromCodigo(cuenta.getTipoCuenta())) &&
			CODIGO_PRODUCTO_REPATRIACION_2021.equals(cuenta.getProductoAlt()) &&
			CODIGO_SUBPRODUCTO_REPATRIACION_2021.equals(cuenta.getSubProductoAlt());
	}

}
