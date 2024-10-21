/*
 * 
 */
package ar.com.santanderrio.obp.servicios.inversiones.titulos.licitaciones.view;

import java.util.ArrayList;
import java.util.List;

import ar.com.santanderrio.base.web.view.View;

/**
 * The Class BuscadorEspeciesView.
 */
public class BuscadorEspeciesView extends View{
	
	/** The serial VersionUID. */
	private static final long serialVersionUID = 5998380578087089665L;

	/** The lista especies. */
	List<EspeciesView> listaEspecies = new ArrayList<EspeciesView>();
	
	/** The mensaje. */
	String mensaje;

	/**
	 * Gets the lista especies.
	 *
	 * @return the listaEspecies
	 */
	public List<EspeciesView> getListaEspecies() {
		return listaEspecies;
	}

	/**
	 * Sets the lista especies.
	 *
	 * @param listaEspecies
	 *            the listaEspecies to set
	 */
	public void setListaEspecies(List<EspeciesView> listaEspecies) {
		this.listaEspecies = listaEspecies;
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
}
