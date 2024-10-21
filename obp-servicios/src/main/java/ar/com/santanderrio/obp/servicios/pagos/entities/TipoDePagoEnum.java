/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

/**
 * The Enum TipoDePagoEnum.
 */
public enum TipoDePagoEnum {

	/** The pagomiscuentaspuntual. */
	PAGOMISCUENTASPUNTUAL("Pago puntual de Pagomiscuentas", false),
	/** The pagomiscuentasdebito. */
	PAGOMISCUENTASDEBITO("Pago automático de Pagomiscuentas", false),
	/** The pagomiscuentaserrorconsultadebitoautomatico. */
	PAGOMISCUENTASERRORCONSULTADEBITOAUTOMATICO("* No se pudo determinar el medio de pago *", false),

	/** The tarjetadebitoautomaticopagominimo. */
	TARJETADEBITOAUTOMATICOPAGOMINIMO("Débito automático en cuenta - Pago mínimo", true),
	/** The tarjetadebitoautomaticopagototal. */
	TARJETADEBITOAUTOMATICOPAGOTOTAL("Débito automático en cuenta - Pago total", true),
	/** The tarjetapagopuntual. */
	TARJETAPAGOPUNTUAL("Pago puntual", true),
	/** The tarjetapagopuntual. */
	TARJETARECARGABLEPAGOPUNTUAL("Pago puntual", true),

	/** The prestamodebitoencuenta. */
	PRESTAMODEBITOENCUENTA("Débito automático en cuenta", true),
	/** The prestamospagopuntual. */
	PRESTAMOSPAGOPUNTUAL("Pago puntual", true),
	/** The prestamoserrorconsultadebitoautomatico. */
	PRESTAMOSERRORCONSULTADEBITOAUTOMATICO("* No se pudo determinar el medio de pago *", true),

	/** The pagoprogramado. */
	PAGOPROGRAMADO("Pago programado", true),
	/** The tarjetapagopuntual. */
	TARJETARECARGABLEPROGRAMADO("Pago programado", true),

	/** The servicioadheridoadebitoautomaticoencuenta. */
	SERVICIOADHERIDOADEBITOAUTOMATICOENCUENTA("Débito automático en cuenta", true);

	/** The nombre tipo de pago. */
	private String nombreTipoDePago;

	/** The permite mes anterior. */
	private boolean permiteMesAnterior;

	/**
	 * Instantiates a new tipo de pago enum.
	 *
	 * @param nombreTipoDePago
	 *            the nombre tipo de pago
	 * @param permiteMesAnterior
	 *            the permite mes anterior
	 */
	private TipoDePagoEnum(String nombreTipoDePago, boolean permiteMesAnterior) {
		this.nombreTipoDePago = nombreTipoDePago;
		this.permiteMesAnterior = permiteMesAnterior;
	}

	/**
	 * Gets the nombre tipo de pago.
	 *
	 * @return the nombreTipoDePago
	 */
	public String getNombreTipoDePago() {
		return nombreTipoDePago;
	}

	/**
	 * Checks if is permite mes anterior.
	 *
	 * @return the permiteMesAnterior
	 */
	public boolean isPermiteMesAnterior() {
		return permiteMesAnterior;
	}

	/**
	 * Devuelve el enum correspondiente.
	 *
	 * @param tipoDePago
	 *            the tipo de pago
	 * @return the by string
	 */
	public static TipoDePagoEnum getByString(String tipoDePago) {
		for (TipoDePagoEnum tipoDePagoEnum : values()) {
			if (tipoDePagoEnum.toString().equals(tipoDePago)) {
				return tipoDePagoEnum;
			}
		}
		return null;
	}

}
