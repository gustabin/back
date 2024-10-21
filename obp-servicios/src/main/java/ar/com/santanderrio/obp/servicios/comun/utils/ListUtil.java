/*
 * 
 */
package ar.com.santanderrio.obp.servicios.comun.utils;

import java.util.Collections;
import java.util.List;

/**
 * Util para operaciones con listas.
 *
 * @author emilio.watemberg
 * @since Dec 19, 2016.
 */
public final class ListUtil {

	/**
	 * Constructor por defecto.
	 */
	private ListUtil() {
		// no hace nada por el momento.
	}

	/**
	 * Null check in an enhanced for loop.
	 *
	 * @param <E>
	 *            the element type
	 * @param other
	 *            the other
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> safe(List<E> other) {
		return other == null ? Collections.EMPTY_LIST : other;
	}
}
