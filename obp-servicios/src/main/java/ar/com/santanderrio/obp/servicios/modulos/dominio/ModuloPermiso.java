/**
 * 
 */
package ar.com.santanderrio.obp.servicios.modulos.dominio;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import ar.com.santanderrio.obp.servicios.home.web.view.AccionController;

/**
 * The Class ModuloPermiso.
 *
 * @author sergio.e.goldentair
 */
public class ModuloPermiso implements Serializable {
	/** Serial. */
	private static final long serialVersionUID = -6101157860735770456L;
	/** Codigo del modulo excluido. */
	private AccionController accionController;
	/** Mensaje del modulo en caso de que requiera mostrar info. */
	private String mensaje;
	/** Indicar si el permiso es sobre una accion del topbar, home o interna. */
	private boolean menu;
	/**
	 * Indica si el modulo se puede acceder o no o si debe utilizar un mensaje
	 * si llega a la vista.
	 */
	private ModuloEstado moduloEstado;

	/**
	 * Gets the accion controller.
	 *
	 * @return the accionController
	 */
	public AccionController getAccionController() {
		return accionController;
	}

	/**
	 * Sets the accion controller.
	 *
	 * @param accionController
	 *            the accionController to set
	 */
	public void setAccionController(AccionController accionController) {
		this.accionController = accionController;
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
	 * Sets the mensaje.
	 *
	 * @param mensaje
	 *            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Gets the modulo estado.
	 *
	 * @return the moduloEstado
	 */
	public ModuloEstado getModuloEstado() {
		return moduloEstado;
	}

	/**
	 * Sets the modulo estado.
	 *
	 * @param moduloEstado
	 *            the moduloEstado to set
	 */
	public void setModuloEstado(ModuloEstado moduloEstado) {
		this.moduloEstado = moduloEstado;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(accionController);
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

		ModuloPermiso other = (ModuloPermiso) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(accionController, other.getAccionController());
		return eb.isEquals();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("Accion", accionController).append("Estado", moduloEstado)
				.append("Menu", menu).append("Mensaje", mensaje).toString();
	}

	/**
	 * Indica si el modulo tiene permiso para ser mostrado.
	 *
	 * @return true, if successful
	 */
	public boolean tienePermisoDeVisibilidad() {
		return ModuloEstado.MOSTRAR.equals(moduloEstado);
	}

	/**
	 * Indica si el modulo debe mostrar mensaje.
	 * 
	 * @return Boolean
	 */
	public boolean muestraMensaje() {
		return ModuloEstado.MOSTRAR_MENSAJE.equals(moduloEstado);
	}

	/**
	 * El modulo no se muestra.
	 * 
	 * @return Boolean
	 */
	public boolean isModuloOculto() {
		return ModuloEstado.OCULTAR.equals(moduloEstado);
	}

	/**
	 * Checks if is menu.
	 *
	 * @return the menu
	 */
	public boolean isMenu() {
		return menu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu
	 *            the menu to set
	 */
	public void setMenu(boolean menu) {
		this.menu = menu;
	}

}
