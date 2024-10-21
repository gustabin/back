package ar.com.santanderrio.obp.base.mensaje.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class Mensaje.
 */
public class Mensaje {

	/** The codigo. */
	private String codigo;

	/** The mensaje. */
	private String mensaje;

	/** The tag. */
	private String tag;

	/** Indica si el mensaje es generico. */
	private Boolean generico = Boolean.FALSE;

	/**
	 * Instantiates a new mensaje.
	 */
	public Mensaje() {
		super();
	}

	/**
	 * Instantiates a new mensaje.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public Mensaje(Mensaje mensaje) {
		super();
		this.setCodigo(mensaje.codigo);
		this.setMensaje(mensaje.mensaje);
		this.setGenerico(mensaje.generico);
		this.setTag(mensaje.tag);
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Setter para codigo.
	 *
	 * @param codigo
	 *            el nuevo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Gets the mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Setter para mensaje.
	 *
	 * @param mensaje
	 *            el nuevo mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the tag.
	 *
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Setter para tag.
	 *
	 * @param tag
	 *            el nuevo tag
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}

	/**
	 * Gets the generico.
	 *
	 * @return the generico
	 */
	public Boolean getGenerico() {
		return generico;
	}

	/**
	 * Sets the generico.
	 *
	 * @param generico
	 *            the generico to set
	 */
	public void setGenerico(Boolean generico) {
		this.generico = generico;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(codigo);
		hcb.append(mensaje);
		hcb.append(tag);
		hcb.append(generico);
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

		Mensaje other = (Mensaje) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(this.codigo, other.getCodigo());
		eb.append(this.tag, other.getTag());
		eb.append(this.mensaje, other.getMensaje());
		return eb.isEquals();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Mensaje [" + (codigo != null ? "codigo=" + codigo + ", " : "")
				+ (mensaje != null ? "mensaje=" + mensaje + ", " : "") + (tag != null ? "tag=" + tag : "") + "]";
	}

}
