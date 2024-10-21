/*
 * 
 */
package ar.com.santanderrio.obp.servicios.billetera.dto;

import java.util.List;

import ar.com.santanderrio.obp.servicios.billetera.common.BilleteraConstants;

/**
 * The Class BilleteraInDTO.
 */
public class BilleteraInDTO {

	/** The clave. */
	private String clave;

	/** The codigo area. */
	private String codigoArea;

	/** The cuenta acreditacion. */
	private Integer cuentaAcreditacion;

	/** The email. */
	private String email;

	/** The empresa seleccionada. */
	private String empresaSeleccionada;

	/** The idx cta tp. */
	private String idxCtaTp;

	/** The tarjetas activas. */
	private List<MedioDePagoBilleteraDTO> mediosDePagoActivos;

	/** The pregunta seguridad. */
	private String preguntaSeguridad;

	/** The respuesta seguridad. */
	private String respuestaSeguridad;

	/** The telefono. */
	private String telefono;

	/**
	 * Gets the clave.
	 *
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Gets the codigo area.
	 *
	 * @return the codigoArea
	 */
	public String getCodigoArea() {
		return codigoArea;
	}

	/**
	 * Gets the cuenta acreditacion.
	 *
	 * @return the cuentaAcreditacion
	 */
	public Integer getCuentaAcreditacion() {
		return cuentaAcreditacion;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the empresa seleccionada.
	 *
	 * @return the empresaSeleccionada
	 */
	public String getEmpresaSeleccionada() {
		return empresaSeleccionada;
	}

	/**
	 * Gets the idx cta tp.
	 *
	 * @return the idxCtaTp
	 */
	public String getIdxCtaTp() {
		return idxCtaTp;
	}

	/**
	 * Gets the medio de pago favorito.
	 *
	 * @return the medio de pago favorito
	 */
	public MedioDePagoBilleteraDTO getMedioDePagoFavorito() {
		for (MedioDePagoBilleteraDTO mp : mediosDePagoActivos) {
			if (BilleteraConstants.FAVORITO_S.equals(mp.getFavorito())) {
				return mp;
			}
		}
		return null;
	}

	/**
	 * Gets the medios de pago activos.
	 *
	 * @return the mediosDePagoActivos
	 */
	public List<MedioDePagoBilleteraDTO> getMediosDePagoActivos() {
		return mediosDePagoActivos;
	}

	/**
	 * Gets the pregunta seguridad.
	 *
	 * @return the preguntaSeguridad
	 */
	public String getPreguntaSeguridad() {
		return preguntaSeguridad;
	}

	/**
	 * Gets the principal.
	 *
	 * @return the principal
	 */
	public String getPrincipal() {
		return String.valueOf(getMedioDePagoFavorito().getCtaAsociada().getIndex());
	}

	/**
	 * Gets the respuesta seguridad.
	 *
	 * @return the respuestaSeguridad
	 */
	public String getRespuestaSeguridad() {
		return respuestaSeguridad;
	}

	/**
	 * Gets the telefono.
	 *
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * Gets the vincular.
	 *
	 * @return the vincular
	 */
	public String[] getVincular() {
		int idx = 0;
		String[] vincular = new String[mediosDePagoActivos.size()];
		for (MedioDePagoBilleteraDTO mp : mediosDePagoActivos) {
			vincular[idx] = String.valueOf(mp.getCtaAsociada().getIndex());
			idx++;
		}
		return vincular;
	}

	/**
	 * Sets the clave.
	 *
	 * @param clave
	 *            the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Sets the codigo area.
	 *
	 * @param codigoArea
	 *            the codigoArea to set
	 */
	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	/**
	 * Sets the cuenta acreditacion.
	 *
	 * @param cuentaAcreditacion
	 *            the cuentaAcreditacion to set
	 */
	public void setCuentaAcreditacion(Integer cuentaAcreditacion) {
		this.cuentaAcreditacion = cuentaAcreditacion;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the empresa seleccionada.
	 *
	 * @param empresaSeleccionada
	 *            the empresaSeleccionada to set
	 */
	public void setEmpresaSeleccionada(String empresaSeleccionada) {
		this.empresaSeleccionada = empresaSeleccionada;
	}

	/**
	 * Sets the idx cta tp.
	 *
	 * @param idxCtaTp
	 *            the idxCtaTp to set
	 */
	public void setIdxCtaTp(String idxCtaTp) {
		this.idxCtaTp = idxCtaTp;
	}

	/**
	 * Sets the medios de pago activos.
	 *
	 * @param mediosDePagoActivos
	 *            the mediosDePagoActivos to set
	 */
	public void setMediosDePagoActivos(List<MedioDePagoBilleteraDTO> mediosDePagoActivos) {
		this.mediosDePagoActivos = mediosDePagoActivos;
	}

	/**
	 * Sets the pregunta seguridad.
	 *
	 * @param preguntaSeguridad
	 *            the preguntaSeguridad to set
	 */
	public void setPreguntaSeguridad(String preguntaSeguridad) {
		this.preguntaSeguridad = preguntaSeguridad;
	}

	/**
	 * Sets the respuesta seguridad.
	 *
	 * @param respuestaSeguridad
	 *            the respuestaSeguridad to set
	 */
	public void setRespuestaSeguridad(String respuestaSeguridad) {
		this.respuestaSeguridad = respuestaSeguridad;
	}

	/**
	 * Sets the telefono.
	 *
	 * @param telefono
	 *            the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
