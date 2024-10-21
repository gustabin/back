/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity;

import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;

/**
 * The Class ValidacionCuentaInCBUEntity.
 */
public class ValidacionCuentaInCBUEntity {

	/** The Constant VALOR_DEFECTO_A22. */
	private static final String VALOR_DEFECTO_A22 = "                      ";

	/** The Constant VALOR_DEFECTO_A12. */
	private static final String VALOR_DEFECTO_A12 = "            ";

	/** The Constant VALOR_DEFECTO_A04. */
	private static final String VALOR_DEFECTO_A04 = "    ";

	/** The cliente. */
	private Cliente cliente;

	/** The nro tarjeta. */
	// A18
	@Pattern(regexp = "^[0-9]{16}[ ]{2}$")
	private String nroTarjeta;

	/** The direccion ip. */
	// N12
	@Pattern(regexp = "^[0-9]{12}$")
	private String direccionIp;

	/** cbu.A22 */
	@Pattern(regexp = "^[a-zA-Z0-9]{22}|[ ]{22}$")
	private String cbu = VALOR_DEFECTO_A22;

	/** tipoCuentaCredito. A02 */
	@Pattern(regexp = "^00|01|02|03|04|09|10|  $")
	private String tipoCuentaCredito = TipoCuentaCreditoEnum.TIPO_CUENTA_CREDITO_VOID.getCampo();

	/** sucursalCuentaCredito. A04 */
	@Pattern(regexp = "^[a-zA-Z0-9]{3}|   $")
	private String sucursalCuentaCredito = VALOR_DEFECTO_A04;

	/** numeroCuentaCredito.A12 */
	@Pattern(regexp = "^[a-zA-Z0-9]{7}|[ ]{7}$")
	private String numeroCuentaCredito = VALOR_DEFECTO_A12;

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nro tarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the new nro tarjeta
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the direccion ip.
	 *
	 * @return the direccion ip
	 */
	public String getDireccionIp() {
		return direccionIp;
	}

	/**
	 * Sets the direccion ip.
	 *
	 * @param direccionIp
	 *            the new direccion ip
	 */
	public void setDireccionIp(String direccionIp) {
		this.direccionIp = direccionIp;
	}

	/**
	 * Gets the cliente.
	 *
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Sets the cliente.
	 *
	 * @param cliente
	 *            the new cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the cbu.
	 *
	 * @return the cbu
	 */
	public String getCbu() {
		return cbu;
	}

	/**
	 * Sets the cbu.
	 *
	 * @param cbu
	 *            the new cbu
	 */
	public void setCbu(String cbu) {
		this.cbu = cbu;
	}

	/**
	 * Gets the tipo cuenta credito.
	 *
	 * @return the tipo cuenta credito
	 */
	public String getTipoCuentaCredito() {
		return tipoCuentaCredito;
	}

	/**
	 * Sets the tipo cuenta credito.
	 *
	 * @param tipoCuentaCredito
	 *            the new tipo cuenta credito
	 */
	public void setTipoCuentaCredito(String tipoCuentaCredito) {
		this.tipoCuentaCredito = tipoCuentaCredito;
	}

	/**
	 * Gets the sucursal cuenta credito.
	 *
	 * @return the sucursal cuenta credito
	 */
	public String getSucursalCuentaCredito() {
		return sucursalCuentaCredito;
	}

	/**
	 * Sets the sucursal cuenta credito.
	 *
	 * @param sucursalCuentaCredito
	 *            the new sucursal cuenta credito
	 */
	public void setSucursalCuentaCredito(String sucursalCuentaCredito) {
		this.sucursalCuentaCredito = sucursalCuentaCredito;
	}

	/**
	 * Gets the numero cuenta credito.
	 *
	 * @return the numero cuenta credito
	 */
	public String getNumeroCuentaCredito() {
		return numeroCuentaCredito;
	}

	/**
	 * Sets the numero cuenta credito.
	 *
	 * @param numeroCuentaCredito
	 *            the new numero cuenta credito
	 */
	public void setNumeroCuentaCredito(String numeroCuentaCredito) {
		this.numeroCuentaCredito = numeroCuentaCredito;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.
	 * ConsultaAgendaDestinatarioInEntity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}

		ValidacionCuentaInCBUEntity other = (ValidacionCuentaInCBUEntity) obj;
		EqualsBuilder eb = new EqualsBuilder();
		return this.equals(obj)
				&& eb.append(nroTarjeta, other.getNroTarjeta()).append(direccionIp, other.getDireccionIp()).isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.
	 * ConsultaAgendaDestinatarioInEntity#hashCode()
	 */
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getCliente()).append(getTipoCuentaCredito())
				.append(getSucursalCuentaCredito()).append(getNumeroCuentaCredito()).append(getCbu()).append(nroTarjeta)
				.append(direccionIp).toHashCode();
	}

}
