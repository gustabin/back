/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.sesion.web.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ar.com.santanderrio.obp.servicios.agenda.destinatarios.entity.DestinatarioEntity;
import ar.com.santanderrio.obp.servicios.comun.ContadorIntentos;

/**
 * The Class SesionAgendaDestinatarios.
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SesionAgendaDestinatarios {

	/** The contador intentos alta. */
	private ContadorIntentos contadorAlta;

	/** The cuit cuil. */
	private String cuitCuil;

	/** The lista destinatarios entity. */
	private List<DestinatarioEntity> destinatariosEntity = new ArrayList<DestinatarioEntity>();

	/** The id destinatario seleccionado. */
	private int idDestinatarioSeleccionado;

	/**
	 * Gets the contador alta.
	 *
	 * @return the contador alta
	 */
	public ContadorIntentos getContadorAlta() {
		return contadorAlta;
	}

	/**
	 * Sets the contador alta.
	 *
	 * @param contadorAlta
	 *            the new contador alta
	 */
	public void setContadorAlta(ContadorIntentos contadorAlta) {
		this.contadorAlta = contadorAlta;
	}

	/**
	 * Gets the cuit cuil.
	 *
	 * @return the cuit cuil
	 */
	public String getCuitCuil() {
		return cuitCuil;
	}

	/**
	 * Sets the cuit cuil.
	 *
	 * @param cuitCuil
	 *            the new cuit cuil
	 */
	public void setCuitCuil(String cuitCuil) {
		this.cuitCuil = cuitCuil;
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
	 * Inicializar contador reintentos.
	 *
	 * @param maxIntentos
	 *            the max intentos
	 */
	public void inicializarContadorReintentos(int maxIntentos) {
		if (this.contadorAlta == null) {
			this.contadorAlta = new ContadorIntentos(maxIntentos);
		}
	}

	/**
	 * Gets the id destinatario seleccionado.
	 *
	 * @return the id destinatario seleccionado
	 */
	public int getIdDestinatarioSeleccionado() {
		return idDestinatarioSeleccionado;
	}

	/**
	 * Sets the id destinatario seleccionado.
	 *
	 * @param idDestinatarioSeleccionado
	 *            the new id destinatario seleccionado
	 */
	public void setIdDestinatarioSeleccionado(int idDestinatarioSeleccionado) {
		this.idDestinatarioSeleccionado = idDestinatarioSeleccionado;
	}

	/**
	 * Obtener destinatario entity seleccionado.
	 *
	 * @return the destinatario entity
	 */
	public DestinatarioEntity obtenerDestinatarioEntitySeleccionado() {
		if (this.destinatariosEntity != null && this.destinatariosEntity.size() > 0
				&& this.idDestinatarioSeleccionado != 0
				&& this.destinatariosEntity.get(this.idDestinatarioSeleccionado) != null) {
			return this.destinatariosEntity.get(this.idDestinatarioSeleccionado);
		} else {
			return null;
		}
	}
}
