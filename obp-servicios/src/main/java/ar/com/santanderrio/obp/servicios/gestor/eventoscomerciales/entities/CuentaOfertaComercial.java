/**
 * 
 */
package ar.com.santanderrio.obp.servicios.gestor.eventoscomerciales.entities;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class CuentaTarjeta.
 *
 * @author florencia.n.martinez
 */
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonTypeName("cuenta")
@JsonSerialize(include = Inclusion.NON_NULL)
public class CuentaOfertaComercial {

	/** The tipo cuenta. */
	@JsonProperty("tipo_cuenta")
	private String tipoCuenta;

	/** The suc cuenta. */
	@JsonProperty("sucursal_cuenta")
	private String sucCuenta;

	/** The nro cuenta. */
	@JsonProperty("nro_cuenta")
	private String nroCuenta;

	/** The cod titularidad. */
	@JsonProperty("cod_titularidad_cuenta")
	private String codTitularidad;

	/** The saldo ars. */
	@JsonProperty("saldo_ars")
	private String saldoArs;

	/** The saldo uds. */
	@JsonProperty("saldo_usd")
	private String saldoUds;

	/**
	 * Instantiates a new cuenta tarjeta.
	 */
	public CuentaOfertaComercial() {
		super();
	}

	/**
	 * Instantiates a new cuenta tarjeta.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	public CuentaOfertaComercial(Cuenta cuenta) {
		this.tipoCuenta = StringUtils.leftPad(String.valueOf(cuenta.getTipoCuentaEnum().getCodigo()), 2, "0");
		this.sucCuenta = cuenta.getNroSucursal();
		this.nroCuenta = cuenta.getNroCuentaProducto();
		this.codTitularidad = cuenta.getCodigoTitularidad();
		completarSaldos(cuenta);
	}

	/**
	 * Completar saldos.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	private void completarSaldos(Cuenta cuenta) {
		if (cuenta.esCuentaUnica()) {
			if (cuenta.esCuentaUnicaPesos()) {
				this.saldoArs = formatearSaldo(cuenta.getSaldoCUPesos());
			}
			if (cuenta.esCuentaUnicaDolares()) {
				this.saldoUds = formatearSaldo(cuenta.getSaldoCUDls());
			}
		} else {
			if (cuenta.esSaldoPesos()) {
				this.saldoArs = formatearSaldo(cuenta.getSaldoCuenta());
			}
			if (cuenta.esSaldoDolares()) {
				this.saldoUds = formatearSaldo(cuenta.getSaldoCuenta());
			}
		}

	}

	/**
	 * Formatear saldo.
	 *
	 * @param saldo
	 *            the saldo
	 * @return the string
	 */
	private static String formatearSaldo(String saldo) {
	    int l = saldo.length();
	    boolean tieneSigno = saldo.charAt(0) == '+';
        boolean isNegativo = saldo.charAt(0) == '-';
        String signo = isNegativo ? "-" : "+";

        if (tieneSigno || isNegativo) {
            saldo = saldo.substring(1, l);
        }
        return ISBANStringUtils.formatearNumericosParaIatxSeparadoPorPunto(saldo, 14, 2) + signo;
	}
	
	/**
	 * Gets the tipo cuenta.
	 *
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * Sets the tipo cuenta.
	 *
	 * @param tipoCuenta
	 *            the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * Gets the suc cuenta.
	 *
	 * @return the sucCuenta
	 */
	public String getSucCuenta() {
		return sucCuenta;
	}

	/**
	 * Sets the suc cuenta.
	 *
	 * @param sucCuenta
	 *            the sucCuenta to set
	 */
	public void setSucCuenta(String sucCuenta) {
		this.sucCuenta = sucCuenta;
	}

	/**
	 * Gets the nro cuenta.
	 *
	 * @return the nroCuenta
	 */
	public String getNroCuenta() {
		return nroCuenta;
	}

	/**
	 * Sets the nro cuenta.
	 *
	 * @param nroCuenta
	 *            the nroCuenta to set
	 */
	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	/**
	 * Gets the cod titularidad.
	 *
	 * @return the codTitularidad
	 */
	public String getCodTitularidad() {
		return codTitularidad;
	}

	/**
	 * Sets the cod titularidad.
	 *
	 * @param codTitularidad
	 *            the codTitularidad to set
	 */
	public void setCodTitularidad(String codTitularidad) {
		this.codTitularidad = codTitularidad;
	}

	/**
	 * Gets the saldo ars.
	 *
	 * @return the saldoArs
	 */
	public String getSaldoArs() {
		return saldoArs;
	}

	/**
	 * Sets the saldo ars.
	 *
	 * @param saldoArs
	 *            the saldoArs to set
	 */
	public void setSaldoArs(String saldoArs) {
		this.saldoArs = saldoArs;
	}

	/**
	 * Gets the saldo uds.
	 *
	 * @return the saldoUds
	 */
	public String getSaldoUds() {
		return saldoUds;
	}

	/**
	 * Sets the saldo uds.
	 *
	 * @param saldoUds
	 *            the saldoUds to set
	 */
	public void setSaldoUds(String saldoUds) {
		this.saldoUds = saldoUds;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codTitularidad);
		hcb.append(nroCuenta);
		hcb.append(sucCuenta);
		hcb.append(tipoCuenta);
		hcb.append(saldoArs);
		hcb.append(saldoUds);
		return hcb.toHashCode();
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaOfertaComercial other = (CuentaOfertaComercial) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codTitularidad, other.getCodTitularidad());
		eb.append(nroCuenta, other.getNroCuenta());
		eb.append(sucCuenta, other.getSucCuenta());
		eb.append(tipoCuenta, other.getTipoCuenta());
		eb.append(saldoArs, other.getSaldoArs());
		eb.append(saldoUds, other.getSaldoUds());
		return eb.isEquals();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("tipoCuenta", tipoCuenta).append("sucCuenta", sucCuenta)
				.append("nroCuenta", nroCuenta).append("codTitularidad", codTitularidad).append("saldoArs", saldoArs)
				.append("saldoUds", saldoUds).toString();
	}

}
