/**
 * 
 */
package ar.com.santanderrio.obp.servicios.home.web.view;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The Class CajaHomePuntosSuperclubView.
 *
 * @author florencia.n.martinez
 */
@JsonSerialize(include = Inclusion.NON_NULL)
public class CajaHomePuntosSuperclubView extends Caja {

	/** The Constant SUPERCLUB. */
	private static final String SUPERCLUB = "Superclub";

	/** The titulo. */
	private String titulo;

	/** The encabezado. */
	private String encabezado;

	/** The cantidad. */
	private String cantidad;

	/** The descripcion. */
	private String descripcion;
	
	/** The texto link. */
	private String textoLink;

	/**
	 * Instantiates a new caja home puntos superclub view.
	 */
	public CajaHomePuntosSuperclubView() {
		super();
	}

	/**
	 * Instantiates a new caja home puntos superclub view.
	 *
	 * @param cantidad
	 *            the cantidad
	 * @param descripcion
	 *            the descripcion
	 */
	public CajaHomePuntosSuperclubView(String cantidad, String descripcion) {
	    this.titulo = SUPERCLUB;
		this.encabezado = SUPERCLUB;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo
	 *            the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the encabezado.
	 *
	 * @return the encabezado
	 */
	public String getEncabezado() {
		return encabezado;
	}

	/**
	 * Sets the encabezado.
	 *
	 * @param encabezado
	 *            the encabezado to set
	 */
	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	/**
	 * Gets the cantidad.
	 *
	 * @return the cantidad
	 */
	public String getCantidad() {
		return cantidad;
	}

	/**
	 * Sets the cantidad.
	 *
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(String cantidad) { this.cantidad = cantidad; }

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(cantidad);
		hcb.append(descripcion);
		hcb.append(encabezado);
		hcb.append(titulo);
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
		CajaHomePuntosSuperclubView other = (CajaHomePuntosSuperclubView) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(cantidad, other.getCantidad());
		eb.append(descripcion, other.getDescripcion());
		eb.append(encabezado, other.getEncabezado());
		eb.append(titulo, other.getTitulo());
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
		return new ToStringBuilder(this).append("titulo", titulo).append("encabezado", encabezado)
				.append("cantidad", cantidad).append("descripcion", descripcion).toString();
	}

    /**
	 * Gets the texto link.
	 *
	 * @return the texto link
	 */
    public String getTextoLink() {
        return textoLink;
    }

    /**
	 * Sets the texto link.
	 *
	 * @param textoLink
	 *            the new texto link
	 */
    public void setTextoLink(String textoLink) {
        this.textoLink = textoLink;
    }

}
