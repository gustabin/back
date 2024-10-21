/*
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.clientes.entities.DatosCliente;
import ar.com.santanderrio.obp.servicios.comun.ISBANStringUtils;

/**
 * The Class ConfiguracionAltaDestinatarioDTO.
 */
public class ConfiguracionAltaDestinatarioDTO {
	/** The Nombre Y apellido. */
	private String nombreYApellido;

	/** The cuit O cuil. */
	private String cuitOCuil;

	/** The numero cuil. */
	private String numeroCuil;

	/** The nup. */
	private String nup;

	/**
	 * Instantiates a new configuracion alta destinatario DTO.
	 */
	public ConfiguracionAltaDestinatarioDTO() {
		super();
	}

	/**
	 * Instantiates a new configuracion alta destinatario DTO.
	 *
	 * @param datosCliente
	 *            the datos cliente
	 */
	public ConfiguracionAltaDestinatarioDTO(DatosCliente datosCliente) {
		this.nombreYApellido = datosCliente.getNombre().trim();
		this.cuitOCuil = datosCliente.getTipoCUILCUIT();
		this.numeroCuil = ISBANStringUtils.agregarGuionesANumeroCuitCuil(datosCliente.getNumeroCUILCUIT());
		this.nup = datosCliente.getNup();
	}

	/**
	 * Gets the nombre Y apellido.
	 *
	 * @return the nombre Y apellido
	 */
	public String getNombreYApellido() {
		return nombreYApellido;
	}

	/**
	 * Sets the nombre Y apellido.
	 *
	 * @param nombreYApellido
	 *            the new nombre Y apellido
	 */
	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}

	/**
	 * Gets the cuit O cuil.
	 *
	 * @return the cuit O cuil
	 */
	public String getCuitOCuil() {
		return cuitOCuil;
	}

	/**
	 * Sets the cuit O cuil.
	 *
	 * @param cuitOCuil
	 *            the new cuit O cuil
	 */
	public void setCuitOCuil(String cuitOCuil) {
		this.cuitOCuil = cuitOCuil;
	}

	/**
	 * Gets the numero cuil.
	 *
	 * @return the numero cuil
	 */
	public String getNumeroCuil() {
		return numeroCuil;
	}

	/**
	 * Sets the numero cuil.
	 *
	 * @param numeroCuil
	 *            the new numero cuil
	 */
	public void setNumeroCuil(String numeroCuil) {
		this.numeroCuil = numeroCuil;
	}

	/**
	 * Gets the nup.
	 *
	 * @return the nup
	 */
	public String getNup() {
		return nup;
	}

	/**
	 * Sets the nup.
	 *
	 * @param nup
	 *            the new nup
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Equaals.
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
		ConfiguracionAltaDestinatarioDTO other = (ConfiguracionAltaDestinatarioDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(this.nombreYApellido, other.getNombreYApellido());
		eb.append(this.cuitOCuil, other.getCuitOCuil());
		eb.append(this.numeroCuil, other.getNumeroCuil());
		eb.append(this.nup, other.nup);
		return eb.isEquals();
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(nombreYApellido);
		hcb.append(cuitOCuil);
		hcb.append(numeroCuil);
		hcb.append(nup);
		return hcb.toHashCode();
	}

	/**
	 * ToString.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("nombreYApellido", nombreYApellido).append("cuitOCuil", cuitOCuil)
				.append("numeroCuil", numeroCuil).append("nup", nup).toString();
	}
}
