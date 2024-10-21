/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class CuentaPagoMisCuentas.
 *
 * @author B025331
 */
public class CuentaPagoMisCuentas extends Cuenta {

	/** The tipo cuenta canonico. */
	private String tipoCuentaCanonico;

	/** The numero cuenta canonico. */
	private String numeroCuentaCanonico;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new cuenta pago mis cuentas.
	 *
	 * @param tipoCuentaCanonico
	 *            the tipo cuenta canonico
	 * @param numeroCuentaCanonico
	 *            the numero cuenta canonico
	 * @param nroCuentaProducto
	 *            the nro cuenta producto
	 */
	public CuentaPagoMisCuentas(String tipoCuentaCanonico, String numeroCuentaCanonico, String nroCuentaProducto) {
		this.tipoCuentaCanonico = tipoCuentaCanonico;
		this.numeroCuentaCanonico = numeroCuentaCanonico;
		this.setNroCuentaProducto(nroCuentaProducto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o.getClass().equals(this.getClass()))) {
			return false;
		}
		CuentaPagoMisCuentas cpmc = CuentaPagoMisCuentas.class.cast(o);
		return tipoCuentaCanonico.equals(cpmc.tipoCuentaCanonico)
				&& numeroCuentaCanonico.equals(cpmc.numeroCuentaCanonico);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (tipoCuentaCanonico + numeroCuentaCanonico).hashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " = {tipoCuentaCanonico: " + tipoCuentaCanonico + ", numeroCuentaCanonico: "
				+ numeroCuentaCanonico + "}";
	}

}
