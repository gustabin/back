/**
 * 
 */
package ar.com.santanderrio.obp.base.clientes.entities;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class ResumenCliente.
 *
 * @author Jonatan_Bocian
 */
public class ResumenCliente extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant RACF_ID_INICIAL. */
	public static final String RACF_ID_INICIAL = "FREEUSER";

	/** The Constant RACF_PWD_INICIAL. */
	public static final String RACF_PWD_INICIAL = "FREEUSR0";

	/** The Constant RACF_TIPO_INICIAL. */
	public static final String RACF_TIPO_INICIAL = "04";

	/** The Constant RACF_TIPO_COMMMON. */
	public static final String RACF_TIPO_COMMMON = "03";

	/** The usuario racf. */
	private String usuarioRacf;

	/** The password racf. */
	@XmlTransient
	private String passwordRacf;

	/** The tipo racf. */
	private String tipoRacf;

	/** The dni. */
	private String dni;

	/** The nup. */
	private String nup;

	/** The fecha nacimiento. */
	private String fechaNacimiento;

	/** The tipo persona. */
	private String tipoPersona;

	/** The marca anph. */
	private String marcaANPH;

	/** The tipo clave. */
	private String tipoClave;

	/** The tipo documento. */
	private String tipoDocumento;

	/** The valor sinonimo. */
	private String valorSinonimo;

	/** The nro Comprobante. */
	private String nroComprobante;
	


	/**
	 * Instantiates a new resumen cliente.
	 */
	public ResumenCliente() {
		super();
	}

	/**
	 * Instantiates a new resumen cliente.
	 *
	 * @param cc
	 *            the cc
	 */
	public ResumenCliente(CredencialCliente cc) {
		this.dni = cc.getDniOri();
	}

	/**
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Setter para dni.
	 *
	 * @param dni
	 *            the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the valor sinonimo.
	 *
	 * @return the valorSinonimo
	 */
	public String getValorSinonimo() {
		return valorSinonimo;
	}

	/**
	 * Setter para valor sinonimo.
	 *
	 * @param valorSinonimo
	 *            the valorSinonimo to set
	 */
	public void setValorSinonimo(String valorSinonimo) {
		this.valorSinonimo = valorSinonimo;
	}

	/**
	 * Gets the tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Setter para tipo documento.
	 *
	 * @param tipoDocumento
	 *            the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Gets the usuario racf.
	 *
	 * @return the usuarioRacf
	 */
	public String getUsuarioRacf() {
		return usuarioRacf;
	}

	/**
	 * Setter para usuario racf.
	 *
	 * @param usuarioRacf
	 *            the usuarioRacf to set
	 */
	public void setUsuarioRacf(String usuarioRacf) {
		this.usuarioRacf = usuarioRacf;
	}

	/**
	 * Gets the password racf.
	 *
	 * @return the passwordRacf
	 */
	public String getPasswordRacf() {
		return passwordRacf;
	}

	/**
	 * Setter para password racf.
	 *
	 * @param passwordRacf
	 *            the passwordRacf to set
	 */
	public void setPasswordRacf(String passwordRacf) {
		this.passwordRacf = passwordRacf;
	}

	/**
	 * Gets the tipo racf.
	 *
	 * @return the tipoRacf
	 */
	public String getTipoRacf() {
		return tipoRacf;
	}

	/**
	 * Setter para tipo racf.
	 *
	 * @param tipoRacf
	 *            the tipoRacf to set
	 */
	public void setTipoRacf(String tipoRacf) {
		this.tipoRacf = tipoRacf;
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
	 * Setter para nup.
	 *
	 * @param nup
	 *            the nup to set
	 */
	public void setNup(String nup) {
		this.nup = nup;
	}

	/**
	 * Gets the fecha nacimiento.
	 *
	 * @return the fechaNacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Setter para fecha nacimiento.
	 *
	 * @param fechaNacimiento
	 *            the fechaNacimiento to set
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Gets the tipo persona.
	 *
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * Setter para tipo persona.
	 *
	 * @param tipoPersona
	 *            the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * Gets the marca anph.
	 *
	 * @return the marcaANPH
	 */
	public String getMarcaANPH() {
		return marcaANPH;
	}

	/**
	 * Setter para marca anph.
	 *
	 * @param marcaANPH
	 *            the marcaANPH to set
	 */
	public void setMarcaANPH(String marcaANPH) {
		this.marcaANPH = marcaANPH;
	}

	/**
	 * Gets the tipo clave.
	 *
	 * @return the tipoClave
	 */
	public String getTipoClave() {
		return tipoClave;
	}

	/**
	 * Setter para tipo clave.
	 *
	 * @param tipoClave
	 *            the tipoClave to set
	 */
	public void setTipoClave(String tipoClave) {
		this.tipoClave = tipoClave;
	}

	/**
	 * Gets the nro comprobante.
	 *
	 * @return the nro comprobante
	 */
	public String getNroComprobante() {
		return nroComprobante;
	}

	/**
	 * Sets the nro comprobante.
	 *
	 * @param nroComprobante
	 *            the new nro comprobante
	 */
	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(dni);
		hcb.append(fechaNacimiento);
		hcb.append(marcaANPH);
		hcb.append(nup);
		hcb.append(passwordRacf);
		hcb.append(tipoClave);
		hcb.append(tipoDocumento);
		hcb.append(tipoPersona);
		hcb.append(tipoRacf);
		hcb.append(usuarioRacf);
		hcb.append(valorSinonimo);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
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
		ResumenCliente other = (ResumenCliente) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(dni, other.getDni());
		eb.append(fechaNacimiento, other.getFechaNacimiento());
		eb.append(marcaANPH, other.getMarcaANPH());
		eb.append(nup, other.getNup());
		eb.append(passwordRacf, other.getPasswordRacf());
		eb.append(tipoClave, other.getTipoClave());
		eb.append(tipoDocumento, other.getTipoDocumento());
		eb.append(tipoPersona, other.getTipoPersona());
		eb.append(tipoRacf, other.getTipoRacf());
		eb.append(usuarioRacf, other.getUsuarioRacf());
		eb.append(valorSinonimo, other.getValorSinonimo());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("tipoRacf=", tipoRacf).append("nup=", nup)
				.append("fechaNacimiento=", fechaNacimiento).append("tipoPersona=", tipoPersona)
				.append("marcaANPH=", marcaANPH).append("tipoClave=", tipoClave).append("tipoDocumento=", tipoDocumento)
				.append("valorSinonimo=", valorSinonimo).toString();
	}
}
