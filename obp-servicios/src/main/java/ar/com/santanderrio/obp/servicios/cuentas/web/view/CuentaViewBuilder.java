/*
 * 
 */
package ar.com.santanderrio.obp.servicios.cuentas.web.view;

import ar.com.santanderrio.obp.servicios.cuentas.entities.ResumenDetalleCuenta;
import ar.com.santanderrio.obp.servicios.cuentas.entities.TipoCuenta;

/**
 * The Class CuentaViewBuilder.
 */
public class CuentaViewBuilder {

	/**
	 * Construir cuenta selector view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @return the cuenta selector view
	 */
	public static CuentaSelectorView construirCuentaSelectorView(ResumenDetalleCuenta resumenDetalleCuenta) {
		CuentaSelectorView cuentaSelectorView = null;
		switch (TipoCuenta.fromCodigo(resumenDetalleCuenta.getTipoCuenta())) {
		case CUENTA_UNICA:
			cuentaSelectorView = new CuentaUnicaSelectorView();
			break;
		case CUENTA_CORRIENTE_PESOS:
			cuentaSelectorView = new CuentaCorrientePesosSelectorView();
			break;
		case CAJA_AHORRO_PESOS:
			cuentaSelectorView = new CajaAhorroPesosSelectorView();
			break;
		case CAJA_AHORRO_DOLARES:
		case CUENTA_CORRIENTE_DOLARES:
			cuentaSelectorView = new CajaAhorroDolaresSelectorView();
			break;
		default:
			break;
		}
		cuentaSelectorView.build(resumenDetalleCuenta);
		return cuentaSelectorView;
	}

	/**
	 * Construir box cuenta view.
	 *
	 * @param resumenDetalleCuenta
	 *            the resumen detalle cuenta
	 * @param tipoCajitaCuentaEnum
	 *            the tipo cajita cuenta enum
	 * @return the box cuenta view
	 */
	public static BoxCuentaView construirBoxCuentaView(ResumenDetalleCuenta resumenDetalleCuenta,
			TipoCuenta tipoCajitaCuentaEnum) {
		BoxCuentaView boxCuentaView = null;
		switch (tipoCajitaCuentaEnum) {
		case CUENTA_UNICA_DOLARES:
			boxCuentaView = new CuentaUnicaDolaresBoxCuentaView();

			break;
		case CUENTA_UNICA_PESOS:
			boxCuentaView = new CuentaUnicaPesosBoxCuentaView();
			break;
		case CAJA_AHORRO_PESOS:
		case CUENTA_CORRIENTE_PESOS:
			boxCuentaView = new CuentaCorrientePesosBoxCuentaView();
			break;
		case CAJA_AHORRO_DOLARES:
		case CUENTA_CORRIENTE_DOLARES:
			boxCuentaView = new CuentaCorrienteDolaresBoxCuentaView();
			break;
		default:
			break;
		}
		boxCuentaView.build(resumenDetalleCuenta);
		return boxCuentaView;
	}

}
