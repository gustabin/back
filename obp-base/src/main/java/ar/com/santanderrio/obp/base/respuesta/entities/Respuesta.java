package ar.com.santanderrio.obp.base.respuesta.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Respuesta.
 *
 * @author Jonatan_Bocian
 * @param <E>
 *            the element type
 */
public class Respuesta<E> extends Entity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * ERROR : La propiedad respuesta no tiene informacion que se deba
	 * considerar (tampoco la propiedad respuestaVacia) La propiedad
	 * itemsMensajeRespuesta contiene informacion de valor WARNING: La propiedad
	 * respusta y itemsMensajeRespuesta contienen informacion de valor OK: La
	 * propiedad respuesta tiene informacion de valor La propiedad
	 * itemsMensajeRespuesta no contiene informacion de valor .
	 */
	private EstadoRespuesta estadoRespuesta;

	/**
	 * El dato consultado si no hubo ningon error o si hubo errores parciales.
	 */
	private E respuesta;

	/**
	 * Lista de mensajes asociados a la consulta: Viene con datos cuando hay
	 * alg�n mensaje asociado a la consulta que se realizon.
	 * 
	 */
	private List<ItemMensajeRespuesta> itemsMensajeRespuesta;

	/**
	 * flag redundante que indica si la respuesta obtenida es vacia (no es util
	 * esta informacion para casos de condicion de error).
	 */
	private boolean respuestaVacia;

	/**
	 * Este flag estara siempre en false y solo se utiliza para situaciones en
	 * las que se cierra la session del usuario y no se quiere loguear el
	 * outbound del servicio invocado ya que esto provoca que se cree una nueva
	 * sesion, escriba en un log minusculo aparte y se retorne al browser la
	 * cookie de la nueva session dificultando el control de concurrencia.
	 */
	private Boolean skipLog = Boolean.FALSE;

	/**
	 * Instantiates a new respuesta.
	 */
	public Respuesta() {

	}

	/**
	 * Instantiates a new respuesta.
	 *
	 * @param respuesta
	 *            the respuesta
	 */
	public Respuesta(Respuesta<E> respuesta) {
		this.estadoRespuesta = respuesta.getEstadoRespuesta();
		if (respuesta.getItemsMensajeRespuesta() != null) {
			this.itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>(respuesta.getItemsMensajeRespuesta());
		}
		this.respuestaVacia = respuesta.isRespuestaVacia();
		this.respuesta = respuesta.getRespuesta();
	}

	/**
	 * Checks if is respuesta vacia.
	 *
	 * @return true, if is respuesta vacia
	 */
	public boolean isRespuestaVacia() {
		return respuestaVacia;
	}

	/**
	 * Setter para respuesta vacia.
	 *
	 * @param respuestaVacia
	 *            el nuevo respuesta vacia
	 */
	public void setRespuestaVacia(boolean respuestaVacia) {
		this.respuestaVacia = respuestaVacia;
	}

	/**
	 * Gets the estado respuesta.
	 *
	 * @return the estado respuesta
	 */
	public EstadoRespuesta getEstadoRespuesta() {
		return estadoRespuesta;
	}

	/**
	 * Setter para estado respuesta.
	 *
	 * @param estadoRespuesta
	 *            el nuevo estado respuesta
	 */
	public void setEstadoRespuesta(EstadoRespuesta estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

	/**
	 * Gets the respuesta.
	 *
	 * @return the respuesta
	 */
	public E getRespuesta() {
		return respuesta;
	}

	/**
	 * Setter para respuesta.
	 *
	 * @param respuesta
	 *            el nuevo respuesta
	 */
	public void setRespuesta(E respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * Gets the items mensaje respuesta.
	 *
	 * @return the items mensaje respuesta
	 */
	public List<ItemMensajeRespuesta> getItemsMensajeRespuesta() {
		return itemsMensajeRespuesta;
	}

	/**
	 * Setter para item mensaje respuesta.
	 *
	 * @param itemMensajeRespuesta
	 *            el nuevo item mensaje respuesta
	 */
	public void setItemMensajeRespuesta(List<ItemMensajeRespuesta> itemMensajeRespuesta) {
		this.itemsMensajeRespuesta = itemMensajeRespuesta;
	}

	/**
	 * Gets the skip log.
	 *
	 * @return the skipLog
	 */
	public Boolean getSkipLog() {
		return skipLog;
	}

	/**
	 * Sets the skip log.
	 *
	 * @param skipLog
	 *            the skipLog to set
	 */
	public void setSkipLog(Boolean skipLog) {
		this.skipLog = skipLog;
	}

	/**
	 * Adds the.
	 *
	 * @param itemMensajeRespuesta
	 *            the item mensaje respuesta
	 */
	public void add(ItemMensajeRespuesta itemMensajeRespuesta) {
		this.addAll(Arrays.asList(itemMensajeRespuesta));
	}

	/**
	 * Adds the all.
	 *
	 * @param itemsMensajeRespuesta
	 *            the items mensaje respuesta
	 */
	public void addAll(List<ItemMensajeRespuesta> itemsMensajeRespuesta) {
		if (this.getItemsMensajeRespuesta() == null) {
			this.itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>();
		}
		this.itemsMensajeRespuesta.addAll(itemsMensajeRespuesta);
	}

	/**
	 * Copy.
	 *
	 * @param <T>
	 *            the generic type
	 * @param class1
	 *            the class1
	 * @param origin
	 *            the origin
	 * @return the respuesta
	 */
	public static <T> Respuesta<T> copy(Class<T> class1, Respuesta<?> origin) {
		return copy(origin);
	}

	public static <T> Respuesta<T> copy(Respuesta<?> origin) {
		Respuesta<T> copy = new Respuesta<T>();

		copy.estadoRespuesta = origin.getEstadoRespuesta();
		if (origin.getItemsMensajeRespuesta() != null) {
			copy.itemsMensajeRespuesta = new ArrayList<ItemMensajeRespuesta>(origin.getItemsMensajeRespuesta());
		}
		copy.setSkipLog(origin.getSkipLog());
		copy.respuestaVacia = origin.isRespuestaVacia();
		return copy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("Respuesta [estadoRespuesta=", estadoRespuesta)
				.append("respuesta=", respuesta).append("skipLog=", skipLog)
				.append("itemsMensajeRespuesta=", itemsMensajeRespuesta).append("respuestaVacia=", respuestaVacia)
				.append(']').toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(estadoRespuesta);
		hcb.append(itemsMensajeRespuesta);
		hcb.append(respuesta);
		hcb.append(skipLog);
		hcb.append(respuestaVacia);
		return hcb.toHashCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
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

		Respuesta<E> other = (Respuesta<E>) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(estadoRespuesta, other.getEstadoRespuesta());
		eb.append(itemsMensajeRespuesta, other.getItemsMensajeRespuesta());
		eb.append(respuesta, other.getRespuesta());
		eb.append(skipLog, other.getSkipLog());
		eb.append(respuestaVacia, other.isRespuestaVacia());
		return eb.isEquals();

	}

}
