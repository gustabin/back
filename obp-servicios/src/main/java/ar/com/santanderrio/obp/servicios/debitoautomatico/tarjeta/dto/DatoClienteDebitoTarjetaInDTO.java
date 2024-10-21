/**
 * 
 */
package ar.com.santanderrio.obp.servicios.debitoautomatico.tarjeta.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.cuentas.entities.Cuenta;
import ar.com.santanderrio.obp.servicios.pagoautomatico.view.HashDebitoAutomaticoTarjetaView;

/**
 * The Class DatoClienteDebitoTarjetaInDTO.
 *
 * @author florencia.n.martinez
 */
public class DatoClienteDebitoTarjetaInDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The nro cuenta visa. */
	private String nroCuentaVisa;

	/** The nro tarjeta. */
	private String nroTarjetaVisa;

	/** The empresa servicio. */
	private String empresaServicio;

	/** The nro cuenta. */
	private String identificador;

	/**
	 * Instantiates a new hash debito automatico tarjeta in DTO.
	 */
	public DatoClienteDebitoTarjetaInDTO() {
		super();
	}

	/**
	 * Instantiates a new hash debito automatico tarjeta in DTO.
	 *
	 * @param inView
	 *            the in view
	 * @param cuenta
	 *            the cuenta
	 */
	public DatoClienteDebitoTarjetaInDTO(HashDebitoAutomaticoTarjetaView inView, Cuenta cuenta) {
		this.nroCuentaVisa = cuenta.getNroCuentaProducto();
		this.nroTarjetaVisa = cuenta.getNroTarjetaCredito();
		this.empresaServicio = inView.getNombreFantasia();
		this.identificador = inView.getNroIdentificacion();
	}

	/**
	 * Gets the nro cuenta visa.
	 *
	 * @return the nroCuentaVisa
	 */
	public String getNroCuentaVisa() {
		return nroCuentaVisa;
	}

	/**
	 * Sets the nro cuenta visa.
	 *
	 * @param nroCuentaVisa
	 *            the nroCuentaVisa to set
	 */
	public void setNroCuentaVisa(String nroCuentaVisa) {
		this.nroCuentaVisa = nroCuentaVisa;
	}

	/**
	 * Gets the nro tarjeta visa.
	 *
	 * @return the nroTarjetaVisa
	 */
	public String getNroTarjetaVisa() {
		return nroTarjetaVisa;
	}

	/**
	 * Sets the nro tarjeta visa.
	 *
	 * @param nroTarjetaVisa
	 *            the nroTarjetaVisa to set
	 */
	public void setNroTarjetaVisa(String nroTarjetaVisa) {
		this.nroTarjetaVisa = nroTarjetaVisa;
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
	 * Gets the identificador.
	 *
	 * @return the identificador
	 */
	public String getIdentificador() {
		return identificador;
	}

	/**
	 * Sets the identificador.
	 *
	 * @param identificador
	 *            the identificador to set
	 */
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
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
		hcb.append(identificador);
		hcb.append(nroCuentaVisa);
		hcb.append(nroTarjetaVisa);
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
		DatoClienteDebitoTarjetaInDTO other = (DatoClienteDebitoTarjetaInDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(empresaServicio, other.getEmpresaServicio());
		eb.append(identificador, other.getIdentificador());
		eb.append(nroCuentaVisa, other.getNroCuentaVisa());
		eb.append(nroTarjetaVisa, other.getNroTarjetaVisa());
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
		return new ToStringBuilder(this).append("nroCuentaVisa", nroCuentaVisa).append("nroTarjetaVisa", nroTarjetaVisa)
				.append("empresaServicio", empresaServicio).append("identificador", identificador).toString();
	}

}
