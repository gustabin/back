/*
 * 
 */
package ar.com.santanderrio.obp.servicios.monedero.utils;

import java.util.Comparator;

/**
 * The Class TrjCompara.
 *
 * @author B008979
 * 
 *         Para cambiar la plantilla para este comentario de tipo generado vaya
 *         a Ventana&gt;Preferencias&gt;Java&gt;Generación de código&gt;Código y
 *         comentarios
 */
public class TrjCompara implements Comparator {

	/**
	 * Instantiates a new trj compara.
	 */
	public TrjCompara() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	/*
	 * (no Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object arg0, Object arg1) {
		Tarjeta t1 = (Tarjeta) arg0;
		Tarjeta t2 = (Tarjeta) arg1;
		if (t1.getUbicacion() < t2.getUbicacion()) {
			return -1;
		}
		if (t1.getUbicacion() == t2.getUbicacion()) {
			return 0;
		}
		return 1;
	}
}
