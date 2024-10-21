/*
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

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class TarjetaOfertaComercial.
 */
@JsonTypeInfo(include = As.WRAPPER_OBJECT, use = Id.NAME)
@JsonTypeName("tarjeta")
@JsonSerialize(include = Inclusion.NON_NULL)
public class TarjetaOfertaComercial {

	/** The tipo cuenta tc. */
	@JsonProperty("tipo_cuenta_tc")
	private String tipoCuentaTc;

	/** The suc cuenta tc. */
	@JsonProperty("suc_cuenta_tc")
	private String sucCuentaTc;

	/** The nro tc. */
	@JsonProperty("nro_tc")
	private String nroTc;

	/** The cod titularidad tc. */
	@JsonProperty("cod_titularidad_tc")
	private String codTitularidadTc;

	/**
	 * Instantiates a new tarjeta oferta comercial.
	 */
	public TarjetaOfertaComercial() {
		super();
	}

	/**
	 * Instantiates a new tarjeta oferta comercial.
	 *
	 * @param cuenta
	 *            the cuenta
	 */
	public TarjetaOfertaComercial(Cuenta cuenta) {
		this.tipoCuentaTc = cuenta.getTipoCuenta();
		this.sucCuentaTc = cuenta.getNroSucursal();
		this.nroTc = cuenta.getNroTarjetaCredito();
		this.codTitularidadTc = StringUtils.substring(cuenta.getCodigoTitularidad(), 0, 1);
	}

	/**
	 * Gets the tipo cuenta tc.
	 *
	 * @return the tipo cuenta tc
	 */
	public String getTipoCuentaTc() {
		return tipoCuentaTc;
	}

	/**
	 * Sets the tipo cuenta tc.
	 *
	 * @param tipoCuentaTc
	 *            the new tipo cuenta tc
	 */
	public void setTipoCuentaTc(String tipoCuentaTc) {
		this.tipoCuentaTc = tipoCuentaTc;
	}

	/**
	 * Gets the suc cuenta tc.
	 *
	 * @return the suc cuenta tc
	 */
	public String getSucCuentaTc() {
		return sucCuentaTc;
	}

	/**
	 * Sets the suc cuenta tc.
	 *
	 * @param sucCuentaTc
	 *            the new suc cuenta tc
	 */
	public void setSucCuentaTc(String sucCuentaTc) {
		this.sucCuentaTc = sucCuentaTc;
	}

	/**
	 * Gets the nro tc.
	 *
	 * @return the nro tc
	 */
	public String getNroTc() {
		return nroTc;
	}

	/**
	 * Sets the nro tc.
	 *
	 * @param nroTc
	 *            the new nro tc
	 */
	public void setNroTc(String nroTc) {
		this.nroTc = nroTc;
	}

	/**
	 * Gets the cod titularidad tc.
	 *
	 * @return the cod titularidad tc
	 */
	public String getCodTitularidadTc() {
		return codTitularidadTc;
	}

	/**
	 * Sets the cod titularidad tc.
	 *
	 * @param codTitularidadTc
	 *            the new cod titularidad tc
	 */
	public void setCodTitularidadTc(String codTitularidadTc) {
		this.codTitularidadTc = codTitularidadTc;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codTitularidadTc);
		hcb.append(nroTc);
		hcb.append(sucCuentaTc);
		hcb.append(tipoCuentaTc);
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
		TarjetaOfertaComercial other = (TarjetaOfertaComercial) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(codTitularidadTc, other.getCodTitularidadTc());
		eb.append(nroTc, other.getNroTc());
		eb.append(sucCuentaTc, other.getSucCuentaTc());
		eb.append(tipoCuentaTc, other.getTipoCuentaTc());
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
		return new ToStringBuilder(this).append("tipoCuentaTc", tipoCuentaTc).append("sucCuentaTc", sucCuentaTc)
				.append("nroTc", nroTc).append("codTitularidadTc", codTitularidadTc).toString();
	}

}