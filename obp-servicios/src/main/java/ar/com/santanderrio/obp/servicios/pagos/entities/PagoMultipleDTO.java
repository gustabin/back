/*
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.entities;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.comun.respuesta.TipoError;

/**
 * The Class PagoMultipleDTO.
 * 
 * @author ignacio.valek
 * @author manuel.vargas
 * @author emilio.watemberg
 * @since Dec 29, 2016
 */
public class PagoMultipleDTO {

	/**
	 * TRUE si todos los pagos fueron realizados con exito. FALSE en otro caso.
	 */
	private boolean todosOk = false;

	/** TRUE si todos los pagos tuvieron un unico error. */
	private boolean errorUnico = false;

	/** Si errorUnico=true, indica el tipo de error que tienen todos. */
	private TipoError tipoErrorUnico;

	/** Si errorUnico=true, indica el mensaje que lleva el error. */
	private String mensajeErrorUnico;

	/** Si errorUnico=true, indica si admite reintento para todos sus pagos. */
	private boolean reintentarErrorUnico = false;

	/** The id pagos ok. */
	private int[] idPagosOk = null;

	/** The pagos. */
	private List<PagoInEntity> pagos;

	/** The mensaje cabecera. */
	private String mensajeCabecera;

	/**
	 * Gets the todos OK.
	 *
	 * @return the todos OK
	 */
	public boolean isTodosOK() {
		return todosOk;
	}

	/**
	 * Sets the todos OK.
	 *
	 * @param todosOK
	 *            the new todos OK
	 */
	public void setTodosOK(boolean todosOK) {
		this.todosOk = todosOK;
	}

	/**
	 * Gets the pagos.
	 *
	 * @return the pagos
	 */
	public List<PagoInEntity> getPagos() {
		return pagos;
	}

	/**
	 * Sets the pagos.
	 *
	 * @param pagos
	 *            the new pagos
	 */
	public void setPagos(List<PagoInEntity> pagos) {
		this.pagos = pagos;
	}

	/**
	 * Gets the error unico.
	 *
	 * @return the error unico
	 */
	public boolean isErrorUnico() {
		return errorUnico;
	}

	/**
	 * Sets the error unico.
	 *
	 * @param errorUnico
	 *            the new error unico
	 */
	public void setErrorUnico(boolean errorUnico) {
		this.errorUnico = errorUnico;
	}

	/**
	 * Gets the tipo error unico.
	 *
	 * @return the tipo error unico
	 */
	public TipoError getTipoErrorUnico() {
		return tipoErrorUnico;
	}

	/**
	 * Sets the tipo error unico.
	 *
	 * @param tipoErrorUnico
	 *            the new tipo error unico
	 */
	public void setTipoErrorUnico(TipoError tipoErrorUnico) {
		this.tipoErrorUnico = tipoErrorUnico;
	}

	/**
	 * Gets the mensaje error unico.
	 *
	 * @return the mensaje error unico
	 */
	public String getMensajeErrorUnico() {
		return mensajeErrorUnico;
	}

	/**
	 * Sets the mensaje error unico.
	 *
	 * @param mensajeErrorUnico
	 *            the new mensaje error unico
	 */
	public void setMensajeErrorUnico(String mensajeErrorUnico) {
		this.mensajeErrorUnico = mensajeErrorUnico;
	}

	/**
	 * Gets the reintentar error unico.
	 *
	 * @return the reintentar error unico
	 */
	public boolean isReintentarErrorUnico() {
		return reintentarErrorUnico;
	}

	/**
	 * Sets the reintentar error unico.
	 *
	 * @param reintentarErrorUnico
	 *            the new reintentar error unico
	 */
	public void setReintentarErrorUnico(boolean reintentarErrorUnico) {
		this.reintentarErrorUnico = reintentarErrorUnico;
	}

	/**
	 * Checks if is todos ok.
	 *
	 * @return true, if is todos ok
	 */
	public boolean isTodosOk() {
		return todosOk;
	}

	/**
	 * Sets the todos ok.
	 *
	 * @param todosOk
	 *            the new todos ok
	 */
	public void setTodosOk(boolean todosOk) {
		this.todosOk = todosOk;
	}

	/**
	 * Gets the id pagos ok.
	 *
	 * @return the id pagos ok
	 */
	public int[] getIdPagosOk() {
		return idPagosOk;
	}

	/**
	 * Sets the id pagos ok.
	 *
	 * @param idPagosOk
	 *            the new id pagos ok
	 */
	public void setIdPagosOk(int[] idPagosOk) {
		this.idPagosOk = idPagosOk;
	}

	/**
	 * Sets the mensaje cabecera.
	 *
	 * @param mensajeCabecera
	 *            the new mensaje cabecera
	 */
	public void setMensajeCabecera(String mensajeCabecera) {
		this.mensajeCabecera = mensajeCabecera;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		return new ToStringBuilder(this).append("todosOk", todosOk).append("errorUnico", errorUnico)
				.append("tipoErrorUnico", tipoErrorUnico).append("mensajeErrorUnico", mensajeErrorUnico)
				.append("reintentarErrorUnico", reintentarErrorUnico).append("idPagosOk", idPagosOk)
				.append("pagos", pagos).append("mensajeCabecera", mensajeCabecera).toString();
	}
}
