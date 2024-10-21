/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import ar.com.santanderrio.obp.base.entities.Entity;
import ar.com.santanderrio.obp.base.respuesta.entities.EstadoRespuesta;

/**
 * Engloba el resultado de una transacción de iatx Número de comprobante, fecha
 * y hora.
 *
 * @author b039542
 */
public class ResultadoTransaccion extends Entity {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The numero comprobante. */
	private String numeroComprobante = null;

	/** The fecha transaccion. */
	private Date fechaTransaccion = null;

	/** The estado respuesta. */
	private EstadoRespuesta estadoRespuesta = null;

	/** The codigo respuesta. */
	private Integer codigoRespuesta = null;

	/** The sistema asociado error. */
	private String sistemaAsociadoError = null;

	/** The mensaje error. */
	private String mensajeError = null;

	/** The descripciones de error. */
	private List<String> descripcionesDeError;

	/**
	 * Instantiates a new resultado transaccion.
	 */
	public ResultadoTransaccion() {
		super();
	}

	/**
	 * Instantiates a new resultado transaccion.
	 *
	 * @param numeroComprobante
	 *            the numero comprobante
	 * @param fechaTransaccion
	 *            the fecha transaccion
	 * @param estadoRespuesta
	 *            the estado respuesta
	 * @param codigoRespuesta
	 *            the codigo respuesta
	 * @param sistemaAsociadoError
	 *            the sistema asociado error
	 * @param mensajeError
	 *            the mensaje error
	 */
	public ResultadoTransaccion(String numeroComprobante, Date fechaTransaccion, EstadoRespuesta estadoRespuesta,
			Integer codigoRespuesta, String sistemaAsociadoError, String mensajeError) {
		this();
		this.numeroComprobante = numeroComprobante;
		this.fechaTransaccion = fechaTransaccion;
		this.estadoRespuesta = estadoRespuesta;
		this.codigoRespuesta = codigoRespuesta;
		this.sistemaAsociadoError = sistemaAsociadoError;
		this.mensajeError = mensajeError;
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
	 * Sets the estado respuesta.
	 *
	 * @param estadoRespuesta
	 *            the new estado respuesta
	 */
	public void setEstadoRespuesta(EstadoRespuesta estadoRespuesta) {
		this.estadoRespuesta = estadoRespuesta;
	}

	/**
	 * Gets the codigo respuesta.
	 *
	 * @return the codigo respuesta
	 */
	public Integer getCodigoRespuesta() {
		return codigoRespuesta;
	}

	/**
	 * Sets the codigo respuesta.
	 *
	 * @param codigoRespuesta
	 *            the new codigo respuesta
	 */
	public void setCodigoRespuesta(Integer codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	/**
	 * Gets the sistema asociado error.
	 *
	 * @return the sistema asociado error
	 */
	public String getSistemaAsociadoError() {
		return sistemaAsociadoError;
	}

	/**
	 * Sets the sistema asociado error.
	 *
	 * @param sistemaAsociadoError
	 *            the new sistema asociado error
	 */
	public void setSistemaAsociadoError(String sistemaAsociadoError) {
		this.sistemaAsociadoError = sistemaAsociadoError;
	}

	/**
	 * Gets the mensaje error.
	 *
	 * @return the mensaje error
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * Sets the mensaje error.
	 *
	 * @param mensajeError
	 *            the new mensaje error
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * Gets the numero comprobante.
	 *
	 * @return the numero comprobante
	 */
	public String getNumeroComprobante() {
		return numeroComprobante;
	}

	/**
	 * Sets the numero comprobante.
	 *
	 * @param numeroComprobante
	 *            the new numero comprobante
	 */
	public void setNumeroComprobante(String numeroComprobante) {
		this.numeroComprobante = numeroComprobante;
	}

	/**
	 * Gets the fecha transaccion.
	 *
	 * @return the fecha transaccion
	 */
	public Date getFechaTransaccion() {
		return fechaTransaccion == null ? null : new Date(fechaTransaccion.getTime());
	}

	/**
	 * Sets the fecha transaccion.
	 *
	 * @param fechaTransaccion
	 *            the new fecha transaccion
	 */
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion == null ? null : new Date(fechaTransaccion.getTime());
	}

	/**
	 * Gets the descripciones de error.
	 *
	 * @return the descripciones de error
	 */
	public List<String> getDescripcionesDeError() {
		return descripcionesDeError;
	}

	/**
	 * Sets the descripciones de error.
	 *
	 * @param descripcionesDeError
	 *            the new descripciones de error
	 */
	public void setDescripcionesDeError(List<String> descripcionesDeError) {
		this.descripcionesDeError = descripcionesDeError;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ar.com.santanderrio.obp.base.entities.Entity#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(numeroComprobante);
		hcb.append(fechaTransaccion);
		hcb.append(estadoRespuesta);
		hcb.append(codigoRespuesta);
		hcb.append(sistemaAsociadoError);
		hcb.append(mensajeError);
		return hcb.toHashCode();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ar.com.santanderrio.obp.base.entities.Entity#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ResultadoTransaccion other = (ResultadoTransaccion) obj;
		if (numeroComprobante == null) {
			if (other.numeroComprobante != null) {
				return false;
			}
		} else if (!numeroComprobante.equals(other.numeroComprobante)) {
			return false;
		}
		return true;
	}

}
