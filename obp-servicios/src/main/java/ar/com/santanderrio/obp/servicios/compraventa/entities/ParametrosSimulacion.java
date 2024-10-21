/**
 * 
 */
package ar.com.santanderrio.obp.servicios.compraventa.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.clientes.entities.Cliente;
import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;

/**
 * The Class ParametrosSimulacion.
 *
 * @author sabrina.cis
 */
public class ParametrosSimulacion {

	/** The cliente. */
	private Cliente cliente;

	/** The nup tipo. */
	private String nupTipo;

	/** The nup num doc. */
	private String nupNumDoc;

	/** The cuenta destino. */
	private Cuenta cuentaDestino;

	/** The numero cuenta destino. */
	private String numeroCuentaDestino;

	/** The cuenta origen. */
	private Cuenta cuentaOrigen;

	/** The numero cuenta origen. */
	private String numeroCuentaOrigen;

	/** The cotizacion. */
	private String cotizacion;

	/** The importe. */
	private String importe;

	/** The is dolar. */
	private Boolean isDolar;

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
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Gets the nup tipo.
	 *
	 * @return the nupTipo
	 */
	public String getNupTipo() {
		return nupTipo;
	}

	/**
	 * Sets the nup tipo.
	 *
	 * @param nupTipo
	 *            the nupTipo to set
	 */
	public void setNupTipo(String nupTipo) {
		this.nupTipo = nupTipo;
	}

	/**
	 * Gets the nup num doc.
	 *
	 * @return the nupNumDoc
	 */
	public String getNupNumDoc() {
		return nupNumDoc;
	}

	/**
	 * Sets the nup num doc.
	 *
	 * @param nupNumDoc
	 *            the nupNumDoc to set
	 */
	public void setNupNumDoc(String nupNumDoc) {
		this.nupNumDoc = nupNumDoc;
	}

	/**
	 * Gets the cuenta destino.
	 *
	 * @return the cuentaDestino
	 */
	public Cuenta getCuentaDestino() {
		return cuentaDestino;
	}

	/**
	 * Sets the cuenta destino.
	 *
	 * @param cuentaDestino
	 *            the cuentaDestino to set
	 */
	public void setCuentaDestino(Cuenta cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}

	/**
	 * Gets the cuenta origen.
	 *
	 * @return the cuentaOrigen
	 */
	public Cuenta getCuentaOrigen() {
		return cuentaOrigen;
	}

	/**
	 * Sets the cuenta origen.
	 *
	 * @param cuentaOrigen
	 *            the cuentaOrigen to set
	 */
	public void setCuentaOrigen(Cuenta cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}

	/**
	 * Gets the cotizacion.
	 *
	 * @return the cotizacion
	 */
	public String getCotizacion() {
		return cotizacion;
	}

	/**
	 * Sets the cotizacion.
	 *
	 * @param cotizacion
	 *            the cotizacio to set
	 */
	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	/**
	 * Gets the importe.
	 *
	 * @return the importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Sets the importe.
	 *
	 * @param importe
	 *            the importe to set
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Gets the checks if is dolar.
	 *
	 * @return the isDolar
	 */
	public Boolean getIsDolar() {
		return isDolar;
	}

	/**
	 * Sets the checks if is dolar.
	 *
	 * @param isDolar
	 *            the isDolar to set
	 */
	public void setIsDolar(Boolean isDolar) {
		this.isDolar = isDolar;
	}

	/**
	 * Gets the numero cuenta destino.
	 *
	 * @return the numeroCuentaDestino
	 */
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}

	/**
	 * Sets the numero cuenta destino.
	 *
	 * @param numeroCuentaDestino
	 *            the numeroCuentaDestino to set
	 */
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}

	/**
	 * Gets the numero cuenta origen.
	 *
	 * @return the numeroCuentaOrigen
	 */
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	/**
	 * Sets the numero cuenta origen.
	 *
	 * @param numeroCuentaOrigen
	 *            the numeroCuentaOrigen to set
	 */
	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cliente);
		hcb.append(numeroCuentaDestino);
		hcb.append(numeroCuentaOrigen);
		hcb.append(nupTipo);
		hcb.append(nupNumDoc);
		hcb.append(cuentaDestino);
		hcb.append(cuentaOrigen);
		hcb.append(cotizacion);
		hcb.append(importe);
		hcb.append(isDolar);
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
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ParametrosSimulacion other = (ParametrosSimulacion) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cliente, other.getCliente());
		eb.append(numeroCuentaDestino, other.getNumeroCuentaDestino());
		eb.append(numeroCuentaOrigen, other.getNumeroCuentaOrigen());
		eb.append(nupTipo, other.getNupTipo());
		eb.append(nupNumDoc, other.getCuentaDestino());
		eb.append(cuentaDestino, other.getCuentaDestino());
		eb.append(cuentaOrigen, other.getCuentaOrigen());
		eb.append(cotizacion, other.getCotizacion());
		eb.append(importe, other.getImporte());
		eb.append(isDolar, other.getIsDolar());
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
		return new ToStringBuilder(this).append("cliente", cliente).append("nupTipo", nupTipo)
				.append("nupNumDoc", nupNumDoc).append("cuentaDestino", cuentaDestino)
				.append("numeroCuentaDestino", numeroCuentaDestino).append("cuentaOrigen", cuentaOrigen)
				.append("numeroCuentaOrigen", numeroCuentaOrigen).append("cotizacion", cotizacion)
				.append("importe", importe).append("isDolar", isDolar).toString();
	}

}
