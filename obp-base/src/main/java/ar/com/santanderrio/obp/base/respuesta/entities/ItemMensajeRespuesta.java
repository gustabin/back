/**
 * 
 */
package ar.com.santanderrio.obp.base.respuesta.entities;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemMensajeRespuesta.
 *
 * @author Federico_Juliano
 */
public class ItemMensajeRespuesta implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The tipo error. */
	private String tipoError;

	/** The mensaje. */
	private String mensaje;

	/** The extra. */
	private String extra;

	/** The tag. */
	private String tag;

	/** The tipo error. */
	private String detalleTipoError;

	/**
	 * Instantiates a new item mensaje respuesta.
	 */
	public ItemMensajeRespuesta() {
		super();
	}

	/**
	 * Instantiates a new item mensaje respuesta.
	 *
	 * @param mensaje
	 *            the mensaje
	 */
	public ItemMensajeRespuesta(String mensaje) {
		super();
		this.mensaje = mensaje;
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
	 * Gets the extra.
	 *
	 * @return the extra
	 */
	public String getExtra() {
		return extra;
	}

	/**
	 * Setter para extra.
	 *
	 * @param extra
	 *            the extra to set
	 */
	public void setExtra(String extra) {
		this.extra = extra;
	}

	/**
	 * Gets the tipo error.
	 *
	 * @return the tipo error
	 */
	public String getTipoError() {
		return tipoError;
	}

	/**
	 * Setter para tipo error.
	 *
	 * @param tipoError
	 *            el nuevo tipo error
	 */
	public void setTipoError(String tipoError) {
		this.tipoError = tipoError;
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
	 * Gets the detalle tipo error.
	 *
	 * @return the detalle tipo error
	 */
	public String getDetalleTipoError() {
		return detalleTipoError;
	}

	/**
	 * Sets the detalle tipo error.
	 *
	 * @param detalleTipoError
	 *            the new detalle tipo error
	 */
	public void setDetalleTipoError(String detalleTipoError) {
		this.detalleTipoError = detalleTipoError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(extra);
		hcb.append(mensaje);
		hcb.append(tag);
		hcb.append(tipoError);
		hcb.append(detalleTipoError);
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

		ItemMensajeRespuesta other = (ItemMensajeRespuesta) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(extra, other.getExtra());
		eb.append(mensaje, other.getMensaje());
		eb.append(tag, other.getTag());
		eb.append(tipoError, other.getTipoError());
		eb.append(detalleTipoError, other.getDetalleTipoError());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append(tipoError).append(mensaje).append(extra).append(tag)
				.append(detalleTipoError).toString();
	}

}
