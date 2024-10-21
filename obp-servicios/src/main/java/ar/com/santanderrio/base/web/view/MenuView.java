/*
 * 
 */
package ar.com.santanderrio.base.web.view;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class MenuView.
 */
public class MenuView extends OpcionView {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The elementos. */
	private List<MenuView> elementos;

	/**
	 * Gets the elementos.
	 *
	 * @return the elementos
	 */
	public List<MenuView> getElementos() {
		return elementos;
	}

	/**
	 * Setter para elementos.
	 *
	 * @param elementos
	 *            el nuevo elementos
	 */
	public void setElementos(List<MenuView> elementos) {
		this.elementos = elementos;
	}

	/**
	 * Es hoja si no tiene elementos.
	 *
	 * @return true si no tiene elementos
	 */
	public boolean isHoja() {
		return this.elementos == null || this.elementos.isEmpty();
	}

	/**
	 * Adds the elemento.
	 *
	 * @param elemento
	 *            the elemento
	 * @return the menu view
	 */
	public MenuView addElemento(MenuView elemento) {
		if (this.elementos == null) {
			this.elementos = new ArrayList<MenuView>();
		}
		this.elementos.add(elemento);
		return this;
	}

}
