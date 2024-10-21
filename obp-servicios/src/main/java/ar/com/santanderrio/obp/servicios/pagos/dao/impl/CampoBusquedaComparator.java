/**
 * 
 */
package ar.com.santanderrio.obp.servicios.pagos.dao.impl;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

import ar.com.santanderrio.obp.servicios.pagos.entities.MedioPago;

/**
 * Calcula la distancia desde el inicio de una palabra al termino de busqueda y
 * compara ese valor para determinar el orden.<br>
 * A igualdad de distancias desempata por el orden alfabetico.<br>
 * 
 * @author sergio.e.goldentair
 *
 */
public class CampoBusquedaComparator implements Comparator<MedioPago> {
	/** Campo con el cual se mediara la distancia. */
	private String terminoBusqueda;

	/**
	 * Instantiates a new campo busqueda comparator.
	 *
	 * @param terminoBusqueda
	 *            the termino busqueda
	 */
	public CampoBusquedaComparator(String terminoBusqueda) {
		super();
		this.terminoBusqueda = terminoBusqueda;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(MedioPago object1, MedioPago object2) {
		int distanciaObj1 = obtenerDistancia(object1.getCampoBusqueda());
		int distanciaObj2 = obtenerDistancia(object2.getCampoBusqueda());
		if (distanciaObj1 < distanciaObj2) {
			return -1;
		} else if (distanciaObj1 == distanciaObj2) {
			return object1.getCampoBusqueda().compareToIgnoreCase(object2.getCampoBusqueda());
		} else {
			return 1;
		}
	}

	/**
	 * Obtener posicion del termino ignorando si esta mayuscula o minuscula.
	 *
	 * @param campo
	 *            the campo
	 * @return the int
	 */
	private int obtenerDistancia(String campo) {
		return StringUtils.indexOfIgnoreCase(campo, terminoBusqueda);
	}

}
