/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;

/**
 * The Class HashDebitoAutomaticoTarjetaInDTO.
 *
 * @author florencia.n.martinez
 */
public class HashDebitoAutomaticoTarjetaInDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nro tarjeta. */
	private String nroTarjeta;

	/** The nro cuenta. */
	private String nroCuentaCliente;

	/** The empresa servicio. */
	private String empresaServicio;

	/**
	 * Instantiates a new hash debito automatico tarjeta in DTO.
	 */
	public HashDebitoAutomaticoTarjetaInDTO() {
		super();
	}

	/**
	 * Instantiates a new hash debito automatico tarjeta in DTO.
	 *
	 * @param inView
	 *            the in view
	 */
	public HashDebitoAutomaticoTarjetaInDTO(HashDebitoAutomaticoTarjetaView inView) {
		this.nroTarjeta = inView.getNumeroTarjetaEnmascarado();
		this.nroCuentaCliente = inView.getNroIdentificacion();
		this.empresaServicio = inView.getNombreFantasia();
	}

	/**
	 * Gets the nro tarjeta.
	 *
	 * @return the nroTarjeta
	 */
	public String getNroTarjeta() {
		return nroTarjeta;
	}

	/**
	 * Sets the nro tarjeta.
	 *
	 * @param nroTarjeta
	 *            the nroTarjeta to set
	 */
	public void setNroTarjeta(String nroTarjeta) {
		this.nroTarjeta = nroTarjeta;
	}

	/**
	 * Gets the nro cuenta cliente.
	 *
	 * @return the nroCuentaCliente
	 */
	public String getNroCuentaCliente() {
		return nroCuentaCliente;
	}

	/**
	 * Sets the nro cuenta cliente.
	 *
	 * @param nroCuentaCliente
	 *            the nroCuentaCliente to set
	 */
	public void setNroCuentaCliente(String nroCuentaCliente) {
		this.nroCuentaCliente = nroCuentaCliente;
	}

	/**
	 * Gets the empresa servicio.
	 *
	 * @return the empresaServicio
	 */
	public String getEmpresaServicio() {
		return empresaServicio;
	}

	/**
	 * Sets the empresa servicio.
	 *
	 * @param empresaServicio
	 *            the empresaServicio to set
	 */
	public void setEmpresaServicio(String empresaServicio) {
		this.empresaServicio = empresaServicio;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(empresaServicio);
		hcb.append(nroCuentaCliente);
		hcb.append(nroTarjeta);
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
		HashDebitoAutomaticoTarjetaInDTO other = (HashDebitoAutomaticoTarjetaInDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(empresaServicio, other.getEmpresaServicio());
		eb.append(nroCuentaCliente, other.getNroCuentaCliente());
		eb.append(nroTarjeta, other.getNroTarjeta());
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
		return new ToStringBuilder(this).append("nroTarjeta", nroTarjeta).append("nroCuentaCliente", nroCuentaCliente)
				.append("empresaServicio", empresaServicio).toString();
	}
}
