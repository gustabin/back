/**
 * 
 */
package ar.com.santanderrio.obp.servicios.agenda.destinatarios.dto;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;

/**
 * The Class AgendaDestinatarioDTO.
 *
 * @author florencia.n.martinez
 */
public class AgendaDestinatarioDTO {

	/** The mensaje cabecera. */
	private String mensajeCabecera;

	/** The tiene error cuentas propias. */
	private boolean tieneErrorCuentasPropias;

	/** The tiene cuenta propias. */
	private boolean tieneCuentaPropias;

	/** The cantidad de cuentas propias. */
	private int cantCuentasPropias;

	/** The tiene error cuentas no propias. */
	private boolean tieneErrorCuentasNoPropias;

	/** The tiene error rellamado. */
	private boolean tieneErrorRellamado;

	/** The cantidad de cuentas no propias. */
	private int cantidadCuentasNoPropias;

	/** The tiene error time out. */
	private boolean tieneErrorTimeOut;

	/** The lista destinatarios. */
	private List<DestinatarioAgendaDTO> listaDestinatarios;

	/** The lista destinatarios entity. */
	private List<DestinatarioEntity> destinatariosEntity;

	/**
	 * Instantiates a new agenda destinatario DTO.
	 */
	public AgendaDestinatarioDTO() {
		super();
		listaDestinatarios = new ArrayList<DestinatarioAgendaDTO>();
		cantCuentasPropias = 0;
		cantidadCuentasNoPropias = 0;

	}

	/**
	 * Gets the mensaje cabecera.
	 *
	 * @return the mensajeCabecera
	 */
	public String getMensajeCabecera() {
		return mensajeCabecera;
	}

	/**
	 * Checks if is tiene error rellamado.
	 *
	 * @return true, if is tiene error rellamado
	 */
	public boolean isTieneErrorRellamado() {
		return tieneErrorRellamado;
	}

	/**
	 * Sets the tiene error rellamado.
	 *
	 * @param tieneErrorRellamado
	 *            the new tiene error rellamado
	 */
	public void setTieneErrorRellamado(boolean tieneErrorRellamado) {
		this.tieneErrorRellamado = tieneErrorRellamado;
	}

	/**
	 * Sets the mensaje cabecera.
	 *
	 * @param mensajeCabecera
	 *            the mensajeCabecera to set
	 */
	public void setMensajeCabecera(String mensajeCabecera) {
		this.mensajeCabecera = mensajeCabecera;
	}

	/**
	 * Gets the lista destinatarios.
	 *
	 * @return the listaDestinatarios
	 */
	public List<DestinatarioAgendaDTO> getListaDestinatarios() {
		return listaDestinatarios;
	}

	/**
	 * Sets the lista destinatarios.
	 *
	 * @param listaDestinatarios
	 *            the listaDestinatarios to set
	 */
	public void setListaDestinatarios(List<DestinatarioAgendaDTO> listaDestinatarios) {
		this.listaDestinatarios = listaDestinatarios;
	}

	/**
	 * Checks if is tiene error cuentas propias.
	 *
	 * @return true, if is tiene error cuentas propias
	 */
	public boolean isTieneErrorCuentasPropias() {
		return tieneErrorCuentasPropias;
	}

	/**
	 * Sets the tiene error cuentas propias.
	 *
	 * @param tieneErrorCuentasPropias
	 *            the new tiene error cuentas propias
	 */
	public void setTieneErrorCuentasPropias(boolean tieneErrorCuentasPropias) {
		this.tieneErrorCuentasPropias = tieneErrorCuentasPropias;
	}

	/**
	 * Checks if is tiene error cuentas no propias.
	 *
	 * @return true, if is tiene error cuentas no propias
	 */
	public boolean isTieneErrorCuentasNoPropias() {
		return tieneErrorCuentasNoPropias;
	}

	/**
	 * Sets the tiene error cuentas no propias.
	 *
	 * @param tieneErrorCuentasNoPropias
	 *            the new tiene error cuentas no propias
	 */
	public void setTieneErrorCuentasNoPropias(boolean tieneErrorCuentasNoPropias) {
		this.tieneErrorCuentasNoPropias = tieneErrorCuentasNoPropias;
	}

	/**
	 * Gets the cant cuentas propias.
	 *
	 * @return the cant cuentas propias
	 */
	public int getCantCuentasPropias() {
		return cantCuentasPropias;
	}

	/**
	 * Sets the cant cuentas propias.
	 *
	 * @param cantCuentasPropias
	 *            the new cant cuentas propias
	 */
	public void setCantCuentasPropias(int cantCuentasPropias) {
		this.cantCuentasPropias = cantCuentasPropias;
	}

	/**
	 * Gets the cantidad cuentas no propias.
	 *
	 * @return the cantidad cuentas no propias
	 */
	public int getCantidadCuentasNoPropias() {
		return cantidadCuentasNoPropias;
	}

	/**
	 * Sets the cantidad cuentas no propias.
	 *
	 * @param cantidadCuentasNoPropias
	 *            the new cantidad cuentas no propias
	 */
	public void setCantidadCuentasNoPropias(int cantidadCuentasNoPropias) {
		this.cantidadCuentasNoPropias = cantidadCuentasNoPropias;
	}

	/**
	 * Checks if is tiene error time out.
	 *
	 * @return true, if is tiene error time out
	 */
	public boolean isTieneErrorTimeOut() {
		return tieneErrorTimeOut;
	}

	/**
	 * Sets the tiene error time out.
	 *
	 * @param tieneErrorTimeOut
	 *            the new tiene error time out
	 */
	public void setTieneErrorTimeOut(boolean tieneErrorTimeOut) {
		this.tieneErrorTimeOut = tieneErrorTimeOut;
	}

	/**
	 * Gets the destinatarios entity.
	 *
	 * @return the destinatariosEntity
	 */
	public List<DestinatarioEntity> getDestinatariosEntity() {
		return destinatariosEntity;
	}

	/**
	 * Sets the destinatarios entity.
	 *
	 * @param destinatariosEntity
	 *            the destinatariosEntity to set
	 */
	public void setDestinatariosEntity(List<DestinatarioEntity> destinatariosEntity) {
		this.destinatariosEntity = destinatariosEntity;
	}

	/**
	 * Checks if is tiene cuenta propias.
	 *
	 * @return the tieneCuentaPropias
	 */
	public final boolean isTieneCuentaPropias() {
		return tieneCuentaPropias;
	}

	/**
	 * Sets the tiene cuenta propias.
	 *
	 * @param tieneCuentaPropias
	 *            the tieneCuentaPropias to set
	 */
	public final void setTieneCuentaPropias(boolean tieneCuentaPropias) {
		this.tieneCuentaPropias = tieneCuentaPropias;
	}

	/**
	 * HashCode.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(mensajeCabecera);
		hcb.append(tieneErrorCuentasPropias);
		return hcb.toHashCode();
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
		AgendaDestinatarioDTO other = (AgendaDestinatarioDTO) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(mensajeCabecera, other.getMensajeCabecera());
		eb.append(tieneErrorCuentasPropias, other.isTieneErrorCuentasPropias());
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
		return new ToStringBuilder(this).append("mensajeCabecera", mensajeCabecera)
				.append("listaDestinatarios", listaDestinatarios)
				.append("tieneErrorCuentasPropias", tieneErrorCuentasPropias).toString();
	}

}